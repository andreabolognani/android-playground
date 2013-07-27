package org.kiyuko.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		EditDetailsFragment detailsFragment;
		Intent intent;
		Bundle extras;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		if (findViewById(R.id.details_container) != null) {

			// Only add the fragment the first time the activity is created
			if (savedInstanceState != null) {
				return;
			}

			// Get the intent that has started this activity
			intent = getIntent();
			extras = intent.getExtras();

			if (extras != null && extras.containsKey(Common.KEY_ID)) {

				// Existing item: pass the id to the fragment
				detailsFragment = EditDetailsFragment.newInstance(extras.getLong(Common.KEY_ID));
			}
			else {

				// New item
				detailsFragment = EditDetailsFragment.newInstance();
			}


			// Show the fragment
			getFragmentManager().beginTransaction()
				.add(R.id.details_container, detailsFragment)
			.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent;

		switch(item.getItemId()) {

			case android.R.id.home:

				intent = new Intent(this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(intent);

				return true;

			default:

				return super.onOptionsItemSelected(item);
		}
	}
}
