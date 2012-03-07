// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.more;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.entity.KeyWord;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

public class SearchActivity extends MyActivity
{

    public SearchActivity()
    {
    }

    private void searchSubmit(String s, String s1)
    {
        if(!TextUtils.isEmpty(s) || s.trim().length() != 0)
        {
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putString("keyWord", s);
            bundle.putString("searchway", s1);
            intent.putExtras(bundle);
            setResult(-1, intent);
            autoCompleteTextView.clearComposingText();
            finish();
        }
    }

    private void showDialog()
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

                final SearchActivity this$0;

            
            {
                this$0 = SearchActivity.this;
                super();
            }
            }
);
            post(new Runnable() {

                public void run()
                {
                    dialogBuilder.show();
                }

                final SearchActivity this$0;

            
            {
                this$0 = SearchActivity.this;
                super();
            }
            }
);
        }
    }

    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PROMPT", "");
        startActivityForResult(intent, 1234);
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
                String s = items[l];
                searchSubmit(s, "voice");
                listDialog.dismiss();
            }

            final SearchActivity this$0;
            private final String val$items[];

            
            {
                this$0 = SearchActivity.this;
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

            final SearchActivity this$0;

            
            {
                this$0 = SearchActivity.this;
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

    public void onClickHand(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131493527 2131493528: default 28
    //                   2131493527 50
    //                   2131493528 29;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        searchSubmit(autoCompleteTextView.getText().toString(), "text");
        continue; /* Loop/switch isn't completed */
_L2:
        showDialog();
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03007d);
        fromMenuFlag = getIntent().getBooleanExtra("fromMenuFlag", false);
        dialogBuilder = new android.app.AlertDialog.Builder(this);
        searchCleanButton = (ImageButton)findViewById(0x7f0c0299);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(0x7f0c00f5);
        listView = (ListView)findViewById(0x7f0c00fa);
        autoCompleteTextView.setOnKeyListener(new android.view.View.OnKeyListener() {

            public boolean onKey(View view, int i, KeyEvent keyevent)
            {
                boolean flag;
                if(i == 66)
                {
                    searchSubmit(autoCompleteTextView.getText().toString(), "text");
                    flag = true;
                } else
                {
                    flag = false;
                }
                return flag;
            }

            final SearchActivity this$0;

            
            {
                this$0 = SearchActivity.this;
                super();
            }
        }
);
        autoCompleteTextView.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                autoCompleteTextView.requestFocus();
                return false;
            }

            final SearchActivity this$0;

            
            {
                this$0 = SearchActivity.this;
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
                if(TextUtils.isEmpty(charsequence))
                {
                    searchCleanButton.setVisibility(8);
                    listView.setVisibility(8);
                } else
                {
                    searchCleanButton.setVisibility(0);
                    searchCleanButton.setOnClickListener(new android.view.View.OnClickListener() {

                        public void onClick(View view)
                        {
                            autoCompleteTextView.setText(null);
                            searchCleanButton.setVisibility(8);
                        }

                        final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                    }
);
                }
                if(!TextUtils.isEmpty(charsequence) || charsequence.toString().trim().length() != 0) goto _L2; else goto _L1
_L1:
                listView.post(new Runnable() {

                    public void run()
                    {
                        listView.setVisibility(8);
                    }

                    final _cls3 this$1;

                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                }
);
_L4:
                return;
_L2:
                listView.setVisibility(0);
                JSONObject jsonobject = new JSONObject();
                try
                {
                    jsonobject.put("keyword", charsequence.toString());
                }
                catch(JSONException jsonexception)
                {
                    if(Log.V)
                        Log.v("SearchActivity", jsonexception.getMessage());
                }
                getHttpGroupaAsynPool().add("tip", jsonobject, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        try
                        {
                            com.jindong.app.mall.utils.JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("tip");
                            if(jsonarraypoxy.length() >= 1)
                            {
                                java.util.LinkedList linkedlist = KeyWord.toList(jsonarraypoxy, 0);
                                SearchActivity searchactivity = _fld0;
                                String as[] = new String[2];
                                as[0] = "name";
                                as[1] = "countString";
                                int ai[] = new int[2];
                                ai[0] = 0x7f0c010e;
                                ai[1] = 0x7f0c010f;
                                final MySimpleAdapter arrayAdapter = new MySimpleAdapter(searchactivity, linkedlist, 0x7f030031, as, ai);
                                listView.post(new Runnable() {

                                    public void run()
                                    {
                                        listView.setAdapter(arrayAdapter);
                                        listView.invalidate();
                                    }

                                    final _cls3 this$2;
                                    private final MySimpleAdapter val$arrayAdapter;

                        
                        {
                            this$2 = _cls3.this;
                            arrayAdapter = mysimpleadapter;
                            super();
                        }
                                }
);
                                listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                                    public void onItemClick(AdapterView adapterview, View view, int l, long l1)
                                    {
                                        KeyWord keyword = (KeyWord)((Adapter)adapterview.getAdapter()).getItem(l);
                                        autoCompleteTextView.setText(keyword.getName());
                                        searchSubmit(keyword.getName(), "text");
                                        listView.setVisibility(8);
                                    }

                                    final _cls3 this$2;

                        
                        {
                            this$2 = _cls3.this;
                            super();
                        }
                                }
);
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

                    final _cls3 this$1;


                    
                    {
                        this$1 = _cls3.this;
                        super();
                    }
                }
);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final SearchActivity this$0;


            
            {
                this$0 = SearchActivity.this;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                autoCompleteTextView.setText(keyword);
            }

            final SearchActivity this$0;
            private final String val$keyword;

            
            {
                this$0 = SearchActivity.this;
                keyword = s;
                super();
            }
        }
, 50);
    }

    protected void onResume()
    {
        autoCompleteTextView.requestFocus();
        super.onResume();
    }

    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView2;
    private android.app.AlertDialog.Builder dialogBuilder;
    boolean fromMenuFlag;
    private AlertDialog listDialog;
    ListView listView;
    Button mVoiceSearchButton;
    ImageButton searchCleanButton;




}
