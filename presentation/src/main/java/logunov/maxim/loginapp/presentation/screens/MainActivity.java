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
import android.widget.ScrollView;

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
        init();
    }

    private void init() {
        initOnTouchListener();
        binding.container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                binding.container.getWindowVisibleDisplayFrame(r);
                int screenHeight = binding.container.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    binding.logoIv.setVisibility(View.GONE);
                } else {
                    binding.logoIv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initOnTouchListener() {
        binding.showPasswordBtn.setOnTouchListener(new View.OnTouchListener() {
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
        });
    }
}
