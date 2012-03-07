// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            UpdateOrderInfoInterface, DongAdapter, JingAdapter, LpAdapter

public class EditYouHuiLipinActivity extends MyActivity
    implements UpdateOrderInfoInterface
{

    public EditYouHuiLipinActivity()
    {
        TAG = "EditYouHuiLipinActivity";
        items = null;
        bJingOrDong = 0;
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

    private void createDongAlertDiglog()
    {
        try
        {
            if(jbDongQuan == null || jbDongQuan.length() < 1)
                Toast.makeText(this, "\u60A8\u6CA1\u6709\u4E1C\u5238", 0).show();
            else
                setDongDataView();
        }
        catch(Exception exception)
        {
            if(Log.D)
                Log.d(TAG, exception.getMessage());
        }
    }

    private void createJingAlertDiglog()
    {
        try
        {
            if(jbJingQuan == null || jbJingQuan.length() < 1)
                Toast.makeText(this, "\u60A8\u6CA1\u6709\u4EAC\u5238", 0).show();
            else
                setJingDataView();
        }
        catch(Exception exception)
        {
            if(Log.D)
                Log.d(TAG, exception.getMessage());
            Toast.makeText(this, "\u8BF7\u91CD\u65B0\u9009\u62E9", 0).show();
        }
    }

    private void createLiPINAlertDiglog(String s)
    {
        final String temp;
        new JSONArray();
        temp = mLipin.getText().toString();
        if(jbLipin != null && jbLipin.length() >= 1 && jbLipin.getJSONArray("GiftCards") != null && jbLipin.getJSONArray("GiftCards").length() >= 1) goto _L2; else goto _L1
_L1:
        Toast.makeText(this, "\u60A8\u6CA1\u6709\u793C\u54C1\u5361", 0).show();
          goto _L3
_L2:
        JSONArray jsonarray;
        int i;
        jsonarray = jbLipin.getJSONArray("GiftCards");
        items = new String[jsonarray.length()];
        i = 0;
_L7:
        int j = jsonarray.length();
        if(i < j) goto _L5; else goto _L4
_L4:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("which:")).append(k).toString());
                if(temp.compareTo(items[k]) == 0)
                {
                    mLipin.setText(items[k]);
                    dialoginterface.dismiss();
                } else
                {
                    dialoginterface.dismiss();
                }
            }

            final EditYouHuiLipinActivity this$0;
            private final String val$temp;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                temp = s;
                super();
            }
        }
).show();
        break; /* Loop/switch isn't completed */
_L5:
        items[i] = (new StringBuilder(String.valueOf(String.valueOf(jsonarray.getJSONObject(i).get("Discount"))))).append("  ").append(jsonarray.getJSONObject(i).getString("Id")).append("  ").append(jsonarray.getJSONObject(i).getString("TimeEnd")).toString();
        i++;
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
        if(true) goto _L4; else goto _L3
_L3:
        return;
        if(true) goto _L7; else goto _L6
_L6:
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

    private void getCoupons()
    {
        updatCcomositeBody();
        setUpConnAndGetData("usedElecCoupons", jbBody, "usedElecCoupons");
    }

    private void getLinpin()
    {
        updatCcomositeBody();
        setUpConnAndGetData("getGiftCart", jbBody, "getGiftCard");
    }

    private void getScreenHW()
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
        if(Log.D)
            Log.d("Height", String.valueOf(screenHeight));
    }

    private int getType(String s)
    {
        int i;
        if(s.compareTo("usedElecCoupons") == 0)
            i = 1;
        else
        if(s.compareTo("getGiftCard") == 0)
            i = 3;
        else
            i = -1;
        return i;
    }

    private void handleClickEvent()
    {
        mConfirm.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                updateYouhuiInfo();
                EditYouHuiLipinActivity.jbCoupon = null;
                EditYouHuiLipinActivity.jbCoupon = new JSONObject();
                EditYouHuiLipinActivity.jbLipin = null;
                EditYouHuiLipinActivity.jbLipin = new JSONObject();
                EditYouHuiLipinActivity.jbJingQuan = null;
                EditYouHuiLipinActivity.jbJingQuan = new JSONArray();
                EditYouHuiLipinActivity.jbDongQuan = null;
                EditYouHuiLipinActivity.jbDongQuan = new JSONArray();
                setResult(5);
                finish();
            }

            final EditYouHuiLipinActivity this$0;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
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
    //                   2 36
    //                   3 105;
           goto _L1 _L2 _L1 _L3
_L1:
        return;
_L2:
        jbCoupon = httpresponse.getJSONObject().getJSONObject("usedElecCoupons");
        setCouponView();
