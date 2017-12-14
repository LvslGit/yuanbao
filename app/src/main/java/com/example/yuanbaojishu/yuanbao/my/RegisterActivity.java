package com.example.yuanbaojishu.yuanbao.my;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.yuanbaojishu.yuanbao.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * 注册模块.
 * 使用Mob公司提供的SMSSDK来进行手机短信码验证
 */
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private Button backBtn = null;
    private Button loginBtn = null;
    private Intent intent = null;

    // 注册相关逻辑
    private Button getBtn = null;// 获取验证码
    private Button registerBtn = null;
    private AutoCompleteTextView phoneText;
    private AutoCompleteTextView messageText;
    private String phone;
    private String message;

    private EventHandler eventHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);


        // 注册短信验证码的SDK
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码
//        SMSSDK.setAskPermisionOnReadContact(boolShowInDialog);
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if(data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Log.i(TAG, "afterEvent: 验证成功");
                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);

        // 使用mob的自带GUI的操作
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int enent, int result, Object data) {
                // 解析注册结果
                if(result == SMSSDK.RESULT_COMPLETE) {
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>)data;
                    String country = (String)phoneMap.get("country");
                    String phone = (String)phoneMap.get("phone");

                    // 提交用户信息
//                    registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }


    private void init() {
        loginBtn = (Button)findViewById(R.id.registerBtn_login);
        backBtn = (Button)findViewById(R.id.backBtn_login);

        getBtn = (Button)findViewById(R.id.getBtn);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        // 跳转点击事件
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private static final int CODE_ING = 1;                  //已发送，倒计时
    private static final int CODE_REPEAT = 2;               //重新发送
    private static final int SMSDDK_HANDLER = 3;            //短信回调
    private int TIME = 60;//倒计时60s
    /**
     * 使用mob进行短信注册
     */
    // 初始化SDK
//    private void initSDK() {
//        SMSSDK.initSDK(this, "11f8e73cd1a1c", "ac3ad611934a934ed4b5121066637f6a");
//
//        eventHandler = new EventHandler() {
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//                Message msg = new Message();
//                msg.arg1 = event;
//                msg.arg2 = result;
//                msg.obj = data;
//                msg.what = SMSDDK_HANDLER;
//                handler.sendMessage(msg);
//            }
//        };
//        // 注册回调监听接口
//        SMSSDK.registerEventHandler(eventHandler);
//    }
//    // 监听函数
//    private class OnClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            userPhone = userPhoneText.getText().toString();
//            switch (v.getId()) {
//                case R.id.get_code_button://获取验证码
//                    new AlertDialog.Builder(RegisterActivity.this)
//                            .setTitle("发送短信")
//                            .setMessage("我们将把验证码发送到以下号码:\n"+"+86:"+userPhone)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    SMSSDK.getVerificationCode("86", userPhone);
//                                    getBtn.setClickable(false);
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            for (int i = 60; i > 0; i--) {
//                                                handler.sendEmptyMessage(CODE_ING);
//                                                if (i <= 0) {
//                                                    break;
//                                                } try {
//                                                    Thread.sleep(1000);
//                                                } catch (InterruptedException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                            handler.sendEmptyMessage(CODE_REPEAT);
//                                        }
//                                    }).start();
//                                }
//                            })
//                            .create()
//                            .show();
//                    break;
//
//                case R.id.registerBtn://注册
//                    SMSSDK.submitVerificationCode("86", userPhone, codeText.getText().toString());//对验证码进行验证->回调函数
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//    Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case CODE_ING://已发送,倒计时
//                    getBtn.setText("重新发送("+--TIME+"s)");
//                    break;
//                case CODE_REPEAT://重新发送
//                    getBtn.setText("获取验证码");
//                    getBtn.setClickable(true);
//                    break;
//                case SMSDDK_HANDLER:
//                    int event = msg.arg1;
//                    int result = msg.arg2;
//                    Object data = msg.obj;
//                    //回调完成
//                    if (result == SMSSDK.RESULT_COMPLETE) {
//                        //验证码验证成功
//                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                            Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_LONG).show();
//                            // 其他合法性的检测
//                            if (check()) {
//                                UserModel user  = new UserModel();
//                                user.setUserId(MyUUID.getUUID());              //id
//                                user.setUserPhone(userPhone);
//                                user.setUserPassword(MD5.md5(userPassword));   //md5加密
//                                user.setUserGender(gender);                    //性别
//                                user.setUserName(userName);
//                                user.setUserBirthday("19920401");                      //暂时为空
//                                //user.setUserIdCard(userIdCard);
//                                //user.setUserImage("");                         //暂时为空
//                                //注册->服务器
//                                UserController.userRegister(user, handler);
//                            }
//                        }
//                        //已发送验证码
//                        else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                            Toast.makeText(getApplicationContext(), "验证码已经发送",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            ((Throwable) data).printStackTrace();
//                        }
//                    }
//                    if(result==SMSSDK.RESULT_ERROR) {
//                        try {
//                            Throwable throwable = (Throwable) data;
//                            throwable.printStackTrace();
//                            JSONObject object = new JSONObject(throwable.getMessage());
//                            String des = object.optString("detail");//错误描述
//                            int status = object.optInt("status");//错误代码
//                            if (status > 0 && !TextUtils.isEmpty(des)) {
//                                Toast.makeText(getApplicationContext(), des, Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        } catch (Exception e) {
//                            //do something
//                        }
//                    }
//                    break;
//                case R.id.register_status:
//                    String result_code = msg.getData().getString("result").toString();
//                    if("1".equals(result_code))
//                    {
//                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//                        intent.putExtra("userPhone", userPhone);
//                        RegisterActivity.this.setResult(RESULE_CODE, intent);
//                        //startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_LONG).show();
//                    }
//                    break;
//                case R.id.check_phone_exist://手机号是否已存在
//                    String result_code_2 = msg.getData().getString("result").toString();
//                    if("1".equals(result_code_2)) {
//                        errPhoneText.setText("手机号码已经注册，请换用其他号码");
//                        resultMap.put("phone", false);
//                    } else {
//                        errPhoneText.setText("");
//                        resultMap.put("phone", true);
//                    }
//                    break;
//            }
//        }
//    };
//
//    /**
//     * 注册逻辑点击事件
//     */
//    // 获取短信验证码
//    private void getMessage() {
//        getBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                XXXXXXXXXXXXX
//            }
//        });
//    }
//    private void Next() {
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
