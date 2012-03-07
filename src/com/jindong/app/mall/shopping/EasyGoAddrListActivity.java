// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.DefaultEasyTempOrderInfo;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.login.LoginActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.shopping:
//            FillOrderActivity

public class EasyGoAddrListActivity extends MyActivity
{

    public EasyGoAddrListActivity()
    {
        TAG = "EasyGoAddrListActivity";
    }

    private void getEasyOrderTemplate()
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        setUpConnAndGetData("getOrderTemplate", jsonobject, "getOrderTemplate");
    }

    private void getScreenHW()
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
        if(Log.D)
            Log.d("Height", String.valueOf(screenHeight));
    }

    private void handleClickEvent()
    {
    }

    private void handleDatas(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse, String s)
    {
        try
        {
            if(s.compareTo("getOrderTemplate") == 0)
            {
                jsonOrderInfoContainer = httpresponse.getJSONObject();
                if(jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate") != null && (jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").get("Orders") == null || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").get("Orders").toString() == "null" || jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").get("Orders").toString().compareTo("[]") == 0))
                    setEmptyView();
                else
                if(jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate") != null && jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").get("Orders") != null && jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").get("Orders").toString() != "null")
                {
                    jsonTempList = jsonOrderInfoContainer.getJSONObjectOrNull("orderTemplate").getJSONArrayOrNull("Orders");
                    setEasyBuy_list();
                }
                break MISSING_BLOCK_LABEL_498;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            break MISSING_BLOCK_LABEL_498;
        }
        if(s.compareTo("easyBuySetDefaultTemp") == 0)
        {
            if(httpresponse.getJSONObject().toString().contains("defaultTemplate") && httpresponse.getJSONObject().get("defaultTemplate") != null && httpresponse.getJSONObject().get("defaultTemplate").toString() != "null")
            {
                showMsg(httpresponse.getJSONObject().getString("defaultTemplate"));
                getEasyOrderTemplate();
            } else
            {
                showMsg("\u8BBE\u7F6E\u8F7B\u677E\u8D2D\u4E0D\u6210\u529F");
            }
        } else
        if(s.compareTo("delOrderTemp") == 0)
        {
            JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
            if(jsonobjectproxy.toString().contains("delOrderTemplate"))
                if(jsonobjectproxy.getJSONObject("delOrderTemplate") != null && jsonobjectproxy.getJSONObject("delOrderTemplate").getBoolean("Flag"))
                {
                    if(jsonobjectproxy.getJSONObject("delOrderTemplate").get("Message") != null && jsonobjectproxy.getJSONObject("delOrderTemplate").get("Message").toString() != "null")
                    {
                        showMsg(jsonobjectproxy.getJSONObject("delOrderTemplate").get("Message").toString());
                        getEasyOrderTemplate();
                    } else
                    {
                        showMsg("\u60A8\u6210\u529F\u5220\u9664\u4E86\u8F7B\u677E\u8D2D");
                        getEasyOrderTemplate();
                    }
                } else
                if(jsonobjectproxy.getJSONObject("delOrderTemplate") != null && !jsonobjectproxy.getJSONObject("delOrderTemplate").getBoolean("Flag"))
                {
                    if(jsonobjectproxy.getJSONObject("delOrderTemplate").get("Message") != null && jsonobjectproxy.getJSONObject("delOrderTemplate").get("Message").toString() != "null")
                        showMsg(jsonobjectproxy.getJSONObject("delOrderTemplate").get("Message").toString());
                    else
                        showMsg("\u60A8\u6CA1\u6709\u6210\u529F\u5220\u9664\u8F7B\u677E\u8D2D");
                } else
                {
                    showMsg("\u60A8\u6CA1\u6709\u6210\u529F\u5220\u9664\u8F7B\u677E\u8D2D");
                }
        } else
        {
            showMsg("\u60A8\u6CA1\u6709\u6210\u529F\u5220\u9664\u8F7B\u677E\u8D2D");
        }
    }

    private void initComponent()
    {
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f090182);
    }

    private void setDefaultTemp(String s)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("Id", s);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        setUpConnAndGetData("easyBuySetDefaultTemp", jsonobject, "easyBuySetDefaultTemp");
    }

    private void setEasyBuy_list()
    {
        post(new Runnable() {

            public void run()
            {
                ListView listview;
                LayoutInflater layoutinflater = LayoutInflater.from(EasyGoAddrListActivity.this);
                LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0c00c1);
                LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0c00c2);
                LinearLayout linearlayout2 = (LinearLayout)layoutinflater.inflate(0x7f030025, null).findViewById(0x7f0c00c6);
                listview = (ListView)linearlayout2.getChildAt(0);
                java.util.ArrayList arraylist = Product.toList(EasyGoAddrListActivity.jsonTempList, 12);
                EasyGoAddrListActivity easygoaddrlistactivity = EasyGoAddrListActivity.this;
                String as[] = new String[1];
                as[0] = "name";
                int ai[] = new int[1];
                ai[0] = 0x7f0c009a;
                listview.setAdapter(new MySimpleAdapter(easygoaddrlistactivity, arraylist, 0x7f030024, as, ai) {

                    public View getView(final int index, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(index, view, viewgroup);
                        RadioButton radiobutton;
                        if(index % 2 == 1)
                            view1.setBackgroundResource(0x7f0200a2);
                        else
                            view1.setBackgroundResource(0x7f0200a3);
                        radiobutton = (RadioButton)view1.findViewById(0x7f0c00c4);
                        try
                        {
                            if(EasyGoAddrListActivity.jsonTempList.getJSONObject(index).getInt("IsDefault") == 1)
                            {
                                radiobutton.setChecked(true);
                                Contants.oldBtn = radiobutton;
                            }
                        }
                        catch(JSONException jsonexception)
                        {
                            jsonexception.printStackTrace();
                        }
                        radiobutton.setOnClickListener(new android.view.View.OnClickListener() {

                            public void onClick(View view2)
                            {
                                setDefaultTemp(EasyGoAddrListActivity.jsonTempList.getJSONObject(index).get("Id").toString());
_L1:
                                return;
                                JSONException jsonexception1;
                                jsonexception1;
                                jsonexception1.printStackTrace();
                                  goto _L1
                            }

                            final _cls1 this$2;
                            private final int val$index;

                        
                        {
                            this$2 = _cls1.this;
                            index = i;
                            super();
                        }
                        }
);
                        return view1;
                    }

                    final _cls2 this$1;


                    
                    {
                        this$1 = _cls2.this;
                        super(myactivity, list, i, as, ai);
                    }
                }
);
                listview.setClickable(true);
                RelativeLayout relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f030026, null).findViewById(0x7f0c00c8);
                mAdd = (Button)relativelayout.getChildAt(1);
                mAdd.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        if(!LoginUser.hasLogin())
                        {
                            Toast.makeText(_fld0, 0x7f090181, 0).show();
                            Intent intent1 = new Intent(_fld0, com/jindong/app/mall/login/LoginActivity);
                            startActivityInFrame(intent1);
                        } else
                        if(EasyGoAddrListActivity.jsonTempList != null && EasyGoAddrListActivity.jsonTempList.length() >= 20)
                        {
                            Contants.ShowMsg("\u60A8\u6DFB\u52A0\u7684\u8F7B\u677E\u8D2D\u8BBE\u7F6E\u8FBE\u5230\u4E0A\u9650\uFF0C\u8BF7\u5220\u9664\u4E4B\u540E\u518D\u6DFB\u52A0", _fld0);
                        } else
                        {
                            Contants.bAddEasyBuy = true;
                            Contants.bEasyBuy = false;
                            Contants.bModifyEasyBuy = false;
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/FillOrderActivity);
                            startTaskActivityInFrame(intent);
                        }
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                linearlayout.removeAllViews();
                linearlayout1.removeAllViews();
                linearlayout1.setVisibility(0);
                mAdd.setVisibility(0);
                linearlayout.addView(linearlayout2);
                linearlayout1.addView(relativelayout);
                if(screenHeight >= 1024 || screenHeight <= 480 || screenWidth <= 320 || screenWidth >= 600) goto _L2; else goto _L1
