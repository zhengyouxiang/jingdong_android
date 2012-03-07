// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.product:
//            ProductDetailActivity, ProductFilterActivity, CommercialRuleActivity

public class ProductListActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{
    private class ButtonClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            view.setPressed(false);
            if(view.getId() != 0x7f0c02c8) goto _L2; else goto _L1
_L1:
            if(Log.D)
                Log.d("ProductListActivity", "titleRightButton--pressed");
            if(!"search".equals(functionId)) goto _L4; else goto _L3
_L3:
            openOptionsDialog();
_L10:
            return;
_L4:
            if("searchCatelogy".equals(functionId))
            {
                Intent intent = new Intent(ProductListActivity.this, com/jindong/app/mall/product/ProductFilterActivity);
                Bundle bundle = new Bundle();
                if(Log.D)
                    Log.d("ProductListActivity", (new StringBuilder(String.valueOf(levelFirst))).append("-").append(levelSecond).append("-").append(catelogyId).toString());
                bundle.putString("catelogyId", (new StringBuilder(String.valueOf(levelFirst))).append("-").append(levelSecond).append("-").append(catelogyId).toString());
                bundle.putString("expandSortId", expandSortId);
                bundle.putString("name", name);
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            } else
            if("viewActivity".equals(functionId))
            {
                Intent intent1 = new Intent(ProductListActivity.this, com/jindong/app/mall/product/CommercialRuleActivity);
                Bundle bundle1 = new Bundle();
                bundle1.putString("title", commercialTitle);
                bundle1.putString("detail", promotionDetail);
                intent1.putExtras(bundle1);
                startActivityInFrame(intent1);
            }
              goto _L5
_L2:
            JSONObject jsonobject;
            jsonobject = params;
            if(Log.D)
                Log.d("ProductListActivity", (new StringBuilder("befor-order:params:")).append(jsonobject.toString()).toString());
            int i = view.getId();
            i;
            JVM INSTR tableswitch 2131493498 2131493500: default 416
        //                       2131493498 443
        //                       2131493499 538
        //                       2131493500 591;
               goto _L6 _L7 _L8 _L9
_L6:
            JSONException jsonexception;
            try
            {
                jsonobject.put("page", "1");
            }
            catch(JSONException jsonexception1)
            {
                jsonexception1.printStackTrace();
            }
            getWareInfoList(functionId, jsonobject);
_L5:
            if(true) goto _L10; else goto _L7
_L7:
            if(Log.D)
                Log.d("ProductListActivity", "orderButton1--pressed");
            if(sortKey != 3)
                break MISSING_BLOCK_LABEL_515;
            sortKey = 2;
            sortButtonLeft.setBackgroundResource(0x7f0200a0);
_L11:
            jsonobject.put("sort", String.valueOf(sortKey));
              goto _L6
            jsonexception;
            jsonexception.printStackTrace();
              goto _L6
            sortKey = 3;
            sortButtonLeft.setBackgroundResource(0x7f0200a1);
              goto _L11
_L8:
            sortButtonLeft.setBackgroundResource(0x7f02004b);
            if(Log.D)
                Log.d("ProductListActivity", "orderButton2--pressed");
            sortKey = 1;
            jsonobject.put("sort", String.valueOf(sortKey));
              goto _L6
_L9:
            if(Log.D)
                Log.d("temp", (new StringBuilder("functionId:")).append(functionId).toString());
            sortButtonLeft.setBackgroundResource(0x7f02004b);
            if(Log.D)
                Log.d("ProductListActivity", "orderButton3--pressed");
            sortKey = 5;
            if("searchCatelogy".equals(functionId))
                jsonobject.put("sort", String.valueOf(sortKey));
            else
                jsonobject.remove("sort");
              goto _L6
        }

        final ProductListActivity this$0;

        private ButtonClickListener()
        {
            this$0 = ProductListActivity.this;
            super();
        }

        ButtonClickListener(ButtonClickListener buttonclicklistener)
        {
            this();
        }
    }


    public ProductListActivity()
    {
        isFirst = true;
        alertDialog = null;
    }

    private void getWareInfoList(String s, JSONObject jsonobject)
    {
        if(Log.D)
            Log.d("ProductListActivity", "getWareInfoList");
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("params:")).append(jsonobject.toString()).toString());
        String s1 = getString(0x7f0900a7);
        (new NextPageLoader(this, wareInfoList, loadingLayout, s, jsonobject, s1) {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                ProductListActivity productlistactivity = ProductListActivity.this;
                String as[] = new String[3];
                as[0] = "imageurl";
                as[1] = "name";
                as[2] = "adWord";
                int ai[] = new int[3];
                ai[0] = 0x7f0c01ea;
                ai[1] = 0x7f0c0209;
                ai[2] = 0x7f0c020a;
                return new MySimpleAdapter(productlistactivity, arraylist, 0x7f030078, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        Product product;
                        ProductShow productshow;
                        TextView textview;
                        TextView textview1;
                        String s2;
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
                            s2 = "";
                        } else
                        {
                            ProductListActivity productlistactivity1 = _fld0;
                            Object aobj[] = new Object[1];
                            aobj[0] = product.getJdPrice();
                            s2 = productlistactivity1.getString(0x7f0900bf, aobj);
                        }
                        textview.setText(s2);
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

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final Integer productTotalCount;
                final Integer totalCount;
                super.onEnd(httpresponse);
                productTotalCount = httpresponse.getJSONObject().getIntOrNull("wareCount");
                totalCount = httpresponse.getJSONObject().getIntOrNull("totalCount");
                promotionDetail = httpresponse.getJSONObject().getStringOrNull("promotionDetail");
                commercialTitle = httpresponse.getJSONObject().getStringOrNull("title");
                if(!"search".equals(functionId)) goto _L2; else goto _L1
_L1:
                post(new Runnable() {

                    public void run()
                    {
                        if(productTotalCount == null)
                            searchCateText.setText(name);
                        else
                            searchCateText.setText((new StringBuilder(String.valueOf(name))).append("(").append(productTotalCount).append(")").toString());
                    }

                    final _cls1 this$1;
                    private final Integer val$productTotalCount;

                    
                    {
                        this$1 = _cls1.this;
                        productTotalCount = integer;
                        super();
                    }
                }
);
_L4:
                return;
_L2:
                if("searchCatelogy".equals(functionId))
                    post(new Runnable() {

                        public void run()
                        {
                            if(productTotalCount == null)
                            {
                                searchCateText.setText(name);
                            } else
                            {
                                TextView textview = searchCateText;
                                ProductListActivity productlistactivity = _fld0;
                                Object aobj[] = new Object[2];
                                aobj[0] = name;
                                aobj[1] = productTotalCount;
                                textview.setText(productlistactivity.getString(0x7f0900d7, aobj));
                            }
                        }

                        final _cls1 this$1;
                        private final Integer val$productTotalCount;

                    
                    {
                        this$1 = _cls1.this;
                        productTotalCount = integer;
                        super();
                    }
                    }
);
                else
                if("viewActivity".equals(functionId))
                {
                    if(!TextUtils.isEmpty(promotionDetail))
                        post(new Runnable() {

                            public void run()
                            {
                                filterButton.setVisibility(0);
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                    if(TextUtils.isEmpty(commercialTitle))
                        commercialTitle = name;
                    post(new Runnable() {

                        public void run()
                        {
                            if(totalCount == null)
                                searchCateText.setText(commercialTitle);
                            else
                                searchCateText.setText((new StringBuilder(String.valueOf(commercialTitle))).append("(").append(totalCount).append(")").toString());
                        }

                        final _cls1 this$1;
                        private final Integer val$totalCount;

                    
                    {
                        this$1 = _cls1.this;
                        totalCount = integer;
                        super();
                    }
                    }
);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            protected void showError()
            {
                if(Log.D)
                    Log.d("ProductListActivity", "showError() -->> ?");
                post(new Runnable() {

                    public void run()
                    {
                        sortButtonLeft.setClickable(false);
                        sortButtonMiddle.setClickable(false);
                        sortButtonRight.setClickable(false);
                        searchCateText.setText((new StringBuilder(String.valueOf(name))).append("(").append(0).append(")").toString());
                        filterButton.setVisibility(8);
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
                ArrayList arraylist;
                if(commercialId != null)
                    arraylist = Product.toList(jsonobjectproxy.getJSONArrayOrNull("activityProducts"), 1);
                else
                    arraylist = Product.toList(jsonobjectproxy.getJSONArrayOrNull("wareInfo"), 1);
                return arraylist;
            }

            final ProductListActivity this$0;


            
            {
                this$0 = ProductListActivity.this;
                super(myactivity, abslistview, view, s, jsonobject, s1);
            }
        }
).showPageOne();
        post(new Runnable() {

            public void run()
            {
                searchCateText.setText(name);
            }

            final ProductListActivity this$0;

            
            {
                this$0 = ProductListActivity.this;
                super();
            }
        }
);
        sortButtonLeft.setOnClickListener(listener);
        sortButtonMiddle.setOnClickListener(listener);
        sortButtonRight.setOnClickListener(listener);
        filterButton.setOnClickListener(listener);
        wareInfoList.setOnItemClickListener(this);
    }

    private void initComponent()
    {
        Intent intent;
        dbService = new DBHelperUtil(this);
        searchCateText = (TextView)findViewById(0x7f0c02c7);
        filterButton = (Button)findViewById(0x7f0c02c8);
        sortButtonGroup = (RadioGroup)findViewById(0x7f0c0279);
        sortButtonLeft = (RadioButton)findViewById(0x7f0c027a);
        sortButtonLeft.setText(0x7f09009d);
        sortButtonMiddle = (RadioButton)findViewById(0x7f0c027b);
        sortButtonMiddle.setText(0x7f09009e);
        sortButtonRight = (RadioButton)findViewById(0x7f0c027c);
        wareInfoList = (ListView)findViewById(0x7f0c027e);
        listener = new ButtonClickListener(null);
        params = new JSONObject();
        intent = getIntent();
        commercial = (Commercial)intent.getExtras().getSerializable("commercial");
        if(commercial == null) goto _L2; else goto _L1
_L1:
        commercialId = Long.valueOf(Long.parseLong(commercial.getId()));
_L12:
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("catelogyId=")).append(catelogyId).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("keyWord=")).append(keyWord).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("searchway=")).append(searchWay).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("name=")).append(name).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("filterName=")).append(filterName).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("expandSortId=")).append(expandSortId).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("levelFirst=")).append(levelFirst).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("levelSecond=")).append(levelSecond).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("commercial=")).append(commercial).toString());
        if(Log.D)
            Log.d("ProductListActivity", (new StringBuilder("commercialId=")).append(commercialId).toString());
        if(commercialId == null) goto _L4; else goto _L3
