package com.example.projectprn392flashcard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.adapter.AdminViewPagerAdapter;
import com.example.projectprn392flashcard.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminHomeActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);




        navigationView = findViewById(R.id.bottomNav);
        viewPager = findViewById(R.id.viewPager);

        AdminViewPagerAdapter adapter = new AdminViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.decks).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.feedback).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.profile) {
                    viewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.decks) {
                    viewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.feedback) {
                    viewPager.setCurrentItem(2);
                } else if (item.getItemId() == R.id.account) {
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}