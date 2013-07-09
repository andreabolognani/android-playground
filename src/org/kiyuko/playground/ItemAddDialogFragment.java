package org.kiyuko.playground;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class ItemAddDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder;

		builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(R.string.itemAddDialogFragmentTitle);
		builder.setMessage(R.string.notImplemented);

		return builder.create();
	}

}
