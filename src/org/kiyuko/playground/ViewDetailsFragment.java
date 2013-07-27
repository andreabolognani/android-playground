package org.kiyuko.playground;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewDetailsFragment extends Fragment {

	private Activity activity;
	private ItemDatabaseHelper dbHelper;
	private long id;

	private TextView nameView;
	private TextView descriptionView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view;
		Item item;

		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_view_details, container, false);
		nameView = (TextView) view.findViewById(R.id.nameView);
		descriptionView = (TextView) view.findViewById(R.id.descriptionView);

		dbHelper = new ItemDatabaseHelper(activity);

		id = activity.getSharedPreferences(Common.SHARED_PREFERENCES_FILE, Activity.MODE_PRIVATE)
			.getLong(Common.KEY_ID, Item.INVALID_ID);

		// Show details for selected item
		item = dbHelper.get(id);

		if (item != null) {

			nameView.setText(item.getName());
			descriptionView.setText(item.getDescription());
		}

		return view;
	}

	@Override
	public void onDestroy() {

		dbHelper.close();

		super.onDestroy();
	}
}
