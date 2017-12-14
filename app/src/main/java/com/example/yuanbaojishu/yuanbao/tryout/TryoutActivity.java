package com.example.yuanbaojishu.yuanbao.tryout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.yuanbaojishu.yuanbao.R;

import java.util.List;

/**
 * @author lvsonglin
 * 试用模块
 */
public class TryoutActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "试用";
    // 商品搜索相关
    private Button tryoutBackBtn = null;
    private SearchView tryoutSearch = null;
    // 排序按钮
    private Button tryoutNew = null;
    private Button tryoutValue = null;
    private Button tryoutRenqi = null;

//    private HomeAdapter tryoutAdapter = null;
    private List<String> tryoutData = null;

    // 商品列表相关
    private RecyclerView tryoutRecycler = null;
    private ImageButton btnChange = null;// GridView以及ListView的切换
    //切换标示,默认显示线性布局
    private boolean isLinearLayout = true;
    private tryoutRecyclerViewAdapter recyclerViewAdapter;
    private int[] images;
    private String[] strDescs;
    private String[] strItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tryout);
        initView();
    }

    // 界面的初始化方法
    private void initView() {
        tryoutBackBtn = (Button)findViewById(R.id.tryoutBackBtn);
        tryoutSearch = (SearchView)findViewById(R.id.tryoutSearch);
        // recyclerView设置相关
        tryoutRecycler = (RecyclerView)findViewById(R.id.tryout_recyclerView);
//        btnChange
        tryoutRecycler.setItemAnimator(new DefaultItemAnimator());
//        tryoutRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
    // 内容填充方法
    private void initData() {
        // 先填充8个网格数据
        strItems  = getResources().getStringArray(R.array.str_item);
        strDescs = getResources().getStringArray(R.array.str_desc);
        images = new int[]{
                R.drawable.ic_photo,
                R.drawable.ic_photo,
                R.drawable.ic_photo,
                R.drawable.ic_photo,
                R.drawable.ic_photo,
                R.drawable.ic_photo,
                R.drawable.ic_photo
        };

        recyclerViewAdapter = new tryoutRecyclerViewAdapter(this, strItems, strDescs);// 填充
        tryoutRecycler.setLayoutManager(new LinearLayoutManager(this));
        tryoutRecycler.setAdapter(recyclerViewAdapter);
        startAnimation(R.anim.scale);// 开启动画
    }
    // 设置切换的相关方法
    private void setListener() {
        btnChange.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tryoutAll:// 稍后设置,显示全部商品时,候选显示
                //切换
                if(isLinearLayout) {
                    //切换成网格布局
                    recyclerViewAdapter.setType(1);
                    tryoutRecycler.setLayoutManager(new GridLayoutManager(this, 2));
                    recyclerViewAdapter.notifyDataSetChanged();
                    startAnimation(R.anim.zoom_in);
                    isLinearLayout = false;
                } else {
                    //切换成垂直线性布局
                    recyclerViewAdapter.setType(0);
                    tryoutRecycler.setLayoutManager(new LinearLayoutManager(this));
                    recyclerViewAdapter.notifyDataSetChanged();
                    startAnimation(R.anim.zoom_in);
                    isLinearLayout = true;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 开启动画
     */
    private void startAnimation(int anim) {
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(this,anim));
        lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
        tryoutRecycler.setLayoutAnimation(lac);
        tryoutRecycler.startLayoutAnimation();
    }

//    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
//            @Override
//            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                MyViewHolder
//            }
//    }

    /**
     * 开启动画效果
     * @param anim
     */
//    private void startAnimation(int anim) {
////        LayoutAnimationController animationController = new
////                LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim));
//        // 动画执行顺序
//        animationController.setOrder(LayoutAnimationController.ORDER_RANDOM);
//        tryoutRecycler.setLayoutAnimation(animationController);
//        tryoutRecycler.startLayoutAnimation();// 开启动画
//    }

    /**
     * 搜索框相关设置
     */
    private void setTryoutSearch() {
        // 设置文本监听
        tryoutSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            // 文本内容改变时触发
            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){// 搜索框不为空
                    Log.i(TAG, "onQueryTextChange: 搜索框输入触发");
                }
                return false;
            }
        });
    }
}
