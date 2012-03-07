// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.MySimpleAdapter;

public class LinearLayoutForListView extends LinearLayout
{

    public LinearLayoutForListView(Context context)
    {
        super(context);
        onClickListener = null;
    }

    public LinearLayoutForListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        onClickListener = null;
    }

    public void bindLinearLayout()
    {
        int i = adapter.getCount();
        int j = 0;
        do
        {
            if(j >= i)
            {
                if(Log.V)
                    Log.v("countTAG", (new StringBuilder()).append(i).toString());
                return;
            }
            View view = adapter.getView(j, null, null);
            view.setOnClickListener(onClickListener);
            if(j == i - 1)
                ((LinearLayout)view).removeViewAt(2);
            addView(view, j);
            j++;
        } while(true);
    }

    public MySimpleAdapter getAdpater()
    {
        return adapter;
    }

    public android.view.View.OnClickListener getOnclickListner()
    {
        return onClickListener;
    }

    public void setAdapter(MySimpleAdapter mysimpleadapter)
    {
        adapter = mysimpleadapter;
        bindLinearLayout();
    }

    public void setOnclickLinstener(android.view.View.OnClickListener onclicklistener)
    {
        onClickListener = onclicklistener;
    }

    private MySimpleAdapter adapter;
    private android.view.View.OnClickListener onClickListener;
}
