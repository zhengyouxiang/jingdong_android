// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.thread;

import android.os.Process;
import com.jindong.app.mall.utils.IPriority;
import com.jindong.app.mall.utils.PriorityCollection;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils.thread:
//            PooledThread

public class ThreadPool
{

    public ThreadPool(int i, int j)
    {
        threads = new Vector();
        initialized = false;
        hasIdleThread = false;
        queue = new PriorityQueue();
        maxPoolSize = i;
        initPoolSize = j;
    }

    /**
     * @deprecated Method pollTasks is deprecated
     */

    private IPriority pollTasks()
    {
        this;
        JVM INSTR monitorenter ;
        IPriority ipriority = (IPriority)queue.poll();
        this;
        JVM INSTR monitorexit ;
        return ipriority;
        Exception exception;
        exception;
        throw exception;
    }

    public PooledThread getIdleThread()
    {
_L7:
        Iterator iterator = threads.iterator();
_L4:
        if(iterator.hasNext()) goto _L2; else goto _L1
_L1:
        PooledThread pooledthread1;
        if(getPoolSize() >= maxPoolSize)
            continue; /* Loop/switch isn't completed */
        PooledThread pooledthread2 = new PooledThread(this);
        pooledthread2.start();
        threads.add(pooledthread2);
        pooledthread1 = pooledthread2;
_L5:
        return pooledthread1;
_L2:
        PooledThread pooledthread = (PooledThread)iterator.next();
        if(pooledthread.isRunning()) goto _L4; else goto _L3
_L3:
        pooledthread1 = pooledthread;
          goto _L5
        if(waitForIdleThread()) goto _L7; else goto _L6
_L6:
        pooledthread1 = null;
          goto _L5
    }

    public int getPoolSize()
    {
        return threads.size();
    }

    public void init()
    {
        initialized = true;
        int i = 0;
        do
        {
            if(i >= initPoolSize)
            {
                (new Thread(new Runnable() {

                    public void run()
                    {
                        Process.setThreadPriority(19);
_L2:
                        PooledThread pooledthread1 = getIdleThread();
                        Collection collection = (Collection)pollTasks();
                        if(collection != null)
                        {
                            pooledthread1.putTasks(collection);
                            pooledthread1.startTasks();
                            continue; /* Loop/switch isn't completed */
                        }
                        PriorityQueue priorityqueue = queue;
                        priorityqueue;
                        JVM INSTR monitorenter ;
                        Exception exception;
                        try
                        {
                            queue.wait();
                        }
                        catch(InterruptedException interruptedexception) { }
                        priorityqueue;
                        JVM INSTR monitorexit ;
                        if(true) goto _L2; else goto _L1
_L1:
                        exception;
                        throw exception;
                    }

                    final ThreadPool this$0;

            
            {
                this$0 = ThreadPool.this;
                super();
            }
                }
)).start();
                return;
            }
            PooledThread pooledthread = new PooledThread(this);
            pooledthread.start();
            threads.add(pooledthread);
            i++;
        } while(true);
    }

    /**
     * @deprecated Method notifyForIdleThread is deprecated
     */

    protected void notifyForIdleThread()
    {
        this;
        JVM INSTR monitorenter ;
        hasIdleThread = true;
        notify();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method offerTask is deprecated
     */

    public void offerTask(Runnable runnable, int i)
    {
        this;
        JVM INSTR monitorenter ;
        PriorityCollection prioritycollection = new PriorityCollection(i);
        prioritycollection.add(runnable);
        offerTasks(prioritycollection);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method offerTasks is deprecated
     */

    public void offerTasks(IPriority ipriority)
    {
        this;
        JVM INSTR monitorenter ;
        queue.offer(ipriority);
        synchronized(queue)
        {
            queue.notify();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        priorityqueue;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setMaxPoolSize(int i)
    {
        maxPoolSize = i;
        if(i < getPoolSize())
            setPoolSize(i);
    }

    public void setPoolSize(int i)
    {
        if(initialized) goto _L2; else goto _L1
_L1:
        initPoolSize = i;
_L4:
        return;
_L2:
        if(i <= getPoolSize())
            break; /* Loop/switch isn't completed */
        int j = getPoolSize();
        while(j < i && j < maxPoolSize) 
        {
            PooledThread pooledthread = new PooledThread(this);
            pooledthread.start();
            threads.add(pooledthread);
            j++;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(i < getPoolSize())
            while(getPoolSize() > i) 
                ((PooledThread)threads.remove(0)).kill();
        if(true) goto _L4; else goto _L5
_L5:
    }

    /**
     * @deprecated Method waitForIdleThread is deprecated
     */

    protected boolean waitForIdleThread()
    {
        this;
        JVM INSTR monitorenter ;
        hasIdleThread = false;
_L4:
        if(hasIdleThread) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = getPoolSize();
        j = maxPoolSize;
        if(i >= j) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        wait();
          goto _L4
        InterruptedException interruptedexception;
        interruptedexception;
        flag = false;
          goto _L5
        Exception exception;
        exception;
        throw exception;
          goto _L4
    }

    protected boolean hasIdleThread;
    protected int initPoolSize;
    protected boolean initialized;
    protected int maxPoolSize;
    protected PriorityQueue queue;
    protected Vector threads;

}
