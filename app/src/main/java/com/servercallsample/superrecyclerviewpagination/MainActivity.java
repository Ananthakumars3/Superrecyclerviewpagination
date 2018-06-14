package com.servercallsample.superrecyclerviewpagination;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.SparseItemRemoveAnimator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMoreListener {
    SuperRecyclerView mRecycler;
    private ListPaginationAdapter mAdapter;
    private SparseItemRemoveAnimator mSparseAnimator;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String BASEURL = "https://api.themoviedb.org/3/";
    List<MovieModel> movieList = new ArrayList<MovieModel>();
    private String pagecount = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = (SuperRecyclerView) findViewById(R.id.main_recycler);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecycler.setLayoutManager(mLayoutManager);

//        mRecycler.setRefreshListener(this);
        mRecycler.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        mRecycler.setupMoreListener(this, 10);
        mAdapter = new ListPaginationAdapter(MainActivity.this,movieList);
        mRecycler.setAdapter(mAdapter);
        getMovieContentCall();
    }

    private void getMovieContentCall() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<ResponseBody> call = requestInterface.getMovieCall("417d6c8434bbd87344771dc370e33fcd","en-US",pagecount);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() != null && response.code() == 200) {

                        String data = response.body().string();
                        Log.d("JSON_data", data);
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("results"));

                        for (int i = 0; i < jsonArray.length(); i++) {
                            String name = jsonArray.getJSONObject(i).getString("title");
                            String review = jsonArray.getJSONObject(i).getString("overview");
                            MovieModel m = new MovieModel();
                            m.setTitle(name);
                            m.setReview(review);
                            m.setImage("https://www.google.co.in/search?q=ajith+images&safe=active&rlz=1C1GYPO_enIN784IN784&tbm=isch&source=iu&ictx=1&fir=GKBkYShb_parDM%253A%252CqZL7Uk477MTxPM%252C_&usg=__4029I3aceV6quxdAdKFhlcdSOP4%3D&sa=X&ved=0ahUKEwj2up-vr-zZAhXMs48KHZd0BGAQ9QEILDAB#imgrc=GKBkYShb_parDM:");
                            mAdapter.add(m);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("ERROR", t.toString());
            }
        });
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        int page = Integer.parseInt(pagecount);
        page++;
        pagecount = String.valueOf(page);
        getMovieContentCall();
    }
}
