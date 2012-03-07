// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import java.util.Hashtable;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public class ExpandedProductParsedResult extends ParsedResult
{

    ExpandedProductParsedResult()
    {
        super(ParsedResultType.PRODUCT);
        productID = "";
        sscc = "";
        lotNumber = "";
        productionDate = "";
        packagingDate = "";
        bestBeforeDate = "";
        expirationDate = "";
        weight = "";
        weightType = "";
        weightIncrement = "";
        price = "";
        priceIncrement = "";
        priceCurrency = "";
        uncommonAIs = new Hashtable();
    }

    public ExpandedProductParsedResult(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9, String s10, String s11, String s12, Hashtable hashtable)
    {
        super(ParsedResultType.PRODUCT);
        productID = s;
        sscc = s1;
        lotNumber = s2;
        productionDate = s3;
        packagingDate = s4;
        bestBeforeDate = s5;
        expirationDate = s6;
        weight = s7;
        weightType = s8;
        weightIncrement = s9;
        price = s10;
        priceIncrement = s11;
        priceCurrency = s12;
        uncommonAIs = hashtable;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(!(obj instanceof ExpandedProductParsedResult))
        {
            flag = false;
        } else
        {
            ExpandedProductParsedResult expandedproductparsedresult = (ExpandedProductParsedResult)obj;
            if(productID.equals(expandedproductparsedresult.productID) && sscc.equals(expandedproductparsedresult.sscc) && lotNumber.equals(expandedproductparsedresult.lotNumber) && productionDate.equals(expandedproductparsedresult.productionDate) && bestBeforeDate.equals(expandedproductparsedresult.bestBeforeDate) && expirationDate.equals(expandedproductparsedresult.expirationDate) && weight.equals(expandedproductparsedresult.weight) && weightType.equals(expandedproductparsedresult.weightType) && weightIncrement.equals(expandedproductparsedresult.weightIncrement) && price.equals(expandedproductparsedresult.price) && priceIncrement.equals(expandedproductparsedresult.priceIncrement) && priceCurrency.equals(expandedproductparsedresult.priceCurrency) && uncommonAIs.equals(expandedproductparsedresult.uncommonAIs))
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public String getBestBeforeDate()
    {
        return bestBeforeDate;
    }

    public String getDisplayResult()
    {
        return productID;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public String getLotNumber()
    {
        return lotNumber;
    }

    public String getPackagingDate()
    {
        return packagingDate;
    }

    public String getPrice()
    {
        return price;
    }

    public String getPriceCurrency()
    {
        return priceCurrency;
    }

    public String getPriceIncrement()
    {
        return priceIncrement;
    }

    public String getProductID()
    {
        return productID;
    }

    public String getProductionDate()
    {
        return productionDate;
    }

    public String getSscc()
    {
        return sscc;
    }

    public Hashtable getUncommonAIs()
    {
        return uncommonAIs;
    }

    public String getWeight()
    {
        return weight;
    }

    public String getWeightIncrement()
    {
        return weightIncrement;
    }

    public String getWeightType()
    {
        return weightType;
    }

    public int hashCode()
    {
        return 31 * (31 * (31 * (31 * (31 * (31 * productID.hashCode() + sscc.hashCode()) + lotNumber.hashCode()) + productionDate.hashCode()) + bestBeforeDate.hashCode()) + expirationDate.hashCode()) + weight.hashCode() ^ 31 * (31 * (31 * (31 * (31 * weightType.hashCode() + weightIncrement.hashCode()) + price.hashCode()) + priceIncrement.hashCode()) + priceCurrency.hashCode()) + uncommonAIs.hashCode();
    }

    public static final String KILOGRAM = "KG";
    public static final String POUND = "LB";
    private final String bestBeforeDate;
    private final String expirationDate;
    private final String lotNumber;
    private final String packagingDate;
    private final String price;
    private final String priceCurrency;
    private final String priceIncrement;
    private final String productID;
    private final String productionDate;
    private final String sscc;
    private final Hashtable uncommonAIs;
    private final String weight;
    private final String weightIncrement;
    private final String weightType;
}
