// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.login.LoginActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.shopping.*;
import com.jindong.app.mall.utils.*;
import com.jindong.app.mall.utils.frame.ScrollableTabActivity;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.entity:
//            LastOrderInfo, UserInfo, PaymentInfo, InvoiceInfo, 
//            YouHuiQuan

public class DefaultEasyTempOrderInfo extends LastOrderInfo
{

    public DefaultEasyTempOrderInfo()
    {
    }

    public DefaultEasyTempOrderInfo(UserInfo userinfo, PaymentInfo paymentinfo, InvoiceInfo invoiceinfo, YouHuiQuan youhuiquan)
    {
        super(userinfo, paymentinfo, invoiceinfo, youhuiquan);
    }

    private static void calculateOrder(final MyActivity context)
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            resetJBBody();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("calcOrder");
        httpsetting.setJsonParams(jbBody);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy;
                int i;
                jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("yunfeeList");
                i = 0;
_L4:
                int j = jsonarraypoxy.length();
                if(i < j) goto _L2; else goto _L1
_L1:
                DefaultEasyTempOrderInfo.submitEasyBuyOrder(context);
                return;
_L2:
                try
                {
                    if(!jsonarraypoxy.getJSONObject(i).getString("label").contains("\u5E94\u4ED8\u603B\u989D"))
                        break; /* Loop/switch isn't completed */
                    DefaultEasyTempOrderInfo.sTotalMoney = jsonarraypoxy.getJSONObject(i).getString("value");
                    Contants.dTotalPrice = jsonarraypoxy.getJSONObject(i).getString("value");
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
                if(true) goto _L1; else goto _L3
_L3:
                i++;
                  goto _L4
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

            private final MyActivity val$context;

            
            {
                context = myactivity;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        context.getHttpGroupaAsynPool().add(httpsetting);
    }

    private static void compositeOrderStr()
    {
label0:
        {
            jbOrderStr = new JSONObject();
            try
            {
                if(!LoginUser.hasLogin())
                    break MISSING_BLOCK_LABEL_987;
                jbOrderStr.put("pin", LoginUser.getLoginUserName());
                if(isLastOrderInfoContainField("Name", jsonDefaultTemp))
                    jbOrderStr.put("Name", jsonDefaultTemp.getString("Name"));
                if(isLastOrderInfoContainField("Mobile", jsonDefaultTemp))
                    jbOrderStr.put("Mobile", jsonDefaultTemp.getString("Mobile"));
                if(isLastOrderInfoContainField("Phone", jsonDefaultTemp))
                    jbOrderStr.put("Phone", jsonDefaultTemp.getString("Phone"));
                if(isLastOrderInfoContainField("Zip", jsonDefaultTemp))
                    jbOrderStr.put("Zip", jsonDefaultTemp.get("Zip"));
                if(isLastOrderInfoContainField("IdProvince", jsonDefaultTemp))
                    jbOrderStr.put("IdProvince", jsonDefaultTemp.get("IdProvince"));
                if(isLastOrderInfoContainField("IdCity", jsonDefaultTemp))
                    jbOrderStr.put("IdCity", jsonDefaultTemp.get("IdCity"));
                if(isLastOrderInfoContainField("IdArea", jsonDefaultTemp))
                    jbOrderStr.put("IdArea", jsonDefaultTemp.get("IdArea"));
                if(isLastOrderInfoContainField("Where", jsonDefaultTemp))
                    jbOrderStr.put("Where", jsonDefaultTemp.get("Where"));
                if(isLastOrderInfoContainField("Email", jsonDefaultTemp))
                    jbOrderStr.put("Email", jsonDefaultTemp.get("Email"));
                if(isLastOrderInfoContainField("UserLevel", jsonDefaultTemp))
                    jbOrderStr.put("UserLevel", jsonDefaultTemp.get("UserLevel"));
                if(isLastOrderInfoContainField("InvoiceTitle", jsonDefaultTemp))
                    jbOrderStr.put("InvoiceTitle", jsonDefaultTemp.get("InvoiceTitle"));
                if(isLastOrderInfoContainField("IdInvoiceType", jsonDefaultTemp))
                    jbOrderStr.put("IdInvoiceType", jsonDefaultTemp.get("IdInvoiceType"));
                if(isLastOrderInfoContainField("IdInvoiceHeaderType", jsonDefaultTemp))
                    jbOrderStr.put("IdInvoiceHeaderType", jsonDefaultTemp.get("IdInvoiceHeaderType"));
                if(isLastOrderInfoContainField("IdInvoiceContentTypeBook", jsonDefaultTemp))
                    jbOrderStr.put("IdInvoiceContentTypeBook", jsonDefaultTemp.get("IdInvoiceContentTypeBook"));
                if(isLastOrderInfoContainField("IdCompanyBranch", jsonDefaultTemp))
                    jbOrderStr.put("IdCompanyBranch", jsonDefaultTemp.get("IdCompanyBranch"));
                if(isLastOrderInfoContainField("IdCompanyBranch", jsonDefaultTemp))
                    jbOrderStr.put("IdCompanyBranch", jsonDefaultTemp.get("IdCompanyBranch"));
                if(isLastOrderInfoContainField("IdInvoiceContentsType", jsonDefaultTemp))
                    jbOrderStr.put("IdInvoiceContentsType", jsonDefaultTemp.get("IdInvoiceContentsType"));
                if(isLastOrderInfoContainField("CompanyName", jsonDefaultTemp))
                    jbOrderStr.put("CompanyName", jsonDefaultTemp.get("CompanyName"));
                if(isLastOrderInfoContainField("IdInvoicePutType", jsonDefaultTemp))
                    jbOrderStr.put("IdInvoicePutType", jsonDefaultTemp.get("IdInvoicePutType"));
                if(!isLastOrderInfoContainField("IsPutBookInvoice", jsonDefaultTemp) || jsonDefaultTemp.getBooleanOrNull("IsPutBookInvoice") == null)
                    break label0;
                if(!jsonDefaultTemp.getBooleanOrNull("IsPutBookInvoice").booleanValue())
                {
                    jbOrderStr.put("IdInvoiceContentTypeBook", -1);
                    jbOrderStr.put("IsPutBookInvoice", jsonDefaultTemp.getBooleanOrNull("IsPutBookInvoice"));
                } else
                {
                    jbOrderStr.put("IsPutBookInvoice", jsonDefaultTemp.getBooleanOrNull("IsPutBookInvoice"));
                }
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
                break MISSING_BLOCK_LABEL_987;
            }
        }
_L1:
        if(isLastOrderInfoContainField("IdPaymentType", jsonDefaultTemp))
            jbOrderStr.put("IdPaymentType", jsonDefaultTemp.get("IdPaymentType"));
        if(isLastOrderInfoContainField("PaymentWay", jsonDefaultTemp))
            jbOrderStr.put("PaymentWay", jsonDefaultTemp.get("PaymentWay"));
        if(isLastOrderInfoContainField("IdShipmentType", jsonDefaultTemp))
            jbOrderStr.put("IdShipmentType", jsonDefaultTemp.get("IdShipmentType"));
        if(isLastOrderInfoContainField("CODTime", jsonDefaultTemp))
            jbOrderStr.put("CODTime", jsonDefaultTemp.get("CODTime"));
        if(isLastOrderInfoContainField("CodDate", jsonDefaultTemp))
            jbOrderStr.put("CodDate", jsonDefaultTemp.get("CodDate"));
        if(isLastOrderInfoContainField("ShipTime", jsonDefaultTemp))
            jbOrderStr.put("ShipTime", jsonDefaultTemp.get("ShipTime"));
        if(isLastOrderInfoContainField("ShipTime", jsonDefaultTemp))
            jbOrderStr.put("ShipTime", jsonDefaultTemp.get("ShipTime"));
        if(isLastOrderInfoContainField("IdPickSite", jsonDefaultTemp))
            jbOrderStr.put("IdPickSite", jsonDefaultTemp.get("IdPickSite"));
        if(isLastOrderInfoContainField("PromotionPrice", jsonDefaultTemp))
            jbOrderStr.put("PromotionPrice", jsonDefaultTemp.get("PromotionPrice"));
        if(isLastOrderInfoContainField("Price", jsonDefaultTemp))
            jbOrderStr.put("Price", jsonDefaultTemp.get("Price"));
        if(isLastOrderInfoContainField("IsUseBalance", jsonDefaultTemp))
        {
            jbOrderStr.put("IsUseBalance", jsonDefaultTemp.get("IsUseBalance"));
            break MISSING_BLOCK_LABEL_987;
        }
        break MISSING_BLOCK_LABEL_977;
        jbOrderStr.put("IdInvoiceContentTypeBook", -1);
          goto _L1
        jbOrderStr.put("IsUseBalance", true);
    }

    private static void getCartItemInfo(MyActivity myactivity)
    {
        JSONArray jsonarray;
        JSONObject jsonobject;
        new JSONObject();
        jbCartStr = new JSONObject();
        jsonarray = new JSONArray();
        jsonobject = new JSONObject();
        jsonobject.put("Id", String.valueOf(Contants.easyBuyProdId));
        jsonobject.put("num", String.valueOf(1));
        jsonarray.put(jsonobject);
        jbCartStr.put("TheSkus", jsonarray);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public static void getDefaultOrderInfo(final MyActivity context)
    {
        if(!LoginUser.hasLogin())
        {
            context.startSingleActivityInFrame(new Intent(context, com/jindong/app/mall/login/LoginActivity));
        } else
        {
            final String TAG = context.getLocalClassName();
            JSONObject jsonobject = new JSONObject();
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
            try
            {
                jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setFunctionId("easyBuyGetDefaultTemp");
            httpsetting.setJsonParams(jsonobject);
            httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    DefaultEasyTempOrderInfo.handleDatas(httpresponse, context);
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

                private final String val$TAG;
                private final MyActivity val$context;

            
            {
                TAG = s;
                context = myactivity;
                super();
            }
            }
);
            httpsetting.setNotifyUser(true);
            context.getHttpGroupaAsynPool().add(httpsetting);
        }
    }

    public static void getEasyBuyById(final MyActivity context, Long long1)
    {
        if(!LoginUser.hasLogin())
        {
            context.startSingleActivityInFrame(new Intent(context, com/jindong/app/mall/login/LoginActivity));
        } else
        {
            final String TAG = context.getLocalClassName();
            JSONObject jsonobject = new JSONObject();
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
            try
            {
                jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
                jsonobject.put("Id", long1);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setFunctionId("getOrderTemplateById");
            httpsetting.setJsonParams(jsonobject);
            httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    DefaultEasyTempOrderInfo.handleDatas(httpresponse, context);
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

                private final String val$TAG;
                private final MyActivity val$context;

            
            {
                TAG = s;
                context = myactivity;
                super();
            }
            }
);
            httpsetting.setNotifyUser(true);
            context.getHttpGroupaAsynPool().add(httpsetting);
        }
    }

