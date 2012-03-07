// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.utils.JSONObjectProxy;
import org.json.JSONException;
import org.json.JSONObject;

public class OnClickBuyFlow
{

    public OnClickBuyFlow()
    {
    }

    private void calculateOrder()
    {
    }

    public static void initLastOrderInfo(JSONObjectProxy jsonobjectproxy)
    {
        if(isLastOrderInfoContainField("Name", jsonobjectproxy) && "" != jsonobjectproxy.getStringOrNull("Name") && jsonobjectproxy.getStringOrNull("Name") != null && jsonobjectproxy.getStringOrNull("Name").length() > 0)
            LastOrderInfo.mUserInfo.setUserName(jsonobjectproxy.getStringOrNull("Name"));
        if(isLastOrderInfoContainField("Phone", jsonobjectproxy))
            LastOrderInfo.mUserInfo.setUserPhone(jsonobjectproxy.getStringOrNull("Phone"));
        if(isLastOrderInfoContainField("Mobile", jsonobjectproxy))
            LastOrderInfo.mUserInfo.setUserMobile(jsonobjectproxy.getStringOrNull("Mobile"));
        if(isLastOrderInfoContainField("Zip", jsonobjectproxy))
            LastOrderInfo.mUserInfo.setUserZip(jsonobjectproxy.getStringOrNull("Zip"));
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("IdProvince", jsonobjectproxy.getIntOrNull("IdProvince"));
        jsonobject.put("IdCity", jsonobjectproxy.getIntOrNull("IdCity"));
        jsonobject.put("IdArea", jsonobjectproxy.getIntOrNull("IdArea"));
        if(isLastOrderInfoContainField("Where", jsonobjectproxy))
            jsonobject.put("Where", jsonobjectproxy.getStringOrNull("Where"));
        if(isLastOrderInfoContainField("Email", jsonobjectproxy))
            jsonobject.put("Email", jsonobjectproxy.getStringOrNull("Email"));
        jsonobject.put("UserLevel", jsonobjectproxy.get("UserLevel"));
        LastOrderInfo.mUserInfo.setUserAddr(jsonobject);
        JSONObject jsonobject1 = new JSONObject();
        jsonobject1.put("IdPaymentType", jsonobjectproxy.getIntOrNull("IdPaymentType"));
        jsonobject1.put("IdShipmentType", jsonobjectproxy.getIntOrNull("IdShipmentType"));
        if(isLastOrderInfoContainField("CODTime", jsonobjectproxy))
            jsonobject1.put("CODTime", jsonobjectproxy.getInt("CODTime"));
        if(isLastOrderInfoContainField("CodDate", jsonobjectproxy))
            jsonobject1.put("CodDate", jsonobjectproxy.get("CodDate"));
        if(isLastOrderInfoContainField("ShipTime", jsonobjectproxy))
            jsonobject1.put("ShipTime", jsonobjectproxy.get("ShipTime"));
        if(isLastOrderInfoContainField("IsCodInform", jsonobjectproxy))
            jsonobject1.put("IsCodInform", jsonobjectproxy.get("IsCodInform"));
        if(isLastOrderInfoContainField("PaymentWay", jsonobjectproxy))
            jsonobject1.put("PaymentWay", jsonobjectproxy.get("PaymentWay"));
        if(isLastOrderInfoContainField("IdPickSite", jsonobjectproxy))
            jsonobject1.put("IdPickSite", jsonobjectproxy.get("IdPickSite"));
        LastOrderInfo.mPaymentInfo.nSelected = jsonobject1.getInt("IdPaymentType");
        LastOrderInfo.mPaymentInfo.nSelected;
        LastOrderInfo.mPaymentInfo.setPayMentType(jsonobject1.getInt("IdPaymentType"), jsonobject1);
        JSONObject jsonobject2 = new JSONObject();
        if(isLastOrderInfoContainField("IdInvoicePutType", jsonobjectproxy))
            jsonobject2.put("IdInvoicePutType", jsonobjectproxy.get("IdInvoicePutType"));
        if(isLastOrderInfoContainField("IdInvoiceContentTypeBook", jsonobjectproxy))
            jsonobject2.put("IdInvoiceContentTypeBook", jsonobjectproxy.get("IdInvoiceContentTypeBook"));
        if(isLastOrderInfoContainField("IdInvoiceContentsType", jsonobjectproxy))
            jsonobject2.put("IdInvoiceContentsType", jsonobjectproxy.get("IdInvoiceContentsType"));
        if(isLastOrderInfoContainField("InvoiceTitle", jsonobjectproxy))
            jsonobject2.put("InvoiceTitle", jsonobjectproxy.get("InvoiceTitle"));
        if(isLastOrderInfoContainField("IdInvoiceType", jsonobjectproxy))
            jsonobject2.put("IdInvoiceType", jsonobjectproxy.get("IdInvoiceType"));
        if(isLastOrderInfoContainField("CompanyName", jsonobjectproxy))
            jsonobject2.put("CompanyName", jsonobjectproxy.get("CompanyName"));
        if(isLastOrderInfoContainField("IdCompanyBranch", jsonobjectproxy))
            jsonobject2.put("IdCompanyBranch", jsonobjectproxy.get("IdCompanyBranch"));
        if(isLastOrderInfoContainField("IdInvoiceHeaderType", jsonobjectproxy))
            jsonobject2.put("IdInvoiceHeaderType", jsonobjectproxy.getInt("IdInvoiceHeaderType"));
        if(isLastOrderInfoContainField("IsPutBookInvoice", jsonobjectproxy))
            jsonobject2.put("IsPutBookInvoice", jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice"));
        if(isLastOrderInfoContainField("IdInvoiceContentTypeBook", jsonobjectproxy))
            jsonobject2.put("IdInvoiceContentTypeBook", jsonobjectproxy.getIntOrNull("IdInvoiceContentTypeBook"));
        LastOrderInfo.mInvoiceInfo.setInvoiceInfo(jsonobject2);
        JSONObject jsonobject3 = new JSONObject();
        if(isLastOrderInfoContainField("TheCoupons", jsonobjectproxy))
        {
            jsonobject3.put("TheCoupons", jsonobjectproxy.get("TheCoupons"));
            LastOrderInfo.mYouhuiQuan.Coupons = jsonobject3;
        }
        JSONObject jsonobject4 = new JSONObject();
        if(isLastOrderInfoContainField("TheLipinkas", jsonobjectproxy))
        {
            jsonobject4.put("TheLipinkas", jsonobjectproxy.get("TheLipinkas"));
            LastOrderInfo.mYouhuiQuan.LipinKas = jsonobject4;
        }
        if(isLastOrderInfoContainField("Remark", jsonobjectproxy))
            LastOrderInfo.mRemark = jsonobjectproxy.getStringOrNull("Remark");
        if(isLastOrderInfoContainField("PromotionPrice", jsonobjectproxy))
            LastOrderInfo.dPromotionPrice = Double.parseDouble(jsonobjectproxy.get("PromotionPrice").toString());
        if(isLastOrderInfoContainField("Price", jsonobjectproxy))
            LastOrderInfo.dPrice = Double.parseDouble(jsonobjectproxy.get("Price").toString());
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private static boolean isLastOrderInfoContainField(String s, JSONObjectProxy jsonobjectproxy)
    {
        boolean flag;
        if(jsonobjectproxy.toString().contains(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean onClick = false;

}
