// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MyOrderDetailActivity extends MyActivity
{

    public MyOrderDetailActivity()
    {
        itemHeight = 0;
        flag_once = false;
    }

    private void getIntentInfo(Intent intent)
    {
        params = new JSONObject();
        String s = getPreferences(0).getString("pin", "");
        params.put("pin", s);
        params.put("orderId", intent.getStringExtra("orderId"));
        mProductlist = (ArrayList)intent.getSerializableExtra("productList");
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L1
    }

    private void getOrderInfo()
    {
        if(params.length() >= 1)
        {
            com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    try
                    {
                        jsonOrderDetail = httpresponse.getJSONObject().getJSONArray("orderInfo");
                        if(Log.D)
                            Log.d("Order information", jsonOrderDetail.toString());
                        setOrderDetailInfo();
                    }
                    catch(JSONException jsonexception)
                    {
                        jsonexception.printStackTrace();
                    }
                    if(Log.D)
                        Log.d("Order information", "End to get Order information...");
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

                final MyOrderDetailActivity this$0;

            
            {
                this$0 = MyOrderDetailActivity.this;
                super();
            }
            }
;
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setFunctionId(functionId);
            httpsetting.setJsonParams(params);
            httpsetting.setListener(onalllistener);
            httpsetting.setNotifyUser(true);
            getHttpGroupaAsynPool().add(httpsetting);
        }
    }

    private void initComponent()
    {
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090119);
        mainContent = (LinearLayout)findViewById(0x7f0c01c0);
        info1 = (LinearLayout)findViewById(0x7f0c01c1);
        info2 = (LinearLayout)findViewById(0x7f0c01c2);
        info3 = (LinearLayout)findViewById(0x7f0c01c3);
        info4 = (LinearLayout)findViewById(0x7f0c01c4);
        info5 = (LinearLayout)findViewById(0x7f0c01c5);
    }

    private SpannableStringBuilder markValueStringGray(String s, String s1)
    {
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        spannablestringbuilder.append(s).append(s1);
        spannablestringbuilder.setSpan(blackColorSpan, 0, s.length(), 33);
        if(!TextUtils.isEmpty(s1))
            spannablestringbuilder.setSpan(greyColorSpan, s.length(), s.length() + s1.length(), 33);
        return spannablestringbuilder;
    }

    private void setOrderDetailInfo()
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                if(jsonOrderDetail != null)
                    try
                    {
                        mainContent.setVisibility(0);
                        greyColorSpan = new ForegroundColorSpan(getResources().getColor(0x7f070006));
                        blackColorSpan = new ForegroundColorSpan(getResources().getColor(0x106000c));
                        if(jsonOrderDetail.getJSONArray(0) != null && jsonOrderDetail.getJSONArrayOrNull(0).length() > 0)
                            setTxView(info1, jsonOrderDetail.getJSONArray(0));
                        if(jsonOrderDetail.getJSONArrayOrNull(1) != null && jsonOrderDetail.getJSONArrayOrNull(1).length() > 0)
                            setTxView(info2, jsonOrderDetail.getJSONArrayOrNull(1));
                        if(jsonOrderDetail.getJSONArrayOrNull(2) != null && jsonOrderDetail.getJSONArrayOrNull(2).length() > 0)
                            setTxView(info3, jsonOrderDetail.getJSONArrayOrNull(2));
                        if(jsonOrderDetail.getJSONArrayOrNull(3) != null && jsonOrderDetail.getJSONArrayOrNull(3).length() > 0)
                            setTxView(info4, jsonOrderDetail.getJSONArray(3));
                        if(jsonOrderDetail.getJSONArrayOrNull(4) != null && jsonOrderDetail.getJSONArrayOrNull(4).length() > 0)
                            setTxView(info5, jsonOrderDetail.getJSONArrayOrNull(4));
                        else
                            info5.setVisibility(8);
                    }
                    catch(JSONException jsonexception)
                    {
                        jsonexception.printStackTrace();
                    }
            }

            final MyOrderDetailActivity this$0;

            
            {
                this$0 = MyOrderDetailActivity.this;
                super();
            }
        }
);
    }

    private void setProductList()
    {
        final ListView mProductListView = (ListView)findViewById(0x7f0c01c6);
        final android.view.ViewGroup.LayoutParams layoutParams = mProductListView.getLayoutParams();
        ArrayList arraylist = mProductlist;
        String as[] = new String[1];
        as[0] = "imageUrl";
        int ai[] = new int[1];
        ai[0] = 0x7f0c01ea;
        mProductListView.setAdapter(new MySimpleAdapter(this, arraylist, 0x7f03005e, as, ai) {

            public View getView(int i, View view, ViewGroup viewgroup)
            {
                View view1 = super.getView(i, view, viewgroup);
                TextView textview = (TextView)view1.findViewById(0x7f0c01eb);
                Product product = (Product)getItem(i);
                textview.setText(product.getName());
                TextView textview1 = (TextView)view1.findViewById(0x7f0c01ed);
                MyOrderDetailActivity myorderdetailactivity = MyOrderDetailActivity.this;
                Object aobj[] = new Object[1];
                aobj[0] = product.getNum();
                textview1.setText(myorderdetailactivity.getString(0x7f0900de, aobj));
                return view1;
            }

            final MyOrderDetailActivity this$0;

            
            {
                this$0 = MyOrderDetailActivity.this;
                super(myactivity, list, i, as, ai);
            }
        }
);
        mProductListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                Intent intent = new Intent(MyOrderDetailActivity.this, com/jindong/app/mall/product/ProductDetailActivity);
                Bundle bundle = new Bundle();
                bundle.putLong("id", product.getId().longValue());
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final MyOrderDetailActivity this$0;

            
            {
                this$0 = MyOrderDetailActivity.this;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                if(mProductListView.getChildCount() > 0)
                {
                    int i = mProductListView.getChildAt(0).getMeasuredHeight();
                    int j = mProductListView.getDividerHeight();
                    layoutParams.height = (i + j) * mProductlist.size();
                    mProductListView.invalidate();
                }
            }

            final MyOrderDetailActivity this$0;
            private final android.view.ViewGroup.LayoutParams val$layoutParams;
            private final ListView val$mProductListView;

            
            {
                this$0 = MyOrderDetailActivity.this;
                mProductListView = listview;
                layoutParams = layoutparams;
                super();
            }
        }
);
    }

    private void setTxView(LinearLayout linearlayout, JSONArrayPoxy jsonarraypoxy)
        throws JSONException
    {
        if(jsonarraypoxy == null || jsonarraypoxy.length() == 0) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = jsonarraypoxy.length();
        j = 0;
_L5:
        if(j < i) goto _L3; else goto _L2
_L2:
        if(jsonarraypoxy != null && jsonarraypoxy.length() <= 1)
        {
            TextView textview = new TextView(getBaseContext());
            textview.setText(0x7f0900b0);
            textview.setSaveEnabled(false);
            linearlayout.addView(textview, linearlayout.getChildCount());
        }
        return;
_L3:
        JSONObjectProxy jsonobjectproxy = jsonarraypoxy.getJSONObjectOrNull(j);
        TextView textview1 = new TextView(getBaseContext());
        String s = jsonobjectproxy.getStringOrNull("label");
        String s1 = jsonobjectproxy.getStringOrNull("value");
        if(s1 == null)
            s1 = "";
        textview1.setText(markValueStringGray(s, s1));
        textview1.setLineSpacing(1.0F, 1.5F);
        textview1.setSaveEnabled(false);
        linearlayout.addView(textview1, j);
        j++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030056);
        Intent intent = getIntent();
        functionId = "orderInfo";
        getIntentInfo(intent);
        initComponent();
        setProductList();
        getOrderInfo();
    }

    public void onStart()
    {
        super.onStart();
    }

    ForegroundColorSpan blackColorSpan;
    boolean flag_once;
    private String functionId;
    ForegroundColorSpan greyColorSpan;
    LinearLayout info1;
    LinearLayout info2;
    LinearLayout info3;
    LinearLayout info4;
    LinearLayout info5;
    int itemHeight;
    private JSONArrayPoxy jsonOrderDetail;
    ArrayList mProductlist;
    LinearLayout mainContent;
    private JSONObject params;




}
