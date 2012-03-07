// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ListView;
import android.widget.TextView;
import com.jindong.app.mall.entity.Commercial;
import com.jindong.app.mall.entity.JdNews;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.jdnews.JdNewsDetailActivity;
import com.jindong.app.mall.jdnews.JdNewsListActivity;
import com.jindong.app.mall.utils.HttpGroup;
import com.jindong.app.mall.utils.JSONArrayPoxy;
import com.jindong.app.mall.utils.JSONObjectProxy;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.MyActivity;
import com.jindong.app.mall.utils.MySimpleAdapter;
import com.jindong.app.mall.utils.ui.anim.Rotate3d;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall:
//            MainActivity

public class HomeSharedToPersionActivity
{
    public static interface OnCommercialListener
    {

        public abstract void onCommercial(ArrayList arraylist);

        public abstract void onError(int i, Commercial commercial, com.jindong.app.mall.utils.HttpGroup.HttpError httperror);

        public abstract void onFinish();

        public abstract void onSyncCommercial(int i, Commercial commercial);
    }

    public static interface OnCommercialOnTouchListener
    {

        public abstract boolean onChange(boolean flag);
    }

    public static interface OnEndListener
    {

        public abstract void onEnd();
    }


    public HomeSharedToPersionActivity()
    {
    }

    private void push_left(Context context, ImageSwitcher imageswitcher)
    {
        imageswitcher.setInAnimation(AnimationUtils.loadAnimation(context, 0x7f040001));
        imageswitcher.setOutAnimation(AnimationUtils.loadAnimation(context, 0x7f040002));
    }

    private void push_right(Context context, ImageSwitcher imageswitcher)
    {
        imageswitcher.setInAnimation(AnimationUtils.loadAnimation(context, 0x7f040003));
        imageswitcher.setOutAnimation(AnimationUtils.loadAnimation(context, 0x7f040004));
    }

    public View addNightGap(Context context)
    {
        View view = new View(context);
        view.setLayoutParams(new android.view.ViewGroup.LayoutParams(4, -1));
        view.setBackgroundResource(0x7f020004);
        return view;
    }

    public View addNightLamp(Context context, Object obj, int i, int j)
    {
        View view = new View(context);
        view.setTag(obj);
        view.setLayoutParams(new android.view.ViewGroup.LayoutParams(i, j));
        view.setBackgroundResource(0x7f020002);
        if(Log.D)
            Log.e("addNightLamp", obj.toString());
        return view;
    }

