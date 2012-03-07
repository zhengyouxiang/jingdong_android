// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.utils.Contants;
import java.util.ArrayList;
import java.util.Map;

public class EasyAdapter extends BaseAdapter
{
    private static class ViewHolder
    {

        Button mDel;
        RadioButton mImage;
        TextView mName;

        private ViewHolder()
        {
        }

        ViewHolder(ViewHolder viewholder)
        {
            this();
        }
    }


    public EasyAdapter(Context context1, ArrayList arraylist, int ai[])
    {
        mInflater = null;
        seleted = new int[0];
        context = context1;
        mInflater = LayoutInflater.from(context1);
        item = arraylist;
        seleted = ai;
    }

    public int getCount()
    {
        int i;
        if(item != null && !item.isEmpty())
            i = item.size();
        else
            i = 0;
        return i;
    }

    public Object getItem(int i)
    {
        return item.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        new ViewHolder(null);
        if(view != null)
            break MISSING_BLOCK_LABEL_183;
        viewholder = new ViewHolder(null);
        view = mInflater.inflate(0x7f030024, null);
        viewholder.mImage = (RadioButton)view.findViewById(0x7f0c00c4);
        viewholder.mImage.setVisibility(0);
        viewholder.mImage.setChecked(false);
        if(seleted == null || seleted.length <= 0) goto _L2; else goto _L1
_L1:
        int j = 0;
_L4:
        if(j < seleted.length) goto _L3; else goto _L2
_L2:
        viewholder.mName = (TextView)view.findViewById(0x7f0c009a);
        view.setTag(viewholder);
_L5:
        Map map = (Map)item.get(i);
        viewholder.mName.setText((CharSequence)map.get("name"));
        return view;
_L3:
        if(seleted[j] == i)
        {
            viewholder.mImage.setChecked(true);
            Contants.oldBtn = viewholder.mImage;
        }
        j++;
          goto _L4
        viewholder = (ViewHolder)view.getTag();
          goto _L5
    }

    private Context context;
    ArrayList item;
    private RelativeLayout jingLayout;
    private LayoutInflater mInflater;
    int seleted[];
}
