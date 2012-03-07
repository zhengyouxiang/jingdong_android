// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.gis.GisUrlUtil;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.personel:
//            MyOrderDetailActivity, MyOrderPostPayConfirm, MyOrderListActivity

public class CheckMyOrderDetail extends MyActivity
{

    public CheckMyOrderDetail()
    {
        postConfrimFlag = false;
        cancleOrder = false;
    }

    private void cancelOrder()
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setPost(false);
        httpsetting.setFunctionId("cancleOrder");
        JSONObject jsonobject = new JSONObject();
        final android.app.AlertDialog.Builder alertDialogBuilder;
        try
        {
            jsonobject.put("orderId", product.getOrderId());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        httpsetting.setJsonParams(jsonobject);
        noShowAgain();
        alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(0x7f090112);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(httpresponse.getJSONObject() != null)
                {
                    final String message = httpresponse.getJSONObject().getStringOrNull("cancelInfo");
                    post(new Runnable() {

                        public void run()
                        {
                            alertDialogBuilder.setTitle(message);
                            alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int i)
                                {
                                    if(message.contains(getString(0x7f0901d0)))
                                    {
                                        finish();
                                        Intent intent = new Intent(_fld0, com/jindong/app/mall/personel/MyOrderListActivity);
                                        startActivityInFrame(intent);
                                    }
                                    dialoginterface.dismiss();
                                }

                                final _cls2 this$2;
                                private final String val$message;

                        
                        {
                            this$2 = _cls2.this;
                            message = s;
                            super();
                        }
                            }
);
                            alertDialogBuilder.show();
                            orderCancelButton.setVisibility(8);
                        }

                        final _cls3 this$1;
                        private final android.app.AlertDialog.Builder val$alertDialogBuilder;
                        private final String val$message;


                    
                    {
                        this$1 = _cls3.this;
                        alertDialogBuilder = builder;
                        message = s;
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
                        alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                dialoginterface.dismiss();
                            }

                            final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super();
                        }
                        }
);
                        alertDialogBuilder.setTitle(0x7f0901ce);
                        alertDialogBuilder.show();
                    }

                    final _cls3 this$1;
                    private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls3.this;
                        alertDialogBuilder = builder;
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

            final CheckMyOrderDetail this$0;
            private final android.app.AlertDialog.Builder val$alertDialogBuilder;


            
            {
                this$0 = CheckMyOrderDetail.this;
                alertDialogBuilder = builder;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void getTraceInfo()
    {
        if(params.length() >= 1)
        {
            com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    JSONObjectProxy jsonobjectproxy;
                    jsonOrderDetail = httpresponse.getJSONObject().getJSONArray("orderMessageList");
                    wareInfoList = httpresponse.getJSONObject().getJSONArrayOrNull("wareInfoList");
                    jsonobjectproxy = httpresponse.getJSONObject().getJSONObjectOrNull("orderInfo");
                    if(jsonobjectproxy != null && jsonobjectproxy.length() != 0) goto _L2; else goto _L1
_L1:
                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(CheckMyOrderDetail.this);
                    alertDialogBuilder.setTitle(0x7f090112);
                    alertDialogBuilder.setIcon(0x108001d);
                    alertDialogBuilder.setMessage(0x7f0900e0);
                    alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            dialoginterface.dismiss();
                            finish();
                        }

                        final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            alertDialogBuilder.show();
                        }

                        final _cls5 this$1;
                        private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls5.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                    }
);
                      goto _L3
_L2:
                    product.setOrderStatus(jsonobjectproxy.getString("orderStatus"));
                    product.setOrderSubtime(jsonobjectproxy.getString("dataSubmit"));
                    product.setOrderPrice(jsonobjectproxy.getString("price"));
                    postConfrimFlag = jsonobjectproxy.getBoolean("confirmOrder");
                    cancleOrder = jsonobjectproxy.getBoolean("cancleOrder");
                    if(postConfrimFlag)
                        post(new Runnable() {

                            public void run()
                            {
                                mTitleRightButton.setVisibility(0);
                            }

                            final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                        }
);
                    if(cancleOrder)
                        post(new Runnable() {

                            public void run()
                            {
                                orderCancelButton.setVisibility(0);
                            }

                            final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                        }
);
                    setTraceInfo();
                    setGallery();
                    if(Log.D)
                        Log.d("order trace information", jsonOrderDetail.toString());
_L4:
                    if(Log.D)
                        Log.d("Order trace information", "End to get Order trace information...");
                    break; /* Loop/switch isn't completed */
                    JSONException jsonexception;
                    jsonexception;
                    jsonexception.printStackTrace();
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

                final CheckMyOrderDetail this$0;


            
            {
                this$0 = CheckMyOrderDetail.this;
                super();
            }
            }
