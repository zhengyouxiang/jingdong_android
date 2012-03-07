// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log

public class SimpleJsonAdapter extends BaseAdapter
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
            JSONArray jsonarray = mUnfilteredData;
            filterresults.values = jsonarray;
            filterresults.count = jsonarray.length();
_L4:
            return filterresults;
_L2:
            String s;
            JSONArray jsonarray1;
            int i;
            JSONArray jsonarray2;
            int j;
            s = charsequence.toString().toLowerCase();
            jsonarray1 = mUnfilteredData;
            i = jsonarray1.length();
            jsonarray2 = new JSONArray();
            j = 0;
_L8:
label0:
            {
                if(j < i)
                    break label0;
                filterresults.values = jsonarray2;
                filterresults.count = jsonarray2.length();
            }
            if(true) goto _L4; else goto _L3
_L3:
            JSONObject jsonobject;
            int k;
            int l;
            try
            {
                jsonobject = jsonarray1.getJSONObject(j);
            }
            catch(JSONException jsonexception)
            {
                RuntimeException runtimeexception = new RuntimeException(jsonexception);
                throw runtimeexception;
            }
            if(jsonobject == null) goto _L6; else goto _L5
_L5:
            k = mTo.length;
            l = 0;
_L9:
            if(l < k) goto _L7; else goto _L6
_L6:
            j++;
              goto _L8
_L7:
            String as[];
            int j1;
            String s1;
            int i1;
            try
            {
                s1 = jsonobject.getString(mFrom[l]);
            }
            catch(JSONException jsonexception1)
            {
                RuntimeException runtimeexception1 = new RuntimeException(jsonexception1);
                throw runtimeexception1;
            }
            as = s1.split(" ");
            i1 = as.length;
            j1 = 0;
_L10:
            if(j1 < i1)
            {
label1:
                {
                    if(!as[j1].toLowerCase().startsWith(s))
                        break label1;
                    jsonarray2.put(jsonobject);
                }
            }
            l++;
              goto _L9
            j1++;
              goto _L10
        }

        protected void publishResults(CharSequence charsequence, android.widget.Filter.FilterResults filterresults)
        {
            mData = (JSONArray)filterresults.values;
            if(filterresults.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        final SimpleJsonAdapter this$0;

        private SimpleFilter()
        {
            this$0 = SimpleJsonAdapter.this;
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


    public SimpleJsonAdapter(Context context, JSONArray jsonarray, int i, String as[], int ai[])
    {
        viewList = new ArrayList();
        mData = jsonarray;
        mDropDownResource = i;
        mResource = i;
        mFrom = as;
        mTo = ai;
        mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    private void bindView(int i, View view)
    {
        JSONObject jsonobject;
        try
        {
            jsonobject = mData.getJSONObject(i);
        }
        catch(JSONException jsonexception)
        {
            throw new RuntimeException(jsonexception);
        }
        if(jsonobject != null) goto _L2; else goto _L1
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
        Object obj;
        String s;
        boolean flag;
        try
        {
            obj = jsonobject.get(as[k]);
        }
        catch(JSONException jsonexception1)
        {
            throw new RuntimeException(jsonexception1);
        }
        if(obj == null)
            s = "";
        else
            s = obj.toString();
        if(s == null)
            s = "";
        flag = false;
        if(viewbinder != null)
            flag = viewbinder.setViewValue(view1, obj, s);
        if(flag) goto _L5; else goto _L6
_L6:
        if(!(view1 instanceof Checkable))
            break MISSING_BLOCK_LABEL_242;
        if(!(obj instanceof Boolean)) goto _L8; else goto _L7
_L7:
        ((Checkable)view1).setChecked(((Boolean)obj).booleanValue());
_L5:
        k++;
          goto _L9
_L8:
        throw new IllegalStateException((new StringBuilder(String.valueOf(view1.getClass().getName()))).append(" should be bound to a Boolean, not a ").append(obj.getClass()).toString());
        if(view1 instanceof TextView)
            setViewText((TextView)view1, s);
        else
        if(view1 instanceof ImageView)
        {
            if(obj instanceof Integer)
                setViewImage((ImageView)view1, ((Integer)obj).intValue());
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
            break MISSING_BLOCK_LABEL_98;
        view1 = mInflater.inflate(j, viewgroup, false);
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
        return mData.length();
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
        Object obj;
        try
        {
            obj = mData.get(i);
        }
        catch(JSONException jsonexception)
        {
            throw new RuntimeException(jsonexception);
        }
        return obj;
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i)
    {
        if(Log.D)
            Log.d("size", (new StringBuilder()).append(viewList.size()).toString());
        return null;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        return createViewFromResource(i, view, viewgroup, mResource);
    }

    public ViewBinder getViewBinder()
    {
        return mViewBinder;
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

    private JSONArray mData;
    private int mDropDownResource;
    private SimpleFilter mFilter;
    private String mFrom[];
    private final WeakHashMap mHolders = new WeakHashMap();
    private LayoutInflater mInflater;
    private int mResource;
    private int mTo[];
    private JSONArray mUnfilteredData;
    private ViewBinder mViewBinder;
    private ArrayList viewList;






}
