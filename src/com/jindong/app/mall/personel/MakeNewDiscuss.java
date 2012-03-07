// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.personel;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.*;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.entity.DiscussImage;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.io.*;
import java.util.*;
import org.json.JSONArray;

// Referenced classes of package com.jindong.app.mall.personel:
//            MyCommentDiscussActivity

public class MakeNewDiscuss extends MyActivity
{

    public MakeNewDiscuss()
    {
        imageList = new ArrayList();
        imageAdapter = null;
        alertDialog = null;
        resultAlertDialog = null;
    }

    private boolean checkInput()
    {
        boolean flag;
        if(imageAdapter.getCount() < 1)
        {
            Toast.makeText(this, getText(0x7f0901a0), 0).show();
            submitButton.setClickable(true);
            flag = false;
        } else
        if(discussTitle.getText().toString().trim().length() < 4 || discussTitle.getText().toString().trim().length() > 50)
        {
            if(Log.D)
                Log.d("temp", (new StringBuilder("discussTitle-length:")).append(discussTitle.getText().toString().length()).toString());
            Toast.makeText(this, getText(0x7f0901a7), 0).show();
            submitButton.setClickable(true);
            flag = false;
        } else
        if(discussTaste.getText().toString().trim().length() < 5 || discussTaste.getText().toString().trim().length() > 200)
        {
            Toast.makeText(this, getText(0x7f0901ae), 0).show();
            submitButton.setClickable(true);
            flag = false;
        } else
        {
            submitButton.setClickable(true);
            flag = true;
        }
        return flag;
    }

    private boolean checkSize()
    {
        boolean flag;
        if(imageList.size() >= 3)
        {
            Toast.makeText(this, 0x7f0901b4, 1).show();
            flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    private void findView()
    {
        title = (TextView)findViewById(0x7f0c02c7);
        discussTitle = (EditText)findViewById(0x7f0c0162);
        discussTaste = (EditText)findViewById(0x7f0c0164);
        cameraButton = (Button)findViewById(0x7f0c0165);
        submitButton = (Button)findViewById(0x7f0c0166);
        imageGallery = (Gallery)findViewById(0x7f0c0167);
    }

    private void handleClickEvent()
    {
        cameraButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(checkSize())
                {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MakeNewDiscuss.this);
                    builder.setTitle(0x7f090199);
                    String as[] = new String[2];
                    as[0] = getString(0x7f090189);
                    as[1] = getString(0x7f09018a);
                    builder.setItems(as, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            alertDialog.dismiss();
                            i;
                            JVM INSTR tableswitch 0 1: default 36
                        //                                       0 37
                        //                                       1 210;
                               goto _L1 _L2 _L3
_L1:
                            return;
_L2:
                            if(CommonUtil.checkSDcard())
                            {
                                Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                                File file = new File(Environment.getExternalStorageDirectory(), (new StringBuilder(String.valueOf(System.currentTimeMillis()))).append(".jpg").toString());
                                uri = Uri.fromFile(file);
                                intent1.putExtra("output", uri);
                                try
                                {
                                    startActivityForResult(intent1, 0);
                                }
                                catch(ActivityNotFoundException activitynotfoundexception)
                                {
                                    alert(0x7f090050);
                                }
                            } else
                            {
                                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(_fld0);
                                builder1.setTitle(0x7f09019e);
                                builder1.setMessage(0x7f09019f);
                                builder1.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialoginterface1, int j)
                                    {
                                    }

                                    final _cls1 this$2;

                        
                        {
                            this$2 = _cls1.this;
                            super();
                        }
                                }
);
                                builder1.show();
                            }
                            continue; /* Loop/switch isn't completed */
_L3:
                            Intent intent = new Intent("android.intent.action.GET_CONTENT");
                            intent.addCategory("android.intent.category.OPENABLE");
                            intent.setType("image/*");
                            startActivityForResult(intent, 1);
                            if(true) goto _L1; else goto _L4
_L4:
                        }

