package com.example.projectprn392flashcard.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectprn392flashcard.R;

public class AdminHomeFragment extends Fragment {

    private static final String ARG_HOME_PARAM1 = "homeParam1";
    private static final String ARG_HOME_PARAM2 = "homeParam2";

    private String homeParam1;
    private String homeParam2;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    public static AdminHomeFragment newInstance(String param1, String param2) {
        AdminHomeFragment fragment = new AdminHomeFragment();
        AdminHomeFragmentArgs args = new AdminHomeFragmentArgs(param1, param2);
        fragment.setArguments(args.toBundle());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            AdminHomeFragmentArgs args = AdminHomeFragmentArgs.fromBundle(getArguments());
            homeParam1 = args.getParam1();
            homeParam2 = args.getParam2();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_home, container, false);
    }
}

class AdminHomeFragmentArgs {

    private static final String ARG_HOME_PARAM1 = "homeParam1";
    private static final String ARG_HOME_PARAM2 = "homeParam2";
    private final String param1;
    private final String param2;

    public AdminHomeFragmentArgs(String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public Bundle toBundle() {
        Bundle args = new Bundle();
        args.putString(AdminHomeFragmentArgs.ARG_HOME_PARAM1, param1);
        args.putString(AdminHomeFragmentArgs.ARG_HOME_PARAM2, param2);
        return args;
    }

    public static AdminHomeFragmentArgs fromBundle(Bundle bundle) {
        String param1 = bundle.getString(AdminHomeFragmentArgs.ARG_HOME_PARAM1);
        String param2 = bundle.getString(AdminHomeFragmentArgs.ARG_HOME_PARAM2);
        return new AdminHomeFragmentArgs(param1, param2);
    }
}
