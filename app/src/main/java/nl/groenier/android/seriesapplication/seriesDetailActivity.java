package nl.groenier.android.seriesapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Martijn on 29/09/2016.
 */

public class SeriesDetailActivity extends AppCompatActivity {

    private Series series;
    private TextView textViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_detail);

        textViewId = (TextView) findViewById(R.id.text_view_series_id);

        long seriesId = getIntent().getLongExtra("seriesId", -1);

        textViewId.setText(Long.toString(seriesId));

    }

}