;
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setFunctionId("orderMessage");
            httpsetting.setJsonParams(params);
            httpsetting.setListener(onalllistener);
            httpsetting.setNotifyUser(true);
            getHttpGroupaAsynPool().add(httpsetting);
        }
    }

    private void initComponent(final Product product)
    {
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090112);
        mTitleRightButton = (Button)findViewById(0x7f0c02c8);
        mTitleRightButton.setText(0x7f0900e3);
        mTitleRightButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mTitleRightButton.setPressed(false);
                Intent intent = new Intent(getApplicationContext(), com/jindong/app/mall/personel/MyOrderPostPayConfirm);
                intent.putExtra("product", product);
                putBooleanToPreference("post_order_confrim_flag", Boolean.valueOf(false));
                startActivityInFrame(intent);
            }

            final CheckMyOrderDetail this$0;
            private final Product val$product;

            
            {
                this$0 = CheckMyOrderDetail.this;
                product = product1;
                super();
            }
        }
);
        mOrderIdNum = (TextView)findViewById(0x7f0c01b2);
        mOrderStatus = (TextView)findViewById(0x7f0c01b4);
        mTotalPrice = (TextView)findViewById(0x7f0c01bc);
        mOrderTime = (TextView)findViewById(0x7f0c01bd);
        mTraceView = (RelativeLayout)findViewById(0x7f0c01b7);
        mOrderIdNum.setText(product.getOrderId());
        orderInfoLayout = findViewById(0x7f0c01ba);
        orderProductGalleryLayout = findViewById(0x7f0c01be);
        orderCancelButton = (Button)findViewById(0x7f0c01b6);
        orderCancelButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                orderCancelButton.setClickable(false);
                orderCancelButton.postDelayed(new Runnable() {

                    public void run()
                    {
                        orderCancelButton.setClickable(true);
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
, 1000L);
                cancelOrder();
            }

            final CheckMyOrderDetail this$0;


            
            {
                this$0 = CheckMyOrderDetail.this;
                super();
            }
        }
);
    }

    private void setGallery()
    {
        mProductlist = Product.toList(wareInfoList, 6);
        ArrayList arraylist = mProductlist;
        String as[] = new String[1];
        as[0] = "imageUrl";
        int ai[] = new int[1];
        ai[0] = 0x7f0c0110;
        final MySimpleAdapter adapter = new MySimpleAdapter(this, arraylist, 0x7f03005f, as, ai);
        final Gallery gallery = (Gallery)findViewById(0x7f0c01bf);
        post(new Runnable() {

            public void run()
            {
                orderProductGalleryLayout.setVisibility(0);
                gallery.setAdapter(adapter);
                if(mProductlist.size() > 1)
                    gallery.setSelection(1);
            }

            final CheckMyOrderDetail this$0;
            private final MySimpleAdapter val$adapter;
            private final Gallery val$gallery;

            
            {
                this$0 = CheckMyOrderDetail.this;
                gallery = gallery1;
                adapter = mysimpleadapter;
                super();
            }
        }
);
        gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Product product1 = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                Intent intent = new Intent(CheckMyOrderDetail.this, com/jindong/app/mall/product/ProductDetailActivity);
                Bundle bundle = new Bundle();
                bundle.putLong("id", product1.getId().longValue());
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final CheckMyOrderDetail this$0;

            
            {
                this$0 = CheckMyOrderDetail.this;
                super();
            }
        }
);
    }

    private void setTraceInfo()
    {
        post(new Runnable() {

            public void run()
            {
                LayoutInflater layoutinflater;
                orderInfoLayout.setVisibility(0);
                layoutinflater = LayoutInflater.from(getBaseContext());
                mOrderStatus.setText(product.getOrderStatus());
                mTotalPrice.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(product.getOrderPrice()).toString());
                mOrderTime.setText(product.getOrderSubtime());
                mTraceView = (ViewGroup)findViewById(0x7f0c01b8);
                traceListOther = (ViewGroup)findViewById(0x7f0c01b9);
                if(jsonOrderDetail == null || jsonOrderDetail.length() == 0) goto _L2; else goto _L1
_L1:
                int i;
                int j;
                i = jsonOrderDetail.length();
                j = 0;
_L11:
                if(j < i) goto _L4; else goto _L3
_L3:
                traceListOther.setVisibility(8);
                  goto _L5
_L4:
                View view = layoutinflater.inflate(0x7f03004f, null);
                j;
                JVM INSTR tableswitch 0 2: default 236
            //                           0 336
            //                           1 336
            //                           2 406;
                   goto _L6 _L7 _L7 _L8
_L6:
                traceListOther.addView(view);
                JSONObjectProxy jsonobjectproxy2 = jsonOrderDetail.getJSONObject(j);
                if(j == i - 1)
                {
                    imageViewLess = (ImageView)view.findViewById(0x7f0c018e);
                    imageViewLess.setImageResource(0x7f02003d);
                    imageViewLess.setVisibility(0);
                    view.findViewById(0x7f0c018f).setVisibility(8);
                }
                setTraceViewItem(view, jsonobjectproxy2);
                  goto _L9
_L7:
                JSONObjectProxy jsonobjectproxy1 = jsonOrderDetail.getJSONObject(j);
                setTraceViewItem(view, jsonobjectproxy1);
                mTraceView.addView(view, j);
                  goto _L9
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                if(Log.V)
                    Log.v(TAG, "trace item JSONException");
                  goto _L5
_L8:
                JSONObjectProxy jsonobjectproxy = jsonOrderDetail.getJSONObject(j);
                if(i > 3)
                {
                    imageViewMore = (ImageView)view.findViewById(0x7f0c018e);
                    imageViewMore.setImageResource(0x7f02003e);
                    imageViewMore.setVisibility(0);
                }
                setTraceViewItem(view, jsonobjectproxy);
                positionLine = view.findViewById(0x7f0c018f);
                positionLine.setVisibility(8);
                mTraceView.addView(view, j);
                  goto _L9
_L2:
                TextView textview = new TextView(getBaseContext());
                textview.setPadding(20, 10, 10, 10);
                textview.setText(0x7f0900df);
                textview.setTextColor(0xff000000);
                mTraceView.addView(textview);
                mTraceView.setClickable(false);
_L5:
                return;
_L9:
                j++;
                if(true) goto _L11; else goto _L10
_L10:
            }

            final CheckMyOrderDetail this$0;

            
            {
                this$0 = CheckMyOrderDetail.this;
                super();
            }
        }
);
    }

    private void setTraceViewItem(View view, JSONObject jsonobject)
        throws JSONException
    {
        TextView textview = (TextView)view.findViewById(0x7f0c018c);
        TextView textview1 = (TextView)view.findViewById(0x7f0c018d);
        textview.setText(jsonobject.getString("crateTime"));
        textview1.setText(jsonobject.getString("message"));
    }

    public void finish()
    {
        if(gisUrlUtil != null)
            gisUrlUtil.cancelHandler();
        super.finish();
    }

    public void handleClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131493302 2131493306: default 40
    //                   2131493302 40
    //                   2131493303 41
    //                   2131493304 40
    //                   2131493305 40
    //                   2131493306 134;
           goto _L1 _L1 _L2 _L1 _L1 _L3
