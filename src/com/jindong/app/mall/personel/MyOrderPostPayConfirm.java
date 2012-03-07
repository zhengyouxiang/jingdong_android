// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.*;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class MyOrderPostPayConfirm extends MyActivity
{

    public MyOrderPostPayConfirm()
    {
        mDateSetListener = new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker datepicker, int i, int j, int k)
            {
                mYear = i;
                mMonth = j;
                mDay = k;
                updateDisplay();
            }

            final MyOrderPostPayConfirm this$0;

            
            {
                this$0 = MyOrderPostPayConfirm.this;
                super();
            }
        }
;
    }

    private void initView()
    {
        submitBtn = (Button)findViewById(0x7f0c018a);
        submitBtn.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                submit();
            }

            final MyOrderPostPayConfirm this$0;

            
            {
                this$0 = MyOrderPostPayConfirm.this;
                super();
            }
        }
);
        payCity = (TextView)findViewById(0x7f0c017f);
        selectPayment = (TextView)findViewById(0x7f0c0181);
        payDate = (TextView)findViewById(0x7f0c0183);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(1);
        mMonth = calendar.get(2);
        mDay = calendar.get(5);
        payDate.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new DatePickerDialog(MyOrderPostPayConfirm.this, mDateSetListener, mYear, mMonth, mDay)).show();
            }

            final MyOrderPostPayConfirm this$0;

            
            {
                this$0 = MyOrderPostPayConfirm.this;
                super();
            }
        }
);
        payOrderId = (TextView)findViewById(0x7f0c0184);
        payMethod = (TextView)findViewById(0x7f0c0185);
        bankName = (TextView)findViewById(0x7f0c0187);
        payMoney = (TextView)findViewById(0x7f0c0188);
        payRemark = (TextView)findViewById(0x7f0c0189);
        payCityBtn = (ImageButton)findViewById(0x7f0c0180);
        selectPaymentBtn = (ImageButton)findViewById(0x7f0c0182);
        payMethodBtn = (ImageButton)findViewById(0x7f0c0186);
        final String cityItems[] = getResources().getStringArray(0x7f0a0000);
        cityCodeArray = getResources().getStringArray(0x7f0a0001);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MyOrderPostPayConfirm.this);
                builder.setTitle(0x7f0900e6);
                builder.setSingleChoiceItems(cityItems, 1, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        payCity.setText(cityItems[i]);
                        payCity.setTag(cityCodeArray[i]);
                        dialoginterface.dismiss();
                    }

                    final _cls4 this$1;
                    private final String val$cityItems[];

                    
                    {
                        this$1 = _cls4.this;
                        cityItems = as;
                        super();
                    }
                }
);
                builder.show();
            }

            final MyOrderPostPayConfirm this$0;
            private final String val$cityItems[];


            
            {
                this$0 = MyOrderPostPayConfirm.this;
                cityItems = as;
                super();
            }
        }
;
        payCity.setOnClickListener(onclicklistener);
        payCity.setClickable(true);
        payCityBtn.setOnClickListener(onclicklistener);
        selectPaymentCodyArray = getResources().getStringArray(0x7f0a0004);
        android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MyOrderPostPayConfirm.this);
                builder.setTitle(0x7f0900e8);
                builder.setSingleChoiceItems(selectPaymentItems, 1, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        selectPayment.setText(selectPaymentItems[i]);
                        selectPayment.setTag(selectPaymentCodyArray[i]);
                        dialoginterface.dismiss();
                    }

                    final _cls5 this$1;
                    private final String val$selectPaymentItems[];

                    
                    {
                        this$1 = _cls5.this;
                        selectPaymentItems = as;
                        super();
                    }
                }
);
                builder.show();
            }

            final MyOrderPostPayConfirm this$0;
            private final String val$selectPaymentItems[];


            
            {
                this$0 = MyOrderPostPayConfirm.this;
                selectPaymentItems = as;
                super();
            }
        }
;
        selectPayment.setOnClickListener(onclicklistener1);
        selectPayment.setClickable(true);
        selectPaymentBtn.setOnClickListener(onclicklistener1);
        android.view.View.OnClickListener onclicklistener2 = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MyOrderPostPayConfirm.this);
                builder.setTitle(0x7f0900ef);
                builder.setSingleChoiceItems(paymethodItems, 1, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        payMethod.setText(paymethodItems[i]);
                        dialoginterface.dismiss();
                    }

                    final _cls6 this$1;
                    private final String val$paymethodItems[];

                    
                    {
                        this$1 = _cls6.this;
                        paymethodItems = as;
                        super();
                    }
                }
);
                builder.show();
            }

            final MyOrderPostPayConfirm this$0;
            private final String val$paymethodItems[];


            
            {
                this$0 = MyOrderPostPayConfirm.this;
                paymethodItems = as;
                super();
            }
        }
