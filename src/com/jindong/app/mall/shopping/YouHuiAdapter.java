// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;

public class YouHuiAdapter extends BaseAdapter
{
    private static class ViewHolder
    {

        TextView mName;

        private ViewHolder()
        {
        }

        ViewHolder(ViewHolder viewholder)
        {
            this();
        }
    }


    public YouHuiAdapter(Context context1, ArrayList arraylist)
    {
        mInflater = null;
        context = context1;
        mInflater = LayoutInflater.from(context1);
        mYouHui = arraylist;
    }

    public int getCount()
    {
        int i;
        if(mYouHui != null && mYouHui.size() > 0)
            i = mYouHui.size();
        else
            i = 0;
        return i;
    }

    public Object getItem(int i)
    {
        return mYouHui.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        new ViewHolder(null);
        ViewHolder viewholder;
        String s;
        if(view == null)
        {
            viewholder = new ViewHolder(null);
            view = mInflater.inflate(0x7f03008b, null);
            viewholder.mName = (TextView)view.findViewById(0x7f0c02d7);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        s = (String)((Map)mYouHui.get(i)).get("name");
        if(((Map)mYouHui.get(i)).get("name") == " " || s == "" || s == " ")
        {
            viewholder.mName.setVisibility(8);
            ((TextView)view.findViewById(0x7f0c02d6)).setVisibility(0);
        } else
        {
            viewholder.mName.setVisibility(0);
            ((TextView)view.findViewById(0x7f0c02d6)).setVisibility(8);
        }
        viewholder.mName.setText(s);
        return view;
    }

    private Context context;
    private LayoutInflater mInflater;
    ArrayList mYouHui;
}
