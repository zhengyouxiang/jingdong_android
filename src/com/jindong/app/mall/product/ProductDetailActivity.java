// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.app.AlertDialog;
import android.content.*;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.login.LoginActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.shopping.PacksListActivity;
import com.jindong.app.mall.shopping.config;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.product:
//            DiscussListActivity, BuyAskListActivity, CommentListActivity, ProductDetailInfoActivity, 
//            ImageActivity

public class ProductDetailActivity extends MyActivity
{
    private class OptionsOnClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            refresh(id);
        }

        private long id;
        final ProductDetailActivity this$0;

        public OptionsOnClickListener(long l)
        {
            this$0 = ProductDetailActivity.this;
            super();
            id = l;
        }
    }


    public ProductDetailActivity()
    {
        product = new Product();
        commentBundle = new Bundle();
        provincePosition = 0;
        layoutParams = new android.widget.LinearLayout.LayoutParams(-2, -1, 1.0F);
        layoutParams.setMargins(0, 2, 4, 2);
        provinceOnClickListener = new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                mProvinceSelectId = i;
                citySelectorDialog = showSelector(getCityStringArray(), getDefaultCitySelectId(), cityOnClickListener);
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
;
        cityOnClickListener = new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                mCitySelectId = i;
                ProvinceMode1 provincemode1 = (ProvinceMode1)product.getProvinceMode1List().get(mProvinceSelectId);
                CityMode1 citymode1 = (CityMode1)provincemode1.getChildren().get(mCitySelectId);
                int j = provincemode1.getId();
                int k = citymode1.getId();
                long l = citymode1.getProductId();
                long l1 = product.getId().longValue();
                product.setProvinceIdMode1(Integer.valueOf(j));
                product.setCityIdMode1(Integer.valueOf(k));
                product.setId(Long.valueOf(l));
                mDefaultProvinceId = j;
                mDefaultCityId = k;
                android.content.SharedPreferences.Editor editor = CommonUtil.getJdSharedPreferences().edit();
                editor.putInt("PROVINCE_ID_MODE_1", j);
                editor.putInt("CITY_ID_MODE_1", k);
                editor.commit();
                if(l1 != product.getId().longValue())
                    refresh(l);
                else
                    queryStock();
                if(citySelectorDialog != null)
                    citySelectorDialog.dismiss();
                if(provinceSelectorDialog != null)
                    provinceSelectorDialog.dismiss();
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
;
        background = new Drawable() {

            public void draw(Canvas canvas)
            {
                Paint paint = new Paint();
                paint.setStyle(android.graphics.Paint.Style.STROKE);
                paint.setColor(0x33000000);
                paint.setStrokeWidth(2.0F);
                Rect rect = canvas.getClipBounds();
                canvas.drawLine(rect.left, rect.bottom, rect.right, rect.bottom, paint);
            }

            public int getOpacity()
            {
                return 0;
            }

            public void setAlpha(int i)
            {
            }

            public void setColorFilter(ColorFilter colorfilter)
            {
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
;
    }

    private int cityId2SelectId(int i)
    {
        Integer integer = ((ProvinceMode1)product.getProvinceMode1List().get(mProvinceSelectId)).getCityMode1IndexById(i);
        int j;
        if(integer == null)
            j = 0;
        else
            j = integer.intValue();
        return j;
    }

    private Integer cityId2SelectId(int i, int j)
    {
        Integer integer = ((ProvinceMode1)product.getProvinceMode1List().get(provinceId2SelectId(i))).getCityMode1IndexById(j);
        Integer integer1;
        if(integer == null)
            integer1 = Integer.valueOf(0);
        else
            integer1 = integer;
        return integer1;
    }

    private String[] getCityStringArray()
    {
        ArrayList arraylist = ((ProvinceMode1)product.getProvinceMode1List().get(mProvinceSelectId)).getChildren();
        String as[] = new String[arraylist.size()];
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                return as;
            as[i] = ((CityMode1)arraylist.get(i)).getName();
            i++;
        } while(true);
    }

    private int getDefaultCityId()
    {
        int i;
        if(mDefaultCityId != 0)
        {
            i = mDefaultCityId;
        } else
        {
            mDefaultCityId = CommonUtil.getJdSharedPreferences().getInt("CITY_ID_MODE_1", 0);
            i = mDefaultCityId;
        }
        return i;
    }

    private int getDefaultCitySelectId()
    {
        return cityId2SelectId(getDefaultCityId());
    }

    private int getDefaultProvinceId()
    {
        int i;
        if(mDefaultProvinceId != 0)
        {
            i = mDefaultProvinceId;
        } else
        {
            mDefaultProvinceId = CommonUtil.getJdSharedPreferences().getInt("PROVINCE_ID_MODE_1", 0);
            i = mDefaultProvinceId;
        }
        return i;
    }

    private int getDefaultProvinceSelectId()
    {
        return provinceId2SelectId(getDefaultProvinceId());
    }

    private String[] getProvinceStringArray()
    {
        ArrayList arraylist = product.getProvinceMode1List();
        String as[] = new String[arraylist.size()];
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                return as;
            as[i] = ((ProvinceMode1)arraylist.get(i)).getName();
            i++;
        } while(true);
    }

    private void init()
    {
        productStockProvinceChoosed = (TextView)findViewById(0x7f0c025b);
        stockProvinceButton = (ImageButton)findViewById(0x7f0c025c);
        stockResultText = (TextView)findViewById(0x7f0c025d);
        provinceName = getStringFromPreference("provinceName", getString(0x7f09004f));
        provinceID = getStringFromPreference("provinceID", "1");
        dbService = new DBHelperUtil(this);
        easyButton = (Button)findViewById(0x7f0c0261);
    }

    private void initCartButton()
    {
        ((Button)findViewById(0x7f0c0260)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Contants.bEasyBuy = false;
                Contants.bAddEasyBuy = false;
                Contants.bModifyEasyBuy = false;
                Contants.canBuyProduct(id, product.getProvinceID(), product, ProductDetailActivity.this, dbService);
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
);
    }

    private void initCollectButton()
    {
        final ImageButton collectButton = (ImageButton)findViewById(0x7f0c0262);
        if(LoginUser.hasLogin() && dbService.queryIsFavorited(id))
            post(new Runnable() {

                public void run()
                {
                    collectButton.setSelected(true);
                }

                final ProductDetailActivity this$0;
                private final ImageButton val$collectButton;

            
            {
                this$0 = ProductDetailActivity.this;
                collectButton = imagebutton;
                super();
            }
            }
);
        collectButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(LoginUser.hasLogin()) goto _L2; else goto _L1
_L1:
                Toast.makeText(ProductDetailActivity.this, 0x7f09004e, 0).show();
                Intent intent = new Intent(ProductDetailActivity.this, com/jindong/app/mall/login/LoginActivity);
                startActivityInFrame(intent);
_L4:
                return;
_L2:
                if(!dbService.queryIsFavorited(product.getId().longValue()))
                {
                    com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                    httpsetting.setFunctionId("addFavorite");
                    httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getId()).toString());
                    httpsetting.putJsonParam("pin", LoginUser.getLoginUserName());
                    httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                        public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                        {
                            final String msg = httpresponse.getJSONObject().getStringOrNull("addFavorite");
                            post(new Runnable() {

                                public void run()
                                {
                                    Toast.makeText(_fld0, msg, 0).show();
                                }

                                final _cls1 this$2;
                                private final String val$msg;

                        
                        {
                            this$2 = _cls1.this;
                            msg = s;
                            super();
                        }
                            }
);
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

                        final _cls10 this$1;


                    
                    {
                        this$1 = _cls10.this;
                        super();
                    }
                    }
);
                    httpsetting.setNotifyUser(true);
                    getHttpGroupaAsynPool().add(httpsetting);
                    dbService.insertOrUpdateFavority(product.getId().longValue(), product.getName(), false);
                    collectButton.setSelected(true);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ProductDetailActivity this$0;
            private final ImageButton val$collectButton;


            
            {
                this$0 = ProductDetailActivity.this;
                collectButton = imagebutton;
                super();
            }
        }
);
    }

    private void initEasyButton()
    {
        easyButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                easyButton.setClickable(false);
                Contants.bEasyBuy = true;
                Contants.bAddEasyBuy = false;
                Contants.bModifyEasyBuy = false;
                if(Contants.bEasyBuy)
                {
                    Contants.easyBuyProdId = id;
                    DefaultEasyTempOrderInfo.getDefaultOrderInfo(ProductDetailActivity.this);
                }
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
);
        if(LoginUser.hasLogin() && Contants.canSeeEasyBuyBtn(this) && canUseEasyByStock(product))
            canEasyBuyWare(product.getId().longValue(), easyButton);
        else
            post(new Runnable() {

                public void run()
                {
                    easyButton.setEnabled(false);
                    easyButton.setVisibility(4);
                }

                final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
            }
);
    }

    private void initPacksButton(String s)
    {
        ShowPacksButton(s);
    }

    private void initProvinceClick()
    {
        productStockProvinceChoosed.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                stockProvinceButton.performClick();
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
);
        stockProvinceButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                provinceSelectorDialog = showSelector(getProvinceStringArray(), getDefaultProvinceSelectId(), provinceOnClickListener);
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
);
    }

    private int provinceId2SelectId(int i)
    {
        Integer integer = product.getProvinceMode1IndexById(i);
        int j;
        if(integer == null)
            j = 0;
        else
            j = integer.intValue();
        return j;
    }

    private void provinceStockMode0()
    {
        queryStock(product.getProvinceID());
        productStockProvinceChoosed.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                stockProvinceButton.performClick();
            }

            final ProductDetailActivity this$0;

            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
);
        stockProvinceButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!flag)
                {
                    flag = true;
                    final android.app.AlertDialog.Builder catelogyFilterDialog = new android.app.AlertDialog.Builder(ProductDetailActivity.this);
                    catelogyFilterDialog.setTitle(0x7f09004a);
                    com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                        public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                        {
                            flag = false;
                            final JSONArrayPoxy jsonArray_;
                            jsonArray_ = httpresponse.getJSONObject().getJSONArray("provinces");
                            if(Log.D)
                                Log.d("Temp", (new StringBuilder("httpResponse.getJSONObject() -->> ")).append(httpresponse.getJSONObject()).toString());
                            String as[];
                            int i;
                            as = new String[jsonArray_.length()];
                            i = 0;
_L4:
                            if(i < jsonArray_.length()) goto _L2; else goto _L1
_L1:
                            catelogyFilterDialog.setSingleChoiceItems(as, provincePosition, new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int j)
                                {
                                    provincePosition = j;
                                    JSONObjectProxy jsonobjectproxy1 = jsonArray_.getJSONObject(j);
                                    product.setProvinceName(jsonobjectproxy1.getStringOrNull("value"));
                                    provinceName = product.getProvinceName();
                                    putStringToPreference("provinceName", product.getProvinceName());
                                    product.setProvinceID(jsonobjectproxy1.getStringOrNull("label"));
                                    provinceID = product.getProvinceID();
                                    putStringToPreference("provinceID", product.getProvinceID());
                                    queryStock(product.getProvinceID());
                                    alertDialog.dismiss();
_L1:
                                    return;
                                    JSONException jsonexception2;
                                    jsonexception2;
                                    jsonexception2.printStackTrace();
                                      goto _L1
                                }

                                final _cls1 this$2;
                                private final JSONArrayPoxy val$jsonArray_;

                        
                        {
                            this$2 = _cls1.this;
                            jsonArray_ = jsonarraypoxy;
                            super();
                        }
                            }
);
                            post(new Runnable() {

                                public void run()
                                {
                                    alertDialog = catelogyFilterDialog.show();
                                }

                                final _cls1 this$2;
                                private final android.app.AlertDialog.Builder val$catelogyFilterDialog;

                        
                        {
                            this$2 = _cls1.this;
                            catelogyFilterDialog = builder;
                            super();
                        }
                            }
);
_L3:
                            return;
                            JSONException jsonexception;
                            jsonexception;
                            if(Log.V)
                                Log.v(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception);
                              goto _L3
