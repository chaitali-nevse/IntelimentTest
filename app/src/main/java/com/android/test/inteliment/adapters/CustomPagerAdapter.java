package com.android.test.inteliment.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.test.inteliment.R;
import com.android.test.inteliment.constants.Numerics;
import com.android.test.inteliment.helper.Toaster;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;

    private TypedArray bgImages = null;
    public CustomPagerAdapter(Context mContext) {
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // getting images from mipmap using array
        bgImages = mContext.getResources().obtainTypedArray(R.array.pager_images);

    }

    @Override
    public int getCount() {
        return Numerics.FOUR;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ViewHolder holder = new ViewHolder();
        View itemView = inflater.inflate(R.layout.pager_item, container,
                false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicked(position);
            }
        });
        holder.pagerImg = (ImageView) itemView.findViewById(R.id.image);
        setItem(position, holder);
        container.addView(itemView);
        return itemView;
    }

    private void setItem(int position, ViewHolder holder) {
        holder.pagerImg.setImageResource(bgImages.getResourceId(position, -1));
    }

    /**
     * handle click on items
     * display toast with clicked item number
     *
     * @param position
     */
    private void itemClicked(int position) {
        Toaster.showToast(" " + (position + 1));
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    /**
     * Holder to hold views
     * using view holder pattern
     */
    private class ViewHolder {
        private ImageView pagerImg;
    }
}
