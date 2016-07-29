package com.example.rp.ezhibo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.FriendAdapter;
import com.example.rp.ezhibo.adapter.FriendAdapterHong;
import com.example.rp.ezhibo.bean.WeekHong;
import com.example.rp.ezhibo.bean.WeekRen;
import com.example.rp.ezhibo.view.FriendWeekRengActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 三尺清辉照山河 on 2016/7/27.
 */
public class RankSumFragment extends Fragment {

    private ListView lv1_rank_week;
    private ListView lv2_rank_week;
    private String path1 = "http://appgw.yizhibo.tv/v2/assetsranklist?count=20&start=0&type=send&sessionid=YI21w4MONbs0nPYUl2NPZSp2FLcPypw9";
    private String path2 = "http://appgw.yizhibo.tv/v2/assetsranklist?count=20&start=0&type=receive&sessionid=YI21w4MONbs0nPYUl2NPZSp2FLcPypw9";
    private List<WeekRen.RetinfoBean.SendRankListBean> list1;
    private List<WeekHong.RetinfoBean.ReceiveRankListBean> list2;
    private FriendAdapter adapter1;
    private FriendAdapterHong adapter2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rank_week_fragment, container, false);

        initView(v);
        adapter1 = new FriendAdapter(getActivity());
        adapter2 = new FriendAdapterHong(getActivity());
        getInit(path1);
        getInit2(path2);

        lv1_rank_week.addHeaderView(inflater.inflate(R.layout.weekheaderview, lv1_rank_week, false));

        View footerView = inflater.inflate(R.layout.weekfooterview, lv1_rank_week, false);

        footerView.findViewById(R.id.tv_FooterView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), FriendWeekRengActivity.class);
                intent.putExtra("path",path1);
                startActivity(intent);
//                startActivity(new Intent(getActivity(), FriendWeekRengActivity.class));
            }
        });
        lv1_rank_week.addFooterView(footerView);
        lv1_rank_week.setAdapter(adapter1);
        lv2_rank_week.setAdapter(adapter2);

        return v;
    }

    private void initView(View v) {
        lv1_rank_week = (ListView) v.findViewById(R.id.lv1_rank_week);
        lv2_rank_week = (ListView) v.findViewById(R.id.lv2_rank_week);
        list1 = new ArrayList<WeekRen.RetinfoBean.SendRankListBean>();
        list2 = new ArrayList<WeekHong.RetinfoBean.ReceiveRankListBean>();
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

                    Gson gson = new Gson();

                    WeekRen weekRen = gson.fromJson(response.body().string(), WeekRen.class);
                    for (int i = 0; i < 5; i++) {
                        list1.add(weekRen.getRetinfo().getSend_rank_list().get(i));
                    }
                    lv1_rank_week.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter1.addList(list1);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getInit2(String p) {
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

                    Gson gson = new Gson();

                    WeekHong weekHong = gson.fromJson(response.body().string(), WeekHong.class);

                    for (int i = 0; i < 5; i++) {
                        list2.add(weekHong.getRetinfo().getReceive_rank_list().get(i));
                    }
                    lv2_rank_week.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter2.addList(list2);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
