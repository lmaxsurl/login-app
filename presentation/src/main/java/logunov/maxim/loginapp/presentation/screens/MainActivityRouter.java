package logunov.maxim.loginapp.presentation.screens;

import android.widget.Toast;

import logunov.maxim.loginapp.presentation.base.BaseRouter;

public class MainActivityRouter extends BaseRouter<MainActivity> {

    public MainActivityRouter(MainActivity activity) {
        super(activity);
    }

    public void showErrorMessage(String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
