package org.kiyuko.playground;

import android.app.Activity;
import android.os.Bundle;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		DetailsFragment detailsFragment;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		if (findViewById(R.id.fragment_container) != null) {

			// Only add the fragment the first time the activity is created
			if (savedInstanceState != null) {
				return;
			}

			// Create a new fragment and show it
			detailsFragment = new DetailsFragment();

			getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, detailsFragment)
			.commit();
		}
	}
}