    private static void handleDatas(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, MyActivity myactivity)
    {
        String s = myactivity.getLocalClassName();
        if(!Contants.bEasyBuy) goto _L2; else goto _L1
_L1:
        jsonOrderInfoContainer = httpresponse.getJSONObject();
        if(jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate") != null && (jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders") == null || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders").length() < 1))
        {
            showAlertDilgog(myactivity);
            break MISSING_BLOCK_LABEL_450;
        }
        if(jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate") == null || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders") == null || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders").length() <= 0 || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders") == null || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders").getJSONObjectOrNull(0) == null)
            break MISSING_BLOCK_LABEL_450;
        sTempName = jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders").getJSONObject(0).getString("Name");
        jsonDefaultTemp = jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders").getJSONObjectOrNull(0).getJSONObjectOrNull("Info");
        Contants.bEasyBuy = true;
        if(!Contants.shouldConfirmEasyBuy(myactivity)) goto _L4; else goto _L3
_L3:
        Contants.bModifyEasyBuy = false;
        Contants.bAddEasyBuy = false;
        turnToOther(myactivity);
_L5:
        Exception exception1;
        if(myactivity instanceof ProductDetailActivity)
            ((ProductDetailActivity)myactivity).canClickEasyBuyBtn();
        break MISSING_BLOCK_LABEL_450;
_L4:
        try
        {
            compositeOrderStr();
            getCartItemInfo(myactivity);
            calculateOrder(myactivity);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception1)
        {
            Contants.bEasyBuy = false;
            if(Log.D)
                Log.d(s, exception1.getMessage());
            break MISSING_BLOCK_LABEL_450;
        }
        if(true) goto _L5; else goto _L2
_L2:
        if(!Contants.bModifyEasyBuy)
            break MISSING_BLOCK_LABEL_450;
        try
        {
            jsonOrderInfoContainer = httpresponse.getJSONObject();
            if(jsonOrderInfoContainer.getJSONObjectOrNull("Order") != null && jsonOrderInfoContainer.getJSONObjectOrNull("Order").getJSONObjectOrNull("Info") == null)
            {
                showAlertDilgog(myactivity);
                Contants.bModifyEasyBuy = false;
                break MISSING_BLOCK_LABEL_450;
            }
        }
        catch(Exception exception)
        {
            Contants.bModifyEasyBuy = false;
            if(Log.D)
                Log.d(s, exception.getMessage());
            break MISSING_BLOCK_LABEL_450;
        }
        if(jsonOrderInfoContainer.getJSONObjectOrNull("Order") != null && jsonOrderInfoContainer.getJSONObjectOrNull("Order").getJSONObjectOrNull("Info") != null)
        {
            sTempName = jsonOrderInfoContainer.getJSONObjectOrNull("Order").getString("Name");
            jsonDefaultTemp = jsonOrderInfoContainer.getJSONObjectOrNull("Order").getJSONObjectOrNull("Info");
            Contants.bModifyEasyBuy = true;
            turnToOther(myactivity);
        }
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

