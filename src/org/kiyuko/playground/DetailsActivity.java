package org.kiyuko.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		getFragmentManager().beginTransaction()
			.replace(R.id.details_container, new EditDetailsFragment())
		.commit();
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
