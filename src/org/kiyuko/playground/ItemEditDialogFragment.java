package org.kiyuko.playground;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ItemEditDialogFragment extends DialogFragment {

	private Item item;
	private DialogFragmentListener listener;

	private EditText nameEdit;
	private EditText descriptionEdit;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		this.listener = (DialogFragmentListener) activity;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder;
		LayoutInflater inflater;
		View view;

		inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.dialog_item_add, null);

		nameEdit = (EditText) view.findViewById(R.id.nameEdit);
		descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);

		if (item != null) {

			nameEdit.setText(item.getName());
			descriptionEdit.setText(item.getDescription());
		}

		builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.itemAddDialogFragmentTitle);
		builder.setView(view);

		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

					item = new Item(nameEdit.getText().toString(), descriptionEdit.getText().toString());

					listener.onPositiveClick(ItemEditDialogFragment.this);

			}
		});

		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (listener != null) {
					listener.onNegativeClick(ItemEditDialogFragment.this);
				}
			}
		});

		return builder.create();
	}

	public void setItem(Item item) {

		this.item = item;
	}

	public Item getItem() {

		return this.item;
	}
}
