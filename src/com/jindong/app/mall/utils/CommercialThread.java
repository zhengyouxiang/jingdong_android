// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;


// Referenced classes of package com.jindong.app.mall.utils:
//            Log

public class CommercialThread extends Thread
{
    public static interface CommercialThreadListener
    {

        public abstract void doRun();
    }


    public CommercialThread(CommercialThreadListener commercialthreadlistener)
    {
        sleepTime = 5000L;
        listner = commercialthreadlistener;
        stop = false;
        isRunner = true;
    }

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
_L5:
        boolean flag = stop;
        if(!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        try
        {
            if(!isRunner)
                wait();
            listner.doRun();
            Thread.sleep(sleepTime);
            continue; /* Loop/switch isn't completed */
        }
        catch(Exception exception1)
        {
            if(Log.D)
                exception1.printStackTrace();
        }
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public boolean isRunner;
    private CommercialThreadListener listner;
    public long sleepTime;
    public boolean stop;
}
