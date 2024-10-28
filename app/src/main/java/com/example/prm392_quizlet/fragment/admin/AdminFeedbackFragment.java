package com.example.projectprn392flashcard.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectprn392flashcard.R;

public class AdminFeedbackFragment extends Fragment {

    private static final String ARG_FEEDBACK_PARAM1 = "feedbackParam1";
    private static final String ARG_FEEDBACK_PARAM2 = "feedbackParam2";

    private String feedbackParam1;
    private String feedbackParam2;

    public AdminFeedbackFragment() {
        // Required empty public constructor
    }

    public static AdminFeedbackFragment newInstance(String param1, String param2) {
        AdminFeedbackFragment fragment = new AdminFeedbackFragment();
        AdminFeedbackFragmentArgs args = new AdminFeedbackFragmentArgs(param1, param2);
        fragment.setArguments(args.toBundle());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            AdminFeedbackFragmentArgs args = AdminFeedbackFragmentArgs.fromBundle(getArguments());
            feedbackParam1 = args.getParam1();
            feedbackParam2 = args.getParam2();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_feedback, container, false);
    }
}

class AdminFeedbackFragmentArgs {

    private static final String ARG_FEEDBACK_PARAM1 = "feedbackParam1";
    private static final String ARG_FEEDBACK_PARAM2 = "feedbackParam2";

    private final String param1;
    private final String param2;

    public AdminFeedbackFragmentArgs(String param1, String param2) {
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
        args.putString(ARG_FEEDBACK_PARAM1, param1);
        args.putString(ARG_FEEDBACK_PARAM2, param2);
        return args;
    }

    public static AdminFeedbackFragmentArgs fromBundle(Bundle bundle) {
        String param1 = bundle.getString(ARG_FEEDBACK_PARAM1);
        String param2 = bundle.getString(ARG_FEEDBACK_PARAM2);
        return new AdminFeedbackFragmentArgs(param1, param2);
    }
}
