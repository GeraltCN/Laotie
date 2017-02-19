package com.tanya.tanya1;

import android.app.Application;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ducky on 2/9/17.
 */

public class App extends Application {

    private String token = null;
    private String username = null;
    private String nickname = null;
    private String historyOrder = null;
    private String presentOrder = null;
    private String dc = null;
    private int ap = 0;

////////////////////////////////////////////////////////

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setHistoryOrder(String historyOrder) {
        this.historyOrder = historyOrder;
    }

    public String getHistoryOrder() {
        return historyOrder;
    }

    public void setPresentOrder(String presentOrder) {
        this.presentOrder = presentOrder;
    }

    public String getPresentOrder() {
        return presentOrder;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getAp() {
        return ap;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getDc() {
        return dc;
    }

    //////////////////////////////////////////////////////////



    //////////////////////////////////////////////////////////

    // Http 请求队列
    private static RequestQueue queue;


    public static RequestQueue getHttpQueue () {
        return queue;
    }


    public void volleyGet(final String url, String tag, final HashMap<String,String> map) {


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println("url是"+ url);
                System.out.println("这里ok！");
                callback.example(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("Sorry");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        // 设置标签
        request.setTag(tag);
        // 加入队列
        queue.add(request);

    }

    public void volleyPost(String url, final String tag, final HashMap<String, String> map) {


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.example(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.print("程序失败于 " + tag);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        request.setTag(tag);
        queue.add(request);

    }

    public Callback callback = null;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public static interface Callback {
        void example(String string);
    }



/////////////////////////////////////////////////////////////


    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }





}
