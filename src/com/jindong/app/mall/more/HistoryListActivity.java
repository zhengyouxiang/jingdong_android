// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.more;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoryListActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public HistoryListActivity()
    {
    }

    private void getWareInfoList(String s, JSONObject jsonobject)
    {
        (new NextPageLoader(this, wareInfoList, loadingLayout, s, jsonobject) {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                HistoryListActivity historylistactivity = HistoryListActivity.this;
                String as[] = new String[3];
                as[0] = "imageurl";
                as[1] = "name";
                as[2] = "adword";
                int ai[] = new int[3];
                ai[0] = 0x7f0c01ea;
                ai[1] = 0x7f0c0209;
                ai[2] = 0x7f0c020a;
                return new MySimpleAdapter(historylistactivity, arraylist, 0x7f030078, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        Product product;
                        ProductShow productshow;
                        TextView textview;
                        TextView textview1;
                        String s1;
                        if(i % 2 == 1)
                            view1.setBackgroundResource(0x7f0200a2);
                        else
                            view1.setBackgroundResource(0x7f0200a3);
                        product = (Product)getItem(i);
                        productshow = new ProductShow(_fld0, product);
                        textview = (TextView)view1.findViewById(0x7f0c020b);
                        textview1 = (TextView)view1.findViewById(0x7f0c027f);
                        if(TextUtils.isEmpty(product.getJdPrice()))
                        {
                            s1 = "";
                        } else
                        {
                            HistoryListActivity historylistactivity1 = _fld0;
                            Object aobj[] = new Object[1];
                            aobj[0] = product.getJdPrice();
                            s1 = historylistactivity1.getString(0x7f0900bf, aobj);
                        }
                        textview.setText(s1);
                        textview1.setText(productshow.getMarketPrice());
                        return view1;
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super(myactivity, list, i, as, ai);
                    }
                }
;
            }

            protected void handleParamsBeforeLoading()
            {
                params.put("wareId", getProductIdArrayStr(pageNum.intValue(), pageSize.intValue()));
_L1:
                return;
                JSONException jsonexception;
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L1
            }

            protected void showError()
            {
                if(Log.D)
                    Log.d("ProductListActivity", "showError() -->> ?");
            }

            protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                ArrayList arraylist1 = Product.toList(jsonobjectproxy.getJSONArray("wareInfoList"), 1);
                ArrayList arraylist = arraylist1;
_L2:
                return arraylist;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d("ProductListActivity", "JSONException -->> 1111", jsonexception);
                arraylist = null;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final HistoryListActivity this$0;


            
            {
                this$0 = HistoryListActivity.this;
                super(myactivity, abslistview, view, s, jsonobject);
            }
        }
).showPageOne();
    }

    public String getProductIdArrayStr(int i, int j)
    {
        ArrayList arraylist;
        StringBuffer stringbuffer;
        arraylist = dbService.getHistoryByPage(i, j);
        stringbuffer = new StringBuffer();
        if(arraylist == null || arraylist.size() == 0) goto _L2; else goto _L1
_L1:
        Iterator iterator = arraylist.iterator();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        if(stringbuffer.length() > 0)
            stringbuffer.deleteCharAt(stringbuffer.length() - 1);
        return stringbuffer.toString();
_L3:
        stringbuffer.append(((Product)iterator.next()).getId()).append(",");
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004b);
        dbService = new DBHelperUtil(this);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f0900b6);
        Button button = (Button)findViewById(0x7f0c02c8);
        button.setVisibility(0);
        button.setText(0x7f0900b7);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(HistoryListActivity.this)).create();
                alertdialog.setMessage(getText(0x7f0900b8));
                alertdialog.setTitle(0x7f0900b7);
                alertdialog.setButton(getText(0x7f09007a), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dbService.delAllHistory();
                        dialoginterface.dismiss();
                        onCreate(null);
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                alertdialog.setButton2(getText(0x7f09000f), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dialoginterface.dismiss();
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                alertdialog.show();
            }

            final HistoryListActivity this$0;


            
            {
                this$0 = HistoryListActivity.this;
                super();
            }
        }
);
        params = new JSONObject();
        functionId = "wareHistory";
        wareInfoList = (ListView)findViewById(0x7f0c0178);
        wareInfoList.setOnItemClickListener(this);
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        wareInfoList.addFooterView(loadingLayout);
        getWareInfoList(functionId, params);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/product/ProductDetailActivity);
        Product product = (Product)adapterview.getAdapter().getItem(i);
        if(product != null)
        {
            intent.putExtra("product", product);
            Bundle bundle = new Bundle();
            bundle.putLong("id", product.getId().longValue());
            bundle.putString("title", product.getName());
            intent.putExtras(bundle);
            startActivityInFrame(intent);
        }
    }

    private DBHelperUtil dbService;
    private String functionId;
    private LinearLayout loadingLayout;
    private JSONObject params;
    private ListView wareInfoList;

}
