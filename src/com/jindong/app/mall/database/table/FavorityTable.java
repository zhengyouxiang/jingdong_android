// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.DBHelperUtil;
import java.util.Date;

public class FavorityTable
{

    public FavorityTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE favority('id' INTEGER PRIMARY KEY  NOT NULL ,productName TEXT,productCode LONG,userName TEXT,'browseTime' DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public static void delAllFavority()
    {
        DBHelperUtil.getDatabase().delete("favority", "1=1", null);
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    /**
     * @deprecated Method insertOrUpdateFavority is deprecated
     */

    public static void insertOrUpdateFavority(long l, String s, boolean flag)
    {
        com/jindong/app/mall/database/table/FavorityTable;
        JVM INSTR monitorenter ;
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        String as[];
        sqlitedatabase = DBHelperUtil.getDatabase();
        contentvalues = new ContentValues();
        as = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        contentvalues.put("userName", LoginUser.getLoginUserName());
        if(!flag) goto _L2; else goto _L1
_L1:
        sqlitedatabase.delete("favority", "productCode =?", as);
        if(true)
            break MISSING_BLOCK_LABEL_80;
        null.close();
        DBHelperUtil.closeDatabase();
_L3:
        com/jindong/app/mall/database/table/FavorityTable;
        JVM INSTR monitorexit ;
        return;
_L2:
        cursor = sqlitedatabase.query("favority", null, "productCode =?", as, null, null, null);
        if(cursor == null || cursor.getCount() == 0)
            break MISSING_BLOCK_LABEL_179;
        contentvalues.put("browseTime", (new Date(System.currentTimeMillis())).toLocaleString());
        sqlitedatabase.update("favority", contentvalues, "productCode =?", as);
_L4:
        if(cursor == null)
            break MISSING_BLOCK_LABEL_165;
        cursor.close();
        DBHelperUtil.closeDatabase();
          goto _L3
        Exception exception1;
        exception1;
        throw exception1;
        contentvalues.put("productCode", Long.valueOf(l));
        contentvalues.put("productName", s);
        sqlitedatabase.insert("favority", null, contentvalues);
          goto _L4
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        if(cursor == null)
            break MISSING_BLOCK_LABEL_231;
        cursor.close();
        DBHelperUtil.closeDatabase();
          goto _L3
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static boolean queryIsFavorited(long l)
    {
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[2];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        as[1] = LoginUser.getLoginUserName();
        cursor = sqlitedatabase.query("favority", null, "productCode =? and userName =?", as, null, null, null);
        if(cursor == null) goto _L2; else goto _L1
_L1:
        int i = cursor.getCount();
        if(i == 0) goto _L2; else goto _L3
_L3:
        boolean flag;
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        flag = true;
_L5:
        return flag;
_L2:
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void upgrade(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("drop table if exists favority");
    }

    public static final String TB_CLOUMN_BROWSE_TIME = "browseTime";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "productName";
    public static final String TB_CLOUMN_USER_NAME = "userName";
    public static final String TB_FAVORITY_TABLE = "favority";
}
