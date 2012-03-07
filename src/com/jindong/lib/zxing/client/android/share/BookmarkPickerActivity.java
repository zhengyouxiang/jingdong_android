// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.share;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public final class BookmarkPickerActivity extends ListActivity
{

    public BookmarkPickerActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        cursor = getContentResolver().query(Browser.BOOKMARKS_URI, BOOKMARK_PROJECTION, "bookmark = 1", null, null);
        startManagingCursor(cursor);
        setListAdapter(new SimpleCursorAdapter(this, 0x7f03000d, cursor, BOOKMARK_PROJECTION, TWO_LINE_VIEW_IDS));
    }

    protected void onListItemClick(ListView listview, View view, int i, long l)
    {
        if(cursor.moveToPosition(i))
        {
            Intent intent = new Intent();
            intent.addFlags(0x80000);
            intent.putExtra("title", cursor.getString(0));
            intent.putExtra("url", cursor.getString(1));
            setResult(-1, intent);
        } else
        {
            setResult(0);
        }
        finish();
    }

    private static final String BOOKMARK_PROJECTION[];
    private static final String BOOKMARK_SELECTION = "bookmark = 1";
    private static final int TITLE_COLUMN = 0;
    private static final int TWO_LINE_VIEW_IDS[];
    private static final int URL_COLUMN = 1;
    private Cursor cursor;

    static 
    {
        String as[] = new String[2];
        as[0] = "title";
        as[1] = "url";
        BOOKMARK_PROJECTION = as;
        int ai[] = new int[2];
        ai[0] = 0x7f0c0036;
        ai[1] = 0x7f0c0037;
        TWO_LINE_VIEW_IDS = ai;
    }
}
