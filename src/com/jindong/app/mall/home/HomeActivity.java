// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.home;

import android.app.AlertDialog;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Process;
import android.text.*;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.*;
import com.jindong.app.mall.entity.*;
import com.jindong.app.mall.jdnews.JdNewsListActivity;
import com.jindong.app.mall.login.LoginActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.personel.PersonelActivity;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.product.ProductListActivity;
import com.jindong.app.mall.register.RegisterActivity;
import com.jindong.app.mall.service.MessagePullService;
import com.jindong.app.mall.utils.*;
import java.util.*;
import org.json.*;

public class HomeActivity extends MyActivity
    implements com.jindong.app.mall.login.LoginActivity.LoginListener, android.widget.ViewSwitcher.ViewFactory
{
    private class ClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            view.setPressed(false);
            view.getId();
            JVM INSTR lookupswitch 4: default 52
        //                       2131493108: 196
        //                       2131493129: 53
        //                       2131493130: 169
        //                       2131493131: 142;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L3:
            Intent intent2 = new Intent(HomeActivity.this, com/jindong/app/mall/login/LoginActivity);
            Bundle bundle = new Bundle();
            String s = UUID.randomUUID().toString().substring(0, 6);
            bundle.putString("id", s);
            if(Log.D)
                Log.d("test id", s);
            intent2.putExtras(bundle);
            intent2.putExtra("com.360buy:loginResendFlag", 1);
            startActivityInFrame(intent2);
            continue; /* Loop/switch isn't completed */
_L5:
            Intent intent1 = new Intent(HomeActivity.this, com/jindong/app/mall/jdnews/JdNewsListActivity);
            startActivityInFrame(intent1);
            continue; /* Loop/switch isn't completed */
_L4:
            Intent intent = new Intent(HomeActivity.this, com/jindong/app/mall/register/RegisterActivity);
            startActivityInFrame(intent);
            continue; /* Loop/switch isn't completed */
_L2:
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

                    final ClickListener this$1;

                
                {
                    this$1 = ClickListener.this;
                    super();
                }
                }
);
                post(new Runnable() {

                    public void run()
                    {
                        dialogBuilder.show();
                    }

                    final ClickListener this$1;

                
                {
                    this$1 = ClickListener.this;
                    super();
                }
                }
);
            }
            if(true) goto _L1; else goto _L6
