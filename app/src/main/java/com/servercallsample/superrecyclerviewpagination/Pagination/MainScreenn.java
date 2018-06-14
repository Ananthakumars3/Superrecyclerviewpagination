package com.servercallsample.superrecyclerviewpagination.Pagination;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.servercallsample.superrecyclerviewpagination.R;

public class MainScreenn extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SuperRecyclerView mRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screenn);
        mRecycler=(SuperRecyclerView)findViewById(R.id.list);
        mRecycler.setLayoutManager(new LinearLayoutManager(MainScreenn.this));
        mRecycler.setRefreshListener(MainScreenn.this);



    }

    @Override
    public void onRefresh() {

    }
}
