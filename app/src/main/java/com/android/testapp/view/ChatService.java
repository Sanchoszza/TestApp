package com.android.testapp.view;


import com.android.testapp.network.ChatRequest;
import com.android.testapp.network.response.ChatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ChatService {
    @POST("completions")
    Call<ChatResponse> getCompletion(@Header("Authorization") String authorization, @Body ChatRequest request);
}