    private static void messageAfterSubmit(final JSONObject message, final MyActivity context)
    {
        context.runOnUiThread(new Runnable() {

            public void run()
            {
                String s;
                if(DefaultEasyTempOrderInfo.jsonDefaultTemp.getInt("IdPaymentType") == 1)
                    s = "\u8D27\u5230\u4ED8\u6B3E";
                else
                if(DefaultEasyTempOrderInfo.jsonDefaultTemp.getInt("IdPaymentType") == 2)
                    s = "\u90AE\u5C40\u6C47\u6B3E";
                else
                if(DefaultEasyTempOrderInfo.jsonDefaultTemp.getInt("IdPaymentType") == 3)
                {
                    s = "\u4EAC\u4E1C\u81EA\u63D0";
                } else
                {
                    if(DefaultEasyTempOrderInfo.jsonDefaultTemp.getInt("IdPaymentType") != 4)
                        break MISSING_BLOCK_LABEL_395;
                    s = "\u5728\u7EBF\u652F\u4ED8";
                }
_L4:
                if(message.getJSONObject("submitOrder").get("OrderId").toString() == "null" || message.getJSONObject("submitOrder").get("OrderId").toString().compareTo("0") == 0) goto _L2; else goto _L1
_L1:
                String s1 = message.getJSONObject("submitOrder").get("OrderId").toString();
                Contants.jbOrderNum = new JSONObject();
                Contants.jbOrderNum.put("orderId", message.getJSONObject("submitOrder").get("OrderId"));
                Intent intent = new Intent(context, com/jindong/app/mall/shopping/CompleteOrderActivity);
                Bundle bundle = new Bundle();
                bundle.putString("order_no", s1);
                bundle.putString("order_money", DefaultEasyTempOrderInfo.sTotalMoney);
                bundle.putString("order_way", s);
                if(message != null && message.toString().contains("coMsg") && message.getString("coMsg") != "null")
                    bundle.putString("order_msg", message.getString("coMsg"));
                else
                    bundle.putString("order_msg", " ");
                intent.putExtras(bundle);
                LastOrderInfo.mUserInfo = new UserInfo();
                LastOrderInfo.mPaymentInfo = new PaymentInfo();
                LastOrderInfo.mInvoiceInfo = new InvoiceInfo();
                LastOrderInfo.mYouhuiQuan = new YouHuiQuan();
                DefaultEasyTempOrderInfo.jsonDefaultTemp = new JSONObjectProxy();
                context.startActivityInFrame(intent);
                  goto _L3
                JSONException jsonexception;
                jsonexception;
                DefaultEasyTempOrderInfo.jsonDefaultTemp = new JSONObjectProxy();
                jsonexception.printStackTrace();
                  goto _L3
_L2:
                Toast.makeText(context, message.getJSONObject("submitOrder").getString("Message"), 1).show();
_L3:
                return;
                s = "\u516C\u53F8\u8F6C\u8D26";
                  goto _L4
            }

            private final MyActivity val$context;
            private final JSONObject val$message;

            
            {
                message = jsonobject;
                context = myactivity;
                super();
            }
        }
);
    }

