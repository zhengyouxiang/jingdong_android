// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class ProductParsedResult extends ParsedResult
{

    ProductParsedResult(String s)
    {
        this(s, s);
    }

    ProductParsedResult(String s, String s1)
    {
        super(ParsedResultType.PRODUCT);
        productID = s;
        normalizedProductID = s1;
    }

    public String getDisplayResult()
    {
        return productID;
    }

    public String getNormalizedProductID()
    {
        return normalizedProductID;
    }

    public String getProductID()
    {
        return productID;
    }

    private final String normalizedProductID;
    private final String productID;
}
