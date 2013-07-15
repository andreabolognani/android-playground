package org.kiyuko.playground;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity implements ItemEditDialogFragment.Listener {

	private SQLiteDatabase db;
	private ListView listView;
	private ListAdapter adapter;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Cursor cursor;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = getListView();

		cursor = new ItemDatabaseHelper(this).getAllItemsCursor();

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

		new ItemDatabaseHelper(this).addItem(item, position);

		// Scroll the ListView to the appropriate position
		listView.setSelection(position);
	}

	@Override
	public void onNegativeClick(DialogFragment fragment) {
		// Do nothing
	}

	private void addItem() {

		ItemEditDialogFragment dialog;

		dialog = new ItemEditDialogFragment(adapter.getCount());
		dialog.show(getFragmentManager(), "ItemEditDialogFragment");
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
