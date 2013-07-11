package org.kiyuko.playground;

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

	public ItemEditDialogFragment(Item item, DialogFragmentListener listener) {

		super();

		this.item = item;
		this.listener = listener;
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

		nameEdit.setText(item.getName());
		descriptionEdit.setText(item.getDescription());

		builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.itemAddDialogFragmentTitle);
		builder.setView(view);

		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (listener != null) {

					item.setName(nameEdit.getText().toString());
					item.setDescription(descriptionEdit.getText().toString());

					listener.onPositiveClick(ItemEditDialogFragment.this);
				}
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

	public Item getItem() {

		return this.item;
	}
}
