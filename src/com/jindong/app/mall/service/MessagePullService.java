// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.CommonUtil;
import com.jindong.app.mall.utils.DBHelperUtil;
import com.jindong.app.mall.utils.FileService;
import com.jindong.app.mall.utils.HttpGroup;
import com.jindong.app.mall.utils.JSONArrayPoxy;
import com.jindong.app.mall.utils.JSONObjectProxy;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.cache.WidgetImageCache;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessagePullService extends Service
{
    private class AutoUpdateWidgetThread extends Thread
    {

        public void run()
        {
            do
            {
                if(stop || handler == null)
                    return;
                if(System.currentTimeMillis() - time >= 5000L)
                {
                    handler.sendEmptyMessage(4096);
                    time = System.currentTimeMillis();
                }
                try
                {
                    Thread.sleep(5000L);
                }
                catch(InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            } while(true);
        }

        public void setStop(boolean flag)
        {
            stop = flag;
        }

        public void setTime(long l)
        {
            time = l;
        }

        private boolean stop;
        final MessagePullService this$0;
        private long time;

        public AutoUpdateWidgetThread(long l, boolean flag)
        {
            this$0 = MessagePullService.this;
            super();
            time = l;
            stop = flag;
        }
    }


    public MessagePullService()
    {
        msgAutoUpdateTimes = 0xdbba0L;
        network_retry_time = 0;
        updateWidgetIndex = 0;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
                if(Log.D)
                {
                    Log.d("Temp", "MessagePullService  handleMessage() -->>");
                    Log.d("Temp", (new StringBuilder("handleMessage() msg.what -->>")).append(message.what).toString());
                }
                message.what;
                JVM INSTR lookupswitch 5: default 92
            //                           4096: 93
            //                           4352: 212
            //                           4353: 222
            //                           4354: 258
            //                           4355: 330;
                   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
                return;
_L2:
                MessagePullService messagepullservice1 = MessagePullService.this;
                int i;
                if(updateWidgetIndex < productList.size() - 1)
                {
                    MessagePullService messagepullservice2 = MessagePullService.this;
                    int j = 1 + messagepullservice2.updateWidgetIndex;
                    messagepullservice2.updateWidgetIndex = j;
                    i = j;
                } else
                {
                    i = 0;
                }
                messagepullservice1.updateWidgetIndex = i;
                updateWidget();
                if(autoUpdateWidgetThread == null)
                {
                    autoUpdateWidgetThread = new AutoUpdateWidgetThread(System.currentTimeMillis(), false);
                    autoUpdateWidgetThread.start();
                }
                continue; /* Loop/switch isn't completed */
_L3:
                updateWidget();
                continue; /* Loop/switch isn't completed */
_L4:
                Intent intent1 = new Intent("Action_Get_Widget_Info");
                intent1.setClass(MessagePullService.this, com/jindong/app/mall/service/MessagePullService);
                startService(intent1);
                continue; /* Loop/switch isn't completed */
_L5:
                final String url = (String)message.obj;
                com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                httpsetting.setPriority(5000);
                httpsetting.setType(5000);
                httpsetting.setUrl(url);
                httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        android.graphics.Bitmap bitmap = httpresponse.getBitmap();
                        WidgetImageCache.putBitmap(url, bitmap);
                    }

                    final _cls2 this$1;
                    private final String val$url;

                    
                    {
                        this$1 = _cls2.this;
                        url = s;
                        super();
                    }
                }
);
                httpGroup.add(httpsetting);
                continue; /* Loop/switch isn't completed */
_L6:
                Intent intent = (Intent)message.obj;
                MessagePullService messagepullservice = MessagePullService.this;
                messagepullservice.network_retry_time = 1 + messagepullservice.network_retry_time;
                startService(intent);
                if(true) goto _L1; else goto _L7
_L7:
            }

            final MessagePullService this$0;

            
            {
                this$0 = MessagePullService.this;
                super();
            }
        }
