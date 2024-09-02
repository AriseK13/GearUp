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

public class BuyerRegister extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailAddEditText;
    private EditText mobileNoEditText;
    private Button registerButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_register);

        // Initialize EditText fields
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmpassword);
        firstNameEditText = findViewById(R.id.etfn);
        lastNameEditText = findViewById(R.id.etln);
        emailAddEditText = findViewById(R.id.etemailadd);
        mobileNoEditText = findViewById(R.id.etemobileno);

        // Initialize Buttons
        registerButton = findViewById(R.id.btn1);
        cancelButton = findViewById(R.id.btn2);

        // Set click listener for the Register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditTexts
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String emailAdd = emailAddEditText.getText().toString().trim();
                String mobileNo = mobileNoEditText.getText().toString().trim();

                // Validate inputs
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                        firstName.isEmpty() || lastName.isEmpty() || emailAdd.isEmpty() || mobileNo.isEmpty()) {
                    Toast.makeText(BuyerRegister.this, "All fields must not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(BuyerRegister.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save the registered credentials to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Inside BuyerRegister.java
                // Just before storing the credentials
                editor.putString(email, password);
                editor.putString(email + "_type", "buyer");  // Store user type as "buyer"
                editor.apply();


                // If validation is successful, show a success message
                Toast.makeText(BuyerRegister.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                // Navigate to LoginActivity after successful registration
                Intent intent = new Intent(BuyerRegister.this, Login.class);
                startActivity(intent);

                // Optionally, finish the current activity so the user cannot return to it
                finish();
            }
        });

        // Set click listener for the Cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous activity
                finish();
            }
        });
    }
}
