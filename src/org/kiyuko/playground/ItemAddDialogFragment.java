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
import android.widget.Toast;

public class ItemAddDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder;
		View view;
		LayoutInflater inflater;

		inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.dialog_item_add, null);

		builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.itemAddDialogFragmentTitle);
		builder.setView(view);

		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				AlertDialog dial;
				EditText nameEdit;
				EditText descriptionEdit;
				Toast toast;
				String message;
				int duration;

				dial = (AlertDialog) dialog;
				nameEdit = (EditText) dial.findViewById(R.id.nameEdit);
				descriptionEdit = (EditText) dial.findViewById(R.id.descriptionEdit);

				message = "(\"";
				message += nameEdit.getText().toString();
				message += "\" \"";
				message += descriptionEdit.getText().toString();
				message += "\")";
				duration = Toast.LENGTH_SHORT;

				toast = Toast.makeText(dial.getContext(), message, duration);
				toast.show();
			}
		});

		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		return builder.create();
	}

}
