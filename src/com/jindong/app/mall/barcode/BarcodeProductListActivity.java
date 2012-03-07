// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jindong.app.mall.entity.BarcodeRecord;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.MyActivity;
import com.jindong.app.mall.utils.MySimpleAdapter;
import java.util.ArrayList;
import java.util.List;

public class BarcodeProductListActivity extends MyActivity
{

    public BarcodeProductListActivity()
    {
    }

    private void findView()
    {
        listView = (ListView)findViewById(0x7f0c002c);
    }

    private void init()
    {
        Bundle bundle = getIntent().getExtras();
        productList = (ArrayList)bundle.getSerializable("productList");
        barcodeRecord = (BarcodeRecord)bundle.getSerializable("barcodeRecord");
        MySimpleAdapter mysimpleadapter = new MySimpleAdapter(this, productList, 0x7f03000b, new String[0], new int[0]) {

            public View getView(int i, View view, ViewGroup viewgroup)
            {
                View view1 = super.getView(i, view, viewgroup);
                Product product;
                ProductShow productshow;
                if(i % 2 == 1)
                    view1.setBackgroundResource(0x7f0200a2);
                else
                    view1.setBackgroundResource(0x7f0200a3);
                product = (Product)getItem(i);
                productshow = new ProductShow(BarcodeProductListActivity.this, product);
                ((TextView)view1.findViewById(0x7f0c002f)).setText(productshow.getNameAndAdWord());
                return view1;
            }

            final BarcodeProductListActivity this$0;

            
            {
                this$0 = BarcodeProductListActivity.this;
                super(myactivity, list, i, as, ai);
            }
        }
;
        listView.setAdapter(mysimpleadapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                Intent intent = new Intent(BarcodeProductListActivity.this, com/jindong/app/mall/product/ProductDetailActivity);
                Product product = (Product)((Adapter)adapterview.getAdapter()).getItem(i);
                Bundle bundle1 = new Bundle();
                bundle1.putLong("id", product.getId().longValue());
                bundle1.putString("title", product.getName());
                intent.putExtras(bundle1);
                startActivityInFrame(intent);
            }

            final BarcodeProductListActivity this$0;

            
            {
                this$0 = BarcodeProductListActivity.this;
                super();
            }
        }
);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000a);
        findView();
        init();
        ((TextView)findViewById(0x7f0c02c7)).setText((new StringBuilder("\u6761\u7801\u201C")).append(barcodeRecord.getContent()).append("\u201D\u7684\u76F8\u5173\u5546\u54C1\uFF1A").toString());
    }

    private BarcodeRecord barcodeRecord;
    private ListView listView;
    private ArrayList productList;
}
