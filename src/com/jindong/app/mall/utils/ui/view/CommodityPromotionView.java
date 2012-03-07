// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.ui.view;

import android.content.*;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.jindong.app.mall.entity.Commercial;
import com.jindong.app.mall.product.ProductListActivity;
import com.jindong.app.mall.utils.*;
import com.jindong.app.mall.utils.ui.anim.Rotate3d;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class CommodityPromotionView
{
    public static interface OnCommercialListener
    {

        public abstract void onError(int i, String s, com.jindong.app.mall.utils.HttpGroup.HttpError httperror);

        public abstract void onFinish();

        public abstract void onSuccess(int i, String s);

        public abstract void onSyncronizotionParams(ArrayList arraylist);
    }


    public CommodityPromotionView(MyActivity myactivity, ImageSwitcher imageswitcher, LinearLayout linearlayout)
    {
        isOnline = true;
        isSet = true;
        mActivity = myactivity;
        mContext = myactivity;
        mSwitcher = imageswitcher;
        commLayout = linearlayout;
        initActivities();
    }

    private void getActivitesImage(final int index, final ArrayList commercialList, Commercial commercial, final HttpGroup pool, final OnCommercialListener listener)
    {
        final String key;
        key = commercial.getHorizontalImg();
        if(Log.D)
            Log.e("getActivitesImage", (new StringBuilder("url:")).append(key).toString());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setType(5000);
        httpsetting.setUrl(key);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            private void loadNext(int i)
            {
                if(Log.D)
                    Log.d("getActivitesImage", (new StringBuilder(" -->> loadNext()")).append(i).toString());
                if(i < commercialList.size())
                    getActivitesImage(i, commercialList, (Commercial)commercialList.get(i), pool, listener);
                else
                    isOnline = false;
            }

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                Drawable drawable = httpresponse.getDrawable();
                if(drawable != null && !TextUtils.isEmpty(key))
                    CommodityPromotionView.commercialCache.put(key, drawable);
                listener.onSuccess(index, key);
                loadNext(1 + index);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.e("getActivitesImage", httperror.toString());
                listener.onError(index, key, httperror);
                loadNext(1 + index);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final CommodityPromotionView this$0;
            private final ArrayList val$commercialList;
            private final int val$index;
            private final String val$key;
            private final OnCommercialListener val$listener;
            private final HttpGroup val$pool;

            
            {
                this$0 = CommodityPromotionView.this;
                listener = oncommerciallistener;
                index = i;
                key = s;
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

    private ImageSwitcher initActivities()
    {
        int i = DPIUtil.getWidth() / 2;
        leftAnimation = new Rotate3d(0.0F, -90F, i, 0.0F);
        rightAnimation = new Rotate3d(90F, 0.0F, i, 0.0F);
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
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                if(Log.D)
                    System.out.println("onAnimationStart");
                lockTouch = true;
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
;
        leftAnimation.setAnimationListener(animationlistener);
        rightAnimation.setAnimationListener(animationlistener);
        setAnimation(0);
        mSwitcher.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
);
        mSwitcher.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                if(isSet && size() > 1)
                    setTransition();
                return false;
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
);
        initCommercial();
        return mSwitcher;
    }

    private void initCommercial()
    {
        final GestureDetector detector = new GestureDetector(new IGestureListener(new com.jindong.app.mall.utils.IGestureListener.TouchFlingActionListener() {

            public void next()
            {
                doNext();
            }

            public void previous()
            {
                doPrevious();
            }

            public void startActivity()
            {
                doStartActivity();
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
));
        mSwitcher.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                if(Log.D)
                    Log.e("CommodityPromotionView", (new StringBuilder(" -->>onTouch() ")).append(lockTouch).toString());
                boolean flag;
                if(!lockTouch)
                    flag = detector.onTouchEvent(motionevent);
                else
                    flag = false;
                return flag;
            }

            final CommodityPromotionView this$0;
            private final GestureDetector val$detector;

            
            {
                this$0 = CommodityPromotionView.this;
                detector = gesturedetector;
                super();
            }
        }
);
    }

    private Drawable next(Drawable drawable)
    {
        type;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 30
    //                   1 49;
           goto _L1 _L2 _L3
_L1:
        return drawable;
_L2:
        leftAnimation.reverseTransformation(false);
        rightAnimation.reverseTransformation(false);
        continue; /* Loop/switch isn't completed */
_L3:
        push_left();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private Drawable previous(Drawable drawable)
    {
        type;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 30
    //                   1 49;
           goto _L1 _L2 _L3
_L1:
        return drawable;
_L2:
        leftAnimation.reverseTransformation(true);
        rightAnimation.reverseTransformation(true);
        continue; /* Loop/switch isn't completed */
_L3:
        push_right();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void push_left()
    {
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, 0x7f040001));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, 0x7f040002));
    }

    private void push_right()
    {
        mSwitcher.setInAnimation(AnimationUtils.loadAnimation(mContext, 0x7f040003));
        mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(mContext, 0x7f040004));
    }

    private void rotate3d()
    {
        mSwitcher.setInAnimation(rightAnimation);
        mSwitcher.setOutAnimation(leftAnimation);
    }

    private void setAnimation(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 38
    //                   1 45
    //                   2 52;
           goto _L1 _L2 _L3 _L4
_L1:
        rotate3d();
_L6:
        type = i;
        return;
_L2:
        rotate3d();
        continue; /* Loop/switch isn't completed */
_L3:
        push_left();
        continue; /* Loop/switch isn't completed */
_L4:
        push_right();
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void setImage(Drawable drawable)
    {
        if(drawable != null)
        {
            mSwitcher.setImageDrawable(drawable);
        } else
        {
            if(Log.D)
                Log.e("imageList", (new StringBuilder("drawable is null isOnline=")).append(isOnline).toString());
            if(isOnline)
                mSwitcher.setImageDrawable(new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(mContext, mContext.getString(0x7f09007c)));
            else
                mSwitcher.setImageDrawable(new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(mContext, mContext.getString(0x7f0900b0)));
        }
    }

    private void setTransition()
    {
        String as[] = new String[2];
        as[0] = "3D\u7279\u6548";
        as[1] = "\u666E\u901A\u7279\u6548";
        (new android.app.AlertDialog.Builder(mContext)).setAdapter(new ArrayAdapter(mContext, 0x1090003, as), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                setAnimation(i);
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
).show();
    }

    public View addNightGap()
    {
        View view = new View(mContext);
        view.setLayoutParams(new android.view.ViewGroup.LayoutParams(4, -1));
        view.setBackgroundResource(0x7f020004);
        return view;
    }

    public View addNightLamp(Object obj, int i, int j)
    {
        View view = new View(mContext);
        view.setTag(obj);
        view.setLayoutParams(new android.view.ViewGroup.LayoutParams(i, j));
        view.setBackgroundResource(0x7f020002);
        if(Log.D)
            Log.e("addNightLamp", (new StringBuilder(String.valueOf(obj.toString()))).append(";w=").append(i).append(",h=").append(j).toString());
        return view;
    }

    public void clearDrawable()
    {
        if(commercialCache != null)
            commercialCache.clear();
    }

    public void closeNight(int i)
    {
        if(commLayout == null) goto _L2; else goto _L1
_L1:
        int j = 0;
_L5:
        if(j < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
        commLayout.findViewWithTag(Integer.valueOf(j)).setBackgroundColor(0xccffffff);
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void doNext()
    {
        if(Log.D)
            Log.e("commercialList", (new StringBuilder("doPrevious() commIndex=")).append(commIndex).toString());
        if(!isEmpty() && commIndex != 0)
        {
            int i = commIndex - 1;
            commIndex = i;
            int j;
            if(i < 0)
            {
                j = 0;
            } else
            {
                j = commIndex;
                commIndex = j - 1;
            }
            commIndex = j;
            setImage(previous(getCacheDrawable(((Commercial)commercialsList.get(commIndex)).getHorizontalImg())));
            if(size() > 1)
            {
                closeNight(size());
                commLayout.findViewWithTag(Integer.valueOf(commIndex)).setBackgroundResource(0x7f020003);
            }
        } else
        {
            mSwitcher.startAnimation(AnimationUtils.loadAnimation(mContext, 0x7f040005));
        }
    }

    public void doPrevious()
    {
        if(Log.D)
            Log.e("commercialList", (new StringBuilder("doNext() commIndex=")).append(commIndex).toString());
        if(!isEmpty() && commIndex != size() - 1)
        {
            int i = 1 + commIndex;
            commIndex = i;
            int j;
            if(i > size() - 1)
            {
                j = size() - 1;
            } else
            {
                j = commIndex;
                commIndex = j + 1;
            }
            commIndex = j;
            setImage(next(getCacheDrawable(((Commercial)commercialsList.get(commIndex)).getHorizontalImg())));
            if(size() > 1)
            {
                closeNight(size());
                commLayout.findViewWithTag(Integer.valueOf(commIndex)).setBackgroundResource(0x7f020003);
            }
        } else
        {
            mSwitcher.startAnimation(AnimationUtils.loadAnimation(mContext, 0x7f040005));
        }
    }

    public void doStartActivity()
    {
        Intent intent = new Intent(mContext, com/jindong/app/mall/product/ProductListActivity);
        Bundle bundle = new Bundle();
        int i;
        int j;
        if(commIndex < 0)
            i = 0;
        else
            i = commIndex;
        commIndex = i;
        if(commIndex > size() - 1)
            j = size() - 1;
        else
            j = commIndex;
        commIndex = j;
        bundle.putSerializable("commercial", (Commercial)commercialsList.get(commIndex));
        intent.putExtras(bundle);
        mActivity.startActivityInFrame(intent);
    }

    public Drawable getCacheDrawable(String s)
    {
        Drawable drawable;
        if(commercialCache == null || TextUtils.isEmpty(s))
            drawable = null;
        else
            drawable = (Drawable)commercialCache.get(s);
        return drawable;
    }

    public boolean isEmpty()
    {
        boolean flag;
        if(commercialsList == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void my618()
    {
        my618(new OnCommercialListener() {

            public void onError(int i, String s, com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.e("OnCommercialListener", (new StringBuilder("onError() currentIndex=")).append(i).append(" commercialList size=").append(size()).toString());
                if(isEmpty() || size() <= 0 || i >= size() || i != commIndex) goto _L2; else goto _L1
_L1:
                showActivities(i, null);
_L4:
                return;
_L2:
                if(Log.D)
                    Log.e("OnCommercialListener", "onError() commercialList is null or size <= 0");
                if(true) goto _L4; else goto _L3
_L3:
            }

            public void onFinish()
            {
                if(Log.D)
                    Log.e("OnCommercialListener", "onFinish()");
            }

            public void onSuccess(int i, String s)
            {
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("onSuccess() index -->> ")).append(i).toString());
                if(i == commIndex && !isEmpty() && size() > 0)
                    showActivities(i, s);
            }

            public void onSyncronizotionParams(ArrayList arraylist)
            {
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("onSyncronizotionParams list -->> ")).append(arraylist).toString());
                show();
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
);
    }

    public void my618(final OnCommercialListener listener)
    {
        com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener customonalllistener;
        isOnline = true;
        customonalllistener = new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                CommodityPromotionView.commercialsList = Commercial.toList(httpresponse.getJSONObject().getJSONArrayOrNull("activityList"), 0);
                if(CommodityPromotionView.commercialsList != null && CommodityPromotionView.commercialsList.size() >= 1) goto _L2; else goto _L1
_L1:
                if(Log.D)
                    Log.e("My618", " -->> commercialList is null or size < 1");
                if(CommodityPromotionView.commercialsList != null && CommodityPromotionView.commercialsList.size() > 0)
                    listener.onFinish();
_L4:
                return;
_L2:
                if(CommodityPromotionView.commercialsList != null && CommodityPromotionView.commercialsList.size() > 0)
                {
                    if(CommodityPromotionView.commercialCache == null)
                        CommodityPromotionView.commercialCache = new HashMap();
                    listener.onSyncronizotionParams(CommodityPromotionView.commercialsList);
                    getActivitesImage(0, CommodityPromotionView.commercialsList, (Commercial)CommodityPromotionView.commercialsList.get(0), mActivity.getHttpGroupaAsynPool(), listener);
                }
                if(CommodityPromotionView.commercialsList != null && CommodityPromotionView.commercialsList.size() > 0)
                    listener.onFinish();
                continue; /* Loop/switch isn't completed */
                Exception exception2;
                exception2;
                if(Log.D)
                    exception2.printStackTrace();
                if(CommodityPromotionView.commercialsList != null && CommodityPromotionView.commercialsList.size() > 0)
                    listener.onFinish();
                if(true) goto _L4; else goto _L3
_L3:
                Exception exception1;
                exception1;
                if(CommodityPromotionView.commercialsList != null && CommodityPromotionView.commercialsList.size() > 0)
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

            final CommodityPromotionView this$0;
            private final OnCommercialListener val$listener;

            
            {
                this$0 = CommodityPromotionView.this;
                listener = oncommerciallistener;
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
        mActivity.getHttpGroupaAsynPool().add(httpsetting);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public void removeDrawable(String s)
    {
        if(commercialCache != null)
            commercialCache.remove(s);
    }

    public void setNightLampWidth(int i)
    {
        nightLampWith = i;
    }

    public void show()
    {
        mActivity.post(new Runnable() {

            public void run()
            {
                int i = 0;
                do
                {
                    if(i >= size() || size() <= 1)
                    {
                        mSwitcher.setVisibility(0);
                        commLayout.setVisibility(0);
                        mSwitcher.setImageDrawable(new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(mContext, mContext.getString(0x7f09007c)));
                        if(commLayout.findViewWithTag(Integer.valueOf(0)) != null)
                            commLayout.findViewWithTag(Integer.valueOf(0)).setBackgroundResource(0x7f020003);
                        return;
                    }
                    int j = (commLayout.getWidth() - DPIUtil.dip2px(1.0F) * (size() - 2)) / size();
                    if(nightLampWith < 1)
                        nightLampWith = j;
                    commLayout.addView(addNightLamp(Integer.valueOf(i), nightLampWith, DPIUtil.dip2px(3F)));
                    if(i != size() - 1)
                        commLayout.addView(addNightGap());
                    i++;
                } while(true);
            }

            final CommodityPromotionView this$0;

            
            {
                this$0 = CommodityPromotionView.this;
                super();
            }
        }
);
    }

    public void showActivities(final int index, final String key)
    {
        if(Log.D)
            Log.e("OnCommercialListener", "showActivities()");
        mActivity.post(new Runnable() {

            public void run()
            {
                Object obj;
                if(CommodityPromotionView.commercialCache != null && !TextUtils.isEmpty(key))
                    obj = (Drawable)CommodityPromotionView.commercialCache.get(key);
                else
                    obj = new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(mContext, mContext.getString(0x7f09007d));
                mSwitcher.setVisibility(0);
                mSwitcher.setImageDrawable(((Drawable) (obj)));
                commLayout.setVisibility(0);
                if(CommodityPromotionView.commercialsList.size() > 1)
                    commLayout.findViewWithTag(Integer.valueOf(index)).setBackgroundResource(0x7f020003);
                rotate3d();
            }

            final CommodityPromotionView this$0;
            private final int val$index;
            private final String val$key;

            
            {
                this$0 = CommodityPromotionView.this;
                key = s;
                index = i;
                super();
            }
        }
);
    }

    public int size()
    {
        int i;
        if(commercialsList == null)
            i = 0;
        else
            i = commercialsList.size();
        return i;
    }

    public static HashMap commercialCache;
    public static ArrayList commercialsList;
    public int commIndex;
    private LinearLayout commLayout;
    private boolean isOnline;
    public boolean isSet;
    private Rotate3d leftAnimation;
    private boolean lockTouch;
    private MyActivity mActivity;
    private Context mContext;
    private ImageSwitcher mSwitcher;
    private int nightLampWith;
    private Rotate3d rightAnimation;
    private int type;













}
