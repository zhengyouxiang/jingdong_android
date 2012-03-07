// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.HomeSharedToPersionActivity;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.home.HomeActivity;
import com.jindong.app.mall.login.LoginActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.product.ProductListActivity;
import com.jindong.app.mall.service.MessagePullService;
import com.jindong.app.mall.utils.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.personel:
//            MyWebMessage, MyOrderListActivity, MyCollectActivity, MyCommentDiscussActivity

public class PersonelActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener, android.widget.ViewSwitcher.ViewFactory
{

    public PersonelActivity()
    {
        jPin = null;
        jsonUserInfo = null;
        jsonArray = null;
        jsonRecomandArray = null;
        spin = "pin";
        nCurrentIndex = 0;
        nLength = 0;
        acEditView = new AutoCompleteTextView[2];
        commercialThread = null;
    }

    private void autoComplete()
    {
        resolveAutoComplete(acEditView[0], mSearchResultView, mScrollView);
        resolveAutoComplete(acEditView[1], mSearchResultView, mScrollView);
        ((Button)findViewById(0x7f0c00f6)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                searchSubmit(acEditView[acEditViewIndex].getText().toString());
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
    }

    private void changeListViewVisable()
    {
        if(mRearchResulLayout.getVisibility() != 0)
        {
            mScrollView.setVisibility(8);
            mRearchResulLayout.setVisibility(0);
        }
    }

    private void changeScrollViewVisable()
    {
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(acEditView[acEditViewIndex].getWindowToken(), 0);
        if(mRearchResulLayout.getVisibility() == 0)
        {
            mScrollView.setVisibility(0);
            mRearchResulLayout.setVisibility(8);
        }
    }

    private void doNext()
    {
        if(Log.D)
            Log.e("commercialList", (new StringBuilder("doPrevious() commIndex=")).append(commIndex).toString());
        if(commIndex != 0)
        {
            int i = commIndex - 1;
            commIndex = i;
            int j;
            if(i < 0)
            {
                j = 0;
            } else
            {
                j = commIndex;
                commIndex = j - 1;
            }
            commIndex = j;
            shared.setImage(mSwitcher, shared.previous(this, mSwitcher, ((Commercial)commercialList.get(commIndex)).getDrawable()));
            shared.closeNight(commLayout, commercialList.size());
            if(commercialList.size() > 1)
                commLayout.findViewWithTag(Integer.valueOf(commIndex)).setBackgroundResource(0x7f020003);
        } else
        {
            mSwitcher.startAnimation(AnimationUtils.loadAnimation(this, 0x7f040005));
        }
    }

    private void doPrevious()
    {
        if(Log.D)
            Log.e("commercialList", (new StringBuilder("doNext() commIndex=")).append(commIndex).toString());
        if(commIndex != commercialList.size() - 1)
        {
            int i = 1 + commIndex;
            commIndex = i;
            int j;
            if(i > commercialList.size() - 1)
            {
                j = commercialList.size() - 1;
            } else
            {
                j = commIndex;
                commIndex = j + 1;
            }
            commIndex = j;
            shared.setImage(mSwitcher, shared.next(this, mSwitcher, ((Commercial)commercialList.get(commIndex)).getDrawable()));
            shared.closeNight(commLayout, commercialList.size());
            if(commercialList.size() > 1)
                commLayout.findViewWithTag(Integer.valueOf(commIndex)).setBackgroundResource(0x7f020003);
        } else
        {
            mSwitcher.startAnimation(AnimationUtils.loadAnimation(this, 0x7f040005));
        }
    }

    private void geCrazyList()
    {
        if(HomeSharedToPersionActivity.crazyBuyProductList == null)
            shared.showCrazyBuy(this, new com.jindong.app.mall.HomeSharedToPersionActivity.OnEndListener() {

                public void onEnd()
                {
                    setRecomandList(HomeSharedToPersionActivity.crazyBuyProductList, 0);
                }

                final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
            }
);
    }

    private void getRecomandProduct()
    {
        com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener customonalllistener = new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                try
                {
                    jsonRecomandArray = httpresponse.getJSONObject().getJSONArray("wareInfoList");
                    ArrayList arraylist = Product.toList(jsonRecomandArray, 4);
                    if(arraylist != null && arraylist.size() > 0)
                        guessProductlist = arraylist;
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
                if(Log.D)
                    Log.d("Recomand", "End to get recomand product list...");
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Recomand", "Start to get recomand product list...");
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("recommend");
        httpsetting.setListener(customonalllistener);
        httpsetting.setJsonParams(jPin);
        httpsetting.setLocalFileCache(true);
        httpsetting.setLocalFileCacheTime(0x1b7740L);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void getUnreadMessage()
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("messageCount");
        httpsetting.setJsonParams(jPin);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                final int unReadMessageCount = ((JSONObject)httpresponse.getJSONObject().get("messageCount")).getInt("data");
                post(new Runnable() {

                    public void run()
                    {
                        TextView textview = (TextView)findViewById(0x7f0c0240);
                        if(unReadMessageCount > 0)
                        {
                            textview.setText((new StringBuilder(String.valueOf(unReadMessageCount))).toString());
                            textview.setVisibility(0);
                        } else
                        {
                            textview.setText("");
                            textview.setVisibility(8);
                        }
                    }

                    final _cls10 this$1;
                    private final int val$unReadMessageCount;

                    
                    {
                        this$1 = _cls10.this;
                        unReadMessageCount = i;
                        super();
                    }
                }
);
_L1:
                return;
                Exception exception;
                exception;
                if(Log.D)
                    Log.d("Personel", (new StringBuilder("error message:")).append(exception.getMessage()).toString());
                  goto _L1
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Personel", "start to get user information");
            }

            final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void getUserInfo()
    {
        jsonUserInfo = new JSONObject();
        jsonArray = new JSONObject();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("userInfo");
        httpsetting.setJsonParams(jPin);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                jsonArray = (JSONObject)httpresponse.getJSONObject().get("userInfoSns");
                if(Log.D)
                    Log.d("userinfoSns", jsonArray.toString());
                if(Log.D)
                    Log.d("Login...end", String.valueOf(jsonArray.length()));
                if(jsonArray != null && jsonArray.length() > 0)
                {
                    jsonUserInfo.put("left_balance", httpresponse.getJSONObject().get("balance").toString());
                    jsonUserInfo.put("score", httpresponse.getJSONObject().get("score").toString());
                    if(Log.D)
                        Log.d("userinfo", jsonUserInfo.toString());
                }
                setUserInfo();
_L2:
                if(Log.D)
                    Log.d("Personel", "end to get user information");
                return;
                Exception exception;
                exception;
                if(Log.D)
                    Log.d("Personel", (new StringBuilder("error message:")).append(exception.getMessage()).toString());
                if(true) goto _L2; else goto _L1
_L1:
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Personel", "start to get user information");
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void init()
    {
        if(!LoginUser.hasLogin())
        {
            startToLoginActivity();
        } else
        {
            if(oldPin != null && !oldPin.equals(LoginUser.getLoginUserName()))
            {
                mUserClass.setText("");
                mSayHello.setText("");
                mScore.setText("");
                mImgView.setImageResource(0x7f02005f);
                if(guessProductlist != null)
                    guessProductlist.clear();
            }
            oldPin = LoginUser.getLoginUserName();
            int i = acEditViewIndex;
            int j = 1 + acEditViewIndex;
            acEditViewIndex = j;
            int k;
            if(j >= acEditView.length)
                k = 0;
            else
                k = acEditViewIndex;
            acEditViewIndex = k;
            acEditView[i].setVisibility(8);
            acEditView[acEditViewIndex].setVisibility(0);
            acEditView[acEditViewIndex].setText(acEditView[i].getText());
            changeScrollViewVisable();
            jPin = new JSONObject();
            getUserInfo();
            if(guessProductlist != null && guessProductlist.size() > 0)
                setRecomandList(guessProductlist, 0);
            else
                getRecomandProduct();
            if(HomeSharedToPersionActivity.crazyBuyProductList != null && HomeSharedToPersionActivity.crazyBuyProductList.size() > 0)
                setRecomandList(HomeSharedToPersionActivity.crazyBuyProductList, 0);
            else
                geCrazyList();
            getUnreadMessage();
        }
    }

    private void initCommercial()
    {
        mSwitcher.setFactory(this);
        int i = DPIUtil.getWidth() / 2;
        IGestureListener igesturelistener = new IGestureListener(new com.jindong.app.mall.utils.IGestureListener.TouchFlingActionListener() {

            public void next()
            {
                doNext();
            }

            public void previous()
            {
                doPrevious();
            }

            public void startActivity()
            {
                Intent intent = new Intent(PersonelActivity.this, com/jindong/app/mall/product/ProductListActivity);
                Bundle bundle = new Bundle();
                PersonelActivity personelactivity = PersonelActivity.this;
                int j;
                PersonelActivity personelactivity1;
                int k;
                if(commIndex < 0)
                    j = 0;
                else
                    j = commIndex;
                personelactivity.commIndex = j;
                personelactivity1 = PersonelActivity.this;
                if(commIndex > commercialList.size() - 1)
                    k = commercialList.size() - 1;
                else
                    k = commIndex;
                personelactivity1.commIndex = k;
                bundle.putSerializable("commercial", (Commercial)commercialList.get(commIndex));
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
        mSwitcher = shared.initActivities(this, mSwitcher, i, commIndex, new com.jindong.app.mall.HomeSharedToPersionActivity.OnCommercialOnTouchListener() {

            public boolean onChange(boolean flag)
            {
                lockTouch = flag;
                return flag;
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
        final GestureDetector detector = new GestureDetector(igesturelistener);
        mSwitcher.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                boolean flag;
                if(!lockTouch)
                    flag = detector.onTouchEvent(motionevent);
                else
                    flag = false;
                return flag;
            }

            final PersonelActivity this$0;
            private final GestureDetector val$detector;

            
            {
                this$0 = PersonelActivity.this;
                detector = gesturedetector;
                super();
            }
        }
);
    }

    private void initCommercialThread()
    {
        commercialThread = new CommercialThread(new com.jindong.app.mall.utils.CommercialThread.CommercialThreadListener() {

            public void doRun()
            {
                if(commercialList != null)
                {
                    if(commIndex == commercialList.size() - 1)
                        commIndex = 0;
                    post(main);
                }
            }

            private final Runnable main = new Runnable() {

                public void run()
                {
                    doPrevious();
                }

                final _cls23 this$1;

                    
                    {
                        this$1 = _cls23.this;
                        super();
                    }
            }
;
            final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
    }

    private void initComponent()
    {
        shared = new HomeSharedToPersionActivity();
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f09003c);
        Button button = (Button)findViewById(0x7f0c02c8);
        button.setVisibility(0);
        button.setText(0x7f0900c2);
        rightArrow = findViewById(0x7f0c01ec);
        leftArrow = findViewById(0x7f0c0248);
        acEditView[0] = (AutoCompleteTextView)findViewById(0x7f0c00f5);
        acEditView[1] = (AutoCompleteTextView)findViewById(0x7f0c00f7);
        mVoiceSearchButton = (Button)findViewById(0x7f0c00f4);
        mVoiceSearchButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() != 0)
                {
                    startVoiceRecognitionActivity();
                } else
                {
                    dialogBuilder.setTitle(0x7f09001f);
                    dialogBuilder.setMessage(0x7f090021);
                    dialogBuilder.setPositiveButton(0x7f09000e, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                        }

                        final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            dialogBuilder.show();
                        }

                        final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                    }
);
                }
            }

            final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
        dialogBuilder = new android.app.AlertDialog.Builder(this);
        searchCleanButton = (ImageButton)findViewById(0x7f0c00f8);
        mRearchResulLayout = (FrameLayout)findViewById(0x7f0c00f9);
        mSearchResultView = (ListView)findViewById(0x7f0c00fa);
        mScrollView = (ScrollView)findViewById(0x7f0c0235);
        autoComplete();
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(PersonelActivity.this)).create();
                alertdialog.setMessage(getText(0x7f0900c3));
                alertdialog.setTitle(0x7f0900c2);
                alertdialog.setButton(getText(0x7f09007a), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        jsonUserInfo = new JSONObject();
                        jsonArray = new JSONObject();
                        LoginUser.setUserState(Contants.LOG_OFF);
                        putBooleanToPreference("login", Boolean.valueOf(false));
                        putBooleanToPreference("remember", Boolean.valueOf(false));
                        Contants.clearOrderInfo();
                        if(!MessagePullService.widgetIsRunInBg())
                        {
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/service/MessagePullService);
                            intent.setAction("Action_Stop_Message_Service");
                            startService(intent);
                        }
                        removeAllHistory();
                        HttpGroup.cleanCookies();
                        Intent intent1 = new Intent(_fld0, com/jindong/app/mall/home/HomeActivity);
                        startSingleActivityInFrame(intent1);
                        dialoginterface.dismiss();
                    }

                    final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                }
);
                alertdialog.setButton2(getText(0x7f09000f), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dialoginterface.dismiss();
                    }

                    final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                }
);
                alertdialog.show();
            }

            final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
        commLayout = (LinearLayout)findViewById(0x7f0c0237);
        mSwitcher = (ImageSwitcher)findViewById(0x7f0c0236);
        mSwitcher.setVisibility(8);
        initCommercial();
        shared.my618(this, getHttpGroupaAsynPool(), onCommercialListener);
        mUserClass = (TextView)findViewById(0x7f0c023a);
        mSayHello = (TextView)findViewById(0x7f0c0239);
        mBalance = (TextView)findViewById(0x7f0c023c);
        mScore = (TextView)findViewById(0x7f0c023b);
        mImgView = (ImageView)findViewById(0x7f0c0238);
        findViewById(0x7f0c0246).setSelected(true);
        mGallery = (Gallery)findViewById(0x7f0c0249);
        mGallery.setOnItemClickListener(this);
        geCrazyList();
        shared.showReportList(this, new com.jindong.app.mall.HomeSharedToPersionActivity.OnEndListener() {

            public void onEnd()
            {
                post(new Runnable() {

                    public void run()
                    {
                        mScrollView.smoothScrollTo(0, 0);
                    }

                    final _cls4 this$1;

                    
                    {
                        this$1 = _cls4.this;
                        super();
                    }
                }
);
            }

            final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
    }

    private void resolveAutoComplete(final AutoCompleteTextView autoCompleteTextView, final ListView mSearchResultView, ScrollView scrollview)
    {
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setOnKeyListener(new android.view.View.OnKeyListener() {

            public boolean onKey(View view, int i, KeyEvent keyevent)
            {
                i;
                JVM INSTR lookupswitch 2: default 28
            //                           4: 59
            //                           66: 34;
                   goto _L1 _L2 _L3
_L1:
                boolean flag = false;
_L5:
                return flag;
_L3:
                searchSubmit(autoCompleteTextView.getText().toString());
                flag = true;
                break; /* Loop/switch isn't completed */
_L2:
                if(mRearchResulLayout.getVisibility() != 0)
                    continue; /* Loop/switch isn't completed */
                changeScrollViewVisable();
                flag = true;
                if(true) goto _L5; else goto _L4
_L4:
                if(true) goto _L1; else goto _L6
_L6:
            }

            final PersonelActivity this$0;
            private final AutoCompleteTextView val$autoCompleteTextView;

            
            {
                this$0 = PersonelActivity.this;
                autoCompleteTextView = autocompletetextview;
                super();
            }
        }
);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
                JSONObject jsonobject;
                if(TextUtils.isEmpty(charsequence))
                {
                    searchCleanButton.setVisibility(8);
                    changeScrollViewVisable();
                } else
                {
                    searchCleanButton.setVisibility(0);
                    searchCleanButton.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            autoCompleteTextView.setText(null);
                            searchCleanButton.setVisibility(8);
                        }

                        final _cls19 this$1;
                        private final AutoCompleteTextView val$autoCompleteTextView;

                    
                    {
                        this$1 = _cls19.this;
                        autoCompleteTextView = autocompletetextview;
                        super();
                    }
                    }
);
                }
                jsonobject = new JSONObject();
                if((!TextUtils.isEmpty(charsequence) || charsequence.toString().trim().length() != 0) && !charsequence.toString().equals(keyWord)) goto _L2; else goto _L1
