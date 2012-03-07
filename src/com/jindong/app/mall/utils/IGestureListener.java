// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.view.MotionEvent;
import java.io.PrintStream;

public class IGestureListener
    implements android.view.GestureDetector.OnGestureListener
{
    public static interface TouchFlingActionListener
    {

        public abstract void next();

        public abstract void previous();

        public abstract void startActivity();
    }


    public IGestureListener(TouchFlingActionListener touchflingactionlistener)
    {
        onListener = touchflingactionlistener;
    }

    public boolean onDown(MotionEvent motionevent)
    {
        System.out.println("onDown");
        return false;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        System.out.println((new StringBuilder("onFling velocityX=")).append(f).append(",velocityY=").append(f1).toString());
        if(motionevent.getX() - motionevent1.getX() <= 5F) goto _L2; else goto _L1
_L1:
        onListener.previous();
_L4:
        return false;
_L2:
        if(motionevent.getX() - motionevent1.getX() < -5F)
            onListener.next();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onLongPress(MotionEvent motionevent)
    {
        System.out.println("onLongPress");
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        System.out.println((new StringBuilder("onScroll distanceX=")).append(f).append(",distanceY=").append(f1).toString());
        if(motionevent.getX() - motionevent1.getX() <= 50F) goto _L2; else goto _L1
_L1:
        onListener.previous();
_L4:
        return false;
_L2:
        if(motionevent.getX() - motionevent1.getX() < -50F)
            onListener.next();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onShowPress(MotionEvent motionevent)
    {
        System.out.println("onShowPress");
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        System.out.println("onSingleTapUp");
        onListener.startActivity();
        return false;
    }

    private TouchFlingActionListener onListener;
}
