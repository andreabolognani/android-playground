package org.kiyuko.playground;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemEditDialogFragment extends DialogFragment {

	public interface Listener {
		public void onPositiveClick(DialogFragment dialog, Item item, int position);
		public void onNegativeClick(DialogFragment dialog);
	}

	private Item item;
	private int position;

	private EditText nameEdit;
	private EditText descriptionEdit;

	public ItemEditDialogFragment() {

		super();

		this.item = null;
		this.position = -1;
	}

	public ItemEditDialogFragment(Item item, int position) {

		super();

		this.item = item;
		this.position = position;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final AlertDialog dialog;
		AlertDialog.Builder builder;
		LayoutInflater inflater;
		View view;
		TextWatcher textWatcher;

		inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.dialog_item_add, null);

		nameEdit = (EditText) view.findViewById(R.id.nameEdit);
		descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);

		builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.itemAddDialogFragmentTitle);
		builder.setView(view);

		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

					Item item;

					item = new Item(nameEdit.getText().toString(), descriptionEdit.getText().toString());

					((Listener) getActivity()).onPositiveClick(ItemEditDialogFragment.this, item, position);
			}
		});

		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				((Listener) getActivity()).onNegativeClick(ItemEditDialogFragment.this);
			}
		});

		dialog = builder.create();
		dialog.show();

		textWatcher = new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {

				Button button;

				button = dialog.getButton(Dialog.BUTTON_POSITIVE);
				button.setEnabled(s.toString().length() != 0);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Do nothing
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// Do nothing
			}
		};

		nameEdit.addTextChangedListener(textWatcher);
		descriptionEdit.addTextChangedListener(textWatcher);

		if (item != null) {

			// Fill in with the existing information
			nameEdit.setText(item.getName());
			descriptionEdit.setText(item.getDescription());
		}
		else {

			// Set to empty to trigger the TextWatcher
			nameEdit.setText("");
			descriptionEdit.setText("");
		}

		return dialog;
	}
}
