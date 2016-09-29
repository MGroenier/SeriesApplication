package nl.groenier.android.seriesapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public long createAssignment(String assignment, long courseId) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ASSIGNMENT, assignment);
        values.put(MySQLiteHelper.COLUMN_COURSE_ID, courseId);
        long insertId = database.insert(MySQLiteHelper.TABLE_ASSIGNMENTS, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();

        return insertId;
    }

    public void deleteAssignment(long id) {
        if (!database.isOpen())
            open();
        database.delete(MySQLiteHelper.TABLE_ASSIGNMENTS, MySQLiteHelper.COLUMN_ASSIGNMENT_ID + " =?", new String[] { Long.toString(id)});
        if (database.isOpen())
            close();
    }

    public void updateAssignment(Assignment assignment) {

        if (!database.isOpen())

            open();

        ContentValues args = new ContentValues();

        args.put(MySQLiteHelper.COLUMN_ASSIGNMENT, assignment.getTitle());

        args.put(MySQLiteHelper.COLUMN_COURSE_ID, assignment.getCourse().getId());

        database.update(MySQLiteHelper.TABLE_ASSIGNMENTS, args, MySQLiteHelper.COLUMN_ASSIGNMENT_ID + "=?", new String[] { Long.toString(assignment.getId()) });

        if (database.isOpen())

            close();

    }





}
