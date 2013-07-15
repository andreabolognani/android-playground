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

	private static String BUNDLE_NAME = "name";
	private static String BUNDLE_DESCRIPTION = "description";
	private static String BUNDLE_POSITION = "position";

	private String name;
	private String description;
	private int position;

	public interface Listener {
		public void onPositiveClick(DialogFragment dialog, Item item, int position);
		public void onNegativeClick(DialogFragment dialog);
	}

	public ItemEditDialogFragment() {

		super();

		this.name = "";
		this.description = "";
		this.position = -1;
	}

	public ItemEditDialogFragment(int position) {

		super();

		this.name = "";
		this.description = "";
		this.position = position;
	}

	public ItemEditDialogFragment(Item item, int position) {

		super();

		this.name = item.getName();
		this.description = item.getDescription();
		this.position = position;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder;
		LayoutInflater inflater;
		View view;
		TextWatcher textWatcher;
		final AlertDialog dialog;
		final EditText nameEdit;
		final EditText descriptionEdit;

		if (savedInstanceState != null) {

			name = savedInstanceState.getString(BUNDLE_NAME);
			description = savedInstanceState.getString(BUNDLE_DESCRIPTION);
			position = savedInstanceState.getInt(BUNDLE_POSITION);
		}

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
				String name;
				String description;

				name = nameEdit.getText().toString();
				description = descriptionEdit.getText().toString();

				button = dialog.getButton(Dialog.BUTTON_POSITIVE);
				button.setEnabled(name.length() > 0 && description.length() > 0);
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

		// Fill in with the existing information
		nameEdit.setText(name);
		descriptionEdit.setText(description);

		return dialog;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);

		outState.putString(BUNDLE_NAME, name);
		outState.putString(BUNDLE_DESCRIPTION, description);
		outState.putInt(BUNDLE_POSITION, position);
	}
}
