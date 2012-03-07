// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.entity.CommAddr;
import com.jindong.app.mall.utils.DBHelperUtil;
import java.util.ArrayList;

public class CommAddrTable
{

    public CommAddrTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE comm_addr('id' INTEGER PRIMARY KEY  NOT NULL ,user_name TEXT,mobile TEXT,province TEXT,city TEXT,area TEXT,swhere TEXT,zip TEXT,mail TEXT,provincecode INTEGER,citycode INTEGER,areacode INTEGER)");
    }

    public static void delCommAddr(int i)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        (new ContentValues()).put("id", Integer.valueOf(i));
        String as[] = new String[1];
        as[0] = (new StringBuilder(String.valueOf(i))).toString();
        sqlitedatabase.delete("comm_addr", "id =?", as);
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static ArrayList getCommAddrList()
    {
        ArrayList arraylist;
        Cursor cursor;
        arraylist = new ArrayList();
        cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[11];
        as[0] = "user_name";
        as[1] = "mobile";
        as[2] = "province";
        as[3] = "city";
        as[4] = "area";
        as[5] = "swhere";
        as[6] = "zip";
        as[7] = "mail";
        as[8] = "provincecode";
        as[9] = "citycode";
        as[10] = "areacode";
        cursor = sqlitedatabase.query("comm_addr", as, null, null, null, null, null);
        cursor.moveToFirst();
        if(cursor == null || cursor.getCount() == 0) goto _L2; else goto _L1
_L1:
        int i = cursor.getCount();
        int j = 0;
_L7:
        if(j < i) goto _L3; else goto _L2
_L2:
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
_L5:
        return arraylist;
_L3:
        cursor.moveToPosition(j);
        CommAddr commaddr = new CommAddr();
        commaddr.sUser_name = cursor.getString(cursor.getColumnIndex("user_name"));
        commaddr.sMobile = cursor.getString(cursor.getColumnIndex("mobile"));
        commaddr.sProvince = cursor.getString(cursor.getColumnIndex("province"));
        commaddr.sCity = cursor.getString(cursor.getColumnIndex("city"));
        commaddr.sArea = cursor.getString(cursor.getColumnIndex("area"));
        commaddr.sWhere = cursor.getString(cursor.getColumnIndex("swhere"));
        commaddr.sZip = cursor.getString(cursor.getColumnIndex("zip"));
        commaddr.sMail = cursor.getString(cursor.getColumnIndex("mail"));
        commaddr.province_code = cursor.getInt(cursor.getColumnIndex("provincecode"));
        commaddr.city_code = cursor.getInt(cursor.getColumnIndex("citycode"));
        commaddr.area_code = cursor.getInt(cursor.getColumnIndex("areacode"));
        arraylist.add(commaddr);
        j++;
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

    public static void insertCommAddr(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
            int i, int j, int k)
    {
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("user_name", s);
        contentvalues.put("mobile", s1);
        contentvalues.put("province", s2);
        contentvalues.put("city", s3);
        contentvalues.put("area", s4);
        contentvalues.put("swhere", s5);
        contentvalues.put("zip", s6);
        contentvalues.put("mail", s7);
        contentvalues.put("provincecode", Integer.valueOf(i));
        contentvalues.put("citycode", Integer.valueOf(j));
        contentvalues.put("areacode", Integer.valueOf(k));
        sqlitedatabase.insert("comm_addr", null, contentvalues);
        DBHelperUtil.closeDatabase();
        return;
        Exception exception;
        exception;
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    public static void upgrade(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("drop table if exists comm_addr");
    }

    public static final String COMMON_ADDR_TABLE_NAME = "comm_addr";
    public static final String TB_COLUMN_AREA = "area";
    public static final String TB_COLUMN_AREA_CODE = "areacode";
    public static final String TB_COLUMN_CITY = "city";
    public static final String TB_COLUMN_CITY_CODE = "citycode";
    public static final String TB_COLUMN_MAIL = "mail";
    public static final String TB_COLUMN_MOBILE = "mobile";
    public static final String TB_COLUMN_PROVINCE = "province";
    public static final String TB_COLUMN_PROVINCE_CODE = "provincecode";
    public static final String TB_COLUMN_USER_NAME = "user_name";
    public static final String TB_COLUMN_WHERE = "swhere";
    public static final String TB_COLUMN_ZIP = "zip";
}
