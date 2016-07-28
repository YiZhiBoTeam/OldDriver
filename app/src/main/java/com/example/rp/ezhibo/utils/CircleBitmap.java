package com.example.rp.ezhibo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.android.volley.toolbox.ImageRequest;

/**
 * Created by Administrator on 2016/1/25 0025.
 */
public class CircleBitmap implements ImageRequest.Transformation {
    @Override
    public Bitmap transform(Bitmap source, int maxWidth, int maxHeight) {

        int min = source.getHeight()>source.getWidth()?source.getWidth():source.getHeight();
        return createCircleImage(source,min);
    }

    /**
     * 创建圆形图片的一个方法
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN  交互
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }


    @Override
    public String key() {
        return null;
    }
}
