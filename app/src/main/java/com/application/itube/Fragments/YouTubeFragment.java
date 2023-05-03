package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.itube.R;
import com.google.android.material.transition.MaterialSharedAxis;

public class YouTubeFragment extends Fragment {

    private MaterialSharedAxis enterSharedAxis;
    private MaterialSharedAxis returnSharedAxis;

    public YouTubeFragment() {

    }


    public static YouTubeFragment newInstance() {

        YouTubeFragment fragment = new YouTubeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        enterSharedAxis = new MaterialSharedAxis(MaterialSharedAxis.X, true);
        returnSharedAxis = new MaterialSharedAxis(MaterialSharedAxis.X, false);

        // Let the default enter animation be fade through
        setEnterTransition(enterSharedAxis);
        setReturnTransition(returnSharedAxis);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_you_tube, container, false);
    }
}