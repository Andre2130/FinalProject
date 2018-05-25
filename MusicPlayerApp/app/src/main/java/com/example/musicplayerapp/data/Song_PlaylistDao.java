package com.example.musicplayerapp.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Song_PlaylistDao {
    @Query("SELECT * FROM SONG_PLAYLIST_PAIRS")
    List<Song_Playlist> loadSong_playlist_pairs();
}
