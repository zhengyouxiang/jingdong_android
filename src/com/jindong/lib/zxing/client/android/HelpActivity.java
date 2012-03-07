// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.jindong.app.mall.utils.Log;

public final class HelpActivity extends Activity
{
    private final class HelpClient extends WebViewClient
    {

        public void onPageFinished(WebView webview, String s)
        {
            setTitle(webview.getTitle());
            backButton.setEnabled(webview.canGoBack());
        }

        public boolean shouldOverrideUrlLoading(WebView webview, String s)
        {
            boolean flag;
            if(s.startsWith("file"))
            {
                flag = false;
            } else
            {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                flag = true;
            }
            return flag;
        }

        final HelpActivity this$0;

        private HelpClient()
        {
            this$0 = HelpActivity.this;
            super();
        }

        HelpClient(HelpClient helpclient)
        {
            this();
        }
    }


    public HelpActivity()
    {
    }

    private void checkBuggyDevice()
    {
        String s;
        s = Build.MODEL;
        if(Log.I)
            Log.i(TAG, (new StringBuilder("Build model is ")).append(s).toString());
        if(s == null) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        int j;
        as = BUGGY_MODEL_SUBSTRINGS;
        i = as.length;
        j = 0;
_L6:
        if(j < i) goto _L3; else goto _L2
_L2:
        return;
_L3:
        if(!s.contains(as[j]))
            break; /* Loop/switch isn't completed */
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(0x7f09020a);
        builder.setPositiveButton(0x7f0901ee, groupsListener);
        builder.setNegativeButton(0x7f0901e5, null);
        builder.show();
        if(true) goto _L2; else goto _L4
_L4:
        j++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03002e);
        webView = (WebView)findViewById(0x7f0c00ef);
        webView.setWebViewClient(new HelpClient(null));
        Intent intent = getIntent();
        if(bundle != null && bundle.getBoolean("webview_state_present", false))
            webView.restoreState(bundle);
        else
        if(intent != null)
        {
            String s = intent.getStringExtra("requested_page_key");
            if(s != null && s.length() > 0)
                webView.loadUrl((new StringBuilder("file:///android_asset/html/")).append(s).toString());
            else
                webView.loadUrl("file:///android_asset/html/index.html");
        } else
        {
            webView.loadUrl("file:///android_asset/html/index.html");
        }
        backButton = (Button)findViewById(0x7f0c00f0);
        backButton.setOnClickListener(backListener);
        findViewById(0x7f0c00f1).setOnClickListener(doneListener);
        if(!initialized)
        {
            initialized = true;
            checkBuggyDevice();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4 && webView.canGoBack())
        {
            webView.goBack();
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        String s = webView.getUrl();
        if(s != null && s.length() > 0)
        {
            webView.saveState(bundle);
            bundle.putBoolean("webview_state_present", true);
        }
    }

    private static final String BASE_URL = "file:///android_asset/html/";
    private static final String BUGGY_MODEL_SUBSTRINGS[];
    private static final Uri BUGGY_URI = Uri.parse("http://code.google.com/p/zxing/wiki/FrequentlyAskedQuestions");
    public static final String DEFAULT_PAGE = "index.html";
    public static final String REQUESTED_PAGE_KEY = "requested_page_key";
    private static final String TAG = com/jindong/lib/zxing/client/android/HelpActivity.getSimpleName();
    private static final String WEBVIEW_STATE_PRESENT = "webview_state_present";
    public static final String WHATS_NEW_PAGE = "whatsnew.html";
    private static boolean initialized = false;
    private Button backButton;
    private final android.view.View.OnClickListener backListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            webView.goBack();
        }

        final HelpActivity this$0;

            
            {
                this$0 = HelpActivity.this;
                super();
            }
    }
;
    private final android.view.View.OnClickListener doneListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            finish();
        }

        final HelpActivity this$0;

            
            {
                this$0 = HelpActivity.this;
                super();
            }
    }
;
    private final android.content.DialogInterface.OnClickListener groupsListener = new android.content.DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Intent intent = new Intent("android.intent.action.VIEW", HelpActivity.BUGGY_URI);
            intent.addFlags(0x80000);
            startActivity(intent);
        }

        final HelpActivity this$0;

            
            {
                this$0 = HelpActivity.this;
                super();
            }
    }
;
    private WebView webView;

    static 
    {
        String as[] = new String[4];
        as[0] = "Desire";
        as[1] = "Pulse";
        as[2] = "Geeksphone";
        as[3] = "supersonic";
        BUGGY_MODEL_SUBSTRINGS = as;
    }



}
