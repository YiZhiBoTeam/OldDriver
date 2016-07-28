package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.HFFragmentAdapter;
import com.example.rp.ezhibo.bean.HFListViewEntity;
import com.example.rp.ezhibo.view.MyApp;
import com.google.gson.Gson;

/**
 * Created by rp on 2016/7/26.
 */
public class HFFragment extends Fragment {
    private HFFragmentAdapter hfAdapter;
    private ListView hflistview;
    private  View view;
    private String Path="http://appgw.yizhibo.tv/v2/topicvideolist?live=0&count=20&start=0&sessionid=YI21w4MONbs0nPYUl2NPZSp2FLcPypw9&topicid=0";
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.hffragment,container,false);

        getData();

        return view;
    }

    private void getData() {
        StringRequest stringRequest =new StringRequest(Path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null&&response.length()>0)
                {
                    Gson gson=new Gson();
                    HFListViewEntity hfListViewEntity = gson.fromJson(response, HFListViewEntity.class);
                    if(hfListViewEntity!=null) {
                        hfAdapter = new HFFragmentAdapter(getActivity());
                        hflistview = (ListView) view.findViewById(R.id.hflistview);
                        hflistview.setDividerHeight(20);
                        hfAdapter.addData(hfListViewEntity.getRetinfo().getVideos());
                        hflistview.setAdapter(hfAdapter);
                    }
                    else{
                        Log.e("AAAAAAAAAAAA","下载为空1" );
                    }

                }
                else{
                    Log.e("AAAAAAAAAAAA","下载为空2" );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("AAAAAAAAAAAA","下载错误" );
            }
        });
        stringRequest.setTag("qx");
        MyApp.getQueue().add(stringRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApp.getQueue().cancelAll("qx");
    }
}
