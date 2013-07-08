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
		super(context, R.layout.item, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		TextView nameView;
		TextView descriptionView;
		LayoutInflater inflater;
		Item item;

		view = convertView;
		item = getItem(position);

		if (view == null) {

			inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item, null);
		}

		nameView = (TextView) view.findViewById(R.id.nameView);
		descriptionView = (TextView) view.findViewById(R.id.descriptionView);

		if (item != null) {

			nameView.setText(item.getName());
			descriptionView.setText(item.getDescription());
		}
		else {

			nameView.setText("Undefined");
			descriptionView.setText("Undefined");
		}

		return view;
	}
}
