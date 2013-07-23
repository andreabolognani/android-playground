package org.kiyuko.playground;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ItemDatabaseHelper dbHelper;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (findViewById(R.id.fragment_container) != null) {

			if (savedInstanceState != null) {
				return;
			}

			getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, MainFragment.newInstance())
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

		MainFragment fragment;

		switch (menuItem.getItemId()) {

			case R.id.action_add:

				fragment = (MainFragment) getFragmentManager().findFragmentById(R.id.fragment_container);

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

	private void notImplemented() {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(getApplicationContext(), R.string.notImplemented, duration);
		toast.show();
	}
}
