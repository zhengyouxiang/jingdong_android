// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            UpdateOrderInfoInterface, OrderAdapter

public class ModifyOrderAddr extends MyActivity
    implements UpdateOrderInfoInterface
{

    public ModifyOrderAddr()
    {
        TAG = "ModifyOrderAddr";
        nSelProvinceIndex = 0;
        nSelCityIndex = 0;
        nSelAreaIndex = 0;
        nSelProvincePosition = 0;
        nSelCityPosition = 0;
        nSelAreaPosition = 0;
        items = null;
        comm = false;
        init = false;
        click = false;
    }

    private void addCommonAddr()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        if(sUserName != null && sUserName != "" && sUserName != " ") goto _L2; else goto _L1
_L1:
        Toast.makeText(this, "\u8BF7\u8F93\u5165\u7701\u4EFD", 0);
          goto _L3
_L2:
        jsonobject.put("Name", sUserName);
        if(sProvince != null && sProvince != "" && sProvince != " ") goto _L5; else goto _L4
_L4:
        Toast.makeText(this, "\u8BF7\u8F93\u5165\u7701\u4EFD", 0);
          goto _L3
        Exception exception;
        exception;
        exception.printStackTrace();
_L6:
        setUpConnAndGetData("addAddress", jsonobject, "addAddress");
        break; /* Loop/switch isn't completed */
_L5:
        jsonobject.put("IdProvince", getProvinceCode(sProvince));
        if(sCity == null || sCity == "" || sCity == " ")
        {
            Toast.makeText(this, "\u8BF7\u8F93\u5165\u57CE\u5E02", 0);
            break; /* Loop/switch isn't completed */
        }
        jsonobject.put("IdCity", getCityCode(sCity));
        if(sArea == null || sArea == "" || sArea == " ")
        {
            Toast.makeText(this, "\u8BF7\u8F93\u5165\u5730\u533A", 0);
            break; /* Loop/switch isn't completed */
        }
        jsonobject.put("IdArea", getAreaCode(sArea));
        if(sArea == null || sArea == "" || sArea == " ")
        {
            Toast.makeText(this, "\u8BF7\u8F93\u5165\u8BE6\u7EC6\u5730\u5740\uFF0C\u5982\u8857\u9053\uFF0C\u95E8\u724C\u53F7\u7B49", 0);
            break; /* Loop/switch isn't completed */
        }
        jsonobject.put("Where", sWhere);
        if(sZip == null || sZip == "" || sZip == " ")
        {
            Toast.makeText(this, "\u8BF7\u8F93\u5165\u90AE\u7F16", 0);
            break; /* Loop/switch isn't completed */
        }
        jsonobject.put("Zip", sZip);
        if(sMobile == null || sMobile == "" || sMobile == " ")
        {
            Toast.makeText(this, "\u8BF7\u8F93\u5165\u624B\u673A\u53F7\u7801", 0);
            break; /* Loop/switch isn't completed */
        }
        jsonobject.put("Mobile", sMobile);
        if(sMail == null || sMail == "" || sMail == " ")
        {
            Toast.makeText(this, "\u8BF7\u8F93\u5165\u90AE\u7BB1\u5730\u5740", 0);
            break; /* Loop/switch isn't completed */
        }
        jsonobject.put("Email", sMail);
        if(true) goto _L6; else goto _L3
_L3:
    }

    private void createAlertDiglog(String s, String s1)
    {
        new JSONArray();
        getType(s1);
        JVM INSTR tableswitch 1 3: default 40
    //                   1 81
    //                   2 170
    //                   3 259;
           goto _L1 _L2 _L3 _L4
_L1:
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(s);
        builder.setSingleChoiceItems(items, -1, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int l)
            {
                if(Log.D)
                    Log.d("ModifyOrderAddr", (new StringBuilder("which:")).append(l).toString());
                dialoginterface.dismiss();
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
).show();
        return;
_L2:
        JSONArray jsonarray2;
        int k;
        jsonarray2 = jbAreas.getJSONArray("Areas");
        items = new String[jsonarray2.length()];
        k = 0;
_L5:
        if(k >= jsonarray2.length())
            continue; /* Loop/switch isn't completed */
        items[k] = jsonarray2.getJSONObject(k).getString("Name");
        k++;
          goto _L5
        JSONException jsonexception2;
        jsonexception2;
        if(Log.D)
            Log.d(TAG, jsonexception2.getMessage());
        continue; /* Loop/switch isn't completed */
_L3:
        JSONArray jsonarray1;
        int j;
        jsonarray1 = jbCitys.getJSONArray("Areas");
        items = new String[jsonarray1.length()];
        j = 0;
_L6:
        if(j >= jsonarray1.length())
            continue; /* Loop/switch isn't completed */
        items[j] = jsonarray1.getJSONObject(j).getString("Name");
        j++;
          goto _L6
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        continue; /* Loop/switch isn't completed */
_L4:
        JSONArray jsonarray;
        int i;
        jsonarray = jbProvinces.getJSONArray("Areas");
        items = new String[jsonarray.length()];
        i = 0;
_L7:
        if(i >= jsonarray.length())
            continue; /* Loop/switch isn't completed */
        items[i] = jsonarray.getJSONObject(i).getString("Name");
        i++;
          goto _L7
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
        if(true) goto _L1; else goto _L8
_L8:
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
                    dialoginterface.dismiss();
                    nSelAreaPosition = k;
                } else
                {
                    nSelAreaPosition = k;
                    setNewSelectArea(k);
                    dialoginterface.dismiss();
                }
            }

            final ModifyOrderAddr this$0;
            private final String val$oldArea;

            
            {
                this$0 = ModifyOrderAddr.this;
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
                    dialoginterface.dismiss();
                    nSelCityPosition = k;
                    nSelAreaPosition = 0;
                } else
                {
                    bNew = true;
                    nSelCityPosition = k;
                    nSelAreaPosition = 0;
                    setNewSelectCity(k);
                    dialoginterface.dismiss();
                }
            }

            final ModifyOrderAddr this$0;
            private final String val$oldCity;

            
            {
                this$0 = ModifyOrderAddr.this;
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
                    dialoginterface.dismiss();
                    nSelProvincePosition = k;
                    nSelCityPosition = 0;
                } else
                {
                    bNew = true;
                    nSelCityPosition = 0;
                    nSelProvincePosition = k;
                    setNewSelectProvince(k);
                    dialoginterface.dismiss();
                }
            }

            final ModifyOrderAddr this$0;
            private final String val$oldProvince;

            
            {
                this$0 = ModifyOrderAddr.this;
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
        sWhere = (new StringBuilder(String.valueOf(mPCA.getText().toString()))).append(mWhere.getText().toString()).toString();
        sZip = mZip.getText().toString();
        sMail = mMail.getText().toString();
    }

    private void getArea(int i)
    {
        try
        {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("action", "GetAreas");
            jsonobject.put("IdCity", String.valueOf(i));
            if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
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

    private int getAreaCode(String s)
    {
        JSONArray jsonarray;
        int i;
        jsonarray = new JSONArray();
        i = 0;
        JSONArray jsonarray1 = jbAreas.getJSONArray("Areas");
        jsonarray = jsonarray1;
_L3:
        int j = 0;
_L4:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return i;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L3
_L2:
        int k;
        if(jsonarray.getJSONObject(j).getString("Name").compareTo(s) != 0)
            break MISSING_BLOCK_LABEL_121;
        k = jsonarray.getJSONObject(j).getInt("Id");
        i = k;
          goto _L1
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        j++;
          goto _L4
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
            break MISSING_BLOCK_LABEL_119;
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

    private void getCity(int i)
    {
        try
        {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("action", "GetCitys");
            jsonobject.put("IdProvince", String.valueOf(i));
            if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
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

    private int getCityCode(String s)
    {
        JSONArray jsonarray;
        int i;
        jsonarray = new JSONArray();
        i = 0;
        JSONArray jsonarray1 = jbCitys.getJSONArray("Areas");
        jsonarray = jsonarray1;
_L3:
        int j = 0;
_L4:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return i;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L3
_L2:
        int k;
        if(jsonarray.getJSONObject(j).getString("Name").compareTo(s) != 0)
            break MISSING_BLOCK_LABEL_121;
        k = jsonarray.getJSONObject(j).getInt("Id");
        i = k;
          goto _L1
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        j++;
          goto _L4
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
            break MISSING_BLOCK_LABEL_119;
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

    private void getCommonAddr()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        setUpConnAndGetData("getAddressByPin", jsonobject, "addressList");
    }

    private void getProvices()
    {
        try
        {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("action", "GetProvinces");
            if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
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

    private int getProvinceCode(String s)
    {
        JSONArray jsonarray;
        int i;
        jsonarray = new JSONArray();
        i = 0;
        JSONArray jsonarray1 = jbProvinces.getJSONArray("Areas");
        jsonarray = jsonarray1;
_L3:
        int j = 0;
_L4:
        if(j < jsonarray.length()) goto _L2; else goto _L1
_L1:
        return i;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L3
_L2:
        int k;
        if(jsonarray.getJSONObject(j).getString("Name").compareTo(s) != 0)
            break MISSING_BLOCK_LABEL_121;
        k = jsonarray.getJSONObject(j).getInt("Id");
        i = k;
          goto _L1
        JSONException jsonexception1;
        jsonexception1;
        if(Log.D)
            Log.d(TAG, jsonexception1.getMessage());
        j++;
          goto _L4
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
            break MISSING_BLOCK_LABEL_119;
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

    private int getType(String s)
    {
        int i;
        if(s.compareTo("GetAreas") == 0)
            i = 1;
        else
        if(s.compareTo("GetCitys") == 0)
            i = 2;
        else
        if(s.compareTo("GetProvinces") == 0)
            i = 3;
        else
        if(s.compareTo("addressList") == 0)
            i = 4;
        else
        if(s.compareTo("addAddress") == 0)
            i = 5;
        else
            i = -1;
        return i;
    }

    private void handleClickEvent()
    {
        mConfirmAddr.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                getAddrViewText();
                if(sUserName == null || sUserName == "" || sUserName == " " || sUserName.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u586B\u5199\u6536\u8D27\u4EBA\u59D3\u540D!", 0).show();
                else
                if(!CommonUtil.checkUsername(sUserName))
                    Toast.makeText(ModifyOrderAddr.this, "\u60A8\u8F93\u5165\u7684\u6536\u8D27\u4EBA\u59D3\u540D\u542B\u6709\u7279\u6B8A\u5B57\u7B26\uFF0C\u8BF7\u8F93\u5165\u82F1\u6587\u5B57\u7B26\uFF0C\u4E2D\u6587\u6216\u8005\u6570\u5B57", 0).show();
                else
                if(sProvince == null || sProvince == "" || sProvince == " " || sProvince.length() < 1 || sProvince.compareTo("\u9009\u62E9\u7701\u4EFD") == 0)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u9009\u62E9\u6536\u8D27\u7701\u4EFD!", 0).show();
                else
                if(sCity == null || sCity == "" || sCity == " " || sCity.length() < 1 || sCity.compareTo("\u9009\u62E9\u57CE\u5E02") == 0)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u9009\u62E9\u6536\u8D27\u57CE\u5E02!", 0).show();
                else
                if(sArea == null || sArea == "" || sArea == " " || sArea.length() < 1 || sArea.compareTo("\u9009\u62E9\u5730\u533A") == 0)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u9009\u62E9\u6536\u8D27\u5730\u533A!", 0).show();
                else
                if(sMobile == null || sMobile == "" || sMobile == " " || sMobile.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u8F93\u5165\u7535\u8BDD\u53F7\u7801", 0).show();
                else
                if(sWhere == null || sWhere == "" || sWhere == " " || sWhere.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                else
                if(!"".equals(mWhere.getText().toString().trim()))
                {
                    if(!CommonUtil.checkAddrWithSpace(mWhere.getText().toString()))
                        Toast.makeText(ModifyOrderAddr.this, "\u5730\u5740\u542B\u6709\u7279\u6B8A\u5B57\u7B26\uFF0C\u8BF7\u8F93\u5165\u82F1\u6587\u5B57\u7B26\uFF0C\u4E2D\u6587\u6216\u8005\u6570\u5B57", 0).show();
                    else
                    if(sMail != "" && sMail != " " && sMail.length() > 0 && !CommonUtil.checkEmailWithSuffix(sMail))
                    {
                        Toast.makeText(ModifyOrderAddr.this, "\u60A8\u8F93\u5165\u90AE\u7BB1\u5730\u5740\u683C\u5F0F\u4E0D\u5BF9", 0).show();
                    } else
                    {
                        updateUserInfo();
                        setResult(1);
                        finish();
                    }
                } else
                {
                    Toast.makeText(ModifyOrderAddr.this, "\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                }
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mAdd.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                getAddrViewText();
                if(sUserName == null || sUserName == "" || sUserName == " " || sUserName.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u586B\u5199\u6536\u8D27\u4EBA\u59D3\u540D!", 0).show();
                else
                if(!CommonUtil.checkUsername(sUserName))
                    Toast.makeText(ModifyOrderAddr.this, "\u60A8\u8F93\u5165\u7684\u6536\u8D27\u4EBA\u59D3\u540D\u542B\u6709\u7279\u6B8A\u5B57\u7B26\uFF0C\u8BF7\u8F93\u5165\u82F1\u6587\u5B57\u7B26\uFF0C\u4E2D\u6587\u6216\u8005\u6570\u5B57", 0).show();
                else
                if(sProvince == null || sProvince == "" || sProvince == " " || sProvince.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u9009\u62E9\u6536\u8D27\u7701\u4EFD!", 0).show();
                else
                if(sCity == null || sCity == "" || sCity == " " || sCity.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u9009\u62E9\u6536\u8D27\u57CE\u5E02!", 0).show();
                else
                if(sArea == null || sArea == "" || sArea == " " || sArea.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u9009\u62E9\u6536\u8D27\u5730\u533A!", 0).show();
                else
                if(sMobile == null || sMobile == "" || sMobile == " " || sMobile.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u8BF7\u8F93\u5165\u7535\u8BDD\u53F7\u7801", 0).show();
                else
                if(sWhere == null || sWhere == "" || sWhere == " " || sWhere.length() < 1)
                    Toast.makeText(ModifyOrderAddr.this, "\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                else
                if(!"".equals(mWhere.getText().toString().trim()))
                {
                    if(!CommonUtil.checkAddrWithSpace(mWhere.getText().toString()))
                        Toast.makeText(ModifyOrderAddr.this, "\u5730\u5740\u542B\u6709\u7279\u6B8A\u5B57\u7B26\uFF0C\u8BF7\u8F93\u5165\u82F1\u6587\u5B57\u7B26\uFF0C\u4E2D\u6587\u6216\u8005\u6570\u5B57", 0).show();
                    else
                    if(sMail != "" && sMail != " " && sMail.length() > 0 && !CommonUtil.checkEmailWithSuffix(sMail))
                        Toast.makeText(ModifyOrderAddr.this, "\u60A8\u8F93\u5165\u90AE\u7BB1\u5730\u5740\u683C\u5F0F\u4E0D\u5BF9", 0).show();
                    else
                        addCommonAddr();
                } else
                {
                    Toast.makeText(ModifyOrderAddr.this, "\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
                }
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mProvince.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mProvince2.performClick();
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mProvince2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createProvinceAlertDiglog("\u9009\u62E9\u7701\u4EFD");
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mCity.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mCity2.performClick();
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mCity2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createCityAlertDiglog("\u9009\u62E9\u57CE\u5E02");
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mArea.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mArea2.performClick();
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        mArea2.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                createAreaAlertDiglog("\u9009\u62E9\u5730\u533A");
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
    }

    private void handleDatas(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        if(s.compareTo("GetAreas") != 0) goto _L2; else goto _L1
_L1:
        jbAreas = httpresponse.getJSONObject().getJSONObject("provinceInfo");
        if(!comm) goto _L4; else goto _L3
_L3:
        if(jbAreas == null || jbAreas.getJSONArray("Areas") == null) goto _L6; else goto _L5
_L5:
        int j = 0;
_L10:
        if(j < jbAreas.getJSONArray("Areas").length()) goto _L7; else goto _L6
_L6:
        setAddress(nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
          goto _L8
_L7:
        if(nSelAreaIndex != jbAreas.getJSONArray("Areas").getJSONObject(j).getInt("Id"))
            break; /* Loop/switch isn't completed */
        nSelAreaPosition = j;
        if(true) goto _L6; else goto _L9
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L8
_L9:
        j++;
          goto _L10
_L4:
        if(bNew)
            nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(0).getInt("Id");
        setAddress(nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
        getCommonAddr();
        bNew = false;
          goto _L8
_L2:
        if(s.compareTo("GetCitys") != 0) goto _L12; else goto _L11
_L11:
        jbCitys = httpresponse.getJSONObject().getJSONObject("provinceInfo");
        if(jbCitys.get("Areas").toString() != "null" && jbCitys.get("Areas") != null) goto _L14; else goto _L13
_L13:
        nSelCityIndex = -1;
        nSelAreaIndex = -1;
        setAddress(nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
          goto _L8
_L14:
        if(!comm) goto _L16; else goto _L15
_L15:
        if(jbCitys == null || jbCitys.getJSONArray("Areas") == null) goto _L18; else goto _L17
_L17:
        int i = 0;
_L19:
        if(i < jbCitys.getJSONArray("Areas").length())
        {
            if(nSelCityIndex != jbCitys.getJSONArray("Areas").getJSONObject(i).getInt("Id"))
                break MISSING_BLOCK_LABEL_561;
            nSelCityPosition = i;
        }
_L18:
        getArea(nSelCityIndex);
        break; /* Loop/switch isn't completed */
_L16:
        if(bNew)
            nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(0).getInt("Id");
        if(true) goto _L18; else goto _L8
_L12:
        if(s.compareTo("GetProvinces") == 0)
        {
            jbProvinces = httpresponse.getJSONObject().getJSONObject("provinceInfo");
            getCity(nSelProvinceIndex);
            getCommonAddr();
        } else
        if(s.compareTo("addAddress") == 0)
        {
            JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject().getJSONObject("addAddress");
            if(jsonobjectproxy.getBoolean("Flag"))
            {
                makeToast("\u5E38\u7528\u5730\u5740\u6DFB\u52A0\u6210\u529F");
                getCommonAddr();
            } else
            {
                makeToast(jsonobjectproxy.getString("Message"));
            }
        } else
        if(s.compareTo("addressList") == 0)
        {
            jbCommAddrUsed = httpresponse.getJSONObject().getJSONArray("addressList");
            updateCommAddrView();
        }
_L8:
        return;
        i++;
          goto _L19
    }

    private void initCoponent()
    {
        mConfirmAddr = (Button)findViewById(0x7f0c0177);
        mUserName = (EditText)findViewById(0x7f0c00d7);
        mMobile = (EditText)findViewById(0x7f0c00d9);
        mProvince = (TextView)findViewById(0x7f0c00db);
        mCity = (TextView)findViewById(0x7f0c00dd);
        mArea = (TextView)findViewById(0x7f0c00df);
        mWhere = (EditText)findViewById(0x7f0c00e2);
        mPCA = (TextView)findViewById(0x7f0c0172);
        mZip = (EditText)findViewById(0x7f0c00e4);
        mMail = (EditText)findViewById(0x7f0c0174);
        mAdd = (ImageButton)findViewById(0x7f0c0175);
        if(Contants.bAddEasyBuy || Contants.bModifyEasyBuy)
        {
            mAdd.setVisibility(8);
            ((TextView)findViewById(0x7f0c0176)).setVisibility(8);
        }
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mProvince2 = (ImageButton)findViewById(0x7f0c00dc);
        mCity2 = (ImageButton)findViewById(0x7f0c00de);
        mArea2 = (ImageButton)findViewById(0x7f0c00e0);
        if(LastOrderInfo.mUserInfo.getUserAddr() == null)
            break MISSING_BLOCK_LABEL_408;
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdProvince")) goto _L2; else goto _L1
_L1:
        nSelProvinceIndex = LastOrderInfo.mUserInfo.getUserAddr().getInt("IdProvince");
_L7:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdCity")) goto _L4; else goto _L3
_L3:
        nSelCityIndex = LastOrderInfo.mUserInfo.getUserAddr().getInt("IdCity");
_L9:
        if(!LastOrderInfo.mUserInfo.getUserAddr().toString().contains("IdArea")) goto _L6; else goto _L5
_L5:
        nSelAreaIndex = LastOrderInfo.mUserInfo.getUserAddr().getInt("IdArea");
_L8:
        mWhere.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        return;
_L2:
        nSelProvinceIndex = 1;
          goto _L7
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L8
_L4:
        nSelCityIndex = 72;
          goto _L9
_L6:
        nSelAreaIndex = 2819;
          goto _L8
        nSelProvinceIndex = 1;
        nSelCityIndex = 72;
        nSelAreaIndex = 2819;
          goto _L8
    }

    private void makeToast(final String mesg)
    {
        post(new Runnable() {

            public void run()
            {
                Toast.makeText(ModifyOrderAddr.this, mesg, 0).show();
            }

            final ModifyOrderAddr this$0;
            private final String val$mesg;

            
            {
                this$0 = ModifyOrderAddr.this;
                mesg = s;
                super();
            }
        }
);
    }

    private void queryCommUsedAddr()
    {
        dbCommonAddr = new DBHelperUtil(this);
        addrList = dbCommonAddr.getCommAddrList();
        int i = 0;
        do
        {
            if(i >= addrList.size())
                return;
            ((CommAddr)addrList.get(i)).sComUsedAddr = (new StringBuilder(String.valueOf(((CommAddr)addrList.get(i)).sUser_name))).append(",").append(((CommAddr)addrList.get(i)).sMobile).append(",").append(((CommAddr)addrList.get(i)).sProvince).append(",").append(((CommAddr)addrList.get(i)).sCity).append(",").append(((CommAddr)addrList.get(i)).sArea).append(",").append(((CommAddr)addrList.get(i)).sZip).append(",").append(((CommAddr)addrList.get(i)).sMail).toString();
            ((CommAddr)addrList.get(i)).isChecked = Boolean.valueOf(false);
            i++;
        } while(true);
    }

    private void saveCommUsedAddr()
    {
        dbCommonAddr = new DBHelperUtil(this);
        dbCommonAddr.insertCommAddr(sUserName, sMobile, sProvince, sCity, sArea, sWhere, sZip, sMail, nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
    }

    private void setAddrListArea()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c0171);
        RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030049, null).findViewById(0x7f0c016f);
        commonUsedAddrInfoList = (ListView)relativelayout1.getChildAt(0);
        OrderAdapter orderadapter = new OrderAdapter(this, addrList);
        commonUsedAddrInfoList.setAdapter(orderadapter);
        commonUsedAddrInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
        commonUsedAddrInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                mUserName.setText(((CommAddr)addrList.get(i)).sUser_name);
                mMobile.setText(((CommAddr)addrList.get(i)).sMobile);
                mProvince.setText(((CommAddr)addrList.get(i)).sProvince);
                mCity.setText(((CommAddr)addrList.get(i)).sCity);
                mArea.setText(((CommAddr)addrList.get(i)).sArea);
                mPCA.setText((new StringBuilder(String.valueOf(((CommAddr)addrList.get(i)).sProvince))).append(((CommAddr)addrList.get(i)).sCity).append(((CommAddr)addrList.get(i)).sArea).toString());
                try
                {
                    if(init)
                    {
                        init = false;
                        String s = (new StringBuilder(String.valueOf(((CommAddr)addrList.get(i)).sProvince))).append(((CommAddr)addrList.get(i)).sCity).append(((CommAddr)addrList.get(i)).sArea).toString();
                        mWhere.setText(((CommAddr)addrList.get(i)).sWhere.substring(s.length()));
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                mZip.setText(((CommAddr)addrList.get(i)).sZip);
                mMail.setText(((CommAddr)addrList.get(i)).sMail);
                nSelProvinceIndex = ((CommAddr)addrList.get(i)).province_code;
                nSelCityIndex = ((CommAddr)addrList.get(i)).city_code;
                nSelAreaIndex = ((CommAddr)addrList.get(i)).area_code;
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
    }

    private void setAddrView()
    {
        mUserName.setText(LastOrderInfo.mUserInfo.getUserName());
        mMobile.setText(LastOrderInfo.mUserInfo.getUserMobile());
        mZip.setText(LastOrderInfo.mUserInfo.getUserZip());
        mMail.setText(LastOrderInfo.mUserInfo.getUserAddr().getString("Email"));
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void setAddrView(JSONObject jsonobject)
    {
        mUserName.setText(jsonobject.getString("Name"));
        mMobile.setText(jsonobject.getString("Mobile"));
        mProvince.setText(getProvincesName(jsonobject.getInt("IdProvince")));
        mCity.setText(getCityName(jsonobject.getInt("IdCity")));
        mArea.setText(getCityName(jsonobject.getInt("IdArea")));
        mWhere.setText(jsonobject.getString("Where"));
        mZip.setText(jsonobject.getString("Zip"));
        mMail.setText(jsonobject.getString("Email"));
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void setAddrView2(JSONObject jsonobject, int i)
    {
        comm = true;
        mUserName.setText(jsonobject.getString("Name"));
        mMobile.setText(jsonobject.getString("Mobile"));
        mZip.setText(jsonobject.getString("Zip"));
        mMail.setText(jsonobject.getString("Email"));
        nSelProvinceIndex = jsonobject.getInt("IdProvince");
        nSelCityIndex = jsonobject.getInt("IdCity");
        nSelAreaIndex = jsonobject.getInt("IdArea");
        if(jbProvinces == null || jbProvinces.get("Areas").toString() == "null" || jbProvinces.get("Areas") == null) goto _L2; else goto _L1
_L1:
        if(!comm) goto _L4; else goto _L3
_L3:
        if(jbProvinces == null || jbProvinces.getJSONArray("Areas") == null) goto _L2; else goto _L5
_L5:
        int j = 0;
_L12:
        if(j < jbProvinces.getJSONArray("Areas").length()) goto _L6; else goto _L2
_L2:
        getCity(nSelProvinceIndex);
          goto _L7
_L6:
        if(nSelProvinceIndex != jbProvinces.getJSONArray("Areas").getJSONObject(j).getInt("Id")) goto _L9; else goto _L8
_L8:
        nSelProvincePosition = j;
          goto _L2
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L7
_L9:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        if(!bNew) goto _L2; else goto _L10
_L10:
        nSelProvinceIndex = jbProvinces.getJSONArray("Areas").getJSONObject(0).getInt("Id");
          goto _L2
_L7:
        return;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void setAddress(final int province_code, final int city_code, final int area_code)
    {
        post(new Runnable() {

            public void run()
            {
                String s2;
                if(province_code == -1)
                {
                    mProvince.setText("\u9009\u62E9\u7701\u4EFD");
                    mProvince.setEnabled(false);
                    mProvince2.setEnabled(false);
                    mCity.setText("\u9009\u62E9\u57CE\u5E02");
                    mCity.setEnabled(false);
                    mCity2.setEnabled(false);
                    mArea.setText("\u9009\u62E9\u5730\u533A");
                    mArea.setEnabled(false);
                    mArea2.setEnabled(false);
                    mPCA.setText("");
                } else
                {
label0:
                    {
                        if(city_code == -1)
                        {
                            mProvince.setEnabled(true);
                            mProvince2.setEnabled(true);
                            mCity.setEnabled(false);
                            mCity2.setEnabled(false);
                            mArea.setEnabled(false);
                            mArea2.setEnabled(false);
                            JSONException jsonexception;
                            if(getProvincesName(province_code) == " ")
                            {
                                mProvince.setText("\u9009\u62E9\u7701\u4EFD");
                                mCity.setText("\u9009\u62E9\u57CE\u5E02");
                                mArea.setText("\u9009\u62E9\u5730\u533A");
                            } else
                            {
                                mProvince.setText(getProvincesName(province_code));
                                mCity.setText("");
                                mArea.setText("");
                            }
                        } else
                        if(province_code != -1 && city_code != -1 && area_code == -1)
                        {
                            mProvince.setEnabled(true);
                            mProvince2.setEnabled(true);
                            mCity.setEnabled(true);
                            mCity2.setEnabled(true);
                            mArea.setEnabled(false);
                            mArea2.setEnabled(false);
                            if(getProvincesName(province_code) == " ")
                            {
                                mProvince.setText("\u9009\u62E9\u7701\u4EFD");
                                mCity.setText("\u9009\u62E9\u57CE\u5E02");
                                mArea.setText("\u9009\u62E9\u5730\u533A");
                            } else
                            {
                                mProvince.setText(getProvincesName(province_code));
                                mCity.setText(getCityName(city_code));
                                mArea.setText("");
                            }
                        } else
                        if(province_code != -1 && city_code != -1 && area_code != -1)
                        {
                            mProvince.setEnabled(true);
                            mProvince2.setEnabled(true);
                            mCity.setEnabled(true);
                            mCity2.setEnabled(true);
                            mArea.setEnabled(true);
                            mArea2.setEnabled(true);
                            if(getProvincesName(province_code) == " ")
                            {
                                mProvince.setText("\u9009\u62E9\u7701\u4EFD");
                                mCity.setText("\u9009\u62E9\u57CE\u5E02");
                                mArea.setText("\u9009\u62E9\u5730\u533A");
                            } else
                            {
                                mProvince.setText(getProvincesName(province_code));
                                mCity.setText(getCityName(city_code));
                                mArea.setText(getAreaName(area_code));
                            }
                        } else
                        if(getProvincesName(province_code) == " ")
                        {
                            mProvince.setText("\u9009\u62E9\u7701\u4EFD");
                            mCity.setText("\u9009\u62E9\u57CE\u5E02");
                            mArea.setText("\u9009\u62E9\u5730\u533A");
                        } else
                        {
                            mProvince.setText(getProvincesName(province_code));
                            mCity.setText(getCityName(city_code));
                            mArea.setText(getAreaName(area_code));
                        }
                        try
                        {
                            if(!init)
                                break MISSING_BLOCK_LABEL_1167;
                            init = false;
                            s2 = "";
                            if(province_code != -1)
                                break label0;
                            mPCA.setText(s2);
                        }
                        // Misplaced declaration of an exception variable
                        catch(JSONException jsonexception)
                        {
                            jsonexception.printStackTrace();
                        }
                    }
                }
_L1:
                return;
                if(city_code == -1)
                {
                    mProvince.setEnabled(true);
                    s2 = getProvincesName(province_code);
                } else
                if(province_code != -1 && city_code != -1 && area_code == -1)
                    s2 = (new StringBuilder(String.valueOf(getProvincesName(province_code)))).append(getCityName(city_code)).toString();
                else
                if(province_code != -1 && city_code != -1 && area_code != -1)
                    s2 = (new StringBuilder(String.valueOf(getProvincesName(province_code)))).append(getCityName(city_code)).append(getAreaName(area_code)).toString();
                mPCA.setText(s2);
                if(LastOrderInfo.mUserInfo.getUserAddr().getString("Where").contains(s2))
                    mWhere.setText(LastOrderInfo.mUserInfo.getUserAddr().getString("Where").substring(s2.length()));
                else
                    mWhere.setText(LastOrderInfo.mUserInfo.getUserAddr().getString("Where"));
                  goto _L1
                if(comm)
                {
                    comm = false;
                    String s1 = "";
                    if(getProvincesName(jbSelCommAddr.getInt("IdProvince")) == " ")
                    {
                        mPCA.setText(s1);
                    } else
                    {
                        if(getCityName(jbSelCommAddr.getInt("IdCity")) == " ")
                            s1 = getProvincesName(jbSelCommAddr.getInt("IdProvince"));
                        else
                        if(getProvincesName(jbSelCommAddr.getInt("IdProvince")) != " " && getCityName(jbSelCommAddr.getInt("IdCity")) != " " && getAreaName(jbSelCommAddr.getInt("IdArea")) == " ")
                            s1 = (new StringBuilder(String.valueOf(getProvincesName(jbSelCommAddr.getInt("IdProvince"))))).append(getCityName(jbSelCommAddr.getInt("IdCity"))).toString();
                        else
                        if(getProvincesName(jbSelCommAddr.getInt("IdProvince")) != " " && getCityName(jbSelCommAddr.getInt("IdCity")) != " " && getAreaName(jbSelCommAddr.getInt("IdArea")) != " ")
                            s1 = (new StringBuilder(String.valueOf(getProvincesName(jbSelCommAddr.getInt("IdProvince"))))).append(getCityName(jbSelCommAddr.getInt("IdCity"))).append(getAreaName(jbSelCommAddr.getInt("IdArea"))).toString();
                        mPCA.setText(s1);
                        if(Log.D)
                            Log.d("where", jbSelCommAddr.getString("Where"));
                        jbSelCommAddr.getString("Where").trim();
                        if(jbSelCommAddr.getString("Where").contains(s1))
                            mWhere.setText(jbSelCommAddr.getString("Where").substring(s1.length()));
                        else
                            mWhere.setText(jbSelCommAddr.getString("Where"));
                    }
                } else
                if(click)
                {
                    String s = (new StringBuilder(String.valueOf(mProvince.getText().toString()))).append(mCity.getText().toString()).append(mArea.getText().toString()).toString();
                    mPCA.setText(s);
                    click = false;
                }
                  goto _L1
            }

            final ModifyOrderAddr this$0;
            private final int val$area_code;
            private final int val$city_code;
            private final int val$province_code;

            
            {
                this$0 = ModifyOrderAddr.this;
                province_code = i;
                city_code = j;
                area_code = k;
                super();
            }
        }
);
    }

    private void setCommAddrListView()
    {
        if(addrList.size() < 1)
        {
            mTitle.setText(getApplicationContext().getString(0x7f0901b9));
        } else
        {
            mTitle.setText(getApplicationContext().getString(0x7f0901b8));
            setAddrListArea();
        }
    }

    private void setNewSelectArea(int i)
    {
        if(items[i].compareTo(jbAreas.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_191;
        nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(i).getInt("Id");
_L3:
        mArea.setText(getAreaName(nSelAreaIndex));
        click = true;
        setAddress(nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
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
            break MISSING_BLOCK_LABEL_183;
        nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(i).getInt("Id");
_L3:
        mCity.setText(getCityName(nSelCityIndex));
        click = true;
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
            break MISSING_BLOCK_LABEL_183;
        nSelProvinceIndex = jbProvinces.getJSONArray("Areas").getJSONObject(i).getInt("Id");
_L3:
        mProvince.setText(getProvincesName(nSelProvinceIndex));
        click = true;
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

    private void setNewSelectProvince1(int i, int j)
    {
        j;
        JVM INSTR tableswitch 1 3: default 28
    //                   1 335
    //                   2 204
    //                   3 47;
           goto _L1 _L2 _L3 _L4
_L1:
        setAddress(nSelProvinceIndex, nSelCityIndex, nSelAreaIndex);
          goto _L5
_L4:
        if(items[i].compareTo(jbProvinces.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0) goto _L7; else goto _L6
_L6:
        nSelProvinceIndex = jbProvinces.getJSONArray("Areas").getJSONObject(i).getInt("Id");
          goto _L1
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(TAG, jsonexception.getMessage());
          goto _L5
_L7:
        int i1 = 0;
_L20:
        if(i1 >= items.length) goto _L1; else goto _L8
_L8:
        if(items[i1].compareTo(jbProvinces.getJSONArray("Areas").getJSONObject(i1).getString("Name")) != 0) goto _L10; else goto _L9
_L9:
        nSelProvinceIndex = jbProvinces.getJSONArray("Areas").getJSONObject(i1).getInt("Id");
          goto _L1
_L3:
        if(items[i].compareTo(jbCitys.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0) goto _L12; else goto _L11
_L11:
        nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(i).getInt("Id");
          goto _L1
_L21:
        int l;
        if(l >= items.length) goto _L1; else goto _L13
_L13:
        if(items[l].compareTo(jbCitys.getJSONArray("Areas").getJSONObject(l).getString("Name")) != 0) goto _L15; else goto _L14
_L14:
        nSelCityIndex = jbCitys.getJSONArray("Areas").getJSONObject(l).getInt("Id");
          goto _L1
_L2:
        if(items[i].compareTo(jbAreas.getJSONArray("Areas").getJSONObject(i).getString("Name")) != 0)
            break MISSING_BLOCK_LABEL_487;
        nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(i).getInt("Id");
          goto _L1
_L19:
        int k;
        if(k >= items.length) goto _L1; else goto _L16
_L16:
        if(items[k].compareTo(jbAreas.getJSONArray("Areas").getJSONObject(k).getString("Name")) != 0) goto _L18; else goto _L17
_L17:
        nSelAreaIndex = jbAreas.getJSONArray("Areas").getJSONObject(k).getInt("Id");
          goto _L1
_L18:
        k++;
          goto _L19
_L5:
        return;
_L10:
        i1++;
          goto _L20
_L12:
        l = 0;
          goto _L21
_L15:
        l++;
          goto _L21
        k = 0;
          goto _L19
    }

    private void setReceiverInfoArea()
    {
        getAddrViewText();
        if(Log.D)
            Log.d("test", "ui2");
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0c0171);
        RelativeLayout relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030049, null).findViewById(0x7f0c016f);
        commonUsedAddrInfoList = (ListView)relativelayout1.getChildAt(0);
        List list = value;
        String as[] = new String[1];
        as[0] = "addr";
        int ai[] = new int[1];
        ai[0] = 0x7f0c016e;
        SimpleAdapter simpleadapter = new SimpleAdapter(this, list, 0x7f030048, as, ai);
        commonUsedAddrInfoList.setAdapter(simpleadapter);
        if(commonUsedAddrInfoList.getCount() < 4)
            commonUsedAddrInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, -2));
        else
            commonUsedAddrInfoList.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, 160));
        relativelayout.removeAllViews();
        relativelayout.addView(relativelayout1);
        commonUsedAddrInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
            }

            final ModifyOrderAddr this$0;

            
            {
                this$0 = ModifyOrderAddr.this;
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

            final ModifyOrderAddr this$0;
            private final String val$action;

            
            {
                this$0 = ModifyOrderAddr.this;
                action = s;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void updateCommAddrView()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(jbCommAddrUsed != null && jbCommAddrUsed.length() >= 1) goto _L2; else goto _L1
_L1:
                mTitle.setText(getApplicationContext().getString(0x7f0901b9));
_L5:
                return;
_L2:
                int i;
                LayoutInflater layoutinflater;
                RelativeLayout relativelayout;
                RelativeLayout relativelayout1;
                int j;
                if(jbCommAddrUsed.length() < 1)
                    mTitle.setText(getApplicationContext().getString(0x7f0901b9));
                else
                    mTitle.setText(getApplicationContext().getString(0x7f0901b8));
                layoutinflater = LayoutInflater.from(ModifyOrderAddr.this);
                relativelayout = (RelativeLayout)findViewById(0x7f0c0171);
                relativelayout1 = (RelativeLayout)layoutinflater.inflate(0x7f030017, null).findViewById(0x7f0c006e);
                mAddrListGroup = (RadioGroup)relativelayout1.getChildAt(0);
                i = 0;
_L13:
                j = jbCommAddrUsed.length();
                if(i < j) goto _L4; else goto _L3
_L3:
                relativelayout.removeAllViews();
                relativelayout.addView(relativelayout1);
                  goto _L5
_L4:
                RadioButton radiobutton;
                JSONObject jsonobject;
                String s;
                String s1;
                String s2;
                String s3;
                String s4;
                String s5;
                String s6;
                String s7;
                radiobutton = new RadioButton(ModifyOrderAddr.this);
                jsonobject = jbCommAddrUsed.getJSONObject(i);
                s = null;
                s1 = null;
                s2 = null;
                s3 = null;
                s4 = null;
                s5 = null;
                s6 = null;
                s7 = null;
                if(jsonobject.toString().contains("Name"))
                    s = jsonobject.getString("Name");
                  goto _L6
_L14:
                if(jsonobject.toString().contains("IdProvince"))
                    s1 = getProvincesName(jsonobject.getInt("IdProvince"));
                  goto _L7
_L15:
                if(jsonobject.toString().contains("IdCity"))
                    s2 = getCityName(jsonobject.getInt("IdCity"));
                  goto _L8
_L16:
                if(jsonobject.toString().contains("IdArea"))
                    s3 = getAreaName(jsonobject.getInt("IdArea"));
                  goto _L9
_L17:
                if(jsonobject.toString().contains("Mobile"))
                    s4 = jsonobject.getString("Mobile");
                  goto _L10
_L18:
                if(jsonobject.toString().contains("Where"))
                    s5 = jsonobject.getString("Where");
                  goto _L11
_L19:
                if(jsonobject.toString().contains("Zip"))
                    s6 = jsonobject.getString("Zip");
                  goto _L12
_L20:
                if(jsonobject.toString().contains(""))
                    s7 = jsonobject.getString("Email");
                break MISSING_BLOCK_LABEL_786;
_L21:
                final int index = i;
                android.view.View.OnClickListener onclicklistener;
                if(s5.contains(s1) && s5.contains(s2) && s5.contains(s3))
                    radiobutton.setText((new StringBuilder("  ")).append(s).append(" ").append(s5).append(s4).append(s7).append(s6).toString());
                else
                    radiobutton.setText((new StringBuilder("  ")).append(s).append(" ").append(s1).append(s2).append(s3).append(s5).append(s4).append(s7).append(s6).toString());
                onclicklistener = new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        jbSelCommAddr = jbCommAddrUsed.getJSONObject(index);
                        setAddrView2(jbCommAddrUsed.getJSONObject(index), index);
                        if(Log.D)
                            Log.d(TAG, jbCommAddrUsed.getJSONObject(index).toString());
_L1:
                        return;
                        JSONException jsonexception1;
                        jsonexception1;
                        jsonexception1.printStackTrace();
                          goto _L1
                    }

                    final _cls18 this$1;
                    private final int val$index;

                    
                    {
                        this$1 = _cls18.this;
                        index = i;
                        super();
                    }
                }
;
                radiobutton.setOnClickListener(onclicklistener);
                radiobutton.setSingleLine();
                radiobutton.setEms(16);
                radiobutton.setEllipsize(android.text.TextUtils.TruncateAt.END);
                radiobutton.setButtonDrawable(0x7f02006c);
                mAddrListGroup.addView(radiobutton);
                i++;
                  goto _L13
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d(TAG, jsonexception.getMessage());
                  goto _L3
_L6:
                if(s == null)
                    s = "";
                  goto _L14
_L7:
                if(s1 == null)
                    s1 = "";
                  goto _L15
_L8:
                if(s2 == null)
                    s2 = "";
                  goto _L16
_L9:
                if(s3 == null)
                    s3 = "";
                  goto _L17
_L10:
                if(s4 == null)
                    s4 = "";
                  goto _L18
_L11:
                if(s5 == null)
                    s5 = "";
                  goto _L19
_L12:
                if(s6 == null)
                    s6 = "";
                  goto _L20
                if(s7 == null)
                    s7 = "";
                  goto _L21
            }

            final ModifyOrderAddr this$0;


            
            {
                this$0 = ModifyOrderAddr.this;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004a);
        addrList = new ArrayList();
        jbProvinces = new JSONObject();
        jbCitys = new JSONObject();
        jbAreas = new JSONObject();
        jbSelCommAddr = new JSONObject();
        jbCommAddrUsed = new JSONArray();
        jbCommAddrGeneral = new JSONArray();
        bNew = false;
        init = true;
        initCoponent();
        setAddrView();
        if(!Contants.bAddEasyBuy && !Contants.bModifyEasyBuy)
            getCartItemInfo();
        getProvices();
        handleClickEvent();
    }

    public void onResume()
    {
        super.onResume();
        TextView textview = (TextView)findViewById(0x7f0c00d6);
        textview.setFocusable(true);
        textview.requestFocus();
        textview.setFocusableInTouchMode(true);
    }

    public void onStart()
    {
        super.onStart();
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
    boolean click;
    boolean comm;
    ListView commonUsedAddrInfoList;
    DBHelperUtil dbCommonAddr;
    boolean init;
    String items[];
    JSONObject jbAreas;
    JSONObject jbCartStr;
    JSONObject jbCitys;
    JSONArray jbCommAddrGeneral;
    JSONArray jbCommAddrUsed;
    JSONObject jbProvinces;
    JSONObject jbSelCommAddr;
    ImageButton mAdd;
    RadioGroup mAddrListGroup;
    TextView mArea;
    ImageButton mArea2;
    TextView mCity;
    ImageButton mCity2;
    Button mConfirmAddr;
    EditText mMail;
    EditText mMobile;
    TextView mPCA;
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
    List value;





















}
