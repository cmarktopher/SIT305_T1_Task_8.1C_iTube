package com.application.itube.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.itube.DataModels.Playlist;
import com.application.itube.R;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistItemCard> {

    private final Context context;
    private List<Playlist> playlists = new ArrayList<>();


    public PlaylistAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PlaylistItemCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlist_item_card, parent, false);

        return new PlaylistItemCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistItemCard holder, int position) {

        Playlist playlist = playlists.get(position);
        holder.getUrlTextView().setText(playlist.youTubeUrl);
    }

    @Override
    public int getItemCount() {

        return playlists.size();
    }

    /**
     * Will be called by the owning fragment to update playlists.
     * @param newPlaylists
     */
    public void updatePlaylist(List<Playlist> newPlaylists){
        playlists = newPlaylists;
        notifyDataSetChanged();
    }
}
