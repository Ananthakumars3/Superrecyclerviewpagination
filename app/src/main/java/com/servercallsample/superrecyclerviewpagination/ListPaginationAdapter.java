package com.servercallsample.superrecyclerviewpagination;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ananthkumar on 14-03-2018.
 */

class ListPaginationAdapter extends RecyclerView.Adapter<ListPaginationAdapter.ViewHolder> {
    Context context;
    List<MovieModel> movieModels = new ArrayList<>();

    public ListPaginationAdapter(MainActivity mainActivity, List<MovieModel> movieList) {
        this.movieModels = movieList;
        this.context = mainActivity;


    }

    public void add(MovieModel category) {
        insert(category, movieModels.size());
    }

    private void insert(MovieModel category, int count) {
        movieModels.add(count, category);
        notifyItemInserted(count);
    }

    public void remove(int position) {
        movieModels.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = movieModels.size();
        movieModels.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layoutlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieModel movieModel = movieModels.get(position);
        holder.title.setText(movieModel.getTitle());
        holder.review.setText(movieModel.getReview());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "SELECTED" + movieModel.getReview(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, review;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listlayout_title);
            review = (TextView) itemView.findViewById(R.id.listlayout_overview);
        }

    }
}
