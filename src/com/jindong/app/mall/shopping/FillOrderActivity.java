// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import com.jindong.app.mall.utils.ui.DialogController;
import java.util.*;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            YouHuiAdapter, EditYouHuiLipinActivity, ModifyOrderAddr, ReceiptInfoEditActivity, 
//            SelectPaymentType, CompleteOrderActivity

public class FillOrderActivity extends MyActivity
{
    public static class CaptchaDialogController extends DialogController
    {

        private void bindImage()
        {
            currentMyActivity.post(new Runnable() {

                public void run()
                {
                    captchaImage.setImageDrawable(loadingDrawable);
                }

                final CaptchaDialogController this$1;

                
                {
                    this$1 = CaptchaDialogController.this;
                    super();
                }
            }
);
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setType(5000);
            httpsetting.setPriority(5000);
            httpsetting.setUrl(captchaUrl);
            httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnCommonListener() {

                public void onEnd(final com.jindong.app.mall.utils.HttpGroup.HttpResponse httpResponse)
                {
                    currentMyActivity.post(new Runnable() {

                        public void run()
                        {
                            captchaImage.setImageDrawable(httpResponse.getDrawable());
                        }

                        final _cls6 this$2;
                        private final com.jindong.app.mall.utils.HttpGroup.HttpResponse val$httpResponse;

                        
                        {
                            this$2 = _cls6.this;
                            httpResponse = httpresponse;
                            super();
                        }
                    }
);
                }

                public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                {
                    currentMyActivity.post(new Runnable() {

                        public void run()
                        {
                            captchaImage.setImageDrawable(noDrawable);
                        }

                        final _cls6 this$2;

                        
                        {
                            this$2 = _cls6.this;
                            super();
                        }
                    }
);
                }

                public void onReady(com.jindong.app.mall.utils.HttpGroup.HttpSettingParams httpsettingparams)
                {
                    captchaKey = (new StringBuilder(String.valueOf(StatisticsReportUtil.readDeviceUUID()))).append(randomText(6)).toString();
                    httpsettingparams.putMapParams("key", captchaKey);
                }

                final CaptchaDialogController this$1;


                
                {
                    this$1 = CaptchaDialogController.this;
                    super();
                }
            }
);
            currentMyActivity.getHttpGroupaAsynPool().add(httpsetting);
        }

        private String randomText(int i)
        {
            char ac[] = new char[62];
            ac[0] = '0';
            ac[1] = '1';
            ac[2] = '2';
            ac[3] = '3';
            ac[4] = '4';
            ac[5] = '5';
            ac[6] = '6';
            ac[7] = '7';
            ac[8] = '8';
            ac[9] = '9';
            ac[10] = 'a';
            ac[11] = 'b';
            ac[12] = 'c';
            ac[13] = 'd';
            ac[14] = 'e';
            ac[15] = 'f';
            ac[16] = 'g';
            ac[17] = 'h';
            ac[18] = 'i';
            ac[19] = 'j';
            ac[20] = 'k';
            ac[21] = 'l';
            ac[22] = 'm';
            ac[23] = 'n';
            ac[24] = 'o';
            ac[25] = 'p';
            ac[26] = 'q';
            ac[27] = 'r';
            ac[28] = 's';
            ac[29] = 't';
            ac[30] = 'u';
            ac[31] = 'v';
            ac[32] = 'w';
            ac[33] = 'x';
            ac[34] = 'y';
            ac[35] = 'z';
            ac[36] = 'A';
            ac[37] = 'B';
            ac[38] = 'C';
            ac[39] = 'D';
            ac[40] = 'E';
            ac[41] = 'F';
            ac[42] = 'G';
            ac[43] = 'H';
            ac[44] = 'I';
            ac[45] = 'J';
            ac[46] = 'K';
            ac[47] = 'L';
            ac[48] = 'M';
            ac[49] = 'N';
            ac[50] = 'O';
            ac[51] = 'P';
            ac[52] = 'Q';
            ac[53] = 'R';
            ac[54] = 'S';
            ac[55] = 'T';
            ac[56] = 'U';
            ac[57] = 'V';
            ac[58] = 'W';
            ac[59] = 'X';
            ac[60] = 'Y';
            ac[61] = 'Z';
            StringBuilder stringbuilder = new StringBuilder();
            Random random = new Random();
            int j = 0;
            do
            {
                if(j >= i)
                    return stringbuilder.toString();
                stringbuilder.append(ac[random.nextInt(ac.length)]);
                j++;
            } while(true);
        }

        public void findView()
        {
            currentMyActivity.post(new Runnable() {

                public void run()
                {
                    RelativeLayout relativelayout = new RelativeLayout(currentMyActivity);
                    setView(relativelayout);
                    view = LayoutInflater.from(currentMyActivity).inflate(0x7f030010, relativelayout);
                    captchaImage = (ImageView)view.findViewById(0x7f0c0040);
                    captchaInput = (EditText)view.findViewById(0x7f0c0041);
                }

                final CaptchaDialogController this$1;

                
                {
                    this$1 = CaptchaDialogController.this;
                    super();
                }
            }
);
        }

        public void onClick(DialogInterface dialoginterface, int i)
        {
            i;
            JVM INSTR tableswitch -3 -1: default 28
        //                       -3 28
        //                       -2 79
        //                       -1 29;
               goto _L1 _L1 _L2 _L3
_L1:
            return;
_L3:
            String s = captchaInput.getText().toString();
            if(TextUtils.isEmpty(s))
                currentMyActivity.post(new Runnable() );
            else
                submit(s, captchaKey);
            continue; /* Loop/switch isn't completed */
_L2:
            bindImage();
            currentMyActivity.post(new Runnable() );
            if(true) goto _L1; else goto _L4
_L4:
        }

        protected void submit(String s, String s1)
        {
        }

        private ImageView captchaImage;
        private EditText captchaInput;
        private String captchaKey;
        private final String captchaUrl;
        private MyActivity currentMyActivity;
        private com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable loadingDrawable;
        private com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable noDrawable;
        private View view;













