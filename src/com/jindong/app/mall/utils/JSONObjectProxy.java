// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.util.Iterator;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, JSONArrayPoxy

public class JSONObjectProxy extends JSONObject
{

    public JSONObjectProxy()
    {
        jsonObject = new JSONObject();
    }

    public JSONObjectProxy(JSONObject jsonobject)
    {
        jsonObject = jsonobject;
    }

    public JSONObject accumulate(String s, Object obj)
        throws JSONException
    {
        return jsonObject.accumulate(s, obj);
    }

    public boolean equals(Object obj)
    {
        return jsonObject.equals(obj);
    }

    public Object get(String s)
        throws JSONException
    {
        return jsonObject.get(s);
    }

    public boolean getBoolean(String s)
        throws JSONException
    {
        return jsonObject.getBoolean(s);
    }

    public Boolean getBooleanOrNull(String s)
    {
        Boolean boolean2 = Boolean.valueOf(jsonObject.getBoolean(s));
        Boolean boolean1 = boolean2;
_L2:
        return boolean1;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v(com/jindong/app/mall/utils/JSONObjectProxy.getName(), jsonexception.getMessage());
        boolean1 = Boolean.valueOf(false);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public double getDouble(String s)
        throws JSONException
    {
        return jsonObject.getDouble(s);
    }

    public int getInt(String s)
        throws JSONException
    {
        return jsonObject.getInt(s);
    }

    public Integer getIntOrNull(String s)
    {
        Integer integer1 = Integer.valueOf(jsonObject.getInt(s));
        Integer integer = integer1;
_L2:
        return integer;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v(com/jindong/app/mall/utils/JSONObjectProxy.getName(), jsonexception.getMessage());
        integer = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public JSONArrayPoxy getJSONArray(String s)
        throws JSONException
    {
        return new JSONArrayPoxy(jsonObject.getJSONArray(s));
    }

    public volatile JSONArray getJSONArray(String s)
        throws JSONException
    {
        return getJSONArray(s);
    }

    public JSONArrayPoxy getJSONArrayOrNull(String s)
    {
        JSONArrayPoxy jsonarraypoxy;
        try
        {
            jsonarraypoxy = new JSONArrayPoxy(jsonObject.getJSONArray(s));
        }
        catch(JSONException jsonexception)
        {
            jsonarraypoxy = null;
        }
        return jsonarraypoxy;
    }

    public JSONObjectProxy getJSONObject(String s)
        throws JSONException
    {
        return new JSONObjectProxy(jsonObject.getJSONObject(s));
    }

    public volatile JSONObject getJSONObject(String s)
        throws JSONException
    {
        return getJSONObject(s);
    }

    public JSONObjectProxy getJSONObjectOrNull(String s)
    {
        JSONObjectProxy jsonobjectproxy;
        try
        {
            jsonobjectproxy = new JSONObjectProxy(jsonObject.getJSONObject(s));
        }
        catch(JSONException jsonexception)
        {
            jsonobjectproxy = null;
        }
        return jsonobjectproxy;
    }

    public long getLong(String s)
        throws JSONException
    {
        return jsonObject.getLong(s);
    }

    public Long getLongOrNull(String s)
    {
        Long long2 = Long.valueOf(jsonObject.getLong(s));
        Long long1 = long2;
_L2:
        return long1;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v(com/jindong/app/mall/utils/JSONObjectProxy.getName(), jsonexception.getMessage());
        long1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getString(String s)
        throws JSONException
    {
        return jsonObject.getString(s);
    }

    public String getStringOrNull(String s)
    {
        String s2;
        boolean flag;
        s2 = jsonObject.getString(s);
        flag = "null".equals(s2);
        String s1;
        if(flag)
            s1 = null;
        else
            s1 = s2;
_L2:
        return s1;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v(com/jindong/app/mall/utils/JSONObjectProxy.getName(), jsonexception.getMessage());
        s1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean has(String s)
    {
        return jsonObject.has(s);
    }

    public int hashCode()
    {
        return jsonObject.hashCode();
    }

    public boolean isNull(String s)
    {
        return jsonObject.isNull(s);
    }

    public Iterator keys()
    {
        return jsonObject.keys();
    }

    public int length()
    {
        return jsonObject.length();
    }

    public JSONArray names()
    {
        return jsonObject.names();
    }

    public Object opt(String s)
    {
        return jsonObject.opt(s);
    }

    public boolean optBoolean(String s)
    {
        return jsonObject.optBoolean(s);
    }

    public boolean optBoolean(String s, boolean flag)
    {
        return jsonObject.optBoolean(s, flag);
    }

    public double optDouble(String s)
    {
        return jsonObject.optDouble(s);
    }

    public double optDouble(String s, double d)
    {
        return jsonObject.optDouble(s, d);
    }

    public int optInt(String s)
    {
        return jsonObject.optInt(s);
    }

    public int optInt(String s, int i)
    {
        return jsonObject.optInt(s, i);
    }

    public JSONArray optJSONArray(String s)
    {
        return jsonObject.optJSONArray(s);
    }

    public JSONObject optJSONObject(String s)
    {
        return jsonObject.optJSONObject(s);
    }

    public long optLong(String s)
    {
        return jsonObject.optLong(s);
    }

    public long optLong(String s, long l)
    {
        return jsonObject.optLong(s, l);
    }

    public String optString(String s)
    {
        return jsonObject.optString(s);
    }

    public String optString(String s, String s1)
    {
        return jsonObject.optString(s, s1);
    }

    public JSONObject put(String s, double d)
        throws JSONException
    {
        return jsonObject.put(s, d);
    }

    public JSONObject put(String s, int i)
        throws JSONException
    {
        return jsonObject.put(s, i);
    }

    public JSONObject put(String s, long l)
        throws JSONException
    {
        return jsonObject.put(s, l);
    }

    public JSONObject put(String s, Object obj)
        throws JSONException
    {
        return jsonObject.put(s, obj);
    }

    public JSONObject put(String s, boolean flag)
        throws JSONException
    {
        return jsonObject.put(s, flag);
    }

    public JSONObject putOpt(String s, Object obj)
        throws JSONException
    {
        return jsonObject.putOpt(s, obj);
    }

    public Object remove(String s)
    {
        return jsonObject.remove(s);
    }

    public JSONArray toJSONArray(JSONArray jsonarray)
        throws JSONException
    {
        return jsonObject.toJSONArray(jsonarray);
    }

    public String toString()
    {
        return jsonObject.toString();
    }

    public String toString(int i)
        throws JSONException
    {
        return jsonObject.toString(i);
    }

    private JSONObject jsonObject;
}
