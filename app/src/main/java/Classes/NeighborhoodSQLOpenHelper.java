package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;

import com.example.roberrera.project_2.R;

/**
 * Created by Rob on 2/2/16.
 */
public class NeighborhoodSQLOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = NeighborhoodSQLOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NeighborhoodDB.db";
    public static final String NEIGHBORHOOD_TABLE_NAME = "neighborhood_table";

    public static final String COL_ID = "_id";
    public static final String COL_PLACE_NAME = "placename";
    public static final String COL_DESC = "description";
    public static final String COL_ADDRESS = "address";
    public static final String COL_FAVE = "favorite";
    public static final String COL_TYPE = "type";
    public static final String COL_RATING = "rating";

    // TODO: Add images to the database, instead of using a switch statement.



    public static final String[] NEIGHBORHOOD_COLUMNS = {
            COL_ID,
            COL_PLACE_NAME,
            COL_DESC,
            COL_ADDRESS,
            COL_FAVE,
            COL_TYPE,
            COL_RATING};

    // Constructor for the database helper.
    public NeighborhoodSQLOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static NeighborhoodSQLOpenHelper instance;
    public static NeighborhoodSQLOpenHelper getInstance(Context context) {
        if(instance == null){
            instance = new NeighborhoodSQLOpenHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NEIGHBORHOOD_TABLE =
                "CREATE TABLE " + NEIGHBORHOOD_TABLE_NAME + " ( " +
                        COL_ID + " INTEGER PRIMARY KEY, " +
                        COL_PLACE_NAME + " TEXT, " +
                        COL_DESC + " TEXT, " +
                        COL_ADDRESS + " TEXT, " +
                        COL_FAVE + " TEXT, " +
                        COL_TYPE + " TEXT, " +
                        COL_RATING + " TEXT)";

        db.execSQL(CREATE_NEIGHBORHOOD_TABLE); // Creates the database.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NEIGHBORHOOD_TABLE_NAME);
        this.onCreate(db);
    }

    public void addPlace(String name, String desc, String address, int fave, String type, int rating){
        SQLiteDatabase db = getWritableDatabase(); // We now have access to the database.

        ContentValues values = new ContentValues();
        Neighborhood neighborhood = Neighborhood.instance;
        values.put(COL_PLACE_NAME, name);
        values.put(COL_DESC, desc);
        values.put(COL_ADDRESS, address);
        values.put(COL_FAVE, fave);
        values.put(COL_TYPE, type);
        values.put(COL_RATING, rating);

        db.insert(NEIGHBORHOOD_TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getNeighborhoodList() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                NEIGHBORHOOD_COLUMNS,
                null,
                null,
                null,
                null,
                null,
                null );

        if (cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public String getLocationNameByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                new String[]{COL_PLACE_NAME},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COL_PLACE_NAME));
    }

    public String getLocationAddressByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                new String[]{COL_ADDRESS},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
    }

    public String getLocationDescByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                new String[]{COL_DESC},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COL_DESC));
    }

    public int getFavoritesByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                new String[]{COL_FAVE},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null );

        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COL_FAVE));
    }

    public Cursor getFavorites(int fave) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                NEIGHBORHOOD_COLUMNS,
                COL_FAVE + " = ?",
                new String[]{String.valueOf(fave)},
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void updateFavoriteByID (int id, int newValue){

        SQLiteDatabase db = this.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_FAVE, newValue);

        // Which row to update, based on the ID
        String selection = COL_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update(
                NEIGHBORHOOD_TABLE_NAME,
                values,
                selection,
                selectionArgs);

        db.update(NEIGHBORHOOD_TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public int getRatingByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                new String[]{COL_RATING},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COL_RATING));
    }

    public void updateRatingByID (int id, float newValue){

        SQLiteDatabase db = this.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COL_RATING, newValue);

        // Which row to update, based on the ID
        String selection = COL_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update(
                NEIGHBORHOOD_TABLE_NAME,
                values,
                selection,
                selectionArgs);

        db.update(NEIGHBORHOOD_TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public String getTypeByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
                new String[]{COL_TYPE},
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COL_TYPE));
    }

    public Cursor searchPlaces(String query){
//        String[] splitQuery = query.split(" ");

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                NEIGHBORHOOD_COLUMNS, // b. column names
                // To search across place name, type of place, and the address, we create three queries.
                COL_PLACE_NAME + " LIKE ?" + " OR " + COL_TYPE + " LIKE ?" + " OR " + COL_ADDRESS + " LIKE ?", // c. selections
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        while (!cursor.isAfterLast()){
            cursor.moveToNext();
        }
        return cursor;
    }

}