// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.jindong.app.mall.utils.*;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

public class Commercial
    implements Serializable
{

    public Commercial()
    {
    }

    public Commercial(JSONObjectProxy jsonobjectproxy, int i)
    {
        update(jsonobjectproxy, i);
    }

    public static boolean isAdd(Commercial commercial)
    {
        boolean flag;
        if(TextUtils.isEmpty(commercial.getId()))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        if(jsonarraypoxy != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist2 = null;
_L7:
        return arraylist2;
_L2:
        ArrayList arraylist = null;
        ArrayList arraylist1 = new ArrayList();
        int j = 0;
_L4:
        if(j >= jsonarraypoxy.length())
        {
            arraylist = arraylist1;
            break; /* Loop/switch isn't completed */
        }
        Commercial commercial = new Commercial(jsonarraypoxy.getJSONObject(j), i);
        if(isAdd(commercial))
            arraylist1.add(commercial);
        j++;
        if(true) goto _L4; else goto _L3
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L5:
        if(Log.V)
            Log.v("Ware", jsonexception1.getMessage());
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        arraylist = arraylist1;
        if(true) goto _L5; else goto _L3
_L3:
        arraylist2 = arraylist;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public Drawable getDrawable()
    {
        return drawable;
    }

    public String getFeature()
    {
        return feature;
    }

    public String getHorizontalImg()
    {
        return horizontalImg;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getVerticalImg()
    {
        return verticalImg;
    }

    public String getWareIds()
    {
        return wareIds;
    }

    public void setDrawable(Drawable drawable1)
    {
        drawable = drawable1;
    }

    public void setFeature(String s)
    {
        feature = s;
    }

    public void setHorizontalImg(String s)
    {
        horizontalImg = s;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setVerticalImg(String s)
    {
        verticalImg = s;
    }

    public void setWareIds(String s)
    {
        wareIds = s;
    }

    public void update(JSONObjectProxy jsonobjectproxy, int i)
    {
        id = jsonobjectproxy.getStringOrNull("activityId");
        horizontalImg = jsonobjectproxy.getStringOrNull("horizontalImag");
        verticalImg = jsonobjectproxy.getStringOrNull("verticalImage");
    }

    public static final int FOCUSACTIVITY = 0;
    private static final long serialVersionUID = 1L;
    private Drawable drawable;
    private String feature;
    private String horizontalImg;
    private String id;
    private String title;
    private String verticalImg;
    private String wareIds;
}
