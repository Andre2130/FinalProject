package com.example.musicplayerapp.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PlaylistViewModel extends AndroidViewModel {
    private MusicRepository musicRepository;
    private LiveData<List<Playlist>> allPlaylists;

    public PlaylistViewModel(Application app){
        super(app);
        musicRepository = new MusicRepository(app);
        allPlaylists = musicRepository.getAllPlaylists();
    }

    LiveData<List<Playlist>> getAllPlaylists(){
        return allPlaylists;
    }

    public void insert(Playlist playlist){
        musicRepository.insert(playlist);
    }
}