        public CaptchaDialogController(String s, String s1)
        {
            currentMyActivity = MyApplication.getInstance().getCurrentMyActivity();
            captchaUrl = s;
            loadingDrawable = new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(MyApplication.getInstance(), "\u52A0\u8F7D\u4E2D");
            noDrawable = new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(MyApplication.getInstance(), "\u52A0\u8F7D\u5931\u8D25");
            findView();
            bindImage();
            setTitle("\u9700\u8981\u9A8C\u8BC1\u7801");
            if(TextUtils.isEmpty(s1))
                s1 = "\u8BF7\u6839\u636E\u56FE\u7247\u8F93\u5165\u9A8C\u8BC1\u7801\u3002";
            setMessage(s1);
            setPositiveButton("\u63D0\u4EA4");
            setNeutralButton("\u53D6\u6D88");
            setNegativeButton("\u6362\u4E00\u5F20");
            setCanBack(true);
            init(currentMyActivity);
            currentMyActivity.post(new Runnable() {

                public void run()
                {
                    show();
                }

                final CaptchaDialogController this$1;

                
                {
                    this$1 = CaptchaDialogController.this;
                    super();
                }
            }
);
        }
    }


    public FillOrderActivity()
    {
        TAG = "FillOrderActivity";
        items = null;
        bNew = false;
        submit = false;
        show = false;
        onlyBook = false;
        noBook = false;
        newWay = false;
        update_by_addr = false;
        update_by_payment = false;
        update_by_invoince = false;
    }

    private void ShowMsg(final String msg)
    {
        post(new Runnable() {

            public void run()
            {
                Toast.makeText(FillOrderActivity.this, msg, 0).show();
            }

            final FillOrderActivity this$0;
            private final String val$msg;

            
            {
                this$0 = FillOrderActivity.this;
                msg = s;
                super();
            }
        }
);
    }

    private void addCoupon()
    {
        try
        {
            jbOrderStr.put("CouponPassword", "46F3-9CBB-8205-76AD");
            resetJBBody();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        setUpConnAndGetData("addcoupon", jbBody, "couponInfo");
    }

    private void addCoupon(JSONObject jsonobject)
    {
        try
        {
            jbOrderStr.put("CouponPassword", "46F3-9CBB-8205-76AD");
            resetJBBody();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        setUpConnAndGetData("addcoupon", jbBody, "couponInfo");
    }

    /**
     * @deprecated Method addEasyBuyTemp is deprecated
     */

    private void addEasyBuyTemp(String s)
    {
        this;
        JVM INSTR monitorenter ;
        jbBody = null;
        jbBody = new JSONObject();
        if(!jbOrderStr.toString().contains("IdInvoiceContentTypeBook") || jbOrderStr.getInt("IdInvoiceContentTypeBook") == -1) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("IsPutBookInvoice", true);
_L3:
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("templateName", s);
_L4:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new HttpSetting();
        httpsetting.setPost(true);
        httpsetting.setFunctionId("easyBuyAddOrderTemp");
        httpsetting.setJsonParams(jbBody);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                getOrderInfoFromSer(httpresponse, "easyBuyAddOrderTemp");
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d(TAG, "setUpConnAndGetData()-start");
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        jbOrderStr.put("IsPutBookInvoice", false);
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L4
        Exception exception;
        exception;
        throw exception;
          goto _L3
    }

    private void addLiPin()
    {
        try
        {
            jbOrderStr.put("GiftCardKey", "F1BF-C0CB-814C-214B");
            resetJBBody();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        setUpConnAndGetData("addGiftCart", jbBody, "addGiftCart");
    }

    /**
     * @deprecated Method calculateOrder is deprecated
     */

    private void calculateOrder()
    {
        this;
        JVM INSTR monitorenter ;
        JSONObject jsonobject = null;
        JSONObject jsonobject1 = resetJBBody();
        jsonobject = jsonobject1;
_L2:
        if(jsonobject != null)
            break MISSING_BLOCK_LABEL_21;
        comositeBody();
        setUpConnAndGetData("calcOrder", jsonobject, "yunfeeList");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method clearCart is deprecated
     */

    private void clearCart()
    {
        this;
        JVM INSTR monitorenter ;
        DBHelperUtil dbhelperutil = new DBHelperUtil(this);
        dbhelperutil.delAllCart();
        dbhelperutil.delAllPacksCart();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void compositeDefaultOrderStr()
    {
        jbOrderStr = new JSONObject();
        if(LoginUser.hasLogin())
        {
            jbOrderStr.put("pin", LoginUser.getLoginUserName());
            jbOrderStr.put("Name", LoginUser.getLoginUserName());
            jbOrderStr.put("InvoiceTitle", "\u4E2A\u4EBA");
            jbOrderStr.put("IdInvoiceType", 1);
            jbOrderStr.put("IdInvoiceHeaderType", 4);
            jbOrderStr.put("IsUseBalance", true);
            jbOrderStr.put("IdInvoiceContentTypeBook", -1);
            jbOrderStr.put("IdInvoiceContentsType", 1);
            jbOrderStr.put("IdPaymentType", 1);
            jbOrderStr.put("IdShipmentType", 70);
        }
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    /**
     * @deprecated Method compositeEasyBuyCartStr is deprecated
     */

    private void compositeEasyBuyCartStr()
    {
        this;
        JVM INSTR monitorenter ;
        JSONArray jsonarray;
        JSONObject jsonobject;
        jbCartStr = new JSONObject();
        jsonarray = new JSONArray();
        jsonobject = new JSONObject();
        jsonobject.put("Id", String.valueOf(Contants.easyBuyProdId));
        jsonobject.put("num", String.valueOf(1));
        jsonarray.put(jsonobject);
        jbCartStr.put("TheSkus", jsonarray);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void compositeOrderStr()
    {
        jbOrderStr = new JSONObject();
        if(!LoginUser.hasLogin()) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("pin", LoginUser.getLoginUserName());
        if(isLastOrderInfoContainField("Name", jsonOrderInfoContainer))
            jbOrderStr.put("Name", LastOrderInfo.mUserInfo.getUserName());
        if(isLastOrderInfoContainField("Mobile", jsonOrderInfoContainer))
            jbOrderStr.put("Mobile", LastOrderInfo.mUserInfo.getUserMobile());
        if(isLastOrderInfoContainField("Phone", jsonOrderInfoContainer))
            jbOrderStr.put("Phone", LastOrderInfo.mUserInfo.getUserPhone());
        if(isLastOrderInfoContainField("Zip", jsonOrderInfoContainer))
            jbOrderStr.put("Zip", LastOrderInfo.mUserInfo.getUserZip());
        if(isLastOrderInfoContainField("IdProvince", jsonOrderInfoContainer))
            jbOrderStr.put("IdProvince", LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince"));
        if(isLastOrderInfoContainField("IdCity", jsonOrderInfoContainer))
            jbOrderStr.put("IdCity", LastOrderInfo.mUserInfo.getUserAddr().get("IdCity"));
        if(isLastOrderInfoContainField("IdArea", jsonOrderInfoContainer))
            jbOrderStr.put("IdArea", LastOrderInfo.mUserInfo.getUserAddr().get("IdArea"));
        if(isLastOrderInfoContainField("Where", jsonOrderInfoContainer))
            jbOrderStr.put("Where", LastOrderInfo.mUserInfo.getUserAddr().get("Where"));
        if(isLastOrderInfoContainField("Email", jsonOrderInfoContainer))
            jbOrderStr.put("Email", LastOrderInfo.mUserInfo.getUserAddr().get("Email"));
        if(isLastOrderInfoContainField("UserLevel", jsonOrderInfoContainer))
            jbOrderStr.put("UserLevel", LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel"));
        if(isLastOrderInfoContainField("CompanyName", jsonOrderInfoContainer))
            jbOrderStr.put("CompanyName", jsonOrderInfoContainer.get("CompanyName"));
        if(isLastOrderInfoContainField("InvoiceTitle", jsonOrderInfoContainer))
            jbOrderStr.put("InvoiceTitle", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("InvoiceTitle"));
        if(isLastOrderInfoContainField("IdInvoiceType", jsonOrderInfoContainer))
            jbOrderStr.put("IdInvoiceType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceType"));
        if(isLastOrderInfoContainField("IdInvoiceHeaderType", jsonOrderInfoContainer))
            jbOrderStr.put("IdInvoiceHeaderType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceHeaderType"));
        if(isLastOrderInfoContainField("IdInvoiceContentTypeBook", jsonOrderInfoContainer))
            jbOrderStr.put("IdInvoiceContentTypeBook", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentTypeBook"));
        if(isLastOrderInfoContainField("IdCompanyBranch", jsonOrderInfoContainer))
            jbOrderStr.put("IdCompanyBranch", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdCompanyBranch"));
        if(isLastOrderInfoContainField("IdInvoiceContentsType", jsonOrderInfoContainer))
            jbOrderStr.put("IdInvoiceContentsType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentsType"));
        if(!isLastOrderInfoContainField("IsPutBookInvoice", jsonOrderInfoContainer) || jsonOrderInfoContainer.getBooleanOrNull("IsPutBookInvoice") == null) goto _L4; else goto _L3
_L3:
        if(!jsonOrderInfoContainer.getBooleanOrNull("IsPutBookInvoice").booleanValue())
        {
            jbOrderStr.put("IdInvoiceContentTypeBook", -1);
            jbOrderStr.put("IsPutBookInvoice", jsonOrderInfoContainer.getBooleanOrNull("IsPutBookInvoice"));
        } else
        {
            jbOrderStr.put("IsPutBookInvoice", jsonOrderInfoContainer.getBooleanOrNull("IsPutBookInvoice"));
        }
_L12:
        if(isLastOrderInfoContainField("IdPaymentType", jsonOrderInfoContainer))
            jbOrderStr.put("IdPaymentType", LastOrderInfo.mPaymentInfo.type);
        if(isLastOrderInfoContainField("PaymentWay", jsonOrderInfoContainer))
            jbOrderStr.put("PaymentWay", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay"));
        if(isLastOrderInfoContainField("IdShipmentType", jsonOrderInfoContainer))
            jbOrderStr.put("IdShipmentType", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("IdShipmentType"));
        if(isLastOrderInfoContainField("CODTime", jsonOrderInfoContainer))
            jbOrderStr.put("CODTime", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("CODTime"));
        if(isLastOrderInfoContainField("CodDate", jsonOrderInfoContainer))
            jbOrderStr.put("CodDate", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("CodDate"));
        if(isLastOrderInfoContainField("ShipTime", jsonOrderInfoContainer))
            jbOrderStr.put("ShipTime", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("ShipTime"));
        if(isLastOrderInfoContainField("ShipTime", jsonOrderInfoContainer))
            jbOrderStr.put("ShipTime", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("ShipTime"));
        if(isLastOrderInfoContainField("IdPickSite", jsonOrderInfoContainer))
            jbOrderStr.put("IdPickSite", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("IdPickSite"));
        if(!isLastOrderInfoContainField("IsCodInform", jsonOrderInfoContainer)) goto _L6; else goto _L5
_L5:
        jbOrderStr.put("IsCodInform", jsonOrderInfoContainer.get("IsCodInform"));
_L9:
        if(!isLastOrderInfoContainField("IsUseBalance", jsonOrderInfoContainer)) goto _L8; else goto _L7
_L7:
        jbOrderStr.put("IsUseBalance", jsonOrderInfoContainer.get("IsUseBalance"));
_L10:
        if(isLastOrderInfoContainField("PromotionPrice", jsonOrderInfoContainer))
            jbOrderStr.put("PromotionPrice", LastOrderInfo.dPromotionPrice);
        if(isLastOrderInfoContainField("Price", jsonOrderInfoContainer))
            jbOrderStr.put("Price", LastOrderInfo.dPrice);
          goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L4:
        jbOrderStr.put("IdInvoiceContentTypeBook", -1);
        continue; /* Loop/switch isn't completed */
_L6:
        jbOrderStr.put("IsCodInform", false);
          goto _L9
_L8:
        jbOrderStr.put("IsUseBalance", true);
          goto _L10
_L2:
        return;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void compositeOrderStr(JSONObjectProxy jsonobjectproxy)
    {
        jbOrderStr = new JSONObject();
        if(!LoginUser.hasLogin()) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("pin", LoginUser.getLoginUserName());
        if(isLastOrderInfoContainField("Name", jsonobjectproxy))
            jbOrderStr.put("Name", jsonobjectproxy.getStringOrNull("Name"));
        if(isLastOrderInfoContainField("Mobile", jsonobjectproxy))
            jbOrderStr.put("Mobile", jsonobjectproxy.getStringOrNull("Mobile"));
        if(isLastOrderInfoContainField("Phone", jsonobjectproxy))
            jbOrderStr.put("Phone", jsonobjectproxy.getStringOrNull("Phone"));
        if(isLastOrderInfoContainField("Zip", jsonobjectproxy))
            jbOrderStr.put("Zip", jsonobjectproxy.get("Zip"));
        if(isLastOrderInfoContainField("IdProvince", jsonobjectproxy))
            jbOrderStr.put("IdProvince", jsonobjectproxy.get("IdProvince"));
        if(isLastOrderInfoContainField("IdCity", jsonobjectproxy))
            jbOrderStr.put("IdCity", jsonobjectproxy.get("IdCity"));
        if(isLastOrderInfoContainField("IdArea", jsonobjectproxy))
            jbOrderStr.put("IdArea", jsonobjectproxy.get("IdArea"));
        if(isLastOrderInfoContainField("Where", jsonobjectproxy))
            jbOrderStr.put("Where", jsonobjectproxy.get("Where"));
        if(isLastOrderInfoContainField("Email", jsonobjectproxy))
            jbOrderStr.put("Email", jsonobjectproxy.get("Email"));
        if(isLastOrderInfoContainField("UserLevel", jsonobjectproxy))
            jbOrderStr.put("UserLevel", jsonobjectproxy.get("UserLevel"));
        if(isLastOrderInfoContainField("CompanyName", jsonobjectproxy))
            jbOrderStr.put("CompanyName", jsonobjectproxy.get("CompanyName"));
        if(isLastOrderInfoContainField("InvoiceTitle", jsonobjectproxy))
            jbOrderStr.put("InvoiceTitle", jsonobjectproxy.get("InvoiceTitle"));
        if(isLastOrderInfoContainField("IdInvoiceType", jsonobjectproxy))
            jbOrderStr.put("IdInvoiceType", jsonobjectproxy.get("IdInvoiceType"));
        if(isLastOrderInfoContainField("IdInvoiceHeaderType", jsonobjectproxy))
            jbOrderStr.put("IdInvoiceHeaderType", jsonobjectproxy.get("IdInvoiceHeaderType"));
        if(isLastOrderInfoContainField("IdInvoiceContentTypeBook", jsonobjectproxy))
            jbOrderStr.put("IdInvoiceContentTypeBook", jsonobjectproxy.get("IdInvoiceContentTypeBook"));
        if(isLastOrderInfoContainField("IdCompanyBranch", jsonobjectproxy))
            jbOrderStr.put("IdCompanyBranch", jsonobjectproxy.get("IdCompanyBranch"));
        if(isLastOrderInfoContainField("IdInvoiceContentsType", jsonobjectproxy))
            jbOrderStr.put("IdInvoiceContentsType", jsonobjectproxy.get("IdInvoiceContentsType"));
        if(!isLastOrderInfoContainField("IsPutBookInvoice", jsonobjectproxy) || jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice") == null) goto _L4; else goto _L3
_L3:
        if(!jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice").booleanValue())
        {
            jbOrderStr.put("IdInvoiceContentTypeBook", -1);
            jbOrderStr.put("IsPutBookInvoice", jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice"));
        } else
        {
            jbOrderStr.put("IsPutBookInvoice", jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice"));
        }
_L11:
        if(isLastOrderInfoContainField("IdPaymentType", jsonobjectproxy))
            jbOrderStr.put("IdPaymentType", jsonobjectproxy.get("IdPaymentType"));
        if(isLastOrderInfoContainField("PaymentWay", jsonobjectproxy))
            jbOrderStr.put("PaymentWay", jsonobjectproxy.get("PaymentWay"));
        if(isLastOrderInfoContainField("IdShipmentType", jsonobjectproxy))
            jbOrderStr.put("IdShipmentType", jsonobjectproxy.get("IdShipmentType"));
        if(isLastOrderInfoContainField("CODTime", jsonobjectproxy))
            jbOrderStr.put("CODTime", jsonobjectproxy.get("CODTime"));
        if(isLastOrderInfoContainField("CodDate", jsonobjectproxy))
            jbOrderStr.put("CodDate", jsonobjectproxy.get("CodDate"));
        if(isLastOrderInfoContainField("ShipTime", jsonobjectproxy))
            jbOrderStr.put("ShipTime", jsonobjectproxy.get("ShipTime"));
        if(isLastOrderInfoContainField("ShipTime", jsonobjectproxy))
            jbOrderStr.put("ShipTime", jsonobjectproxy.get("ShipTime"));
        if(isLastOrderInfoContainField("IdPickSite", jsonobjectproxy))
            jbOrderStr.put("IdPickSite", jsonobjectproxy.get("IdPickSite"));
        if(!isLastOrderInfoContainField("IsCodInform", jsonobjectproxy)) goto _L6; else goto _L5
_L5:
        jbOrderStr.put("IsCodInform", jsonobjectproxy.get("IsCodInform"));
_L9:
        if(isLastOrderInfoContainField("PromotionPrice", jsonobjectproxy))
            jbOrderStr.put("PromotionPrice", jsonobjectproxy.get("PromotionPrice"));
        if(isLastOrderInfoContainField("Price", jsonobjectproxy))
            jbOrderStr.put("Price", jsonobjectproxy.get("Price"));
        if(!isLastOrderInfoContainField("IsUseBalance", jsonobjectproxy)) goto _L8; else goto _L7
_L7:
        jbOrderStr.put("IsUseBalance", jsonobjectproxy.get("IsUseBalance"));
          goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L4:
        jbOrderStr.put("IdInvoiceContentTypeBook", -1);
        continue; /* Loop/switch isn't completed */
_L6:
        jbOrderStr.put("IsCodInform", false);
          goto _L9
_L8:
        jbOrderStr.put("IsUseBalance", true);
_L2:
        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    private void createBalanceAlertDiglog(String s)
    {
        String s1 = null;
        int i;
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy)
        {
            s1 = mBalance.getText().toString();
            android.app.AlertDialog.Builder builder;
            try
            {
                if(jbBalance == null || !jbBalance.toString().contains("Balance"))
                {
                    Toast.makeText(this, getResources().getString(0x7f090158), 0).show();
                    break MISSING_BLOCK_LABEL_239;
                }
                items = getResources().getStringArray(0x7f0a0006);
            }
            catch(Exception exception)
            {
                if(Log.D)
                    Log.d(TAG, exception.getMessage());
            }
        } else
        {
            items = getResources().getStringArray(0x7f0a0006);
        }
        s1;
        builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        if(mBalance.getText() != "" && mBalance.getText() != " ")
        {
            if(mBalance.getText().toString().compareTo(items[1]) == 0)
                i = 1;
            else
                i = 0;
        } else
        {
            i = 0;
        }
        builder.setSingleChoiceItems(items, i, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int j)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(j).toString());
                if(items[j].compareTo(items[0]) != 0)
                    break MISSING_BLOCK_LABEL_217;
                FillOrderActivity.jbOrderStr.put("IsUseBalance", true);
                if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy) goto _L2; else goto _L1
_L1:
                if(Contants.bEasyBuy) goto _L4; else goto _L3
_L3:
                mBalance.setText((new StringBuilder(String.valueOf(FillOrderActivity.jbBalance.get("Balance").toString()))).append("\u5143").toString());
_L5:
                resetJBBody();
                calculateOrder();
_L7:
                FillOrderActivity.bUseBalance = true;
_L6:
                dialoginterface.dismiss();
                return;
_L4:
                mBalance.setText(items[0]);
                  goto _L5
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
                  goto _L6
_L2:
                mBalance.setText(items[0]);
                  goto _L7
                mBalance.setText(items[1]);
                FillOrderActivity.bUseBalance = false;
                FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy)
                {
                    resetJBBody();
                    calculateOrder();
                }
                  goto _L6
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
).show();
    }

    private void createOrderInfoDetail(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        jsonOrderInfoContainer = new JSONObjectProxy((JSONObject)httpresponse.getJSONObject().get(s));
        if(Log.D)
            Log.d("order info", jsonOrderInfoContainer.toString());
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void fininshAddEasyTemp()
    {
        post(new Runnable() {

            public void run()
            {
                Contants.addNewTemplate = true;
                finish();
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                  goto _L1
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void getArea(int i)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("action", "GetAreas");
            jsonobject.put("IdCity", String.valueOf(i));
            if(Contants.bModifyEasyBuy || Contants.bAddEasyBuy || Contants.bEasyBuy)
            {
                setUpConnAndGetData("easyBuyOrderAddress", jsonobject, "GetAreas");
            } else
            {
                if(jbCartStr.toString().contains("TheSkus") && jbCartStr.getJSONArray("TheSkus") != null)
                    jsonobject.put("TheSkus", jbCartStr.getJSONArray("TheSkus"));
                if(jbCartStr.toString().contains("ThePacks") && jbCartStr.getJSONArray("ThePacks") != null)
                    jsonobject.put("ThePacks", jbCartStr.getJSONArray("ThePacks"));
                setUpConnAndGetData("orderAddress", jsonobject, "GetAreas");
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private String getAreaName(int i)
    {
        JSONArray jsonarray;
        String s;
        jsonarray = new JSONArray();
        s = " ";
        JSONArray jsonarray1 = jbAreas.getJSONArray("Areas");
        jsonarray = jsonarray1;
_L3:
        int j = 0;
_L4:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return s;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L3
_L2:
        String s1;
        if(jsonarray.getJSONObject(j).getInt("Id") != i)
            break MISSING_BLOCK_LABEL_120;
        s1 = jsonarray.getJSONObject(j).getString("Name");
        s = s1;
          goto _L1
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        j++;
          goto _L4
    }

    private void getBalance()
    {
        try
        {
            resetJBBody();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        setUpConnAndGetData("usedBalance", jbBody, "usedBalanceMap");
    }

    private void getCartItemInfo()
    {
        JSONObject jsonobject;
        ArrayList arraylist;
        ArrayList arraylist1;
        JSONArray jsonarray;
        new ArrayList();
        new ArrayList();
        DBHelperUtil dbhelperutil = new DBHelperUtil(this);
        jsonobject = new JSONObject();
        arraylist = dbhelperutil.getCartList();
        arraylist1 = dbhelperutil.getPacksList();
        jbCartStr = new JSONObject();
        jsonarray = new JSONArray();
        if(arraylist != null && !arraylist.isEmpty() || arraylist1 != null && !arraylist1.isEmpty()) goto _L2; else goto _L1
_L1:
        arraylist.clear();
        arraylist1.clear();
_L19:
        return;
_L2:
        if(arraylist == null || arraylist.isEmpty())
            arraylist.clear();
        if(arraylist1 == null || arraylist1.isEmpty())
            arraylist1.clear();
        if(!Contants.bEasyBuy) goto _L4; else goto _L3
_L3:
        int l;
        if(arraylist == null || arraylist.isEmpty())
            continue; /* Loop/switch isn't completed */
        l = 0;
_L7:
        if(l < arraylist.size()) goto _L6; else goto _L5
_L5:
        JSONException jsonexception7;
        try
        {
            jsonarray.put(jsonobject);
            jbCartStr.put("TheSkus", jsonarray);
        }
        catch(JSONException jsonexception8)
        {
            jsonexception8.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
_L6:
        jsonobject = new JSONObject();
        if(Long.valueOf(((CartTable)arraylist.get(l)).productCode).longValue() != Contants.easyBuyProdId)
            break MISSING_BLOCK_LABEL_276;
        jsonobject.put("Id", String.valueOf(((CartTable)arraylist.get(l)).productCode));
        jsonobject.put("num", String.valueOf(1));
          goto _L5
        jsonexception7;
        jsonexception7.printStackTrace();
        l++;
        if(true) goto _L7; else goto _L4
_L4:
        JSONArray jsonarray1 = new JSONArray();
        if(arraylist == null || arraylist.isEmpty()) goto _L9; else goto _L8
_L8:
        int k = 0;
_L15:
        if(k < arraylist.size()) goto _L11; else goto _L10
_L10:
        JSONArray jsonarray2;
        int i;
        JSONArray jsonarray3;
        int j;
        try
        {
            jbCartStr.put("TheSkus", jsonarray1);
        }
        catch(JSONException jsonexception6)
        {
            jsonexception6.printStackTrace();
        }
_L9:
        jsonarray2 = new JSONArray();
        if(arraylist1 == null || arraylist1.isEmpty())
            continue; /* Loop/switch isn't completed */
        i = 0;
_L16:
        if(i < arraylist1.size()) goto _L13; else goto _L12
_L12:
        JSONObject jsonobject1;
        JSONException jsonexception;
        JSONObject jsonobject2;
        JSONException jsonexception5;
        try
        {
            jbCartStr.put("ThePacks", jsonarray2);
        }
        catch(JSONException jsonexception1)
        {
            jsonexception1.printStackTrace();
        }
        if(Contants.skusOfSuites == null || Contants.skusOfSuites.length() <= 0)
            continue; /* Loop/switch isn't completed */
        jsonarray3 = new JSONArray();
        j = 0;
_L17:
        if(j >= Contants.skusOfSuites.length())
        {
            jbCartStr.put("SkusOfThePacks", jsonarray3);
            continue; /* Loop/switch isn't completed */
        }
          goto _L14
_L11:
        jsonobject2 = new JSONObject();
        try
        {
            jsonobject2.put("Id", String.valueOf(((CartTable)arraylist.get(k)).productCode));
            jsonobject2.put("num", String.valueOf(((CartTable)arraylist.get(k)).buyCount));
            jsonarray1.put(k, jsonobject2);
        }
        // Misplaced declaration of an exception variable
        catch(JSONException jsonexception5)
        {
            jsonexception5.printStackTrace();
        }
        k++;
          goto _L15
_L13:
        jsonobject1 = new JSONObject();
        try
        {
            jsonobject1.put("Id", String.valueOf(((PacksTable)arraylist1.get(i)).packId));
            jsonobject1.put("num", String.valueOf(((PacksTable)arraylist1.get(i)).buyCount));
            jsonarray2.put(i, jsonobject1);
        }
        // Misplaced declaration of an exception variable
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        i++;
          goto _L16
_L14:
        jsonarray3.put(j, Contants.skusOfSuites.getString(j));
        j++;
          goto _L17
        JSONException jsonexception4;
        jsonexception4;
        JSONException jsonexception3 = jsonexception4;
_L20:
        jsonexception3.printStackTrace();
        if(true) goto _L19; else goto _L18
_L18:
        JSONException jsonexception2;
        jsonexception2;
        jsonexception3 = jsonexception2;
          goto _L20
    }

    private void getCity(int i)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("action", "GetCitys");
            jsonobject.put("IdProvince", String.valueOf(i));
            if(Contants.bModifyEasyBuy || Contants.bAddEasyBuy || Contants.bEasyBuy)
            {
                setUpConnAndGetData("easyBuyOrderAddress", jsonobject, "GetCitys");
            } else
            {
                if(jbCartStr.toString().contains("TheSkus") && jbCartStr.getJSONArray("TheSkus") != null)
                    jsonobject.put("TheSkus", jbCartStr.getJSONArray("TheSkus"));
                if(jbCartStr.toString().contains("ThePacks") && jbCartStr.getJSONArray("ThePacks") != null)
                    jsonobject.put("ThePacks", jbCartStr.getJSONArray("ThePacks"));
                setUpConnAndGetData("orderAddress", jsonobject, "GetCitys");
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private String getCityName(int i)
    {
        JSONArray jsonarray;
        String s;
        jsonarray = new JSONArray();
        s = " ";
        JSONArray jsonarray1 = jbCitys.getJSONArray("Areas");
        jsonarray = jsonarray1;
_L3:
        int j = 0;
_L4:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return s;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L3
_L2:
        String s1;
        if(jsonarray.getJSONObject(j).getInt("Id") != i)
            break MISSING_BLOCK_LABEL_120;
        s1 = jsonarray.getJSONObject(j).getString("Name");
        s = s1;
          goto _L1
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        j++;
          goto _L4
    }

    private void getCopouns()
    {
    }

    private void getInvoiceContentsBook()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy || Contants.bEasyBuy)
        {
            jbBody = new JSONObject();
            try
            {
                s = "invoiceContentsBook";
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "invoiceContentsBook";
        }
        if(jbBody == null)
            resetJBBody();
        setUpConnAndGetData(s, jbBody, "invoiceContentsBook");
    }

    private void getInvoiceContentsGeneral()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy || Contants.bEasyBuy)
        {
            jbBody = new JSONObject();
            try
            {
                s = "easyBuyInvoiceContentsGeneral";
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "invoiceContentsGeneral";
        }
        if(jbBody == null)
            resetJBBody();
        setUpConnAndGetData(s, jbBody, "invoiceContentsGeneral");
    }

    private void getInvoiceHeaderTypes()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy || Contants.bEasyBuy)
        {
            jbBody = new JSONObject();
            try
            {
                s = "easyBuyInvoiceHeaderTypes";
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "invoiceHeaderTypes";
        }
        if(jbBody == null)
            resetJBBody();
        setUpConnAndGetData(s, jbBody, "invoiceHeaderTypeInfo");
    }

    /**
     * @deprecated Method getInvoiceTypes is deprecated
     */

    private void getInvoiceTypes()
    {
        this;
        JVM INSTR monitorenter ;
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy)
            break MISSING_BLOCK_LABEL_85;
        jbBody = new JSONObject();
        String s;
        s = "easyBuyInvoiceTypes";
        jbBody.put("OrderStr", jbOrderStr);
_L1:
        if(jbBody == null)
            resetJBBody();
        setUpConnAndGetData(s, jbBody, "invoiceTypesInfo");
        this;
        JVM INSTR monitorexit ;
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
        Exception exception;
        exception;
        throw exception;
        s = "invoiceTypes";
          goto _L1
    }

    private void getLarstOrderInfo()
    {
        JSONObject jsonobject = new JSONObject();
        if(LoginUser.hasLogin()) goto _L2; else goto _L1
_L1:
        finish();
          goto _L3
_L2:
        jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
_L4:
        setUpConnAndGetData("lastOrder", jsonobject, "lastOderInfo");
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void getOrderInfoFromSer(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        if(getType(s) <= 0) goto _L2; else goto _L1
_L1:
        getType(s);
        JVM INSTR tableswitch 1 22: default 116
    //                   1 117
    //                   2 137
    //                   3 288
    //                   4 681
    //                   5 730
    //                   6 807
    //                   7 873
    //                   8 900
    //                   9 952
    //                   10 1180
    //                   11 189
    //                   12 232
    //                   13 1054
    //                   14 1159
    //                   15 1288
    //                   16 116
    //                   17 116
    //                   18 1329
    //                   19 1148
    //                   20 116
    //                   21 116
    //                   22 1486;
           goto _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L2 _L2 _L18 _L19 _L2 _L2 _L20
_L2:
        return;
_L3:
        createOrderInfoDetail(httpresponse, s);
        initLastOrderInfo(jsonOrderInfoContainer);
        getProvices();
          goto _L2
_L4:
        try
        {
            jbProvinces = httpresponse.getJSONObject().getJSONObject("provinceInfo");
            if(LastOrderInfo.mUserInfo.getUserAddr() != null)
                getCity(LastOrderInfo.mUserInfo.getUserAddr().getInt("IdProvince"));
        }
        catch(JSONException jsonexception10)
        {
            jsonexception10.printStackTrace();
        }
          goto _L2
_L13:
        try
        {
            jbCitys = httpresponse.getJSONObject().getJSONObject("provinceInfo");
            getArea(LastOrderInfo.mUserInfo.getUserAddr().getInt("IdArea"));
        }
        catch(JSONException jsonexception9)
        {
            jsonexception9.printStackTrace();
        }
          goto _L2
_L14:
        try
        {
            jbAreas = httpresponse.getJSONObject().getJSONObject("provinceInfo");
        }
        catch(JSONException jsonexception8)
        {
            jsonexception8.printStackTrace();
        }
        if(Contants.bModifyEasyBuy || Contants.bEasyBuy || Contants.bAddEasyBuy)
            updatePaymentTypesByAddEasyBuy();
        else
            getPaymentTypes();
          goto _L2
_L5:
        if(!Contants.bAddEasyBuy)
            break MISSING_BLOCK_LABEL_431;
        mNewPaymentInfo.setPaymentTypes(httpresponse.getJSONObject().getJSONArray("paymentInfo"));
        setReceiverInfoArea();
        if(update_by_payment)
            break MISSING_BLOCK_LABEL_423;
        LastOrderInfo.mPaymentInfo = new PaymentInfo();
        LastOrderInfo.mPaymentInfo.nSelected = httpresponse.getJSONObject().getJSONArray("paymentInfo").getJSONObject(0).getInt("Id");
_L21:
        if(LastOrderInfo.mPaymentInfo.nSelected > 4 || LastOrderInfo.mPaymentInfo.nSelected < 1)
            LastOrderInfo.mPaymentInfo.nSelected = 1;
        getPaymentDetatilInformation(LastOrderInfo.mPaymentInfo.nSelected);
        if(update_by_invoince)
        {
            getInvoiceTypes();
            update_by_invoince = false;
        }
          goto _L2
        JSONException jsonexception7;
        jsonexception7;
        jsonexception7.printStackTrace();
          goto _L2
        update_by_payment = false;
          goto _L21
        if(Contants.bEasyBuy)
            try
            {
                mNewPaymentInfo.setPaymentTypes(httpresponse.getJSONObject().getJSONArray("paymentInfo"));
                setReceiverInfoArea();
                if(LastOrderInfo.mPaymentInfo.nSelected > 4 || LastOrderInfo.mPaymentInfo.nSelected < 1)
                    LastOrderInfo.mPaymentInfo.nSelected = 1;
                getPaymentDetatilInformation(LastOrderInfo.mPaymentInfo.nSelected);
                getInvoiceTypes();
            }
            catch(JSONException jsonexception6)
            {
                jsonexception6.printStackTrace();
            }
        else
        if(Contants.bModifyEasyBuy)
        {
            try
            {
                mNewPaymentInfo.setPaymentTypes(httpresponse.getJSONObject().getJSONArray("paymentInfo"));
                setReceiverInfoArea();
                if(LastOrderInfo.mPaymentInfo.nSelected > 4 || LastOrderInfo.mPaymentInfo.nSelected < 1)
                    LastOrderInfo.mPaymentInfo.nSelected = 1;
                getPaymentDetatilInformation(LastOrderInfo.mPaymentInfo.nSelected);
                getInvoiceTypes();
            }
            catch(JSONException jsonexception5)
            {
                jsonexception5.printStackTrace();
            }
        } else
        {
            try
            {
                mNewPaymentInfo.setPaymentTypes(httpresponse.getJSONObject().getJSONArray("paymentInfo"));
                if(update_by_addr || !mNewPaymentInfo.getPaymentTypes().toString().contains(String.valueOf(LastOrderInfo.mPaymentInfo.nSelected)))
                    LastOrderInfo.mPaymentInfo.nSelected = mNewPaymentInfo.getPaymentTypes().getJSONObject(0).getInt("Id");
            }
            catch(JSONException jsonexception4)
            {
                jsonexception4.printStackTrace();
            }
            setPaymentInfo();
            getInvoiceTypes();
        }
          goto _L2
_L6:
        createOrderInfoDetail(httpresponse, s);
        setPeiSongInfo(jsonOrderInfoContainer, s);
        if(LastOrderInfo.mPaymentInfo.nSelected == 1 || LastOrderInfo.mPaymentInfo.nSelected == 4)
            setPaymentInfoAreaWithItem(1);
        else
            getPostAddr();
          goto _L2
_L7:
        createOrderInfoDetail(httpresponse, s);
        setGetBySelfInfo(jsonOrderInfoContainer, s);
        try
        {
            if(update_by_addr || !hasIt(jsonOrderInfoContainer.getJSONArray("PickSites"), LastOrderInfo.mPaymentInfo.getPayMentType(3).getInt("IdPickSite")))
            {
                updatePayWayInfoSelf();
                update_by_addr = false;
            }
        }
        catch(JSONException jsonexception3)
        {
            jsonexception3.printStackTrace();
        }
        setPaymentInfoAreaWithItem(3);
          goto _L2
_L8:
        try
        {
            mNewPaymentInfo.setPostArray(httpresponse.getJSONObject().getJSONArray("postInfo"));
            if(update_by_addr)
            {
                updatePayWayInfoPost();
                update_by_addr = false;
            }
        }
        catch(JSONException jsonexception2)
        {
            jsonexception2.printStackTrace();
        }
        setPaymentInfoAreaWithItem(2);
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy)
            calculateOrder();
          goto _L2
_L9:
        try
        {
            mNewInvoiceInfo.setInvoinceTypes(httpresponse.getJSONObject());
        }
        catch(Exception exception6)
        {
            exception6.printStackTrace();
        }
        getInvoiceHeaderTypes();
          goto _L2
_L10:
        try
        {
            mNewInvoiceInfo.setInvoinceHeaders(httpresponse.getJSONObject());
        }
        catch(Exception exception5)
        {
            exception5.printStackTrace();
        }
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy || Contants.bEasyBuy)
            getInvoiceContentsGeneral();
        else
            getInvoiceContentsBook();
          goto _L2
_L11:
        try
        {
            mNewInvoiceInfo.setInvoinceBooks(httpresponse.getJSONObject());
            if(!mNewInvoiceInfo.getInvoinceBooks().toString().contains("\u4E0D\u5F00\u53D1\u7968"))
            {
                JSONObject jsonobject = new JSONObject();
                jsonobject.put("Name", "\u4E0D\u5F00\u53D1\u7968");
                jsonobject.put("Id", "-1");
                mNewInvoiceInfo.getInvoinceBooks().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").put(jsonobject);
            }
        }
        catch(Exception exception4)
        {
            exception4.printStackTrace();
        }
        getInvoiceContentsGeneral();
          goto _L2
_L15:
        try
        {
            jbYunFeeList = httpresponse.getJSONObject().getJSONArray("yunfeeList");
        }
        catch(Exception exception3)
        {
            exception3.printStackTrace();
        }
        setMoneyInfo(jbYunFeeList);
        if(Contants.bEasyBuy && submit)
        {
            submit = false;
            submitEasyBuyOrder();
        } else
        if(!Contants.bEasyBuy && !Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && submit)
        {
            submitOrder();
            submit = false;
        }
          goto _L2
_L19:
        messageAfterSubmit(httpresponse.getJSONObject());
          goto _L2
_L16:
        try
        {
            messageAfterSubmit(httpresponse.getJSONObject());
        }
        catch(Exception exception2)
        {
            exception2.printStackTrace();
        }
          goto _L2
_L12:
        mNewInvoiceInfo.setInvoinceGenerals(httpresponse.getJSONObject());
        if(!mNewInvoiceInfo.getInvoinceGenerals().toString().contains("InvoiceContents"))
            onlyBook = true;
        if(mNewInvoiceInfo.getInvoinceGenerals().toString().contains("InvoiceContentsBook"))
            break MISSING_BLOCK_LABEL_1259;
        noBook = true;
_L22:
        setInvoinceInfoArea();
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy)
            getBalance();
          goto _L2
        try
        {
            mNewInvoiceInfo.setInvoinceBooks(httpresponse.getJSONObject().getJSONObject("InvoiceContentsBook"));
        }
        catch(Exception exception1)
        {
            exception1.printStackTrace();
        }
          goto _L22
_L17:
        try
        {
            jbBalance = httpresponse.getJSONObject().getJSONObject("usedBalanceMap");
            setBalanceView();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        (new Thread() {

            public void run()
            {
                super.run();
                try
                {
                    Thread.sleep(50L);
                }
                catch(InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
                calculateOrder();
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
).start();
          goto _L2
_L18:
        JSONObjectProxy jsonobjectproxy1 = httpresponse.getJSONObject().getJSONObjectOrNull("createTemp");
        if(jsonobjectproxy1 == null || jsonobjectproxy1.get("Flag") == null || !jsonobjectproxy1.getBoolean("Flag")) goto _L24; else goto _L23
_L23:
        ShowMsg(getResources().getString(0x7f09015a));
        fininshAddEasyTemp();
          goto _L2
        JSONException jsonexception1;
        jsonexception1;
        jsonexception1.printStackTrace();
          goto _L2
_L24:
        if(jsonobjectproxy1 == null) goto _L2; else goto _L25
_L25:
        if(jsonobjectproxy1.get("Flag") == null || jsonobjectproxy1.getBoolean("Flag")) goto _L2; else goto _L26
_L26:
        if(jsonobjectproxy1.get("Message") != null && jsonobjectproxy1.get("Message").toString() != "null")
            ShowMsg(jsonobjectproxy1.getString("Message"));
        else
            ShowMsg(getResources().getString(0x7f09015b));
          goto _L2
_L20:
        JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject().getJSONObjectOrNull("createTemp");
        if(jsonobjectproxy == null || jsonobjectproxy.get("Flag") == null || !jsonobjectproxy.getBoolean("Flag")) goto _L28; else goto _L27
_L27:
        ShowMsg(getResources().getString(0x7f09015c));
        fininshAddEasyTemp();
          goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L28:
        if(jsonobjectproxy == null) goto _L2; else goto _L29
_L29:
        if(jsonobjectproxy.get("Flag") == null || jsonobjectproxy.getBoolean("Flag")) goto _L2; else goto _L30
_L30:
        if(jsonobjectproxy.get("Message") != null && jsonobjectproxy.get("Message").toString() != "null")
            ShowMsg(jsonobjectproxy.getString("Message"));
        else
            ShowMsg(getResources().getString(0x7f09015d));
          goto _L2
    }

    private void getPaymentDetatilInformation(int i)
    {
        String s;
        String s1;
        s = null;
        s1 = null;
        if(i != 1 && i != 4) goto _L2; else goto _L1
_L1:
        s1 = "shipmentTypesInfo";
        jbOrderStr.put("IdPaymentType", 1);
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy) goto _L4; else goto _L3
_L3:
        s = "easyBuyShipmentTypes";
        jbBody.put("OrderStr", jbOrderStr);
_L2:
        if(i != 2) goto _L6; else goto _L5
_L5:
        s1 = "shipmentTypesInfo";
        jbOrderStr.put("IdPaymentType", 1);
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy) goto _L8; else goto _L7
_L7:
        s = "easyBuyShipmentTypes";
        jbBody.put("OrderStr", jbOrderStr);
_L6:
        if(i != 3) goto _L10; else goto _L9
_L9:
        s1 = "pickSitesInfo";
        jbOrderStr.put("IdPaymentType", 3);
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy) goto _L12; else goto _L11
_L11:
        s = "easyBuyPickSites";
        jbBody.put("OrderStr", jbOrderStr);
_L10:
        setUpConnAndGetData(s, jbBody, s1);
        if(i != 4)
            break MISSING_BLOCK_LABEL_199;
        jbOrderStr.put("IdPaymentType", 4);
_L13:
        return;
_L4:
        try
        {
            s = "shipmentTypes";
            resetJBBody();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
          goto _L2
_L8:
        try
        {
            s = "shipmentTypes";
            resetJBBody();
        }
        catch(JSONException jsonexception2)
        {
            jsonexception2.printStackTrace();
        }
          goto _L6
_L12:
        try
        {
            s = "pickSites";
            resetJBBody();
        }
        catch(JSONException jsonexception1)
        {
            jsonexception1.printStackTrace();
        }
          goto _L10
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L13
    }

    private void getPaymentTypes()
    {
        comositeBody();
        setUpConnAndGetData("paymentType", jbBody, "paymentInfo");
    }

    private void getPostAddr()
    {
        try
        {
            jbOrderStr.put("IdPaymentType", 2);
            resetJBBody();
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        setUpConnAndGetData("postAddress", jbBody, "postInfo");
    }

    private void getProvices()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("action", "GetProvinces");
            if(Contants.bModifyEasyBuy || Contants.bAddEasyBuy || Contants.bEasyBuy)
            {
                setUpConnAndGetData("easyBuyOrderAddress", jsonobject, "GetProvinces");
            } else
            {
                if(jbCartStr.toString().contains("TheSkus") && jbCartStr.getJSONArray("TheSkus") != null)
                    jsonobject.put("TheSkus", jbCartStr.getJSONArray("TheSkus"));
                if(jbCartStr.toString().contains("ThePacks") && jbCartStr.getJSONArray("ThePacks") != null)
                    jsonobject.put("ThePacks", jbCartStr.getJSONArray("ThePacks"));
                setUpConnAndGetData("orderAddress", jsonobject, "GetProvinces");
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private String getProvincesName(int i)
    {
        JSONArray jsonarray;
        String s;
        jsonarray = new JSONArray();
        s = " ";
        JSONArray jsonarray1 = jbProvinces.getJSONArray("Areas");
        jsonarray = jsonarray1;
_L3:
        int j = 0;
_L4:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return s;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L3
_L2:
        String s1;
        if(jsonarray.getJSONObject(j).getInt("Id") != i)
            break MISSING_BLOCK_LABEL_120;
        s1 = jsonarray.getJSONObject(j).getString("Name");
        s = s1;
          goto _L1
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        j++;
          goto _L4
    }

    private void getRemark()
    {
        sRemark = mRemark.getText().toString();
        if(sRemark == null || sRemark == " " || sRemark.length() <= 0)
            break MISSING_BLOCK_LABEL_57;
        jbOrderStr.put("Remark", sRemark);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private int getType(String s)
    {
        int i;
        if(s.compareTo("lastOderInfo") == 0)
            i = 1;
        else
        if(s.compareTo("GetProvinces") == 0)
            i = 2;
        else
        if(s.compareTo("GetCitys") == 0)
            i = 11;
        else
        if(s.compareTo("GetAreas") == 0)
            i = 12;
        else
        if(s.compareTo("paymentInfo") == 0)
            i = 3;
        else
        if(s.compareTo("shipmentTypesInfo") == 0)
            i = 4;
        else
        if(s.compareTo("pickSitesInfo") == 0)
            i = 5;
        else
        if(s.compareTo("postInfo") == 0)
            i = 6;
        else
        if(s.compareTo("invoiceTypesInfo") == 0)
            i = 7;
        else
        if(s.compareTo("invoiceHeaderTypeInfo") == 0)
            i = 8;
        else
        if(s.compareTo("invoiceContentsBook") == 0)
            i = 9;
        else
        if(s.compareTo("invoiceContentsGeneral") == 0)
            i = 10;
        else
        if(s.compareTo("yunfeeList") == 0)
            i = 13;
        else
        if(s.compareTo("submitOrder") == 0)
            i = 14;
        else
        if(s.compareTo("usedBalanceMap") == 0)
            i = 15;
        else
        if(s.compareTo("couponInfo") == 0)
            i = 16;
        else
        if(s.compareTo("addGiftCart") == 0)
            i = 17;
        else
        if(s.compareTo("easyBuyAddOrderTemp") == 0)
            i = 18;
        else
        if(s.compareTo("easyBuyPaymentType") == 0)
            i = 20;
        else
        if(s.compareTo("easyBuySubmitOrder") == 0)
            i = 19;
        else
        if(s.compareTo("easyBuyModifyTemp") == 0)
            i = 22;
        else
            i = -1;
        return i;
    }

    private void handleClickEvent()
    {
        mSubmit.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!Contants.bAddEasyBuy) goto _L2; else goto _L1
_L1:
                if(mTempName.getText().toString().length() >= 1) goto _L4; else goto _L3
_L3:
                Toast.makeText(FillOrderActivity.this, getResources().getString(0x7f090155), 0).show();
_L14:
                return;
_L4:
                if(!CommonUtil.checkUsername(mTempName.getText().toString()))
                {
                    Toast.makeText(FillOrderActivity.this, getResources().getString(0x7f090156), 0).show();
                    continue; /* Loop/switch isn't completed */
                }
                if(!FillOrderActivity.bUseBalance) goto _L6; else goto _L5
_L5:
                FillOrderActivity.jbOrderStr.put("IsUseBalance", true);
_L7:
                addEasyBuyTemp(mTempName.getText().toString());
                continue; /* Loop/switch isn't completed */
_L6:
                try
                {
                    FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                }
                catch(JSONException jsonexception1)
                {
                    jsonexception1.printStackTrace();
                }
                if(true) goto _L7; else goto _L2
_L2:
                if(Contants.bEasyBuy)
                {
                    submit = true;
                    resetJBBody();
                    calculateOrder();
                    continue; /* Loop/switch isn't completed */
                }
                if(!Contants.bModifyEasyBuy) goto _L9; else goto _L8
_L8:
                if(mTempName.getText().toString().length() < 1)
                {
                    Toast.makeText(FillOrderActivity.this, getResources().getString(0x7f090155), 0).show();
                    continue; /* Loop/switch isn't completed */
                }
                if(!CommonUtil.checkUsername(mTempName.getText().toString()))
                {
                    Toast.makeText(FillOrderActivity.this, getResources().getString(0x7f090156), 0).show();
                    continue; /* Loop/switch isn't completed */
                }
                if(!FillOrderActivity.bUseBalance) goto _L11; else goto _L10
_L10:
                FillOrderActivity.jbOrderStr.put("IsUseBalance", true);
_L12:
                modifyEasyBuyTemp(mTempName.getText().toString(), DefaultEasyTempOrderInfo.sTempId);
                continue; /* Loop/switch isn't completed */
_L11:
                try
                {
                    FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
                if(true) goto _L12; else goto _L9
_L9:
                submit = true;
                getRemark();
                if(!CommonUtil.checkUsername(sRemark, 0))
                {
                    submit = false;
                    Toast.makeText(FillOrderActivity.this, getResources().getString(0x7f090157), 0).show();
                } else
                {
                    resetJBBody();
                    calculateOrder();
                }
                if(true) goto _L14; else goto _L13
_L13:
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        mBalance.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mBalance2.performClick();
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        mBalance2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createBalanceAlertDiglog(getResources().getString(0x7f09014c));
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private boolean hasIt(JSONArray jsonarray, int i)
    {
        boolean flag;
        int j;
        flag = false;
        j = 0;
_L5:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return flag;
_L2:
        int k;
        int l;
        k = jsonarray.getJSONObject(j).getInt("Id");
        l = LastOrderInfo.mPaymentInfo.getPayMentType(3).getInt("IdPickSite");
        if(k != l)
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L1; else goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
_L3:
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void initComponent()
    {
        mTitle = (TextView)findViewById(0x7f0c02c7);
        if(Contants.bAddEasyBuy)
            mTitle.setText(0x7f090183);
        else
        if(Contants.bModifyEasyBuy)
            mTitle.setText(0x7f090180);
        else
            mTitle.setText(0x7f09013f);
        if(Contants.bEasyBuy)
            mTitle.setText(0x7f090184);
        receiverInfoList = (ListView)findViewById(0x7f0c01ef);
        paymentInfoList = (ListView)findViewById(0x7f0c01d0);
        mTotalPrice = (TextView)findViewById(0x7f0c00ab);
        mTransportFee = (TextView)findViewById(0x7f0c00af);
        mYouhuiQuan = (TextView)findViewById(0x7f0c00b2);
        mLipin = (TextView)findViewById(0x7f0c00b5);
        mFanXian = (TextView)findViewById(0x7f0c00bb);
        mLastMoney = (TextView)findViewById(0x7f0c00ac);
        mSubmit = (Button)findViewById(0x7f0c00be);
        if(Contants.bEasyBuy)
            mSubmit.setText(0x7f090094);
        else
        if(Contants.bModifyEasyBuy)
            mSubmit.setText(0x7f090177);
        mBalance = (TextView)findViewById(0x7f0c00a2);
        mBalance2 = (ImageButton)findViewById(0x7f0c00a3);
        mRemark = (EditText)findViewById(0x7f0c00a7);
        mBalance1 = (TextView)findViewById(0x7f0c00b9);
        if(Contants.bEasyBuy)
        {
            ((RelativeLayout)findViewById(0x7f0c00a5)).setVisibility(8);
            ((RelativeLayout)findViewById(0x7f0c00a9)).setVisibility(0);
            findViewById(0x7f0c00a8).setVisibility(8);
            mRemark.setVisibility(8);
            mBalance.setEnabled(false);
            mBalance2.setEnabled(false);
        } else
        if(Contants.bModifyEasyBuy || Contants.bEasyBuy)
        {
            ((RelativeLayout)findViewById(0x7f0c00a5)).setVisibility(8);
            findViewById(0x7f0c00a8).setVisibility(8);
            mRemark.setVisibility(8);
            mBalance.setEnabled(true);
            mBalance2.setEnabled(true);
        } else
        if(Contants.bAddEasyBuy)
        {
            ((RelativeLayout)findViewById(0x7f0c00a5)).setVisibility(8);
            findViewById(0x7f0c00a8).setVisibility(8);
        }
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
            ((RelativeLayout)findViewById(0x7f0c00a9)).setVisibility(8);
    }

    private boolean isLastOrderInfoContainField(String s, JSONObjectProxy jsonobjectproxy)
    {
        boolean flag;
        if(jsonobjectproxy.toString().contains(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void messageAfterSubmit(final JSONObject message)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(LastOrderInfo.mPaymentInfo.nSelected != 1) goto _L2; else goto _L1
_L1:
                sPayWay = "\u8D27\u5230\u4ED8\u6B3E";
_L15:
                if(message.getJSONObject("submitOrder").get("OrderId").toString() == "null" || message.getJSONObject("submitOrder").get("OrderId").toString().compareTo("0") == 0) goto _L4; else goto _L3
_L3:
                show = false;
                sOrderNo = message.getJSONObject("submitOrder").get("OrderId").toString();
                if(!Contants.bEasyBuy) goto _L6; else goto _L5
_L5:
                Contants.jbOrderNum = new JSONObject();
                Contants.jbOrderNum.put("orderId", message.getJSONObject("submitOrder").get("OrderId"));
_L11:
                Intent intent;
                Bundle bundle1;
                intent = new Intent(FillOrderActivity.this, com/jindong/app/mall/shopping/CompleteOrderActivity);
                bundle1 = new Bundle();
                bundle1.putString("order_no", sOrderNo);
                bundle1.putString("order_money", sTotalMoney);
                bundle1.putString("order_way", sPayWay);
                if(message == null || !message.toString().contains("coMsg") || message.getString("coMsg") == "null") goto _L8; else goto _L7
_L7:
                bundle1.putString("order_msg", message.getString("coMsg"));
_L12:
                intent.putExtras(bundle1);
                LastOrderInfo.mUserInfo = new UserInfo();
                LastOrderInfo.mPaymentInfo = new PaymentInfo();
                LastOrderInfo.mInvoiceInfo = new InvoiceInfo();
                LastOrderInfo.mYouhuiQuan = new YouHuiQuan();
                startActivityInFrame(intent);
                Contants.skusOfSuites = null;
                Contants.skusOfSuites = new JSONArrayPoxy();
_L13:
                Contants.dongSel = new JSONObject();
                Contants.jinSelArray = new JSONArray();
                Contants.liSelArray = new JSONArray();
                Contants.nSelectDongQuanId = "";
                Contants.nselectJingQuanIDs = null;
                Contants.nSelectLipinIDs = null;
                Contants.jSelected = false;
                Contants.dSelected = false;
                Contants.liSelected = false;
                Contants.skusOfSuites = null;
                Contants.skusOfSuites = new JSONArrayPoxy();
                  goto _L9
_L2:
                if(LastOrderInfo.mPaymentInfo.nSelected == 2)
                {
                    sPayWay = "\u90AE\u5C40\u6C47\u6B3E";
                    continue; /* Loop/switch isn't completed */
                }
                  goto _L10
                JSONException jsonexception;
                jsonexception;
                Contants.dongSel = new JSONObject();
                Contants.jinSelArray = new JSONArray();
                Contants.liSelArray = new JSONArray();
                Contants.nSelectDongQuanId = "";
                Contants.nselectJingQuanIDs = null;
                Contants.nSelectLipinIDs = null;
                Contants.jSelected = false;
                Contants.dSelected = false;
                Contants.liSelected = false;
                Contants.skusOfSuites = null;
                Contants.skusOfSuites = new JSONArrayPoxy();
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
                  goto _L9
_L10:
                if(LastOrderInfo.mPaymentInfo.nSelected == 3)
                    sPayWay = "\u4EAC\u4E1C\u81EA\u63D0";
                else
                if(LastOrderInfo.mPaymentInfo.nSelected == 4)
                    sPayWay = "\u5728\u7EBF\u652F\u4ED8";
                else
                    sPayWay = "\u516C\u53F8\u8F6C\u8D26";
                continue; /* Loop/switch isn't completed */
_L6:
                clearCart();
                  goto _L11
_L8:
                bundle1.putString("order_msg", " ");
                  goto _L12
_L4:
                String s;
                FillOrderActivity fillorderactivity;
                s = message.getJSONObject("submitOrder").getString("Message");
                fillorderactivity = FillOrderActivity.this;
                String s1;
                if(s != null && !"null".equals(s))
                    s1 = s;
                else
                    s1 = "\u8BA2\u5355\u63D0\u4EA4\u5931\u8D25\uFF0C\u8BF7\u91CD\u8BD5\u3002";
                Toast.makeText(fillorderactivity, s1, 1).show();
                  goto _L13
_L9:
                return;
                if(true) goto _L15; else goto _L14
_L14:
            }

            final FillOrderActivity this$0;
            private final JSONObject val$message;

            
            {
                this$0 = FillOrderActivity.this;
                message = jsonobject;
                super();
            }
        }
);
    }

    /**
     * @deprecated Method modifyEasyBuyTemp is deprecated
     */

    private void modifyEasyBuyTemp(String s, Long long1)
    {
        this;
        JVM INSTR monitorenter ;
        jbBody = null;
        jbBody = new JSONObject();
        if(!jbOrderStr.toString().contains("IdInvoiceContentTypeBook") || jbOrderStr.getInt("IdInvoiceContentTypeBook") == -1) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("IsPutBookInvoice", true);
_L3:
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("templateName", s);
        jbBody.put("Id", long1);
_L4:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setPost(true);
        httpsetting.setFunctionId("easyBuyModifyOrderTemp");
        httpsetting.setJsonParams(jbBody);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                getOrderInfoFromSer(httpresponse, "easyBuyModifyTemp");
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d(TAG, "setUpConnAndGetData()-start");
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        jbOrderStr.put("IsPutBookInvoice", false);
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L4
        Exception exception;
        exception;
        throw exception;
          goto _L3
    }

    private void recalOrderAfterYouHui()
    {
        JSONArray jsonarray;
        JSONArray jsonarray1;
        JSONObject jsonobject;
        jsonarray = new JSONArray();
        jsonarray1 = new JSONArray();
        jsonobject = new JSONObject();
        if(!Contants.jdSwitch) goto _L2; else goto _L1
_L1:
        if(Contants.dongSel == null || Contants.dongSel.toString() == "null" || Contants.dongSel.get("Id") == null || Contants.dongSel.toString() == "") goto _L4; else goto _L3
_L3:
        jsonobject.put("Id", Contants.dongSel.get("Id"));
        jsonarray.put(jsonobject);
        jbOrderStr.put("TheCoupons", jsonarray);
_L12:
        if(Contants.liSelArray == null || Contants.liSelArray.toString() == "null") goto _L6; else goto _L5
_L5:
        int i = Contants.liSelArray.length();
        if(i <= 0) goto _L6; else goto _L7
_L7:
        int j;
        JSONObject jsonobject1;
        j = 0;
        jsonobject1 = jsonobject;
_L23:
        if(j < Contants.liSelArray.length()) goto _L9; else goto _L8
_L8:
        if(jsonarray1.toString() != "" && jsonarray1.toString() != "[]" && jsonarray1.length() >= 1) goto _L11; else goto _L10
_L10:
        jbOrderStr.put("TheLipinkas", null);
        jsonobject1;
_L25:
        resetJBBody();
_L13:
        setUpConnAndGetData("calcOrder", jbBody, "yunfeeList");
        return;
_L4:
        jbOrderStr.put("TheCoupons", null);
          goto _L12
        Exception exception;
        exception;
        Exception exception1 = exception;
_L26:
        exception1.printStackTrace();
          goto _L13
_L2:
        String s = "";
        if(Contants.jinSelArray == null || Contants.jinSelArray.toString() == "null" || Contants.jinSelArray.toString() == "" || Contants.jinSelArray.length() <= 0) goto _L15; else goto _L14
_L14:
        int k = 0;
_L28:
        if(k < Contants.jinSelArray.length()) goto _L17; else goto _L16
_L16:
        if(s == "") goto _L19; else goto _L18
_L18:
        JSONArray jsonarray2 = new JSONArray((new StringBuilder("[")).append(s).append("]").toString());
        jbOrderStr.put("TheCoupons", jsonarray2);
          goto _L12
_L17:
        String s1;
        new JSONObject();
        if(Contants.jinSelArray.get(k) == "" || Contants.jinSelArray.get(k).toString() == "" || Contants.jinSelArray.getJSONObject(k) == null || Contants.jinSelArray.getJSONObject(k).toString() == "null")
            break MISSING_BLOCK_LABEL_877;
        JSONObject jsonobject5 = Contants.jinSelArray.getJSONObject(k);
        if(jsonobject5 == null || jsonobject5.toString() == "null" || jsonobject5.get("Id") == "")
            break MISSING_BLOCK_LABEL_877;
        jsonobject.put("Id", jsonobject5.get("Id"));
        if(k == 0)
        {
            s = (new StringBuilder(String.valueOf(s))).append(jsonobject.toString()).toString();
            break MISSING_BLOCK_LABEL_877;
        }
        s1 = (new StringBuilder(String.valueOf(s))).append(",").append(jsonobject.toString()).toString();
        s = s1;
        break MISSING_BLOCK_LABEL_877;
        JSONException jsonexception3;
        jsonexception3;
        jsonexception3.printStackTrace();
        break MISSING_BLOCK_LABEL_877;
_L19:
        jbOrderStr.put("TheCoupons", null);
          goto _L12
_L15:
        jbOrderStr.put("TheCoupons", null);
          goto _L12
_L9:
        JSONObject jsonobject2 = new JSONObject();
        if(jsonobject2 == null || jsonobject2.toString() == "null" || Contants.liSelArray.get(j) == "" || Contants.liSelArray.get(j).toString() == "" || Contants.liSelArray.getJSONObject(j).toString() == "" || Contants.liSelArray.getJSONObject(j) == null) goto _L21; else goto _L20
_L20:
        JSONObject jsonobject4 = Contants.liSelArray.getJSONObject(j);
        if(jsonobject4 == null || jsonobject4.toString() == "null") goto _L21; else goto _L22
_L22:
        JSONObject jsonobject3 = new JSONObject();
        jsonobject3.put("Id", jsonobject4.get("Id"));
        jsonobject3.put("DiscountCurUsed", jsonobject4.get("DiscountCurUsed"));
        jsonobject3.put("DiscountBind", jsonobject4.get("DiscountBind"));
        jsonobject3.put("DiscountUsed", jsonobject4.get("DiscountUsed"));
        jsonarray1.put(jsonobject3);
_L24:
        j++;
        jsonobject1 = jsonobject3;
          goto _L23
        JSONException jsonexception;
        jsonexception;
        JSONException jsonexception1;
        jsonexception1 = jsonexception;
        jsonobject3 = jsonobject1;
_L27:
        jsonexception1.printStackTrace();
          goto _L24
_L11:
        jbOrderStr.put("TheLipinkas", jsonarray1);
        jsonobject1;
          goto _L25
_L6:
        jbOrderStr.put("TheLipinkas", null);
          goto _L25
        Exception exception3;
        exception3;
        exception1 = exception3;
          goto _L26
        Exception exception2;
        exception2;
        exception1 = exception2;
        jsonobject1;
          goto _L26
        JSONException jsonexception2;
        jsonexception2;
        jsonexception1 = jsonexception2;
          goto _L27
_L21:
        jsonobject3 = jsonobject1;
          goto _L24
        k++;
          goto _L28
    }

    /**
     * @deprecated Method resetJBBody is deprecated
     */

    private JSONObject resetJBBody()
    {
        this;
        JVM INSTR monitorenter ;
        jbBody = null;
        jbBody = new JSONObject();
        if(jbOrderStr == null)
            jbOrderStr = new JSONObject();
        if(!jbOrderStr.toString().contains("IdInvoiceContentTypeBook") || jbOrderStr.getInt("IdInvoiceContentTypeBook") == -1) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("IsPutBookInvoice", true);
_L3:
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("CartStr", jbCartStr);
_L4:
        JSONObject jsonobject = jbBody;
        this;
        JVM INSTR monitorexit ;
        return jsonobject;
_L2:
        jbOrderStr.put("IsPutBookInvoice", false);
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L4
        Exception exception;
        exception;
        throw exception;
          goto _L3
    }

    private void setBalanceView()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(!Contants.bEasyBuy) goto _L2; else goto _L1
_L1:
                if(!DefaultEasyTempOrderInfo.jsonDefaultTemp.toString().contains("IsUseBalance")) goto _L4; else goto _L3
_L3:
                if(DefaultEasyTempOrderInfo.jsonDefaultTemp.getBoolean("IsUseBalance"))
                    FillOrderActivity.bUseBalance = true;
                else
                    FillOrderActivity.bUseBalance = false;
_L13:
                if(!FillOrderActivity.bUseBalance) goto _L6; else goto _L5
_L5:
                mBalance.setText("\u4F7F\u7528");
_L10:
                if(!DefaultEasyTempOrderInfo.jsonDefaultTemp.toString().contains("IsUseBalance")) goto _L8; else goto _L7
_L7:
                FillOrderActivity.jbOrderStr.put("IsUseBalance", DefaultEasyTempOrderInfo.jsonDefaultTemp.getBoolean("IsUseBalance"));
_L11:
                mBalance.setEnabled(false);
                  goto _L9
                Exception exception;
                exception;
                exception.printStackTrace();
                  goto _L9
_L4:
                FillOrderActivity.bUseBalance = true;
                continue; /* Loop/switch isn't completed */
_L6:
                mBalance.setText("\u4E0D\u4F7F\u7528");
                  goto _L10
_L8:
                FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                  goto _L11
_L2:
                if(Contants.bModifyEasyBuy)
                {
                    if(DefaultEasyTempOrderInfo.jsonTemp.toString().contains("IsUseBalance"))
                    {
                        if(DefaultEasyTempOrderInfo.jsonTemp.getJSONObjectOrNull("Info").getBoolean("IsUseBalance"))
                            FillOrderActivity.bUseBalance = true;
                        else
                            FillOrderActivity.bUseBalance = false;
                    } else
                    {
                        FillOrderActivity.bUseBalance = true;
                    }
                    if(FillOrderActivity.bUseBalance)
                        mBalance.setText("\u4F7F\u7528");
                    else
                        mBalance.setText("\u4E0D\u4F7F\u7528");
                    if(DefaultEasyTempOrderInfo.jsonTemp.toString().contains("IsUseBalance"))
                        FillOrderActivity.jbOrderStr.put("IsUseBalance", DefaultEasyTempOrderInfo.jsonTemp.getJSONObjectOrNull("Info").getBoolean("IsUseBalance"));
                    else
                        FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                    mBalance.setEnabled(true);
                } else
                if(Contants.bAddEasyBuy)
                {
                    if(FillOrderActivity.bUseBalance)
                    {
                        mBalance.setText("\u4F7F\u7528");
                        FillOrderActivity.jbOrderStr.put("IsUseBalance", true);
                    } else
                    {
                        mBalance.setText("\u4E0D\u4F7F\u7528");
                        FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                    }
                } else
                if(FillOrderActivity.bUseBalance)
                {
                    mBalance.setText((new StringBuilder(String.valueOf(FillOrderActivity.jbBalance.get("Balance").toString()))).append("\u5143").toString());
                    FillOrderActivity.jbOrderStr.put("IsUseBalance", true);
                } else
                {
                    mBalance.setText("\u4E0D\u4F7F\u7528");
                    FillOrderActivity.jbOrderStr.put("IsUseBalance", false);
                }
_L9:
                return;
                if(true) goto _L13; else goto _L12
_L12:
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setEmptyInvoinceInfoArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                myAdapter = null;
                if(LastOrderInfo.mUserInfo.getUserName() == null)
                {
                    LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                    RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c009f);
                    RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030038, null).findViewById(0x7f0c0120);
                    invoinceInfoList = (ListView)relativelayout1.getChildAt(0);
                    if(Log.D)
                        Log.d("test", "ui1");
                    value = new ArrayList();
                    HashMap hashmap = new HashMap();
                    hashmap.put("info", " ");
                    value.add(hashmap);
                    FillOrderActivity fillorderactivity = FillOrderActivity.this;
                    FillOrderActivity fillorderactivity1 = FillOrderActivity.this;
                    List list = value;
                    String as[] = new String[1];
                    as[0] = "info";
                    int ai[] = new int[1];
                    ai[0] = 0x7f0c011e;
                    fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f030037, as, ai);
                    invoinceInfoList.setAdapter(myAdapter);
                    invoinceInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                    relativelayout.removeAllViews();
                    relativelayout.addView(relativelayout1);
                    invoinceInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView adapterview, View view, int i, long l)
                        {
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/ReceiptInfoEditActivity);
                            startActivityForResult(intent, 4);
                        }

                        final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super();
                    }
                    }
);
                }
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setEmptyPaymentInfoArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                myAdapter = null;
                if(LastOrderInfo.mUserInfo.getUserName() == null)
                {
                    LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                    RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c009e);
                    RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030058, null).findViewById(0x7f0c01cf);
                    paymentInfoList = (ListView)relativelayout1.getChildAt(0);
                    value = new ArrayList();
                    HashMap hashmap = new HashMap();
                    hashmap.put("info", " ");
                    value.add(hashmap);
                    FillOrderActivity fillorderactivity = FillOrderActivity.this;
                    FillOrderActivity fillorderactivity1 = FillOrderActivity.this;
                    List list = value;
                    String as[] = new String[1];
                    as[0] = "info";
                    int ai[] = new int[1];
                    ai[0] = 0x7f0c01d6;
                    fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f03005a, as, ai);
                    paymentInfoList.setAdapter(myAdapter);
                    paymentInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                    relativelayout.removeAllViews();
                    relativelayout.addView(relativelayout1);
                    paymentInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView adapterview, View view, int i, long l)
                        {
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/SelectPaymentType);
                            startActivityForResult(intent, 3);
                        }

                        final _cls14 this$1;

                    
                    {
                        this$1 = _cls14.this;
                        super();
                    }
                    }
);
                }
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setEmptyReceiverInfoArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                myAdapter = null;
                if(LastOrderInfo.mUserInfo.getUserName() == null)
                {
                    LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                    RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c009d);
                    RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030061, null).findViewById(0x7f0c01ee);
                    receiverInfoList = (ListView)relativelayout1.getChildAt(0);
                    if(Log.D)
                        Log.d("test", "ui1");
                    value = new ArrayList();
                    HashMap hashmap = new HashMap();
                    hashmap.put("info", " ");
                    value.add(hashmap);
                    FillOrderActivity fillorderactivity = FillOrderActivity.this;
                    FillOrderActivity fillorderactivity1 = FillOrderActivity.this;
                    List list = value;
                    String as[] = new String[1];
                    as[0] = "info";
                    int ai[] = new int[1];
                    ai[0] = 0x7f0c01d3;
                    fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f030059, as, ai);
                    receiverInfoList.setAdapter(myAdapter);
                    receiverInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                    relativelayout.removeAllViews();
                    relativelayout.addView(relativelayout1);
                    receiverInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView adapterview, View view, int i, long l)
                        {
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/ModifyOrderAddr);
                            startActivityForResult(intent, 1);
                        }

                        final _cls11 this$1;

                    
                    {
                        this$1 = _cls11.this;
                        super();
                    }
                    }
);
                }
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setGetBySelfArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                HashMap hashmap;
                LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c009e);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030062, null).findViewById(0x7f0c01f0);
                paymentInfoList = (ListView)relativelayout1.getChildAt(1);
                value = new ArrayList();
                bundle = new Bundle();
                hashmap = new HashMap();
                JSONArray jsonarray;
                hashmap.put("PayName", FillOrderActivity.mNewPaymentInfo.getSelectedPaymentType(3).getString("Name"));
                new JSONArray();
                jsonarray = FillOrderActivity.mNewPaymentInfo.getPaymentDetailInfos();
                if(Log.D)
                    Log.d("Picksite", LastOrderInfo.mPaymentInfo.getPayMentType(3).get("IdPickSite").toString());
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(3).toString().contains("IdPickSite")) goto _L2; else goto _L1
_L1:
                int j = 0;
