package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yuanbaojishu.yuanbao.R;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private Button backBtn, registerBtn, loginBtn, forgetBtn;
    private ImageButton wechatBtn;
    private AutoCompleteTextView phoneNum;
    private AutoCompleteTextView pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        backBtn = (Button)findViewById(R.id.backBtn_login);
        registerBtn = (Button)findViewById(R.id.registerBtn_login);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        forgetBtn = (Button)findViewById(R.id.forgetBtn);
        phoneNum = (AutoCompleteTextView)findViewById(R.id.phoneNum);
        pwd = (AutoCompleteTextView)findViewById(R.id.pwd);

        wechatBtn = (ImageButton)findViewById(R.id.wechatBtn);
    }

    // 基本点击事件
    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            int id = view.getId();
            switch (id) {
                case R.id.backBtn :
                    intent.setClass(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.registerBtn :
                    intent.setClass(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.loginBtn :
                    Log.i(TAG, "onClick: 登录");
                    loginNext();
                    break;
                case R.id.forgetBtn :
                    Log.i(TAG, "onClick: 忘记密码");
                    forgetNext();
                case R.id.wechatBtn :
                    Log.i(TAG, "onClick: 微信登录接口");
                    wechatLogin();
                    break;
            }
        }
    }

    // 登录事件逻辑
    private void loginNext() {

    }

    // 忘记密码事件逻辑
    private void forgetNext() {

    }

    // 微信登录接口
    private void wechatLogin() {
        IWXAPI mApi = WXAPIFactory.createWXAPI(this, WXEntryActivity.WEIXIN_APP_ID, true);
        mApi.registerApp(WXEntryActivity.WEIXIN_APP_ID);

        if (mApi != null && mApi.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test_neng";
            mApi.sendReq(req);
        } else
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
    }

}
