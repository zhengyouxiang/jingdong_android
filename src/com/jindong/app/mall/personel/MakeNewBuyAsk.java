// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeNewBuyAsk extends MyActivity
{

    public MakeNewBuyAsk()
    {
        TYPE_PRODUCT = "2";
        TYPE_STOCK_SEND = "3";
        TYPE_PAY = "4";
        TYPE_INVOICE_REPAIRE = "5";
        TYPE_PROMOTION = "6";
    }

    private boolean check()
    {
        content = askContent.getText().toString().trim();
        boolean flag;
        if(content.length() < 4 || content.length() > 100)
        {
            Toast.makeText(this, 0x7f0901ad, 0).show();
            flag = false;
        } else
        {
            if(Log.D)
                Log.d("MakeNewBuyAsk", (new StringBuilder("type:")).append(choosedType.getText().toString()).toString());
            choosedType.setText(0x7f090194);
            type = TYPE_PRODUCT;
            flag = true;
        }
        return flag;
    }

    private void handleSubmit()
    {
        submitButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!check())
                    break MISSING_BLOCK_LABEL_77;
                params.put("wareId", wareId);
                params.put("type", type);
                params.put("content", content);
                saveConsultation();
_L1:
                return;
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L1
            }

            final MakeNewBuyAsk this$0;

            
            {
                this$0 = MakeNewBuyAsk.this;
                super();
            }
        }
);
    }

    private void handleType()
    {
        choosedType.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                chooseButton.performClick();
            }

            final MakeNewBuyAsk this$0;

            
            {
                this$0 = MakeNewBuyAsk.this;
                super();
            }
        }
);
        chooseButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewBuyAsk.this);
                alertDialogBuilder.setTitle(0x7f090193);
                String as[] = new String[5];
                as[0] = getString(0x7f090194);
                as[1] = getString(0x7f090195);
                as[2] = getString(0x7f090196);
                as[3] = getString(0x7f090197);
                as[4] = getString(0x7f090198);
                alertDialogBuilder.setSingleChoiceItems(as, position, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        position = i;
                        if(Log.D)
                            Log.d("temp", (new StringBuilder("position:")).append(position).toString());
                        if(Log.D)
                            Log.d("MakeNewBuyAsk", (new StringBuilder("which:")).append(i).toString());
                        i;
                        JVM INSTR tableswitch 0 4: default 108
                    //                                   0 157
                    //                                   1 208
                    //                                   2 259
                    //                                   3 310
                    //                                   4 361;
                           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
                        choosedType.setText(0x7f090194);
                        type = TYPE_PRODUCT;
                        chooseDialog.dismiss();
_L8:
                        return;
_L2:
                        choosedType.setText(0x7f090194);
                        type = TYPE_PRODUCT;
                        chooseDialog.dismiss();
                        continue; /* Loop/switch isn't completed */
_L3:
                        choosedType.setText(0x7f090195);
                        type = TYPE_STOCK_SEND;
                        chooseDialog.dismiss();
                        continue; /* Loop/switch isn't completed */
_L4:
                        choosedType.setText(0x7f090196);
                        type = TYPE_PAY;
                        chooseDialog.dismiss();
                        continue; /* Loop/switch isn't completed */
_L5:
                        choosedType.setText(0x7f090197);
                        type = TYPE_INVOICE_REPAIRE;
                        chooseDialog.dismiss();
                        continue; /* Loop/switch isn't completed */
_L6:
                        choosedType.setText(0x7f090198);
                        type = TYPE_PROMOTION;
                        chooseDialog.dismiss();
                        if(true) goto _L8; else goto _L7
_L7:
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                post(new Runnable() {

                    public void run()
                    {
                        chooseDialog = alertDialogBuilder.show();
                    }

                    final _cls2 this$1;
                    private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls2.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                }
);
            }

            final MakeNewBuyAsk this$0;


            
            {
                this$0 = MakeNewBuyAsk.this;
                super();
            }
        }
);
    }

    private void initView()
    {
        title = (TextView)findViewById(0x7f0c02c7);
        if(name.length() > 20)
            name = name.substring(0, 20);
        TextView textview = title;
        Object aobj[] = new Object[1];
        aobj[0] = name;
        textview.setText(getString(0x7f0900d0, aobj));
        choosedType = (TextView)findViewById(0x7f0c0151);
        chooseButton = (ImageButton)findViewById(0x7f0c0152);
        position = 0;
        askContent = (EditText)findViewById(0x7f0c0154);
        submitButton = (Button)findViewById(0x7f0c0155);
    }

    private void saveConsultation()
    {
        if(Log.D)
            Log.d("MakeNewBuyAsk", params.toString());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(functionId);
        httpsetting.setJsonParams(params);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
label0:
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    String s;
                    try
                    {
                        s = jsonobjectproxy.getString("saveConsulation");
                        if(Log.D)
                            Log.d("MakeNewBuyAsk", (new StringBuilder("saveConsulation : ")).append(s).toString());
                    }
                    catch(JSONException jsonexception)
                    {
                        break label0;
                    }
                    if("true".equals(s))
                    {
                        if(Log.D)
                            Log.d("MakeNewBuyAsk", "saveConsulation--success");
                        noShowAgain();
                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewBuyAsk.this);
                        alertDialogBuilder.setTitle(0x7f0900b3);
                        alertDialogBuilder.setMessage(0x7f0900b4);
                        alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                alertDialog.dismiss();
                                Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                                Bundle bundle = new Bundle();
                                bundle.putLong("id", Long.valueOf(wareId).longValue());
                                bundle.putString("title", name);
                                intent.putExtras(bundle);
                                startActivityInFrame(intent);
                            }

                            final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                        }
);
                        post(new Runnable() {

                            public void run()
                            {
                                alertDialog = alertDialogBuilder.show();
                            }

                            final _cls4 this$1;
                            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls4.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                        }
);
                    } else
                    {
                        if(Log.D)
                            Log.d("MakeNewBuyAsk", "saveConsulation--failed");
                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewBuyAsk.this);
                        alertDialogBuilder.setMessage(0x7f0900b5);
                        alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                alertDialog.dismiss();
                            }

                            final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                        }
);
                        post(new Runnable() {

                            public void run()
                            {
                                alertDialog = alertDialogBuilder.show();
                            }

                            final _cls4 this$1;
                            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls4.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                        }
);
                    }
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("MakeNewBuyAsk", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("MakeNewBuyAsk", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("MakeNewBuyAsk", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("MakeNewBuyAsk", "saveConsultation()-start");
            }

            AlertDialog alertDialog;
            final MakeNewBuyAsk this$0;


            
            {
                this$0 = MakeNewBuyAsk.this;
                super();
                alertDialog = null;
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030044);
        functionId = "saveConsultation";
        params = new JSONObject();
        Intent intent = getIntent();
        wareId = intent.getExtras().getString("id");
        name = intent.getExtras().getString("name");
        if(Log.D)
            Log.d("MakeNewBuyAsk", (new StringBuilder("wareId=")).append(wareId).toString());
        initView();
        handleType();
        handleSubmit();
    }

    private String TYPE_INVOICE_REPAIRE;
    private String TYPE_PAY;
    private String TYPE_PRODUCT;
    private String TYPE_PROMOTION;
    private String TYPE_STOCK_SEND;
    private EditText askContent;
    private ImageButton chooseButton;
    private AlertDialog chooseDialog;
    private TextView choosedType;
    private String content;
    private String functionId;
    private String name;
    private JSONObject params;
    private int position;
    private Button submitButton;
    private TextView title;
    private String type;
    private String wareId;



















}
