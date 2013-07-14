package org.kiyuko.playground;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ItemDatabaseHelper extends SQLiteOpenHelper {

	private static String DATABASE_NAME = "items";
	private static int DATABASE_VERSION = 1;

	private static String TABLE_ITEMS = "items";
	private static String COLUMN_ID = "_id";
	private static String COLUMN_NAME = "name";
	private static String COLUMN_DESCRIPTION = "description";

	public ItemDatabaseHelper(Context context) {

		// Use the default Cursor factory
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// Create a table to store items
		db.execSQL("CREATE TABLE " + TABLE_ITEMS +
				" (" +
					COLUMN_ID + " INTEGER PRIMARY KEY, " +
					COLUMN_NAME + " TEXT, " +
					COLUMN_DESCRIPTION + " TEXT" +
				");");
//		db.execSQL("INSERT INTO " + TABLE_ITEMS +
//				" VALUES (1, \"test\", \"test description\");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// Do nothing
	}

}
