// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.location.*;
import android.os.Bundle;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            UpdateOrderInfoInterface

public class SelectPaymentType extends MyActivity
    implements UpdateOrderInfoInterface
{
    private class JavaScriptInterface
    {

        public double getLatitude()
        {
            double d1;
            if(mostRecentLocation != null)
                break MISSING_BLOCK_LABEL_48;
            d1 = 0.0D;
            double d2 = SelectPaymentType.jbSendTypes.getJSONArray("PickSites").getJSONObject(0).getDouble("lat");
            d1 = d2;
_L1:
            double d = d1;
_L2:
            return d;
            JSONException jsonexception;
            jsonexception;
            jsonexception.printStackTrace();
              goto _L1
            d = mostRecentLocation.getLatitude();
              goto _L2
        }

        public double getLongitude()
        {
            double d1;
            if(mostRecentLocation != null)
                break MISSING_BLOCK_LABEL_48;
            d1 = 0.0D;
            double d2 = SelectPaymentType.jbSendTypes.getJSONArray("PickSites").getJSONObject(0).getDouble("lng");
            d1 = d2;
_L1:
            double d = d1;
_L2:
            return d;
            JSONException jsonexception;
            jsonexception;
            jsonexception.printStackTrace();
              goto _L1
            d = mostRecentLocation.getLongitude();
              goto _L2
        }

        public String getPickSitesInfo()
        {
            return SelectPaymentType.jbSendTypes.toString();
        }

        public void pickSitesSelected(String s)
        {
            final int pos = Integer.parseInt(s);
            post(new Runnable() {

                public void run()
                {
                    pickSitesSelectedByJs(pos);
                }

                final JavaScriptInterface this$1;
                private final int val$pos;

                
                {
                    this$1 = JavaScriptInterface.this;
                    pos = i;
                    super();
                }
            }
);
        }

        final SelectPaymentType this$0;


        private JavaScriptInterface()
        {
            this$0 = SelectPaymentType.this;
            super();
        }

        JavaScriptInterface(JavaScriptInterface javascriptinterface)
        {
            this();
        }
    }


    public SelectPaymentType()
    {
        TAG = "SelectPaymentType";
        sPaymentType = " ";
        sSendType = " ";
        sSendTime = " ";
        sPayContentType = " ";
        sConfirm = " ";
        sGetPosition = " ";
        nPhoneCheck = 0;
        nSelectedPaymentType = 0;
        nSelectedSendTypes = 0;
        nselectedSendTime = 0;
        nPayMonyTypes = 0;
        nSelectGetPotion = 0;
        nSelectedPaymentTypeID = 1;
        nSelectedSendTypesID = 70;
        nselectedSendTimeID = 1;
        nPayMonyTypesID = 0;
        nSelectGetPotionID = 1;
        items = null;
        view_type = 0;
        bNew = false;
        init = true;
        bFill = false;
        noShipmentInfo = false;
        phone = false;
        pickSitesLoadingFlag = false;
    }

    private void InitViewFromLastOrder(int i)
    {
        if(i == 1)
            setPayAfterReceiveView();
        else
        if(i == 2 || i == 4)
            setPayPostView();
        else
        if(i == 3)
            setSelfView();
        else
            setDefaultView();
    }

    private void compositeOrderStr()
    {
        jbOrderStr = new JSONObject();
        if(!LoginUser.hasLogin()) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("pin", LoginUser.getLoginUserName());
        if(LastOrderInfo.mUserInfo.getUserMobile() != null)
            jbOrderStr.put("Mobile", LastOrderInfo.mUserInfo.getUserMobile());
        else
            jbOrderStr.put("Mobile", "");
        if(LastOrderInfo.mUserInfo.getUserPhone() == null) goto _L4; else goto _L3
_L3:
        jbOrderStr.put("Phone", LastOrderInfo.mUserInfo.getUserPhone());
_L53:
        if(LastOrderInfo.mUserInfo.getUserZip() == null) goto _L6; else goto _L5
_L5:
        jbOrderStr.put("Zip", LastOrderInfo.mUserInfo.getUserZip());
_L37:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince") == null) goto _L8; else goto _L7
_L7:
        jbOrderStr.put("IdProvince", LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince"));
_L38:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdCity") == null) goto _L10; else goto _L9
_L9:
        jbOrderStr.put("IdCity", LastOrderInfo.mUserInfo.getUserAddr().get("IdCity"));
_L39:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdArea") == null) goto _L12; else goto _L11
_L11:
        jbOrderStr.put("IdArea", LastOrderInfo.mUserInfo.getUserAddr().get("IdArea"));
_L40:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Where")) goto _L14; else goto _L13
_L13:
        jbOrderStr.put("Where", LastOrderInfo.mUserInfo.getUserAddr().get("Where"));
_L41:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Email") || LastOrderInfo.mUserInfo.getUserAddr().get("Email") == null) goto _L16; else goto _L15
_L15:
        jbOrderStr.put("Email", LastOrderInfo.mUserInfo.getUserAddr().get("Email"));
_L42:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("UserLevel") || LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel") == null) goto _L18; else goto _L17
_L17:
        jbOrderStr.put("UserLevel", LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel"));
_L43:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceType") == null) goto _L20; else goto _L19
_L19:
        jbOrderStr.put("IdInvoiceType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceType"));
_L44:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceHeaderType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceHeaderType") == null) goto _L22; else goto _L21
_L21:
        jbOrderStr.put("IdInvoiceHeaderType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceHeaderType"));
_L45:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentTypeBook") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentTypeBook") == null) goto _L24; else goto _L23
_L23:
        jbOrderStr.put("IdInvoiceContentTypeBook", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentTypeBook"));
_L46:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdCompanyBranch") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdCompanyBranch") == null) goto _L26; else goto _L25
_L25:
        jbOrderStr.put("IdCompanyBranch", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdCompanyBranch"));
_L47:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentsType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentsType") == null) goto _L28; else goto _L27
_L27:
        jbOrderStr.put("IdInvoiceContentsType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentsType"));
_L48:
        if(LastOrderInfo.mPaymentInfo.type >= 5 || LastOrderInfo.mPaymentInfo.type <= 0) goto _L30; else goto _L29
_L29:
        jbOrderStr.put("IdPaymentType", LastOrderInfo.mPaymentInfo.type);
_L49:
        if(!LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).toString().contains("PaymentWay") || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay") == null) goto _L32; else goto _L31
