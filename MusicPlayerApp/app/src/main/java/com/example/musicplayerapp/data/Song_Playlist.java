package com.example.musicplayerapp.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "song_playlist_pairs")
public class Song_Playlist implements Parcelable {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "song_id")
    private long song_id;

    @ColumnInfo(name = "playlist_id")
    private long playlist_id;

    public Song_Playlist(long id, long song_id, long playlist_id){
        this.id = id;
        this.song_id = song_id;
        this.playlist_id = playlist_id;
    }

    public long getId() {
        return id;
    }
    public long getSong_id() {
        return song_id;
    }
    public long getPlalist_id() { return playlist_id; }

    public void setId(long id) {
        this.id = id;
    }
    public void setSong_id(long song_id) {
        this.song_id = song_id;
    }
    public void setPlalist_id(long song_id) { this.playlist_id = playlist_id; }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Song_Playlist(Parcel in) {
        id = in.readLong();
        song_id = in.readLong();
        playlist_id = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(song_id);
        parcel.writeLong(playlist_id);
    }

    public static final Creator<Song_Playlist> CREATOR = new Creator<Song_Playlist>() {
        @Override
        public Song_Playlist createFromParcel(Parcel in) {
            return new Song_Playlist(in);
        }

        @Override
        public Song_Playlist[] newArray(int size) {
            return new Song_Playlist[size];
        }
    };
}
