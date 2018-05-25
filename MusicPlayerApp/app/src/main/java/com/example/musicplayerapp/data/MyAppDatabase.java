package com.example.musicplayerapp.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Song.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDao myDao();
    public abstract SongDao songDao();
    public abstract PlaylistDao playlistDao();

    private static MyAppDatabase INSTANCE;

    public static MyAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyAppDatabase.class) {

                if (INSTANCE == null) {


                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyAppDatabase.class, "")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();

        }
    };



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final MyDao myDao;

        PopulateDbAsync(MyAppDatabase db){
            myDao = db.myDao();
        }

        @Override
        protected Void doInBackground(Void... voids){

            return null;
        }
    }
}