_L6:
        }

        final HomeActivity this$0;


        private ClickListener()
        {
            this$0 = HomeActivity.this;
            super();
        }

        ClickListener(ClickListener clicklistener)
        {
            this();
        }
    }

    private class CrazyBackgroundRegulator
        implements Runnable
    {

        /**
         * @deprecated Method cut is deprecated
         */

        private void cut()
        {
            this;
            JVM INSTR monitorenter ;
            if(Log.D)
                Log.d("Temp", "crazy wait(100) start -->> ");
            wait(100L);
            if(Log.D)
                Log.d("Temp", "crazy wait(100) end -->> ");
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            InterruptedException interruptedexception;
            interruptedexception;
            cut();
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method mWait is deprecated
         */

        private void mWait()
        {
            this;
            JVM INSTR monitorenter ;
            Exception exception;
            try
            {
                if(Log.D)
                    Log.d("Temp", "crazy wait start -->> ");
                wait();
                if(Log.D)
                    Log.d("Temp", "crazy wait end -->> ");
            }
            catch(InterruptedException interruptedexception) { }
            this;
            JVM INSTR monitorexit ;
            return;
            exception;
            throw exception;
        }

        public void run()
        {
            do
            {
                if(thread == null)
                    thread = Thread.currentThread();
                mWait();
                cut();
                post(new Runnable() {

                    public void run()
                    {
                        mItem.setSelected(true);
                    }

                    final CrazyBackgroundRegulator this$1;

                
                {
                    this$1 = CrazyBackgroundRegulator.this;
                    super();
                }
                }
);
            } while(true);
        }

        /**
         * @deprecated Method setItem is deprecated
         */

        public void setItem(View view)
        {
            this;
            JVM INSTR monitorenter ;
            if(view != null) goto _L2; else goto _L1
_L1:
            if(Log.D)
                Log.d("test", "onItemSelected() -->> view is null ");
_L5:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            mItem = view;
            if(thread.getState() != Thread.State.TIMED_WAITING) goto _L4; else goto _L3
_L3:
            thread.interrupt();
              goto _L5
            Exception exception;
            exception;
            throw exception;
_L4:
            if(thread.getState() != Thread.State.WAITING) goto _L5; else goto _L6
_L6:
            notify();
              goto _L5
        }

        private View mItem;
        final HomeActivity this$0;
        private Thread thread;


        private CrazyBackgroundRegulator()
        {
            this$0 = HomeActivity.this;
            super();
        }

        CrazyBackgroundRegulator(CrazyBackgroundRegulator crazybackgroundregulator)
        {
            this();
        }
    }


    public HomeActivity()
    {
        commercialThread = null;
        acEditView = new AutoCompleteTextView[2];
        listener = new ClickListener(null);
        crazyBackground = new CrazyBackgroundRegulator(null);
        crazyBackgroundThread = new Thread(crazyBackground);
        crazyBackgroundThread.start();
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

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
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
        if(mRearchResulLayout.getVisibility() == 0)
        {
            mScrollView.setVisibility(0);
            mRearchResulLayout.setVisibility(8);
        }
    }

    private void checkLoginStatus2()
    {
        if(!((MainActivity)getParent()).hasTargetActivity()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(LoginUser.hasLogin())
        {
            if(Log.D)
                Log.d("Temp", "checkLoginStatus2() check is login -->> ");
            if(((MainActivity)getParent()).getCurrentActivity().getClass() == com/jindong/app/mall/home/HomeActivity)
                startSingleActivityInFrame(new Intent(this, com/jindong/app/mall/personel/PersonelActivity));
        } else
        if(Log.D)
            Log.d("Temp", "checkLoginStatus2() check is not login -->> ");
        if(true) goto _L1; else goto _L3
_L3:
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
            if(commercialList.size() > 1)
            {
                shared.closeNight(commLayout, commercialList.size());
                commLayout.findViewWithTag(Integer.valueOf(commIndex)).setBackgroundResource(0x7f020003);
            }
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
            if(commercialList.size() > 1)
            {
                shared.closeNight(commLayout, commercialList.size());
                commLayout.findViewWithTag(Integer.valueOf(commIndex)).setBackgroundResource(0x7f020003);
            }
        } else
        {
            mSwitcher.startAnimation(AnimationUtils.loadAnimation(this, 0x7f040005));
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
                Intent intent = new Intent(HomeActivity.this, com/jindong/app/mall/product/ProductListActivity);
                Bundle bundle = new Bundle();
                HomeActivity homeactivity = HomeActivity.this;
                int j;
                HomeActivity homeactivity1;
                int k;
                if(commIndex < 0)
                    j = 0;
                else
                    j = commIndex;
                homeactivity.commIndex = j;
                homeactivity1 = HomeActivity.this;
                if(commIndex > commercialList.size() - 1)
                    k = commercialList.size() - 1;
                else
                    k = commIndex;
                homeactivity1.commIndex = k;
                bundle.putSerializable("commercial", (Commercial)commercialList.get(commIndex));
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
                super();
            }
        }
);
        mSwitcher = shared.initActivities(this, mSwitcher, i, commIndex, new com.jindong.app.mall.HomeSharedToPersionActivity.OnCommercialOnTouchListener() {

            public boolean onChange(boolean flag)
            {
                if(commercialThread != null)
                {
                    CommercialThread commercialthread = commercialThread;
                    boolean flag1;
                    if(flag)
                        flag1 = false;
                    else
                        flag1 = true;
                    commercialthread.isRunner = flag1;
                }
                lockTouch = flag;
                return flag;
            }

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
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
                {
                    if(commercialThread != null)
                        commercialThread.isRunner = false;
                    flag = detector.onTouchEvent(motionevent);
                } else
                {
                    flag = false;
                }
                return flag;
            }

            final HomeActivity this$0;
            private final GestureDetector val$detector;

            
            {
                this$0 = HomeActivity.this;
                detector = gesturedetector;
                super();
            }
        }
);
    }

    private void initCommercialThread()
    {
        if(commercialList != null && commercialList.size() > 1)
            commercialThread = new CommercialThread(new com.jindong.app.mall.utils.CommercialThread.CommercialThreadListener() {

                public void doRun()
                {
                    if(commercialList != null)
                    {
                        if(commIndex == commercialList.size() - 1)
                            commIndex = -1;
                        post(main);
                    }
                }

                private final Runnable main = new Runnable() {

                    public void run()
                    {
                        doPrevious();
                    }

                    final _cls15 this$1;

                    
                    {
                        this$1 = _cls15.this;
                        super();
                    }
                }
;
                final HomeActivity this$0;


            
            {
                this$0 = HomeActivity.this;
                super();
            }
            }
);
    }

    private void initComponent()
    {
        checkLoginStatus(false);
        dialogBuilder = new android.app.AlertDialog.Builder(this);
        mLoginImgbtn.setOnClickListener(listener);
        mRegisterImgBtn.setOnClickListener(listener);
        mVoiceSearchButton.setOnClickListener(listener);
        autoComplete();
        shared.my618(this, getHttpGroupaAsynPool(), onCommercialListener);
        showCrazyBuy();
        shared.showReportList(this, new com.jindong.app.mall.HomeSharedToPersionActivity.OnEndListener() {

            public void onEnd()
            {
                post(new Runnable() {

                    public void run()
                    {
                        mScrollView.smoothScrollTo(0, -800);
                    }

                    final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                }
);
            }

            final HomeActivity this$0;


            
            {
                this$0 = HomeActivity.this;
                super();
            }
        }
);
    }

    private void initView()
    {
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f09003c);
        shared = new HomeSharedToPersionActivity();
        mLoginImgbtn = (Button)findViewById(0x7f0c0109);
        mRegisterImgBtn = (Button)findViewById(0x7f0c010a);
        mVoiceSearchButton = (Button)findViewById(0x7f0c00f4);
        searchCleanButton = (ImageButton)findViewById(0x7f0c00f8);
        mRearchResulLayout = (FrameLayout)findViewById(0x7f0c00f9);
        mSearchResultView = (ListView)findViewById(0x7f0c00fa);
        mScrollView = (ScrollView)findViewById(0x7f0c00fb);
        acEditView[0] = (AutoCompleteTextView)findViewById(0x7f0c00f5);
        acEditView[1] = (AutoCompleteTextView)findViewById(0x7f0c00f7);
        commLayout = (LinearLayout)findViewById(0x7f0c00fe);
        mSwitcher = (ImageSwitcher)findViewById(0x7f0c00fd);
        initCommercial();
    }

    private void queryModule()
    {
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                try
                {
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject().getJSONObjectOrNull("modules");
                    if(jsonobjectproxy != null)
                        HttpGroup.setModules(jsonobjectproxy);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                Process.killProcess(Process.myTid());
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("module");
        httpsetting.putJsonParam("version", StatisticsReportUtil.getSoftwareVersionName());
        httpsetting.setListener(onalllistener);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
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

            final HomeActivity this$0;
            private final AutoCompleteTextView val$autoCompleteTextView;

            
            {
                this$0 = HomeActivity.this;
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

                        final _cls7 this$1;
                        private final AutoCompleteTextView val$autoCompleteTextView;

                    
                    {
                        this$1 = _cls7.this;
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
                        Log.v(TAG, jsonexception.getMessage());
                }
                getHttpGroupaAsynPool().add("tip", jsonobject, new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        try
                        {
                            if(httpresponse.getJSONObject() != null)
                            {
                                com.jindong.app.mall.utils.JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("tip");
                                if(jsonarraypoxy.length() < 1 || TextUtils.isEmpty(keyWord))
                                {
                                    mSearchResultView.removeAllViewsInLayout();
                                } else
                                {
                                    java.util.LinkedList linkedlist = KeyWord.toList(jsonarraypoxy, 0);
                                    HomeActivity homeactivity = _fld0;
                                    String as[] = new String[2];
                                    as[0] = "name";
                                    as[1] = "countString";
                                    int ai[] = new int[2];
                                    ai[0] = 0x7f0c010e;
                                    ai[1] = 0x7f0c010f;
                                    final MySimpleAdapter arrayAdapter = new MySimpleAdapter(homeactivity, linkedlist, 0x7f030031, as, ai) {

                                        public View getView(int l, View view, ViewGroup viewgroup)
                                        {
                                            super.getView(l, view, viewgroup);
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

                    final _cls7 this$1;
                    private final AutoCompleteTextView val$autoCompleteTextView;
                    private final ListView val$mSearchResultView;


                    
                    {
                        this$1 = _cls7.this;
                        mSearchResultView = listview;
                        autoCompleteTextView = autocompletetextview;
                        super();
                    }
                }
);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final HomeActivity this$0;
            private final AutoCompleteTextView val$autoCompleteTextView;
            private final ListView val$mSearchResultView;


            
            {
                this$0 = HomeActivity.this;
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
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(acEditView[acEditViewIndex].getWindowToken(), 0);
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

    private void setGalleryBg()
    {
    }

    private void showCrazyBuy()
    {
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                ArrayList arraylist = Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("carzyInfo"), 0);
                HomeSharedToPersionActivity.crazyBuyProductList = arraylist;
                HomeActivity homeactivity = HomeActivity.this;
                String as[] = new String[1];
                as[0] = "imageurl";
                int ai[] = new int[1];
                ai[0] = 0x7f0c0110;
                final CirculationGalleryAdapter adapter = new CirculationGalleryAdapter(homeactivity, arraylist, 0x7f030032, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        StateListDrawable statelistdrawable = new StateListDrawable();
                        Drawable drawable = getResources().getDrawable(0x7f020012);
                        Drawable drawable1 = new Drawable() {

                            public void draw(Canvas canvas)
                            {
                                Paint paint = new Paint();
                                paint.setStyle(android.graphics.Paint.Style.FILL);
                                paint.setColor(-1);
                                Rect rect = copyBounds();
                                rect.top = rect.top + DPIUtil.dip2px(6F);
                                rect.bottom = rect.bottom - DPIUtil.dip2px(6F);
                                rect.left = rect.left + DPIUtil.dip2px(6F);
                                rect.right = rect.right - DPIUtil.dip2px(6F);
                                canvas.drawRoundRect(new RectF(rect), 5F, 5F, paint);
                            }

                            public int getOpacity()
                            {
                                return 0;
                            }

                            public void setAlpha(int j)
                            {
                            }

                            public void setColorFilter(ColorFilter colorfilter)
                            {
                            }

                            final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super();
                        }
                        }
;
                        int ai1[] = new int[1];
                        ai1[0] = 0x10100a1;
                        statelistdrawable.addState(ai1, drawable);
                        int ai2[] = new int[1];
                        ai2[0] = 0x10100a7;
                        statelistdrawable.addState(ai2, drawable);
                        int ai3[] = new int[2];
                        ai3[0] = -0x10100a1;
                        ai3[1] = -0x10100a7;
                        statelistdrawable.addState(ai3, drawable1);
                        view1.setBackgroundDrawable(statelistdrawable);
                        return view1;
                    }

                    final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super(myactivity, list, i, as, ai);
                    }
                }
;
                gallery = (Gallery)findViewById(0x7f0c0102);
                gallery.setCallbackDuringFling(false);
                gallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                        Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                        Bundle bundle = new Bundle();
                        bundle.putLong("id", product.getId().longValue());
                        intent.putExtras(bundle);
                        startActivityInFrame(intent);
                    }

                    final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super();
                    }
                }
);
                gallery.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView adapterview, View view, int i, long l)
                    {
                        Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                        TextView textview = (TextView)mCrazyContentLayout.findViewById(0x7f0c0106);
                        TextView textview1 = (TextView)mCrazyContentLayout.findViewById(0x7f0c0107);
                        String s;
                        if(TextUtils.isEmpty(product.getJdPrice()))
                        {
                            s = "";
                        } else
                        {
                            HomeActivity homeactivity1 = _fld0;
                            Object aobj[] = new Object[1];
                            aobj[0] = product.getJdPrice();
                            s = homeactivity1.getString(0x7f0900bf, aobj);
                        }
                        textview1.setText(s);
                        textview.setText(Html.fromHtml((new StringBuilder(String.valueOf(product.getName()))).append("<font color='red'>").append(product.getAdWord()).append("</font>").toString()), android.widget.TextView.BufferType.SPANNABLE);
                        mCrazyContentLayout.setTag(product);
                        crazyBackground.setItem(view);
                    }

                    public void onNothingSelected(AdapterView adapterview)
                    {
                    }

                    final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super();
                    }
                }
);
                post(new Runnable() {

                    public void run()
                    {
                        gallery.setAdapter(adapter);
                        if(adapter.getActualCount() != 0)
                        {
                            int i = 1 + (Math.abs(0x3fffffff) / adapter.getActualCount()) * adapter.getActualCount();
                            gallery.setSelection(i);
                        }
                        mCrazyContentLayout = (RelativeLayout)findViewById(0x7f0c0105);
                        mCrazyContentLayout.setOnClickListener(new android.view.View.OnClickListener() {

                            public void onClick(View view)
                            {
                                if(view.getTag() != null)
                                {
                                    Product product = (Product)view.getTag();
                                    Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ProductDetailActivity);
                                    Bundle bundle = new Bundle();
                                    bundle.putLong("id", product.getId().longValue());
                                    intent.putExtras(bundle);
                                    startActivityInFrame(intent);
                                }
                            }

                            final _cls4 this$2;

                        
                        {
                            this$2 = _cls4.this;
                            super();
                        }
                        }
);
                        ((MainActivity)getParent()).deleteToken("com.360buy:crazyBuyGlobalInitToken");
                    }

                    final _cls9 this$1;
                    private final CirculationGalleryAdapter val$adapter;


                    
                    {
                        this$1 = _cls9.this;
                        adapter = circulationgalleryadapter;
                        super();
                    }
                }
);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("HomeActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("HomeActivity", "start");
            }

            final HomeActivity this$0;


            
            {
                this$0 = HomeActivity.this;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("crazy");
        httpsetting.putJsonParam("page", "1");
        httpsetting.putJsonParam("pagesize", "50");
        httpsetting.setListener(onalllistener);
        httpsetting.setLocalFileCache(true);
        httpsetting.setLocalFileCacheTime(0x493e0L);
        httpsetting.setNeedGlobalInitialization(false);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PROMPT", "");
        startActivityForResult(intent, 1234);
    }

    public void checkLoginStatus(boolean flag)
    {
        if(!((MainActivity)getParent()).hasTargetActivity()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(LoginUser.hasLogin())
        {
            if(Log.D)
                Log.d("Temp", "onResume() check is login -->> ");
            if(flag || ((MainActivity)getParent()).getCurrentActivity().getClass() == com/jindong/app/mall/home/HomeActivity)
            {
                Intent intent = new Intent(this, com/jindong/app/mall/personel/PersonelActivity);
                intent.putExtra("com.360buy:singleInstanceFlag", true);
                noShowAgain();
                startActivityInFrame(intent);
            }
        } else
        if(Log.D)
            Log.d("Temp", "onResume() check is not login -->> ");
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void loginCompletedNotify()
    {
        if(Log.D)
            Log.d("Temp", "loginCompletedNotify() -->> ");
        if(!getSharedPreferences("jdAndroidClient", 0).getBoolean("jd_widget_deleted", true))
        {
            Intent intent = new Intent("Action_Get_Widget_Info");
            intent.setClass(this, com/jindong/app/mall/service/MessagePullService);
            startService(intent);
        }
        if(getSharedPreferences("jdAndroidClient", 0).getBoolean(getString(0x7f0901c5), false) && getBooleanFromPreference("login", false))
        {
            Intent intent1 = new Intent();
            intent1.setClass(this, com/jindong/app/mall/service/MessagePullService);
            intent1.setAction("Action_Get_Message");
            startService(intent1);
        }
        if(Log.D)
            Log.d("Temp", "LoginActivity  after-startMessagePullService -->>");
        post(new Runnable() {

            public void run()
            {
                checkLoginStatus2();
                initComponent();
                ((MainActivity)getParent()).checkTargetActivity();
            }

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
                super();
            }
        }
);
    }

    public View makeView()
    {
        ImageView imageview = new ImageView(this);
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
                Intent intent1 = new Intent(HomeActivity.this, com/jindong/app/mall/product/ProductListActivity);
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

            final HomeActivity this$0;
            private final String val$items[];

            
            {
                this$0 = HomeActivity.this;
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

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
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
        setContentView(0x7f030030);
        initView();
        LoginActivity.onLogin(this, this);
    }

    protected void onDestroy()
    {
        if(Log.D)
            Log.d(TAG, " -->> onDestroy()");
        if(commercialThread != null)
        {
            commercialThread.stop = true;
            commercialThread = null;
        }
        super.onDestroy();
    }

    public void onHideModal()
    {
        super.onHideModal();
        if(Log.D)
            Log.d("Temp", "HomeActivity onHideModal() -->> ");
        (new Thread() {

            public void run()
            {
                GlobalInitialization.initNetwork(true);
            }

            final HomeActivity this$0;

            
            {
                this$0 = HomeActivity.this;
                super();
            }
        }
).start();
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
        if(Log.D)
            Log.d("Temp", "HomeActivity onResume() -->> ");
        super.onResume();
        checkLoginStatus(true);
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
        if(gallery != null && gallery.getChildCount() > 3)
            gallery.setSelection(3 + gallery.getSelectedItemPosition());
        mScrollView.smoothScrollTo(0, -800);
        if(commercialThread != null)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("HomeActivity onResume() -->> commercialThread.isRunner=")).append(commercialThread.isRunner).toString());
            commercialThread.isRunner = true;
            synchronized(commercialThread)
            {
                commercialThread.notify();
            }
        }
        return;
        exception;
        commercialthread;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void onStop()
    {
        super.onStop();
    }

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    final String TAG = com/jindong/app/mall/home/HomeActivity.getSimpleName();
    private AutoCompleteTextView acEditView[];
    private int acEditViewIndex;
    private int commIndex;
    private LinearLayout commLayout;
    private List commercialList;
    private CommercialThread commercialThread;
    private CrazyBackgroundRegulator crazyBackground;
    private Thread crazyBackgroundThread;
    private android.app.AlertDialog.Builder dialogBuilder;
    Gallery gallery;
    private String keyWord;
    private AlertDialog listDialog;
    ClickListener listener;
    private boolean lockTouch;
    RelativeLayout mCrazyContentLayout;
    ImageView mLeftArrow;
    Button mLoginImgbtn;
    FrameLayout mRearchResulLayout;
    Button mRegisterImgBtn;
    ImageView mRightArrow;
    ScrollView mScrollView;
    ListView mSearchResultView;
    private ImageSwitcher mSwitcher;
    Button mVoiceSearchButton;
    private boolean mustUpdateFalg;
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
                            commLayout.addView(shared.addNightLamp(_fld0, Integer.valueOf(j), (commLayout.getWidth() - DPIUtil.dip2px(1.0F) * (i - 2)) / i, DPIUtil.dip2px(3F)));
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
                commercial.setDrawable(new com.jindong.app.mall.utils.MySimpleAdapter.ExceptionDrawable(HomeActivity.this, getString(0x7f09007d)));
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

        final HomeActivity this$0;


            
            {
                this$0 = HomeActivity.this;
                super();
            }
    }
;
    ImageButton searchCleanButton;
    private HomeSharedToPersionActivity shared;


























}
