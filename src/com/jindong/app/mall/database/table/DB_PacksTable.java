// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.entity.PacksTable;
import com.jindong.app.mall.utils.DBHelperUtil;
import com.jindong.app.mall.utils.MyActivity;
import java.util.ArrayList;

public class DB_PacksTable
{

    public DB_PacksTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE PacksTable('id' INTEGER PRIMARY KEY  NOT NULL ,packId LONG,name TEXT,buyCount INTEGER,childCount INTEGER, 'browseTime' DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public static void delAllPacksCart(Context context)
    {
        DBHelperUtil.getDatabase().delete("PacksTable", "1=1", null);
        ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
_L2:
        return;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        DBHelperUtil.closeDatabase();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void delAllPacksCartNoListener()
    {
        DBHelperUtil.getDatabase().delete("PacksTable", "1=1", null);
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void delPacksCart(long l, Context context)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        (new ContentValues()).put("packId", Long.valueOf(l));
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        sqlitedatabase.delete("PacksTable", "packId =?", as);
        ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static ArrayList getPacksList()
    {
        ArrayList arraylist;
        Cursor cursor;
        arraylist = new ArrayList();
        cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[4];
        as[0] = "packId";
        as[1] = "name";
        as[2] = "buyCount";
        as[3] = "childCount";
        cursor = sqlitedatabase.query("PacksTable", as, null, null, null, null, null);
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
        PacksTable packstable = new PacksTable();
        packstable.packId = cursor.getLong(cursor.getColumnIndex("packId"));
        packstable.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        packstable.buyCount = cursor.getShort(cursor.getColumnIndexOrThrow("buyCount"));
        packstable.childCount = cursor.getInt(cursor.getColumnIndex("childCount"));
        arraylist.add(packstable);
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
     * @deprecated Method insertAllPacksCart is deprecated
     */

    public static void insertAllPacksCart(ArrayList arraylist, Context context)
    {
        com/jindong/app/mall/database/table/DB_PacksTable;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        if(arraylist == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        if(i < arraylist.size()) goto _L3; else goto _L2
_L2:
        ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        com/jindong/app/mall/database/table/DB_PacksTable;
        JVM INSTR monitorexit ;
        return;
_L3:
        ContentValues contentvalues = new ContentValues();
        PacksTable packstable = (PacksTable)arraylist.get(i);
        contentvalues.put("packId", Long.valueOf(packstable.packId));
        contentvalues.put("name", packstable.name);
        contentvalues.put("buyCount", Integer.valueOf(packstable.buyCount));
        contentvalues.put("childCount", Integer.valueOf(packstable.childCount));
        sqlitedatabase.insert("PacksTable", null, contentvalues);
        i++;
          goto _L4
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
        Exception exception1;
        exception1;
        com/jindong/app/mall/database/table/DB_PacksTable;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public static void insertPacksCart(long l, String s, int i, int j, Context context)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("packId", Long.valueOf(l));
        contentvalues.put("name", s);
        contentvalues.put("buyCount", Integer.valueOf(i));
        contentvalues.put("childCount", Integer.valueOf(j));
        sqlitedatabase.insert("PacksTable", null, contentvalues);
        ((MyActivity)context).validatCartIcon();
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static PacksTable queryCartByPacksId(long l)
    {
        PacksTable packstable;
        Cursor cursor;
        packstable = null;
        cursor = null;
        PacksTable packstable1;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        String as1[] = new String[4];
        as1[0] = "packId";
        as1[1] = "name";
        as1[2] = "buyCount";
        as1[3] = "childCount";
        cursor = sqlitedatabase.query("PacksTable", as1, "packId =?", as, null, null, null);
        cursor.moveToFirst();
        if(cursor == null || cursor.getCount() == 0)
            break MISSING_BLOCK_LABEL_196;
        cursor.moveToPosition(0);
        packstable1 = new PacksTable();
        packstable1.packId = cursor.getLong(cursor.getColumnIndex("packId"));
        packstable1.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        packstable1.buyCount = cursor.getShort(cursor.getColumnIndexOrThrow("buyCount"));
        packstable1.childCount = cursor.getInt(cursor.getColumnIndex("childCount"));
        packstable = packstable1;
        if(cursor != null)
            cursor.close();
        DBHelperUtil.closeDatabase();
        return packstable;
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

    public static void updatePacksCart(long l, String s, int i, int j, Context context)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("packId", Long.valueOf(l));
        contentvalues.put("name", s);
        contentvalues.put("buyCount", Integer.valueOf(i));
        contentvalues.put("childCount", Integer.valueOf(j));
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(l))).toString();
        sqlitedatabase.update("PacksTable", contentvalues, "packId =?", as);
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
        sqlitedatabase.execSQL("drop table if exists PacksTable");
    }

    public static final String TB_COLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_COLOUMN_CHILD_COUNT = "childCount";
    public static final String TB_COLOUMN_MAIN_SKU_ID = "mainSkuId";
    public static final String TB_COLOUMN_PACK_ID = "packId";
    public static final String TB_COLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_COLOUMN_PRODUCT_NAME = "name";
    public static final String TB_COLOUMN_USER_NAME = "userName";
    public static final String TB_PACKS_TABLE = "PacksTable";
}