_L2:
                            JSONObjectProxy jsonobjectproxy = jsonArray_.getJSONObject(i);
                            as[i] = jsonobjectproxy.getStringOrNull("value");
                            if(product.getProvinceName().equals(jsonobjectproxy.getStringOrNull("value")))
                                provincePosition = i;
                            i++;
                              goto _L4
                            JSONException jsonexception1;
                            jsonexception1;
                            jsonexception1.printStackTrace();
                              goto _L3
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

                        private AlertDialog alertDialog;
                        final _cls16 this$1;
                        private final android.app.AlertDialog.Builder val$catelogyFilterDialog;




                    
                    {
                        this$1 = _cls16.this;
                        catelogyFilterDialog = builder;
                        super();
                        alertDialog = null;
                    }
                    }
;
                    com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                    httpsetting.setFunctionId("selectedProvince");
                    httpsetting.setListener(onalllistener);
                    httpsetting.setLocalFileCache(true);
                    httpsetting.setLocalFileCacheTime(0xf731400L);
                    httpsetting.setNotifyUser(true);
                    getHttpGroupaAsynPool().add(httpsetting);
                }
            }

            private boolean flag;
            final ProductDetailActivity this$0;



            
            {
                this$0 = ProductDetailActivity.this;
                super();
                flag = false;
            }
        }
);
    }

    private void provinceStockMode1()
    {
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                if(jsonobjectproxy != null)
                {
                    Product product1 = product;
                    JSONArrayPoxy jsonarraypoxy = jsonobjectproxy.getJSONArrayOrNull("wareInfoList");
                    Object aobj[] = new Object[1];
                    aobj[0] = product;
                    product1.setProvinceMode1List(ProvinceMode1.toList(jsonarraypoxy, 0, aobj));
                    initProvinceClick();
                    post(new Runnable() {

                        public void run()
                        {
                            queryStock();
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

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailActivity this$0;


            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("directWare");
        httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getId()).toString());
        httpsetting.setListener(onalllistener);
        httpsetting.setNotifyUser(true);
        httpGroup.add(httpsetting);
    }

    private void queryStock()
    {
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                post(new Runnable() {

                    public void run()
                    {
                        showCommentCount();
                        showConsultationCount();
                        showOrderCommentCount();
                    }

                    final _cls14 this$1;

                    
                    {
                        this$1 = _cls14.this;
                        super();
                    }
                }
);
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                if(jsonobjectproxy != null)
                {
                    String s2 = jsonobjectproxy.getStringOrNull("jdPrice");
                    final String result = jsonobjectproxy.getStringOrNull("stockStatus");
                    Boolean boolean1 = jsonobjectproxy.getBooleanOrNull("flag");
                    product.setJdPrice(s2);
                    product.setProvinceStockContent(result);
                    product.setProvinceStockFlag(boolean1);
                    initEasyButton();
                    post(new Runnable() {

                        public void run()
                        {
                            ProductShow productshow = new ProductShow(_fld0, product);
                            ((TextView)findViewById(0x7f0c0251)).setText(productshow.getJdPrice());
                            TextView textview = stockResultText;
                            String s3;
                            if(result != null)
                                s3 = result;
                            else
                                s3 = "";
                            textview.setText(s3);
                        }

                        final _cls14 this$1;
                        private final String val$result;

                    
                    {
                        this$1 = _cls14.this;
                        result = s;
                        super();
                    }
                    }
);
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                post(new Runnable() {

                    public void run()
                    {
                        showCommentCount();
                        showConsultationCount();
                        showOrderCommentCount();
                    }

                    final _cls14 this$1;

                    
                    {
                        this$1 = _cls14.this;
                        super();
                    }
                }
);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailActivity this$0;


            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
;
        CityMode1 citymode1 = product.getCityMode1BySkuId(product.getId());
        Integer integer = product.getProvinceIdMode1();
        if(integer == null)
        {
            integer = Integer.valueOf(citymode1.getParent().getId());
            mDefaultProvinceId = integer.intValue();
        }
        Integer integer1 = product.getCityIdMode1();
        if(integer1 == null)
        {
            integer1 = Integer.valueOf(citymode1.getId());
            mDefaultCityId = integer1.intValue();
        }
        if(Log.D)
            Log.d("Temp", (new StringBuilder("provinceId -->> ")).append(integer).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("cityId -->> ")).append(integer1).toString());
        String s;
        String s1;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        if(integer == null)
        {
            ProvinceMode1 provincemode1 = (ProvinceMode1)product.getProvinceMode1List().get(getDefaultProvinceSelectId());
            integer = Integer.valueOf(provincemode1.getId());
            s = provincemode1.getName();
        } else
        {
            s = ((ProvinceMode1)product.getProvinceMode1List().get(provinceId2SelectId(integer.intValue()))).getName();
        }
        if(integer1 == null)
        {
            CityMode1 citymode1_2 = (CityMode1)((ProvinceMode1)product.getProvinceMode1List().get(provinceId2SelectId(integer.intValue()))).getChildren().get(getDefaultCitySelectId());
            integer1 = Integer.valueOf(citymode1_2.getId());
            s1 = citymode1_2.getName();
        } else
        {
            Integer integer2 = cityId2SelectId(integer.intValue(), integer1.intValue());
            CityMode1 citymode1_1 = (CityMode1)((ProvinceMode1)product.getProvinceMode1List().get(provinceId2SelectId(integer.intValue()))).getChildren().get(integer2.intValue());
            if(Log.D)
                Log.d("Temp", (new StringBuilder("cityMode1 -->> ")).append(citymode1_1).toString());
            s1 = citymode1_1.getName();
        }
        productStockProvinceChoosed.setText((new StringBuilder(String.valueOf(s))).append(">").append(s1).toString());
        stockResultText.setText(getString(0x7f09007b));
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("directStock");
        httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getId()).toString());
        httpsetting.putJsonParam("idProvince", (new StringBuilder()).append(integer).toString());
        httpsetting.putJsonParam("idCity", (new StringBuilder()).append(integer1).toString());
        httpsetting.setListener(onalllistener);
        httpsetting.setNotifyUser(true);
        httpGroup.add(httpsetting);
    }

    private void queryStock(String s)
    {
        productStockProvinceChoosed.setText(product.getProvinceName());
        stockResultText.setText(getString(0x7f09007b));
        JSONObject jsonobject = new JSONObject();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            jsonobject.put("skuId", (new StringBuilder()).append(id).toString());
            jsonobject.put("provinceId", s);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("stock");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                post(new Runnable() {

                    public void run()
                    {
                        showCommentCount();
                        showConsultationCount();
                        showOrderCommentCount();
                    }

                    final _cls17 this$1;

                    
                    {
                        this$1 = _cls17.this;
                        super();
                    }
                }
);
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                product.setProvinceStockContent(jsonobjectproxy.getStringOrNull("stockStatus"));
                product.setProvinceStockFlag(jsonobjectproxy.getBooleanOrNull("flag"));
                initEasyButton();
                post(new Runnable() {

                    public void run()
                    {
                        String s1 = product.getProvinceStockContent();
                        TextView textview = stockResultText;
                        String s2;
                        if(s1 != null)
                            s2 = s1;
                        else
                            s2 = "";
                        textview.setText(s2);
                    }

                    final _cls17 this$1;

                    
                    {
                        this$1 = _cls17.this;
                        super();
                    }
                }
);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                post(new Runnable() {

                    public void run()
                    {
                        showCommentCount();
                        showConsultationCount();
                        showOrderCommentCount();
                    }

                    final _cls17 this$1;

                    
                    {
                        this$1 = _cls17.this;
                        super();
                    }
                }
);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailActivity this$0;


            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void refresh(long l)
    {
        id = l;
        showProduct();
    }

    private void removeAllLinearLayout(ViewGroup viewgroup)
    {
        int i = viewgroup.getChildCount() - 1;
        do
        {
            if(i < 0)
                return;
            View view = viewgroup.getChildAt(i);
            if(view instanceof LinearLayout)
                viewgroup.removeView(view);
            i--;
        } while(true);
    }

    private void removeAllTextView(ViewGroup viewgroup)
    {
        int i = viewgroup.getChildCount() - 1;
        do
        {
            if(i < 0)
                return;
            View view = viewgroup.getChildAt(i);
            if(view instanceof TextView)
                viewgroup.removeView(view);
            i--;
        } while(true);
    }

    private void showCommentCount()
    {
        final LinearLayout productCommentCount;
        final View productCommentCountNderline;
        JSONObject jsonobject;
        productCommentCount = (LinearLayout)findViewById(0x7f0c0266);
        productCommentCountNderline = findViewById(0x7f0c0267);
        productCommentCount.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                productCommentCount.setPressed(false);
                Intent intent = new Intent(ProductDetailActivity.this, com/jindong/app/mall/product/CommentListActivity);
                commentBundle.putSerializable("product", product);
                intent.putExtras(commentBundle);
                startActivityInFrame(intent);
            }

            final ProductDetailActivity this$0;
            private final LinearLayout val$productCommentCount;

            
            {
                this$0 = ProductDetailActivity.this;
                productCommentCount = linearlayout;
                super();
            }
        }
);
        jsonobject = new JSONObject();
        jsonobject.put("wareId", (new StringBuilder()).append(id).toString());
