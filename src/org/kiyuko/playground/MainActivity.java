package org.kiyuko.playground;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private ItemDatabaseHelper dbHelper;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbHelper = new ItemDatabaseHelper(this);

		adapter = new SimpleCursorAdapter(this,
				R.layout.item,
				null,
				new String[] {"name", "description"},
				new int[] {R.id.nameView, R.id.descriptionView});
		setListAdapter(adapter);
	}

	@Override
	protected void onResume() {

		Cursor cursor;

		cursor = dbHelper.getAllItemsCursor();

		if (cursor != null) {

			adapter.changeCursor(cursor);
		}

		super.onResume();
	}

	@Override
	protected void onDestroy() {

		dbHelper.close();

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {

		editItem((int) id);
	}

	private void addItem() {

		Intent intent;

		intent = new Intent(this, DetailsActivity.class);

		startActivity(intent);
	}

	private void editItem(int id) {

		Intent intent;

		intent = new Intent(this, DetailsActivity.class);
		intent.putExtra(DetailsFragment.PARAMETER_ID, id);

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
