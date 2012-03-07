// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;


// Referenced classes of package com.jindong.app.mall.entity:
//            UserInfo, PaymentInfo, InvoiceInfo, YouHuiQuan

public class LastOrderInfo
{

    public LastOrderInfo()
    {
        hasOrderBefore = false;
        mUserInfo = new UserInfo();
        mPaymentInfo = new PaymentInfo();
        mInvoiceInfo = new InvoiceInfo();
        mYouhuiQuan = new YouHuiQuan();
    }

    public LastOrderInfo(UserInfo userinfo, PaymentInfo paymentinfo, InvoiceInfo invoiceinfo, YouHuiQuan youhuiquan)
    {
        hasOrderBefore = false;
        mUserInfo = userinfo;
        mPaymentInfo = paymentinfo;
        mInvoiceInfo = invoiceinfo;
        mYouhuiQuan = youhuiquan;
    }

    public static double dPrice;
    public static double dPromotionPrice;
    public static InvoiceInfo mInvoiceInfo = new InvoiceInfo();
    public static PaymentInfo mPaymentInfo = new PaymentInfo();
    public static String mRemark;
    public static UserInfo mUserInfo = new UserInfo();
    public static YouHuiQuan mYouhuiQuan = new YouHuiQuan();
    public boolean hasOrderBefore;

}
