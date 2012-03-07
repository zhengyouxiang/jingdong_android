// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.cache;

import android.graphics.Bitmap;
import java.util.*;

public class WidgetImageCache
{

    public WidgetImageCache()
    {
    }

    /**
     * @deprecated Method clearAllBitmap is deprecated
     */

    public static void clearAllBitmap()
    {
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorenter ;
        Iterator iterator = bitmapCache.keySet().iterator();
_L1:
        if(iterator.hasNext())
            break MISSING_BLOCK_LABEL_34;
        bitmapCache.clear();
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorexit ;
        return;
        Bitmap bitmap = (Bitmap)bitmapCache.get(iterator.next());
        if(bitmap != null && bitmap != null)
            bitmap.recycle();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method containsKeyBitmap is deprecated
     */

    public static boolean containsKeyBitmap(Object obj)
    {
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorenter ;
        boolean flag = bitmapCache.containsKey(obj);
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getBitmap is deprecated
     */

    public static Bitmap getBitmap(Object obj)
    {
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorenter ;
        Bitmap bitmap = (Bitmap)bitmapCache.get(obj);
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorexit ;
        return bitmap;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method putBitmap is deprecated
     */

    public static Bitmap putBitmap(String s, Bitmap bitmap)
    {
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorenter ;
        Bitmap bitmap1 = (Bitmap)bitmapCache.put(s, bitmap);
        com/jindong/app/mall/utils/cache/WidgetImageCache;
        JVM INSTR monitorexit ;
        return bitmap1;
        Exception exception;
        exception;
        throw exception;
    }

    protected static HashMap bitmapCache = new HashMap();

}