;
        mCrazyMessage = new Runnable() {

            public void run()
            {
                JSONObject jsonobject = new JSONObject();
                com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
                try
                {
                    jsonobject.put("page", "1");
                    jsonobject.put("pagesize", "50");
                }
                catch(JSONException jsonexception)
                {
                    Log.v("messagePullService", jsonexception.getMessage());
                }
                android.util.Log.d("MessagePullService", "++++mCrazyMessage");
                httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                httpsetting.setUrl(getURL("crazy", jsonobject));
                httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        ArrayList arraylist;
                        Iterator iterator;
                        arraylist = Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("carzyInfo"), 0);
                        if(Log.D)
                        {
                            StringBuilder stringbuilder = new StringBuilder("updateWidget() mCrazyMessage -->> tempList.size()-->> ");
                            int i;
                            if(arraylist == null)
                                i = 0;
                            else
                                i = arraylist.size();
                            Log.d("Temp", stringbuilder.append(i).toString());
                        }
                        if(arraylist == null || arraylist.size() <= 0) goto _L2; else goto _L1
_L1:
                        iterator = arraylist.iterator();
_L6:
                        if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mCrazyMessage -->> productFlag-->> ")).append(productFlag).toString());
                        Product product;
                        Message message;
                        if(productFlag == 0 || productFlag == 4)
                        {
                            productList = new ArrayList();
                            WidgetImageCache.clearAllBitmap();
                            productList.addAll(arraylist);
                            productFlag = 1;
                        } else
                        if(productFlag == 2)
                        {
                            productList.addAll(arraylist);
                            productFlag = 3;
                        } else
                        if(productList.size() < 1)
                            productList.addAll(arraylist);
                        handler.sendEmptyMessage(4096);
_L2:
                        return;
_L4:
                        product = (Product)iterator.next();
                        if(Log.D)
                        {
                            Log.d("Temp", (new StringBuilder("updateWidget() mCrazyMessage -->> containsKeyBitmap-->> ")).append(WidgetImageCache.containsKeyBitmap(product.getImageUrl())).toString());
                            Log.d("Temp", (new StringBuilder("updateWidget() mCrazyMessage -->> getBitmap-->> ")).append(WidgetImageCache.getBitmap(product.getImageUrl())).toString());
                        }
                        if(!WidgetImageCache.containsKeyBitmap(product.getImageUrl()) || WidgetImageCache.getBitmap(product.getImageUrl()) == null)
                        {
                            message = handler.obtainMessage(4354, product.getImageUrl());
                            handler.sendMessage(message);
                        }
                        if(true) goto _L6; else goto _L5
_L5:
                    }

                    final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                }
);
                httpGroup.add(httpsetting);
            }

            final MessagePullService this$0;


            
            {
                this$0 = MessagePullService.this;
                super();
            }
        }
;
        productFlag = 0;
        mRecommandMessage = new Runnable() {

            public void run()
            {
                JSONObject jsonobject = new JSONObject();
                com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
                try
                {
                    jsonobject.put("pin", "1");
                }
                catch(JSONException jsonexception)
                {
                    Log.v("messagePullService", jsonexception.getMessage());
                }
                httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                httpsetting.setUrl(getURL("recommend", jsonobject));
                httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        ArrayList arraylist;
                        Iterator iterator;
                        arraylist = Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("wareInfoList"), 4);
                        if(Log.D)
                        {
                            StringBuilder stringbuilder = new StringBuilder("updateWidget() mRecommandMessage -->> tempList.size()-->> ");
                            int i;
                            if(arraylist == null)
                                i = 0;
                            else
                                i = arraylist.size();
                            Log.d("Temp", stringbuilder.append(i).toString());
                        }
                        if(arraylist == null || arraylist.size() <= 0) goto _L2; else goto _L1
_L1:
                        iterator = arraylist.iterator();
_L6:
                        if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mRecommandMessage -->> productFlag-->> ")).append(productFlag).toString());
                        Product product;
                        Message message;
                        if(productFlag == 0 || productFlag == 4)
                        {
                            productList = new ArrayList();
                            productList.addAll(arraylist);
                            productFlag = 2;
                        } else
                        if(productFlag == 1)
                        {
                            productList.addAll(arraylist);
                            productFlag = 3;
                        }
                        handler.removeMessages(4096);
                        handler.sendEmptyMessage(4096);
_L2:
                        return;
_L4:
                        product = (Product)iterator.next();
                        if(Log.D)
                        {
                            Log.d("Temp", (new StringBuilder("updateWidget() mRecommandMessage -->> containsKeyBitmap-->> ")).append(WidgetImageCache.containsKeyBitmap(product.getImageUrl())).toString());
                            Log.d("Temp", (new StringBuilder("updateWidget() mRecommandMessage -->> getBitmap-->> ")).append(WidgetImageCache.getBitmap(product.getImageUrl())).toString());
                        }
                        if(!WidgetImageCache.containsKeyBitmap(product.getImageUrl()) || WidgetImageCache.getBitmap(product.getImageUrl()) == null)
                        {
                            message = handler.obtainMessage(4354, product.getImageUrl());
                            handler.sendMessage(message);
                        }
                        if(true) goto _L6; else goto _L5
_L5:
                    }

                    final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                }
);
                httpGroup.add(httpsetting);
            }

            final MessagePullService this$0;


            
            {
                this$0 = MessagePullService.this;
                super();
            }
        }
