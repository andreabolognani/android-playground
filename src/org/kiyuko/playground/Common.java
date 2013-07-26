package org.kiyuko.playground;

import android.content.Context;
import android.widget.Toast;

public class Common {

	public static final String KEY_ID = "org.kiyuko.playground.Common.KEY_ID";

	public static void notImplemented(Context context) {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(context, R.string.notImplemented, duration);
		toast.show();
	}
}
