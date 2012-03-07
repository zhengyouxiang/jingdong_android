// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.book;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package com.jindong.lib.zxing.client.android.book:
//            SearchBookContentsResult

public final class SearchBookContentsListItem extends LinearLayout
{

    SearchBookContentsListItem(Context context)
    {
        super(context);
    }

    public SearchBookContentsListItem(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        pageNumberView = (TextView)findViewById(0x7f0c029d);
        snippetView = (TextView)findViewById(0x7f0c029e);
    }

    public void set(SearchBookContentsResult searchbookcontentsresult)
    {
        String s;
        pageNumberView.setText(searchbookcontentsresult.getPageNumber());
        s = searchbookcontentsresult.getSnippet();
        if(s.length() <= 0)
            break MISSING_BLOCK_LABEL_135;
        if(!searchbookcontentsresult.getValidSnippet()) goto _L2; else goto _L1
_L1:
        String s1;
        String s2;
        SpannableString spannablestring;
        StyleSpan stylespan;
        int i;
        int j;
        s1 = SearchBookContentsResult.getQuery().toLowerCase();
        s2 = s.toLowerCase();
        spannablestring = new SpannableString(s);
        stylespan = new StyleSpan(1);
        i = s1.length();
        j = 0;
_L5:
        int k = s2.indexOf(s1, j);
        if(k >= 0) goto _L4; else goto _L3
_L3:
        snippetView.setText(spannablestring);
_L6:
        return;
_L4:
        spannablestring.setSpan(stylespan, k, k + i, 0);
        j = k + i;
          goto _L5
_L2:
        snippetView.setText(s);
          goto _L6
        snippetView.setText("");
          goto _L6
    }

    private TextView pageNumberView;
    private TextView snippetView;
}
