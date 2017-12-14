package com.example.yuanbaojishu.yuanbao.tryout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuanbaojishu.yuanbao.R;

/**
 * 适配器
 * Created by yuanbaojishu on 2017/11/21.
 */

public class tryoutRecyclerViewAdapter extends
        RecyclerView.Adapter<tryoutRecyclerViewAdapter.BaseViewHolder> {

    private int type = 0;
    private String[] strDescs;
    private String[] strItems;
    private Context mContext;
    private LayoutInflater inflater;

    public tryoutRecyclerViewAdapter(Context context, String[] strItems, String[] strDescs) {
        this.mContext = context;
        this.strItems = strItems;
        this.strDescs = strDescs;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView;
        if(viewType == 0) {
            rootView = inflater.inflate(R.layout.tryout_layout_linear, null, null);
            LinearViewHolder linearViewHolder = new LinearViewHolder(rootView);
            return linearViewHolder;
        } else {
            rootView = inflater.inflate(R.layout.tryout_layout_grid, null, null);
            GridViewHolder gridViewHolder = new GridViewHolder(rootView);
            return gridViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(tryoutRecyclerViewAdapter.BaseViewHolder holder, int position) {
        if(type == 0) {
            LinearViewHolder linearViewHolder = (LinearViewHolder)holder;
            linearViewHolder.tvName.setText(strItems[position]);
            linearViewHolder.tvDesc.setText(strDescs[position]);
        } else {
            GridViewHolder gridViewHolder = (GridViewHolder)holder;
            gridViewHolder.tvName.setText(strItems[position]);
        }
    }

    @Override
    public int getItemCount() {
        return strItems.length;
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 网格视图以及列表视图
     */
    public static class GridViewHolder extends BaseViewHolder {
        private TextView tvName;

        public GridViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tryout_tv_name);
        }
    }
    public static class LinearViewHolder extends BaseViewHolder {
        private TextView tvName;
        private TextView tvDesc;

        public LinearViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tryout_tv_name);
            tvDesc = (TextView) itemView.findViewById(R.id.tryout_tv_desc);
        }
    }
}