_L10:
                if(j < jsonarray.length()) goto _L4; else goto _L3
_L3:
                value.add(hashmap);
                FillOrderActivity fillorderactivity = FillOrderActivity.this;
                FillOrderActivity fillorderactivity1 = FillOrderActivity.this;
                List list = value;
                String as[] = new String[3];
                as[0] = "PayName";
                as[1] = "Name";
                as[2] = "Address";
                int ai[] = new int[3];
                ai[0] = 0x7f0c01d7;
                ai[1] = 0x7f0c01d9;
                ai[2] = 0x7f0c01db;
                fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f03005b, as, ai);
                paymentInfoList.setAdapter(myAdapter);
                relativelayout1.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                if(!Contants.bEasyBuy) goto _L6; else goto _L5
_L5:
                paymentInfoList.setEnabled(false);
                paymentInfoList.setClickable(false);
_L12:
                paymentInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int k, long l)
                    {
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/SelectPaymentType);
                        try
                        {
                            if(newWay)
                                bundle.putBoolean("new_way", true);
                            bundle.putString("pay_type", FillOrderActivity.mNewPaymentInfo.getSelectedPaymentType(3).getString("Name"));
                        }
                        catch(JSONException jsonexception1)
                        {
                            jsonexception1.printStackTrace();
                        }
                        bundle.putInt("type", 3);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 3);
                    }

                    final _cls15 this$1;

                    
                    {
                        this$1 = _cls15.this;
                        super();
                    }
                }
);
                  goto _L7
