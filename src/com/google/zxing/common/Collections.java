// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import java.util.Vector;

// Referenced classes of package com.google.zxing.common:
//            Comparator

public final class Collections
{

    private Collections()
    {
    }

    public static void insertionSort(Vector vector, Comparator comparator)
    {
        int i = vector.size();
        for(int j = 1; j < i; j++)
        {
            Object obj = vector.elementAt(j);
            int k = j - 1;
            do
            {
                if(k < 0)
                    break;
                Object obj1 = vector.elementAt(k);
                if(comparator.compare(obj1, obj) <= 0)
                    break;
                vector.setElementAt(obj1, k + 1);
                k--;
            } while(true);
            vector.setElementAt(obj, k + 1);
        }

    }
}
