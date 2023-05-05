package com.application.itube.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.application.itube.DataModels.Playlist;
import com.application.itube.DataModels.User;
import com.application.itube.Database.ITubeDatabase;
import com.application.itube.Repositories.PlaylistRepository;
import com.application.itube.Repositories.UserRepository;

import java.util.List;

/**
 * View model for the playlist to bridge the UI to the repository.
 */
public class PlaylistViewModel extends AndroidViewModel {

    // Reference to playlist repository
    private PlaylistRepository playlistRepository;

    // Live data of playlist urls
    private final LiveData<List<Playlist>> playlistUrls;

    /**
     * Initialize our playlist view model
     * @param application
     */
    public PlaylistViewModel(@NonNull Application application) {

        super(application);
        playlistRepository = new PlaylistRepository(application);
        playlistUrls = playlistRepository.getAllPlaylistUrls();
    }

    /**
     * Get all playlist urls
     * @return
     */
    public LiveData<List<Playlist>> getAllPlaylistUrls(){

        return playlistUrls;
    }

    /**
     * Insert a new playlist url
     * @param newPlaylistUrl
     */
    public void insertNewPlaylistUrl(Playlist newPlaylistUrl){

        playlistRepository.insertNewPlaylistUrl(newPlaylistUrl);
    }
}
