package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.itube.R;
import com.application.itube.RecyclerView.PlaylistAdapter;
import com.application.itube.ViewModels.PlaylistViewModel;
import com.application.itube.databinding.FragmentLogInBinding;
import com.application.itube.databinding.FragmentPlaylistBinding;

public class PlaylistFragment extends Fragment {

    public PlaylistFragment() {


    }


    public static PlaylistFragment newInstance() {

        PlaylistFragment fragment = new PlaylistFragment();
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
        FragmentPlaylistBinding binding = FragmentPlaylistBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Create and bind recycler view adapter to the recycler view
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(requireActivity());
        binding.playlistRecyclerView.setAdapter(playlistAdapter);
        binding.playlistRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));

        PlaylistViewModel playlistViewModel = new ViewModelProvider(requireActivity()).get(PlaylistViewModel.class);
        playlistViewModel.getAllPlaylistUrls().observe(getViewLifecycleOwner(), items -> {

            playlistAdapter.updatePlaylist(items);
        });

        return view;
    }
}