// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall;

import android.app.AlertDialog;
import android.content.*;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.barcode.BarcodeActivity;
import com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jindong.app.mall.category.CategoryActivity;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.home.HomeActivity;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.more.AboutActivity;
import com.jindong.app.mall.more.FeedbackActivity;
import com.jindong.app.mall.more.HelpActivity;
import com.jindong.app.mall.more.HistoryListActivity;
import com.jindong.app.mall.more.SearchActivity;
import com.jindong.app.mall.more.SettingActivity;
import com.jindong.app.mall.personel.MyMessageShow;
import com.jindong.app.mall.personel.PersonelActivity;
import com.jindong.app.mall.product.ProductDetailActivity;
import com.jindong.app.mall.product.ProductListActivity;
import com.jindong.app.mall.shopping.EasyGoAddrListActivity;
import com.jindong.app.mall.shopping.ShoppingCarActivity;
import com.jindong.app.mall.utils.CPAUtils;
import com.jindong.app.mall.utils.CommonUtil;
import com.jindong.app.mall.utils.DPIUtil;
import com.jindong.app.mall.utils.GlobalInitialization;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.ShortCutUtils;
import com.jindong.app.mall.utils.frame.ScrollableTabActivity;
import java.util.*;

// Referenced classes of package com.jindong.app.mall:
//            MyApplication

public class MainActivity extends ScrollableTabActivity
{

    public MainActivity()
    {
        tokens = new HashSet();
        resume = null;
        isShow = false;
    }