_L2:
        httpGroup.add("commentCount", jsonobject, new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final JSONArrayPoxy jsonArray = httpresponse.getJSONObject().getJSONArrayOrNull("scoreList");
                if(jsonArray == null || jsonArray.length() == 0)
                    post(new Runnable() {

                        public void run()
                        {
                            removeAllTextView(productCommentCount);
                            TextView textview = new TextView(_fld0);
                            textview.setText(0x7f090054);
                            productCommentCount.addView(textview, 0, new android.widget.LinearLayout.LayoutParams(-2, -2, 1.0F));
                            productCommentCount.setVisibility(0);
                            productCommentCountNderline.setVisibility(0);
                            productCommentCount.setClickable(false);
                        }

                        final _cls24 this$1;
                        private final LinearLayout val$productCommentCount;
                        private final View val$productCommentCountNderline;

                    
                    {
                        this$1 = _cls24.this;
                        productCommentCount = linearlayout;
                        productCommentCountNderline = view;
                        super();
                    }
                    }
);
                else
                    post(new Runnable() {

                        public void run()
                        {
                            int i;
                            removeAllTextView(productCommentCount);
                            i = 0;
_L3:
                            int j = jsonArray.length();
                            if(i < j) goto _L2; else goto _L1
_L1:
                            productCommentCount.setVisibility(0);
                            productCommentCountNderline.setVisibility(0);
_L4:
                            return;
_L2:
                            JSONObjectProxy jsonobjectproxy = jsonArray.getJSONObject(i);
                            String s = jsonobjectproxy.getStringOrNull("message");
                            commentBundle.putString((new StringBuilder("message")).append(i).toString(), s);
                            String s1 = jsonobjectproxy.getStringOrNull((new StringBuilder("scoreCount")).append(i + 1).toString());
                            if(Log.D)
                                Log.d((new StringBuilder("scoreCount")).append(i).toString(), s1);
                            TextView textview;
                            SpannableStringBuilder spannablestringbuilder;
                            if(s1 == null)
                                commentBundle.putString((new StringBuilder("scoreCount")).append(i).toString(), "0");
                            else
                                commentBundle.putString((new StringBuilder("scoreCount")).append(i).toString(), s1);
                            textview = new TextView(_fld0);
                            textview.setText(s);
                            spannablestringbuilder = new SpannableStringBuilder((new StringBuilder("\uFF08")).append(s1).append("\uFF09").toString());
                            spannablestringbuilder.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), 0, spannablestringbuilder.length(), 33);
                            textview.append(spannablestringbuilder);
                            productCommentCount.addView(textview, i, new android.widget.LinearLayout.LayoutParams(-2, -2, 1.0F));
                            i++;
                              goto _L3
                            JSONException jsonexception1;
                            jsonexception1;
                            if(Log.D)
                                Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception1);
                              goto _L4
                        }

                        final _cls24 this$1;
                        private final JSONArrayPoxy val$jsonArray;
                        private final LinearLayout val$productCommentCount;
                        private final View val$productCommentCountNderline;

                    
                    {
                        this$1 = _cls24.this;
                        productCommentCount = linearlayout;
                        jsonArray = jsonarraypoxy;
                        productCommentCountNderline = view;
                        super();
                    }
                    }
);
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

            final ProductDetailActivity this$0;
            private final LinearLayout val$productCommentCount;
            private final View val$productCommentCountNderline;


            
            {
                this$0 = ProductDetailActivity.this;
                productCommentCount = linearlayout;
                productCommentCountNderline = view;
                super();
            }
        }
);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void showConsultationCount()
    {
        final TextView productConsultationCountTextView;
        final LinearLayout productConsultationCount;
        final View productConsultationCountNderline;
        JSONObject jsonobject;
        productConsultationCountTextView = (TextView)findViewById(0x7f0c026c);
        productConsultationCount = (LinearLayout)findViewById(0x7f0c026b);
        productConsultationCountNderline = findViewById(0x7f0c026d);
        productConsultationCount.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                productConsultationCount.setPressed(false);
                Intent intent = new Intent(ProductDetailActivity.this, com/jindong/app/mall/product/BuyAskListActivity);
                Bundle bundle = new Bundle();
                bundle.putLong("id", product.getShowId().longValue());
                bundle.putString("name", product.getName());
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final ProductDetailActivity this$0;
            private final LinearLayout val$productConsultationCount;

            
            {
                this$0 = ProductDetailActivity.this;
                productConsultationCount = linearlayout;
                super();
            }
        }
);
        jsonobject = new JSONObject();
        jsonobject.put("wareId", (new StringBuilder()).append(id).toString());