_L5:
        try
        {
            getLinpin();
        }
        catch(Exception exception)
        {
            if(Log.D)
                Log.d(TAG, exception.getMessage());
        }
          goto _L1
        Exception exception2;
        exception2;
        if(!Log.D) goto _L5; else goto _L4
_L4:
        Log.d(TAG, exception2.getMessage());
          goto _L5
_L3:
        try
        {
            jbLipin = httpresponse.getJSONObject().getJSONObject("getGiftCard");
        }
        catch(Exception exception1)
        {
            if(Log.D)
                Log.d(TAG, exception1.getMessage());
        }
        setLipinView();
          goto _L1
    }

    private void initComponent()
    {
        mLiView = (ListView)findViewById(0x7f0c02ae);
        mYouHui = (ListView)findViewById(0x7f0c02a6);
        mDongQuan = (ListView)findViewById(0x7f0c02aa);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f09014e);
        mConfirm = (Button)findViewById(0x7f0c02af);
    }

    private void setCouponView()
    {
        post(new Runnable() {

            public void run()
            {
                if(EditYouHuiLipinActivity.jbCoupon == null || EditYouHuiLipinActivity.jbCoupon.toString() == "null") goto _L2; else goto _L1
_L1:
                JSONArray jsonarray = EditYouHuiLipinActivity.jbCoupon.getJSONArray("Coupons");
                if(jsonarray == null || jsonarray.toString() == "null" || jsonarray.length() <= 0) goto _L4; else goto _L3
_L3:
                int i = 0;
_L12:
                if(i < jsonarray.length()) goto _L6; else goto _L5
_L5:
                if(EditYouHuiLipinActivity.jbJingQuan == null || EditYouHuiLipinActivity.jbJingQuan.toString() == "null" || EditYouHuiLipinActivity.jbJingQuan.length() <= 0) goto _L8; else goto _L7
_L7:
                Contants.noJing = false;
                Contants.bNoYouHui = false;
                ((TextView)findViewById(0x7f0c02a5)).setVisibility(8);
                setJingDataView();
_L11:
                if(EditYouHuiLipinActivity.jbDongQuan == null || EditYouHuiLipinActivity.jbDongQuan.toString() == "null" || EditYouHuiLipinActivity.jbDongQuan.length() <= 0) goto _L10; else goto _L9
_L9:
                Contants.noDong = false;
                Contants.bNoYouHui = false;
                ((TextView)findViewById(0x7f0c02a9)).setVisibility(8);
                mDongQuan.setVisibility(0);
                setDongDataView();
                  goto _L2
_L6:
                if(jsonarray.getJSONObject(i).getInt("CouponType") == 0)
                    EditYouHuiLipinActivity.jbJingQuan.put(jsonarray.getJSONObject(i));
                else
                if(jsonarray.getJSONObject(i).getInt("CouponType") == 1)
                    EditYouHuiLipinActivity.jbDongQuan.put(jsonarray.getJSONObject(i));
                break MISSING_BLOCK_LABEL_433;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
                  goto _L2
_L8:
                TextView textview2 = (TextView)findViewById(0x7f0c02a5);
                textview2.setText("\u6CA1\u6709\u53EF\u7528\u7684\u4EAC\u5238");
                textview2.setVisibility(0);
                mYouHui.setVisibility(8);
                  goto _L11
_L10:
                TextView textview3 = (TextView)findViewById(0x7f0c02a9);
                textview3.setVisibility(0);
                mDongQuan.setVisibility(8);
                textview3.setText("\u6CA1\u6709\u53EF\u7528\u7684\u4E1C\u5238");
                  goto _L2
_L4:
                TextView textview = (TextView)findViewById(0x7f0c02a5);
                textview.setText("\u6CA1\u6709\u53EF\u7528\u7684\u4EAC\u5238");
                textview.setVisibility(0);
                mYouHui.setVisibility(8);
                TextView textview1 = (TextView)findViewById(0x7f0c02a9);
                textview1.setVisibility(0);
                textview1.setText("\u6CA1\u6709\u53EF\u7528\u7684\u4E1C\u5238");
                mDongQuan.setVisibility(8);
_L2:
                return;
                i++;
                  goto _L12
            }

            final EditYouHuiLipinActivity this$0;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                super();
            }
        }
);
    }

    private void setDongDataView()
    {
        ArrayList arraylist = new ArrayList();
        if(jbDongQuan == null || jbDongQuan.toString() == "null" || jbDongQuan.length() <= 0) goto _L2; else goto _L1
_L1:
        int j = 0;
_L7:
        if(j < jbDongQuan.length()) goto _L3; else goto _L2
_L2:
        int ai[] = new int[0];
        if(Contants.nSelectDongQuanId == null || Contants.nSelectDongQuanId == "" || Contants.nSelectDongQuanId == " ") goto _L5; else goto _L4
_L4:
        int i;
        ai = new int[1];
        i = 0;
_L9:
        if(i < jbDongQuan.length()) goto _L6; else goto _L5
_L5:
        JSONException jsonexception2;
        JSONException jsonexception;
        JSONException jsonexception1;
        JSONObject jsonobject;
        HashMap hashmap1;
        if(arraylist.size() < 1)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("name", "\u6CA1\u6709\u53EF\u7528\u4E1C\u5238");
            hashmap.put("time", " ");
            arraylist.add(hashmap);
            Contants.noDong = true;
        } else
        {
            Contants.noDong = false;
        }
        mDongQuan.setAdapter(new DongAdapter(this, arraylist, ai));
        mDongQuan.setClickable(true);
        if(screenHeight < 800 && screenHeight > 480 && screenWidth > 320 && screenWidth < 600)
            mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 70 * mDongQuan.getCount()));
        else
        if(screenHeight < 1024 && screenHeight >= 800 && screenWidth > 320 && screenWidth < 600)
            mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 59 * mDongQuan.getCount()));
        else
        if(screenHeight >= 1024 && screenWidth >= 600)
        {
            if(mDongQuan.getCount() < 16)
                mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mDongQuan.getCount()));
            else
                mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mDongQuan.getCount()));
        } else
        {
            boolean flag;
            boolean flag1;
            if(screenHeight == 480)
                flag = true;
            else
                flag = false;
            if(screenWidth == 320)
                flag1 = true;
            else
                flag1 = false;
            if(flag & flag1)
            {
                mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 68 * mDongQuan.getCount()));
            } else
            {
                boolean flag2;
                boolean flag3;
                if(screenHeight < 480)
                    flag2 = true;
                else
                    flag2 = false;
                if(screenWidth <= 320)
                    flag3 = true;
                else
                    flag3 = false;
                if(flag2 & flag3)
                    mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 28 * mDongQuan.getCount()));
                else
                if(screenHeight < 1024 && screenHeight > 480 && screenWidth >= 640)
                    mDongQuan.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 80 * mDongQuan.getCount()));
            }
        }
        mDongQuan.setItemsCanFocus(true);
        mDongQuan.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int k, long l)
            {
                if(Contants.jSelected) goto _L2; else goto _L1
_L1:
                RadioButton radiobutton = (RadioButton)view.findViewById(0x7f0c013e);
                if(!radiobutton.isChecked()) goto _L4; else goto _L3
_L3:
                radiobutton.setChecked(false);
                Contants.dSelected = false;
                if(Contants.oldBtn != null)
                {
                    Contants.oldBtn.setChecked(false);
                    EditYouHuiLipinActivity.oldPosition = -1;
                }
                Contants.dongSel = null;
                Contants.nSelectDongQuanId = null;
_L6:
                return;
_L4:
                try
                {
                    if(Double.valueOf(Contants.dYTotalPrice).doubleValue() < EditYouHuiLipinActivity.jbDongQuan.getJSONObject(k).getDouble("Quota"))
                    {
                        Toast.makeText(EditYouHuiLipinActivity.this, (new StringBuilder("\u60A8\u8D2D\u4E70\u7684\u91D1\u989D\u4E0D\u8DB3")).append(EditYouHuiLipinActivity.jbDongQuan.getJSONObject(k).get("Quota")).append("\u5143,").append("\u4E0D\u80FD\u4F7F\u7528\u8FD9\u5F20\u4E1C\u5238").toString(), 0).show();
                        continue; /* Loop/switch isn't completed */
                    }
                }
                catch(JSONException jsonexception4)
                {
                    jsonexception4.printStackTrace();
                }
                if(Contants.oldBtn != null)
                    Contants.oldBtn.setChecked(false);
                radiobutton.setChecked(true);
                EditYouHuiLipinActivity.oldPosition = k;
                Contants.oldBtn = radiobutton;
                Contants.dSelected = true;
                Contants.jdSwitch = true;
                addDongQuan(EditYouHuiLipinActivity.jbDongQuan, k);
                continue; /* Loop/switch isn't completed */
_L2:
                Toast.makeText(EditYouHuiLipinActivity.this, "\u60A8\u5DF2\u7ECF\u9009\u62E9\u4E86\u4EAC\u5238", 0).show();
                if(true) goto _L6; else goto _L5
_L5:
            }

            final EditYouHuiLipinActivity this$0;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                super();
            }
        }
);
        return;
