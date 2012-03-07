// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.lang.ref.SoftReference;
import java.util.*;

public class ImageCache
{

    public ImageCache()
    {
    }

    /**
     * @deprecated Method clearAllBitmap is deprecated
     */

    public static void clearAllBitmap()
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        Iterator iterator = bitmapCache.keySet().iterator();
_L1:
        if(iterator.hasNext())
            break MISSING_BLOCK_LABEL_34;
        bitmapCache.clear();
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return;
        SoftReference softreference = (SoftReference)bitmapCache.get(iterator.next());
        if(softreference != null && softreference.get() != null)
            ((Bitmap)softreference.get()).recycle();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method containsKey is deprecated
     */

    public static boolean containsKey(Object obj)
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        if(tempImageCache.containsKey(obj)) goto _L2; else goto _L1
_L1:
        boolean flag1 = imageCache.containsKey(obj);
        if(!flag1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        flag = false;
        if(true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method containsKeyBitmap is deprecated
     */

    public static boolean containsKeyBitmap(Object obj)
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        boolean flag = bitmapCache.containsKey(obj);
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method get is deprecated
     */

    public static Drawable get(String s)
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        Drawable drawable = (Drawable)tempImageCache.get(s);
        if(drawable == null) goto _L2; else goto _L1
_L1:
        tempImageCache.remove(s);
        imageCache.put(s, new SoftReference(drawable));
        Drawable drawable2 = drawable;
_L4:
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return drawable2;
_L2:
        Drawable drawable1;
        SoftReference softreference = (SoftReference)imageCache.get(s);
        if(softreference == null)
        {
            drawable2 = null;
            continue; /* Loop/switch isn't completed */
        }
        drawable1 = (Drawable)softreference.get();
        drawable2 = drawable1;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method getBitmap is deprecated
     */

    public static SoftReference getBitmap(Object obj)
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        SoftReference softreference = (SoftReference)bitmapCache.get(obj);
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return softreference;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method put is deprecated
     */

    public static void put(String s, Drawable drawable)
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        tempImageCache.put(s, drawable);
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method putBitmap is deprecated
     */

    public static SoftReference putBitmap(String s, SoftReference softreference)
    {
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorenter ;
        SoftReference softreference1 = (SoftReference)bitmapCache.put(s, softreference);
        com/jindong/app/mall/utils/cache/ImageCache;
        JVM INSTR monitorexit ;
        return softreference1;
        Exception exception;
        exception;
        throw exception;
    }

    protected static HashMap bitmapCache = new HashMap();
    protected static HashMap imageCache = new HashMap();
    protected static HashMap tempImageCache = new HashMap();

}