_L1:
                changeScrollViewVisable();
_L4:
                return;
_L2:
                keyWord = charsequence.toString();
                try
                {
                    jsonobject.put("keyword", keyWord);
                }
                catch(JSONException jsonexception)
                {
                    if(Log.V)
                        Log.v(PersonelActivity.TAG, jsonexception.getMessage());
                }
                getHttpGroupaAsynPool().add("tip", jsonobject, new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        try
                        {
                            if(httpresponse.getJSONObject() != null)
                            {
                                JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("tip");
                                if(jsonarraypoxy.length() < 1 || TextUtils.isEmpty(keyWord))
                                {
                                    mSearchResultView.removeAllViewsInLayout();
                                } else
                                {
                                    java.util.LinkedList linkedlist = KeyWord.toList(jsonarraypoxy, 0);
                                    PersonelActivity personelactivity = _fld0;
                                    String as[] = new String[2];
                                    as[0] = "name";
                                    as[1] = "countString";
                                    int ai[] = new int[2];
                                    ai[0] = 0x7f0c010e;
                                    ai[1] = 0x7f0c010f;
                                    final MySimpleAdapter arrayAdapter = new MySimpleAdapter(personelactivity, linkedlist, 0x7f030031, as, ai) {

                                        public View getView(int l, View view, ViewGroup viewgroup)
                                        {
                                            View view1 = super.getView(l, view, viewgroup);
                                            if(view1 != null)
                                                if(l % 2 == 1)
                                                    view1.setBackgroundResource(0x7f02006a);
                                                else
                                                    view1.setBackgroundResource(0x7f02006b);
                                            return view1;
                                        }

                                        final _cls2 this$2;

                        
                        {
                            this$2 = _cls2.this;
                            super(myactivity, list, i, as, ai);
                        }
                                    }
;
                                    post(new Runnable() {

                                        public void run()
                                        {
                                            mSearchResultView.setAdapter(arrayAdapter);
                                            changeListViewVisable();
                                        }

                                        final _cls2 this$2;
                                        private final MySimpleAdapter val$arrayAdapter;
                                        private final ListView val$mSearchResultView;

                        
                        {
                            this$2 = _cls2.this;
                            mSearchResultView = listview;
                            arrayAdapter = mysimpleadapter;
                            super();
                        }
                                    }
);
                                    mSearchResultView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                                        public void onItemClick(AdapterView adapterview, View view, int l, long l1)
                                        {
                                            KeyWord keyword = (KeyWord)((Adapter)adapterview.getAdapter()).getItem(l);
                                            autoCompleteTextView.setTextKeepState(keyword.getName());
                                            searchSubmit(keyword.getName());
                                        }

                                        final _cls2 this$2;
                                        private final AutoCompleteTextView val$autoCompleteTextView;

                        
                        {
                            this$2 = _cls2.this;
                            autoCompleteTextView = autocompletetextview;
                            super();
                        }
                                    }
);
                                }
                            }
                        }
                        catch(JSONException jsonexception1)
                        {
                            if(Log.V)
                                Log.v("HomeActivity", jsonexception1.getMessage());
                        }
                    }

                    public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                    {
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("error -->> ")).append(httperror).toString());
                    }

                    public void onProgress(int l, int i1)
                    {
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("max -->> ")).append(l).toString());
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("progress -->> ")).append(i1).toString());
                    }

                    public void onStart()
                    {
                    }

                    final _cls19 this$1;
                    private final AutoCompleteTextView val$autoCompleteTextView;
                    private final ListView val$mSearchResultView;


                    
                    {
                        this$1 = _cls19.this;
                        mSearchResultView = listview;
                        autoCompleteTextView = autocompletetextview;
                        super();
                    }
                }
);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final PersonelActivity this$0;
            private final AutoCompleteTextView val$autoCompleteTextView;
            private final ListView val$mSearchResultView;


            
            {
                this$0 = PersonelActivity.this;
                autoCompleteTextView = autocompletetextview;
                mSearchResultView = listview;
                super();
            }
        }
);
    }

    private void searchSubmit(String s)
    {
        if(!TextUtils.isEmpty(s))
        {
            changeScrollViewVisable();
            Intent intent = new Intent(this, com/jindong/app/mall/product/ProductListActivity);
            Bundle bundle = new Bundle();
            keyWord = s;
            bundle.putString("keyWord", s);
            bundle.putString("searchway", "text");
            intent.putExtras(bundle);
            startActivityInFrame(intent);
        }
    }

    private void setBalanceAndScore()
    {
        String s3;
        String s;
        String s1;
        String s4;
        if(TextUtils.isEmpty((CharSequence)jsonUserInfo.get("left_balance")))
            s = "0.0";
        else
            s = jsonUserInfo.get("left_balance").toString();
        s1 = (new DecimalFormat("0.00")).format(Float.valueOf(s));
        if(!TextUtils.isEmpty((CharSequence)jsonUserInfo.get("score"))) goto _L2; else goto _L1
_L1:
        s3 = "0.00";
_L3:
        s4 = (new StringBuilder(String.valueOf(getString(0x7f0900c5)))).append("<font color='#CC0000'>").append(s1).append("</font>").toString();
        mScore.setText((new StringBuilder(String.valueOf(getString(0x7f0900c4)))).append(s3).toString());
        mBalance.setText(Html.fromHtml(s4), android.widget.TextView.BufferType.SPANNABLE);
        break MISSING_BLOCK_LABEL_210;
_L2:
        String s2 = (new StringBuilder(String.valueOf(jsonUserInfo.get("score").toString()))).toString();
        s3 = s2;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        jsonexception.printStackTrace();
    }

    private void setRecomandList(ArrayList arraylist, int i)
    {
        if(arraylist != null && arraylist.size() != 0)
        {
            String as[] = new String[1];
            as[0] = "imageUrl";
            int ai[] = new int[1];
            ai[0] = 0x7f0c01ea;
            post(new Runnable() {

                public void run()
                {
                    mGallery.setAdapter(adapter);
                    PersonelActivity personelactivity = PersonelActivity.this;
                    PersonelActivity personelactivity1 = PersonelActivity.this;
                    int j = 1 + personelactivity1.nCurrentIndex;
                    personelactivity1.nCurrentIndex = j;
                    int k;
                    if(j >= adapter.getCount())
                        k = 0;
                    else
                        k = nCurrentIndex;
                    personelactivity.nCurrentIndex = k;
                    mGallery.setSelection(nCurrentIndex);
                }

                final PersonelActivity this$0;
                private final MySimpleAdapter val$adapter;

            
            {
                this$0 = PersonelActivity.this;
                adapter = mysimpleadapter;
                super();
            }
            }
);
            mGallery.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

                public void onItemSelected(AdapterView adapterview, View view, int j, long l)
                {
                    if(j == ((Adapter)adapterview.getAdapter()).getCount() - 1)
                    {
                        rightArrow.setVisibility(4);
                        rightArrow.postInvalidate();
                    } else
                    {
                        rightArrow.setVisibility(0);
                        rightArrow.postInvalidate();
                    }
                    if(j == 0)
                    {
                        leftArrow.setVisibility(8);
                        rightArrow.postInvalidate();
                    } else
                    {
                        leftArrow.setVisibility(0);
                        rightArrow.postInvalidate();
                    }
                }

                public void onNothingSelected(AdapterView adapterview)
                {
                }

                final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
            }
);
        }
    }

    private void setSayHelloName()
    {
        TextView textview = mSayHello;
        Object aobj[] = new Object[1];
        aobj[0] = LoginUser.getLoginUserName();
        textview.setText(getString(0x7f0900cc, aobj));
    }

    private void setUserClass()
    {
        try
        {
            if(jsonArray.getString("uclass").length() < 1)
            {
                if(Log.D)
                    Log.d("User class", "No class for the current user");
                mUserClass.setText(0x7f09008c);
            } else
            {
                mUserClass.setText(jsonArray.getString("uclass"));
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private void setUserImage()
    {
        try
        {
            if(!TextUtils.isEmpty(jsonArray.getString("imgUrl")))
            {
                if(!TextUtils.isEmpty(jsonArray.getString("imgUrl")))
                {
                    com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
                    httpsetting.setType(5000);
                    httpsetting.setUrl(jsonArray.getString("imgUrl"));
                    httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

                        public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                        {
                            final Drawable img = httpresponse.getDrawable();
                            post(new Runnable() {

                                public void run()
                                {
                                    mImgView.setImageDrawable(img);
                                    mImgView.invalidate();
                                }

                                final _cls12 this$1;
                                private final Drawable val$img;

                    
                    {
                        this$1 = _cls12.this;
                        img = drawable;
                        super();
                    }
                            }
);
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

                        final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
                    }
);
                    getHttpGroupaAsynPool().add(httpsetting);
                }
            } else
            {
                post(new Runnable() {

                    public void run()
                    {
                        mImgView.setImageResource(0x7f02005f);
                    }

                    final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
                }
);
            }
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
    }

    private void setUserInfo()
    {
        setUserImage();
        post(new Runnable() {

            public void run()
            {
                setUserClass();
                setSayHelloName();
                setBalanceAndScore();
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                if(Log.D)
                    Log.d("Persionel Error", (new StringBuilder("Error Message:")).append(exception.getMessage()).toString());
                  goto _L1
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
    }

    private void startToLoginActivity()
    {
        Intent intent = new Intent(this, com/jindong/app/mall/login/LoginActivity);
        intent.putExtra("com.360buy:loginResendFlag", 1);
        resendActivityInFrame(intent);
    }

    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PROMPT", "");
        startActivityForResult(intent, 1234);
    }

    public void handleClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131493438 2131493447: default 60
    //                   2131493438 61
    //                   2131493439 60
    //                   2131493440 60
    //                   2131493441 88
    //                   2131493442 113
    //                   2131493443 138
    //                   2131493444 60
    //                   2131493445 60
    //                   2131493446 156
    //                   2131493447 183;
           goto _L1 _L2 _L1 _L1 _L3 _L4 _L5 _L1 _L1 _L6 _L7
_L1:
        return;
_L2:
        Intent intent2 = new Intent(this, com/jindong/app/mall/personel/MyWebMessage);
        view.setPressed(false);
        startActivityInFrame(intent2);
        continue; /* Loop/switch isn't completed */
_L3:
        Intent intent1 = new Intent(this, com/jindong/app/mall/personel/MyOrderListActivity);
        view.setPressed(false);
        startActivityInFrame(intent1);
        continue; /* Loop/switch isn't completed */
_L4:
        Intent intent = new Intent(this, com/jindong/app/mall/personel/MyCollectActivity);
        view.setPressed(false);
        startActivityInFrame(intent);
        continue; /* Loop/switch isn't completed */
_L5:
        startActivityInFrame(new Intent(this, com/jindong/app/mall/personel/MyCommentDiscussActivity));
        continue; /* Loop/switch isn't completed */
_L6:
        setRecomandList(HomeSharedToPersionActivity.crazyBuyProductList, 0);
        view.setSelected(true);
        findViewById(0x7f0c0247).setSelected(false);
        continue; /* Loop/switch isn't completed */
_L7:
        setRecomandList(guessProductlist, 0);
        view.setSelected(true);
        findViewById(0x7f0c0246).setSelected(false);
        if(true) goto _L1; else goto _L8
_L8:
    }

    public View makeView()
    {
        ImageView imageview = new ImageView(this);
        imageview.setTag("icon");
        imageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        imageview.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, (15 * DPIUtil.getHeight()) / 100));
        return imageview;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(i != 1234 || j != -1) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        final String items[];
        int k;
        arraylist = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
        items = new String[arraylist.size()];
        k = 0;
_L6:
        if(k < arraylist.size()) goto _L4; else goto _L3
_L3:
        dialogBuilder.setTitle(0x7f090020);
        dialogBuilder.setItems(items, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int l)
            {
                Log.d("voice", (new StringBuilder("content:")).append(l).append(" ").append(items[l]).append(" ").toString());
                Intent intent1 = new Intent(PersonelActivity.this, com/jindong/app/mall/product/ProductListActivity);
                Bundle bundle = new Bundle();
                keyWord = items[l];
                bundle.putString("keyWord", keyWord);
                bundle.putString("searchway", "voice");
                intent1.putExtras(bundle);
                startActivityInFrame(intent1);
                listDialog.dismiss();
                acEditView[0].setTextKeepState(keyWord);
                acEditView[1].setTextKeepState(keyWord);
            }

            final PersonelActivity this$0;
            private final String val$items[];

            
            {
                this$0 = PersonelActivity.this;
                items = as;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                listDialog = dialogBuilder.show();
            }

            final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super();
            }
        }
);
_L2:
        super.onActivityResult(i, j, intent);
        return;
