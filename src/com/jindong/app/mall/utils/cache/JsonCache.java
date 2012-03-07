// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.cache;

import com.jindong.app.mall.utils.JSONObjectProxy;
import java.util.HashMap;

public class JsonCache
{

    public JsonCache()
    {
    }

    /**
     * @deprecated Method containsKey is deprecated
     */

    public static boolean containsKey(Object obj)
    {
        com/jindong/app/mall/utils/cache/JsonCache;
        JVM INSTR monitorenter ;
        boolean flag = jsonCache.containsKey(obj);
        com/jindong/app/mall/utils/cache/JsonCache;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method get is deprecated
     */

    public static JSONObjectProxy get(String s)
    {
        com/jindong/app/mall/utils/cache/JsonCache;
        JVM INSTR monitorenter ;
        JSONObjectProxy jsonobjectproxy = (JSONObjectProxy)jsonCache.get(s);
        com/jindong/app/mall/utils/cache/JsonCache;
        JVM INSTR monitorexit ;
        return jsonobjectproxy;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method put is deprecated
     */

    public static void put(String s, JSONObjectProxy jsonobjectproxy)
    {
        com/jindong/app/mall/utils/cache/JsonCache;
        JVM INSTR monitorenter ;
        jsonCache.put(s, jsonobjectproxy);
        com/jindong/app/mall/utils/cache/JsonCache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected static HashMap jsonCache = new HashMap();

}