;
        mTask = new Runnable() {

            public void run()
            {
                JSONObject jsonobject = new JSONObject();
                jsonobject.put("pagesize", "10");
                jsonobject.put("page", "1");
                com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                httpsetting.setUrl(getURL("messageList", jsonobject));
                httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        JSONObjectProxy jsonobjectproxy;
                        String s;
                        jsonobjectproxy = httpresponse.getJSONObject();
                        s = defaltPreference.getString("lasteMessageReadeTime", null);
                        JSONArray jsonarray;
                        ArrayList arraylist;
                        int k;
                        jsonarray = jsonobjectproxy.getJSONArray("messageList");
                        if(Log.D)
                        {
                            StringBuilder stringbuilder = new StringBuilder("updateWidget() mTask -->> jsonMessageList.length()-->> ");
                            int i;
                            StringBuilder stringbuilder1;
                            if(jsonarray == null)
                                i = 0;
                            else
                                i = jsonarray.length();
                            Log.d("Temp", stringbuilder.append(i).toString());
                        }
                        if(jsonarray == null || jsonarray.length() == 0) goto _L2; else goto _L1
_L1:
                        arraylist = Product.toList(new JSONArrayPoxy(jsonarray), 8);
                        if(Log.D)
                        {
                            stringbuilder1 = new StringBuilder("updateWidget() mTask -->> Product-list.size()-->> ");
                            int j;
                            if(arraylist == null)
                                j = 0;
                            else
                                j = arraylist.size();
                            Log.d("Temp", stringbuilder1.append(j).toString());
                        }
                        if(arraylist != null && arraylist.size() > 0)
                            defaltPreference.edit().putString("lasteMessageReadeTime", ((Product)arraylist.get(0)).getMsgTime()).commit();
                        k = arraylist.size() - 1;
                          goto _L3
_L13:
                        Product product;
                        product = (Product)arraylist.get(k);
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> product.getMsgFlag()-->> ")).append(product.getMsgFlag()).toString());
                        if(!"0".equals(product.getMsgFlag())) goto _L5; else goto _L4
_L4:
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> lastReadTime-->> ")).append(s).toString());
                        if(s != null) goto _L7; else goto _L6
_L6:
                        JSONException jsonexception1;
                        int i1 = Integer.valueOf(product.getMsgType()).intValue();
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> msgType-->> ")).append(i1).toString());
                        if(i1 == 1)
                            product.setMessageBody(jsonarray.getJSONObject(0).getString("msgCbody"));
                        else
                            product.setMessageBody(jsonarray.getJSONObject(0).getString("msgBody"));
                        showNotification(product);
                          goto _L5
_L7:
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date;
                        Date date1;
                        date = simpledateformat.parse(product.getMsgTime());
                        date1 = simpledateformat.parse(s);
                        if(Log.D)
                        {
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> remote-->> ")).append(date.toString()).toString());
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> local-->> ")).append(date1.toString()).toString());
                        }
                        if(!date.after(date1)) goto _L5; else goto _L8
_L8:
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> product.getMsgFlag()-->> ")).append(product.getMsgFlag()).toString());
                        if(!product.getMsgFlag().equals("0")) goto _L10; else goto _L9
_L9:
                        int l;
                        l = Integer.valueOf(product.getMsgType()).intValue();
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("updateWidget() mTask -->> msgType-->> ")).append(l).toString());
                        if(l != 1) goto _L12; else goto _L11
_L11:
                        product.setMessageBody(jsonarray.getJSONObject(0).getString("msgCbody"));
_L10:
                        showNotification(product);
                          goto _L5
                        ParseException parseexception;
                        parseexception;
                        try
                        {
                            parseexception.printStackTrace();
                        }
                        // Misplaced declaration of an exception variable
                        catch(JSONException jsonexception1)
                        {
                            jsonexception1.printStackTrace();
                            break; /* Loop/switch isn't completed */
                        }
                          goto _L5
