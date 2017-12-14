package com.example.yuanbaojishu.yuanbao.tryout;

import com.example.yuanbaojishu.yuanbao.R;
import com.example.yuanbaojishu.yuanbao.tryout.detailLib.GradationScrollView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 试用商品详情页面
 * @author lvsonglin
 */

public class TryoutDetailActivity extends AppCompatActivity
         {
//    implements GradationScrollView.ScrollViewListener
    @Bind();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tryout_detail);

    }
}