_L31:
        jbOrderStr.put("PaymentWay", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay"));
_L50:
        if(LastOrderInfo.dPromotionPrice < 0.0D) goto _L34; else goto _L33
_L33:
        jbOrderStr.put("PromotionPrice", LastOrderInfo.dPromotionPrice);
_L51:
        if(LastOrderInfo.dPrice < 0.0D) goto _L36; else goto _L35
_L35:
        jbOrderStr.put("Price", LastOrderInfo.dPrice);
          goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L4:
        jbOrderStr.put("Phone", "");
        continue; /* Loop/switch isn't completed */
_L6:
        jbOrderStr.put("Zip", "");
          goto _L37
_L8:
        jbOrderStr.put("IdProvince", 1);
          goto _L38
_L10:
        jbOrderStr.put("IdCity", 72);
          goto _L39
_L12:
        jbOrderStr.put("IdArea", 2819);
          goto _L40
_L14:
        jbOrderStr.put("Where", "");
          goto _L41
_L16:
        jbOrderStr.put("Email", "");
          goto _L42
_L18:
        jbOrderStr.put("UserLevel", 0);
          goto _L43
_L20:
        jbOrderStr.put("IdInvoiceType", 0);
          goto _L44
_L22:
        jbOrderStr.put("IdInvoiceHeaderType", 0);
          goto _L45
_L24:
        jbOrderStr.put("IdInvoiceContentTypeBook", 0);
          goto _L46
_L26:
        jbOrderStr.put("IdCompanyBranch", 0);
          goto _L47
_L28:
        jbOrderStr.put("IdInvoiceContentsType", 0);
          goto _L48
_L30:
        jbOrderStr.put("IdPaymentType", 1);
          goto _L49
_L32:
        jbOrderStr.put("PaymentWay", 0);
          goto _L50
_L34:
        jbOrderStr.put("PromotionPrice", 0);
          goto _L51
_L36:
        jbOrderStr.put("Price", 0);
_L2:
        return;
        if(true) goto _L53; else goto _L52
_L52:
    }

    private void compositeOrderStrByAddEasyBuy()
    {
        jbOrderStr = new JSONObject();
        if(!LoginUser.hasLogin()) goto _L2; else goto _L1
_L1:
        jbOrderStr.put("pin", LoginUser.getLoginUserName());
        if(LastOrderInfo.mUserInfo.getUserMobile() != null)
            jbOrderStr.put("Mobile", LastOrderInfo.mUserInfo.getUserMobile());
        else
            jbOrderStr.put("Mobile", "");
        if(LastOrderInfo.mUserInfo.getUserPhone() == null) goto _L4; else goto _L3
_L3:
        jbOrderStr.put("Phone", LastOrderInfo.mUserInfo.getUserPhone());
_L32:
        if(LastOrderInfo.mUserInfo.getUserZip() == null) goto _L6; else goto _L5
_L5:
        jbOrderStr.put("Zip", LastOrderInfo.mUserInfo.getUserZip());
_L23:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince") == null) goto _L8; else goto _L7
_L7:
        jbOrderStr.put("IdProvince", LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince"));
_L24:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdCity") == null) goto _L10; else goto _L9
_L9:
        jbOrderStr.put("IdCity", LastOrderInfo.mUserInfo.getUserAddr().get("IdCity"));
_L25:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdArea") == null) goto _L12; else goto _L11
_L11:
        jbOrderStr.put("IdArea", LastOrderInfo.mUserInfo.getUserAddr().get("IdArea"));
_L26:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Where")) goto _L14; else goto _L13
_L13:
        jbOrderStr.put("Where", LastOrderInfo.mUserInfo.getUserAddr().get("Where"));
_L27:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Email") || LastOrderInfo.mUserInfo.getUserAddr().get("Email") == null) goto _L16; else goto _L15
_L15:
        jbOrderStr.put("Email", LastOrderInfo.mUserInfo.getUserAddr().get("Email"));
_L28:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("UserLevel") || LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel") == null) goto _L18; else goto _L17
_L17:
        jbOrderStr.put("UserLevel", LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel"));
_L29:
        if(LastOrderInfo.mPaymentInfo.type >= 5 || LastOrderInfo.mPaymentInfo.type <= 0) goto _L20; else goto _L19
_L19:
        jbOrderStr.put("IdPaymentType", LastOrderInfo.mPaymentInfo.type);
_L30:
        if(!LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).toString().contains("PaymentWay") || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay") == null) goto _L22; else goto _L21
_L21:
        jbOrderStr.put("PaymentWay", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay"));
          goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L4:
        jbOrderStr.put("Phone", "");
        continue; /* Loop/switch isn't completed */
_L6:
        jbOrderStr.put("Zip", "");
          goto _L23
_L8:
        jbOrderStr.put("IdProvince", 1);
          goto _L24
_L10:
        jbOrderStr.put("IdCity", 72);
          goto _L25
_L12:
        jbOrderStr.put("IdArea", 2819);
          goto _L26
_L14:
        jbOrderStr.put("Where", "");
          goto _L27
_L16:
        jbOrderStr.put("Email", "");
          goto _L28
_L18:
        jbOrderStr.put("UserLevel", 0);
          goto _L29
_L20:
        jbOrderStr.put("IdPaymentType", 1);
          goto _L30
_L22:
        jbOrderStr.put("PaymentWay", 0);
_L2:
        return;
        if(true) goto _L32; else goto _L31
_L31:
    }

    private void createPaymentTypeAlertDiglog(String s)
    {
        final String oldPayment;
        new JSONArray();
        oldPayment = mPayType.getText().toString();
        if(jbPaymentTypes != null && jbPaymentTypes.get("paymentInfo").toString() != "null" && jbPaymentTypes.get("paymentInfo") != null) goto _L2; else goto _L1
_L2:
        JSONArray jsonarray;
        int i;
        jsonarray = jbPaymentTypes.getJSONArray("paymentInfo");
        items = new String[jsonarray.length()];
        i = 0;
_L6:
        int j = jsonarray.length();
        if(i < j) goto _L4; else goto _L3
_L3:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nSelectedPaymentType, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(k).toString());
                if(oldPayment.compareTo(items[k]) == 0)
                {
                    mPayType.setText(items[k]);
                    nSelectedPaymentType = k;
                    dialoginterface.dismiss();
                } else
                {
                    nSelectedPaymentType = k;
                    bNew = true;
                    setNewPaymentType(k);
                    dialoginterface.dismiss();
                }
            }

            final SelectPaymentType this$0;
            private final String val$oldPayment;

            
            {
                this$0 = SelectPaymentType.this;
                oldPayment = s;
                super();
            }
        }
).show();
        break; /* Loop/switch isn't completed */
_L4:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
        if(true) goto _L3; else goto _L1
_L1:
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void createSendTimeAlertDiglog(String s)
    {
        final String temp;
        new JSONArray();
        temp = mSendTime.getText().toString();
        if(jbShipments != null && jbShipments.get("ShipmentTypes").toString() != "null" && jbShipments.get("ShipmentTypes") != null && jbShipments.getJSONArray("ShipmentTypes").get(nSelectedSendTypes) != null && jbShipments.getJSONArray("ShipmentTypes").get(nSelectedSendTypes).toString() != "null" && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupCodTime") != null && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupCodTime").toString() != "null") goto _L2; else goto _L1
_L2:
        JSONArray jsonarray;
        int i;
        jsonarray = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime");
        items = new String[jsonarray.length()];
        i = 0;
_L6:
        int j = jsonarray.length();
        if(i < j) goto _L4; else goto _L3
_L3:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nselectedSendTime, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(k).toString());
                if(temp.compareTo(items[k]) == 0)
                {
                    mSendTime.setText(items[k]);
                    nselectedSendTime = k;
                    dialoginterface.dismiss();
                } else
                {
                    nselectedSendTime = k;
                    bNew = true;
                    setNewSendTime(k);
                    dialoginterface.dismiss();
                }
            }

            final SelectPaymentType this$0;
            private final String val$temp;

            
            {
                this$0 = SelectPaymentType.this;
                temp = s;
                super();
            }
        }
).show();
        break; /* Loop/switch isn't completed */
_L4:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
        if(true) goto _L3; else goto _L1
_L1:
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void createSendTypeAlertDiglog(String s)
    {
        android.app.AlertDialog.Builder builder;
        new JSONArray();
        builder = new android.app.AlertDialog.Builder(this);
        if(nSelectedPaymentTypeID != 1 && nSelectedPaymentTypeID != 4 && nSelectedPaymentTypeID != 2) goto _L2; else goto _L1
_L1:
        final String temp = mSendType.getText().toString();
        if(jbShipments != null && jbShipments.get("ShipmentTypes").toString() != "null" && jbShipments.get("ShipmentTypes") != null) goto _L4; else goto _L3
_L4:
        JSONArray jsonarray;
        int i;
        jsonarray = jbShipments.getJSONArray("ShipmentTypes");
        items = new String[jsonarray.length()];
        i = 0;
_L12:
        int j = jsonarray.length();
        if(i < j) goto _L6; else goto _L5
_L5:
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nSelectedSendTypes, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int l)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(l).toString());
                if(temp.compareTo(items[l]) == 0)
                {
                    if(nSelectedPaymentTypeID == 3)
                        mGetPosition.setText(items[l]);
                    else
                        mSendType.setText(items[l]);
                    nSelectedSendTypes = l;
                    dialoginterface.dismiss();
                } else
                {
                    nSelectedSendTypes = l;
                    bNew = true;
                    setNewSendType(l);
                    dialoginterface.dismiss();
                }
            }

            final SelectPaymentType this$0;
            private final String val$temp;

            
            {
                this$0 = SelectPaymentType.this;
                temp = s;
                super();
            }
        }
).show();
        break; /* Loop/switch isn't completed */
_L6:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
        if(true) goto _L5; else goto _L3
_L2:
        if(nSelectedPaymentTypeID != 3) goto _L3; else goto _L7
_L7:
        JSONArray jsonarray1;
        int k;
        mGetPosition.getText().toString();
        jsonarray1 = jbSendTypes.getJSONArray("PickSites");
        items = new String[jsonarray1.length()];
        k = 0;
_L10:
        if(k < jsonarray1.length()) goto _L9; else goto _L8
_L8:
        builder.setTitle(s);
        setMapButton(builder);
          goto _L3
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
          goto _L3
_L9:
        items[k] = jsonarray1.getJSONObject(k).getString("Name");
        k++;
          goto _L10
_L3:
        return;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void createpayMoneyContentAlertDiglog(String s)
    {
        final String temp;
        new JSONArray();
        temp = mPayContentType.getText().toString();
        if(jbShipments != null && jbShipments.get("ShipmentTypes").toString() != "null" && jbShipments.get("ShipmentTypes") != null && jbShipments.getJSONArray("ShipmentTypes").get(nSelectedSendTypes) != null && jbShipments.getJSONArray("ShipmentTypes").get(nSelectedSendTypes).toString() != "null" && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupPaymentWay") != null && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupPaymentWay").toString() != "null") goto _L2; else goto _L1
_L2:
        JSONArray jsonarray;
        int i;
        jsonarray = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay");
        items = new String[jsonarray.length()];
        i = 0;
_L6:
        int j = jsonarray.length();
        if(i < j) goto _L4; else goto _L3
_L3:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nPayMonyTypes, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(k).toString());
                if(temp.compareTo(items[k]) == 0)
                {
                    mPayContentType.setText(items[k]);
                    nPayMonyTypes = k;
                    dialoginterface.dismiss();
                } else
                {
                    nPayMonyTypes = k;
                    bNew = true;
                    setPayMoneyContent(k);
                    dialoginterface.dismiss();
                }
            }

            final SelectPaymentType this$0;
            private final String val$temp;

            
            {
                this$0 = SelectPaymentType.this;
                temp = s;
                super();
            }
        }
).show();
        break; /* Loop/switch isn't completed */
