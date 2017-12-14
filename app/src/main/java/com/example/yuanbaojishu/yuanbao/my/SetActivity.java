package com.example.yuanbaojishu.yuanbao.my;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanbaojishu.yuanbao.R;
import com.example.yuanbaojishu.yuanbao.Util.ImageUtils;

import java.io.File;


public class SetActivity extends AppCompatActivity {

    private static final String TAG = "SetActivity";
    private Button backBtn, photoBtn, nicknameBtn, accountBtn, aboutBtn, updateBtn, signoutBtn;

    private ImageView photoImage;// 修改头像相关设置
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private TextView nickname;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set);
    }

    private void init() {
        backBtn = (Button)findViewById(R.id.backBtn_set);
        photoBtn = (Button)findViewById(R.id.photoBtn_set);// 修改头像
        nicknameBtn = (Button)findViewById(R.id.nicknameBtn_set);
        accountBtn = (Button)findViewById(R.id.account_set);
        aboutBtn = (Button)findViewById(R.id.aboutBtn);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        signoutBtn = (Button)findViewById(R.id.signout);

        photoImage = (ImageView)findViewById(R.id.photo_set);
        nickname = (TextView)findViewById(R.id.nicknameSet_set);
    }

    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.backBtn_set:
                    intent.setClass(SetActivity.this, MyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.photoBtn_set:
                    showChoosePicDialog();
                    break;
                case R.id.nicknameBtn_set:
                    intent.setClass(SetActivity.this, NicknameActivity.class);
                    startActivity(intent);
                    break;
                case R.id.account_set:
                    intent.setClass(SetActivity.this, AccountActivity.class);
                    startActivity(intent);
                    break;
                case R.id.aboutBtn:
                    about();
                    break;
                case R.id.updateBtn:
                    update();
                    break;
                case R.id.signout:
                    signout();
                    break;
            }
        }
    }

    // 关于原宝
    private void about() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("原宝")
                .setMessage("帮助您免费试用商品")
                .setPositiveButton("是", null)
                .show();
    }

    // 更新
    private void update() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("原宝1.0")
                .setMessage("您已是最新版本")
                .setPositiveButton("是", null)
                .show();
    }

    // 退出登录
    private void signout() {
        intent.setClass(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        Log.i(TAG, "onClick: 选择本地图片");
                        break;
                    case TAKE_PICTURE: // 拍照
                        takePicture();
                        Log.i(TAG, "onClick: 拍照");
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void takePicture() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= 23) {
            // 需要申请动态权限
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            Log.i(TAG, "takePicture: 申请动态权限");
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Log.i(TAG, "takePicture: 授权");
                Toast.makeText(this, "获准授权", Toast.LENGTH_SHORT).show();
            }
        }
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "image.jpg");
        // 判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= 24) {
            openCameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            tempUri = FileProvider.getUriForFile(SetActivity.this, "com.example.yuanbao.fileProvider", file);
        } else {
            tempUri = Uri.fromFile(new File(Environment
                    .getExternalStorageDirectory(), "image.jpg"));
        }
        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Log.d(TAG,"setImageToView:" + photo);
            photo = ImageUtils.toRoundBitmap(photo);// 这个时候的图片已经被处理成圆形的了
            photoImage.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
            Log.d(TAG,"imagePath:"+imagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }
}
