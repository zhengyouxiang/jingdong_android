// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.frame;

import android.app.*;
import android.content.*;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.utils.*;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils.frame:
//            RadioStateDrawable, TabBarButton

public class ScrollableTabActivity extends ActivityGroup
{
    public class ButtonAction
    {

        public boolean isHighlight()
        {
            return highlight;
        }

        public void run()
        {
            mRunnable.run();
        }

        public void setHighlight(boolean flag)
        {
            highlight = flag;
        }

        private boolean highlight;
        private Runnable mRunnable;
        final ScrollableTabActivity this$0;

        public ButtonAction(final Intent intent)
        {
            this(new Runnable() {

                public void run()
                {
                    this$0.startSubActivity(intent);
                }

                private final Intent val$intent;
                private final ScrollableTabActivity val$this$0;

                
                {
                    this$0 = scrollabletabactivity;
                    intent = intent1;
                    super();
                }
            }
);
        }

        public ButtonAction(Runnable runnable)
        {
            this(runnable, true);
        }

        public ButtonAction(Runnable runnable, boolean flag)
        {
            this$0 = ScrollableTabActivity.this;
            super();
            mRunnable = runnable;
            buttonActionList.add(this);
            setHighlight(flag);
        }
    }

    public class ButtonStyle
    {

        final ScrollableTabActivity this$0;

        public ButtonStyle(String s, int i)
        {
            this$0 = ScrollableTabActivity.this;
            super();
            int ai[] = new int[3];
            ai[0] = i;
            ai[1] = defaultOffShade;
            ai[2] = defaultOnShade;
            states.add(ai);
            titleList.add(s);
        }

        public ButtonStyle(String s, int i, int j)
        {
            this$0 = ScrollableTabActivity.this;
            super();
            int ai[] = new int[2];
            ai[0] = j;
            ai[1] = i;
            states.add(ai);
            titleList.add(s);
        }

        public ButtonStyle(String s, int i, int j, int k)
        {
            this$0 = ScrollableTabActivity.this;
            super();
            int ai[] = new int[3];
            ai[0] = i;
            ai[1] = j;
            ai[2] = k;
            states.add(ai);
            titleList.add(s);
        }
    }

    class CheckedChangeListener
        implements android.widget.RadioGroup.OnCheckedChangeListener
    {

        public void onCheckedChanged(RadioGroup radiogroup, int i)
        {
            if(!oldFlag) goto _L2; else goto _L1
_L1:
            oldFlag = false;
_L4:
            return;
_L2:
            if(mNow != i)
            {
                int j = mNow;
                mNow = i;
                ButtonAction buttonaction = (ButtonAction)buttonActionList.get(i);
                if(!buttonaction.isHighlight())
                    checkNoEvent(Integer.valueOf(j));
                buttonaction.run();
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void setOldFlag(boolean flag)
        {
            oldFlag = flag;
        }

        public void setmNow(int i)
        {
            mNow = i;
        }

        private int mNow;
        private boolean oldFlag;
        final ScrollableTabActivity this$0;

        CheckedChangeListener()
        {
            this$0 = ScrollableTabActivity.this;
            super();
        }
    }

    private class LayoutListener
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        public void init(View view1)
        {
            view = view1;
            initHeight = 0;
        }

        public void onGlobalLayout()
        {
            if(navigationDisplayMode != -1) goto _L2; else goto _L1
_L1:
            return;
_L2:
            int i = view.getHeight();
            if(initHeight == 0)
                initHeight = i;
            if(initHeight == i)
                (new Handler()).postDelayed(new Runnable() {

                    public void run()
                    {
                        showNavigation();
                    }

                    final LayoutListener this$1;

                
                {
                    this$1 = LayoutListener.this;
                    super();
                }
                }
, 100L);
            else
            if(initHeight > i)
                hideNavigation();
            if(true) goto _L1; else goto _L3
_L3:
        }

        public int initHeight;
        final ScrollableTabActivity this$0;
        private View view;


        private LayoutListener()
        {
            this$0 = ScrollableTabActivity.this;
            super();
        }

        LayoutListener(LayoutListener layoutlistener)
        {
            this();
        }
    }

    private static class Record
    {

        public String getId()
        {
            return id;
        }

        public Intent getIntent()
        {
            return intent;
        }

        public void setId(String s)
        {
            id = s;
        }

        public void setIntent(Intent intent1)
        {
            intent = intent1;
        }

        private String id;
        private Intent intent;

        private Record()
        {
        }

        Record(Record record)
        {
            this();
        }
    }


    public ScrollableTabActivity()
    {
        recordList = new Stack();
        singleInstanceRecordList = new ArrayList();
        recordIdAndRadioId = new HashMap();
        layoutListener = new LayoutListener(null);
        resendRequests = new Stack();
        radioGroupListener = new CheckedChangeListener();
    }

    private void check(String s)
    {
        Integer integer = findRadioId(s);
        if(integer != null)
            checkNoEvent(integer);
        else
            checkNoEvent(Integer.valueOf(-1));
    }

    private void checkNoEvent(Integer integer)
    {
        if(bottomRadioGroup.getCheckedRadioButtonId() != integer.intValue())
        {
            radioGroupListener.setOldFlag(true);
            radioGroupListener.setmNow(integer.intValue());
            bottomRadioGroup.check(integer.intValue());
        }
    }

    private void collectErrorData(Intent intent)
    {
        Bundle bundle;
        StringBuffer stringbuffer;
        bundle = intent.getExtras();
        stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder("activity class name\uFF1A")).append(intent.getComponent().getClassName()).toString());
        stringbuffer.append("\uFF0Cintent content\uFF1A");
        if(bundle == null) goto _L2; else goto _L1
_L1:
        Iterator iterator = bundle.keySet().iterator();
_L5:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        MyUncaughtExceptionHandler.resetErrorInfo(stringbuffer.toString());
        return;
_L3:
        String s = iterator.next().toString();
        stringbuffer.append((new StringBuilder(String.valueOf(s))).append("\uFF1A").toString());
        String s1;
        if(bundle.get(s) == null)
            s1 = "<null>";
        else
            s1 = bundle.get(s).toString();
        stringbuffer.append((new StringBuilder(String.valueOf(s1))).append("\uFF0C").toString());
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void doJump()
    {
        jump = false;
        removeRecordTop();
    }

