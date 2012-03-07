// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.gis;

import com.jindong.app.mall.utils.JSONObjectProxy;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderJsonResponseHandler
{

    public OrderJsonResponseHandler(GisUrlUtil.GisOrderInfo gisorderinfo)
    {
        gisOrderInfo = gisorderinfo;
    }

    private void setValue(JSONObjectProxy jsonobjectproxy, GisUrlUtil.OrderSiteInfo ordersiteinfo)
    {
        if(jsonobjectproxy != null)
        {
            ordersiteinfo.orderid = jsonobjectproxy.getStringOrNull("orderid");
            ordersiteinfo.provincename = jsonobjectproxy.getStringOrNull("provincename");
            ordersiteinfo.cityname = jsonobjectproxy.getStringOrNull("cityname");
            ordersiteinfo.address = jsonobjectproxy.getStringOrNull("address");
            ordersiteinfo.id = jsonobjectproxy.getStringOrNull("id");
            Integer integer = jsonobjectproxy.getIntOrNull("type");
            int i;
            if(integer == null)
                i = 0;
            else
                i = integer.intValue();
            ordersiteinfo.type = i;
            try
            {
                ordersiteinfo.lngx = jsonobjectproxy.getDouble("lngx");
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            try
            {
                ordersiteinfo.laty = jsonobjectproxy.getDouble("laty");
            }
            catch(JSONException jsonexception1)
            {
                jsonexception1.printStackTrace();
            }
        }
    }

    public void parseJsonString(JSONObjectProxy jsonobjectproxy)
    {
        String s = jsonobjectproxy.getStringOrNull("getOrders");
        JSONObjectProxy jsonobjectproxy1 = new JSONObjectProxy(new JSONObject(s));
        JSONObjectProxy jsonobjectproxy2 = jsonobjectproxy1.getJSONObjectOrNull("customer");
        JSONObjectProxy jsonobjectproxy3 = jsonobjectproxy1.getJSONObjectOrNull("cangku");
        JSONObjectProxy jsonobjectproxy4 = jsonobjectproxy1.getJSONObjectOrNull("zhandian");
        JSONObjectProxy jsonobjectproxy5 = jsonobjectproxy1.getJSONObjectOrNull("psy");
        setValue(jsonobjectproxy2, gisOrderInfo.customer);
        setValue(jsonobjectproxy3, gisOrderInfo.cangku);
        setValue(jsonobjectproxy4, gisOrderInfo.zhandian);
        if(jsonobjectproxy5 != null)
        {
            gisOrderInfo.gisContactInfo.id = jsonobjectproxy5.getStringOrNull("id");
            gisOrderInfo.gisContactInfo.orderid = jsonobjectproxy5.getStringOrNull("orderid");
            gisOrderInfo.gisContactInfo.telephone = jsonobjectproxy5.getStringOrNull("telephone");
            gisOrderInfo.gisContactInfo.name = jsonobjectproxy5.getStringOrNull("name");
        }
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public GisUrlUtil.GisOrderInfo gisOrderInfo;
}
