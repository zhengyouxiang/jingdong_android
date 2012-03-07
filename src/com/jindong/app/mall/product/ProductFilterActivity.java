// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jindong.app.mall.entity.CatelogyExpandSort;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.product:
//            ProductListActivity

public class ProductFilterActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public ProductFilterActivity()
    {
    }

    private void getProductFilter()
    {
        JSONObject jsonobject = new JSONObject();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            jsonobject.put("catelogyId", catelogyId);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("expandSort");
        httpsetting.setPost(true);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArrayOrNull("expandSorts");
                if(jsonarraypoxy != null && jsonarraypoxy.length() != 0) goto _L2; else goto _L1
_L1:
                final TextView hintText = (TextView)findViewById(0x7f0c0053);
                post(new Runnable() {

                    public void run()
                    {
                        listView.setVisibility(8);
                        hintText.setVisibility(0);
                        submitButton.setText(0x7f090170);
                        if(Log.D)
                            Log.d("ProductFilterActivity", "head-view");
                    }

                    final _cls2 this$1;
                    private final TextView val$hintText;

                    
                    {
                        this$1 = _cls2.this;
                        hintText = textview;
                        super();
                    }
                }
);
_L6:
                return;
_L2:
                String as[];
                list = CatelogyExpandSort.toList(jsonarraypoxy, 1);
                as = expandSortId.split("-");
                if(list == null) goto _L4; else goto _L3
_L3:
                int i;
                Iterator iterator;
                i = 0;
                iterator = list.iterator();
_L9:
                if(iterator.hasNext()) goto _L5; else goto _L4
_L4:
                ProductFilterActivity productfilteractivity = ProductFilterActivity.this;
                ArrayList arraylist = list;
                String as1[] = new String[1];
                as1[0] = "expandSortName";
                int ai[] = new int[1];
                ai[0] = 0x7f0c0054;
                final MySimpleAdapter adapter = new MySimpleAdapter(productfilteractivity, arraylist, 0x7f030076, as1, ai) {

                    public View getView(int j1, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(j1, view, viewgroup);
                        CatelogyExpandSort catelogyexpandsort1 = (CatelogyExpandSort)list.get(j1);
                        TextView textview = (TextView)view1.findViewById(0x7f0c0277);
                        if(catelogyexpandsort1.getSelectedSortOrder() > 0)
                        {
                            textview.setTextColor(0xffff0000);
                            textview.setText(catelogyexpandsort1.getValueList()[catelogyexpandsort1.getSelectedSortOrder()]);
                        } else
                        {
                            textview.setTextColor(0xff000000);
                            textview.setText(catelogyexpandsort1.getValueList()[0]);
                        }
                        ((TextView)view1.findViewById(0x7f0c0276)).setText(((CatelogyExpandSort)list.get(j1)).expandSortName);
                        if(j1 % 2 == 1)
                            view1.setBackgroundResource(0x7f02006a);
                        else
                            view1.setBackgroundResource(0x7f02006b);
                        return view1;
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super(myactivity, list1, i, as, ai);
                    }
                }
;
                post(new Runnable() {

                    public void run()
                    {
                        listView.setAdapter(adapter);
                    }

                    final _cls2 this$1;
                    private final ListAdapter val$adapter;

                    
                    {
                        this$1 = _cls2.this;
                        adapter = listadapter;
                        super();
                    }
                }
);
                  goto _L6
_L5:
                CatelogyExpandSort catelogyexpandsort;
                int j;
                catelogyexpandsort = (CatelogyExpandSort)iterator.next();
                j = Integer.valueOf(as[i]).intValue();
                if(j == 0) goto _L8; else goto _L7
_L7:
                int k;
                Integer ainteger[];
                int l;
                int i1;
                k = 0;
                ainteger = catelogyexpandsort.getKeyList();
                l = ainteger.length;
                i1 = 0;
_L10:
                if(i1 < l)
                {
label0:
                    {
                        if(ainteger[i1].intValue() != j)
                            break label0;
                        catelogyexpandsort.setSelectedSortOrder(k);
                        catelogyexpandsort.setSelectedItem(j);
                    }
                }
_L8:
                i++;
                  goto _L9
                k++;
                i1++;
                  goto _L10
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

            final ProductFilterActivity this$0;


            
            {
                this$0 = ProductFilterActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030012);
        catelogyId = getIntent().getExtras().getString("catelogyId");
        expandSortId = getIntent().getExtras().getString("expandSortId");
        if(expandSortId == null)
            expandSortId = "0-0-0-0-0-0-0";
        if(Log.D)
            Log.d("expandSortId", (new StringBuilder(String.valueOf(expandSortId))).append("+++++++++++++++++++++++++++++++++++++").toString());
        name = getIntent().getExtras().getString("name");
        TextView textview = (TextView)findViewById(0x7f0c02c7);
        String s;
        if(name.length() > 10)
            s = (new StringBuilder(String.valueOf(name.substring(0, 10)))).append("...").toString();
        else
            s = name;
        textview.setText((new StringBuilder(String.valueOf(s))).append(" - ").append(getString(0x7f09016d)).toString());
        submitButton = (Button)findViewById(0x7f0c02c8);
        submitButton.setText(0x7f090177);
        submitButton.setVisibility(0);
        submitButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent;
                Iterator iterator;
                intent = new Intent(ProductFilterActivity.this, com/jindong/app/mall/product/ProductListActivity);
                expandSortId = "";
                if(list == null)
                    break MISSING_BLOCK_LABEL_291;
                iterator = list.iterator();
_L5:
                if(iterator.hasNext()) goto _L2; else goto _L1
_L1:
                int i = 7 - list.size();
_L6:
                if(i > 0) goto _L4; else goto _L3
_L3:
                Bundle bundle1 = new Bundle();
                bundle1.putString("cid", catelogyId);
                bundle1.putString("expandSortId", expandSortId.substring(0, expandSortId.length() - 1));
                bundle1.putString("name", name);
                if(Log.D)
                    Log.d("expandSortId", expandSortId.substring(0, expandSortId.length() - 1));
                intent.putExtras(bundle1);
                noShowAgain();
                startActivityInFrame(intent);
                return;
_L2:
                CatelogyExpandSort catelogyexpandsort = (CatelogyExpandSort)iterator.next();
                ProductFilterActivity productfilteractivity = ProductFilterActivity.this;
                productfilteractivity.expandSortId = (new StringBuilder(String.valueOf(productfilteractivity.expandSortId))).append(catelogyexpandsort.getSelectedItem()).append("-").toString();
                  goto _L5
_L4:
                ProductFilterActivity productfilteractivity1 = ProductFilterActivity.this;
                productfilteractivity1.expandSortId = (new StringBuilder(String.valueOf(productfilteractivity1.expandSortId))).append("0-").toString();
                i--;
                  goto _L6
                expandSortId = "0-0-0-0-0-0-0-";
                  goto _L3
            }

            final ProductFilterActivity this$0;

            
            {
                this$0 = ProductFilterActivity.this;
                super();
            }
        }
);
        listView = (ListView)findViewById(0x7f0c0052);
        listView.setOnItemClickListener(this);
        getProductFilter();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(0x7f09016d);
        final CatelogyExpandSort catelogyExpandSort = (CatelogyExpandSort)list.get(i);
        if(catelogyExpandSort.getKeyList() != null)
        {
            final TextView choosed = (TextView)view.findViewById(0x7f0c0277);
            builder.setSingleChoiceItems(catelogyExpandSort.getValueList(), catelogyExpandSort.getSelectedSortOrder(), new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    catelogyExpandSort.setSelectedItem(catelogyExpandSort.getKeyList()[j].intValue());
                    catelogyExpandSort.setSelectedSortOrder(j);
                    choosed.setText(catelogyExpandSort.getValueList()[j]);
                    choosed.setTextColor(0xffff0000);
                    dialoginterface.dismiss();
                }

                final ProductFilterActivity this$0;
                private final CatelogyExpandSort val$catelogyExpandSort;
                private final TextView val$choosed;

            
            {
                this$0 = ProductFilterActivity.this;
                catelogyExpandSort = catelogyexpandsort;
                choosed = textview;
                super();
            }
            }
);
            builder.show();
        }
    }

    String catelogyId;
    private String expandSortId;
    private ArrayList list;
    private ListView listView;
    private String name;
    private Button submitButton;







}