    public void closeNight(View view, int i)
    {
        if(view == null) goto _L2; else goto _L1
_L1:
        int j = 0;
_L5:
        if(j < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
        view.findViewWithTag(Integer.valueOf(j)).setBackgroundColor(0xccffffff);
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void getActivitesImage(final int index, final ArrayList commercialList, final Commercial comm, final OnCommercialListener listener, final HttpGroup pool)
    {
        if(Log.D)
            Log.e("getActivitesImage", (new StringBuilder("url:")).append(comm.getHorizontalImg()).toString());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setType(5000);
        httpsetting.setUrl(comm.getHorizontalImg());
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            private void loadNext(int i)
            {
                if(Log.D)
                    Log.d("getActivitesImage", (new StringBuilder(" -->> loadNext()")).append(i).toString());
                if(i < commercialList.size())
                    getActivitesImage(i, commercialList, (Commercial)commercialList.get(i), listener, pool);
            }

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                Drawable drawable = httpresponse.getDrawable();
                if(drawable != null)
                    comm.setDrawable(drawable);
                listener.onSyncCommercial(index, comm);
                loadNext(1 + index);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.e("getActivitesImage", httperror.toString());
                listener.onError(index, comm, httperror);
                loadNext(1 + index);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final HomeSharedToPersionActivity this$0;
            private final Commercial val$comm;
            private final ArrayList val$commercialList;
            private final int val$index;
            private final OnCommercialListener val$listener;
            private final HttpGroup val$pool;

            
            {
                this$0 = HomeSharedToPersionActivity.this;
                listener = oncommerciallistener;
                index = i;
                comm = commercial;
                commercialList = arraylist;
                pool = httpgroup;
                super();
            }
        }
);
        httpsetting.setLocalFileCache(true);
        httpsetting.setLocalFileCacheTime(0x493e0L);
        pool.add(httpsetting);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public ImageSwitcher initActivities(Context context, ImageSwitcher imageswitcher, float f, int i, final OnCommercialOnTouchListener touchListener)
    {
        leftAnimation = new Rotate3d(0.0F, -90F, f, 0.0F);
        rightAnimation = new Rotate3d(90F, 0.0F, f, 0.0F);
        leftAnimation.setFillAfter(true);
        leftAnimation.setDuration(500L);
        rightAnimation.setFillAfter(true);
        rightAnimation.setDuration(500L);
        android.view.animation.Animation.AnimationListener animationlistener = new android.view.animation.Animation.AnimationListener() {

            public void onAnimationEnd(Animation animation)
            {
                if(Log.D)
                    System.out.println("onAnimationEnd");
                lockTouch = false;
                touchListener.onChange(lockTouch);
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                if(Log.D)
                    System.out.println("onAnimationStart");
                lockTouch = true;
                touchListener.onChange(lockTouch);
            }

            final HomeSharedToPersionActivity this$0;
            private final OnCommercialOnTouchListener val$touchListener;

            
            {
                this$0 = HomeSharedToPersionActivity.this;
                touchListener = oncommercialontouchlistener;
                super();
            }
        }
;
        leftAnimation.setAnimationListener(animationlistener);
        rightAnimation.setAnimationListener(animationlistener);
        setAnimation(null, imageswitcher, 0);
        imageswitcher.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
            }

            final HomeSharedToPersionActivity this$0;

            
            {
                this$0 = HomeSharedToPersionActivity.this;
                super();
            }
        }
);
        return imageswitcher;
    }

    public void my618(final Context context, final HttpGroup pool, final OnCommercialListener listener)
    {
        com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener customonalllistener = new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                ArrayList arraylist = null;
                arraylist = Commercial.toList(httpresponse.getJSONObject().getJSONArrayOrNull("activityList"), 0);
                if(arraylist != null && arraylist.size() >= 1) goto _L2; else goto _L1
_L1:
                if(Log.D)
                    Log.e("My618", " -->> commercialList is null or size < 1");
                if(arraylist != null && arraylist.size() > 0)
                    listener.onFinish();
_L9:
                return;
_L2:
                listener.onCommercial(arraylist);
                if(arraylist == null || arraylist.size() <= 0) goto _L4; else goto _L3
_L3:
                int i = 0;
_L7:
                if(i < arraylist.size()) goto _L6; else goto _L5
_L5:
                getActivitesImage(0, arraylist, (Commercial)arraylist.get(0), listener, pool);
_L4:
                if(arraylist != null && arraylist.size() > 0)
                    listener.onFinish();
                continue; /* Loop/switch isn't completed */
_L6:
                if(Log.D)
                    Log.e("My618", (new StringBuilder("size=")).append(arraylist.size()).toString());
                ((Commercial)arraylist.get(i)).setDrawable(new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(context, context.getString(0x7f09007c)));
                i++;
                  goto _L7
                Exception exception2;
                exception2;
                exception2.printStackTrace();
                if(arraylist != null && arraylist.size() > 0)
                    listener.onFinish();
                if(true) goto _L9; else goto _L8
_L8:
                Exception exception1;
                exception1;
                if(arraylist != null && arraylist.size() > 0)
                    listener.onFinish();
                throw exception1;
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.e("My618", " -->> onError() commercialList is null");
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final HomeSharedToPersionActivity this$0;
            private final Context val$context;
            private final OnCommercialListener val$listener;
            private final HttpGroup val$pool;

            
            {
                this$0 = HomeSharedToPersionActivity.this;
                listener = oncommerciallistener;
                context = context1;
                pool = httpgroup;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("listActivity");
        httpsetting.putJsonParam("page", "1");
        httpsetting.putJsonParam("pagesize", "12");
        httpsetting.setListener(customonalllistener);
        httpsetting.setLocalFileCache(true);
        httpsetting.setLocalFileCacheTime(0x493e0L);
        httpsetting.setNeedGlobalInitialization(false);
        pool.add(httpsetting);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public Drawable next(Context context, ImageSwitcher imageswitcher, Drawable drawable)
    {
        if(drawable == null)
            break MISSING_BLOCK_LABEL_66;
        type;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 38
    //                   1 57;
           goto _L1 _L2 _L3
_L1:
        Drawable drawable1 = drawable;
_L4:
        return drawable1;
_L2:
        leftAnimation.reverseTransformation(false);
        rightAnimation.reverseTransformation(false);
          goto _L1
_L3:
        push_left(context, imageswitcher);
          goto _L1
        drawable1 = null;
          goto _L4
    }

    public Drawable previous(Context context, ImageSwitcher imageswitcher, Drawable drawable)
    {
        if(drawable == null)
            break MISSING_BLOCK_LABEL_66;
        type;
        JVM INSTR tableswitch 0 1: default 32
    //                   0 38
    //                   1 57;
           goto _L1 _L2 _L3
_L1:
        Drawable drawable1 = drawable;
_L4:
        return drawable1;
_L2:
        leftAnimation.reverseTransformation(true);
        rightAnimation.reverseTransformation(true);
          goto _L1
_L3:
        push_right(context, imageswitcher);
          goto _L1
        drawable1 = null;
          goto _L4
    }

    public void setAnimation(Context context, ImageSwitcher imageswitcher, int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 50
    //                   1 69
    //                   2 78;
           goto _L1 _L2 _L3 _L4
_L1:
        imageswitcher.setInAnimation(rightAnimation);
        imageswitcher.setOutAnimation(leftAnimation);
_L6:
        type = i;
        return;
_L2:
        imageswitcher.setInAnimation(rightAnimation);
        imageswitcher.setOutAnimation(leftAnimation);
        continue; /* Loop/switch isn't completed */
_L3:
        push_left(context, imageswitcher);
        continue; /* Loop/switch isn't completed */
_L4:
        push_right(context, imageswitcher);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setImage(ImageSwitcher imageswitcher, Drawable drawable)
    {
        if(drawable == null) goto _L2; else goto _L1
_L1:
        imageswitcher.setImageDrawable(drawable);
_L4:
        return;
_L2:
        if(Log.D)
            Log.e("imageList", "drawable is null");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void showCrazyBuy(MyActivity myactivity, final OnEndListener listener)
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("page", "1");
        jsonobject.put("pagesize", "50");
_L2:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("crazy");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                HomeSharedToPersionActivity.crazyBuyProductList = Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("carzyInfo"), 0);
                listener.onEnd();
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
            }

            final HomeSharedToPersionActivity this$0;
            private final OnEndListener val$listener;

            
            {
                this$0 = HomeSharedToPersionActivity.this;
                listener = onendlistener;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        myactivity.getHttpGroupaAsynPool().add(httpsetting);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("HomeActivity", jsonexception.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void showReportList(final MyActivity myActivity, final OnEndListener listener)
    {
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final ListView report_list = (ListView)myActivity.findViewById(0x7f0c010d);
                android.view.ViewGroup.LayoutParams layoutparams = report_list.getLayoutParams();
                byte byte0 = 0;
                if(layoutparams.height == -2)
                {
                    byte0 = -2;
                    layoutparams.height = report_list.getMeasuredHeight();
                }
                byte byte1 = byte0;
                reportJsonArray = httpresponse.getJSONObject().getJSONArrayOrNull("jdnewsList");
                if(reportJsonArray != null)
                {
                    final ArrayList final_list = JdNews.toList(reportJsonArray, 0);
                    final MyActivity final_myactivity = myActivity;
                    String as[] = new String[1];
                    as[0] = "title";
                    int ai[] = new int[1];
                    ai[0] = 0x7f0c0113;
                    final MySimpleAdapter adapter = new MySimpleAdapter(ai, report_list, byte1, layoutparams, myActivity) {

                        public View getView(final int position, View view, ViewGroup viewgroup)
                        {
                            View view1 = super.getView(position, view, viewgroup);
                            int i = view1.getMeasuredHeight();
                            int j = report_list.getDividerHeight();
                            if(!one && i > 0 && heightFlag_ == -2)
                            {
                                one = true;
                                int k = (i + j) * getCount() - j;
                                report_listLayoutParams.height = k;
                            }
                            view1.setOnClickListener(new android.view.View.OnClickListener() {

                                public void onClick(View view2)
                                {
                                    android.util.Log.i("test", (new StringBuilder("++++")).append(view2.getId()).toString());
                                    JSONObject jsonobject = (JSONObject)reportJsonArray.get(position);
                                    Intent intent = new Intent(myActivity, com/jindong/app/mall/jdnews/JdNewsDetailActivity);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("jdNewsId", jsonobject.getString("jdNewsId"));
                                    bundle.putString("title", jsonobject.getString("title"));
                                    intent.putExtras(bundle);
                                    myActivity.startActivityInFrame(intent);
_L1:
                                    return;
                                    JSONException jsonexception;
                                    jsonexception;
                                    if(Log.D)
                                        Log.d("HomeActivity", jsonexception.toString());
                                      goto _L1
                                }

                                final _cls1 this$2;
                                private final MyActivity val$myActivity;
                                private final int val$position;

                        
                        {
                            this$2 = _cls1.this;
                            position = i;
                            myActivity = myactivity;
                            super();
                        }
                            }
);
                            return view1;
                        }

                        private boolean one;
                        final _cls1 this$1;
                        private final int val$heightFlag_;
                        private final MyActivity val$myActivity;
                        private final ListView val$report_list;
                        private final android.view.ViewGroup.LayoutParams val$report_listLayoutParams;


                    
                    {
                        this$1 = _cls1.this;
                        report_list = listview;
                        heightFlag_ = j;
                        report_listLayoutParams = layoutparams;
                        myActivity = myactivity1;
                        super(final_myactivity, final_list, final_i, final_as, ai);
                    }
                    }
;
                    myActivity.post(new Runnable() {

                        public void run()
                        {
                            report_list.setAdapter(adapter);
                            listener.onEnd();
                            reportText.setOnClickListener(new android.view.View.OnClickListener() {

                                public void onClick(View view)
                                {
                                    Intent intent = new Intent(myActivity, com/jindong/app/mall/jdnews/JdNewsListActivity);
                                    myActivity.startActivityInFrame(intent);
                                }

                                final _cls2 this$2;
                                private final MyActivity val$myActivity;

                        
                        {
                            this$2 = _cls2.this;
                            myActivity = myactivity;
                            super();
                        }
                            }
);
                            ((MainActivity)myActivity.getParent()).deleteToken("com.360buy:jdNewsGlobalInitToken");
                        }

                        final _cls1 this$1;
                        private final MySimpleAdapter val$adapter;
                        private final OnEndListener val$listener;
                        private final MyActivity val$myActivity;
                        private final TextView val$reportText;
                        private final ListView val$report_list;

                    
                    {
                        this$1 = _cls1.this;
                        report_list = listview;
                        adapter = mysimpleadapter;
                        listener = onendlistener;
                        reportText = textview;
                        myActivity = myactivity;
                        super();
                    }
                    }
);
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("HomeActivity", "showList()-start");
            }

            final HomeSharedToPersionActivity this$0;
            private final OnEndListener val$listener;
            private final MyActivity val$myActivity;
            private final TextView val$reportText;


            
            {
                this$0 = HomeSharedToPersionActivity.this;
                myActivity = myactivity;
                listener = onendlistener;
                reportText = textview;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("jdNews");
        httpsetting.putJsonParam("page", "1");
        httpsetting.putJsonParam("pagesize", "8");
        httpsetting.setListener(onalllistener);
        httpsetting.setLocalFileCache(true);
        httpsetting.setLocalFileCacheTime(0x493e0L);
        httpsetting.setNeedGlobalInitialization(false);
        httpsetting.setNotifyUser(true);
        myActivity.getHttpGroupaAsynPool().add(httpsetting);
    }

    public static ArrayList crazyBuyProductList = null;
    private Rotate3d leftAnimation;
    private boolean lockTouch;
    private JSONArrayPoxy reportJsonArray;
    private Rotate3d rightAnimation;
    private int type;





}
