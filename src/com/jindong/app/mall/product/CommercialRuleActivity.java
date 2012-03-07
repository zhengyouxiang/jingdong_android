// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.jindong.app.mall.entity.Commercial;
import com.jindong.app.mall.utils.MyActivity;

public class CommercialRuleActivity extends MyActivity
{

    public CommercialRuleActivity()
    {
    }

    private void showCommercialRule()
    {
        ((TextView)findViewById(0x7f0c0130)).setVisibility(8);
        post(new Runnable() {

            public void run()
            {
                jdnewsDetailTitle.setText(title);
                jdnewsDetailContent.setText(detail);
            }

            final CommercialRuleActivity this$0;
            private final TextView val$jdnewsDetailContent;
            private final TextView val$jdnewsDetailTitle;

            
            {
                this$0 = CommercialRuleActivity.this;
                jdnewsDetailTitle = textview;
                jdnewsDetailContent = textview1;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03003b);
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f09016e);
        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        detail = intent.getExtras().getString("detail");
        showCommercialRule();
    }

    private Commercial commercial;
    private String detail;
    private String title;


}