_L1:
                if(listview.getCount() < 16)
                    listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 100 * listview.getCount()));
                else
                    listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 102 * listview.getCount()));
_L4:
                if(screenHeight < 1024 && screenHeight > 480 && screenWidth >= 640)
                    listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 131 * listview.getCount()));
                listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        DefaultEasyTempOrderInfo.jsonTemp = new JSONObjectProxy();
                        DefaultEasyTempOrderInfo.jsonTemp = EasyGoAddrListActivity.jsonTempList.getJSONObject(i);
                        DefaultEasyTempOrderInfo.sTempName = EasyGoAddrListActivity.jsonTempList.getJSONObject(i).getString("Name");
                        DefaultEasyTempOrderInfo.sTempId = Long.valueOf(EasyGoAddrListActivity.jsonTempList.getJSONObject(i).get("Id").toString());
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/FillOrderActivity);
                        Contants.bModifyEasyBuy = true;
                        Contants.bEasyBuy = false;
                        Contants.bAddEasyBuy = false;
                        startTaskActivityInFrame(intent);
_L1:
                        return;
                        NumberFormatException numberformatexception;
                        numberformatexception;
                        numberformatexception.printStackTrace();
                          goto _L1
                        JSONException jsonexception;
                        jsonexception;
                        jsonexception.printStackTrace();
                          goto _L1
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                listview.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

                    public boolean onItemLongClick(AdapterView adapterview, View view, final int index, long l)
                    {
                        final String items[] = new String[3];
                        items[0] = getString(0x7f09017d);
                        items[1] = getString(0x7f09017e);
                        items[2] = getString(0x7f09017f);
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(_fld0);
                        builder.setTitle(0x7f090180);
                        builder.setItems(items, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                if(Log.D)
                                    Log.d(TAG, (new StringBuilder("which:")).append(i).toString());
                                int j = items[i].compareTo(getString(0x7f09017d));
                                if(j != 0) goto _L2; else goto _L1
_L1:
                                DefaultEasyTempOrderInfo.jsonTemp = new JSONObjectProxy();
                                DefaultEasyTempOrderInfo.jsonTemp = EasyGoAddrListActivity.jsonTempList.getJSONObject(index);
                                DefaultEasyTempOrderInfo.sTempName = EasyGoAddrListActivity.jsonTempList.getJSONObject(index).getString("Name");
                                DefaultEasyTempOrderInfo.sTempId = Long.valueOf(EasyGoAddrListActivity.jsonTempList.getJSONObject(index).get("Id").toString());
                                Intent intent = new Intent(_fld0, com/jindong/app/mall/shopping/FillOrderActivity);
                                Contants.bModifyEasyBuy = true;
                                Contants.bEasyBuy = false;
                                Contants.bAddEasyBuy = false;
                                startTaskActivityInFrame(intent);
_L3:
                                dialoginterface.dismiss();
                                return;
                                NumberFormatException numberformatexception;
                                numberformatexception;
                                try
                                {
                                    numberformatexception.printStackTrace();
                                }
                                catch(Exception exception)
                                {
                                    if(Log.D)
                                        Log.d(TAG, exception.getMessage());
                                }
                                  goto _L3
                                JSONException jsonexception2;
                                jsonexception2;
                                jsonexception2.printStackTrace();
                                  goto _L3
_L2:
                                int k = items[i].compareTo(getString(0x7f09017f));
                                if(k != 0)
                                    break MISSING_BLOCK_LABEL_314;
                                setDefaultTemp(EasyGoAddrListActivity.jsonTempList.getJSONObject(index).get("Id").toString());
                                  goto _L3
                                JSONException jsonexception1;
                                jsonexception1;
                                jsonexception1.printStackTrace();
                                  goto _L3
                                int i1 = items[i].compareTo(getString(0x7f09017e));
                                if(i1 != 0) goto _L3; else goto _L4
_L4:
                                if(EasyGoAddrListActivity.jsonTempList.getJSONObject(index).getInt("IsDefault") != 1)
                                    break MISSING_BLOCK_LABEL_392;
                                showMsg("\u4E0D\u80FD\u5220\u9664\u9ED8\u8BA4\u8F7B\u677E\u8D2D\u8BBE\u7F6E\uFF0C\u60A8\u53EF\u4EE5\u5148\u8BBE\u7F6E\u5176\u4ED6\u8F7B\u677E\u8D2D\u4E3A\u9ED8\u8BA4\u8F7B\u677E\u8D2D\u8BBE\u7F6E\uFF0C\u7136\u540E\u518D\u5220\u9664");
                                  goto _L3
                                JSONException jsonexception;
                                jsonexception;
                                jsonexception.printStackTrace();
                                  goto _L3
                                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(_fld0)).create();
                                alertdialog.setMessage(getText(0x7f090179));
                                alertdialog.setTitle(0x7f09017a);
                                alertdialog.setButton(getText(0x7f09007a), new android.content.DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialoginterface1, int j1)
                                    {
                                        try
                                        {
                                            delTemp(EasyGoAddrListActivity.jsonTempList.getJSONObject(index).getInt("Id"));
                                        }
                                        catch(JSONException jsonexception3)
                                        {
                                            jsonexception3.printStackTrace();
                                        }
                                        dialoginterface1.dismiss();
                                    }

                                    final _cls1 this$3;
                                    private final int val$index;

                            
                            {
                                this$3 = _cls1.this;
                                index = i;
                                super();
                            }
                                }
);
                                alertdialog.setButton2(getText(0x7f09000f), new android.content.DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialoginterface1, int j1)
                                    {
                                        dialoginterface1.dismiss();
                                    }

                                    final _cls1 this$3;

                            
                            {
                                this$3 = _cls1.this;
                                super();
                            }
                                }
);
                                alertdialog.show();
                                  goto _L3
                            }

                            final _cls4 this$2;
                            private final int val$index;
                            private final String val$items[];


                        
                        {
                            this$2 = _cls4.this;
                            items = as;
                            index = i;
                            super();
                        }
                        }
).show();
                        return false;
                    }

                    final _cls2 this$1;


                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                return;
