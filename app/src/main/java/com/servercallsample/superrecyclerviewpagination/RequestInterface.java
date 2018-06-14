package com.servercallsample.superrecyclerviewpagination;

import android.graphics.Movie;


import com.servercallsample.superrecyclerviewpagination.MoviesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ananthkumar on 13-03-2018.
 */

public interface RequestInterface {
    /*@GET("movie/popular")
    Call<MoviesResponse> getpopularmovies(@Query("api_key") String apikey, @Query("page") int pageIndex);

    @GET("movie/popular")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apikey, @Query("page") int pageIndex);*/

    @GET("movie/top_rated")
    Call<ResponseBody> getMovieCall(@Query("api_key") String userkey,@Query("language") String lang,@Query("page") String page_id);
}
