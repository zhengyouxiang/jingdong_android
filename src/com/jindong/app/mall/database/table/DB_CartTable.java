// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.entity.CartTable;
import com.jindong.app.mall.utils.DBHelperUtil;
import com.jindong.app.mall.utils.MyActivity;
import java.util.ArrayList;

public class DB_CartTable
{

    public DB_CartTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE CartTable('id' INTEGER PRIMARY KEY  NOT NULL ,name TEXT,productCode LONG,buyCount 'browseTime' DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public static void delAllCart(Context context)
    {
        DBHelperUtil.getDatabase().delete("CartTable", "1=1", null);
        if(context instanceof MyActivity)
            ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void delAllCartNoListener()
    {
        DBHelperUtil.getDatabase().delete("CartTable", "1=1", null);
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void delCart(long l, Context context)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        (new ContentValues()).put("productCode", Long.valueOf(l));
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        sqlitedatabase.delete("CartTable", "productCode =?", as);
        if(context instanceof MyActivity)
            ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static ArrayList getCartList()
    {
        ArrayList arraylist;
        Cursor cursor;
        arraylist = new ArrayList();
        cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[3];
        as[0] = "productCode";
        as[1] = "name";
        as[2] = "buyCount";
        cursor = sqlitedatabase.query("CartTable", as, null, null, null, null, null);
        cursor.moveToFirst();
        if(cursor == null || cursor.getCount() == 0) goto _L2; else goto _L1
_L1:
        int i = cursor.getCount();
        int j = 0;
_L5:
        if(j < i) goto _L3; else goto _L2
_L2:
        if(cursor != null)
            cursor.close();
        DBHelperUtil.closeDatabase();
        return arraylist;
_L3:
        cursor.moveToPosition(j);
        CartTable carttable = new CartTable();
        carttable.productCode = cursor.getLong(cursor.getColumnIndex("productCode"));
        carttable.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        carttable.buyCount = cursor.getShort(cursor.getColumnIndexOrThrow("buyCount"));
        arraylist.add(carttable);
        j++;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    /**
     * @deprecated Method insertAllCart is deprecated
     */

    public static void insertAllCart(ArrayList arraylist, Context context)
    {
        com/jindong/app/mall/database/table/DB_CartTable;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        if(arraylist == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        if(i < arraylist.size()) goto _L3; else goto _L2
_L2:
        if(context instanceof MyActivity)
            ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        com/jindong/app/mall/database/table/DB_CartTable;
        JVM INSTR monitorexit ;
        return;
_L3:
        ContentValues contentvalues = new ContentValues();
        CartTable carttable = (CartTable)arraylist.get(i);
        contentvalues.put("productCode", Long.valueOf(carttable.productCode));
        contentvalues.put("name", carttable.name);
        contentvalues.put("buyCount", Integer.valueOf(carttable.buyCount));
        sqlitedatabase.insert("CartTable", null, contentvalues);
        i++;
          goto _L4
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
        Exception exception1;
        exception1;
        com/jindong/app/mall/database/table/DB_CartTable;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public static void insertCart(long l, String s, int i, Context context)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("productCode", Long.valueOf(l));
        contentvalues.put("name", s);
        contentvalues.put("buyCount", Integer.valueOf(i));
        sqlitedatabase.insert("CartTable", null, contentvalues);
        if(context instanceof MyActivity)
            ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static CartTable queryCartByProductId(long l)
    {
        CartTable carttable;
        Cursor cursor;
        carttable = null;
        cursor = null;
        CartTable carttable1;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        String as1[] = new String[3];
        as1[0] = "productCode";
        as1[1] = "name";
        as1[2] = "buyCount";
        cursor = sqlitedatabase.query("CartTable", as1, "productCode =?", as, null, null, null);
        cursor.moveToFirst();
        if(cursor == null || cursor.getCount() == 0)
            break MISSING_BLOCK_LABEL_171;
        cursor.moveToPosition(0);
        carttable1 = new CartTable();
        carttable1.productCode = cursor.getLong(cursor.getColumnIndex("productCode"));
        carttable1.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        carttable1.buyCount = cursor.getShort(cursor.getColumnIndexOrThrow("buyCount"));
        carttable = carttable1;
        if(cursor != null)
            cursor.close();
        DBHelperUtil.closeDatabase();
        return carttable;
        Exception exception;
        exception;
_L2:
        if(cursor != null)
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void updateCart(long l, String s, int i, Context context)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("productCode", Long.valueOf(l));
        contentvalues.put("name", s);
        contentvalues.put("buyCount", Integer.valueOf(i));
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        sqlitedatabase.update("CartTable", contentvalues, "productCode =?", as);
        if(context instanceof MyActivity)
            ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void upgrade(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("drop table if exists CartTable");
    }

    public static final String TB_CART_TABLE = "CartTable";
    public static final String TB_CLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_CLOUMN_EXTEND = "extendProt";
    public static final String TB_CLOUMN_PACKS_CODE = "packId";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "name";
    public static final String TB_CLOUMN_USER_NAME = "userName";
}
