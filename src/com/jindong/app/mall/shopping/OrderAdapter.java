// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import com.jindong.app.mall.entity.CommAddr;
import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter
{
    static class ViewHolder
    {

        RadioButton mRadio;

        ViewHolder()
        {
        }
    }


    public OrderAdapter(Context context, ArrayList arraylist)
    {
        mContext = null;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        addrList = arraylist;
    }

    public int getCount()
    {
        return addrList.size();
    }

    public Object getItem(int i)
    {
        return addrList.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        LayoutInflater layoutinflater = LayoutInflater.from(mContext);
        ViewHolder viewholder;
        if(view == null)
        {
            view = layoutinflater.inflate(0x7f030048, null);
            viewholder = new ViewHolder();
            viewholder.mRadio = (RadioButton)view.findViewById(0x7f0c016e);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        viewholder.mRadio.setText(((CommAddr)addrList.get(i)).sComUsedAddr);
        viewholder.mRadio.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
            }

            final OrderAdapter this$0;

            
            {
                this$0 = OrderAdapter.this;
                super();
            }
        }
);
        return view;
    }

    ArrayList addrList;
    private Context mContext;
    private LayoutInflater mInflater;
}