_L3:
        sortButtonGroup.setVisibility(8);
        filterButton.setText(0x7f09016e);
        functionId = "viewActivity";
        name = getString(0x7f09016f);
        String as[];
        try
        {
            params.put("activityId", commercialId);
        }
        catch(JSONException jsonexception3)
        {
            jsonexception3.printStackTrace();
        }
_L10:
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        wareInfoList.addFooterView(loadingLayout);
        return;
_L2:
        catelogyId = intent.getExtras().getString("cid");
        keyWord = intent.getExtras().getString("keyWord");
        searchWay = intent.getExtras().getString("searchway");
        name = intent.getExtras().getString("name");
        filterName = intent.getExtras().getString("filterName");
        expandSortId = intent.getExtras().getString("expandSortId");
        levelFirst = intent.getExtras().getString("levelFirst");
        levelSecond = intent.getExtras().getString("levelSecond");
        if(catelogyId == null || !catelogyId.contains("-"))
            continue; /* Loop/switch isn't completed */
        as = catelogyId.split("-");
        as.length;
        JVM INSTR tableswitch 1 3: default 1212
    //                   1 780
    //                   2 799
    //                   3 791;
           goto _L5 _L6 _L7 _L8
_L5:
        if(false)
            ;
        continue; /* Loop/switch isn't completed */