_L4:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
        if(true) goto _L3; else goto _L1
_L1:
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void getBundleData(Bundle bundle)
    {
        if(bundle.getInt("type") != 1 && bundle.getInt("type") != 4) goto _L2; else goto _L1
_L1:
        if(bundle.containsKey("pay_type"))
        {
            sPaymentType = bundle.getString("pay_type");
            nSelectedPaymentTypeID = LastOrderInfo.mPaymentInfo.nSelected;
        } else
        {
            sPaymentType = " ";
        }
        if(bundle.containsKey("send_type"))
            sSendType = bundle.getString("send_type");
        else
            sSendType = " ";
        if(bundle.containsKey("pay_content"))
        {
            sPayContentType = bundle.getString("pay_content");
            try
            {
                nPayMonyTypesID = LastOrderInfo.mPaymentInfo.getPayMentType(1).getInt("PaymentWay");
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            sPayContentType = " ";
        }
        if(bundle.containsKey("send_time"))
            sSendTime = bundle.getString("send_time");
        else
            sSendTime = " ";
        if(bundle.containsKey("phone_confirm"))
            sConfirm = bundle.getString("phone_confirm");
        else
            sConfirm = " ";
        view_type = 1;
_L4:
        return;
_L2:
        if(bundle.getInt("type") == 3)
        {
            if(bundle.containsKey("pay_type"))
            {
                sPaymentType = bundle.getString("pay_type");
                nSelectedPaymentTypeID = LastOrderInfo.mPaymentInfo.nSelected;
            } else
            {
                sPaymentType = " ";
            }
            if(bundle.containsKey("get_position"))
            {
                sGetPosition = bundle.getString("get_position");
                try
                {
                    nSelectGetPotionID = LastOrderInfo.mPaymentInfo.getPayMentType(3).getInt("IdPickSite");
                }
                catch(JSONException jsonexception1)
                {
                    jsonexception1.printStackTrace();
                }
            } else
            {
                sGetPosition = " ";
            }
            view_type = 3;
        } else
        if(bundle.getInt("type") == 2)
        {
            if(bundle.containsKey("pay_type"))
            {
                sPaymentType = bundle.getString("pay_type");
                nSelectedPaymentTypeID = LastOrderInfo.mPaymentInfo.nSelected;
            } else
            {
                sPaymentType = " ";
            }
            if(bundle.containsKey("send_type"))
                sSendType = bundle.getString("send_type");
            else
                sSendType = " ";
            if(bundle.containsKey("pay_content"))
                sPayContentType = bundle.getString("pay_content");
            else
                sPayContentType = " ";
            if(bundle.containsKey("send_time"))
                sSendTime = bundle.getString("send_time");
            else
                sSendTime = " ";
            if(bundle.containsKey("phone_confirm"))
                sConfirm = bundle.getString("phone_confirm");
            else
                sConfirm = " ";
            view_type = 2;
        }
        if(true) goto _L4; else goto _L3
_L3:
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
            break MISSING_BLOCK_LABEL_278;
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

    private void getCartItemInfo2()
    {
        ArrayList arraylist;
        JSONArray jsonarray;
        new ArrayList();
        arraylist = (new DBHelperUtil(this)).getCartList();
        jbCartStr = new JSONObject();
        jsonarray = new JSONArray();
        if(!arraylist.isEmpty() && arraylist != null) goto _L2; else goto _L1
_L1:
        arraylist.clear();
_L4:
        return;
_L2:
        int i = 0;
_L5:
label0:
        {
            if(i < arraylist.size())
                break label0;
            JSONObject jsonobject;
            JSONException jsonexception;
            try
            {
                jbCartStr.put("TheSkus", jsonarray);
            }
            catch(JSONException jsonexception1)
            {
                jsonexception1.printStackTrace();
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
        jsonobject = new JSONObject();
        try
        {
            jsonobject.put("Id", String.valueOf(((CartTable)arraylist.get(i)).productCode));
            jsonobject.put("num", String.valueOf(((CartTable)arraylist.get(i)).buyCount));
            jsonarray.put(i, jsonobject);
        }
        // Misplaced declaration of an exception variable
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        i++;
          goto _L5
    }

    private void getLocation()
    {
        LocationManager locationmanager = (LocationManager)getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(1);
        String s = locationmanager.getBestProvider(criteria, true);
        if(s == null)
            s = "network";
        mostRecentLocation = locationmanager.getLastKnownLocation(s);
    }

    private void getPaymentTypes()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            try
            {
                s = "easyBuyPaymentType";
                jbBody = new JSONObject();
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "paymentType";
            updatCcomositeBody();
        }
        setUpConnAndGetData(s, jbBody, "paymentType");
    }

    private void getPositions()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            try
            {
                s = "easyBuyPickSites";
                jbBody = new JSONObject();
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "pickSites";
            updatCcomositeBody();
        }
        setUpConnAndGetData(s, jbBody, "pickSites");
    }

    private void getSendTypes()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            try
            {
                s = "easyBuyShipmentTypes";
                jbBody = new JSONObject();
                jbBody.put("OrderStr", jbOrderStr);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            s = "shipmentTypes";
            updatCcomositeBody();
        }
        setUpConnAndGetData(s, jbBody, "shipmentTypes");
    }

    private void handleClick()
    {
        mPayType.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mPayType2.performClick();
            }

            final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
        mPayType2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createPaymentTypeAlertDiglog("\u9009\u62E9\u652F\u4ED8\u65B9\u5F0F");
            }

            final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
        mConfirm.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                updatePaymentInfo();
                setResult(3);
                finish();
            }

            final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
    }

    private void handleDatas(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        try
        {
            if(s.compareTo("shipmentTypes") == 0)
            {
                jbShipments = httpresponse.getJSONObject().getJSONObject("shipmentTypesInfo");
                if(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(0).get("SupCodTime") != null)
                    jbShipments.getJSONArray("ShipmentTypes").getJSONObject(0).get("SupCodTime").toString();
                if(nSelectedPaymentTypeID == 1)
                    updatePayAfterReceiveView();
                else
                if(nSelectedPaymentTypeID == 2 || nSelectedPaymentTypeID == 4)
                    updatePayPostView();
                break MISSING_BLOCK_LABEL_204;
            }
        }
        catch(JSONException jsonexception)
        {
            if(Log.D)
                Log.d(TAG, jsonexception.getMessage());
            break MISSING_BLOCK_LABEL_204;
        }
        if(s.compareTo("pickSites") == 0)
        {
            jbSendTypes = httpresponse.getJSONObject().getJSONObject("pickSitesInfo");
            updateSelfView();
        } else
        if(s.compareTo("paymentType") == 0)
        {
            bFill = true;
            jbPaymentTypes = httpresponse.getJSONObject();
            updatePayments();
            if(LastOrderInfo.mPaymentInfo.nSelected == 3)
                getPositions();
            else
                getSendTypes();
        }
    }

    private void initComponent()
    {
        mPayType = (TextView)findViewById(0x7f0c02a0);
        mPayType2 = (ImageButton)findViewById(0x7f0c02a1);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f090128);
        mConfirm = (Button)findViewById(0x7f0c02a3);
    }

    private void initDefaultView()
    {
        RelativeLayout relativelayout;
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        (RelativeLayout)findViewById(0x7f0c02a2);
        relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f03006e, null).findViewById(0x7f0c021e);
        JSONArray jsonarray;
        int i;
        jsonarray = jbPaymentTypes.getJSONArray("paymentInfo");
        i = 0;
_L3:
        if(i < jsonarray.length()) goto _L2; else goto _L1
_L1:
        ((TextView)relativelayout.getChildAt(1)).setText(sSendType);
        ((TextView)relativelayout.getChildAt(4)).setText(sSendTime);
        RadioGroup radiogroup = (RadioGroup)relativelayout.getChildAt(7);
        RadioButton radiobutton = (RadioButton)radiogroup.getChildAt(0);
        if(sConfirm.contains("\u662F") || sConfirm.contains("yes") || sConfirm.contains("Yes"))
            radiobutton.setChecked(true);
        RadioButton radiobutton1 = (RadioButton)radiogroup.getChildAt(1);
        if(sConfirm.contains("\u5426") || sConfirm.contains("no") || sConfirm.contains("No"))
            radiobutton1.setChecked(true);
        ((TextView)relativelayout.getChildAt(9)).setText(sPayContentType);
_L4:
        nSelectedPaymentType = 1;
        return;
