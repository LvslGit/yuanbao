package com.example.yuanbaojishu.yuanbao.tryout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.yuanbaojishu.yuanbao.R;

/**
 * 试用申请页面
 */

public class ApplicationActivity extends AppCompatActivity {

    private static final String TAG = "ApplicationActivity";

    // imageBtn核对淘口令和店铺
    private ImageButton imageButtonTKL = null;// 淘口令
    private ImageButton imageButtonDP = null;// 店铺
    // 不同的验证显示不同的布局
    private LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_application);

        init();
    }

    // 各项控件及布局设置的初始化
    private void init() {
        imageButtonTKL = (ImageButton)findViewById(R.id.imageButton_taokouling);
        imageButtonDP =  (ImageButton)findViewById(R.id.imageButton_dianpu);

        lp = new LinearLayout.LayoutParams(150, LinearLayout.LayoutParams.FILL_PARENT);// 切换布局
        lp.setMargins(-150, 0, 0, 0);
        ((LinearLayout)findViewById(R.id.left_linear_image)).setLayoutParams(lp);

        // imageBtn的点击切换图片
        imageButtonTKL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // 设置按下时的按钮的背景图片切换
//                    ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.android_b);
                    // 设置点击时的按钮下方的布局切换
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    );// 设置布局的大小
                    lp.setMargins(0,0,0,0);
                    ((LinearLayout)findViewById(R.id.left_linear_image)).setLayoutParams(lp);
                    Log.i(TAG, "onTouch: 此处为左侧切换淘口令布局");
                    
                } else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // 抬起时恢复为正常图片
//                     ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.android_b);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    );
                    lp.setMargins(0,0,0,0);
                    ((LinearLayout)findViewById(R.id.right_linear_image)).setLayoutParams(lp);
                    Log.i(TAG, "onTouch: 此处为切换淘宝店铺布局");
                }

                return false;
            }
        });
        imageButtonDP.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.ic_change));
                    // 布局切换

                } else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    ((ImageButton)view).setImageDrawable(getResources().getDrawable(R.drawable.ic_change));

                }
                return false;
            }
        });

    }


}
