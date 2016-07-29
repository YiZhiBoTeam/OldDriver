package com.example.rp.ezhibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rp.ezhibo.R;

/**
 * Created by 三尺清辉照山河 on 2016/7/26.
 *
 * 朋友--同城
 */
public class CityFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.city_fragment,container,false);
        return v;
    }
}
