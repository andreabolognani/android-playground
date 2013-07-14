package org.kiyuko.playground;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity implements ItemEditDialogFragment.Listener {

	private SQLiteDatabase db;
	private ListView listView;
	private ArrayList<Item> items;
	private ListAdapter adapter;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		ItemDatabaseHelper dbHelper;
		Cursor cursor;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = getListView();

		dbHelper = new ItemDatabaseHelper(this);
		db = dbHelper.getReadableDatabase();

		cursor = db.query("items", new String[] {"_id", "name", "description"}, null, null, null, null, null, null);

		adapter = new SimpleCursorAdapter(this,
				R.layout.item,
				cursor,
				new String[] {"name", "description"},
				new int[] {R.id.nameView, R.id.descriptionView});
		setListAdapter(adapter);
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

		//editItem(adapter.getItem(position), position);
	}

	@Override
	public void onPositiveClick(DialogFragment fragment, Item item, int position) {

		if (position >= 0) {

			// Existing item: replace the previous one
			items.set(position, item);
		}
		else {

			// New item: add it to the end of the list
			items.add(item);
		}
		//adapter.notifyDataSetChanged();

		// Scroll the ListView to the appropriate position
		listView.setSelection(position);
	}

	@Override
	public void onNegativeClick(DialogFragment fragment) {
		// Do nothing
	}

	private void addItem() {

		notImplemented();

//		ItemEditDialogFragment dialog;
//
//		dialog = new ItemEditDialogFragment();
//		dialog.show(getFragmentManager(), "ItemEditDialogFragment");
	}

	private void editItem(Item item, int position) {

		notImplemented();

//		ItemEditDialogFragment dialog;
//
//		dialog = new ItemEditDialogFragment(item, position);
//		dialog.show(getFragmentManager(), "ItemEditDialogFragment");
	}

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
