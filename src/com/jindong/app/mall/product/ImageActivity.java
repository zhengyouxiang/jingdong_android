// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.utils.*;
import com.jindong.app.mall.utils.cache.ImageCache;
import java.lang.ref.SoftReference;
import java.util.List;

public class ImageActivity extends MyActivity
{

    public ImageActivity()
    {
        displayHeight = -1;
        isImageViewEvent = false;
        imageLayoutParams = new android.view.ViewGroup.LayoutParams(-1, -1);
        onClickListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view.getId();
                JVM INSTR tableswitch 2131493142 2131493144: default 32
            //                           2131493142 55
            //                           2131493143 32
            //                           2131493144 33;
                   goto _L1 _L2 _L1 _L3
_L1:
                return;
_L3:
                com.jindong.app.mall.utils.TouchImageView.Img img1 = imageView.getImg();
                if(img1 != null)
                    img1.zoomIn();
                continue; /* Loop/switch isn't completed */
_L2:
                com.jindong.app.mall.utils.TouchImageView.Img img = imageView.getImg();
                if(img != null)
                    img.zoomOut();
                if(true) goto _L1; else goto _L4
_L4:
            }

            final ImageActivity this$0;

            
            {
                this$0 = ImageActivity.this;
                super();
            }
        }
;
    }

    private int getDisplayHeight()
    {
        if(displayHeight == -1)
        {
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            int i;
            if(getResources().getConfiguration().orientation == 2)
                i = Math.min(displaymetrics.widthPixels, displaymetrics.heightPixels);
            else
                i = Math.max(displaymetrics.widthPixels, displaymetrics.heightPixels);
            displayHeight = i;
        }
        return displayHeight;
    }

    private void initData()
    {
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        position = Integer.valueOf(bundle.getInt("position"));
        product = (Product)bundle.getSerializable("product");
        orderComment = (OrderComment)bundle.getSerializable("orderComment");
        if(product == null) goto _L2; else goto _L1
_L1:
        imageList = product.getImageList();
_L4:
        return;
_L2:
        if(orderComment != null)
            imageList = orderComment.getImageList();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initGallery()
    {
        List list = imageList;
        String as[] = new String[1];
        as[0] = "small";
        int ai[] = new int[1];
        ai[0] = 0x7f0c011a;
        final MySimpleAdapter mySimpleAdapter = new MySimpleAdapter(this, list, 0x7f030035, as, ai);
        mySimpleAdapter.setAllowNoImage(false);
        post(new Runnable() {

            public void run()
            {
                gallery.setAdapter(mySimpleAdapter);
                gallery.setSelection(position.intValue());
            }

            final ImageActivity this$0;
            private final MySimpleAdapter val$mySimpleAdapter;

            
            {
                this$0 = ImageActivity.this;
                mySimpleAdapter = mysimpleadapter;
                super();
            }
        }
);
        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Image image = (Image)((Adapter)adapterview.getAdapter()).getItem(i);
                url = image.getBig();
                showImage();
            }

            final ImageActivity this$0;

            
            {
                this$0 = ImageActivity.this;
                super();
            }
        }
);
    }

    private void initImage()
    {
        String _tmp = android.os.Build.VERSION.SDK;
        zoomIn.setVisibility(0);
        zoomIn.setOnClickListener(onClickListener);
        zoomOut.setVisibility(0);
        zoomOut.setOnClickListener(onClickListener);
        imageView = new TouchImageView(this);
    }

    private void initView()
    {
        imageContainer = (RelativeLayout)findViewById(0x7f0c0115);
        zoomIn = (ImageButton)findViewById(0x7f0c0118);
        zoomOut = (ImageButton)findViewById(0x7f0c0116);
        devideLine = findViewById(0x7f0c0117);
        devideLine.setBackgroundColor(Color.rgb(143, 143, 143));
        gallery = (Gallery)findViewById(0x7f0c0119);
    }

    private void showImage()
    {
        if(ImageCache.containsKeyBitmap(url) && ImageCache.getBitmap(url).get() != null)
            post(new Runnable() {

                public void run()
                {
                    if(!isAddImageView)
                    {
                        imageContainer.addView(imageView, 0, imageLayoutParams);
                        isAddImageView = true;
                    }
                    imageView.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
                    if(imageView instanceof TouchImageView)
                        imageView.init(ImageActivity.this, (Bitmap)ImageCache.getBitmap(url).get(), gallery.getHeight());
                }

                final ImageActivity this$0;

            
            {
                this$0 = ImageActivity.this;
                super();
            }
            }
);
        else
            httpGroup.add(url, null, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(final com.jindong.app.mall.utils.HttpGroup.HttpResponse httpResponse)
                {
                    if(httpResponse.getDrawable() != null)
                    {
                        ImageCache.putBitmap(url, new SoftReference(httpResponse.getBitmap()));
                        post(new Runnable() {

                            public void run()
                            {
                                if(!isAddImageView)
                                {
                                    imageContainer.addView(imageView, 0, imageLayoutParams);
                                    isAddImageView = true;
                                }
                                imageView.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
                                if(imageView instanceof TouchImageView)
                                    imageView.init(_fld0, httpResponse.getBitmap(), gallery.getHeight());
                            }

                            final _cls5 this$1;
                            private final com.jindong.app.mall.utils.HttpGroup.HttpResponse val$httpResponse;

                    
                    {
                        this$1 = _cls5.this;
                        httpResponse = httpresponse;
                        super();
                    }
                        }
);
                    }
                }

                public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                {
                    post(new Runnable() {

                        public void run()
                        {
                            Toast.makeText(_fld0, "\u52A0\u8F7D\u5931\u8D25", 0).show();
                        }

                        final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                    }
);
                }

                public void onProgress(int i, int j)
                {
                }

                public void onStart()
                {
                }

                final ImageActivity this$0;


            
            {
                this$0 = ImageActivity.this;
                super();
            }
            }
);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030034);
        httpGroup = getHttpGroupaAsynPool(5000);
        initData();
        initView();
        initGallery();
        initImage();
        showImage();
    }

    protected void onDestroy()
    {
        ImageCache.clearAllBitmap();
        super.onDestroy();
    }

    private View devideLine;
    private int displayHeight;
    private Gallery gallery;
    private HttpGroup httpGroup;
    private RelativeLayout imageContainer;
    private android.view.ViewGroup.LayoutParams imageLayoutParams;
    private List imageList;
    private TouchImageView imageView;
    private boolean isAddImageView;
    private boolean isImageViewEvent;
    private Bitmap oldBitmap;
    private android.view.View.OnClickListener onClickListener;
    private OrderComment orderComment;
    private Integer position;
    private Product product;
    private String url;
    private ImageButton zoomIn;
    private ImageButton zoomOut;










}
