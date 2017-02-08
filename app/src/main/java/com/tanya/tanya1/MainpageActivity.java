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
    }


}
