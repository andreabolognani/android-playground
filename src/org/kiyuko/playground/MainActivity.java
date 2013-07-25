package org.kiyuko.playground;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

	private static final String PARAMETER_SELECTION_ID = "org.kiyuko.playground.MainActivity.PARAMETER_SELECTION_ID";

	private long selectionId = Item.INVALID_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState != null) {

			// Restore previous selection id
			selectionId = savedInstanceState.getLong(PARAMETER_SELECTION_ID);
		}

		if (findViewById(R.id.list_container) != null) {

			getFragmentManager().beginTransaction()
				.replace(R.id.list_container, ItemListFragment.newInstance())
			.commit();
		}

		if (findViewById(R.id.details_container) != null) {

			if (selectionId == Item.INVALID_ID) {

				// No previous selection
				getFragmentManager().beginTransaction()
					.replace(R.id.details_container, ViewDetailsFragment.newInstance())
				.commit();
			}
			else {

				// Restore previous selection
				getFragmentManager().beginTransaction()
					.replace(R.id.details_container, ViewDetailsFragment.newInstance(selectionId))
				.commit();
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		outState.putLong(PARAMETER_SELECTION_ID, selectionId);

		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		ItemListFragment fragment;

		switch (menuItem.getItemId()) {

			case R.id.action_add:

				fragment = (ItemListFragment) getFragmentManager().findFragmentById(R.id.list_container);

				if (fragment != null) {

					fragment.addItem();
				}

				return true;

			case R.id.action_settings:

				notImplemented();

				return true;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}

	public void onItemClick(AdapterView parent, View view, int position, long id) {

		selectionId = id;

		// Running in two-panes mode
		if (findViewById(R.id.details_container) != null) {

			// Replace the current details fragment with a new one
			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, ViewDetailsFragment.newInstance(selectionId))
			.commit();
		}
	}

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
