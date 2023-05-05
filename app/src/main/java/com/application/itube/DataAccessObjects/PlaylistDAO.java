package com.application.itube.DataAccessObjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.application.itube.DataModels.Playlist;

import java.util.List;

@Dao
public interface PlaylistDAO {

    @Query("SELECT * FROM playlists")
    LiveData<List<Playlist>> getAllUrls();

    @Insert
    void insertNewUrl(Playlist newPlaylist);
}

