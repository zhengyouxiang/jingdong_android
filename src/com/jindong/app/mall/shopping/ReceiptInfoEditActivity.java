// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            UpdateOrderInfoInterface

public class ReceiptInfoEditActivity extends MyActivity
    implements UpdateOrderInfoInterface
{

    public ReceiptInfoEditActivity()
    {
        TAG = "ReceiptInfoEditActivity";
        nBookSelected = 0;
        nGeneralSelected = 0;
        nBookSelectedID = 4;
        nGeneralSelectedID = 1;
        bPersonel = true;
        bCompany = false;
        items = null;
        onlyBook = false;
        noBook = false;
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
_L50:
        if(LastOrderInfo.mUserInfo.getUserZip() == null) goto _L6; else goto _L5
_L5:
        jbOrderStr.put("Zip", LastOrderInfo.mUserInfo.getUserZip());
_L35:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince") == null) goto _L8; else goto _L7
_L7:
        jbOrderStr.put("IdProvince", LastOrderInfo.mUserInfo.getUserAddr().get("IdProvince"));
_L36:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdCity") == null) goto _L10; else goto _L9
_L9:
        jbOrderStr.put("IdCity", LastOrderInfo.mUserInfo.getUserAddr().get("IdCity"));
_L37:
        if(LastOrderInfo.mUserInfo.getUserAddr().get("IdArea") == null) goto _L12; else goto _L11
_L11:
        jbOrderStr.put("IdArea", LastOrderInfo.mUserInfo.getUserAddr().get("IdArea"));
_L38:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Where")) goto _L14; else goto _L13
_L13:
        jbOrderStr.put("Where", LastOrderInfo.mUserInfo.getUserAddr().get("Where"));
_L39:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("Email") || LastOrderInfo.mUserInfo.getUserAddr().get("Email") == null) goto _L16; else goto _L15
_L15:
        jbOrderStr.put("Email", LastOrderInfo.mUserInfo.getUserAddr().get("Email"));
_L40:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("UserLevel") || LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel") == null) goto _L18; else goto _L17
_L17:
        jbOrderStr.put("UserLevel", LastOrderInfo.mUserInfo.getUserAddr().get("UserLevel"));
_L41:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceType") == null) goto _L20; else goto _L19
_L19:
        jbOrderStr.put("IdInvoiceType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceType"));
_L42:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceHeaderType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceHeaderType") == null) goto _L22; else goto _L21
_L21:
        jbOrderStr.put("IdInvoiceHeaderType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceHeaderType"));
_L43:
        if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentTypeBook") && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentTypeBook") != null)
            jbOrderStr.put("IdInvoiceContentTypeBook", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentTypeBook"));
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdCompanyBranch") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdCompanyBranch") == null) goto _L24; else goto _L23
_L23:
        jbOrderStr.put("IdCompanyBranch", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdCompanyBranch"));
_L44:
        if(!LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentsType") || LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentsType") == null) goto _L26; else goto _L25
_L25:
        jbOrderStr.put("IdInvoiceContentsType", LastOrderInfo.mInvoiceInfo.getInvoiceInfo().get("IdInvoiceContentsType"));
_L45:
        if(LastOrderInfo.mPaymentInfo.type >= 5 || LastOrderInfo.mPaymentInfo.type <= 0) goto _L28; else goto _L27
_L27:
        jbOrderStr.put("IdPaymentType", LastOrderInfo.mPaymentInfo.type);
_L46:
        if(!LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).toString().contains("PaymentWay") || LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay") == null) goto _L30; else goto _L29
_L29:
        jbOrderStr.put("PaymentWay", LastOrderInfo.mPaymentInfo.getPayMentType(LastOrderInfo.mPaymentInfo.type).get("PaymentWay"));
_L47:
        if(LastOrderInfo.dPromotionPrice < 0.0D) goto _L32; else goto _L31
_L31:
        jbOrderStr.put("PromotionPrice", LastOrderInfo.dPromotionPrice);
_L48:
        if(LastOrderInfo.dPrice < 0.0D) goto _L34; else goto _L33
