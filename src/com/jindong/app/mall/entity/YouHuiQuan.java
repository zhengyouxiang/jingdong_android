// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import org.json.JSONObject;

public class YouHuiQuan
{
    static class dongQuan
    {

        public static JSONObject jbDongQuanInfo;
        public static int nTotalCount = 0;
        public static int nUsedCount = 0;


        public dongQuan()
        {
            jbDongQuanInfo = new JSONObject();
        }
    }

    static class jingQuan
    {

        public static JSONObject jbJingQuanInfo = null;
        public static int nTotalCount = 0;
        public static int nUsedCount = 0;


        public jingQuan()
        {
            jbJingQuanInfo = new JSONObject();
        }
    }

    static class liPinKa
    {

        public static JSONObject jbLiPinKaInfo;
        public static double nTotalBalance = 0.0D;
        public static double nUsedBalance = 0.0D;


        public liPinKa()
        {
            jbLiPinKaInfo = new JSONObject();
        }
    }


    public YouHuiQuan()
    {
    }

    public JSONObject getJingDongQuanDetailInfo(int i)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 28
    //                   2 35;
           goto _L1 _L2 _L3
_L1:
        JSONObject jsonobject = null;
_L5:
        return jsonobject;
_L2:
        jsonobject = jingQuan.jbJingQuanInfo;
        continue; /* Loop/switch isn't completed */
_L3:
        jsonobject = dongQuan.jbDongQuanInfo;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public int getJingDongQuanTotalCount(int i)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 28
    //                   2 35;
           goto _L1 _L2 _L3
_L1:
        int j = 0;
_L5:
        return j;
_L2:
        j = jingQuan.nTotalCount;
        continue; /* Loop/switch isn't completed */
_L3:
        j = dongQuan.nTotalCount;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public int getJingDongQuanUsedCount(int i)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 29
    //                   2 36;
           goto _L1 _L2 _L3
_L1:
        int j = -1;
_L5:
        return j;
_L2:
        j = jingQuan.nUsedCount;
        continue; /* Loop/switch isn't completed */
_L3:
        j = dongQuan.nUsedCount;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public JSONObject getLiPinKaInfo()
    {
        return liPinKa.jbLiPinKaInfo;
    }

    public double getLiPinKaTotalMoney()
    {
        return liPinKa.nTotalBalance;
    }

    public double getLiPinKaUsedBalance()
    {
        return liPinKa.nUsedBalance;
    }

    public void setJingDongQuanDetailInfo(int i, JSONObject jsonobject)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 25
    //                   2 29;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        jingQuan.jbJingQuanInfo = jsonobject;
_L3:
        dongQuan.jbDongQuanInfo = jsonobject;
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setJingDongQuanTotalCount(int i, int j)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 25
    //                   2 32;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        jingQuan.nTotalCount = j;
        continue; /* Loop/switch isn't completed */
_L3:
        dongQuan.nTotalCount = j;
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setJingDongQuanUsedCount(int i, int j)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 25
    //                   2 32;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        jingQuan.nUsedCount = j;
        continue; /* Loop/switch isn't completed */
_L3:
        dongQuan.nUsedCount = j;
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setLiPinKaInfo(JSONObject jsonobject)
    {
        liPinKa.jbLiPinKaInfo = jsonobject;
    }

    public void setLiPinKaTotalMoney(double d)
    {
        liPinKa.nTotalBalance = d;
    }

    public void setLiPinKaUsedBalance(double d)
    {
        liPinKa.nUsedBalance = d;
    }

    public JSONObject Coupons;
    private final int DONG_QUAN = 2;
    private final int JING_QUAN = 1;
    private final int LI_PIN_KA = 3;
    public JSONObject LipinKas;
}
