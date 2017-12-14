package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.yuanbaojishu.yuanbao.R;

/**
 * 忘记密码界面
 */

public class ForgetActivity extends AppCompatActivity {

    private static final String TAG = "ForgetActivity";
    private Button backBtn, getBtn, nextBtn;
    private AutoCompleteTextView phone, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forget);
    }

    private void init() {
        backBtn = (Button)findViewById(R.id.backBtn_forget);
        getBtn = (Button)findViewById(R.id.getBtn_forget);
        nextBtn = (Button)findViewById(R.id.next_forget);
        phone = (AutoCompleteTextView)findViewById(R.id.phone_forget);
        message = (AutoCompleteTextView)findViewById(R.id.message_forget);
    }

    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.backBtn_forget:

                    break;
                case R.id.getBtn_forget:
                    // 获取短信验证码
                    getMessage();
                    break;
                case R.id.next_forget:
                    // 短信验证码通过,重置新密码
                    next();
                    break;
            }
        }
    }

    // 获取短信验证码
    private void getMessage() {

    }

    // 短信验证码通过,去下一界面重置新密码
    private void next() {
        Intent intent = new Intent();
        intent.setClass(this, ChangePwdActivity.class);
        startActivity(intent);
    }
}
