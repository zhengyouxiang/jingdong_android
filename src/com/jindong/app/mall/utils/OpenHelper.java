// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jindong.app.mall.database.table.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log

class OpenHelper extends SQLiteOpenHelper
{

    public OpenHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        HistoryTable.create(sqlitedatabase);
        DB_CartTable.create(sqlitedatabase);
        DB_PacksTable.create(sqlitedatabase);
        FavorityTable.create(sqlitedatabase);
        CommAddrTable.create(sqlitedatabase);
        ScanCodeTable.create(sqlitedatabase);
        CacheFileTable.create(sqlitedatabase);
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        if(i < j)
        {
            if(Log.I)
                Log.i("onUpgrade", (new StringBuilder("++++++++++oldVersion:")).append(i).append("newVersion:").append(j).toString());
            CacheFileTable.upgrade(sqlitedatabase, i, j);
            HistoryTable.upgrade(sqlitedatabase);
            DB_CartTable.upgrade(sqlitedatabase);
            DB_PacksTable.upgrade(sqlitedatabase);
            FavorityTable.upgrade(sqlitedatabase);
            CommAddrTable.upgrade(sqlitedatabase);
            ScanCodeTable.upgrade(sqlitedatabase);
            onCreate(sqlitedatabase);
        }
    }
}
