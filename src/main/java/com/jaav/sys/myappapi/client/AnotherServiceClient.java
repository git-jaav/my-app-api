package com.jaav.sys.myappapi.client;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface AnotherServiceClient {

    @GET("api/sys/v1/process/stack")
    Call<List<String>> getProcessStack();

}