_L3:
        jsonobject = jbDongQuan.getJSONObject(j);
        hashmap1 = new HashMap();
        hashmap1.put("name", (new StringBuilder(String.valueOf(jsonobject.get("Discount").toString().substring(0, jsonobject.get("Discount").toString().indexOf("."))))).append("\u5143").append("(\u6EE1").append(jsonobject.get("Quota").toString().substring(0, jsonobject.get("Quota").toString().indexOf("."))).append("\u62B5").append(jsonobject.get("Discount").toString().substring(0, jsonobject.get("Discount").toString().indexOf("."))).append(")").toString());
        hashmap1.put("time", jsonobject.getString("TimeEnd").substring(0, 10));
        arraylist.add(hashmap1);
_L8:
        j++;
          goto _L7
        jsonexception1;
        jsonexception2 = jsonexception1;
_L10:
        jsonexception2.printStackTrace();
          goto _L8
_L6:
        if(jbDongQuan.getJSONObject(i).getString("Id").compareTo(Contants.nSelectDongQuanId) != 0)
            break MISSING_BLOCK_LABEL_522;
        ai[0] = i;
          goto _L5
        jsonexception;
        jsonexception.printStackTrace();
        i++;
          goto _L9
        JSONException jsonexception3;
        jsonexception3;
        jsonexception2 = jsonexception3;
          goto _L10
    }

    private void setJingDataView()
    {
        ArrayList arraylist = new ArrayList();
        if(jbJingQuan == null || jbJingQuan.toString() == "null" || jbJingQuan.length() <= 0) goto _L2; else goto _L1
_L1:
        int l = 0;
_L7:
        if(l < jbJingQuan.length()) goto _L3; else goto _L2
_L2:
        int ai[] = new int[0];
        if(Contants.nselectJingQuanIDs == null || Contants.nselectJingQuanIDs.size() <= 0) goto _L5; else goto _L4
_L4:
        int i;
        int j;
        i = 0;
        ai = new int[Contants.nselectJingQuanIDs.size()];
        j = 0;
_L11:
        if(j < Contants.nselectJingQuanIDs.size()) goto _L6; else goto _L5
_L5:
        JSONException jsonexception2;
        int k;
        JSONException jsonexception;
        JSONException jsonexception1;
        JSONObject jsonobject;
        HashMap hashmap1;
        if(arraylist.size() < 1)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("name", "\u6CA1\u6709\u53EF\u7528\u7684\u4EAC\u5238");
            hashmap.put("time", " ");
            arraylist.add(hashmap);
            Contants.noJing = true;
        } else
        {
            Contants.noJing = false;
        }
        mYouHui.setAdapter(new JingAdapter(this, arraylist, ai));
        if(screenHeight < 800 && screenHeight > 480 && screenWidth > 320 && screenWidth < 600)
            mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 70 * mYouHui.getCount()));
        else
        if(screenHeight < 1024 && screenHeight >= 800 && screenWidth > 320 && screenWidth < 600)
            mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 69 * mYouHui.getCount()));
        else
        if(screenHeight >= 1024 && screenWidth >= 600)
        {
            if(mYouHui.getCount() < 16)
                mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mYouHui.getCount()));
            else
                mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mYouHui.getCount()));
        } else
        {
            boolean flag;
            boolean flag1;
            if(screenHeight == 480)
                flag = true;
            else
                flag = false;
            if(screenWidth == 320)
                flag1 = true;
            else
                flag1 = false;
            if(flag & flag1)
            {
                mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 68 * mYouHui.getCount()));
            } else
            {
                boolean flag2;
                boolean flag3;
                if(screenHeight < 480)
                    flag2 = true;
                else
                    flag2 = false;
                if(screenWidth <= 320)
                    flag3 = true;
                else
                    flag3 = false;
                if(flag2 & flag3)
                    mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 32 * mYouHui.getCount()));
                else
                if(screenHeight < 1024 && screenHeight > 480 && screenWidth >= 640)
                    mYouHui.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mYouHui.getCount()));
            }
        }
        mYouHui.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i1, long l1)
            {
                if(!Contants.dSelected)
                {
                    CheckBox checkbox = (CheckBox)view.findViewById(0x7f0c013c);
                    if(checkbox.isChecked())
                    {
                        checkbox.setChecked(false);
                        removeJingQuan(EditYouHuiLipinActivity.jbJingQuan, i1, Contants.jinSelArray);
                        if(Contants.jinSelArray == null || Contants.jinSelArray.length() < 1)
                            Contants.jSelected = false;
                    } else
                    {
                        checkbox.setChecked(true);
                        if(Log.D)
                            Log.d("jing", EditYouHuiLipinActivity.jbJingQuan.toString());
                        addJingQuan(EditYouHuiLipinActivity.jbJingQuan, i1);
                        Contants.jSelected = true;
                        Contants.jdSwitch = false;
                    }
                } else
                {
                    Toast.makeText(EditYouHuiLipinActivity.this, "\u60A8\u5DF2\u7ECF\u9009\u62E9\u4E86\u4E1C\u5238", 0).show();
                }
            }

            final EditYouHuiLipinActivity this$0;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                super();
            }
        }
);
        return;