_L2:
        httpGroup.add("consultationCount", jsonobject, new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final String num = httpresponse.getJSONObject().getStringOrNull("totalCount");
                if(num != null && !"".equals(num))
                    post(new Runnable() {

                        public void run()
                        {
                            productConsultationCount.setVisibility(0);
                            productConsultationCountNderline.setVisibility(0);
                            productConsultationCountTextView.setText(getResources().getText(0x7f09004d));
                            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder((new StringBuilder("\uFF08")).append(num).append("\uFF09").toString());
                            spannablestringbuilder.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), 0, spannablestringbuilder.length(), 33);
                            productConsultationCountTextView.append(spannablestringbuilder);
                        }

                        final _cls22 this$1;
                        private final String val$num;
                        private final LinearLayout val$productConsultationCount;
                        private final View val$productConsultationCountNderline;
                        private final TextView val$productConsultationCountTextView;

                    
                    {
                        this$1 = _cls22.this;
                        productConsultationCount = linearlayout;
                        productConsultationCountNderline = view;
                        productConsultationCountTextView = textview;
                        num = s;
                        super();
                    }
                    }
);
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

            final ProductDetailActivity this$0;
            private final LinearLayout val$productConsultationCount;
            private final View val$productConsultationCountNderline;
            private final TextView val$productConsultationCountTextView;


            
            {
                this$0 = ProductDetailActivity.this;
                productConsultationCount = linearlayout;
                productConsultationCountNderline = view;
                productConsultationCountTextView = textview;
                super();
            }
        }
);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void showOptions()
    {
        final RelativeLayout productOptionsView;
        final View line;
        final LinearLayout productOptionsColorView;
        final LinearLayout productOptionsSizeView;
        JSONObject jsonobject;
        productOptionsView = (RelativeLayout)findViewById(0x7f0c0255);
        line = findViewById(0x7f0c0258);
        productOptionsColorView = (LinearLayout)findViewById(0x7f0c0256);
        productOptionsSizeView = (LinearLayout)findViewById(0x7f0c0257);
        jsonobject = new JSONObject();
        jsonobject.put("wareId", (new StringBuilder()).append(id).toString());
_L2:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("sku");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy;
                JSONArrayPoxy jsonarraypoxy;
                ProductDetailActivity productdetailactivity = ProductDetailActivity.this;
                Runnable runnable = new Runnable() {

                    public void run()
                    {
                        showStock();
                    }

                    final _cls18 this$1;

                    
                    {
                        this$1 = _cls18.this;
                        super();
                    }
                }
;
                productdetailactivity.post(runnable);
                jsonobjectproxy = httpresponse.getJSONObject();
                jsonarraypoxy = null;
                JSONArrayPoxy jsonarraypoxy3 = jsonobjectproxy.getJSONArray("skuColor");
                jsonarraypoxy = jsonarraypoxy3;
_L1:
                JSONArrayPoxy jsonarraypoxy1;
                JSONException jsonexception1;
                JSONArrayPoxy jsonarraypoxy2;
                if(jsonarraypoxy == null || jsonarraypoxy.length() < 1)
                {
                    post(new Runnable() {

                        public void run()
                        {
                            productOptionsColorView.setVisibility(8);
                            if(productOptionsColorView.getVisibility() == 8 && productOptionsSizeView.getVisibility() == 8)
                            {
                                productOptionsView.setVisibility(8);
                                line.setVisibility(8);
                            }
                        }

                        final _cls18 this$1;
                        private final View val$line;
                        private final LinearLayout val$productOptionsColorView;
                        private final LinearLayout val$productOptionsSizeView;
                        private final RelativeLayout val$productOptionsView;

                    
                    {
                        this$1 = _cls18.this;
                        productOptionsColorView = linearlayout;
                        productOptionsSizeView = linearlayout1;
                        productOptionsView = relativelayout;
                        line = view;
                        super();
                    }
                    }
);
                } else
                {
                    final JSONArrayPoxy jsonColorArray = jsonarraypoxy;
                    post(new Runnable() {

                        public void run()
                        {
                            productOptionsColorView.setVisibility(0);
                            productOptionsView.setVisibility(0);
                            line.setVisibility(0);
                            LinearLayout linearlayout;
                            LinearLayout linearlayout1;
                            removeAllLinearLayout(productOptionsColorView);
                            linearlayout = new LinearLayout(_fld0);
                            linearlayout.setOrientation(1);
                            productOptionsColorView.addView(linearlayout, new android.widget.LinearLayout.LayoutParams(-1, -2));
                            linearlayout1 = null;
                            JSONException jsonexception3;
                            for(int i = 0; i < jsonColorArray.length(); i++)
                            {
                                JSONObjectProxy jsonobjectproxy1 = jsonColorArray.getJSONObject(i);
                                String s = jsonobjectproxy1.getStringOrNull("color");
                                if(s == null)
                                    s = "";
                                jsonobjectproxy1.getStringOrNull("colorImg");
                                jsonobjectproxy1.getStringOrNull("promotion");
                                jsonobjectproxy1.getStringOrNull("waretitle");
                                Long long1 = jsonobjectproxy1.getLongOrNull("wareId");
                                if(long1 == null)
                                    long1 = Long.valueOf(0L);
                                jsonobjectproxy1.getStringOrNull("wname");
                                Button button = new Button(_fld0);
                                button.setText(s);
                                button.setGravity(17);
                                int j;
                                if(long1.longValue() == id)
                                {
                                    button.setBackgroundResource(0x7f020066);
                                    button.setTextColor(getResources().getColor(0x7f070007));
                                } else
                                {
                                    button.setBackgroundResource(0x7f020067);
                                    button.setTextColor(getResources().getColor(0x7f070008));
                                }
                                button.setOnClickListener(new OptionsOnClickListener(long1.longValue()));
                                j = (i + 1) % 3;
                                if(j == 1)
                                {
                                    linearlayout1 = new LinearLayout(_fld0);
                                    linearlayout1.setOrientation(0);
                                }
                                linearlayout1.addView(button, layoutParams);
                                if(j == 0 || i == jsonColorArray.length() - 1)
                                    linearlayout.addView(linearlayout1, new android.widget.LinearLayout.LayoutParams(-1, -2));
                                break MISSING_BLOCK_LABEL_415;
                            }

                            break MISSING_BLOCK_LABEL_414;
                            jsonexception3;
                            if(Log.D)
                                Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception3);
                        }

                        final _cls18 this$1;
                        private final JSONArrayPoxy val$jsonColorArray;
                        private final View val$line;
                        private final LinearLayout val$productOptionsColorView;
                        private final RelativeLayout val$productOptionsView;

                    
                    {
                        this$1 = _cls18.this;
                        productOptionsColorView = linearlayout;
                        productOptionsView = relativelayout;
                        line = view;
                        jsonColorArray = jsonarraypoxy;
                        super();
                    }
                    }
);
                }
                jsonarraypoxy1 = null;
                jsonarraypoxy2 = jsonobjectproxy.getJSONArray("skuSize");
                jsonarraypoxy1 = jsonarraypoxy2;
