// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.gis;

import com.jindong.app.mall.utils.JSONObjectProxy;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderTimeJsonResponseHandler
{

    public OrderTimeJsonResponseHandler(GisUrlUtil.OrderStatetime orderstatetime)
    {
        orderStatetime = orderstatetime;
    }

    public void parseJsonString(JSONObjectProxy jsonobjectproxy)
    {
        String s = jsonobjectproxy.getStringOrNull("getOrderState");
        JSONObjectProxy jsonobjectproxy1 = new JSONObjectProxy(new JSONObject(s));
        orderStatetime.orderid = jsonobjectproxy1.getStringOrNull("orderid");
        orderStatetime.state = jsonobjectproxy1.getIntOrNull("state").intValue();
        orderStatetime.begintime = jsonobjectproxy1.getStringOrNull("begintime");
        orderStatetime.endtime = jsonobjectproxy1.getStringOrNull("endtime");
        orderStatetime.updatetime = jsonobjectproxy1.getStringOrNull("updatetime");
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public GisUrlUtil.OrderStatetime orderStatetime;
}
