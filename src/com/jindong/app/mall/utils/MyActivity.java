// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.entity.CartTable;
import com.jindong.app.mall.entity.PacksTable;
import com.jindong.app.mall.home.HomeActivity;
import com.jindong.app.mall.utils.frame.ScrollableTabActivity;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, DBHelperUtil, HttpGroup

public class MyActivity extends Activity
{
    public static interface DestroyListener
    {

        public abstract void onDestroy();
    }

    public static interface PauseListener
    {

        public abstract void onPause();
    }

    public static interface ResumeListener
    {

        public abstract void onResume();
    }


    public MyActivity()
    {
        destroyListenerList = new ArrayList();
        pauseListenerList = new ArrayList();
        resumeListenerList = new ArrayList();
        isCanResend = true;
    }

    private Thread getUiThread()
    {
        return mUiThread;
    }

    public void addDestroyListener(DestroyListener destroylistener)
    {
        if(destroyListenerList != null)
            destroyListenerList.add(destroylistener);
    }

    public void addPauseListener(PauseListener pauselistener)
    {
        if(pauseListenerList != null)
            pauseListenerList.add(pauselistener);
    }

    public void addResumeListener(ResumeListener resumelistener)
    {
        if(resumeListenerList != null)
            resumeListenerList.add(resumelistener);
    }

