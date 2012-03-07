// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.personel:
//            CheckMyOrderDetail

public class MyMessageShow extends MyActivity
{

    public MyMessageShow()
    {
    }

    private void getProductDateal()
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new HttpSetting();
        httpsetting.setFunctionId("messageDetail");
        httpsetting.setJsonParams(params);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final JSONObjectProxy jsonMsg = httpresponse.getJSONObject().getJSONObjectOrNull("messageDetail");
                final JSONObjectProxy jsonProduct = httpresponse.getJSONObject().getJSONObjectOrNull("wareInfo");
                post(new Runnable() {

                    public void run()
                    {
                        if(msgType == 3)
                            break MISSING_BLOCK_LABEL_411;
                        if(jsonProduct == null)
                            break MISSING_BLOCK_LABEL_325;
                        setLeftProductImage(jsonProduct.getString("imageurl"));
                        product.setAdWord(jsonProduct.getString("adword"));
                        product.setName(jsonProduct.getString("wname"));
                        product.setId(Long.valueOf(jsonProduct.getLong("wareId")));
                        String s = (new StringBuilder(String.valueOf(product.getName()))).append("<font color='red'>").append(product.getAdWord()).append("</font>").toString();
                        productTextView.setText(Html.fromHtml(s));
_L1:
                        contentBoby.setVisibility(0);
                        if(msgType == 1)
                        {
                            mQuestionUser.setText(LoginUser.getLoginUserName());
                            mQuestionCreatTime.setText(jsonMsg.getString("createTime"));
                            mQuestionBody.setText(jsonMsg.getString("msgBody"));
                            mAnswerTime.setText(jsonMsg.getString("updateTime"));
                            mAnswerBody.setText(jsonMsg.getString("msgCbody"));
                            break MISSING_BLOCK_LABEL_411;
                        }
                        break MISSING_BLOCK_LABEL_345;
                        JSONException jsonexception;
                        jsonexception;
                        jsonexception.printStackTrace();
                        break MISSING_BLOCK_LABEL_411;
                        findViewById(0x7f0c01a0).setVisibility(8);
                          goto _L1
                        mQuestionUser.setText(jsonMsg.getString("msgName"));
                        mQuestionCreatTime.setText(jsonMsg.getString("createTime"));
                        mAnswerBody.setText(jsonMsg.getString("msgBody"));
                    }

                    final _cls3 this$1;
                    private final JSONObject val$jsonMsg;
                    private final JSONObject val$jsonProduct;

                    
                    {
                        this$1 = _cls3.this;
                        jsonProduct = jsonobject;
                        jsonMsg = jsonobject1;
                        Object();
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

            final MyMessageShow this$0;


            
            {
                this$0 = MyMessageShow.this;
                Object();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void initViewContentTypeByConsulting()
    {
        contentBoby = (ScrollView)findViewById(0x7f0c0197);
        contentBoby.setVisibility(8);
        mQuestionUser = (TextView)findViewById(0x7f0c0199);
        mQuestionCreatTime = (TextView)findViewById(0x7f0c019a);
        mQuestionBody = (TextView)findViewById(0x7f0c019b);
        answerBody = (LinearLayout)findViewById(0x7f0c019d);
        if(msgType == 1)
        {
            mAnswerTime = (TextView)findViewById(0x7f0c019e);
            mAnswerBody = (TextView)findViewById(0x7f0c019f);
        } else
        {
            mAnswerBody = (TextView)findViewById(0x7f0c019f);
            mQuestionBody.setVisibility(8);
            answerBody.setVisibility(8);
        }
        productTextView = (TextView)findViewById(0x7f0c0196);
        productTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view.setPressed(false);
                intent = new Intent();
                intent.setClass(getBaseContext(), com/jindong/app/mall/product/ProductDetailActivity);
                intent.putExtra("id", product.getId());
                startActivityInFrame(intent);
            }

            final MyMessageShow this$0;

            
            {
                this$0 = MyMessageShow.this;
                Object();
            }
        }
);
    }

    private void initViewContentTypeBySelf()
    {
        mOrderNumber = (TextView)findViewById(0x7f0c0192);
        mOrderCreatTime = (TextView)findViewById(0x7f0c0193);
        mTxViewBody = (TextView)findViewById(0x7f0c016b);
        mTxQuestion = (TextView)findViewById(0x7f0c0195);
        productTextView = (TextView)findViewById(0x7f0c0196);
        mOrderNumber.setText((new StringBuilder(String.valueOf(getString(0x7f09008f)))).append(product.getOrderId()).toString());
        mOrderCreatTime.setText(product.getMsgTime());
        mTxViewBody.setText(product.getMsgBody());
        productTextView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                intent = new Intent();
                view.setPressed(false);
                if("3".equals(product.getMsgType()))
                {
                    intent.setClass(getBaseContext(), com/jindong/app/mall/personel/CheckMyOrderDetail);
                    intent.putExtra("product", product);
                } else
                {
                    intent.setClass(getBaseContext(), com/jindong/app/mall/product/ProductDetailActivity);
                    intent.putExtra("id", product.getId());
                }
                startActivityInFrame(intent);
            }

            final MyMessageShow this$0;

            
            {
                this$0 = MyMessageShow.this;
                Object();
            }
        }
);
    }

    private void setLeftProductImage(String s)
    {
        NoImageUtils.initImageView(this, imageHttpGroup, (ImageView)findViewById(0x7f0c01a1), s, true);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        intent = getIntent();
        product = (Product)intent.getSerializableExtra("product");
        if(product == null)
            try
            {
                product = new Product(new JSONObjectProxy(new JSONObject(getStringFromPreference("message"))), 8);
            }
            catch(JSONException jsonexception1)
            {
                throw new RuntimeException(jsonexception1);
            }
        msgType = Integer.valueOf(product.getMsgType()).intValue();
        try
        {
            params = new JSONObject();
            params.put("pin", LoginUser.getLoginUserName());
            params.put("msgId", product.getsMsgId());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        imageHttpGroup = getHttpGroupaAsynPool(5000);
        getProductDateal();
        if(msgType != 3)
        {
            setContentView(0x7f030052);
            initViewContentTypeByConsulting();
        } else
        {
            setContentView(0x7f030051);
            initViewContentTypeBySelf();
        }
        mTitleView = (TextView)findViewById(0x7f0c02c7);
        mTitleView.setText(product.getMsgTypeName());
    }

    LinearLayout answerBody;
    ScrollView contentBoby;
    HttpGroup imageHttpGroup;
    Intent intent;
    TextView mAnswerBody;
    TextView mAnswerTime;
    TextView mOrderCreatTime;
    TextView mOrderNumber;
    TextView mQuestionBody;
    TextView mQuestionCreatTime;
    TextView mQuestionUser;
    TextView mTitleView;
    TextView mTxQuestion;
    TextView mTxViewBody;
    int msgType;
    private JSONObject params;
    Product product;
    TextView productTextView;

}
