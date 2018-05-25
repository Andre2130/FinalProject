package com.example.musicplayerapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongDao {

    @Query("Select * from songs where id = :id")
    Song loadSongById(long id);

    @Insert
    public void addSong(Song song);

    @Query("SELECT * FROM songs")
    LiveData<List<Song>> getAllSongs();

    void insert(Song param);
}
