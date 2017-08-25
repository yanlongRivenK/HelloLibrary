package com.yanlongrivenk.nothing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yanlongrivenk.simpleprogressview.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    protected CircleProgressView mCir;
    protected Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCir = ((CircleProgressView) findViewById(R.id.cir));
        mCir.setWidth(DensityUtil.dip2px(this, 200));
        mCir.setHeight(DensityUtil.dip2px(this, 200));
        mCir.setCircleWidth(DensityUtil.dip2px(this, 5));


        bt = ((Button) findViewById(R.id.bt));
    }

    private void testProgress() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 101; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d("MainActivity", "异常");
                    }
                    final float percent = (float) (i * 100 / 100)/100;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCir.drawProgress(percent);
                            if (100 == mCir.getProgress()) bt.setClickable(true);
                        }
                    });

                }
            }
        }).start();



    }

    public void testProgress(View v){

        bt.setClickable(false);
        testProgress();
    }
}
