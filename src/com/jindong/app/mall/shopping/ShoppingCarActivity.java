// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.content.*;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.category.CategoryActivity;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.home.HomeActivity;
import com.jindong.app.mall.login.LoginActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import java.text.DecimalFormat;
import java.util.*;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.shopping:
//            FillOrderActivity

public class ShoppingCarActivity extends MyActivity
{
    private class updateTask extends AsyncTask
        implements com.jindong.app.mall.utils.HttpGroup.OnAllListener
    {

        protected transient Object doInBackground(Object aobj[])
        {
            deleteCartItem(index, prod.getId());
            hs = null;
            hs = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            hs.setPost(true);
            hs.setFunctionId(functionId);
            hs.setJsonParams(param);
            hs.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    jbResult = new JSONObjectProxy(httpresponse.getJSONObject().getJSONObject("cartInfo"));
_L1:
                    return;
                    JSONException jsonexception;
                    jsonexception;
                    jsonexception.printStackTrace();
                      goto _L1
                }

                public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                {
                    if(Log.D)
                        Log.d("ShoppingCarActivity-update", (new StringBuilder("error -->> ")).append(httperror).toString());
                }

                public void onProgress(int i, int j)
                {
                    post(new Runnable() {

                        public void run()
                        {
                            mCalAccount.setEnabled(false);
                            mTotalPrice.setText(0x7f090150);
                        }

                        final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super();
                        }
                    }
);
                    if(Log.D)
                        Log.d("ShoppingCarActivity-update", (new StringBuilder("max -->> ")).append(i).toString());
                    if(Log.D)
                        Log.d("ShoppingCarActivity-update", (new StringBuilder("progress -->> ")).append(j).toString());
                }

                public void onStart()
                {
                    if(Log.D)
                        Log.d("ShoppingCarActivity-update", "setUpConnAndGetData()-start");
                }

