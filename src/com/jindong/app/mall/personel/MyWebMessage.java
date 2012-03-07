// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.personel:
//            MyMessageShow

public class MyWebMessage extends MyActivity
{

    public MyWebMessage()
    {
        sMsgTypeTitle = null;
    }

    private void InitComponenet()
    {
        wareInfoList = (ListView)findViewById(0x7f0c0190);
    }

    private void setAllMessageReaded(ArrayList arraylist)
    {
        JSONObject jsonobject = new JSONObject();
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = arraylist.iterator();
        do
        {
            do
            {
                if(!iterator.hasNext())
                {
                    if(stringbuilder.length() > 0)
                    {
                        stringbuilder.deleteCharAt(stringbuilder.length() - 1);
                        Product product;
                        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
                        try
                        {
                            jsonobject.put("msgIds", stringbuilder.toString());
                        }
                        catch(JSONException jsonexception)
                        {
                            jsonexception.printStackTrace();
                        }
                        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                        httpsetting.setFunctionId("allMessageReaded");
                        httpsetting.setJsonParams(jsonobject);
                        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                            {
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

                            final MyWebMessage this$0;

            
            {
                this$0 = MyWebMessage.this;
                super();
            }
                        }
);
                        getHttpGroupaAsynPool().add(httpsetting);
                    }
                    return;
                }
                product = (Product)iterator.next();
            } while(product.getMsgFlag() == null || !product.getMsgFlag().equals("0"));
            stringbuilder.append(product.getsMsgId()).append(",");
        } while(true);
    }

    private void setMsgTypeTitle(String s)
    {
        if(s.length() <= 0) goto _L2; else goto _L1
_L1:
        if(s.compareToIgnoreCase("1") != 0) goto _L4; else goto _L3
_L3:
        sMsgTypeTitle = getApplicationContext().getString(0x7f090095);
_L2:
        return;
_L4:
        if(s.compareToIgnoreCase("2") == 0)
            sMsgTypeTitle = getApplicationContext().getString(0x7f090096);
        else
        if(s.compareToIgnoreCase("3") == 0)
            sMsgTypeTitle = getApplicationContext().getString(0x7f090097);
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void setMyMessage()
    {
        JSONObject jsonobject = new JSONObject();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                ArrayList arraylist = Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("messageList"), 8);
                if(arraylist != null && arraylist.size() > 0)
                {
                    putStringToPreference("lasteMessageReadeTime", ((Product)arraylist.get(0)).getMsgTime());
                    setAllMessageReaded(arraylist);
                    MyWebMessage mywebmessage = MyWebMessage.this;
                    String as[] = new String[2];
                    as[0] = "msgBody";
                    as[1] = "msgName";
                    int ai[] = new int[2];
                    ai[0] = 0x7f0c016b;
                    ai[1] = 0x7f0c0169;
                    final MySimpleAdapter adapter = new MySimpleAdapter(mywebmessage, arraylist, 0x7f030047, as, ai) {

                        public View getView(int i, View view, ViewGroup viewgroup)
                        {
                            View view1 = super.getView(i, view, viewgroup);
                            TextView textview = (TextView)view1.findViewById(0x7f0c0169);
                            TextView textview1 = (TextView)view1.findViewById(0x7f0c016a);
                            Product product = (Product)getItem(i);
                            setNewMsgFlag(view1, product.getMsgFlag());
                            textview1.setText(product.getMsgTime().substring(0, 10));
                            if(product.getMsgTypeName() == null || TextUtils.isEmpty(product.getMsgTypeName()))
                                setMsgTypeTitle(product.getMsgType());
                            else
                                sMsgTypeTitle = product.getMsgTypeName();
                            textview.setText(sMsgTypeTitle);
                            if(i % 2 == 1)
                                view1.setBackgroundResource(0x7f02006a);
                            else
                                view1.setBackgroundResource(0x7f02006b);
                            return view1;
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super(myactivity, list, i, as, ai);
                    }
                    }
;
                    post(new Runnable() {

                        public void run()
                        {
                            wareInfoList.setAdapter(adapter);
                        }

                        final _cls1 this$1;
                        private final MySimpleAdapter val$adapter;

                    
                    {
                        this$1 = _cls1.this;
                        adapter = mysimpleadapter;
                        super();
                    }
                    }
);
                } else
                {
                    final TextView noData = (TextView)findViewById(0x7f0c0191);
                    post(new Runnable() {

                        public void run()
                        {
                            noData.setVisibility(0);
                            wareInfoList.setVisibility(8);
                        }

                        final _cls1 this$1;
                        private final TextView val$noData;

                    
                    {
                        this$1 = _cls1.this;
                        noData = textview;
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

            final MyWebMessage this$0;


            
            {
                this$0 = MyWebMessage.this;
                super();
            }
        }
;
        httpsetting.setFunctionId("messageList");
        httpsetting.setPost(true);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(onalllistener);
        getHttpGroupaAsynPool().add(httpsetting);
        wareInfoList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Intent intent = new Intent();
                final Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                if(product != null)
                {
                    final View mReadFlag = view.findViewById(0x7f0c0168);
                    post(new Runnable() {

                        public void run()
                        {
                            if(product.getMsgFlag() != null && product.getMsgFlag().equals("0"))
                            {
                                mReadFlag.setVisibility(8);
                                product.setMessageFlag("1");
                            }
                        }

                        final _cls2 this$1;
                        private final View val$mReadFlag;
                        private final Product val$product;

                    
                    {
                        this$1 = _cls2.this;
                        product = product1;
                        mReadFlag = view;
                        super();
                    }
                    }
);
                    intent.putExtra("product", product);
                    intent.setClass(getApplicationContext(), com/jindong/app/mall/personel/MyMessageShow);
                    startActivityInFrame(intent);
                }
            }

            final MyWebMessage this$0;

            
            {
                this$0 = MyWebMessage.this;
                super();
            }
        }
);
    }

    private void setNewMsgFlag(View view, String s)
    {
        View view1 = view.findViewById(0x7f0c0168);
        TextView textview = (TextView)view.findViewById(0x7f0c0169);
        TextView textview1 = (TextView)view.findViewById(0x7f0c016a);
        if(s.length() > 0 && s.compareToIgnoreCase("0") == 0)
        {
            view1.setVisibility(0);
            textview.setTextAppearance(getBaseContext(), 0x1010099);
            textview1.setTextAppearance(getBaseContext(), 0x1010099);
        } else
        {
            view1.setVisibility(4);
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030050);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f09008b);
        Intent intent = getIntent();
        params = new JSONObject();
        try
        {
            params.put("pin", intent.getStringExtra("pin"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        InitComponenet();
    }

    public void onStart()
    {
        super.onStart();
        setMyMessage();
    }

    TextView mTitle;
    private JSONObject params;
    private String sMsgTypeTitle;
    private ListView wareInfoList;






}