_L4:
                if(Log.D)
                    Log.d("Picksite", jsonarray.getJSONObject(j).get("Id").toString());
                if(jsonarray.getJSONObject(j).getInt("Id") != LastOrderInfo.mPaymentInfo.getPayMentType(3).getInt("IdPickSite")) goto _L9; else goto _L8
_L8:
                hashmap.put("Name", jsonarray.getJSONObject(j).getString("Name"));
                bundle.putString("get_position", jsonarray.getJSONObject(j).getString("Name"));
                hashmap.put("Address", jsonarray.getJSONObject(j).getString("Address"));
                  goto _L3
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L7
_L9:
                j++;
                  goto _L10
_L2:
                int i = 0;
_L13:
                if(i >= jsonarray.length()) goto _L3; else goto _L11
_L11:
                if(jsonarray.getJSONObject(i).getInt("Id") != FillOrderActivity.mNewPaymentInfo.getPayMentType(3).getInt("IdPickSite"))
                    break MISSING_BLOCK_LABEL_662;
                hashmap.put("Name", jsonarray.getJSONObject(i).getString("Name"));
                bundle.putString("get_position", jsonarray.getJSONObject(i).getString("Name"));
                hashmap.put("Address", jsonarray.getJSONObject(i).getString("Address"));
                  goto _L3
_L6:
                if(Contants.bModifyEasyBuy)
                {
                    paymentInfoList.setEnabled(true);
                    paymentInfoList.setClickable(true);
                }
                  goto _L12
_L7:
                return;
                i++;
                  goto _L13
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setGetBySelfInfo(JSONObjectProxy jsonobjectproxy, String s)
    {
        mNewPaymentInfo.setPaymentDetailInfos(jsonobjectproxy.getJSONArray("PickSites"));
        mNewPaymentInfo.setPayMentType(3, jsonobjectproxy);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    private void setInvoinceInfoArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(LastOrderInfo.mUserInfo.getUserName() != null) goto _L2; else goto _L1
_L1:
                setEmptyInvoinceInfoArea();
_L23:
                return;
_L2:
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                HashMap hashmap;
                myAdapter = null;
                if(Log.D)
                    Log.d("test", "ui2");
                LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c009f);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030039, null).findViewById(0x7f0c0122);
                invoinceInfoList = (ListView)relativelayout1.getChildAt(1);
                value = new ArrayList();
                hashmap = new HashMap();
                if(Log.D)
                    Log.d(TAG, LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString());
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceType") || !FillOrderActivity.mNewInvoiceInfo.getInvoinceTypes().toString().contains("invoiceTypesInfo")) goto _L4; else goto _L3
_L3:
                int i2 = FillOrderActivity.mNewInvoiceInfo.getInvoinceTypes().getJSONObject("invoiceTypesInfo").getJSONArray("InvoiceTypes").length();
                int j2 = 0;
_L24:
                int i;
                int j;
                int i1;
                int j1;
                if(j2 < i2)
                    try
                    {
                        if(FillOrderActivity.mNewInvoiceInfo.getInvoinceTypes().getJSONObject("invoiceTypesInfo").getJSONArray("InvoiceTypes").getJSONObject(j2).getInt("Id") != 1)
                            break MISSING_BLOCK_LABEL_789;
                        hashmap.put("type", FillOrderActivity.mNewInvoiceInfo.getInvoinceTypes().getJSONObject("invoiceTypesInfo").getJSONArray("InvoiceTypes").getJSONObject(j2).getString("Name"));
                    }
                    catch(JSONException jsonexception7)
                    {
                        jsonexception7.printStackTrace();
                    }