                final updateTask this$1;


                
                {
                    this$1 = updateTask.this;
                    super();
                }
            }
);
            getHttpGroupaAsynPool().add(hs);
            return jbResult;
        }

        public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
        {
            try
            {
                jbResult = new JSONObjectProxy(httpresponse.getJSONObject().getJSONObject("cartInfo"));
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
            onPostExecute(jbResult);
        }

        public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
        {
        }

        protected void onPostExecute(Object obj)
        {
            JSONArrayPoxy jsonarraypoxy;
            jbResult = (JSONObjectProxy)obj;
            jsonSkusInfo = jbResult.getJSONArrayOrNull("Skus");
            jsonarraypoxy = jbResult.getJSONArrayOrNull("Suits");
            new JSONArrayPoxy();
            if(jsonarraypoxy == null) goto _L2; else goto _L1
_L1:
            int i = 0;
_L7:
            if(i < jsonarraypoxy.length()) goto _L3; else goto _L2
_L2:
            int j;
            JSONArrayPoxy jsonarraypoxy1;
            if(jsonSkusInfo == null && jsonCartInfoContainer.getJSONArrayOrNull("Gifts") == null)
            {
                setEmptyView();
                if(cartItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(ShoppingCarActivity.this);
                    dbCart.delAllCart();
                    cartItem.clear();
                    cartItem = null;
                    cartItem = new ArrayList();
                }
            } else
            {
                getGifts();
                updateCartList();
            }
            jbResult = null;
              goto _L4
_L3:
            jsonarraypoxy1 = jsonarraypoxy.getJSONObject(i).getJSONArrayOrNull("Skus");
            if(jsonarraypoxy1 == null)
                break MISSING_BLOCK_LABEL_292;
            if(jsonSkusInfo == null)
                jsonSkusInfo = new JSONArrayPoxy();
              goto _L5
_L6:
            for(; j < jsonarraypoxy1.length(); j++)
                jsonSkusInfo.put(jsonarraypoxy1.getJSONObject(j));

            break MISSING_BLOCK_LABEL_292;
            JSONException jsonexception;
            jsonexception;
            jsonexception.printStackTrace();
_L4:
            return;
_L5:
            j = 0;
              goto _L6
            i++;
              goto _L7
        }

        public void onProgress(int i, int j)
        {
        }

        public void onStart()
        {
        }

        String fuctionId;
        int index;
        JSONObjectProxy jbResult;
        JSONObject param;
        Product prod;
        final ShoppingCarActivity this$0;


        public updateTask(String s, JSONObject jsonobject, Product product, int i)
        {
            this$0 = ShoppingCarActivity.this;
            super();
            jbResult = new JSONObjectProxy();
            fuctionId = s;
            param = jsonobject;
            index = i;
            prod = product;
        }
    }


    public ShoppingCarActivity()
    {
        cartItem = null;
        packsItem = null;
        functionId = "cart";
        hasGifts = false;
        count = 3;
        bUpdate = false;
        bDeleted = false;
    }

    private ArrayList CountDownSort(ArrayList arraylist)
    {
        ArrayList arraylist1 = new ArrayList();
        if(arraylist != null && (arraylist.size() <= 0 || arraylist.size() >= 1)) goto _L2; else goto _L1
_L1:
        ArrayList arraylist2 = arraylist;
_L4:
        return arraylist2;
_L2:
        int i = arraylist.size() - 1;
        do
        {
label0:
            {
                if(i >= 0)
                    break label0;
                arraylist2 = arraylist1;
            }
            if(true)
                continue;
            arraylist1.add((Product)arraylist.get(i));
            i--;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void EnableEditList()
    {
        int i = 0;
_L2:
        ViewGroup viewgroup;
        if(i >= wareInfoList.getCount())
            return;
        viewgroup = (ViewGroup)wareInfoList.getChildAt(i);
        if(viewgroup != null)
            break; /* Loop/switch isn't completed */
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        EditText edittext = (EditText)viewgroup.getChildAt(4);
        if(edittext != null)
            edittext.setEnabled(true);
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private void EnpackEditList()
    {
        Iterator iterator = packLayoutGp.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            View view = (View)iterator.next();
            EditText edittext = (EditText)view.findViewById(0x7f0c020f);
            TextView textview = (TextView)view.findViewById(0x7f0c0210);
            Button button = (Button)view.findViewById(0x7f0c0212);
            edittext.setVisibility(0);
            textview.setVisibility(8);
            edittext.setEnabled(true);
            edittext.setClickable(true);
            button.setEnabled(true);
            button.setVisibility(0);
        } while(true);
    }

    private void UnpackEditList()
    {
        Iterator iterator = packLayoutGp.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            View view = (View)iterator.next();
            EditText edittext = (EditText)view.findViewById(0x7f0c020f);
            TextView textview = (TextView)view.findViewById(0x7f0c0210);
            Button button = (Button)view.findViewById(0x7f0c0212);
            edittext.setEnabled(false);
            edittext.setVisibility(8);
            textview.setVisibility(0);
            edittext.setClickable(false);
            button.setEnabled(false);
            button.setVisibility(8);
        } while(true);
    }

    private void addEditView(View view, int i, final JSONObjectProxy tp, final int index)
    {
        final EditText mCount = (EditText)view.findViewById(0x7f0c020f);
        TextView textview = (TextView)view.findViewById(0x7f0c01ff);
        TextView textview1 = (TextView)view.findViewById(0x7f0c0210);
        Button button = (Button)view.findViewById(0x7f0c0212);
        mCount.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int j, int k, int l)
            {
                temp = charsequence;
            }

            public void onTextChanged(CharSequence charsequence, int j, int k, int l)
            {
                selectionStart = mCount.getSelectionStart();
                selectionEnd = mCount.getSelectionEnd();
                if(Log.I)
                    Log.i("gongbiao1", (new StringBuilder()).append(selectionStart).toString());
                if(temp.length() > 4)
                {
                    Toast.makeText(ShoppingCarActivity.this, 0x7f09017b, 0).show();
                    ((Editable)charsequence).delete(selectionStart - 1, selectionEnd);
                    int i1 = selectionStart;
                    mCount.setText(charsequence);
                    mCount.setSelection(i1);
                }
            }

            private int selectionEnd;
            private int selectionStart;
            private CharSequence temp;
            final ShoppingCarActivity this$0;
            private final EditText val$mCount;

            
            {
                this$0 = ShoppingCarActivity.this;
                mCount = edittext;
                super();
            }
        }
);
        if(bModified)
            button.setVisibility(0);
        else
            button.setVisibility(8);
        button.setText(0x7f09017e);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view1)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ShoppingCarActivity.this);
                builder.setTitle("\u5220\u9664\u786E\u8BA4");
                builder.setMessage("\u60A8\u786E\u5B9A\u8981\u5220\u9664\u8FD9\u4E2A\u4F18\u60E0\u5957\u88C5\u5417\uFF1F");
                builder.setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        if(((InputMethodManager)getSystemService("input_method")).isActive() && getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
                            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
                        (new Thread() {

                            public void run()
                            {
                                super.run();
                                int k = index;
                                if(cartItem != null && cartItem.size() > 0 && k >= packsItem.size())
                                    k -= cartItem.size();
                                updatePackItemData();
                                try
                                {
                                    Thread.sleep(50L);
                                }
                                catch(InterruptedException interruptedexception)
                                {
                                    interruptedexception.printStackTrace();
                                }
                                try
                                {
                                    deletePackItem(k, Long.valueOf(tp.getLong("Id")));
                                    packLayoutGp.remove(k);
                                }
                                catch(JSONException jsonexception)
                                {
                                    jsonexception.printStackTrace();
                                }
                                updateCartItem();
                                post(new Runnable() {

                                    public void run()
                                    {
                                        mDone.setEnabled(true);
                                    }

                                    final _cls1 this$3;

                            
                            {
                                this$3 = _cls1.this;
                                super();
                            }
                                }
);
                            }

                            final _cls1 this$2;
                            private final int val$index;
                            private final JSONObjectProxy val$tp;


                        
                        {
                            this$2 = _cls1.this;
                            index = i;
                            tp = jsonobjectproxy;
                            super();
                        }
                        }
).start();
                        dialoginterface.dismiss();
                    }

                    final _cls7 this$1;
                    private final int val$index;
                    private final JSONObjectProxy val$tp;


                    
                    {
                        this$1 = _cls7.this;
                        index = i;
                        tp = jsonobjectproxy;
                        super();
                    }
                }
);
                builder.setNeutralButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        mDone.setEnabled(true);
                        dialoginterface.dismiss();
                    }

                    final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                }
);
                builder.show();
            }

            final ShoppingCarActivity this$0;
            private final int val$index;
            private final JSONObjectProxy val$tp;


            
            {
                this$0 = ShoppingCarActivity.this;
                index = i;
                tp = jsonobjectproxy;
                super();
            }
        }
);
        if(String.valueOf(i).length() < 1)
        {
            mCount.setText("1");
            textview1.setText("1");
        } else
        {
            mCount.setText(String.valueOf(i));
            textview1.setText(String.valueOf(i));
        }
        if(bModified)
        {
            textview1.setVisibility(8);
            mCount.setVisibility(0);
            mCount.setEnabled(true);
            mCount.setClickable(true);
        } else
        {
            textview1.setVisibility(0);
            mCount.setVisibility(8);
            mCount.setEnabled(false);
            mCount.setClickable(false);
        }
        if(tp.getStringOrNull("PriceShow") != null)
        {
            textview.setText(tp.getStringOrNull("PriceShow"));
        } else
        {
            float f = Float.valueOf(tp.getStringOrNull("Price")).floatValue();
            float f1 = Float.valueOf(tp.getStringOrNull("Discount")).floatValue();
            String s = (new DecimalFormat("0.00")).format(f - f1);
            textview.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s).toString());
        }
    }

    private void addManZengView(JSONObjectProxy jsonobjectproxy, int i, View view)
    {
        TextView textview = (TextView)view.findViewById(0x7f0c02c0);
        TextView textview1 = (TextView)view.findViewById(0x7f0c02c4);
        TextView textview2 = (TextView)view.findViewById(0x7f0c0211);
        EditText edittext = (EditText)view.findViewById(0x7f0c02c2);
        Button button = (Button)view.findViewById(0x7f0c02c5);
        float f;
        if(jsonobjectproxy.getStringOrNull("Name") != null)
            try
            {
                textview.setText(jsonobjectproxy.getString("Name"));
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        else
            textview.setText(" ");
        if(jsonobjectproxy.getIntOrNull("Num") != null)
            try
            {
                edittext.setText(String.valueOf(jsonobjectproxy.getIntOrNull("Num")));
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        else
            edittext.setText(" ");
        if(bModified)
        {
            edittext.setEnabled(true);
            button.setEnabled(true);
            button.setVisibility(0);
        } else
        {
            edittext.setEnabled(false);
            button.setEnabled(false);
            button.setVisibility(4);
        }
        if(jsonobjectproxy.getLongOrNull("Id") != null)
            textview2.setText(String.valueOf(jsonobjectproxy.getLongOrNull("Id")));
        else
            textview2.setText(" ");
        f = Float.valueOf(jsonobjectproxy.getStringOrNull("Price")).floatValue();
        if(f - 0.0F <= 0.0F)
        {
            textview1.setText("-");
        } else
        {
            float f1 = Float.valueOf(jsonobjectproxy.getStringOrNull("Discount")).floatValue();
            String s = (new DecimalFormat("0.00")).format(f - f1);
            textview1.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s).toString());
        }
    }

    private void addPackTitleView(View view, JSONObjectProxy jsonobjectproxy, int i)
    {
        TextView textview = (TextView)view.findViewById(0x7f0c01f6);
        ImageView imageview = (ImageView)view.findViewById(0x7f0c01f8);
        if(jsonobjectproxy.getStringOrNull("Name") != null)
            textview.setText((new StringBuilder("\u3010\u4F18\u60E0\u3011")).append(jsonobjectproxy.getStringOrNull("Name")).toString());
        else
            textview.setText("\u4F18\u60E0\u5957\u88C5");
        if(jsonobjectproxy.toString().contains("Gifts") && jsonobjectproxy.getJSONArrayOrNull("Gifts") != null)
            imageview.setImageResource(0x7f020043);
    }

    private void addPackView(JSONObjectProxy jsonobjectproxy, int i, View view)
    {
        TextView textview = (TextView)view.findViewById(0x7f0c0204);
        TextView textview1 = (TextView)view.findViewById(0x7f0c0205);
        float f;
        if(jsonobjectproxy.getStringOrNull("Name") != null)
            try
            {
                textview.setText(jsonobjectproxy.getString("Name"));
            }
            catch(JSONException jsonexception)
            {
                jsonexception.printStackTrace();
            }
        else
            textview.setText(" ");
        f = Float.valueOf(jsonobjectproxy.getStringOrNull("Price")).floatValue();
        if(f - 0.0F <= 0.0F)
        {
            textview1.setText("-");
        } else
        {
            float f1 = Float.valueOf(jsonobjectproxy.getStringOrNull("Discount")).floatValue();
            String s = (new DecimalFormat("0.00")).format(f - f1);
            textview1.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s).toString());
        }
    }

    private void clearDB()
    {
        dbCart = new DBHelperUtil(this);
        dbCart.delAllCart();
    }

    private void clearPackDb()
    {
        dbCart = new DBHelperUtil(this);
        dbCart.delAllPacksCart();
    }

    private void compontSkuInfo()
    {
        if(!hasGifts || hasZengArray == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L5:
        if(i < jsonGiftsInfo.length()) goto _L3; else goto _L2
_L2:
        return;
_L3:
        try
        {
            JSONObjectProxy jsonobjectproxy = jsonGiftsInfo.getJSONObject(i).getJSONObject("MainSku");
            jsonobjectproxy.put("Num", jsonGiftsInfo.getJSONObject(i).getInt("Num"));
            if(jsonSkusInfo == null)
                jsonSkusInfo = new JSONArrayPoxy();
            jsonSkusInfo.put(jsonobjectproxy);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        i++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void deleteCartItem(int i, Long long1)
    {
        if(i >= 0 && cartItem.size() >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(hasZengArray == null || hasZengArray.size() <= 0 || !isInArray(long1)) goto _L4; else goto _L3
_L3:
        int k = 0;
_L8:
        if(k < hasZengArray.size()) goto _L6; else goto _L5
_L5:
        if(hasZengArray == null || hasZengArray.size() <= 0)
        {
            hasZengArray = null;
            hasGifts = false;
        }
_L4:
        if(cartItem.size() >= 2)
            break; /* Loop/switch isn't completed */
        cartItem.clear();
        cartItem = null;
        cartItem = new ArrayList();
        clearDB();
        continue; /* Loop/switch isn't completed */
_L6:
        if(((Long)hasZengArray.get(k)).equals(long1))
            hasZengArray.remove(k);
        k++;
        if(true) goto _L8; else goto _L7
_L7:
        if(i < cartItem.size() && i >= 0)
        {
            if(((CartTable)cartItem.get(i)).productCode != long1.longValue())
                break; /* Loop/switch isn't completed */
            cartItem.remove(i);
        }
_L10:
        updateCartDB();
        if(true) goto _L1; else goto _L9
_L9:
        int j = 0;
_L11:
        if(j < cartItem.size())
        {
label0:
            {
                if(((CartTable)cartItem.get(j)).productCode != long1.longValue())
                    break label0;
                cartItem.remove(j);
            }
        }
          goto _L10
        j++;
          goto _L11
    }

    private void deletePackItem(int i, Long long1)
    {
        if(i >= 0 && packsItem.size() >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(packsItem.size() < 2)
        {
            packsItem.clear();
            clearPackDb();
            continue; /* Loop/switch isn't completed */
        }
        if(i < packsItem.size() && i >= 0)
        {
            if(((PacksTable)packsItem.get(i)).packId != long1.longValue())
                break; /* Loop/switch isn't completed */
            packsItem.remove(i);
        }
_L4:
        updatePacksDB();
        if(true) goto _L1; else goto _L3
_L3:
        int j = 0;
_L5:
        if(j < packsItem.size())
        {
label0:
            {
                if(((PacksTable)packsItem.get(j)).packId != long1.longValue())
                    break label0;
                packsItem.remove(j);
            }
        }
          goto _L4
        j++;
          goto _L5
    }

    private void fillPackCountArray()
    {
        int i = 0;
        do
        {
            if(i >= packsItem.size())
                return;
            i++;
        } while(true);
    }

    private boolean getCartItem()
    {
        cartItem = null;
        cartItem = new ArrayList();
        dbCart = new DBHelperUtil(this);
        cartItem = dbCart.getCartList();
        boolean flag;
        if(cartItem.isEmpty() || cartItem == null)
        {
            cartItem.clear();
            cartItem = null;
            cartItem = new ArrayList();
            flag = false;
        } else
        {
            setUpCart();
            flag = true;
        }
        return flag;
    }

    private void getGifts()
    {
        if(jsonCartInfoContainer.length() <= 0)
            break MISSING_BLOCK_LABEL_81;
        if(jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null) goto _L2; else goto _L1
_L1:
        hasGifts = false;
_L3:
        return;
        Exception exception;
        exception;
        hasGifts = false;
        exception.printStackTrace();
          goto _L3
_L2:
        try
        {
            jsonGiftsInfo = jsonCartInfoContainer.getJSONArray("Gifts");
            handleGifts();
            hasGifts = true;
        }
        catch(JSONException jsonexception)
        {
            hasGifts = false;
            jsonexception.printStackTrace();
        }
          goto _L3
        hasGifts = false;
          goto _L3
    }

    private boolean getPackItem()
    {
        packsItem = null;
        packsItem = new ArrayList();
        dbCart = new DBHelperUtil(this);
        packsItem = dbCart.getPacksList();
        boolean flag;
        if(packsItem.isEmpty() || packsItem == null)
        {
            packsItem.clear();
            flag = false;
        } else
        {
            setUpPacks();
            flag = true;
        }
        return flag;
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
        mCalAccount.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(LoginUser.hasLogin())
                {
                    Contants.bAddEasyBuy = false;
                    Contants.bEasyBuy = false;
                    Contants.bModifyEasyBuy = false;
                    Intent intent1 = new Intent(ShoppingCarActivity.this, com/jindong/app/mall/shopping/FillOrderActivity);
                    startTaskActivityInFrame(intent1);
                } else
                {
                    Intent intent = new Intent(ShoppingCarActivity.this, com/jindong/app/mall/login/LoginActivity);
                    startActivityInFrame(intent);
                    Toast.makeText(ShoppingCarActivity.this, getResources().getString(0x7f090151), 0).show();
                }
                view.setPressed(false);
            }

            final ShoppingCarActivity this$0;

            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
        mDone.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(!bModified) goto _L2; else goto _L1
_L1:
                if(getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null && ((InputMethodManager)getSystemService("input_method")).isActive())
                    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
                if(!tooManyProd()) goto _L4; else goto _L3
_L3:
                return;
_L4:
                updateCartItemData();
                updatePackItemData();
                updateCartItem();
                bModified = false;
                mDone.setText(0x7f09014f);
                mCalAccount.setEnabled(true);
                mCalAccount.setTextColor(0xff000000);
                if(adapter != null)
                    adapter.notifyDataSetChanged();
                if(packLayoutGp != null && packLayoutGp.size() > 0)
                    UnpackEditList();
                continue; /* Loop/switch isn't completed */
_L2:
                mDone.setText(0x7f090177);
                mCalAccount.setEnabled(false);
                mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                bModified = true;
                if(adapter != null)
                    adapter.notifyDataSetChanged();
                if(packLayoutGp != null && packLayoutGp.size() > 0)
                    EnpackEditList();
                if(true) goto _L3; else goto _L5
_L5:
            }

            final ShoppingCarActivity this$0;

            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
    }

    private void handleGifts()
    {
        int i;
        hasZengArray = new ArrayList();
        if(jsonGiftsInfo.length() <= 0)
            break MISSING_BLOCK_LABEL_77;
        i = 0;
_L3:
        if(i < jsonGiftsInfo.length()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        try
        {
            hasZengArray.add(jsonGiftsInfo.getJSONObject(i).getJSONObject("MainSku").getLongOrNull("Id"));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        i++;
          goto _L3
        hasZengArray = null;
          goto _L1
    }

    private void initComponent()
    {
        mTotalPrice = (TextView)findViewById(0x7f0c02bf);
        mOrignalPrice = (TextView)findViewById(0x7f0c02bd);
        mFanXian = (TextView)findViewById(0x7f0c00bb);
        mCalAccount = (Button)findViewById(0x7f0c00ca);
        mCalAccount.setEnabled(false);
        mCalAccount.setTextColor(Color.rgb(153, 153, 153));
        mTotalPrice.setText(0x7f09007b);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f09013b);
        mDone = (Button)findViewById(0x7f0c02c8);
        mDone.setText(0x7f09014f);
        mDone.setVisibility(0);
        ((LinearLayout)findViewById(0x7f0c02bb)).setVisibility(8);
    }

    private boolean isInArray(Long long1)
    {
        int i = 0;
_L6:
        if(i < hasZengArray.size()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        if(!((Long)hasZengArray.get(i)).equals(long1))
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean isManZeng(Long long1)
    {
        if(jsonManZengInfo != null && jsonManZengInfo.length() > 0) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L3:
        return flag;
_L2:
        int i = 0;
_L4:
label0:
        {
            if(i < jsonManZengInfo.length())
                break label0;
            flag = false;
        }
          goto _L3
        int j = jsonManZengInfo.getJSONObjectOrNull(i).getString("Id").trim().compareTo(String.valueOf(long1));
        if(j != 0)
            break MISSING_BLOCK_LABEL_82;
        flag = true;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
        i++;
          goto _L4
    }

    private void resetCartItemArray()
    {
        if(cartItem.size() >= 1 && wareInfoList.getCount() >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        cartItem.clear();
        cartItem = null;
        cartItem = new ArrayList();
        CartTable carttable = new CartTable();
        int i = 0;
        do
        {
label0:
            {
                if(i < wareInfoList.getCount())
                    break label0;
                updateCartDB();
            }
            if(true)
                continue;
            ViewGroup viewgroup = (ViewGroup)wareInfoList.getChildAt(i);
            if(viewgroup != null)
            {
                EditText edittext = (EditText)viewgroup.getChildAt(4);
                if(edittext != null)
                {
                    Product product = (Product)wareInfoList.getAdapter().getItem(i);
                    carttable.buyCount = product.geItemCount();
                    carttable.productCode = product.getId().longValue();
                    carttable.name = product.getName();
                    if(edittext.getText().toString() == null || edittext.getText().toString() == "" || edittext.getText().toString() == " " || edittext.getText().toString().length() < 1 || Integer.valueOf(edittext.getText().toString()).intValue() == 0)
                        carttable.buyCount = 1;
                    else
                        carttable.buyCount = Integer.valueOf(edittext.getText().toString()).intValue();
                    cartItem.add(carttable);
                }
            }
            i++;
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private void setCartList()
    {
        final int totlacount;
        totlacount = 0;
        compontSkuInfo();
        if(jsonSkusInfo != null && checkSkus(jsonSkusInfo)) goto _L2; else goto _L1
_L1:
        setEmptyView();
        if(cartItem.size() > 0)
        {
            if(dbCart == null)
                dbCart = new DBHelperUtil(this);
            dbCart.delAllCart();
            cartItem.clear();
            cartItem = null;
            cartItem = new ArrayList();
        }
_L5:
        return;
_L2:
        final ArrayList listTemp;
        list = Product.toList(jsonSkusInfo, 9);
        listTemp = CountDownSort(list);
        if(cartItem.size() <= 0) goto _L4; else goto _L3
_L3:
        int i = 0;
_L6:
        if(i < cartItem.size())
            break MISSING_BLOCK_LABEL_162;
_L4:
        post(new Runnable() {

            public void run()
            {
                if(totlacount > 1000)
                {
                    bModified = true;
                    mDone.setText(getApplicationContext().getString(0x7f090177));
                    mCalAccount.setEnabled(false);
                    mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                    Toast.makeText(ShoppingCarActivity.this, getResources().getString(0x7f090143), 0);
                }
                adapter = null;
                ShoppingCarActivity shoppingcaractivity = ShoppingCarActivity.this;
                ShoppingCarActivity shoppingcaractivity1 = ShoppingCarActivity.this;
                ArrayList arraylist = listTemp;
                String as[] = new String[1];
                as[0] = "Price";
                int ai[] = new int[1];
                ai[0] = 0x7f0c02c4;
                shoppingcaractivity.adapter = new MySimpleAdapter(shoppingcaractivity1, arraylist, 0x7f030087, as, ai) {

                    public View getView(final int index, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(index, view, viewgroup);
                        final Product product = (Product)getItem(index);
                        TextView textview;
                        TextView textview1;
                        ProductShow productshow;
                        Button button;
                        android.view.View.OnClickListener onclicklistener;
                        final EditText productCount;
                        android.view.View.OnClickListener onclicklistener1;
                        android.view.View.OnLongClickListener onlongclicklistener;
                        TextWatcher textwatcher;
                        TextView textview2;
                        float f;
                        String s;
                        float f1;
                        String s1;
                        if(isManZeng(product.getId()))
                            view1.setBackgroundResource(0x7f020098);
                        else
                        if(index % 2 == 1)
                            view1.setBackgroundResource(0x7f0200a2);
                        else
                            view1.setBackgroundResource(0x7f0200a3);
                        textview = (TextView)view1.findViewById(0x7f0c02c0);
                        textview1 = (TextView)view1.findViewById(0x7f0c0211);
                        productshow = new ProductShow(_fld0, product);
                        if(hasGifts && hasZengArray != null && isInArray(product.getId()))
                            textview.setText(productshow.getNameAndZeng());
                        else
                            textview.setText(product.getName());
                        textview1.setText(product.getId().toString());
                        button = (Button)view1.findViewById(0x7f0c02c5);
                        if(bModified)
                            button.setVisibility(0);
                        else
                            button.setVisibility(8);
                        button.setText(0x7f09017e);
                        onclicklistener = new android.view.View.OnClickListener() {

                            public void onClick(View view2)
                            {
                                bUpdate = true;
                                mDone.setEnabled(false);
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(_fld0);
                                builder.setTitle("\u5220\u9664\u786E\u8BA4");
                                builder.setMessage("\u60A8\u786E\u5B9A\u8981\u5220\u9664\u8FD9\u4E2A\u5546\u54C1\u5417\uFF1F");
                                builder.setPositiveButton("\u786E\u5B9A", new android.content.DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialoginterface, int j)
                                    {
                                        if(((InputMethodManager)getSystemService("input_method")).isActive() && getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
                                            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
                                        (new Thread() {

                                            public void run()
                                            {
                                                super.run();
                                                updateCartItemData();
                                                deleteCartItem(index, product.getId());
                                                updateCartItem();
                                                post(new Runnable() {

                                                    public void run()
                                                    {
                                                        mDone.setEnabled(true);
                                                    }

                                                    final _cls1 this$5;

                                    
                                    {
                                        this$5 = _cls1.this;
                                        super();
                                    }
                                                }
);
                                            }

                                            final _cls1 this$4;
                                            private final int val$index;
                                            private final Product val$product;


                                
                                {
                                    this$4 = _cls1.this;
                                    index = i;
                                    product = product1;
                                    super();
                                }
                                        }
).start();
                                        dialoginterface.dismiss();
                                    }

                                    final _cls1 this$3;
                                    private final int val$index;
                                    private final Product val$product;


                            
                            {
                                this$3 = _cls1.this;
                                index = i;
                                product = product1;
                                super();
                            }
                                }
);
                                builder.setNeutralButton("\u53D6\u6D88", new android.content.DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialoginterface, int j)
                                    {
                                        mDone.setEnabled(true);
                                        dialoginterface.dismiss();
                                    }

                                    final _cls1 this$3;

                            
                            {
                                this$3 = _cls1.this;
                                super();
                            }
                                }
);
                                builder.show();
_L1:
                                return;
                                Exception exception;
                                exception;
                                exception.printStackTrace();
                                  goto _L1
                            }

                            final _cls1 this$2;
                            private final int val$index;
                            private final Product val$product;


                        
                        {
                            this$2 = _cls1.this;
                            index = i;
                            product = product1;
                            super();
                        }
                        }
;
                        button.setOnClickListener(onclicklistener);
                        productCount = (EditText)view1.findViewById(0x7f0c02c2);
                        onclicklistener1 = new android.view.View.OnClickListener() {

                            public void onClick(View view2)
                            {
                                if(!bModified)
                                {
                                    if(Log.D)
                                        Log.d("Click", "click item");
                                    Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                                    Bundle bundle = new Bundle();
                                    bundle.putLong("id", product.getId().longValue());
                                    bundle.putString("title", product.getName());
                                    intent.putExtras(bundle);
                                    startActivityInFrame(intent);
                                }
                            }

                            final _cls1 this$2;
                            private final Product val$product;

                        
                        {
                            this$2 = _cls1.this;
                            product = product1;
                            super();
                        }
                        }
;
                        view1.setOnClickListener(onclicklistener1);
                        onlongclicklistener = new android.view.View.OnLongClickListener() {

                            public boolean onLongClick(View view2)
                            {
                                if(!bModified)
                                {
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(_fld0);
                                    builder.setTitle(getResources().getString(0x7f090153));
                                    String as1[] = new String[2];
                                    as1[0] = getResources().getString(0x7f09017e);
                                    as1[1] = getResources().getString(0x7f090154);
                                    builder.setItems(as1, new android.content.DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialoginterface, int j)
                                        {
label0:
                                            {
                                                {
                                                    if(j != 0)
                                                        break label0;
                                                    Intent intent;
                                                    Bundle bundle;
                                                    try
                                                    {
                                                        bUpdate = true;
                                                        bUpdate = true;
                                                        (new Thread() {

                                                            public void run()
                                                            {
                                                                super.run();
                                                                deleteCartItem(index, product.getId());
                                                                updateCartItem();
                                                            }

                                                            final _cls1 this$4;
                                                            private final int val$index;
                                                            private final Product val$product;

                                
                                {
                                    this$4 = _cls1.this;
                                    index = i;
                                    product = product1;
                                    super();
                                }
                                                        }
).start();
                                                        dialoginterface.dismiss();
                                                    }
                                                    catch(Exception exception)
                                                    {
                                                        exception.printStackTrace();
                                                    }
                                                }
                                                dialoginterface.dismiss();
                                                return;
                                            }
                                            if(j != 1)
                                                break MISSING_BLOCK_LABEL_63;
                                            if(bModified)
                                                break MISSING_BLOCK_LABEL_63;
                                            intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                                            bundle = new Bundle();
                                            bundle.putLong("id", product.getId().longValue());
                                            bundle.putString("title", product.getName());
                                            intent.putExtras(bundle);
                                            startActivityInFrame(intent);
                                            break MISSING_BLOCK_LABEL_63;
                                        }

                                        final _cls3 this$3;
                                        private final int val$index;
                                        private final Product val$product;


                            
                            {
                                this$3 = _cls3.this;
                                product = product1;
                                index = i;
                                super();
                            }
                                    }
).show();
                                }
                                return true;
                            }

                            final _cls1 this$2;
                            private final int val$index;
                            private final Product val$product;


                        
                        {
                            this$2 = _cls1.this;
                            product = product1;
                            index = i;
                            super();
                        }
                        }
;
                        view1.setOnLongClickListener(onlongclicklistener);
                        textwatcher = new TextWatcher() {

                            public void afterTextChanged(Editable editable)
                            {
                            }

                            public void beforeTextChanged(CharSequence charsequence, int j, int k, int l)
                            {
                                temp = charsequence;
                            }

                            public void onTextChanged(CharSequence charsequence, int j, int k, int l)
                            {
                                selectionStart = productCount.getSelectionStart();
                                selectionEnd = productCount.getSelectionEnd();
                                if(Log.I)
                                    Log.i("gongbiao1", (new StringBuilder()).append(selectionStart).toString());
                                if(temp.length() > 4)
                                {
                                    Toast.makeText(_fld0, 0x7f09017b, 0).show();
                                    ((Editable)charsequence).delete(selectionStart - 1, selectionEnd);
                                    int i1 = selectionStart;
                                    productCount.setText(charsequence);
                                    productCount.setSelection(i1);
                                }
                            }

                            private int selectionEnd;
                            private int selectionStart;
                            private CharSequence temp;
                            final _cls1 this$2;
                            private final EditText val$productCount;

                        
                        {
                            this$2 = _cls1.this;
                            productCount = edittext;
                            super();
                        }
                        }
;
                        productCount.addTextChangedListener(textwatcher);
                        if(String.valueOf(product.geItemCount()).length() < 1)
                            productCount.setText("1");
                        else
                            productCount.setText(String.valueOf(product.geItemCount()));
                        if(bModified)
                            productCount.setEnabled(true);
                        else
                            productCount.setEnabled(false);
                        textview2 = (TextView)view1.findViewById(0x7f0c02c4);
                        f = Float.valueOf(product.getJdPrice()).floatValue();
                        s = product.getJdDixcount();
                        if(Log.D)
                            Log.d("Temprereer", s);
                        f1 = Float.valueOf(s).floatValue();
                        s1 = (new DecimalFormat("0.00")).format(f - f1);
                        textview2.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s1).toString());
                        if(Log.D)
                            Log.d("price", product.getJdPrice().toString());
                        return view1;
                    }

                    final _cls10 this$1;


                    
                    {
                        this$1 = _cls10.this;
                        super(myactivity, list1, i, as, ai);
                    }
                }
;
                wareInfoList.setAdapter(adapter);
                Math.round(480D / (double)screenHeight);
                if(Log.D)
                    Log.d("value", String.valueOf((double)screenHeight / 480D));
                if(screenHeight >= 1024 || screenHeight <= 480 || screenWidth <= 320 || screenWidth >= 600) goto _L2; else goto _L1
_L1:
                if(wareInfoList.getCount() < 16)
                    wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 193 * wareInfoList.getCount()));
                else
                    wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 194 * wareInfoList.getCount()));
_L4:
                return;
_L2:
                if(screenHeight >= 1024 && screenWidth >= 600)
                {
                    if(wareInfoList.getCount() < 16)
                        wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 192 * wareInfoList.getCount()));
                    else
                        wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 202 * wareInfoList.getCount()));
                } else
                if(screenHeight == 480 && screenWidth == 320)
                    wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 128 * wareInfoList.getCount()));
                else
                if(screenHeight < 480 && screenWidth <= 320)
                    wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 98 * wareInfoList.getCount()));
                else
                if(screenHeight < 1024 && screenHeight > 480 && screenWidth >= 640)
                {
                    if(wareInfoList.getCount() < 16)
                        wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 259 * wareInfoList.getCount()));
                    else
                        wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 259 * wareInfoList.getCount()));
                } else
                if(screenHeight == 400 && screenWidth == 360)
                    wareInfoList.setLayoutParams(new android.widget.LinearLayout.LayoutParams(screenWidth, 193 * wareInfoList.getCount()));
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ShoppingCarActivity this$0;
            private final ArrayList val$listTemp;
            private final int val$totlacount;


            
            {
                this$0 = ShoppingCarActivity.this;
                totlacount = i;
                listTemp = arraylist;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                float f;
                float f1;
                float f2;
                String s;
                if(packsItem == null || packsItem.size() <= 0)
                    ((LinearLayout)findViewById(0x7f0c02bb)).setVisibility(8);
                else
                    ((LinearLayout)findViewById(0x7f0c02bb)).setVisibility(0);
                f = Float.valueOf(jsonCartInfoContainer.getStringOrNull("Price")).floatValue();
                f1 = Float.valueOf(jsonCartInfoContainer.getStringOrNull("Discount")).floatValue();
                f2 = Float.valueOf(jsonCartInfoContainer.getStringOrNull("RePrice")).floatValue();
                s = (new DecimalFormat("0.00")).format(f - f2 - f1);
                mOrignalPrice.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append((new DecimalFormat("0.00")).format(f - f1)).toString());
                mFanXian.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append((new DecimalFormat("0.00")).format(f2)).toString());
                if(Log.D)
                    Log.d("total", (new StringBuilder("total:")).append(wareInfoList.getCount()).toString());
                if(wareInfoList.getCount() > 50)
                {
                    mCalAccount.setEnabled(false);
                    mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                    Toast.makeText(ShoppingCarActivity.this, getApplicationContext().getString(0x7f090142), 1).show();
                    mTotalPrice.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s).toString());
                } else
                if(bModified)
                {
                    mCalAccount.setEnabled(false);
                    mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                } else
                {
                    mCalAccount.setEnabled(true);
                    mCalAccount.setTextColor(0xff000000);
                }
                mTotalPrice.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s).toString());
            }

            final ShoppingCarActivity this$0;

            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
          goto _L5
        totlacount += ((CartTable)cartItem.get(i)).buyCount;
        i++;
          goto _L6
    }

    private void setEmptyView()
    {
        Contants.skusOfSuites = null;
        post(new Runnable() {

            public void run()
            {
                LayoutInflater layoutinflater = LayoutInflater.from(ShoppingCarActivity.this);
                LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0c00c1);
                LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0c00c2);
                LinearLayout linearlayout2 = (LinearLayout)findViewById(0x7f0c02bb);
                RelativeLayout relativelayout = (RelativeLayout)layoutinflater.inflate(0x7f030028, null).findViewById(0x7f0c00ce);
                ((Button)relativelayout.getChildAt(1)).setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view)
                    {
                        if(LoginUser.hasLogin())
                        {
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/category/CategoryActivity);
                            intent.putExtra("com.360buy:navigationFlag", true);
                            intent.putExtra("com.360buy:navigationId", 1);
                            startActivityInFrame(intent);
                        } else
                        {
                            Intent intent1 = new Intent(_fld0, com/jindong/app/mall/home/HomeActivity);
                            startSingleActivityInFrame(intent1);
                        }
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                linearlayout2.removeAllViews();
                linearlayout.removeAllViews();
                linearlayout2.setVisibility(8);
                linearlayout1.setVisibility(4);
                mDone.setVisibility(4);
                bDeleted = true;
                bModified = false;
                mDone.setText(0x7f09014f);
                mCalAccount.setEnabled(true);
                mCalAccount.setTextColor(0xff000000);
                linearlayout.addView(relativelayout);
                linearlayout.setVisibility(0);
            }

            final ShoppingCarActivity this$0;


            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
    }

    private void setTextWatcher(final EditText wEditTxt, final Product pt)
    {
        post(new Runnable() {

            public void run()
            {
                wEditTxt.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable editable)
                    {
                        int i = Integer.valueOf(wEditTxt.getText().toString()).intValue();
                        if(i <= Contants.MAX_SINGLE_PROD_COUNT) goto _L2; else goto _L1
_L1:
                        Toast.makeText(_fld0, getApplicationContext().getString(0x7f090142), 1).show();
                        wEditTxt.selectAll();
_L4:
                        return;
_L2:
                        pt.setItemCount(i);
                        if(Log.D)
                            Log.d("Single Cart Item Count", String.valueOf(i));
                        if(true) goto _L4; else goto _L3
_L3:
                    }

                    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
                    {
                    }

                    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
                    {
                    }

                    final _cls12 this$1;
                    private final Product val$pt;
                    private final EditText val$wEditTxt;

                    
                    {
                        this$1 = _cls12.this;
                        wEditTxt = edittext;
                        pt = product;
                        super();
                    }
                }
);
            }

            final ShoppingCarActivity this$0;
            private final Product val$pt;
            private final EditText val$wEditTxt;


            
            {
                this$0 = ShoppingCarActivity.this;
                wEditTxt = edittext;
                pt = product;
                super();
            }
        }
);
    }

    private void setUpCart()
    {
        boolean flag = false;
        if(cartItem.size() <= 1) goto _L2; else goto _L1
_L1:
        int i = 0;
_L6:
        if(i < cartItem.size() - 1) goto _L4; else goto _L3
_L3:
        if(flag)
            updateCartDB();
_L2:
        return;
_L4:
        int j = i + 1;
        do
        {
label0:
            {
                if(j < cartItem.size())
                    break label0;
                i++;
            }
            if(true)
                continue;
            if(((CartTable)cartItem.get(i)).productCode == ((CartTable)cartItem.get(j)).productCode)
            {
                ((CartTable)cartItem.get(i)).buyCount = ((CartTable)cartItem.get(i)).buyCount + ((CartTable)cartItem.get(j)).buyCount;
                cartItem.remove(j);
                flag = true;
            }
            j++;
        } while(true);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void setUpPacks()
    {
        boolean flag = false;
        if(packsItem.size() <= 1) goto _L2; else goto _L1
_L1:
        int i = 0;
_L6:
        if(i < packsItem.size() - 1) goto _L4; else goto _L3
_L3:
        if(flag)
            updatePacksDB();
_L2:
        return;
_L4:
        int j = i + 1;
        do
        {
label0:
            {
                if(j < packsItem.size())
                    break label0;
                i++;
            }
            if(true)
                continue;
            if(((PacksTable)packsItem.get(i)).packId == ((PacksTable)packsItem.get(j)).packId)
            {
                ((PacksTable)packsItem.get(i)).buyCount = ((PacksTable)packsItem.get(i)).buyCount + ((PacksTable)packsItem.get(j)).buyCount;
                packsItem.remove(j);
                flag = true;
            }
            j++;
        } while(true);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void setViewWithItem()
    {
        mCalAccount.setEnabled(false);
        mCalAccount.setTextColor(Color.rgb(153, 153, 153));
        mTotalPrice.setText(0x7f090150);
        LayoutInflater layoutinflater = LayoutInflater.from(this);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0c00c1);
        LinearLayout linearlayout1 = (LinearLayout)layoutinflater.inflate(0x7f030088, null).findViewById(0x7f0c00c6);
        wareInfoList = (ListView)linearlayout1.getChildAt(0);
        if(bDeleted)
        {
            ((LinearLayout)findViewById(0x7f0c00c2)).setVisibility(0);
            bDeleted = false;
            mDone.setVisibility(0);
        }
        showMyCartList();
        linearlayout.removeAllViews();
        linearlayout.addView(linearlayout1);
        if(cartItem == null || cartItem.size() <= 0)
            linearlayout.setVisibility(8);
        else
            linearlayout.setVisibility(0);
        wareInfoList.setClickable(true);
    }

    private void showMyCartList()
    {
        JSONArray jsonarray;
        params = null;
        params = new JSONObject();
        jsonarray = new JSONArray();
        if(cartItem.size() <= 0 && packsItem.size() <= 0)
            break MISSING_BLOCK_LABEL_414;
        if(cartItem.size() <= 0) goto _L2; else goto _L1
_L1:
        int j = 0;
_L9:
        if(j < cartItem.size()) goto _L4; else goto _L3
_L3:
        JSONArray jsonarray1;
        int i;
        try
        {
            params.put("TheSkus", jsonarray);
        }
        catch(JSONException jsonexception3)
        {
            jsonexception3.printStackTrace();
        }
_L2:
        jsonarray1 = new JSONArray();
        if(packsItem.size() <= 0) goto _L6; else goto _L5
_L5:
        ((LinearLayout)findViewById(0x7f0c02bb)).setVisibility(0);
        i = 0;
_L10:
        if(i < packsItem.size()) goto _L8; else goto _L7
_L7:
        try
        {
            params.put("ThePacks", jsonarray1);
        }
        catch(JSONException jsonexception1)
        {
            jsonexception1.printStackTrace();
        }
_L6:
        hs = null;
        hs = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        hs.setPost(true);
        hs.setFunctionId(functionId);
        hs.setJsonParams(params);
        hs.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy;
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("httpResponse.getString() -->> ")).append(httpresponse.getString()).toString());
                jsonCartInfoContainer = new JSONObjectProxy(httpresponse.getJSONObject().getJSONObject("cartInfo"));
                jsonSkusInfo = null;
                jsonSkusInfo = new JSONArrayPoxy();
                jsonSkusInfo = jsonCartInfoContainer.getJSONArrayOrNull("Skus");
                jsonPacksInfo = null;
                jsonPacksInfo = new JSONArrayPoxy();
                jsonarraypoxy = jsonCartInfoContainer.getJSONArrayOrNull("Suits");
                new JSONArrayPoxy();
                if(jsonarraypoxy == null) goto _L2; else goto _L1
