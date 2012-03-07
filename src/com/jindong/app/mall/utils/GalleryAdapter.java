// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.res.TypedArray;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.cache.ImageCache;
import java.lang.ref.SoftReference;
import java.util.List;

// Referenced classes of package com.jindong.app.mall.utils:
//            MyActivity, Log, HttpGroup

public class GalleryAdapter extends BaseAdapter
{

    public GalleryAdapter(MyActivity myactivity, List list, int i)
    {
        sr_d = null;
        mResource = i;
        myActivity = myactivity;
        mInflater = LayoutInflater.from(myactivity);
        beanList = list;
        int j;
        if(list == null)
            j = 0;
        else
            j = list.size();
        actuallAcount = j;
        loadingDrawable = new MySimpleAdapter.ExceptionDrawable(myactivity, myactivity.getString(0x7f09007c));
        noDrawable = new MySimpleAdapter.ExceptionDrawable(myactivity, myactivity.getString(0x7f09007d));
        myactivity.obtainStyledAttributes(com.jindong.app.mall.R.styleable.gallery_bg).recycle();
    }

    public int getActualCount()
    {
        return actuallAcount;
    }

    public int getCount()
    {
        int i;
        if(actuallAcount == 0)
            i = 0;
        else
            i = 0x7fffffff;
        return i;
    }

    public Product getItem(int i)
    {
        return (Product)beanList.get(i % actuallAcount);
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public long getItemId(int i)
    {
        return ((Product)beanList.get(i % actuallAcount)).getId().longValue();
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        View view1;
        final String url;
        final ImageView mImageView;
        if(view == null)
            view1 = mInflater.inflate(mResource, viewgroup, false);
        else
            view1 = view;
        if(Log.I)
            Log.i("position", (new StringBuilder()).append(i).toString());
        product = getItem(i % getActualCount());
        url = product.getImageUrl();
        mImageView = (ImageView)view1.findViewById(0x7f0c0110);
        if(!ImageCache.containsKey(url))
        {
            ImageCache.put(url, loadingDrawable);
            if(imageHttpGroup == null)
                imageHttpGroup = myActivity.getHttpGroupaAsynPool(5000);
            imageHttpGroup.add(url, null, new HttpGroup.CustomOnAllListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    if(Log.D)
                        Log.d("Adapter", (new StringBuilder("put in -->> ")).append(url).toString());
                    if(httpresponse != null && httpresponse.getDrawable() != null)
                        ImageCache.put(url, httpresponse.getDrawable());
                    else
                        ImageCache.put(url, noDrawable);
                    myActivity.post(new Runnable() {

                        public void run()
                        {
                            mImageView.setImageDrawable(ImageCache.get(url));
                            mImageView.destroyDrawingCache();
                            mImageView.invalidate();
                        }

                        final _cls1 this$1;
                        private final ImageView val$mImageView;
                        private final String val$url;

                    
                    {
                        this$1 = _cls1.this;
                        mImageView = imageview;
                        url = s;
                        super();
                    }
                    }
);
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                    ImageCache.put(url, noDrawable);
                    myActivity.post(new Runnable() {

                        public void run()
                        {
                            mImageView.setImageDrawable(ImageCache.get(url));
                            mImageView.destroyDrawingCache();
                            mImageView.invalidate();
                        }

                        final _cls1 this$1;
                        private final ImageView val$mImageView;
                        private final String val$url;

                    
                    {
                        this$1 = _cls1.this;
                        mImageView = imageview;
                        url = s;
                        super();
                    }
                    }
);
                }

                public void onProgress(int j, int k)
                {
                }

                public void onStart()
                {
                }

                final GalleryAdapter this$0;
                private final ImageView val$mImageView;
                private final String val$url;

            
            {
                this$0 = GalleryAdapter.this;
                url = s;
                mImageView = imageview;
                super();
            }
            }
);
        }
        mImageView.setImageDrawable(ImageCache.get(url));
        return view1;
    }

    private final int actuallAcount;
    private List beanList;
    private HttpGroup imageHttpGroup;
    private MySimpleAdapter.ExceptionDrawable loadingDrawable;
    private LayoutInflater mInflater;
    private int mResource;
    private MyActivity myActivity;
    private MySimpleAdapter.ExceptionDrawable noDrawable;
    private Product product;
    SoftReference sr_d;


}
