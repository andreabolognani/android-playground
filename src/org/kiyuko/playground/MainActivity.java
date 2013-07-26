package org.kiyuko.playground;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

	private static final String PARAMETER_SELECTION_ID = "org.kiyuko.playground.MainActivity.PARAMETER_SELECTION_ID";

	private ItemDatabaseHelper dbHelper;
	private long selectionId = Item.INVALID_ID;
	private boolean dualPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		dbHelper = new ItemDatabaseHelper(this);

		if (savedInstanceState != null) {

			// Restore previous selection id
			selectionId = savedInstanceState.getLong(PARAMETER_SELECTION_ID);
		}
	}

	@Override
	protected void onStart() {

		long lowestId;
		long highestId;

		super.onStart();

		lowestId = dbHelper.getLowestId();
		highestId = dbHelper.getHighestId();

		if (highestId == 0) {

			dualPane = false;

			// Show a message if no item has been created
			setContentView(R.layout.no_items);

			return;
		}

		if (selectionId == Item.INVALID_ID) {

			// No previous selection, pick the first one
			selectionId = lowestId;
		}

		setContentView(R.layout.activity_main);

		if (findViewById(R.id.list_container) != null) {

			getFragmentManager().beginTransaction()
				.replace(R.id.list_container, ItemListFragment.newInstance())
			.commit();
		}

		if (findViewById(R.id.details_container) != null) {

			dualPane = true;

			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, ViewDetailsFragment.newInstance(selectionId))
			.commit();
		}
	}

	@Override
	protected void onDestroy() {

		dbHelper.close();

		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		outState.putLong(PARAMETER_SELECTION_ID, selectionId);

		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);

		if (dualPane) {

			// Showing details: make remove action visible
			menu.findItem(R.id.action_remove).setVisible(true);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		switch (menuItem.getItemId()) {

			case R.id.action_add:

				addItem();

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
		if (dualPane) {

			// Replace the current details fragment with a new one
			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, ViewDetailsFragment.newInstance(selectionId))
			.commit();
		}
	}

	private void addItem() {

		Intent intent;

		intent = new Intent(this, DetailsActivity.class);

		startActivity(intent);
	}

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
