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

	public Item get(int position) {

		SQLiteDatabase db;
		Cursor cursor;
		Item item;

		db = getReadableDatabase();

		if (db == null) {
			return null;
		}

		// Look for existing Items at the given position
		cursor = db.query(TABLE_ITEMS,
			PROJECTION,
			SELECTION,
			new String[] { "" + position },
			null,
			null,
			null);

		if (cursor.getCount() <= 0) {
			return null;
		}

		if (!cursor.moveToFirst()) {
			return null;
		}

		item = new Item(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
				cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));

		return item;
	}

	public void set(int position, Item item) {

		SQLiteDatabase db;
		ContentValues values;

		db = getWritableDatabase();

		if (db == null) {
			return;
		}

		values = new ContentValues();
		values.put(COLUMN_ID, position);
		values.put(COLUMN_NAME, item.getName());
		values.put(COLUMN_DESCRIPTION, item.getDescription());

		if (get(position) != null) {

			// Update an existing Item
			db.update(TABLE_ITEMS,
				values,
				SELECTION,
				new String[] { "" + position });
		}
		else {

			// Add a new Item
			db.insert(TABLE_ITEMS,
				null,
				values);
		}
	}
}
