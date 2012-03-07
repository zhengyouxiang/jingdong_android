// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Comment;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentListActivity extends MyActivity
{
    private class CommentNextPageLoader extends NextPageLoader
    {

        protected MySimpleAdapter createAdapter(MyActivity myactivity, ArrayList arraylist)
        {
            CommentListActivity commentlistactivity = CommentListActivity.this;
            String as[] = new String[7];
            as[0] = "title";
            as[1] = "userName";
            as[2] = "insertTime";
            as[3] = "pros";
            as[4] = "cons";
            as[5] = "content";
            as[6] = "score";
            int ai[] = new int[7];
            ai[0] = 0x7f0c0064;
            ai[1] = 0x7f0c0066;
            ai[2] = 0x7f0c0067;
            ai[3] = 0x7f0c0069;
            ai[4] = 0x7f0c006a;
            ai[5] = 0x7f0c006b;
            ai[6] = 0x7f0c0065;
            MySimpleAdapter mysimpleadapter = new MySimpleAdapter(commentlistactivity, arraylist, 0x7f030016, as, ai) {

                public View getView(int i, View view, ViewGroup viewgroup)
                {
                    View view1 = super.getView(i, view, viewgroup);
                    Comment comment = (Comment)getItem(i);
                    if(Log.D)
                        Log.d("isbook", (new StringBuilder()).append(isbook).toString());
                    if(isbook)
                    {
                        TextView textview = (TextView)view1.findViewById(0x7f0c0069);
                        TextView textview1 = (TextView)view1.findViewById(0x7f0c006a);
                        textview.setVisibility(8);
                        textview1.setVisibility(8);
                    } else
                    {
                        ((TextView)view1.findViewById(0x7f0c006b)).setText((new StringBuilder(String.valueOf(getString(0x7f09005c)))).append(comment.getContent()).toString());
                    }
                    view1.setBackgroundColor(-1);
                    return view1;
                }

                final CommentNextPageLoader this$1;

                
                {
                    this$1 = CommentNextPageLoader.this;
                    super(myactivity, list, i, as, ai);
                }
            }
;
            mysimpleadapter.setViewBinder(new com.jindong.app.mall.utils.SimpleBeanAdapter.ViewBinder() {

                public boolean setViewValue(View view, Object obj, String s)
                {
                    boolean flag;
                    if(view.getId() == 0x7f0c0065)
                    {
                        if(Log.D)
                            Log.d("Temp", (new StringBuilder("data -->> ")).append(obj).toString());
                        ((RatingBar)view).setRating(((Integer)obj).intValue());
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    return flag;
                }

                final CommentNextPageLoader this$1;

                
                {
                    this$1 = CommentNextPageLoader.this;
                    super();
                }
            }
);
            return mysimpleadapter;
        }

        protected void showError()
        {
        }

        protected ArrayList toList(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
        {
            JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
            ArrayList arraylist1 = Comment.toList(jsonobjectproxy.getJSONArray("commentInfoList"), 0);
            ArrayList arraylist = arraylist1;
_L2:
            return arraylist;
            JSONException jsonexception;
            jsonexception;
            if(Log.D)
                Log.d("CommentActivity", "JSONException -->> ", jsonexception);
            arraylist = null;
            if(true) goto _L2; else goto _L1
_L1:
        }

        final CommentListActivity this$0;


        public CommentNextPageLoader(MyActivity myactivity, AbsListView abslistview, View view, String s)
        {
            this$0 = CommentListActivity.this;
            super(myactivity, abslistview, view, s);
        }
    }


    public CommentListActivity()
    {
        showScoreType = 0;
    }

    private void first()
    {
        initContent();
        RadioGroup radiogroup = (RadioGroup)findViewById(0x7f0c005b);
        final RadioButton buttonAll = (RadioButton)findViewById(0x7f0c005c);
        final RadioButton buttonGood = (RadioButton)findViewById(0x7f0c005d);
        final RadioButton buttonCenter = (RadioButton)findViewById(0x7f0c005e);
        final RadioButton buttonBad = (RadioButton)findViewById(0x7f0c005f);
        radiogroup.check(0x7f0c005c);
        buttonAll.setText(0x7f090056);
        buttonGood.setText(messageGood);
        buttonCenter.setText(messageMiddle);
        buttonBad.setText(messageBad);
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(scoreCountGood);
        spannablestringbuilder.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), 0, spannablestringbuilder.length(), 33);
        buttonGood.append(spannablestringbuilder);
        SpannableStringBuilder spannablestringbuilder1 = new SpannableStringBuilder(scoreCountCenter);
        spannablestringbuilder1.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), 0, spannablestringbuilder1.length(), 33);
        buttonCenter.append(spannablestringbuilder1);
        SpannableStringBuilder spannablestringbuilder2 = new SpannableStringBuilder(scoreCountBad);
        spannablestringbuilder2.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), 0, spannablestringbuilder2.length(), 33);
        buttonBad.append(spannablestringbuilder2);
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(view != buttonGood) goto _L2; else goto _L1
_L1:
                showContent(5);
_L4:
                return;
_L2:
                if(view == buttonCenter)
                    showContent(3);
                else
                if(view == buttonBad)
                    showContent(1);
                else
                if(view == buttonAll)
                    showContent(0);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final CommentListActivity this$0;
            private final RadioButton val$buttonAll;
            private final RadioButton val$buttonBad;
            private final RadioButton val$buttonCenter;
            private final RadioButton val$buttonGood;

            
            {
                this$0 = CommentListActivity.this;
                buttonGood = radiobutton;
                buttonCenter = radiobutton1;
                buttonBad = radiobutton2;
                buttonAll = radiobutton3;
                super();
            }
        }
