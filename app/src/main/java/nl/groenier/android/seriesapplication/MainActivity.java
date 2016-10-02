package nl.groenier.android.seriesapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.groenier.android.seriesapplication.RecyclerView.SeriesAdapter;
import nl.groenier.android.seriesapplication.SQLite.DataSource;
import nl.groenier.android.seriesapplication.SQLite.MySQLiteHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView seriesRecyclerView;
    private RecyclerView.Adapter seriesAdapter;
    private RecyclerView.LayoutManager seriesLayoutManager;

    private SeriesListAdapter seriesListAdapter;
    private List<Series> seriesDataSet = new ArrayList<>();
    private ListView seriesListView;
    private DataSource datasource;
    private SimpleCursorAdapter simpleCursorAdapter;

    private EditText editTextAddSeries;
    private FloatingActionButton fABAddSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextAddSeries = (EditText) findViewById(R.id.edit_text_add_series);
        fABAddSeries = (FloatingActionButton) findViewById(R.id.fab_add_series);

//        seriesRecyclerView = (RecyclerView) findViewById(R.id.series_recycler_view);
        datasource = new DataSource(this);
//        populateList();

        String[] columns = new String[] { "_id", MySQLiteHelper.COLUMN_SERIES };

        int[] to = new int[] { R.id.text_view_list_item_series_id, R.id.text_view_list_item_series_title };

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.series_list_item, datasource.getAllSeriesCursor(), columns, to, 0);

        seriesListView = (ListView) findViewById(R.id.series_list_view);
        seriesListView.setAdapter(simpleCursorAdapter);

        //Set the layout manager
//        seriesAdapter = new SeriesAdapter(seriesDataSet);
//        seriesLayoutManager = new LinearLayoutManager(getApplicationContext());
//        seriesRecyclerView.setLayoutManager(seriesLayoutManager);
//        seriesRecyclerView.setAdapter(seriesAdapter);

//        seriesListView = (ListView) findViewById(R.id.series_list_view);
//
//        seriesListAdapter = new SeriesListAdapter(seriesDataSet, this);
//        seriesListView.setAdapter(seriesListAdapter);
//        populateList();
//        seriesListAdapter.notifyDataSetChanged();

//        seriesAdapter.notifyDataSetChanged();

        fABAddSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datasource.createSeries(editTextAddSeries.getText().toString());
                editTextAddSeries.setText("");
                updateSeriesListView();
                Toast.makeText(MainActivity.this, "Series added!", Toast.LENGTH_SHORT).show();
            }
        });

        seriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SeriesDetailActivity.class);
                intent.putExtra("seriesId", simpleCursorAdapter.getItemId(position));
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSeriesListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "Result received!", Toast.LENGTH_SHORT).show();
    }

    private void populateList() {
//        seriesDataSet.add(new Series(1, "How I Met Your Mother"));
//        seriesDataSet.add(new Series(2, "Friends"));
//        seriesDataSet.add(new Series(3, "That 70's Show"));

        datasource.createSeries("How I Met Your Mother");
        datasource.createSeries("Friends");
        datasource.createSeries("That 70's Show");

    }

    public void updateSeriesListView() {

//        assignmentList = datasource.getAllAssignments();
//        assignmentAdapter = new AssignmentAdapter(this, R.layout.list_item_assignment, assignmentList);

        String[] columns = new String[] { "_id", MySQLiteHelper.COLUMN_SERIES };
        int[] to = new int[] { R.id.text_view_list_item_series_id, R.id.text_view_list_item_series_title };
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.series_list_item, datasource.getAllSeriesCursor(), columns, to, 0);
        seriesListView.setAdapter(simpleCursorAdapter);
        simpleCursorAdapter.notifyDataSetChanged();

    }

}
