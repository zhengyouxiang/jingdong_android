// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;


public class CartTable
{

    public CartTable()
    {
    }

    public CartTable(String s, long l, int i)
    {
        productCode = l;
        name = s;
        buyCount = i;
    }

    public CartTable(String s, long l, int i, long l1)
    {
        productCode = l;
        name = s;
        buyCount = i;
        packId = l1;
    }

    public static final String TB_CART_TABLE = "CartTable";
    public static final String TB_CLOUMN_BUY_COUNT = "buyCount";
    public static final String TB_CLOUMN_EXTEND = "extendProt";
    public static final String TB_CLOUMN_PACKS_CODE = "packId";
    public static final String TB_CLOUMN_PRODUCT_CODE = "productCode";
    public static final String TB_CLOUMN_PRODUCT_NAME = "name";
    public static final String TB_CLOUMN_USER_NAME = "userName";
    public int buyCount;
    public String name;
    public long packId;
    public long productCode;
}
