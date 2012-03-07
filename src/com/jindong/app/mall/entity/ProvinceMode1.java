// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.JSONArrayPoxy;
import com.jindong.app.mall.utils.JSONObjectProxy;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;

// Referenced classes of package com.jindong.app.mall.entity:
//            Product, CityMode1

public class ProvinceMode1
{

    private ProvinceMode1(JSONObjectProxy jsonobjectproxy, int i, Object aobj[])
    {
        i;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        Product product = null;
        if(aobj != null && aobj.length > 0 && aobj[0] != null && (aobj[0] instanceof Product))
            product = (Product)aobj[0];
        setName(jsonobjectproxy.getStringOrNull("name"));
        setId(jsonobjectproxy.getIntOrNull("idProvince").intValue());
        JSONArrayPoxy jsonarraypoxy = jsonobjectproxy.getJSONArrayOrNull("idCityes");
        Object aobj1[] = new Object[2];
        aobj1[0] = this;
        aobj1[1] = product;
        setChildren(CityMode1.toList(jsonarraypoxy, i, aobj1));
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        return toList(jsonarraypoxy, i, null);
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i, Object aobj[])
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
        arraylist1.add(new ProvinceMode1(jsonarraypoxy.getJSONObject(j), i, aobj));
        j++;
        if(true) goto _L4; else goto _L3
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L5:
        jsonexception1.printStackTrace();
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

    public ArrayList getChildren()
    {
        return children;
    }

    public Integer getCityMode1IndexById(int i)
    {
        return (Integer)childrenMap.get(Integer.valueOf(i));
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setChildren(ArrayList arraylist)
    {
        children = arraylist;
        childrenMap = new HashMap();
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                return;
            CityMode1 citymode1 = (CityMode1)arraylist.get(i);
            childrenMap.put(Integer.valueOf(citymode1.getId()), Integer.valueOf(i));
            i++;
        } while(true);
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public static final int DIRECT_WARE;
    private ArrayList children;
    private HashMap childrenMap;
    private int id;
    private String name;
}
