package nl.groenier.android.seriesapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Martijn on 27/09/2016.
 */

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    private List<Series> seriesList;

    public SeriesAdapter(List seriesDataSet) {
        this.seriesList = seriesDataSet;
    }

    @Override
    public SeriesAdapter.SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public SeriesViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }


}
