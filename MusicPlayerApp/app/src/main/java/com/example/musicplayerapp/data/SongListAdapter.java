package com.example.musicplayerapp.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musicplayerapp.R;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private List<Song> songList;
    private Context c;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView artist;
        public TextView id;


        public ViewHolder(View iv){
            super(iv);

            title = iv.findViewById(R.id.title);
            artist = iv.findViewById(R.id.artist);
            id = iv.findViewById(R.id.id);
        }
    }

    public SongListAdapter(Context c){
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = inflater.inflate(R.layout.song_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListAdapter.ViewHolder vh, int pos){
        if(songList != null){
            Song curSong = songList.get(pos);

            vh.title.setText(curSong.getTitle());
            vh.artist.setText(curSong.getArtist());
            vh.id.setText(Long.toString(curSong.getId()));
        }else{
            vh.title.setText(R.string.not_available);
            vh.artist.setText(R.string.not_available);
            vh.id.setText(R.string.not_available);
        }
    }

    public void setSongList(List<Song> newSongList){
        this.songList = newSongList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return songList != null ? songList.size() : 0;
    }
}