_L3:
        jsonobject = jbJingQuan.getJSONObject(l);
        if(!jsonobject.getBoolean("CanUsed"))
            break MISSING_BLOCK_LABEL_350;
        hashmap1 = new HashMap();
        hashmap1.put("name", (new StringBuilder(String.valueOf(jsonobject.get("Discount").toString()))).append("\u5143").toString());
        hashmap1.put("time", jsonobject.getString("TimeEnd").substring(0, 10));
        arraylist.add(hashmap1);
_L8:
        l++;
          goto _L7
        jsonexception1;
        jsonexception2 = jsonexception1;
_L13:
        jsonexception2.printStackTrace();
          goto _L8
_L6:
        k = 0;
_L12:
        if(k < jbJingQuan.length()) goto _L10; else goto _L9
_L9:
        j++;
          goto _L11
_L10:
        if(jbJingQuan.getJSONObject(k).getString("Id").compareTo((String)Contants.nselectJingQuanIDs.get(j)) != 0)
            break MISSING_BLOCK_LABEL_440;
        ai[i] = k;
        i++;
          goto _L9
        jsonexception;
        jsonexception.printStackTrace();
        k++;
          goto _L12
        JSONException jsonexception3;
        jsonexception3;
        jsonexception2 = jsonexception3;
          goto _L13
    }

    private void setLipinDataView()
    {
        ArrayList arraylist;
        JSONArray jsonarray;
        arraylist = new ArrayList();
        jsonarray = new JSONArray();
        if(jbLipin == null || jbLipin.toString() == "null" || jbLipin.get("GiftCards") == null || jbLipin.get("GiftCards").toString() == "null" || jbLipin.getJSONArray("GiftCards").length() <= 0) goto _L2; else goto _L1
_L1:
        int j1;
        jsonarray = jbLipin.getJSONArray("GiftCards");
        j1 = 0;
_L7:
        int k1 = jsonarray.length();
        if(j1 < k1) goto _L3; else goto _L2
_L2:
        final JSONArray jbTemp2;
        int ai[];
        jbTemp2 = jsonarray;
        ai = new int[0];
        if(Contants.nSelectLipinIDs == null || Contants.nSelectLipinIDs.size() <= 0) goto _L5; else goto _L4
_L4:
        int i;
        int j;
        i = 0;
        ai = new int[Contants.nSelectLipinIDs.size()];
        j = 0;
_L11:
        int k = Contants.nSelectLipinIDs.size();
        if(j < k) goto _L6; else goto _L5
_L5:
        JSONException jsonexception3;
        JSONException jsonexception;
        int l;
        int i1;
        JSONException jsonexception1;
        JSONException jsonexception2;
        JSONObject jsonobject;
        HashMap hashmap;
        if(arraylist.size() < 1)
        {
            TextView textview = (TextView)findViewById(0x7f0c02ad);
            textview.setText("\u6CA1\u6709\u53EF\u7528\u7684\u793C\u54C1\u5361");
            textview.setVisibility(0);
            mLiView.setVisibility(8);
            Contants.noLi = true;
        } else
        {
            Contants.noLi = false;
            ((TextView)findViewById(0x7f0c02ad)).setVisibility(8);
            mLiView.setVisibility(0);
            ListView listview = mLiView;
            LpAdapter lpadapter = new LpAdapter(this, arraylist, ai);
            listview.setAdapter(lpadapter);
            ListView listview1;
            android.widget.AdapterView.OnItemClickListener onitemclicklistener;
            if(screenHeight < 800 && screenHeight > 480 && screenWidth > 320 && screenWidth < 600)
                mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 70 * mLiView.getCount()));
            else
            if(screenHeight < 1024 && screenHeight >= 800 && screenWidth > 320 && screenWidth < 600)
                mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 69 * mLiView.getCount()));
            else
            if(screenHeight >= 1024 && screenWidth >= 600)
            {
                if(mLiView.getCount() < 16)
                    mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mLiView.getCount()));
                else
                    mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mLiView.getCount()));
            } else
            {
                boolean flag;
                boolean flag1;
                if(screenHeight == 480)
                    flag = true;
                else
                    flag = false;
                if(screenWidth == 320)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag & flag1)
                {
                    mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 68 * mLiView.getCount()));
                } else
                {
                    boolean flag2;
                    boolean flag3;
                    if(screenHeight < 480)
                        flag2 = true;
                    else
                        flag2 = false;
                    if(screenWidth <= 320)
                        flag3 = true;
                    else
                        flag3 = false;
                    if(flag2 & flag3)
                        mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 32 * mLiView.getCount()));
                    else
                    if(screenHeight < 1024 && screenHeight > 480 && screenWidth >= 640)
                        mLiView.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(screenWidth, 90 * mLiView.getCount()));
                }
            }
            listview1 = mLiView;
            onitemclicklistener = new android.widget.AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView adapterview, View view, int l1, long l2)
                {
                    CheckBox checkbox = (CheckBox)view.findViewById(0x7f0c0144);
                    if(checkbox.isChecked())
                    {
                        checkbox.setChecked(false);
                        removeSelLipin(jbTemp2, l1, Contants.liSelArray);
                    } else
                    {
                        checkbox.setChecked(true);
                        addSelLipin(jbTemp2, l1);
                    }
                }

                final EditYouHuiLipinActivity this$0;
                private final JSONArray val$jbTemp2;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                jbTemp2 = jsonarray;
                super();
            }
            }
