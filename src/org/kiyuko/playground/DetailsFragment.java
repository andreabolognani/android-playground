package org.kiyuko.playground;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DetailsFragment extends Fragment {

	public static final String EXTRA_NAME = "org.kiyuko.playground.DetailsFragment.EXTRA_NAME";
	public static final String EXTRA_DESCRIPTION = "org.kiyuko.playground.DetailsFragment.EXTRA_DESCRIPTION";
	public static final String EXTRA_POSITION = "org.kiyuko.playground.DetailsFragment.EXTRA_POSITION";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view;
		EditText nameEdit;
		EditText descriptionEdit;

		view = inflater.inflate(R.layout.fragment_details, container, false);
		nameEdit = (EditText) view.findViewById(R.id.nameEdit);
		descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);

		if (getArguments() != null) {

			nameEdit.setText(getArguments().getString(EXTRA_NAME));
			descriptionEdit.setText(getArguments().getString(EXTRA_DESCRIPTION));
		}

		return view;
	}
}
