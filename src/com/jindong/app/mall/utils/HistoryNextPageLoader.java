// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.utils:
//            MyActivity, HttpGroup, Log, MySimpleAdapter

public abstract class HistoryNextPageLoader
    implements HttpGroup.OnAllListener
{
    private abstract class OnScrollLastListener
        implements android.widget.AbsListView.OnScrollListener
    {

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
            firstVisibleItem = i;
            visibleItemCount = j;
            totalItemCount = k;
        }

        public abstract void onScrollLast();

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
            if(i == 0 && firstVisibleItem + visibleItemCount == totalItemCount)
                onScrollLast();
        }

        private int firstVisibleItem;
        final HistoryNextPageLoader this$0;
        private int totalItemCount;
        private int visibleItemCount;

        private OnScrollLastListener()
        {
            this$0 = HistoryNextPageLoader.this;
            super();
        }

        OnScrollLastListener(OnScrollLastListener onscrolllastlistener)
        {
            this();
        }
    }


    public HistoryNextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s)
    {
        showItemList = new ArrayList();
        loading = false;
        nextItemList = null;
        loadedShow = false;
        loadedLastPage = false;
        params = new JSONObject();
        pageNumParamKey = "page";
        pageSizeParamKey = "pagesize";
        pageNum = Integer.valueOf(1);
        pageSize = Integer.valueOf(10);
        myActivity = myactivity;
        httpGroup = myActivity.getHttpGroupaAsynPool();
        listView = abslistview;
        loadingView = view;
        functionId = s;
    }

    public HistoryNextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s, JSONObject jsonobject)
    {
        this(myactivity, abslistview, view, s);
        params = jsonobject;
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

    private void loading()
    {
        params.put("wareId", "107225,104089,117884,110277,104087,165369,163395,261281,253234,311451");
_L2:
        httpGroup.add(functionId, params, this);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("NextPageLoader", "JSONException -->> ", jsonexception);
        if(true) goto _L2; else goto _L1
_L1:
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
        showItemList.addAll(arraylist);
        if(showItemList.size() < 1 && (listView instanceof ListView))
        {
            final TextView textView = new TextView(myActivity);
            textView.setGravity(17);
            textView.setText(0x7f0900b0);
            myActivity.post(new Runnable() {

                public void run()
                {
                    ((ListView)listView).addHeaderView(textView);
                }

                final HistoryNextPageLoader this$0;
                private final TextView val$textView;

            
            {
                this$0 = HistoryNextPageLoader.this;
                textView = textview;
                super();
            }
            }
);
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
            listView.setOnScrollListener(new OnScrollLastListener() {

                public void onScrollLast()
                {
                    if(!isLoadedLastPage())
                        tryShowNextPage();
                }

                final HistoryNextPageLoader this$0;

            
            {
                this$0 = HistoryNextPageLoader.this;
                super(null);
            }
            }
);
            myActivity.post(new Runnable() {

                public void run()
                {
                    listView.setAdapter(adapter);
                    loadingUnLock();
                }

                final HistoryNextPageLoader this$0;

            
            {
                this$0 = HistoryNextPageLoader.this;
                super();
            }
            }
);
        } else
        {
            myActivity.post(new Runnable() {

                public void run()
                {
                    adapter.notifyDataSetChanged();
                    loadingUnLock();
                }

                final HistoryNextPageLoader this$0;

            
            {
                this$0 = HistoryNextPageLoader.this;
                super();
            }
            }
);
        }
        if(isLoadedLastPage())
            myActivity.post(new Runnable() {

                public void run()
                {
                    if(listView instanceof ListView)
                    {
                        if(Log.D)
                            Log.d("tag", "+++++++++++++++++++++[removeFooterView");
                        ((ListView)listView).removeFooterView(loadingView);
                    }
                }

                final HistoryNextPageLoader this$0;

            
            {
                this$0 = HistoryNextPageLoader.this;
                super();
            }
            }
);
    }

    private void tryShowNextPage()
    {
        if(nextItemList == null)
        {
            if(!loadingLock())
                applyLoadedShow();
            else
                loading();
        } else
        {
            showNextPage(nextItemList);
        }
    }

    protected abstract MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist);

    public JSONObject getParams()
    {
        return params;
    }

    public void onEnd(HttpGroup.HttpResponse httpresponse)
    {
        ArrayList arraylist;
        pageNum.intValue();
        arraylist = toList(httpresponse);
        if(arraylist != null) goto _L2; else goto _L1
_L1:
        showError();
_L4:
        return;
_L2:
        nextItemList = arraylist;
        if(loadedShow())
            showNextPage(arraylist);
        if(true) goto _L4; else goto _L3
_L3:
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
    private String functionId;
    protected HttpGroup httpGroup;
    private AbsListView listView;
    private boolean loadedLastPage;
    private boolean loadedShow;
    private boolean loading;
    private View loadingView;
    private MyActivity myActivity;
    private ArrayList nextItemList;
    protected Integer pageNum;
    protected String pageNumParamKey;
    protected Integer pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    private ArrayList showItemList;






}
