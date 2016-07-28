package com.example.rp.ezhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rp.ezhibo.R;
import com.example.rp.ezhibo.bean.ReMListViewEntity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rp on 2016/7/26.
 */
public class ReMFragmentAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private String bigurl="";
    private String smallurl="";
    private List<ReMListViewEntity.RetinfoBean.NoticesBean>list;
    public ReMFragmentAdapter(Context context)
    {
        list=new ArrayList<>();
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    public void addData(List<ReMListViewEntity.RetinfoBean.NoticesBean>list)
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
            convertView=mInflater.inflate(R.layout.remlistviewitem,parent,false);
            holder.headimg= (ImageView) convertView.findViewById(R.id.remlistview_headimg);
            holder.img= (ImageView) convertView.findViewById(R.id.remlistview_img);
            holder.title= (TextView) convertView.findViewById(R.id.remlistview_title);
            holder.nickname= (TextView) convertView.findViewById(R.id.remlistview_nickname);
            holder.location= (TextView) convertView.findViewById(R.id.remlistview_location);
            holder.watch= (TextView) convertView.findViewById(R.id.remlistview_watch);
               convertView.setTag(holder);
        }
        else{
          holder= (ViewHolder) convertView.getTag();
        }
        holder.location.setText(list.get(position).getLocation());
        holder.nickname.setText(list.get(position).getNickname());
        holder.title.setText(list.get(position).getTitle());
     //   holder.watch.setText(list.get(position).getSubscribe_count());
       // holder.watch.setTextColor(Color.RED);
        bigurl=list.get(position).getThumb().toString().trim();
        smallurl=list.get(position).getLogourl().toString().trim();
        Picasso.with(context).load(bigurl).placeholder(R.drawable.bs_ic_more)
                .error(R.drawable.bs_ic_more)
                .config(Bitmap.Config.RGB_565)
                .into(holder.img);

        Picasso.with(context).load(smallurl).placeholder(R.drawable.bs_ic_more)
                .error(R.drawable.bs_ic_more)
                .config(Bitmap.Config.RGB_565)
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        Bitmap bitmap=toRoundBitmap(source);
                        if(bitmap!=source)
                        {
                            source.recycle();
                        }
                        return bitmap;
                    }

                    @Override
                    public String key() {
                        return "zh";
                    }
                }).into(holder.headimg);
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
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        //圆形图片宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //正方形的边长
        int r = 0;
        //取最短边做边长
        if(width > height) {
            r = height;
        } else {
            r = width;
        }
        //构建一个bitmap
        Bitmap backgroundBmp = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas = new Canvas(backgroundBmp);
        Paint paint = new Paint();
        //设置边缘光滑，去掉锯齿
        paint.setAntiAlias(true);
        //宽高相等，即正方形
        RectF rect = new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, paint);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, paint);
        //返回已经绘画好的backgroundBmp
        return backgroundBmp;
    }

}