_L1:
                int k;
                jsonManZengInfo = null;
                jsonManZengInfo = new JSONArrayPoxy();
                Contants.skusOfSuites = null;
                Contants.skusOfSuites = new JSONArrayPoxy();
                k = 0;
_L11:
                if(k < jsonarraypoxy.length()) goto _L3; else goto _L2
_L2:
                if(jsonSkusInfo != null || jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null || jsonPacksInfo != null) goto _L5; else goto _L4
_L4:
                setEmptyView();
                if(cartItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(ShoppingCarActivity.this);
                    dbCart.delAllCart();
                    cartItem.clear();
                    cartItem = null;
                    cartItem = new ArrayList();
                }
                if(packsItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(ShoppingCarActivity.this);
                    dbCart.delAllCart();
                    packsItem.clear();
                }
                  goto _L6
_L3:
                JSONArrayPoxy jsonarraypoxy1;
                int l;
                JSONArrayPoxy jsonarraypoxy2;
                int i1;
                if(jsonarraypoxy.getJSONObject(k).getIntOrNull("SuitType") != null && jsonarraypoxy.getJSONObject(k).getInt("SuitType") == 10)
                {
                    jsonarraypoxy2 = jsonarraypoxy.getJSONObject(k).getJSONArrayOrNull("Skus");
                    if(jsonarraypoxy2 != null)
                    {
                        if(jsonSkusInfo == null)
                            jsonSkusInfo = new JSONArrayPoxy();
                        if(jsonManZengInfo == null)
                            jsonManZengInfo = new JSONArrayPoxy();
                        break MISSING_BLOCK_LABEL_706;
                    }
                } else
                {
                    jsonPacksInfo.put(jsonarraypoxy.getJSONObject(k));
                }
                  goto _L7
