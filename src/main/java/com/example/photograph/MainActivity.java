package com.example.photograph;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {


    private ImageView one;
    private ImageView tow;
    private ImageView three;
    private ImageView fro;
    Bitmap mBitmap;
    private float mRotate;
    private float mSaturation;
    private float mScale;
    private ImageView zhu;
    private AppCompatSeekBar seek;
    private static int MIN_PROGRESS = 128;
    private static int MAX_PROGRESS = 255;
    private Button sd;
    private AppCompatSeekBar seek2;
    private AppCompatSeekBar seek3;
    private LinearLayout lj;
    private ImageView fh;
    private ziran ziran;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        seek.setMax(MAX_PROGRESS);
        seek.setProgress(MIN_PROGRESS);
        seek2.setMax(MAX_PROGRESS);
        seek2.setProgress(MIN_PROGRESS);
        seek3.setMax(MAX_PROGRESS);
        seek3.setProgress(MIN_PROGRESS);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.s);
        Bitmap result = mohu.fastblur(bitmap, 0.1f, 20);
        BitmapDrawable ob = new BitmapDrawable(getResources(), result);
        zhu.setBackground(ob);

        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fh.setVisibility(View.VISIBLE);
                sd.setVisibility(View.GONE);
                seek.setVisibility(View.VISIBLE);
                seek2.setVisibility(View.VISIBLE);
                seek3.setVisibility(View.VISIBLE);
                lj.setVisibility(View.GONE);
            }
        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fh.setVisibility(View.GONE);
                sd.setVisibility(View.VISIBLE);
                seek.setVisibility(View.GONE);
                seek2.setVisibility(View.GONE);
                seek3.setVisibility(View.GONE);
                lj.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        seek = (AppCompatSeekBar) findViewById(R.id.seek);
        one = (ImageView) findViewById(R.id.one);
        tow = (ImageView) findViewById(R.id.tow);
        three = (ImageView) findViewById(R.id.three);
        fro = (ImageView) findViewById(R.id.fro);
        zhu = (ImageView) findViewById(R.id.zhu);
        seek = (AppCompatSeekBar) findViewById(R.id.seek);
        sd = (Button) findViewById(R.id.sd);
        sd.setOnClickListener(this);
        seek2 = (AppCompatSeekBar) findViewById(R.id.seek2);
        seek2.setOnClickListener(this);
        seek3 = (AppCompatSeekBar) findViewById(R.id.seek3);
        seek3.setOnClickListener(this);
        lj = (LinearLayout) findViewById(R.id.lj);
        lj.setOnClickListener(this);
        one.setOnClickListener(this);
        tow.setOnClickListener(this);
        three.setOnClickListener(this);
        fro.setOnClickListener(this);

        seek.setOnSeekBarChangeListener(this);
        seek2.setOnSeekBarChangeListener(this);
        seek3.setOnSeekBarChangeListener(this);
        fh = (ImageView) findViewById(R.id.fh);
        fh.setOnClickListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        switch (id) {
            case R.id.seek:
                mRotate = (seek.getProgress() - 128f) * 1.0f / 128f * 180;
                break;
            case R.id.seek2:
                mSaturation = seek2.getProgress() / 128f;
                break;
            case R.id.seek3:
                mScale = seek3.getProgress() / 128f;
                break;
        }
        if (mBitmap != null) {
            Bitmap bitmap = BitmapUtils.beautyImage(mBitmap, mRotate, mSaturation, mScale);
            zhu.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sd:

                break;
            case R.id.one:
                Resources res = getResources();
                Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.s);
                Bitmap newBitmap = ziran.changeToGray(bitmap,1);

                //把添加滤镜后的效果显示在imageview上
                zhu.setImageBitmap(newBitmap);
                break;
            case R.id.tow:
                Resources res2 = getResources();
                Bitmap bitmap2 = BitmapFactory.decodeResource(res2, R.drawable.s);
                Bitmap newBitmap2 = ziran.changeToGray(bitmap2,2);

                //把添加滤镜后的效果显示在imageview上
                zhu.setImageBitmap(newBitmap2);
                break;
            case R.id.three:
                Resources res3 = getResources();
                Bitmap bitmap3 = BitmapFactory.decodeResource(res3, R.drawable.s);
                Bitmap newBitmap3 = ziran.changeToGray(bitmap3,3);

                //把添加滤镜后的效果显示在imageview上
                zhu.setImageBitmap(newBitmap3);
                break;
            case R.id.fro:
                Resources res4 = getResources();
                Bitmap bitmap4 = BitmapFactory.decodeResource(res4, R.drawable.s);
                Bitmap newBitmap4 = ziran.changeToGray(bitmap4,4);

                //把添加滤镜后的效果显示在imageview上
                zhu.setImageBitmap(newBitmap4);
                break;

        }
    }
}