_L2:
        if(jsonarray.getJSONObject(i).getInt("Id") == 1 && jsonarray.getJSONObject(i).getInt("Id") == 4)
            mPayType.setText(jsonarray.getJSONObject(i).getString("Name"));
        i++;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L4
    }

    private void initSelID()
    {
        if(LastOrderInfo.mPaymentInfo.type < 5 && LastOrderInfo.mPaymentInfo.type > 0)
            nSelectedPaymentTypeID = LastOrderInfo.mPaymentInfo.nSelected;
        else
            nSelectedPaymentTypeID = 1;
        if(LastOrderInfo.mPaymentInfo != null && LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected) != null && LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).toString().contains("PaymentWay"))
            try
            {
                nPayMonyTypesID = LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).getInt("PaymentWay");
            }
            catch(JSONException jsonexception1)
            {
                jsonexception1.printStackTrace();
            }
        if(LastOrderInfo.mPaymentInfo == null || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected) == null || !LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).toString().contains("CODTime"))
            break MISSING_BLOCK_LABEL_163;
        nselectedSendTimeID = LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.nSelected).getInt("CODTime");
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void setDefaultView()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
        RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006e, null).findViewById(0x7f0c021e);
        RadioGroup radiogroup;
        if(sPaymentType != null && sPaymentType != " " && sPaymentType != "" && sPaymentType.length() > 0)
            mPayType.setText(sPaymentType.trim());
        else
            mPayType.setText(sPaymentType);
        mSendType = (TextView)relativelayout1.getChildAt(1);
        mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
        mSendType.setText(sSendType);
        mSendTime = (TextView)relativelayout1.getChildAt(4);
        mSendTime.setText(sSendTime);
        radiogroup = (RadioGroup)relativelayout1.getChildAt(7);
        mPhoneConfirmYes = (RadioButton)radiogroup.getChildAt(0);
        mPhoneConfirmNo = (RadioButton)radiogroup.getChildAt(1);
        if(sConfirm.contains("\u662F") || sConfirm.contains("yes") || sConfirm.contains("Yes"))
            mPhoneConfirmYes.setChecked(true);
        else
        if(sConfirm.contains("\u5426") || sConfirm.contains("no") || sConfirm.contains("No"))
            mPhoneConfirmNo.setChecked(true);
        else
            mPhoneConfirmYes.setChecked(true);
        mPayContentType = (TextView)relativelayout1.getChildAt(9);
        mPayContentType.setText(sPayContentType);
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
        nSelectedPaymentType = 1;
        oldSelectPaymentType = 1;
    }

    private void setNewPaymentType(int i)
    {
        if(items[i].compareTo(jbPaymentTypes.getJSONArray("paymentInfo").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_275;
        nSelectedPaymentTypeID = jbPaymentTypes.getJSONArray("paymentInfo").getJSONObject(i).getInt("Id");
_L7:
        if(items[i] == null || items[i] == " " || items[i].length() <= 0) goto _L2; else goto _L1
_L1:
        mPayType.setText(items[i].trim());
_L11:
        jbOrderStr.put("IdPaymentType", nSelectedPaymentTypeID);
        bNew = true;
        if(nSelectedPaymentTypeID != 1 && nSelectedPaymentTypeID != 4 && nSelectedPaymentTypeID != 2) goto _L4; else goto _L3
_L3:
        getSendTypes();
          goto _L5
_L10:
        int j;
        if(j >= items.length) goto _L7; else goto _L6
_L6:
        if(items[i].compareTo(jbPaymentTypes.getJSONArray("paymentInfo").getJSONObject(j).getString("Name")) != 0) goto _L9; else goto _L8
_L8:
        nSelectedPaymentTypeID = jbPaymentTypes.getJSONArray("paymentInfo").getJSONObject(j).getInt("Id");
          goto _L7
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L5
_L9:
        j++;
          goto _L10
_L2:
        mPayType.setText(items[i]);
          goto _L11
_L4:
        if(nSelectedPaymentTypeID == 3)
            getPositions();
_L5:
        return;
        j = 0;
          goto _L10
    }

    private void setNewSendTime(int i)
    {
        if(items[i].compareTo(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_231;
        nselectedSendTimeID = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime").getJSONObject(i).getInt("Id");
_L3:
        mSendTime.setText(items[i]);
        jbOrderStr.put("CODTime ", nselectedSendTimeID);
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nselectedSendTimeID = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime").getJSONObject(j).getInt("Id");
          goto _L3
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L1
_L5:
        j++;
          goto _L6
_L1:
        return;
        j = 0;
          goto _L6
    }

    private void setNewSendType(int i)
    {
        if(nSelectedPaymentTypeID != 3) goto _L2; else goto _L1
_L1:
        if(items[i].compareTo(jbSendTypes.getJSONArray("PickSites").getJSONObject(i).getString("Name")) != 0) goto _L4; else goto _L3
_L3:
        nSelectGetPotionID = jbSendTypes.getJSONArray("PickSites").getJSONObject(i).getInt("Id");
_L7:
        mGetPosition.setText(items[i]);
        jbOrderStr.put("IdPickSite", nSelectGetPotionID);
_L11:
        bNew = true;
        if(nSelectedPaymentTypeID == 1 || nSelectedPaymentTypeID == 4 || nSelectedPaymentTypeID == 2)
            getSendTypes();
          goto _L5
_L10:
        int k;
        if(k >= items.length) goto _L7; else goto _L6
_L6:
        if(items[i].compareTo(jbSendTypes.getJSONArray("PickSites").getJSONObject(k).getString("Name")) != 0) goto _L9; else goto _L8
_L8:
        nSelectGetPotionID = jbSendTypes.getJSONArray("PickSites").getJSONObject(k).getInt("Id");
          goto _L7
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L5
_L9:
        k++;
          goto _L10
_L2:
        if(i > jbShipments.getJSONArray("ShipmentTypes").length() || items[i].compareTo(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_402;
        nSelectedSendTypesID = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i).getInt("Id");
_L13:
        mSendType.setText(items[i]);
        jbOrderStr.put("IdShipmentType", nSelectedSendTypesID);
          goto _L11
_L16:
        int j;
        if(j >= items.length) goto _L13; else goto _L12
_L12:
        if(items[i].compareTo(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(j).getString("Name")) != 0) goto _L15; else goto _L14
_L14:
        nSelectedSendTypesID = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i).getInt("Id");
          goto _L13
_L15:
        j++;
          goto _L16
_L5:
        return;
_L4:
        k = 0;
          goto _L10
        j = 0;
          goto _L16
    }

    private void setPayAfterReceiveView()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
        RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006e, null).findViewById(0x7f0c021e);
        mPayType.setText("");
        if(sPaymentType != null && sPaymentType != " " && sPaymentType != "" && sPaymentType.length() > 0)
            mPayType.setText(sPaymentType.trim());
        else
            mPayType.setText(sPaymentType);
        mSendType = (TextView)relativelayout1.getChildAt(1);
        mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
        if(mSendType != null)
        {
            mSendType.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(!bFill)
                        getPaymentTypes();
                    else
                        mSendType2.performClick();
                }

                final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
            }
);
            mSendType2.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(!bFill)
                        getPaymentTypes();
                    createSendTypeAlertDiglog("\u9009\u62E9\u914D\u9001\u65B9\u5F0F");
                }

                final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
            }
);
        }
        mSendType.setText(sSendType);
        if(!sSendType.contains("\u5FEB\u9012\u8FD0\u8F93"))
        {
            mSendTime = (TextView)relativelayout1.getChildAt(4);
            mSendTime.setText(sSendTime);
            mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
            if(mSendTime != null)
            {
                mSendTime.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        mSendTime2.performClick();
                    }

                    final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
                }
);
                mSendTime2.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        createSendTimeAlertDiglog("\u9009\u62E9\u9001\u8D27\u65F6\u95F4");
                    }

                    final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
                }
);
            }
            RadioGroup radiogroup = (RadioGroup)relativelayout1.getChildAt(7);
            mPhoneConfirmYes = (RadioButton)radiogroup.getChildAt(0);
            if(sConfirm.contains("\u662F") || sConfirm.contains("yes") || sConfirm.contains("Yes"))
                mPhoneConfirmYes.setChecked(true);
            mPhoneConfirmNo = (RadioButton)radiogroup.getChildAt(1);
            if(sConfirm.contains("\u5426") || sConfirm.contains("no") || sConfirm.contains("No"))
                mPhoneConfirmNo.setChecked(true);
            mPayContentType = (TextView)relativelayout1.getChildAt(9);
            mPayContentType2 = (ImageButton)relativelayout1.getChildAt(10);
            if(mPayContentType != null)
            {
                mPayContentType.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        mPayContentType2.performClick();
                    }

                    final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
                }
);
                mPayContentType2.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        createpayMoneyContentAlertDiglog("\u9009\u62E9\u652F\u4ED8\u5F62\u5F0F");
                    }

                    final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
                }
);
            }
            mPayContentType.setText(sPayContentType);
        } else
        {
            mSendTime = (TextView)relativelayout1.getChildAt(4);
            mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
            mSendTime.setVisibility(8);
            mSendTime2.setVisibility(8);
            ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
            ((TextView)relativelayout1.getChildAt(3)).setVisibility(8);
            ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
        }
        nSelectedPaymentTypeID = 1;
        oldSelectPaymentType = 1;
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
    }

    private void setPayMoneyContent(int i)
    {
        if(items[i].compareTo(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_231;
        nPayMonyTypesID = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay").getJSONObject(i).getInt("Id");
_L3:
        mPayContentType.setText(items[i]);
        jbOrderStr.put("PaymentWay ", nPayMonyTypesID);
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nPayMonyTypesID = jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay").getJSONObject(j).getInt("Id");
          goto _L3
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L1
_L5:
        j++;
          goto _L6
_L1:
        return;
        j = 0;
          goto _L6
    }

    private void setPayPostView()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = null;
        RelativeLayout relativelayout1;
        try
        {
            relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006f, null).findViewById(0x7f0c022a);
        mPayType.setText("");
        if(sPaymentType != null && sPaymentType != " " && sPaymentType != "" && sPaymentType.length() > 0)
            mPayType.setText(sPaymentType.trim());
        else
            mPayType.setText(sPaymentType);
        mSendType = (TextView)relativelayout1.getChildAt(1);
        mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
        if(mSendType != null)
        {
            mSendType.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(bFill)
                        mSendType2.performClick();
                    else
                        getPaymentTypes();
                }

                final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
            }
);
            mSendType2.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(!bFill)
                        getPaymentTypes();
                    createSendTypeAlertDiglog("\u9009\u62E9\u914D\u9001\u65B9\u5F0F");
                }

                final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
            }
);
        }
        mSendType.setText(sSendType);
        if(!sSendType.contains("\u5FEB\u9012\u8FD0\u8F93"))
        {
            mSendTime = (TextView)relativelayout1.getChildAt(4);
            mSendTime.setText(sSendTime);
            mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
            if(mSendTime != null)
            {
                mSendTime.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        mSendTime2.performClick();
                    }

                    final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
                }
);
                mSendTime2.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        createSendTimeAlertDiglog("\u9009\u62E9\u9001\u8D27\u65F6\u95F4");
                    }

                    final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
                }
);
            }
            RadioGroup radiogroup = (RadioGroup)relativelayout1.getChildAt(7);
            mPhoneConfirmYes = (RadioButton)radiogroup.getChildAt(0);
            if(sConfirm.contains("\u662F") || sConfirm.contains("yes") || sConfirm.contains("Yes"))
                mPhoneConfirmYes.setChecked(true);
            mPhoneConfirmNo = (RadioButton)radiogroup.getChildAt(1);
            if(sConfirm.contains("\u5426") || sConfirm.contains("no") || sConfirm.contains("No"))
                mPhoneConfirmNo.setChecked(true);
            nSelectedPaymentTypeID = 2;
            oldSelectPaymentType = 2;
        } else
        {
            mSendTime = (TextView)relativelayout1.getChildAt(4);
            mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
            mSendTime.setVisibility(8);
            mSendTime2.setVisibility(8);
            ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
            ((TextView)relativelayout1.getChildAt(3)).setVisibility(8);
            ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
        }
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
    }

    private void setSelfView()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
        RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006d, null).findViewById(0x7f0c0219);
        mPayType.setText(sPaymentType.trim());
        mGetPosition = (TextView)relativelayout1.getChildAt(1);
        mGetPosition2 = (ImageButton)relativelayout1.getChildAt(2);
        if(mGetPosition != null)
        {
            mGetPosition.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(!bFill)
                        getPaymentTypes();
                    else
                        mGetPosition2.performClick();
                }

                final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
            }
);
            mGetPosition2.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(!bFill)
                        getPaymentTypes();
                    if(nSelectedPaymentTypeID == 3)
                        createSendTypeAlertDiglog("\u9009\u62E9\u81EA\u63D0\u70B9");
                }

                final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
            }
);
        }
        mGetPosition.setText(sGetPosition);
        nSelectedPaymentTypeID = 3;
        oldSelectPaymentType = 3;
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
    }

    private void setUpConnAndGetData(String s, JSONObject jsonobject, final String action)
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("funcID")).append(s).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("param")).append(jsonobject.toString()).toString());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(s);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                handleDatas(httpresponse, action);
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

            final SelectPaymentType this$0;
            private final String val$action;

            
            {
                this$0 = SelectPaymentType.this;
                action = s;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void updatePayAfterReceiveView()
    {
        post(new Runnable() {

            public void run()
            {
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                LayoutInflater layoutinflater = LayoutInflater.from(SelectPaymentType.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006e, null).findViewById(0x7f0c021e);
                JSONObject jsonobject = new JSONObject();
                if(SelectPaymentType.jbShipments == null || SelectPaymentType.jbShipments.get("ShipmentTypes") == null || SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes") == null || SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").toString() == "null" || SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").length() <= 1) goto _L2; else goto _L1
_L1:
                if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy) goto _L4; else goto _L3
_L12:
                int l1 = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").length();
                int k1;
                if(k1 < l1) goto _L6; else goto _L5
_L5:
                if(jsonobject != null && jsonobject.getString("Name") != null && jsonobject.getString("Name").length() >= 1) goto _L8; else goto _L7
_L7:
                noShipmentInfo = true;
                ((TextView)relativelayout1.getChildAt(0)).setVisibility(8);
                mSendType = (TextView)relativelayout1.getChildAt(1);
                mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
                mSendType.setVisibility(8);
                mSendType2.setVisibility(8);
                mSendTime = (TextView)relativelayout1.getChildAt(4);
                mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
                mSendTime.setVisibility(8);
                mSendTime2.setVisibility(8);
                ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
                ((TextView)relativelayout1.getChildAt(3)).setVisibility(8);
                ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
                  goto _L9
_L6:
                if(SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(k1).getInt("Id") != nSelectedSendTypesID) goto _L11; else goto _L10
_L10:
                jsonobject = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(k1);
                nSelectedSendTypes = k1;
                  goto _L5
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
_L19:
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                  goto _L9
_L11:
                k1++;
                  goto _L12
_L4:
                if(LastOrderInfo.mPaymentInfo.getSelectedPaymentType(LastOrderInfo.mPaymentInfo.nSelected) == null && !LastOrderInfo.mPaymentInfo.getSelectedPaymentType(LastOrderInfo.mPaymentInfo.nSelected).toString().contains("IdShipmentType")) goto _L5; else goto _L13
_L30:
                int j2 = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").length();
                int i2;
                if(i2 >= j2) goto _L5; else goto _L14
_L14:
                if(SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i2).getInt("Id") != nSelectedSendTypesID) goto _L16; else goto _L15
_L15:
                jsonobject = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i2);
                nSelectedSendTypes = i2;
                  goto _L5
_L2:
                jsonobject = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(0);
                nSelectedSendTypesID = jsonobject.getInt("Id");
                  goto _L5
_L8:
                mSendType = (TextView)relativelayout1.getChildAt(1);
                mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
                if(mSendType != null)
                {
                    TextView textview2 = mSendType;
                    android.view.View.OnClickListener onclicklistener4 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mSendType2.performClick();
                        }

                        final _cls26 this$1;

                    
                    {
                        this$1 = _cls26.this;
                        super();
                    }
                    }
;
                    textview2.setOnClickListener(onclicklistener4);
                    ImageButton imagebutton2 = mSendType2;
                    android.view.View.OnClickListener onclicklistener5 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            createSendTypeAlertDiglog("\u9009\u62E9\u914D\u9001\u65B9\u5F0F");
                        }

                        final _cls26 this$1;

                    
                    {
                        this$1 = _cls26.this;
                        super();
                    }
                    }
