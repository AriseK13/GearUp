package com.example.gearup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize EditText fields
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginbtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Validate login credentials
                    if (validateLogin(email, password)) {
                        Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // Check if the user is a seller or buyer
                        if (isSeller(email)) {
                            // Navigate to SellerBottomNavigation if it's a seller
                            Intent intent = new Intent(Login.this, SellerBottomNavigator.class);
                            startActivity(intent);
                        } else {
                            // Navigate to BuyerBottomNavigation if it's a buyer
                            Intent intent = new Intent(Login.this, BuyerBottomNavigation.class);
                            startActivity(intent);
                        }
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Inside Login.java
    private boolean validateLogin(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // Check if the entered email and password match any stored credentials
        String registeredPassword = sharedPreferences.getString(email, null);
        return registeredPassword != null && registeredPassword.equals(password);
    }

    private boolean isSeller(String email) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String userType = sharedPreferences.getString(email + "_type", "buyer");  // Default to "buyer" if not found
        return "seller".equals(userType);
    }
}
