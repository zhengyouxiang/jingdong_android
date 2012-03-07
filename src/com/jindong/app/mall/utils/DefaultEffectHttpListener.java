// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.graphics.drawable.ColorDrawable;
import android.view.*;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            MyActivity, Log

public class DefaultEffectHttpListener
    implements HttpGroup.OnStartListener, HttpGroup.OnEndListener, HttpGroup.OnErrorListener, MyActivity.DestroyListener
{
    private class State
        implements Runnable
    {

        private void addModal()
        {
            if(hasThread)
            {
                waitTime = -1;
                notify();
            } else
            {
                final ViewGroup rootFrameLayout = getRootFrameLayout();
                final ViewGroup modal = getModal();
                newProgressBar();
                myActivity.post(new Runnable() {

                    public void run()
                    {
                        if(Log.D)
                            Log.d("DefaultEffectHttpListener", (new StringBuilder("state add modal -->> ")).append(modal).toString());
                        rootFrameLayout.addView(modal, new android.view.ViewGroup.LayoutParams(-1, -1));
                        rootFrameLayout.invalidate();
                        myActivity.onShowModal();
                    }

                    final State this$1;
                    private final ViewGroup val$modal;
                    private final ViewGroup val$rootFrameLayout;

                
                {
                    this$1 = State.this;
                    modal = viewgroup;
                    rootFrameLayout = viewgroup1;
                    super();
                }
                }
);
            }
        }

        private ViewGroup getModal()
        {
            ViewGroup viewgroup;
            if(modal != null)
            {
                viewgroup = modal;
            } else
            {
                modal = new RelativeLayout(myActivity);
                modal.setOnTouchListener(new android.view.View.OnTouchListener() {

                    public boolean onTouch(View view, MotionEvent motionevent)
                    {
                        return true;
                    }

                    final State this$1;

                
                {
                    this$1 = State.this;
                    super();
                }
                }
);
                ColorDrawable colordrawable = new ColorDrawable(0xff000000);
                colordrawable.setAlpha(100);
                modal.setBackgroundDrawable(colordrawable);
                viewgroup = modal;
            }
            return viewgroup;
        }

        private ViewGroup getRootFrameLayout()
        {
            ViewGroup viewgroup;
            if(rootFrameLayout != null)
            {
                viewgroup = rootFrameLayout;
            } else
            {
                rootFrameLayout = (ViewGroup)myActivity.getWindow().peekDecorView();
                if(rootFrameLayout == null)
                {
                    try
                    {
                        Thread.sleep(50L);
                    }
                    catch(InterruptedException interruptedexception) { }
                    rootFrameLayout = getRootFrameLayout();
                }
                viewgroup = rootFrameLayout;
            }
            return viewgroup;
        }

        private void newProgressBar()
        {
            myActivity.post(new Runnable() {

                public void run()
                {
                    modal.removeView(progressBar);
                    progressBar = new ProgressBar(myActivity);
                    modal.addView(progressBar, layoutParams);
                }

                final State this$1;

                
                {
                    this$1 = State.this;
                    super();
                }
            }
);
        }

        private void removeModal()
        {
            if(hasThread)
            {
                waitTime = 500;
                notify();
            } else
            {
                (new Thread(this)).start();
                hasThread = true;
            }
        }

        /**
         * @deprecated Method add is deprecated
         */

        public boolean add()
        {
            this;
            JVM INSTR monitorenter ;
            n = 1 + n;
            if(n != 1) goto _L2; else goto _L1
_L1:
            if(Log.D)
                Log.d("DefaultEffectHttpListener", (new StringBuilder("state add true -->> ")).append(this).toString());
            addModal();
            boolean flag = true;
_L4:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            flag = false;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method remove is deprecated
         */

        public boolean remove()
        {
            this;
            JVM INSTR monitorenter ;
            n = n - 1;
            if(n >= 1) goto _L2; else goto _L1
_L1:
            if(Log.D)
                Log.d("DefaultEffectHttpListener", (new StringBuilder("state remove true -->> ")).append(this).toString());
            removeModal();
            boolean flag = true;
_L4:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            flag = false;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method run is deprecated
         */

        public void run()
        {
            this;
            JVM INSTR monitorenter ;
_L6:
            int i = waitTime;
            if(i != -1) goto _L2; else goto _L1
_L1:
            if(Log.D)
                Log.d("Temp", "modal wait start -->> ");
            wait();
            if(Log.D)
                Log.d("Temp", "modal wait end -->> ");
_L3:
            if(waitTime != 0)
                continue; /* Loop/switch isn't completed */
            final ViewGroup rootFrameLayout = getRootFrameLayout();
            final ViewGroup modal = getModal();
            myActivity.post(new Runnable() {

                public void run()
                {
                    if(Log.D)
                        Log.d("DefaultEffectHttpListener", (new StringBuilder("state remove modal -->> ")).append(modal).toString());
                    rootFrameLayout.removeView(modal);
                    rootFrameLayout.invalidate();
                    myActivity.onHideModal();
                }

                final State this$1;
                private final ViewGroup val$modal;
                private final ViewGroup val$rootFrameLayout;

                
                {
                    this$1 = State.this;
                    modal = viewgroup;
                    rootFrameLayout = viewgroup1;
                    super();
                }
            }
);
            waitTime = 500;
            hasThread = false;
            this;
            JVM INSTR monitorexit ;
            return;
            InterruptedException interruptedexception1;
            interruptedexception1;
            interruptedexception1.printStackTrace();
              goto _L3
            Exception exception;
            exception;
            throw exception;
_L2:
            int j = waitTime;
            waitTime = 0;
            if(Log.D)
                Log.d("Temp", (new StringBuilder("modal wait(")).append(j).append(") start -->> ").toString());
            wait(j);
            if(Log.D)
                Log.d("Temp", (new StringBuilder("modal wait(")).append(j).append(") end -->> ").toString());
              goto _L3
            InterruptedException interruptedexception;
            interruptedexception;
            interruptedexception.printStackTrace();
            if(true) goto _L3; else goto _L4
_L4:
            if(true) goto _L6; else goto _L5
_L5:
        }

        private static final int WAITING = -1;
        private static final int WAIT_TIME = 500;
        private boolean hasThread;
        private android.widget.RelativeLayout.LayoutParams layoutParams;
        private ViewGroup modal;
        private MyActivity myActivity;
        private int n;
        private ProgressBar progressBar;
        private ViewGroup rootFrameLayout;
        final DefaultEffectHttpListener this$0;
        private int waitTime;






        public State(MyActivity myactivity)
        {
            this$0 = DefaultEffectHttpListener.this;
            super();
            waitTime = 500;
            layoutParams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            myActivity = myactivity;
        }
    }


    public DefaultEffectHttpListener(HttpGroup.HttpSetting httpsetting, MyActivity myactivity)
    {
        onStartListener = httpsetting.getOnStartListener();
        onEndListener = httpsetting.getOnEndListener();
        onErrorListener = httpsetting.getOnErrorListener();
        myActivity = myactivity;
        myactivity.addDestroyListener(this);
    }

    /**
     * @deprecated Method hideModal is deprecated
     */

    private void hideModal()
    {
        this;
        JVM INSTR monitorenter ;
        MyActivity myactivity = myActivity;
        if(myactivity != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        ((State)stateMap.get(myActivity)).remove();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    /**
     * @deprecated Method showModal is deprecated
     */

    private void showModal()
    {
        this;
        JVM INSTR monitorenter ;
        MyActivity myactivity = myActivity;
        if(myactivity != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Map map = stateMap;
        map;
        JVM INSTR monitorenter ;
        State state;
        State state1;
        if(Log.D)
            Log.d("DefaultEffectHttpListener", (new StringBuilder("state get with -->> ")).append(myActivity).toString());
        state = (State)stateMap.get(myActivity);
        if(Log.D)
            Log.d("DefaultEffectHttpListener", (new StringBuilder("state get -->> ")).append(state).toString());
        if(state != null)
            break MISSING_BLOCK_LABEL_133;
        state1 = new State(myActivity);
        stateMap.put(myActivity, state1);
        state = state1;
        map;
        JVM INSTR monitorexit ;
        if(Log.D)
            Log.d("DefaultEffectHttpListener", (new StringBuilder("state is -->> ")).append(state).toString());
        state.add();
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
_L5:
        map;
        JVM INSTR monitorexit ;
        throw exception1;
        exception1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    /**
     * @deprecated Method onDestroy is deprecated
     */

    public void onDestroy()
    {
        this;
        JVM INSTR monitorenter ;
        stateMap.remove(myActivity);
        myActivity = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onEnd(HttpGroup.HttpResponse httpresponse)
    {
        if(onEndListener != null)
            onEndListener.onEnd(httpresponse);
        hideModal();
    }

    public void onError(HttpGroup.HttpError httperror)
    {
        if(onErrorListener != null)
            onErrorListener.onError(httperror);
        hideModal();
    }

    public void onStart()
    {
        showModal();
        if(onStartListener != null)
            onStartListener.onStart();
    }

    private static final String TAG = "DefaultEffectHttpListener";
    private static final Map stateMap = Collections.synchronizedMap(new HashMap());
    private MyActivity myActivity;
    private HttpGroup.OnEndListener onEndListener;
    private HttpGroup.OnErrorListener onErrorListener;
    private HttpGroup.OnStartListener onStartListener;

}