;
                    imagebutton2.setOnClickListener(onclicklistener5);
                }
                mSendType.setText(jsonobject.getString("Name"));
                if(jsonobject.get("SupCodTime") != null && jsonobject.get("SupCodTime").toString() != "null" && jsonobject.getJSONArray("SupCodTime") != null && jsonobject.getJSONArray("SupCodTime").toString() != "null") goto _L18; else goto _L17
_L17:
                noShipmentInfo = true;
                ((TextView)relativelayout1.getChildAt(3)).setVisibility(8);
                mSendTime = (TextView)relativelayout1.getChildAt(4);
                mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
                RadioGroup radiogroup = (RadioGroup)relativelayout1.getChildAt(7);
                ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
                mSendTime.setVisibility(8);
                mSendTime2.setVisibility(8);
                radiogroup.setVisibility(8);
                ((TextView)relativelayout1.getChildAt(8)).setVisibility(8);
                mPayContentType = (TextView)relativelayout1.getChildAt(9);
                mPayContentType2 = (ImageButton)relativelayout1.getChildAt(10);
                mPayContentType.setVisibility(8);
                mPayContentType2.setVisibility(8);
                  goto _L19
_L18:
                mSendTime = (TextView)relativelayout1.getChildAt(4);
                mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
                if(mSendTime != null)
                {
                    TextView textview1 = mSendTime;
                    android.view.View.OnClickListener onclicklistener2 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mSendTime2.performClick();
                        }

                        final _cls26 this$1;

                    
                    {
                        this$1 = _cls26.this;
                        super();
                    }
                    }
;
                    textview1.setOnClickListener(onclicklistener2);
                    ImageButton imagebutton1 = mSendTime2;
                    android.view.View.OnClickListener onclicklistener3 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            createSendTimeAlertDiglog("\u9009\u62E9\u9001\u8D27\u65F6\u95F4");
                        }

                        final _cls26 this$1;

                    
                    {
                        this$1 = _cls26.this;
                        super();
                    }
                    }
