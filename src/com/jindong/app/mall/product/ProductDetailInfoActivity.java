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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import com.jindong.app.mall.entity.Coupon;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.entity.show.ProductShow;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductDetailInfoActivity extends MyActivity
{

    public ProductDetailInfoActivity()
    {
    }

    private RelativeLayout createTabBody()
    {
        RelativeLayout relativelayout = new RelativeLayout(this);
        relativelayout.setPadding(0, DPIUtil.dip2px(5F), 0, DPIUtil.dip2px(5F));
        relativelayout.addView(new ProgressBar(this, null, 0x7f0b000b));
        return relativelayout;
    }

    private void cutBranch(View view)
    {
        nowBranchView.setVisibility(8);
        nowBranchView = view;
        nowBranchView.setVisibility(0);
    }

    private void initData()
    {
        product = (Product)getIntent().getExtras().get("product");
    }

    private void initTabComponents()
    {
        final RadioGroup radioGroupLayout = new RadioGroup(this);
        radioGroupLayout.setOrientation(0);
        final HorizontalScrollView scrollView = new HorizontalScrollView(this);
        scrollView.setId(1001);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.addView(radioGroupLayout, layoutParamsFW);
        final RelativeLayout contentLayout = new RelativeLayout(this);
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("basicInfoText");
        httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getShowId()).toString());
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy;
                int i;
                int j;
                jsonarraypoxy = httpresponse.getJSONObject().getJSONArrayOrNull("basicInfo");
                i = jsonarraypoxy.length();
                j = 0;
_L9:
                if(j < i) goto _L2; else goto _L1
_L1:
                ProductDetailInfoActivity productdetailinfoactivity = ProductDetailInfoActivity.this;
                Runnable runnable = new Runnable() {

                    public void run()
                    {
                        android.widget.RelativeLayout.LayoutParams layoutparams3 = new android.widget.RelativeLayout.LayoutParams(ProductDetailInfoActivity.layoutParamsFW);
                        layoutparams3.addRule(3, 0x7f0c000d);
                        containerView.addView(scrollView, layoutparams3);
                        android.widget.RelativeLayout.LayoutParams layoutparams4 = new android.widget.RelativeLayout.LayoutParams(ProductDetailInfoActivity.layoutParamsFF);
                        layoutparams4.addRule(3, 1001);
                        containerView.addView(contentLayout, layoutparams4);
                    }

                    final _cls1 this$1;
                    private final RelativeLayout val$contentLayout;
                    private final HorizontalScrollView val$scrollView;

                    
                    {
                        this$1 = _cls1.this;
                        scrollView = horizontalscrollview;
                        contentLayout = relativelayout;
                        super();
                    }
                }
;
                productdetailinfoactivity.post(runnable);
                  goto _L3
_L8:
                String s;
                RadioButton radiobutton;
                int k;
                JSONObjectProxy jsonobjectproxy = jsonarraypoxy.getJSONObjectOrNull(j);
                if(jsonobjectproxy == null)
                    break MISSING_BLOCK_LABEL_423;
                s = jsonobjectproxy.getStringOrNull("value");
                String s1 = jsonobjectproxy.getStringOrNull("label");
                if(s == null || "".equals(s))
                    break MISSING_BLOCK_LABEL_423;
                if(s1 == null || "".equals(s))
                    s1 = getString(0x7f090044);
                radiobutton = (RadioButton)getLayoutInflater().inflate(0x7f030074, null);
                radiobutton.setText(s1);
                k = getWindowManager().getDefaultDisplay().getWidth() - 3 * DPIUtil.dip2px(1.0F);
                if(i <= 4) goto _L5; else goto _L4
_L4:
                int l = 4;
_L7:
                Exception exception;
                boolean flag;
                boolean flag1;
                final RelativeLayout tabContentLayout;
                int i1 = k / l;
                android.widget.RadioGroup.LayoutParams layoutparams = new android.widget.RadioGroup.LayoutParams(i1, -2);
                radioGroupLayout.addView(radiobutton, layoutparams);
                if(!flag1)
                {
                    View view = getLayoutInflater().inflate(0x7f030075, null);
                    android.widget.RadioGroup.LayoutParams layoutparams2 = new android.widget.RadioGroup.LayoutParams(DPIUtil.dip2px(1.0F), -1);
                    radioGroupLayout.addView(view, layoutparams2);
                }
                radiobutton.setId(2000 + j);
                tabContentLayout = initTabContent(s);
                tabContentLayout.setId(3000 + j);
                android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(ProductDetailInfoActivity.layoutParamsFF);
                contentLayout.addView(tabContentLayout, layoutparams1);
                android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

                    public void onClick(View view1)
                    {
                        cutBranch(tabContentLayout);
                    }

                    final _cls1 this$1;
                    private final RelativeLayout val$tabContentLayout;

                    
                    {
                        this$1 = _cls1.this;
                        tabContentLayout = relativelayout;
                        super();
                    }
                }
;
                radiobutton.setOnClickListener(onclicklistener);
                if(flag)
                {
                    radioGroupLayout.check(radiobutton.getId());
                    nowBranchView = tabContentLayout;
                    break MISSING_BLOCK_LABEL_423;
                }
                break; /* Loop/switch isn't completed */