                        final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                    }
);
                    alertDialog = builder.show();
                }
            }

            final MakeNewDiscuss this$0;


            
            {
                this$0 = MakeNewDiscuss.this;
                super();
            }
        }
);
        imageGallery.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                final DiscussImage discussImage = (DiscussImage)((Adapter)adapterview.getAdapter()).getItem(i);
                if(Log.D)
                    Log.d("temp", (new StringBuilder("position = ")).append(i).toString());
                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewDiscuss.this);
                alertDialogBuilder.setMessage(0x7f0901a1);
                alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        imageList.remove(discussImage);
                        imageAdapter.notifyDataSetChanged();
                    }

                    final _cls2 this$1;
                    private final DiscussImage val$discussImage;

                    
                    {
                        this$1 = _cls2.this;
                        discussImage = discussimage;
                        super();
                    }
                }
);
                alertDialogBuilder.setNeutralButton(0x7f09000f, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        dialoginterface.dismiss();
                    }

                    final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
                }
);
                post(new Runnable() {

                    public void run()
                    {
                        alertDialogBuilder.show();
                    }

                    final _cls2 this$1;
                    private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls2.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                }
);
            }

            final MakeNewDiscuss this$0;


            
            {
                this$0 = MakeNewDiscuss.this;
                super();
            }
        }
);
        submitButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                submitButton.setClickable(false);
                if(checkInput())
                    submit();
            }

            final MakeNewDiscuss this$0;

            
            {
                this$0 = MakeNewDiscuss.this;
                super();
            }
        }
);
    }

    private void init()
    {
        product = (Product)getIntent().getSerializableExtra("product");
        name = product.getName();
        if(name.length() > 20)
            name = (new StringBuilder(String.valueOf(name.substring(0, 20)))).append("...").toString();
        TextView textview = title;
        Object aobj[] = new Object[1];
        aobj[0] = name;
        textview.setText(getString(0x7f0900d1, aobj));
    }

    private void initGallery()
    {
        ArrayList arraylist = imageList;
        String as[] = new String[1];
        as[0] = "picture";
        imageAdapter = new MySimpleAdapter(this, arraylist, 0x7f030032, as, new int[1]) {

            public View getView(int i, View view, ViewGroup viewgroup)
            {
                View view1 = super.getView(i, view, viewgroup);
                ((ImageView)view1).setImageDrawable(((DiscussImage)getItem(i)).getPicture());
                return view1;
            }

            final MakeNewDiscuss this$0;

            
            {
                this$0 = MakeNewDiscuss.this;
                super(myactivity, list, i, as, ai);
            }
        }
;
        post(new Runnable() {

            public void run()
            {
                imageGallery.setAdapter(imageAdapter);
            }

            final MakeNewDiscuss this$0;

            
            {
                this$0 = MakeNewDiscuss.this;
                super();
            }
        }
);
    }

    private void setWordNumberText(int i)
    {
        if(i > 0)
            mWordCount.setText((new StringBuilder(String.valueOf(String.valueOf(Contants.MAX_DISCUSS_TEXT_LENGTH - i)))).append("/").append(String.valueOf(Contants.MAX_DISCUSS_TEXT_LENGTH)).toString());
    }

    private void submit()
    {
        String s;
        String s1;
        JSONArray jsonarray;
        Iterator iterator;
        hideSoftInput();
        submitButton.setClickable(false);
        s = (new StringBuilder()).append(discussTitle.getText()).toString();
        s1 = (new StringBuilder()).append(discussTaste.getText()).toString();
        jsonarray = new JSONArray();
        iterator = imageList.iterator();
_L3:
        DiscussImage discussimage;
        if(!iterator.hasNext())
        {
            com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
            httpsetting.putJsonParam("wareId", (new StringBuilder()).append(product.getId()).toString());
            httpsetting.putJsonParam("title", (new StringBuilder()).append(s).toString());
            httpsetting.putJsonParam("content", (new StringBuilder()).append(s1).toString());
            httpsetting.putJsonParam("imgArray", jsonarray);
            httpsetting.putJsonParam("orderId", product.getOrderId());
            httpsetting.putJsonParam("type", "2");
            httpsetting.putJsonParam("pin", LoginUser.getLoginUserName());
            httpsetting.setFunctionId("saveCommentOrder");
            httpsetting.setPost(true);
            com.jindong.app.mall.utils.HttpGroup.OnAllListener onalllistener = new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

                public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
                {
                    Boolean boolean1;
                    String s2;
                    JSONObjectProxy jsonobjectproxy = httpresponse.getJSONObject();
                    boolean1 = jsonobjectproxy.getBooleanOrNull("flag");
                    s2 = jsonobjectproxy.getStringOrNull("saveComment");
                    if(boolean1 == null || !boolean1.booleanValue()) goto _L2; else goto _L1
_L1:
                    submitButton.setClickable(true);
                    noShowAgain();
                    final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewDiscuss.this);
                    alertDialogBuilder.setTitle(0x7f0900b3);
                    alertDialogBuilder.setMessage(0x7f09019c);
                    alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int k1)
                        {
                            resultAlertDialog.dismiss();
                            Intent intent = new Intent(_fld0, com/jindong/app/mall/personel/MyCommentDiscussActivity);
                            startActivityInFrame(intent);
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            resultAlertDialog = alertDialogBuilder.show();
                        }

                        final _cls7 this$1;
                        private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls7.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                    }
);
_L4:
                    return;
_L2:
                    if(s2 != null)
                    {
                        submitButton.setClickable(true);
                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MakeNewDiscuss.this);
                        alertDialogBuilder.setTitle(0x7f0900b5);
                        if(s2 != null)
                            alertDialogBuilder.setMessage(s2);
                        alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int k1)
                            {
                                resultAlertDialog.dismiss();
                            }

                            final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                        }
);
                        post(new Runnable() {

                            public void run()
                            {
                                resultAlertDialog = alertDialogBuilder.show();
                            }

                            final _cls7 this$1;
                            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

                    
                    {
                        this$1 = _cls7.this;
                        alertDialogBuilder = builder;
                        super();
                    }
                        }
);
                    }
                    if(true) goto _L4; else goto _L3
