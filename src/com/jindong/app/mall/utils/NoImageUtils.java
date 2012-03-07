// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import com.jindong.app.mall.MyApplication;

// Referenced classes of package com.jindong.app.mall.utils:
//            MyActivity, HttpGroup, CommonUtil

public class NoImageUtils
{

    public NoImageUtils()
    {
    }

    public static void initImageView(final MyActivity myActivity, final HttpGroup imageHttpGroup, final ImageView imageView, final String url, boolean flag)
    {
        if(flag)
            myActivity.addResumeListener(new MyActivity.ResumeListener() {

                public void onResume()
                {
                    NoImageUtils.initImageView(myActivity, imageHttpGroup, imageView, url, false);
                }

                private final HttpGroup val$imageHttpGroup;
                private final ImageView val$imageView;
                private final MyActivity val$myActivity;
                private final String val$url;

            
            {
                myActivity = myactivity;
                imageHttpGroup = httpgroup;
                imageView = imageview;
                url = s;
                super();
            }
            }
);
        if(needNoImage())
        {
            imageView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

                public boolean onLongClick(View view)
                {
                    NoImageUtils.loadImage(true, myActivity, imageHttpGroup, imageView, url);
                    return true;
                }

                private final HttpGroup val$imageHttpGroup;
                private final ImageView val$imageView;
                private final MyActivity val$myActivity;
                private final String val$url;

            
            {
                myActivity = myactivity;
                imageHttpGroup = httpgroup;
                imageView = imageview;
                url = s;
                super();
            }
            }
);
        } else
        {
            imageView.setOnLongClickListener(null);
            imageView.setLongClickable(false);
        }
        loadImage(false, myActivity, imageHttpGroup, imageView, url);
    }

    public static void loadImage(boolean flag, final MyActivity myActivity, HttpGroup httpgroup, final ImageView imageView, String s)
    {
        final boolean useCache;
        HttpGroup.OnCommonListener oncommonlistener;
        HttpGroup.HttpSetting httpsetting;
        if(!flag && needNoImage())
            useCache = true;
        else
            useCache = false;
        oncommonlistener = new HttpGroup.OnCommonListener() {

            public void onEnd(final HttpGroup.HttpResponse httpResponse)
            {
                myActivity.post(new Runnable() {

                    public void run()
                    {
                        imageView.setImageDrawable(httpResponse.getDrawable());
                        imageView.invalidate();
                    }

                    final _cls3 this$1;
                    private final HttpGroup.HttpResponse val$httpResponse;
                    private final ImageView val$imageView;

                    
                    {
                        this$1 = _cls3.this;
                        imageView = imageview;
                        httpResponse = httpresponse;
                        super();
                    }
                }
);
            }

            public void onError(HttpGroup.HttpError httperror)
            {
                myActivity.post(new Runnable() {

                    public void run()
                    {
                        if(useCache)
                            imageView.setImageDrawable(new MySimpleAdapter.ExceptionDrawable(myActivity, myActivity.getString(0x7f0901c2)));
                        else
                            imageView.setImageDrawable(new MySimpleAdapter.ExceptionDrawable(myActivity, myActivity.getString(0x7f09007d)));
                    }

                    final _cls3 this$1;
                    private final ImageView val$imageView;
                    private final MyActivity val$myActivity;
                    private final boolean val$useCache;

                    
                    {
                        this$1 = _cls3.this;
                        useCache = flag;
                        imageView = imageview;
                        myActivity = myactivity;
                        super();
                    }
                }
);
            }

            public void onReady(HttpGroup.HttpSettingParams httpsettingparams)
            {
            }

            private final ImageView val$imageView;
            private final MyActivity val$myActivity;
            private final boolean val$useCache;

            
            {
                myActivity = myactivity;
                imageView = imageview;
                useCache = flag;
                super();
            }
        }
;
        httpsetting = new HttpGroup.HttpSetting();
        httpsetting.setType(5000);
        httpsetting.setUrl(s);
        httpsetting.setListener(oncommonlistener);
        if(useCache)
            httpsetting.setCacheMode(1);
        httpgroup.add(httpsetting);
    }

    public static boolean needNoImage()
    {
        String s = MyApplication.getInstance().getString(0x7f0901c3);
        return CommonUtil.getJdSharedPreferences().getBoolean(s, false);
    }
}
