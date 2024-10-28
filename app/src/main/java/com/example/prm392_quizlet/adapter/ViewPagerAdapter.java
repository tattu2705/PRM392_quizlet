package com.example.projectprn392flashcard.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectprn392flashcard.fragment.user.CreateDeckFragment;
import com.example.projectprn392flashcard.fragment.user.ProfileFragment;
import com.example.projectprn392flashcard.fragment.user.SearchFragment;
import com.example.projectprn392flashcard.fragment.user.UserHomeFragment;
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 4;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment[] fragments = new Fragment[] {
                new UserHomeFragment(),
                new SearchFragment(),
                new CreateDeckFragment(),
                new ProfileFragment()
        };

        if (position >= 0 && position < fragments.length) {
            return fragments[position];
        } else {
            throw new IllegalArgumentException("Vị trí không hợp lệ: " + position);
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
