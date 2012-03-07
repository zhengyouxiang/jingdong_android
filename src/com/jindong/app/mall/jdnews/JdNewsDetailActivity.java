// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.jdnews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.jindong.app.mall.entity.JdNews;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class JdNewsDetailActivity extends MyActivity
{

    public JdNewsDetailActivity()
    {
    }

    private void showJdNewsDetials()
    {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("jdNewsId", jdNewsId);
_L2:
        com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                ArrayList arraylist = JdNews.toList(httpresponse.getJSONObject().getJSONArrayOrNull("jdNews"), 1);
                if(arraylist != null && arraylist.size() > 0)
                {
                    final JdNews jdNews = (JdNews)arraylist.get(0);
                    final TextView jdnewsDetailTitle = (TextView)findViewById(0x7f0c012f);
                    final TextView jdnewsDetailTime = (TextView)findViewById(0x7f0c0130);
                    final TextView jdnewsDetailContent = (TextView)findViewById(0x7f0c0132);
                    post(new Runnable() {

                        public void run()
                        {
                            jdnewsDetailTitle.setText(jdNews.getTitle());
                            jdnewsDetailTime.setText(jdNews.getAddTime());
                            jdnewsDetailContent.setText(jdNews.getContent());
                        }

                        final _cls1 this$1;
                        private final JdNews val$jdNews;
                        private final TextView val$jdnewsDetailContent;
                        private final TextView val$jdnewsDetailTime;
                        private final TextView val$jdnewsDetailTitle;

                    
                    {
                        this$1 = _cls1.this;
                        jdnewsDetailTitle = textview;
                        jdNews = jdnews;
                        jdnewsDetailTime = textview1;
                        jdnewsDetailContent = textview2;
                        super();
                    }
                    }
);
                }
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("JdNewsDetailActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("JdNewsDetailActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("JdNewsDetailActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("JdNewsDetailActivity", "showJdNewsDetials()-start");
            }

            final JdNewsDetailActivity this$0;

            
            {
                this$0 = JdNewsDetailActivity.this;
                super();
            }
        }
;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("jdNews");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(onalllistener);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("JdNewsDetailActivity", jsonexception.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03003b);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090040);
        jdNewsId = getIntent().getExtras().getString("jdNewsId");
        if(Log.D)
            Log.d("JdNewsDetailActivity", (new StringBuilder("jdNewsId=")).append(jdNewsId).toString());
        showJdNewsDetials();
    }

    private String jdNewsId;
    private String jdNewsTitle;
    private JSONArrayPoxy jsonArray;
}
