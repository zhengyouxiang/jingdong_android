// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.jindong.app.mall.ErrorActivity;
import java.io.PrintWriter;
import java.io.StringWriter;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, StatisticsReportUtil

public class MyUncaughtExceptionHandler
    implements Thread.UncaughtExceptionHandler
{

    public MyUncaughtExceptionHandler(Context context1)
    {
        context = context1;
        mOldUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static void appendErrorInfo(String s)
    {
        errorDataBuffer.append(s);
    }

    private boolean myUncaughtException(Thread thread, Throwable throwable)
    {
        Intent intent = new Intent(context, com/jindong/app/mall/ErrorActivity);
        intent.setFlags(0x10000000);
        try
        {
            if(Log.D)
                throwable.printStackTrace();
            StringWriter stringwriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringwriter));
            String s = (new StringBuilder(String.valueOf(errorDataBuffer.toString()))).append("||").append(stringwriter.toString()).toString();
            intent.putExtra("user", StatisticsReportUtil.getReportString(true));
            intent.putExtra("error", s);
            context.startActivity(intent);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            System.exit(0);
        }
        return true;
    }

    public static void resetErrorInfo(String s)
    {
        errorDataBuffer.setLength(0);
        errorDataBuffer.append(s);
    }

    public void uncaughtException(Thread thread, Throwable throwable)
    {
        if(!myUncaughtException(thread, throwable) && mOldUncaughtExceptionHandler != null)
        {
            mOldUncaughtExceptionHandler.uncaughtException(thread, throwable);
        } else
        {
            Process.killProcess(Process.myTid());
            System.exit(0);
        }
    }

    private static StringBuffer errorDataBuffer = new StringBuffer();
    private Context context;
    private Thread.UncaughtExceptionHandler mOldUncaughtExceptionHandler;

}
