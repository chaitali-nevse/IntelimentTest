package com.android.test.inteliment.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.test.inteliment.R;
import com.android.test.inteliment.activities.App;
import com.android.test.inteliment.constants.Numerics;
import com.android.test.inteliment.helper.NetworkUtil;
import com.android.test.inteliment.helper.SnackBar;
import com.android.test.inteliment.model.TouristPlace;
import com.android.test.inteliment.services.Services;
import com.android.test.inteliment.services.rest.RestClient;

import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class ScenarioTwoFragment extends Fragment {

    private ACProgressFlower progressDialog;

    private MaterialSpinner spinner;

    private View rootView;

    private Button navigateBtn;

    private TextView modeOfTransportTxt;

    /**
     * Dismisses dialog if it is already showing
     */
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Show dialog if not showing already
     */
    public void showDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // already progress bar object is created
        } else {
            progressDialog = new ACProgressFlower.Builder(getActivity())
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .bgColor(android.R.color.black)
                    .bgAlpha(Numerics.FLOAT_ONE)
                    .themeColor(Color.WHITE)
                    .petalCount(Numerics.THIRTY)
                    .petalThickness(Numerics.THREE)
                    .text(App.get().getString(R.string.loading))
                    .fadeColor(Color.DKGRAY).build();
            progressDialog.show();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (rootView != null) {
                callService();
                clearData();
            }
        } else {
            dismissDialog();
        }
    }

    private void clearData() {
        if (modeOfTransportTxt != null) {
            modeOfTransportTxt.setText("");
        }
        if (navigateBtn != null) {
            navigateBtn.setEnabled(false);
        }
    }

    private void callService() {
        if (NetworkUtil.isOnline(getActivity())) {
            Services services = RestClient.getInstance().getService();
            showDialog();
            services.getTouristPlaces(new Callback<List<TouristPlace>>() {
                                          @Override
                                          public void success(List<TouristPlace> touristPlaces, Response response) {
                                              setDataToSpinner(touristPlaces);
                                              dismissDialog();
                                          }

                                          @Override
                                          public void failure(RetrofitError error) {
                                              dismissDialog();
                                              SnackBar.showSnackBar(spinner, R.string.unable_to_fetch).show();
                                          }
                                      }

            );
        } else {
            SnackBar.showSnackBar(spinner, R.string.no_internet).show();
        }
    }

    private void setDataToSpinner(final List<TouristPlace> touristPlaces) {
        ArrayAdapter<TouristPlace> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, touristPlaces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    setDataToUI(touristPlaces.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setDataToUI(final TouristPlace place) {
        String result = "";
        if (!TextUtils.isEmpty(place.getFromCentral().getCar())) {
            result += getActivity().getString(R.string.car) + " - " + place.getFromCentral().getCar();
        }
        if (!TextUtils.isEmpty(place.getFromCentral().getTrain())) {
            result += "\n" + getActivity().getString(R.string.train) + " - " + place.getFromCentral().getTrain();
        }
        if (!TextUtils.isEmpty(result)) {
            modeOfTransportTxt.setText(result);
        }

        navigateBtn.setEnabled(true);
        navigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:" + place.getLocation().getLatitude() + "," + place.getLocation().getLongitude() + "?z=" + Numerics.SIXTEEN);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scenario_two, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = (MaterialSpinner) view.findViewById(R.id.spinner);
        navigateBtn = (Button) view.findViewById(R.id.btn_navigate);
        navigateBtn.setEnabled(false);
        modeOfTransportTxt = (TextView) view.findViewById(R.id.mode_of_transport);
        rootView = view;
    }
}