    private Integer findRadioId(String s)
    {
        return (Integer)recordIdAndRadioId.get(s);
    }

    private Record findsingleInstanceRecord(Intent intent)
    {
        Iterator iterator = singleInstanceRecordList.iterator();
_L2:
        Record record1;
        if(!iterator.hasNext())
        {
            record1 = null;
        } else
        {
            Record record = (Record)iterator.next();
            if(!record.getIntent().filterEquals(intent))
                continue; /* Loop/switch isn't completed */
            record1 = record;
        }
        return record1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private Integer getNavigationId(Intent intent)
    {
        Integer integer;
        if(isNavigation(intent))
            integer = Integer.valueOf(intent.getIntExtra("com.360buy:navigationId", 0));
        else
            integer = null;
        return integer;
    }

    private HashMap getTaskId(Intent intent)
    {
        return (HashMap)intent.getSerializableExtra("com.360buy:taskIdFlag");
    }

    private boolean isJump()
    {
        return jump;
    }

    private boolean isNavigation(Intent intent)
    {
        return intent.getBooleanExtra("com.360buy:navigationFlag", false);
    }

    private boolean isResend(Intent intent)
    {
        return intent.getBooleanExtra("com.360buy:resendFlag", false);
    }

    public static boolean isSingleInstance(Intent intent)
    {
        return intent.getBooleanExtra("com.360buy:singleInstanceFlag", false);
    }

    private void showActivity(String s, Intent intent)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("showActivity() -->> ")).append(intent).toString());
        navigationDisplayMode = intent.getIntExtra("com.360buy:navigationDisplayFlag", 0);
        switch(navigationDisplayMode)
        {
        default:
            showNavigation();
            break;

        case -1: 
            break MISSING_BLOCK_LABEL_125;
        }
