package org.kiyuko.playground;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements ItemEditDialogFragment.Listener {

	private ListView listView;
	private ArrayList<Item> items;
	private ItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = getListView();

		items = new ArrayList<Item>();

		adapter = new ItemAdapter(this, items);
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

		editItem(adapter.getItem(position), position);
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
		adapter.notifyDataSetChanged();

		// Scroll the ListView to the appropriate position
		listView.setSelection(position);
	}

	@Override
	public void onNegativeClick(DialogFragment fragment) {
		// Do nothing
	}

	private void addItem() {

		ItemEditDialogFragment dialog;

		dialog = new ItemEditDialogFragment();
		dialog.show(getFragmentManager(), "ItemEditDialogFragment");
	}

	private void editItem(Item item, int position) {

		ItemEditDialogFragment dialog;

		dialog = new ItemEditDialogFragment(item, position);
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
