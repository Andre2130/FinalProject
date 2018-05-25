package com.example.musicplayerapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PlaylistDao {
    @Query("SELECT * FROM PLAYLISTS")
    LiveData<List<Playlist>> getAllPlaylists();

    @Insert
    public void addPlaylist(Playlist playlist);
}