;
        buttonAll.setOnClickListener(onclicklistener);
        buttonGood.setOnClickListener(onclicklistener);
        buttonCenter.setOnClickListener(onclicklistener);
        buttonBad.setOnClickListener(onclicklistener);
    }

    private void initContent()
    {
        commentListContentAll = (ListView)findViewById(0x7f0c0060);
        commentListContentGood = (ListView)findViewById(0x7f0c0061);
        commentListContentMiddle = (ListView)findViewById(0x7f0c0062);
        commentListContentBad = (ListView)findViewById(0x7f0c0063);
        LinearLayout linearlayout = (LinearLayout)LayoutInflater.from(this).inflate(0x7f030041, null);
        linearlayout.setGravity(17);
        commentListContentAll.addFooterView(linearlayout);
        commentListContentGood.addFooterView(linearlayout);
        commentListContentMiddle.addFooterView(linearlayout);
        commentListContentBad.addFooterView(linearlayout);
        commentListContentAllNextPageLoader = new CommentNextPageLoader(this, commentListContentAll, linearlayout, "comment");
        commentListContentGoodNextPageLoader = new CommentNextPageLoader(this, commentListContentGood, linearlayout, "comment");
        commentListContentMiddleNextPageLoader = new CommentNextPageLoader(this, commentListContentMiddle, linearlayout, "comment");
        commentListContentBadNextPageLoader = new CommentNextPageLoader(this, commentListContentBad, linearlayout, "comment");
        setParams(commentListContentAllNextPageLoader, 0);
        setParams(commentListContentGoodNextPageLoader, 5);
        setParams(commentListContentMiddleNextPageLoader, 3);
        setParams(commentListContentBadNextPageLoader, 1);
        commentListContentAllNextPageLoader.showPageOne();
    }

    private void setParams(NextPageLoader nextpageloader, int i)
    {
        JSONObject jsonobject = nextpageloader.getParams();
        jsonobject.put("wareId", (new StringBuilder()).append(id).toString());
        jsonobject.put("score", (new StringBuilder()).append(i).toString());
_L1:
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v(com/jindong/app/mall/product/CommentListActivity.getName(), "JSONException -->> ", jsonexception);
          goto _L1
    }

    private void showContent(int i)
    {
        int j;
        j = showScoreType;
        showScoreType = i;
        j;
        JVM INSTR tableswitch 0 5: default 48
    //                   0 89
    //                   1 125
    //                   2 48
    //                   3 113
    //                   4 48
    //                   5 101;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L5
_L1:
        i;
        JVM INSTR tableswitch 0 5: default 88
    //                   0 137
    //                   1 191
    //                   2 88
    //                   3 173
    //                   4 88
    //                   5 155;
           goto _L6 _L7 _L8 _L6 _L9 _L6 _L10
_L6:
        return;
_L2:
        commentListContentAll.setVisibility(8);
          goto _L1
_L5:
        commentListContentGood.setVisibility(8);
          goto _L1
_L4:
        commentListContentMiddle.setVisibility(8);
          goto _L1
_L3:
        commentListContentBad.setVisibility(8);
          goto _L1
_L7:
        commentListContentAll.setVisibility(0);
        commentListContentAllNextPageLoader.showPageOne();
          goto _L6
_L10:
        commentListContentGood.setVisibility(0);
        commentListContentGoodNextPageLoader.showPageOne();
          goto _L6
_L9:
        commentListContentMiddle.setVisibility(0);
        commentListContentMiddleNextPageLoader.showPageOne();
          goto _L6
_L8:
        commentListContentBad.setVisibility(0);
        commentListContentBadNextPageLoader.showPageOne();
          goto _L6
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Bundle bundle1 = getIntent().getExtras();
        product = (Product)bundle1.getSerializable("product");
        id = product.getShowId().longValue();
        name = product.getName();
        isbook = product.isBook().booleanValue();
        messageGood = bundle1.getString("message0");
        messageMiddle = bundle1.getString("message1");
        messageBad = bundle1.getString("message2");
        scoreCountGood = bundle1.getString("scoreCount0");
        scoreCountCenter = bundle1.getString("scoreCount1");
        scoreCountBad = bundle1.getString("scoreCount2");
        if(Log.D)
            Log.d("temp", name);
        if(Log.D)
            Log.d("temp", scoreCountGood);
        if(Log.D)
            Log.d("temp", scoreCountCenter);
        if(Log.D)
            Log.d("temp", scoreCountBad);
        setContentView(0x7f030015);
        TextView textview = (TextView)findViewById(0x7f0c02c7);
        Object aobj[];
        if(name.length() > 14)
            subName = (new StringBuilder(String.valueOf(name.substring(0, 14)))).append("...").toString();
        else
            subName = name;
        aobj = new Object[1];
        aobj[0] = subName;
        textview.setText(getString(0x7f0900d3, aobj));
        first();
    }

    private static final int SCORE_ALL = 0;
    private static final int SCORE_BAD = 1;
    private static final int SCORE_CENTER = 3;
    private static final int SCORE_GOOD = 5;
    private ListView commentListContentAll;
    private NextPageLoader commentListContentAllNextPageLoader;
    private ListView commentListContentBad;
    private NextPageLoader commentListContentBadNextPageLoader;
    private ListView commentListContentGood;
    private NextPageLoader commentListContentGoodNextPageLoader;
    private ListView commentListContentMiddle;
    private NextPageLoader commentListContentMiddleNextPageLoader;
    private long id;
    private boolean isbook;
    private String messageBad;
    private String messageGood;
    private String messageMiddle;
    private String name;
    private Product product;
    private String scoreCountBad;
    private String scoreCountCenter;
    private String scoreCountGood;
    private int showScoreType;
    private String subName;


}
