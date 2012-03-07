// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.thread;

import android.os.Process;
import com.jindong.app.mall.config.Configuration;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils.thread:
//            ThreadPool

public class PooledThread extends Thread
{

    public PooledThread(ThreadPool threadpool)
    {
        tasks = new ArrayList();
        running = false;
        stopped = false;
        paused = false;
        killed = false;
        pool = threadpool;
    }

    public static ThreadPool getThreadPool()
    {
        return sPool;
    }

    public boolean isRunning()
    {
        return running;
    }

    public void kill()
    {
        if(!running)
            interrupt();
        else
            killed = true;
    }

    public void killSync()
    {
        kill();
        do
        {
            if(!isAlive())
                return;
            try
            {
                sleep(5L);
            }
            catch(InterruptedException interruptedexception) { }
        } while(true);
    }

    public void pauseTasks()
    {
        paused = true;
    }

    public void pauseTasksSync()
    {
        pauseTasks();
        do
        {
            if(!isRunning())
                return;
            try
            {
                sleep(5L);
            }
            catch(InterruptedException interruptedexception) { }
        } while(true);
    }

    protected Runnable popTask()
    {
        Runnable runnable;
        if(tasks.size() > 0)
            runnable = (Runnable)tasks.remove(0);
        else
            runnable = null;
        return runnable;
    }

    public void putTask(Runnable runnable)
    {
        tasks.add(runnable);
    }

    public void putTasks(Collection collection)
    {
        tasks.addAll(collection);
    }

    /**
     * @deprecated Method run is deprecated
     */

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        Process.setThreadPriority(19);
_L4:
        if(running && tasks.size() != 0) goto _L2; else goto _L1
_L1:
        pool.notifyForIdleThread();
        wait();
_L8:
        if(!killed) goto _L4; else goto _L3
_L3:
        killed = false;
_L13:
        this;
        JVM INSTR monitorexit ;
        return;
_L12:
        Runnable runnable;
        runnable.run();
        if(!stopped) goto _L6; else goto _L5
_L5:
        stopped = false;
        if(tasks.size() <= 0) goto _L6; else goto _L7
_L7:
        tasks.clear();
        System.out.println((new StringBuilder(String.valueOf(Thread.currentThread().getId()))).append(": Tasks are stopped").toString());
_L11:
        running = false;
          goto _L8
_L6:
        if(!paused) goto _L2; else goto _L9
_L9:
        paused = false;
        if(tasks.size() <= 0) goto _L2; else goto _L10
_L10:
        System.out.println((new StringBuilder(String.valueOf(Thread.currentThread().getId()))).append(": Tasks are paused").toString());
          goto _L11
        Exception exception;
        exception;
        throw exception;
_L2:
        runnable = popTask();
        if(runnable != null) goto _L12; else goto _L11
        InterruptedException interruptedexception;
        interruptedexception;
          goto _L13
    }

    /**
     * @deprecated Method startTasks is deprecated
     */

    public void startTasks()
    {
        this;
        JVM INSTR monitorenter ;
        running = true;
        notify();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void stopTasks()
    {
        stopped = true;
    }

    public void stopTasksSync()
    {
        stopTasks();
        do
        {
            if(!isRunning())
                return;
            try
            {
                sleep(5L);
            }
            catch(InterruptedException interruptedexception) { }
        } while(true);
    }

    private static ThreadPool sPool;
    protected boolean killed;
    protected boolean paused;
    private ThreadPool pool;
    protected boolean running;
    protected boolean stopped;
    protected List tasks;

    static 
    {
        sPool = new ThreadPool(Integer.parseInt(Configuration.getProperty("maxPoolSize")), Integer.parseInt(Configuration.getProperty("initPoolSize")));
        sPool.init();
    }
}
