package org.kiyuko.playground;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity implements ItemDetailsDialogFragment.Listener {

	private ItemDatabaseHelper dbHelper;
	private ListView listView;
	private SimpleCursorAdapter adapter;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Cursor cursor;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = getListView();

		dbHelper = new ItemDatabaseHelper(this);
		cursor = dbHelper.getAllItemsCursor();

		if (cursor != null) {

			adapter = new SimpleCursorAdapter(this,
					R.layout.item,
					cursor,
					new String[] {"name", "description"},
					new int[] {R.id.nameView, R.id.descriptionView});
			setListAdapter(adapter);
		}
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

				Intent intent;

				intent = new Intent(this, DetailsActivity.class);
				startActivity(intent);

				return true;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {

		Item item;

		item = dbHelper.get(position);

		if (item != null) {

			editItem(item, position);
		}
	}

	@Override
	public void onPositiveClick(DialogFragment fragment, Item item, int position) {

		dbHelper.set(position, item);
		adapter.changeCursor(dbHelper.getAllItemsCursor());

		// Scroll the ListView to the appropriate position
		listView.setSelection(position);
	}

	@Override
	public void onNegativeClick(DialogFragment fragment) {
		// Do nothing
	}

	private void addItem() {

		ItemDetailsDialogFragment dialog;

		dialog = new ItemDetailsDialogFragment(adapter.getCount());
		dialog.show(getFragmentManager(), "ItemEditDialogFragment");
	}

	private void editItem(Item item, int position) {

		ItemDetailsDialogFragment dialog;

		dialog = new ItemDetailsDialogFragment(item, position);
		dialog.show(getFragmentManager(), "ItemEditDialogFragment");
	}

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
