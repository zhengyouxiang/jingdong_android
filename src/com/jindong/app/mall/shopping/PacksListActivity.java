// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            config, ShoppingCarActivity

public class PacksListActivity extends MyActivity
{

    public PacksListActivity()
    {
    }

    private boolean CanAddNewPacks()
    {
        boolean flag;
        ArrayList arraylist;
        ArrayList arraylist1;
        flag = true;
        DBHelperUtil dbhelperutil = new DBHelperUtil(this);
        arraylist = dbhelperutil.getCartList();
        arraylist1 = dbhelperutil.getPacksList();
        if(arraylist.size() + arraylist1.size() < 50) goto _L2; else goto _L1
_L1:
        boolean flag1 = false;
_L8:
        return flag1;
_L2:
        int i = 0;
        if(arraylist == null || arraylist.size() == 0) goto _L4; else goto _L3
_L3:
        int k = 0;
_L9:
        if(k < arraylist.size()) goto _L5; else goto _L4
_L4:
        if(arraylist1 == null || arraylist1.size() == 0) goto _L7; else goto _L6
_L6:
        int j = 0;
_L10:
        if(j < arraylist1.size())
            break MISSING_BLOCK_LABEL_134;
_L7:
        if(i >= 1000)
            flag = false;
        flag1 = flag;
          goto _L8
_L5:
        i += ((CartTable)arraylist.get(k)).buyCount;
        k++;
          goto _L9
        i += ((PacksTable)arraylist1.get(j)).buyCount * ((PacksTable)arraylist1.get(j)).childCount;
        j++;
          goto _L10
    }

    private JSONArrayPoxy addToArray(JSONArrayPoxy jsonarraypoxy, int i)
    {
        JSONArrayPoxy jsonarraypoxy1 = new JSONArrayPoxy();
        JSONObject jsonobject = new JSONObject();
        int j;
        try
        {
            jsonobject.put("SkuId", jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i).get("MainSkuId"));
            jsonobject.put("SkuPicUrl", jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i).get("MainSkuPicUrl"));
            jsonobject.put("SkuName", jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i).get("MainSkuName"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        jsonarraypoxy1.put(jsonobject);
        j = 0;
        do
        {
            if(j >= jsonarraypoxy.length())
                return jsonarraypoxy1;
            jsonarraypoxy1.put(jsonarraypoxy.getJSONObjectOrNull(j));
            j++;
        } while(true);
    }

