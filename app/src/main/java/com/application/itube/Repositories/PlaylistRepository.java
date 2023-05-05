package com.application.itube.Repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.application.itube.DataAccessObjects.PlaylistDAO;
import com.application.itube.DataModels.Playlist;
import com.application.itube.Database.ITubeDatabase;
import java.util.List;

/**
 * Repository to handle interaction with the playlist table in the database via the playlist data access object.
 */
public class PlaylistRepository {

    // Cache the application ref
    private Application currentApplication;

    // Playlist data access object
    private PlaylistDAO playlistDAO;

    // Live data of our playlist
    private LiveData<List<Playlist>> playlistUrls;

    // Constructor to initialize our repository
    public PlaylistRepository(Application application){

        currentApplication = application;
        playlistDAO = ITubeDatabase.getDatabase(application).playlistDAO();
        playlistUrls = playlistDAO.getAllUrls();
    }

    /**
     * Get all playlist urls
     * @return
     */
    public LiveData<List<Playlist>> getAllPlaylistUrls() {
        return playlistUrls;
    }

    /**
     * Insert a new playlist url
     * @param newPlaylistUrl
     */
    public void insertNewPlaylistUrl(Playlist newPlaylistUrl){

        ITubeDatabase.databaseWriteExecutor.execute(() -> {
            playlistDAO.insertNewUrl(newPlaylistUrl);
        });
    }

}
