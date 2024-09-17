package dev.samar.ultimatepokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.samar.ultimatepokedex.api.API;
import dev.samar.ultimatepokedex.api.SignUpApi;
import dev.samar.ultimatepokedex.model.dto.SignUpRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText userName, password, name, age, address;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        submitButton = findViewById(R.id.buttonReg);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect data from the form
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String fullName = name.getText().toString();
                int userAge;
                try {
                    userAge = Integer.parseInt(age.getText().toString());
                } catch (NumberFormatException e) {
                    userAge = 0; // Default value or handle the error
                }
                String userAddress = address.getText().toString();

                // Create SignUpRequest object
                SignUpRequest signUpRequest = new SignUpRequest(user, pass, fullName, userAge, userAddress);

                // Call the API
                SignUpApi signUpApi = API.getSignUpApi();
                signUpApi.signup(signUpRequest).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign up failed: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Sign up error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.login);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }
}
