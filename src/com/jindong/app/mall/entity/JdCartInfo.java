// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.Contants;
import java.util.ArrayList;

// Referenced classes of package com.jindong.app.mall.entity:
//            JdTradeProduct, JdCartItemInfo

public class JdCartInfo extends JdTradeProduct
{

    public JdCartInfo()
    {
        mCartItemList = new ArrayList();
        nItemCount = 0;
        dTotalPrice = 0.0D;
    }

    public boolean appendCartItem(JdCartItemInfo jdcartiteminfo)
    {
        boolean flag;
        if(mCartItemList.size() < Contants.MAX_CART_PROD_COUNT && jdcartiteminfo != null)
        {
            mCartItemList.add(jdcartiteminfo);
            nItemCount = 1 + nItemCount;
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void clearCart()
    {
        mCartItemList.clear();
        mCartItemList = null;
        nItemCount = 0;
    }

    public ArrayList getAllCartItems()
    {
        return mCartItemList;
    }

    public JdCartItemInfo getCartItem(int i)
    {
        return (JdCartItemInfo)mCartItemList.get(i);
    }

    public int getTotalCount()
    {
        return nItemCount;
    }

    public double getTotalPrice()
    {
        return dTotalPrice;
    }

    public void setTotalCount(int i)
    {
        nItemCount = i;
    }

    public void setTotalPrice(double d)
    {
        dTotalPrice = d;
    }

    private double dTotalPrice;
    private ArrayList mCartItemList;
    private int nItemCount;
}
