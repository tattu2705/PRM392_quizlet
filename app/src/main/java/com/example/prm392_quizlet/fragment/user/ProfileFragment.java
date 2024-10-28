package com.example.projectprn392flashcard.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.IntroduceActivity;
import com.example.projectprn392flashcard.activity.LoginActivity;
import com.example.projectprn392flashcard.activity.feedback.SendFeedbackActivity;
import com.example.projectprn392flashcard.activity.user.AccountInfoActivity;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

public class ProfileFragment extends Fragment {

    private Button btnAccountInfo, btnSendFeedback, btnIntroduce, btnDeleteAccount, btnLogout;
    private UserDAO userDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupUserDAO();
        setupListeners();
    }

    private void initializeViews(View view) {
        btnAccountInfo = view.findViewById(R.id.btn_account_info);
        btnSendFeedback = view.findViewById(R.id.btn_send_feedback);
        btnIntroduce = view.findViewById(R.id.btn_introduce);
        btnDeleteAccount = view.findViewById(R.id.btn_delete_account);
        btnLogout = view.findViewById(R.id.btn_logout);
    }

    private void setupUserDAO() {
        userDAO = new UserDAO(getContext());
    }

    private void setupListeners() {
        btnAccountInfo.setOnClickListener(v -> navigateToAccountInfoActivity());

        btnSendFeedback.setOnClickListener(v -> navigateToSendFeedbackActivity());

        btnIntroduce.setOnClickListener(v -> navigateToIntroduceActivity());

        btnDeleteAccount.setOnClickListener(v -> showDeleteAccountConfirmationDialog());

        btnLogout.setOnClickListener(v -> handleLogout());
    }

    private void navigateToAccountInfoActivity() {
        Intent intent = new Intent(getActivity(), AccountInfoActivity.class);
        startActivity(intent);
    }

    private void navigateToSendFeedbackActivity() {
        Intent intent = new Intent(getActivity(), SendFeedbackActivity.class);
        startActivity(intent);
    }

    private void navigateToIntroduceActivity() {
        Intent intent = new Intent(getActivity(), IntroduceActivity.class);
        startActivity(intent);
    }

    private void showDeleteAccountConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    deleteAccount();
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteAccount() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        User user = userDAO.getUserById(userId);
        user.setStatus(0);
        userDAO.updateUser(user);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

        Toast.makeText(getContext(), "Your account has been deleted", Toast.LENGTH_SHORT).show();
    }

    private void handleLogout() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
