package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application.itube.R;
import com.application.itube.Utilities.CredentialsValidator;
import com.application.itube.Utilities.InputValidator;
import com.application.itube.ViewModels.UserViewModel;
import com.application.itube.databinding.FragmentLogInBinding;
import com.google.android.material.transition.MaterialFadeThrough;

public class LogInFragment extends Fragment {

    private FragmentLogInBinding binding;

    public LogInFragment() {
        // Required empty public constructor
    }

    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        MaterialFadeThrough exitFadeThrough = new MaterialFadeThrough();
        exitFadeThrough.setDuration(2000);
        setExitTransition(exitFadeThrough);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create our binding and view
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Bind to log in button
        binding.logInButton.setOnClickListener(this::onLogInPressed);

        // Bind to sign up button
        binding.signUpButton.setOnClickListener(this::onSignUpPressed);

        return view;
    }

    private void onLogInPressed(View view){

        // Clear errors
        binding.logInUserNameInputLayout.setError(null);
        binding.logInPasswordInputLayout.setError(null);

        // Get our username and password
        String userName;
        String password;

        try {
            userName = InputValidator.validateInputString(binding.logInUserNameInputLayout, binding.logInUserNameInputView, "Please provide your username");
            password = InputValidator.validateInputString(binding.logInPasswordInputLayout, binding.logInPasswordInputView, "Please provide your password");
        }
        catch (Exception e){

            return;
        }

        // Validate credentials
        if (CredentialsValidator.validateCredentials(getActivity(), userName, password)){

            NavDirections action = LogInFragmentDirections.actionLogInFragmentToHomeFragment();
            Navigation.findNavController(getView()).navigate(action);
            return;
        }

        // If we reach here, that means authentication has failed.
        // Could do a toast message, but thought it be nicer to have these error messages.
        binding.logInUserNameInputLayout.setError("Authentication Failed: Please check your username");
        binding.logInPasswordInputLayout.setError("Authentication Failed: Please check your password");
    }

    private void onSignUpPressed(View view){

        NavDirections action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment();
        Navigation.findNavController(getView()).navigate(action);
    }
}