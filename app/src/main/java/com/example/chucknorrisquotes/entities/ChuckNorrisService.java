package com.example.chucknorrisquotes.entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChuckNorrisService {
    //calls API quote method filtered by category
    @GET("random")
    Call<ChuckNorrisResponse> getQuote(@Query("category") String category);
}
