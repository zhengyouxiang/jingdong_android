// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.RadioButton;
import android.widget.Toast;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.shopping.ShoppingCarActivity;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            MyActivity, HttpGroup, DBHelperUtil, JSONArrayPoxy, 
//            Log, JSONObjectProxy

public class Contants
{

    public Contants()
    {
    }

    public static void ShowMsg(final String msg, final MyActivity actvity)
    {
        actvity.post(new Runnable() {

            public void run()
            {
                Toast.makeText(actvity, msg, 0).show();
            }

            private final MyActivity val$actvity;
            private final String val$msg;

            
            {
                actvity = myactivity;
                msg = s;
                super();
            }
        }
);
    }

    public static String StringFilter(String s)
    {
        return Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~\uFF01@#\uFFE5%\u2026\u2026& amp;*\uFF08\uFF09\u2014\u2014+|{}\u3010\u3011\u2018\uFF1B\uFF1A\u201D\u201C\u2019\u3002\uFF0C\u3001\uFF1F]").matcher(s).replaceAll("").trim();
    }

    public static void addToShoopingCart(final long id, String s, final Product prod, final MyActivity actvity, final DBHelperUtil dbService)
    {
        String s1 = prod.getProvinceStockContent();
        Boolean boolean1 = prod.getProvinceStockFlag();
        if(s1 != null && boolean1 != null)
        {
            localCheck(id, prod, actvity, dbService, s1, boolean1);
        } else
        {
            JSONObject jsonobject = new JSONObject();
            try
            {
                jsonobject.put("skuId", (new StringBuilder()).append(id).toString());
                jsonobject.put("provinceId", s);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            actvity.getHttpGroupaAsynPool().add("stock", jsonobject, new HttpGroup.OnAllListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    if(jsonobjectproxy != null)
                        Contants.localCheck(id, prod, actvity, dbService, jsonobjectproxy.getStringOrNull("stockStatus"), jsonobjectproxy.getBooleanOrNull("flag"));
                    else
                        Contants.ShowMsg("\u52A0\u5165\u8D2D\u7269\u8F66\u5931\u8D25", actvity);
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                }

                public void onProgress(int i, int j)
                {
                }

                public void onStart()
                {
                }

                private final MyActivity val$actvity;
                private final DBHelperUtil val$dbService;
                private final long val$id;
                private final Product val$prod;

            
            {
                id = l;
                prod = product;
                actvity = myactivity;
                dbService = dbhelperutil;
                super();
            }
            }
);
        }
    }

    public static void appendImgCacheList(final String sImgUrl, MyActivity myactivity, int i)
    {
        if(sImgUrl.length() > 0 && gStrImgUrlCache.size() < 20 && !gStrImgUrlCache.containsKey(sImgUrl))
            myactivity.getHttpGroupaAsynPool().add(sImgUrl, null, new HttpGroup.OnAllListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    Contants.gStrImgUrlCache.put(sImgUrl, new SoftReference(httpresponse.getDrawable()));
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                }

                public void onProgress(int j, int k)
                {
                }

                public void onStart()
                {
                }

                private final String val$sImgUrl;

            
            {
                sImgUrl = s;
                super();
            }
            }
);
    }

    public static void canAddToCart(Product product, DBHelperUtil dbhelperutil, MyActivity myactivity)
    {
        canBuyProduct(product, myactivity, dbhelperutil);
    }

    public static boolean canAddToCart(long l, DBHelperUtil dbhelperutil, MyActivity myactivity)
    {
        CartTable carttable = dbhelperutil.queryCartByProductId(l);
        mMaxCount = dbhelperutil.getCartList().size();
        boolean flag;
        if(carttable == null && mMaxCount >= 50)
            flag = false;
        else
            flag = true;
        return flag;
    }

    public static boolean canBuyProduct(final long id, final String provinceValue, final Product prod, final MyActivity actvity, final DBHelperUtil dbService)
    {
        if(canAddToCart(id, dbService, actvity)) goto _L2; else goto _L1
_L1:
        boolean flag;
        ShowMsg(actvity.getString(0x7f090098), actvity);
        flag = false;
_L8:
        return flag;
_L2:
        ArrayList arraylist;
        ArrayList arraylist1;
        int i;
        arraylist = dbService.getCartList();
        arraylist1 = dbService.getPacksList();
        i = 0;
        if(arraylist == null || arraylist.size() == 0) goto _L4; else goto _L3
_L3:
        int l = 0;
_L9:
        if(l < arraylist.size()) goto _L5; else goto _L4
_L4:
        int j = i;
        if(arraylist1 == null || arraylist1.size() == 0) goto _L7; else goto _L6
_L6:
        int k = 0;
_L10:
        if(k < arraylist1.size())
            break MISSING_BLOCK_LABEL_154;
_L7:
        if(j >= 1000)
        {
            ShowMsg(actvity.getString(0x7f09010c), actvity);
            flag = false;
        } else
        {
            JSONObject jsonobject = new JSONObject();
            HttpGroup.HttpSetting httpsetting;
            try
            {
                jsonobject.put("skuId", (new StringBuilder()).append(id).toString());
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            httpsetting = new HttpGroup.HttpSetting();
            httpsetting.setFunctionId("canBuy");
            httpsetting.setJsonParams(jsonobject);
            httpsetting.setListener(new HttpGroup.OnAllListener() {

                public void onEnd(HttpGroup.HttpResponse httpresponse)
                {
                    JSONObjectProxy jsonobjectproxy;
                    try
                    {
                        jsonobjectproxy = httpresponse.getJSONObject();
                        if(jsonobjectproxy == null || !jsonobjectproxy.toString().contains("canBuy") || jsonobjectproxy.get("canBuy") == null || jsonobjectproxy.get("canBuy").toString() == "null" || !jsonobjectproxy.toString().contains("Flag"))
                            break MISSING_BLOCK_LABEL_200;
                        if(jsonobjectproxy.getJSONObject("canBuy").getBoolean("Flag"))
                        {
                            Contants.ret = true;
                            if(!Contants.bEasyBuy)
                                Contants.addToShoopingCart(id, provinceValue, prod, actvity, dbService);
                            else
                                DefaultEasyTempOrderInfo.getDefaultOrderInfo(actvity);
                            break MISSING_BLOCK_LABEL_216;
                        }
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                        break MISSING_BLOCK_LABEL_216;
                    }
                    if(jsonobjectproxy.getJSONObject("canBuy").get("Message") != null && jsonobjectproxy.getJSONObject("canBuy").get("Message").toString() != "null")
                        Contants.ShowMsg(jsonobjectproxy.getJSONObject("canBuy").get("Message").toString(), actvity);
                    else
                        Contants.ShowMsg(actvity.getString(0x7f090099), actvity);
                    break MISSING_BLOCK_LABEL_216;
                    Contants.ShowMsg(actvity.getString(0x7f090099), actvity);
                }

                public void onError(HttpGroup.HttpError httperror)
                {
                }

                public void onProgress(int i1, int j1)
                {
                }

                public void onStart()
                {
                }

                private final MyActivity val$actvity;
                private final DBHelperUtil val$dbService;
                private final long val$id;
                private final Product val$prod;
                private final String val$provinceValue;

            
            {
                id = l;
                provinceValue = s;
                prod = product;
                actvity = myactivity;
                dbService = dbhelperutil;
                super();
            }
            }
);
            httpsetting.setNotifyUser(true);
            actvity.getHttpGroupaAsynPool().add(httpsetting);
            flag = ret;
        }
          goto _L8
_L5:
        i += ((CartTable)arraylist.get(l)).buyCount;
        l++;
          goto _L9
        j += ((PacksTable)arraylist1.get(k)).buyCount * ((PacksTable)arraylist1.get(k)).childCount;
        k++;
          goto _L10
    }

    public static boolean canBuyProduct(final Product prod, final MyActivity actvity, final DBHelperUtil dbService)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("skuId", (new StringBuilder()).append(prod.getId()).toString());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        actvity.getHttpGroupaAsynPool().add("canBuy", jsonobject, new HttpGroup.OnAllListener() {

            public void onEnd(HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy;
                try
                {
                    jsonobjectproxy = httpresponse.getJSONObject();
                    if(jsonobjectproxy == null || !jsonobjectproxy.toString().contains("canBuy") || jsonobjectproxy.get("canBuy") == null || jsonobjectproxy.get("canBuy").toString() == "null" || !jsonobjectproxy.toString().contains("Flag"))
                        break MISSING_BLOCK_LABEL_284;
                    if(!jsonobjectproxy.getJSONObject("canBuy").getBoolean("Flag"))
                        break MISSING_BLOCK_LABEL_208;
                    Contants.ret = true;
                    CartTable carttable = dbService.queryCartByProductId(prod.getId().longValue());
                    Contants.mMaxCount = dbService.getCartList().size();
                    if(carttable != null)
                    {
                        dbService.updateCart(prod.getId().longValue(), prod.getName(), 1 + carttable.buyCount);
                        break MISSING_BLOCK_LABEL_300;
                    }
                    if(Contants.mMaxCount > 50)
                    {
                        Contants.ShowMsg(actvity.getString(0x7f090098), actvity);
                        break MISSING_BLOCK_LABEL_300;
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    break MISSING_BLOCK_LABEL_300;
                }
                dbService.insertCart(prod.getId().longValue(), prod.getName(), 1);
                break MISSING_BLOCK_LABEL_300;
                if(jsonobjectproxy.getJSONObject("canBuy").get("Message") != null && jsonobjectproxy.getJSONObject("canBuy").get("Message").toString() != "null")
                    Contants.ShowMsg(jsonobjectproxy.getJSONObject("canBuy").get("Message").toString(), actvity);
                else
                    Contants.ShowMsg(actvity.getString(0x7f090099), actvity);
                break MISSING_BLOCK_LABEL_300;
                Contants.ShowMsg(actvity.getString(0x7f090099), actvity);
            }

            public void onError(HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            private final MyActivity val$actvity;
            private final DBHelperUtil val$dbService;
            private final Product val$prod;

            
            {
                dbService = dbhelperutil;
                prod = product;
                actvity = myactivity;
                super();
            }
        }
);
        return ret;
    }

    public static void canEasyBuyWare(long l, final MyActivity actvity)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("wareId", (new StringBuilder()).append(l).toString());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        actvity.getHttpGroupaAsynPool().add("easyBuySwitch", jsonobject, new HttpGroup.OnAllListener() {

            public void onEnd(HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                if(jsonobjectproxy != null)
                    try
                    {
                        if(jsonobjectproxy.toString().contains("easyBuy"))
                            if(jsonobjectproxy.getString("easyBuy") != null && jsonobjectproxy.getString("easyBuy").compareTo("true") == 0)
                                DefaultEasyTempOrderInfo.getDefaultOrderInfo(actvity);
                            else
                                Contants.ShowMsg(actvity.getString(0x7f09009a), actvity);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
            }

            public void onError(HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            private final MyActivity val$actvity;

            
            {
                actvity = myactivity;
                super();
            }
        }
);
    }

    public static boolean canSeeEasyBuyBtn(MyActivity myactivity)
    {
        boolean flag;
        if(myactivity.getStringFromPreference("easyBuySwitch").compareTo("1") == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void clearOrderInfo()
    {
        liSelArray = new JSONArray();
        jinSelArray = new JSONArray();
        dongSel = new JSONObject();
        nselectJingQuanIDs = new ArrayList();
        nSelectLipinIDs = new ArrayList();
        nSelectDongQuanId = null;
        bNoYouHui = false;
        dSelected = false;
        liSelected = false;
        jSelected = false;
        skusOfSuites = null;
        skusOfSuites = new JSONArrayPoxy();
    }

    public static Intent getGlobalIntent()
    {
        Intent intent;
        if(gIntent == null)
            intent = null;
        else
            intent = gIntent;
        return intent;
    }

    public static void localCheck(final long id, final Product prod, final MyActivity actvity, final DBHelperUtil dbService, String s, Boolean boolean1)
    {
        if(s == null || !boolean1.booleanValue())
            actvity.runOnUiThread(new Runnable() {

                public void run()
                {
                    Toast.makeText(actvity, 0x7f09009b, 500).show();
                }

                private final MyActivity val$actvity;

            
            {
                actvity = myactivity;
                super();
            }
            }
);
        else
            actvity.runOnUiThread(new Runnable() {

                public void run()
                {
                    if(prod.getJdPrice().toString().contains("\u6682\u65E0\u62A5\u4EF7"))
                    {
                        Toast.makeText(actvity, 0x7f09009c, 500).show();
                    } else
                    {
                        Contants.hasNewTocart = true;
                        CartTable carttable = dbService.queryCartByProductId(id);
                        final android.app.AlertDialog.Builder alertDialogBuilder;
                        if(carttable != null)
                            dbService.updateCart(id, prod.getName(), 1 + carttable.buyCount);
                        else
                            dbService.insertCart(id, prod.getName(), 1);
                        alertDialogBuilder = new android.app.AlertDialog.Builder(actvity);
                        alertDialogBuilder.setTitle(0x7f0900a3);
                        alertDialogBuilder.setMessage(0x7f0900a4);
                        alertDialogBuilder.setPositiveButton(0x7f0900a5, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                Intent intent = new Intent(actvity, com/jindong/app/mall/shopping/ShoppingCarActivity);
                                intent.putExtra("com.360buy:singleInstanceFlag", true);
                                actvity.startActivityInFrame(intent);
                                Contants.alertDialog.dismiss();
                            }

                            final _cls8 this$1;
                            private final MyActivity val$actvity;

                    
                    {
                        this$1 = _cls8.this;
                        actvity = myactivity;
                        super();
                    }
                        }
);
                        alertDialogBuilder.setNegativeButton(0x7f0900a6, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                Contants.alertDialog.dismiss();
                            }

                            final _cls8 this$1;

                    
                    {
                        this$1 = _cls8.this;
                        super();
                    }
                        }
);
                        actvity.post(new Runnable() {

                            public void run()
                            {
                                Contants.alertDialog = alertDialogBuilder.show();
                            }

                            final _cls8 this$1;
                            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls8.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                        }
);
                    }
                }

                private final MyActivity val$actvity;
                private final DBHelperUtil val$dbService;
                private final long val$id;
                private final Product val$prod;

            
            {
                prod = product;
                actvity = myactivity;
                dbService = dbhelperutil;
                id = l;
                super();
            }
            }
);
    }

    public static Drawable popImgUrlFromCache(String s)
    {
        Drawable drawable;
        if(gStrImgUrlCache.size() > 0 && gStrImgUrlCache.containsKey(s))
            drawable = (Drawable)((SoftReference)gStrImgUrlCache.get(s)).get();
        else
            drawable = null;
        return drawable;
    }

    public static void setTransferIntentGlobal(Intent intent)
    {
        gIntent.putExtras(intent);
_L1:
        return;
        Exception exception;
        exception;
        if(Log.D)
            Log.d("global intent error", exception.getMessage());
          goto _L1
    }

    public static boolean shouldConfirmEasyBuy(MyActivity myactivity)
    {
        boolean flag;
        if(myactivity.getStringFromPreference("easyBuyConfirm").compareTo("1") == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final String JD_SHARE_PREFERENCE_CITY_ID_MODE_1 = "CITY_ID_MODE_1";
    public static final String JD_SHARE_PREFERENCE_PROVINCE_ID_MODE_1 = "PROVINCE_ID_MODE_1";
    public static final String LOGIN_FLAG = "login";
    public static int LOG_IN = 0;
    public static int LOG_OFF = 0;
    public static int MAX_CART_PROD_COUNT = 0;
    public static int MAX_DISCUSS_TEXT_LENGTH = 0;
    public static int MAX_IMG_URL_CACHE_LIST = 0;
    public static int MAX_LATEST_VISITED_PRODUCT = 0;
    public static int MAX_SINGLE_PROD_COUNT = 0;
    public static final String REMEMBER_FLAG = "remember";
    public static final String REMEMBER_NAME = "userName";
    public static final String REMEMBER_PASSWORD = "password";
    public static String REN_MIN_BI = "\245";
    public static final String SHOW_COST = "showCost";
    public static boolean addNewTemplate = false;
    static AlertDialog alertDialog = null;
    public static boolean bAddEasyBuy = false;
    public static boolean bEasyBuy = false;
    public static boolean bModifyEasyBuy = false;
    public static boolean bNoYouHui = false;
    public static boolean bPhone = false;
    public static int bQuan;
    public static boolean dSelected = false;
    public static String dTotalPrice = "0";
    public static String dYTotalPrice = "0";
    public static JSONObject dongSel = null;
    public static long easyBuyProdId;
    private static Intent gIntent = new Intent();
    public static HashMap gStrImgUrlCache = new HashMap();
    public static boolean hasLogIn = false;
    public static boolean hasNewTocart = false;
    public static boolean hasNewway = false;
    public static ArrayList item;
    public static boolean jSelected = false;
    public static JSONObject jbAreas = new JSONObject();
    public static JSONObject jbCitys = new JSONObject();
    public static JSONObject jbOrderNum = null;
    public static JSONObject jbProvinces = new JSONObject();
    public static boolean jdSwitch = false;
    public static JSONArray jinSelArray = null;
    public static JSONArray liSelArray = null;
    public static boolean liSelected = false;
    public static JdCartInfo mCartInfoHuiZong;
    public static int mMaxCount = 0;
    public static InvoiceInfo mModifiedInvoiceInfo;
    public static PaymentInfo mModifiedPaymentInfo;
    public static UserInfo mModifiedUserInfo;
    public static YouHuiQuan mModifiedYouhuiQuan;
    public static String nPayway;
    public static String nSelectDongQuanId = null;
    public static ArrayList nSelectLipinIDs = null;
    public static boolean noDong = false;
    public static boolean noJing = false;
    public static boolean noLi = false;
    public static ArrayList nselectJingQuanIDs = null;
    public static RadioButton oldBtn = null;
    public static long packMainProdId;
    static boolean ret = false;
    public static JSONArrayPoxy skusOfSuites;

    static 
    {
        LOG_IN = 1;
        LOG_OFF = 0;
        MAX_LATEST_VISITED_PRODUCT = 20;
        MAX_IMG_URL_CACHE_LIST = 20;
        MAX_DISCUSS_TEXT_LENGTH = 200;
        MAX_CART_PROD_COUNT = 50;
        MAX_SINGLE_PROD_COUNT = 1000;
    }
}
