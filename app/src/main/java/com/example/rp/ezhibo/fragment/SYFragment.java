package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.TitleViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rp on 2016/7/25.
 */
public class SYFragment extends Fragment {
    private View view;

    private TabLayout mTabLayout;
    private ViewPager tabViewpager;
    private List<String>titles;
    private List<Fragment> fragmentlist;

    private TitleViewPagerFragmentAdapter hfAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.syfragment,container,false);
        mTabLayout= (TabLayout) view.findViewById(R.id.tablayout);
        tabViewpager= (ViewPager) view.findViewById(R.id.tabviewpager);
        initData();
      hfAdapter=new TitleViewPagerFragmentAdapter(getActivity().getSupportFragmentManager(),titles,fragmentlist);
        tabViewpager.setAdapter(hfAdapter);
        //mTabLayout和mViewPager  进行关联
        mTabLayout.setupWithViewPager(tabViewpager);
        //fixed 平分屏幕  SCROLLABLE:屏幕能展示多少  就先展多少  其余的滑动进入
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }

    private void initData() {
        titles=new ArrayList<>();
        titles.add("回放");
        titles.add("热门");
        titles.add("预告");

        fragmentlist=new ArrayList<>();
        HFFragment hffragment=new HFFragment();//回放
        fragmentlist.add(hffragment);


        ReMFragment remfragment=new ReMFragment();//热门
        fragmentlist.add(remfragment);

        YUGFragment yugfragment=new YUGFragment();//预告


        fragmentlist.add(yugfragment);


    }
}