_L33:
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
          goto _L35
_L8:
        jbOrderStr.put("IdProvince", 1);
          goto _L36
_L10:
        jbOrderStr.put("IdCity", 72);
          goto _L37
_L12:
        jbOrderStr.put("IdArea", 2819);
          goto _L38
_L14:
        jbOrderStr.put("Where", "");
          goto _L39
_L16:
        jbOrderStr.put("Email", "");
          goto _L40
_L18:
        jbOrderStr.put("UserLevel", 0);
          goto _L41
_L20:
        jbOrderStr.put("IdInvoiceType", 0);
          goto _L42
_L22:
        jbOrderStr.put("IdInvoiceHeaderType", 0);
          goto _L43
_L24:
        jbOrderStr.put("IdCompanyBranch", 0);
          goto _L44
_L26:
        jbOrderStr.put("IdInvoiceContentsType", 0);
          goto _L45
_L28:
        jbOrderStr.put("IdPaymentType", 1);
          goto _L46
_L30:
        jbOrderStr.put("PaymentWay", 0);
          goto _L47
_L32:
        jbOrderStr.put("PromotionPrice", 0);
          goto _L48
_L34:
        jbOrderStr.put("Price", 0);
_L2:
        return;
        if(true) goto _L50; else goto _L49
_L49:
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

    private void createBookAlertDiglog(String s)
    {
        final String temp;
        new JSONArray();
        temp = mBook.getText().toString();
        if(jbBooks != null && jbBooks.get("InvoiceContents").toString() != "null" && jbBooks.get("InvoiceContents") != null) goto _L2; else goto _L1
_L2:
        JSONArray jsonarray;
        int i;
        jsonarray = jbBooks.getJSONArray("InvoiceContents");
        items = new String[jsonarray.length()];
        i = 0;
_L6:
        int j = jsonarray.length();
        if(i < j) goto _L4; else goto _L3
_L3:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        if(nBookSelected == -1)
            nBookSelected = 4;
        builder.setSingleChoiceItems(items, nBookSelected, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(k).toString());
                if(temp.compareTo(items[k]) == 0)
                {
                    mBook.setText(items[k]);
                    nBookSelected = k;
                    dialoginterface.dismiss();
                } else
                {
                    nBookSelected = k;
                    setNewBook(k);
                    dialoginterface.dismiss();
                }
            }

            final ReceiptInfoEditActivity this$0;
            private final String val$temp;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
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

    private void createGeneralAlertDiglog(String s)
    {
        final String temp;
        new JSONArray();
        temp = mGeneral.getText().toString();
        if(jbGeneral != null && jbGeneral.get("InvoiceContents").toString() != "null" && jbGeneral.get("InvoiceContents") != null) goto _L2; else goto _L1
_L2:
        JSONArray jsonarray;
        int i;
        jsonarray = jbGeneral.getJSONArray("InvoiceContents");
        items = new String[jsonarray.length()];
        i = 0;
_L6:
        int j = jsonarray.length();
        if(i < j) goto _L4; else goto _L3
_L3:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nGeneralSelected, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(k).toString());
                if(temp.compareTo(items[k]) == 0)
                {
                    mGeneral.setText(items[k]);
                    nGeneralSelected = k;
                    dialoginterface.dismiss();
                } else
                {
                    nGeneralSelected = k;
                    setNewGeneral(k);
                    dialoginterface.dismiss();
                }
            }

            final ReceiptInfoEditActivity this$0;
            private final String val$temp;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
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

    private void getInvoincesContentBook()
    {
        updatCcomositeBody();
        setUpConnAndGetData("invoiceContentsBook", jbBody, "invoiceContentsBook");
    }

    private void getInvoincesContentGeneral()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            updatCcomositeBodyByAddEasyBuy();
            s = "easyBuyInvoiceContentsGeneral";
        } else
        {
            updatCcomositeBody();
            s = "invoiceContentsGeneral";
        }
        setUpConnAndGetData(s, jbBody, "invoiceContentsGeneral");
    }

    private void getInvoincesTypes()
    {
        String s;
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            updatCcomositeBodyByAddEasyBuy();
            s = "easyBuyInvoiceTypes";
        } else
        {
            updatCcomositeBody();
            s = "invoiceTypes";
        }
        setUpConnAndGetData(s, jbBody, "invoiceTypesInfo");
    }

    private int getType(String s)
    {
        int i;
        if(s.compareTo("invoiceTypesInfo") == 0)
            i = 1;
        else
        if(s.compareTo("invoiceContentsBook") == 0)
            i = 2;
        else
        if(s.compareTo("invoiceContentsGeneral") == 0)
            i = 3;
        else
            i = -1;
        return i;
    }

    private void handleClickEvent()
    {
        mBook.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mBook2.performClick();
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
        mBook2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createBookAlertDiglog("\u56FE\u4E66\u7C7B\u578B");
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
        mGeneral.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mGeneral2.performClick();
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
        mGeneral2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createGeneralAlertDiglog("\u975E\u56FE\u4E66\u7C7B\u578B");
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
        mPersonel.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if(flag)
                {
                    mCompanyEdt.setVisibility(8);
                    bPersonel = true;
                } else
                {
                    bPersonel = true;
                }
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
        mCompanyBtn.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if(flag)
                {
                    mCompanyEdt.setVisibility(0);
                    bCompany = true;
                } else
                {
                    bCompany = false;
                }
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
        mConfirm.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!bCompany) goto _L2; else goto _L1
