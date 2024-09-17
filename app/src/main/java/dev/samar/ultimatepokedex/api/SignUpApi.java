package dev.samar.ultimatepokedex.api;

import dev.samar.ultimatepokedex.model.dto.SignUpRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {

    @POST("signup")
    Call<Void> signup(@Body SignUpRequest signupRequest);


    void enqueue(Callback<Void> callback);
}