// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.SharedPreferences;
import android.os.Handler;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.cpa.JniInterface;
import com.jindong.app.mall.cpa.RSAHelper;

// Referenced classes of package com.jindong.app.mall.utils:
//            HttpGroup, Log, CommonUtil, JSONObjectProxy

public class CPAUtils
{
    static interface GlobalInitializationInterface
    {

        public abstract void exit();

        public abstract void registerDevice(boolean flag);
    }

    public static class Processor
    {

        private void getCpaToken()
        {
            HttpGroup.OnCommonListener oncommonlistener = new HttpGroup.OnCommonListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    if(jsonobjectproxy == null)
                    {
                        globalInitializationInterface.exit();
                    } else
                    {
                        String s = jsonobjectproxy.getStringOrNull("ticket");
                        if(s == null)
                            globalInitializationInterface.exit();
                        else
                            startCPa(s, strByte);
                    }
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                    globalInitializationInterface.exit();
                }

                public void onReady(HttpGroup.HttpSettingParams httpsettingparams)
                {
                }

                final Processor this$1;
                private final byte val$strByte[];

                
                {
                    this$1 = Processor.this;
                    strByte = abyte0;
                    super();
                }
            }
;
            HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
            httpsetting.setFunctionId("cpaTalk");
            httpsetting.setNeedGlobalInitialization(false);
            httpsetting.setListener(oncommonlistener);
            httpGroup.add(httpsetting);
        }

        private void startCPa(String s, byte abyte0[])
        {
            if(Log.I)
                Log.i("RSA", (new StringBuilder("CE:")).append(new String(abyte0)).toString());
            String s1;
            RSAHelper.init();
            s1 = RSAHelper.encryptBySeg(abyte0);
            if(Log.I)
                Log.i("RSA", (new StringBuilder("JE:")).append(s1).toString());
            HttpGroup.OnCommonListener oncommonlistener = new HttpGroup.OnCommonListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    if(jsonobjectproxy == null)
                    {
                        globalInitializationInterface.exit();
                    } else
                    {
                        String s2 = jsonobjectproxy.getStringOrNull("code");
                        if(s2 != null && s2.equals("0"))
                        {
                            CommonUtil.getJdSharedPreferences().edit().putBoolean("cpaFlag", true).commit();
                            globalInitializationInterface.registerDevice(true);
                        } else
                        {
                            globalInitializationInterface.exit();
                        }
                    }
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                    globalInitializationInterface.exit();
                }

                public void onReady(HttpGroup.HttpSettingParams httpsettingparams)
                {
                }

                final Processor this$1;

                
                {
                    this$1 = Processor.this;
                    super();
                }
            }
;
            HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
            httpsetting.setFunctionId("cpa");
            httpsetting.putJsonParam("info", s1);
            httpsetting.putJsonParam("ticket", s);
            httpsetting.putJsonParam("unionId", Configuration.getProperty("unionId"));
            httpsetting.putJsonParam("subunionId", Configuration.getProperty("subunionId"));
            httpsetting.putJsonParam("partner", Configuration.getProperty("partner"));
            httpsetting.setNeedGlobalInitialization(false);
            httpsetting.setListener(oncommonlistener);
            httpGroup.add(httpsetting);
_L2:
            return;
            Exception exception;
            exception;
            exception.printStackTrace();
            globalInitializationInterface.exit();
            if(true) goto _L2; else goto _L1
_L1:
        }

        public boolean beforeRegisterDevice()
        {
            boolean flag;
            if(!CommonUtil.getJdSharedPreferences().getBoolean("cpaFlag", false))
            {
                handler.post(new Runnable() {

                    public void run()
                    {
                        getCpaToken();
                    }

                    final Processor this$1;

                
                {
                    this$1 = Processor.this;
                    super();
                }
                }
);
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        private GlobalInitializationInterface globalInitializationInterface;
        private Handler handler;
        private HttpGroup httpGroup;




        public Processor(Handler handler1, HttpGroup httpgroup, GlobalInitializationInterface globalinitializationinterface)
        {
            handler = handler1;
            httpGroup = httpgroup;
            globalInitializationInterface = globalinitializationinterface;
        }
    }


    public CPAUtils()
    {
    }

    public static void loadLibrary()
    {
        System.loadLibrary("jd-jni");
    }

    private static final String SHARED_PREFERENCES_ALREADY_CPA = "cpaFlag";
}
