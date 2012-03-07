// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.entity.Image;
import com.jindong.app.mall.entity.OrderComment;
import com.jindong.app.mall.utils.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.product:
//            ImageActivity

public class DiscussDetailActivity extends MyActivity
{

    public DiscussDetailActivity()
    {
        jsonObject = null;
        imageList = new ArrayList();
    }

    private void showOrderCommentDetail()
    {
        params = new JSONObject();
        params.put("commentId", id);
_L2:
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("commentDetail");
        httpsetting.setJsonParams(params);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                jsonObject = httpresponse.getJSONObject();
                final OrderComment orderComment = new OrderComment(jsonObject.getJSONObject("orderCommentInfo"), jsonObject.getJSONArray("imagPath"), 1);
                post(new Runnable() {

                    public void run()
                    {
                        commentDetailTitle.setText(orderComment.getTitle());
                        TextView textview = userId;
                        DiscussDetailActivity discussdetailactivity1 = _fld0;
                        Object aobj[] = new Object[1];
                        aobj[0] = orderComment.getUserId();
                        textview.setText(discussdetailactivity1.getString(0x7f09018c, aobj));
                        TextView textview1 = creationTime;
                        DiscussDetailActivity discussdetailactivity2 = _fld0;
                        Object aobj1[] = new Object[1];
                        aobj1[0] = orderComment.getCreationTime();
                        textview1.setText(discussdetailactivity2.getString(0x7f09018d, aobj1));
                        commentDetailContent.setText(orderComment.getContent());
                        DiscussDetailActivity discussdetailactivity3 = _fld0;
                        List list = orderComment.getImageList();
                        String as1[] = new String[1];
                        as1[0] = "small";
                        int ai1[] = new int[1];
                        ai1[0] = 0x7f0c008f;
                        MySimpleAdapter mysimpleadapter = new MySimpleAdapter(discussdetailactivity3, list, 0x7f03001e, as1, ai1, 1, DPIUtil.dip2px(245F), DPIUtil.dip2px(245F)) {

                            public View getView(int i, View view, ViewGroup viewgroup)
                            {
                                return super.getView(i, view, viewgroup);
                            }

                            int mGalleryItemBackground;
                            final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super(myactivity, list, i, as, ai, j, f, f1);
                        }
                        }
;
                        productImageGallery.setAdapter(mysimpleadapter);
                        productImageGallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

                            public void onItemClick(AdapterView adapterview, View view, int i, long l)
                            {
                                String s = ((Image)((Adapter)adapterview.getAdapter()).getItem(i)).getBig();
                                Bundle bundle = new Bundle();
                                bundle.putString("url", s);
                                bundle.putInt("position", i);
                                bundle.putSerializable("orderComment", orderComment);
                                Intent intent = new Intent(_fld0, com/jindong/app/mall/product/ImageActivity);
                                intent.putExtras(bundle);
                                startActivityInFrame(intent);
                            }

                            final _cls1 this$2;
                            private final OrderComment val$orderComment;

                        
                        {
                            this$2 = _cls1.this;
                            orderComment = ordercomment;
                            super();
                        }
                        }
);
                    }

                    final _cls1 this$1;
                    private final OrderComment val$orderComment;


                    
                    {
                        this$1 = _cls1.this;
                        orderComment = ordercomment;
                        super();
                    }
                }
);
                final ListView replyList = (ListView)findViewById(0x7f0c0087);
                ArrayList arraylist = OrderComment.toList(jsonObject.getJSONArray("replyList"), 2);
                DiscussDetailActivity discussdetailactivity = DiscussDetailActivity.this;
                String as[] = new String[3];
                as[0] = "userId";
                as[1] = "creationTime";
                as[2] = "content";
                int ai[] = new int[3];
                ai[0] = 0x7f0c0090;
                ai[1] = 0x7f0c0091;
                ai[2] = 0x7f0c0092;
                final MySimpleAdapter adapter = new MySimpleAdapter(discussdetailactivity, arraylist, 0x7f03001f, as, ai) {

                    public View getView(int i, View view, ViewGroup viewgroup)
                    {
                        View view1 = super.getView(i, view, viewgroup);
                        if(i % 2 == 1)
                            view1.setBackgroundResource(0x7f070003);
                        else
                            view1.setBackgroundResource(0x7f070004);
                        return view1;
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super(myactivity, list, i, as, ai);
                    }
                }
;
                post(new Runnable() {

                    public void run()
                    {
                        detailHeadContainer.setPadding(10, 10, 10, 10);
                        replyList.addHeaderView(detailHeadContainer);
                        replyList.setAdapter(adapter);
                    }

                    final _cls1 this$1;
                    private final ListAdapter val$adapter;
                    private final ListView val$replyList;

                    
                    {
                        this$1 = _cls1.this;
                        replyList = listview;
                        adapter = listadapter;
                        super();
                    }
                }
);
_L1:
                return;
                JSONException jsonexception1;
                jsonexception1;
                if(Log.D)
                    Log.d(com/jindong/app/mall/product/DiscussDetailActivity.getName(), "JSONException -->> ", jsonexception1);
                  goto _L1
                Exception exception;
                exception;
                exception.printStackTrace();
                  goto _L1
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("OrderCommentDetailActivity", (new StringBuilder("error -->> ")).append(httperror).toString());
            }

            public void onProgress(int i, int j)
            {
                if(Log.D)
                    Log.d("OrderCommentDetailActivity", (new StringBuilder("max -->> ")).append(i).toString());
                if(Log.D)
                    Log.d("OrderCommentDetailActivity", (new StringBuilder("progress -->> ")).append(j).toString());
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("OrderCommentDetailActivity", "showOrderCommentDetail-----start");
            }

            final DiscussDetailActivity this$0;


            
            {
                this$0 = DiscussDetailActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("OrderCommentDetailActivity", jsonexception.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03001c);
        id = getIntent().getExtras().getString("id");
        title = (TextView)findViewById(0x7f0c02c7);
        title.setText(0x7f09018f);
        detailHeadContainer = (RelativeLayout)LayoutInflater.from(this).inflate(0x7f03001d, null);
        commentDetailTitle = (TextView)detailHeadContainer.findViewById(0x7f0c0088);
        userId = (TextView)detailHeadContainer.findViewById(0x7f0c008a);
        creationTime = (TextView)detailHeadContainer.findViewById(0x7f0c008b);
        commentDetailContent = (TextView)detailHeadContainer.findViewById(0x7f0c008e);
        productImageGallery = (Gallery)detailHeadContainer.findViewById(0x7f0c008d);
        showOrderCommentDetail();
    }

    private TextView commentDetailContent;
    private TextView commentDetailTitle;
    private TextView creationTime;
    private RelativeLayout detailHeadContainer;
    private String id;
    private List imageList;
    private JSONObjectProxy jsonObject;
    private Bitmap littleBitmap;
    private JSONObject params;
    private Gallery productImageGallery;
    private TextView title;
    private TextView userId;








}
