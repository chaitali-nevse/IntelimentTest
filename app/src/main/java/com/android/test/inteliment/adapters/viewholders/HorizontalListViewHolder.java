package com.android.test.inteliment.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.android.test.inteliment.R;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class HorizontalListViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;

    private View rootView;

    public HorizontalListViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        image = (ImageView) rootView.findViewById(R.id.item_image);
    }

    public View getRootView() {
        return rootView;
    }

    public ImageView getImage() {
        return image;
    }

}
