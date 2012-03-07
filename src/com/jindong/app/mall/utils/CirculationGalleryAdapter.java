// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

// Referenced classes of package com.jindong.app.mall.utils:
//            MySimpleAdapter, MyActivity

public class CirculationGalleryAdapter extends MySimpleAdapter
{

    public CirculationGalleryAdapter(MyActivity myactivity, List list, int i, String as[], int ai[])
    {
        super(myactivity, list, i, as, ai);
        beanList = list;
        myactivity.obtainStyledAttributes(com.jindong.app.mall.R.styleable.gallery_bg).recycle();
    }

    public int getActualCount()
    {
        int i;
        if(beanList == null)
            i = 0;
        else
            i = beanList.size();
        return i;
    }

    public int getCount()
    {
        int i;
        if(getActualCount() == 0)
            i = 0;
        else
            i = 0x7fffffff;
        return i;
    }

    public Object getItem(int i)
    {
        Object obj;
        if(beanList == null || beanList.size() < 1)
            obj = null;
        else
            obj = beanList.get(toActualPosition(i));
        return obj;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return super.getView(toActualPosition(i), view, viewgroup);
    }

    public int toActualPosition(int i)
    {
        return i % getActualCount();
    }

    private List beanList;
}
