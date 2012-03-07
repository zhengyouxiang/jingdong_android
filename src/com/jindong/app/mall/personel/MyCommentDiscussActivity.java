// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.personel:
//            MakeNewComments, MakeNewDiscuss

public class MyCommentDiscussActivity extends MyActivity
{

    public MyCommentDiscussActivity()
    {
    }

    private void InitComponenet()
    {
        orderWaresList = (ListView)findViewById(0x7f0c0060);
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        orderWaresList.addFooterView(loadingLayout);
    }

    private void adjustOrder(final Intent intent, final int type, final Product product)
    {
        if(Log.D)
            Log.d("temp", (new StringBuilder("adjustParam:post")).append(adjustParam.toString()).toString());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("adjustOrder");
        httpsetting.setJsonParams(adjustParam);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
label0:
                {
                    if(Log.D)
                        Log.d("MyCommentDiscussActivity", "adjustOrder()-onEnd");
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    String s;
                    try
                    {
                        s = jsonobjectproxy.getString("flag");
                        if(Log.D)
                            Log.d("MyCommentDiscussActivity", (new StringBuilder("flag:")).append(s).toString());
                    }
                    catch(JSONException jsonexception)
                    {
                        break label0;
                    }
                    if("true".equals(s))
                    {
                        if(Log.D)
                            Log.d("temp", "flag:true");
                        if(type == 1)
                            post(new Runnable() {

                                public void run()
                                {
                                    intent.setClass(_fld0, com/jindong/app/mall/personel/MakeNewComments);
                                    startActivityInFrame(intent);
                                }

                                final _cls3 this$1;
                                private final Intent val$intent;

                    
                    {
                        this$1 = _cls3.this;
                        intent = intent1;
                        super();
                    }
                            }
);
                        if(type == 2)
                            post(new Runnable() {

                                public void run()
                                {
                                    intent.setClass(_fld0, com/jindong/app/mall/personel/MakeNewDiscuss);
                                    intent.putExtra("product", product);
                                    startActivityInFrame(intent);
                                }

                                final _cls3 this$1;
                                private final Intent val$intent;
                                private final Product val$product;

                    
                    {
                        this$1 = _cls3.this;
                        intent = intent1;
                        product = product1;
                        super();
                    }
                            }
);
                    } else
                    {
                        if(Log.D)
                            Log.d("temp", "flag:false");
                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MyCommentDiscussActivity.this);
                        if(type == 1)
                        {
                            alertDialogBuilder.setTitle(0x7f09019a);
                            alertDialogBuilder.setMessage(0x7f0901b2);
                        }
                        if(type == 2)
                        {
                            alertDialogBuilder.setTitle(0x7f09019b);
                            alertDialogBuilder.setMessage(0x7f0901b3);
                        }
                        alertDialogBuilder.setNegativeButton(0x7f09007a, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i)
                            {
                                alertDialog.dismiss();
                            }

                            final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                        }
);
                        post(new Runnable() {

                            public void run()
                            {
                                alertDialog = alertDialogBuilder.show();
                            }

                            final _cls3 this$1;
                            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls3.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                        }
);
                    }
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("MyCommentDiscussActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("MyCommentDiscussActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("MyCommentDiscussActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("MyCommentDiscussActivity", "adjustOrder()-start");
            }

            AlertDialog alertDialog;
            final MyCommentDiscussActivity this$0;
            private final Intent val$intent;
            private final Product val$product;
            private final int val$type;


            
            {
                this$0 = MyCommentDiscussActivity.this;
                type = i;
                intent = intent1;
                product = product1;
                super();
                alertDialog = null;
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void getCommentDiscussList()
    {
        orderWaresList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                view.setPressed(false);
                Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                Intent intent = new Intent(MyCommentDiscussActivity.this, com/jindong/app/mall/product/ProductDetailActivity);
                Bundle bundle = new Bundle();
                bundle.putLong("id", product.getId().longValue());
                bundle.putString("title", product.getName());
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final MyCommentDiscussActivity this$0;

            
            {
                this$0 = MyCommentDiscussActivity.this;
                super();
            }
        }
);
        (new NextPageLoader(this, orderWaresList, loadingLayout, functionId, params) {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                MyCommentDiscussActivity mycommentdiscussactivity = MyCommentDiscussActivity.this;
                String as[] = new String[1];
                as[0] = "imageurl";
                int ai[] = new int[1];
                ai[0] = 0x7f0c007d;
                return new MySimpleAdapter(mycommentdiscussactivity, arraylist, 0x7f030019, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        TextView textview = (TextView)view1.findViewById(0x7f0c007e);
                        final Product product = (Product)getItem(i);
                        if(product != null)
                        {
                            textview.setMaxLines(4);
                            textview.setText(product.getName());
                            mNewDiscuss = (Button)view1.findViewById(0x7f0c0080);
                            mNewComment = (Button)view1.findViewById(0x7f0c007f);
                            android.view.View.OnClickListener onclicklistener;
                            if(i % 2 == 1)
                                view1.setBackgroundResource(0x7f02006a);
                            else
                                view1.setBackgroundResource(0x7f02006b);
                            onclicklistener = new android.view.View.OnClickListener() {

                                public void onClick(View view2)
                                {
                                    Intent intent;
                                    intent = new Intent();
                                    intent.putExtra("product", product);
                                    adjustParam = 
// JavaClassFileOutputException: get_constant: invalid tag

                                final _cls1 this$2;
                                private final Product val$product;

                        
                        {
                            this$2 = _cls1.this;
                            product = product1;
                            super();
                        }
                            }
;
                            mNewDiscuss.setOnClickListener(onclicklistener);
                            mNewComment.setOnClickListener(onclicklistener);
                        }
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

            protected void showError()
            {
            }

            protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                ArrayList arraylist = new ArrayList();
                if(jsonobjectproxy != null)
                    arraylist = Product.toList(jsonobjectproxy.getJSONArrayOrNull("orderWares"), 7);
                return arraylist;
            }

            final MyCommentDiscussActivity this$0;



            
            {
                this$0 = MyCommentDiscussActivity.this;
                super(myactivity, abslistview, view, s, jsonobject);
            }
        }
).showPageOne();
    }

    public void onCreate(Bundle bundle)
    {
        onCreate(bundle);
        setContentView(0x7f03004c);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090092);
        functionId = "orderWares";
        params = new JSONObject();
        adjustParam = new JSONObject();
        InitComponenet();
    }

    public void onStart()
    {
        getCommentDiscussList();
        onStart();
    }

    private JSONObject adjustParam;
    private String functionId;
    private LinearLayout loadingLayout;
    Button mNewComment;
    Button mNewDiscuss;
    TextView mTitle;
    private ListView orderWaresList;
    private JSONObject params;



}
