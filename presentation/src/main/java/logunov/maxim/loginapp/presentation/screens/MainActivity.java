package logunov.maxim.loginapp.presentation.screens;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import logunov.maxim.loginapp.R;
import logunov.maxim.loginapp.databinding.ActivityMainBinding;
import logunov.maxim.loginapp.presentation.base.BaseMvvmActivity;

public class MainActivity extends BaseMvvmActivity<
        MainActivityViewModel,
        ActivityMainBinding,
        MainActivityRouter> {
    @Override
    protected MainActivityViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainActivityRouter provideRouter() {
        return new MainActivityRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.showPasswordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
    }
}
