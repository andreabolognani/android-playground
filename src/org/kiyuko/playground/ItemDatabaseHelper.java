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

	private static String[] PROJECTION = new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION };
	private static String SELECTION = COLUMN_ID + " = ?";

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

		if (db == null) {
			return null;
		}

		cursor = db.query(TABLE_ITEMS,
			new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION },
			null,
			null,
			null,
			null,
			null,
			null);

		return cursor;
	}

	public void set(int position, Item item) {

		SQLiteDatabase db;
		Cursor cursor;
		ContentValues values;
		String[] selectionArgs;

		db = getWritableDatabase();

		if (db == null) {
			return;
		}

		selectionArgs = new String[] { "" + position };

		// Look for existing Items at the given position
		cursor = db.query(TABLE_ITEMS,
			PROJECTION,
			SELECTION,
			selectionArgs,
			null,
			null,
			null);

		if (cursor == null) {
			return;
		}

		values = new ContentValues();
		values.put(COLUMN_ID, position);
		values.put(COLUMN_NAME, item.getName());
		values.put(COLUMN_DESCRIPTION, item.getDescription());

		if (cursor.getCount() > 0) {

			// Update an existing Item
			db.update(TABLE_ITEMS,
				values,
				SELECTION,
				selectionArgs);
		}
		else {

			// Add a new Item
			db.insert(TABLE_ITEMS,
				null,
				values);
		}
	}
}
