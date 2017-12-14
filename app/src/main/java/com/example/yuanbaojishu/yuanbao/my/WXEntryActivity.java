package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.android.volley.toolbox.Volley;
//import com.example.yuanbaojishu.yuanbao.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信登录
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";
    private static final String APP_SECRET = "fa0d3636204bde9300f192800ee2f46e";
    private IWXAPI mWeixinAPI;
    public static final String WEIXIN_APP_ID = "wx7c1e8ab308f97330";
    private final static String PACKGE = "com.yuanbao";
    private static String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_wxentry);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);// 必须调用此句话
    }

    // 微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq baseReq) {
        Log.i(TAG, "onReq");
    }

    // 发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp baseResp) {
        Log.i(TAG, "onResp");
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.i(TAG, "ERR_OK");
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) baseResp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    getAccess_token(code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.i(TAG, "ERR_USER_CANCEL");//发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.i(TAG, "ERR_AUTH_DENIED");//发送被拒绝
                break;
            default:
                //发送返回
                break;
        }
    }

    /**
     * 获取openid accessToken值用于后期操作
     * @param code 请求码
     */
    private void getAccess_token(final String code) {
//        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
//                + WEIXIN_APP_ID
//                + "&secret="
//                + APP_SECRET
//                + "&code="
//                + code
//                + "&grant_type=authorization_code";
//        Log.i(TAG, "getAccess_token: " + path);
//
//        // 网络请求，根据自己的请求方式
//        VolleyRequest.get(this, path, "getAccess_token", false, null, new VolleyRequest.Callback() {
//            @Override
//            public void onSuccess(String result) {
//                Log.i(TAG, "getAccess_token_result: " + result);
//                JSONObject jsonObject = null;
//
//                try {
//                    jsonObject = new JSONObject(result);
//                    String openid = jsonObject.getString("openid").toString().trim();
//                    String access_token = jsonObject.getString("access_token").toString().trim();
//                    getUserMesg(access_token, openid);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                Log.i(TAG, "onError: ");
//            }
//        });
    }

    /**
     * 获取微信的个人信息
     * @param access_token
     * @param openid
     */
//    private void getUserMesg(final String access_token, final String openid) {
//        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
//                + access_token
//                + "&openid="
//                + openid;
//        Log.i(TAG, "getUserMesg: " + path);
//        //网络请求，根据自己的请求方式
////        VolleyRequest.get(this, path, "getAccess_token", false, null, new VolleyRequest.Callback() {
////            @Override
////            public void onSuccess(String result) {
////                Log.i(TAG, "getUserMesg_result: " + result);
////                JSONObject jsonObject = null;
////                try {
////                    jsonObject = new JSONObject(result);
////                    String nickname = jsonObject.getString("nickname");
////                    int sex = Integer.parseInt(jsonObject.get("sex").toString());
////                    String headimgurl = jsonObject.getString("headimgurl");
////
////                    Log.i(TAG,"用户基本信息:");
////                    Log.i(TAG, "nickname: " + nickname);
////                    Log.i(TAG, "sex: " + sex);
////                    Log.i(TAG, "headimgurl" + headimgurl);
//////                    LogUtils.log("nickname:" + nickname);
//////                    LogUtils.log("sex:" + sex);
//////                    LogUtils.log("headimgurl:" + headimgurl);
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////                finish();
////            }
////
////            @Override
////            public void onError(String errorMessage) {
////
////            }
////        });
//    }
//    }
    
}
