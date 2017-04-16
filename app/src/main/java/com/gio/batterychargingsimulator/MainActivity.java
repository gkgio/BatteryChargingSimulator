package com.gio.batterychargingsimulator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private View viewBattery;

    // Аниматор фона
    private ValueAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_page_title);

        viewBattery = findViewById(R.id.viewBattery);

        // Создаём аниматор
        mAnimator = ValueAnimator.ofInt(0, 10000);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setDuration(3000);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Integer value = (Integer) valueAnimator.getAnimatedValue();
                viewBattery.getBackground().setLevel(value);
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mAnimator.cancel();
        mAnimator.removeAllUpdateListeners();
        mAnimator = null;
    }
}


