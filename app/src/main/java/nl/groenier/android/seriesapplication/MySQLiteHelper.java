package nl.groenier.android.seriesapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Martijn on 29/09/2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database info
    private static final String DATABASE_NAME = "series_application.db";
    private static final int DATABASE_VERSION = 1;

    // Courses
    public static final String TABLE_SERIES = "courses";
    public static final String COLUMN_SERIES_ID = "course_id";
    public static final String COLUMN_SERIES = "course";

    // Creating the table
    private static final String DATABASE_CREATE_SERIES =
            "CREATE TABLE " + TABLE_SERIES +
                    "(" +
                    COLUMN_SERIES_ID + " integer primary key autoincrement, " +
                    COLUMN_SERIES + " text not null" +
                    ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the sql to create the table assignments
        db.execSQL(DATABASE_CREATE_SERIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // When the database gets upgraded you should handle the update to make sure there is no data loss.
        // This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
        if(oldVersion == 1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIES);
            onCreate(db);
        }
    }

}