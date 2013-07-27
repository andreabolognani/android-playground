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
	private long id;
	private boolean dualPane;

	public void update() {

		dualPane = false;

		if (dbHelper.isEmpty()) {

			// Show a message if no item has been created yet
			setContentView(R.layout.no_items);

			// Rebuild menu
			invalidateOptionsMenu();

			return;
		}

		id = getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE)
				.getLong(Common.KEY_ID, Item.INVALID_ID);

		if (id == Item.INVALID_ID) {

			// No previous selection, pick the first item
			id = dbHelper.getLowestId();

			getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE)
			.edit()
				.putLong(Common.KEY_ID, id)
			.commit();
		}

		setContentView(R.layout.activity_main);

		if (findViewById(R.id.list_container) != null) {

			getFragmentManager().beginTransaction()
				.replace(R.id.list_container, new ItemListFragment())
			.commit();
		}

		if (findViewById(R.id.details_container) != null) {

			dualPane = true;

			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, new ViewDetailsFragment())
			.commit();
		}

		// Rebuild menu
		invalidateOptionsMenu();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		dbHelper = new ItemDatabaseHelper(this);
	}

	@Override
	protected void onResume() {

		super.onResume();

		update();
	}

	@Override
	protected void onPause() {

		getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE)
		.edit()
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

		// Show the remove action only in dual-pane mode
		menu.findItem(R.id.action_remove).setVisible(dualPane);

		return super.onCreateOptionsMenu(menu);
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

				Common.shortToast(this, R.string.notImplemented);

				return true;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}

	public void onItemClick(AdapterView parent, View view, int position, long id) {

		this.id = id;

		getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE)
		.edit()
			.putLong(Common.KEY_ID, id)
		.commit();

		// Running in two-panes mode
		if (dualPane) {

			// Replace the current details fragment with a new one
			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, new ViewDetailsFragment())
			.commit();
		}
	}

	private void addItem() {

		Intent intent;

		id = dbHelper.newId();
		getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE)
		.edit()
			.putLong(Common.KEY_ID, id)
		.commit();

		intent = new Intent(this, DetailsActivity.class);

		startActivity(intent);
	}

	private void removeItem() {

		dbHelper.remove(id);

		id = Item.INVALID_ID;
		getSharedPreferences(Common.SHARED_PREFERENCES_FILE, MODE_PRIVATE)
		.edit()
			.putLong(Common.KEY_ID, id)
		.commit();

		update();
	}
}
