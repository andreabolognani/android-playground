package org.kiyuko.playground;

import android.content.Context;
import android.widget.Toast;

public class Common {

	public static final String KEY_ID = "org.kiyuko.playground.Common.KEY_ID";

	public static final String SHARED_PREFERENCES_FILE = "preferences";

	public static void shortToast(Context context, int message) {

		Toast toast;
		int duration;

		duration = Toast.LENGTH_SHORT;

		toast = Toast.makeText(context, message, duration);
		toast.show();
	}
}
