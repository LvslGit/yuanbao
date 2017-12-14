package com.example.yuanbaojishu.yuanbao.prise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuanbaojishu.yuanbao.R;

/**
 * 抽奖界面
 */

public class PriseActivity extends AppCompatActivity
        implements LuckPanLayout.AnimationEndListener {

    private RotatePan rotatePan;
    private LuckPanLayout luckPanLayout;
    private ImageView goBtn;
    private ImageView yunIv;
    private String[] strs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_prise);
    }

    public void rotation(View view){
        luckPanLayout.rotate(-1,100);
    }

    @Override
    public void endAnimation(int position) {
        Toast.makeText(this,"Position = "+position+","+strs[position],Toast.LENGTH_SHORT).show();
    }
}