_L12:
                if(i1 < jsonarraypoxy2.length()) goto _L8; else goto _L7
_L7:
                jsonarraypoxy1 = jsonarraypoxy.getJSONObject(k).getJSONArrayOrNull("Skus");
                l = 0;
_L13:
                if(l < jsonarraypoxy1.length()) goto _L10; else goto _L9
_L9:
                k++;
                  goto _L11
_L8:
                jsonSkusInfo.put(jsonarraypoxy2.getJSONObject(i1));
                jsonManZengInfo.put(jsonarraypoxy2.getJSONObject(i1));
                i1++;
                  goto _L12
                JSONException jsonexception4;
                jsonexception4;
                jsonexception4.printStackTrace();
                  goto _L6
_L10:
                Contants.skusOfSuites.put(jsonarraypoxy1.getJSONObject(l).getString("Id"));
                l++;
                  goto _L13
_L5:
                if(jsonPacksInfo != null && jsonPacksInfo.length() > 0)
                    showPacksView(jsonPacksInfo);
                if(jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null)
                    getGifts();
                if(jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null || jsonSkusInfo != null)
                    setCartList();
_L6:
                return;
                i1 = 0;
                  goto _L12
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int k, int l)
            {
                post(new Runnable() {

                    public void run()
                    {
                        mCalAccount.setEnabled(false);
                        mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                        mTotalPrice.setText(0x7f090150);
                    }

                    final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                }
);
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", (new StringBuilder("max -->> ")).append(k).toString());
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", (new StringBuilder("progress -->> ")).append(l).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", "setUpConnAndGetData()-start");
            }

            final ShoppingCarActivity this$0;


            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
        hs.setNotifyUser(true);
        getHttpGroupaAsynPool().add(hs);