_L12:
                        product.setMessageBody(jsonarray.getJSONObject(0).getString("msgBody"));
                          goto _L10
_L3:
                        if(k >= 0) goto _L13; else goto _L2
_L2:
                        return;
_L5:
                        k--;
                          goto _L3
                        if(true) goto _L13; else goto _L14
_L14:
                    }

                    final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                }
);
                httpGroup.add(httpsetting);
_L1:
                return;
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L1
            }

            final MessagePullService this$0;


            
            {
                this$0 = MessagePullService.this;
                super();
            }
        }
;
    }

    private String getURL(String s, JSONObject jsonobject)
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  getURL() -->>");
        return (new StringBuilder(String.valueOf((new StringBuilder("http://")).append(Configuration.getProperty("host")).append("/client.action").toString()))).append("?").append("functionId=").append(s).append("&").append("body=").append(jsonobject.toString()).toString();
    }

    private void removeWidget()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  removeWidget() -->>");
        if(Log.D)
            Log.d("Temp", (new StringBuilder("cancel alarm for widget -->> ")).append(widgetPi).toString());
        if(autoUpdateWidgetThread != null)
        {
            autoUpdateWidgetThread.setStop(true);
            autoUpdateWidgetThread = null;
        }
        alarmMgr.cancel(widgetPi);
        if(productList != null)
            productList.clear();
        handler.removeMessages(4096);
        WidgetImageCache.clearAllBitmap();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("removeWidget() Service-start-->> ")).append(defaltPreference.getBoolean(getString(0x7f0901c5), false)).toString());
        if(!defaltPreference.getBoolean(getString(0x7f0901c5), false))
            stepService();
    }

    private void schudelToFleshWidget()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  schudelToFleshWidget() -->>");
        long l = System.currentTimeMillis();
        int i = Calendar.getInstance().get(11);
        setWidgetIntent();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("schudelToFleshWidget() hourOfDay-->> ")).append(i).toString());
        if(i < 9 || i > 21)
        {
            Calendar calendar = Calendar.getInstance();
            if(i > 21)
                calendar.add(5, 1);
            calendar.set(11, 9);
            long l1 = calendar.getTimeInMillis();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("set alarm for widget -->> ")).append((new Date(l1)).toLocaleString()).toString());
            alarmMgr.set(0, l1, widgetPi);
        } else
        {
            long l2 = l + 0x6ddd00L;
            if(Log.D)
                Log.d("Temp", (new StringBuilder("set alarm for widget -->> ")).append((new Date(l2)).toLocaleString()).toString());
            alarmMgr.set(0, l2, widgetPi);
        }
    }

    private void schudelToGeMessage()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  schudelToGeMessage() -->>");
        long l = System.currentTimeMillis() + msgAutoUpdateTimes;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("set alarm for message -->> ")).append((new Date(l)).toLocaleString()).toString());
        alarmMgr.set(0, l, messagePi);
    }

    private void schudelToGeMessageNextDay(int i)
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  schudelToGeMessageNextDay(hours) -->>");
        if(Log.D)
            Log.d("Temp", (new StringBuilder("cancel alarm for message -->> ")).append(messagePi).toString());
        alarmMgr.cancel(messagePi);
        long l = System.currentTimeMillis() + 0x36ee80L * (long)i;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("set alarm for message -->> ")).append((new Date(l)).toLocaleString()).toString());
        alarmMgr.set(0, l, messagePi);
    }

    public static boolean serviceIsRunInBg()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  setMsgTypeTitle() -->>");
        MyApplication myapplication = MyApplication.getInstance();
        SharedPreferences sharedpreferences = myapplication.getSharedPreferences("jdAndroidClient", 0);
        boolean flag = sharedpreferences.getBoolean(myapplication.getString(0x7f0901c5), false);
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if(sharedpreferences.getBoolean("jd_widget_deleted", true))
            flag1 = false;
        else
            flag1 = true;
        flag2 = sharedpreferences.getBoolean("login", false);
        if(Log.D)
        {
            Log.d("Temp", (new StringBuilder("serviceIsRunInBg() msgAutoUpdate-->> ")).append(flag).toString());
            Log.d("Temp", (new StringBuilder("serviceIsRunInBg() userLoginFlag-->> ")).append(flag2).toString());
            Log.d("Temp", (new StringBuilder("serviceIsRunInBg() widgetIsRun-->> ")).append(flag1).toString());
        }
        if((!flag || !flag2) && !flag1)
            flag3 = false;
        else
            flag3 = true;
        return flag3;
    }

    private void setAlarmIntent()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  setAlarmIntent() -->>");
        Intent intent = new Intent();
        intent.setClass(this, com/jindong/app/mall/service/MessagePullService);
        intent.setAction("Action_Get_Message");
        messagePi = PendingIntent.getService(this, 0, intent, 0);
    }

    private String setMsgTypeTitle(int i)
    {
        String s;
        if(Log.D)
        {
            Log.d("Temp", "MessagePullService  setMsgTypeTitle() -->>");
            Log.d("Temp", (new StringBuilder("setMsgTypeTitle() type -->>")).append(i).toString());
        }
        s = null;
        i;
        JVM INSTR tableswitch 1 3: default 64
    //                   1 66
    //                   2 77
    //                   3 88;
           goto _L1 _L2 _L3 _L4
_L1:
        return s;
_L2:
        s = getString(0x7f090095);
        continue; /* Loop/switch isn't completed */
_L3:
        s = getString(0x7f090096);
        continue; /* Loop/switch isn't completed */
_L4:
        s = getString(0x7f090097);
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void setWidgetIntent()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  setWidgetIntent() -->>");
        Intent intent = new Intent();
        intent.setClass(this, com/jindong/app/mall/service/MessagePullService);
        intent.setAction("Action_Get_Widget_Info");
        widgetPi = PendingIntent.getService(this, 0, intent, 0);
    }

    private void showNotification(Product product)
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  showNotification(product) -->>");
        Notification notification = new Notification(0x7f020021, null, System.currentTimeMillis());
        Intent intent = InterfaceBroadcastReceiver.createIntent(2, null);
        JSONObject jsonobject = new JSONObject();
        PendingIntent pendingintent;
        try
        {
            jsonobject.put("createTime", product.getMsgTime());
            jsonobject.put("msgId", product.getsMsgId());
            jsonobject.put("ordId", product.getOrderId());
            jsonobject.put("msgBody", product.getMsgBody());
            jsonobject.put("msgType", product.getMsgType());
            jsonobject.put("msgName", product.getMsgTypeName());
            defaltPreference.edit().putString("message", jsonobject.toString()).commit();
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        pendingintent = PendingIntent.getBroadcast(this, 0, intent, 0x8000000);
        notification.setLatestEventInfo(this, product.getMsgTypeName(), product.getMsgBody(), pendingintent);
        notification.flags = 20;
        mNM.notify(MOOD_NOTIFICATIONS, notification);
    }

    private void stepService()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  stepService() -->>");
        mNM.cancel(MOOD_NOTIFICATIONS);
        if(Log.D)
        {
            Log.d("Temp", (new StringBuilder("stepService() messagePi-->> ")).append(messagePi).toString());
            Log.d("Temp", (new StringBuilder("stepService() alarmMgr-->> ")).append(alarmMgr).toString());
        }
        if(messagePi != null && alarmMgr != null)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("cancel alarm for message -->> ")).append(messagePi).toString());
            alarmMgr.cancel(messagePi);
        }
        defaltPreference.edit().putBoolean("serviceStopFlag", true).commit();
        defaltPreference.edit().putString("lasteMessageReadeTime", null).commit();
        Log.d("MessagePullService", (new StringBuilder("msg+++4+++stop")).append(System.currentTimeMillis()).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("stepService() JD_WIDGET_DELETED_FLAG-->> ")).append(defaltPreference.getBoolean("jd_widget_deleted", true)).toString());
        Log.d("MessagePullService", (new StringBuilder("msg+++5+++stop")).append(System.currentTimeMillis()).toString());
        if(widgetPi != null && alarmMgr != null)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("cancel alarm for widget -->> ")).append(widgetPi).toString());
            alarmMgr.cancel(widgetPi);
        }
        stopSelf();
        Process.killProcess(Process.myPid());
    }

    private boolean updateEmptyWidget()
    {
        boolean flag;
        if(!getSharedPreferences("jdAndroidClient", 0).getBoolean("jd_widget_deleted", true))
        {
            Intent intent = new Intent("jd_widget_update_action");
            intent.putExtra("service_stoped", "service_stoped");
            sendBroadcast(intent);
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private void updateWidget()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  updateWidget() -->>");
        if(productList.size() >= 1)
        {
            Intent intent = new Intent("jd_widget_update_action");
            Product product = (Product)productList.get(updateWidgetIndex);
            intent.putExtra("productName", product.getName());
            intent.putExtra("productId", product.getId());
            intent.putExtra("productPrice", product.getJdPrice());
            if(Log.D)
                Log.d("Temp", (new StringBuilder("updateWidget() getBitmap-->> ")).append(WidgetImageCache.getBitmap(product.getImageUrl())).toString());
            if(WidgetImageCache.getBitmap(product.getImageUrl()) != null)
            {
                intent.putExtra("productImage", WidgetImageCache.getBitmap(product.getImageUrl()));
            } else
            {
                Message message = handler.obtainMessage(4354, product.getImageUrl());
                handler.sendMessage(message);
            }
            sendBroadcast(intent);
        }
    }

    public static boolean widgetIsRunInBg()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  setMsgTypeTitle() -->>");
        boolean flag;
        if(MyApplication.getInstance().getSharedPreferences("jdAndroidClient", 0).getBoolean("jd_widget_deleted", true))
            flag = false;
        else
            flag = true;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("widgetIsRunInBg() widgetIsRun-->> ")).append(flag).toString());
        return flag;
    }

    public void checkIsOldUser()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  checkIsOldUser() -->>");
        String s = defaltPreference.getString("cookies", null);
        String s1 = defaltPreference.getString("oldCookies", null);
        if(Log.D)
        {
            Log.d("Temp", (new StringBuilder("checkIsOldUser() newUserCokies-->> ")).append(s).toString());
            Log.d("Temp", (new StringBuilder("checkIsOldUser() oldUserCokies-->> ")).append(s1).toString());
        }
        if(s1 != null && !s1.equals(s))
        {
            mNM.cancel(MOOD_NOTIFICATIONS);
            defaltPreference.edit().putString("oldCookies", s).commit();
            defaltPreference.edit().putString("lasteMessageReadeTime", "").commit();
        }
    }

    public IBinder onBind(Intent intent)
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  onBind(intent) -->>");
        return mBinder;
    }

    public void onDestroy()
    {
        if(Log.D)
            Log.d("Temp", "MessagePullService  onDestroy() -->>");
        if(autoUpdateWidgetThread != null)
        {
            autoUpdateWidgetThread.setStop(true);
            autoUpdateWidgetThread = null;
            updateEmptyWidget();
        }
    }

    public void onStart(Intent intent, int i)
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("MessagePullService  onStart() -->> Process.myPid() ")).append(Process.myPid()).toString());
        if(intent == null) goto _L2; else goto _L1
