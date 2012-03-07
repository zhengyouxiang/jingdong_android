// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.personel.CheckMyOrderDetail;
import com.jindong.app.mall.personel.PersonelActivity;
import com.jindong.app.mall.utils.Contants;
import com.jindong.app.mall.utils.MyActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class CompleteOrderActivity extends MyActivity
{

    public CompleteOrderActivity()
    {
    }

    private void handleClickEvent()
    {
        mCompleted.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(Contants.bEasyBuy)
                {
                    Intent intent = new Intent(CompleteOrderActivity.this, com/jindong/app/mall/personel/CheckMyOrderDetail);
                    Product product = new Product();
                    try
                    {
                        product.setOrderId(String.valueOf(Contants.jbOrderNum.get("orderId")));
                    }
                    catch(JSONException jsonexception)
                    {
                        jsonexception.printStackTrace();
                    }
                    intent.putExtra("product", product);
                    startActivityInFrame(intent);
                } else
                {
                    Intent intent1 = new Intent(CompleteOrderActivity.this, com/jindong/app/mall/personel/PersonelActivity);
                    startActivityInFrame(intent1);
                }
            }

            final CompleteOrderActivity this$0;

            
            {
                this$0 = CompleteOrderActivity.this;
                super();
            }
        }
);
    }

    private void initComponent()
    {
        mOrderNo = (TextView)findViewById(0x7f0c0074);
        mPayMoney = (TextView)findViewById(0x7f0c0076);
        mPayWay = (TextView)findViewById(0x7f0c0078);
        mPayMsg = (TextView)findViewById(0x7f0c007a);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mCompleted = (Button)findViewById(0x7f0c007c);
        if(Contants.bEasyBuy)
        {
            mCompleted.setText(0x7f0900f7);
            mTitle.setText(0x7f0900f8);
        } else
        {
            mTitle.setText(0x7f09013f);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030018);
        noShowAgain();
        initComponent();
        Bundle bundle1 = getIntent().getExtras();
        if(bundle1.getString("order_no") != null)
            mOrderNo.setText(bundle1.getString("order_no"));
        if(bundle1.getString("order_money") != null)
            mPayMoney.setText((new StringBuilder(String.valueOf(bundle1.getString("order_money")))).append("\u5143").toString());
        if(bundle1.getString("order_way") != null)
            mPayWay.setText(bundle1.getString("order_way"));
        if(bundle1.getString("order_msg") != null)
            mPayMsg.setText(bundle1.getString("order_msg"));
        Contants.skusOfSuites = null;
        handleClickEvent();
    }

    Button mCompleted;
    TextView mOrderNo;
    TextView mPayMoney;
    TextView mPayMsg;
    TextView mPayWay;
    TextView mTitle;
}
