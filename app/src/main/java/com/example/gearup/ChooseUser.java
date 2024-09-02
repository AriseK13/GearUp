package com.example.gearup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseUser extends AppCompatActivity {

    RadioButton seller, buyer, rider;
    Button btn;
    RadioButton lastChecked = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        seller = findViewById(R.id.seller);
        buyer = findViewById(R.id.buyer);
        rider = findViewById(R.id.rider);
        btn = findViewById(R.id.proceedbtn);

        // Setting a listener for each radio button to handle selection and deselection
        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton clickedRadioButton = (RadioButton) v;

                // If the clicked radio button is the last one checked, deselect it
                if (clickedRadioButton == lastChecked) {
                    clickedRadioButton.setChecked(false);
                    lastChecked = null;
                } else {
                    // Otherwise, deselect the previous selection (if any) and check the new one
                    if (lastChecked != null) {
                        lastChecked.setChecked(false);
                    }
                    clickedRadioButton.setChecked(true);
                    lastChecked = clickedRadioButton;
                }
            }
        };

        // Attach the listener to each radio button
        seller.setOnClickListener(radioButtonClickListener);
        buyer.setOnClickListener(radioButtonClickListener);
        rider.setOnClickListener(radioButtonClickListener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (seller.isChecked()) {
                    intent = new Intent(ChooseUser.this, SellerRegister.class);
                } else if (buyer.isChecked()) {
                    intent = new Intent(ChooseUser.this, BuyerRegister.class);
                } else if (rider.isChecked()) {
                    intent = new Intent(ChooseUser.this, RiderRegister.class);
                }

                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(ChooseUser.this, "Please select a role", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
