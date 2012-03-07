// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.database.table.*;
import com.jindong.app.mall.entity.*;
import java.util.ArrayList;

// Referenced classes of package com.jindong.app.mall.utils:
//            OpenHelper, Log

public class DBHelperUtil
{

    public DBHelperUtil(Context context)
    {
        db = null;
        mContext = context;
    }

    public static void closeDatabase()
    {
    }

    /**
     * @deprecated Method getDatabase is deprecated
     */

    public static SQLiteDatabase getDatabase()
    {
        com/jindong/app/mall/utils/DBHelperUtil;
        JVM INSTR monitorenter ;
        OpenHelper openhelper = mOpenHelper;
        if(openhelper != null) goto _L2; else goto _L1
_L1:
        versionCode = MyApplication.getInstance().getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0).versionCode;
_L3:
        mOpenHelper = new OpenHelper(MyApplication.getInstance(), "jd.db", null, versionCode);
_L2:
        SQLiteDatabase sqlitedatabase2;
        sqlitedatabase2 = mOpenHelper.getWritableDatabase();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("writableDatabase -->> ")).append(sqlitedatabase2).toString());
        SQLiteDatabase sqlitedatabase1 = sqlitedatabase2;
_L4:
        com/jindong/app/mall/utils/DBHelperUtil;
        JVM INSTR monitorexit ;
        return sqlitedatabase1;
        Exception exception2;
        exception2;
        exception2.printStackTrace();
          goto _L3
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        SQLiteDatabase sqlitedatabase;
        exception1.printStackTrace();
        sqlitedatabase = mOpenHelper.getReadableDatabase();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("readableDatabase -->> ")).append(sqlitedatabase).toString());
        sqlitedatabase1 = sqlitedatabase;
          goto _L4
    }

    /**
     * @deprecated Method openDatabase is deprecated
     */

    private void openDatabase()
    {
        this;
        JVM INSTR monitorenter ;
        db = getDatabase();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void delAllBarcodeRecord()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "ScanCodeTable -->> delAllBarcodeRecord");
        ScanCodeTable.delAllBarcodeRecord(mContext);
    }

    public void delAllCart()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> delAllCart");
        DB_CartTable.delAllCart(mContext);
    }

    public void delAllCartNoListener()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> delAllCartNoListener");
        DB_CartTable.delAllCartNoListener();
    }

    public void delAllFavority()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "FavorityTable -->> delAllFavority");
        FavorityTable.delAllFavority();
    }

    public void delAllHistory()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "HistoryTable -->> delAllHistory");
        HistoryTable.delAllHistory();
    }

    public void delAllPacksCart()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> delAllPacksCart");
        DB_PacksTable.delAllPacksCart(mContext);
    }

    public void delAllPacksCartNoListener()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> delAllPacksCartNoListener");
        DB_PacksTable.delAllPacksCartNoListener();
    }

    public void delBarcodeRecord(BarcodeRecord barcoderecord)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "ScanCodeTable -->> delBarcodeRecord");
        ScanCodeTable.delBarcodeRecord(barcoderecord);
    }

    public void delCart(long l)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> delCart");
        DB_CartTable.delCart(l, mContext);
    }

    public void delCommAddr(int i)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "CommAddrTable -->> delCommAddr");
        CommAddrTable.delCommAddr(i);
    }

    public void delPacksCart(long l)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> delPacksCart");
        DB_PacksTable.delPacksCart(l, mContext);
    }

    public ArrayList getBarcodeRecordList()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "ScanCodeTable -->> getBarcodeRecordList");
        return ScanCodeTable.getBarcodeRecordList();
    }

    public ArrayList getCartList()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> getCartList");
        return DB_CartTable.getCartList();
    }

    public ArrayList getCommAddrList()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "CommAddrTable -->> getCommAddrList");
        return CommAddrTable.getCommAddrList();
    }

    public ArrayList getHistoryByPage(int i, int j)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "HistoryTable -->> getHistoryByPage");
        return HistoryTable.getHistoryByPage(i, j);
    }

    public ArrayList getPacksList()
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> getPacksList");
        return DB_PacksTable.getPacksList();
    }

    public void insertAllCart(ArrayList arraylist)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> insertAllCart");
        DB_CartTable.insertAllCart(arraylist, mContext);
    }

    public void insertAllPacksCart(ArrayList arraylist)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> insertAllPacksCart");
        DB_PacksTable.insertAllPacksCart(arraylist, mContext);
    }

    public void insertCart(long l, String s, int i)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> insertCart");
        DB_CartTable.insertCart(l, s, i, mContext);
    }

    public void insertCommAddr(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, int i, int j, int k)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "CommAddrTable -->> insertCommAddr");
        CommAddrTable.insertCommAddr(s, s1, s2, s3, s4, s5, s6, s7, i, j, k);
    }

    public void insertOrUpdateBarcodeRecord(BarcodeRecord barcoderecord)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "ScanCodeTable -->> insertOrUpdateBarcodeRecord");
        ScanCodeTable.insertOrUpdateBarcodeRecord(barcoderecord);
    }

    public void insertOrUpdateBrowseHistory(long l, String s)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "HistoryTable -->> insertOrUpdateBrowseHistory");
        HistoryTable.insertOrUpdateBrowseHistory(l, s);
    }

    public void insertOrUpdateFavority(long l, String s, boolean flag)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "FavorityTable -->> insertOrUpdateFavority");
        FavorityTable.insertOrUpdateFavority(l, s, flag);
    }

    public void insertPacksCart(long l, String s, int i, int j)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> insertAllCart");
        DB_PacksTable.insertPacksCart(l, s, i, j, mContext);
    }

    public PacksTable queryCartByPacksId(long l)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> queryCartByPacksId");
        return DB_PacksTable.queryCartByPacksId(l);
    }

    /**
     * @deprecated Method queryCartByProductId is deprecated
     */

    public CartTable queryCartByProductId(long l)
    {
        this;
        JVM INSTR monitorenter ;
        CartTable carttable;
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> queryCartByProductId");
        carttable = DB_CartTable.queryCartByProductId(l);
        this;
        JVM INSTR monitorexit ;
        return carttable;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean queryIsFavorited(long l)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "FavorityTable -->> queryIsFavorited");
        return FavorityTable.queryIsFavorited(l);
    }

    public void updateCart(long l, String s, int i)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_CartTable -->> updateCart");
        DB_CartTable.updateCart(l, s, i, mContext);
    }

    public void updatePacksCart(long l, String s, int i, int j)
    {
        if(Log.D)
            Log.d("DBHelperUtil", "DB_PacksTable -->> updatePacksCart");
        DB_PacksTable.updatePacksCart(l, s, i, j, mContext);
    }

    private static final String DB_NAME = "jd.db";
    private static final String TAG = "DBHelperUtil";
    private static OpenHelper mOpenHelper;
    private static int versionCode = 1;
    private Cursor c;
    private SQLiteDatabase db;
    private Context mContext;

}
