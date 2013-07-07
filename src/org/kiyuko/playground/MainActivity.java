package org.kiyuko.playground;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayList<Item> items;
	private ArrayAdapter<Item> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		items = new ArrayList<Item>();
		items.add(new Item("1"));
		items.add(new Item("2"));
		items.add(new Item("3"));

		adapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1, items);

		ListView listView1 = (ListView) findViewById(R.id.listView1);
		listView1.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		switch (menuItem.getItemId()) {

			case R.id.action_add:

				items.add(new Item(Integer.valueOf(items.size() + 1).toString()));
				adapter.notifyDataSetChanged();

				return true;

			case R.id.action_settings:

				toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
				toast.show();

				return true;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}
}