    private void buyPack(JSONObject jsonobject)
    {
        DBHelperUtil dbhelperutil = new DBHelperUtil(this);
label0:
        {
            PacksTable packstable = dbhelperutil.queryCartByPacksId(jsonobject.getLong("PackId"));
            int i = 1 + jsonobject.getJSONArray("ProductList").length();
            JSONException jsonexception;
            if(packstable != null)
            {
                if(CanAddNewPacks())
                {
                    Contants.hasNewTocart = true;
                    dbhelperutil.updatePacksCart(packstable.packId, packstable.name, 1 + packstable.buyCount, i);
                    android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(this);
                    builder1.setTitle(0x7f0900a3);
                    builder1.setMessage(0x7f0900a4);
                    builder1.setPositiveButton(0x7f0900a5, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int j)
                        {
                            Intent intent = new Intent(PacksListActivity.this, com/jindong/app/mall/shopping/ShoppingCarActivity);
                            intent.putExtra("com.360buy:singleInstanceFlag", true);
                            startActivityInFrame(intent);
                            PacksListActivity.alertDialog.dismiss();
                        }

                        final PacksListActivity this$0;

            
            {
                this$0 = PacksListActivity.this;
                super();
            }
                    }
);
                    builder1.setNegativeButton(0x7f0900a6, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int j)
                        {
                            PacksListActivity.alertDialog.dismiss();
                        }

                        final PacksListActivity this$0;

            
            {
                this$0 = PacksListActivity.this;
                super();
            }
                    }
);
                    alertDialog = builder1.show();
                } else
                {
                    Contants.ShowMsg(getString(0x7f09010c), this);
                }
                break label0;
            }
            try
            {
                if(CanAddNewPacks())
                {
                    Contants.hasNewTocart = true;
                    dbhelperutil.insertPacksCart(jsonobject.getLong("PackId"), jsonobject.getString("PackName"), 1, i);
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                    builder.setTitle(0x7f0900a3);
                    builder.setMessage(0x7f0900a4);
                    builder.setPositiveButton(0x7f0900a5, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int j)
                        {
                            Intent intent = new Intent(PacksListActivity.this, com/jindong/app/mall/shopping/ShoppingCarActivity);
                            intent.putExtra("com.360buy:singleInstanceFlag", true);
                            startActivityInFrame(intent);
                            PacksListActivity.alertDialog.dismiss();
                        }

                        final PacksListActivity this$0;

            
            {
                this$0 = PacksListActivity.this;
                super();
            }
                    }
);
                    builder.setNegativeButton(0x7f0900a6, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int j)
                        {
                            PacksListActivity.alertDialog.dismiss();
                        }

                        final PacksListActivity this$0;

            
            {
                this$0 = PacksListActivity.this;
                super();
            }
                    }
);
                    alertDialog = builder.show();
                } else
                {
                    Contants.ShowMsg(getString(0x7f09010c), this);
                }
            }
            // Misplaced declaration of an exception variable
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        }
        return;
    }

    private void fillLayout()
    {
        LayoutInflater layoutinflater;
        LinearLayout linearlayout;
        LinearLayout linearlayout1;
        layoutinflater = LayoutInflater.from(this);
        linearlayout = (LinearLayout)findViewById(0x7f0c0217);
        linearlayout1 = (LinearLayout)findViewById(0x7f0c0218);
        if(jsonCartInfoContainer != null && jsonCartInfoContainer.toString() != "null" && jsonCartInfoContainer.getJSONArrayOrNull("data") != null && jsonCartInfoContainer.getJSONArrayOrNull("data").length() > 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String as[];
        int i;
        bExtendFlag = new boolean[jsonCartInfoContainer.getJSONArrayOrNull("data").length()];
        bExtendFlag[0] = true;
        packLayoutBtn = null;
        packLayoutBtn = new ArrayList();
        if(jsonCartInfoContainer.getJSONArrayOrNull("data").length() > 0 && jsonCartInfoContainer.getJSONArrayOrNull("data").length() <= 1)
        {
            View view2 = layoutinflater.inflate(0x7f030064, null);
            ListView listview1 = (ListView)view2.findViewById(0x7f0c01fa);
            String as1[] = new String[1];
            as1[0] = jsonCartInfoContainer.getStringOrNull("imageDomain");
            if(jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(0) != null && jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(0).getJSONArrayOrNull("ProductList") != null)
            {
                LinearLayout linearlayout4;
                LinearLayout linearlayout5;
                try
                {
                    setPackButn(view2, 0, jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(0).getString("PackName"));
                    packLayoutBtn.add(view2);
                }
                catch(JSONException jsonexception1)
                {
                    jsonexception1.printStackTrace();
                }
                setPackItemsData(listview1, jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(0).getJSONArrayOrNull("ProductList"), as1, 0);
                setMoneyInfo(view2, jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(0), 0);
            }
            linearlayout4 = (LinearLayout)view2.findViewById(0x7f0c01fb);
            linearlayout5 = (LinearLayout)view2.findViewById(0x7f0c01f9);
            view2.findViewById(0x7f0c009c).setVisibility(0);
            linearlayout5.setVisibility(0);
            linearlayout4.setVisibility(0);
            bExtendFlag[0] = true;
            linearlayout.removeAllViews();
            linearlayout.addView(view2, 0);
            linearlayout1.setVisibility(8);
            continue; /* Loop/switch isn't completed */
        }
        if(jsonCartInfoContainer.getJSONArrayOrNull("data").length() <= 1)
            continue; /* Loop/switch isn't completed */
        as = new String[1];
        as[0] = jsonCartInfoContainer.getStringOrNull("imageDomain");
        i = 0;
_L4:
label0:
        {
            if(i < jsonCartInfoContainer.getJSONArrayOrNull("data").length())
                break label0;
            linearlayout1.setVisibility(0);
        }
        if(true) goto _L1; else goto _L3
_L3:
        View view = layoutinflater.inflate(0x7f030064, null);
        ListView listview = (ListView)view.findViewById(0x7f0c01fa);
        LinearLayout linearlayout2;
        LinearLayout linearlayout3;
        View view1;
        if(jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i) != null && jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i).getJSONArrayOrNull("ProductList") != null)
        {
            try
            {
                setPackButn(view, i, jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i).getString("PackName"));
                packLayoutBtn.add(view);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            setPackItemsData(listview, jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i).getJSONArrayOrNull("ProductList"), as, i);
            setMoneyInfo(view, jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(i), i);
        }
        linearlayout2 = (LinearLayout)view.findViewById(0x7f0c01fb);
        linearlayout3 = (LinearLayout)view.findViewById(0x7f0c01f9);
        view1 = view.findViewById(0x7f0c009c);
        if(i == 0)
        {
            view1.setVisibility(0);
            linearlayout3.setVisibility(0);
            linearlayout2.setVisibility(0);
            bExtendFlag[i] = true;
        } else
        {
            view1.setVisibility(8);
            linearlayout3.setVisibility(8);
            linearlayout2.setVisibility(8);
            bExtendFlag[i] = false;
        }
        linearlayout1.addView(view, i);
        i++;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void getScreenHW()
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    private void setMoneyInfo(View view, JSONObject jsonobject, final int index)
    {
        TextView textview = (TextView)view.findViewById(0x7f0c01ff);
        TextView textview1 = (TextView)view.findViewById(0x7f0c0201);
        TextView textview2 = (TextView)view.findViewById(0x7f0c0202);
        Button button = (Button)view.findViewById(0x7f0c0203);
        try
        {
            textview.setText(jsonobject.getString("PackPrice"));
            textview.setTextColor(Color.rgb(200, 10, 10));
            textview1.setText(jsonobject.getString("PackListPrice"));
            textview1.setTextColor(Color.rgb(153, 153, 153));
            textview2.setText(jsonobject.getString("Discount"));
            textview2.setTextColor(Color.rgb(10, 200, 10));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                buyPack(jsonCartInfoContainer.getJSONArrayOrNull("data").getJSONObjectOrNull(index));
            }

            final PacksListActivity this$0;
            private final int val$index;

            
            {
                this$0 = PacksListActivity.this;
                index = i;
                super();
            }
        }
);
    }

    private void setPackButn(final View layout, final int index, String s)
    {
        ((TextView)layout.findViewById(0x7f0c01f6)).setText((new StringBuilder("\u3010\u4F18\u60E0\u3011")).append(s).toString());
        final ImageView mFlag = (ImageView)layout.findViewById(0x7f0c01f8);
        View view;
        if(index == 0 && bExtendFlag[index])
            mFlag.setBackgroundResource(0x7f02003d);
        else
            mFlag.setBackgroundResource(0x7f02003e);
        view = layout.findViewById(0x7f0c01f5);
        mFlag.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(bExtendFlag[index])
                {
                    mFlag.setBackgroundResource(0x7f02003e);
                    bExtendFlag[index] = false;
                    View view4 = layout.findViewById(0x7f0c01fb);
                    View view5 = layout.findViewById(0x7f0c01f9);
                    layout.findViewById(0x7f0c009c).setVisibility(8);
                    view5.setVisibility(8);
                    view4.setVisibility(8);
                } else
                {
                    mFlag.setBackgroundResource(0x7f02003d);
                    bExtendFlag[index] = true;
                    View view2 = layout.findViewById(0x7f0c01fb);
                    View view3 = layout.findViewById(0x7f0c01f9);
                    layout.findViewById(0x7f0c009c).setVisibility(0);
                    view3.setVisibility(0);
                    view2.setVisibility(0);
                }
            }

            final PacksListActivity this$0;
            private final int val$index;
            private final View val$layout;
            private final ImageView val$mFlag;

            
            {
                this$0 = PacksListActivity.this;
                index = i;
                mFlag = imageview;
                layout = view;
                super();
            }
        }
);
        view.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                if(!bExtendFlag[index]) goto _L2; else goto _L1
