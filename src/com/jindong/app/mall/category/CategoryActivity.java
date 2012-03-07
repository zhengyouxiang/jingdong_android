// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Catelogy;
import com.jindong.app.mall.product.ProductListActivity;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

public class CategoryActivity extends MyActivity
{

    public CategoryActivity()
    {
        cId = "0";
        cName = null;
        cLevel = 0;
        jsonArray = null;
        catelog_list = null;
        parentList = new LinkedList();
    }

    private void getCategoryList(String s, Integer integer)
    {
        categoryText = (TextView)findViewById(0x7f0c02c7);
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                jsonArray = httpresponse.getJSONObject().getJSONArray("catelogyList");
                if(Log.D)
                    Log.d("CategoryActivity", (new StringBuilder("jsonArray:")).append(jsonArray.toString()).toString());
                if(jsonArray != null) goto _L2; else goto _L1
_L1:
                if(Log.D)
                    Log.d("CategoryActivity", (new StringBuilder("jsonArray:")).append(jsonArray.toString()).toString());
_L4:
                if(Log.D)
                    Log.d("CategoryActivity", "onEnd----------");
                return;
_L2:
                try
                {
                    ArrayList arraylist = Catelogy.toList(jsonArray, 0);
                    CategoryActivity categoryactivity = CategoryActivity.this;
                    String as[] = new String[1];
                    as[0] = "name";
                    int ai[] = new int[1];
                    ai[0] = 0x7f0c0054;
                    final MySimpleAdapter adapter = new MySimpleAdapter(categoryactivity, arraylist, 0x7f030013, as, ai) {

                        public View getView(int i, View view, ViewGroup viewgroup)
                        {
                            View view1 = super.getView(i, view, viewgroup);
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
                    catelog_list = (ListView)findViewById(0x7f0c0052);
                    catelog_list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView adapterview, View view, int i, long l)
                        {
                            try
                            {
                                if(Log.D)
                                    Log.d("CategoryActivity", (new StringBuilder("onItemClick:position")).append(i).toString());
                                if(Log.D)
                                    Log.d("CategoryActivity", (new StringBuilder("this.cLevel=")).append(cLevel).toString());
                                saveParent();
                                JSONObject jsonobject = (JSONObject)jsonArray.get(i);
                                cId = jsonobject.getString("cid");
                                cName = jsonobject.getString("name");
                                CategoryActivity categoryactivity1 = _fld0;
                                categoryactivity1.cLevel = 1 + categoryactivity1.cLevel;
                                if(cLevel < 3)
                                {
                                    if(Log.D)
                                        Log.d("temp", "removeAll");
                                    deleteALL();
                                    getCategoryList(cId, Integer.valueOf(cLevel));
                                } else
                                {
                                    if(Log.D)
                                        Log.d("CategoryActivity", (new StringBuilder("cLevel=")).append(cLevel).toString());
                                    CategoryActivity categoryactivity2 = _fld0;
                                    categoryactivity2.cLevel = categoryactivity2.cLevel - 1;
                                    if(Log.D)
                                        Log.d("CategoryActivity", (new StringBuilder("this.cLevel=")).append(cLevel).toString());
                                    if(Log.D)
                                        Log.d("CategoryActivity", "goto--ProductListActivity");
                                    Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductListActivity);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", cName);
                                    if(Log.D)
                                        Log.d("CategoryActivity", (new StringBuilder("Level-1:")).append(getParent(2)).toString());
                                    if(Log.D)
                                        Log.d("CategoryActivity", (new StringBuilder("Level-2:")).append(getParent(3)).toString());
                                    bundle.putString("levelFirst", (String)getParent(2).get("fId"));
                                    bundle.putString("levelSecond", (String)getParent(3).get("fId"));
                                    bundle.putString("cid", cId);
                                    intent.putExtras(bundle);
                                    startActivityInFrame(intent);
                                }
                            }
                            catch(JSONException jsonexception1)
                            {
                                jsonexception1.printStackTrace();
                            }
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            if(Log.D)
                                Log.d("CategoryActivity", "set---adapter");
                            catelog_list.setAdapter(adapter);
                            if(Log.D)
                                Log.d("Temp", (new StringBuilder("catelog_list.getCount() -->> ")).append(catelog_list.getCount()).toString());
                            if(cLevel < 1)
                                categoryText.setText(0x7f090041);
                            else
                                categoryText.setText(cName);
                        }

                        final _cls1 this$1;
                        private final ListAdapter val$adapter;

                    
                    {
                        this$1 = _cls1.this;
                        adapter = listadapter;
                        super();
                    }
                    }
);
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("CategoryActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("CategoryActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("CategoryActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("CategoryActivity", "getCategoryList()-start");
            }

            final CategoryActivity this$0;


            
            {
                this$0 = CategoryActivity.this;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("catelogy");
        httpsetting.putJsonParam("catelogyId", s);
        httpsetting.putJsonParam("level", String.valueOf(integer));
        httpsetting.setListener(onalllistener);
        httpsetting.setLocalFileCache(true);
        httpsetting.setLocalFileCacheTime(0x36ee80L);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    public boolean deleteALL()
    {
        ArrayList arraylist = new ArrayList();
        String as[] = new String[1];
        as[0] = "";
        int ai[] = new int[1];
        ai[0] = 0x7f0c0054;
        post(new Runnable() {

            public void run()
            {
                catelog_list.setAdapter(adapter2);
                categoryText.setText("");
            }

            final CategoryActivity this$0;
            private final ListAdapter val$adapter2;

            
            {
                this$0 = CategoryActivity.this;
                adapter2 = listadapter;
                super();
            }
        }
);
        return true;
    }

    public Map getParent(int i)
    {
        return (Map)parentList.get(i - 1);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030012);
        if(Log.D)
            Log.d("CategoryActivityLife", "onCreate----------");
        getCategoryList("0", Integer.valueOf(0));
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR tableswitch 4 4: default 20
    //                   4 31;
           goto _L1 _L2
_L1:
        boolean flag = super.onKeyDown(i, keyevent);
_L4:
        return flag;
_L2:
        if(Log.D)
            Log.d("CategoryActivity", "onBackPressed-----------");
        if(Log.D)
            Log.d("CategoryActivity", (new StringBuilder("Level=")).append(cLevel).toString());
        if(cLevel == 0)
            continue; /* Loop/switch isn't completed */
        cId = (String)getParent(cLevel).get("fId");
        cName = (String)getParent(cLevel).get("fName");
        cLevel = cLevel - 1;
        if(Log.D)
            Log.d("CategoryActivity", (new StringBuilder("cLevel=")).append(cLevel).toString());
        if(Log.D)
            Log.d("temp", "removeAll");
        deleteALL();
        getCategoryList(cId, Integer.valueOf(cLevel));
        if(cLevel < 1)
            categoryText.setText(0x7f090041);
        else
            categoryText.setText(cName);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void saveParent()
    {
        if(Log.D)
            Log.d("CategoryActivity", "saveParent()--------------");
        HashMap hashmap = new HashMap();
        hashmap.put("fId", cId);
        hashmap.put("fName", cName);
        parentList.add(cLevel, hashmap);
        if(Log.D)
            Log.d("CategoryActivity", (new StringBuilder("parentList:")).append(parentList.toString()).toString());
    }

    private String cId;
    private int cLevel;
    private String cName;
    private TextView categoryText;
    private ListView catelog_list;
    private JSONArrayPoxy jsonArray;
    private LinkedList parentList;












}
