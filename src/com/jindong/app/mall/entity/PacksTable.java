// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;


public class PacksTable
{

    public PacksTable()
    {
    }

    public PacksTable(long l, String s, int i, int j)
    {
        childCount = j;
        name = s;
        buyCount = i;
        packId = l;
    }

    public static final String TB_COLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_COLOUMN_CHILD_COUNT = "childCount";
    public static final String TB_COLOUMN_MAIN_SKU_ID = "mainSkuId";
    public static final String TB_COLOUMN_PACK_ID = "packId";
    public static final String TB_COLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_COLOUMN_PRODUCT_NAME = "name";
    public static final String TB_COLOUMN_USER_NAME = "userName";
    public static final String TB_PACKS_TABLE = "PacksTable";
    public int buyCount;
    public int childCount;
    public long mainSkuId;
    public String name;
    public long packId;
    public long productCode;
}
