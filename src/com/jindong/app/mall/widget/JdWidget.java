// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.service.MessagePullService;
import com.jindong.app.mall.utils.Log;

public class JdWidget extends AppWidgetProvider
{

    public JdWidget()
    {
    }

    public void onDisabled(Context context)
    {
        context.getSharedPreferences("jdAndroidClient", 0).edit().putBoolean("jd_widget_deleted", true).commit();
        Intent intent = new Intent("Action_delete_widget");
        intent.setClass(context, com/jindong/app/mall/service/MessagePullService);
        context.startService(intent);
        super.onDisabled(context);
    }

    public void onEnabled(Context context)
    {
        super.onEnabled(context);
        Intent intent = new Intent("Action_Get_Widget_Info");
        intent.setClass(context, com/jindong/app/mall/service/MessagePullService);
        context.startService(intent);
        context.getSharedPreferences("jdAndroidClient", 0).edit().putBoolean("jd_widget_deleted", false).commit();
    }

    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);
        if(Log.D)
            android.util.Log.d("Temp", "JdWidget onReceive() -->> ");
        if(!"service_stoped".equals(intent.getStringExtra("service_stoped"))) goto _L2; else goto _L1
_L1:
        product = null;
        isServiceStop = true;
_L4:
        onUpdate(context, AppWidgetManager.getInstance(context), null);
        return;
_L2:
        if("android.appwidget.action.APPWIDGET_UPDATE".equals(intent.getAction()))
        {
            if(Log.D)
                android.util.Log.d("Temp", "JdWidget onReceive() 1 -->> ");
            super.onReceive(context, intent);
            Intent intent1 = new Intent("Action_Get_Widget_Info");
            intent1.setClass(context, com/jindong/app/mall/service/MessagePullService);
            context.startService(intent1);
        } else
        if("jd_widget_update_action".equals(intent.getAction()))
        {
            if(Log.D)
                android.util.Log.d("Temp", "JdWidget onReceive() 2 -->> ");
            if(!"loading".equals(intent.getStringExtra("loading")))
            {
                product = new Product();
                product.setName(intent.getStringExtra("productName"));
                product.setId(Long.valueOf(intent.getLongExtra("productId", 0L)));
                product.setJdPrice(intent.getStringExtra("productPrice"));
                bitmap = (Bitmap)intent.getParcelableExtra("productImage");
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int ai[])
    {
        if(Log.D)
            android.util.Log.d("Temp", "JdWidget onUpdate() -->> ");
        Bundle bundle = new Bundle();
        RemoteViews remoteviews = new RemoteViews(context.getPackageName(), 0x7f03008a);
        remoteviews.setViewVisibility(0x7f0c02cf, 0);
        if(bitmap != null)
            remoteviews.setImageViewBitmap(0x7f0c02cf, bitmap);
        else
            remoteviews.setImageViewResource(0x7f0c02cf, 0x7f02009b);
        if(product != null)
        {
            MessagePullService.isStopWidgetText = false;
            remoteviews.setTextViewText(0x7f0c02d0, product.getName());
            remoteviews.setTextViewText(0x7f0c02d1, (new StringBuilder("\uFFE5")).append(product.getJdPrice()).toString());
            bundle.putString("pid", "");
            bundle.putString("unionId", "");
            bundle.putString("subunionId", "");
            Intent intent;
            Intent intent1;
            Intent intent2;
            long l;
            if(product.getId() == null)
                l = 0L;
            else
                l = product.getId().longValue();
            bundle.putLong("id", l);
            remoteviews.setOnClickPendingIntent(0x7f0c02ce, PendingIntent.getBroadcast(context, 0, InterfaceBroadcastReceiver.createIntent(4, bundle), 0x8000000));
        } else
        {
            PendingIntent pendingintent;
            if(isServiceStop)
            {
                remoteviews.setTextViewText(0x7f0c02d0, context.getString(0x7f0901de));
                remoteviews.setViewVisibility(0x7f0c02cf, 8);
                remoteviews.setTextViewText(0x7f0c02d1, "");
                Intent intent3 = new Intent();
                intent3.setAction("Action_restart_service");
                intent3.setClass(context, com/jindong/app/mall/service/MessagePullService);
                pendingintent = PendingIntent.getService(context, 0, intent3, 0x8000000);
                MessagePullService.isStopWidgetText = true;
                Intent intent4 = new Intent();
                intent4.setClass(MyApplication.getInstance(), com/jindong/app/mall/service/MessagePullService);
                intent4.setAction("Action_Stop_Message_Service");
                MyApplication.getInstance().startService(intent4);
            } else
            {
                remoteviews.setTextViewText(0x7f0c02d0, context.getString(0x7f09007c));
                remoteviews.setTextViewText(0x7f0c02d1, "");
                pendingintent = PendingIntent.getBroadcast(context, 0, new Intent(), 0x8000000);
            }
            remoteviews.setOnClickPendingIntent(0x7f0c02ce, pendingintent);
        }
        intent = InterfaceBroadcastReceiver.createIntent(3, null);
        Log.i("extras", (new StringBuilder(String.valueOf(intent.getExtras().getInt("moduleId")))).toString());
        remoteviews.setOnClickPendingIntent(0x7f0c02ca, PendingIntent.getBroadcast(context, 1, intent, 0x8000000));
        intent1 = new Intent();
        intent1.setAction("Action_Get_Widget_Info_pre");
        intent1.setClass(context, com/jindong/app/mall/service/MessagePullService);
        remoteviews.setOnClickPendingIntent(0x7f0c02d3, PendingIntent.getService(context, 0, intent1, 0x8000000));
        intent2 = new Intent();
        intent2.setAction("Action_Get_Widget_Info_next");
        intent2.setClass(context, com/jindong/app/mall/service/MessagePullService);
        remoteviews.setOnClickPendingIntent(0x7f0c02d4, PendingIntent.getService(context, 3, intent2, 0x8000000));
        appwidgetmanager.updateAppWidget(new ComponentName(context, com/jindong/app/mall/widget/JdWidget), remoteviews);
        mAppWidgetManager = appwidgetmanager;
    }

    public IBinder peekService(Context context, Intent intent)
    {
        return super.peekService(context, intent);
    }

    public static final String UPDATE_ACTION_NAME = "jd_widget_update_action";
    public static final String UPDATE_LOADING = "loading";
    private final String TAG = com/jindong/app/mall/widget/JdWidget.getSimpleName();
    int appWidgetIds[];
    Bitmap bitmap;
    private boolean isServiceStop;
    AppWidgetManager mAppWidgetManager;
    Product product;
}
