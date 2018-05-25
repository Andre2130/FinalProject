package com.example.musicplayerapp.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MusicRepository {
    private SongDao songDao;
    private PlaylistDao playlistDao;
    private LiveData<List<Playlist>> allPlaylists;
    private LiveData<List<Song>> allSongs;

    public MusicRepository(Application app){
        MyAppDatabase db = MyAppDatabase.getDatabase(app);
        songDao = db.songDao();
        playlistDao = db.playlistDao();
        allSongs = songDao.getAllSongs();
        allPlaylists = playlistDao.getAllPlaylists();
    }

    public LiveData<List<Song>> getAllSongs(){
        return allSongs;
    }

    public LiveData<List<Playlist>> getAllPlaylists(){return allPlaylists;}

    public void insert(Song song){
        new insertAsyncTask(songDao).execute(song);
    }

    public void insert(Playlist playlist){ new insertPlaylistAsyncTask(playlistDao).execute(playlist);}

    private class insertAsyncTask extends AsyncTask<Song, Void, Void> {

        private SongDao mAsyncTaskDao;

        public insertAsyncTask(SongDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Song... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private class insertPlaylistAsyncTask extends AsyncTask<Playlist, Void, Void> {

        private PlaylistDao mAsyncTaskDao;

        public insertPlaylistAsyncTask(PlaylistDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Playlist... params){
            mAsyncTaskDao.addPlaylist(params[0]);
            return null;
        }
    }
}