;
                    imagebutton1.setOnClickListener(onclicklistener3);
                }
                if(jsonobject == null || jsonobject.getJSONArray("SupCodTime") == null || jsonobject.getJSONArray("SupCodTime").toString() == "null" || jsonobject.getJSONArray("SupCodTime").length() <= 1) goto _L21; else goto _L20
_L20:
                int k = 0;
_L31:
                int l = jsonobject.getJSONArray("SupCodTime").length();
                if(k < l) goto _L23; else goto _L22
_L22:
                if(jsonobject.get("SupInforms") == null || jsonobject.get("SupInforms").toString() == "null")
                {
                    ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
                    ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
                } else
                {
                    JSONArray jsonarray = jsonobject.getJSONArray("SupInforms");
                    final RadioGroup rg = (RadioGroup)relativelayout1.getChildAt(7);
                    mPhoneConfirmYes = (RadioButton)rg.getChildAt(0);
                    mPhoneConfirmNo = (RadioButton)rg.getChildAt(1);
                    mPhoneConfirmNo.setChecked(true);
                    if(!jsonarray.getJSONObject(0).getBoolean("CanSelected") && !jsonarray.getJSONObject(1).getBoolean("CanSelected"))
                        rg.setVisibility(8);
                    android.widget.RadioGroup.OnCheckedChangeListener oncheckedchangelistener;
                    if(!jsonarray.getJSONObject(0).getBoolean("CanSelected"))
                        if(jsonarray.getJSONObject(0).getInt("Id") == 1)
                            mPhoneConfirmYes.setVisibility(8);
                        else
                        if(jsonarray.getJSONObject(0).getInt("Id") == 0)
                            mPhoneConfirmNo.setVisibility(8);
                    if(!jsonarray.getJSONObject(1).getBoolean("CanSelected"))
                        if(jsonarray.getJSONObject(1).getInt("Id") == 1)
                            mPhoneConfirmYes.setVisibility(8);
                        else
                        if(jsonarray.getJSONObject(1).getInt("Id") == 0)
                            mPhoneConfirmNo.setVisibility(8);
                    if(phone)
                        mPhoneConfirmYes.setChecked(true);
                    else
                        mPhoneConfirmNo.setChecked(true);
                    oncheckedchangelistener = new android.widget.RadioGroup.OnCheckedChangeListener() {

                        public void onCheckedChanged(RadioGroup radiogroup1, int k2)
                        {
                            if(k2 != rg.getChildAt(0).getId()) goto _L2; else goto _L1
_L1:
                            phone = true;
_L4:
                            return;
_L2:
                            if(k2 == rg.getChildAt(1).getId())
                                phone = false;
                            if(true) goto _L4; else goto _L3
_L3:
                        }

                        final _cls26 this$1;
                        private final RadioGroup val$rg;

                    
                    {
                        this$1 = _cls26.this;
                        rg = radiogroup;
                        super();
                    }
                    }
;
                    rg.setOnCheckedChangeListener(oncheckedchangelistener);
                }
                if(jsonobject.get("SupPaymentWay") != null && jsonobject.get("SupPaymentWay").toString() != "null") goto _L25; else goto _L24
_L24:
                ((TextView)relativelayout1.getChildAt(8)).setVisibility(8);
                mPayContentType = (TextView)relativelayout1.getChildAt(9);
                mPayContentType2 = (ImageButton)relativelayout1.getChildAt(10);
                mPayContentType.setVisibility(8);
                mPayContentType2.setVisibility(8);
                  goto _L19
_L23:
                int i1 = jsonobject.getJSONArray("SupCodTime").getJSONObject(k).getInt("Id");
                int j1;
                if(nselectedSendTimeID == 0)
                    j1 = 1;
                else
                    j1 = nselectedSendTimeID;
                if(i1 != j1) goto _L27; else goto _L26
_L26:
                nselectedSendTime = k;
                nselectedSendTimeID = jsonobject.getJSONArray("SupCodTime").getJSONObject(k).getInt("Id");
                mSendTime.setText(jsonobject.getJSONArray("SupCodTime").getJSONObject(nselectedSendTime).getString("Name"));
                  goto _L22
_L21:
                nselectedSendTime = 0;
                nselectedSendTimeID = jsonobject.getJSONArray("SupCodTime").getJSONObject(0).getInt("Id");
                mSendTime.setText(jsonobject.getJSONArray("SupCodTime").getJSONObject(nselectedSendTime).getString("Name"));
                  goto _L22
_L25:
                jsonobject.getJSONArray("SupPaymentWay");
                mPayContentType = (TextView)relativelayout1.getChildAt(9);
                mPayContentType2 = (ImageButton)relativelayout1.getChildAt(10);
                if(mPayContentType != null)
                {
                    TextView textview = mPayContentType;
                    android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mPayContentType2.performClick();
                        }

                        final _cls26 this$1;

                    
                    {
                        this$1 = _cls26.this;
                        super();
                    }
                    }
;
                    textview.setOnClickListener(onclicklistener);
                    ImageButton imagebutton = mPayContentType2;
                    android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            createpayMoneyContentAlertDiglog("\u9009\u62E9\u652F\u4ED8\u5F62\u5F0F");
                        }

                        final _cls26 this$1;

                    
                    {
                        this$1 = _cls26.this;
                        super();
                    }
                    }
;
                    imagebutton.setOnClickListener(onclicklistener1);
                }
                if(jsonobject == null || jsonobject.getJSONArray("SupPaymentWay") == null || jsonobject.getJSONArray("SupPaymentWay").toString() == "null" || jsonobject.getJSONArray("SupPaymentWay").length() <= 1) goto _L29; else goto _L28
_L28:
                int i = 0;
_L32:
                int j = jsonobject.getJSONArray("SupPaymentWay").length();
                if(i < j)
                {
                    if(jsonobject.getJSONArray("SupPaymentWay").getJSONObject(i).getInt("Id") != nPayMonyTypesID)
                        break MISSING_BLOCK_LABEL_2067;
                    nPayMonyTypes = i;
                    nPayMonyTypesID = jsonobject.getJSONArray("SupPaymentWay").getJSONObject(i).getInt("Id");
                    mPayContentType.setText(jsonobject.getJSONArray("SupPaymentWay").getJSONObject(nPayMonyTypes).getString("Name"));
                }
                  goto _L19
_L29:
                nPayMonyTypes = 0;
                nPayMonyTypesID = jsonobject.getJSONArray("SupPaymentWay").getJSONObject(0).getInt("Id");
                mPayContentType.setText(jsonobject.getJSONArray("SupPaymentWay").getJSONObject(nPayMonyTypes).getString("Name"));
                  goto _L19
_L3:
                k1 = 0;
                  goto _L12
_L9:
                return;
_L13:
                i2 = 0;
                  goto _L30
_L16:
                i2++;
                  goto _L30
_L27:
                k++;
                  goto _L31
                i++;
                  goto _L32
            }

            final SelectPaymentType this$0;


            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
    }

    private void updatePayPostView()
    {
        post(new Runnable() {

            public void run()
            {
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                JSONObject jsonobject;
                int l;
                LayoutInflater layoutinflater = LayoutInflater.from(SelectPaymentType.this);
                relativelayout = null;
                try
                {
                    relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006f, null).findViewById(0x7f0c022a);
                jsonobject = new JSONObject();
                if(SelectPaymentType.jbShipments == null || SelectPaymentType.jbShipments.get("ShipmentTypes").toString() == "null" || SelectPaymentType.jbShipments.get("ShipmentTypes") == null || SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes") == null || SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").toString() == "null" || SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").length() <= 1) goto _L2; else goto _L1
_L1:
                if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy) goto _L4; else goto _L3
_L12:
                if(l < SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").length()) goto _L6; else goto _L5
_L5:
                if(jsonobject != null && jsonobject.getString("Name") != "" && jsonobject.getString("Name") != " " && jsonobject.getString("Name") != null && jsonobject.getString("Name").length() >= 1) goto _L8; else goto _L7
_L7:
                noShipmentInfo = true;
                ((TextView)relativelayout1.getChildAt(0)).setVisibility(8);
                mSendType = (TextView)relativelayout1.getChildAt(1);
                mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
                mSendType.setVisibility(8);
                mSendType2.setVisibility(8);
                mSendTime = (TextView)relativelayout1.getChildAt(4);
                mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
                mSendTime.setVisibility(8);
                mSendTime2.setVisibility(8);
                ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
                ((TextView)relativelayout1.getChildAt(3)).setVisibility(8);
                ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
_L11:
                return;
_L6:
                if(SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(l).getInt("Id") != nSelectedSendTypesID) goto _L10; else goto _L9
_L9:
                jsonobject = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(l);
                nSelectedSendTypes = l;
                  goto _L5
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
_L19:
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                  goto _L11
_L10:
                l++;
                  goto _L12
_L4:
                if(LastOrderInfo.mPaymentInfo.getSelectedPaymentType(LastOrderInfo.mPaymentInfo.nSelected) == null && !LastOrderInfo.mPaymentInfo.getSelectedPaymentType(LastOrderInfo.mPaymentInfo.nSelected).toString().contains("IdShipmentType")) goto _L5; else goto _L13
_L23:
                int i1;
                if(i1 >= SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").length()) goto _L5; else goto _L14
_L14:
                if(SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i1).getInt("Id") != nSelectedSendTypesID) goto _L16; else goto _L15
_L15:
                jsonobject = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(i1);
                nSelectedSendTypes = i1;
                  goto _L5
_L2:
                jsonobject = SelectPaymentType.jbShipments.getJSONArray("ShipmentTypes").getJSONObject(0);
                nSelectedSendTypesID = jsonobject.getInt("Id");
                  goto _L5
_L8:
                mSendType = (TextView)relativelayout1.getChildAt(1);
                mSendType2 = (ImageButton)relativelayout1.getChildAt(2);
                if(mSendType != null)
                {
                    TextView textview1 = mSendType;
                    android.view.View.OnClickListener onclicklistener2 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mSendType2.performClick();
                        }

                        final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                    }
;
                    textview1.setOnClickListener(onclicklistener2);
                    ImageButton imagebutton1 = mSendType2;
                    android.view.View.OnClickListener onclicklistener3 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            createSendTypeAlertDiglog("\u9009\u62E9\u914D\u9001\u65B9\u5F0F");
                        }

                        final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                    }
;
                    imagebutton1.setOnClickListener(onclicklistener3);
                }
                mSendType.setText(jsonobject.getString("Name"));
                if(jsonobject.get("SupCodTime") != null && jsonobject.get("SupCodTime").toString() != "null" && jsonobject.get("SupCodTime").toString() != "null" && jsonobject.getJSONArray("SupCodTime") != null && jsonobject.getJSONArray("SupCodTime").toString() != "null") goto _L18; else goto _L17
