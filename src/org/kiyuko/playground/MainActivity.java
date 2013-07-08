package org.kiyuko.playground;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private ListView listView;
	private ArrayList<Item> items;
	private ArrayAdapter<Item> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = getListView();

		items = new ArrayList<Item>();
		items.add(new Item("1", "..."));
		items.add(new Item("2", "..."));
		items.add(new Item("3", "..."));

		adapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1, items);
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

				items.add(new Item(Integer.valueOf(items.size() + 1).toString(), "..."));
				adapter.notifyDataSetChanged();

				// Scroll to the bottom of the ListView
				listView.setSelection(listView.getCount() - 1);

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

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
