// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            SimpleBeanAdapter, MyActivity, Log, HttpGroup, 
//            NextPageLoader, ProductImagesView, NoImageUtils, DPIUtil, 
//            CirculationGalleryAdapter

public class MySimpleAdapter extends SimpleBeanAdapter
    implements MyActivity.DestroyListener, MyActivity.PauseListener, MyActivity.ResumeListener
{
    public static class ExceptionDrawable extends Drawable
    {

        public void draw(Canvas canvas)
        {
            Rect rect = getBounds();
            float f = rect.right - rect.width() / 2;
            float f1 = rect.bottom - rect.height() / 2;
            canvas.drawText(text, f, f1, paint);
            canvas.drawBitmap(bitmap, f - (float)(width / 2), (f1 - (float)(height / 2)) + (float)DPIUtil.dip2px(10F), paint);
        }

        public int getOpacity()
        {
            return 0;
        }

        public void setAlpha(int i)
        {
        }

        public void setColorFilter(ColorFilter colorfilter)
        {
        }

        private final Bitmap bitmap;
        private final int height;
        private Paint paint;
        private final String text;
        private final int width;

        public ExceptionDrawable(Context context, String s)
        {
            paint = new Paint();
            paint.setColor(0xff888888);
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setTextSize(DPIUtil.dip2px(12F));
            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
            paint.setAntiAlias(true);
            text = s;
            bitmap = ((BitmapDrawable)context.getResources().getDrawable(0x7f020077)).getBitmap();
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
    }


    public MySimpleAdapter(MyActivity myactivity, List list, int i, String as[], int ai[])
    {
        super(myactivity, list, i, as, ai);
        localImageCache = new HashMap();
        positionToUrl = new HashMap();
        allowNoImage = true;
        myActivity = myactivity;
        handler = myactivity.getHandler();
        myactivity.addDestroyListener(this);
        myactivity.addPauseListener(this);
        myactivity.addResumeListener(this);
        loadingDrawable = new ExceptionDrawable(myactivity, myactivity.getString(0x7f09007c));
        noDrawable = new ExceptionDrawable(myactivity, myactivity.getString(0x7f09007d));
        interactionDrawable = new ExceptionDrawable(myactivity, myactivity.getString(0x7f0901c2));
    }

    public MySimpleAdapter(MyActivity myactivity, List list, int i, String as[], int ai[], int j, float f, 
            float f1)
    {
        this(myactivity, list, i, as, ai);
        thumbType = j;
        thumbWidth = f;
        thumbHeight = f1;
    }

    private void loadImage(final boolean onlyCache, ImageView imageview, final String url)
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("loadImage() 2 -->> url:")).append(url).toString());
        if((Drawable)localImageCache.get(url) != loadingDrawable)
        {
            localImageCache.put(url, loadingDrawable);
            HttpGroup.HttpSetting httpsetting;
            if(imageview != null)
                imageview.setImageDrawable(loadingDrawable);
            else
                notifyDownloadComplete();
            if(imageHttpGroup == null)
                imageHttpGroup = myActivity.getHttpGroupaAsynPool(5000);
            httpsetting = new HttpGroup.HttpSetting();
            httpsetting.setEffect(0);
            httpsetting.setUrl(url);
            if(onlyCache)
                httpsetting.setCacheMode(1);
            httpsetting.setListener(new HttpGroup.OnCommonListener() {

                public void onEnd(final HttpGroup.HttpResponse httpResponse)
                {
                    if(Log.D)
                        Log.d("Temp", "loadImage() 2 onEnd() -->> ");
                    handler.post(new Runnable() {

                        public void run()
                        {
                            if(!isFinishing)
                            {
                                Drawable drawable;
                                if(thumbType == 0)
                                    drawable = httpResponse.getDrawable();
                                else
                                    drawable = httpResponse.getThumbDrawable(thumbWidth, thumbHeight);
                                if(Log.D)
                                    Log.d("Temp", (new StringBuilder("loadImage() 2 onEnd() -->> drawable url:")).append(url).toString());
                                localImageCache.put(url, drawable);
                                notifyDownloadComplete();
                            }
                        }

                        final _cls4 this$1;
                        private final HttpGroup.HttpResponse val$httpResponse;
                        private final String val$url;

                    
                    {
                        this$1 = _cls4.this;
                        httpResponse = httpresponse;
                        url = s;
                        super();
                    }
                    }
);
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                    if(Log.D)
                        Log.d("Temp", "loadImage() 2 onError() -->> ");
                    handler.post(new Runnable() {

                        public void run()
                        {
                            if(!isFinishing) goto _L2; else goto _L1
_L1:
                            return;
_L2:
                            if(!onlyCache)
                                break; /* Loop/switch isn't completed */
                            if(Log.D)
                                Log.d("Temp", (new StringBuilder("loadImage() 2 onError() -->> interactionDrawable url:")).append(url).toString());
                            localImageCache.put(url, interactionDrawable);
_L4:
                            notifyDownloadComplete();
                            if(true) goto _L1; else goto _L3
_L3:
                            if(Log.D)
                                Log.d("Temp", (new StringBuilder("loadImage() 2 onError() -->> noDrawable url:")).append(url).toString());
                            localImageCache.put(url, noDrawable);
                              goto _L4
                            if(true) goto _L1; else goto _L5
_L5:
                        }

                        final _cls4 this$1;
                        private final boolean val$onlyCache;
                        private final String val$url;

                    
                    {
                        this$1 = _cls4.this;
                        onlyCache = flag;
                        url = s;
                        super();
                    }
                    }
);
                }

                public void onReady(HttpGroup.HttpSettingParams httpsettingparams)
                {
                }

                final MySimpleAdapter this$0;
                private final boolean val$onlyCache;
                private final String val$url;


            
            {
                this$0 = MySimpleAdapter.this;
                onlyCache = flag;
                url = s;
                super();
            }
            }
);
            imageHttpGroup.add(httpsetting);
        }
    }

    private boolean loadImage(String s)
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("loadImage() -->> url:")).append(s).toString());
        Drawable drawable = (Drawable)localImageCache.get(s);
        boolean flag;
        if(!(drawable instanceof ExceptionDrawable))
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("loadImage() false -->> drawable:")).append(drawable).toString());
            flag = false;
        } else
        {
            isLongClickAndDataSetChange = true;
            loadImage(false, null, s);
            if(Log.D)
                Log.d("Temp", (new StringBuilder("loadImage() true -->> url:")).append(s).toString());
            flag = true;
        }
        return flag;
    }

    private void notifyDownloadComplete()
    {
        if(nextPageLoader != null)
            nextPageLoader.notifyDataSetChanged();
        else
            notifyDataSetChanged();
    }

    private String positionToUrl(Integer integer)
    {
        return (String)positionToUrl.get(integer);
    }

    private void putPositionAndUrl(Integer integer, String s)
    {
        positionToUrl.put(integer, s);
    }

    public HttpGroup getImageHttpGroup()
    {
        return imageHttpGroup;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1 = super.getView(i, view, viewgroup);
        if(imageUrl != null)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getView() PositionAndUrl -->> ")).append(i).append(" ").append(imageUrl).toString());
            putPositionAndUrl(Integer.valueOf(i), imageUrl);
        }
        if(viewgroup instanceof ListView)
        {
            if(imageParentTouchListener == null)
                imageParentTouchListener = new android.view.View.OnTouchListener() {

                    public boolean onTouch(View view2, MotionEvent motionevent)
                    {
                        boolean flag;
                        if(isLongClickAndDataSetChange && motionevent.getAction() != 0)
                            flag = true;
                        else
                        if(motionevent.getAction() == 0)
                        {
                            isLongClickAndDataSetChange = false;
                            flag = false;
                        } else
                        {
                            flag = false;
                        }
                        return flag;
                    }

                    final MySimpleAdapter this$0;

            
            {
                this$0 = MySimpleAdapter.this;
                super();
            }
                }
;
            ((ListView)viewgroup).setOnTouchListener(imageParentTouchListener);
        }
        if(isOnlyOneImage() && ((viewgroup instanceof Gallery) || (viewgroup instanceof ProductImagesView)))
        {
            if(Log.D)
                Log.d("Temp", "performItemLongClick() 2 -->> l:");
            AdapterView adapterview = (AdapterView)viewgroup;
            final android.widget.AdapterView.OnItemLongClickListener onItemLongClickListener = adapterview.getOnItemLongClickListener();
            if(imageClickListener == null)
                imageClickListener = new android.widget.AdapterView.OnItemLongClickListener() {

                    public boolean onItemLongClick(AdapterView adapterview1, View view2, int j, long l)
                    {
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("onItemLongClick() -->> position:")).append(j).toString());
                        android.widget.Adapter adapter = adapterview1.getAdapter();
                        if(adapter instanceof CirculationGalleryAdapter)
                            j = ((CirculationGalleryAdapter)adapter).toActualPosition(j);
                        if(!isNoImage()) goto _L2; else goto _L1
_L1:
                        String s = positionToUrl(Integer.valueOf(j));
                        if(!loadImage(s)) goto _L4; else goto _L3
_L3:
                        boolean flag;
                        if(Log.D)
                            Log.d("Temp", "onItemLongClick() -->> loadImage and true");
                        flag = true;
_L6:
                        return flag;
_L4:
                        if(onItemLongClickListener != null)
                        {
                            if(Log.D)
                                Log.d("Temp", "onItemLongClick() -->> old long click");
                            flag = onItemLongClickListener.onItemLongClick(adapterview1, view2, j, l);
                            continue; /* Loop/switch isn't completed */
                        }
_L2:
                        flag = false;
                        if(true) goto _L6; else goto _L5
_L5:
                    }

                    final MySimpleAdapter this$0;
                    private final android.widget.AdapterView.OnItemLongClickListener val$onItemLongClickListener;

            
            {
                this$0 = MySimpleAdapter.this;
                onItemLongClickListener = onitemlongclicklistener;
                super();
            }
                }