_L1:
                mFlag.setBackgroundResource(0x7f02003e);
                bExtendFlag[index] = false;
                View view8 = layout.findViewById(0x7f0c01fb);
                View view9 = layout.findViewById(0x7f0c01f9);
                layout.findViewById(0x7f0c009c).setVisibility(8);
                view9.setVisibility(8);
                view8.setVisibility(8);
_L4:
                return;
_L2:
                int i;
                if(packLayoutBtn.size() > 0 && packLayoutBtn.size() <= 1)
                {
                    mFlag.setBackgroundResource(0x7f02003d);
                    bExtendFlag[index] = true;
                    View view6 = layout.findViewById(0x7f0c01fb);
                    View view7 = layout.findViewById(0x7f0c01f9);
                    layout.findViewById(0x7f0c009c).setVisibility(0);
                    view7.setVisibility(0);
                    view6.setVisibility(0);
                    continue; /* Loop/switch isn't completed */
                }
                i = 0;
_L5:
label0:
                {
                    if(i < packLayoutBtn.size())
                        break label0;
                    mFlag.setBackgroundResource(0x7f02003d);
                    bExtendFlag[index] = true;
                    View view4 = layout.findViewById(0x7f0c01fb);
                    View view5 = layout.findViewById(0x7f0c01f9);
                    layout.findViewById(0x7f0c009c).setVisibility(0);
                    view5.setVisibility(0);
                    view4.setVisibility(0);
                }
                if(true) goto _L4; else goto _L3
_L3:
                if(bExtendFlag[i])
                {
                    bExtendFlag[i] = false;
                    View view2 = ((View)packLayoutBtn.get(i)).findViewById(0x7f0c01fb);
                    View view3 = ((View)packLayoutBtn.get(i)).findViewById(0x7f0c01f9);
                    ((View)packLayoutBtn.get(i)).findViewById(0x7f0c009c).setVisibility(8);
                    view3.setVisibility(8);
                    view2.setVisibility(8);
                    ((ImageView)((View)packLayoutBtn.get(i)).findViewById(0x7f0c01f8)).setBackgroundResource(0x7f02003e);
                }
                i++;
                  goto _L5
                if(true) goto _L4; else goto _L6