    private boolean checkNetState()
    {
        boolean flag = CommonUtil.CheckNetWork();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("CommonUtil.checkNetWorkType() -->> ")).append(CommonUtil.checkNetWorkType()).toString());
        if(!flag)
        {
            AlertDialog alertdialog = (new android.app.AlertDialog.Builder(this)).create();
            alertdialog.setTitle(0x7f090000);
            alertdialog.setMessage(getText(0x7f090001));
            alertdialog.setButton(getText(0x7f09000e), new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    MyApplication.exitAll();
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
);
            alertdialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                {
                    return true;
                }

                final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
            }
);
            alertdialog.show();
        }
        return flag;
    }

    private boolean globalInit()
    {
        rootFrameLayout = (ViewGroup)getWindow().peekDecorView();
        modal = new RelativeLayout(this);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(12);
        layoutparams.addRule(14);
        stateTextView = new TextView(this);
        stateTextView.setPadding(0, 0, 0, DPIUtil.percentHeight(0.25F));
        stateTextView.setTextColor(-1);
        modal.addView(stateTextView, layoutparams);
        modal.setBackgroundDrawable(getResources().getDrawable(0x7f020020));
        modal.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                return true;
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        rootFrameLayout.addView(modal, new android.view.ViewGroup.LayoutParams(-1, -1));
        rootFrameLayout.invalidate();
        setStateText("\u68C0\u67E5\u7F51\u7EDC\u72B6\u6001...");
        boolean flag;
        if(!checkNetState())
        {
            flag = false;
        } else
        {
            if(CommonUtil.getJdSharedPreferences().getBoolean("showCost", true) && Configuration.getBooleanProperty("costHint").booleanValue())
                showHintDialog();
            loadHomeActivity();
            flag = true;
        }
        return flag;
    }

    private void loadHomeActivity()
    {
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                navigationInit();
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
, 100L);
        (new Handler()).postDelayed(new Runnable() {

            public void run()
            {
                deleteToken("");
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
, 5000L);
    }

    private void notifyTokenChange()
    {
        if(tokens.size() <= 0)
        {
            rootFrameLayout.removeView(modal);
            rootFrameLayout.invalidate();
        }
    }

    private void showHintDialog()
    {
        final AlertDialog alertDialog = (new android.app.AlertDialog.Builder(this)).create();
        alertDialog.setTitle(0x7f09003c);
        RelativeLayout relativelayout = new RelativeLayout(this);
        View view = LayoutInflater.from(this).inflate(0x7f03001a, relativelayout);
        CheckBox checkbox = (CheckBox)view.findViewById(0x7f0c0082);
        checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                compoundbutton.getId();
                JVM INSTR tableswitch 2131492994 2131492994: default 24
            //                           2131492994 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                SharedPreferences sharedpreferences = CommonUtil.getJdSharedPreferences();
                if(flag)
                    sharedpreferences.edit().putBoolean("showCost", false).commit();
                else
                    sharedpreferences.edit().putBoolean("showCost", true).commit();
                if(true) goto _L1; else goto _L3
_L3:
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        checkbox.setChecked(false);
        alertDialog.setView(view);
        alertDialog.setButton(getText(0x7f09000e), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                alertDialog.dismiss();
            }

            final MainActivity this$0;
            private final AlertDialog val$alertDialog;

            
            {
                this$0 = MainActivity.this;
                alertDialog = alertdialog;
                super();
            }
        }
);
        alertDialog.setButton2(getText(0x7f09000f), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                MyApplication.exitAll();
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        alertDialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                return true;
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
        alertDialog.show();
    }

    private void showSearchActivity(Bundle bundle)
    {
        if(Log.D)
            Log.d("Temp", "showSearchActivity() -->> ");
        Intent intent = new Intent(this, com/jindong/app/mall/more/SearchActivity);
        if(bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, 272);
    }

    public void checkTargetActivity()
    {
        if(Log.D)
            Log.d("Temp", "checkTargetActivity() -->> ");
        Bundle bundle = getIntent().getExtras();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("checkTargetActivity() bundle -->> ")).append(bundle).toString());
        if(bundle != null)
        {
            com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command command = com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command.createCommand(getIntent());
            if(Log.D)
                Log.d("Temp", (new StringBuilder("checkTargetActivity() command -->> ")).append(command).toString());
            if(command != null)
                toTargetActivity(command);
        }
    }

    public String createToken(String s)
    {
        tokens.add(s);
        return s;
    }

    public void deleteToken(String s)
    {
        tokens.remove(s);
        notifyTokenChange();
    }

    public boolean hasTargetActivity()
    {
        if(Log.D)
            Log.d("Temp", "hasTargetActivity() -->> ");
        if(1 != targetActivityState) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        if(targetActivityState == 0)
        {
            Bundle bundle = getIntent().getExtras();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("hasTargetActivity() bundle -->> ")).append(bundle).toString());
            if(bundle != null)
            {
                com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command command = com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command.createCommand(getIntent());
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("hasTargetActivity() command -->> ")).append(command).toString());
                if(command != null)
                {
                    targetActivityState = 1;
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
            }
        }
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void navigationInit()
    {
        final Intent homeActivityIntent = new Intent(this, com/jindong/app/mall/home/HomeActivity);
        homeActivityIntent.putExtra("com.360buy:singleInstanceFlag", true);
        homeActivityIntent.putExtra("com.360buy:navigationFlag", true);
        homeActivityIntent.putExtra("com.360buy:navigationId", 0);
        final Intent personelActivityIntent = new Intent(this, com/jindong/app/mall/personel/PersonelActivity);
        personelActivityIntent.putExtra("com.360buy:singleInstanceFlag", true);
        Runnable runnable = new Runnable() {

            public void run()
            {
                if(LoginUser.hasLogin())
                    startSubActivity(personelActivityIntent);
                else
                    startSubActivity(homeActivityIntent);
            }

            final MainActivity this$0;
            private final Intent val$homeActivityIntent;
            private final Intent val$personelActivityIntent;

            
            {
                this$0 = MainActivity.this;
                personelActivityIntent = intent;
                homeActivityIntent = intent1;
                super();
            }
        }
;
        addTab(new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonStyle(this, "\u9996\u9875", 0x7f020095), new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonAction(this, runnable));
        Intent intent = new Intent(this, com/jindong/app/mall/category/CategoryActivity);
        intent.putExtra("com.360buy:navigationFlag", true);
        intent.putExtra("com.360buy:navigationId", 1);
        addTab(new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonStyle(this, "\u5206\u7C7B", 0x7f020091), new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonAction(this, intent));
        Intent intent1 = new Intent(this, com/jindong/app/mall/shopping/ShoppingCarActivity);
        intent1.putExtra("com.360buy:singleInstanceFlag", true);
        intent1.putExtra("com.360buy:navigationFlag", true);
        intent1.putExtra("com.360buy:navigationId", 2);
        addTab(new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonStyle(this, "\u8D2D\u7269\u8F66", 0x7f020090), new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonAction(this, intent1));
        Intent intent2 = new Intent(this, com/jindong/app/mall/barcode/BarcodeActivity);
        intent2.putExtra("com.360buy:singleInstanceFlag", true);
        intent2.putExtra("com.360buy:navigationFlag", true);
        intent2.putExtra("com.360buy:navigationId", 3);
        addTab(new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonStyle(this, "\u6761\u7801\u8D2D", 0x7f020085), new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonAction(this, intent2));
        Runnable runnable1 = new Runnable() {

            public void run()
            {
                openOptionsMenu();
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
;
        addTab(new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonStyle(this, "\u66F4\u591A", 0x7f020096), new com.jindong.app.mall.utils.frame.ScrollableTabActivity.ButtonAction(this, runnable1, false));
        commit();
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(Log.D)
            Log.d("Temp", "onActivityResult() -->> ");
        if(272 == i && -1 == j)
        {
            Intent intent1 = new Intent(this, com/jindong/app/mall/product/ProductListActivity);
            intent1.putExtras(intent.getExtras());
            startSubActivity(intent1);
        } else
        {
            super.onActivityResult(i, j, intent);
        }
    }

    public void onCreate(Bundle bundle)
    {
        MyApplication.getInstance().setMainActivity(this);
        super.onCreate(bundle);
        if(!getSharedPreferences("jdAndroidClient", 0).getBoolean("add_short_cut_flag", false) && Configuration.getBooleanProperty("applicationShortcut").booleanValue())
            ShortCutUtils.addSortcutForJdApp(this);
        if(globalInit());
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(tokens.size() <= 0)
            getMenuInflater().inflate(0x7f0d0000, menu);
        return true;
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(Log.I)
            Log.i("test", "onSearchRequested++++main");
        boolean flag;
        if(i == 84)
        {
            String s = com/jindong/app/mall/home/HomeActivity.getSimpleName();
            String s1 = com/jindong/app/mall/personel/PersonelActivity.getSimpleName();
            String s2 = getCurrentActivity().getClass().getSimpleName();
            if(!s.equals(s2) && !s1.equals(s2))
                showSearchActivity(null);
            flag = true;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        menuitem.getItemId();
        JVM INSTR tableswitch 2131493599 2131493606: default 52
    //                   2131493599 54
    //                   2131493600 87
    //                   2131493601 108
    //                   2131493602 126
    //                   2131493603 145
    //                   2131493604 183
    //                   2131493605 164
    //                   2131493606 202;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        return true;
_L2:
        Intent intent = new Intent(this, com/jindong/app/mall/shopping/EasyGoAddrListActivity);
        intent.putExtra("com.360buy:singleInstanceFlag", true);
        startSubActivity(intent);
        continue; /* Loop/switch isn't completed */
_L3:
        startActivityForResult(new Intent(this, com/jindong/app/mall/more/SearchActivity), 272);
        continue; /* Loop/switch isn't completed */
_L4:
        startActivity(new Intent(this, com/jindong/app/mall/more/SettingActivity));
        continue; /* Loop/switch isn't completed */
_L5:
        startSubActivity(new Intent(this, com/jindong/app/mall/more/HistoryListActivity));
        continue; /* Loop/switch isn't completed */
_L6:
        startSubActivity(new Intent(this, com/jindong/app/mall/more/FeedbackActivity));
        continue; /* Loop/switch isn't completed */
_L8:
        startSubActivity(new Intent(this, com/jindong/app/mall/more/AboutActivity));
        continue; /* Loop/switch isn't completed */
_L7:
        startSubActivity(new Intent(this, com/jindong/app/mall/more/HelpActivity));
        continue; /* Loop/switch isn't completed */
_L9:
        MyApplication.exitDialog();
        if(true) goto _L1; else goto _L10
_L10:
    }

    protected void onPause()
    {
        isShow = false;
        leaveTime = new Date();
        super.onPause();
    }

    protected void onResume()
    {
        if(Log.D)
            Log.d("Temp", "MainActivity onResume() -->> ");
        isShow = true;
        if(leaveTime != null && (new Date()).getTime() - leaveTime.getTime() > (long)Configuration.getIntegerProperty("leaveTimeGap").intValue())
            GlobalInitialization.regDevice();
        super.onResume();
        if(resume != null)
        {
            resume.run();
            resume = null;
        }
    }

    public void setStateText(String s)
    {
        stateTextView.setText(s);
    }

    public void toTargetActivity(com.jindong.app.mall.broadcastReceiver.InterfaceBroadcastReceiver.Command command)
    {
        int i;
        final Bundle tempBundle;
        InterfaceBroadcastReceiver.cps();
        i = command.getModuleId();
        tempBundle = command.getOutBundle();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("moduleId -->> ")).append(i).toString());
        if(!Log.D || tempBundle == null) goto _L2; else goto _L1
_L1:
        Iterator iterator;
        Log.d("Temp", (new StringBuilder("bundle -->> ")).append(tempBundle).toString());
        iterator = tempBundle.keySet().iterator();
_L9:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        i;
        JVM INSTR tableswitch 1 4: default 124
    //                   1 220
    //                   2 187
    //                   3 299
    //                   4 344;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        break; /* Loop/switch isn't completed */
_L8:
        break MISSING_BLOCK_LABEL_344;
_L10:
        targetActivityState = 2;
        return;
_L3:
        String s = (String)iterator.next();
        Object obj = tempBundle.get(s);
        Log.d("Temp", (new StringBuilder("bundle key value -->> ")).append(s).append("\uFF1A").append(obj).toString());
          goto _L9
_L6:
        if(Log.D)
            Log.d("Temp", "MainActivity toTargetActivity() -->> MODULE_ID_MESSAGE");
        startSubActivity(new Intent(this, com/jindong/app/mall/personel/MyMessageShow));
          goto _L10
_L5:
        if(Log.D)
            Log.d("Temp", "MainActivity toTargetActivity() -->> MODULE_ID_HOME");
        if(getCurrentActivity().getClass() == com/jindong/app/mall/home/HomeActivity)
        {
            targetActivityState = 2;
            ((HomeActivity)getCurrentActivity()).checkLoginStatus(false);
        } else
        {
            Intent intent1 = new Intent(this, com/jindong/app/mall/home/HomeActivity);
            intent1.putExtra("com.360buy:singleInstanceFlag", true);
            startSubActivity(intent1);
        }
          goto _L10
_L7:
        if(Log.D)
            Log.d("Temp", "MainActivity toTargetActivity() -->> MODULE_ID_SEARCH");
        if(!isShow)
            resume = new Runnable() {

                public void run()
                {
                    showSearchActivity(tempBundle);
                }

                final MainActivity this$0;
                private final Bundle val$tempBundle;

            
            {
                this$0 = MainActivity.this;
                tempBundle = bundle;
                super();
            }
            }
;
        else
            showSearchActivity(tempBundle);
          goto _L10
        if(Log.D)
            Log.d("Temp", "MainActivity toTargetActivity() -->> MODULE_ID_PRODUCT");
        Intent intent = new Intent(this, com/jindong/app/mall/product/ProductDetailActivity);
        intent.putExtras(tempBundle);
        startSubActivity(intent);
          goto _L10
    }

    public static final String CRAZYBUY_GLOBALINITTOKEN = "com.360buy:crazyBuyGlobalInitToken";
    public static final String JDNEWS_GLOBALINITTOKEN = "com.360buy:jdNewsGlobalInitToken";
    final int REQUEST_SEARCH_CODE = 272;
    private boolean isShow;
    private Date leaveTime;
    private ViewGroup modal;
    private Runnable resume;
    private ViewGroup rootFrameLayout;
    private TextView stateTextView;
    private int targetActivityState;
    private Set tokens;

    static 
    {
        CPAUtils.loadLibrary();
    }

}
