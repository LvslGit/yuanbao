package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.yuanbaojishu.yuanbao.R;

/**
 * 重置新密码
 */
public class ChangePwdActivity extends AppCompatActivity {

    private static final String TAG = "ChangePwdActivity";
    private Button backBtn, confirmBtn;
    private AutoCompleteTextView pwdSt, pwdOnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
    }

    private void init() {
        backBtn = (Button)findViewById(R.id.backBtn_pwd);
        confirmBtn = (Button)findViewById(R.id.confirmBtn);
        pwdSt = (AutoCompleteTextView)findViewById(R.id.pwdSt);
        pwdOnd = (AutoCompleteTextView)findViewById(R.id.pwdOnd);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 返回上一层
                Intent intent = new Intent();
                intent.setClass(ChangePwdActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmNext();
            }
        });
    }

    private void confirmNext() {
        String pwd1 = pwdSt.getText().toString();
        String pwd2 = pwdOnd.getText().toString();
        if (pwd1 == pwd2) {
            // 两次密码一致
        } else {
            // 两次密码不同
            Toast.makeText(this, "两次密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
        }
    }
}