    private static void resetJBBody()
    {
        jbBody = null;
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

    public static void showAlertDilgog(final MyActivity context)
    {
        context.post(new Runnable() {

            public void run()
            {
                if(!Contants.bEasyBuy) goto _L2; else goto _L1
_L1:
                AlertDialog alertdialog1 = (new android.app.AlertDialog.Builder(context)).create();
                alertdialog1.setMessage("\u60A8\u8FD8\u6CA1\u6709\u6DFB\u52A0\u8F7B\u677E\u8D2D\u6216\u6CA1\u6709\u8BBE\u7F6E\u9ED8\u8BA4\u8F7B\u677E\u8D2D,\u73B0\u5728\u53BB\u6DFB\u52A0\u5417?");
                alertdialog1.setTitle("\u6DFB\u52A0\u8F7B\u677E\u8D2D");
                alertdialog1.setButton(context.getText(0x7f09007a), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        Intent intent = new Intent(context, com/jindong/app/mall/shopping/EasyGoAddrListActivity);
                        context.startTaskActivityInFrame(intent);
                        dialoginterface.dismiss();
                    }

                    final _cls8 this$1;
                    private final MyActivity val$context;

                    
                    {
                        this$1 = _cls8.this;
                        context = myactivity;
                        super();
                    }
                }
);
                alertdialog1.setButton2(context.getText(0x7f09000f), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dialoginterface.dismiss();
                    }

                    final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                }
);
                alertdialog1.show();
_L4:
                return;
_L2:
                if(Contants.bModifyEasyBuy)
                {
                    AlertDialog alertdialog = (new android.app.AlertDialog.Builder(context)).create();
                    alertdialog.setMessage("\u60A8\u67E5\u8BE2\u7684\u8F7B\u677E\u8D2D\u4E0D\u5B58\u5728\u6216\u8005\u6709\u8BEF\uFF0C\u8BF7\u91CD\u65B0\u67E5\u8BE2");
                    alertdialog.setTitle("\u7F16\u8F91\u8F7B\u677E\u8D2D");
                    alertdialog.setButton(context.getText(0x7f09007a), new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            Contants.bModifyEasyBuy = false;
                            dialoginterface.dismiss();
                        }

                        final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                    }
);
                    alertdialog.setButton2(context.getText(0x7f09000f), new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            Contants.bModifyEasyBuy = false;
                            dialoginterface.dismiss();
                        }

