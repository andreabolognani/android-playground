package org.kiyuko.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class ItemDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		ItemDetailsFragment detailsFragment;
		Intent intent;
		Bundle extras;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_details);

		if (findViewById(R.id.fragment_container) != null) {

			// Only add the fragment the first time the activity is created
			if (savedInstanceState != null) {
				return;
			}

			// Get the intent that has started this activity
			intent = getIntent();
			extras = intent.getExtras();

			if (extras != null && extras.containsKey(ItemDetailsFragment.PARAMETER_ID)) {

				// Existing item: pass the id to the fragment
				detailsFragment = ItemDetailsFragment.newInstance(extras.getLong(ItemDetailsFragment.PARAMETER_ID));
			}
			else {

				// New item
				detailsFragment = ItemDetailsFragment.newInstance();
			}


			// Show the fragment
			getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, detailsFragment)
			.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent intent;

		switch(item.getItemId()) {

			case android.R.id.home:

				intent = new Intent(this, ItemListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(intent);

				return true;

			default:

				return super.onOptionsItemSelected(item);
		}
	}
}