_L6:
        levelFirst = as[0];
        continue; /* Loop/switch isn't completed */
_L8:
        catelogyId = as[2];
_L7:
        levelSecond = as[1];
        if(true) goto _L6; else goto _L4
_L4:
        if(catelogyId != null && name != null && keyWord == null)
        {
            functionId = "searchCatelogy";
            sortButtonGroup.check(0x7f0c027b);
            sortKey = 1;
            filterButton.setVisibility(0);
            filterButton.setText(0x7f09016d);
            try
            {
                sortButtonRight.setText(0x7f0900a0);
                params.put("catelogyId", catelogyId);
                if(expandSortId != null)
                    params.put("expandSortId", expandSortId);
            }
            catch(JSONException jsonexception2)
            {
                jsonexception2.printStackTrace();
            }
        } else
        {
            functionId = "search";
            sortButtonGroup.check(0x7f0c027c);
            sortButtonRight.setText(0x7f09009f);
            if(keyWord != null && catelogyId == null)
            {
                filterButton.setVisibility(0);
                filterButton.setText(0x7f09016c);
                Object aobj1[] = new Object[1];
                aobj1[0] = keyWord;
                name = getString(0x7f0900d5, aobj1);
                try
                {
                    params.put("keyword", keyWord);
                    params.put("searchway", searchWay);
                }
                catch(JSONException jsonexception1)
                {
                    jsonexception1.printStackTrace();
                }
            } else
            {
                filterButton.setVisibility(8);
                int i = filterName.length();
                Object aobj[];
                if(keyWord.length() > 11 - i)
                    name = keyWord;
                else
                    name = keyWord;
                aobj = new Object[2];
                aobj[0] = name;
                aobj[1] = filterName;
                name = getString(0x7f0900d6, aobj);
                try
                {
                    params.put("keyword", keyWord);
                    params.put("cid", catelogyId);
                    params.put("searchway", "filter");
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
            }
        }
        if(true) goto _L10; else goto _L9
_L9:
        Exception exception;
        exception;
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void openOptionsDialog()
    {
        JSONObject jsonobject;
        catelogyFilterDialog = new android.app.AlertDialog.Builder(this);
        catelogyFilterDialog.setTitle(0x7f09016c);
        jsonobject = new JSONObject();
        jsonobject.put("keyword", keyWord);
_L2:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("catelogyFilter");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy;
                try
                {
                    jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("catelogyList");
                    if(Log.D)
                        Log.d("tmp", (new StringBuilder("jsonArray:")).append(jsonarraypoxy.toString()).toString());
                }
                catch(JSONException jsonexception1)
                {
                    showHintDialog();
                    if(Log.D)
                        Log.d("ProductListActivity", "JSONException -->> catelogyList", jsonexception1);
                    continue; /* Loop/switch isn't completed */
                }
                if(jsonarraypoxy != null && jsonarraypoxy.length() >= 1) goto _L2; else goto _L1
_L1:
                showHintDialog();
_L4:
                return;
_L2:
                final ArrayList list;
                String as[];
                int i;
                list = Catelogy.toList(jsonarraypoxy, 1);
                as = new String[list.size()];
                i = 0;
_L5:
label0:
                {
                    if(i < list.size())
                        break label0;
                    catelogyFilterDialog.setSingleChoiceItems(as, -1, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int j)
                        {
                            Catelogy catelogy = (Catelogy)list.get(j);
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductListActivity);
                            Bundle bundle = new Bundle();
                            bundle.putString("keyWord", keyWord);
                            bundle.putString("cid", catelogy.getcId());
                            bundle.putString("filterName", catelogy.getName());
                            intent.putExtras(bundle);
                            startActivityInFrame(intent);
                            alertDialog.dismiss();
                        }

                        final _cls3 this$1;
                        private final ArrayList val$list;

                    
                    {
                        this$1 = _cls3.this;
                        list = arraylist;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            alertDialog = catelogyFilterDialog.show();
                        }

                        final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                    }
);
                }
                if(true) goto _L4; else goto _L3
