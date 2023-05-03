package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.itube.R;
import com.application.itube.databinding.FragmentHomeBinding;
import com.application.itube.databinding.FragmentLogInBinding;
import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.MaterialSharedAxis;


public class HomeFragment extends Fragment {

    private MaterialFadeThrough enterFadeThrough;
    private MaterialFadeThrough exitFadeThrough;
    private MaterialSharedAxis exitSharedAxis;
    private MaterialSharedAxis reEnterSharedAxis;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set up our animations
        enterFadeThrough = new MaterialFadeThrough();
        enterFadeThrough.setDuration(2000);

        exitFadeThrough = new MaterialFadeThrough();
        exitFadeThrough.setDuration(2000);

        exitSharedAxis = new MaterialSharedAxis(MaterialSharedAxis.X, true);
        reEnterSharedAxis = new MaterialSharedAxis(MaterialSharedAxis.X, false);

        // Let the default enter animation be fade through
        setEnterTransition(enterFadeThrough);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create our binding and view
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Bind buttons
        binding.homeYouTubePlayButton.setOnClickListener(this::onPlayPressed);
        binding.homeAddToPlaylistButton.setOnClickListener(this::addToPlaylistPressed);
        binding.homeMyPlaylistButton.setOnClickListener(this::myPlaylistPressed);

        return view;
    }

    private void onPlayPressed(View view){

        // Set our transition animations
        setExitTransition(exitSharedAxis);
        setReenterTransition(reEnterSharedAxis);

        // Perform the transition
        NavDirections action = HomeFragmentDirections.actionHomeFragmentToYouTubeFragment();
        Navigation.findNavController(getView()).navigate(action);
    }

    private void addToPlaylistPressed(View view){

    }

    private void myPlaylistPressed(View view){

        // Set our transition animations
        setExitTransition(exitFadeThrough);
        setReenterTransition(enterFadeThrough);

        // Perform the transition
        NavDirections action = HomeFragmentDirections.actionHomeFragmentToPlaylistFragment();
        Navigation.findNavController(getView()).navigate(action);

    }
}