package com.example.musicplayerapp.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class SongViewModel extends AndroidViewModel {
    private MusicRepository musicRepository;

    private LiveData<List<Song>> allSongs;

    public SongViewModel(Application app){
        super(app);
        musicRepository = new MusicRepository(app);
        allSongs = musicRepository.getAllSongs();
    }

    public LiveData<List<Song>> getAllSongs(){
        return allSongs;
    }

    public void insert(Song song){
        musicRepository.insert(song);
    }
}
