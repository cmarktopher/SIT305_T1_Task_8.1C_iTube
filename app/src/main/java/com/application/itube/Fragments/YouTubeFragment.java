package com.application.itube.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.application.itube.R;
import com.application.itube.databinding.FragmentHomeBinding;
import com.application.itube.databinding.FragmentYouTubeBinding;
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

        // Create our binding and view
        FragmentYouTubeBinding binding = FragmentYouTubeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        handleWebViewYouTubeEmbed(binding);

        return view;
    }

    /**
     * So, since the official YouTube Android Player API is no longer supported,
     * I'll use this source to show me how to use web view and embed a youtube link.
     * https://androidchunk.com/how-to-embed-youtube-video-in-android-webview/
     * I assume it is not good to use deprecated or no longer supported APIs, especially if there is security issues.
     * @param binding
     */
    private void handleWebViewYouTubeEmbed(FragmentYouTubeBinding binding){

        WebView webView = binding.webView;

        // Lets get the url from what we passed in via the navigation controller
        String url = YouTubeFragmentArgs.fromBundle(getArguments()).getUrlArgs();

        // If the string is null or empty, then return
        if (url == null || url.isEmpty()){

            return;
        }

        // We essentially need to provide html and place our link within an iframe tag
        String html = "<html>" +
                "<body>" +
                "<iframe width=\"100%\" height=\"100%\" src=\"" + url + "\" frameborder=\"0\" allowfullscreen></iframe>" +
                "</body>" +
                " </html>";

        // Some additional settings
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);


        // Load the URL
        webView.loadData(html, "text/html", "utf-8");
    }
}