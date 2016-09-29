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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView seriesRecyclerView;
    private RecyclerView.Adapter seriesAdapter;
    private RecyclerView.LayoutManager seriesLayoutManager;
    private List<Series> seriesDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        seriesRecyclerView = (RecyclerView) findViewById(R.id.series_recycler_view);

        //Set the layout manager
        seriesAdapter = new SeriesAdapter(seriesDataSet);
        seriesLayoutManager = new LinearLayoutManager(getApplicationContext());
        seriesRecyclerView.setLayoutManager(seriesLayoutManager);
        seriesRecyclerView.setAdapter(seriesAdapter);

        populateList();
        seriesAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        seriesDataSet.add(new Series(1, "How I Met Your Mother"));
        seriesDataSet.add(new Series(2, "Friends"));
        seriesDataSet.add(new Series(3, "That 70's Show"));
    }

}