_L25:
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceHeaderType")) goto _L6; else goto _L5
_L5:
                if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceHeaderType") != 5) goto _L8; else goto _L7
_L7:
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("CompanyName") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName") == null || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName") == "" || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName") == " ") goto _L10; else goto _L9
_L9:
                if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("InvoiceTitle").compareTo("\u5355\u4F4D") != 0) goto _L12; else goto _L11
_L11:
                hashmap.put("title", (new StringBuilder("\u5355\u4F4D(")).append(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName")).append(")").toString());
_L6:
                if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy) goto _L14; else goto _L13
_L13:
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentTypeBook")) goto _L16; else goto _L15
_L15:
                i1 = FillOrderActivity.mNewInvoiceInfo.getInvoinceBooks().getJSONArray("InvoiceContents").length();
                j1 = 0;
_L26:
                if(j1 < i1)
                    try
                    {
                        if(FillOrderActivity.mNewInvoiceInfo.getInvoinceBooks().getJSONArray("InvoiceContents").getJSONObject(j1).getInt("Id") != LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentTypeBook"))
                            break MISSING_BLOCK_LABEL_1031;
                        hashmap.put("books", FillOrderActivity.mNewInvoiceInfo.getInvoinceBooks().getJSONArray("InvoiceContents").getJSONObject(j1).getString("Name"));
                    }
                    catch(JSONException jsonexception1)
                    {
                        jsonexception1.printStackTrace();
                    }
_L27:
                if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy && !Contants.bEasyBuy) goto _L18; else goto _L17
_L17:
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentsType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentsType") == -1) goto _L20; else goto _L19
_L19:
                i = FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").length();
                j = 0;
_L36:
                if(j < i) goto _L22; else goto _L21
_L21:
                value.add(hashmap);
                FillOrderActivity fillorderactivity = FillOrderActivity.this;
                FillOrderActivity fillorderactivity1 = FillOrderActivity.this;
                List list = value;
                String as[] = new String[4];
                as[0] = "type";
                as[1] = "title";
                as[2] = "books";
                as[3] = "general";
                int ai[] = new int[4];
                ai[0] = 0x7f0c0125;
                ai[1] = 0x7f0c0128;
                ai[2] = 0x7f0c012b;
                ai[3] = 0x7f0c012d;
                fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f03003a, as, ai);
                invoinceInfoList.setAdapter(myAdapter);
                relativelayout1.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                JSONException jsonexception;
                JSONException jsonexception2;
                JSONException jsonexception3;
                int k;
                int l;
                JSONException jsonexception4;
                JSONException jsonexception5;
                int k1;
                int l1;
                JSONException jsonexception6;
                if(Contants.bEasyBuy)
                {
                    invoinceInfoList.setEnabled(false);
                    invoinceInfoList.setClickable(false);
                } else
                if(Contants.bModifyEasyBuy)
                {
                    invoinceInfoList.setEnabled(true);
                    invoinceInfoList.setClickable(true);
                }
                invoinceInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int k2, long l2)
                    {
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/ReceiptInfoEditActivity);
                        startActivityForResult(intent, 4);
                    }

                    final _cls12 this$1;

                    
                    {
                        this$1 = _cls12.this;
                        super();
                    }
                }
);
                  goto _L23
                j2++;
                  goto _L24
_L4:
                try
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceType", 1);
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
                hashmap.put("type", "\u666E\u901A\u53D1\u7968");
                  goto _L25
_L12:
                try
                {
                    hashmap.put("title", (new StringBuilder("\u5355\u4F4D(")).append(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName")).append(")").toString());
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception6)
                {
                    jsonexception6.printStackTrace();
                }
                  goto _L6
_L10:
                hashmap.put("title", "\u5355\u4F4D");
                  goto _L6
_L8:
                if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceHeaderType") == 4)
                    hashmap.put("title", "\u4E2A\u4EBA");
                else
                    hashmap.put("title", "\u4E2A\u4EBA");
                  goto _L6
                j1++;
                  goto _L26
_L16:
                try
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceContentTypeBook", -1);
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception4)
                {
                    jsonexception4.printStackTrace();
                }
                hashmap.put("books", "\u4E0D\u5F00\u53D1\u7968");
                  goto _L27
_L14:
                if(!noBook) goto _L29; else goto _L28
_L28:
                hashmap.put("books", "");
                  goto _L27
_L29:
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentTypeBook")) goto _L31; else goto _L30
_L30:
                k1 = FillOrderActivity.mNewInvoiceInfo.getInvoinceBooks().getJSONArray("InvoiceContents").length();
                l1 = 0;
_L35:
                if(l1 >= k1) goto _L27; else goto _L32
_L32:
                if(FillOrderActivity.mNewInvoiceInfo.getInvoinceBooks().getJSONArray("InvoiceContents").getJSONObject(l1).getInt("Id") != LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentTypeBook")) goto _L34; else goto _L33
_L33:
                hashmap.put("books", FillOrderActivity.mNewInvoiceInfo.getInvoinceBooks().getJSONArray("InvoiceContents").getJSONObject(l1).getString("Name"));
                  goto _L27
_L34:
                l1++;
                  goto _L35
_L31:
                try
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceContentTypeBook", -1);
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception5)
                {
                    jsonexception5.printStackTrace();
                }
                hashmap.put("books", "\u4E0D\u5F00\u53D1\u7968");
                  goto _L27
_L22:
label0:
                {
                    if(FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").getJSONObject(j).getInt("Id") != LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentsType"))
                        break label0;
                    hashmap.put("general", FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").getJSONObject(j).getString("Name"));
                }
                  goto _L21
                j++;
                  goto _L36
_L20:
                try
                {
                    hashmap.put("general", " ");
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception2)
                {
                    jsonexception2.printStackTrace();
                }
                  goto _L21
_L18:
label1:
                {
                    if(!onlyBook)
                        break label1;
                    hashmap.put("general", " ");
                }
                  goto _L21
                if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentsType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentsType") == -1)
                    break MISSING_BLOCK_LABEL_1609;
                if(FillOrderActivity.mNewInvoiceInfo == null || FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals() == null || FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").get("InvoiceContents") == null)
                    break MISSING_BLOCK_LABEL_1594;
                k = FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").length();
                l = 0;
_L37:
                if(l < k)
                {
                    if(FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").getJSONObject(l).getInt("Id") != LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentsType"))
                        break MISSING_BLOCK_LABEL_1588;
                    hashmap.put("general", FillOrderActivity.mNewInvoiceInfo.getInvoinceGenerals().getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").getJSONObject(l).getString("Name"));
                }
                  goto _L21
                jsonexception3;
                jsonexception3.printStackTrace();
                  goto _L21
                l++;
                  goto _L37
                hashmap.put("general", " ");
                  goto _L21
                hashmap.put("general", " ");
                  goto _L21
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setMoneyInfo(final JSONArray jbMoney)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                int i = 0;
_L2:
                if(i < jbMoney.length())
                {
                    if(jbMoney.getJSONObject(i).getString("label").contains("\u5546\u54C1\u91D1\u989D"))
                    {
                        mTotalPrice.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                        Contants.dYTotalPrice = jbMoney.getJSONObject(i).getString("value");
                        break MISSING_BLOCK_LABEL_548;
                    }
                    JSONException jsonexception;
                    if(jbMoney.getJSONObject(i).getString("label").contains("\u8FD0\u8D39"))
                    {
                        mTransportFee.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                        break MISSING_BLOCK_LABEL_548;
                    }
                    try
                    {
                        if(jbMoney.getJSONObject(i).getString("label").contains("\u4F18\u60E0\u5238"))
                            mYouhuiQuan.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                        else
                        if(jbMoney.getJSONObject(i).getString("label").contains("\u793C\u54C1\u5361"))
                            mLipin.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                        else
                        if(jbMoney.getJSONObject(i).getString("label").contains("\u8FD4\u73B0"))
                            mFanXian.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                        else
                        if(jbMoney.getJSONObject(i).getString("label").contains("\u4F59\u989D"))
                            mBalance1.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                        else
                        if(jbMoney.getJSONObject(i).getString("label").contains("\u5E94\u4ED8\u603B\u989D"))
                        {
                            mLastMoney.setText((new StringBuilder(String.valueOf(jbMoney.getJSONObject(i).getString("value")))).append("\u5143").toString());
                            sTotalMoney = jbMoney.getJSONObject(i).getString("value");
                            Contants.dTotalPrice = jbMoney.getJSONObject(i).getString("value");
                        }
                        break MISSING_BLOCK_LABEL_548;
                    }
                    // Misplaced declaration of an exception variable
                    catch(JSONException jsonexception)
                    {
                        if(Log.D)
                            Log.d(TAG, jsonexception.getMessage());
                    }
                }
                return;
                i++;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final FillOrderActivity this$0;
            private final JSONArray val$jbMoney;

            
            {
                this$0 = FillOrderActivity.this;
                jbMoney = jsonarray;
                super();
            }
        }
);
    }

    private void setPayAfterReceiveArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                HashMap hashmap;
                LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c009e);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030062, null).findViewById(0x7f0c01f0);
                paymentInfoList = (ListView)relativelayout1.getChildAt(1);
                value = new ArrayList();
                bundle = new Bundle();
                hashmap = new HashMap();
                JSONObject jsonobject;
                JSONArray jsonarray;
                JSONArray jsonarray1;
                int k;
                int i1;
                FillOrderActivity fillorderactivity;
                FillOrderActivity fillorderactivity1;
                List list;
                String as[];
                int ai[];
                ListView listview;
                android.widget.AdapterView.OnItemClickListener onitemclicklistener;
                int l;
                if(LastOrderInfo.mPaymentInfo.nSelected == 4)
                {
                    hashmap.put("PayName", "\u5728\u7EBF\u652F\u4ED8");
                    bundle.putString("pay_type", "\u5728\u7EBF\u652F\u4ED8");
                } else
                {
                    hashmap.put("PayName", FillOrderActivity.mNewPaymentInfo.getSelectedPaymentType(1).getString("Name"));
                    bundle.putString("pay_type", FillOrderActivity.mNewPaymentInfo.getSelectedPaymentType(1).getString("Name"));
                }
                jsonobject = new JSONObject();
                if(Log.D)
                    Log.d("test", FillOrderActivity.mNewPaymentInfo.getPayMentType(1).toString());
                if(FillOrderActivity.mNewPaymentInfo.getPayMentType(1) == null || FillOrderActivity.mNewPaymentInfo.getPayMentType(1).toString() == "null" || FillOrderActivity.mNewPaymentInfo.getPayMentType(1).get("ShipmentTypes") == null || FillOrderActivity.mNewPaymentInfo.getPayMentType(1).get("ShipmentTypes").toString() == "null") goto _L2; else goto _L1
_L1:
                if(FillOrderActivity.mNewPaymentInfo.getPayMentType(1).getJSONArray("ShipmentTypes").length() <= 1) goto _L4; else goto _L3
_L3:
                i1 = 0;
_L37:
                if(i1 < FillOrderActivity.mNewPaymentInfo.getPayMentType(1).getJSONArray("ShipmentTypes").length()) goto _L6; else goto _L5
_L5:
                if(Log.D)
                    Log.d("ship", jsonobject.toString());
                hashmap.put("PeisongName", jsonobject.getString("Name"));
                bundle.putString("send_type", jsonobject.getString("Name"));
                new JSONArray();
                new JSONArray();
                new JSONArray();
                if(jsonobject.get("SupCodTime") == null || jsonobject.get("SupCodTime").toString() == "null" || jsonobject.getJSONArray("SupCodTime") == null || jsonobject.getJSONArray("SupCodTime").toString() == "null") goto _L8; else goto _L7
_L7:
                jsonarray1 = jsonobject.getJSONArray("SupCodTime");
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(1).toString().contains("CODTime")) goto _L10; else goto _L9
_L9:
                if(LastOrderInfo.mPaymentInfo.getPayMentType(1).getInt("CODTime") <= 0)
                    LastOrderInfo.mPaymentInfo.getPayMentType(1).put("CODTime", 1);
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(1).toString().contains("CODTime")) goto _L12; else goto _L11
_L11:
                k = 0;
_L38:
                l = jsonarray1.length();
                if(k < l) goto _L13; else goto _L12
_L12:
                if(jsonobject.get("SupPaymentWay") == null || jsonobject.get("SupPaymentWay").toString() == "null" || jsonobject.getJSONArray("SupPaymentWay") == null || jsonobject.getJSONArray("SupPaymentWay").toString() == "null") goto _L15; else goto _L14
_L14:
                jsonarray = jsonobject.getJSONArray("SupPaymentWay");
                if(LastOrderInfo.mPaymentInfo.nSelected != 4) goto _L17; else goto _L16
_L16:
                hashmap.put("PaymentWayName", " ");
                bundle.putString("pay_content", " ");
_L33:
                if(jsonobject.get("SupInforms") == null || jsonobject.get("SupInforms").toString() == "null" || jsonobject.getJSONArray("SupInforms") == null || jsonobject.getJSONArray("SupInforms").toString() == "null") goto _L19; else goto _L18
_L18:
                jsonobject.getJSONArray("SupInforms");
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(1).toString().contains("IsCodInform")) goto _L21; else goto _L20
_L20:
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(1).getBoolean("IsCodInform")) goto _L23; else goto _L22
_L22:
                hashmap.put("mInforms", "\u662F");
                bundle.putString("phone_confirm", "\u662F");
                Contants.bPhone = true;
_L34:
                value.add(hashmap);
_L35:
                fillorderactivity = FillOrderActivity.this;
                fillorderactivity1 = FillOrderActivity.this;
                list = value;
                as = new String[5];
                as[0] = "PayName";
                as[1] = "PeisongName";
                as[2] = "mCodTime";
                as[3] = "mInforms";
                as[4] = "PaymentWayName";
                ai = new int[5];
                ai[0] = 0x7f0c01d7;
                ai[1] = 0x7f0c01dd;
                ai[2] = 0x7f0c01df;
                ai[3] = 0x7f0c01e1;
                ai[4] = 0x7f0c01e3;
                fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f03005c, as, ai);
                paymentInfoList.setAdapter(myAdapter);
                relativelayout1.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                if(!Contants.bEasyBuy) goto _L25; else goto _L24
_L24:
                paymentInfoList.setEnabled(false);
                paymentInfoList.setClickable(false);
_L36:
                listview = paymentInfoList;
                onitemclicklistener = new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int j1, long l1)
                    {
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/SelectPaymentType);
                        if(LastOrderInfo.mPaymentInfo.nSelected == 4)
                            bundle.putInt("type", 4);
                        else
                            bundle.putInt("type", 1);
                        if(newWay)
                            bundle.putBoolean("new_way", true);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 3);
                    }

                    final _cls16 this$1;

                    
                    {
                        this$1 = _cls16.this;
                        super();
                    }
                }
;
                listview.setOnItemClickListener(onitemclicklistener);
                  goto _L26
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L26
_L6:
                if(LastOrderInfo.mPaymentInfo.getPayMentType(1).getInt("IdShipmentType") != FillOrderActivity.mNewPaymentInfo.getPayMentType(1).getJSONArray("ShipmentTypes").getJSONObject(i1).getInt("Id")) goto _L28; else goto _L27
_L27:
                jsonobject = FillOrderActivity.mNewPaymentInfo.getPayMentType(1).getJSONArray("ShipmentTypes").getJSONObject(i1);
                  goto _L5
_L4:
                jsonobject = FillOrderActivity.mNewPaymentInfo.getPayMentType(1).getJSONArray("ShipmentTypes").getJSONObject(0);
                  goto _L5
_L13:
                if(jsonarray1.getJSONObject(k).getInt("Id") != LastOrderInfo.mPaymentInfo.getPayMentType(1).getInt("CODTime")) goto _L30; else goto _L29
_L29:
                hashmap.put("mCodTime", jsonarray1.getJSONObject(k).getString("Name"));
                bundle.putString("send_time", jsonarray1.getJSONObject(k).getString("Name"));
                  goto _L12
_L10:
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                  goto _L12
_L8:
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                  goto _L12
_L17:
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(1).toString().contains("PaymentWay")) goto _L32; else goto _L31
_L31:
                int i = 0;
_L39:
                int j = jsonarray.length();
                if(i < j)
                {
                    if(jsonarray.getJSONObject(i).getInt("Id") != LastOrderInfo.mPaymentInfo.getPayMentType(1).getInt("PaymentWay"))
                        break MISSING_BLOCK_LABEL_1636;
                    hashmap.put("PaymentWayName", jsonarray.getJSONObject(i).getString("Name"));
                    bundle.putString("pay_content", jsonarray.getJSONObject(i).getString("Name"));
                }
                  goto _L33
_L32:
                hashmap.put("PaymentWayName", " ");
                bundle.putString("pay_content", " ");
                  goto _L33
_L15:
                hashmap.put("PaymentWayName", " ");
                bundle.putString("pay_content", " ");
                  goto _L33
_L23:
                hashmap.put("mInforms", "\u5426");
                Contants.bPhone = false;
                bundle.putString("phone_confirm", "\u5426");
                  goto _L34
_L21:
                hashmap.put("mInforms", " ");
                Contants.bPhone = false;
                bundle.putString("phone_confirm", " ");
                  goto _L34
_L19:
                hashmap.put("mInforms", " ");
                Contants.bPhone = false;
                bundle.putString("phone_confirm", " ");
                  goto _L34
_L2:
                hashmap.put("PeisongName", " ");
                bundle.putString("send_type", " ");
                hashmap.put("PaymentWayName", " ");
                bundle.putString("pay_content", " ");
                hashmap.put("mInforms", " ");
                bundle.putString("phone_confirm", " ");
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                value.add(hashmap);
                  goto _L35
_L25:
                if(Contants.bModifyEasyBuy)
                {
                    paymentInfoList.setEnabled(true);
                    paymentInfoList.setClickable(true);
                }
                  goto _L36
_L26:
                return;
_L28:
                i1++;
                  goto _L37
_L30:
                k++;
                  goto _L38
                i++;
                  goto _L39
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setPaymentInfo()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(LastOrderInfo.mUserInfo.getUserName() == null)
                {
                    setEmptyReceiverInfoArea();
                    setEmptyPaymentInfoArea();
                } else
                {
                    setReceiverInfoArea();
                    if(LastOrderInfo.mPaymentInfo.nSelected > 4 || LastOrderInfo.mPaymentInfo.nSelected < 1)
                        LastOrderInfo.mPaymentInfo.nSelected = 1;
                    if(!FillOrderActivity.mNewPaymentInfo.getPaymentTypes().toString().contains(String.valueOf(LastOrderInfo.mPaymentInfo.nSelected)))
                        try
                        {
                            LastOrderInfo.mPaymentInfo.nSelected = FillOrderActivity.mNewPaymentInfo.getPaymentTypes().getJSONObject(0).getInt("Id");
                        }
                        catch(JSONException jsonexception)
                        {
                            jsonexception.printStackTrace();
                        }
                    getPaymentDetatilInformation(LastOrderInfo.mPaymentInfo.nSelected);
                }
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setPaymentInfoAreaWithItem(int i)
    {
        i;
        JVM INSTR tableswitch 1 3: default 28
    //                   1 36
    //                   2 43
    //                   3 29;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L4:
        setGetBySelfArea();
        continue; /* Loop/switch isn't completed */
_L2:
        setPayAfterReceiveArea();
        continue; /* Loop/switch isn't completed */
_L3:
        setPostArea();
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void setPeiSongInfo(JSONObjectProxy jsonobjectproxy, String s)
    {
        mNewPaymentInfo.setShipments(jsonobjectproxy);
        mNewPaymentInfo.setPayMentType(1, jsonobjectproxy);
        if(update_by_addr)
        {
            updatePayWayInfo(jsonobjectproxy);
            update_by_addr = false;
        }
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    private void setPostArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                HashMap hashmap;
                LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c009e);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030062, null).findViewById(0x7f0c01f0);
                paymentInfoList = (ListView)relativelayout1.getChildAt(1);
                bundle = new Bundle();
                value = new ArrayList();
                hashmap = new HashMap();
                JSONObject jsonobject;
                hashmap.put("PayName", FillOrderActivity.mNewPaymentInfo.getSelectedPaymentType(2).getString("Name"));
                bundle.putString("pay_type", FillOrderActivity.mNewPaymentInfo.getSelectedPaymentType(2).getString("Name"));
                jsonobject = new JSONObject();
                if(FillOrderActivity.mNewPaymentInfo.getPayMentType(1) == null || FillOrderActivity.mNewPaymentInfo.getPayMentType(1).toString() == "null" || FillOrderActivity.mNewPaymentInfo.getPayMentType(1).get("ShipmentTypes") == null || FillOrderActivity.mNewPaymentInfo.getPayMentType(1).get("ShipmentTypes").toString() == "null") goto _L2; else goto _L1
