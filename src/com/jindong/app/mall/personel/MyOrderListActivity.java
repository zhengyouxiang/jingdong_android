// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.personel:
//            CheckMyOrderDetail

public class MyOrderListActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{
    class MyNextPageLoader extends NextPageLoader
    {

        protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
        {
            MyOrderListActivity myorderlistactivity = MyOrderListActivity.this;
            String as[] = new String[1];
            as[0] = "orderStatus";
            int ai[] = new int[1];
            ai[0] = 0x7f0c01ca;
            return new MySimpleAdapter(myorderlistactivity, arraylist, 0x7f030057, as, ai) {

                public View getView(int i, View view, ViewGroup viewgroup)
                {
                    View view1 = super.getView(i, view, viewgroup);
                    Product product = (Product)getItem(i);
                    TextView textview = (TextView)view1.findViewById(0x7f0c01c9);
                    TextView textview1;
                    if(product.getSubOrderFlag())
                        textview.setText((new StringBuilder(String.valueOf(product.getOrderId()))).append(getString(0x7f0900e1)).toString());
                    else
                        textview.setText(product.getOrderId());
                    textview1 = (TextView)view1.findViewById(0x7f0c01ca);
                    if(product.getOrderStatus() != null && product.getOrderStatus().contains(getString(0x7f090177)))
                        textview1.setTextColor(0xff000000);
                    else
                        textview1.setTextColor(0xffff0000);
                    ((TextView)view1.findViewById(0x7f0c01cc)).setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(product.getOrderPrice()).toString());
                    ((TextView)view1.findViewById(0x7f0c01ce)).setText(product.getOrderSubtime());
                    if(i % 2 == 1)
                        view1.setBackgroundResource(0x7f02006a);
                    else
                        view1.setBackgroundResource(0x7f02006b);
                    return view1;
                }

                final MyNextPageLoader this$1;

                
                {
                    this$1 = MyNextPageLoader.this;
                    super(myactivity, list, i, as, ai);
                }
            }
;
        }

        protected void showError()
        {
        }

        protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
        {
            return Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("orderList"), 6);
        }

        final MyOrderListActivity this$0;


        public MyNextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s, JSONObject jsonobject)
        {
            this$0 = MyOrderListActivity.this;
            super(myactivity, abslistview, view, s, jsonobject);
            setPageSize(50);
        }
    }


    public MyOrderListActivity()
    {
        preMonthBtnClickOnce = true;
    }

    private void InitComponenet()
    {
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090088);
        oneMonthOrders = (Button)findViewById(0x7f0c017a);
        oneMonthOrders.setSelected(true);
        preMonthOrders = (Button)findViewById(0x7f0c017b);
        mOneMonthList = (ListView)findViewById(0x7f0c017d);
        mOneMonthList.setOnItemClickListener(this);
        mPreMonthList = (ListView)findViewById(0x7f0c017c);
        mPreMonthList.setOnItemClickListener(this);
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        mOneMonthList.addFooterView(loadingLayout);
        mPreMonthList.addFooterView(loadingLayout);
    }

    private void setOrderList()
    {
        mOneMonthPageLoader = new MyNextPageLoader(this, mOneMonthList, loadingLayout, functionId, params);
        mOneMonthPageLoader.showPageOne();
        functionId = "beforeOneMonthOrder";
        mPreMonthPageLoader = new MyNextPageLoader(this, mPreMonthList, loadingLayout, functionId, params);
    }

    public void handlerClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131493242 2131493243: default 28
    //                   2131493242 29
    //                   2131493243 71;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        functionId = "oneMonthOrder";
        oneMonthOrders.setSelected(true);
        preMonthOrders.setSelected(false);
        mOneMonthList.setVisibility(0);
        mPreMonthList.setVisibility(8);
        continue; /* Loop/switch isn't completed */
_L3:
        mOneMonthList.setVisibility(8);
        mPreMonthList.setVisibility(0);
        oneMonthOrders.setSelected(false);
        preMonthOrders.setSelected(true);
        if(preMonthBtnClickOnce)
        {
            mPreMonthPageLoader.showPageOne();
            preMonthBtnClickOnce = false;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004d);
        LoginUser.checkUserLogin(this);
        functionId = "oneMonthOrder";
        params = new JSONObject();
        try
        {
            params.put("pin", LoginUser.getLoginUserName());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        InitComponenet();
        setOrderList();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/personel/CheckMyOrderDetail);
        Product product = (Product)adapterview.getAdapter().getItem(i);
        if(product != null)
        {
            intent.putExtra("product", product);
            startActivityInFrame(intent);
        }
    }

    protected void onResume()
    {
        super.onResume();
    }

    private String functionId;
    private LinearLayout loadingLayout;
    private ListView mOneMonthList;
    NextPageLoader mOneMonthPageLoader;
    private ListView mPreMonthList;
    NextPageLoader mPreMonthPageLoader;
    private Button oneMonthOrders;
    private JSONObject params;
    private boolean preMonthBtnClickOnce;
    private Button preMonthOrders;
}
