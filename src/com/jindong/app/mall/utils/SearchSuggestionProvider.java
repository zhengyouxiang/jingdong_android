// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.SearchRecentSuggestionsProvider;

public class SearchSuggestionProvider extends SearchRecentSuggestionsProvider
{

    public SearchSuggestionProvider()
    {
        setupSuggestions("com.jd.app.trade.utils.SearchSuggestionProvider", 1);
    }

    static final String AUTHORITY = "com.jd.app.trade.utils.SearchSuggestionProvider";
    static final int MODE = 1;
}