_L1:
                if(FillOrderActivity.mNewPaymentInfo.getShipments() == null || FillOrderActivity.mNewPaymentInfo.getShipments().get("ShipmentTypes") == null || FillOrderActivity.mNewPaymentInfo.getShipments().get("ShipmentTypes").toString() == "null") goto _L4; else goto _L3
_L3:
                if(FillOrderActivity.mNewPaymentInfo.getShipments().getJSONArray("ShipmentTypes").length() <= 1) goto _L6; else goto _L5
_L5:
                int k = 0;
_L33:
                JSONArray jsonarray;
                int i;
                int j;
                if(k < FillOrderActivity.mNewPaymentInfo.getShipments().getJSONArray("ShipmentTypes").length())
                {
                    if(LastOrderInfo.mPaymentInfo.getPayMentType(2).getInt("IdShipmentType") != FillOrderActivity.mNewPaymentInfo.getShipments().getJSONArray("ShipmentTypes").getJSONObject(k).getInt("Id"))
                        break MISSING_BLOCK_LABEL_1654;
                    jsonobject = FillOrderActivity.mNewPaymentInfo.getShipments().getJSONArray("ShipmentTypes").getJSONObject(k);
                }
_L25:
                hashmap.put("PeisongName", jsonobject.getString("Name"));
                bundle.putString("send_type", jsonobject.getString("Name"));
                new JSONArray();
                new JSONArray();
                if(jsonobject.get("SupCodTime") == null || jsonobject.get("SupCodTime").toString() == "null" || jsonobject.getJSONArray("SupCodTime") == null || jsonobject.getJSONArray("SupCodTime").toString() == "null") goto _L8; else goto _L7
_L7:
                jsonarray = jsonobject.getJSONArray("SupCodTime");
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(2).toString().contains("CODTime")) goto _L10; else goto _L9
_L9:
                if(LastOrderInfo.mPaymentInfo.getPayMentType(2).getInt("CODTime") <= 0)
                    LastOrderInfo.mPaymentInfo.getPayMentType(2).put("CODTime", 1);
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(2).toString().contains("CODTime")) goto _L12; else goto _L11
_L11:
                i = 0;
_L28:
                j = jsonarray.length();
                if(i < j) goto _L13; else goto _L8
_L8:
                if(jsonobject.get("SupInforms") == null || jsonobject.get("SupInforms").toString() == "null" || jsonobject.getJSONArray("SupInforms") == null || jsonobject.getJSONArray("SupInforms").toString() == "null") goto _L15; else goto _L14
_L14:
                jsonobject.getJSONArray("SupInforms");
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(2).toString().contains("IsCodInform")) goto _L17; else goto _L16
_L16:
                if(!LastOrderInfo.mPaymentInfo.getPayMentType(2).getBoolean("IsCodInform")) goto _L19; else goto _L18
_L18:
                hashmap.put("mInforms", "\u662F");
                bundle.putString("phone_confirm", "\u662F");
                Contants.bPhone = true;
_L29:
                if(!FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(0).toString().contains("\u5546\u6237\u5BA2\u6237\u53F7")) goto _L21; else goto _L20
_L20:
                hashmap.put("postid", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(0).getString("value"));
                hashmap.put("postreceiver", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(1).getString("value"));
_L30:
                value.add(hashmap);
_L31:
                FillOrderActivity fillorderactivity = FillOrderActivity.this;
                FillOrderActivity fillorderactivity1 = FillOrderActivity.this;
                List list = value;
                String as[] = new String[6];
                as[0] = "PayName";
                as[1] = "PeisongName";
                as[2] = "mCodTime";
                as[3] = "mInforms";
                as[4] = "postreceiver";
                as[5] = "postid";
                int ai[] = new int[6];
                ai[0] = 0x7f0c01d7;
                ai[1] = 0x7f0c01dd;
                ai[2] = 0x7f0c01df;
                ai[3] = 0x7f0c01e1;
                ai[4] = 0x7f0c01e9;
                ai[5] = 0x7f0c01e7;
                fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f03005d, as, ai);
                paymentInfoList.setAdapter(myAdapter);
                relativelayout1.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                if(!Contants.bEasyBuy) goto _L23; else goto _L22
_L22:
                paymentInfoList.setEnabled(false);
                paymentInfoList.setClickable(false);
_L32:
                ListView listview = paymentInfoList;
                android.widget.AdapterView.OnItemClickListener onitemclicklistener = new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int l, long l1)
                    {
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/SelectPaymentType);
                        bundle.putInt("type", 2);
                        if(newWay)
                            bundle.putBoolean("new_way", true);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 3);
                    }

                    final _cls17 this$1;

                    
                    {
                        this$1 = _cls17.this;
                        super();
                    }
                }
;
                listview.setOnItemClickListener(onitemclicklistener);
                  goto _L24
_L6:
                jsonobject = FillOrderActivity.mNewPaymentInfo.getShipments().getJSONArray("ShipmentTypes").getJSONObject(0);
                  goto _L25
_L13:
                if(jsonarray.getJSONObject(i).getInt("Id") != LastOrderInfo.mPaymentInfo.getPayMentType(2).getInt("CODTime")) goto _L27; else goto _L26
_L26:
                hashmap.put("mCodTime", jsonarray.getJSONObject(i).getString("Name"));
                bundle.putString("send_time", jsonarray.getJSONObject(i).getString("Name"));
                  goto _L8
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L24
_L27:
                i++;
                  goto _L28
_L12:
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                  goto _L8
_L10:
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                  goto _L8
_L19:
                hashmap.put("mInforms", "\u5426");
                bundle.putString("phone_confirm", "\u5426");
                Contants.bPhone = false;
                  goto _L29
_L17:
                hashmap.put("mInforms", " ");
                bundle.putString("phone_confirm", " ");
                Contants.bPhone = false;
                  goto _L29
_L15:
                hashmap.put("mInforms", " ");
                bundle.putString("phone_confirm", " ");
                Contants.bPhone = false;
                  goto _L29
_L21:
                hashmap.put("postreceiver", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(0).getString("value"));
                hashmap.put("postid", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(1).getString("value"));
                  goto _L30
_L4:
                hashmap.put("PeisongName", " ");
                bundle.putString("send_type", " ");
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                hashmap.put("mInforms", " ");
                bundle.putString("phone_confirm", " ");
                  goto _L31
_L2:
                hashmap.put("PeisongName", " ");
                bundle.putString("send_type", " ");
                hashmap.put("mCodTime", " ");
                bundle.putString("send_time", " ");
                hashmap.put("mInforms", " ");
                bundle.putString("phone_confirm", " ");
                if(FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(0).toString().contains("\u5546\u6237\u5BA2\u6237\u53F7"))
                {
                    hashmap.put("postid", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(0).getString("value"));
                    hashmap.put("postreceiver", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(1).getString("value"));
                } else
                {
                    hashmap.put("postreceiver", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(0).getString("value"));
                    hashmap.put("postid", FillOrderActivity.mNewPaymentInfo.getPostArray().getJSONObject(1).getString("value"));
                }
                value.add(hashmap);
                  goto _L31
_L23:
                if(Contants.bModifyEasyBuy)
                {
                    paymentInfoList.setEnabled(true);
                    paymentInfoList.setClickable(true);
                }
                  goto _L32
_L24:
                return;
                k++;
                  goto _L33
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    private void setReceiverInfoArea()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                myAdapter = null;
                if(Log.D)
                    Log.d("test", "ui2");
                LayoutInflater layoutinflater = LayoutInflater.from(FillOrderActivity.this);
                RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c009d);
                RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030060, null).findViewById(0x7f0c01ee);
                receiverInfoList = (ListView)relativelayout1.getChildAt(1);
                value = new ArrayList();
                HashMap hashmap = new HashMap();
                hashmap.put("Name", LastOrderInfo.mUserInfo.getUserName());
                hashmap.put("Mobile", LastOrderInfo.mUserInfo.getUserMobile());
                FillOrderActivity fillorderactivity;
                FillOrderActivity fillorderactivity1;
                List list;
                String as[];
                int ai[];
                try
                {
                    hashmap.put("addr", LastOrderInfo.mUserInfo.getUserAddr().getString("Where"));
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
                value.add(hashmap);
                fillorderactivity = FillOrderActivity.this;
                fillorderactivity1 = FillOrderActivity.this;
                list = value;
                as = new String[3];
                as[0] = "Name";
                as[1] = "Mobile";
                as[2] = "addr";
                ai = new int[3];
                ai[0] = 0x7f0c00d7;
                ai[1] = 0x7f0c00d9;
                ai[2] = 0x7f0c01f2;
                fillorderactivity.myAdapter = new SimpleAdapter(fillorderactivity1, list, 0x7f030063, as, ai);
                receiverInfoList.setAdapter(myAdapter);
                relativelayout1.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                if(!Contants.bEasyBuy) goto _L2; else goto _L1
_L1:
                receiverInfoList.setEnabled(false);
                receiverInfoList.setClickable(false);
_L4:
                receiverInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/ModifyOrderAddr);
                        startActivityForResult(intent, 1);
                    }

                    final _cls13 this$1;

                    
                    {
                        this$1 = _cls13.this;
                        super();
                    }
                }
);
                return;
_L2:
                if(Contants.bModifyEasyBuy)
                {
                    receiverInfoList.setEnabled(true);
                    receiverInfoList.setClickable(true);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
    }

    /**
     * @deprecated Method setUpConnAndGetData is deprecated
     */

    private void setUpConnAndGetData(String s, JSONObject jsonobject, final String type)
    {
        this;
        JVM INSTR monitorenter ;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(s);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                getOrderInfoFromSer(httpresponse, type);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(getType(type) == 14)
                    ShowMsg(getResources().getString(0x7f090159));
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d(TAG, "setUpConnAndGetData()-start");
            }

            final FillOrderActivity this$0;
            private final String val$type;

            
            {
                this$0 = FillOrderActivity.this;
                type = s;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void setYouhuiArea()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c00e7);
        RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030029, null).findViewById(0x7f0c00d1);
        youhuiInfoList = (ListView)relativelayout1.getChildAt(0);
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            youhuiInfoList.setVisibility(8);
        } else
        {
            ArrayList arraylist = new ArrayList();
            HashMap hashmap = new HashMap();
            hashmap.put("name", " ");
            arraylist.add(hashmap);
            youhuiInfoList.setAdapter(new YouHuiAdapter(this, arraylist));
            youhuiInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
            youhuiInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view, int i, long l)
                {
                    Intent intent = new Intent(FillOrderActivity.this, com/jindong/app/mall/shopping/EditYouHuiLipinActivity);
                    startActivityForResult(intent, 5);
                }

                final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
            }
);
        }
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
    }

    private void submitEasyBuyOrder()
    {
        submitEasyBuyOrder(null, null);
    }

    private void submitEasyBuyOrder(String s, String s1)
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            resetJBBody();
            jbBody.put("totalPrice", String.valueOf(Contants.dTotalPrice));
            jbBody.put("templateName", DefaultEasyTempOrderInfo.sTempName);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setPost(true);
        httpsetting.setFunctionId("easyBuySubmitOrder");
        httpsetting.setJsonParams(jbBody);
        if(!TextUtils.isEmpty(s))
            httpsetting.putJsonParam("resultCode", s);
        if(!TextUtils.isEmpty(s1))
            httpsetting.putJsonParam("key", s1);
        if(InterfaceBroadcastReceiver.usid == null) goto _L2; else goto _L1
_L1:
        httpsetting.putMapParams("usid", InterfaceBroadcastReceiver.usid);
_L6:
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                String s2 = null;
                String s3 = null;
                JSONObjectProxy jsonobjectproxy = null;
                JSONObjectProxy jsonobjectproxy1 = httpresponse.getJSONObject();
                if(jsonobjectproxy1 != null)
                    jsonobjectproxy = jsonobjectproxy1.getJSONObjectOrNull("submitOrder");
                if(jsonobjectproxy != null)
                {
                    s2 = jsonobjectproxy.getStringOrNull("url");
                    s3 = jsonobjectproxy.getStringOrNull("Message");
                }
                if(!TextUtils.isEmpty(s2))
                    new CaptchaDialogController(s2, s3) {

                        protected void submit(String s4, String s5)
                        {
                            submitEasyBuyOrder(s4, s5);
                        }

                        final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super(s, s1);
                    }
                    }
;
                else
                    getOrderInfoFromSer(httpresponse, "easyBuySubmitOrder");
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
_L4:
        return;
