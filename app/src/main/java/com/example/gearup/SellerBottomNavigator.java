package com.example.gearup;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gearup.R;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;




public class SellerBottomNavigator extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_bottom_navigator);
        getSupportActionBar().hide();

        loadFragments(new HomeFragment());
        bottomNavigationView=findViewById(R.id.bottomView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public boolean loadFragments(Fragment fragment)
    {
        if(fragment!=null )
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.parent_container,fragment)
                    .commit();
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment= null;

        switch (item.getItemId()) {
            case R.id.homeId:
                fragment = new HomeFragment();
                break;
            case R.id.trendsId:
                fragment = new TrendsFragment();
                break;
            case R.id.inventoryId:
                fragment = new InventoryFragment();
                break;
            case R.id.notiId:
                fragment = new NotificationsFragment();
                break;
            case R.id.meId:
                fragment = new ProfileFragment();
                break;
        }

        return loadFragments(fragment);
    }
    @Override
    public void onBackPressed()
    {
        if(bottomNavigationView.getSelectedItemId()==R.id.homeId)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            bottomNavigationView.setSelectedItemId(R.id.homeId);
        }

    }
}