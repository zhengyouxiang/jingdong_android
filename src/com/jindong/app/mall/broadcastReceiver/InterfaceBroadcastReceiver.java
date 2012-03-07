// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.broadcastReceiver;

import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jindong.app.mall.MainActivity;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

public class InterfaceBroadcastReceiver extends BroadcastReceiver
{
    public static class Command
    {

        public static Command createCommand(Intent intent)
        {
            Uri uri = intent.getData();
            if(uri == null) goto _L2; else goto _L1
_L1:
            Command command = new Command(uri);
_L4:
            return command;
_L2:
            Bundle bundle = intent.getExtras();
            if(bundle != null)
            {
                int i = bundle.getInt("moduleId", 0);
                Bundle bundle1 = new Bundle();
                Iterator iterator = bundle.keySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                    {
                        if(i == 0)
                            break;
                        command = new Command(i, bundle1);
                        continue; /* Loop/switch isn't completed */
                    }
                    String s = (String)iterator.next();
                    if(s.startsWith("param_"))
                    {
                        Object obj = bundle.get(s);
                        if(obj instanceof String)
                            bundle1.putString(s.split("_")[1], (String)obj);
                        else
                        if(obj instanceof Integer)
                            bundle1.putInt(s.split("_")[1], ((Integer)obj).intValue());
                        else
                        if(obj instanceof Long)
                            bundle1.putLong(s.split("_")[1], ((Long)obj).longValue());
                    }
                } while(true);
            }
            Command command1 = (Command)bundle.getSerializable("command");
            if(command1 != null)
                command = command1;
            else
                command = null;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static void outBundleToBundle(Bundle bundle, Bundle bundle1)
        {
            Iterator iterator = bundle.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    return;
                String s = (String)iterator.next();
                Object obj = bundle.get(s);
                if(obj instanceof String)
                    bundle1.putString((new StringBuilder("param_")).append(s).toString(), (String)obj);
                else
                if(obj instanceof Integer)
                    bundle1.putInt((new StringBuilder("param_")).append(s).toString(), ((Integer)obj).intValue());
                else
                if(obj instanceof Long)
                    bundle1.putLong((new StringBuilder("param_")).append(s).toString(), ((Long)obj).longValue());
            } while(true);
        }

        public Bundle getBundle()
        {
            Bundle bundle = new Bundle();
            bundle.putInt("moduleId", moduleId);
            outBundleToBundle(outBundle, bundle);
            return bundle;
        }

        public int getModuleId()
        {
            return moduleId;
        }

        public Bundle getOutBundle()
        {
            return outBundle;
        }

        private int moduleId;
        private Bundle outBundle;

        private Command(int i, Bundle bundle)
        {
            moduleId = 0;
            outBundle = new Bundle();
            moduleId = i;
            outBundle = bundle;
        }

        private Command(Uri uri)
        {
            String s;
            String s1;
            String s2;
            String s3;
            String s4;
            moduleId = 0;
            outBundle = new Bundle();
            s = null;
            s1 = null;
            s2 = null;
            s3 = null;
            s4 = uri.getQueryParameter("params");
            if(TextUtils.isEmpty(s4)) goto _L2; else goto _L1
_L1:
            String s5;
            JSONObjectProxy jsonobjectproxy = new JSONObjectProxy(new JSONObject(s4));
            s = jsonobjectproxy.getStringOrNull("type");
            s1 = jsonobjectproxy.getStringOrNull("keyword");
            s2 = jsonobjectproxy.getStringOrNull("unionId");
            s5 = jsonobjectproxy.getStringOrNull("subunionId");
            s3 = s5;
_L5:
            Log.d("Temp", (new StringBuilder("keyword -->> ")).append(s1).toString());
            Log.d("Temp", (new StringBuilder("type -->> ")).append(s).toString());
            if(s2 != null)
                outBundle.putString("unionId", s2);
            if(s3 != null)
                outBundle.putString("subunionId", s3);
            if(!"1".equals(s)) goto _L4; else goto _L3
_L3:
            moduleId = 1;
_L6:
            if(moduleId != 0)
            {
                InterfaceBroadcastReceiver.type = s;
                InterfaceBroadcastReceiver.keyword = s1;
                InterfaceBroadcastReceiver.unionId = s2;
                InterfaceBroadcastReceiver.subunionId = s3;
                InterfaceBroadcastReceiver.host = uri.getHost();
                InterfaceBroadcastReceiver.timestamp = new Date();
            }
_L7:
            return;
            JSONException jsonexception;
            jsonexception;
            jsonexception.printStackTrace();
              goto _L5
_L2:
            s = uri.getQueryParameter("type");
            s1 = uri.getQueryParameter("keyword");
            s2 = uri.getQueryParameter("unionId");
            s3 = uri.getQueryParameter("subunionId");
              goto _L5
_L4:
            if(!"2".equals(s))
                break MISSING_BLOCK_LABEL_315;
            long l = Long.parseLong(s1);
            moduleId = 4;
            outBundle.putLong("id", l);
              goto _L6
            NumberFormatException numberformatexception;
            numberformatexception;
              goto _L7
            if("3".equals(s))
            {
                moduleId = 3;
                outBundle.putString("keyword", s1);
            } else
            {
                moduleId = 1;
            }
              goto _L6
        }
    }


    public InterfaceBroadcastReceiver()
    {
    }

    public static void cps()
    {
        cps(null);
    }

    public static void cps(final Runnable runnable)
    {
        if(unionId != null || subunionId != null)
        {
            MyActivity myactivity = (MyActivity)MyApplication.getInstance().getMainActivity().getCurrentActivity();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("cps myActivity -->> ")).append(myactivity).toString());
            if(myactivity != null)
            {
                com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener customonalllistener = new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("cps httpResponse.getString() -->> ")).append(httpresponse.getString()).toString());
                        JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                        if(jsonobjectproxy != null)
                            InterfaceBroadcastReceiver.usid = jsonobjectproxy.getStringOrNull("usid");
                        if(runnable != null)
                            runnable.run();
                    }

                    public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                    {
                        if(runnable != null)
                            runnable.run();
                    }

                    public void onProgress(int i, int j)
                    {
                    }

                    public void onStart()
                    {
                    }

                    private final Runnable val$runnable;

            
            {
                runnable = runnable1;
                super();
            }
                }
