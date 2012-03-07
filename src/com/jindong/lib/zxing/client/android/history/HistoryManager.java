// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.history;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.CaptureActivity;
import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package com.jindong.lib.zxing.client.android.history:
//            DBHelper, HistoryClickListener

public final class HistoryManager
{

    public HistoryManager(CaptureActivity captureactivity)
    {
        activity = captureactivity;
    }

    private static String massageHistoryField(String s)
    {
        return s.replace("\"", "\"\"");
    }

    static Uri saveHistory(String s)
    {
        File file = new File(new File(Environment.getExternalStorageDirectory(), "BarcodeScanner"), "History");
        if(file.exists() || file.mkdirs()) goto _L2; else goto _L1
_L1:
        Uri uri;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Couldn't make dir ")).append(file).toString());
        uri = null;
_L3:
        return uri;
_L2:
        File file1;
        OutputStreamWriter outputstreamwriter;
        file1 = new File(file, (new StringBuilder("history-")).append(System.currentTimeMillis()).append(".csv").toString());
        outputstreamwriter = null;
        OutputStreamWriter outputstreamwriter1 = new OutputStreamWriter(new FileOutputStream(file1), Charset.forName("UTF-8"));
        Uri uri1;
        outputstreamwriter1.write(s);
        uri1 = Uri.parse((new StringBuilder("file://")).append(file1.getAbsolutePath()).toString());
        uri = uri1;
        if(outputstreamwriter1 != null)
            try
            {
                outputstreamwriter1.close();
            }
            catch(IOException ioexception4) { }
          goto _L3
        IOException ioexception5;
        ioexception5;
        IOException ioexception1 = ioexception5;
_L6:
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Couldn't access file ")).append(file1).append(" due to ").append(ioexception1).toString());
        Exception exception;
        if(outputstreamwriter != null)
            try
            {
                outputstreamwriter.close();
            }
            catch(IOException ioexception3) { }
        uri = null;
          goto _L3
        exception;
_L5:
        if(outputstreamwriter != null)
            try
            {
                outputstreamwriter.close();
            }
            catch(IOException ioexception2) { }
        throw exception;
        exception;
        outputstreamwriter = outputstreamwriter1;
        if(true) goto _L5; else goto _L4
_L4:
        IOException ioexception;
        ioexception;
        ioexception1 = ioexception;
        outputstreamwriter = outputstreamwriter1;
          goto _L6
    }

