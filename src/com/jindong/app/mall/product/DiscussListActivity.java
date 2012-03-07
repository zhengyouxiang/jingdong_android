// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.OrderComment;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.product:
//            DiscussDetailActivity

public class DiscussListActivity extends MyActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public DiscussListActivity()
    {
    }

    private void showOrderCommentList()
    {
        (new NextPageLoader(this, orderCommentList, loadingLayout, "orderComment", params) {

            protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
            {
                String as[] = new String[3];
                as[0] = "title";
                as[1] = "creationTime";
                as[2] = "replyCount";
                int ai[] = new int[3];
                ai[0] = 0x7f0c0094;
                ai[1] = 0x7f0c0096;
                ai[2] = 0x7f0c0097;
                return new MySimpleAdapter(myactivity, arraylist, 0x7f030021, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        if(i % 2 == 1)
                            view1.setBackgroundResource(0x7f0200a2);
                        else
                            view1.setBackgroundResource(0x7f0200a3);
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
                    Log.d("OrderCommentListActivity", "showError() -->> ?");
            }

            protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                ArrayList arraylist1 = OrderComment.toList(jsonobjectproxy.getJSONArray("orderCommentList"), 0);
                ArrayList arraylist = arraylist1;
_L2:
                return arraylist;
                JSONException jsonexception;
                jsonexception;
                if(Log.D)
                    Log.d("OrderCommentListActivity", "JSONException -->> ", jsonexception);
                arraylist = null;
                if(true) goto _L2; else goto _L1
_L1:
            }

            final DiscussListActivity this$0;

            
            {
                this$0 = DiscussListActivity.this;
                super(myactivity, abslistview, view, s, jsonobject);
            }
        }
).showPageOne();
        orderCommentList.setOnItemClickListener(this);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030020);
        params = new JSONObject();
        Intent intent = getIntent();
        wareId = intent.getExtras().getLong("id");
        name = intent.getExtras().getString("name");
        TextView textview;
        Object aobj[];
        try
        {
            params.put("wareId", String.valueOf(wareId));
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        orderCommentList = (ListView)findViewById(0x7f0c0093);
        textview = (TextView)findViewById(0x7f0c02c7);
        if(name.length() > 14)
            subName = (new StringBuilder(String.valueOf(name.substring(0, 14)))).append("...").toString();
        else
            subName = name;
        aobj = new Object[1];
        aobj[0] = subName;
        textview.setText(getString(0x7f0900d4, aobj));
        loadingLayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        loadingLayout.setGravity(17);
        orderCommentList.addFooterView(loadingLayout);
        showOrderCommentList();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent(this, com/jindong/app/mall/product/DiscussDetailActivity);
        OrderComment ordercomment = (OrderComment)adapterview.getAdapter().getItem(i);
        if(ordercomment != null)
        {
            Bundle bundle = new Bundle();
            bundle.putString("id", ordercomment.getId());
            intent.putExtras(bundle);
            startActivityInFrame(intent);
        }
    }

    private LinearLayout loadingLayout;
    private String name;
    private ListView orderCommentList;
    private JSONObject params;
    private String subName;
    private long wareId;
}