_L1:
        contentViewLayout.removeAllViews();
        View view = activityManager.startActivity(s, intent).getDecorView();
        if(!resendRequests.isEmpty())
        {
            Runnable runnable = (Runnable)resendRequests.pop();
            resendRequests.clear();
            runnable.run();
        } else
        {
            Activity activity = getCurrentActivity();
            if(activity instanceof MyActivity)
                ((MyActivity)activity).canNotResend();
            contentViewLayout.addView(view, contentViewLayoutParams);
            if(Log.D)
                Log.d("MyActivity", (new StringBuilder("showActivity() view ok ready highlight -->> ")).append(intent).toString());
            if(".personel.PersonelActivity".equals(intent.getComponent().getShortClassName()))
                checkNoEvent(Integer.valueOf(0));
            else
            if(".category.CategoryActivity".equals(intent.getComponent().getShortClassName()))
                checkNoEvent(Integer.valueOf(1));
            else
            if(".shopping.ShoppingCarActivity".equals(intent.getComponent().getShortClassName()))
                checkNoEvent(Integer.valueOf(2));
            else
                check(s);
        }
        return;
        hideNavigation();
          goto _L1
    }

    protected void addTab(ButtonStyle buttonstyle, ButtonAction buttonaction)
    {
    }

    public void commit()
    {
        bottomRadioGroup.removeAllViews();
        int i1 = getWindowManager().getDefaultDisplay().getWidth();
        int i = (int)((double)i1 / 64D);
_L1:
        int l;
        int j = getWindowManager().getDefaultDisplay().getWidth();
        Exception exception;
        int k;
        if(buttonActionList.size() <= i)
            k = j / buttonActionList.size();
        else
            k = j / 5;
        RadioStateDrawable.width = k;
        RadioStateDrawable.screen_width = j;
        buttonLayoutParams = new android.widget.RadioGroup.LayoutParams(k, -1);
        l = 0;
_L2:
        if(l >= buttonActionList.size())
        {
            bottomRadioGroup.check(0);
            ((ButtonAction)buttonActionList.get(0)).run();
            return;
        }
        break MISSING_BLOCK_LABEL_149;
        exception;
        i = 5;
          goto _L1
        TabBarButton tabbarbutton = new TabBarButton(this);
        if(l == 2)
            MyActivity.carStateController = tabbarbutton.getStateController();
        int ai[] = (int[])states.get(l);
        if(ai.length == 1)
            tabbarbutton.setState(((String)titleList.get(l)).toString(), ai[0]);
        else
        if(ai.length == 2)
            tabbarbutton.setState(((String)titleList.get(l)).toString(), ai[0], ai[1]);
        else
        if(ai.length == 3)
            tabbarbutton.setState(((String)titleList.get(l)).toString(), ai[0], ai[1], ai[2]);
        tabbarbutton.setBackgroundResource(0x7f020094);
        tabbarbutton.setId(l);
        tabbarbutton.setGravity(17);
        bottomRadioGroup.addView(tabbarbutton, l, buttonLayoutParams);
        l++;
          goto _L2
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        Activity activity = activityManager.getCurrentActivity();
        boolean flag;
        if(activity == null)
            flag = super.dispatchKeyEvent(keyevent);
        else
        if(activity.dispatchKeyEvent(keyevent))
            flag = true;
        else
            flag = super.dispatchKeyEvent(keyevent);
        return flag;
    }

    public void finish()
    {
        removeRecordTop();
        if(!recordList.empty())
        {
            Record record = (Record)recordList.peek();
            showActivity(record.getId(), record.getIntent());
        } else
        {
            LoginUser.setUserState(Contants.LOG_OFF);
            super.finish();
        }
    }

    public void finishFromChild(Activity activity)
    {
        finish();
    }

    public void finishThis()
    {
        super.finish();
    }

    public void hideNavigation()
    {
        bottomBar.setVisibility(8);
    }

    public void markJump()
    {
        jump = true;
    }

    public Record newActivity(String s, Intent intent)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("newActivity() -->> ")).append(intent).toString());
        Record record = new Record(null);
        record.setId(s);
        record.setIntent(intent);
        if(isSingleInstance(intent))
            singleInstanceRecordList.add(record);
        newActivity(record);
        return record;
    }

    public void newActivity(Record record)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("newActivity() -->> ")).append(record).toString());
        if(!isResend(record.getIntent())) goto _L2; else goto _L1
_L1:
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("newActivity() do resend -->> ")).append(record).toString());
        removeRecordTop();
_L4:
        if(!recordList.isEmpty())
        {
            HashMap hashmap = getTaskId(((Record)recordList.peek()).getIntent());
            if(hashmap != null)
            {
                HashMap hashmap1 = getTaskId(record.getIntent());
                if(hashmap1 == null || !hashmap.equals(hashmap1))
                {
                    if(Log.D)
                        Log.d("MyActivity", (new StringBuilder("newActivity() remove task record -->> ")).append(record).toString());
                    removeRecordTop();
                }
            }
        }
        recordList.push(record);
        Integer integer = getNavigationId(record.getIntent());
        if(integer != null && findRadioId(record.getId()) == null)
        {
            if(Log.D)
                Log.d("MyActivity", (new StringBuilder("newActivity() save Record id and Radio id -->> ")).append(record).toString());
            recordIdAndRadioId.put(record.getId(), integer);
        }
        collectErrorData(record.getIntent());
        showActivity(record.getId(), record.getIntent());
        return;