_L2:
                JSONException jsonexception2;
                if(jsonarraypoxy1 == null || jsonarraypoxy1.length() < 1)
                {
                    post(new Runnable() {

                        public void run()
                        {
                            productOptionsSizeView.setVisibility(8);
                            if(productOptionsColorView.getVisibility() == 8 && productOptionsSizeView.getVisibility() == 8)
                            {
                                productOptionsView.setVisibility(8);
                                line.setVisibility(8);
                            }
                        }

                        final _cls18 this$1;
                        private final View val$line;
                        private final LinearLayout val$productOptionsColorView;
                        private final LinearLayout val$productOptionsSizeView;
                        private final RelativeLayout val$productOptionsView;

                    
                    {
                        this$1 = _cls18.this;
                        productOptionsSizeView = linearlayout;
                        productOptionsColorView = linearlayout1;
                        productOptionsView = relativelayout;
                        line = view;
                        super();
                    }
                    }
);
                } else
                {
                    final JSONArrayPoxy jsonSizeArray = jsonarraypoxy1;
                    post(new Runnable() {

                        public void run()
                        {
                            productOptionsSizeView.setVisibility(0);
                            productOptionsView.setVisibility(0);
                            line.setVisibility(0);
                            LinearLayout linearlayout;
                            LinearLayout linearlayout1;
                            removeAllLinearLayout(productOptionsSizeView);
                            linearlayout = new LinearLayout(_fld0);
                            linearlayout.setOrientation(1);
                            productOptionsSizeView.addView(linearlayout, new android.widget.LinearLayout.LayoutParams(-1, -2));
                            linearlayout1 = null;
                            JSONException jsonexception3;
                            for(int i = 0; i < jsonSizeArray.length(); i++)
                            {
                                JSONObjectProxy jsonobjectproxy1 = jsonSizeArray.getJSONObject(i);
                                jsonobjectproxy1.getStringOrNull("promotion");
                                String s = jsonobjectproxy1.getStringOrNull("size");
                                long l = jsonobjectproxy1.getLongOrNull("wareId").longValue();
                                Button button = new Button(_fld0);
                                button.setText(s);
                                button.setGravity(17);
                                int j;
                                if(l == id)
                                {
                                    button.setBackgroundResource(0x7f020066);
                                    button.setTextColor(getResources().getColor(0x7f070007));
                                } else
                                {
                                    button.setBackgroundResource(0x7f020067);
                                    button.setTextColor(getResources().getColor(0x7f070008));
                                }
                                button.setOnClickListener(new OptionsOnClickListener(l));
                                j = (i + 1) % 4;
                                if(j == 1)
                                {
                                    linearlayout1 = new LinearLayout(_fld0);
                                    linearlayout1.setOrientation(0);
                                }
                                linearlayout1.addView(button, layoutParams);
                                if(j == 0 || i == jsonSizeArray.length() - 1)
                                    linearlayout.addView(linearlayout1, new android.widget.LinearLayout.LayoutParams(-1, -2));
                                break MISSING_BLOCK_LABEL_368;
                            }

                            break MISSING_BLOCK_LABEL_367;
                            jsonexception3;
                            if(Log.D)
                                Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception3);
                        }

                        final _cls18 this$1;
                        private final JSONArrayPoxy val$jsonSizeArray;
                        private final View val$line;
                        private final LinearLayout val$productOptionsSizeView;
                        private final RelativeLayout val$productOptionsView;

                    
                    {
                        this$1 = _cls18.this;
                        productOptionsSizeView = linearlayout;
                        productOptionsView = relativelayout;
                        line = view;
                        jsonSizeArray = jsonarraypoxy;
                        super();
                    }
                    }
);
                }
                if(productOptionsColorView.getVisibility() == 8 && productOptionsSizeView.getVisibility() == 8)
                {
                    productOptionsView.setVisibility(8);
                    line.setVisibility(8);
                }
                return;
                jsonexception1;
                if(Log.D)
                    Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception1);
                  goto _L1
                jsonexception2;
                if(Log.D)
                    Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception2);
                  goto _L2
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                post(new Runnable() {

                    public void run()
                    {
                        showStock();
                    }

                    final _cls18 this$1;

                    
                    {
                        this$1 = _cls18.this;
                        super();
                    }
                }
);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailActivity this$0;
            private final View val$line;
            private final LinearLayout val$productOptionsColorView;
            private final LinearLayout val$productOptionsSizeView;
            private final RelativeLayout val$productOptionsView;


            
            {
                this$0 = ProductDetailActivity.this;
                productOptionsColorView = linearlayout;
                productOptionsSizeView = linearlayout1;
                productOptionsView = relativelayout;
                line = view;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void showOrderCommentCount()
    {
        final TextView productOrderCommentCountTextView;
        final LinearLayout productOrderCommentCount;
        final View productOrderCommentCountNderline;
        JSONObject jsonobject;
        productOrderCommentCountTextView = (TextView)findViewById(0x7f0c0269);
        productOrderCommentCount = (LinearLayout)findViewById(0x7f0c0268);
        productOrderCommentCountNderline = findViewById(0x7f0c026a);
        productOrderCommentCount.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                productOrderCommentCount.setPressed(false);
                Intent intent = new Intent(ProductDetailActivity.this, com/jindong/app/mall/product/DiscussListActivity);
                Bundle bundle = new Bundle();
                bundle.putLong("id", product.getShowId().longValue());
                bundle.putString("name", product.getName());
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final ProductDetailActivity this$0;
            private final LinearLayout val$productOrderCommentCount;

            
            {
                this$0 = ProductDetailActivity.this;
                productOrderCommentCount = linearlayout;
                super();
            }
        }
);
        jsonobject = new JSONObject();
        jsonobject.put("wareId", (new StringBuilder()).append(id).toString());
