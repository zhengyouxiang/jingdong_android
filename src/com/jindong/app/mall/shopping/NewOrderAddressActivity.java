// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.CartTable;
import com.jindong.app.mall.entity.UserInfo;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            UpdateOrderInfoInterface

public class NewOrderAddressActivity extends MyActivity
    implements UpdateOrderInfoInterface
{

    public NewOrderAddressActivity()
    {
        TAG = "NewOrderAddressActivity";
        nSelProvinceIndex = 0;
        nSelCityIndex = 0;
        nSelAreaIndex = 0;
        nSelProvincePosition = 0;
        nSelCityPosition = 0;
        nSelAreaPosition = 0;
        items = null;
    }

    private void createAreaAlertDiglog(String s)
    {
        final String oldArea;
        new JSONArray();
        oldArea = mArea.getText().toString();
        JSONArray jsonarray;
        int i;
        jsonarray = jbAreas.getJSONArray("Areas");
        items = new String[jsonarray.length()];
        i = 0;
_L3:
        int j = jsonarray.length();
        if(i < j) goto _L2; else goto _L1
_L1:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nSelAreaPosition, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d("ModifyOrderAddr", (new StringBuilder("which:")).append(k).toString());
                if(oldArea.compareTo(items[k]) == 0)
                {
                    nSelAreaPosition = k;
                    dialoginterface.dismiss();
                } else
                {
                    nSelAreaPosition = k;
                    setNewSelectArea(k);
                    dialoginterface.dismiss();
                }
            }

            final NewOrderAddressActivity this$0;
            private final String val$oldArea;

            
            {
                this$0 = NewOrderAddressActivity.this;
                oldArea = s;
                super();
            }
        }
).show();
        return;
_L2:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L1
    }

    private void createCityAlertDiglog(String s)
    {
        final String oldCity;
        new JSONArray();
        oldCity = mCity.getText().toString();
        JSONArray jsonarray;
        int i;
        jsonarray = jbCitys.getJSONArray("Areas");
        items = new String[jsonarray.length()];
        i = 0;
_L3:
        int j = jsonarray.length();
        if(i < j) goto _L2; else goto _L1
_L1:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nSelCityPosition, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d("ModifyOrderAddr", (new StringBuilder("which:")).append(k).toString());
                if(oldCity.compareTo(items[k]) == 0)
                {
                    nSelCityPosition = k;
                    nSelAreaPosition = 0;
                    dialoginterface.dismiss();
                } else
                {
                    bNew = true;
                    nSelCityPosition = k;
                    nSelAreaPosition = 0;
                    setNewSelectCity(k);
                    dialoginterface.dismiss();
                }
            }

            final NewOrderAddressActivity this$0;
            private final String val$oldCity;

            
            {
                this$0 = NewOrderAddressActivity.this;
                oldCity = s;
                super();
            }
        }
).show();
        return;
_L2:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L1
    }

    private void createProvinceAlertDiglog(String s)
    {
        final String oldProvince;
        new JSONArray();
        oldProvince = mProvince.getText().toString();
        JSONArray jsonarray;
        int i;
        jsonarray = jbProvinces.getJSONArray("Areas");
        items = new String[jsonarray.length()];
        i = 0;
_L3:
        int j = jsonarray.length();
        if(i < j) goto _L2; else goto _L1
_L1:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, nSelProvincePosition, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int k)
            {
                if(Log.D)
                    Log.d("ModifyOrderAddr", (new StringBuilder("which:")).append(k).toString());
                if(oldProvince.compareTo(items[k]) == 0)
                {
                    nSelProvincePosition = k;
                    nSelCityPosition = 0;
                    dialoginterface.dismiss();
                } else
                {
                    bNew = true;
                    nSelProvincePosition = k;
                    nSelCityPosition = 0;
                    setNewSelectProvince(k);
                    dialoginterface.dismiss();
                }
            }

            final NewOrderAddressActivity this$0;
            private final String val$oldProvince;

            
            {
                this$0 = NewOrderAddressActivity.this;
                oldProvince = s;
                super();
            }
        }
).show();
        return;
_L2:
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L1
    }

    private void getAddrViewText()
    {
        sUserName = mUserName.getText().toString();
        sMobile = mMobile.getText().toString();
        sProvince = mProvince.getText().toString();
        sCity = mCity.getText().toString();
        sArea = mArea.getText().toString();
        sWhere = (new StringBuilder(String.valueOf(sProvince))).append(sCity).append(sArea).append(mWhere.getText().toString()).toString();
        sZip = mZip.getText().toString();
        sMail = mMail.getText().toString();
    }

    private void getArea(int i)
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        jsonobject.put("action", "GetAreas");
        jsonobject.put("IdCity", String.valueOf(i));
        if(!Contants.bAddEasyBuy) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray = new JSONArray();
        JSONObject jsonobject1 = new JSONObject();
        jsonobject1.put("Id", "118846");
        jsonobject1.put("num", "2");
        jsonarray.put(jsonobject1);
        jsonobject.put("TheSkus", jsonarray);
_L4:
        setUpConnAndGetData("orderAddress", jsonobject, "GetAreas");
        return;
