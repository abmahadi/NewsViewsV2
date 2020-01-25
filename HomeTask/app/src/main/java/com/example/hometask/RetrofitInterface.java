package com.example.hometask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("15baeq")
    Call<List<UserInformation>> getData();


}