_L4:
        items[k] = (String)arraylist.get(k);
        k++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Log.D)
            Log.d("enter", "create login......");
        setContentView(0x7f030070);
        initComponent();
    }

    protected void onDestroy()
    {
        if(commercialThread == null)
            break MISSING_BLOCK_LABEL_24;
        this;
        JVM INSTR monitorenter ;
        commercialThread.stop = true;
        this;
        JVM INSTR monitorexit ;
        commercialThread = null;
        super.onDestroy();
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/product/ProductDetailActivity);
        Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
        Bundle bundle = new Bundle();
        bundle.putLong("id", product.getId().longValue());
        bundle.putString("title", product.getName());
        intent.putExtras(bundle);
        startActivityInFrame(intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            MyApplication.exitDialog();
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    protected void onPause()
    {
        if(Log.D)
            Log.d(TAG, " -->> +++++++onPause()");
        if(commercialThread != null)
            commercialThread.isRunner = false;
        super.onPause();
    }

    protected void onRestart()
    {
        super.onRestart();
    }

    protected void onResume()
    {
        super.onResume();
        if(Log.D)
            Log.d("enter", "resume login......");
        init();
        mScrollView.smoothScrollTo(0, 0);
        if(commercialThread != null)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder(String.valueOf(TAG))).append(" onResume() -->> commercialThread.isRunner=").append(commercialThread.isRunner).toString());
            commercialThread.isRunner = true;
        }
    }

    public void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    public static final int GET_IMG_DONE = 257;
    public static final String TAG = com/jindong/app/mall/personel/PersonelActivity.getSimpleName();
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    private AutoCompleteTextView acEditView[];
    private int acEditViewIndex;
    private int commIndex;
    private LinearLayout commLayout;
    private ArrayList commercialList;
    private CommercialThread commercialThread;
    private android.app.AlertDialog.Builder dialogBuilder;
    ArrayList guessProductlist;
    JSONObject jPin;
    JSONObject jsonArray;
    private JSONArrayPoxy jsonRecomandArray;
    JSONObject jsonUserInfo;
    private String keyWord;
    View leftArrow;
    private AlertDialog listDialog;
    private boolean lockTouch;
    TextView mBalance;
    Gallery mGallery;
    ImageView mImgView;
    FrameLayout mRearchResulLayout;
    TextView mSayHello;
    TextView mScore;
    ScrollView mScrollView;
    ListView mSearchResultView;
    private ImageSwitcher mSwitcher;
    TextView mUserClass;
    Gallery mUserGallery;
    Button mVoiceSearchButton;
    int nCurrentIndex;
    int nLength;
    private String oldPin;
    private final com.jindong.app.mall.HomeSharedToPersionActivity.OnCommercialListener onCommercialListener = new com.jindong.app.mall.HomeSharedToPersionActivity.OnCommercialListener() {

        private void showActivities(final Commercial comm, final int index)
        {
            if(Log.D)
                Log.e("OnCommercialListener", "showActivities()");
            post(new Runnable() {

                public void run()
                {
                    mSwitcher.setVisibility(0);
                    mSwitcher.setImageDrawable(comm.getDrawable());
                    commLayout.setVisibility(0);
                    if(commercialList.size() > 1)
                        commLayout.findViewWithTag(Integer.valueOf(index)).setBackgroundResource(0x7f020003);
                }

                final _cls1 this$1;
                private final Commercial val$comm;
                private final int val$index;

                    
                    {
                        this$1 = _cls1.this;
                        comm = commercial;
                        index = i;
                        super();
                    }
            }
);
        }

        public void onCommercial(ArrayList arraylist)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("onCommercial() list -->> ")).append(arraylist).toString());
            commercialList = arraylist;
            if(commercialList != null)
                post(new Runnable() {

                    public void run()
                    {
                        int i = commercialList.size();
                        int j = 0;
                        do
                        {
                            if(j >= i || i <= 1)
                            {
                                mSwitcher.setVisibility(0);
                                commLayout.setVisibility(0);
                                return;
                            }
                            commLayout.addView(shared.addNightLamp(_fld0, Integer.valueOf(j), (commLayout.getWidth() - DPIUtil.dip2px(1.0F) * (i - 2) - DPIUtil.dip2px(10F)) / i, DPIUtil.dip2px(3F)));
                            if(j != i - 1)
                                commLayout.addView(shared.addNightGap(_fld0));
                            j++;
                        } while(true);
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
        }

        public void onError(int i, Commercial commercial, com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
        {
            if(Log.D)
                Log.e("OnCommercialListener", (new StringBuilder("onError() currentIndex=")).append(i).append(" commercialList size=").append(commercialList.size()).toString());
            if(commercialList == null || commercialList.size() <= 0 || i >= commercialList.size()) goto _L2; else goto _L1
_L1:
            if(commercialList.get(i) != null)
                commercialList.remove(i);
            try
            {
                commercial.setDrawable(new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(PersonelActivity.this, getString(0x7f09007d)));
                commercialList.add(i, commercial);
            }
            catch(Exception exception)
            {
                commercialList.add(commercial);
                exception.printStackTrace();
            }
            if(i == commIndex)
                showActivities(commercial, i);
_L4:
            return;
_L2:
            if(Log.D)
                Log.e("OnCommercialListener", "onError() commercialList is null or size <= 0");
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onFinish()
        {
            if(Log.D)
                Log.e("OnCommercialListener", "onFinish()");
        }

        public void onSyncCommercial(int i, Commercial commercial)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("onSyncCommercial() index -->> ")).append(i).toString());
            if(commercialList.get(i) != null)
                commercialList.remove(i);
            commercialList.add(i, commercial);
            if(i == commIndex)
                showActivities(commercial, i);
        }

        final PersonelActivity this$0;


            
            {
                this$0 = PersonelActivity.this;
                super();
            }
    }
