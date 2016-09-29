package nl.groenier.android.seriesapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_list_item, parent, false);

        return new SeriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {
        Series series = seriesList.get(position);
        holder.title.setText(series.getTitle());
        holder.id.setText(Long.toString(series.getId()));
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public class SeriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView id;
        public TextView title;
        private Context context;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.text_view_list_item_series_id);
            title = (TextView) itemView.findViewById(R.id.text_view_list_item_series_title);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Item pressed!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(), SeriesDetailActivity.class);
            long seriesId = 5;
            intent.putExtra("seriesId",Long.parseLong(id.getText().toString()));
            context.startActivity(intent);
        }

    }

}
