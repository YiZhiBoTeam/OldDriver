package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rp.ezhibo.R;

/**
 * Created by 三尺清辉照山河 on 2016/7/26.
 * <p/>
 * 朋友--排行
 */
public class RanKingFragment extends Fragment {
    private TextView week_friend_ranking;
    private TextView month_friend_ranking;
    private TextView sum_friend_ranking;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ranking_fragment, container, false);
        initView(v);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_friend_raking,new RankWeekFragment()).commit();

        //周榜
        week_friend_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                week_friend_ranking.setBackgroundColor(0xfffb6655);
                month_friend_ranking.setBackgroundColor(0xffffffff);
                sum_friend_ranking.setBackgroundColor(0xffffffff);

                week_friend_ranking.setTextColor(0xffffffff);
                month_friend_ranking.setTextColor(0xfffb6655);
                sum_friend_ranking.setTextColor(0xfffb6655);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_friend_raking,new RankWeekFragment()).commit();
            }
        });

        //月榜
        month_friend_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                week_friend_ranking.setBackgroundColor(0xffffffff);
                month_friend_ranking.setBackgroundColor(0xfffb6655);
                sum_friend_ranking.setBackgroundColor(0xffffffff);

                week_friend_ranking.setTextColor(0xfffb6655);
                month_friend_ranking.setTextColor(0xffffffff);
                sum_friend_ranking.setTextColor(0xfffb6655);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_friend_raking,new RankMonthFragment()).commit();

            }
        });

        //总榜
        sum_friend_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                week_friend_ranking.setBackgroundColor(0xffffffff);
                month_friend_ranking.setBackgroundColor(0xffffffff);
                sum_friend_ranking.setBackgroundColor(0xfffb6655);

                week_friend_ranking.setTextColor(0xfffb6655);
                month_friend_ranking.setTextColor(0xfffb6655);
                sum_friend_ranking.setTextColor(0xffffffff);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_friend_raking,new RankSumFragment()).commit();

            }
        });



        return v;
    }

    private void initView(View v) {
        week_friend_ranking = (TextView) v.findViewById(R.id.week_friend_ranking);
        month_friend_ranking = (TextView) v.findViewById(R.id.month_friend_ranking);
        sum_friend_ranking = (TextView) v.findViewById(R.id.sum_friend_ranking);
    }
}
