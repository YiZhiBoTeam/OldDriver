package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.adapter.FriendFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 三尺清辉照山河 on 2016/7/26.
 *
 * 朋友
 */
public class FriendFragment extends Fragment {
    private TabLayout tabLayout_FriendFragment;
    private ViewPager viewPager_FriendFragment;
    private List<Fragment> fragments;
    private List<String> titles;
    private FragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friend_fragment, container, false);

        initView(v);
        initData();
        return v;
    }

    private void initData() {

        fragments=new ArrayList<>();
        titles=new ArrayList<>();

        titles.add("排行");
        titles.add("关注");
        titles.add("同城");

        RanKingFragment rf=new RanKingFragment();
        FollowFragment ff=new FollowFragment();
        CityFragment cf=new CityFragment();

        fragments.add(rf);
        fragments.add(ff);
        fragments.add(cf);

        adapter=new FriendFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragments,titles);

        viewPager_FriendFragment.setAdapter(adapter);

        //tabLayout和ViewPager 进行关联
        tabLayout_FriendFragment.setupWithViewPager(viewPager_FriendFragment);

        tabLayout_FriendFragment.setTabMode(TabLayout.MODE_FIXED);

    }

    private void initView(View v) {
        tabLayout_FriendFragment = (TabLayout) v.findViewById(R.id.tabLayout_FriendFragment);
        viewPager_FriendFragment = (ViewPager) v.findViewById(R.id.viewPager_FriendFragment);
    }
}
