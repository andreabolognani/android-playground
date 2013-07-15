package org.kiyuko.playground;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Do nothing
	}

	public Cursor getAllItemsCursor() {

		SQLiteDatabase db;
		Cursor cursor;

		db = getReadableDatabase();

		if (db != null) {

			cursor = db.query(TABLE_ITEMS,
				new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION },
				null,
				null,
				null,
				null,
				null,
				null);
		}
		else {

			cursor = null;
		}

		return cursor;
	}

	public void addItem(Item item, int position) {

		SQLiteDatabase db;
		ContentValues values;

		values = new ContentValues();
		values.put(COLUMN_ID, position);
		values.put(COLUMN_NAME, item.getName());
		values.put(COLUMN_DESCRIPTION, item.getDescription());

		db = getWritableDatabase();

		if (db != null) {

			db.insert(TABLE_ITEMS, null, values);
		}
	}
}