_L17:
                noShipmentInfo = true;
                mSendTime = (TextView)relativelayout1.getChildAt(4);
                mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
                mSendTime.setVisibility(8);
                mSendTime2.setVisibility(8);
                ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
                ((TextView)relativelayout1.getChildAt(3)).setVisibility(8);
                ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
                  goto _L19
_L18:
                mSendTime = (TextView)relativelayout1.getChildAt(4);
                mSendTime2 = (ImageButton)relativelayout1.getChildAt(5);
                if(mSendTime != null)
                {
                    TextView textview = mSendTime;
                    android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mSendTime2.performClick();
                        }

                        final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                    }
;
                    textview.setOnClickListener(onclicklistener);
                    ImageButton imagebutton = mSendTime2;
                    android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            createSendTimeAlertDiglog("\u9009\u62E9\u9001\u8D27\u65F6\u95F4");
                        }

                        final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                    }
;
                    imagebutton.setOnClickListener(onclicklistener1);
                }
                if(jsonobject == null || jsonobject.getJSONArray("SupCodTime") == null || jsonobject.getJSONArray("SupCodTime").toString() == "null" || jsonobject.getJSONArray("SupCodTime").length() <= 1) goto _L21; else goto _L20
_L20:
                int i = 0;
_L24:
                if(i < jsonobject.getJSONArray("SupCodTime").length())
                {
                    int j = jsonobject.getJSONArray("SupCodTime").getJSONObject(i).getInt("Id");
                    int k;
                    if(nselectedSendTimeID == 0)
                        k = 1;
                    else
                        k = nselectedSendTimeID;
                    if(j != k)
                        break MISSING_BLOCK_LABEL_1635;
                    nselectedSendTime = i;
                    nselectedSendTimeID = jsonobject.getJSONArray("SupCodTime").getJSONObject(i).getInt("Id");
                    mSendTime.setText(jsonobject.getJSONArray("SupCodTime").getJSONObject(nselectedSendTime).getString("Name"));
                }
_L22:
                if(jsonobject.get("SupInforms") == null || jsonobject.get("SupInforms").toString() == "null")
                {
                    ((TextView)relativelayout1.getChildAt(6)).setVisibility(8);
                    ((RadioGroup)relativelayout1.getChildAt(7)).setVisibility(8);
                } else
                {
                    JSONArray jsonarray = jsonobject.getJSONArray("SupInforms");
                    final RadioGroup rg = (RadioGroup)relativelayout1.getChildAt(7);
                    mPhoneConfirmYes = (RadioButton)rg.getChildAt(0);
                    mPhoneConfirmNo = (RadioButton)rg.getChildAt(1);
                    mPhoneConfirmNo.setChecked(true);
                    if(!jsonarray.getJSONObject(0).getBoolean("CanSelected") && !jsonarray.getJSONObject(1).getBoolean("CanSelected"))
                        rg.setVisibility(8);
                    android.widget.RadioGroup.OnCheckedChangeListener oncheckedchangelistener;
                    if(!jsonarray.getJSONObject(0).getBoolean("CanSelected"))
                        if(jsonarray.getJSONObject(0).getInt("Id") == 1)
                            mPhoneConfirmYes.setVisibility(8);
                        else
                        if(jsonarray.getJSONObject(0).getInt("Id") == 0)
                            mPhoneConfirmNo.setVisibility(8);
                    if(!jsonarray.getJSONObject(1).getBoolean("CanSelected"))
                        if(jsonarray.getJSONObject(1).getInt("Id") == 1)
                            mPhoneConfirmYes.setVisibility(8);
                        else
                        if(jsonarray.getJSONObject(1).getInt("Id") == 0)
                            mPhoneConfirmNo.setVisibility(8);
                    if(phone)
                        mPhoneConfirmYes.setChecked(true);
                    else
                        mPhoneConfirmNo.setChecked(true);
                    oncheckedchangelistener = new android.widget.RadioGroup.OnCheckedChangeListener() {

                        public void onCheckedChanged(RadioGroup radiogroup, int j1)
                        {
                            if(j1 != rg.getChildAt(0).getId()) goto _L2; else goto _L1
_L1:
                            phone = true;
_L4:
                            return;
_L2:
                            if(j1 == rg.getChildAt(1).getId())
                                phone = false;
                            if(true) goto _L4; else goto _L3
_L3:
                        }

                        final _cls25 this$1;
                        private final RadioGroup val$rg;

                    
                    {
                        this$1 = _cls25.this;
                        rg = radiogroup;
                        super();
                    }
                    }
;
                    rg.setOnCheckedChangeListener(oncheckedchangelistener);
                }
                  goto _L19
_L21:
                nselectedSendTime = 0;
                nselectedSendTimeID = jsonobject.getJSONArray("SupCodTime").getJSONObject(0).getInt("Id");
                mSendTime.setText(jsonobject.getJSONArray("SupCodTime").getJSONObject(nselectedSendTime).getString("Name"));
                  goto _L22
_L3:
                l = 0;
                  goto _L12
_L13:
                i1 = 0;
                  goto _L23
_L16:
                i1++;
                  goto _L23
                i++;
                  goto _L24
            }

            final SelectPaymentType this$0;


            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
    }

    private void updatePayments()
    {
        post(new Runnable() {

            public void run()
            {
                JSONArray jsonarray;
                int i;
                if(SelectPaymentType.jbPaymentTypes == null || SelectPaymentType.jbPaymentTypes.getJSONArray("paymentInfo") == null)
                    break MISSING_BLOCK_LABEL_189;
                jsonarray = SelectPaymentType.jbPaymentTypes.getJSONArray("paymentInfo");
                i = 0;
_L1:
                if(i >= jsonarray.length())
                    break MISSING_BLOCK_LABEL_189;
                if(jsonarray.getJSONObject(i) != null && jsonarray.getJSONObject(i).getInt("Id") == LastOrderInfo.mPaymentInfo.nSelected)
                {
                    JSONException jsonexception;
                    if(jsonarray.getJSONObject(i).getString("Name") != null && jsonarray.getJSONObject(i).getString("Name") != " " && jsonarray.getJSONObject(i).getString("Name").length() > 0)
                    {
                        mPayType.setText(jsonarray.getJSONObject(i).getString("Name").trim());
                        nSelectedPaymentType = i;
                        break MISSING_BLOCK_LABEL_189;
                    }
                    try
                    {
                        mPayType.setText(jsonarray.getJSONObject(i).getString("Name"));
                        nSelectedPaymentType = i;
                    }
                    // Misplaced declaration of an exception variable
                    catch(JSONException jsonexception)
                    {
                        jsonexception.printStackTrace();
                    }
                    break MISSING_BLOCK_LABEL_189;
                }
                i++;
                  goto _L1
            }

            final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
    }

    private void updateSelfView()
    {
        post(new Runnable() {

            public void run()
            {
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                LayoutInflater layoutinflater = LayoutInflater.from(SelectPaymentType.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c02a2);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f03006d, null).findViewById(0x7f0c0219);
                if(SelectPaymentType.jbSendTypes == null || SelectPaymentType.jbSendTypes.toString() == "null" || SelectPaymentType.jbSendTypes.get("PickSites").toString() == "null" || SelectPaymentType.jbSendTypes.get("PickSites") == null || SelectPaymentType.jbSendTypes.getJSONArray("PickSites") == null || SelectPaymentType.jbSendTypes.getJSONArray("PickSites").toString() == "null") goto _L2; else goto _L1
_L1:
                mGetPosition = (TextView)relativelayout1.getChildAt(1);
                mGetPosition2 = (ImageButton)relativelayout1.getChildAt(2);
                if(mGetPosition != null)
                {
                    mGetPosition.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            mGetPosition2.performClick();
                        }

                        final _cls27 this$1;

                    
                    {
                        this$1 = _cls27.this;
                        super();
                    }
                    }
);
                    mGetPosition2.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            if(nSelectedPaymentTypeID == 3)
                                createSendTypeAlertDiglog("\u9009\u62E9\u81EA\u63D0\u70B9");
                        }

                        final _cls27 this$1;

                    
                    {
                        this$1 = _cls27.this;
                        super();
                    }
                    }
);
                }
                if(nSelectGetPotion < 0 || nSelectGetPotion > SelectPaymentType.jbSendTypes.getJSONArray("PickSites").length())
                    nSelectGetPotion = 0;
                mGetPosition.setText(SelectPaymentType.jbSendTypes.getJSONArray("PickSites").getJSONObject(nSelectGetPotion).getString("Name"));
                nSelectGetPotionID = SelectPaymentType.jbSendTypes.getJSONArray("PickSites").getJSONObject(nSelectGetPotion).getInt("Id");
