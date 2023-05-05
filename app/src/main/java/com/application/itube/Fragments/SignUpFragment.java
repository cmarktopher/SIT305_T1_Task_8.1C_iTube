package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.itube.DataModels.User;
import com.application.itube.R;
import com.application.itube.Utilities.InputValidator;
import com.application.itube.ViewModels.UserViewModel;
import com.application.itube.databinding.FragmentLogInBinding;
import com.application.itube.databinding.FragmentSignUpBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment {

    // UI Binding
    private FragmentSignUpBinding binding;

    public SignUpFragment() {

    }

    public static SignUpFragment newInstance() {

        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create our binding and view
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.signUpCreateAccountButton.setOnClickListener(this::onCreateAccountPressed);

        return view;
    }

    /**
     * Gather inputs and create an account.
     * @param buttonView
     */
    private void onCreateAccountPressed(View buttonView){

        // Need to clear the errors on all views first
        binding.signUpFullNameInputLayout.setError(null);
        binding.signUpUserNameInputLayout.setError(null);
        binding.signUpPasswordInputLayout.setError(null);
        binding.signUpConfirmPasswordInputLayout.setError(null);

        String fullName;
        String userName;
        String passWord;
        String confirmPassWord;

        try {

            fullName = InputValidator.validateInputString(binding.signUpFullNameInputLayout, binding.signUpFullNameInputText, "A name must be provided");
            userName = InputValidator.validateInputString(binding.signUpUserNameInputLayout, binding.signUpUserNameInputText, "A user name must be provided");
            passWord = InputValidator.validateInputString(binding.signUpPasswordInputLayout, binding.signUpPasswordInputText,"A password must be provided");
            confirmPassWord = InputValidator.validateInputString(binding.signUpConfirmPasswordInputLayout, binding.signUpConfirmPasswordInputText,"Please re-enter password");

        }
        catch (Exception e){

            return;
        }

        // Need some extra logic to check if password matches
        if (!passWord.matches(confirmPassWord)) {

            binding.signUpPasswordInputLayout.setError("Passwords do not match");
            binding.signUpConfirmPasswordInputLayout.setError("Passwords do not match");
            return;
        }

        // Turn off error that might have been placed for these layouts
        binding.signUpPasswordInputLayout.setError(null);
        binding.signUpConfirmPasswordInputLayout.setError(null);

        // Create a new user
        User newUser = new User(fullName, userName, passWord);

        // Update the database with our new user
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.insertNewUser(newUser);

        Navigation.findNavController(getView()).popBackStack();
    }
}