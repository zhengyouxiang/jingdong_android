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

public class JingAdapter extends BaseAdapter
{
    private class ClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            if(rb.isShown())
            {
                rb.setVisibility(4);
                rb.setChecked(false);
            } else
            {
                rb.setVisibility(0);
                rb.setChecked(true);
            }
        }

        int index;
        RadioButton rb;
        final JingAdapter this$0;

        public ClickListener(RadioButton radiobutton, int i)
        {
            this$0 = JingAdapter.this;
            super();
            rb = radiobutton;
            index = i;
        }
    }

    private static class ViewHolder
    {

        CheckBox bCheck;
        TextView mEndTime;
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


    public JingAdapter(Context context1, ArrayList arraylist, int ai[])
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
            break MISSING_BLOCK_LABEL_328;
        viewholder = new ViewHolder(null);
        view = mInflater.inflate(0x7f03003e, null);
        if(Contants.noJing) goto _L2; else goto _L1
_L1:
        viewholder.bCheck = (CheckBox)view.findViewById(0x7f0c013c);
        viewholder.bCheck.setChecked(false);
        viewholder.bCheck.setVisibility(0);
        if(seleted == null || seleted.length <= 0) goto _L4; else goto _L3
_L3:
        int j = 0;
_L6:
        if(j < seleted.length) goto _L5; else goto _L4
_L4:
        TextView textview = (TextView)view.findViewById(0x7f0c013b);
        textview.setVisibility(0);
        if(Contants.bNoYouHui)
        {
            textview.setVisibility(8);
            viewholder.bCheck = (CheckBox)view.findViewById(0x7f0c013c);
            viewholder.bCheck.setChecked(false);
            viewholder.bCheck.setVisibility(8);
        }
_L7:
        viewholder.mName = (TextView)view.findViewById(0x7f0c0139);
        viewholder.mEndTime = (TextView)view.findViewById(0x7f0c013a);
        view.setTag(viewholder);
_L8:
        Map map = (Map)item.get(i);
        viewholder.mName.setText((CharSequence)map.get("name"));
        viewholder.mEndTime.setText((CharSequence)map.get("time"));
        return view;
_L5:
        if(seleted[j] == i)
            viewholder.bCheck.setChecked(true);
        j++;
          goto _L6
_L2:
        ((TextView)view.findViewById(0x7f0c013b)).setVisibility(8);
        viewholder.bCheck = (CheckBox)view.findViewById(0x7f0c013c);
        viewholder.bCheck.setChecked(false);
        viewholder.bCheck.setVisibility(8);
          goto _L7
        viewholder = (ViewHolder)view.getTag();
          goto _L8
    }

    private Context context;
    ArrayList item;
    private RelativeLayout jingLayout;
    private LayoutInflater mInflater;
    int seleted[];
}