_L2:
        InterfaceBroadcastReceiver.unionId = Configuration.getProperty("unionId");
        InterfaceBroadcastReceiver.subunionId = Configuration.getProperty("subunionId");
        if(InterfaceBroadcastReceiver.unionId == null)
            continue; /* Loop/switch isn't completed */
        InterfaceBroadcastReceiver.cps(new Runnable() {

            public void run()
            {
                submitOrder();
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void submitOrder()
    {
        submitOrder(null, null);
    }

    private void submitOrder(String s, String s1)
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            resetJBBody();
            jbBody.put("totalPrice", String.valueOf(Contants.dTotalPrice));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setPost(true);
        httpsetting.setFunctionId("submitOrder");
        httpsetting.setJsonParams(jbBody);
        if(!TextUtils.isEmpty(s))
            httpsetting.putJsonParam("resultCode", s);
        if(!TextUtils.isEmpty(s1))
            httpsetting.putJsonParam("key", s1);
        if(InterfaceBroadcastReceiver.usid == null) goto _L2; else goto _L1
_L1:
        httpsetting.putMapParams("usid", InterfaceBroadcastReceiver.usid);
_L6:
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                String s2 = null;
                String s3 = null;
                JSONObjectProxy jsonobjectproxy = null;
                JSONObjectProxy jsonobjectproxy1 = httpresponse.getJSONObject();
                if(jsonobjectproxy1 != null)
                    jsonobjectproxy = jsonobjectproxy1.getJSONObjectOrNull("submitOrder");
                if(jsonobjectproxy != null)
                {
                    s2 = jsonobjectproxy.getStringOrNull("url");
                    s3 = jsonobjectproxy.getStringOrNull("Message");
                }
                if(!TextUtils.isEmpty(s2))
                    new CaptchaDialogController(s2, s3) {

                        protected void submit(String s4, String s5)
                        {
                            submitOrder(s4, s5);
                        }

                        final _cls28 this$1;

                    
                    {
                        this$1 = _cls28.this;
                        super(s, s1);
                    }
                    }
;
                else
                    getOrderInfoFromSer(httpresponse, "submitOrder");
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final FillOrderActivity this$0;


            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
_L4:
        return;
_L2:
        InterfaceBroadcastReceiver.unionId = Configuration.getProperty("unionId");
        InterfaceBroadcastReceiver.subunionId = Configuration.getProperty("subunionId");
        if(InterfaceBroadcastReceiver.unionId == null)
            continue; /* Loop/switch isn't completed */
        InterfaceBroadcastReceiver.cps(new Runnable() {

            public void run()
            {
                submitOrder();
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
);
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void updateCompositeOrderStr()
    {
        try
        {
            if(LoginUser.hasLogin())
                if(jbOrderStr == null)
                {
                    jbOrderStr = new JSONObject();
                    updateOrderStrByUserInfo();
                    if(isLastOrderInfoContainField("IdInvoiceType", jsonOrderInfoContainer))
                        jbOrderStr.put("IdInvoiceType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceType"));
                    if(isLastOrderInfoContainField("IdInvoiceHeaderType", jsonOrderInfoContainer))
                        jbOrderStr.put("IdInvoiceHeaderType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceHeaderType"));
                    if(isLastOrderInfoContainField("IdInvoiceContentTypeBook", jsonOrderInfoContainer))
                        jbOrderStr.put("IdInvoiceContentTypeBook", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentTypeBook"));
                    if(isLastOrderInfoContainField("IdCompanyBranch", jsonOrderInfoContainer))
                        jbOrderStr.put("IdCompanyBranch", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdCompanyBranch"));
                    if(isLastOrderInfoContainField("IdInvoiceContentsType", jsonOrderInfoContainer))
                        jbOrderStr.put("IdInvoiceContentsType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentsType"));
                    if(isLastOrderInfoContainField("IdPaymentType", jsonOrderInfoContainer))
                        jbOrderStr.put("IdPaymentType", LastOrderInfo.mPaymentInfo.type);
                    if(isLastOrderInfoContainField("PaymentWay", jsonOrderInfoContainer))
                        jbOrderStr.put("PaymentWay", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay"));
                    if(isLastOrderInfoContainField("PromotionPrice", jsonOrderInfoContainer))
                        jbOrderStr.put("PromotionPrice", LastOrderInfo.dPromotionPrice);
                    if(isLastOrderInfoContainField("Price", jsonOrderInfoContainer))
                        jbOrderStr.put("Price", LastOrderInfo.dPrice);
                } else
                {
                    updateOrderStrByUserInfo();
                }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private void updateCompositeOrderStrByInvoice()
    {
        JSONObject jsonobject = Contants.mModifiedInvoiceInfo.getInvoiceInfo();
        if(jsonobject.toString().contains("IdInvoiceContentsType") && jsonobject.get("IdInvoiceContentsType") != null && jsonobject.get("IdInvoiceContentsType") != "" && jsonobject.get("IdInvoiceContentsType") != " ")
            jbOrderStr.put("IdInvoiceContentsType", jsonobject.get("IdInvoiceContentsType"));
        if(jsonobject.toString().contains("InvoiceTitle") && jsonobject.get("InvoiceTitle") != null && jsonobject.get("InvoiceTitle") != "" && jsonobject.get("InvoiceTitle") != " ")
            jbOrderStr.put("InvoiceTitle", jsonobject.get("InvoiceTitle"));
        if(jsonobject.toString().contains("IdInvoiceHeaderType") && jsonobject.get("IdInvoiceHeaderType") != null && jsonobject.get("IdInvoiceHeaderType") != "" && jsonobject.get("IdInvoiceHeaderType") != " ")
            jbOrderStr.put("IdInvoiceHeaderType", jsonobject.get("IdInvoiceHeaderType"));
        if(jsonobject.toString().contains("CompanyName") && jsonobject.get("CompanyName") != null && jsonobject.get("CompanyName") != "" && jsonobject.get("CompanyName") != " ")
            jbOrderStr.put("CompanyName", jsonobject.get("CompanyName"));
        if(jsonobject.toString().contains("IdInvoiceType") && jsonobject.get("IdInvoiceType") != null && jsonobject.get("IdInvoiceType") != "" && jsonobject.get("IdInvoiceType") != " ")
            jbOrderStr.put("IdInvoiceType", jsonobject.get("IdInvoiceType"));
        if(jsonobject.toString().contains("IdInvoiceContentTypeBook") && jsonobject.get("IdInvoiceContentTypeBook") != null && jsonobject.get("IdInvoiceContentTypeBook") != "" && jsonobject.get("IdInvoiceContentTypeBook") != " ")
            jbOrderStr.put("IdInvoiceContentTypeBook", jsonobject.get("IdInvoiceContentTypeBook"));
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updateCompositeOrderStrByModifyPayment()
    {
        try
        {
            JSONObject jsonobject = Contants.mModifiedPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected);
            if(LastOrderInfo.mPaymentInfo.nSelected == 3)
            {
                if(jsonobject.get("IdPaymentType").toString() != "null" && jsonobject.get("IdPaymentType") != null && jsonobject.get("IdPaymentType") != "" && jsonobject.get("IdPaymentType") != " ")
                    jbOrderStr.put("IdPaymentType", jsonobject.get("IdPaymentType"));
                if(jsonobject.get("IdPickSite").toString() != "null" && jsonobject.get("IdPickSite") != null && jsonobject.get("IdPickSite") != "" && jsonobject.get("IdPickSite") != " ")
                    jbOrderStr.put("IdPickSite", jsonobject.get("IdPickSite"));
            } else
            {
                if(jsonobject.toString().contains("IdPaymentType") && jsonobject.get("IdPaymentType").toString() != "null" && jsonobject.get("IdPaymentType") != null && jsonobject.get("IdPaymentType") != "" && jsonobject.get("IdPaymentType") != " ")
                    jbOrderStr.put("IdPaymentType", jsonobject.get("IdPaymentType"));
                if(jsonobject.toString().contains("IdShipmentType") && jsonobject.get("IdShipmentType").toString() != "null" && jsonobject.get("IdShipmentType") != null && jsonobject.get("IdShipmentType") != "" && jsonobject.get("IdShipmentType") != " ")
                    jbOrderStr.put("IdShipmentType", jsonobject.get("IdShipmentType"));
                if(jsonobject.toString().contains("CODTime") && jsonobject.get("CODTime") != null && jsonobject.get("CODTime") != "" && jsonobject.get("CODTime") != " ")
                    jbOrderStr.put("CODTime", jsonobject.get("CODTime"));
                if(jsonobject.toString().contains("IsCodInform") && jsonobject.get("IsCodInform") != null && jsonobject.get("IsCodInform") != "" && jsonobject.get("IsCodInform") != " ")
                    jbOrderStr.put("IsCodInform", jsonobject.get("IsCodInform"));
                if(jsonobject.toString().contains("PaymentWay") && jsonobject.get("PaymentWay") != null && jsonobject.get("PaymentWay") != "" && jsonobject.get("PaymentWay") != " ")
                    jbOrderStr.put("PaymentWay", jsonobject.get("PaymentWay"));
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private void updateInvoinceInfo()
    {
        if(mNewInvoiceInfo == null)
            mNewInvoiceInfo = new InvoiceInfo();
        if(mNewInvoiceInfo.getInvoiceInfo() == null)
            mNewInvoiceInfo.setInvoiceInfo(new JSONObject());
        JSONObject jsonobject = Contants.mModifiedInvoiceInfo.getInvoiceInfo();
        try
        {
            if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo() == null)
            {
                LastOrderInfo.mInvoiceInfo.setInvoiceInfo(jsonobject);
                mNewInvoiceInfo.setInvoiceInfo(jsonobject);
            } else
            {
                if(jsonobject.toString().contains("IdInvoiceContentsType") && jsonobject.get("IdInvoiceContentsType") != null && jsonobject.get("IdInvoiceContentsType") != "" && jsonobject.get("IdInvoiceContentsType") != " ")
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceContentsType", jsonobject.get("IdInvoiceContentsType"));
                    mNewInvoiceInfo.getInvoiceInfo().put("IdInvoiceContentsType", jsonobject.get("IdInvoiceContentsType"));
                }
                if(jsonobject.toString().contains("InvoiceTitle") && jsonobject.get("InvoiceTitle") != null && jsonobject.get("InvoiceTitle") != "" && jsonobject.get("InvoiceTitle") != " ")
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("InvoiceTitle", jsonobject.get("InvoiceTitle"));
                    mNewInvoiceInfo.getInvoiceInfo().put("InvoiceTitle", jsonobject.get("InvoiceTitle"));
                }
                if(jsonobject.toString().contains("IdInvoiceHeaderType") && jsonobject.get("IdInvoiceHeaderType") != null && jsonobject.get("IdInvoiceHeaderType") != "" && jsonobject.get("IdInvoiceHeaderType") != " ")
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceHeaderType", jsonobject.get("IdInvoiceHeaderType"));
                    mNewInvoiceInfo.getInvoiceInfo().put("IdInvoiceHeaderType", jsonobject.get("IdInvoiceHeaderType"));
                }
                if(jsonobject.toString().contains("CompanyName") && jsonobject.get("CompanyName") != null && jsonobject.get("CompanyName") != "" && jsonobject.get("CompanyName") != " ")
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("CompanyName", jsonobject.get("CompanyName"));
                    mNewInvoiceInfo.getInvoiceInfo().put("CompanyName", jsonobject.get("CompanyName"));
                }
                if(jsonobject.toString().contains("IdInvoiceType") && jsonobject.get("IdInvoiceType") != null && jsonobject.get("IdInvoiceType") != "" && jsonobject.get("IdInvoiceType") != " ")
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceType", jsonobject.get("IdInvoiceType"));
                    mNewInvoiceInfo.getInvoiceInfo().put("IdInvoiceType", jsonobject.get("IdInvoiceType"));
                }
                if(jsonobject.toString().contains("IdInvoiceContentTypeBook") && jsonobject.get("IdInvoiceContentTypeBook") != null && jsonobject.get("IdInvoiceContentTypeBook") != "" && jsonobject.get("IdInvoiceContentTypeBook") != " ")
                {
                    LastOrderInfo.mInvoiceInfo.getInvoiceInfo().put("IdInvoiceContentTypeBook", jsonobject.get("IdInvoiceContentTypeBook"));
                    mNewInvoiceInfo.getInvoiceInfo().put("IdInvoiceContentTypeBook", jsonobject.get("IdInvoiceContentTypeBook"));
                }
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private void updateMoneyInfoByTransFee(String s)
    {
        mTransportFee.setText(s);
        double d = (Contants.mCartInfoHuiZong.getTotalPrice() + Double.parseDouble(mTransportFee.getText().toString())) - Double.parseDouble(mYouhuiQuan.getText().toString()) - Double.parseDouble(mLipin.getText().toString()) - Double.parseDouble(mFanXian.getText().toString());
        mLastMoney.setText(String.valueOf(d));
    }

    private void updateOrderStrByUserInfo()
    {
        jbOrderStr.put("pin", LoginUser.getLoginUserName());
        if(LastOrderInfo.mUserInfo.getUserName() != null && LastOrderInfo.mUserInfo.getUserName() != "" && LastOrderInfo.mUserInfo.getUserName() != " ")
            jbOrderStr.put("Name", LastOrderInfo.mUserInfo.getUserName());
        if(LastOrderInfo.mUserInfo.getUserMobile() != null && LastOrderInfo.mUserInfo.getUserMobile() != "" && LastOrderInfo.mUserInfo.getUserMobile() != " ")
            jbOrderStr.put("Mobile", LastOrderInfo.mUserInfo.getUserMobile());
        if(isLastOrderInfoContainField("Phone", jsonOrderInfoContainer))
            jbOrderStr.put("Phone", LastOrderInfo.mUserInfo.getUserPhone());
        if(LastOrderInfo.mUserInfo.getUserZip() != null && LastOrderInfo.mUserInfo.getUserZip() != "" && LastOrderInfo.mUserInfo.getUserZip() != " ")
            jbOrderStr.put("Zip", LastOrderInfo.mUserInfo.getUserZip());
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdProvince"))
            jbOrderStr.put("IdProvince", LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdCity"))
            jbOrderStr.put("IdCity", LastOrderInfo.mUserInfo.getUserAddr().get("IdCity"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdArea"))
            jbOrderStr.put("IdArea", LastOrderInfo.mUserInfo.getUserAddr().get("IdArea"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Where"))
            jbOrderStr.put("Where", LastOrderInfo.mUserInfo.getUserAddr().get("Where"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Email"))
            jbOrderStr.put("Email", LastOrderInfo.mUserInfo.getUserAddr().get("Email"));
        if(isLastOrderInfoContainField("UserLevel", jsonOrderInfoContainer))
            jbOrderStr.put("UserLevel", LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel"));
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updateOrderStrByUserInfoByAddEasyTemp()
    {
        jbOrderStr.put("pin", LoginUser.getLoginUserName());
        if(LastOrderInfo.mUserInfo.getUserName() != null && LastOrderInfo.mUserInfo.getUserName() != "" && LastOrderInfo.mUserInfo.getUserName() != " ")
            jbOrderStr.put("Name", LastOrderInfo.mUserInfo.getUserName());
        if(LastOrderInfo.mUserInfo.getUserMobile() != null && LastOrderInfo.mUserInfo.getUserMobile() != "" && LastOrderInfo.mUserInfo.getUserMobile() != " ")
            jbOrderStr.put("Mobile", LastOrderInfo.mUserInfo.getUserMobile());
        if(LastOrderInfo.mUserInfo.getUserZip() != null && LastOrderInfo.mUserInfo.getUserZip() != "" && LastOrderInfo.mUserInfo.getUserZip() != " ")
            jbOrderStr.put("Zip", LastOrderInfo.mUserInfo.getUserZip());
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdProvince"))
            jbOrderStr.put("IdProvince", LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdCity"))
            jbOrderStr.put("IdCity", LastOrderInfo.mUserInfo.getUserAddr().get("IdCity"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdArea"))
            jbOrderStr.put("IdArea", LastOrderInfo.mUserInfo.getUserAddr().get("IdArea"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Where"))
            jbOrderStr.put("Where", LastOrderInfo.mUserInfo.getUserAddr().get("Where"));
        if(LastOrderInfo.mUserInfo.getUserAddr() != null && LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Email"))
            jbOrderStr.put("Email", LastOrderInfo.mUserInfo.getUserAddr().get("Email"));
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updatePayWayInfo(JSONObjectProxy jsonobjectproxy)
    {
        new JSONObject();
        JSONObjectProxy jsonobjectproxy1 = jsonobjectproxy.getJSONArray("ShipmentTypes").getJSONObject(0);
        if(Log.D)
            Log.d("ship", jsonobjectproxy1.toString());
        new JSONArray();
        new JSONArray();
        new JSONArray();
        JSONArray jsonarray = jsonobjectproxy1.getJSONArray("SupPaymentWay");
        JSONArray jsonarray1 = jsonobjectproxy1.getJSONArray("SupCodTime");
        jsonobjectproxy1.getJSONArray("SupInforms");
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("IdPaymentType", LastOrderInfo.mPaymentInfo.nSelected);
        jsonobject.put("IdShipmentType", jsonobjectproxy1.get("Id"));
        if(jsonarray1 != null)
            jsonobject.put("CODTime", jsonarray1.getJSONObject(0).getInt("Id"));
        if(jsonarray != null)
            jsonobject.put("PaymentWay", jsonarray.getJSONObject(0).getInt("Id"));
        LastOrderInfo.mPaymentInfo.nSelected = jsonobject.getInt("IdPaymentType");
        LastOrderInfo.mPaymentInfo.setPayMentType(jsonobject.getInt("IdPaymentType"), jsonobject);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updatePayWayInfoPost()
    {
        new JSONObject();
        try
        {
            JSONObject jsonobject = mNewPaymentInfo.getPayMentType(2).getJSONArray("ShipmentTypes").getJSONObject(0);
            if(Log.D)
                Log.d("ship", jsonobject.toString());
            new JSONArray();
            new JSONArray();
            JSONArray jsonarray = jsonobject.getJSONArray("SupCodTime");
            jsonobject.getJSONArray("SupInforms");
            JSONObject jsonobject1 = new JSONObject();
            if(mNewPaymentInfo.getPostArray().getJSONObject(0).toString().contains("\u5546\u6237\u5BA2\u6237\u53F7"))
            {
                jsonobject1.put("postid", mNewPaymentInfo.getPostArray().getJSONObject(0).getString("value"));
                jsonobject1.put("postreceiver", mNewPaymentInfo.getPostArray().getJSONObject(1).getString("value"));
            } else
            {
                jsonobject1.put("postid", mNewPaymentInfo.getPostArray().getJSONObject(1).getString("value"));
                jsonobject1.put("postreceiver", mNewPaymentInfo.getPostArray().getJSONObject(0).getString("value"));
            }
            jsonobject1.put("IdPaymentType", LastOrderInfo.mPaymentInfo.nSelected);
            jsonobject1.put("IdShipmentType", jsonobject.get("Id"));
            jsonobject1.put("CODTime", jsonarray.getJSONObject(0).getInt("Id"));
            LastOrderInfo.mPaymentInfo.nSelected = jsonobject1.getInt("IdPaymentType");
            LastOrderInfo.mPaymentInfo.setPayMentType(jsonobject1.getInt("IdPaymentType"), jsonobject1);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private void updatePayWayInfoSelf()
    {
        new JSONArray();
        JSONArray jsonarray = mNewPaymentInfo.getPaymentDetailInfos();
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("IdPaymentType", LastOrderInfo.mPaymentInfo.nSelected);
        jsonobject.put("IdPickSite", jsonarray.getJSONObject(0).getInt("Id"));
        LastOrderInfo.mPaymentInfo.nSelected = jsonobject.getInt("IdPaymentType");
        LastOrderInfo.mPaymentInfo.setPayMentType(jsonobject.getInt("IdPaymentType"), jsonobject);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updatePaymentInfo()
    {
        JSONObject jsonobject;
        if(mNewPaymentInfo == null)
            mNewPaymentInfo = new PaymentInfo();
        jsonobject = Contants.mModifiedPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected);
        try
        {
            if(LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected) == null || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).toString() == "{}" || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).toString() == "{ }" || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).toString() == "" || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).length() < 1 || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).toString() == " ")
            {
                LastOrderInfo.mPaymentInfo.setPayMentType(LastOrderInfo.mPaymentInfo.nSelected, jsonobject);
                mNewPaymentInfo.setPayMentType(LastOrderInfo.mPaymentInfo.nSelected, jsonobject);
                break MISSING_BLOCK_LABEL_1041;
            }
            if(LastOrderInfo.mPaymentInfo.nSelected == 3)
            {
                if(jsonobject.get("IdPaymentType") != null && jsonobject.get("IdPaymentType") != "" && jsonobject.get("IdPaymentType") != " ")
                {
                    LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdPaymentType", jsonobject.get("IdPaymentType"));
                    mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdPaymentType", jsonobject.get("IdPaymentType"));
                }
                if(jsonobject.get("IdPickSite") != null && jsonobject.get("IdPickSite") != "" && jsonobject.get("IdPickSite") != " ")
                {
                    LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdPickSite", jsonobject.get("IdPickSite"));
                    mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdPickSite", jsonobject.get("IdPickSite"));
                }
                break MISSING_BLOCK_LABEL_1041;
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            break MISSING_BLOCK_LABEL_1041;
        }
        if(jsonobject.get("IdPaymentType") != null && jsonobject.get("IdPaymentType") != "" && jsonobject.get("IdPaymentType") != " ")
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdPaymentType", jsonobject.get("IdPaymentType"));
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdPaymentType", jsonobject.get("IdPaymentType"));
        }
        if(jsonobject.get("IdShipmentType") != null && jsonobject.get("IdShipmentType") != "" && jsonobject.get("IdShipmentType") != " ")
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdShipmentType", jsonobject.get("IdShipmentType"));
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IdShipmentType", jsonobject.get("IdShipmentType"));
        }
        if(jsonobject.toString().contains("CODTime") && jsonobject.get("CODTime") != null && jsonobject.get("CODTime").toString() != "null" && jsonobject.get("CODTime").toString() != "" && jsonobject.get("CODTime").toString() != " ")
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("CODTime", jsonobject.get("CODTime"));
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("CODTime", jsonobject.get("CODTime"));
        } else
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).remove("CODTime");
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).remove("CODTime");
        }
        if(jsonobject.toString().contains("PaymentWay") && jsonobject.get("PaymentWay").toString() != "null" && jsonobject.get("PaymentWay") != null && jsonobject.get("PaymentWay").toString() != "" && jsonobject.get("PaymentWay").toString() != " ")
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("PaymentWay", jsonobject.get("PaymentWay"));
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("PaymentWay", jsonobject.get("PaymentWay"));
        } else
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).remove("PaymentWay");
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).remove("PaymentWay");
        }
        if(jsonobject.toString().contains("IsCodInform") && jsonobject.get("IsCodInform").toString() != "null" && jsonobject.get("IsCodInform") != null && jsonobject.get("IsCodInform").toString() != "" && jsonobject.get("IsCodInform").toString() != " ")
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IsCodInform", jsonobject.get("IsCodInform"));
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).put("IsCodInform", jsonobject.get("IsCodInform"));
        } else
        {
            LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).remove("IsCodInform");
            mNewPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).remove("IsCodInform");
        }
    }

    private void updatePaymentTypes()
    {
        updatCcomositeBody();
        setUpConnAndGetData("paymentType", jbBody, "paymentInfo");
    }

    private void updatePaymentTypesByAddEasyBuy()
    {
        try
        {
            jbBody = null;
            jbBody = new JSONObject();
            jbBody.put("OrderStr", jbOrderStr);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        setUpConnAndGetData("easyBuyPaymentType", jbBody, "paymentInfo");
    }

    private void updatePaymentTypesByInvoice()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            s = "easyBuyPaymentType";
            updateCompositeOrderStrByInvoice();
            jbBody = new JSONObject();
            try
            {
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "paymentType";
            updatCcomositeBodyByInvoice();
        }
        setUpConnAndGetData(s, jbBody, "paymentInfo");
    }

    private void updatePaymentTypesByModifyPayment()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            s = "easyBuyPaymentType";
            updateCompositeOrderStrByModifyPayment();
            jbBody = new JSONObject();
            try
            {
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "paymentType";
            updatCcomositeBodyByModifyPayment();
        }
        setUpConnAndGetData(s, jbBody, "paymentInfo");
    }

    private void updateUserInfo()
    {
        JSONObject jsonobject;
        if(mNewUserInfo == null)
            mNewUserInfo = new UserInfo();
        if(Contants.mModifiedUserInfo.getUserName() != null && Contants.mModifiedUserInfo.getUserName() != "" && Contants.mModifiedUserInfo.getUserName() != " ")
        {
            LastOrderInfo.mUserInfo.setUserName(Contants.mModifiedUserInfo.getUserName());
            mNewUserInfo.setUserName(Contants.mModifiedUserInfo.getUserName());
        }
        if(Contants.mModifiedUserInfo.getUserMobile() != null && Contants.mModifiedUserInfo.getUserMobile() != "" && Contants.mModifiedUserInfo.getUserMobile() != " ")
        {
            LastOrderInfo.mUserInfo.setUserMobile(Contants.mModifiedUserInfo.getUserMobile());
            mNewUserInfo.setUserMobile(Contants.mModifiedUserInfo.getUserMobile());
        }
        if(Contants.mModifiedUserInfo.getUserZip() != null && Contants.mModifiedUserInfo.getUserZip() != "" && Contants.mModifiedUserInfo.getUserZip() != " ")
        {
            LastOrderInfo.mUserInfo.setUserZip(Contants.mModifiedUserInfo.getUserZip());
            mNewUserInfo.setUserZip(Contants.mModifiedUserInfo.getUserZip());
        }
        new JSONObject();
        jsonobject = Contants.mModifiedUserInfo.getUserAddr();
        if(jsonobject.toString().contains("IdProvince") && jsonobject.get("IdProvince") != null && jsonobject.get("IdProvince") != "" && jsonobject.get("IdProvince") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("IdProvince", jsonobject.get("IdProvince"));
            mNewUserInfo.getUserAddr().put("IdProvince", jsonobject.get("IdProvince"));
        }
        if(jsonobject.toString().contains("IdCity") && jsonobject.get("IdCity") != null && jsonobject.get("IdCity") != "" && jsonobject.get("IdCity") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("IdCity", jsonobject.get("IdCity"));
            mNewUserInfo.getUserAddr().put("IdCity", jsonobject.get("IdCity"));
        }
        if(jsonobject.toString().contains("IdArea") && jsonobject.get("IdArea") != null && jsonobject.get("IdArea") != "" && jsonobject.get("IdArea") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("IdArea", jsonobject.get("IdArea"));
            mNewUserInfo.getUserAddr().put("IdArea", jsonobject.get("IdArea"));
        }
        if(jsonobject.toString().contains("Where") && jsonobject.get("Where") != null && jsonobject.get("Where") != "" && jsonobject.get("Where") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("Where", jsonobject.get("Where"));
            mNewUserInfo.getUserAddr().put("Where", jsonobject.get("Where"));
        }
        if(jsonobject.toString().contains("Email") && jsonobject.get("Email") != null && jsonobject.get("Email") != "" && jsonobject.get("Email") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("Email", jsonobject.get("Email"));
            mNewUserInfo.getUserAddr().put("Email", jsonobject.get("Email"));
        }
        jbProvinces = Contants.jbProvinces;
        jbCitys = Contants.jbCitys;
        jbAreas = Contants.jbAreas;
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updateUserInfoByAddEasyBuy()
    {
        JSONObject jsonobject;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            mNewUserInfo = new UserInfo();
            LastOrderInfo.mUserInfo = new UserInfo();
        }
        if(Contants.mModifiedUserInfo.getUserName() != null && Contants.mModifiedUserInfo.getUserName() != "" && Contants.mModifiedUserInfo.getUserName() != " ")
        {
            LastOrderInfo.mUserInfo.setUserName(Contants.mModifiedUserInfo.getUserName());
            mNewUserInfo.setUserName(Contants.mModifiedUserInfo.getUserName());
        }
        if(Contants.mModifiedUserInfo.getUserMobile() != null && Contants.mModifiedUserInfo.getUserMobile() != "" && Contants.mModifiedUserInfo.getUserMobile() != " ")
        {
            LastOrderInfo.mUserInfo.setUserMobile(Contants.mModifiedUserInfo.getUserMobile());
            mNewUserInfo.setUserMobile(Contants.mModifiedUserInfo.getUserMobile());
        }
        if(Contants.mModifiedUserInfo.getUserZip() != null && Contants.mModifiedUserInfo.getUserZip() != "" && Contants.mModifiedUserInfo.getUserZip() != " ")
        {
            LastOrderInfo.mUserInfo.setUserZip(Contants.mModifiedUserInfo.getUserZip());
            mNewUserInfo.setUserZip(Contants.mModifiedUserInfo.getUserZip());
        }
        new JSONObject();
        jsonobject = Contants.mModifiedUserInfo.getUserAddr();
        if(jsonobject.toString().contains("IdProvince") && jsonobject.get("IdProvince") != null && jsonobject.get("IdProvince") != "" && jsonobject.get("IdProvince") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("IdProvince", jsonobject.get("IdProvince"));
            mNewUserInfo.getUserAddr().put("IdProvince", jsonobject.get("IdProvince"));
        }
        if(jsonobject.toString().contains("IdCity") && jsonobject.get("IdCity") != null && jsonobject.get("IdCity") != "" && jsonobject.get("IdCity") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("IdCity", jsonobject.get("IdCity"));
            mNewUserInfo.getUserAddr().put("IdCity", jsonobject.get("IdCity"));
        }
        if(jsonobject.toString().contains("IdArea") && jsonobject.get("IdArea") != null && jsonobject.get("IdArea") != "" && jsonobject.get("IdArea") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("IdArea", jsonobject.get("IdArea"));
            mNewUserInfo.getUserAddr().put("IdArea", jsonobject.get("IdArea"));
        }
        if(jsonobject.toString().contains("Where") && jsonobject.get("Where") != null && jsonobject.get("Where") != "" && jsonobject.get("Where") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("Where", jsonobject.get("Where"));
            mNewUserInfo.getUserAddr().put("Where", jsonobject.get("Where"));
        }
        if(jsonobject.toString().contains("Email") && jsonobject.get("Email") != null && jsonobject.get("Email") != "" && jsonobject.get("Email") != "  ")
        {
            LastOrderInfo.mUserInfo.getUserAddr().put("Email", jsonobject.get("Email"));
            mNewUserInfo.getUserAddr().put("Email", jsonobject.get("Email"));
        }
        jbProvinces = Contants.jbProvinces;
        jbCitys = Contants.jbCitys;
        jbAreas = Contants.jbAreas;
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void updateYouHuiXin()
    {
        RelativeLayout relativelayout;
        RelativeLayout relativelayout1;
        ArrayList arraylist;
        HashMap hashmap;
        String s;
        boolean flag;
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        relativelayout = (RelativeLayout)findViewById(0x7f0c00e7);
        relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03008c, null).findViewById(0x7f0c02d9);
        youhuiInfoList = null;
        youhuiInfoList = (ListView)relativelayout1.getChildAt(1);
        ((TextView)relativelayout1.getChildAt(0)).setVisibility(0);
        relativelayout1.requestLayout();
        arraylist = new ArrayList();
        hashmap = new HashMap();
        s = " ";
        flag = false;
        if(!Contants.jdSwitch) goto _L2; else goto _L1
_L1:
        if(Contants.dongSel == null || Contants.dongSel.toString() == "null" || Contants.dongSel.toString() == "" || Contants.dongSel.toString() == " ") goto _L4; else goto _L3
_L3:
        String s1 = (new StringBuilder(String.valueOf(s))).append("\u4F7F\u75281\u5F20\u4E1C\u5238, \u9762\u989D").append(Contants.dongSel.get("Discount").toString()).append("\u5143").toString();
        s = s1;
_L7:
        int i;
        double d;
        int j;
        if(Contants.liSelArray == null || Contants.liSelArray.toString() == "null" || Contants.liSelArray.length() <= 0)
            break MISSING_BLOCK_LABEL_839;
        i = 0;
        d = 0.0D;
        j = 0;
_L10:
        int k = Contants.liSelArray.length();
        if(j < k) goto _L6; else goto _L5
_L5:
        ListView listview;
        YouHuiAdapter youhuiadapter;
        ListView listview1;
        android.widget.AdapterView.OnItemClickListener onitemclicklistener;
        JSONException jsonexception;
        double d1;
        int l;
        double d2;
        int i1;
        JSONException jsonexception1;
        double d3;
        JSONException jsonexception2;
        if(s == null || s == "" || s == " ")
            s = (new StringBuilder(String.valueOf(s))).append("\u4F7F\u7528").append(String.valueOf(i)).append("\u5F20").append("\u793C\u54C1\u5361").append(",").append(" \u9762\u989D").append(String.valueOf(d)).append("\u5143").toString();
        else
            s = (new StringBuilder(String.valueOf(s))).append("\n \u4F7F\u7528").append(String.valueOf(i)).append("\u5F20").append("\u793C\u54C1\u5361").append(",").append(" \u9762\u989D").append(String.valueOf(d)).append("\u5143").toString();
_L12:
        if(s == "" || s == " ")
            s = "\u60A8\u6CA1\u6709\u9009\u62E9\u4F18\u60E0\u5238\u6216\u793C\u54C1\u5361";
        hashmap.put("name", s);
        arraylist.add(hashmap);
        Contants.item = new ArrayList();
        Contants.item.add(hashmap);
        listview = youhuiInfoList;
        youhuiadapter = new YouHuiAdapter(this, Contants.item);
        listview.setAdapter(youhuiadapter);
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
        listview1 = youhuiInfoList;
        onitemclicklistener = new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int k1, long l1)
            {
                Intent intent = new Intent(FillOrderActivity.this, com/jindong/app/mall/shopping/EditYouHuiLipinActivity);
                startActivityForResult(intent, 5);
            }

            final FillOrderActivity this$0;

            
            {
                this$0 = FillOrderActivity.this;
                super();
            }
        }
;
        listview1.setOnItemClickListener(onitemclicklistener);
        return;
        jsonexception2;
        jsonexception2.printStackTrace();
          goto _L7
_L4:
        flag = true;
          goto _L7
_L2:
        if(Contants.jinSelArray == null || Contants.jinSelArray.toString() == "null" || Contants.jinSelArray.toString() == "" || Contants.jinSelArray.length() <= 0)
            break MISSING_BLOCK_LABEL_681;
        l = 0;
        d2 = 0.0D;
        i1 = 0;
_L8:
label0:
        {
            int j1 = Contants.jinSelArray.length();
            if(i1 < j1)
                break label0;
            s = (new StringBuilder(String.valueOf(s))).append("\u4F7F\u7528").append(String.valueOf(l)).append("\u5F20").append("\u4EAC\u5238").append(",").append(" \u9762\u989D").append(String.valueOf(d2)).append("\u5143").toString();
        }
          goto _L7
        JSONObject jsonobject2 = new JSONObject();
        if(jsonobject2 == null || jsonobject2.toString() == "null")
            break MISSING_BLOCK_LABEL_665;
        JSONObject jsonobject3 = Contants.jinSelArray.getJSONObject(i1);
        l++;
        d3 = Double.valueOf(jsonobject3.get("Discount").toString()).doubleValue();
        d2 += d3;
_L9:
        i1++;
          goto _L8
        jsonexception1;
        jsonexception1.printStackTrace();
          goto _L9
        flag = true;
          goto _L7
_L6:
        JSONObject jsonobject = new JSONObject();
        if(jsonobject == null || jsonobject.toString() == "null")
            break MISSING_BLOCK_LABEL_751;
        JSONObject jsonobject1 = Contants.liSelArray.getJSONObject(j);
        i++;
        d1 = Double.valueOf(jsonobject1.get("Discount").toString()).doubleValue();
        d += d1;
_L11:
        j++;
          goto _L10
        jsonexception;
        jsonexception.printStackTrace();
          goto _L11
        if(flag)
            s = "\u60A8\u6CA1\u6709\u9009\u62E9\u4F18\u60E0\u5238\u6216\u793C\u54C1\u5361";
          goto _L12
    }

    public void comositeBody()
    {
        compositeOrderStr();
        getCartItemInfo();
        jbBody = new JSONObject();
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("CartStr", jbCartStr);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public void initLastOrderInfo(JSONObjectProxy jsonobjectproxy)
    {
        JSONObject jsonobject1;
        JSONObject jsonobject2;
        JSONObject jsonobject;
        JSONObject jsonobject3;
        JSONObject jsonobject4;
        if(isLastOrderInfoContainField("Name", jsonobjectproxy) && "" != jsonobjectproxy.getStringOrNull("Name") && jsonobjectproxy.getStringOrNull("Name") != null && jsonobjectproxy.getStringOrNull("Name").length() > 0)
            LastOrderInfo.mUserInfo.setUserName(jsonobjectproxy.getStringOrNull("Name"));
        else
            bNew = true;
        if(isLastOrderInfoContainField("Phone", jsonobjectproxy))
            LastOrderInfo.mUserInfo.setUserPhone(jsonobjectproxy.getStringOrNull("Phone"));
        if(isLastOrderInfoContainField("Mobile", jsonobjectproxy))
            LastOrderInfo.mUserInfo.setUserMobile(jsonobjectproxy.getStringOrNull("Mobile"));
        if(isLastOrderInfoContainField("Zip", jsonobjectproxy))
            LastOrderInfo.mUserInfo.setUserZip(jsonobjectproxy.getStringOrNull("Zip"));
        jsonobject = new JSONObject();
        jsonobject.put("IdProvince", jsonobjectproxy.getIntOrNull("IdProvince"));
        jsonobject.put("IdCity", jsonobjectproxy.getIntOrNull("IdCity"));
        jsonobject.put("IdArea", jsonobjectproxy.getIntOrNull("IdArea"));
        if(isLastOrderInfoContainField("Where", jsonobjectproxy))
            jsonobject.put("Where", jsonobjectproxy.getStringOrNull("Where"));
        if(isLastOrderInfoContainField("Email", jsonobjectproxy))
            jsonobject.put("Email", jsonobjectproxy.getStringOrNull("Email"));
        jsonobject.put("UserLevel", jsonobjectproxy.get("UserLevel"));
        LastOrderInfo.mUserInfo.setUserAddr(jsonobject);
        jsonobject1 = new JSONObject();
        jsonobject1.put("IdPaymentType", jsonobjectproxy.getIntOrNull("IdPaymentType"));
        jsonobject1.put("IdShipmentType", jsonobjectproxy.getIntOrNull("IdShipmentType"));
        if(isLastOrderInfoContainField("CODTime", jsonobjectproxy))
            jsonobject1.put("CODTime", jsonobjectproxy.getInt("CODTime"));
        if(isLastOrderInfoContainField("CodDate", jsonobjectproxy))
            jsonobject1.put("CodDate", jsonobjectproxy.get("CodDate"));
        if(isLastOrderInfoContainField("ShipTime", jsonobjectproxy))
            jsonobject1.put("ShipTime", jsonobjectproxy.get("ShipTime"));
        if(!isLastOrderInfoContainField("IsCodInform", jsonobjectproxy)) goto _L2; else goto _L1
_L1:
        jsonobject1.put("IsCodInform", jsonobjectproxy.get("IsCodInform"));
_L10:
        if(isLastOrderInfoContainField("PaymentWay", jsonobjectproxy))
            jsonobject1.put("PaymentWay", jsonobjectproxy.get("PaymentWay"));
        if(isLastOrderInfoContainField("IdPickSite", jsonobjectproxy))
            jsonobject1.put("IdPickSite", jsonobjectproxy.get("IdPickSite"));
        LastOrderInfo.mPaymentInfo.nSelected = jsonobject1.getInt("IdPaymentType");
        LastOrderInfo.mPaymentInfo.nSelected;
        LastOrderInfo.mPaymentInfo.setPayMentType(jsonobject1.getInt("IdPaymentType"), jsonobject1);
        jsonobject2 = new JSONObject();
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
        if(!isLastOrderInfoContainField("IsPutBookInvoice", jsonobjectproxy) || jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice") == null) goto _L4; else goto _L3
_L3:
        if(jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice").booleanValue()) goto _L6; else goto _L5
_L5:
        jsonobject2.put("IdInvoiceContentTypeBook", -1);
        jsonobject2.put("IsPutBookInvoice", jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice"));
_L8:
        LastOrderInfo.mInvoiceInfo.setInvoiceInfo(jsonobject2);
        jsonobject3 = new JSONObject();
        if(isLastOrderInfoContainField("TheCoupons", jsonobjectproxy))
        {
            jsonobject3.put("TheCoupons", jsonobjectproxy.get("TheCoupons"));
            LastOrderInfo.mYouhuiQuan.Coupons = jsonobject3;
        }
        jsonobject4 = new JSONObject();
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
          goto _L7
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L7
_L2:
        jsonobject1.put("IsCodInform", false);
        continue; /* Loop/switch isn't completed */
_L6:
        jsonobject2.put("IsPutBookInvoice", jsonobjectproxy.getBooleanOrNull("IsPutBookInvoice"));
          goto _L8
_L4:
        jsonobject2.put("IdInvoiceContentTypeBook", -1);
          goto _L8
_L7:
        return;
        if(true) goto _L10; else goto _L9
_L9:
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        j;
        JVM INSTR tableswitch 1 5: default 44
    //                   1 45
    //                   2 93
    //                   3 141
    //                   4 157
    //                   5 178;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            updateUserInfoByAddEasyBuy();
            updateOrderStrByUserInfoByAddEasyTemp();
            update_by_addr = true;
            updatePaymentTypesByAddEasyBuy();
        } else
        {
            updateUserInfo();
            update_by_addr = true;
            updatePaymentTypes();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            updateUserInfoByAddEasyBuy();
            updateOrderStrByUserInfoByAddEasyTemp();
            update_by_addr = true;
            updatePaymentTypesByAddEasyBuy();
        } else
        {
            updateUserInfo();
            update_by_addr = true;
            updatePaymentTypes();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        update_by_payment = true;
        updatePaymentInfo();
        updatePaymentTypesByModifyPayment();
        continue; /* Loop/switch isn't completed */
_L5:
        update_by_invoince = true;
        update_by_payment = true;
        updateInvoinceInfo();
        updatePaymentTypesByInvoice();
        continue; /* Loop/switch isn't completed */
_L6:
        updateYouHuiXin();
        recalOrderAfterYouHui();
        if(true) goto _L1; else goto _L7
_L7:
    }

    protected void onCreate(Bundle bundle1)
    {
        super.onCreate(bundle1);
        if(Contants.bAddEasyBuy)
        {
            setContentView(0x7f030022);
            mNewPaymentInfo = new PaymentInfo();
            mNewInvoiceInfo = new InvoiceInfo();
            jbYunFeeList = new JSONArray();
            jbBalance = new JSONObject();
            mTempName = (EditText)findViewById(0x7f0c009b);
            mTempName.setEnabled(true);
            initComponent();
            compositeDefaultOrderStr();
            show = true;
            onlyBook = false;
            bUseBalance = true;
            update_by_addr = false;
            update_by_payment = false;
            update_by_invoince = false;
            noBook = false;
            newWay = false;
            setEmptyReceiverInfoArea();
            setEmptyPaymentInfoArea();
            setEmptyInvoinceInfoArea();
            setBalanceView();
            getCartItemInfo();
            handleClickEvent();
        } else
        if(Contants.bEasyBuy)
        {
            setContentView(0x7f030022);
            mNewPaymentInfo = new PaymentInfo();
            mNewInvoiceInfo = new InvoiceInfo();
            jbYunFeeList = new JSONArray();
            jbBalance = new JSONObject();
            mTempName = (EditText)findViewById(0x7f0c009b);
            mTempName.setEnabled(false);
            mTempName.setText(DefaultEasyTempOrderInfo.sTempName);
            initComponent();
            show = true;
            onlyBook = false;
            bUseBalance = true;
            update_by_addr = false;
            noBook = false;
            newWay = false;
            compositeEasyBuyCartStr();
            compositeOrderStr(DefaultEasyTempOrderInfo.jsonDefaultTemp);
            handleClickEvent();
            jsonOrderInfoContainer = DefaultEasyTempOrderInfo.jsonDefaultTemp;
            initLastOrderInfo(DefaultEasyTempOrderInfo.jsonDefaultTemp);
            setBalanceView();
            getProvices();
        } else
        if(Contants.bModifyEasyBuy)
        {
            setContentView(0x7f030022);
            mNewPaymentInfo = new PaymentInfo();
            mNewInvoiceInfo = new InvoiceInfo();
            jbYunFeeList = new JSONArray();
            jbBalance = new JSONObject();
            mTempName = (EditText)findViewById(0x7f0c009b);
            mTempName.setEnabled(true);
            mTempName.setText(DefaultEasyTempOrderInfo.sTempName);
            initComponent();
            compositeOrderStr(DefaultEasyTempOrderInfo.jsonTemp.getJSONObjectOrNull("Info"));
            show = true;
            onlyBook = false;
            bUseBalance = true;
            update_by_addr = false;
            noBook = false;
            newWay = false;
            handleClickEvent();
            setBalanceView();
            jsonOrderInfoContainer = DefaultEasyTempOrderInfo.jsonTemp;
            initLastOrderInfo(DefaultEasyTempOrderInfo.jsonTemp.getJSONObjectOrNull("Info"));
            getProvices();
        } else
        {
            setContentView(0x7f03002c);
            mNewPaymentInfo = new PaymentInfo();
            mNewInvoiceInfo = new InvoiceInfo();
            jbYunFeeList = new JSONArray();
            jbBalance = new JSONObject();
            initComponent();
            show = true;
            onlyBook = false;
            bUseBalance = true;
            update_by_addr = false;
            noBook = false;
            newWay = false;
            getCartItemInfo();
            getLarstOrderInfo();
            handleClickEvent();
            setYouhuiArea();
        }
    }

    public void onDestroy()
    {
        LastOrderInfo.mUserInfo = new UserInfo();
        LastOrderInfo.mPaymentInfo = new PaymentInfo();
        LastOrderInfo.mInvoiceInfo = new InvoiceInfo();
        LastOrderInfo.mYouhuiQuan = new YouHuiQuan();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
        {
            Contants.dongSel = new JSONObject();
            Contants.jinSelArray = new JSONArray();
            Contants.liSelArray = new JSONArray();
            Contants.nSelectDongQuanId = "";
            Contants.nselectJingQuanIDs = null;
            Contants.nSelectLipinIDs = null;
            Contants.jSelected = false;
            Contants.dSelected = false;
            Contants.liSelected = false;
        }
        return super.onKeyDown(i, keyevent);
    }

    public void onStart()
    {
        super.onStart();
    }

    public void updatCcomositeBody()
    {
        updateCompositeOrderStr();
        getCartItemInfo();
        jbBody = new JSONObject();
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("CartStr", jbCartStr);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public void updatCcomositeBodyByInvoice()
    {
        updateCompositeOrderStrByInvoice();
        getCartItemInfo();
        jbBody = new JSONObject();
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("CartStr", jbCartStr);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public void updatCcomositeBodyByModifyPayment()
    {
        updateCompositeOrderStrByModifyPayment();
        getCartItemInfo();
        jbBody = new JSONObject();
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("CartStr", jbCartStr);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public static final int ADD_ADDR_CODE = 2;
    public static final int ADD_LINPIN = 17;
    public static final int ADD_YOUHUI = 16;
    public static final int CALC_ORDER = 13;
    public static final int EASY_BUY_ADD_TEMP = 18;
    public static final int EASY_BUY_MODIFY = 22;
    public static final int EASY_BUY_PAYMENT_INFO = 20;
    public static final int EASY_BUY_SUBMIT = 21;
    public static final int EASY_BUY_SUBMIT_ORDER = 19;
    public static final int GET_AREAS = 12;
    public static final int GET_BALANCE = 15;
    public static final int GET_BY_SELF_INFO = 5;
    public static final int GET_CITYS = 11;
    public static final int GET_INVOINCE_BOOK = 9;
    public static final int GET_INVOINCE_GENERAL = 10;
    public static final int GET_INVOINCE_TITLE = 8;
    public static final int GET_INVOINCE_TYPES = 7;
    public static final int GET_LAST_ORDER_INFO = 1;
    public static final int GET_PAYMMENT_TYPE = 3;
    public static final int GET_PEI_SONG_INFO = 4;
    public static final int GET_POST_INFO = 6;
    public static final int GET_PROVINCE = 2;
    public static final int MODIFY_ADDR_CODE = 1;
    public static final int MODIFY_INVOINCE_CODE = 4;
    public static final int MODIFY_PAYMENT_CODE = 3;
    public static final int MODIFY_YOU_HUI_CODE = 5;
    public static final int SUBMIT_ORDER = 14;
    static boolean bUseBalance = true;
    static JSONObject jbBalance;
    static JSONObject jbBody;
    static JSONObject jbCartStr;
    static JSONObject jbOrderStr;
    private static JSONObjectProxy jsonOrderInfoContainer;
    public static InvoiceInfo mNewInvoiceInfo;
    public static PaymentInfo mNewPaymentInfo;
    public static UserInfo mNewUserInfo;
    public static YouHuiQuan mNewYouhuiQuan;
    static String sInfoType;
    private String TAG;
    private boolean bNew;
    Bundle bundle;
    private ListView invoinceInfoList;
    String items[];
    JSONObject jbAreas;
    JSONObject jbCitys;
    JSONObject jbDongQuan;
    JSONObject jbJingQuan;
    JSONObject jbLipin;
    JSONObject jbProvinces;
    JSONArray jbYunFeeList;
    TextView mBalance;
    private TextView mBalance1;
    ImageButton mBalance2;
    private TextView mFanXian;
    private TextView mLastMoney;
    private TextView mLipin;
    EditText mRemark;
    Button mSubmit;
    private EditText mTempName;
    TextView mTitle;
    private TextView mTotalPrice;
    private TextView mTransportFee;
    private TextView mYouhuiQuan;
    SimpleAdapter myAdapter;
    boolean newWay;
    boolean noBook;
    boolean onlyBook;
    private ListView paymentInfoList;
    private ListView receiverInfoList;
    String sOrderNo;
    String sPayWay;
    String sRemark;
    String sTotalMoney;
    boolean show;
    boolean submit;
    boolean update_by_addr;
    boolean update_by_invoince;
    boolean update_by_payment;
    List value;
    private ListView youhuiInfoList;


































}
