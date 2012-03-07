// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.barcode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.jindong.app.mall.entity.BarcodeRecord;
import com.jindong.app.mall.entity.Product;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.utils.*;
import com.jindong.lib.zxing.client.android.CaptureActivity;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.jindong.app.mall.barcode:
//            BarcodeInputActivity, BarcodeProductListActivity

public class BarcodeActivity extends MyActivity
{

    public BarcodeActivity()
    {
        historyRecordList = new ArrayList();
    }

    private void checkEmptyForCutUI()
    {
        if(historyRecordList.size() > 0)
        {
            clearButton.setVisibility(0);
            welcomeView.setVisibility(8);
            historyHeader.setVisibility(0);
            historyHeaderNderline.setVisibility(0);
            historyListView.setVisibility(0);
        } else
        {
            clearButton.setVisibility(8);
            welcomeView.setVisibility(0);
            historyHeader.setVisibility(8);
            historyHeaderNderline.setVisibility(8);
            historyListView.setVisibility(8);
        }
    }

    private void clear()
    {
        dbHelper.delAllBarcodeRecord();
        historyRecordList.clear();
        historyAdapter.notifyDataSetChanged();
    }

    private void findView()
    {
        clearButton = (Button)findViewById(0x7f0c02c8);
        historyListView = (ListView)findViewById(0x7f0c0024);
        welcomeView = findViewById(0x7f0c0020);
        historyHeader = (TextView)findViewById(0x7f0c0022);
        historyHeaderNderline = findViewById(0x7f0c0023);
        inputButton = (Button)findViewById(0x7f0c0025);
        scanButton = (Button)findViewById(0x7f0c0026);
    }

    private void init()
    {
        String s = getStringFromPreference("noBarcodeModels", null);
        if(s != null)
            noBarcodeModels = s.split(",");
        dbHelper = new DBHelperUtil(this);
        ArrayList arraylist = historyRecordList;
        String as[] = new String[2];
        as[0] = "content";
        as[1] = "productName";
        int ai[] = new int[2];
        ai[0] = 0x7f0c0027;
        ai[1] = 0x7f0c0028;
        historyAdapter = new MySimpleAdapter(this, arraylist, 0x7f030008, as, ai) {

            public void notifyDataSetChanged()
            {
                checkEmptyForCutUI();
                super.notifyDataSetChanged();
            }

            final BarcodeActivity this$0;

            
            {
                this$0 = BarcodeActivity.this;
                super(myactivity, list, i, as, ai);
            }
        }
;
        historyListView.setAdapter(historyAdapter);
        historyListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                BarcodeRecord barcoderecord = (BarcodeRecord)((Adapter)adapterview.getAdapter()).getItem(i);
                queryServer(barcoderecord);
            }

