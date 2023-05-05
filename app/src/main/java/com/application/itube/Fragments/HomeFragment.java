package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.application.itube.DataModels.Playlist;
import com.application.itube.Fragments.LogInFragmentDirections;
import com.application.itube.R;
import com.application.itube.ViewModels.PlaylistViewModel;
import com.application.itube.ViewModels.UserViewModel;
import com.application.itube.databinding.FragmentHomeBinding;
import com.application.itube.databinding.FragmentLogInBinding;
import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.MaterialSharedAxis;


public class HomeFragment extends Fragment {

    // Binding
    private FragmentHomeBinding binding;

    // Animations
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Bind buttons
        binding.homeYouTubePlayButton.setOnClickListener(this::onPlayPressed);
        binding.homeAddToPlaylistButton.setOnClickListener(this::addToPlaylistPressed);
        binding.homeMyPlaylistButton.setOnClickListener(this::myPlaylistPressed);

        return view;
    }

    private void onPlayPressed(View view){

        binding.homeYouTubeInputLayout.setError(null);

        // Set our transition animations
        setExitTransition(exitSharedAxis);
        setReenterTransition(reEnterSharedAxis);

        // Grab the url from the input box
        String url = binding.homeYouTubeInputText.getText().toString();

        if (url.isEmpty()){
            binding.homeYouTubeInputLayout.setError("Please enter a URL");
            return;
        }

        // Perform the transition and pass in the url
        com.application.itube.Fragments.HomeFragmentDirections.ActionHomeFragmentToYouTubeFragment action = HomeFragmentDirections.actionHomeFragmentToYouTubeFragment(url);
        Navigation.findNavController(getView()).navigate(action);
    }

    private void addToPlaylistPressed(View view){

        String url = binding.homeYouTubeInputText.getText().toString();

        if (url.isEmpty()){

            binding.homeYouTubeInputLayout.setError("Please enter a URL");
            return;
        }

        PlaylistViewModel playlistViewModel = new ViewModelProvider(requireActivity()).get(PlaylistViewModel.class);
        playlistViewModel.insertNewPlaylistUrl(new Playlist(url));

        // Lets also inform the user that the playlist was added
        // Might as well use a toast for this
        Toast toast = new Toast(getContext());
        toast.setText("Youtube URL link added to playlists");
        toast.show();
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