_L2:
        httpGroup.add("orderCommentCount", jsonobject, new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final String num = httpresponse.getJSONObject().getStringOrNull("totalCount");
                if(num != null && !"".equals(num))
                    post(new Runnable() {

                        public void run()
                        {
                            productOrderCommentCount.setVisibility(0);
                            productOrderCommentCountNderline.setVisibility(0);
                            if(Integer.parseInt(num) == 0)
                            {
                                productOrderCommentCountTextView.setText(0x7f090055);
                                productOrderCommentCount.setClickable(false);
                            } else
                            {
                                productOrderCommentCountTextView.setText(getResources().getText(0x7f09005e));
                                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder((new StringBuilder("\uFF08")).append(num).append("\uFF09").toString());
                                spannablestringbuilder.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), 0, spannablestringbuilder.length(), 33);
                                productOrderCommentCountTextView.append(spannablestringbuilder);
                            }
                        }

                        final _cls20 this$1;
                        private final String val$num;
                        private final LinearLayout val$productOrderCommentCount;
                        private final View val$productOrderCommentCountNderline;
                        private final TextView val$productOrderCommentCountTextView;

                    
                    {
                        this$1 = _cls20.this;
                        productOrderCommentCount = linearlayout;
                        productOrderCommentCountNderline = view;
                        num = s;
                        productOrderCommentCountTextView = textview;
                        super();
                    }
                    }
);
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

            final ProductDetailActivity this$0;
            private final LinearLayout val$productOrderCommentCount;
            private final View val$productOrderCommentCountNderline;
            private final TextView val$productOrderCommentCountTextView;


            
            {
                this$0 = ProductDetailActivity.this;
                productOrderCommentCount = linearlayout;
                productOrderCommentCountNderline = view;
                productOrderCommentCountTextView = textview;
                super();
            }
        }
);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void showProduct()
    {
        final ProductImagesView productGalleryView;
        final LinearLayout productInfoView;
        final TextView producNameAndAdWordView;
        final TextView productMarketPriceView;
        final TextView productJdPriceView;
        final TextView productUserPriceView;
        final ImageView galleryLeftArrow;
        final ImageView galleryRightArrow;
        JSONObject jsonobject;
        productGalleryView = (ProductImagesView)findViewById(0x7f0c024b);
        productInfoView = (LinearLayout)findViewById(0x7f0c024f);
        producNameAndAdWordView = (TextView)findViewById(0x7f0c0250);
        productMarketPriceView = (TextView)findViewById(0x7f0c0253);
        productJdPriceView = (TextView)findViewById(0x7f0c0251);
        productUserPriceView = (TextView)findViewById(0x7f0c0252);
        galleryLeftArrow = (ImageView)findViewById(0x7f0c024c);
        galleryRightArrow = (ImageView)findViewById(0x7f0c024d);
        jsonobject = new JSONObject();
        jsonobject.put("wareId", (new StringBuilder()).append(id).toString());
_L2:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("productDetail");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                post(new Runnable() {

                    public void run()
                    {
                        showOptions();
                    }

                    final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                }
);
                try
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    JSONObjectProxy jsonobjectproxy1 = jsonobjectproxy.getJSONObject("productInfo");
                    JSONArrayPoxy jsonarraypoxy = jsonobjectproxy.getJSONArray("imagePaths");
                    final ProductShow productShow;
                    ProductDetailActivity productdetailactivity;
                    List list;
                    String as[];
                    int ai[];
                    final MySimpleAdapter mySimpleAdapter;
                    if(product == null)
                        product = new Product(jsonobjectproxy1, jsonarraypoxy, 3);
                    else
                        product.update(jsonobjectproxy1, jsonarraypoxy, 3);
                    product.setProvinceName(provinceName);
                    product.setProvinceID(provinceID);
                    initPacksButton(String.valueOf(id));
                    initCollectButton();
                    (new DBHelperUtil(ProductDetailActivity.this)).insertOrUpdateBrowseHistory(product.getId().longValue(), product.getName());
                    productInfoView.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            productInfoView.setPressed(false);
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailInfoActivity);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("product", product);
                            intent.putExtras(bundle);
                            startActivityInFrame(intent);
                        }

                        final _cls25 this$1;
                        private final LinearLayout val$productInfoView;

                    
                    {
                        this$1 = _cls25.this;
                        productInfoView = linearlayout;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            ((TextView)findViewById(0x7f0c02c7)).setText(product.getName());
                            commentBundle.putString("name", product.getName());
                            ProductShow.shareProduct((Button)findViewById(0x7f0c02c8), product);
                        }

                        final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                    }
);
                }
                catch(JSONException jsonexception1)
                {
                    if(Log.D)
                        Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception1);
                    break MISSING_BLOCK_LABEL_452;
                }
                if(product.getImageList().size() <= 0) goto _L2; else goto _L1
