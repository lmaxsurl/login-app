package logunov.maxim.loginapp.presentation.screens;

import android.animation.LayoutTransition;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintSet;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;

import java.util.Arrays;
import java.util.List;

import logunov.maxim.loginapp.R;
import logunov.maxim.loginapp.databinding.ActivityMainBinding;
import logunov.maxim.loginapp.presentation.base.BaseMvvmActivity;

public class MainActivity extends BaseMvvmActivity<MainActivityViewModel, ActivityMainBinding,
        MainActivityRouter> implements ViewTreeObserver.OnGlobalLayoutListener, View.OnTouchListener {
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
        init();
    }

    private void init() {
        initOnTouchListener();
        initOnGlobalLayoutListener();
        initAdapter();
    }

    private void initAdapter() {
        binding.emailActv.setAdapter(router.getAdapter());
    }

    private void initOnGlobalLayoutListener() {
        binding.container.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private void initOnTouchListener() {
        binding.showPasswordBtn.setOnTouchListener(this);
    }

    @Override
    public void onGlobalLayout() {
        Rect r = new Rect();
        binding.container.getWindowVisibleDisplayFrame(r);
        int screenHeight = binding.container.getRootView().getHeight();
        int keypadHeight = screenHeight - r.bottom;
        if (keypadHeight > screenHeight * 0.15) {
            viewModel.logo.set(false);
        } else {
            viewModel.logo.set(true);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                binding.passwordEt
                        .setTransformationMethod(null);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                binding.passwordEt
                        .setTransformationMethod(new PasswordTransformationMethod());
                break;
        }
        binding.passwordEt
                .setSelection(binding.passwordEt.getText().length());
        return false;
    }
}