_L1:
                sCompanyName = mCompanyEdt.getText().toString();
                if(sCompanyName != null && sCompanyName.length() >= 1) goto _L4; else goto _L3
_L3:
                Toast.makeText(ReceiptInfoEditActivity.this, "\u8BF7\u8F93\u5165\u5355\u4F4D\u540D\u79F0", 0).show();
_L6:
                return;
_L4:
                if(!CommonUtil.checkUsername(sCompanyName))
                {
                    Toast.makeText(ReceiptInfoEditActivity.this, "\u5355\u4F4D\u540D\u79F0\u53D6\u503C\u8303\u56F4\u4E3Aa-z,A-Z,0-9,'_',\u6C49\u5B57", 0).show();
                    continue; /* Loop/switch isn't completed */
                }
_L2:
                updateInvoinceInfo();
                setResult(4);
                finish();
                if(true) goto _L6; else goto _L5
_L5:
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
    }

    private void handleDatas(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        int i = getType(s);
        i;
        JVM INSTR tableswitch 1 3: default 36
    //                   1 37
    //                   2 100
    //                   3 209;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        jbTypes = httpresponse.getJSONObject();
        setInvoinceTypesView();
_L6:
        try
        {
            getInvoincesContentGeneral();
        }
        catch(Exception exception)
        {
            if(Log.D)
                Log.d(TAG, exception.getMessage());
        }
          goto _L1
        Exception exception3;
        exception3;
        if(!Log.D) goto _L6; else goto _L5
_L5:
        Log.d(TAG, exception3.getMessage());
          goto _L6
_L3:
        try
        {
            jbBooks = httpresponse.getJSONObject();
            if(!jbBooks.toString().contains("\u4E0D\u5F00\u53D1\u7968"))
            {
                JSONObject jsonobject = new JSONObject();
                jsonobject.put("Name", "\u4E0D\u5F00\u53D1\u7968");
                jsonobject.put("Id", "-1");
                jbBooks.getJSONObject("InvoiceContents").getJSONArray("InvoiceContents").put(jsonobject);
            }
        }
        catch(Exception exception2)
        {
            if(Log.D)
                Log.d(TAG, exception2.getMessage());
        }
        setInvoinceBookView();
          goto _L1
_L4:
        try
        {
            jbGeneral = httpresponse.getJSONObject().getJSONObjectOrNull("InvoiceContents");
            jbBooks = httpresponse.getJSONObject().getJSONObjectOrNull("InvoiceContentsBook");
            if(jbGeneral == null)
                onlyBook = true;
            if(jbBooks == null)
                noBook = true;
        }
        catch(Exception exception1)
        {
            if(Log.D)
                Log.d(TAG, exception1.getMessage());
        }
        setInvoinceBookView();
        setInvoinceGeneralView();
          goto _L1
    }

    private void initComponent()
    {
        mReceiptType = (TextView)findViewById(0x7f0c0125);
        mBook = (TextView)findViewById(0x7f0c0285);
        mGeneral = (TextView)findViewById(0x7f0c0286);
        mPersonel = (RadioButton)findViewById(0x7f0c0282);
        mCompanyBtn = (RadioButton)findViewById(0x7f0c0283);
        mBookFlag = (TextView)findViewById(0x7f0c0284);
        mGeneralFlag = (TextView)findViewById(0x7f0c0280);
        mBook2 = (ImageButton)findViewById(0x7f0c0287);
        mGeneral2 = (ImageButton)findViewById(0x7f0c0288);
        mConfirm = (Button)findViewById(0x7f0c0289);
        mCompanyEdt = (EditText)findViewById(0x7f0c000f);
        mCompanyEdt.setVisibility(8);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f090133);
        if(LastOrderInfo.mInvoiceInfo != null && LastOrderInfo.mInvoiceInfo.toString() != "null" && LastOrderInfo.mInvoiceInfo.getInvoiceInfo() != null && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString() != "null" && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("InvoiceTitle"))
        {
            try
            {
                if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("InvoiceTitle").compareTo("\u4E2A\u4EBA") == 0)
                {
                    mPersonel.setChecked(true);
                } else
                {
                    mCompanyBtn.setChecked(true);
                    mCompanyEdt.setVisibility(0);
                    if(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("CompanyName") && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName") != null && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName") != "" && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName") != " ")
                        mCompanyEdt.setText(LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getString("CompanyName"));
                    bCompany = true;
                }
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        } else
        {
            mPersonel.setChecked(true);
            bCompany = false;
        }
    }

    private void setInvoinceBookView()
    {
        post(new Runnable() {

            public void run()
            {
                if(!noBook) goto _L2; else goto _L1
_L1:
                mBook.setVisibility(8);
                mBook2.setVisibility(8);
                mBookFlag.setVisibility(8);
_L4:
                return;
_L2:
                JSONArray jsonarray;
                int i;
                if(jbBooks == null)
                    continue; /* Loop/switch isn't completed */
                jsonarray = jbBooks.getJSONArray("InvoiceContents");
                if(LastOrderInfo.mInvoiceInfo == null || LastOrderInfo.mInvoiceInfo.getInvoiceInfo() == null || !LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentTypeBook"))
                    break MISSING_BLOCK_LABEL_197;
                i = 0;
                do
                {
                    if(i >= jsonarray.length())
                        continue; /* Loop/switch isn't completed */
                    JSONException jsonexception;
                    if(jsonarray.getJSONObject(i).getInt("Id") == LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentTypeBook"))
                    {
                        mBook.setText(jsonarray.getJSONObject(i).getString("Name"));
                        nBookSelected = i;
                        continue; /* Loop/switch isn't completed */
                    }
                    i++;
                } while(true);
                try
                {
                    mBook.setText(jsonarray.getJSONObject(0).getString("Name"));
                    nBookSelected = 0;
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception)
                {
                    if(Log.D)
                        Log.d(TAG, jsonexception.getMessage());
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
    }

    private void setInvoinceGeneralView()
    {
        post(new Runnable() {

            public void run()
            {
                if(!onlyBook) goto _L2; else goto _L1
_L1:
                mGeneral.setVisibility(8);
                mGeneral2.setVisibility(8);
                mGeneralFlag.setVisibility(8);
_L4:
                return;
_L2:
                JSONArray jsonarray;
                int i;
                if(jbGeneral == null)
                    continue; /* Loop/switch isn't completed */
                jsonarray = jbGeneral.getJSONArray("InvoiceContents");
                if(LastOrderInfo.mInvoiceInfo == null || LastOrderInfo.mInvoiceInfo.getInvoiceInfo() == null || !LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentsType"))
                    break MISSING_BLOCK_LABEL_197;
                i = 0;
                do
                {
                    if(i >= jsonarray.length())
                        continue; /* Loop/switch isn't completed */
                    JSONException jsonexception;
                    if(jsonarray.getJSONObject(i).getInt("Id") == LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentsType"))
                    {
                        mGeneral.setText(jsonarray.getJSONObject(i).getString("Name"));
                        nGeneralSelected = i;
                        continue; /* Loop/switch isn't completed */
                    }
                    i++;
                } while(true);
                try
                {
                    mGeneral.setText(jsonarray.getJSONObject(0).getString("Name"));
                    nGeneralSelected = 0;
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception)
                {
                    if(Log.D)
                        Log.d(TAG, jsonexception.getMessage());
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
    }

    private void setInvoinceTypesView()
    {
        post(new Runnable() {

            public void run()
            {
                JSONArray jsonarray;
                int i;
                if(jbTypes == null)
                    break MISSING_BLOCK_LABEL_108;
                jsonarray = jbTypes.getJSONObject("invoiceTypesInfo").getJSONArray("InvoiceTypes");
                i = 0;
_L2:
                if(i >= jsonarray.length())
                    break MISSING_BLOCK_LABEL_108;
                if(jsonarray.getJSONObject(i).getInt("Id") == 1)
                {
                    mReceiptType.setText(jsonarray.getJSONObject(i).getString("Name"));
                    break MISSING_BLOCK_LABEL_108;
                }
                break MISSING_BLOCK_LABEL_102;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
                break MISSING_BLOCK_LABEL_108;
                i++;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final ReceiptInfoEditActivity this$0;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                super();
            }
        }
);
    }

    private void setNewBook(int i)
    {
        if(items[i].compareTo(jbBooks.getJSONArray("InvoiceContents").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_168;
        nBookSelectedID = jbBooks.getJSONArray("InvoiceContents").getJSONObject(i).getInt("Id");
_L3:
        mBook.setText(items[i]);
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbBooks.getJSONArray("InvoiceContents").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nBookSelectedID = jbBooks.getJSONArray("InvoiceContents").getJSONObject(j).getInt("Id");
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

    private void setNewGeneral(int i)
    {
        if(items[i].compareTo(jbGeneral.getJSONArray("InvoiceContents").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_168;
        nGeneralSelectedID = jbGeneral.getJSONArray("InvoiceContents").getJSONObject(i).getInt("Id");
_L3:
        mGeneral.setText(items[i]);
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbGeneral.getJSONArray("InvoiceContents").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nGeneralSelectedID = jbGeneral.getJSONArray("InvoiceContents").getJSONObject(j).getInt("Id");
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

    private void setUpConnAndGetData(String s, JSONObject jsonobject, final String action)
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("funcID")).append(s).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("param")).append(jsonobject.toString()).toString());
        getHttpGroupaAsynPool().add(s, jsonobject, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

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

            final ReceiptInfoEditActivity this$0;
            private final String val$action;

            
            {
                this$0 = ReceiptInfoEditActivity.this;
                action = s;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030079);
        onlyBook = false;
        initComponent();
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            compositeOrderStrByAddEasyBuy();
        } else
        {
            compositeOrderStr();
            getCartItemInfo();
        }
        if(LastOrderInfo.mInvoiceInfo != null && LastOrderInfo.mInvoiceInfo.getInvoiceInfo() != null && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentsType"))
            try
            {
                nGeneralSelectedID = LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentsType");
            }
            catch(JSONException jsonexception1)
            {
                jsonexception1.printStackTrace();
            }
        if(LastOrderInfo.mInvoiceInfo != null && LastOrderInfo.mInvoiceInfo.getInvoiceInfo() != null && LastOrderInfo.mInvoiceInfo.getInvoiceInfo().toString().contains("IdInvoiceContentTypeBook"))
            try
            {
                nBookSelectedID = LastOrderInfo.mInvoiceInfo.getInvoiceInfo().getInt("IdInvoiceContentTypeBook");
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        getInvoincesTypes();
        handleClickEvent();
    }

    public void updatCcomositeBody()
    {
        jbBody = new JSONObject();
        if(jbOrderStr.getInt("IdPaymentType") == 4)
            jbOrderStr.put("IdPaymentType", 1);
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

    public void updatCcomositeBodyByAddEasyBuy()
    {
        jbBody = new JSONObject();
        if(jbOrderStr.getInt("IdPaymentType") == 4)
            jbOrderStr.put("IdPaymentType", 1);
        jbBody.put("OrderStr", jbOrderStr);
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    public void updateInvoinceInfo()
    {
        Contants.mModifiedInvoiceInfo = new InvoiceInfo();
        Contants.mModifiedInvoiceInfo.jbInvoiceInfo = new JSONObject();
        JSONObject jsonobject;
        jsonobject = new JSONObject();
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy)
            break MISSING_BLOCK_LABEL_186;
        jsonobject.put("IdInvoiceType", 1);
        jsonobject.put("IdInvoiceContentTypeBook", nBookSelectedID);
        jsonobject.put("IdInvoiceContentsType", nGeneralSelectedID);
        jsonobject.put("IdInvoiceContentsType", nGeneralSelectedID);
        jsonobject.put("IdInvoiceContentTypeBook", nBookSelectedID);
        if(bCompany)
        {
            jsonobject.put("InvoiceTitle", "\u5355\u4F4D");
            jsonobject.put("CompanyName", sCompanyName);
            jsonobject.put("IdInvoiceHeaderType", 5);
        } else
        {
            jsonobject.put("InvoiceTitle", "\u4E2A\u4EBA");
            jsonobject.put("IdInvoiceHeaderType", 4);
        }
_L1:
        Exception exception;
        Contants.mModifiedInvoiceInfo.setInvoiceInfo(jsonobject);
        break MISSING_BLOCK_LABEL_321;
        try
        {
            jsonobject.put("IdInvoiceType", 1);
            if(onlyBook)
                jsonobject.put("IdInvoiceContentTypeBook", nBookSelectedID);
            else
            if(noBook)
            {
                jsonobject.put("IdInvoiceContentsType", nGeneralSelectedID);
            } else
            {
                jsonobject.put("IdInvoiceContentsType", nGeneralSelectedID);
                jsonobject.put("IdInvoiceContentTypeBook", nBookSelectedID);
            }
            if(bCompany)
            {
                jsonobject.put("InvoiceTitle", "\u5355\u4F4D");
                jsonobject.put("CompanyName", sCompanyName);
                jsonobject.put("IdInvoiceHeaderType", 5);
            } else
            {
                jsonobject.put("InvoiceTitle", "\u4E2A\u4EBA");
                jsonobject.put("IdInvoiceHeaderType", 4);
            }
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            if(Log.D)
                Log.d(TAG, exception.getMessage());
            break MISSING_BLOCK_LABEL_321;
        }
          goto _L1
    }

    public void updatePaymentInfo()
    {
    }

    public void updateUserInfo()
    {
    }

    public void updateYouhuiInfo()
    {
    }

    public static final int GET_INVOINCE_BOOK = 2;
    public static final int GET_INVOINCE_GENERAL = 3;
    public static final int GET_INVOINCE_TYPES = 1;
    private String TAG;
    boolean bCompany;
    boolean bPersonel;
    String items[];
    JSONObject jbBody;
    JSONObject jbBooks;
    JSONObject jbCartStr;
    JSONObject jbGeneral;
    JSONObject jbOrderStr;
    JSONObject jbTypes;
    TextView mBook;
    ImageButton mBook2;
    TextView mBookFlag;
    RadioButton mCompanyBtn;
    EditText mCompanyEdt;
    Button mConfirm;
    TextView mGeneral;
    ImageButton mGeneral2;
    TextView mGeneralFlag;
    RadioButton mPersonel;
    TextView mReceiptType;
    TextView mTitle;
    int nBookSelected;
    int nBookSelectedID;
    int nGeneralSelected;
    int nGeneralSelectedID;
    boolean noBook;
    boolean onlyBook;
    String sCompanyName;






}
