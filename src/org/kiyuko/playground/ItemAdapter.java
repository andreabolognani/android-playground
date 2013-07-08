package org.kiyuko.playground;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {

	public ItemAdapter(Context context, ArrayList<Item> objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;

		view = convertView;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(android.R.layout.simple_list_item_1, null);
		}

		Item item = getItem(position);

		if (item != null) {
			((TextView) view).setText(item.toString());
		}

		return view;
	}
}
