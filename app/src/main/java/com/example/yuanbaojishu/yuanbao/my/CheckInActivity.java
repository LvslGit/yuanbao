package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.yuanbaojishu.yuanbao.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckInActivity extends AppCompatActivity {

    private ImageView imageView = null;
    private Button backBtn = null;

    private SignCalendar calendar;
    private String date;
    private int years;
    private String months;
    private Button signBtn;// 签到按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_check_in);

        init();
    }

    private void init() {
        imageView = (ImageView)findViewById(R.id.adImageview);
        backBtn = (Button)findViewById(R.id.backBtn_check);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CheckInActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });
        signBtn = (Button)findViewById(R.id.signBtn_check);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSignBtn();
            }
        });

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 日期显示格式
        Date curDate = new Date(System.currentTimeMillis());// 当前时间
        date = format.format(curDate);
        calendar = (SignCalendar)findViewById(R.id.sc_main);// 日历控件
    }

    // TODO 签到事件,添加到后台数据库中
    private void setSignBtn() {
        List<String> list = new ArrayList<String>();
        list.add("2017-12-31");
        list.add(date);
        calendar.addMarks(list, 0);
    }
}