                        final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                    }
);
                    alertdialog.show();
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            private final MyActivity val$context;

            
            {
                context = myactivity;
                super();
            }
        }
);
    }

    private static void submitEasyBuyOrder(MyActivity myactivity)
    {
        submitEasyBuyOrder(null, null, myactivity);
    }

    private static void submitEasyBuyOrder(String s, String s1, final MyActivity context)
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            resetJBBody();
            jbBody.put("totalPrice", String.valueOf(Contants.dTotalPrice));
            jbBody.put("templateName", sTempName);
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
                final String final_s = null;
                String s2 = null;
                JSONObjectProxy jsonobjectproxy = null;
                JSONObjectProxy jsonobjectproxy1 = httpresponse.getJSONObject();
                if(jsonobjectproxy1 != null)
                    jsonobjectproxy = jsonobjectproxy1.getJSONObjectOrNull("submitOrder");
                if(jsonobjectproxy != null)
                {
                    final_s = jsonobjectproxy.getStringOrNull("url");
                    s2 = jsonobjectproxy.getStringOrNull("Message");
                }
                if(!TextUtils.isEmpty(final_s))
                    new com.jindong.app.mall.shopping.FillOrderActivity.CaptchaDialogController(s2, context) {

                        protected void submit(String s3, String s4)
                        {
                            DefaultEasyTempOrderInfo.submitEasyBuyOrder(s3, s4, context);
                        }

                        final _cls5 this$1;
                        private final MyActivity val$context;

                    
                    {
                        this$1 = _cls5.this;
                        context = myactivity;
                        super(final_s, s1);
                    }
                    }
;
                else
                    DefaultEasyTempOrderInfo.messageAfterSubmit(httpresponse.getJSONObject(), context);
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

            private final MyActivity val$context;

            
            {
                context = myactivity;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        context.getHttpGroupaAsynPool().add(httpsetting);
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
                DefaultEasyTempOrderInfo.submitEasyBuyOrder(context);
            }

            private final MyActivity val$context;

            
            {
                context = myactivity;
                super();
            }
        }
);
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
    }

    public static void turnToOther(final MyActivity context)
    {
        context.post(new Runnable() {

            public void run()
            {
                if(!Contants.bEasyBuy) goto _L2; else goto _L1
_L1:
                Intent intent = new Intent(context, com/jindong/app/mall/shopping/FillOrderActivity);
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("context:")).append(context).toString());
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("context parent:")).append(context.getParent()).toString());
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("CurrentActivity:")).append(((ScrollableTabActivity)context.getParent()).getCurrentActivity()).toString());
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("intent:")).append(intent).toString());
                context.startTaskActivityInFrame(intent);
_L4:
                return;
_L2:
                if(Contants.bModifyEasyBuy)
                {
                    Intent intent1 = new Intent(context, com/jindong/app/mall/shopping/FillOrderActivity);
                    Contants.bModifyEasyBuy = true;
                    Contants.bEasyBuy = false;
                    Contants.bAddEasyBuy = false;
                    context.startTaskActivityInFrame(intent1);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            private final MyActivity val$context;

            
            {
                context = myactivity;
                super();
            }
        }
);
    }

    static JSONObject jbBody;
    static JSONObject jbCartStr;
    static JSONObject jbOrderStr;
    public static JSONObjectProxy jsonDefaultTemp;
    private static JSONObjectProxy jsonOrderInfoContainer;
    public static JSONObjectProxy jsonTemp;
    public static Long sTempId;
    public static String sTempName;
    static String sTotalMoney;




}
