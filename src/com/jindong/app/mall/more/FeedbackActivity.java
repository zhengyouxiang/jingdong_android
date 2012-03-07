// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.more;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.utils.*;

public class FeedbackActivity extends MyActivity
{

    public FeedbackActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030054);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090037);
        mSubmitBtn = (Button)findViewById(0x7f0c01ab);
        mFeedbackContent = (EditText)findViewById(0x7f0c01a8);
        mFeedbackContact = (EditText)findViewById(0x7f0c01aa);
        mLinearlayoutFeedbackType = (LinearLayout)findViewById(0x7f0c01a4);
        choosedType = (TextView)findViewById(0x7f0c01a5);
        final String items[] = getResources().getStringArray(0x7f0a0005);
        choosedType.setText(items[0]);
        choosedType.setTag(new Integer(0));
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(FeedbackActivity.this);
                choosedType.setError(null);
                builder.setTitle(0x7f0900fa);
                Integer integer = (Integer)choosedType.getTag();
                builder.setSingleChoiceItems(items, integer.intValue(), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        if(Log.D)
                            Log.d("MakeNewBuyAsk", (new StringBuilder("which:")).append(i).toString());
                        choosedType.setTag(Integer.valueOf(i));
                        choosedType.setText(items[i]);
                        dialoginterface.dismiss();
                    }

                    final _cls1 this$1;
                    private final String val$items[];

                    
                    {
                        this$1 = _cls1.this;
                        items = as;
                        super();
                    }
                }
);
                builder.show();
            }

            final FeedbackActivity this$0;
            private final String val$items[];


            
            {
                this$0 = FeedbackActivity.this;
                items = as;
                super();
            }
        }
;
        mLinearlayoutFeedbackType.setOnClickListener(onclicklistener);
        ((ImageButton)findViewById(0x7f0c01a6)).setOnClickListener(onclicklistener);
        mSubmitBtn.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(TextUtils.isEmpty(choosedType.getText()))
                    choosedType.setError(getString(0x7f0900fe));
                else
                if(TextUtils.isEmpty(mFeedbackContent.getText().toString().trim()))
                {
                    mFeedbackContent.setError(getString(0x7f0900fd));
                } else
                {
                    com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                    httpsetting.putJsonParam("contact", mFeedbackContact.getText());
                    httpsetting.putJsonParam("type", choosedType.getText());
                    httpsetting.putJsonParam("partner", Configuration.getProperty("partner", ""));
                    httpsetting.putJsonParam("content", mFeedbackContent.getText());
                    httpsetting.setFunctionId("feedBack");
                    httpsetting.setPost(true);
                    httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                        public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                        {
                            if(Log.I)
                                Log.i("+++++++++++", "feedback onend");
                            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(_fld0);
                            alertDialogBuilder.setMessage(0x7f0900b3);
                            alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialoginterface, int i)
                                {
                                    dialoginterface.dismiss();
                                    finish();
                                }

                                final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super();
                        }
                            }
);
                            post(new Runnable() {

                                public void run()
                                {
                                    alertDialogBuilder.show();
                                }

                                final _cls1 this$2;
                                private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                        
                        {
                            this$2 = _cls1.this;
                            alertDialogBuilder = builder;
                            super();
                        }
                            }
);
                        }

                        public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                        {
                            if(Log.I)
                                Log.i("+++++++++++", "feedback on error");
                            final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(_fld0);
                            alertDialogBuilder.setMessage(0x7f0900ff);
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
                            post(new Runnable() {

                                public void run()
                                {
                                    alertDialogBuilder.show();
                                }

                                final _cls1 this$2;
                                private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                        
                        {
                            this$2 = _cls1.this;
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

                        final _cls2 this$1;


                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                    }
);
                    getHttpGroupaAsynPool().add(httpsetting);
                }
            }

            final FeedbackActivity this$0;


            
            {
                this$0 = FeedbackActivity.this;
                super();
            }
        }
);
    }

    TextView choosedType;
    EditText mFeedbackContact;
    EditText mFeedbackContent;
    EditText mFeedbackName;
    LinearLayout mLinearlayoutFeedbackType;
    Button mSubmitBtn;
}