_L11:
        return;
_L4:
        JSONObject jsonobject1 = new JSONObject();
        try
        {
            jsonobject1.put("Id", String.valueOf(((CartTable)cartItem.get(j)).productCode));
            jsonobject1.put("num", String.valueOf(((CartTable)cartItem.get(j)).buyCount));
        }
        catch(JSONException jsonexception2)
        {
            jsonexception2.printStackTrace();
        }
        jsonarray.put(jsonobject1);
        j++;
          goto _L9
_L8:
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("Id", String.valueOf(((PacksTable)packsItem.get(i)).packId));
            jsonobject.put("num", String.valueOf(((PacksTable)packsItem.get(i)).buyCount));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        jsonarray1.put(jsonobject);
        i++;
          goto _L10
        setEmptyView();
        if(cartItem.size() > 0)
        {
            if(dbCart == null)
                dbCart = new DBHelperUtil(this);
            dbCart.delAllCart();
            cartItem.clear();
            cartItem = null;
            cartItem = new ArrayList();
        }
        if(packsItem.size() > 0)
        {
            if(dbCart == null)
                dbCart = new DBHelperUtil(this);
            dbCart.delAllPacksCart();
            packsItem.clear();
        }
          goto _L11
    }

    private void showPacksView(final JSONArrayPoxy packItems)
    {
        post(new Runnable() {

            public void run()
            {
                LayoutInflater layoutinflater;
                LinearLayout linearlayout;
                LinearLayout linearlayout1;
                int i;
                layoutinflater = LayoutInflater.from(ShoppingCarActivity.this);
                linearlayout = (LinearLayout)findViewById(0x7f0c02bb);
                linearlayout1 = (LinearLayout)findViewById(0x7f0c00c1);
                packLayoutGp = null;
                packLayoutGp = new ArrayList();
                listView = new ArrayList();
                linearlayout.removeAllViews();
                i = 0;
_L7:
                int j = packItems.length();
                if(i >= j)
                {
                    int i1 = 0;
                    if(cartItem != null)
                        i1 = cartItem.size();
                    if(jsonSkusInfo == null || i1 <= 0)
                    {
                        linearlayout1.removeAllViews();
                        float f = Float.valueOf(jsonCartInfoContainer.getStringOrNull("Price")).floatValue();
                        float f1 = Float.valueOf(jsonCartInfoContainer.getStringOrNull("Discount")).floatValue();
                        float f2 = Float.valueOf(jsonCartInfoContainer.getStringOrNull("RePrice")).floatValue();
                        String s = (new DecimalFormat("0.00")).format(f - f2 - f1);
                        mOrignalPrice.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append((new DecimalFormat("0.00")).format(f - f1)).toString());
                        mFanXian.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append((new DecimalFormat("0.00")).format(f2)).toString());
                        JSONObjectProxy jsonobjectproxy;
                        JSONException jsonexception;
                        JSONArrayPoxy jsonarraypoxy;
                        View view;
                        int k;
                        int l;
                        View view1;
                        JSONException jsonexception1;
                        final long id;
                        final String name;
                        android.view.View.OnClickListener onclicklistener;
                        View view2;
                        JSONException jsonexception2;
                        if(bModified)
                        {
                            mCalAccount.setEnabled(false);
                            mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                        } else
                        {
                            mCalAccount.setEnabled(true);
                            mCalAccount.setTextColor(0xff000000);
                        }
                        mTotalPrice.setText((new StringBuilder(String.valueOf(Contants.REN_MIN_BI))).append(s).toString());
                    }
                    return;
                }
                jsonobjectproxy = packItems.getJSONObjectOrNull(i);
                if(jsonobjectproxy == null) goto _L2; else goto _L1
_L1:
                if(jsonobjectproxy.getIntOrNull("SuitType") == null || jsonobjectproxy.getInt("SuitType") != 6) goto _L2; else goto _L3
_L3:
                jsonarraypoxy = jsonobjectproxy.getJSONArrayOrNull("Skus");
                if(jsonarraypoxy == null) goto _L2; else goto _L4
_L4:
                view = layoutinflater.inflate(0x7f03006b, null);
                addPackTitleView(view, jsonobjectproxy, i);
                linearlayout.addView(view);
                k = 0;
_L10:
                l = jsonarraypoxy.length();
                if(k < l) goto _L6; else goto _L5
_L5:
                view2 = layoutinflater.inflate(0x7f030068, null);
                packLayoutGp.add(view2);
                try
                {
                    addEditView(view2, jsonobjectproxy.getInt("Num"), jsonobjectproxy, i);
                    linearlayout.addView(view2);
                }
                // Misplaced declaration of an exception variable
                catch(JSONException jsonexception2)
                {
                    jsonexception2.printStackTrace();
                }
_L2:
                i++;
                  goto _L7
_L6:
                view1 = layoutinflater.inflate(0x7f030065, null);
                listView.add(view1);
                addPackView(jsonarraypoxy.getJSONObjectOrNull(k), k, view1);
                if(k % 2 != 1) goto _L9; else goto _L8
_L8:
                view1.setBackgroundResource(0x7f0200a2);
_L11:
                id = jsonarraypoxy.getJSONObjectOrNull(k).getLong("Id");
                name = jsonarraypoxy.getJSONObjectOrNull(k).getString("Name");
                onclicklistener = new android.view.View.OnClickListener() {

                    public void onClick(View view3)
                    {
                        if(Log.D)
                            Log.d("Pack", "click pack");
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                        Bundle bundle = new Bundle();
                        try
                        {
                            bundle.putLong("id", id);
                            bundle.putString("title", name);
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                        }
                        intent.putExtras(bundle);
                        startActivityInFrame(intent);
                    }

                    final _cls5 this$1;
                    private final long val$id;
                    private final String val$name;

                    
                    {
                        this$1 = _cls5.this;
                        id = l;
                        name = s;
                        super();
                    }
                }
;
                view1.setOnClickListener(onclicklistener);
_L12:
                linearlayout.addView(view1);
                k++;
                  goto _L10
_L9:
                view1.setBackgroundResource(0x7f0200a3);
                  goto _L11
                jsonexception;
                jsonexception.printStackTrace();
                  goto _L2
                jsonexception1;
                jsonexception1.printStackTrace();
                  goto _L12
            }

            final ShoppingCarActivity this$0;
            private final JSONArrayPoxy val$packItems;


            
            {
                this$0 = ShoppingCarActivity.this;
                packItems = jsonarraypoxy;
                super();
            }
        }
);
    }

    private void startDel(int i, Product product)
    {
        JSONArray jsonarray;
        int j;
        params = new JSONObject();
        jsonarray = new JSONArray();
        if(cartItem.size() <= 0)
            break MISSING_BLOCK_LABEL_232;
        j = 0;
_L5:
        if(j < cartItem.size()) goto _L2; else goto _L1
_L1:
        params.put("TheSkus", jsonarray);
        if(!LoginUser.hasLogin()) goto _L4; else goto _L3
_L3:
        params.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
_L6:
        cUpdateTask = null;
        cUpdateTask = new updateTask(functionId, params, product, i);
        cUpdateTask.execute(null);
_L7:
        return;
_L2:
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("Id", String.valueOf(((CartTable)cartItem.get(j)).productCode));
            jsonobject.put("num", String.valueOf(((CartTable)cartItem.get(j)).buyCount));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        jsonarray.put(jsonobject);
        j++;
          goto _L5
_L4:
        try
        {
            params.put("pin", "Hechel12");
        }
        catch(JSONException jsonexception1)
        {
            jsonexception1.printStackTrace();
        }
          goto _L6
        setEmptyView();
        if(cartItem.size() > 0)
        {
            if(dbCart == null)
                dbCart = new DBHelperUtil(this);
            dbCart.delAllCart();
            cartItem.clear();
            cartItem = null;
            cartItem = new ArrayList();
        }
          goto _L7
    }

    private JdCartInfo storedLastCartInfo()
    {
        JdCartInfo jdcartinfo;
        JdCartItemInfo jdcartiteminfo;
        jdcartinfo = new JdCartInfo();
        jdcartiteminfo = new JdCartItemInfo();
        if(wareInfoList.getCount() >= 1) goto _L2; else goto _L1
_L1:
        JdCartInfo jdcartinfo1 = null;
_L4:
        return jdcartinfo1;
_L2:
        int i = 0;
_L5:
label0:
        {
            if(i < wareInfoList.getCount())
                break label0;
            ViewGroup viewgroup;
            EditText edittext;
            Product product;
            if(mTotalPrice.getText().toString() == null || mTotalPrice.getText().toString().length() < 1)
                jdcartinfo.setTotalPrice(0.0D);
            else
                try
                {
                    String s = mTotalPrice.getText().toString();
                    jdcartinfo.setTotalPrice(Double.parseDouble(s.substring(1, s.length())));
                }
                catch(NumberFormatException numberformatexception)
                {
                    numberformatexception.printStackTrace();
                }
            if(cartItem.size() != wareInfoList.getCount())
            {
                jdcartinfo.setTotalCount(cartItem.size());
                Toast.makeText(this, "count different", 0).show();
            } else
            {
                jdcartinfo.setTotalCount(cartItem.size());
            }
            jdcartinfo1 = jdcartinfo;
        }
        if(true) goto _L4; else goto _L3
_L3:
        viewgroup = (ViewGroup)wareInfoList.getChildAt(i);
        if(viewgroup != null)
        {
            edittext = (EditText)viewgroup.getChildAt(4);
            if(edittext != null)
            {
                product = (Product)wareInfoList.getAdapter().getItem(i);
                jdcartiteminfo.setProdName(product.getName());
                jdcartiteminfo.setProdID(String.valueOf(product.getId()));
                jdcartiteminfo.setProdPrice(product.getJdPrice());
                jdcartiteminfo.setCount(Integer.valueOf(edittext.getText().toString()).intValue());
                jdcartinfo.appendCartItem(jdcartiteminfo);
            }
        }
        i++;
          goto _L5
    }

    private boolean tooManyProd()
    {
        boolean flag;
        int i;
        int j;
        flag = false;
        i = 0;
        j = 0;
_L9:
        if(j < wareInfoList.getCount()) goto _L2; else goto _L1
_L1:
        int k = 0;
_L12:
        if(k < wareInfoList.getCount()) goto _L4; else goto _L3
_L3:
        if(packLayoutGp == null || packLayoutGp.size() <= 0) goto _L6; else goto _L5
_L5:
        int l = 0;
_L13:
        if(l < packLayoutGp.size())
            break MISSING_BLOCK_LABEL_342;
_L6:
        if(i > Contants.MAX_SINGLE_PROD_COUNT)
        {
            Toast.makeText(this, getResources().getString(0x7f090143), 1).show();
            flag = true;
        }
        return flag;
_L2:
        ViewGroup viewgroup = (ViewGroup)wareInfoList.getChildAt(j);
        if(viewgroup != null) goto _L8; else goto _L7
_L7:
        EditText edittext;
        j++;
          goto _L9
_L8:
        if((edittext = (EditText)viewgroup.getChildAt(4)) == null) goto _L7; else goto _L10
_L10:
        if(edittext.getText().toString().length() < 1 || edittext.getText().toString() == "" || edittext.getText().toString() == " ")
            edittext.setText("1");
        if(Integer.valueOf(edittext.getText().toString()).intValue() <= Contants.MAX_SINGLE_PROD_COUNT) goto _L7; else goto _L11
_L11:
        edittext.selectAll();
        flag = true;
          goto _L1
_L4:
        ViewGroup viewgroup1 = (ViewGroup)wareInfoList.getChildAt(k);
        if(viewgroup1 != null)
        {
            EditText edittext1 = (EditText)viewgroup1.getChildAt(4);
            if(edittext1 != null)
            {
                if(edittext1.getText().toString().length() < 1 || edittext1.getText().toString() == "" || edittext1.getText().toString() == " ")
                    edittext1.setText("1");
                i += Integer.valueOf(edittext1.getText().toString()).intValue();
            }
        }
        k++;
          goto _L12
        EditText edittext2 = (EditText)((View)packLayoutGp.get(l)).findViewById(0x7f0c020f);
        if(edittext2.getText().toString().length() < 1 || edittext2.getText().toString() == "" || edittext2.getText().toString() == " ")
            edittext2.setText("1");
        if(l < packsItem.size())
            i += Integer.valueOf(edittext2.getText().toString().trim()).intValue() * ((PacksTable)packsItem.get(l)).childCount;
        else
            i += Integer.valueOf(edittext2.getText().toString().trim()).intValue();
        l++;
          goto _L13
    }

    private void unEnableEditList()
    {
        int i = 0;
_L2:
        ViewGroup viewgroup;
        if(i >= wareInfoList.getCount())
            return;
        viewgroup = (ViewGroup)wareInfoList.getChildAt(i);
        if(viewgroup != null)
            break; /* Loop/switch isn't completed */
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        EditText edittext = (EditText)viewgroup.getChildAt(4);
        if(edittext != null)
            edittext.setEnabled(false);
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private void updateCartDB()
    {
        dbCart = new DBHelperUtil(this);
        dbCart.delAllCartNoListener();
        ArrayList arraylist = new ArrayList();
        int i = 0;
        do
        {
            if(i >= cartItem.size())
            {
                if(arraylist != null && arraylist.size() > 0)
                    dbCart.insertAllCart(arraylist);
                else
                    validatCartIcon();
                return;
            }
            if(((CartTable)cartItem.get(i)).buyCount != 0 && String.valueOf(((CartTable)cartItem.get(i)).buyCount).length() >= 1)
                arraylist.add(new CartTable(((CartTable)cartItem.get(i)).name, ((CartTable)cartItem.get(i)).productCode, ((CartTable)cartItem.get(i)).buyCount));
            i++;
        } while(true);
    }

    private void updateCartItem()
    {
        JSONArray jsonarray;
        params = null;
        params = new JSONObject();
        jsonarray = new JSONArray();
        if(cartItem.size() <= 0 && packsItem.size() <= 0)
            break MISSING_BLOCK_LABEL_433;
        if(cartItem.size() <= 0) goto _L2; else goto _L1
_L1:
        int j = 0;
_L9:
        if(j < cartItem.size()) goto _L4; else goto _L3
_L3:
        JSONArray jsonarray1;
        int i;
        try
        {
            params.put("TheSkus", jsonarray);
            if(LoginUser.hasLogin())
                params.put("pin", LoginUser.getLoginUserInfo().getString("pin"));
        }
        catch(JSONException jsonexception3)
        {
            jsonexception3.printStackTrace();
        }
_L2:
        jsonarray1 = new JSONArray();
        if(packsItem.size() <= 0) goto _L6; else goto _L5
_L5:
        i = 0;
_L10:
        if(i < packsItem.size()) goto _L8; else goto _L7
_L7:
        try
        {
            params.put("ThePacks", jsonarray1);
        }
        catch(JSONException jsonexception1)
        {
            jsonexception1.printStackTrace();
        }
_L11:
        hs = null;
        hs = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        hs.setPost(true);
        hs.setFunctionId(functionId);
        hs.setJsonParams(params);
        hs.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy;
                jsonCartInfoContainer = null;
                jsonCartInfoContainer = new JSONObjectProxy(httpresponse.getJSONObject().getJSONObject("cartInfo"));
                jsonSkusInfo = null;
                jsonSkusInfo = new JSONArrayPoxy();
                jsonSkusInfo = jsonCartInfoContainer.getJSONArrayOrNull("Skus");
                jsonPacksInfo = null;
                jsonPacksInfo = new JSONArrayPoxy();
                jsonarraypoxy = jsonCartInfoContainer.getJSONArrayOrNull("Suits");
                new JSONArrayPoxy();
                if(jsonarraypoxy == null) goto _L2; else goto _L1
_L1:
                int k;
                jsonManZengInfo = null;
                jsonManZengInfo = new JSONArrayPoxy();
                Contants.skusOfSuites = null;
                Contants.skusOfSuites = new JSONArrayPoxy();
                k = 0;
_L11:
                if(k < jsonarraypoxy.length()) goto _L3; else goto _L2
_L2:
                if(jsonSkusInfo != null || jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null || jsonPacksInfo != null) goto _L5; else goto _L4
_L4:
                setEmptyView();
                if(cartItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(ShoppingCarActivity.this);
                    dbCart.delAllCart();
                    cartItem.clear();
                    cartItem = null;
                    cartItem = new ArrayList();
                }
                if(packsItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(ShoppingCarActivity.this);
                    dbCart.delAllCart();
                    packsItem.clear();
                }
                  goto _L6
_L3:
                JSONArrayPoxy jsonarraypoxy1;
                int l;
                JSONArrayPoxy jsonarraypoxy2;
                int i1;
                if(jsonarraypoxy.getJSONObject(k).getIntOrNull("SuitType") != null && jsonarraypoxy.getJSONObject(k).getInt("SuitType") == 10)
                {
                    jsonarraypoxy2 = jsonarraypoxy.getJSONObject(k).getJSONArrayOrNull("Skus");
                    if(jsonarraypoxy2 != null)
                    {
                        if(jsonSkusInfo == null)
                            jsonSkusInfo = new JSONArrayPoxy();
                        if(jsonManZengInfo == null)
                            jsonManZengInfo = new JSONArrayPoxy();
                        break MISSING_BLOCK_LABEL_705;
                    }
                } else
                {
                    jsonPacksInfo.put(jsonarraypoxy.getJSONObject(k));
                }
                  goto _L7
_L12:
                if(i1 < jsonarraypoxy2.length()) goto _L8; else goto _L7
_L7:
                jsonarraypoxy1 = jsonarraypoxy.getJSONObject(k).getJSONArrayOrNull("Skus");
                l = 0;
_L13:
                if(l < jsonarraypoxy1.length()) goto _L10; else goto _L9
_L9:
                k++;
                  goto _L11
_L8:
                jsonSkusInfo.put(jsonarraypoxy2.getJSONObject(i1));
                jsonManZengInfo.put(jsonarraypoxy2.getJSONObject(i1));
                i1++;
                  goto _L12
                JSONException jsonexception4;
                jsonexception4;
                jsonexception4.printStackTrace();
                  goto _L6
_L10:
                Contants.skusOfSuites.put(jsonarraypoxy1.getJSONObject(l).getString("Id"));
                l++;
                  goto _L13
_L5:
                boolean flag = checkPacks(jsonPacksInfo);
                if(jsonPacksInfo != null && jsonPacksInfo.length() > 0 && flag)
                    showPacksView(jsonPacksInfo);
                if(jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null)
                    getGifts();
                if(jsonCartInfoContainer.getJSONArrayOrNull("Gifts") != null || jsonSkusInfo != null)
                    updateCartList();
_L6:
                return;
                i1 = 0;
                  goto _L12
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int k, int l)
            {
                post(new Runnable() {

                    public void run()
                    {
                        mCalAccount.setEnabled(false);
                        mCalAccount.setTextColor(Color.rgb(153, 153, 153));
                        mTotalPrice.setText(0x7f090150);
                    }

                    final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super();
                    }
                }
);
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", (new StringBuilder("max -->> ")).append(k).toString());
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", (new StringBuilder("progress -->> ")).append(l).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("ShoppingCarActivity-update", "setUpConnAndGetData()-start");
            }

            final ShoppingCarActivity this$0;


            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
        getHttpGroupaAsynPool().add(hs);
