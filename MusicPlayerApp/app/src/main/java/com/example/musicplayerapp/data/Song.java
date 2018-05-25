package com.example.musicplayerapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "songs")
public class Song implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "song_id")
    private long _id;

    @ColumnInfo(name = "song_title")
    private String title;

    @ColumnInfo(name = "song_artist")
    private String artist;

    public Song(long _id, String title, String artist) {
        this._id = _id;
        this.title = title;
        this.artist = artist;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Song(Parcel in) {
        _id = in.readLong();
        id = in.readLong();
        title = in.readString();
        artist = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(_id);
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(artist);
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}