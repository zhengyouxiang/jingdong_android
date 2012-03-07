// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.jdnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.JdNews;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

// Referenced classes of package com.jindong.app.mall.jdnews:
//            JdNewsDetailActivity

public class JdNewsListActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public JdNewsListActivity()
    {
    }

    private void showJdNewsList()
    {
        (new NextPageLoader(this, jdNewsList, loadingLayout, "jdNews") {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                String as[] = new String[2];
                as[0] = "title";
                as[1] = "addTime";
                int ai[] = new int[2];
                ai[0] = 0x7f0c0136;
                ai[1] = 0x7f0c0137;
                return new MySimpleAdapter(myactivity, arraylist, 0x7f03003d, as, ai) {

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
            }

            protected void showError()
            {
                if(Log.D)
                    Log.d("JdNewsListActivity", "showError() -->> ?");
            }

            protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                ArrayList arraylist1 = JdNews.toList(jsonobjectproxy.getJSONArray("jdnewsList"), 0);
                ArrayList arraylist = arraylist1;
_L2:
                return arraylist;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d("JdNewsListActivity", "JSONException -->> ", jsonexception);
                arraylist = null;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final JdNewsListActivity this$0;

            
            {
                this$0 = JdNewsListActivity.this;
                super(myactivity, abslistview, view, s);
            }
        }
).showPageOne();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03003c);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f09003f);
        jdNewsList = (ListView)findViewById(0x7f0c0134);
        jdNewsList.setOnItemClickListener(this);
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        jdNewsList.addFooterView(loadingLayout);
        showJdNewsList();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/jdnews/JdNewsDetailActivity);
        JdNews jdnews = (JdNews)adapterview.getAdapter().getItem(i);
        Bundle bundle = new Bundle();
        bundle.putString("jdNewsId", (new StringBuilder()).append(jdnews.getJdNewsId()).toString());
        bundle.putString("title", jdnews.getTitle());
        intent.putExtras(bundle);
        startActivityInFrame(intent);
    }

    private ListView jdNewsList;
    private LinearLayout loadingLayout;
}
