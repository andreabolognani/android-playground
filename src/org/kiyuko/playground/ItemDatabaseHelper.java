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

	public int newId() {

		SQLiteDatabase db;
		Cursor cursor;
		int id;

		db = getReadableDatabase();

		if (db == null) {
			return -1;
		}

		cursor = db.query(TABLE_ITEMS,
			new String[] { COLUMN_ID },
			"_id >= 0",
			null,
			null,
			null,
			COLUMN_ID);

		if (cursor.getCount() <= 0) {

			cursor.close();

			return 0;
		}

		if (!cursor.moveToLast()) {

			cursor.close();

			return 0;
		}

		id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
		id += 1;

		return id;
	}

	public Cursor getAllItemsCursor() {

		SQLiteDatabase db;
		Cursor cursor;

		db = getReadableDatabase();

		if (db == null) {
			return null;
		}

		cursor = db.query(TABLE_ITEMS,
			PROJECTION,
			"_id >= 0",
			null,
			null,
			null,
			COLUMN_ID);

		return cursor;
	}

	public Item get(int id) {

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
			new String[] { "" + id },
			null,
			null,
			null);

		if (cursor.getCount() <= 0) {

			cursor.close();

			return null;
		}

		if (!cursor.moveToFirst()) {

			cursor.close();

			return null;
		}

		item = new Item(id,
				cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
				cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));

		return item;
	}

	public void put(Item item) {

		SQLiteDatabase db;
		ContentValues values;

		db = getWritableDatabase();

		if (db == null) {
			return;
		}

		values = new ContentValues();
		values.put(COLUMN_ID, item.getId());
		values.put(COLUMN_NAME, item.getName());
		values.put(COLUMN_DESCRIPTION, item.getDescription());

		if (get(item.getId()) != null) {

			// Update an existing Item
			db.update(TABLE_ITEMS,
				values,
				SELECTION,
				new String[] { "" + item.getId() });
		}
		else {

			// Add a new Item
			db.insert(TABLE_ITEMS,
				null,
				values);
		}
	}
}
