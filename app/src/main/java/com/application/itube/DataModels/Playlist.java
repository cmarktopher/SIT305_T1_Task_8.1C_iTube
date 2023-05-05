package com.application.itube.DataModels;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Data model for playlist
 */

@Entity(tableName = "playlists")
public class Playlist {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "youtube_url")
    public String youTubeUrl;

    public Playlist(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }
}
