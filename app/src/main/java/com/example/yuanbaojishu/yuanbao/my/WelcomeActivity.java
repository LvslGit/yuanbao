package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.yuanbaojishu.yuanbao.MainActivity;
import com.example.yuanbaojishu.yuanbao.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎界面
 */

public class WelcomeActivity extends AppCompatActivity {

    private ImageView imageWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imageWelcome = (ImageView)findViewById(R.id.image_welcome);
        // 定时跳转到主界面
        final Intent intent = new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(task, 1000 * 3);// 三秒
    }
}
