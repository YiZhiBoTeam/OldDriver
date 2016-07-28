package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.ReMFragmentAdapter;
import com.example.rp.ezhibo.bean.ReMListViewEntity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rp on 2016/7/26.
 */
public class ReMFragment extends Fragment {
    private ListView Remlistview;
   private OkHttpClient okHttpClient;
    private ReMFragmentAdapter remAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.remfragment,container,false);
        Remlistview= (ListView) view.findViewById(R.id.remlistview);
        remAdapter=new ReMFragmentAdapter(getActivity());

        getData();
        return view;
    }

    private void getData() {
        okHttpClient=new OkHttpClient();
        final Request request= new Request.Builder()
                .url("http://appgw.yizhibo.tv/v2/livenoticelist?count=20&start=0&sessionid=YI21w4MONbs0nPYUl2NPZSp2FLcPypw9")
                .build();
       final Call call = okHttpClient.newCall(request);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Response response = call.execute();
                    if(response!=null)
                    {
                        Gson gson=new Gson();
                        final ReMListViewEntity reMListViewEntity = gson.fromJson(response.body().string(), ReMListViewEntity.class);
                        Remlistview.post(new Runnable() {
                            @Override
                            public void run() {
                                remAdapter.addData(reMListViewEntity.getRetinfo().getNotices());
                                Remlistview.setAdapter(remAdapter);

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