    public void alert(int i)
    {
        if(hintDialogBuilder == null)
        {
            hintDialogBuilder = new android.app.AlertDialog.Builder(this);
            hintDialogBuilder.setTitle(0x7f09003c);
            hintDialogBuilder.setMessage(i);
            hintDialogBuilder.setPositiveButton(0x7f09000e, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    dialoginterface.dismiss();
                }

                final MyActivity this$0;

            
            {
                this$0 = MyActivity.this;
                super();
            }
            }
);
        }
        hintDialogBuilder.show();
    }

    public void attemptRunOnUiThread(Runnable runnable)
    {
        if(Thread.currentThread() != getUiThread())
            post(runnable);
        else
            runnable.run();
    }

    public void canNotResend()
    {
        isCanResend = false;
    }

    public HashMap createTaskId(Intent intent)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("className", intent.getComponent().getClassName());
        return hashmap;
    }

    public void finish()
    {
        hideSoftInput();
        super.finish();
    }

    public boolean getBooleanFromPreference(String s)
    {
        return sharedPreferences.getBoolean(s, false);
    }

    public boolean getBooleanFromPreference(String s, boolean flag)
    {
        return sharedPreferences.getBoolean(s, flag);
    }

    public com.jindong.app.mall.utils.frame.TabBarButton.StateController getCarStateController()
    {
        return carStateController;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public HttpGroup getHttpGroupaAsynPool()
    {
        return getHttpGroupaAsynPool(1000);
    }

    public HttpGroup getHttpGroupaAsynPool(int i)
    {
        HttpGroup.HttpGroupSetting httpgroupsetting = new HttpGroup.HttpGroupSetting();
        httpgroupsetting.setMyActivity(this);
        httpgroupsetting.setType(i);
        return getHttpGroupaAsynPool(httpgroupsetting);
    }

    public HttpGroup getHttpGroupaAsynPool(HttpGroup.HttpGroupSetting httpgroupsetting)
    {
        HttpGroup.HttpGroupaAsynPool httpgroupaasynpool = new HttpGroup.HttpGroupaAsynPool(httpgroupsetting);
        addDestroyListener(httpgroupaasynpool);
        return httpgroupaasynpool;
    }

    public String getStringFromPreference(String s)
    {
        return sharedPreferences.getString(s, "");
    }

    public String getStringFromPreference(String s, String s1)
    {
        return sharedPreferences.getString(s, s1);
    }

    public void hideSoftInput()
    {
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    public void noShowAgain()
    {
        ((ScrollableTabActivity)getParent()).markJump();
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if(i == 1001 && j == 0)
            MyApplication.exitAll();
    }

    protected void onCreate(Bundle bundle)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("onCreate() -->> ")).append(getClass().getName()).toString());
        mUiThread = Thread.currentThread();
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        handler = new Handler();
        super.onCreate(bundle);
        if(ScrollableTabActivity.isSingleInstance(getIntent()))
            destroyListenerList = null;
        sharedPreferences = getSharedPreferences("jdAndroidClient", 0);
        (new Thread(new Runnable() {

            public void run()
            {
                validatCartIcon();
            }

            final MyActivity this$0;

            
            {
                this$0 = MyActivity.this;
                super();
            }
        }
)).start();
    }

    protected void onDestroy()
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("onDestroy() -->> ")).append(getClass().getName()).toString());
        super.onDestroy();
        if(destroyListenerList == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = destroyListenerList.iterator();
_L6:
        if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
        destroyListenerList = null;
        pauseListenerList = null;
        resumeListenerList = null;
_L2:
        return;
_L4:
        ((DestroyListener)iterator.next()).onDestroy();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onHideModal()
    {
    }

    protected void onPause()
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("onPause() -->> ")).append(getClass().getName()).toString());
        super.onPause();
        Iterator iterator = pauseListenerList.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            ((PauseListener)iterator.next()).onPause();
        } while(true);
    }

    protected void onRestart()
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("onRestart() -->> ")).append(getClass().getName()).toString());
        super.onRestart();
    }

    protected void onResume()
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("onResume() -->> ")).append(getClass().getName()).toString());
        super.onResume();
        isCanResend = true;
        Iterator iterator = resumeListenerList.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            ((ResumeListener)iterator.next()).onResume();
        } while(true);
    }

    public boolean onSearchRequested()
    {
        if(Log.I)
            Log.i("test", "onSearchRequested++++my");
        if((getParent() instanceof ScrollableTabActivity) && !((ScrollableTabActivity)getParent()).getCurrentActivity().getClass().getSimpleName().equals(com/jindong/app/mall/home/HomeActivity.getSimpleName()))
            ((ScrollableTabActivity)getParent()).onSearchRequested();
        return true;
    }

    public void onShowModal()
    {
    }

    protected void onStop()
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("onStop() -->> ")).append(getClass().getName()).toString());
        super.onStop();
    }

    public void post(final Runnable action)
    {
        handler.post(new Runnable() {

            public void run()
            {
                if(!isFinishing())
                    action.run();
            }

            final MyActivity this$0;
            private final Runnable val$action;

            
            {
                this$0 = MyActivity.this;
                action = runnable;
                super();
            }
        }
);
    }

    public void post(final Runnable action, int i)
    {
        handler.postDelayed(new Runnable() {

            public void run()
            {
                if(!isFinishing())
                    action.run();
            }

            final MyActivity this$0;
            private final Runnable val$action;

            
            {
                this$0 = MyActivity.this;
                action = runnable;
                super();
            }
        }
, i);
    }

    public void putBooleanToPreference(String s, Boolean boolean1)
    {
        sharedPreferences.edit().putBoolean(s, boolean1.booleanValue()).commit();
    }

    public void putStringToPreference(String s, String s1)
    {
        sharedPreferences.edit().putString(s, s1).commit();
    }

    public void removeAllHistory()
    {
        if(Log.D)
            Log.d("MyActivity", "removeAllHistory() -->> ");
        ((ScrollableTabActivity)getParent()).removeAllRecords();
    }

    public void resendActivityInFrame(final Intent intent)
    {
        if(isCanResend)
        {
            if(Log.D)
                Log.d("MyActivity", (new StringBuilder("resendActivityInFrame() -->> ")).append(intent).toString());
            final ScrollableTabActivity parent = (ScrollableTabActivity)getParent();
            parent.pushResendRequest(new Runnable() {

                public void run()
                {
                    intent.putExtra("com.360buy:resendFlag", true);
                    parent.startSubActivity(intent);
                }

                final MyActivity this$0;
                private final Intent val$intent;
                private final ScrollableTabActivity val$parent;

            
            {
                this$0 = MyActivity.this;
                intent = intent1;
                parent = scrollabletabactivity;
                super();
            }
            }
);
        }
    }

    public void startActivityInFrame(final Intent intent)
    {
        if(Log.D)
            Log.d("MyActivity", "startActivityInFrame() -->> ");
        if(Integer.valueOf(android.os.Build.VERSION.SDK).intValue() > 10)
            post(new Runnable() {

                public void run()
                {
                    ((ScrollableTabActivity)getParent()).startSubActivity(intent);
                }

                final MyActivity this$0;
                private final Intent val$intent;

            
            {
                this$0 = MyActivity.this;
                intent = intent1;
                super();
            }
            }
);
        else
            ((ScrollableTabActivity)getParent()).startSubActivity(intent);
    }

    public void startSingleActivityInFrame(Intent intent)
    {
        if(Log.D)
            Log.d("MyActivity", "startSingleActivityInFrame() -->> ");
        intent.putExtra("com.360buy:singleInstanceFlag", true);
        ((ScrollableTabActivity)getParent()).startSubActivity(intent);
    }

    public void startTaskActivityInFrame(Intent intent)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("startTaskActivityInFrame() -->> ")).append(intent).toString());
        startTaskActivityInFrame(intent, createTaskId(intent));
    }

    public void startTaskActivityInFrame(Intent intent, HashMap hashmap)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("startTaskActivityInFrame() -->> ")).append(intent).append("|").append(hashmap).toString());
        intent.putExtra("com.360buy:taskIdFlag", hashmap);
        intent.putExtra("com.360buy:navigationDisplayFlag", -1);
        startActivityInFrame(intent);
    }

    public void validatCartIcon()
    {
        ArrayList arraylist;
        int i;
        arraylist = (new DBHelperUtil(this)).getCartList();
        i = 0;
        if(arraylist == null || arraylist.size() == 0) goto _L2; else goto _L1
_L1:
        int k = 0;
_L6:
        if(k < arraylist.size()) goto _L3; else goto _L2
_L2:
        ArrayList arraylist1 = (new DBHelperUtil(this)).getPacksList();
        if(arraylist1 == null || arraylist1.size() == 0) goto _L5; else goto _L4
_L4:
        int j = 0;
_L7:
        if(j < arraylist1.size())
            break MISSING_BLOCK_LABEL_123;
_L5:
        final com.jindong.app.mall.utils.frame.TabBarButton.StateController carStateController2 = getCarStateController();
        final Integer num;
        if(i == 0)
            num = null;
        else
            num = Integer.valueOf(i);
        post(new Runnable() {

            public void run()
            {
                carStateController2.setNum(num);
            }

            final MyActivity this$0;
            private final com.jindong.app.mall.utils.frame.TabBarButton.StateController val$carStateController2;
            private final Integer val$num;

            
            {
                this$0 = MyActivity.this;
                carStateController2 = statecontroller;
                num = integer;
                super();
            }
        }
);
        return;
_L3:
        i += ((CartTable)arraylist.get(k)).buyCount;
        k++;
          goto _L6
        i += ((PacksTable)arraylist1.get(j)).buyCount * ((PacksTable)arraylist1.get(j)).childCount;
        j++;
          goto _L7
    }

    private static final String TAG = "MyActivity";
    public static com.jindong.app.mall.utils.frame.TabBarButton.StateController carStateController;
    private static android.app.AlertDialog.Builder hintDialogBuilder;
    private ArrayList destroyListenerList;
    private Handler handler;
    private boolean isCanResend;
    private Thread mUiThread;
    private ArrayList pauseListenerList;
    private ArrayList resumeListenerList;
    private SharedPreferences sharedPreferences;
}