_L1:
                productdetailactivity = ProductDetailActivity.this;
                list = product.getImageList();
                as = new String[1];
                as[0] = "small";
                ai = new int[1];
                ai[0] = 0x7f0c026e;
                mySimpleAdapter = new MySimpleAdapter(productdetailactivity, list, 0x7f030072, as, ai);
                post(new Runnable() {

                    public void run()
                    {
                        productGalleryView.setAdapter(mySimpleAdapter);
                    }

                    final _cls25 this$1;
                    private final MySimpleAdapter val$mySimpleAdapter;
                    private final ProductImagesView val$productGalleryView;

                    
                    {
                        this$1 = _cls25.this;
                        productGalleryView = productimagesview;
                        mySimpleAdapter = mysimpleadapter;
                        super();
                    }
                }
);
                productGalleryView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        String s = ((Image)((Adapter)adapterview.getAdapter()).getItem(i)).getBig();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", i);
                        bundle.putString("url", s);
                        bundle.putSerializable("product", product);
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ImageActivity);
                        intent.putExtras(bundle);
                        intent.putExtra("com.360buy:navigationDisplayFlag", -1);
                        startActivityInFrame(intent);
                    }

                    final _cls25 this$1;

                    
                    {
                        this$1 = _cls25.this;
                        super();
                    }
                }
);
                productGalleryView.setOnBorderListener(new com.jindong.app.mall.utils.ProductImagesView.BorderListener() {

                    public void onChange(com.jindong.app.mall.utils.ProductImagesView.Border border)
                    {
                        if(border.left == 0)
                            galleryLeftArrow.setVisibility(8);
                        else
                        if(border.left == 1)
                            galleryLeftArrow.setVisibility(0);
                        if(border.right == 0)
                            galleryRightArrow.setVisibility(8);
                        else
                        if(border.right == 1)
                            galleryRightArrow.setVisibility(0);
                    }

                    final _cls25 this$1;
                    private final ImageView val$galleryLeftArrow;
                    private final ImageView val$galleryRightArrow;

                    
                    {
                        this$1 = _cls25.this;
                        galleryLeftArrow = imageview;
                        galleryRightArrow = imageview1;
                        super();
                    }
                }
);
_L4:
                productShow = new ProductShow(ProductDetailActivity.this, product);
                post(new Runnable() {

                    public void run()
                    {
                        producNameAndAdWordView.setText(productShow.getNameAndAdWord());
                        productJdPriceView.setText(productShow.getJdPrice());
                        if(product.getUserPriceLabel() != null)
                        {
                            productUserPriceView.setVisibility(0);
                            productUserPriceView.setText(productShow.getUserPrice());
                        }
                        productMarketPriceView.setText(productShow.getMarketPrice());
                    }

                    final _cls25 this$1;
                    private final TextView val$producNameAndAdWordView;
                    private final TextView val$productJdPriceView;
                    private final TextView val$productMarketPriceView;
                    private final ProductShow val$productShow;
                    private final TextView val$productUserPriceView;

                    
                    {
                        this$1 = _cls25.this;
                        producNameAndAdWordView = textview;
                        productShow = productshow;
                        productJdPriceView = textview1;
                        productUserPriceView = textview2;
                        productMarketPriceView = textview3;
                        super();
                    }
                }
);
                break MISSING_BLOCK_LABEL_452;
_L2:
                post(new Runnable() {

                    public void run()
                    {
                        galleryLeftArrow.setVisibility(8);
                        galleryRightArrow.setVisibility(8);
                    }

                    final _cls25 this$1;
                    private final ImageView val$galleryLeftArrow;
                    private final ImageView val$galleryRightArrow;

                    
                    {
                        this$1 = _cls25.this;
                        galleryLeftArrow = imageview;
                        galleryRightArrow = imageview1;
                        super();
                    }
                }
);
                if(true) goto _L4; else goto _L3
