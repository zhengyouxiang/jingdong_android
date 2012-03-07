// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            BeanUtil

public class SimpleBeanAdapter extends BaseAdapter
    implements Filterable
{
    private class SimpleFilter extends Filter
    {

        protected android.widget.Filter.FilterResults performFiltering(CharSequence charsequence)
        {
            android.widget.Filter.FilterResults filterresults;
            filterresults = new android.widget.Filter.FilterResults();
            if(mUnfilteredData == null)
                mUnfilteredData = mData;
            if(charsequence != null && charsequence.length() != 0) goto _L2; else goto _L1
_L1:
            List list = mUnfilteredData;
            filterresults.values = list;
            filterresults.count = list.size();
_L4:
            return filterresults;
_L2:
            String s;
            List list1;
            int i;
            LinkedList linkedlist;
            int j;
            s = charsequence.toString().toLowerCase();
            list1 = mUnfilteredData;
            i = list1.size();
            linkedlist = new LinkedList();
            j = 0;
_L8:
label0:
            {
                if(j < i)
                    break label0;
                filterresults.values = linkedlist;
                filterresults.count = linkedlist.size();
            }
            if(true) goto _L4; else goto _L3
_L3:
            Object obj = list1.get(j);
            if(obj == null) goto _L6; else goto _L5
_L5:
            int k;
            int l;
            k = mTo.length;
            l = 0;
_L9:
            if(l < k) goto _L7; else goto _L6
_L6:
            j++;
              goto _L8
_L7:
            String as[];
            int i1;
            int j1;
            as = ((String)BeanUtil.getValue(obj, mFrom[l])).split(" ");
            i1 = as.length;
            j1 = 0;
_L10:
            if(j1 < i1)
            {
label1:
                {
                    if(!as[j1].toLowerCase().startsWith(s))
                        break label1;
                    linkedlist.add(obj);
                }
            }
            l++;
              goto _L9
            j1++;
              goto _L10
        }

        protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            mData = (List)filterresults.values;
            if(filterresults.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        final SimpleBeanAdapter this$0;

        private SimpleFilter()
        {
            this$0 = SimpleBeanAdapter.this;
            super();
        }

        SimpleFilter(SimpleFilter simplefilter)
        {
            this();
        }
    }

    public static interface ViewBinder
    {

        public abstract boolean setViewValue(View view, Object obj, String s);
    }


    public SimpleBeanAdapter(Context context, List list, int i, String as[], int ai[])
    {
        viewList = new ArrayList();
        mData = list;
        mDropDownResource = i;
        mResource = i;
        mFrom = as;
        mTo = ai;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    private void bindView(int i, View view)
    {
        Object obj = mData.get(i);
        if(obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ViewBinder viewbinder;
        View aview[];
        String as[];
        int j;
        int k;
        viewbinder = mViewBinder;
        aview = (View[])mHolders.get(view);
        as = mFrom;
        j = mTo.length;
        k = 0;
_L9:
        if(k >= j) goto _L1; else goto _L3
_L3:
        View view1 = aview[k];
        if(view1 == null) goto _L5; else goto _L4
_L4:
        Object obj1;
        String s;
        obj1 = BeanUtil.getValue(obj, as[k]);
        boolean flag;
        if(obj1 == null)
            s = "";
        else
            s = obj1.toString();
        if(s == null)
            s = "";
        flag = false;
        if(viewbinder != null)
            flag = viewbinder.setViewValue(view1, obj1, s);
        if(flag) goto _L5; else goto _L6
_L6:
        if(!(view1 instanceof Checkable))
            break MISSING_BLOCK_LABEL_219;
        if(!(obj1 instanceof Boolean)) goto _L8; else goto _L7
_L7:
        ((Checkable)view1).setChecked(((Boolean)obj1).booleanValue());
_L5:
        k++;
          goto _L9
_L8:
        throw new IllegalStateException((new StringBuilder(String.valueOf(view1.getClass().getName()))).append(" should be bound to a Boolean, not a ").append(obj1.getClass()).toString());
        if(view1 instanceof TextView)
            setViewText((TextView)view1, s);
        else
        if(view1 instanceof ImageView)
        {
            if(obj1 instanceof Integer)
                setViewImage((ImageView)view1, ((Integer)obj1).intValue());
            else
                setViewImage((ImageView)view1, s);
        } else
        {
            throw new IllegalStateException((new StringBuilder(String.valueOf(view1.getClass().getName()))).append(" is not a ").append(" view that can be bounds by this SimpleAdapter").toString());
        }
          goto _L5
    }

    private View createViewFromResource(int i, View view, ViewGroup viewgroup, int j)
    {
        View view1;
        int ai[];
        int k;
        View aview[];
        int l;
        if(view != null)
            break MISSING_BLOCK_LABEL_111;
        view1 = mInflater.inflate(j, viewgroup, false);
        if(view1 instanceof ImageView)
            isOnlyOneImage = true;
        ai = mTo;
        k = ai.length;
        aview = new View[k];
        l = 0;
_L3:
        if(l < k) goto _L2; else goto _L1
_L1:
        mHolders.put(view1, aview);
        viewList.add(view1);
_L4:
        bindView(i, view1);
        return view1;
_L2:
        aview[l] = view1.findViewById(ai[l]);
        l++;
          goto _L3
        view1 = view;
          goto _L4
    }

    public int getCount()
    {
        return mData.size();
    }

    public View getDropDownView(int i, View view, ViewGroup viewgroup)
    {
        return createViewFromResource(i, view, viewgroup, mDropDownResource);
    }

    public Filter getFilter()
    {
        if(mFilter == null)
            mFilter = new SimpleFilter(null);
        return mFilter;
    }

    public Object getItem(int i)
    {
        return mData.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return createViewFromResource(i, view, viewgroup, mResource);
    }

    public ViewBinder getViewBinder()
    {
        return mViewBinder;
    }

    public boolean isOnlyOneImage()
    {
        return isOnlyOneImage;
    }

    public void setData(List list)
    {
        mData = list;
    }

    public void setDropDownViewResource(int i)
    {
        mDropDownResource = i;
    }

    public void setViewBinder(ViewBinder viewbinder)
    {
        mViewBinder = viewbinder;
    }

    public void setViewImage(ImageView imageview, int i)
    {
        imageview.setImageResource(i);
    }

    public void setViewImage(ImageView imageview, String s)
    {
        imageview.setImageResource(Integer.parseInt(s));
_L1:
        return;
        NumberFormatException numberformatexception;
        numberformatexception;
        imageview.setImageURI(Uri.parse(s));
          goto _L1
    }

    public void setViewText(TextView textview, String s)
    {
        textview.setText(s);
    }

    private boolean isOnlyOneImage;
    private List mData;
    private int mDropDownResource;
    private SimpleFilter mFilter;
    private String mFrom[];
    private final WeakHashMap mHolders = new WeakHashMap();
    private LayoutInflater mInflater;
    private int mResource;
    private int mTo[];
    private List mUnfilteredData;
    private ViewBinder mViewBinder;
    private ArrayList viewList;






}
