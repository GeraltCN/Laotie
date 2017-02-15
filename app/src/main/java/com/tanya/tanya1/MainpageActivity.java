package com.tanya.tanya1;

import android.app.ActionBar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainpageActivity extends AppCompatActivity {

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




    }





}