;
            if(onItemLongClickListener != imageClickListener)
                adapterview.setOnItemLongClickListener(imageClickListener);
        }
        return view1;
    }

    public boolean isAllowNoImage()
    {
        return allowNoImage;
    }

    public boolean isNoImage()
    {
        boolean flag;
        if(isAllowNoImage() && NoImageUtils.needNoImage())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void onDestroy()
    {
        isFinishing = true;
        myActivity = null;
        imageHttpGroup = null;
        nextPageLoader = null;
        localImageCache = null;
        loadingDrawable = null;
        noDrawable = null;
    }

    public void onPause()
    {
        localImageCache.clear();
    }

    public void onResume()
    {
        notifyDataSetChanged();
    }

    public void setAllowNoImage(boolean flag)
    {
        allowNoImage = flag;
    }

    public void setNextPageLoader(NextPageLoader nextpageloader)
    {
        nextPageLoader = nextpageloader;
    }

    public void setViewImage(ImageView imageview, final String url)
    {
        imageUrl = url;
        if(myActivity != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(localImageCache.containsKey(url))
        {
            Drawable drawable = (Drawable)localImageCache.get(url);
            if(!isOnlyOneImage())
                if(drawable == interactionDrawable)
                    imageview.setOnLongClickListener(new android.view.View.OnLongClickListener() {

                        public boolean onLongClick(View view)
                        {
                            view.setLongClickable(false);
                            return loadImage(url);
                        }

                        final MySimpleAdapter this$0;
                        private final String val$url;

            
            {
                this$0 = MySimpleAdapter.this;
                url = s;
                super();
            }
                    }
);
                else
                    imageview.setLongClickable(false);
            if(drawable != null)
                imageview.setImageDrawable(drawable);
        } else
        {
            boolean flag = false;
            if(isNoImage())
                flag = true;
            loadImage(flag, imageview, url);
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static final int THUMB_TYPE_CENTER = 1;
    public static final int THUMB_TYPE_NONE;
    private boolean allowNoImage;
    private Handler handler;
    private android.widget.AdapterView.OnItemLongClickListener imageClickListener;
    private HttpGroup imageHttpGroup;
    private android.view.View.OnTouchListener imageParentTouchListener;
    private String imageUrl;
    private ExceptionDrawable interactionDrawable;
    private boolean isFinishing;
    private boolean isLongClickAndDataSetChange;
    private ExceptionDrawable loadingDrawable;
    private Map localImageCache;
    private MyActivity myActivity;
    private NextPageLoader nextPageLoader;
    private ExceptionDrawable noDrawable;
    private Map positionToUrl;
    private float thumbHeight;
    private int thumbType;
    private float thumbWidth;













}
