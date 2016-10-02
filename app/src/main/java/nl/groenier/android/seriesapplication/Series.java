package nl.groenier.android.seriesapplication;

/**
 * Created by Martijn on 27/09/2016.
 */

public class Series {

    private long id;
    private String title;

    public Series() {

    }

    public Series(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
