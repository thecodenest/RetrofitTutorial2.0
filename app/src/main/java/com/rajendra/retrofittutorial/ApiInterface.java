package com.rajendra.retrofittutorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("posts")
    Call<List<Post>> getPost();

    @POST("posts")
    Call<PostData> postDataToServer(@Body PostData postData);


    @GET("posts")
    Call<List<Post>> getPostById(@Query("userId") Integer userId);

    @PATCH("posts/{id}")
    Call<List<Post>> getPatch(@Path("id") int id, @Body Post post);


    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