_L3:
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

            final ProductDetailActivity this$0;
            private final ImageView val$galleryLeftArrow;
            private final ImageView val$galleryRightArrow;
            private final TextView val$producNameAndAdWordView;
            private final ProductImagesView val$productGalleryView;
            private final LinearLayout val$productInfoView;
            private final TextView val$productJdPriceView;
            private final TextView val$productMarketPriceView;
            private final TextView val$productUserPriceView;


            
            {
                this$0 = ProductDetailActivity.this;
                productInfoView = linearlayout;
                productGalleryView = productimagesview;
                galleryLeftArrow = imageview;
                galleryRightArrow = imageview1;
                producNameAndAdWordView = textview;
                productJdPriceView = textview1;
                productUserPriceView = textview2;
                productMarketPriceView = textview3;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.D)
            Log.d(com/jindong/app/mall/product/ProductDetailActivity.getName(), "JSONException -->> ", jsonexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private AlertDialog showSelector(String as[], int i, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        return (new android.app.AlertDialog.Builder(this)).setSingleChoiceItems(as, i, onclicklistener).show();
    }

    private void showStock()
    {
        if(product.getProvinceStockMode().intValue() == 1)
            provinceStockMode1();
        else
            provinceStockMode0();
    }

    public void ShowPacksButton(String s)
    {
        if(Log.D)
            Log.d("PacksListActivity-update", "ShowPacksButton() -->> ");
        JSONObject jsonobject = new JSONObject();
        if(s != null && s != "" && s != " " && jsonobject != null)
        {
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
            try
            {
                jsonobject.put("wareId", s);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setPost(true);
            httpsetting.setFunctionId(config.GET_PACKS);
            httpsetting.setJsonParams(jsonobject);
            httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    final LinearLayout packsButton;
                    final View packsButtonNderline;
                    JSONArrayPoxy jsonarraypoxy;
                    packsButton = (LinearLayout)findViewById(0x7f0c0264);
                    packsButtonNderline = findViewById(0x7f0c0265);
                    jsonarraypoxy = httpresponse.getJSONObject().getJSONArrayOrNull("data");
                    if(jsonarraypoxy == null || jsonarraypoxy.length() <= 0) goto _L2; else goto _L1
_L1:
                    post(new Runnable() {

                        public void run()
                        {
                            packsButton.setVisibility(0);
                            packsButtonNderline.setVisibility(0);
                        }

                        final _cls6 this$1;
                        private final LinearLayout val$packsButton;
                        private final View val$packsButtonNderline;

                    
                    {
                        this$1 = _cls6.this;
                        packsButton = linearlayout;
                        packsButtonNderline = view;
                        super();
                    }
                    }
);
                    packsButton.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            Contants.packMainProdId = id;
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/PacksListActivity);
                            startActivityInFrame(intent);
                        }

                        final _cls6 this$1;

                    
                    {
                        this$1 = _cls6.this;
                        super();
                    }
                    }
);
_L4:
                    initCartButton();
                    return;
_L2:
                    try
                    {
                        post(new Runnable() {

                            public void run()
                            {
                                packsButton.setVisibility(8);
                                packsButtonNderline.setVisibility(8);
                            }

                            final _cls6 this$1;
                            private final LinearLayout val$packsButton;
                            private final View val$packsButtonNderline;

                    
                    {
                        this$1 = _cls6.this;
                        packsButton = linearlayout;
                        packsButtonNderline = view;
                        super();
                    }
                        }
);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                {
                    if(Log.D)
                        Log.d("PacksListActivity-update", (new StringBuilder("error -->> ")).append(httperror).toString());
                }

                public void onProgress(int i, int j)
                {
                    if(Log.D)
                        Log.d("PacksListActivity-update", (new StringBuilder("max -->> ")).append(i).toString());
                    if(Log.D)
                        Log.d("PacksListActivity-update", (new StringBuilder("progress -->> ")).append(j).toString());
                }

                public void onStart()
                {
                    if(Log.D)
                        Log.d("PacksListActivity-update", "setUpConnAndGetData()-start");
                }

                final ProductDetailActivity this$0;


            
            {
                this$0 = ProductDetailActivity.this;
                super();
            }
            }
);
            if(Log.D)
                Log.d("PacksListActivity-update", "open connection -->> ");
            getHttpGroupaAsynPool().add(httpsetting);
        }
    }

    public void canClickEasyBuyBtn()
    {
        easyButton.setClickable(true);
    }

    public void canEasyBuyWare(long l, final Button easyButton)
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
        getHttpGroupaAsynPool().add("easyBuySwitch", jsonobject, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                if(jsonobjectproxy != null)
                    try
                    {
                        if(jsonobjectproxy.toString().contains("easyBuy"))
                            if(jsonobjectproxy.getString("easyBuy") != null && jsonobjectproxy.getString("easyBuy").compareTo("true") == 0)
                                post(new Runnable() {

                                    public void run()
                                    {
                                        easyButton.setEnabled(true);
                                        easyButton.setVisibility(0);
                                    }

                                    final _cls5 this$1;
                                    private final Button val$easyButton;

                    
                    {
                        this$1 = _cls5.this;
                        easyButton = button;
                        super();
                    }
                                }
);
                            else
                                post(new Runnable() {

                                    public void run()
                                    {
                                        easyButton.setEnabled(false);
                                        easyButton.setVisibility(4);
                                    }

                                    final _cls5 this$1;
                                    private final Button val$easyButton;

                    
                    {
                        this$1 = _cls5.this;
                        easyButton = button;
                        super();
                    }
                                }
);
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
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

            final ProductDetailActivity this$0;
            private final Button val$easyButton;

            
            {
                this$0 = ProductDetailActivity.this;
                easyButton = button;
                super();
            }
        }
);
    }

    public boolean canUseEasyByStock(Product product1)
    {
        String s = product1.getProvinceStockContent();
        Boolean boolean1 = product1.getProvinceStockFlag();
        boolean flag;
        if(s != null && boolean1 != null)
            flag = boolean1.booleanValue();
        else
            flag = false;
        return flag;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030071);
        httpGroup = getHttpGroupaAsynPool();
        Bundle bundle1 = getIntent().getExtras();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("click");
        httpsetting.putJsonParam("wareId", (new StringBuilder()).append(bundle1.getLong("id")).toString());
        httpsetting.putJsonParam("type", "1");
        httpGroup.add(httpsetting);
        init();
        refresh(bundle1.getLong("id"));
        initCartButton();
    }

    protected void onResume()
    {
        super.onResume();
        if(product != null)
            initCollectButton();
    }

    private Drawable background;
    private android.content.DialogInterface.OnClickListener cityOnClickListener;
    private AlertDialog citySelectorDialog;
    Bundle commentBundle;
    private DBHelperUtil dbService;
    private Button easyButton;
    private HttpGroup httpGroup;
    private long id;
    private android.widget.LinearLayout.LayoutParams layoutParams;
    private int mCitySelectId;
    private int mDefaultCityId;
    private int mDefaultProvinceId;
    private int mProvinceSelectId;
    private Product product;
    private TextView productStockProvinceChoosed;
    private String provinceID;
    private String provinceName;
    private android.content.DialogInterface.OnClickListener provinceOnClickListener;
    private int provincePosition;
    private AlertDialog provinceSelectorDialog;
    private ImageButton stockProvinceButton;
    private TextView stockResultText;














































}