_L2:
        if(isJump())
        {
            if(Log.D)
                Log.d("MyActivity", (new StringBuilder("newActivity() do jump -->> ")).append(record).toString());
            doJump();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onCreate(Bundle bundle)
    {
        DPIUtil.setDensity(getResources().getDisplayMetrics().density);
        DPIUtil.setDefaultDisplay(getWindowManager().getDefaultDisplay());
        super.onCreate(bundle);
        context = this;
        activityManager = getLocalActivityManager();
        setContentView(0x7f03001b);
        contentViewLayout = (LinearLayout)findViewById(0x7f0c0084);
        bottomBar = (HorizontalScrollView)findViewById(0x7f0c0085);
        bottomRadioGroup = (RadioGroup)findViewById(0x7f0c0086);
        contentViewLayoutParams = new android.widget.LinearLayout.LayoutParams(-1, -1);
        defaultOffShade = 0;
        defaultOnShade = 3;
        bottomRadioGroup.setOnCheckedChangeListener(radioGroupListener);
        buttonActionList = new ArrayList();
        titleList = new ArrayList();
        states = new ArrayList();
        buttonLayoutParams = new android.widget.RadioGroup.LayoutParams(64, -2);
        View view = getWindow().getDecorView();
        ViewTreeObserver viewtreeobserver = view.getViewTreeObserver();
        layoutListener.init(((ViewGroup)((ViewGroup)view).getChildAt(0)).getChildAt(0));
        viewtreeobserver.removeGlobalOnLayoutListener(layoutListener);
        viewtreeobserver.addOnGlobalLayoutListener(layoutListener);
    }

    public void pushResendRequest(Runnable runnable)
    {
        resendRequests.push(runnable);
    }

    public void removeAllRecords()
    {
        int i = recordList.size() - 1;
        do
        {
            if(i <= 1)
                return;
            recordList.pop();
            i--;
        } while(true);
    }

    public void removeRecordTop()
    {
        if(!isPrevNotInRecord) goto _L2; else goto _L1
_L1:
        isPrevNotInRecord = false;
_L4:
        return;
_L2:
        if(!recordList.empty())
        {
            Record record = (Record)recordList.pop();
            if(!isSingleInstance(record.getIntent()))
                activityManager.destroyActivity(record.getId(), true);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setCurrentTab(int i)
    {
        bottomRadioGroup.check(i);
    }

    protected void setDefaultShade(int i, int j)
    {
        defaultOffShade = i;
        defaultOnShade = j;
    }

    public void showNavigation()
    {
        bottomBar.setVisibility(0);
    }

    public Record startSubActivity(Intent intent)
    {
        if(Log.D)
            Log.d("MyActivity", (new StringBuilder("startSubActivity() -->> ")).append(intent).toString());
        if(!isSingleInstance(intent)) goto _L2; else goto _L1
_L1:
        Record record1 = findsingleInstanceRecord(intent);
        if(record1 == null) goto _L2; else goto _L3
_L3:
        Record record;
        newActivity(record1);
        record = record1;
_L5:
        return record;
_L2:
        counter = 1 + counter;
        record = newActivity((new StringBuilder("subActivity:")).append(counter).toString(), intent);
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static final String NAVIGATION_DISPLAY_FLAG = "com.360buy:navigationDisplayFlag";
    public static final int NAVIGATION_DISPLAY_HIDE = -1;
    public static final int NAVIGATION_DISPLAY_SHOW = 0;
    public static final String NAVIGATION_FLAG = "com.360buy:navigationFlag";
    public static final String NAVIGATION_ID = "com.360buy:navigationId";
    public static final String RESEND_FLAG = "com.360buy:resendFlag";
    public static final String SINGLE_INSTANCE_FLAG = "com.360buy:singleInstanceFlag";
    public static final String TASK_ID_FLAG = "com.360buy:taskIdFlag";
    private LocalActivityManager activityManager;
    private HorizontalScrollView bottomBar;
    private RadioGroup bottomRadioGroup;
    private List buttonActionList;
    private android.widget.RadioGroup.LayoutParams buttonLayoutParams;
    private LinearLayout contentViewLayout;
    private android.widget.LinearLayout.LayoutParams contentViewLayoutParams;
    private Context context;
    private int counter;
    private int defaultOffShade;
    private int defaultOnShade;
    private boolean isPrevNotInRecord;
    private boolean jump;
    private LayoutListener layoutListener;
    private int navigationDisplayMode;
    private CheckedChangeListener radioGroupListener;
    private HashMap recordIdAndRadioId;
    private Stack recordList;
    private Stack resendRequests;
    private ArrayList singleInstanceRecordList;
    private List states;
    private List titleList;







}
