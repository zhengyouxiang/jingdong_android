// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.SharedPreferences;
import android.widget.Toast;
import com.jindong.app.mall.MainActivity;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.config.Configuration;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, HttpGroup, MyActivity, StatisticsReportUtil, 
//            JSONObjectProxy, ApplicationUpgradeHelper

public class GlobalInitialization
    implements CPAUtils.GlobalInitializationInterface
{

    private GlobalInitialization()
    {
        blockTaskTokenSet = new HashSet();
        allAlready = false;
    }

    private void addBlockTaskToken(String s)
    {
        blockTaskTokenSet.add(s);
    }

    private void checksofteWareUpdated()
    {
        if(Log.D)
            Log.d("Temp", "GlobalInitialization checksofteWareUpdated() -->> ");
        if(Configuration.getBooleanProperty("applicationUpgrade").booleanValue())
        {
            HttpGroup.CustomOnAllListener customonalllistener = new HttpGroup.CustomOnAllListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    String s = jsonobjectproxy.getStringOrNull("changes");
                    String s1 = jsonobjectproxy.getStringOrNull("version");
                    String s2 = jsonobjectproxy.getStringOrNull("url");
                    String s3 = StatisticsReportUtil.getSoftwareVersionName();
                    if(Log.D)
                        Log.d("Temp", (new StringBuilder("checksofteWareUpdated() remoteVersion -->> ")).append(s1).toString());
                    if(Log.D)
                        Log.d("Temp", (new StringBuilder("checksofteWareUpdated() downloadUrl -->> ")).append(s2).toString());
                    if(Log.D)
                        Log.d("Temp", (new StringBuilder("checksofteWareUpdated() localVersion -->> ")).append(s3).toString());
                    MyActivity myactivity = null;
                    MainActivity mainactivity = MyApplication.getInstance().getMainActivity();
                    if(mainactivity != null)
                    {
                        android.app.Activity activity = mainactivity.getCurrentActivity();
                        if(activity instanceof MyActivity)
                            myactivity = (MyActivity)activity;
                    }
                    if(myactivity != null)
                        ApplicationUpgradeHelper.tryUpgrade(myactivity, s1, s3, s2, s);
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                }

                public void onProgress(int i, int j)
                {
                }

                public void onStart()
                {
                }

                final GlobalInitialization this$0;

            
            {
                this$0 = GlobalInitialization.this;
                super();
            }
            }
;
            HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
            httpsetting.setFunctionId("version");
            httpsetting.putJsonParam("osVersion", android.os.Build.VERSION.SDK);
            httpsetting.putJsonParam("platform", Integer.valueOf(100));
            httpsetting.setListener(customonalllistener);
            httpsetting.setNotifyUser(true);
            httpsetting.setNeedGlobalInitialization(false);
            httpGroup.add(httpsetting);
        }
    }

    public static void initNetwork(boolean flag)
    {
        if(Log.D)
            Log.d("Temp", "GlobalInitialization initNetwork() -->> ");
        if(globalInitialization == null)
            globalInitialization = new GlobalInitialization();
        globalInitialization.networkInitialization(flag);
    }

    /**
     * @deprecated Method networkInitializationEnd is deprecated
     */

    private void networkInitializationEnd()
    {
        this;
        JVM INSTR monitorenter ;
        if(Log.D)
            Log.d("Temp", "GlobalInitialization networkInitializationEnd() -->> ");
        MyApplication.getInstance().networkInitializationState = 2;
        if(Log.D)
            Log.d("Temp", "GlobalInitialization notifyAll -->> ");
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void networkInitializationStart()
    {
        if(Log.D)
            Log.d("Temp", "GlobalInitialization networkInitializationStart() -->> ");
        sharedPreferences = MyApplication.getInstance().getSharedPreferences("jdAndroidClient", 0);
        final boolean alreadyDevice = sharedPreferences.getBoolean("registerDevice", false);
        final boolean alreadyConfig = sharedPreferences.getBoolean("serverConfig", false);
        if(alreadyDevice && alreadyConfig)
            allAlready = true;
        if(!alreadyDevice)
        {
            if(Log.D)
                Log.d("Temp", "not already device -->> ");
            addBlockTaskToken("BLOCK_TASK_TOKEN_REGISTER_DEVICE");
            registerDevice(true);
        }
        if(!alreadyConfig)
        {
            if(Log.D)
                Log.d("Temp", "not already device -->> ");
            addBlockTaskToken("BLOCK_TASK_TOKEN_SERVER_CONFIG");
            serverConfig(true);
        }
        (new Thread(new Runnable() {

            public void run()
            {
                checksofteWareUpdated();
                if(alreadyDevice)
                    registerDevice(false);
                if(alreadyConfig)
                    serverConfig(false);
            }

            final GlobalInitialization this$0;
            private final boolean val$alreadyConfig;
            private final boolean val$alreadyDevice;

            
            {
                this$0 = GlobalInitialization.this;
                alreadyDevice = flag;
                alreadyConfig = flag1;
                super();
            }
        }
)).start();
    }

    public static void regDevice()
    {
        if(globalInitialization == null)
            globalInitialization = new GlobalInitialization();
        globalInitialization.registerDevice(false);
    }

    private void removeBlockTaskToken(String s)
    {
        blockTaskTokenSet.remove(s);
        if(blockTaskTokenSet.size() < 1)
            networkInitializationEnd();
    }

    private void serverConfig(final boolean isFirst)
    {
        if(Log.D)
            Log.d("Temp", "GlobalInitialization serverConfig() -->> ");
        HttpGroup.CustomOnAllListener customonalllistener = new HttpGroup.CustomOnAllListener() {

            public void onEnd(HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject().getJSONObjectOrNull("serverConfig");
                if(jsonobjectproxy == null) goto _L2; else goto _L1
_L1:
                android.content.SharedPreferences.Editor editor;
                Iterator iterator;
                editor = sharedPreferences.edit();
                iterator = jsonobjectproxy.keys();
_L6:
                if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
                editor.putBoolean("serverConfig", true);
                editor.commit();
_L2:
                removeBlockTaskToken("BLOCK_TASK_TOKEN_SERVER_CONFIG");
                return;
_L4:
                String s = (String)iterator.next();
                String s1 = jsonobjectproxy.getStringOrNull(s);
                if(s1 != null)
                    editor.putString(s, s1);
                if(true) goto _L6; else goto _L5
_L5:
            }

            public void onError(HttpGroup.HttpError httperror)
            {
                if(isFirst)
                    exit();
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final GlobalInitialization this$0;
            private final boolean val$isFirst;

            
            {
                this$0 = GlobalInitialization.this;
                isFirst = flag;
                super();
            }
        }
;
        HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
        httpsetting.setFunctionId("serverConfig");
        httpsetting.setListener(customonalllistener);
        boolean flag;
        boolean flag1;
        if(isFirst)
            flag = true;
        else
            flag = false;
        httpsetting.setNotifyUser(flag);
        if(isFirst)
            flag1 = true;
        else
            flag1 = false;
        httpsetting.setNotifyUserWithExit(flag1);
        httpsetting.setNeedGlobalInitialization(false);
        httpGroup.add(httpsetting);
    }

    public void exit()
    {
        myActivity.post(new Runnable() {

            public void run()
            {
                Toast.makeText(MyApplication.getInstance(), "\u670D\u52A1\u5668\u7E41\u5FD9\uFF0C\u8BF7\u7A0D\u540E\u91CD\u8BD5\u3002", 1).show();
            }

            final GlobalInitialization this$0;

            
            {
                this$0 = GlobalInitialization.this;
                super();
            }
        }
);
        myActivity.post(new Runnable() {

            public void run()
            {
                MyApplication.exitAll();
            }

            final GlobalInitialization this$0;

            
            {
                this$0 = GlobalInitialization.this;
                super();
            }
        }
, 2000);
    }

    /**
     * @deprecated Method networkInitialization is deprecated
     */

    public void networkInitialization(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(Log.D)
            Log.d("Temp", "GlobalInitialization networkInitialization() -->> ");
        if(myActivity != null) goto _L2; else goto _L1
_L1:
        myActivity = MyApplication.getInstance().getCurrentMyActivity();
        if(myActivity != null) goto _L2; else goto _L3
_L3:
        if(Log.D)
            Log.d("Temp", "GlobalInitialization myActivity is null -->> ");
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        MyApplication myapplication;
        if(httpGroup == null)
            httpGroup = myActivity.getHttpGroupaAsynPool();
        cpaProcessor = new CPAUtils.Processor(myActivity.getHandler(), httpGroup, this);
        myapplication = MyApplication.getInstance();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("GlobalInitialization myApplication.networkInitializationState -->> ")).append(myapplication.networkInitializationState).toString());
        myapplication.networkInitializationState;
        JVM INSTR tableswitch 0 1: default 234
    //                   0 156
    //                   1 185;
           goto _L4 _L5 _L6
_L5:
        networkInitializationStart();
        if(!allAlready) goto _L8; else goto _L7
_L7:
        myapplication.networkInitializationState = 2;
          goto _L4
        Exception exception;
        exception;
        throw exception;
_L8:
        myapplication.networkInitializationState = 1;
_L6:
        if(!flag) goto _L4; else goto _L9
_L9:
        if(Log.D)
            Log.d("Temp", "GlobalInitialization wait start -->> ");
        wait();
        if(Log.D)
            Log.d("Temp", "GlobalInitialization wait end -->> ");
          goto _L4
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L4
    }

    public void registerDevice(final boolean isFirst)
    {
        if(!isFirst || !cpaProcessor.beforeRegisterDevice()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JSONObject jsonobject;
        if(Log.D)
            Log.d("Temp", "GlobalInitialization registerDevice() -->> ");
        jsonobject = null;
        JSONObject jsonobject1;
        if(MyApplication.getInstance().getMainActivity() == null)
            continue; /* Loop/switch isn't completed */
        jsonobject1 = new JSONObject(StatisticsReportUtil.getDeviceInfoStr());
        jsonobject = jsonobject1;
_L4:
        HttpGroup.CustomOnAllListener customonalllistener = new HttpGroup.CustomOnAllListener() {

            public void onEnd(HttpGroup.HttpResponse httpresponse)
            {
                sharedPreferences.edit().putBoolean("registerDevice", true).commit();
                removeBlockTaskToken("BLOCK_TASK_TOKEN_REGISTER_DEVICE");
            }

            public void onError(HttpGroup.HttpError httperror)
            {
                if(isFirst)
                    exit();
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final GlobalInitialization this$0;
            private final boolean val$isFirst;

            
            {
                this$0 = GlobalInitialization.this;
                isFirst = flag;
                super();
            }
        }
;
        HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setFunctionId("device");
        httpsetting.setListener(customonalllistener);
        JSONException jsonexception;
        boolean flag;
        boolean flag1;
        if(isFirst)
            flag = true;
        else
            flag = false;
        httpsetting.setNotifyUser(flag);
        if(isFirst)
            flag1 = true;
        else
            flag1 = false;
        httpsetting.setNotifyUserWithExit(flag1);
        httpsetting.setNeedGlobalInitialization(false);
        httpGroup.add(httpsetting);
        if(true) goto _L1; else goto _L3
_L3:
        jsonexception;
        jsonexception.printStackTrace();
          goto _L4
    }

    private static final String BLOCK_TASK_TOKEN_REGISTER_DEVICE = "BLOCK_TASK_TOKEN_REGISTER_DEVICE";
    private static final String BLOCK_TASK_TOKEN_SERVER_CONFIG = "BLOCK_TASK_TOKEN_SERVER_CONFIG";
    private static GlobalInitialization globalInitialization;
    private boolean allAlready;
    private Set blockTaskTokenSet;
    private CPAUtils.Processor cpaProcessor;
    private HttpGroup httpGroup;
    private MyActivity myActivity;
    private SharedPreferences sharedPreferences;




}