;
                com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                httpsetting.setFunctionId("cps");
                String s;
                String s1;
                String s2;
                String s3;
                if(type == null)
                    s = "";
                else
                    s = type;
                httpsetting.putJsonParam("type", s);
                if(keyword == null)
                    s1 = "";
                else
                    s1 = keyword;
                httpsetting.putJsonParam("keyword", s1);
                httpsetting.putJsonParam("unionId", unionId);
                if(subunionId == null)
                    s2 = "";
                else
                    s2 = subunionId;
                httpsetting.putJsonParam("subunionId", s2);
                if(host == null)
                    s3 = "";
                else
                    s3 = host;
                httpsetting.putJsonParam("HandleOpenURL_FunctionID", s3);
                httpsetting.setListener(customonalllistener);
                myactivity.getHttpGroupaAsynPool().add(httpsetting);
            }
        }
    }

    public static Intent createIntent(int i, Bundle bundle)
    {
        Intent intent = new Intent("com.360buy.interfaceBroadcastReceiver");
        Bundle bundle1 = new Bundle();
        bundle1.putInt("moduleId", i);
        if(bundle != null)
            Command.outBundleToBundle(bundle, bundle1);
        intent.putExtras(bundle1);
        return intent;
    }

    public void onReceive(Context context, Intent intent)
    {
        Command command;
        if(Log.D)
            Log.d("Temp", "InterfaceBroadcastReceiver onReceive() -->> ");
        command = Command.createCommand(intent);
        if(command.getModuleId() != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Intent intent1;
        MainActivity mainactivity;
        intent1 = new Intent(context, com/jindong/app/mall/MainActivity);
        mainactivity = MyApplication.getInstance().getMainActivity();
        if(mainactivity != null)
            break; /* Loop/switch isn't completed */
        if(Log.D)
            Log.d("Temp", "InterfaceBroadcastReceiver onReceive() -->> not run");
        intent1.putExtras(command.getBundle());
_L4:
        intent1.addFlags(0x10000000);
        context.startActivity(intent1);
        if(true) goto _L1; else goto _L3
_L3:
        if(Log.D)
            Log.d("Temp", "InterfaceBroadcastReceiver onReceive() -->> run");
        mainactivity.toTargetActivity(command);
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    public static final String ACTION = "com.360buy.interfaceBroadcastReceiver";
    public static final int MODULE_ID_HOME = 1;
    public static final int MODULE_ID_MESSAGE = 2;
    public static final int MODULE_ID_PRODUCT = 4;
    public static final int MODULE_ID_SEARCH = 3;
    public static final String MODULE_NAME_HOME = "home";
    public static final String MODULE_NAME_MESSAGE = "message";
    public static final String MODULE_NAME_PRODUCT = "product";
    public static final String MODULE_NAME_SEARCH = "search";
    public static String host;
    public static String keyword;
    public static String subunionId;
    public static Date timestamp;
    public static String type;
    public static String unionId;
    public static String usid;
}
