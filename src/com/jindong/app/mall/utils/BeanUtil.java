// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.lang.reflect.Method;

public class BeanUtil
{

    public BeanUtil()
    {
    }

    public static Object getValue(Object obj, String s)
    {
        Method amethod[];
        int i;
        Object obj2;
        Class class1 = obj.getClass();
        boolean flag;
        if(class1.getClassLoader() != null)
            flag = true;
        else
            flag = false;
        if(flag)
            amethod = class1.getMethods();
        else
            amethod = class1.getDeclaredMethods();
        i = 0;
_L6:
        if(i < amethod.length) goto _L2; else goto _L1
_L1:
        obj2 = null;
_L4:
        return obj2;
_L2:
        Method method = amethod[i];
        String s1 = method.getName();
        if(!(new StringBuilder("get")).append(s).toString().equalsIgnoreCase(s1) && !s.equalsIgnoreCase(s1))
            break; /* Loop/switch isn't completed */
        Object obj1;
        try
        {
            obj1 = method.invoke(obj, null);
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
        obj2 = obj1;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }
}
