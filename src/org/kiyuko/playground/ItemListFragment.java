package org.kiyuko.playground;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ItemListFragment extends ListFragment {

	private ItemDatabaseHelper dbHelper;
	private SimpleCursorAdapter adapter;

	public static ItemListFragment newInstance() {

		return new ItemListFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view;

		view = inflater.inflate(R.layout.fragment_item_list, container, false);

		dbHelper = new ItemDatabaseHelper(getActivity());

		adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.item,
				null,
				new String[] {"name", "description"},
				new int[] {R.id.nameView, R.id.descriptionView});
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

		editItem(id);
	}

	public void addItem() {

		Intent intent;

		intent = new Intent(getActivity(), DetailsActivity.class);

		startActivity(intent);
	}

	private void editItem(long id) {

		Intent intent;

		intent = new Intent(getActivity(), DetailsActivity.class);
		intent.putExtra(ItemDetailsFragment.PARAMETER_ID, id);

		startActivity(intent);
	}
}
