// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.book;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.*;
import android.text.Editable;
import android.view.*;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.*;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.AndroidHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.json.*;

// Referenced classes of package com.jindong.lib.zxing.client.android.book:
//            SearchBookContentsResult, BrowseBookListener, SearchBookContentsAdapter

public final class SearchBookContentsActivity extends Activity
{
    private static final class NetworkThread extends Thread
    {

        private static String getCookie(String s)
        {
            String s1 = CookieManager.getInstance().getCookie(s);
            if(s1 != null && s1.length() != 0) goto _L2; else goto _L1
_L1:
            HttpHead httphead;
            AndroidHttpClient androidhttpclient;
            if(Log.D)
                Log.d(SearchBookContentsActivity.TAG, "Book Search cookie was missing or expired");
            httphead = new HttpHead(s);
            androidhttpclient = AndroidHttpClient.newInstance("ZXing (Android)");
            HttpResponse httpresponse = androidhttpclient.execute(httphead);
            if(httpresponse.getStatusLine().getStatusCode() != 200) goto _L4; else goto _L3
_L3:
            Header aheader[];
            int i;
            int j;
            aheader = httpresponse.getHeaders("set-cookie");
            i = aheader.length;
            j = 0;
_L7:
            if(j < i) goto _L6; else goto _L5
_L5:
            String s2;
            CookieSyncManager.getInstance().sync();
            s2 = CookieManager.getInstance().getCookie(s);
            s1 = s2;
_L4:
            androidhttpclient.close();
_L2:
            return s1;
_L6:
            Header header = aheader[j];
            CookieManager.getInstance().setCookie(s, header.getValue());
            j++;
              goto _L7
            IOException ioexception;
            ioexception;
            if(Log.W)
                Log.w(SearchBookContentsActivity.TAG, "Error setting book search cookie", ioexception);
              goto _L4
        }

        private static String getEncoding(HttpEntity httpentity)
        {
            return "windows-1252";
        }

        public void run()
        {
            AndroidHttpClient androidhttpclient = null;
            if(!isbn.startsWith("http://google.com/books?id=")) goto _L2; else goto _L1
_L1:
            URI uri;
            int i = isbn.indexOf('=');
            String s = isbn.substring(i + 1);
            uri = new URI("http", null, "www.google.com", -1, "/books", (new StringBuilder("id=")).append(s).append("&jscmd=SearchWithinVolume2&q=").append(query).toString(), null);
_L5:
            HttpResponse httpresponse;
            HttpGet httpget = new HttpGet(uri);
            httpget.setHeader("cookie", getCookie(uri.toString()));
            androidhttpclient = AndroidHttpClient.newInstance("ZXing (Android)");
            httpresponse = androidhttpclient.execute(httpget);
            if(httpresponse.getStatusLine().getStatusCode() != 200) goto _L4; else goto _L3
_L3:
            HttpEntity httpentity = httpresponse.getEntity();
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            httpentity.writeTo(bytearrayoutputstream);
            bytearrayoutputstream.flush();
            JSONObject jsonobject = new JSONObject(bytearrayoutputstream.toString(getEncoding(httpentity)));
            bytearrayoutputstream.close();
            Message message = Message.obtain(handler, 0x7f0c000b);
            message.obj = jsonobject;
            message.sendToTarget();
_L6:
            if(androidhttpclient != null)
                androidhttpclient.close();
_L7:
            return;
_L2:
            uri = new URI("http", null, "www.google.com", -1, "/books", (new StringBuilder("vid=isbn")).append(isbn).append("&jscmd=SearchWithinVolume2&q=").append(query).toString(), null);
              goto _L5
_L4:
            if(Log.W)
                Log.w(SearchBookContentsActivity.TAG, (new StringBuilder("HTTP returned ")).append(httpresponse.getStatusLine().getStatusCode()).append(" for ").append(uri).toString());
            Message.obtain(handler, 0x7f0c000a).sendToTarget();
              goto _L6
            Exception exception1;
            exception1;
            if(Log.W)
                Log.w(SearchBookContentsActivity.TAG, "Error accessing book search", exception1);
            Message.obtain(handler, 0x7f0c000a).sendToTarget();
            if(androidhttpclient != null)
                androidhttpclient.close();
              goto _L7
            Exception exception;
            exception;
            if(androidhttpclient != null)
                androidhttpclient.close();
            throw exception;
              goto _L5
        }

