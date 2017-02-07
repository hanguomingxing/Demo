package com.me.daydaystudy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.me.daydaystudy.R;
import com.me.daydaystudy.dialog.DialogUtils;
import com.me.daydaystudy.dialog.OnItemSelectedListener;
import com.me.daydaystudy.view.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PersonalMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private int mWidth;
    private static final int OPEN_CAMERA = 100;
    private static final int OPEN_GALLERY = 101;
    private static final int CROP = 102;
    private File file;
    private CircleImageView circleImageView;
    private String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_message);
        initTitle();
        initData();
        init();
    }

    private void init() {
        circleImageView = (CircleImageView) findViewById(R.id.my_inform_circleImageView);
        findViewById(R.id.my_info_exit).setOnClickListener(this);
        findViewById(R.id.my_info_rl_photo).setOnClickListener(this);
    }

    private void initData() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
    }

    /**
     * 弹出 Dialog
     */
    private void showDialog() {
        DialogUtils.showItemSelectDialog(PersonalMessageActivity.this, mWidth
                , onIllegalListener
                , "相册"
                , "拍照");//可填添加任意多个Item呦
    }

    private OnItemSelectedListener onIllegalListener = new OnItemSelectedListener() {
        @Override
        public void getSelectedItem(String content) {
            if (content.equals("相册")) {
                // 打开相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, OPEN_GALLERY);
            } else if (content.equals("拍照")) {
                file = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".png");
                // 隐式意图打开系统界面 --要求回传
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 存到什么位置
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, OPEN_CAMERA);
            }
        }
    };

    private void initTitle() {
        TextView title_text = (TextView) findViewById(R.id.title_text);
        title_text.setVisibility(View.VISIBLE);
        title_text.setText("个人信息");
        findViewById(R.id.title_back).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAMERA) {
            // 图片怎么取出
            Uri uri = Uri.fromFile(file);
            circleImageView.setImageURI(uri);
            crop(Uri.fromFile(file));

        } else if (requestCode == OPEN_GALLERY) {
            // 相册应用通过putData设置的图片的uri，所以我们这样拿
            Uri uri = data.getData();
            // imageView.setImageURI(uri);
            crop(uri);
        } else if (requestCode == CROP) {
            // 直接拿到一张图片
            final Bitmap bitmap = data.getParcelableExtra("data");
            imagepath = System.currentTimeMillis() + ".png";
            File picFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                    imagepath);
            // 把bitmap放置到文件中
            // format 格式
            // quality 质量
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(
                        picFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 上传服务器
            HttpUtils httpUtils = new HttpUtils();
            // 必须post get 1k
            // 请求方式 请求地址 请求参数 回调
            RequestParams params = new RequestParams();
            params.addBodyParameter("files", picFile);
            httpUtils
                    .send(HttpMethod.POST,
                            "http://169.254.195.242:8080/imageupload/servlet/UploadServlet",
                            params, new RequestCallBack<String>() {

                                @Override
                                public void onFailure(HttpException arg0,
                                                      String arg1) {
                                    Toast.makeText(PersonalMessageActivity.this, "上传失败", Toast.LENGTH_LONG)
                                            .show();
                                }

                                @Override
                                public void onSuccess(ResponseInfo<String> arg0) {
                                    Toast.makeText(PersonalMessageActivity.this, "上传成功", Toast.LENGTH_LONG)
                                            .show();
                                    circleImageView.setImageBitmap(bitmap);

                                }
                            });
            circleImageView.setImageBitmap(bitmap);
        }
    }

    // 手机自带裁剪功能--调用系统裁剪的意图
    public void crop(Uri uri) {
        // 定义图片裁剪意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置是否裁剪
        intent.putExtra("crop", "true");
        // 裁剪框的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 设置图片格式
        intent.putExtra("outputFormat", "JPEG");
        // 是否返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //退出登陆
            case R.id.my_info_exit:
                finish();
                break;
            //popwindowsw
            case R.id.my_info_rl_photo:
                showDialog();
                break;
        }
    }
}
