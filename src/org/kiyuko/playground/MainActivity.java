package org.kiyuko.playground;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
		items.add(new Item("1", "..."));
		items.add(new Item("2", "..."));
		items.add(new Item("3", "..."));

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

		ItemEditDialogFragment dialog;

		switch (menuItem.getItemId()) {

			case R.id.action_add:

				dialog = new ItemEditDialogFragment();
				dialog.show(getFragmentManager(), "ItemAddDialogFragment");

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

		notImplemented();
	}

	@Override
	public void onPositiveClick(DialogFragment fragment) {

		ItemEditDialogFragment dialog;

		dialog = (ItemEditDialogFragment) fragment;

		items.add(dialog.getItem());
		adapter.notifyDataSetChanged();

		// Scroll to the bottom of the ListView
		listView.setSelection(listView.getCount() - 1);
	}

	@Override
	public void onNegativeClick(DialogFragment fragment) {
		// Do nothing
	}

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
