package org.kiyuko.playground;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ItemDetailsFragment extends Fragment {

	public static final String PARAMETER_ID = "org.kiyuko.playground.DetailsFragment.PARAMETER_ID";

	private ItemDatabaseHelper dbHelper;
	private long id;

	private EditText nameEdit;
	private EditText descriptionEdit;

	public static ItemDetailsFragment newInstance() {

		ItemDetailsFragment fragment;

		fragment = new ItemDetailsFragment();
		fragment.id = Item.INVALID_ID;

		return fragment;
	}

	public static ItemDetailsFragment newInstance(long id) {

		ItemDetailsFragment fragment;

		fragment = new ItemDetailsFragment();
		fragment.id = id;

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view;
		Item item;

		view = inflater.inflate(R.layout.fragment_item_details, container, false);
		nameEdit = (EditText) view.findViewById(R.id.nameEdit);
		descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);

		dbHelper = new ItemDatabaseHelper(getActivity());

		if (savedInstanceState != null) {

			// Restarted due to orientation change: the view status is restored
			// automatically, we just need to retrieve the item id
			id = savedInstanceState.getLong(PARAMETER_ID);
		}
		else {

			if (id == Item.INVALID_ID) {

				// New item: ask the database for a new id
				id = dbHelper.newId();
				item = new Item(id, "", "");
			}
			else {

				// Existing item: retrieve it from the database
				item = dbHelper.get(id);
			}

			nameEdit.setText(item.getName());
			descriptionEdit.setText(item.getDescription());
		}

		return view;
	}

	@Override
	public void onPause() {

		Item item;

		// Retrieve data from the form
		item = new Item(id,
				nameEdit.getText().toString(),
				descriptionEdit.getText().toString());

		// Ignore changes if either of the fields is empty
		if (item.getName().length() <= 0 || item.getDescription().length() <= 0) {

			super.onPause();

			return;
		}

		// Store the modified item
		dbHelper.put(item);
		dbHelper.close();

		super.onPause();
	}

	@Override
	public void onDestroy() {

		dbHelper.close();

		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		// Save the item id
		outState.putLong(PARAMETER_ID, id);

		super.onSaveInstanceState(outState);
	}
}
