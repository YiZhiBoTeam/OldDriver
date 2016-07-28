package com.example.rp.ezhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.bean.WeekRen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 三尺清辉照山河 on 2016/7/26.
 */
public class FriendAdapter extends BaseAdapter {

    private Context context;
    private List<WeekRen.RetinfoBean.SendRankListBean> list;

    public FriendAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<WeekRen.RetinfoBean.SendRankListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<WeekRen.RetinfoBean.SendRankListBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_friend_rank, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据

        WeekRen.RetinfoBean.SendRankListBean week = (WeekRen.RetinfoBean.SendRankListBean) getItem(position);

        viewHolder.tv1_item_friend.setText(position+1+"");
        viewHolder.nickname.setText(week.getNickname());
        viewHolder.costecoin.setText("送出易币 "+week.getCostecoin());
        if ("female".equals(week.getGender())) {
            viewHolder.sex.setImageResource(R.drawable.personal_icon_girl);
        } else {
            viewHolder.sex.setImageResource(R.drawable.personal_icon_boy);
        }

        viewHolder.level.setText(week.getLevel()+"");
        switch (week.getVip_level()) {
            case 1:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_1);
                break;
            case 2:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_2);
                break;
            case 3:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_3);
                break;
            case 4:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_4);
                break;
            case 5:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_5);
                break;
            case 6:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_6);
                break;
            case 7:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_7);
                break;
            case 8:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_8);
                break;
            case 9:
                viewHolder.vip_level.setImageResource(R.drawable.live_icon_vip_level_9);
                break;
        }

        //头像
        Picasso.with(context).load(week.getLogourl()).into(viewHolder.item_logo);

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv1_item_friend;
        public ImageView item_logo;
        public TextView nickname;
        public TextView costecoin;
        public ImageView sex;
        public TextView level;
        public ImageView vip_level;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv1_item_friend = (TextView) rootView.findViewById(R.id.tv1_item_friend);
            this.item_logo = (ImageView) rootView.findViewById(R.id.item_logo);
            this.nickname = (TextView) rootView.findViewById(R.id.nickname);
            this.costecoin = (TextView) rootView.findViewById(R.id.costecoin);
            this.sex = (ImageView) rootView.findViewById(R.id.sex);
            this.level = (TextView) rootView.findViewById(R.id.level);
            this.vip_level = (ImageView) rootView.findViewById(R.id.vip_level);
        }

    }
}
