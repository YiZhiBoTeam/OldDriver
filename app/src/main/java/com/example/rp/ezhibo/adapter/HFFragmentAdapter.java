package com.example.rp.ezhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.bean.HFListViewEntity;
import com.example.rp.ezhibo.utils.CircleBitmap;
import com.example.rp.ezhibo.view.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rp on 2016/7/26.
 */
public class HFFragmentAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private List<HFListViewEntity.RetinfoBean.VideosBean>list;
    private String bigUrl="";
    private String smallUrl="";

    public HFFragmentAdapter(Context context)
    {
        list=new ArrayList<>();
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    public void setData(List<HFListViewEntity.RetinfoBean.VideosBean>list)
    {
        this.list=list;
        notifyDataSetChanged();
    }
    public void addData(List<HFListViewEntity.RetinfoBean.VideosBean>list)
    {
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
        ViewHolder holder=null;
        if(convertView==null)
        {
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.hflistviewitem,parent,false);
            holder.headimg= (ImageView) convertView.findViewById(R.id.hflistview_headimg);
            holder.img= (ImageView) convertView.findViewById(R.id.hflistview_img);
            holder.location= (TextView) convertView.findViewById(R.id.hflistview_location);
            holder.nickname= (TextView) convertView.findViewById(R.id.hflistview_nickname);
            holder.watch= (TextView) convertView.findViewById(R.id.hflistview_watch);
            holder.title= (TextView) convertView.findViewById(R.id.hflistview_title);
            convertView.setTag(holder);

        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.location.setText(list.get(position).getLocation());
        holder.nickname.setText(list.get(position).getNickname());
        holder.title.setText(list.get(position).getTitle());
        holder.watch.setText(list.get(position).getWatch_count()+"");
        holder.watch.setTextColor(Color.RED);
      bigUrl=  list.get(position).getLogo_thumb().toString().trim();
        smallUrl=list.get(position).getLogourl().toString().trim();
        ImageLoader.ImageListener imageListener=ImageLoader.getImageListener(holder.img,R.drawable.bs_ic_more,R.drawable.bs_ic_more);
        ImageLoader.ImageListener imageListener1=ImageLoader.getImageListener(holder.headimg,R.drawable.bs_ic_more,R.drawable.bs_ic_more);

        mImageLoader=new ImageLoader(MyApp.getQueue(), new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap>mLruCache=new LruCache<String,Bitmap>(1024*1024){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
            @Override
            public Bitmap getBitmap(String url) {
                return mLruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
              mLruCache.put(url,bitmap);
            }
        });
        mImageLoader.get(bigUrl,imageListener,0,0);
        mImageLoader.get(smallUrl,imageListener1,0,0, ImageView.ScaleType.CENTER_CROP,new CircleBitmap());
        return convertView;
    }
    static class ViewHolder{
        ImageView headimg;
        ImageView img;
        TextView nickname;
        TextView location;
        TextView watch;
        TextView title;
    }
}