_L3:
                as[i] = (new StringBuilder(String.valueOf(((Catelogy)list.get(i)).getName()))).append("(").append(((Catelogy)list.get(i)).getWareNumber()).append(")").toString();
                i++;
                  goto _L5
                if(true) goto _L4; else goto _L6
_L6:
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("catelogyFilter", (new StringBuilder("error -->> ")).append(httperror).toString());
                post(new Runnable() {

                    public void run()
                    {
                        Toast.makeText(_fld0, 0x7f0901b5, 0).show();
                    }

                    final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                }
);
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("catelogyFilter", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("catelogyFilter", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("catelogyFilter", "getCategoryList()-start");
            }

            final ProductListActivity this$0;


            
            {
                this$0 = ProductListActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("catelogyFilter", jsonexception.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void showHintDialog()
    {
        catelogyFilterDialog.setMessage(0x7f090172);
        catelogyFilterDialog.setPositiveButton(0x7f09000e, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                alertDialog.dismiss();
            }

            final ProductListActivity this$0;

            
            {
                this$0 = ProductListActivity.this;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                alertDialog = catelogyFilterDialog.show();
            }

            final ProductListActivity this$0;

            
            {
                this$0 = ProductListActivity.this;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030077);
        initComponent();
        getWareInfoList(functionId, params);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/product/ProductDetailActivity);
        Product product = (Product)adapterview.getAdapter().getItem(i);
        if(product != null)
        {
            Bundle bundle = new Bundle();
            bundle.putLong("id", product.getId().longValue());
            bundle.putString("title", product.getName());
            intent.putExtras(bundle);
            startActivityInFrame(intent);
        }
    }

    public boolean onSearchRequested()
    {
        return super.onSearchRequested();
    }

    public static final int SORT_PRICE_DOWN = 2;
    public static final int SORT_PRICE_UP = 3;
    public static final int SORT_QUANTITY = 1;
    public static final int SORT_TIME = 5;
    private AlertDialog alertDialog;
    private android.app.AlertDialog.Builder catelogyFilterDialog;
    private String catelogyId;
    private Commercial commercial;
    private Long commercialId;
    private String commercialTitle;
    private DBHelperUtil dbService;
    private String expandSortId;
    private Button filterButton;
    private String filterName;
    private String functionId;
    private boolean isFirst;
    private String keyWord;
    private String levelFirst;
    private String levelSecond;
    private ButtonClickListener listener;
    private LinearLayout loadingLayout;
    private String name;
    private JSONObject params;
    private String promotionDetail;
    private TextView searchCateText;
    private String searchWay;
    private RadioGroup sortButtonGroup;
    private RadioButton sortButtonLeft;
    private RadioButton sortButtonMiddle;
    private RadioButton sortButtonRight;
    private int sortKey;
    private int sortPrice;
    private ListView wareInfoList;


























}