_L3:
                }

                public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
                {
                    if(Log.D)
                        Log.d("MakeNewDiscuss", (new StringBuilder("error -->> ")).append(httperror.toString()).toString());
                    post(new Runnable() {

                        public void run()
                        {
                            Toast.makeText(_fld0, 0x7f0901b0, 1).show();
                            submitButton.setClickable(true);
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
);
                }

                public void onProgress(int k1, int l1)
                {
                }

                public void onStart()
                {
                    post(new Runnable() {

                        public void run()
                        {
                        }

                        final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                    }
);
                }

                final MakeNewDiscuss this$0;


            
            {
                this$0 = MakeNewDiscuss.this;
                super();
            }
            }
;
            httpsetting.setListener(onalllistener);
            httpsetting.setNotifyUser(true);
            getHttpGroupaAsynPool().add(httpsetting);
            return;
        }
        discussimage = (DiscussImage)iterator.next();
        Bitmap bitmap;
        float f;
        float f1;
        int k;
        int l;
        FileInputStream fileinputstream = (FileInputStream)getContentResolver().openInputStream(discussimage.getPath());
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeStream(fileinputstream, null, options);
        int i = discussimage.getRotate();
        if(i != 0)
        {
            Matrix matrix = new Matrix();
            matrix.setRotate(i);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        if(fileinputstream != null)
            fileinputstream.close();
        f = Float.parseFloat(Configuration.getProperty("discussUploadImageWidth"));
        f1 = Float.parseFloat(Configuration.getProperty("discussUploadImageHeight"));
        if(0.0F >= f || 0.0F >= f1)
            break MISSING_BLOCK_LABEL_709;
        k = bitmap.getWidth();
        l = bitmap.getHeight();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("sourceWidth -->> ")).append(k).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("sourceHeight -->> ")).append(l).toString());
        if(k <= l) goto _L2; else goto _L1
_L1:
        float f2 = f / (float)k;
_L4:
        Bitmap bitmap1;
        int i1 = Math.round(f2 * (float)k);
        int j1 = Math.round(f2 * (float)l);
        if(Log.D)
            Log.d("Temp", (new StringBuilder("width -->> ")).append(i1).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("height -->> ")).append(j1).toString());
        bitmap1 = Bitmap.createScaledBitmap(bitmap, i1, j1, false);
        bitmap.recycle();
_L5:
        int j = Integer.parseInt(Configuration.getProperty("discussUploadImageQuality"));
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        android.graphics.Bitmap.CompressFormat compressformat = android.graphics.Bitmap.CompressFormat.JPEG;
        bitmap1.compress(compressformat, j, bytearrayoutputstream);
        bitmap1.recycle();
        byte abyte0[] = bytearrayoutputstream.toByteArray();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("3.length -->> ")).append(abyte0.length).toString());
        jsonarray.put(Base64.encodeBytes(abyte0));
          goto _L3
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L3
_L2:
        f2 = f1 / (float)l;
          goto _L4
        bitmap1 = bitmap;
          goto _L5
    }

    protected final void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        final DiscussImage discussImage_;
        discussImage_ = null;
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            break; /* Loop/switch isn't completed */

        case 1: // '\001'
            break;
        }
        break MISSING_BLOCK_LABEL_67;
_L4:
        if(discussImage_ != null)
            post(new Runnable() {

                public void run()
                {
                    imageList.add(discussImage_);
                    imageAdapter.notifyDataSetChanged();
                    imageGallery.setSelection(imageGallery.getCount() - 1, true);
                }

                final MakeNewDiscuss this$0;
                private final DiscussImage val$discussImage_;

            
            {
                this$0 = MakeNewDiscuss.this;
                discussImage_ = discussimage;
                super();
            }
            }
);
        if(true) goto _L1; else goto _L3
_L3:
        discussImage_ = DiscussImage.createDiscussImage(this, uri);
          goto _L4
        uri = intent.getData();
        discussImage_ = DiscussImage.createDiscussImage(this, uri);
          goto _L4
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030046);
        findView();
        init();
        initGallery();
        handleClickEvent();
    }

    private static final int SCAN_PICTURE = 1;
    private static final int TAKE_PICTURE;
    AlertDialog alertDialog;
    Button cameraButton;
    EditText discussTaste;
    EditText discussTitle;
    private MySimpleAdapter imageAdapter;
    private Gallery imageGallery;
    private ArrayList imageList;
    TextView mWordCount;
    private String name;
    private Product product;
    AlertDialog resultAlertDialog;
    Button submitButton;
    TextView title;
    private Uri uri;
    ProgressDialog xh_pDialog;








}