;
            listview1.setOnItemClickListener(onitemclicklistener);
        }
        return;
_L3:
        jsonobject = jsonarray.getJSONObject(j1);
        hashmap = new HashMap();
        hashmap.put("name", (new StringBuilder(String.valueOf(jsonobject.get("Discount").toString()))).append("\u5143").toString());
        hashmap.put("leftMoney", (new StringBuilder(String.valueOf(jsonobject.get("LeaveMoney").toString()))).append("\u5143").toString());
        hashmap.put("time", jsonobject.getString("TimeEnd").substring(0, 10));
        arraylist.add(hashmap);
_L8:
        j1++;
          goto _L7
        jsonexception2;
        jsonexception3 = jsonexception2;
_L13:
        jsonexception3.printStackTrace();
          goto _L8
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L6:
        l = 0;
_L12:
        i1 = jsonarray.length();
        if(l < i1) goto _L10; else goto _L9
_L9:
        j++;
          goto _L11
_L10:
        if(jsonarray.getJSONObject(l).getString("Id").compareTo((String)Contants.nSelectLipinIDs.get(j)) != 0)
            break MISSING_BLOCK_LABEL_437;
        ai[i] = l;
        i++;
          goto _L9
        jsonexception1;
        jsonexception1.printStackTrace();
        l++;
          goto _L12
        JSONException jsonexception4;
        jsonexception4;
        jsonexception3 = jsonexception4;
          goto _L13
    }

    private void setLipinView()
    {
        post(new Runnable() {

            public void run()
            {
                if(EditYouHuiLipinActivity.jbLipin != null)
                {
                    EditYouHuiLipinActivity.jbLipin.getJSONArray("GiftCards");
                    setLipinDataView();
                }
_L1:
                return;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
                  goto _L1
            }

            final EditYouHuiLipinActivity this$0;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                super();
            }
        }
);
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
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

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

            final EditYouHuiLipinActivity this$0;
            private final String val$action;

            
            {
                this$0 = EditYouHuiLipinActivity.this;
                action = s;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    public void addDongQuan(JSONArray jsonarray, int i)
    {
        if(Contants.dongSel != null && Contants.dongSel.toString() != "null")
            break MISSING_BLOCK_LABEL_135;
        Contants.dongSel = new JSONObject();
        Contants.dongSel.put("Id", jsonarray.getJSONObject(i).getString("Id"));
        Contants.dongSel.put("Discount", jsonarray.getJSONObject(i).getString("Discount"));
        Contants.dongSel.put("Quota", jsonarray.getJSONObject(i).getString("Quota"));
        Contants.dongSel.put("TimeEnd", jsonarray.getJSONObject(i).getString("TimeEnd"));
        Contants.nSelectDongQuanId = jsonarray.getJSONObject(i).getString("Id");
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
        try
        {
            Contants.dongSel.put("Id", jsonarray.getJSONObject(i).getString("Id"));
            Contants.dongSel.put("Discount", jsonarray.getJSONObject(i).getString("Discount"));
            Contants.dongSel.put("Quota", jsonarray.getJSONObject(i).getString("Quota"));
            Contants.dongSel.put("TimeEnd", jsonarray.getJSONObject(i).getString("TimeEnd"));
            Contants.nSelectDongQuanId = jsonarray.getJSONObject(i).getString("Id");
        }
        catch(JSONException jsonexception1)
        {
            jsonexception1.printStackTrace();
        }
          goto _L1
    }

    public void addJingQuan(JSONArray jsonarray, int i)
    {
        boolean flag = true;
        if(Contants.jinSelArray != null && Contants.jinSelArray.length() >= 1)
            break MISSING_BLOCK_LABEL_222;
        Contants.jinSelArray = new JSONArray();
        Contants.jinSelArray.put(jsonarray.getJSONObject(i));
        if(Contants.nselectJingQuanIDs == null || Contants.nselectJingQuanIDs.toString() == "null")
            Contants.nselectJingQuanIDs = new ArrayList();
        Contants.nselectJingQuanIDs.add(jsonarray.getJSONObject(i).getString("Id"));
          goto _L1
_L4:
        int j;
        if(j < Contants.jinSelArray.length()) goto _L3; else goto _L2
_L2:
        if(flag)
        {
            Contants.jinSelArray.put(jsonarray.getJSONObject(i));
            if(Contants.nselectJingQuanIDs == null || Contants.nselectJingQuanIDs.toString() == "null")
                Contants.nselectJingQuanIDs = new ArrayList();
            Contants.nselectJingQuanIDs.add(jsonarray.getJSONObject(i).getString("Id"));
        }
          goto _L1
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
_L3:
        int k = Contants.jinSelArray.getJSONObject(j).getString("Id").compareTo(jsonarray.getJSONObject(i).getString("Id"));
label0:
        {
            if(k != 0)
                break label0;
            flag = false;
        }
          goto _L2
        j++;
          goto _L4
_L1:
        return;
        j = 0;
          goto _L4
    }

    public void addSelLipin(JSONArray jsonarray, int i)
    {
        boolean flag;
        flag = true;
        try
        {
            if(Log.D)
                Log.d("Lipi", jsonarray.getJSONObject(i).toString());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        if(Contants.liSelArray != null && Contants.liSelArray.length() >= 1) goto _L2; else goto _L1
_L1:
        Contants.liSelArray = new JSONArray();
        Contants.liSelArray.put(jsonarray.getJSONObject(i));
        if(Contants.nSelectLipinIDs == null || Contants.nSelectLipinIDs.toString() == "null")
            Contants.nSelectLipinIDs = new ArrayList();
        Contants.nSelectLipinIDs.add(jsonarray.getJSONObject(i).getString("Id"));
_L5:
        return;
_L2:
        int j = 0;
_L6:
        if(j < Contants.liSelArray.length()) goto _L4; else goto _L3
_L3:
        if(flag)
        {
            Contants.liSelArray.put(jsonarray.getJSONObject(i));
            if(Contants.nSelectLipinIDs == null || Contants.nSelectLipinIDs.toString() == "null")
                Contants.nSelectLipinIDs = new ArrayList();
            Contants.nSelectLipinIDs.add(jsonarray.getJSONObject(i).getString("Id"));
        }
          goto _L5
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L5
_L4:
        int k = Contants.liSelArray.getJSONObject(j).getString("Id").compareTo(jsonarray.getJSONObject(i).getString("Id"));
label0:
        {
            if(k != 0)
                break label0;
            flag = false;
        }
          goto _L3
        j++;
          goto _L6
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030082);
        jbCoupon = new JSONObject();
        jbLipin = new JSONObject();
        jbJingQuan = new JSONArray();
        jbDongQuan = new JSONArray();
        Contants.bNoYouHui = false;
        Contants.dSelected = false;
        Contants.liSelected = false;
        oldPosition = -1;
        getScreenHW();
        initComponent();
        compositeOrderStr();
        getCartItemInfo();
        getCoupons();
        handleClickEvent();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            updateYouhuiInfo();
            jbCoupon = null;
            jbCoupon = new JSONObject();
            jbLipin = null;
            jbLipin = new JSONObject();
            jbJingQuan = null;
            jbJingQuan = new JSONArray();
            jbDongQuan = null;
            jbDongQuan = new JSONArray();
            setResult(5);
            finish();
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public void removeJingQuan(JSONArray jsonarray, int i, JSONArray jsonarray1)
    {
        if(jsonarray1 == null) goto _L2; else goto _L1
_L1:
        if(jsonarray1.length() >= 1) goto _L3; else goto _L2
_L12:
        int j;
        if(j >= Contants.jinSelArray.length()) goto _L2; else goto _L4
_L4:
        if(jsonarray1.get(j) == "" || jsonarray1.get(j).toString() == "" || jsonarray1.getJSONObject(j).toString() == "" || jsonarray1.getJSONObject(j) == null || jsonarray1.getJSONObject(j).getString("Id").compareTo(jsonarray.getJSONObject(i).getString("Id")) != 0) goto _L6; else goto _L5
_L5:
        JSONArray jsonarray2;
        int k;
        jsonarray2 = new JSONArray();
        k = 0;
_L13:
        if(k < Contants.jinSelArray.length()) goto _L8; else goto _L7
_L7:
        int l;
        Contants.jinSelArray = null;
        Contants.jinSelArray = new JSONArray();
        l = 0;
_L11:
        if(l < jsonarray2.length()) goto _L10; else goto _L9
_L9:
        if(Contants.nselectJingQuanIDs != null && Contants.nselectJingQuanIDs.toString() != "null")
            Contants.nselectJingQuanIDs.remove(jsonarray.getJSONObject(i).getString("Id"));
          goto _L2
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L2
_L8:
        if(jsonarray1.getJSONObject(k).getString("Id").compareTo(jsonarray.getJSONObject(i).getString("Id")) != 0)
            jsonarray2.put(Contants.jinSelArray.get(k));
        break MISSING_BLOCK_LABEL_281;
_L10:
        Contants.jinSelArray.put(jsonarray2.get(l));
        l++;
          goto _L11
_L6:
        j++;
          goto _L12
_L2:
        return;
_L3:
        j = 0;
          goto _L12
        k++;
          goto _L13
    }

    public void removeSelLipin(JSONArray jsonarray, int i, JSONArray jsonarray1)
    {
        if(jsonarray1 == null) goto _L2; else goto _L1
_L1:
        if(jsonarray1.length() >= 1) goto _L3; else goto _L2
_L12:
        int j;
        if(j >= Contants.liSelArray.length()) goto _L2; else goto _L4
_L4:
        if(jsonarray1.get(j) == "" || jsonarray1.get(j).toString() == "" || jsonarray1.getJSONObject(j).toString() == "" || jsonarray1.getJSONObject(j) == null || jsonarray1.getJSONObject(j) == null || jsonarray1.getJSONObject(j).toString() == "null" || jsonarray1.getJSONObject(j).getString("Id").compareTo(jsonarray.getJSONObject(i).getString("Id")) != 0) goto _L6; else goto _L5
_L5:
        JSONArray jsonarray2;
        int k;
        jsonarray2 = new JSONArray();
        k = 0;
_L13:
        if(k < Contants.liSelArray.length()) goto _L8; else goto _L7
_L7:
        int l;
        Contants.liSelArray = null;
        Contants.liSelArray = new JSONArray();
        l = 0;
_L11:
        if(l < jsonarray2.length()) goto _L10; else goto _L9
_L9:
        if(Contants.nSelectLipinIDs != null && Contants.nSelectLipinIDs.toString() != "null")
            Contants.nSelectLipinIDs.remove(jsonarray.getJSONObject(i).getString("Id"));
          goto _L2
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L2
_L8:
        if(jsonarray1.getJSONObject(k).getString("Id").compareTo(jsonarray.getJSONObject(i).getString("Id")) != 0)
            jsonarray2.put(Contants.liSelArray.get(k));
        break MISSING_BLOCK_LABEL_308;
_L10:
        Contants.liSelArray.put(jsonarray2.get(l));
        l++;
          goto _L11
_L2:
        return;
_L3:
        j = 0;
          goto _L12
_L6:
        j++;
          goto _L12
        k++;
          goto _L13
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

    public void updateInvoinceInfo()
    {
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

    public static final int GET_COUPON = 1;
    public static final int GET_JING_QUAN = 2;
    public static final int GET_LIPIN = 3;
    static JSONObject jbCoupon;
    static JSONArray jbDongQuan;
    static JSONArray jbJingQuan;
    static JSONObject jbLipin;
    static int oldPosition = -1;
    private String TAG;
    int bJingOrDong;
    String items[];
    JSONObject jbBody;
    JSONObject jbCartStr;
    JSONObject jbOrderStr;
    Button mConfirm;
    ListView mDongQuan;
    TextView mJIngQuan;
    ImageButton mJIngQuan2;
    ListView mLiView;
    TextView mLipin;
    ImageButton mLipin2;
    TextView mTitle;
    ListView mYouHui;
    int screenHeight;
    int screenWidth;






}
