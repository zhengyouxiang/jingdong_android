// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.book;


final class SearchBookContentsResult
{

    SearchBookContentsResult(String s, String s1, String s2, boolean flag)
    {
        pageId = s;
        pageNumber = s1;
        snippet = s2;
        validSnippet = flag;
    }

    public static String getQuery()
    {
        return query;
    }

    public static void setQuery(String s)
    {
        query = s;
    }

    public String getPageId()
    {
        return pageId;
    }

    public String getPageNumber()
    {
        return pageNumber;
    }

    public String getSnippet()
    {
        return snippet;
    }

    public boolean getValidSnippet()
    {
        return validSnippet;
    }

    private static String query;
    private final String pageId;
    private final String pageNumber;
    private final String snippet;
    private final boolean validSnippet;
}
