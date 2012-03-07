// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.BuyAsk;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.personel.MakeNewBuyAsk;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BuyAskListActivity extends MyActivity
{

    public BuyAskListActivity()
    {
        functionId = "consultation";
    }

    private void handleButtonClick()
    {
        titleButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(Log.D)
                    Log.d("temp", "BuyAsk  Button is pressed");
                if(LoginUser.checkUserLogin(BuyAskListActivity.this))
                {
                    Intent intent = new Intent(BuyAskListActivity.this, com/jindong/app/mall/personel/MakeNewBuyAsk);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", wareId);
                    bundle.putString("name", name);
                    intent.putExtras(bundle);
                    startActivityInFrame(intent);
                }
            }

            final BuyAskListActivity this$0;

            
            {
                this$0 = BuyAskListActivity.this;
                super();
            }
        }
);
    }

    private void showBuyAskList(String s, JSONObject jsonobject)
    {
        (new NextPageLoader(this, buyAskList, loadingLayout, s, jsonobject) {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                BuyAskListActivity buyasklistactivity = BuyAskListActivity.this;
                String as[] = new String[4];
                as[0] = "content";
                as[1] = "userId";
                as[2] = "creationTime";
                as[3] = "replyContent";
                int ai[] = new int[4];
                ai[0] = 0x7f0c003d;
                ai[1] = 0x7f0c003a;
                ai[2] = 0x7f0c003b;
                ai[3] = 0x7f0c003f;
                return new MySimpleAdapter(buyasklistactivity, arraylist, 0x7f03000f, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        if(i % 2 == 1)
                            view1.setBackgroundResource(0x7f070003);
                        else
                            view1.setBackgroundResource(0x7f070004);
                        return view1;
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super(myactivity, list, i, as, ai);
                    }
                }
;
            }

            protected void showError()
            {
                if(Log.D)
                    Log.d("BuyAskListActivity", "showError() -->> ?");
                post(new Runnable() {

                    public void run()
                    {
                        titleButton.setClickable(false);
                        Toast.makeText(_fld0, 0x7f0901b5, 0).show();
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
            }

            protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                ArrayList arraylist1 = BuyAsk.toList(jsonobjectproxy.getJSONArray("consultationList"), 0);
                ArrayList arraylist = arraylist1;
_L2:
                return arraylist;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d("BuyAskListActivity", "JSONException -->> 1111", jsonexception);
                arraylist = null;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final BuyAskListActivity this$0;


            
            {
                this$0 = BuyAskListActivity.this;
                super(myactivity, abslistview, view, s, jsonobject);
            }
        }
).showPageOne();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000e);
        params = new JSONObject();
        Intent intent = getIntent();
        wareId = String.valueOf(intent.getExtras().getLong("id"));
        if(Log.D)
            Log.d("BuyAskListActivity", (new StringBuilder("wareId=")).append(wareId).toString());
        TextView textview;
        Object aobj[];
        try
        {
            params.put("wareId", wareId);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        name = intent.getExtras().getString("name");
        if(Log.D)
            Log.d("temp", (new StringBuilder("name:")).append(name).toString());
        if(name.length() > 8)
            subName = (new StringBuilder(String.valueOf(name.substring(0, 8)))).append("...").toString();
        else
            subName = name;
        tetleText = (TextView)findViewById(0x7f0c02c7);
        textview = tetleText;
        aobj = new Object[1];
        aobj[0] = subName;
        textview.setText(getString(0x7f0900d2, aobj));
        titleButton = (Button)findViewById(0x7f0c02c8);
        titleButton.setVisibility(0);
        titleButton.setText(0x7f090190);
        buyAskList = (ListView)findViewById(0x7f0c0038);
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        buyAskList.addFooterView(loadingLayout);
        showBuyAskList(functionId, params);
        handleButtonClick();
    }

    private ListView buyAskList;
    private String functionId;
    private LinearLayout loadingLayout;
    private String name;
    private JSONObject params;
    private String subName;
    private TextView tetleText;
    private Button titleButton;
    private AlertDialog typeChooseDialog;
    private String wareId;



}
