package Classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

/**
 * Created by Rob on 2/2/16.
 */
public class NeighborhoodSQLOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = NeighborhoodSQLOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NEIGHBORHOOD_DB";
    public static final String NEIGHBORHOOD_TABLE_NAME = "NEIGHBORHOOD_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_PLACE_NAME = "PLACE_NAME";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final int COL_FAVE = 0; // Default is false.
    public static final String[] NEIGHBORHOOD_COLUMNS = {
            COL_ID,
            COL_PLACE_NAME,
            COL_DESC,
            COL_ADDRESS};

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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public void addPlace(String name, String desc, String address, int fave){
=======
    public void insert(int id, String name, String desc, String address, int fave){
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
=======
    public void insert(int id, String name, String desc, String address, int fave){
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
        SQLiteDatabase db = getWritableDatabase(); // We now have access to the database.
        // Equivalent of INSERT INTO games VALUES (...);
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("description", desc);
        values.put("address", address);
        values.put("favorite", 0);

        db.insert(NEIGHBORHOOD_TABLE_NAME, null, values);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
<<<<<<< HEAD
<<<<<<< HEAD
        String CREATE_NEIGHBORHOOD_TABLE =
                "CREATE TABLE " + NEIGHBORHOOD_TABLE_NAME + " ( " +
                        COL_ID + " INTEGER PRIMARY KEY, " +
                        COL_PLACE_NAME + " TEXT, " +
                        COL_DESC + " TEXT, " +
                        COL_ADDRESS + " TEXT, " +
                        COL_FAVE + " TEXT)";

        db.execSQL(CREATE_NEIGHBORHOOD_TABLE); // Creates the database.
=======
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE neighborhood (id INTEGER PRIMARY KEY, name TEXT, year TEXT)"); // Creates the database for Games.
>>>>>>> parent of 8aa7103... Did more setup of layouts and database
=======
        db.execSQL("CREATE TABLE " + NEIGHBORHOOD_TABLE_NAME + " (id INTEGER PRIMARY KEY, " +
                "name TEXT, year TEXT)"); // Creates the database for Games.
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
=======
        db.execSQL("CREATE TABLE " + NEIGHBORHOOD_TABLE_NAME + " (id INTEGER PRIMARY KEY, " +
                "name TEXT, year TEXT)"); // Creates the database for Games.
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NEIGHBORHOOD_TABLE_NAME);
        this.onCreate(db);
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

//    public int getFavoritesByID(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME,
//                new int[]{COL_FAVE},
//                COL_ID + " = ?",
//                new String[]{String.valueOf(id)},
//                null,
//                null,
//                null,
//                null );
//
//        cursor.moveToFirst();
//        return cursor.getInt(cursor.getColumnIndex(COL_FAVE));
//
//    }
}
