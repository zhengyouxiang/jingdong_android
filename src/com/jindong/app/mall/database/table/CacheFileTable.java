// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.entity.CacheFile;
import com.jindong.app.mall.utils.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class CacheFileTable
{

    public CacheFileTable()
    {
    }

    public static void create(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE cache_file('id' INTEGER PRIMARY KEY  NOT NULL ,first_name TEXT,last_name TEXT,clean_time DATETIME DEFAULT CURRENT_TIMESTAMP,dir_path TEXT,dir_space INTEGER)");
        sqlitedatabase.execSQL("CREATE INDEX clean_time_index ON cache_file(clean_time)");
        sqlitedatabase.execSQL("CREATE INDEX name_index ON cache_file(first_name, last_name)");
    }

    /**
     * @deprecated Method delete is deprecated
     */

    public static void delete(CacheFile cachefile)
    {
        com/jindong/app/mall/database/table/CacheFileTable;
        JVM INSTR monitorenter ;
        if(Log.D)
            Log.d("Temp", "CacheFileTable delete() -->> ");
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[2];
        as[0] = cachefile.getFirstName();
        as[1] = cachefile.getLastName();
        sqlitedatabase.delete("cache_file", "first_name = ? AND last_name = ?", as);
        if(Log.D)
            Log.d("Temp", "CacheFileTable delete() -->> ok");
        DBHelperUtil.closeDatabase();
_L2:
        com/jindong/app/mall/database/table/CacheFileTable;
        JVM INSTR monitorexit ;
        return;
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        DBHelperUtil.closeDatabase();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        DBHelperUtil.closeDatabase();
        throw exception1;
    }

    public static ArrayList getListByClean()
    {
        ArrayList arraylist;
        Cursor cursor;
        if(Log.D)
            Log.d("Temp", "CacheFileTable getListByClean() -->> ");
        arraylist = new ArrayList();
        cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[5];
        as[0] = "first_name";
        as[1] = "last_name";
        as[2] = "clean_time";
        as[3] = "dir_path";
        as[4] = "dir_space";
        String as1[] = new String[1];
        as1[0] = (new Date()).toLocaleString();
        cursor = sqlitedatabase.query("cache_file", as, "clean_time < ?", as1, null, null, null);
        if(cursor != null && cursor.moveToFirst())
            do
            {
                CacheFile cachefile = new CacheFile();
                cachefile.setFirstName(cursor.getString(cursor.getColumnIndex("first_name")));
                cachefile.setLastName(cursor.getString(cursor.getColumnIndex("last_name")));
                cachefile.setCleanTime(FormatUtils.parseDate(cursor.getString(cursor.getColumnIndex("clean_time"))));
                cachefile.setDirectory(new com.jindong.app.mall.utils.FileService.Directory(cursor.getString(cursor.getColumnIndex("dir_path")), cursor.getInt(cursor.getColumnIndex("dir_space"))));
                arraylist.add(cachefile);
            } while(cursor.moveToNext());
        if(Log.D)
            Log.d("Temp", "CacheFileTable getListByClean() -->> ok");
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
_L2:
        return arraylist;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
    }

    /**
     * @deprecated Method insertOrUpdate is deprecated
     */

    public static void insertOrUpdate(CacheFile cachefile)
    {
        com/jindong/app/mall/database/table/CacheFileTable;
        JVM INSTR monitorenter ;
        if(Log.D)
            Log.d("Temp", "CacheFileTable insertOrUpdate() -->> ");
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        String as[];
        sqlitedatabase = DBHelperUtil.getDatabase();
        contentvalues = new ContentValues();
        contentvalues.put("first_name", cachefile.getFirstName());
        contentvalues.put("last_name", cachefile.getLastName());
        contentvalues.put("clean_time", cachefile.getCleanTime().toLocaleString());
        com.jindong.app.mall.utils.FileService.Directory directory = cachefile.getDirectory();
        contentvalues.put("dir_path", directory.getPath());
        contentvalues.put("dir_space", Integer.valueOf(directory.getSpace()));
        as = new String[2];
        as[0] = cachefile.getFirstName();
        as[1] = cachefile.getLastName();
        cursor = sqlitedatabase.query("cache_file", null, "first_name = ? AND last_name = ?", as, null, null, null);
        if(cursor == null || cursor.getCount() <= 0) goto _L2; else goto _L1
_L1:
        sqlitedatabase.update("cache_file", contentvalues, "first_name = ? AND last_name = ?", as);
_L3:
        if(Log.D)
            Log.d("Temp", "CacheFileTable insertOrUpdate() -->> ok");
        if(cursor == null)
            break MISSING_BLOCK_LABEL_198;
        if(!cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
_L4:
        com/jindong/app/mall/database/table/CacheFileTable;
        JVM INSTR monitorexit ;
        return;
_L2:
        sqlitedatabase.insert("cache_file", null, contentvalues);
          goto _L3
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        if(cursor == null)
            break MISSING_BLOCK_LABEL_245;
        if(!cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
          goto _L4
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        if(cursor == null)
            break MISSING_BLOCK_LABEL_277;
        if(!cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception1;
          goto _L3
    }

    public static boolean isExpired(File file)
    {
        CacheFile cachefile;
        Cursor cursor;
        if(Log.D)
            Log.d("Temp", "CacheFileTable isExpired() -->> ");
        cachefile = new CacheFile(file);
        cursor = null;
        SQLiteDatabase sqlitedatabase = DBHelperUtil.getDatabase();
        String as[] = new String[2];
        as[0] = cachefile.getFirstName();
        as[1] = cachefile.getLastName();
        cursor = sqlitedatabase.query("cache_file", null, "first_name = ? AND last_name = ?", as, null, null, null);
        if(cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) goto _L2; else goto _L1
_L1:
        long l;
        long l1;
        l = FormatUtils.parseDate(cursor.getString(cursor.getColumnIndex("clean_time"))).getTime();
        l1 = (new Date()).getTime();
        if(l <= l1) goto _L2; else goto _L3
_L3:
        boolean flag = false;
_L7:
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
_L5:
        if(Log.D)
            Log.d("Temp", (new StringBuilder("CacheFileTable isExpired() -->> ")).append(flag).toString());
        return flag;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        flag = true;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        DBHelperUtil.closeDatabase();
        throw exception;
_L2:
        flag = true;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static void upgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("drop index if exists clean_time_index");
        sqlitedatabase.execSQL("drop index if exists name_index");
        sqlitedatabase.execSQL("drop table if exists cache_file");
    }

    public static final String TABLE_NAME = "cache_file";
    public static final String TB_COLUMN_CLEAN_TIME = "clean_time";
    public static final String TB_COLUMN_DIR_PATH = "dir_path";
    public static final String TB_COLUMN_DIR_SPACE = "dir_space";
    public static final String TB_COLUMN_FIRST_NAME = "first_name";
    public static final String TB_COLUMN_LAST_NAME = "last_name";
}
