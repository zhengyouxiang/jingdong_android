// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package com.jindong.app.mall.utils:
//            IPriority

public class PriorityCollection extends ArrayList
    implements Comparable, IPriority
{

    public PriorityCollection(int i)
    {
        priority = i;
    }

    public PriorityCollection(int i, int j)
    {
        super(i);
        priority = j;
    }

    public PriorityCollection(Collection collection, int i)
    {
        super(collection);
        priority = i;
    }

    public int compareTo(IPriority ipriority)
    {
        int i;
        if(getPriority() > ipriority.getPriority())
            i = 1;
        else
        if(getPriority() < ipriority.getPriority())
            i = -1;
        else
            i = 0;
        return i;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((IPriority)obj);
    }

    public int getPriority()
    {
        return priority;
    }

    private static final long serialVersionUID = 0x8c1052438ee7101cL;
    private int priority;
}