        private final Handler handler;
        private final String isbn;
        private final String query;

        NetworkThread(String s, String s1, Handler handler1)
        {
            isbn = s;
            query = s1;
            handler = handler1;
        }
    }


    public SearchBookContentsActivity()
    {
    }

    private void handleSearchResults(JSONObject jsonobject)
    {
        try
        {
            int i = jsonobject.getInt("number_of_results");
            TextView textview = headerView;
            StringBuilder stringbuilder = new StringBuilder("Found ");
            String s;
            if(i == 1)
                s = "1 result";
            else
                s = (new StringBuilder(String.valueOf(i))).append(" results").toString();
            textview.setText(stringbuilder.append(s).toString());
            if(i > 0)
            {
                JSONArray jsonarray = jsonobject.getJSONArray("search_results");
                SearchBookContentsResult.setQuery(queryTextView.getText().toString());
                ArrayList arraylist = new ArrayList(i);
                int j = 0;
                do
                {
                    if(j >= i)
                    {
                        resultListView.setOnItemClickListener(new BrowseBookListener(this, arraylist));
                        resultListView.setAdapter(new SearchBookContentsAdapter(this, arraylist));
                        break;
                    }
                    arraylist.add(parseResult(jsonarray.getJSONObject(j)));
                    j++;
                } while(true);
            } else
            {
                if("false".equals(jsonobject.optString("searchable")))
                    headerView.setText(0x7f09021b);
                resultListView.setAdapter(null);
            }
        }
        catch(JSONException jsonexception)
        {
            if(Log.W)
                Log.w(TAG, "Bad JSON from book search", jsonexception);
            resultListView.setAdapter(null);
            headerView.setText(0x7f09021c);
        }
    }

    private void launchSearch()
    {
        if(networkThread == null)
        {
            String s = queryTextView.getText().toString();
            if(s != null && s.length() > 0)
            {
                networkThread = new NetworkThread(isbn, s, handler);
                networkThread.start();
                headerView.setText(0x7f09021f);
                resultListView.setAdapter(null);
                queryTextView.setEnabled(false);
                queryButton.setEnabled(false);
            }
        }
    }

    private SearchBookContentsResult parseResult(JSONObject jsonobject)
    {
        SearchBookContentsResult searchbookcontentsresult;
        boolean flag;
        String s5;
        String s = jsonobject.getString("page_id");
        String s1 = jsonobject.getString("page_number");
        String s2;
        String s3;
        String s6;
        String s7;
        String s8;
        String s9;
        if(s1.length() > 0)
            s2 = (new StringBuilder(String.valueOf(getString(0x7f09021e)))).append(' ').append(s1).toString();
        else
            s2 = getString(0x7f090221);
        s3 = jsonobject.optString("snippet_text");
        flag = true;
        if(s3.length() <= 0) goto _L2; else goto _L1
_L1:
        s6 = TAG_PATTERN.matcher(s3).replaceAll("");
        s7 = LT_ENTITY_PATTERN.matcher(s6).replaceAll("<");
        s8 = GT_ENTITY_PATTERN.matcher(s7).replaceAll(">");
        s9 = QUOTE_ENTITY_PATTERN.matcher(s8).replaceAll("'");
        s5 = QUOT_ENTITY_PATTERN.matcher(s9).replaceAll("\"");
_L3:
        searchbookcontentsresult = new SearchBookContentsResult(s, s2, s5, flag);
        break MISSING_BLOCK_LABEL_254;
_L2:
        String s4 = (new StringBuilder(String.valueOf('('))).append(getString(0x7f090220)).append(')').toString();
        s5 = s4;
        flag = false;
          goto _L3
        JSONException jsonexception;
        jsonexception;
        searchbookcontentsresult = new SearchBookContentsResult(getString(0x7f09021d), "", "", false);
        return searchbookcontentsresult;
    }

