// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.DBHelperUtil;
import java.util.ArrayList;

public class HistoryTable
{

    public HistoryTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE BrowseHistoryTable('id' INTEGER PRIMARY KEY  NOT NULL ,name TEXT,productCode LONG,userName TEXT,browseTime DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public static void delAllHistory()
    {
        DBHelperUtil.getDatabase().delete("BrowseHistoryTable", "1=1", null);
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static ArrayList getHistoryByPage(int i, int j)
    {
        int k;
        Cursor cursor;
        ArrayList arraylist;
        k = 0;
        cursor = null;
        arraylist = new ArrayList();
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = DBHelperUtil.getDatabase();
        as = new String[2];
        int l;
        int i1;
        if(i != 1)
            k = i * j - 1;
        as[0] = String.valueOf(k);
        as[1] = String.valueOf(j);
        cursor = sqlitedatabase.rawQuery("SELECT id,name,productCode FROM BrowseHistoryTable ORDER BY browseTime desc LIMIT ?,? ", as);
        cursor.moveToFirst();
        if(cursor == null || cursor.getCount() == 0) goto _L2; else goto _L1
_L1:
        l = cursor.getCount();
        i1 = 0;
_L7:
        if(i1 < l) goto _L3; else goto _L2
_L2:
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
_L5:
        return arraylist;
_L3:
        cursor.moveToPosition(i1);
        Product product = new Product();
        product.setId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("productCode"))));
        product.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        arraylist.add(product);
        i1++;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
        if(true) goto _L7; else goto _L6
_L6:
    }

    /**
     * @deprecated Method insertOrUpdateBrowseHistory is deprecated
     */

    public static void insertOrUpdateBrowseHistory(long l, String s)
    {
        com/jindong/app/mall/database/table/HistoryTable;
        JVM INSTR monitorenter ;
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        ContentValues contentvalues = new ContentValues();
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        cursor = sqlitedatabase.query("BrowseHistoryTable", null, "productCode =?", as, null, null, null);
        if(cursor != null && cursor.getCount() != 0)
            sqlitedatabase.delete("BrowseHistoryTable", "productCode =?", as);
        contentvalues.put("productCode", Long.valueOf(l));
        contentvalues.put("name", s);
        sqlitedatabase.insert("BrowseHistoryTable", null, contentvalues);
        if(cursor == null)
            break MISSING_BLOCK_LABEL_133;
        if(!cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        com/jindong/app/mall/database/table/HistoryTable;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        if(cursor == null)
            break MISSING_BLOCK_LABEL_161;
        if(!cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
        Exception exception1;
        exception1;
        com/jindong/app/mall/database/table/HistoryTable;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public static void upgrade(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("drop table if exists BrowseHistoryTable");
    }

    public static final String TB_CLOUMN_BROWSE_TIME = "browseTime";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "name";
    public static final String TB_CLOUMN_USER_NAME = "userName";
    public static final String TB_HISTORY_TABLE = "BrowseHistoryTable";
}
