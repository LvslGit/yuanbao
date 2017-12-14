package com.example.yuanbaojishu.yuanbao.my;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanbaojishu.yuanbao.R;

public class MyActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private TextView nickname;
    private ImageView photo;
    private ImageButton checkBtn;// 签到
    private Button moneyBtn, setBtn, moreorderBtn;
    private Button daizhongjiangBtn, jixuBtn, yizhongjiangBtn, daijiangliBtn, yiwanchengBtn;
    private Button myChoujiang, myShouyi, kefu, myYaoqing;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        init();
    }

    private void init() {
        nickname = (TextView) findViewById(R.id.nicknameSet_set);// 昵称
        photo = (ImageView)findViewById(R.id.photo);// 头像
        setNickPhoto();
        checkBtn = (ImageButton) findViewById(R.id.checkBtn);

        moneyBtn = (Button) findViewById(R.id.moneyBtn);
        setBtn = (Button) findViewById(R.id.setBtn);
        moreorderBtn = (Button) findViewById(R.id.more_order_btn);

        daizhongjiangBtn = (Button) findViewById(R.id.daizhongjiangBtn);
        jixuBtn = (Button) findViewById(R.id.jixuBtn);
        yizhongjiangBtn = (Button) findViewById(R.id.yizhongjiangBtn);
        daijiangliBtn = (Button) findViewById(R.id.daijiangliBtn);
        yiwanchengBtn = (Button) findViewById(R.id.yiwanchengBtn);

        myChoujiang = (Button) findViewById(R.id.mychoujiangBtn);
        myShouyi = (Button) findViewById(R.id.myshouyiBtn);
        kefu = (Button) findViewById(R.id.kefuBtn);
        myYaoqing = (Button) findViewById(R.id.yaoqingBtn);
    }

    // 各项点击事件
    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                // 根据不同的id设置好不同的点击事件
                case R.id.checkBtn:
                    // 签到,更改背景图,并且记录已签到事件
                    setCheckBtn();
                    break;
                case R.id.moneyBtn:
                    // 红包
                    break;
                case R.id.setBtn:
                    // 设置
                    intent.setClass(MyActivity.this, SetActivity.class);
                    startActivity(intent);
                    break;
                case R.id.more_order_btn:
                    // 跳转到更多订单界面
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.daizhongjiangBtn:
                    // 跳到待中奖界面
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.jixuBtn:
                    // 跳到继续试用界面
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.yizhongjiangBtn:
                    // 跳到已中奖页面
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.daijiangliBtn:
                    // 跳到待奖励界面
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.yiwanchengBtn:
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.mychoujiangBtn:
                    // 跳到我的抽奖界面
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.myshouyiBtn:
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.kefuBtn:
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
                case R.id.yaoqingBtn:
//                    intent.setClass(MyActivity.this, XXX.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    // 设置昵称
    private void setNickPhoto() {
        // 昵称设置为本机手机号
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: 设置昵称为本机号码
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String name = tm.getLine1Number();// 获取本机号码
        nickname.setText(name);
        // 设置头像

    }

    // TODO 签到事件的处理,等待后台搭建完善才能够完成
    // 签到事件
    private void setCheckBtn() {
        // 第一步判断今日是否已签到,如果没有签到进行下一步逻辑,以前到的话,btn关闭点击
        boolean first = true;
        if(!first) {
            checkBtn.setEnabled(false);
        } else {
            //        checkBtn.setImageDrawable(R.drawable.已签到);
            // 跳转到签到页面
            intent.setClass(this, CheckInActivity.class);
        }
    }
}
