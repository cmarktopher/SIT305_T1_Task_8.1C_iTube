package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.itube.R;
import com.application.itube.databinding.FragmentLogInBinding;

public class LogInFragment extends Fragment {



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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Create our binding and view
        FragmentLogInBinding binding = FragmentLogInBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Bind to log in button
        binding.logInButton.setOnClickListener(this::onLogInPressed);

        return view;
    }

    private void onLogInPressed(View view){

        NavDirections action = LogInFragmentDirections.actionLogInFragmentToHomeFragment();
        Navigation.findNavController(getView()).navigate(action);
    }
}