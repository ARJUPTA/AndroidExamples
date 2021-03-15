package com.arjupta.daggerdemo.network.main;

import com.arjupta.daggerdemo.model.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {
    @GET("posts")
    Flowable<List<Post>> getPostFromUsers(
            @Query("userId") int id
    );
}
