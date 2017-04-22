package com.android.test.inteliment.helper;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;
import com.android.test.inteliment.R;

import com.android.test.inteliment.activities.App;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class Toaster{
    private static Toast toast;

        /**
         * Default constructor
         */
        private Toaster() {
            // Private constructor to avoid public
        }

        public static Toast showNetworkFailToast() {
            return showToast(R.string.network_not_available);
        }

        public static Toast showToast(int text) {
            if (toast == null) {
                toast = Toast.makeText(App.get(), text, Toast.LENGTH_LONG);
            } else {
                toast.setText(text);
            }
            if (!AppRunningState.isApplicationBroughtToBackgrounds(App.get())) {
                toast.show();
            }
            return toast;
        }

        public static Toast showToast(String text) {
            if (toast == null) {
                toast = Toast.makeText(App.get(), text, Toast.LENGTH_LONG);
            } else {
                toast.setText(text);
            }
            if (!AppRunningState.isApplicationBroughtToBackgrounds(App.get())) {
                toast.show();
            }
            return toast;
        }

        @SuppressLint("ShowToast")
        public static boolean isToastShowing() {
            if (toast == null) {
                toast = Toast.makeText(App.get(), App.get().getResources().getString(R.string.app_name),
                        Toast.LENGTH_LONG);
            }
            return toast.getView().getWindowVisibility() == View.VISIBLE ? true : false;
        }

        public static void cancelToast() {
            if (toast == null) {
                toast = Toast.makeText(App.get(), App.get().getResources().getString(R.string.app_name),
                        Toast.LENGTH_LONG);
            }
            toast.cancel();
        }
}
