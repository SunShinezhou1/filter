package com.example.photograph;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.Image;

/*
 * @author Wangzhouzhou
 * @emil 1746375316@qq.com
 * create at 2017/3/7
 */
public class BitmapUtils  {

    public static Bitmap beautyImage(Bitmap srcBitmap, float rotate, float saturation, float scale) {

        //调整色相
        ColorMatrix rotateMatrix = new ColorMatrix();
        rotateMatrix.setRotate(0, rotate);
        rotateMatrix.setRotate(1, rotate);
        rotateMatrix.setRotate(2, rotate);

        //调整色彩饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        //调整灰度
        ColorMatrix scaleMatrix = new ColorMatrix();
        scaleMatrix.setScale(scale, scale, scale, 1);


        //叠加效果
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.postConcat(rotateMatrix);
        colorMatrix.postConcat(saturationMatrix);
        colorMatrix.postConcat(scaleMatrix);

        //创建一个大小相同的空白Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //载入Canvas,Paint
        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //绘图
        canvas.drawBitmap(srcBitmap, 0, 0, paint);

        return dstBitmap;
    }

    public static Bitmap image(Bitmap srcbitmap){
        int width = srcbitmap.getWidth();
        int height = srcbitmap.getHeight();
        int count = width*height;
        int[] oldpixels = new int[count];
        int[] newpixels = new int[count];

        srcbitmap.getPixels(oldpixels,0,width,0,0,width,height);
        for (int i = 0; i < oldpixels.length; i++) {
            int pixel = oldpixels[i];
            int r = Color.red(pixel);
            int g = Color.green(pixel);
            int b = Color.blue(pixel);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            if (r < 0) {
                r = 0;
            }
            if (g < 0) {
                g = 0;
            }
            if (b < 0) {
                b = 0;
            }

            newpixels[i] = Color.rgb(r, g, b);

        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(newpixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}
