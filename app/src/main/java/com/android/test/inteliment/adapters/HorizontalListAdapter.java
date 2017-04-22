package com.android.test.inteliment.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.test.inteliment.R;
import com.android.test.inteliment.adapters.viewholders.HorizontalListViewHolder;
import com.android.test.inteliment.constants.Numerics;
import com.android.test.inteliment.fragments.ScenarioOneFragment;



/**
 * Created by Chaitali on 12/04/2017.
 */

public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListViewHolder> {

    private Context context;

    private Fragment fragment;

    private TypedArray bgImages = null;

    public HorizontalListAdapter(Context context, Fragment scenarioOneFragment) {
        this.context = context;
        this.fragment = scenarioOneFragment;
        bgImages = context.getResources().obtainTypedArray(R.array.bg_images);
    }

    @Override
    public HorizontalListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotizontal_list, parent, false);

        showThreeItemsPerScreen(view);
        return new HorizontalListViewHolder(view);
    }

    /**
     * Fit the list to display
     * such that only 3 items are visible
     * at any time
     * @param view
     */
    private void showThreeItemsPerScreen(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        Display display = ((AppCompatActivity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        lp.width = screenWidth / Numerics.THREE;
        view.setLayoutParams(lp);
    }

    @Override
    public void onBindViewHolder(final HorizontalListViewHolder holder, final int position) {

        holder.getImage().setImageResource(bgImages.getResourceId(position, -1));
        // getting name of the image mipmap from resource id
        String name = context.getResources().getResourceEntryName(bgImages.getResourceId(position, -1));
        // converting the name to Camelcase
        holder.getImage().setTag(name.substring(0, 1).toUpperCase() + name.substring(1));

        holder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ScenarioOneFragment)fragment).showTextOnTarget(holder.getImage().getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Numerics.FIVE;
    }
}
