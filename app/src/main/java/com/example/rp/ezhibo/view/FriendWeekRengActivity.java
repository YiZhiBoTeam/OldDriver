package com.example.rp.ezhibo.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.FriendAdapter;
import com.example.rp.ezhibo.bean.WeekRen;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FriendWeekRengActivity extends AppCompatActivity {

    private ListView lv_friendweekreng;
    private FriendAdapter adapter;
    private String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_week_reng);

        ActionBar bar=getSupportActionBar();
        bar.hide();

        initView();

        path=getIntent().getStringExtra("path");
        adapter=new FriendAdapter(this);
        getInit(path);
        lv_friendweekreng.setAdapter(adapter);
    }

    private void initView() {
        lv_friendweekreng = (ListView) findViewById(R.id.lv_friendweekreng);
    }

    public void getInit(String p) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个请求
        final Request request = new Request.Builder().url(p).build();

        final Call call = okHttpClient.newCall(request);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Response response = call.execute();

                    Gson gson=new Gson();

                    final WeekRen weekRen =gson.fromJson(response.body().string(),WeekRen.class);
//                    list.addAll(weekRen.getRetinfo().getSend_rank_list());

                    lv_friendweekreng.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.addList(weekRen.getRetinfo().getSend_rank_list());
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void back(View view) {
        finish();
    }
}
