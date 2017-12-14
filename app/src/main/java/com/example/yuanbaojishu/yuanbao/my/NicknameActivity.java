package com.example.yuanbaojishu.yuanbao.my;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.yuanbaojishu.yuanbao.R;

public class NicknameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_nickname);
    }

    // 保存用户的信息
    public static void saveLoginInfo(Context context, String username, String password){
        // 获取SharedPreferences对象
        SharedPreferences sharedPre=context.getSharedPreferences("config", context.MODE_PRIVATE);
        // 获取Editor对象
        SharedPreferences.Editor editor=sharedPre.edit();
        // 设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        // 提交
        editor.commit();
    }
}
