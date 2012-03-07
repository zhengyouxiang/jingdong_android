// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.utils:
//            MyActivity, MySimpleAdapter, Log, HttpGroup

public abstract class NextPageLoader
    implements HttpGroup.OnAllListener, MyActivity.DestroyListener
{
    public static interface ModifyDataRunnable
    {

        public abstract void modifyData(ArrayList arraylist);
    }

    private abstract class OnScrollLastListener
        implements android.widget.AbsListView.OnScrollListener
    {

        public void checkLast()
        {
            if(firstVisibleItem + visibleItemCount == totalItemCount)
                onScrollLast();
        }

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
            firstVisibleItem = i;
            visibleItemCount = j;
            totalItemCount = k;
        }

        public abstract void onScrollFling();

        public abstract void onScrollIdle();

        public abstract void onScrollLast();

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
            i;
            JVM INSTR tableswitch 0 2: default 28
        //                       0 36
        //                       1 28
        //                       2 29;
               goto _L1 _L2 _L1 _L3
_L1:
            return;
_L3:
            onScrollFling();
            continue; /* Loop/switch isn't completed */
_L2:
            onScrollIdle();
            if(true) goto _L1; else goto _L4
_L4:
        }

        private int firstVisibleItem;
        final NextPageLoader this$0;
        private int totalItemCount;
        private int visibleItemCount;

        private OnScrollLastListener()
        {
            this$0 = NextPageLoader.this;
            super();
        }

        OnScrollLastListener(OnScrollLastListener onscrolllastlistener)
        {
            this();
        }
    }


    public NextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s)
    {
        showItemList = new ArrayList();
        loading = false;
        nextItemList = null;
        loadedShow = false;
        loadedLastPage = false;
        firstLoad = true;
        isHolding = false;
        isFling = false;
        params = new JSONObject();
        pageNumParamKey = "page";
        pageSizeParamKey = "pagesize";
        pageNum = Integer.valueOf(1);
        pageSize = Integer.valueOf(10);
        myActivity = myactivity;
        handler = myactivity.getHandler();
        myactivity.addDestroyListener(this);
        httpGroup = myActivity.getHttpGroupaAsynPool();
        listView = abslistview;
        loadingView = view;
        functionId = s;
    }

    public NextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s, JSONObject jsonobject)
    {
        this(myactivity, abslistview, view, s);
        params = jsonobject;
    }

    public NextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s, JSONObject jsonobject, String s1)
    {
        this(myactivity, abslistview, view, s, jsonobject);
        noDataHint = s1;
    }

    private void applyLoadedShow()
    {
        loadedShow = true;
    }

    private boolean isLoadedLastPage()
    {
        return loadedLastPage;
    }

    private boolean loadedShow()
    {
        boolean flag;
        if(loadedShow)
        {
            loadedShow = false;
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    /**
     * @deprecated Method loadingLock is deprecated
     */

    private boolean loadingLock()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(loading)
            break MISSING_BLOCK_LABEL_23;
        loading = true;
        flag = loading;
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
        flag = false;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method loadingUnLock is deprecated
     */

    private void loadingUnLock()
    {
        this;
        JVM INSTR monitorenter ;
        loading = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void showNextPage(ArrayList arraylist)
    {
        nextItemList = null;
        showItemList.addAll(arraylist);
        if(showItemList.size() < 1 && (listView instanceof ListView) && listView.getAdapter() == null)
        {
            TextView textview = new TextView(myActivity);
            textview.setGravity(17);
            final OnScrollLastListener onScrollLastListener;
            if(noDataHint != null)
                textview.setText(noDataHint);
            else
                textview.setText(0x7f0900b0);
            textview.setTextSize(16F);
            textview.setPadding(0, 10, 0, 10);
            ((ListView)listView).addHeaderView(textview);
        }
        if(arraylist.size() < pageSize.intValue())
        {
            loadedLastPage = true;
            listView.setOnScrollListener(null);
        } else
        {
            pageNum = Integer.valueOf(1 + pageNum.intValue());
            loading();
        }
        if(adapter == null)
        {
            adapter = createAdapter(myActivity, showItemList);
            adapter.setNextPageLoader(this);
            onScrollLastListener = new OnScrollLastListener() {

                public void onScrollFling()
                {
                    isFling = true;
                }

                public void onScrollIdle()
                {
                    isHolding = false;
                    isFling = false;
                    if(!isFinishing)
                    {
                        if(hasNotify)
                        {
                            hasNotify = false;
                            adapter.notifyDataSetChanged();
                        }
                        checkLast();
                    }
                }

                public void onScrollLast()
                {
                    if(!isFinishing && !isLoadedLastPage())
                        tryShowNextPage();
                }

                final NextPageLoader this$0;

            
            {
                this$0 = NextPageLoader.this;
                super(null);
            }
            }
;
            listView.setOnScrollListener(onScrollLastListener);
            listView.setOnTouchListener(new android.view.View.OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    motionevent.getAction();
                    JVM INSTR tableswitch 0 1: default 28
                //                               0 30
                //                               1 41;
                       goto _L1 _L2 _L3
_L1:
                    return false;
_L2:
                    isHolding = true;
                    continue; /* Loop/switch isn't completed */
_L3:
                    if(!isFling)
                        onScrollLastListener.onScrollIdle();
                    if(true) goto _L1; else goto _L4
_L4:
                }

                final NextPageLoader this$0;
                private final OnScrollLastListener val$onScrollLastListener;

            
            {
                this$0 = NextPageLoader.this;
                onScrollLastListener = onscrolllastlistener;
                super();
            }
            }
);
            listView.setAdapter(adapter);
            loadingUnLock();
        } else
        {
            adapter.notifyDataSetChanged();
            loadingUnLock();
        }
        handler.postDelayed(new Runnable() {

            public void run()
            {
                if(listView != null && showItemList.size() <= listView.getChildCount())
                    tryShowNextPage();
            }

            final NextPageLoader this$0;

            
            {
                this$0 = NextPageLoader.this;
                super();
            }
        }
, 500L);
        if(isLoadedLastPage() && (listView instanceof ListView))
            ((ListView)listView).removeFooterView(loadingView);
    }

    private void tryShowNextPage()
    {
        if(!loadedLastPage)
            if(nextItemList == null)
            {
                if(!loadingLock())
                    applyLoadedShow();
                else
                    loading();
            } else
            {
                if(Log.D)
                    Log.d("Temp", "show do -->> ");
                showNextPage(nextItemList);
            }
    }

    protected abstract MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist);

    public JSONObject getParams()
    {
        return params;
    }

    protected void handleParamsBeforeLoading()
    {
        params.put(pageNumParamKey, (new StringBuilder()).append(pageNum).toString());
        params.put(pageSizeParamKey, (new StringBuilder()).append(pageSize).toString());
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("NextPageLoader", "JSONException -->> ", jsonexception);
          goto _L1
    }

    protected void loading()
    {
        handleParamsBeforeLoading();
        HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
        httpsetting.setFunctionId(functionId);
        httpsetting.setJsonParams(params);
        httpsetting.setListener(this);
        httpsetting.setNotifyUser(true);
        if(firstLoad)
        {
            httpsetting.setEffect(1);
            firstLoad = false;
        } else
        {
            httpsetting.setEffect(0);
        }
        httpGroup.add(httpsetting);
    }

    public void modifyData(ModifyDataRunnable modifydatarunnable)
    {
        if(!isHolding)
        {
            modifydatarunnable.modifyData(showItemList);
            adapter.notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged()
    {
        if(!isHolding)
            adapter.notifyDataSetChanged();
        else
            hasNotify = true;
    }

    public void onDestroy()
    {
        isFinishing = true;
        myActivity = null;
        listView = null;
        adapter = null;
        loadingView = null;
        showItemList = null;
        nextItemList = null;
        httpGroup = null;
        params = null;
    }

    public void onEnd(HttpGroup.HttpResponse httpresponse)
    {
        final ArrayList itemList = toList(httpresponse);
        handler.post(new Runnable() {

            public void run()
            {
                if(!isFinishing) goto _L2; else goto _L1
_L1:
                return;
_L2:
                if(itemList == null)
                {
                    showError();
                } else
                {
                    nextItemList = itemList;
                    if(loadedShow())
                    {
                        if(Log.D)
                            Log.d("Temp", "show now -->> ");
                        showNextPage(itemList);
                    }
                }
                if(true) goto _L1; else goto _L3
_L3:
            }

            final NextPageLoader this$0;
            private final ArrayList val$itemList;

            
            {
                this$0 = NextPageLoader.this;
                itemList = arraylist;
                super();
            }
        }
);
    }

    public void onError(HttpGroup.HttpError httperror)
    {
        showError();
    }

    public void onProgress(int i, int j)
    {
    }

    public void onStart()
    {
    }

    public void setPageNumParamKey(String s)
    {
        pageNumParamKey = s;
    }

    public void setPageSize(int i)
    {
        pageSize = Integer.valueOf(i);
    }

    public void setPageSizeParamKey(String s)
    {
        pageSizeParamKey = s;
    }

    protected abstract void showError();

    public void showPageOne()
    {
        if(showItemList.size() < 1)
        {
            applyLoadedShow();
            tryShowNextPage();
        }
    }

    protected abstract ArrayList toList(HttpGroup.HttpResponse httpresponse);

    private MySimpleAdapter adapter;
    private boolean firstLoad;
    protected String functionId;
    private Handler handler;
    private boolean hasNotify;
    protected HttpGroup httpGroup;
    private boolean isFinishing;
    private boolean isFling;
    private boolean isHolding;
    private AbsListView listView;
    private boolean loadedLastPage;
    private boolean loadedShow;
    private boolean loading;
    private View loadingView;
    private MyActivity myActivity;
    private ArrayList nextItemList;
    protected String noDataHint;
    protected Integer pageNum;
    protected String pageNumParamKey;
    protected Integer pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    protected ArrayList showItemList;













}
