package org.kiyuko.playground;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewDetailsFragment extends Fragment {

	public static final String PARAMETER_ID = "org.kiyuko.playground.DetailsFragment.PARAMETER_ID";

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

		view = inflater.inflate(R.layout.fragment_view_details, container, false);
		nameView = (TextView) view.findViewById(R.id.nameView);
		descriptionView = (TextView) view.findViewById(R.id.descriptionView);

		dbHelper = new ItemDatabaseHelper(getActivity());

		if (savedInstanceState != null) {

			// Restarted due to orientation change: the view status is restored
			// automatically, we just need to retrieve the item id
			id = savedInstanceState.getLong(PARAMETER_ID);
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
