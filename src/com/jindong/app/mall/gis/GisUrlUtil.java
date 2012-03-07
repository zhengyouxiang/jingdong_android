// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.gis;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.widget.*;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.utils.*;
import org.json.JSONArray;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.gis:
//            OrderTimeJsonResponseHandler, OrderJsonResponseHandler

public class GisUrlUtil
{
    class GisContact
    {

        public String id;
        public String name;
        public String orderid;
        public String telephone;
        final GisUrlUtil this$0;

        GisContact()
        {
            this$0 = GisUrlUtil.this;
            super();
        }
    }

    class GisOrderInfo
    {

        public static final int CANGKU_CODE = 64;
        public static final int PENSONGZHANG_CODE = 4;
        public static final int ZHITIDIAN_CODE = 8;
        public OrderSiteInfo cangku;
        public OrderSiteInfo customer;
        public GisContact gisContactInfo;
        public OrderStatetime orderStatetime;
        final GisUrlUtil this$0;
        public OrderSiteInfo zhandian;

        public GisOrderInfo()
        {
            this$0 = GisUrlUtil.this;
            super();
            cangku = new OrderSiteInfo();
            customer = new OrderSiteInfo();
            gisContactInfo = new GisContact();
            zhandian = new OrderSiteInfo();
            orderStatetime = new OrderStatetime();
        }
    }

    private class JavaScriptInterface
    {

        public double getLatitude()
        {
            double d;
            if(mostRecentLocation == null)
                d = 0.0D;
            else
                d = mostRecentLocation.getLatitude();
            return d;
        }

        public double getLongitude()
        {
            double d;
            if(mostRecentLocation == null)
                d = 0.0D;
            else
                d = mostRecentLocation.getLongitude();
            return d;
        }

        final GisUrlUtil this$0;

        private JavaScriptInterface()
        {
            this$0 = GisUrlUtil.this;
            super();
        }

        JavaScriptInterface(JavaScriptInterface javascriptinterface)
        {
            this();
        }
    }

    class OrderSiteInfo
    {

        public String address;
        public String cityname;
        public String id;
        public double laty;
        public double lngx;
        public String orderid;
        public String provincename;
        final GisUrlUtil this$0;
        public int type;

        OrderSiteInfo()
        {
            this$0 = GisUrlUtil.this;
            super();
        }
    }

    class OrderStatetime
    {

        public static final int ORDER_STATE_FAHAO = 4;
        public static final int ORDER_STATE_FENJIAN = 10;
        public static final int ORDER_STATE_FENJIAN_WANGCHEN_15 = 15;
        public static final int ORDER_STATE_WANGCHEN_16 = 16;
        public static final int ORDER_STATE_ZAITU = 22;
        public static final int ORDER_STATE_ZHANGTOU = 19;
        public String begintime;
        public String endtime;
        public String orderid;
        public int state;
        final GisUrlUtil this$0;
        public String updatetime;

        OrderStatetime()
        {
            this$0 = GisUrlUtil.this;
            super();
        }
    }


