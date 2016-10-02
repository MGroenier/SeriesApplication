package nl.groenier.android.seriesapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SeriesListAdapter extends BaseAdapter {

    private List<Series> arrayList;

    private Context context;

    private LayoutInflater inflater;

    public SeriesListAdapter(List<Series> list, Context context) {

        this.arrayList = list;

        this.context = context;

        inflater = LayoutInflater.from(context);

    }

    @Override

    public int getCount() {

        return arrayList.size();

    }

    @Override

    public Series getItem(int position) {

        return arrayList.get(position);

    }

    @Override

    public long getItemId(int position) {

        return arrayList.get(position).getId(); // No ID's are defined

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        ViewHolder holder;

        //Check if the row is new

        if (row == null) {

            //Inflate the layout if it didn't exist yet

            row = inflater.inflate(R.layout.series_list_item, parent, false);

            //Create a new view holder instance

            holder = new ViewHolder(row);

            //set the holder as a tag so we can get it back later

            row.setTag(holder);

        } else {

            //The row isn't new so we can reuse the view holder

            holder = (ViewHolder) row.getTag();

        }

        //Populate the row

        holder.populateRow(getItem(position));

        return row;

    }

    class ViewHolder {

        private TextView seriesTitle;
        private TextView seriesId;

        //initialize the variables
        public ViewHolder(View view) {

            seriesTitle = (TextView) view.findViewById(R.id.text_view_list_item_series_title);
            seriesId = (TextView) view.findViewById(R.id.text_view_list_item_series_id);

        }

        public void populateRow(Series series) {

            seriesTitle.setText(series.getTitle());
            seriesId.setText(Long.toString(series.getId()));

        }

    }

}
