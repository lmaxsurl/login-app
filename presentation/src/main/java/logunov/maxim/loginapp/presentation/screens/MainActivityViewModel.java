package logunov.maxim.loginapp.presentation.screens;

import android.databinding.ObservableField;

import logunov.maxim.loginapp.app.App;
import logunov.maxim.loginapp.presentation.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<MainActivityRouter> {


    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");


    @Override
    protected void runInject() {
        App.getAppComponent().runInject(this);
    }
}
