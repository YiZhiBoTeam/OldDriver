package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.YuGFragmentAdapter;
import com.example.rp.ezhibo.bean.YuGListViewEntity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rp on 2016/7/26.
 *
 */
public class YUGFragment extends Fragment {
    private OkHttpClient okHttpClient;
    private ListView YuGListview;
    private YuGFragmentAdapter YuGAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.yugfragment,container,false);
        YuGListview= (ListView) view.findViewById(R.id.YuGlistview);
        YuGAdapter=new YuGFragmentAdapter(getActivity());
        getData();
        return view;
    }
    private void getData() {
        okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://appgw.yizhibo.tv/v2/livenoticelist?count=20&start=0&sessionid=YI21w4MONbs0nPYUl2NPZSp2FLcPypw9")
                .build();
       final   Call call = okHttpClient.newCall(request);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Response response = call.execute();//要在子线程里执行
                    if(response!=null)
                    {

                        Gson gson=new Gson();
                        final YuGListViewEntity yuGListViewEntity = gson.fromJson(response.body().string(), YuGListViewEntity.class);
                        YuGListview.post(new Runnable() {
                            @Override
                            public void run() {
                               YuGAdapter.addData(yuGListViewEntity.getRetinfo().getNotices());
                                YuGListview.setAdapter(YuGAdapter);

                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



    }


}
