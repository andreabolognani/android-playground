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

			detailsFragment = DetailsFragment.newInstance(extras.getString(DetailsFragment.PARAMETER_NAME),
					extras.getString(DetailsFragment.PARAMETER_DESCRIPTION),
					extras.getInt(DetailsFragment.PARAMETER_POSITION));

			// Show the fragment
			getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, detailsFragment)
			.commit();
		}
	}
}
