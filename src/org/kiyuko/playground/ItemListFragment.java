package org.kiyuko.playground;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ItemListFragment extends ListFragment {

	private Activity activity;
	private ItemDatabaseHelper dbHelper;
	private SimpleCursorAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;

		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_item_list, container, false);

		dbHelper = new ItemDatabaseHelper(activity);

		adapter = new SimpleCursorAdapter(activity,
				R.layout.item,
				null,
				new String[] { "name" },
				new int[] { R.id.nameView });
		setListAdapter(adapter);

		return view;
	}

	@Override
	public void onResume() {

		Cursor cursor;

		cursor = dbHelper.getAllItemsCursor();

		if (cursor != null) {

			adapter.changeCursor(cursor);
		}

		super.onResume();
	}

	@Override
	public void onDestroy() {

		dbHelper.close();

		super.onDestroy();
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {

		setHighlightedItem(position);

		((AdapterView.OnItemClickListener) activity).onItemClick(listView, view, position, id);
		//editItem(id);
	}

	private void editItem(long id) {

		Intent intent;

		intent = new Intent(activity, DetailsActivity.class);
		intent.putExtra(Common.KEY_ID, id);

		startActivity(intent);
	}

	private void setHighlightedItem(int position) {

		ListView listView;
		View view;
		int count;
		int normalColor;
		int highlightColor;

		listView = getListView();
		count = listView.getCount();

		normalColor = getResources().getColor(android.R.color.black);
		highlightColor = getResources().getColor(android.R.color.holo_blue_dark);

		for (int i = 0; i < count; ++i) {

			view = listView.getChildAt(i);
			view.setBackgroundColor(normalColor);
		}

		view = listView.getChildAt(position);
		view.setBackgroundColor(highlightColor);
	}
}