_L12:
        return;
_L4:
        JSONObject jsonobject1 = new JSONObject();
        try
        {
            jsonobject1.put("Id", String.valueOf(((CartTable)cartItem.get(j)).productCode));
            jsonobject1.put("num", String.valueOf(((CartTable)cartItem.get(j)).buyCount));
        }
        catch(JSONException jsonexception2)
        {
            jsonexception2.printStackTrace();
        }
        jsonarray.put(jsonobject1);
        j++;
          goto _L9
_L8:
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("Id", String.valueOf(((PacksTable)packsItem.get(i)).packId));
            jsonobject.put("num", String.valueOf(((PacksTable)packsItem.get(i)).buyCount));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        jsonarray1.put(jsonobject);
        i++;
          goto _L10
_L6:
        post(new Runnable() {

            public void run()
            {
                LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0c02bb);
                linearlayout.removeAllViews();
                linearlayout.setVisibility(8);
            }

            final ShoppingCarActivity this$0;

            
            {
                this$0 = ShoppingCarActivity.this;
                super();
            }
        }
);
          goto _L11
        setEmptyView();
        if(cartItem.size() > 0)
        {
            if(dbCart == null)
                dbCart = new DBHelperUtil(this);
            dbCart.delAllCart();
            cartItem.clear();
            cartItem = null;
            cartItem = new ArrayList();
        }
        if(packsItem.size() > 0)
        {
            if(dbCart == null)
                dbCart = new DBHelperUtil(this);
            dbCart.delAllPacksCart();
            packsItem.clear();
        }
          goto _L12
    }

    private void updateCartItemData()
    {
        if(wareInfoList.getCount() >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = 0;
_L7:
        if(i < wareInfoList.getCount()) goto _L4; else goto _L3
_L3:
        int k = 0;
_L11:
        if(k < cartItem.size())
            break MISSING_BLOCK_LABEL_457;
        updateCartDB();
          goto _L1
_L4:
        ViewGroup viewgroup = (ViewGroup)wareInfoList.getChildAt(i);
        if(viewgroup != null) goto _L6; else goto _L5
_L5:
        i++;
          goto _L7
_L6:
        EditText edittext = (EditText)viewgroup.getChildAt(4);
        if(edittext == null) goto _L5; else goto _L8
_L8:
        Product product = (Product)wareInfoList.getAdapter().getItem(i);
        if(cartItem == null || cartItem.size() < 1 || i >= cartItem.size()) goto _L1; else goto _L9
_L9:
label0:
        {
            if(((CartTable)cartItem.get(i)).productCode != product.getId().longValue())
                break label0;
            if(edittext.getText().toString() == null || edittext.getText().toString() == "" || edittext.getText().toString() == " " || edittext.getText().toString().length() < 1 || Integer.valueOf(edittext.getText().toString()).intValue() == 0)
                ((CartTable)cartItem.get(i)).buyCount = 0;
            else
                ((CartTable)cartItem.get(i)).buyCount = Integer.valueOf(edittext.getText().toString()).intValue();
        }
          goto _L5
        int j = 0;
_L10:
        if(j < cartItem.size())
        {
label1:
            {
                if(((CartTable)cartItem.get(j)).productCode != product.getId().longValue())
                    break label1;
                if(edittext.getText().toString() == null || edittext.getText().toString() == "" || edittext.getText().toString() == " " || edittext.getText().toString().length() < 1 || Integer.valueOf(edittext.getText().toString()).intValue() == 0)
                    ((CartTable)cartItem.get(j)).buyCount = 0;
                else
                    ((CartTable)cartItem.get(j)).buyCount = Integer.valueOf(edittext.getText().toString()).intValue();
            }
        }
          goto _L5
        j++;
          goto _L10
        if(((CartTable)cartItem.get(k)).buyCount == 0)
        {
            cartItem.remove(k);
            k--;
        }
        k++;
          goto _L11
    }

    private void updateCartList()
    {
        setCartList();
    }

    private void updatePackItemData()
    {
        if(packLayoutGp != null && packLayoutGp.size() > 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i = 0;
_L4:
        int j;
        if(i < packLayoutGp.size())
            continue; /* Loop/switch isn't completed */
        j = 0;
_L5:
        if(j < packsItem.size())
            break MISSING_BLOCK_LABEL_183;
        updatePacksDB();
          goto _L1
        if(i >= packsItem.size()) goto _L1; else goto _L3
_L3:
        EditText edittext = (EditText)((View)packLayoutGp.get(i)).findViewById(0x7f0c020f);
        if(edittext.getText().toString() == "" || edittext.getText().toString() == " " || edittext.getText().toString().length() <= 0)
            ((PacksTable)packsItem.get(i)).buyCount = 0;
        else
            ((PacksTable)packsItem.get(i)).buyCount = Integer.valueOf(edittext.getText().toString().trim()).intValue();
        i++;
          goto _L4
        if(((PacksTable)packsItem.get(j)).buyCount == 0)
        {
            packsItem.remove(j);
            j--;
        }
        j++;
          goto _L5
    }

    private void updatePacksDB()
    {
        dbCart = new DBHelperUtil(this);
        dbCart.delAllPacksCartNoListener();
        ArrayList arraylist = new ArrayList();
        int i = 0;
        do
        {
            if(i >= packsItem.size())
            {
                if(arraylist != null && arraylist.size() > 0)
                    dbCart.insertAllPacksCart(arraylist);
                else
                    validatCartIcon();
                return;
            }
            if(((PacksTable)packsItem.get(i)).buyCount != 0 && String.valueOf(((PacksTable)packsItem.get(i)).buyCount).length() >= 1)
                arraylist.add(new PacksTable(((PacksTable)packsItem.get(i)).packId, ((PacksTable)packsItem.get(i)).name, ((PacksTable)packsItem.get(i)).buyCount, ((PacksTable)packsItem.get(i)).childCount));
            i++;
        } while(true);
    }

    private void updateSinglePacksDB(int i)
    {
        dbCart = new DBHelperUtil(this);
        dbCart.updatePacksCart(((PacksTable)packsItem.get(i)).packId, ((PacksTable)packsItem.get(i)).name, ((PacksTable)packsItem.get(i)).buyCount, ((PacksTable)packsItem.get(i)).childCount);
    }

    public boolean checkPacks(JSONArrayPoxy jsonarraypoxy)
    {
        if(packsItem == null) goto _L2; else goto _L1
_L1:
        Log.d("packs-length", (new StringBuilder("jbSkusInfo.length():")).append(jsonarraypoxy.length()).append("; packsItem.size():").append(packsItem.size()).toString());
        if(Log.D)
            Log.d("packs-content", jsonarraypoxy.toString());
        if(jsonarraypoxy.length() != packsItem.size()) goto _L4; else goto _L3
_L3:
        boolean flag = isChildSame(jsonarraypoxy);
        if(!flag) goto _L2; else goto _L4
_L4:
        dbCart.delAllPacksCart();
_L9:
        ArrayList arraylist;
        int i;
        arraylist = new ArrayList();
        i = 0;
_L10:
        if(i < jsonarraypoxy.length()) goto _L6; else goto _L5
_L5:
        if(arraylist == null || arraylist.size() <= 0) goto _L8; else goto _L7
_L7:
        dbCart.insertAllPacksCart(arraylist);
_L2:
        return getPackItem();
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L9
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
          goto _L2
_L6:
        if(jsonarraypoxy.getJSONObjectOrNull(i) != null)
            arraylist.add(new PacksTable(Long.valueOf(jsonarraypoxy.getJSONObjectOrNull(i).get("Id").toString()).longValue(), jsonarraypoxy.getJSONObjectOrNull(i).getString("Name"), jsonarraypoxy.getJSONObjectOrNull(i).getInt("Num"), jsonarraypoxy.getJSONObjectOrNull(i).getJSONArrayOrNull("Skus").length()));
        break MISSING_BLOCK_LABEL_254;
_L8:
        validatCartIcon();
          goto _L2
        i++;
          goto _L10
    }

    public boolean checkSkus(JSONArrayPoxy jsonarraypoxy)
    {
        if(cartItem == null || jsonarraypoxy.length() == cartItem.size()) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        int i;
        dbCart.delAllCart();
        arraylist = new ArrayList();
        i = 0;
_L8:
        if(i < jsonarraypoxy.length()) goto _L4; else goto _L3
_L3:
        if(arraylist == null || arraylist.size() <= 0) goto _L6; else goto _L5
_L5:
        dbCart.insertAllCart(arraylist);
_L2:
        return getCartItem();
_L4:
        if(jsonarraypoxy.getJSONObjectOrNull(i) != null)
            arraylist.add(new CartTable(jsonarraypoxy.getJSONObjectOrNull(i).getString("Name"), Long.valueOf(jsonarraypoxy.getJSONObjectOrNull(i).get("Id").toString()).longValue(), jsonarraypoxy.getJSONObjectOrNull(i).getInt("Num")));
        break; /* Loop/switch isn't completed */
_L6:
        validatCartIcon();
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
        if(true) goto _L2; else goto _L7
_L7:
        i++;
          goto _L8
    }

    public boolean isChildSame(JSONArrayPoxy jsonarraypoxy)
    {
        if(jsonarraypoxy.length() == packsItem.size()) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        int i = 0;
        do
        {
            if(i >= jsonarraypoxy.length())
            {
                flag = false;
            } else
            {
label0:
                {
                    int j = 0;
                    JSONArrayPoxy jsonarraypoxy1 = jsonarraypoxy.getJSONObjectOrNull(i).getJSONArrayOrNull("Skus");
                    if(jsonarraypoxy1 != null)
                        j = 0 + jsonarraypoxy1.length();
                    if(j == ((PacksTable)packsItem.get(i)).childCount)
                        break label0;
                    flag = true;
                }
            }
            if(true)
                continue;
            i++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030086);
        getScreenHW();
        getCartItem();
        getPackItem();
        initComponent();
        handleClickEvent();
        Contants.skusOfSuites = null;
        bModified = false;
        if((cartItem == null || cartItem.size() < 1) && (packsItem == null || packsItem.size() < 1))
        {
            setEmptyView();
            if(cartItem.size() > 0)
            {
                if(dbCart == null)
                    dbCart = new DBHelperUtil(this);
                dbCart.delAllCart();
                cartItem.clear();
                cartItem = null;
                cartItem = new ArrayList();
            }
            if(packsItem.size() > 0)
            {
                if(dbCart == null)
                    dbCart = new DBHelperUtil(this);
                dbCart.delAllPacksCart();
                packsItem.clear();
            }
        } else
        {
            if(Log.D)
                Log.d("Temp", "onCreate() setViewWithItem() -->> ");
            setViewWithItem();
        }
    }

    public void onDestroy()
    {
        list = null;
        Contants.hasNewTocart = false;
        cartItem = null;
        super.onDestroy();
    }

    public void onResume()
    {
        super.onResume();
        Contants.skusOfSuites = null;
        if(bModified)
        {
            bModified = false;
            mDone.setText(0x7f09014f);
            mCalAccount.setEnabled(true);
            mCalAccount.setTextColor(0xff000000);
        }
        if(!Contants.hasNewTocart) goto _L2; else goto _L1
_L1:
        getCartItem();
        getPackItem();
        if(Log.D)
            Log.d("Temp", "onResume() setViewWithItem() -->> ");
        setViewWithItem();
        Contants.hasNewTocart = false;
_L4:
        return;
_L2:
        if((cartItem == null || cartItem.size() < 1) && (packsItem == null || packsItem.size() < 1))
        {
            getCartItem();
            getPackItem();
            if((cartItem == null || cartItem.size() < 1) && (packsItem == null || packsItem.size() < 1))
            {
                setEmptyView();
                if(cartItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(this);
                    dbCart.delAllCart();
                    cartItem.clear();
                    cartItem = null;
                    cartItem = new ArrayList();
                }
                if(packsItem.size() > 0)
                {
                    if(dbCart == null)
                        dbCart = new DBHelperUtil(this);
                    dbCart.delAllPacksCart();
                    packsItem.clear();
                }
            } else
            {
                if(Log.D)
                    Log.d("Temp", "onResume() setViewWithItem() -->> ");
                setViewWithItem();
            }
        } else
        {
            if(Log.D)
                Log.d("Temp", "onResume() setViewWithItem() -->> ");
            setViewWithItem();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    MySimpleAdapter adapter;
    private boolean bDeleted;
    boolean bExtendFlag[];
    boolean bModified;
    private boolean bUpdate;
    updateTask cUpdateTask;
    ArrayList cartItem;
    private int count;
    DBHelperUtil dbCart;
    int delIndex;
    String functionId;
    boolean hasGifts;
    ArrayList hasZengArray;
    com.jindong.app.mall.utils.HttpGroup.HttpSetting hs;
    private JSONObjectProxy jsonCartInfoContainer;
    private JSONArrayPoxy jsonGiftsInfo;
    private JSONArrayPoxy jsonManZengInfo;
    private JSONArrayPoxy jsonPacksInfo;
    private JSONArrayPoxy jsonSkusInfo;
    ArrayList list;
    ArrayList listView;
    Button mCalAccount;
    Button mDone;
    TextView mFanXian;
    TextView mOrignalPrice;
    TextView mTitle;
    TextView mTotalPrice;
    ArrayList packLayoutGp;
    ArrayList packsItem;
    JSONObject params;
    int screenHeight;
    int screenWidth;
    private ListView wareInfoList;





























}
