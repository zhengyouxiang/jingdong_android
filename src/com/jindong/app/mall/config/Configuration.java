// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.config;

import java.util.*;

public class Configuration
{

    public Configuration()
    {
    }

    public static Boolean getBooleanProperty(String s)
    {
        return getBooleanProperty(s, null);
    }

    public static Boolean getBooleanProperty(String s, Boolean boolean1)
    {
        String s1 = getProperty(s);
        if(s1 != null) goto _L2; else goto _L1
_L1:
        Boolean boolean2 = boolean1;
_L4:
        return boolean2;
_L2:
        Boolean boolean3 = Boolean.valueOf(s1);
        boolean2 = boolean3;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        boolean2 = boolean1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Integer getIntegerProperty(String s)
    {
        return getIntegerProperty(s, null);
    }

    public static Integer getIntegerProperty(String s, Integer integer)
    {
        String s1 = getProperty(s);
        if(s1 != null) goto _L2; else goto _L1
_L1:
        Integer integer1 = integer;
_L4:
        return integer1;
_L2:
        Integer integer2 = Integer.valueOf(s1);
        integer1 = integer2;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        integer1 = integer;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String getProperty(String s)
    {
        return getProperty(s, null);
    }

    public static String getProperty(String s, String s1)
    {
        String s2 = null;
        if(properties != null)
            s2 = properties.getProperty(s);
        if(s2 == null)
            s2 = (String)localProperties.get(s);
        if(s2 == null)
            s2 = s1;
        return s2;
    }

    public static final String APPLICATION_SHORTCUT = "applicationShortcut";
    public static final String APPLICATION_UPGRADE = "applicationUpgrade";
    public static final String ATTEMPTS = "attempts";
    public static final String ATTEMPTS_TIME = "attemptsTime";
    public static final String CONNECT_TIMEOUT = "connectTimeout";
    public static final String COST_HINT = "costHint";
    public static final String DEBUG_LOG = "debugLog";
    public static final String DISCUSSUPLOADIMAGE_HEIGHT = "discussUploadImageHeight";
    public static final String DISCUSSUPLOADIMAGE_QUALITY = "discussUploadImageQuality";
    public static final String DISCUSSUPLOADIMAGE_WIDTH = "discussUploadImageWidth";
    public static final String ERROR_LOG = "errorLog";
    public static final String HOST = "host";
    public static final String INFO_LOG = "infoLog";
    public static final String INIT_POOL_SIZE = "initPoolSize";
    public static final String LEAVE_TIME_GAP = "leaveTimeGap";
    public static final String LOCAL_FILE_CACHE = "localFileCache";
    public static final String LOCAL_MEMORY_CACHE = "localMemoryCache";
    public static final String MAX_POOL_SIZE = "maxPoolSize";
    public static final String PARTNER = "partner";
    public static final String PRINT_LOG = "printLog";
    public static final String READ_TIMEOUT = "readTimeout";
    public static final String REQUEST_METHOD = "requestMethod";
    public static final String ROUTINE_CHECK_DELAY_TIME = "routineCheckDelayTime";
    public static final String SUB_UNION_ID = "subunionId";
    public static final String TEST_MODE = "testMode";
    public static final String UNION_ID = "unionId";
    public static final String VIEW_LOG = "viewLog";
    public static final String WARN_LOG = "warnLog";
    private static Map localProperties;
    private static Properties properties;

    static 
    {
        localProperties = new HashMap();
        localProperties.put("host", "gw.m.360buy.com");
        localProperties.put("connectTimeout", "20000");
        localProperties.put("readTimeout", "20000");
        localProperties.put("attempts", "2");
        localProperties.put("attemptsTime", "0");
        localProperties.put("requestMethod", "post");
        localProperties.put("localMemoryCache", "false");
        localProperties.put("localFileCache", "false");
        localProperties.put("initPoolSize", "5");
        localProperties.put("maxPoolSize", "5");
        localProperties.put("discussUploadImageWidth", "500");
        localProperties.put("discussUploadImageHeight", "500");
        localProperties.put("discussUploadImageQuality", "80");
        localProperties.put("routineCheckDelayTime", "2000");
        localProperties.put("leaveTimeGap", "3600000");
        localProperties.put("printLog", "false");
        localProperties.put("debugLog", "true");
        localProperties.put("viewLog", "true");
        localProperties.put("errorLog", "true");
        localProperties.put("infoLog", "true");
        localProperties.put("warnLog", "true");
        localProperties.put("testMode", "false");
        localProperties.put("partner", "youmi039");
        localProperties.put("unionId", "15561");
        localProperties.put("subunionId", "youmi039");
        localProperties.put("applicationUpgrade", "true");
        localProperties.put("applicationShortcut", "false");
        localProperties.put("costHint", "false");
        java.io.InputStream inputstream = com/jindong/app/mall/config/Configuration.getClassLoader().getResourceAsStream("config.properties");
        if(inputstream != null)
        {
            properties = new Properties();
            properties.load(inputstream);
        }
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }
}
