package com.servercallsample.superrecyclerviewpagination;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ananthkumar on 13-03-2018.
 */

public class MoviesResponse implements Parcelable {
    private Movie[] movies;

    private String page;

    private String total_pages;

    private String total_results;

    public Movie[] getResults() {
        return movies;
    }

    public void setResults(Movie[] results) {
        this.movies = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    @Override
    public String toString() {
        return "ClassPojo [results = " + movies + ", page = " + page + ", total_pages = " + total_pages + ", total_results = " + total_results + "]";
    }

    protected MoviesResponse(Parcel in) {
        page = in.readString();
        total_pages = in.readString();
        total_results = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(page);
        dest.writeString(total_pages);
        dest.writeString(total_results);
    }

    @SuppressWarnings("unused")
    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
        @Override
        public MoviesResponse createFromParcel(Parcel in) {
            return new MoviesResponse(in);
        }

        @Override
        public MoviesResponse[] newArray(int size) {
            return new MoviesResponse[size];
        }
    };

}
