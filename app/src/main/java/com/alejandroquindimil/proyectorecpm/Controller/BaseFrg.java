package com.alejandroquindimil.proyectorecpm.Controller;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.greenrobot.eventbus.EventBus;

public class BaseFrg  extends Fragment {
    public void showError(String msg){
        ToastController.init(getView()).showError(msg);
    }

    public void showInfo(String msg){
        ToastController.init(getView()).showInfo(msg);
    }

    public void showLoading(boolean show){
        LoadingEvent event = new LoadingEvent(show);
        EventBus.getDefault().post(event);
    }

    public void hideKeyb(){
        FragmentActivity act = getActivity();
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = act.getCurrentFocus();
        if (view == null) {
            view = new View(act);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
