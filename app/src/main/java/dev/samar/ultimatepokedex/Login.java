package dev.samar.ultimatepokedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.samar.ultimatepokedex.api.API;
import dev.samar.ultimatepokedex.model.dto.request.LoginDto;
import dev.samar.ultimatepokedex.model.dto.response.ErrorDto;
import dev.samar.ultimatepokedex.model.dto.response.RefreshTokenDto;
import dev.samar.ultimatepokedex.model.dto.response.UserDto;
import dev.samar.ultimatepokedex.prefs.AppPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;

public class Login extends AppCompatActivity {

    TextView register;
    private EditText usernameInput, passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);

        register = findViewById(R.id.login);
        // Initialize UI components
        usernameInput = findViewById(R.id.usernameLog);
        passwordInput = findViewById(R.id.passwordLog);
        loginButton = findViewById(R.id.buttonLog);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Set padding for the main layout to avoid overlap with system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize AppPreferences
        AppPreferences.initialize(this);

        // Check if user already has a valid access token
        if (AppPreferences.getInstance().getAccessToken() != null) {
            me();  // Fetch user details if already logged in
        }

        // Set the login button click listener
        loginButton.setOnClickListener(view -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", "Empty username or password");
            } else {
                // Attempt login with provided username and password
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        Log.d("DEBUG", "Attempting to log in with username: " + username);

        API.apiUser()
                .login(new LoginDto(username, password))
                .enqueue(new Callback<RefreshTokenDto>() {
                    @Override
                    public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull Response<RefreshTokenDto> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            RefreshTokenDto refreshTokenDto = response.body();
                            Log.d("DEBUG", "Login successful. Access Token: " + refreshTokenDto.getAccessToken());

                            // Save access token
                            AppPreferences.getInstance().setAccessToken(refreshTokenDto.getAccessToken());

                            // Proceed to fetch user details
                            me();
                        } else {
                            Log.e("DEBUG", "Login failed. Response code: " + response.code());
                            Toast.makeText(Login.this, "Login failed. Invalid credentials.", Toast.LENGTH_SHORT).show();
                            handleError(response);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RefreshTokenDto> call, @NonNull Throwable t) {
                        Log.e("DEBUG", "Failed to connect to the server", t);
                        Toast.makeText(Login.this, "Failed to login: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void me() {
        Log.d("DEBUG", "Fetching user details...");

        API.apiUser().me().enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(@NonNull Call<UserDto> call, @NonNull Response<UserDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(Login.this, BotNav.class);
                    startActivity(intent);
                    finish();
                    UserDto userDto = response.body();
                    Log.d("DEBUG", "User details fetched. Username: " + userDto.getUsername());
                    Toast.makeText(Login.this, "Welcome, " + userDto.getUsername(), Toast.LENGTH_SHORT).show();

                    // Redirect to BotNav activity

                   // Close the login activity
                } else {
                    Log.e("DEBUG", "Failed to fetch user details. Response code: " + response.code());
                    handleError(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserDto> call, @NonNull Throwable t) {
                Log.e("DEBUG", "Error fetching user details", t);
                Toast.makeText(Login.this, "Failed to fetch user details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleError(Response<?> response) {
        ResponseBody errorBody = response.errorBody();
        if (errorBody != null) {
            try {
                String json = errorBody.string();
                ErrorDto errorDto = API.gson.fromJson(json, ErrorDto.class);
                Log.e("DEBUG", "Error: " + errorDto.getDetail());
                Toast.makeText(Login.this, errorDto.getDetail(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                errorBody.close();
            }
        } else {
            Log.e("DEBUG", "Unknown error occurred");
            Toast.makeText(Login.this, "Unknown error occurred. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}

