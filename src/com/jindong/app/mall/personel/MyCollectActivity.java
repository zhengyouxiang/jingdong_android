// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

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

public class MyCollectActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public MyCollectActivity()
    {
    }

    private void InitComponenet()
    {
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090089);
        wareInfoList = (ListView)findViewById(0x7f0c0178);
        wareInfoList.setOnItemClickListener(this);
        dbService = new DBHelperUtil(this);
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        wareInfoList.addFooterView(loadingLayout);
    }

    public static void cancelFavorites(MyActivity myactivity, Product product)
    {
        cancelFavorites(myactivity, null, product);
    }

    private static void cancelFavorites(final MyActivity context, final NextPageLoader nextPageLoader, final Product product)
    {
        JSONObject jsonobject = new JSONObject();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        try
        {
            jsonobject.put("fid", product.getFid());
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("cancelFavorite");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(httpresponse.getJSONObject() != null)
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    try
                    {
                        if(jsonobjectproxy.getBoolean("flag"))
                        {
                            if(nextPageLoader != null)
                            {
                                (new DBHelperUtil(context)).insertOrUpdateFavority(product.getId().longValue(), product.getName(), true);
                                context.post(new Runnable() {

                                    public void run()
                                    {
                                        nextPageLoader.modifyData(new com.jindong.app.mall.utils.NextPageLoader.ModifyDataRunnable() {

                                            public void modifyData(ArrayList arraylist)
                                            {
                                                arraylist.remove(product);
                                            }

                                            final _cls1 this$2;
                                            private final Product val$product;

                        
                        {
                            this$2 = _cls1.this;
                            product = product1;
                            super();
                        }
                                        }
);
                                    }

                                    final _cls3 this$1;
                                    private final NextPageLoader val$nextPageLoader;
                                    private final Product val$product;

                    
                    {
                        this$1 = _cls3.this;
                        nextPageLoader = nextpageloader;
                        product = product1;
                        super();
                    }
                                }
);
                            }
                            MyCollectActivity.showToast(context, jsonobjectproxy.getString("message"));
                        } else
                        {
                            MyCollectActivity.showToast(context, jsonobjectproxy.getString("message"));
                        }
                    }
                    catch(JSONException jsonexception1)
                    {
                        jsonexception1.printStackTrace();
                    }
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                MyCollectActivity.showToast(context, "\u53D6\u6D88\u6536\u85CF\u5931\u8D25");
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            private final MyActivity val$context;
            private final NextPageLoader val$nextPageLoader;
            private final Product val$product;

            
            {
                nextPageLoader = nextpageloader;
                context = myactivity;
                product = product1;
                super();
            }
        }
);
        context.getHttpGroupaAsynPool().add(httpsetting);
    }

    private void getMyCollect()
    {
        nextPageLoader = new NextPageLoader(this, wareInfoList, loadingLayout, functionId, params) {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                MyCollectActivity mycollectactivity = MyCollectActivity.this;
                String as[] = new String[3];
                as[0] = "imageurl";
                as[1] = "name";
                as[2] = "adword";
                int ai[] = new int[3];
                ai[0] = 0x7f0c01ea;
                ai[1] = 0x7f0c0209;
                ai[2] = 0x7f0c020a;
                return new MySimpleAdapter(mycollectactivity, arraylist, 0x7f030078, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        Product product;
                        ProductShow productshow;
                        TextView textview;
                        TextView textview1;
                        String s;
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
                            s = "";
                        } else
                        {
                            MyCollectActivity mycollectactivity1 = _fld0;
                            Object aobj[] = new Object[1];
                            aobj[0] = product.getJdPrice();
                            s = mycollectactivity1.getString(0x7f0900bf, aobj);
                        }
                        textview.setText(s);
                        textview1.setText(productshow.getMarketPrice());
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
                    Log.d("MyCollectActivity", "showError() -->> ?");
            }

            protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                ArrayList arraylist;
                try
                {
                    ArrayList arraylist1 = Product.toList(jsonobjectproxy.getJSONArray("favoriteList"), 7);
                    Iterator iterator = arraylist1.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                        {
                            arraylist = arraylist1;
                            break;
                        }
                        Product product = (Product)iterator.next();
                        dbHelper.insertOrUpdateFavority(product.getId().longValue(), product.getName(), false);
                    } while(true);
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                    arraylist = null;
                }
                return arraylist;
            }

            final MyCollectActivity this$0;


            
            {
                this$0 = MyCollectActivity.this;
                super(myactivity, abslistview, view, s, jsonobject);
            }
        }
;
        nextPageLoader.showPageOne();
    }

    private void handleLongClickEvent()
    {
        wareInfoList.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                final Product product = (Product)adapterview.getItemAtPosition(i);
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MyCollectActivity.this);
                builder.setTitle("\u64CD\u4F5C");
                builder.setItems(items, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        if(j == 0)
                        {
                            MyCollectActivity.cancelFavorites(_fld0, nextPageLoader, product);
                            dialoginterface.dismiss();
                        }
                    }

                    final _cls2 this$1;
                    private final Product val$product;

                    
                    {
                        this$1 = _cls2.this;
                        product = product1;
                        super();
                    }
                }
);
                builder.show();
                return true;
            }

            String items[];
            final MyCollectActivity this$0;


            
            {
                this$0 = MyCollectActivity.this;
                super();
                String as[] = new String[1];
                as[0] = "\u53D6\u6D88\u6536\u85CF";
                items = as;
            }
        }
);
    }

    static void showToast(final MyActivity context, final String message)
    {
        context.post(new Runnable() {

            public void run()
            {
                Toast.makeText(context, message, 0).show();
            }

            private final MyActivity val$context;
            private final String val$message;

            
            {
                context = myactivity;
                message = s;
                super();
            }
        }
);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03004b);
        dbHelper = new DBHelperUtil(this);
        Intent intent = getIntent();
        functionId = "favoriteList";
        params = new JSONObject();
        try
        {
            params.put("pin", intent.getStringExtra("pin"));
            params.put("pagesize", "10");
            params.put("page", "1");
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        InitComponenet();
        handleLongClickEvent();
        getMyCollect();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/product/ProductDetailActivity);
        Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
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

    DBHelperUtil dbHelper;
    private DBHelperUtil dbService;
    private String functionId;
    private LinearLayout loadingLayout;
    TextView mTitle;
    NextPageLoader nextPageLoader;
    private JSONObject params;
    private ListView wareInfoList;

}