    public void addHistoryItem(Result result)
    {
        if(activity.getIntent().getBooleanExtra("SAVE_HISTORY", true)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        SQLiteDatabase sqlitedatabase = (new DBHelper(activity)).getWritableDatabase();
        String as[] = new String[1];
        as[0] = result.getText();
        sqlitedatabase.delete("history", "text=?", as);
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("text", result.getText());
        contentvalues.put("format", result.getBarcodeFormat().toString());
        contentvalues.put("display", result.getText());
        contentvalues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        sqlitedatabase.insert("history", "timestamp", contentvalues);
        sqlitedatabase.close();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        sqlitedatabase.close();
        throw exception;
    }

    public AlertDialog buildAlert()
    {
        List list = getHistoryItems();
        int i = list.size();
        String as[] = new String[i + 2];
        int j = 0;
        do
        {
            if(j >= i)
            {
                Resources resources = activity.getResources();
                as[as.length - 2] = resources.getString(0x7f090205);
                as[as.length - 1] = resources.getString(0x7f090203);
                HistoryClickListener historyclicklistener = new HistoryClickListener(this, activity, as, list);
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
                builder.setTitle(0x7f090206);
                builder.setItems(as, historyclicklistener);
                return builder.create();
            }
            as[j] = ((Result)list.get(j)).getText();
            j++;
        } while(true);
    }

    CharSequence buildHistory()
    {
        StringBuilder stringbuilder;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        stringbuilder = new StringBuilder(1000);
        sqlitedatabase = (new DBHelper(activity)).getReadableDatabase();
        cursor = null;
        cursor = sqlitedatabase.query("history", EXPORT_COL_PROJECTION, null, null, null, null, "timestamp DESC");
_L1:
        boolean flag = cursor.moveToNext();
        int i;
        if(!flag)
        {
            if(cursor != null)
                cursor.close();
            sqlitedatabase.close();
            return stringbuilder;
        }
        i = 0;
_L2:
        if(i < EXPORT_COL_PROJECTION.length)
            break MISSING_BLOCK_LABEL_156;
        long l = cursor.getLong(EXPORT_COL_PROJECTION.length - 1);
        stringbuilder.append('"').append(massageHistoryField(EXPORT_DATE_TIME_FORMAT.format(new Date(l)))).append("\"\r\n");
          goto _L1
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        sqlitedatabase.close();
        throw exception;
        stringbuilder.append('"').append(massageHistoryField(cursor.getString(i))).append("\",");
        i++;
          goto _L2
    }

    void clearHistory()
    {
        SQLiteDatabase sqlitedatabase = (new DBHelper(activity)).getWritableDatabase();
        sqlitedatabase.delete("history", null, null);
        sqlitedatabase.close();
        return;
        Exception exception;
        exception;
        sqlitedatabase.close();
        throw exception;
    }

    List getHistoryItems()
    {
        ArrayList arraylist;
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        DBHelper dbhelper = new DBHelper(activity);
        arraylist = new ArrayList();
        sqlitedatabase = dbhelper.getReadableDatabase();
        cursor = null;
        cursor = sqlitedatabase.query("history", GET_ITEM_COL_PROJECTION, null, null, null, null, "timestamp DESC");
_L1:
        boolean flag = cursor.moveToNext();
        if(!flag)
        {
            if(cursor != null)
                cursor.close();
            sqlitedatabase.close();
            return arraylist;
        }
        arraylist.add(new Result(cursor.getString(0), null, null, BarcodeFormat.valueOf(cursor.getString(1)), cursor.getLong(2)));
          goto _L1
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        sqlitedatabase.close();
        throw exception;
    }

    public void trimHistory()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = (new DBHelper(activity)).getWritableDatabase();
        cursor = null;
        int i;
        cursor = sqlitedatabase.query("history", ID_COL_PROJECTION, null, null, null, null, "timestamp DESC");
        i = 0;
_L4:
        if(i < 50 && cursor.moveToNext()) goto _L2; else goto _L1
_L1:
        boolean flag = cursor.moveToNext();
        if(!flag)
        {
            if(cursor != null)
                cursor.close();
            sqlitedatabase.close();
            return;
        }
          goto _L3
_L2:
        i++;
          goto _L4
_L3:
        sqlitedatabase.delete("history", (new StringBuilder("id=")).append(cursor.getString(0)).toString(), null);
          goto _L1
        Exception exception;
        exception;
        if(cursor != null)
            cursor.close();
        sqlitedatabase.close();
        throw exception;
          goto _L4
    }

    private static final String EXPORT_COL_PROJECTION[];
    private static final DateFormat EXPORT_DATE_TIME_FORMAT = DateFormat.getDateTimeInstance();
    private static final String GET_ITEM_COL_PROJECTION[];
    private static final String ID_COL_PROJECTION[];
    private static final int MAX_ITEMS = 50;
    private static final String TAG = com/jindong/lib/zxing/client/android/history/HistoryManager.getSimpleName();
    private static final String TEXT_COL_PROJECTION[];
    private final CaptureActivity activity;

    static 
    {
        String as[] = new String[1];
        as[0] = "text";
        TEXT_COL_PROJECTION = as;
        String as1[] = new String[3];
        as1[0] = "text";
        as1[1] = "format";
        as1[2] = "timestamp";
        GET_ITEM_COL_PROJECTION = as1;
        String as2[] = new String[4];
        as2[0] = "text";
        as2[1] = "display";
        as2[2] = "format";
        as2[3] = "timestamp";
        EXPORT_COL_PROJECTION = as2;
        String as3[] = new String[1];
        as3[0] = "id";
        ID_COL_PROJECTION = as3;
    }
}
