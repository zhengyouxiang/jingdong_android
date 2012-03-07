// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.book;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import com.jindong.lib.zxing.client.android.LocaleManager;
import java.util.List;

// Referenced classes of package com.jindong.lib.zxing.client.android.book:
//            SearchBookContentsResult, SearchBookContentsActivity

final class BrowseBookListener
    implements android.widget.AdapterView.OnItemClickListener
{

    BrowseBookListener(SearchBookContentsActivity searchbookcontentsactivity, List list)
    {
        activity = searchbookcontentsactivity;
        items = list;
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        String s = ((SearchBookContentsResult)items.get(i - 1)).getPageId();
        String s1 = SearchBookContentsResult.getQuery();
        if(activity.getISBN().startsWith("http://google.com/books?id=") && s.length() > 0)
        {
            String s2 = activity.getISBN();
            String s3 = s2.substring(1 + s2.indexOf('='));
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://books.google.")).append(LocaleManager.getBookSearchCountryTLD()).append("/books?id=").append(s3).append("&pg=").append(s).append("&vq=").append(s1).toString()));
            intent.addFlags(0x80000);
            activity.startActivity(intent);
        }
    }

    private final SearchBookContentsActivity activity;
    private final List items;
}
