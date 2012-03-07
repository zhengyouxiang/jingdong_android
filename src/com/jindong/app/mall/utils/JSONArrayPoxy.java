// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import org.json.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            JSONObjectProxy

public class JSONArrayPoxy extends JSONArray
{

    public JSONArrayPoxy()
    {
        jsonArray = new JSONArray();
    }

    public JSONArrayPoxy(JSONArray jsonarray)
    {
        jsonArray = jsonarray;
    }

    public boolean equals(Object obj)
    {
        return jsonArray.equals(obj);
    }

    public Object get(int i)
        throws JSONException
    {
        return jsonArray.get(i);
    }

    public boolean getBoolean(int i)
        throws JSONException
    {
        return jsonArray.getBoolean(i);
    }

    public double getDouble(int i)
        throws JSONException
    {
        return jsonArray.getDouble(i);
    }

    public int getInt(int i)
        throws JSONException
    {
        return jsonArray.getInt(i);
    }

    public JSONArrayPoxy getJSONArray(int i)
        throws JSONException
    {
        return new JSONArrayPoxy(jsonArray.getJSONArray(i));
    }

    public volatile JSONArray getJSONArray(int i)
        throws JSONException
    {
        return getJSONArray(i);
    }

    public JSONArrayPoxy getJSONArrayOrNull(int i)
    {
        JSONArrayPoxy jsonarraypoxy;
        try
        {
            jsonarraypoxy = new JSONArrayPoxy(jsonArray.getJSONArray(i));
        }
        catch(JSONException jsonexception)
        {
            jsonarraypoxy = null;
        }
        return jsonarraypoxy;
    }

    public JSONObjectProxy getJSONObject(int i)
        throws JSONException
    {
        return new JSONObjectProxy(new JSONObjectProxy(jsonArray.getJSONObject(i)));
    }

    public volatile JSONObject getJSONObject(int i)
        throws JSONException
    {
        return getJSONObject(i);
    }

    public JSONObjectProxy getJSONObjectOrNull(int i)
    {
        JSONObjectProxy jsonobjectproxy;
        try
        {
            jsonobjectproxy = new JSONObjectProxy(new JSONObjectProxy(jsonArray.getJSONObject(i)));
        }
        catch(JSONException jsonexception)
        {
            jsonobjectproxy = null;
        }
        return jsonobjectproxy;
    }

    public long getLong(int i)
        throws JSONException
    {
        return jsonArray.getLong(i);
    }

    public String getString(int i)
        throws JSONException
    {
        return jsonArray.getString(i);
    }

    public int hashCode()
    {
        return jsonArray.hashCode();
    }

    public boolean isNull(int i)
    {
        return jsonArray.isNull(i);
    }

    public String join(String s)
        throws JSONException
    {
        return jsonArray.join(s);
    }

    public int length()
    {
        return jsonArray.length();
    }

    public Object opt(int i)
    {
        return jsonArray.opt(i);
    }

    public boolean optBoolean(int i)
    {
        return jsonArray.optBoolean(i);
    }

    public boolean optBoolean(int i, boolean flag)
    {
        return jsonArray.optBoolean(i, flag);
    }

    public double optDouble(int i)
    {
        return jsonArray.optDouble(i);
    }

    public double optDouble(int i, double d)
    {
        return jsonArray.optDouble(i, d);
    }

    public int optInt(int i)
    {
        return jsonArray.optInt(i);
    }

    public int optInt(int i, int j)
    {
        return jsonArray.optInt(i, j);
    }

    public JSONArray optJSONArray(int i)
    {
        return jsonArray.optJSONArray(i);
    }

    public JSONObject optJSONObject(int i)
    {
        return jsonArray.optJSONObject(i);
    }

    public long optLong(int i)
    {
        return jsonArray.optLong(i);
    }

    public long optLong(int i, long l)
    {
        return jsonArray.optLong(i, l);
    }

    public String optString(int i)
    {
        return jsonArray.optString(i);
    }

    public String optString(int i, String s)
    {
        return jsonArray.optString(i, s);
    }

    public JSONArray put(double d)
        throws JSONException
    {
        return jsonArray.put(d);
    }

    public JSONArray put(int i)
    {
        return jsonArray.put(i);
    }

    public JSONArray put(int i, double d)
        throws JSONException
    {
        return jsonArray.put(i, d);
    }

    public JSONArray put(int i, int j)
        throws JSONException
    {
        return jsonArray.put(i, j);
    }

    public JSONArray put(int i, long l)
        throws JSONException
    {
        return jsonArray.put(i, l);
    }

    public JSONArray put(int i, Object obj)
        throws JSONException
    {
        return jsonArray.put(i, obj);
    }

    public JSONArray put(int i, boolean flag)
        throws JSONException
    {
        return jsonArray.put(i, flag);
    }

    public JSONArray put(long l)
    {
        return jsonArray.put(l);
    }

    public JSONArray put(Object obj)
    {
        return jsonArray.put(obj);
    }

    public JSONArray put(boolean flag)
    {
        return jsonArray.put(flag);
    }

    public JSONObject toJSONObject(JSONArray jsonarray)
        throws JSONException
    {
        return jsonArray.toJSONObject(jsonarray);
    }

    public String toString()
    {
        return jsonArray.toString();
    }

    public String toString(int i)
        throws JSONException
    {
        return jsonArray.toString(i);
    }

    private JSONArray jsonArray;
}
