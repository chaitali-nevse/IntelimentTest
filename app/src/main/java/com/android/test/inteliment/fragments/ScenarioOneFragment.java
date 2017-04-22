package com.android.test.inteliment.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.test.inteliment.R;
import com.android.test.inteliment.adapters.CustomPagerAdapter;
import com.android.test.inteliment.adapters.HorizontalListAdapter;
import com.android.test.inteliment.helper.CirclePageIndicator;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class ScenarioOneFragment extends Fragment implements View.OnClickListener {

    private TextView targetTxt;

    private LinearLayout btnLL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scenario_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        targetTxt = (TextView) view.findViewById(R.id.txt_view);
        btnLL = (LinearLayout) view.findViewById(R.id.btn_ll);
        initHorizontalList(view);
        initPager(view);
        initButtons(view);
    }

    private void initButtons(View view) {
        view.findViewById(R.id.btn_red).setOnClickListener(this);
        view.findViewById(R.id.btn_green).setOnClickListener(this);
        view.findViewById(R.id.btn_blue).setOnClickListener(this);
    }

    private void initPager(View view) {
        // get size of circular view pager indicator based on screen resolution
        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.viewpager_indicator, outValue, true);
        float value = outValue.getFloat();

        ViewPager pager = (ViewPager) view.findViewById(R.id.pager_images);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getActivity());
        pager.setAdapter(pagerAdapter);
        CirclePageIndicator pagerIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator_pager);
        pagerIndicator.setRadius(value);
        pagerIndicator.setViewPager(pager);
    }

    private void initHorizontalList(View view) {
        RecyclerView horizontalList = (RecyclerView) view.findViewById(R.id.recycler_view);

        // horizontal layout manager to make a recycler view horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalList.setLayoutManager(layoutManager);

        HorizontalListAdapter adapter = new HorizontalListAdapter(getActivity(), ScenarioOneFragment.this);
        horizontalList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int color = -1;
        switch (v.getId()) {
            case R.id.btn_blue:
                color = getResources().getColor(R.color.blue);
                break;
            case R.id.btn_green:
                color = getResources().getColor(R.color.green);
                break;
            case R.id.btn_red:
                color = getResources().getColor(R.color.red);
                break;
            default:
                break;
        }
        if (btnLL != null) {
            btnLL.setBackgroundColor(color);
        }
    }

    public void showTextOnTarget(Object title) {
        if(targetTxt != null) {
            targetTxt.setText(title.toString());
        }
    }
}
