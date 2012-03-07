// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity.show;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.text.style.*;
import android.view.View;
import android.widget.Button;
import com.jindong.app.mall.entity.Product;

public class ProductShow
{

    public ProductShow(Context context1, Product product1)
    {
        context = context1;
        colorSpanRed = new ForegroundColorSpan(context1.getResources().getColor(0x7f070005));
        colorSpanGray = new ForegroundColorSpan(context1.getResources().getColor(0x7f070006));
        product = product1;
    }

    public static void shareProduct(final Button button, final Product product)
    {
        button.setText("\u5206\u4EAB");
        button.setVisibility(0);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "\u55E8\uFF0C\u6211\u5728\u4EAC\u4E1C\u5546\u57CE\u53D1\u73B0\u4E2A\u597D\u4E1C\u4E1C\uFF0C\u8FD8\u633A\u4FBF\u5B9C");
                intent.putExtra("android.intent.extra.TEXT", (new StringBuilder("\u4EAC\u4E1C\u5546\u57CE\u5356\u7684\u201C")).append(product.getName()).append("\u201D\u4E0D\u9519\u54E6\uFF0Chttp://www.360buy.com/product/").append(product.getId()).append(".html").toString());
                ((Activity)button.getContext()).startActivity(Intent.createChooser(intent, "\u5206\u4EAB\u5230\uFF1A"));
            }

            private final Button val$button;
            private final Product val$product;

            
            {
                product = product1;
                button = button1;
                super();
            }
        }
);
    }

    public String getImgUrl(int i)
    {
        return product.popImgUrl(i).toString();
    }

    public CharSequence getJdPrice()
    {
        CharSequence charsequence = context.getResources().getText(0x7f090042);
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder((new StringBuilder()).append(charsequence).append(product.getJdPrice()).toString());
        int i = charsequence.length() - 1;
        int j = spannablestringbuilder.length();
        spannablestringbuilder.setSpan(new RelativeSizeSpan(1.2F), 0, j, 33);
        spannablestringbuilder.setSpan(colorSpanRed, i, j, 33);
        return spannablestringbuilder;
    }

    public CharSequence getMarketPrice()
    {
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(product.getMarketPrice());
        spannablestringbuilder.setSpan(colorSpanGray, 0, spannablestringbuilder.length(), 17);
        spannablestringbuilder.setSpan(new StrikethroughSpan(), 0, spannablestringbuilder.length(), 33);
        spannablestringbuilder.insert(0, context.getResources().getText(0x7f090043));
        return spannablestringbuilder;
    }

    public CharSequence getNameAndAdWord()
    {
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder((new StringBuilder(String.valueOf(product.getName()))).append(product.getAdWord()).toString());
        int i = product.getName().length();
        int j = spannablestringbuilder.length();
        spannablestringbuilder.setSpan(new RelativeSizeSpan(1.2F), 0, i, 33);
        if(product.getAdWord().length() > 0)
            spannablestringbuilder.setSpan(colorSpanRed, i, j, 33);
        if(product.isPromotion().booleanValue())
        {
            spannablestringbuilder.append(" ");
            spannablestringbuilder.setSpan(new ImageSpan(context, 0x7f020043), j, spannablestringbuilder.length(), 33);
        }
        return spannablestringbuilder;
    }

    public CharSequence getNameAndZeng()
    {
        SpannableStringBuilder spannablestringbuilder;
        int i;
        int j;
        if(product.getName().length() > 40)
        {
            spannablestringbuilder = new SpannableStringBuilder((new StringBuilder(String.valueOf(product.getName().substring(0, 40)))).append("he").toString());
            i = product.getName().substring(0, 40).length();
        } else
        {
            spannablestringbuilder = new SpannableStringBuilder((new StringBuilder(String.valueOf(product.getName()))).append("he").toString());
            i = product.getName().length();
        }
        j = spannablestringbuilder.length();
        spannablestringbuilder.setSpan(new RelativeSizeSpan(1.0F), 0, i, 33);
        spannablestringbuilder.setSpan(colorSpanRed, i, j, 33);
        spannablestringbuilder.setSpan(new ImageSpan(context, 0x7f020043), j, j, 33);
        return spannablestringbuilder;
    }

    public String getUserClass()
    {
        return product.getUserClass();
    }

    public String getUserName()
    {
        return product.getUsername();
    }

    public CharSequence getUserPrice()
    {
        String s = (new StringBuilder(String.valueOf(product.getUserPriceLabel()))).append("\uFF1A\uFFE5").toString();
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder((new StringBuilder()).append(s).append(product.getUserPriceContent()).toString());
        int i = s.length() - 1;
        int j = spannablestringbuilder.length();
        spannablestringbuilder.setSpan(new RelativeSizeSpan(1.2F), 0, j, 33);
        spannablestringbuilder.setSpan(colorSpanRed, i, j, 33);
        return spannablestringbuilder;
    }

    private ForegroundColorSpan colorSpanGray;
    private ForegroundColorSpan colorSpanRed;
    private Context context;
    private Product product;
}