            final BarcodeActivity this$0;

            
            {
                this$0 = BarcodeActivity.this;
                super();
            }
        }
);
        historyListView.setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                final BarcodeRecord barcodeRecord = (BarcodeRecord)((Adapter)adapterview.getAdapter()).getItem(i);
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(BarcodeActivity.this);
                builder.setTitle(getString(0x7f090016));
                String as1[] = new String[1];
                as1[0] = getString(0x7f090015);
                builder.setItems(as1, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        if(j == 0)
                        {
                            dbHelper.delBarcodeRecord(barcodeRecord);
                            historyRecordList.remove(barcodeRecord);
                            historyAdapter.notifyDataSetChanged();
                        }
                        dialoginterface.dismiss();
                    }

                    final _cls4 this$1;
                    private final BarcodeRecord val$barcodeRecord;

                    
                    {
                        this$1 = _cls4.this;
                        barcodeRecord = barcoderecord;
                        super();
                    }
                }
).show();
                return true;
            }

            final BarcodeActivity this$0;


            
            {
                this$0 = BarcodeActivity.this;
                super();
            }
        }
);
        ArrayList arraylist1 = dbHelper.getBarcodeRecordList();
        if(Log.D)
            Log.d("BarcodeActivity", (new StringBuilder("barcodeRecordList -->> ")).append(arraylist1.size()).toString());
        recordHistory(arraylist1);
        if(isNoScan())
            scanButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    noScanAlertDialog();
                }

                final BarcodeActivity this$0;

            
            {
                this$0 = BarcodeActivity.this;
                super();
            }
            }
);
        else
            scanButton.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    scan();
                }

                final BarcodeActivity this$0;

            
            {
                this$0 = BarcodeActivity.this;
                super();
            }
            }
);
        inputButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                input();
            }

            final BarcodeActivity this$0;

            
            {
                this$0 = BarcodeActivity.this;
                super();
            }
        }
);
    }

    private void input()
    {
        startActivityForResult(new Intent(this, com/jindong/app/mall/barcode/BarcodeInputActivity), 1);
    }

    private boolean isNoScan()
    {
        String s = Build.MODEL;
        if(noBarcodeModels == null || s == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        if(i < noBarcodeModels.length) goto _L3; else goto _L2
_L2:
        boolean flag = false;
_L5:
        return flag;
_L3:
        if(Log.D)
            Log.d("BarcodeActivity", (new StringBuilder("noBarcodeModels[i] -->> ")).append(noBarcodeModels[i]).toString());
        if(Log.D)
            Log.d("BarcodeActivity", (new StringBuilder("model -->> ")).append(s).toString());
        if(!s.equals(noBarcodeModels[i]))
            break; /* Loop/switch isn't completed */
        flag = true;
        if(true) goto _L5; else goto _L4
_L4:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void noScanAlertDialog()
    {
        final AlertDialog alertDialog = (new android.app.AlertDialog.Builder(this)).create();
        alertDialog.setMessage(getText(0x7f090026));
        alertDialog.setButton(getText(0x7f09000e), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                alertDialog.dismiss();
            }

            final BarcodeActivity this$0;
            private final AlertDialog val$alertDialog;

            
            {
                this$0 = BarcodeActivity.this;
                alertDialog = alertdialog;
                super();
            }
        }
);
        alertDialog.show();
    }

    private void queryServer(final BarcodeRecord barcodeRecord)
    {
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("wareIdByBarCodeList");
        httpsetting.putJsonParam("barcode", barcodeRecord.getContent());
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                ArrayList arraylist = Product.toList(httpresponse.getJSONObject().getJSONArrayOrNull("wareInfoList"), 1);
                if(arraylist != null && arraylist.size() != 0) goto _L2; else goto _L1
_L1:
                barcodeRecord.setProductName(getResources().getString(0x7f09002c));
                dbHelper.insertOrUpdateBarcodeRecord(barcodeRecord);
                post(new Runnable() {

                    public void run()
                    {
                        historyAdapter.notifyDataSetChanged();
                        Toast.makeText(_fld0, 0x7f09002c, 0).show();
                    }

                    final _cls9 this$1;

                    
                    {
                        this$1 = _cls9.this;
                        super();
                    }
                }
);
_L4:
                return;
_L2:
                if(arraylist.size() == 1)
                {
                    Product product1 = (Product)arraylist.get(0);
                    barcodeRecord.setProduct(product1);
                    dbHelper.insertOrUpdateBarcodeRecord(barcodeRecord);
                    final Intent intent = new Intent(BarcodeActivity.this, com/jindong/app/mall/product/ProductDetailActivity);
                    Bundle bundle1 = new Bundle();
                    bundle1.putLong("id", product1.getId().longValue());
                    intent.putExtras(bundle1);
                    post(new Runnable() {

                        public void run()
                        {
                            historyAdapter.notifyDataSetChanged();
                            startActivityInFrame(intent);
                        }

                        final _cls9 this$1;
                        private final Intent val$intent;

                    
                    {
                        this$1 = _cls9.this;
                        intent = intent1;
                        super();
                    }
                    }
);
                } else
                if(arraylist.size() > 1)
                {
                    Product product = (Product)arraylist.get(0);
                    barcodeRecord.setProduct(product);
                    dbHelper.insertOrUpdateBarcodeRecord(barcodeRecord);
                    final Intent intent = new Intent(BarcodeActivity.this, com/jindong/app/mall/barcode/BarcodeProductListActivity);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("productList", arraylist);
                    bundle.putSerializable("barcodeRecord", barcodeRecord);
                    intent.putExtras(bundle);
                    post(new Runnable() {

                        public void run()
                        {
                            historyAdapter.notifyDataSetChanged();
                            startActivityInFrame(intent);
                        }

                        final _cls9 this$1;
                        private final Intent val$intent;

                    
                    {
                        this$1 = _cls9.this;
                        intent = intent1;
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
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final BarcodeActivity this$0;
            private final BarcodeRecord val$barcodeRecord;


            
            {
                this$0 = BarcodeActivity.this;
                barcodeRecord = barcoderecord;
                super();
            }
        }
);
        getHttpGroupaAsynPool().add(httpsetting);
    }

    private void recordHistory(BarcodeRecord barcoderecord)
    {
        if(historyRecordList.size() == 0)
            historyHeader.setText(0x7f09002b);
        dbHelper.insertOrUpdateBarcodeRecord(barcoderecord);
        historyRecordList.add(0, barcoderecord);
        historyAdapter.notifyDataSetChanged();
    }

    private void recordHistory(ArrayList arraylist)
    {
        if(historyRecordList.size() == 0 && arraylist.size() > 0)
            historyHeader.setText(0x7f09002b);
        historyRecordList.addAll(arraylist);
        historyAdapter.notifyDataSetChanged();
    }

    private void scan()
    {
        Intent intent = new Intent(this, com/jindong/lib/zxing/client/android/CaptureActivity);
        intent.setAction("com.jindong.lib.zxing.client.android.SCAN");
        intent.putExtra("SCAN_FORMATS", "EAN_13,EAN_8,QR_CODE");
        intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == -1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch(i)
        {
        case 0: // '\0'
            BarcodeRecord barcoderecord1 = new BarcodeRecord();
            barcoderecord1.setContent(intent.getStringExtra("SCAN_RESULT"));
            barcoderecord1.setFormat(intent.getStringExtra("SCAN_RESULT_FORMAT"));
            recordHistory(barcoderecord1);
            queryServer(barcoderecord1);
            break;

        case 1: // '\001'
            BarcodeRecord barcoderecord = new BarcodeRecord();
            barcoderecord.setContent(intent.getStringExtra("SCAN_RESULT"));
            barcoderecord.setFormat(intent.getStringExtra("SCAN_RESULT_FORMAT"));
            recordHistory(barcoderecord);
            queryServer(barcoderecord);
            break;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030007);
        findView();
        init();
        ((TextView)findViewById(0x7f0c02c7)).setText(0x7f090022);
        clearButton.setText(0x7f090023);
        clearButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(BarcodeActivity.this);
                builder.setTitle(0x7f090024);
                builder.setMessage(0x7f090025);
                builder.setNegativeButton(0x7f09000f, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        dialoginterface.dismiss();
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                builder.setPositiveButton(0x7f09000e, new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        clear();
                        dialoginterface.dismiss();
                    }

                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        super();
                    }
                }
);
                builder.show();
            }

            final BarcodeActivity this$0;


            
            {
                this$0 = BarcodeActivity.this;
                super();
            }
        }
);
    }

    protected void onResume()
    {
        super.onResume();
    }

    private static final int INPUT = 1;
    private static final int SCAN = 0;
    private static final String TAG = "BarcodeActivity";
    private Button clearButton;
    private DBHelperUtil dbHelper;
    private MySimpleAdapter historyAdapter;
    private TextView historyHeader;
    private View historyHeaderNderline;
    private ListView historyListView;
    private ArrayList historyRecordList;
    private Button inputButton;
    private String noBarcodeModels[];
    private Button scanButton;
    private View welcomeView;









}