_L4:
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                return;
_L2:
                try
                {
                    noShipmentInfo = true;
                    ((TextView)relativelayout1.getChildAt(0)).setVisibility(8);
                    mGetPosition = (TextView)relativelayout1.getChildAt(1);
                    mGetPosition2 = (ImageButton)relativelayout1.getChildAt(2);
                    mGetPosition.setVisibility(8);
                    mGetPosition2.setVisibility(8);
                }
                catch(JSONException jsonexception)
                {
                    if(Log.D)
                        Log.d(TAG, jsonexception.getMessage());
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final SelectPaymentType this$0;


            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030081);
        jbPaymentTypes = new JSONObject();
        jbSendTypes = new JSONObject();
        jbShipments = new JSONObject();
        getIntent();
        noShipmentInfo = false;
        phone = Contants.bPhone;
        initComponent();
        initSelID();
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            compositeOrderStrByAddEasyBuy();
        } else
        {
            compositeOrderStr();
            getCartItemInfo();
        }
        getPaymentTypes();
        handleClick();
    }

    public void onStart()
    {
        super.onStart();
    }

    void pickSitesSelectedByJs(int i)
    {
        if(Log.D)
            Log.d(TAG, (new StringBuilder("which:")).append(i).toString());
        if(nSelectedPaymentTypeID == 3)
            mGetPosition.setText(items[i]);
        else
            mSendType.setText(items[i]);
        nSelectGetPotion = i;
        bNew = true;
        setNewSendType(i);
        pickDialog.dismiss();
    }

    void setMapButton(android.app.AlertDialog.Builder builder)
    {
        pickSitesLoadingFlag = false;
        mGetPosition.getText().toString();
        View view = LayoutInflater.from(this).inflate(0x7f03008d, null);
        final WebView webView = (WebView)view.findViewById(0x7f0c02dc);
        webView.setVisibility(8);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JavaScriptInterface(null), "android");
        webView.setInitialScale(150);
        final ListView zhitiListView = (ListView)view.findViewById(0x7f0c02de);
        zhitiListView.setAdapter(new ArrayAdapter(this, 0x1090012, 0x1020014, items) {

            public View getView(int i, View view1, ViewGroup viewgroup)
            {
                View view2 = super.getView(i, view1, viewgroup);
                ((TextView)view2.findViewById(0x1020014)).setTextColor(ColorStateList.valueOf(0xff000000));
                return view2;
            }

            final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super(context, i, j, acharsequence);
            }
        }
);
        zhitiListView.setChoiceMode(1);
        zhitiListView.setItemChecked(nSelectGetPotion, true);
        final Button zhituMapBtn = (Button)view.findViewById(0x7f0c02dd);
        zhituMapBtn.setText("\u5730\u56FE");
        zhituMapBtn.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(!pickSitesLoadingFlag)
                {
                    webView.loadUrl((new StringBuilder("javascript:setPickSites(")).append(nSelectGetPotion).append(",").append(android.os.Build.VERSION.SDK_INT).append(")").toString());
                    pickSitesLoadingFlag = true;
                }
                if(webView.getVisibility() == 8)
                {
                    zhitiListView.setVisibility(8);
                    webView.setVisibility(0);
                    zhituMapBtn.setText("\u5217\u8868");
                } else
                {
                    zhitiListView.setVisibility(0);
                    webView.setVisibility(8);
                    zhituMapBtn.setText("\u5730\u56FE");
                }
            }

            final SelectPaymentType this$0;
            private final WebView val$webView;
            private final ListView val$zhitiListView;
            private final Button val$zhituMapBtn;

            
            {
                this$0 = SelectPaymentType.this;
                webView = webview;
                zhitiListView = listview;
                zhituMapBtn = button;
                super();
            }
        }
);
        builder.setView(view);
        AlertDialog alertdialog = builder.create();
        zhitiListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view1, int i, long l)
            {
                pickSitesSelectedByJs(i);
            }

            final SelectPaymentType this$0;

            
            {
                this$0 = SelectPaymentType.this;
                super();
            }
        }
);
        pickDialog = alertdialog;
        alertdialog.show();
        webView.loadUrl("file:///android_asset/map.htm");
    }

    public void updatCcomositeBody()
    {
        jbBody = new JSONObject();
        jbBody.put("OrderStr", jbOrderStr);
        jbBody.put("CartStr", jbCartStr);
        jbBody.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public void updateInvoinceInfo()
    {
    }

    public void updatePaymentInfo()
    {
        JSONObject jsonobject;
label0:
        {
            Contants.mModifiedPaymentInfo = new PaymentInfo();
            jsonobject = new JSONObject();
            jsonobject.put("IdPaymentType", nSelectedPaymentTypeID);
            if(nSelectedPaymentTypeID == 3)
            {
                if(!noShipmentInfo)
                    jsonobject.put("IdPickSite", nSelectGetPotionID);
            } else
            {
                jsonobject.put("IdShipmentType", nSelectedSendTypesID);
                if(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupCodTime") != null && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupCodTime").toString() != "null" && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime") != null && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupCodTime").toString() != "null")
                    jsonobject.put("CODTime", nselectedSendTimeID);
                if(jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupPaymentWay") != null && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).get("SupPaymentWay").toString() != "null" && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay") != null && jbShipments.getJSONArray("ShipmentTypes").getJSONObject(nSelectedSendTypes).getJSONArray("SupPaymentWay").toString() != "null")
                    jsonobject.put("PaymentWay", nPayMonyTypesID);
                if(!phone)
                    break label0;
                jsonobject.put("IsCodInform", true);
            }
        }
_L1:
        Exception exception;
        LastOrderInfo.mPaymentInfo.nSelected = nSelectedPaymentTypeID;
        Contants.mModifiedPaymentInfo.setPayMentType(jsonobject.getInt("IdPaymentType"), jsonobject);
        break MISSING_BLOCK_LABEL_396;
        try
        {
            jsonobject.put("IsCodInform", false);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            if(Log.D)
                Log.d(TAG, exception.getMessage());
            break MISSING_BLOCK_LABEL_396;
        }
          goto _L1
    }

    public void updateUserInfo()
    {
    }

    public void updateYouhuiInfo()
    {
    }

    private static final String MAP_URL = "file:///android_asset/map.htm";
    static JSONObject jbPaymentTypes;
    static JSONObject jbSendTypes;
    static JSONObject jbShipments;
    private String TAG;
    boolean bFill;
    boolean bNew;
    boolean init;
    String items[];
    JSONObject jbBody;
    JSONObject jbCartStr;
    JSONObject jbOrderStr;
    Button mConfirm;
    TextView mGetPosition;
    ImageButton mGetPosition2;
    TextView mPayContentType;
    ImageButton mPayContentType2;
    TextView mPayType;
    ImageButton mPayType2;
    RadioButton mPhoneConfirmNo;
    RadioButton mPhoneConfirmYes;
    TextView mSendTime;
    ImageButton mSendTime2;
    TextView mSendType;
    ImageButton mSendType2;
    TextView mTitle;
    private Location mostRecentLocation;
    int nPayMonyTypes;
    int nPayMonyTypesID;
    int nPhoneCheck;
    int nSelectGetPotion;
    int nSelectGetPotionID;
    int nSelectedPaymentType;
    int nSelectedPaymentTypeID;
    int nSelectedSendTypes;
    int nSelectedSendTypesID;
    boolean noShipmentInfo;
    int nselectedSendTime;
    int nselectedSendTimeID;
    int oldSelectPaymentType;
    boolean phone;
    Dialog pickDialog;
    boolean pickSitesLoadingFlag;
    String sConfirm;
    String sGetPosition;
    String sPayContentType;
    String sPaymentType;
    String sSendTime;
    String sSendType;
    int view_type;












}