_L2:
        try
        {
            jsonobject.put("TheSkus", jbCartStr.getJSONArray("TheSkus"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        if(true) goto _L4; else goto _L3
_L3:
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
            break MISSING_BLOCK_LABEL_118;
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

    private void getCartItemInfo()
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

    private void getCity(int i)
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        jsonobject.put("action", "GetCitys");
        jsonobject.put("IdProvince", String.valueOf(i));
        if(!Contants.bAddEasyBuy) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray = new JSONArray();
        JSONObject jsonobject1 = new JSONObject();
        jsonobject1.put("Id", "118846");
        jsonobject1.put("num", "2");
        jsonarray.put(jsonobject1);
        jsonobject.put("TheSkus", jsonarray);
_L4:
        setUpConnAndGetData("orderAddress", jsonobject, "GetCitys");
        return;
_L2:
        try
        {
            jsonobject.put("TheSkus", jbCartStr.getJSONArray("TheSkus"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        if(true) goto _L4; else goto _L3
_L3:
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
            break MISSING_BLOCK_LABEL_118;
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

    private void getProvices()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        jsonobject.put("action", "GetProvinces");
        if(!Contants.bAddEasyBuy) goto _L2; else goto _L1
_L1:
        JSONArray jsonarray = new JSONArray();
        JSONObject jsonobject1 = new JSONObject();
        jsonobject1.put("Id", "118846");
        jsonobject1.put("num", "2");
        jsonarray.put(jsonobject1);
        jsonobject.put("TheSkus", jsonarray);
_L4:
        setUpConnAndGetData("orderAddress", jsonobject, "GetProvinces");
        return;
_L2:
        try
        {
            jsonobject.put("TheSkus", jbCartStr.getJSONArray("TheSkus"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        if(true) goto _L4; else goto _L3
_L3:
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
            break MISSING_BLOCK_LABEL_118;
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

    private void handleClickEvent()
    {
        mConfirmAddr.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                getAddrViewText();
                if(sUserName == null || sUserName == "" || sUserName == " " || sUserName.length() < 1)
                    Toast.makeText(NewOrderAddressActivity.this, "\u7528\u6237\u540D\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                else
                if(!CommonUtil.checkUsername(sUserName))
                    Toast.makeText(NewOrderAddressActivity.this, "\u7528\u6237\u540D\u53D6\u503C\u8303\u56F4\u4E3Aa-z,A-Z,0-9,'_',\u6C49\u5B57", 0).show();
                else
                if(sMobile == null || sMobile == "" || sMobile == " " || sMobile.length() < 1)
                    Toast.makeText(NewOrderAddressActivity.this, "\u7535\u8BDD\u53F7\u7801\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                else
                if(sWhere == null || sWhere == "" || sWhere == " " || sWhere.length() < 1)
                    Toast.makeText(NewOrderAddressActivity.this, "\u7528\u6237\u8BE6\u7EC6\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                else
                if(!CommonUtil.checkUsername(sWhere))
                {
                    Toast.makeText(NewOrderAddressActivity.this, "\u5730\u5740\u53D6\u503C\u8303\u56F4\u4E3Aa-z,A-Z,0-9,'_',\u6C49\u5B57", 0).show();
                } else
                {
                    updateUserInfo();
                    setResult(2);
                    finish();
                }
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
        mProvince.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mProvince2.performClick();
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
        mProvince2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createProvinceAlertDiglog("\u9009\u62E9\u7701\u4EFD");
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
        mCity.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mCity2.performClick();
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
        mCity2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createCityAlertDiglog("\u9009\u62E9\u57CE\u5E02");
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
        mArea.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mArea2.performClick();
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
        mArea2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createAreaAlertDiglog("\u9009\u62E9\u5730\u533A");
            }

            final NewOrderAddressActivity this$0;

            
            {
                this$0 = NewOrderAddressActivity.this;
                super();
            }
        }
);
    }

    private void handleDatas(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        try
        {
            if(s.compareTo("GetAreas") == 0)
            {
                jbAreas = httpresponse.getJSONObject().getJSONObject("provinceInfo");
                if(bNew)
                    nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(0).getInt("Id");
                setAddress(nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
                bNew = false;
                break MISSING_BLOCK_LABEL_196;
            }
            if(s.compareTo("GetCitys") == 0)
            {
                jbCitys = httpresponse.getJSONObject().getJSONObject("provinceInfo");
                if(bNew)
                    nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(0).getInt("Id");
                getArea(nSelCityIndex);
                break MISSING_BLOCK_LABEL_196;
            }
        }
        catch(JSONException jsonexception)
        {
            if(Log.D)
                Log.d(TAG, jsonexception.getMessage());
            break MISSING_BLOCK_LABEL_196;
        }
        if(s.compareTo("GetProvinces") == 0)
        {
            jbProvinces = httpresponse.getJSONObject().getJSONObject("provinceInfo");
            getCity(nSelProvinceIndex);
        }
    }

    private void initCoponent()
    {
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f0901b9);
        mConfirmAddr = (Button)findViewById(0x7f0c00be);
        mUserName = (EditText)findViewById(0x7f0c00d7);
        mMobile = (EditText)findViewById(0x7f0c00d9);
        mProvince = (TextView)findViewById(0x7f0c00db);
        mMail = (EditText)findViewById(0x7f0c00e6);
        mCity = (TextView)findViewById(0x7f0c00dd);
        mArea = (TextView)findViewById(0x7f0c00df);
        mWhere = (EditText)findViewById(0x7f0c00e2);
        mZip = (EditText)findViewById(0x7f0c00e4);
        mProvince2 = (ImageButton)findViewById(0x7f0c00dc);
        mCity2 = (ImageButton)findViewById(0x7f0c00de);
        mArea2 = (ImageButton)findViewById(0x7f0c00e0);
    }

    private void setAddress(final int province_code, final int city_code, final int area_code)
    {
        post(new Runnable() {

            public void run()
            {
                mProvince.setText(getProvincesName(province_code));
                mCity.setText(getCityName(city_code));
                mArea.setText(getAreaName(area_code));
            }

            final NewOrderAddressActivity this$0;
            private final int val$area_code;
            private final int val$city_code;
            private final int val$province_code;

            
            {
                this$0 = NewOrderAddressActivity.this;
                province_code = i;
                city_code = j;
                area_code = k;
                super();
            }
        }
);
    }

    private void setNewSelectArea(int i)
    {
        if(items[i].compareTo(jbAreas.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_164;
        nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(i).getInt("Id");
_L3:
        mArea.setText(getAreaName(nSelAreaIndex));
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbAreas.getJSONArray("Areas").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(j).getInt("Id");
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

    private void setNewSelectCity(int i)
    {
        if(items[i].compareTo(jbCitys.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_172;
        nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(i).getInt("Id");
_L3:
        mCity.setText(getCityName(nSelCityIndex));
        getArea(nSelCityIndex);
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbCitys.getJSONArray("Areas").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(j).getInt("Id");
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

    private void setNewSelectProvince(int i)
    {
        if(items[i].compareTo(jbProvinces.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_172;
        nSelProvinceIndex = jbProvinces.getJSONArray("Areas").getJSONObject(i).getInt("Id");
_L3:
        mProvince.setText(getProvincesName(nSelProvinceIndex));
        getCity(nSelProvinceIndex);
          goto _L1
_L6:
        int j;
        if(j >= items.length) goto _L3; else goto _L2
_L2:
        if(items[i].compareTo(jbProvinces.getJSONArray("Areas").getJSONObject(j).getString("Name")) != 0) goto _L5; else goto _L4
_L4:
        nSelProvinceIndex = jbProvinces.getJSONArray("Areas").getJSONObject(j).getInt("Id");
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
        getHttpGroupaAsynPool().add(s, jsonobject, new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

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

            final NewOrderAddressActivity this$0;
            private final String val$action;

            
            {
                this$0 = NewOrderAddressActivity.this;
                action = s;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002b);
        bNew = false;
        initCoponent();
        handleClickEvent();
        if(!Contants.bAddEasyBuy)
            getCartItemInfo();
        nSelProvinceIndex = 1;
        nSelCityIndex = 72;
        nSelAreaIndex = 2819;
        getProvices();
    }

    public void updateInvoinceInfo()
    {
    }

    public void updatePaymentInfo()
    {
    }

    public void updateUserInfo()
    {
        Contants.mModifiedUserInfo = new UserInfo();
        Contants.mModifiedUserInfo.setUserName(sUserName);
        Contants.mModifiedUserInfo.setUserMobile(sMobile);
        Contants.mModifiedUserInfo.setUserZip(sZip);
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("IdProvince", nSelProvinceIndex);
            jsonobject.put("IdCity", nSelCityIndex);
            jsonobject.put("IdArea", nSelAreaIndex);
            jsonobject.put("Where", sWhere);
            jsonobject.put("Email", sMail);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        Contants.mModifiedUserInfo.setUserAddr(jsonobject);
        Contants.jbAreas = jbAreas;
        Contants.jbCitys = jbCitys;
        Contants.jbProvinces = jbProvinces;
    }

    public void updateYouhuiInfo()
    {
    }

    private String TAG;
    ArrayList addrList;
    boolean bNew;
    DBHelperUtil dbCommonAddr;
    String items[];
    JSONObject jbAreas;
    JSONObject jbCartStr;
    JSONObject jbCitys;
    JSONObject jbProvinces;
    TextView mArea;
    ImageButton mArea2;
    TextView mCity;
    ImageButton mCity2;
    Button mConfirmAddr;
    EditText mMail;
    EditText mMobile;
    TextView mProvince;
    ImageButton mProvince2;
    TextView mTitle;
    EditText mUserName;
    EditText mWhere;
    EditText mZip;
    int nSelAreaIndex;
    int nSelAreaPosition;
    int nSelCityIndex;
    int nSelCityPosition;
    int nSelProvinceIndex;
    int nSelProvincePosition;
    private String sArea;
    private String sCity;
    private String sMail;
    private String sMobile;
    private String sProvince;
    private String sUserName;
    private String sWhere;
    private String sZip;















}
