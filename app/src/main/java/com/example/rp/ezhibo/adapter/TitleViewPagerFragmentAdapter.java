package com.example.rp.ezhibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by rp on 2016/7/26.
 */
public class TitleViewPagerFragmentAdapter extends FragmentPagerAdapter{
    private List<String>titles;
    private List<Fragment>list;
    public TitleViewPagerFragmentAdapter(FragmentManager fm, List<String>titles, List<Fragment>list) {
        super(fm);
        this.titles=titles;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
