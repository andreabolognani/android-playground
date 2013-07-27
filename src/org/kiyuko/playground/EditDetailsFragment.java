package org.kiyuko.playground;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditDetailsFragment extends Fragment {

	private Activity activity;
	private ItemDatabaseHelper dbHelper;
	private long id;

	private EditText nameEdit;
	private EditText descriptionEdit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view;
		Item item;

		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_item_details, container, false);
		nameEdit = (EditText) view.findViewById(R.id.nameEdit);
		descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);

		dbHelper = new ItemDatabaseHelper(activity);

		id = activity.getSharedPreferences(Common.SHARED_PREFERENCES_FILE, Activity.MODE_PRIVATE)
			.getLong(Common.KEY_ID, Item.INVALID_ID);

		item = null;

		if (id != Item.INVALID_ID) {

			// Existing item: retrieve it from the database
			item = dbHelper.get(id);
		}

		if (item == null) {

			// New item: start from an empty one
			item = new Item(id, getString(R.string.newItem), "");
		}

		nameEdit.setText(item.getName());
		descriptionEdit.setText(item.getDescription());

		return view;
	}

	@Override
	public void onPause() {

		Item item;

		// Retrieve data from the form
		item = new Item(id,
				nameEdit.getText().toString(),
				descriptionEdit.getText().toString());

		if (item.getName().length() <= 0 || item.getDescription().length() <= 0) {

			// Ignore changes if either of the fields is empty
			activity.getSharedPreferences(Common.SHARED_PREFERENCES_FILE, Activity.MODE_PRIVATE)
			.edit()
				.putLong(Common.KEY_ID, Item.INVALID_ID)
			.commit();

			super.onPause();

			return;
		}

		// Store the modified item
		dbHelper.put(item);

		super.onPause();
	}

	@Override
	public void onDestroy() {

		dbHelper.close();

		super.onDestroy();
	}
}
