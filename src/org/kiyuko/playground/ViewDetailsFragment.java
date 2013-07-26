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

	public static ViewDetailsFragment newInstance() {

		ViewDetailsFragment fragment;

		fragment = new ViewDetailsFragment();
		fragment.id = Item.INVALID_ID;

		return fragment;
	}

	public static ViewDetailsFragment newInstance(long id) {

		ViewDetailsFragment fragment;

		fragment = new ViewDetailsFragment();
		fragment.id = id;

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view;
		Item item;

		if (id == Item.INVALID_ID) {

			// Just display a message
			return inflater.inflate(R.layout.no_items, container, false);
		}

		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_view_details, container, false);
		nameView = (TextView) view.findViewById(R.id.nameView);
		descriptionView = (TextView) view.findViewById(R.id.descriptionView);

		dbHelper = new ItemDatabaseHelper(activity);

		if (savedInstanceState != null) {

			// Restarted due to orientation change: the view status is restored
			// automatically, we just need to retrieve the item id
			id = savedInstanceState.getLong(Common.KEY_ID);
		}
		else {

			// Show details for selected item
			item = dbHelper.get(id);

			if (item != null) {

				nameView.setText(item.getName());
				descriptionView.setText(item.getDescription());
			}
		}

		return view;
	}

	@Override
	public void onDestroy() {

		if (dbHelper != null) {

			dbHelper.close();
		}

		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		// Save the item id
		outState.putLong(Common.KEY_ID, id);

		super.onSaveInstanceState(outState);
	}
}
