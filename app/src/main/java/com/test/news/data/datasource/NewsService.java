package com.test.news.data.datasource;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This interface is a service used for getting the data from api using
 * retrofit. This framework uses this interface.
 *
 * */
public interface NewsService {

    @GET("sources/")
    Single<Wrapper> getSources(@Query("apiKey") String apiKey);

    @GET("everything/")
    Single<Wrapper> getArticles(@Query("q") String query,
                                @Query("apiKey") String apiKey);

}
