package org.kiyuko.playground;

import java.util.ListIterator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements ItemListFragment.OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (findViewById(R.id.list_container) != null) {

			if (savedInstanceState != null) {
				return;
			}

			getFragmentManager().beginTransaction()
				.add(R.id.list_container, ItemListFragment.newInstance())
			.commit();
		}

		if (findViewById(R.id.details_container) != null) {

			if (savedInstanceState != null) {
				return;
			}

			getFragmentManager().beginTransaction()
				.add(R.id.details_container, ViewDetailsFragment.newInstance())
			.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {

		ItemListFragment fragment;

		switch (menuItem.getItemId()) {

			case R.id.action_add:

				fragment = (ItemListFragment) getFragmentManager().findFragmentById(R.id.list_container);

				if (fragment != null) {

					fragment.addItem();
				}

				return true;

			case R.id.action_settings:

				notImplemented();

				return true;

			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}

	public void onItemClick(long id) {

		// Running in two-panes mode
		if (findViewById(R.id.details_container) != null) {

			// Replace the current details fragment with a new one
			getFragmentManager().beginTransaction()
				.replace(R.id.details_container, ViewDetailsFragment.newInstance(id))
			.commit();
		}
	}

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