_L1:
        return;
_L2:
        if(jsonOrderDetail != null && jsonOrderDetail.length() > 3)
            if(traceListOther.getVisibility() != 0)
            {
                traceListOther.setVisibility(0);
                imageViewLess.setVisibility(0);
                imageViewMore.setVisibility(8);
                positionLine.setVisibility(0);
            } else
            {
                traceListOther.setVisibility(8);
                imageViewMore.setVisibility(0);
                positionLine.setVisibility(8);
            }
        continue; /* Loop/switch isn't completed */
_L3:
        Intent intent = new Intent(this, com/jindong/app/mall/personel/MyOrderDetailActivity);
        Bundle bundle = new Bundle();
        try
        {
            bundle.putString("orderId", params.getString("orderId"));
            bundle.putString("pin", params.getString("pin"));
            bundle.putSerializable("productList", mProductlist);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        intent.putExtras(bundle);
        startActivityInFrame(intent);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        product = (Product)getIntent().getSerializableExtra("product");
        params = new JSONObject();
        try
        {
            params.put("pin", LoginUser.getLoginUserName());
            params.put("orderId", product.getOrderId());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        setContentView(0x7f030055);
        initComponent(product);
        getTraceInfo();
    }

    protected void onResume()
    {
        super.onResume();
        if(postConfrimFlag && getBooleanFromPreference("post_order_confrim_flag"))
            mTitleRightButton.setVisibility(8);
    }

    public void onStart()
    {
        super.onStart();
    }

    private final String TAG = com/jindong/app/mall/personel/CheckMyOrderDetail.getSimpleName();
    boolean cancleOrder;
    private GisUrlUtil gisUrlUtil;
    ImageView imageViewLess;
    ImageView imageViewMore;
    private JSONArrayPoxy jsonOrderDetail;
    TextView mAllTraceInfo;
    TextView mOrderIdNum;
    TextView mOrderStatus;
    TextView mOrderTime;
    ArrayList mProductlist;
    Button mTitleRightButton;
    TextView mTotalPrice;
    ViewGroup mTraceView;
    private Button orderCancelButton;
    View orderInfoLayout;
    View orderProductGalleryLayout;
    private JSONObject params;
    View positionLine;
    boolean postConfrimFlag;
    Product product;
    ViewGroup traceListOther;
    private JSONArrayPoxy wareInfoList;









}
