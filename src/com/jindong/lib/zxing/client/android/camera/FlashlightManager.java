// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.camera;

import android.os.IBinder;
import com.jindong.app.mall.utils.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class FlashlightManager
{

    private FlashlightManager()
    {
    }

    static void disableFlashlight()
    {
        setFlashlight(false);
    }

    static void enableFlashlight()
    {
        setFlashlight(true);
    }

    private static Object getHardwareService()
    {
        Class class1 = maybeForName("android.os.ServiceManager");
        Object obj1;
        if(class1 == null)
        {
            obj1 = null;
        } else
        {
            Class aclass[] = new Class[1];
            aclass[0] = java/lang/String;
            Method method = maybeGetMethod(class1, "getService", aclass);
            if(method == null)
            {
                obj1 = null;
            } else
            {
                Object aobj[] = new Object[1];
                aobj[0] = "hardware";
                Object obj = invoke(method, null, aobj);
                if(obj == null)
                {
                    obj1 = null;
                } else
                {
                    Class class2 = maybeForName("android.os.IHardwareService$Stub");
                    if(class2 == null)
                    {
                        obj1 = null;
                    } else
                    {
                        Class aclass1[] = new Class[1];
                        aclass1[0] = android/os/IBinder;
                        Method method1 = maybeGetMethod(class2, "asInterface", aclass1);
                        if(method1 == null)
                        {
                            obj1 = null;
                        } else
                        {
                            Object aobj1[] = new Object[1];
                            aobj1[0] = obj;
                            obj1 = invoke(method1, null, aobj1);
                        }
                    }
                }
            }
        }
        return obj1;
    }

    private static Method getSetFlashEnabledMethod(Object obj)
    {
        Method method;
        if(obj == null)
        {
            method = null;
        } else
        {
            Class class1 = obj.getClass();
            Class aclass[] = new Class[1];
            aclass[0] = Boolean.TYPE;
            method = maybeGetMethod(class1, "setFlashlightEnabled", aclass);
        }
        return method;
    }

    private static transient Object invoke(Method method, Object obj, Object aobj[])
    {
        Object obj2 = method.invoke(obj, aobj);
        Object obj1 = obj2;
_L2:
        return obj1;
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Unexpected error while invoking ")).append(method).toString(), illegalaccessexception);
        obj1 = null;
        continue; /* Loop/switch isn't completed */
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Unexpected error while invoking ")).append(method).toString(), invocationtargetexception.getCause());
        obj1 = null;
        continue; /* Loop/switch isn't completed */
        RuntimeException runtimeexception;
        runtimeexception;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Unexpected error while invoking ")).append(method).toString(), runtimeexception);
        obj1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static Class maybeForName(String s)
    {
        Class class2 = Class.forName(s);
        Class class1 = class2;
_L2:
        return class1;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        class1 = null;
        continue; /* Loop/switch isn't completed */
        RuntimeException runtimeexception;
        runtimeexception;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Unexpected error while finding class ")).append(s).toString(), runtimeexception);
        class1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static transient Method maybeGetMethod(Class class1, String s, Class aclass[])
    {
        Method method1 = class1.getMethod(s, aclass);
        Method method = method1;
_L2:
        return method;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        method = null;
        continue; /* Loop/switch isn't completed */
        RuntimeException runtimeexception;
        runtimeexception;
        if(Log.W)
            Log.w(TAG, (new StringBuilder("Unexpected error while finding method ")).append(s).toString(), runtimeexception);
        method = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static void setFlashlight(boolean flag)
    {
        if(iHardwareService != null)
        {
            Method method = setFlashEnabledMethod;
            Object obj = iHardwareService;
            Object aobj[] = new Object[1];
            aobj[0] = Boolean.valueOf(flag);
            invoke(method, obj, aobj);
        }
    }

    private static final String TAG;
    private static final Object iHardwareService;
    private static final Method setFlashEnabledMethod;

    static 
    {
        TAG = com/jindong/lib/zxing/client/android/camera/FlashlightManager.getSimpleName();
        iHardwareService = getHardwareService();
        setFlashEnabledMethod = getSetFlashEnabledMethod(iHardwareService);
        if(iHardwareService != null) goto _L2; else goto _L1
_L1:
        if(Log.V)
            Log.v(TAG, "This device does supports control of a flashlight");
_L4:
        return;
_L2:
        if(Log.V)
            Log.v(TAG, "This device does not support control of a flashlight");
        if(true) goto _L4; else goto _L3
_L3:
    }
}
