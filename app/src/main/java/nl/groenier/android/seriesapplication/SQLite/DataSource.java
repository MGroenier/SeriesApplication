package nl.groenier.android.seriesapplication.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import nl.groenier.android.seriesapplication.Series;

/**
 * Created by Martijn on 29/09/2016.
 */

public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] assignmentAllColumns = { MySQLiteHelper.COLUMN_SERIES_ID, MySQLiteHelper.COLUMN_SERIES };

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        dbHelper.close();
    }

    // ---------------------------------- TO BE MODIFIED!!! ----------------------------------------

    public long createSeries(String series) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_SERIES, series);
        long insertId = database.insert(MySQLiteHelper.TABLE_SERIES, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();

        return insertId;
    }

    public void deleteSeries(long id) {
        if (!database.isOpen())
            open();
        database.delete(MySQLiteHelper.TABLE_SERIES, MySQLiteHelper.COLUMN_SERIES_ID + " =?", new String[] { Long.toString(id)});
        if (database.isOpen())
            close();
    }

    public void updateSeries(Series series, String title) {

        if (!database.isOpen())

            open();

        ContentValues args = new ContentValues();

        args.put(MySQLiteHelper.COLUMN_SERIES, title);

        //args.put(MySQLiteHelper.COLUMN_COURSE_ID, assignment.getCourse().getId());

        database.update(MySQLiteHelper.TABLE_SERIES, args, MySQLiteHelper.COLUMN_SERIES_ID + "=?", new String[] { Long.toString(series.getId()) });

        if (database.isOpen())

            close();

    }

    public Cursor getAllSeriesCursor() {

        if (!database.isOpen())

            open();

        Cursor cursor = database.rawQuery(

                "SELECT " +

                        MySQLiteHelper.COLUMN_SERIES_ID + " AS _id," +

                        MySQLiteHelper.COLUMN_SERIES +

                        " FROM " + MySQLiteHelper.TABLE_SERIES, null);

        if (cursor != null) {

            cursor.moveToFirst();

        }

        if (database.isOpen())

            close();

        return cursor;

    }

    private Series cursorToSeries(Cursor cursor) {

        try {

            Series series = new Series();

            series.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_SERIES_ID)));

            series.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_SERIES)));

            return series;

        } catch(CursorIndexOutOfBoundsException exception) {

            exception.printStackTrace();

            return null;

        }

    }

    public Series getSeries(long columnId) {

        if (!database.isOpen())

            open();

        Cursor cursor = database.rawQuery(

                "SELECT * " +

                        " FROM " + MySQLiteHelper.TABLE_SERIES + " assignments" +

                        " WHERE " + MySQLiteHelper.COLUMN_SERIES_ID+ " = " + columnId, null);

        cursor.moveToFirst();

        Series series = cursorToSeries(cursor);

        cursor.close();

        if (database.isOpen())

            close();

        return series;

    }

}
