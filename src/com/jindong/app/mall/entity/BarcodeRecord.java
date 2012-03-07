// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import java.io.Serializable;

// Referenced classes of package com.jindong.app.mall.entity:
//            Product

public class BarcodeRecord
    implements Serializable
{

    public BarcodeRecord()
    {
        productName = "\u67E5\u8BE2\u4E2D...";
    }

    public String getContent()
    {
        return content;
    }

    public String getFormat()
    {
        return format;
    }

    public Product getProduct()
    {
        return product;
    }

    public String getProductName()
    {
        String s;
        if(product != null)
            s = product.getName();
        else
            s = productName;
        return s;
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setFormat(String s)
    {
        format = s;
    }

    public void setProduct(Product product1)
    {
        product = product1;
    }

    public void setProductName(String s)
    {
        productName = s;
    }

    private static final long serialVersionUID = 0x8835ce30452ccbd6L;
    private String content;
    private String format;
    private Product product;
    private String productName;
}