_L6:
            }

            final PacksListActivity this$0;
            private final int val$index;
            private final View val$layout;
            private final ImageView val$mFlag;

            
            {
                this$0 = PacksListActivity.this;
                index = i;
                mFlag = imageview;
                layout = view;
                super();
            }
        }
);
    }

    private void setPackItemsData(ListView listview, JSONArrayPoxy jsonarraypoxy, String as[], int i)
    {
        ArrayList arraylist = Product.toList(addToArray(jsonarraypoxy, i), 13, as);
        adapter = null;
        String as1[] = new String[2];
        as1[0] = "imageUrl";
        as1[1] = "name";
        int ai[] = new int[2];
        ai[0] = 0x7f0c01ea;
        ai[1] = 0x7f0c0209;
        adapter = new MySimpleAdapter(this, arraylist, 0x7f030067, as1, ai) {

            public View getView(int j, View view, ViewGroup viewgroup)
            {
                View view1 = super.getView(j, view, viewgroup);
                if(j % 2 == 1)
                    view1.setBackgroundResource(0x7f0200a2);
                else
                    view1.setBackgroundResource(0x7f0200a3);
                view1.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view2)
                    {
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                        Bundle bundle = new Bundle();
                        bundle.putLong("id", product.getId().longValue());
                        bundle.putString("title", product.getName());
                        intent.putExtras(bundle);
                        startActivityInFrame(intent);
                    }

                    final _cls4 this$1;
                    private final Product val$product;

                    
                    {
                        this$1 = _cls4.this;
                        product = product1;
                        super();
                    }
                }
);
                return view1;
            }

            final PacksListActivity this$0;


            
            {
                this$0 = PacksListActivity.this;
                super(myactivity, list, i, as, ai);
            }
        }
;
        listview.setAdapter(adapter);
        listview.getHeight();
        if(screenHeight >= 1024 || screenHeight <= 480 || screenWidth <= 320 || screenWidth >= 600) goto _L2; else goto _L1
_L1:
        if(listview.getCount() < 16)
            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 146 * listview.getCount()));
        else
            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 147 * listview.getCount()));
_L4:
        return;
_L2:
        if(screenHeight >= 1024 && screenWidth >= 600)
        {
            if(listview.getCount() < 16)
                listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 145 * listview.getCount()));
            else
                listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 146 * listview.getCount()));
        } else
        if(screenHeight == 480 && screenWidth == 320)
            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 95 * listview.getCount()));
        else
        if(screenHeight < 480 && screenWidth <= 320)
            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 73 * listview.getCount()));
        else
        if(screenHeight < 1024 && screenHeight > 480 && screenWidth >= 640)
        {
            if(listview.getCount() < 16)
                listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 200 * listview.getCount()));
            else
                listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 200 * listview.getCount()));
        } else
        if(screenHeight == 400 && screenWidth == 360)
            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 193 * listview.getCount()));
        else
        if(screenHeight == 960 && screenWidth == 600)
            listview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, 200 * listview.getCount()));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void getPacksInfo(String s)
    {
        JSONObject jsonobject = new JSONObject();
        if(s != null && s != "" && s != " " && jsonobject != null)
            try
            {
                jsonobject.put("wareId", s);
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        hs = null;
        hs = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        hs.setPost(true);
        hs.setFunctionId(config.GET_PACKS);
        hs.setJsonParams(jsonobject);
        hs.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                jsonCartInfoContainer = new JSONObjectProxy(httpresponse.getJSONObject());
                if(jsonCartInfoContainer != null)
                    post(new Runnable() {

                        public void run()
                        {
                            fillLayout();
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
);
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                  goto _L1
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("PacksListActivity-update", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("PacksListActivity-update", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("PacksListActivity-update", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("PacksListActivity-update", "setUpConnAndGetData()-start");
            }

            final PacksListActivity this$0;


            
            {
                this$0 = PacksListActivity.this;
                super();
            }
        }
);
        getHttpGroupaAsynPool().add(hs);
        hs = null;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03006c);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText("\u5957\u88C5\u7CFB\u5217");
        getScreenHW();
        getPacksInfo(String.valueOf(Contants.packMainProdId));
    }

    static AlertDialog alertDialog = null;
    MySimpleAdapter adapter;
    boolean bExtendFlag[];
    com.jindong.app.mall.utils.HttpGroup.HttpSetting hs;
    JSONObjectProxy jsonCartInfoContainer;
    TextView mTitle;
    ArrayList packLayoutBtn;
    int screenHeight;
    int screenWidth;



}
