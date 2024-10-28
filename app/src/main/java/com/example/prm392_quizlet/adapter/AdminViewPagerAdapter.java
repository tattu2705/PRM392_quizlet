package com.example.projectprn392flashcard.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectprn392flashcard.fragment.admin.AdminAccountFragment;
import com.example.projectprn392flashcard.fragment.admin.AdminDeckFragment;
import com.example.projectprn392flashcard.fragment.admin.AdminFeedbackFragment;
import com.example.projectprn392flashcard.fragment.admin.AdminUserFragment;

public class AdminViewPagerAdapter extends FragmentStatePagerAdapter {

    public AdminViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public AdminViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new AdminDeckFragment();
            case 1: return new AdminUserFragment();
            case 2: return new AdminFeedbackFragment();
            case 3: return new AdminAccountFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