_L1:
        int j;
        String s = intent.getAction();
        if(!s.contains("Message"))
            if(productList == null || productList.size() < 1)
            {
                Intent intent1 = new Intent("jd_widget_update_action");
                intent1.putExtra("loading", "loading");
                sendBroadcast(intent1);
            } else
            if(autoUpdateWidgetThread != null && handler != null && ("Action_Get_Widget_Info_next".equals(s) || "Action_Get_Widget_Info_pre".equals(s)))
                autoUpdateWidgetThread.setTime(System.currentTimeMillis());
        if(httpGroup == null)
        {
            com.jindong.app.mall.utils.HttpGroup.HttpGroupSetting httpgroupsetting = new com.jindong.app.mall.utils.HttpGroup.HttpGroupSetting();
            httpgroupsetting.setPriority(1000);
            httpgroupsetting.setType(1000);
            httpGroup = new com.jindong.app.mall.utils.HttpGroup.HttpGroupaAsynPool(httpgroupsetting);
        }
        mNM = (NotificationManager)getSystemService("notification");
        alarmMgr = (AlarmManager)getSystemService("alarm");
        defaltPreference = getSharedPreferences("jdAndroidClient", 0);
        userLoginFlag = defaltPreference.getBoolean("login", false);
        if(Log.D)
        {
            Log.d("Temp", (new StringBuilder("onStart() intent -->> ")).append(intent).toString());
            if(intent != null)
                Log.d("Temp", (new StringBuilder("onStart() intent.getAction -->> ")).append(intent.getAction()).toString());
        }
        if(intent != null && "Action_Clear_Cache".equals(intent.getAction()))
        {
            FileService.clearCacheFiles();
            DBHelperUtil.getDatabase().close();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("onStart() Service-stop -->> ")).append(defaltPreference.getBoolean("serviceStopFlag", false)).toString());
        }
        if(intent != null && "Action_restart_service".equals(intent.getAction()))
        {
            SharedPreferences sharedpreferences = getSharedPreferences("jdAndroidClient", 0);
            boolean flag;
            boolean flag1;
            if(sharedpreferences.getBoolean("login", false) && sharedpreferences.getBoolean(MyApplication.getInstance().getString(0x7f0901c5), false))
                flag = true;
            else
                flag = false;
            isNeedGetMessage = flag;
            if(sharedpreferences.getBoolean("jd_widget_deleted", true))
                flag1 = false;
            else
                flag1 = true;
            isNeedGetWidget = flag1;
            Log.d("Temp", (new StringBuilder("onStart() Service-stop -->> ")).append(defaltPreference.getBoolean("serviceStopFlag", false)).toString());
        }
        if(!isNeedGetMessage && (intent == null || intent.getAction() == null || !intent.getAction().equals("Action_Get_Message"))) goto _L4; else goto _L3
