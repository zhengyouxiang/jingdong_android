// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.book;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import java.util.List;

// Referenced classes of package com.jindong.lib.zxing.client.android.book:
//            SearchBookContentsListItem, SearchBookContentsResult

final class SearchBookContentsAdapter extends ArrayAdapter
{

    SearchBookContentsAdapter(Context context, List list)
    {
        super(context, 0x7f030080, 0, list);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        Object obj;
        SearchBookContentsListItem searchbookcontentslistitem;
        if(view == null)
        {
            searchbookcontentslistitem = (SearchBookContentsListItem)LayoutInflater.from(getContext()).inflate(0x7f030080, viewgroup, false);
        } else
        {
label0:
            {
                if(!(view instanceof SearchBookContentsListItem))
                    break label0;
                searchbookcontentslistitem = (SearchBookContentsListItem)view;
            }
        }
        searchbookcontentslistitem.set((SearchBookContentsResult)getItem(i));
        obj = searchbookcontentslistitem;
_L2:
        return ((View) (obj));
        obj = view;
        if(true) goto _L2; else goto _L1
_L1:
    }
}
