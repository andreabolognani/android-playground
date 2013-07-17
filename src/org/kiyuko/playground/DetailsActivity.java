package org.kiyuko.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DetailsFragment detailsFragment;
		Intent intent;
		Bundle extras;
		Bundle arguments;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		if (findViewById(R.id.fragment_container) != null) {

			// Only add the fragment the first time the activity is created
			if (savedInstanceState != null) {
				return;
			}

			// Get the intent that has started this activity
			intent = getIntent();
			extras = intent.getExtras();

			detailsFragment = new DetailsFragment();
			arguments = new Bundle();

			// Copy parameters from the intent's extras to the fragment's arguments
			arguments.putString(DetailsFragment.EXTRA_NAME, extras.getString(DetailsFragment.EXTRA_NAME));
			arguments.putString(DetailsFragment.EXTRA_DESCRIPTION, extras.getString(DetailsFragment.EXTRA_DESCRIPTION));
			arguments.putInt(DetailsFragment.EXTRA_POSITION, extras.getInt(DetailsFragment.EXTRA_POSITION));
			detailsFragment.setArguments(arguments);

			// Show the fragment
			getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, detailsFragment)
			.commit();
		}
	}
}
