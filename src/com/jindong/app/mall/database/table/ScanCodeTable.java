// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.entity.BarcodeRecord;
import com.jindong.app.mall.utils.DBHelperUtil;
import com.jindong.app.mall.utils.MyActivity;
import java.util.ArrayList;
import java.util.Iterator;

public class ScanCodeTable
{

    public ScanCodeTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE scan_code('id' INTEGER PRIMARY KEY  NOT NULL ,code TEXT,product_name TEXT,create_time DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public static void delAllBarcodeRecord(Context context)
    {
        DBHelperUtil.getDatabase().delete("scan_code", "1=1", null);
        if(context instanceof MyActivity)
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

    public static void delBarcodeRecord(BarcodeRecord barcoderecord)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[1];
        as[0] = barcoderecord.getContent();
        sqlitedatabase.delete("scan_code", "code=?", as);
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

    public static ArrayList getBarcodeRecordList()
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        SQLiteDatabase sqlitedatabase;
        arraylist = new ArrayList();
        arraylist1 = new ArrayList();
        sqlitedatabase = null;
        Cursor cursor1;
        sqlitedatabase = DBHelperUtil.getDatabase();
        String as2[] = new String[3];
        as2[0] = "id";
        as2[1] = "code";
        as2[2] = "product_name";
        cursor1 = sqlitedatabase.query("scan_code", as2, null, null, null, null, "create_time");
        cursor1.moveToFirst();
        if(cursor1 == null || cursor1.getCount() == 0) goto _L2; else goto _L1
_L1:
        int i = cursor1.getCount();
        int j = i - 1;
_L13:
        if(j >= 0) goto _L3; else goto _L2
_L2:
        Iterator iterator2;
        if(cursor1 != null && !cursor1.isClosed())
            cursor1.close();
        iterator2 = arraylist1.iterator();
_L9:
        if(iterator2.hasNext()) goto _L5; else goto _L4
_L4:
        DBHelperUtil.closeDatabase();
_L7:
        return arraylist;
_L3:
        cursor1.moveToPosition(j);
        if(j < i - 50)
        {
            arraylist1.add((new StringBuilder()).append(cursor1.getInt(cursor1.getColumnIndex("id"))).toString());
        } else
        {
            BarcodeRecord barcoderecord = new BarcodeRecord();
            barcoderecord.setContent(cursor1.getString(cursor1.getColumnIndex("code")));
            barcoderecord.setProductName(cursor1.getString(cursor1.getColumnIndex("product_name")));
            arraylist.add(barcoderecord);
        }
          goto _L6
        Exception exception6;
        exception6;
        Exception exception1;
        Cursor cursor;
        cursor = cursor1;
        exception1 = exception6;
_L11:
        exception1.printStackTrace();
        Iterator iterator1;
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        iterator1 = arraylist1.iterator();
_L8:
label0:
        {
            if(iterator1.hasNext())
                break label0;
            DBHelperUtil.closeDatabase();
        }
          goto _L7
        String s1 = (String)iterator1.next();
        String as1[] = new String[1];
        as1[0] = s1;
        sqlitedatabase.delete("scan_code", "id=?", as1);
          goto _L8
        Exception exception4;
        exception4;
        Exception exception3;
        exception3 = exception4;
        cursor = null;
_L10:
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        Iterator iterator = arraylist1.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                DBHelperUtil.closeDatabase();
                throw exception3;
            }
            String s = (String)iterator.next();
            String as[] = new String[1];
            as[0] = s;
            sqlitedatabase.delete("scan_code", "id=?", as);
        } while(true);
_L5:
        String s2 = (String)iterator2.next();
        String as3[] = new String[1];
        as3[0] = s2;
        sqlitedatabase.delete("scan_code", "id=?", as3);
          goto _L9
        Exception exception5;
        exception5;
        exception3 = exception5;
        cursor = cursor1;
          goto _L10
        Exception exception2;
        exception2;
        exception3 = exception2;
          goto _L10
        Exception exception;
        exception;
        exception1 = exception;
        cursor = null;
          goto _L11
_L6:
        j--;
        if(true) goto _L13; else goto _L12
_L12:
    }

    /**
     * @deprecated Method insertOrUpdateBarcodeRecord is deprecated
     */

    public static void insertOrUpdateBarcodeRecord(BarcodeRecord barcoderecord)
    {
        com/jindong/app/mall/database/table/ScanCodeTable;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        String as[];
        Cursor cursor;
        sqlitedatabase = DBHelperUtil.getDatabase();
        contentvalues = new ContentValues();
        contentvalues.put("code", barcoderecord.getContent());
        contentvalues.put("product_name", barcoderecord.getProductName());
        as = new String[1];
        as[0] = barcoderecord.getContent();
        cursor = sqlitedatabase.query("scan_code", null, "code=?", as, null, null, null);
        if(cursor == null || cursor.getCount() <= 0) goto _L2; else goto _L1
_L1:
        sqlitedatabase.update("scan_code", contentvalues, "code=?", as);
_L3:
        DBHelperUtil.closeDatabase();
_L4:
        com/jindong/app/mall/database/table/ScanCodeTable;
        JVM INSTR monitorexit ;
        return;
_L2:
        sqlitedatabase.insert("scan_code", null, contentvalues);
          goto _L3
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        DBHelperUtil.closeDatabase();
          goto _L4
        Exception exception1;
        exception1;
        throw exception1;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
          goto _L3
    }

    public static void upgrade(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("drop table if exists scan_code");
    }

    public static final String TABLE_NAME = "scan_code";
    public static final String TB_COLUMN_CODE = "code";
    public static final String TB_COLUMN_CREATE_TIME = "create_time";
    public static final String TB_COLUMN_PRODUCT_NAME = "product_name";
}