    private void resetForNewQuery()
    {
        networkThread = null;
        queryTextView.setEnabled(true);
        queryTextView.selectAll();
        queryButton.setEnabled(true);
    }

    String getISBN()
    {
        return isbn;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeExpiredCookie();
        Intent intent = getIntent();
        if(intent == null || !intent.getAction().equals("com.jindong.lib.zxing.client.android.SEARCH_BOOK_CONTENTS"))
        {
            finish();
        } else
        {
            isbn = intent.getStringExtra("ISBN");
            String s;
            if(isbn.startsWith("http://google.com/books?id="))
                setTitle(getString(0x7f090241));
            else
                setTitle((new StringBuilder(String.valueOf(getString(0x7f090241)))).append(": ISBN ").append(isbn).toString());
            setContentView(0x7f03007e);
            queryTextView = (EditText)findViewById(0x7f0c029a);
            s = intent.getStringExtra("QUERY");
            if(s != null && s.length() > 0)
                queryTextView.setText(s);
            queryTextView.setOnKeyListener(keyListener);
            queryButton = (Button)findViewById(0x7f0c029b);
            queryButton.setOnClickListener(buttonListener);
            resultListView = (ListView)findViewById(0x7f0c029c);
            headerView = (TextView)LayoutInflater.from(this).inflate(0x7f03007f, resultListView, false);
            resultListView.addHeaderView(headerView);
        }
    }

    protected void onResume()
    {
        super.onResume();
        queryTextView.selectAll();
    }

    private static final Pattern GT_ENTITY_PATTERN = Pattern.compile("&gt;");
    private static final Pattern LT_ENTITY_PATTERN = Pattern.compile("&lt;");
    private static final Pattern QUOTE_ENTITY_PATTERN = Pattern.compile("&#39;");
    private static final Pattern QUOT_ENTITY_PATTERN = Pattern.compile("&quot;");
    private static final String TAG = com/jindong/lib/zxing/client/android/book/SearchBookContentsActivity.getSimpleName();
    private static final Pattern TAG_PATTERN = Pattern.compile("\\<.*?\\>");
    private static final String USER_AGENT = "ZXing (Android)";
    private final android.view.View.OnClickListener buttonListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            launchSearch();
        }

        final SearchBookContentsActivity this$0;

            
            {
                this$0 = SearchBookContentsActivity.this;
                super();
            }
    }
;
    private final Handler handler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 2131492874 2131492875: default 28
        //                       2131492874 53
        //                       2131492875 29;
               goto _L1 _L2 _L3
_L1:
            return;
_L3:
            handleSearchResults((JSONObject)message.obj);
            resetForNewQuery();
            continue; /* Loop/switch isn't completed */
_L2:
            resetForNewQuery();
            headerView.setText(0x7f09021c);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final SearchBookContentsActivity this$0;

            
            {
                this$0 = SearchBookContentsActivity.this;
                super();
            }
    }
;
    private TextView headerView;
    private String isbn;
    private final android.view.View.OnKeyListener keyListener = new android.view.View.OnKeyListener() {

        public boolean onKey(View view, int i, KeyEvent keyevent)
        {
            boolean flag;
            if(i == 66)
            {
                launchSearch();
                flag = true;
            } else
            {
                flag = false;
            }
            return flag;
        }

        final SearchBookContentsActivity this$0;

            
            {
                this$0 = SearchBookContentsActivity.this;
                super();
            }
    }
;
    private NetworkThread networkThread;
    private Button queryButton;
    private EditText queryTextView;
    private ListView resultListView;






}
