package nl.groenier.android.seriesapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nl.groenier.android.seriesapplication.SQLite.DataSource;

/**
 * Created by Martijn on 29/09/2016.
 */

public class SeriesDetailActivity extends AppCompatActivity {

    private DataSource datasource;

    private Series series;
    private TextView textViewTitle;
    private TextView textViewId;

    private Button buttonDeleteSeries;
    private Button buttonEditSeries;
    private EditText editTexteditTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);

        textViewTitle = (TextView) findViewById(R.id.text_view_series_title);
        textViewId = (TextView) findViewById(R.id.text_view_series_id);

        buttonDeleteSeries = (Button) findViewById(R.id.button_delete_series);
        buttonEditSeries = (Button) findViewById(R.id.button_edit_series);
        editTexteditTitle = (EditText) findViewById(R.id.edit_text_edit_series);

        datasource = new DataSource(this);
        final long seriesId = getIntent().getLongExtra("seriesId", -1);
        series = datasource.getSeries(seriesId);

        textViewTitle.setText(series.getTitle());
        textViewId.setText(Long.toString(series.getId()));
        editTexteditTitle.setText(series.getTitle());

        buttonDeleteSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datasource.deleteSeries(seriesId);
                Toast.makeText(SeriesDetailActivity.this, "Series deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonEditSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datasource.updateSeries(series, editTexteditTitle.getText().toString());
                Toast.makeText(SeriesDetailActivity.this, "Series edited!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
