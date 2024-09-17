package dev.samar.ultimatepokedex.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import dev.samar.ultimatepokedex.interceptor.AuthInterceptor;

public class API {

    private static final String BASE_URL = "https://a508-202-90-134-36.ngrok-free.app/";



    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final AuthInterceptor AUTH_INTERCEPTOR = new AuthInterceptor();

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .addInterceptor(HTTP_LOGGING_INTERCEPTOR)
            .addInterceptor(AUTH_INTERCEPTOR)
            .build();

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OK_HTTP_CLIENT)
            .build();

    public static UserApi apiUser() {
        return RETROFIT.create(UserApi.class);
    }

    public static SignUpApi getSignUpApi() {
        return RETROFIT.create(SignUpApi.class);
    }
}
