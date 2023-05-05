package com.application.itube.RecyclerView;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.application.itube.R;
import com.google.android.material.textview.MaterialTextView;

public class PlaylistItemCard extends RecyclerView.ViewHolder{

    private final MaterialTextView urlTextView;
    public PlaylistItemCard(@NonNull View itemView) {

        super(itemView);

        urlTextView = itemView.findViewById(R.id.urlTextView);
    }

    public MaterialTextView getUrlTextView() {
        return urlTextView;
    }
}