_L3:
        if(Log.D)
            Log.d("Temp", "open alarm for message -->> ");
        setAlarmIntent();
        j = Calendar.getInstance().get(11);
        if(Log.D)
            Log.d("Temp", (new StringBuilder("onStart() hourOfDay-->> ")).append(j).toString());
        if(j >= 9 && j <= 21) goto _L6; else goto _L5
_L5:
        int k;
        if(j < 9)
            k = 9 - j;
        else
            k = 33 - j;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("onStart() hours -->> ")).append(k).toString());
        if(k - 1 > 0)
            schudelToGeMessageNextDay(k - 1);
        else
            schudelToGeMessage();
_L2:
        return;
_L6:
        HttpGroup.setCookies(defaltPreference.getString("cookies", ""));
        msgAutoUpdateFlag = defaltPreference.getBoolean(getString(0x7f0901c5), false);
        if(Log.D)
        {
            Log.d("Temp", (new StringBuilder("onStart() userLoginFlag -->> ")).append(userLoginFlag).toString());
            Log.d("Temp", (new StringBuilder("onStart() msgAutoUpdateFlag -->> ")).append(msgAutoUpdateFlag).toString());
        }
        if(userLoginFlag)
        {
            if(msgAutoUpdateFlag)
            {
                String s1 = defaltPreference.getString(getString(0x7f0901c9), null);
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("onStart() times -->> ")).append(s1).toString());
                int l;
                Thread thread;
                Thread thread1;
                if(TextUtils.isEmpty(s1))
                    msgAutoUpdateTimes = 0xdbba0L;
                else
                    msgAutoUpdateTimes = 60000L * (long)Integer.valueOf(s1).intValue();
                checkIsOldUser();
                schudelToGeMessage();
                getMessageThread = new Thread(null, mTask, "getMessageThread");
                getMessageThread.start();
            } else
            {
                stepService();
            }
        } else
        {
            stepService();
        }