_L2:
                if(screenHeight >= 1024 && screenWidth >= 600)
                {
                    if(listview.getCount() < 16)
                        listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 110 * listview.getCount()));
                    else
                        listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 112 * listview.getCount()));
                } else
                {
                    boolean flag;
                    boolean flag1;
                    if(screenHeight == 480)
                        flag = true;
                    else
                        flag = false;
                    if(screenWidth == 320)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(flag & flag1)
                    {
                        listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 64 * listview.getCount()));
                    } else
                    {
                        boolean flag2;
                        boolean flag3;
                        if(screenHeight < 480)
                            flag2 = true;
                        else
                            flag2 = false;
                        if(screenWidth < 320)
                            flag3 = true;
                        else
                            flag3 = false;
                        if(flag2 & flag3)
                            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 50 * listview.getCount()));
                    }
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final EasyGoAddrListActivity this$0;


            
            {
                this$0 = EasyGoAddrListActivity.this;
                super();
            }
        }
);
    }

    private void setEmptyView()
    {
        post(new Runnable() {

            public void run()
            {
                LayoutInflater layoutinflater = LayoutInflater.from(EasyGoAddrListActivity.this);
                LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0c00c1);
                LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0c00c2);
                RelativeLayout relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f030027, null).findViewById(0x7f0c00cb);
                ((Button)relativelayout.getChildAt(1)).setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        if(LoginUser.hasLogin())
                        {
                            Contants.bEasyBuy = false;
                            Contants.bAddEasyBuy = true;
                            Intent intent1 = new Intent(_fld0, com/jindong/app/mall/shopping/FillOrderActivity);
                            startTaskActivityInFrame(intent1);
                        } else
                        {
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/login/LoginActivity);
                            startSingleActivityInFrame(intent);
                        }
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                linearlayout.removeAllViews();
                linearlayout1.removeAllViews();
                linearlayout1.setVisibility(4);
                linearlayout.addView(relativelayout);
            }

            final EasyGoAddrListActivity this$0;


            
            {
                this$0 = EasyGoAddrListActivity.this;
                super();
            }
        }
);
    }

    private void setUpConnAndGetData(String s, JSONObject jsonobject, final String action)
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("funcID")).append(s).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("param")).append(jsonobject.toString()).toString());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(s);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                handleDatas(httpresponse, action);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d(TAG, (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d(TAG, "setUpConnAndGetData()-start");
            }

            final EasyGoAddrListActivity this$0;
            private final String val$action;

            
            {
                this$0 = EasyGoAddrListActivity.this;
                action = s;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void showMsg(final String msg)
    {
        post(new Runnable() {

            public void run()
            {
                Toast.makeText(EasyGoAddrListActivity.this, msg, 0).show();
            }

            final EasyGoAddrListActivity this$0;
            private final String val$msg;

            
            {
                this$0 = EasyGoAddrListActivity.this;
                msg = s;
                super();
            }
        }
);
    }

    public void delTemp(int i)
    {
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
            jsonobject.put("Id", i);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        setUpConnAndGetData("delOrderTemp", jsonobject, "delOrderTemp");
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030023);
        getScreenHW();
        initComponent();
        handleClickEvent();
    }

    public void onResume()
    {
        super.onResume();
        if(!LoginUser.hasLogin())
        {
            jsonTempList = new JSONArrayPoxy();
            setEmptyView();
        } else
        {
            jsonTempList = new JSONArrayPoxy();
            getEasyOrderTemplate();
        }
    }

    private static JSONArrayPoxy jsonTempList;
    private String TAG;
    private JSONObjectProxy jsonOrderInfoContainer;
    Button mAdd;
    TextView mTitle;
    int screenHeight;
    int screenWidth;





}
