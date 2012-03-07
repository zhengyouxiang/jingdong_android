// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.JSONArrayPoxy;
import com.jindong.app.mall.utils.JSONObjectProxy;
import java.util.ArrayList;
import org.json.JSONException;

// Referenced classes of package com.jindong.app.mall.entity:
//            ProvinceMode1, Product

public class CityMode1
{

    private CityMode1(JSONObjectProxy jsonobjectproxy, int i, Object aobj[])
    {
        i;
        JVM INSTR tableswitch 0 0: default 24
    //                   0 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        ProvinceMode1 provincemode1 = null;
        if(aobj != null && aobj.length > 0 && aobj[0] != null && (aobj[0] instanceof ProvinceMode1))
            provincemode1 = (ProvinceMode1)aobj[0];
        Product product = null;
        if(aobj != null && aobj.length > 1 && aobj[1] != null && (aobj[1] instanceof Product))
            product = (Product)aobj[1];
        setName(jsonobjectproxy.getStringOrNull("name"));
        setId(jsonobjectproxy.getIntOrNull("idCity").intValue());
        setProductId(jsonobjectproxy.getLongOrNull("skuid").longValue());
        setParent(provincemode1);
        if(product != null)
            product.putInCityMode1Map(Long.valueOf(getProductId()), this);
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
        arraylist1.add(new CityMode1(jsonarraypoxy.getJSONObject(j), i, aobj));
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

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public ProvinceMode1 getParent()
    {
        return parent;
    }

    public long getProductId()
    {
        return productId;
    }

    public void setId(int i)
    {
        id = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setParent(ProvinceMode1 provincemode1)
    {
        parent = provincemode1;
    }

    public void setProductId(long l)
    {
        productId = l;
    }

    public static final int DIRECT_WARE;
    private int id;
    private String name;
    private ProvinceMode1 parent;
    private long productId;
}