_L4:
        if(intent == null || intent.getAction() == null || !intent.getAction().equals("Action_Stop_Message_Service")) goto _L8; else goto _L7
_L7:
        if(!isStopWidgetText && updateEmptyWidget()) goto _L10; else goto _L9
_L9:
        stepService();
_L8:
        if(intent == null || !"Action_Get_Widget_Info".equals(intent.getAction())) goto _L12; else goto _L11
_L11:
        if(Log.D)
            Log.d("Temp", "open alarm for widget -->> ");
        if(!CommonUtil.CheckNetWork()) goto _L14; else goto _L13
_L13:
        thread = new Thread(null, mCrazyMessage, "getCrazyProdct");
        schudelToFleshWidget();
        thread.start();
        if(userLoginFlag)
        {
            thread1 = new Thread(null, mRecommandMessage, "recommand");
            thread1.start();
        }
_L12:
        if(isNeedGetWidget || intent != null && "Action_Get_Widget_Info_next".equals(intent.getAction()))
            if(!CommonUtil.CheckNetWork())
            {
                handler.sendEmptyMessageDelayed(4353, 5000L);
            } else
            {
                if(Log.D)
                    if(productList != null)
                        Log.d("Temp", (new StringBuilder("onStart() productList.size-->> ")).append(productList.size()).toString());
                    else
                        Log.d("Temp", "onStart() productList.size-->> 0");
                if(productList != null && productList.size() > 0)
                {
                    int j1 = 1 + updateWidgetIndex;
                    updateWidgetIndex = j1;
                    int k1;
                    if(j1 > productList.size() - 1)
                        k1 = 0;
                    else
                        k1 = updateWidgetIndex;
                    updateWidgetIndex = k1;
                    handler.sendEmptyMessage(4352);
                } else
                {
                    handler.sendEmptyMessageDelayed(4353, 5000L);
                }
            }
        if(intent != null && "Action_Get_Widget_Info_pre".equals(intent.getAction()))
        {
            if(Log.D)
                if(productList != null)
                    Log.d("Temp", (new StringBuilder("onStart() productList.size-->> ")).append(productList.size()).toString());
                else
                    Log.d("Temp", "onStart() productList.size-->> 0");
            if(productList != null && productList.size() > 0)
            {
                l = updateWidgetIndex - 1;
                updateWidgetIndex = l;
                int i1;
                Message message;
                if(l < 0)
                    i1 = productList.size() - 1;
                else
                    i1 = updateWidgetIndex;
                updateWidgetIndex = i1;
                handler.sendEmptyMessage(4352);
            } else
            {
                handler.sendEmptyMessageDelayed(4353, 5000L);
            }
        }
        if(intent != null && "Action_delete_widget".equals(intent.getAction()))
            removeWidget();
