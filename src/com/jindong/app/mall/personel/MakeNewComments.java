// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.personel:
//            MyCommentDiscussActivity

public class MakeNewComments extends MyActivity
{

    public MakeNewComments()
    {
    }

    private boolean checkComment()
    {
        if(!TextUtils.isEmpty(commentTitle.getText().toString().trim())) goto _L2; else goto _L1
_L1:
        boolean flag;
        Toast.makeText(this, 0x7f0901a4, 0).show();
        flag = false;
_L13:
        return flag;
_L2:
        if(commentTitle.getText().toString().trim().length() < 4 || commentTitle.getText().toString().trim().length() > 20)
        {
            Toast.makeText(this, 0x7f0901a6, 0).show();
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if(product.isBook().booleanValue())
        {
            if(TextUtils.isEmpty(commentContent.getText().toString().trim()))
            {
                Toast.makeText(getBaseContext(), 0x7f0901a8, 0).show();
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(commentContent.getText().toString().trim().length() < 10 || commentContent.getText().toString().trim().length() > 2000)
            {
                Toast.makeText(getBaseContext(), 0x7f0901aa, 0).show();
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        } else
        {
            if(TextUtils.isEmpty(commentContent.getText().toString().trim()))
            {
                Toast.makeText(getBaseContext(), 0x7f0901a8, 0).show();
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            if(commentContent.getText().toString().trim().length() < 5 || commentContent.getText().toString().trim().length() > 200)
            {
                Toast.makeText(getBaseContext(), 0x7f0901a9, 0).show();
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
        }
        if(product.isBook().booleanValue()) goto _L4; else goto _L3
_L3:
        if(!TextUtils.isEmpty(commentGood.getText().toString().trim())) goto _L6; else goto _L5
_L5:
        commentGood.setText(0x7f0901a2);
_L10:
        if(!TextUtils.isEmpty(commentBad.getText().toString().trim())) goto _L8; else goto _L7
_L7:
        commentBad.setText(0x7f0901a3);
_L4:
        flag = true;
        continue; /* Loop/switch isn't completed */
_L6:
        if(commentGood.getText().toString().trim().length() >= 5 && commentGood.getText().toString().trim().length() <= 100) goto _L10; else goto _L9
_L9:
        Toast.makeText(getBaseContext(), 0x7f0901ab, 0).show();
        flag = false;
        continue; /* Loop/switch isn't completed */
_L8:
        if(commentBad.getText().toString().trim().length() >= 5 && commentBad.getText().toString().trim().length() <= 100) goto _L4; else goto _L11
_L11:
        Toast.makeText(getBaseContext(), 0x7f0901ac, 0).show();
        flag = false;
        if(true) goto _L13; else goto _L12
_L12:
    }

    private void handleClickEvent()
    {
        mSubmit.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                try
                {
                    if(checkComment())
                    {
                        params.put("wareId", String.valueOf(product.getId()));
                        if(Log.D)
                            Log.d("MakeNewComments", (new StringBuilder("wareId=")).append(product.getId()).toString());
                        params.put("title", commentTitle.getText().toString().trim());
                        int i;
                        if(commentScore.getProgress() % 2 == 0)
                            i = commentScore.getProgress() / 2;
                        else
                            i = 1 + commentScore.getProgress() / 2;
                        params.put("score", (new StringBuilder()).append(i).toString());
                        if(Log.D)
                            Log.d("MakeNewComments", (new StringBuilder("commentScore=")).append(i).toString());
                        if(product.isBook().booleanValue())
                        {
                            params.put("prop", getString(0x7f0901a2));
                            params.put("cons", getString(0x7f0901a3));
                        } else
                        {
                            params.put("prop", commentGood.getText().toString().trim());
                            params.put("cons", commentBad.getText().toString().trim());
                        }
                        params.put("content", commentContent.getText().toString().trim());
                        params.put("type", "Product");
                        if(Log.D)
                            Log.d("MakeNewComments", (new StringBuilder("params:")).append(params.toString()).toString());
                        submitComment();
                    }
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
            }

            final MakeNewComments this$0;

            
            {
                this$0 = MakeNewComments.this;
                super();
            }
        }
);
    }

    private void initComponent()
    {
        TextView textview = (TextView)findViewById(0x7f0c02c7);
        product = (Product)getIntent().getSerializableExtra("product");
        name = product.getName();
        if(name.length() > 20)
            name = (new StringBuilder(String.valueOf(name.substring(0, 20)))).append("...").toString();
        if(Log.D)
            Log.d("MakeNewComments", (new StringBuilder("isbook:")).append(product.isBook()).toString());
        Object aobj[] = new Object[1];
        aobj[0] = name;
        textview.setText(getString(0x7f0900cf, aobj));
        mSubmit = (Button)findViewById(0x7f0c0160);
        commentTitle = (EditText)findViewById(0x7f0c0157);
        commentScore = (RatingBar)findViewById(0x7f0c0159);
        commentGoodText = (TextView)findViewById(0x7f0c015a);
        commentGood = (EditText)findViewById(0x7f0c015b);
        commentBadText = (TextView)findViewById(0x7f0c015c);
        commentBad = (EditText)findViewById(0x7f0c015d);
        commentcontentText = (TextView)findViewById(0x7f0c015e);
        commentContent = (EditText)findViewById(0x7f0c015f);
        if(product.isBook().booleanValue())
        {
            commentGoodText.setVisibility(8);
            commentBadText.setVisibility(8);
            commentGood.setVisibility(8);
            commentBad.setVisibility(8);
            commentcontentText.setText(0x7f090111);
        }
    }

    private void submitComment()
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(functionId);
        httpsetting.setJsonParams(params);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("MakeNewComments", "saveComment()-onEnd");
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                Boolean boolean1 = jsonobjectproxy.getBooleanOrNull("flag");
                String s = jsonobjectproxy.getStringOrNull("saveComment");
                if(boolean1 != null && boolean1.booleanValue())
                {
                    if(Log.D)
                        Log.d("MakeNewComments", "submitComment--success");
                    noShowAgain();
                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewComments.this);
                    alertDialogBuilder.setTitle(0x7f0900b3);
                    if(s != null)
                        alertDialogBuilder.setMessage(s);
                    alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            alertDialog.dismiss();
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/personel/MyCommentDiscussActivity);
                            startActivityInFrame(intent);
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
                            alertDialog = alertDialogBuilder.show();
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
                } else
                {
                    if(Log.D)
                        Log.d("MakeNewComments", "submitComment--failed");
                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewComments.this);
                    alertDialogBuilder.setTitle(0x7f0900b5);
                    if(s != null)
                        alertDialogBuilder.setMessage(s);
                    alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            alertDialog.dismiss();
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
                            alertDialog = alertDialogBuilder.show();
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
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("MakeNewComments", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("MakeNewComments", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("MakeNewComments", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("MakeNewComments", "saveComment()-start");
            }

            AlertDialog alertDialog;
            final MakeNewComments this$0;


            
            {
                this$0 = MakeNewComments.this;
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
        setContentView(0x7f030045);
        functionId = "saveComment";
        params = new JSONObject();
        initComponent();
        handleClickEvent();
    }

    private EditText commentBad;
    private TextView commentBadText;
    private EditText commentContent;
    private EditText commentGood;
    private TextView commentGoodText;
    private RatingBar commentScore;
    private EditText commentTitle;
    private TextView commentcontentText;
    private String functionId;
    private Button mSubmit;
    private String name;
    private JSONObject params;
    private Product product;
    private boolean result;









}
