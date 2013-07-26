package org.kiyuko.playground;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

	private ItemDatabaseHelper dbHelper;
	private long id = Item.INVALID_ID;
	private boolean dualPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		dbHelper = new ItemDatabaseHelper(this);
	}

	@Override
	protected void onResume() {

		long lowestId;
		long highestId;

		super.onResume();

		lowestId = dbHelper.getLowestId();
		highestId = dbHelper.getHighestId();

		if (highestId == 0) {

			dualPane = false;

			// Show a message if no item has been created
			setContentView(R.layout.no_items);

			return;
		}

		id = getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE).getLong(Common.KEY_ID, Item.INVALID_ID);

		if (id == Item.INVALID_ID) {

			// No previous selection, pick the first one
			id = lowestId;
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
				.replace(R.id.details_container, ViewDetailsFragment.newInstance(id))
			.commit();
		}
	}

	@Override
	protected void onPause() {

		getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE).edit()
			.putLong(Common.KEY_ID, id)
		.commit();

		super.onPause();
	}

	@Override
	protected void onDestroy() {

		dbHelper.close();

		super.onDestroy();
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

			case R.id.action_remove:

				removeItem();

				return true;

			case R.id.action_settings:

				Common.notImplemented(this);

				return true;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}

	public void onItemClick(AdapterView parent, View view, int position, long id) {

		this.id = id;

		// Running in two-panes mode
		if (dualPane) {

			// Replace the current details fragment with a new one
			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, ViewDetailsFragment.newInstance(id))
			.commit();
		}
	}

	private void addItem() {

		Intent intent;

		intent = new Intent(this, DetailsActivity.class);

		startActivity(intent);
	}

	private void removeItem() {

		dbHelper.remove(id);

		onResume();

		invalidateOptionsMenu();
	}
}