_L5:
                l = i;
                if(true) goto _L7; else goto _L6
_L6:
                try
                {
                    tabContentLayout.setVisibility(8);
                    break MISSING_BLOCK_LABEL_423;
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
_L3:
                return;
_L2:
                if(j == 0)
                    flag = true;
                else
                    flag = false;
                if(j + 1 < i)
                    flag1 = false;
                else
                    flag1 = true;
                  goto _L8
                j++;
                  goto _L9
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailInfoActivity this$0;
            private final RelativeLayout val$contentLayout;
            private final RadioGroup val$radioGroupLayout;
            private final HorizontalScrollView val$scrollView;


            
            {
                this$0 = ProductDetailInfoActivity.this;
                radioGroupLayout = radiogroup;
                contentLayout = relativelayout;
                scrollView = horizontalscrollview;
                super();
            }
        }
);
        httpGroupaAsynPool.add(httpsetting);
    }

    private RelativeLayout initTabContent(String s)
    {
        final RelativeLayout tabContentLayout = createTabBody();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(s);
        httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getShowId()).toString());
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            private void removeProgressBar()
            {
                post(new Runnable() {

                    public void run()
                    {
                        View view = tabContentLayout.getChildAt(0);
                        if(view instanceof ProgressBar)
                            view.setVisibility(8);
                    }

                    final _cls2 this$1;
                    private final RelativeLayout val$tabContentLayout;

                    
                    {
                        this$1 = _cls2.this;
                        tabContentLayout = relativelayout;
                        super();
                    }
                }
);
            }

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONArrayPoxy jsonarraypoxy;
                StringBuilder stringbuilder;
                int i;
                jsonarraypoxy = httpresponse.getJSONObject().getJSONArrayOrNull("wareInfo");
                stringbuilder = new StringBuilder();
                stringbuilder.append("<html><head></head><body>");
                i = 0;
_L9:
                if(i < jsonarraypoxy.length()) goto _L2; else goto _L1
_L1:
                final String htmlContentStr;
                stringbuilder.append("</body></html>");
                htmlContentStr = stringbuilder.toString();
                if(!"".equals(htmlContentStr)) goto _L4; else goto _L3
_L3:
                removeProgressBar();
                  goto _L5
_L2:
                String s1;
                String s2;
                JSONObjectProxy jsonobjectproxy = jsonarraypoxy.getJSONObjectOrNull(i);
                if(jsonobjectproxy != null)
                {
                    s1 = jsonobjectproxy.getStringOrNull("label");
                    s2 = jsonobjectproxy.getStringOrNull("value");
                    if(s1 == null)
                        s1 = getString(0x7f090046);
                    break MISSING_BLOCK_LABEL_254;
                }
                  goto _L6
_L10:
                String s3 = s2.replaceAll("7\u7AE0", "\uFF17\u7AE0");
                if(!"".equals(s1)) goto _L8; else goto _L7
_L7:
                stringbuilder.append((new StringBuilder("<br />")).append(s3).toString());
                  goto _L6
                Exception exception;
                exception;
                exception.printStackTrace();
                removeProgressBar();
                  goto _L5
_L8:
                stringbuilder.append((new StringBuilder(String.valueOf(s1))).append("&nbsp;").append(s3).append("<br />").toString());
                  goto _L6
_L4:
                removeProgressBar();
                post(new Runnable() {

                    public void run()
                    {
                        WebView webview = new WebView(_fld0);
                        webview.getSettings().setSupportZoom(true);
                        webview.loadDataWithBaseURL(null, htmlContentStr, "text/html", "UTF-8", null);
                        tabContentLayout.addView(webview, ProductDetailInfoActivity.layoutParamsFF);
                    }

                    final _cls2 this$1;
                    private final String val$htmlContentStr;
                    private final RelativeLayout val$tabContentLayout;

                    
                    {
                        this$1 = _cls2.this;
                        htmlContentStr = s;
                        tabContentLayout = relativelayout;
                        super();
                    }
                }
);
_L5:
                return;
_L6:
                i++;
                  goto _L9
                if(s2 == null)
                    s2 = "";
                  goto _L10
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                removeProgressBar();
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailInfoActivity this$0;
            private final RelativeLayout val$tabContentLayout;


            
            {
                this$0 = ProductDetailInfoActivity.this;
                tabContentLayout = relativelayout;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        httpGroupaAsynPool.add(httpsetting);
        return tabContentLayout;
    }

    private void initView()
    {
        productIdView = (TextView)findViewById(0x7f0c0271);
        productNameAndAdWordView = (TextView)findViewById(0x7f0c0270);
        productJdPriceView = (TextView)findViewById(0x7f0c0275);
        productPromotionLabelView = (TextView)findViewById(0x7f0c0272);
        productPromotionView = (TextView)findViewById(0x7f0c0273);
        productCouponView = (TextView)findViewById(0x7f0c0274);
        containerView = (RelativeLayout)findViewById(0x7f0c026f);
    }

    private void showNameAndPrice()
    {
        productIdView.setText((new StringBuilder(String.valueOf(getString(0x7f090045)))).append(product.getShowId()).toString());
        ProductShow productshow = new ProductShow(this, product);
        productNameAndAdWordView.setText(productshow.getNameAndAdWord());
        productJdPriceView.setText(productshow.getJdPrice());
    }

    private void showPromotion()
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("promotion");
        httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getShowId()).toString());
        httpsetting.putJsonParam("level", "-1");
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.CustomOnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                JSONObjectProxy jsonobjectproxy;
                final ArrayList giftList;
                jsonobjectproxy = httpresponse.getJSONObject();
                giftList = Product.toList(jsonobjectproxy.getJSONArrayOrNull("promotionInfos"), 10);
                product.setGiftList(giftList);
                if(giftList != null && giftList.size() != 0) goto _L2; else goto _L1
