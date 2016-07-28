package com.example.rp.ezhibo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.view.MainActivity;

/**
 * Created by 三尺清辉照山河 on 2016/7/26.
 * <p>
 * 朋友--关注
 */
public class FollowFragment extends Fragment {
    private ListView lv_follow_fragment;
    private TextView tv1_follow_fragment;
    private TextView tv2_follow_fragment;
    private TextView tv3_follow_fragment;
    private RelativeLayout empty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.follow_fragment, container, false);

        initView(v);

        tv3_follow_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });



        return v;
    }

    private void initView(View v) {
        lv_follow_fragment = (ListView) v.findViewById(R.id.lv_follow_fragment);
        tv1_follow_fragment = (TextView) v.findViewById(R.id.tv1_follow_fragment);
        tv2_follow_fragment = (TextView) v.findViewById(R.id.tv2_follow_fragment);
        tv3_follow_fragment = (TextView) v.findViewById(R.id.tv3_follow_fragment);
        empty = (RelativeLayout) v.findViewById(android.R.id.empty);
    }
}