;
        payMethod.setOnClickListener(onclicklistener2);
        payMethod.setClickable(true);
        payMethodBtn.setOnClickListener(onclicklistener2);
    }

    private void submit()
    {
        boolean flag = false;
        if(TextUtils.isEmpty(payOrderId.getText().toString()))
        {
            payOrderId.setError(getString(0x7f0900ec));
            flag = true;
        }
        if(TextUtils.isEmpty(payMethod.getText().toString()))
        {
            payOrderId.setError(getString(0x7f0900ed));
            flag = true;
        }
        if(!flag)
        {
            JSONObject jsonobject = new JSONObject();
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
            try
            {
                jsonobject.put("paymoney", payMoney.getText().toString());
                jsonobject.put("selectpayment", selectPayment.getTag());
                jsonobject.put("orderId", product.getOrderId());
                jsonobject.put("bankname", bankName.getText().toString());
                jsonobject.put("paydate", payDate.getText().toString());
                jsonobject.put("paymethod", payMethod.getText().toString());
                jsonobject.put("payorderid", payOrderId.getText().toString());
                jsonobject.put("payremark", payRemark.getText().toString());
                jsonobject.put("paycity", payCity.getTag());
                jsonobject.put("payment", "102");
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.setFunctionId("confirmSubmitInfo");
            httpsetting.setJsonParams(jsonobject);
            httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    final String submitInfo = httpresponse.getJSONObject().getStringOrNull("submitInfo");
                    if(submitInfo != null && submitInfo.length() > 1)
                    {
                        if(submitInfo.contains(getString(0x7f0901d0)))
                        {
                            post(new Runnable() {

                                public void run()
                                {
                                    Toast.makeText(getApplicationContext(), submitInfo, 0);
                                    putBooleanToPreference("post_order_confrim_flag", Boolean.valueOf(true));
                                    finish();
                                }

                                final _cls7 this$1;
                                private final String val$submitInfo;

                    
                    {
                        this$1 = _cls7.this;
                        submitInfo = s;
                        super();
                    }
                            }
);
                            setResult(-1);
                        } else
                        {
                            showDialog(submitInfo);
                        }
                    } else
                    {
                        showDialog(getString(0x7f0901d1));
                    }
                }

                public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                {
                    showDialog(getString(0x7f0901d1));
                }

                public void onProgress(int i, int j)
                {
                }

                public void onStart()
                {
                }

                final MyOrderPostPayConfirm this$0;


            
            {
                this$0 = MyOrderPostPayConfirm.this;
                super();
            }
            }
);
            httpsetting.setNotifyUser(true);
            getHttpGroupaAsynPool().add(httpsetting);
        }
    }

    private void updateDisplay()
    {
        payDate.setText((new StringBuilder()).append(1 + mYear).append("-").append(mMonth).append("-").append(mDay).toString());
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004e);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f0900e3);
        intent = getIntent();
        product = (Product)intent.getSerializableExtra("product");
        initView();
    }

    void showDialog(String s)
    {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(0x7f0900d8);
        alertDialogBuilder.setMessage(s);
        alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            final MyOrderPostPayConfirm this$0;

            
            {
                this$0 = MyOrderPostPayConfirm.this;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                alertDialogBuilder.show();
            }

            final MyOrderPostPayConfirm this$0;
            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

            
            {
                this$0 = MyOrderPostPayConfirm.this;
                alertDialogBuilder = builder;
                super();
            }
        }
);
    }

    private TextView bankName;
    private String cityCodeArray[];
    private Intent intent;
    private android.app.DatePickerDialog.OnDateSetListener mDateSetListener;
    private int mDay;
    private int mMonth;
    private int mYear;
    private TextView payCity;
    private ImageButton payCityBtn;
    private TextView payDate;
    private TextView payMethod;
    private ImageButton payMethodBtn;
    private TextView payMoney;
    private TextView payOrderId;
    private TextView payRemark;
    Product product;
    private TextView selectPayment;
    private ImageButton selectPaymentBtn;
    private String selectPaymentCodyArray[];
    private Button submitBtn;














}
