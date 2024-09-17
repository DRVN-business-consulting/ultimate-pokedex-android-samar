package dev.samar.ultimatepokedex.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import dev.samar.ultimatepokedex.api.API;
import dev.samar.ultimatepokedex.model.dto.response.RefreshTokenDto;
import dev.samar.ultimatepokedex.prefs.AppPreferences;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class AuthInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String url = originalRequest.url().toString();

        // Ensure AppPreferences is not null
        AppPreferences appPreferences = AppPreferences.getInstance();
        if (appPreferences == null) {
            throw new IOException("AppPreferences is not initialized.");
        }

        String accessToken = appPreferences.getAccessToken();
        if (accessToken != null) {
            Request newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            return chain.proceed(newRequest);
        } else {
            // If there is no access token, proceed with the original request
            return chain.proceed(originalRequest);
        }
    }

    private void refreshToken() {
        AppPreferences appPreferences = AppPreferences.getInstance();
        if (appPreferences == null) {
            // Handle the case where AppPreferences is not initialized
            return;
        }

        API.apiUser().refreshToken().enqueue(new Callback<RefreshTokenDto>() {
            @Override
            public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull retrofit2.Response<RefreshTokenDto> response) {
                if (response.isSuccessful()) {
                    RefreshTokenDto refreshTokenDto = response.body();
                    if (refreshTokenDto != null) {
                        appPreferences.setAccessToken(refreshTokenDto.getAccessToken());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RefreshTokenDto> call, @NonNull Throwable t) {
                appPreferences.setAccessToken(null);
            }
        });
    }
}