    public GisUrlUtil(MyActivity myactivity, String s)
    {
        orderTraceInitFlag = false;
        pickSiteType = 0;
        handler = new Handler() {

            public void handleMessage(Message message1)
            {
                if(orderStatetime.state == 22)
                {
                    message = handler.obtainMessage();
                    message.what = 4101;
                    handler.removeMessages(4101);
                    Log.i("test", "entor ");
                    handler.sendMessageDelayed(message, 60000L);
                }
                message1.what;
                JVM INSTR tableswitch 4096 4101: default 128
            //                           4096 129
            //                           4097 128
            //                           4098 128
            //                           4099 128
            //                           4100 517
            //                           4101 538;
                   goto _L1 _L2 _L1 _L1 _L1 _L3 _L4
_L1:
                return;
_L2:
                if(orderStatetime.orderid != null && orderId.equals(orderStatetime.orderid))
                    switch(orderStatetime.state)
                    {
                    case 4: // '\004'
                        getDate("getcktopsztracks", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                            {
                                Log.i("test", httpresponse.getJSONObject().toString());
                                String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                webViewLoadJs(s1, getPickSiteType(), 0);
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                        if(4 == gisOrderInfo.zhandian.type)
                            getDate("orderwancheng", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                                {
                                    Log.i("test", httpresponse.getJSONObject().toString());
                                    String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                    webViewLoadJs(s1, 0, 1);
                                }

                                final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                            }
);
                        break;

                    case 10: // '\n'
                        if(orderId.equals(gisOrderInfo.cangku.orderid))
                        {
                            getDate("getcktopsztracks", 1, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                                {
                                    String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                    webViewLoadJs(s1, getPickSiteType(), 1);
                                }

                                final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                            }
);
                            if(4 == gisOrderInfo.zhandian.type)
                                getDate("getdriverpath", 2, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                                    {
                                        String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                        webViewLoadJs(s1, 0, 1);
                                    }

                                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                                }
);
                        }
                        break;

                    case 22: // '\026'
                        getDate("getcktopsztracks", 2, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                            {
                                String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                webViewLoadJs(s1, getPickSiteType(), 0);
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                        break;

                    case 19: // '\023'
                        getDate("getcktopsztracks", 2, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                            {
                                String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                webViewLoadJs(s1, getPickSiteType(), 0);
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                        if(4 == gisOrderInfo.zhandian.type)
                            getDate("orderwancheng", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                                {
                                    String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                    webViewLoadJs(s1, 0, 1);
                                }

                                final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                            }
);
                        break;

                    case 15: // '\017'
                    case 16: // '\020'
                        getDate("getcktopsztracks", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                            {
                                Log.i("test", httpresponse.getJSONObject().toString());
                                String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                webViewLoadJs(s1, getPickSiteType(), 0);
                            }

                            final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                        }
);
                        if(4 == gisOrderInfo.zhandian.type)
                            getDate("orderwancheng", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                                {
                                    Log.i("test", httpresponse.getJSONObject().toString());
                                    String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                                    webViewLoadJs(s1, 0, 0);
                                }

                                final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                            }
);
                        break;
                    }
                else
                    Log.i("test", "getOrderInfo is empty");
                continue; /* Loop/switch isn't completed */
_L3:
                getDate("getordertracks", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                        webViewLoadJs(s1, 0, 0);
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                continue; /* Loop/switch isn't completed */
_L4:
                getDate("getordertracks", 0, new com.jindong.app.mall.utils.HttpGroup.OnEndListener() {

                    public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                    {
                        String s1 = httpresponse.getJSONObject().getStringOrNull("getcktopsztracks");
                        if(s1 != null)
                        {
                            try
                            {
                                JSONArray jsonarray = (new JSONObject(s1)).getJSONArray("gpsdata");
                                if(jsonarray.length() > 0)
                                {
                                    JSONObject jsonobject = (JSONObject)jsonarray.get(jsonarray.length() - 1);
                                    orderStatetime.endtime = jsonobject.getString("time");
                                    endLatValue = jsonobject.getDouble("lat");
                                    endLngValue = jsonobject.getDouble("lng");
                                }
                            }
                            catch(Exception exception)
                            {
                                exception.printStackTrace();
                            }
                            webViewLoadJs(s1, 0, 0);
                            getOrderStateInfo(true);
                        }
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                if(true) goto _L1; else goto _L5
_L5:
            }

            final GisUrlUtil this$0;


            
            {
                this$0 = GisUrlUtil.this;
                super();
            }
        }
;
        orderId = s;
        myActivity = myactivity;
        webView = (WebView)myactivity.findViewById(0x7f0c01b0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JavaScriptInterface(null), "android");
        webView.loadUrl("file:///android_asset/map.htm");
        scrollView = (ScrollView)myactivity.findViewById(0x7f0c0197);
        final RelativeLayout webViewContainer = (RelativeLayout)myactivity.findViewById(0x7f0c01af);
        tabLayout = (LinearLayout)myactivity.findViewById(0x7f0c01ac);
        myMapButton = (Button)tabLayout.findViewById(0x7f0c01ae);
        myMapButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                scrollView.setVisibility(8);
                myButton.setSelected(false);
                myMapButton.setSelected(true);
                webViewContainer.setVisibility(0);
                if(!orderTraceInitFlag)
                    getOrderStateInfo(false);
            }

            final GisUrlUtil this$0;
            private final RelativeLayout val$webViewContainer;

            
            {
                this$0 = GisUrlUtil.this;
                webViewContainer = relativelayout;
                super();
            }
        }
);
        myButton = (Button)tabLayout.findViewById(0x7f0c01ad);
        myButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                scrollView.setVisibility(0);
                webViewContainer.setVisibility(8);
                myButton.setSelected(true);
                myMapButton.setSelected(false);
            }

            final GisUrlUtil this$0;
            private final RelativeLayout val$webViewContainer;

            
            {
                this$0 = GisUrlUtil.this;
                webViewContainer = relativelayout;
                super();
            }
        }
);
        gisOrderInfo = new GisOrderInfo();
        orderStatetime = new OrderStatetime();
    }

    public void cancelHandler()
    {
        if(handler != null)
            handler.removeMessages(4101);
    }

    public void getDate(String s, int i, com.jindong.app.mall.utils.HttpGroup.OnEndListener onendlistener)
    {
        HttpGroup httpgroup = MyApplication.getInstance().getCurrentMyActivity().getHttpGroupaAsynPool();
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        getUrl(s, i, httpsetting);
        httpsetting.setFunctionId(s);
        httpsetting.setListener(onendlistener);
        httpgroup.add(httpsetting);
    }

    void getOrderInfo()
    {
        getDate("getOrders", 1, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                OrderJsonResponseHandler orderjsonresponsehandler = new OrderJsonResponseHandler(gisOrderInfo);
                jsonStr = httpresponse.getJSONObject().getStringOrNull("getOrders");
                orderjsonresponsehandler.parseJsonString(httpresponse.getJSONObject());
                myActivity.post(new Runnable() {

                    public void run()
                    {
                        if(gisOrderInfo.zhandian.type == 4 || gisOrderInfo.zhandian.type == 8)
                        {
                            tabLayout.setVisibility(0);
                            myButton.setSelected(true);
                        } else
                        {
                            tabLayout.setVisibility(8);
                        }
                    }

                    final _cls6 this$1;

                    
                    {
                        this$1 = _cls6.this;
                        super();
                    }
                }
);
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

            final GisUrlUtil this$0;


            
            {
                this$0 = GisUrlUtil.this;
                super();
            }
        }
);
    }

    void getOrderStateInfo(final boolean traceFlag)
    {
        getDate("getOrderstate", 1, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                (new OrderTimeJsonResponseHandler(orderStatetime)).parseJsonString(httpresponse.getJSONObject());
                webView.loadUrl((new StringBuilder("javascript:setOrderInfo(")).append(jsonStr).append(")").toString());
                if(!traceFlag)
                    handler.sendEmptyMessage(4096);
                else
                    handler.sendEmptyMessage(4100);
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

            final GisUrlUtil this$0;
            private final boolean val$traceFlag;

            
            {
                this$0 = GisUrlUtil.this;
                traceFlag = flag;
                super();
            }
        }
);
    }

    public void getOrderTrace()
    {
        (new Thread(new Runnable() {

            public void run()
            {
                getOrderInfo();
            }

            final GisUrlUtil this$0;

            
            {
                this$0 = GisUrlUtil.this;
                super();
            }
        }
)).start();
    }

    int getPickSiteType()
    {
        byte byte0;
        if(4 == gisOrderInfo.zhandian.type)
            byte0 = 4;
        else
        if(8 == gisOrderInfo.zhandian.type)
            byte0 = 8;
        else
            byte0 = 0;
        return byte0;
    }

    public void getUrl(String s, int i, com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting)
    {
        httpsetting.putJsonParam("orderId", orderId);
        if(!"getdriverpath".equals(s)) goto _L2; else goto _L1
_L1:
        if(i != 1) goto _L4; else goto _L3
_L3:
        httpsetting.putJsonParam("lng1", (new StringBuilder()).append(gisOrderInfo.cangku.lngx).toString());
        httpsetting.putJsonParam("lat1", (new StringBuilder()).append(gisOrderInfo.cangku.laty).toString());
        httpsetting.putJsonParam("lng2", (new StringBuilder()).append(gisOrderInfo.zhandian.lngx).toString());
        httpsetting.putJsonParam("lat2", (new StringBuilder()).append(gisOrderInfo.zhandian.laty).toString());
_L6:
        if("getordertracks".equals(s))
            httpsetting.putJsonParam("beginTime", (new StringBuilder()).append(orderStatetime.endtime).toString());
        return;
_L4:
        if(i == 2)
        {
            httpsetting.putJsonParam("lng1", (new StringBuilder()).append(gisOrderInfo.zhandian.lngx).toString());
            httpsetting.putJsonParam("lat1", (new StringBuilder()).append(gisOrderInfo.zhandian.laty).toString());
            httpsetting.putJsonParam("lng2", (new StringBuilder()).append(gisOrderInfo.customer.lngx).toString());
            httpsetting.putJsonParam("lat2", (new StringBuilder()).append(gisOrderInfo.customer.laty).toString());
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(!"orderwancheng".equals(s) && !"getOrderstate".equals(s) && "getcktopsztracks".equals(s))
        {
            httpsetting.putJsonParam("zdcode", (new StringBuilder()).append(gisOrderInfo.zhandian.id).toString());
            httpsetting.putJsonParam("ckcode", (new StringBuilder()).append(gisOrderInfo.cangku.id).toString());
            httpsetting.putJsonParam("zdtype", (new StringBuilder()).append(gisOrderInfo.zhandian.type).toString());
            httpsetting.putJsonParam("cktype", (new StringBuilder()).append(gisOrderInfo.cangku.type).toString());
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    void webViewLoadJs(String s, int i, int j)
    {
        webView.loadUrl((new StringBuilder("javascript:setPath(")).append(s).append(",").append(i).append(",").append(j).append(")").toString());
    }

    public static final String GIS_GET_CK_TO_PSZ_URL = "getcktopsztracks";
    public static final String GIS_GET_DRIVER_PATH_URL = "getdriverpath";
    public static final String GIS_GET_ORDERS_URL = "getOrders";
    public static final String GIS_GET_ORDER_TRACE_URL = "getordertracks";
    public static final String GIS_GET_STATE_TMIE_URL = "getOrderstate";
    public static final String GIS_ORDERS_WANGCHENG_URL = "orderwancheng";
    private static final String MAP_URL = "file:///android_asset/map.htm";
    private final int DOTTED_LINE = 1;
    final int DRIVERPATHFLAG_CK_TO_PSZ = 1;
    final int DRIVERPATHFLAG_PSZ_TO_CUSTOMER = 2;
    private final int MSG_GET_ORDER_CK_TO_ZD = 4097;
    private final int MSG_GET_ORDER_CK_TO_ZD_DRIVER = 4098;
    private final int MSG_GET_ORDER_GET_TRACE_INFO = 4101;
    private final int MSG_GET_ORDER_GIS_INFO = 4096;
    private final int MSG_GET_ORDER_ZD_TO_CST = 4099;
    private final int MSG_GET_ORDER_ZD_TO_CST_DRIVER = 4100;
    private final int SOLID_LINE = 0;
    private final int TIMES_GET_TRACETE_INFO = 60000;
    public String ckTozhandianLine;
    public String ckTozhangdianDriverLine;
    private double endLatValue;
    private double endLngValue;
    public GisOrderInfo gisOrderInfo;
    private Handler handler;
    private String jsonStr;
    private Message message;
    private Location mostRecentLocation;
    private MyActivity myActivity;
    Button myButton;
    Button myMapButton;
    private String orderId;
    public OrderStatetime orderStatetime;
    private boolean orderTraceInitFlag;
    int pickSiteType;
    private ScrollView scrollView;
    LinearLayout tabLayout;
    private WebView webView;
    public String zhandianToCustomerDriverLine;
    public String zhandianToCustomerLine;













}
