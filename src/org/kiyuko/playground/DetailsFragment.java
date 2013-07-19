package org.kiyuko.playground;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DetailsFragment extends Fragment {

	public static final String PARAMETER_NAME = "org.kiyuko.playground.DetailsFragment.PARAMETER_NAME";
	public static final String PARAMETER_DESCRIPTION = "org.kiyuko.playground.DetailsFragment.PARAMETER_DESCRIPTION";
	public static final String PARAMETER_POSITION = "org.kiyuko.playground.DetailsFragment.PARAMETER_POSITION";

	private String name;
	private String description;
	private int position;

	private EditText nameEdit;
	private EditText descriptionEdit;

	public static DetailsFragment newInstance(String name, String description, int position) {

		DetailsFragment fragment;

		fragment = new DetailsFragment();

		fragment.name = name;
		fragment.description = description;
		fragment.position = position;

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view;

		view = inflater.inflate(R.layout.fragment_details, container, false);
		nameEdit = (EditText) view.findViewById(R.id.nameEdit);
		descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);

		if (savedInstanceState != null) {

			name = savedInstanceState.getString(PARAMETER_NAME);
			description = savedInstanceState.getString(PARAMETER_DESCRIPTION);
			position = savedInstanceState.getInt(PARAMETER_POSITION);
		}

		nameEdit.setText(name);
		descriptionEdit.setText(description);

		return view;
	}

	@Override
	public void onPause() {

		ItemDatabaseHelper dbHelper;
		Item item;

		// Retrieve data from the form
		name = nameEdit.getText().toString();
		description = descriptionEdit.getText().toString();

		// Ignore changes if either of the fields is empty
		if (name.length() <= 0 || description.length() <= 0) {

			super.onPause();

			return;
		}

		// Store the modified item
		dbHelper = new ItemDatabaseHelper(getActivity());
		item = new Item(name, description);

		dbHelper.set(position, item);

		super.onPause();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		outState.putString(PARAMETER_NAME, name);
		outState.putString(PARAMETER_DESCRIPTION, description);
		outState.putInt(PARAMETER_POSITION, position);

		super.onSaveInstanceState(outState);
	}
}
