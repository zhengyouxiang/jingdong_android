// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import org.json.*;

public class PaymentInfo
{

    public PaymentInfo()
    {
        type = 0;
        nSelected = 0;
        jbPostAddrArray = new JSONArray();
        jbPaymentDetailInfos = new JSONArray();
        jbPaymentTypes = new JSONArray();
        jbPay_After_Receive = new JSONObject();
        jbGet_By_Self = new JSONObject();
        jbPost = new JSONObject();
        jbShipments = new JSONObject();
    }

    public static String getPaymentName(int i)
    {
        String s = null;
        i;
        JVM INSTR tableswitch 1 4: default 32
    //                   1 34
    //                   2 34
    //                   3 34
    //                   4 34;
           goto _L1 _L2 _L2 _L2 _L2
_L1:
        return s;
_L2:
        s = "\u5728\u7EBF\u4ED8\u6B3E";
        if(true) goto _L1; else goto _L3
_L3:
    }

    public JSONObject getBySelf()
    {
        return jbGet_By_Self;
    }

    public JSONObject getPayAfterReceive()
    {
        return jbPay_After_Receive;
    }

    public JSONObject getPayMentType(int i)
    {
        JSONObject jsonobject = new JSONObject();
        i;
        JVM INSTR tableswitch 1 4: default 40
    //                   1 42
    //                   2 58
    //                   3 50
    //                   4 42;
           goto _L1 _L2 _L3 _L4 _L2
_L1:
        return jsonobject;
_L2:
        jsonobject = getPayAfterReceive();
        continue; /* Loop/switch isn't completed */
_L4:
        jsonobject = getBySelf();
        continue; /* Loop/switch isn't completed */
_L3:
        jsonobject = getPost();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public JSONArray getPaymentDetailInfos()
    {
        return jbPaymentDetailInfos;
    }

    public JSONArray getPaymentTypes()
    {
        return jbPaymentTypes;
    }

    public JSONObject getPost()
    {
        return jbPost;
    }

    public JSONArray getPostArray()
    {
        return jbPostAddrArray;
    }

    public int getSel()
    {
        int i;
        if(nSelected < 0)
            i = 0;
        else
            i = nSelected;
        return i;
    }

    public JSONObject getSelectedPaymentType(int i)
    {
        JSONObject jsonobject;
        int j;
        jsonobject = null;
        j = 0;
_L5:
        if(j < jbPaymentTypes.length()) goto _L2; else goto _L1
_L1:
        return jsonobject;
_L2:
        jsonobject = new JSONObject();
        int k;
        jsonobject = jbPaymentTypes.getJSONObject(j);
        k = jsonobject.getInt("Id");
        if(k == i) goto _L1; else goto _L3
_L3:
        j++;
        if(true) goto _L5; else goto _L4
_L4:
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L3
    }

    public JSONObject getShipments()
    {
        return jbShipments;
    }

    public void setGetBySelf(JSONObject jsonobject)
    {
        jbGet_By_Self = jsonobject;
    }

    public void setPayAfterReceive(JSONObject jsonobject)
    {
        jbPay_After_Receive = jsonobject;
    }

    public void setPayMentType(int i, JSONObject jsonobject)
    {
        type = i;
        i;
        JVM INSTR tableswitch 1 4: default 36
    //                   1 37
    //                   2 53
    //                   3 45
    //                   4 37;
           goto _L1 _L2 _L3 _L4 _L2
_L1:
        return;
_L2:
        setPayAfterReceive(jsonobject);
        continue; /* Loop/switch isn't completed */
_L4:
        setGetBySelf(jsonobject);
        continue; /* Loop/switch isn't completed */
_L3:
        setPost(jsonobject);
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void setPaymentDetailInfos(JSONArray jsonarray)
    {
        jbPaymentDetailInfos = new JSONArray();
        jbPaymentDetailInfos = jsonarray;
    }

    public void setPaymentTypes(JSONArray jsonarray)
    {
        jbPaymentTypes = new JSONArray();
        jbPaymentTypes = jsonarray;
    }

    public void setPost(JSONObject jsonobject)
    {
        jbPost = jsonobject;
    }

    public void setPostArray(JSONArray jsonarray)
    {
        jbPostAddrArray = jsonarray;
    }

    public void setSel(int i)
    {
        nSelected = i;
    }

    public void setShipments(JSONObject jsonobject)
    {
        jbShipments = jsonobject;
    }

    public static final int GET_BY_SELF = 3;
    public static final int PAY_AFTER_RECEIVE = 1;
    public static final int POST = 2;
    public static final String get_by_self = "\u5230\u4EAC\u4E1C\u81EA\u63D0";
    public static final String pay_after_receive = "\u8D27\u5230\u4ED8\u6B3E";
    public static final String post = "\u90AE\u5C40\u6C47\u6B3E";
    private JSONObject jbGet_By_Self;
    private JSONObject jbPay_After_Receive;
    private JSONArray jbPaymentDetailInfos;
    private JSONArray jbPaymentTypes;
    private JSONObject jbPost;
    private JSONArray jbPostAddrArray;
    private JSONObject jbShipments;
    public int nSelected;
    public int type;
}
