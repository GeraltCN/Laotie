package com.tanya.tanya1;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;



public class MainpageActivity extends AppCompatActivity {

    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;

    private ImageView image_photo;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

//        1.设置顶部toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu_mainpage);//右上角菜单

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.location) {
                    Toast.makeText(MainpageActivity.this , "杭州" , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.notification) {
                    Toast.makeText(MainpageActivity.this , R.string.notification , Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });



//        2.设置底部TabHost
        TabHost th=(TabHost)findViewById(R.id.tabhost);
        th.setup();            //初始化TabHost容器

        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        th.addTab(th.newTabSpec("tab1").setIndicator(null,ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, null)).setContent(R.id.tab1));
        th.addTab(th.newTabSpec("tab2").setIndicator(null,ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, null)).setContent(R.id.tab2));
        th.addTab(th.newTabSpec("tab3").setIndicator(null,ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, null)).setContent(R.id.tab3));





        // 请求数据


        App myApp = ((App)getApplicationContext());

        myApp.setCallback(new App.Callback() {
            @Override
            public void example(String string) {
                try {
                    JSONObject js = new JSONObject(string);
                    /* 这是你会得到的数据格式 使用js.getString("nickname")获取字符串如nickname
                                            使用js.getInt("ap") 获取ap  司机那些需要之后拟定格式 因为数据存储的关系 不碍事的
                   {
                        "ap": 123,                                  积分
                            "dc": "ASDKJLJ123SA",                   抵扣卷
                            "drivers": "Imp",                       司机
                            "history_order": "100000000",           历史订单
                            "nickname": "Snow",                     昵称
                            "present_order": "1000000001",          当前订单
                            "result": 1,                            1 表示获取成功， 0 表示获取失败
                            "username": "ljjjx1997"                 用户的注册名
                    }*/

                    // TODO 开始你的表演








                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        String tag = "FETCH";
        String url = "http://192.168.1.104:8080/get_user_info?";

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", myApp.getToken());

        myApp.volleyPost(url, tag, map);





        th.addTab(th.newTabSpec("tab1").setIndicator(null,ResourcesCompat.getDrawable(getResources(), R.drawable.order2, null)).setContent(R.id.tab1));
        th.addTab(th.newTabSpec("tab2").setIndicator(null,ResourcesCompat.getDrawable(getResources(), R.drawable.watch2, null)).setContent(R.id.tab2));
        th.addTab(th.newTabSpec("tab3").setIndicator(null,ResourcesCompat.getDrawable(getResources(), R.drawable.personal2 , null)).setContent(R.id.tab3));



//        3.绑定各种控件
        init();
    }

    protected void init(){

        image_photo = (ImageView)findViewById(R.id.photo);

    }

    public void gallery(View view) {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                image_photo.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