_L1:
                return;
_L2:
                post(new Runnable() {

                    public void run()
                    {
                        productPromotionLabelView.setVisibility(0);
                        productPromotionView.setVisibility(0);
                        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
                        Iterator iterator = giftList.iterator();
                        do
                        {
                            if(!iterator.hasNext())
                            {
                                productPromotionView.setText(spannablestringbuilder);
                                return;
                            }
                            Product product1 = (Product)iterator.next();
                            spannablestringbuilder.append((new StringBuilder(String.valueOf(product1.getName()))).append(" ").toString());
                            String s = (new StringBuilder("X")).append(String.valueOf(product1.getNum())).toString();
                            int i = spannablestringbuilder.length();
                            StringBuilder stringbuilder = new StringBuilder(String.valueOf(s));
                            String s1;
                            int j;
                            if(iterator.hasNext())
                                s1 = "\n";
                            else
                                s1 = "";
                            spannablestringbuilder.append(stringbuilder.append(s1).toString());
                            j = i + s.length();
                            spannablestringbuilder.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), i, j, 33);
                        } while(true);
                    }

                    final _cls3 this$1;
                    private final ArrayList val$giftList;

                    
                    {
                        this$1 = _cls3.this;
                        giftList = arraylist;
                        super();
                    }
                }
);
                final ArrayList couponList = Coupon.toList(jsonobjectproxy.getJSONArrayOrNull("tokenInfos"), 0);
                if(couponList != null && couponList.size() != 0)
                    post(new Runnable() {

                        public void run()
                        {
                            productCouponView.setVisibility(0);
                            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
                            Iterator iterator = couponList.iterator();
                            do
                            {
                                if(!iterator.hasNext())
                                {
                                    productCouponView.setText(spannablestringbuilder);
                                    return;
                                }
                                Coupon coupon = (Coupon)iterator.next();
                                spannablestringbuilder.append((new StringBuilder(String.valueOf(coupon.getMessage()))).append("\uFF1A").toString());
                                String s = String.valueOf(coupon.getBalance());
                                int i = spannablestringbuilder.length();
                                StringBuilder stringbuilder = (new StringBuilder(String.valueOf(s))).append("\u5143");
                                String s1;
                                int j;
                                if(iterator.hasNext())
                                    s1 = "\n";
                                else
                                    s1 = "";
                                spannablestringbuilder.append(stringbuilder.append(s1).toString());
                                j = i + s.length();
                                spannablestringbuilder.setSpan(new ForegroundColorSpan(getResources().getColor(0x7f070005)), i, j, 33);
                            } while(true);
                        }

                        final _cls3 this$1;
                        private final ArrayList val$couponList;

                    
                    {
                        this$1 = _cls3.this;
                        couponList = arraylist;
                        super();
                    }
                    }
);
                if(true) goto _L1; else goto _L3
_L3:
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ProductDetailInfoActivity this$0;


            
            {
                this$0 = ProductDetailInfoActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        httpGroupaAsynPool.add(httpsetting);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030073);
        httpGroupaAsynPool = getHttpGroupaAsynPool();
        initView();
        initData();
        ((TextView)findViewById(0x7f0c02c7)).setText(product.getName());
        ProductShow.shareProduct((Button)findViewById(0x7f0c02c8), product);
        showNameAndPrice();
        showPromotion();
        initTabComponents();
    }

    private static final int RADIO_BUTTON_ID_OFFSET = 2000;
    private static final int RADIO_BUTTON_LAYOUT_ID = 1001;
    private static final int TAB_CONTENT_ID_OFFSET = 3000;
    private static final android.widget.RelativeLayout.LayoutParams layoutParamsFF = new android.widget.RelativeLayout.LayoutParams(-1, -1);
    private static final android.widget.RelativeLayout.LayoutParams layoutParamsFW = new android.widget.RelativeLayout.LayoutParams(-1, -2);
    private RelativeLayout containerView;
    private HttpGroup httpGroupaAsynPool;
    private View nowBranchView;
    private Product product;
    private TextView productCouponView;
    private TextView productIdView;
    private TextView productJdPriceView;
    private TextView productNameAndAdWordView;
    private TextView productPromotionLabelView;
    private TextView productPromotionView;











}
