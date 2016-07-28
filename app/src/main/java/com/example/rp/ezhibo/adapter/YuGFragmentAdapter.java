package com.example.rp.ezhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.bean.YuGListViewEntity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rp on 2016/7/26.
 */
public class YuGFragmentAdapter extends BaseAdapter{
    private Context  content;
    private List<YuGListViewEntity.RetinfoBean.NoticesBean>list;
    private LayoutInflater mInflater;
    private String bigurl="";
    private String smallurl="";
    public YuGFragmentAdapter(Context context)
    {
        list=new ArrayList<>();
        mInflater=LayoutInflater.from(context);
        this.content=context;
    }
    public void addData(List<YuGListViewEntity.RetinfoBean.NoticesBean>list)
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
      ViewHolder holder;
        if(convertView==null)
        {
            holder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.yuglistview,parent,false);
            holder.headimg= (ImageView) convertView.findViewById(R.id.YuGlistview_headimg);
            holder.img= (ImageView) convertView.findViewById(R.id.YuGlistview_img);
            holder.location= (TextView) convertView.findViewById(R.id.YuGlistview_location);
            holder.nickname= (TextView) convertView.findViewById(R.id.YuGlistview_nickname);
            holder.subscribe= (TextView) convertView.findViewById(R.id.YuGlistview_subscribe);
            holder.title= (TextView) convertView.findViewById(R.id.YuGlistview_title);

            convertView.setTag(holder);
        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.location.setText(list.get(position).getLocation());
        holder.nickname.setText(list.get(position).getNickname());
        holder.title.setText(list.get(position).getTitle());
        bigurl= list.get(position).getThumb().toString().trim();
        smallurl= list.get(position).getLogourl().toString().trim();
        holder.subscribe.setText(list.get(position).getSubscribe_count()+"");
        DisplayImageOptions options=new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.drawable.bs_ic_more)
                .showImageOnFail(R.drawable.bs_ic_more)
                .showImageOnLoading(R.drawable.bs_ic_more)
                .build();
        ImageLoader.getInstance().displayImage(bigurl, holder.img, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });
        DisplayImageOptions options1=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.drawable.bs_ic_more)
                .showImageOnFail(R.drawable.bs_ic_more)
                .showImageOnLoading(R.drawable.bs_ic_more)
                .displayer(new CircleBitmapDisplayer())
                .build();
        ImageLoader.getInstance().displayImage(smallurl, holder.headimg, options1, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {

            }
        });
        return convertView;
    }
    static class ViewHolder{
        ImageView headimg;
        ImageView img;
        TextView nickname;
        TextView location;
        TextView subscribe;
        TextView title;

    }

}
