package com.example.gearup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SellerRegister extends AppCompatActivity {

    private EditText etUsername, etPassword, etConfirmPassword, etFirstName, etLastName, etEmail, etMobileNo, etAddress;
    private Button btnRegister, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        // Initialize views
        etUsername = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirmpassword);
        etFirstName = findViewById(R.id.etfn);
        etLastName = findViewById(R.id.etln);
        etEmail = findViewById(R.id.etemailadd);
        etMobileNo = findViewById(R.id.etemobileno);
        etAddress = findViewById(R.id.etadress);
        btnRegister = findViewById(R.id.btn1);
        btnCancel = findViewById(R.id.btn2);

        // Handle Register button click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSeller();
            }
        });

        // Handle Cancel button click
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous screen
            }
        });
    }

    // Inside SellerRegister.java
    private void registerSeller() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mobileNo = etMobileNo.getText().toString().trim();
        String address = etAddress.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)
                || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(mobileNo) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the registered credentials to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, password);
        editor.putString(username + "_type", "seller");  // Store user type as "seller"
        editor.apply();

        // Show a success message
        Toast.makeText(this, "Seller registered successfully", Toast.LENGTH_SHORT).show();

        // Navigate back to LoginActivity after successful registration
        Intent intent = new Intent(SellerRegister.this, Login.class);
        startActivity(intent);
        finish();
    }
}