_L10:
        if(true) goto _L2; else goto _L14
_L14:
        if(Log.D)
            Log.d("Temp", (new StringBuilder("onStart() network_retry_time-->> ")).append(network_retry_time).toString());
        if(network_retry_time < 3)
        {
            message = handler.obtainMessage();
            message.obj = intent;
            message.what = 4355;
            handler.sendMessageDelayed(message, 5000L);
        }
          goto _L12
    }

    public static final String ACTION_APP_EXIT_CLEAR_CACHE = "Action_Clear_Cache";
    public static final String ACTION_DELETE_WIDGET = "Action_delete_widget";
    public static final String ACTION_GET_MESSAGE = "Action_Get_Message";
    public static final String ACTION_GET_WIDGET_INFO = "Action_Get_Widget_Info";
    public static final String ACTION_GET_WIDGET_Next = "Action_Get_Widget_Info_next";
    public static final String ACTION_GET_WIDGET_PRE = "Action_Get_Widget_Info_pre";
    public static final String ACTION_RESTART_SERVICE = "Action_restart_service";
    public static final String ACTION_STEP_SEEVICE = "Action_Stop_Message_Service";
    private static int MOOD_NOTIFICATIONS = 0;
    public static final String SERVICE_STOP_FLAG = "service_stoped";
    public static boolean isNeedGetMessage = false;
    public static boolean isNeedGetWidget = false;
    public static boolean isStopWidgetText = false;
    private final long DEFUALT_RETRY_INTERVAL = 0xdbba0L;
    private final long MINUTATE_RETRY_INTERVAL = 60000L;
    private final int MSG_GET_IMANGE_TO_CACHE = 4354;
    private final int MSG_NETWORK_CONNECT = 4353;
    private final int MSG_NETWORK_NOT_AVAILABLE_TIMES = 3;
    private final int MSG_NETWORK_NOT_AVAILBLE = 4355;
    private final int MSG_NOT_NOTIFICATION_TIME_MAX = 21;
    private final int MSG_NOT_NOTIFICATION_TIME_MIN = 9;
    private final int MSG_UPDATE_WIDGET = 4096;
    private final int MSG_UPDATE_WIDGET_NOT_NEXT = 4352;
    final String TAG = "MessagePullService";
    AlarmManager alarmMgr;
    private AutoUpdateWidgetThread autoUpdateWidgetThread;
    SharedPreferences defaltPreference;
    private Thread getMessageThread;
    private Handler handler;
    private com.jindong.app.mall.utils.HttpGroup.HttpGroupaAsynPool httpGroup;
    private final IBinder mBinder = new Binder() {

        protected boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            if(Log.D)
                Log.d("Temp", "MessagePullService  onTransact() -->>");
            return super.onTransact(i, parcel, parcel1, j);
        }

        final MessagePullService this$0;

            
            {
                this$0 = MessagePullService.this;
                super();
            }
    }
;
    private Runnable mCrazyMessage;
    private NotificationManager mNM;
    private Runnable mRecommandMessage;
    private Runnable mTask;
    PendingIntent messagePi;
    private boolean msgAutoUpdateFlag;
    private long msgAutoUpdateTimes;
    private int network_retry_time;
    private int productFlag;
    private ArrayList productList;
    private int updateWidgetIndex;
    private boolean userLoginFlag;
    PendingIntent widgetPi;

    static 
    {
        MOOD_NOTIFICATIONS = 4096;
    }















}
