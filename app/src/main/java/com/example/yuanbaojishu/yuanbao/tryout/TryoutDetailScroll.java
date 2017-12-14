package com.example.yuanbaojishu.yuanbao.tryout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by yuanbaojishu on 2017/11/21.
 */

public class TryoutDetailScroll extends ScrollView {
    private static final String TAG = TryoutDetailScroll.class.getName();

    private ScrollListener mScrollListener;

    public void setScrollListener(ScrollListener scrollListener) {

    }

    public TryoutDetailScroll(Context context) {
        super(context);
    }
    public TryoutDetailScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 滑动查看详情
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if(mScrollListener != null) {
                    int contentHeight=getChildAt(0).getHeight();
                    int scrollHeight=getHeight();
                    Log.d(TAG,"scrollY:"+getScrollY()+"contentHeight:"+contentHeight+" scrollHeight"+scrollHeight+"object:"+this);

                    int scrollY = getScrollY();
                    mScrollListener.onScroll(scrollY);

                    if(scrollY+scrollHeight>=contentHeight||contentHeight<=scrollHeight) {
                        mScrollListener.onScrollToBottom();
                    } else {
                        mScrollListener.notBottom();
                    }

                    if(scrollY==0){
                        mScrollListener.onScrollToTop();
                    }
                }
                break;
        }
        boolean result=super.onTouchEvent(event);
        requestDisallowInterceptTouchEvent(false);

        return result;
    }

    public interface ScrollListener{
        void onScrollToBottom();
        void onScrollToTop();
        void onScroll(int scrollY);
        void notBottom();
    }
}