;
    ImageView productImg;
    TextView productJdPriceView;
    TextView productMarketPriceView;
    TextView productTextView;
    JSONArrayPoxy reportJsonArray;
    View rightArrow;
    ImageButton searchCleanButton;
    HomeSharedToPersionActivity shared;
    String spin;






























    // Unreferenced inner class com/jindong/app/mall/personel/PersonelActivity$15

/* anonymous class */
    class _cls15 extends MySimpleAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1 = super.getView(i, view, viewgroup);
            TextView textview = (TextView)view1.findViewById(0x7f0c01eb);
            Product product = (Product)getItem(i);
            ProductShow productshow = new ProductShow(getBaseContext(), product);
            textview.setText(Html.fromHtml((new StringBuilder(String.valueOf(product.getName()))).append("<font color='red'>").append(product.getAdWord()).append("</font>").toString()));
            TextView textview1 = (TextView)view1.findViewById(0x7f0c020b);
            TextView textview2 = (TextView)view1.findViewById(0x7f0c027f);
            String s;
            if(TextUtils.isEmpty(product.getJdPrice()))
            {
                s = "";
            } else
            {
                PersonelActivity personelactivity = PersonelActivity.this;
                Object aobj[] = new Object[1];
                aobj[0] = product.getJdPrice();
                s = personelactivity.getString(0x7f0900bf, aobj);
            }
            textview1.setText(s);
            if("\u6682\u65E0\u62A5\u4EF7".equals(product.getMarketPrice()))
                textview2.setVisibility(8);
            textview2.setText(productshow.getMarketPrice());
            return view1;
        }

        final PersonelActivity this$0;

            
            {
                this$0 = PersonelActivity.this;
                super(myactivity, list, i, as, ai);
            }
    }

}
