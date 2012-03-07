// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.util.Log;
import android.view.MotionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MultiTouchController
{
    public static interface MultiTouchObjectCanvas
    {

        public abstract Object getDraggableObjectAtPoint(PointInfo pointinfo);

        public abstract void getPositionAndScale(Object obj, PositionAndScale positionandscale);

        public abstract void selectObject(Object obj, PointInfo pointinfo);

        public abstract boolean setPositionAndScale(Object obj, PositionAndScale positionandscale, PointInfo pointinfo);
    }

    public static class PointInfo
    {

        private int julery_isqrt(int i)
        {
            int j = 0;
            int k = 32768;
            int l = 15;
            do
            {
                int i1 = k + (j << 1);
                int j1 = l - 1;
                int k1 = i1 << l;
                if(i >= k1)
                {
                    j += k;
                    i -= k1;
                }
                k >>= 1;
                if(k <= 0)
                    return j;
                l = j1;
            } while(true);
        }

        private void set(int i, float af[], float af1[], float af2[], int ai[], int j, boolean flag, 
                long l)
        {
            eventTime = l;
            action = j;
            numPoints = i;
            int k = 0;
            do
            {
                if(k >= i)
                {
                    isDown = flag;
                    boolean flag1;
                    if(i >= 2)
                        flag1 = true;
                    else
                        flag1 = false;
                    isMultiTouch = flag1;
                    if(isMultiTouch)
                    {
                        xMid = 0.5F * (af[0] + af[1]);
                        yMid = 0.5F * (af1[0] + af1[1]);
                        pressureMid = 0.5F * (af2[0] + af2[1]);
                        dx = Math.abs(af[1] - af[0]);
                        dy = Math.abs(af1[1] - af1[0]);
                    } else
                    {
                        xMid = af[0];
                        yMid = af1[0];
                        pressureMid = af2[0];
                        dy = 0.0F;
                        dx = 0.0F;
                    }
                    angleIsCalculated = false;
                    diameterIsCalculated = false;
                    diameterSqIsCalculated = false;
                    return;
                }
                xs[k] = af[k];
                ys[k] = af1[k];
                pressures[k] = af2[k];
                pointerIds[k] = ai[k];
                k++;
            } while(true);
        }

        public int getAction()
        {
            return action;
        }

        public long getEventTime()
        {
            return eventTime;
        }

        public float getMultiTouchAngle()
        {
            if(!angleIsCalculated)
            {
                if(!isMultiTouch)
                    angle = 0.0F;
                else
                    angle = (float)Math.atan2(ys[1] - ys[0], xs[1] - xs[0]);
                angleIsCalculated = true;
            }
            return angle;
        }

        public float getMultiTouchDiameter()
        {
            if(diameterIsCalculated) goto _L2; else goto _L1
_L1:
            if(isMultiTouch) goto _L4; else goto _L3
_L3:
            diameter = 0.0F;
_L6:
            diameterIsCalculated = true;
_L2:
            return diameter;
_L4:
            float f = getMultiTouchDiameterSq();
            float f1;
            if(f == 0.0F)
                f1 = 0.0F;
            else
                f1 = (float)julery_isqrt((int)(256F * f)) / 16F;
            diameter = f1;
            if(diameter < dx)
                diameter = dx;
            if(diameter < dy)
                diameter = dy;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public float getMultiTouchDiameterSq()
        {
            if(!diameterSqIsCalculated)
            {
                float f;
                if(isMultiTouch)
                    f = dx * dx + dy * dy;
                else
                    f = 0.0F;
                diameterSq = f;
                diameterSqIsCalculated = true;
            }
            return diameterSq;
        }

        public float getMultiTouchHeight()
        {
            float f;
            if(isMultiTouch)
                f = dy;
            else
                f = 0.0F;
            return f;
        }

        public float getMultiTouchWidth()
        {
            float f;
            if(isMultiTouch)
                f = dx;
            else
                f = 0.0F;
            return f;
        }

        public int getNumTouchPoints()
        {
            return numPoints;
        }

        public int[] getPointerIds()
        {
            return pointerIds;
        }

        public float getPressure()
        {
            return pressureMid;
        }

        public float[] getPressures()
        {
            return pressures;
        }

        public float getX()
        {
            return xMid;
        }

        public float[] getXs()
        {
            return xs;
        }

        public float getY()
        {
            return yMid;
        }

        public float[] getYs()
        {
            return ys;
        }

        public boolean isDown()
        {
            return isDown;
        }

        public boolean isMultiTouch()
        {
            return isMultiTouch;
        }

        public void set(PointInfo pointinfo)
        {
            numPoints = pointinfo.numPoints;
            int i = 0;
            do
            {
                if(i >= numPoints)
                {
                    xMid = pointinfo.xMid;
                    yMid = pointinfo.yMid;
                    pressureMid = pointinfo.pressureMid;
                    dx = pointinfo.dx;
                    dy = pointinfo.dy;
                    diameter = pointinfo.diameter;
                    diameterSq = pointinfo.diameterSq;
                    angle = pointinfo.angle;
                    isDown = pointinfo.isDown;
                    action = pointinfo.action;
                    isMultiTouch = pointinfo.isMultiTouch;
                    diameterIsCalculated = pointinfo.diameterIsCalculated;
                    diameterSqIsCalculated = pointinfo.diameterSqIsCalculated;
                    angleIsCalculated = pointinfo.angleIsCalculated;
                    eventTime = pointinfo.eventTime;
                    return;
                }
                xs[i] = pointinfo.xs[i];
                ys[i] = pointinfo.ys[i];
                pressures[i] = pointinfo.pressures[i];
                pointerIds[i] = pointinfo.pointerIds[i];
                i++;
            } while(true);
        }

        private int action;
        private float angle;
        private boolean angleIsCalculated;
        private float diameter;
        private boolean diameterIsCalculated;
        private float diameterSq;
        private boolean diameterSqIsCalculated;
        private float dx;
        private float dy;
        private long eventTime;
        private boolean isDown;
        private boolean isMultiTouch;
        private int numPoints;
        private int pointerIds[];
        private float pressureMid;
        private float pressures[];
        private float xMid;
        private float xs[];
        private float yMid;
        private float ys[];



        public PointInfo()
        {
            xs = new float[20];
            ys = new float[20];
            pressures = new float[20];
            pointerIds = new int[20];
        }
    }

    public static class PositionAndScale
    {

        public float getScale()
        {
            float f;
            if(!updateScale)
                f = 1.0F;
            else
                f = scale;
            return f;
        }

        public float getScaleX()
        {
            float f;
            if(!updateScaleXY)
                f = 1.0F;
            else
                f = scaleX;
            return f;
        }

        public float getScaleY()
        {
            float f;
            if(!updateScaleXY)
                f = 1.0F;
            else
                f = scaleY;
            return f;
        }

        public float getXOff()
        {
            return xOff;
        }

        public float getYOff()
        {
            return yOff;
        }

        protected void set(float f, float f1, float f2, float f3, float f4)
        {
            xOff = f;
            yOff = f1;
            float f5;
            float f6;
            float f7;
            if(f2 == 0.0F)
                f5 = 1.0F;
            else
                f5 = f2;
            scale = f5;
            if(f3 == 0.0F)
                f6 = 1.0F;
            else
                f6 = f3;
            scaleX = f6;
            if(f4 == 0.0F)
                f7 = 1.0F;
            else
                f7 = f4;
            scaleY = f7;
        }

        public void set(float f, float f1, boolean flag, float f2, boolean flag1, float f3, float f4, 
                boolean flag2)
        {
            xOff = f;
            yOff = f1;
            updateScale = flag;
            float f5;
            float f6;
            float f7;
            if(f2 == 0.0F)
                f5 = 1.0F;
            else
                f5 = f2;
            scale = f5;
            updateScaleXY = flag1;
            if(f3 == 0.0F)
                f6 = 1.0F;
            else
                f6 = f3;
            scaleX = f6;
            if(f4 == 0.0F)
                f7 = 1.0F;
            else
                f7 = f4;
            scaleY = f7;
            updateAngle = flag2;
        }

        private float scale;
        private float scaleX;
        private float scaleY;
        private boolean updateAngle;
        private boolean updateScale;
        private boolean updateScaleXY;
        private float xOff;
        private float yOff;









        public PositionAndScale()
        {
        }
    }


    public MultiTouchController(MultiTouchObjectCanvas multitouchobjectcanvas)
    {
        this(multitouchobjectcanvas, true);
    }

    public MultiTouchController(MultiTouchObjectCanvas multitouchobjectcanvas, boolean flag)
    {
        selectedObject = null;
        mCurrXform = new PositionAndScale();
        mMode = 0;
        mCurrPt = new PointInfo();
        mPrevPt = new PointInfo();
        handleSingleTouchEvents = flag;
        objectCanvas = multitouchobjectcanvas;
    }

    private void anchorAtThisPositionAndScale()
    {
        if(selectedObject != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        float f;
        objectCanvas.getPositionAndScale(selectedObject, mCurrXform);
        if(mCurrXform.updateScale)
            break; /* Loop/switch isn't completed */
        f = 1.0F;
_L4:
        float f1 = 1.0F / f;
        extractCurrPtInfo();
        startPosX = f1 * (mCurrPtX - mCurrXform.xOff);
        startPosY = f1 * (mCurrPtY - mCurrXform.yOff);
        startScaleOverPinchDiam = mCurrXform.scale / mCurrPtDiam;
        startScaleXOverPinchWidth = mCurrXform.scaleX / mCurrPtWidth;
        startScaleYOverPinchHeight = mCurrXform.scaleY / mCurrPtHeight;
        if(true) goto _L1; else goto _L3
_L3:
        if(mCurrXform.scale == 0.0F)
            f = 1.0F;
        else
            f = mCurrXform.scale;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void decodeTouchEvent(int i, float af[], float af1[], float af2[], int ai[], int j, boolean flag, 
            long l)
    {
        PointInfo pointinfo = mPrevPt;
        mPrevPt = mCurrPt;
        mCurrPt = pointinfo;
        mCurrPt.set(i, af, af1, af2, ai, j, flag, l);
        multiTouchController();
    }

    private void extractCurrPtInfo()
    {
        mCurrPtX = mCurrPt.getX();
        mCurrPtY = mCurrPt.getY();
        float f;
        float f1;
        float f2;
        float f3;
        if(!mCurrXform.updateScale)
            f = 0.0F;
        else
            f = mCurrPt.getMultiTouchDiameter();
        mCurrPtDiam = Math.max(21.3F, f);
        if(!mCurrXform.updateScaleXY)
            f1 = 0.0F;
        else
            f1 = mCurrPt.getMultiTouchWidth();
        mCurrPtWidth = Math.max(30F, f1);
        if(!mCurrXform.updateScaleXY)
            f2 = 0.0F;
        else
            f2 = mCurrPt.getMultiTouchHeight();
        mCurrPtHeight = Math.max(30F, f2);
        if(!mCurrXform.updateAngle)
            f3 = 0.0F;
        else
            f3 = mCurrPt.getMultiTouchAngle();
        mCurrPtAng = f3;
    }

    private void multiTouchController()
    {
        mMode;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 33
    //                   1 114
    //                   2 227;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        if(mCurrPt.isDown())
        {
            selectedObject = objectCanvas.getDraggableObjectAtPoint(mCurrPt);
            if(selectedObject != null)
            {
                mMode = 1;
                objectCanvas.selectObject(selectedObject, mCurrPt);
                anchorAtThisPositionAndScale();
                long l = mCurrPt.getEventTime();
                mSettleEndTime = l;
                mSettleStartTime = l;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(!mCurrPt.isDown())
        {
            mMode = 0;
            MultiTouchObjectCanvas multitouchobjectcanvas1 = objectCanvas;
            selectedObject = null;
            multitouchobjectcanvas1.selectObject(null, mCurrPt);
        } else
        if(mCurrPt.isMultiTouch())
        {
            mMode = 2;
            anchorAtThisPositionAndScale();
            mSettleStartTime = mCurrPt.getEventTime();
            mSettleEndTime = 20L + mSettleStartTime;
        } else
        if(mCurrPt.getEventTime() < mSettleEndTime)
            anchorAtThisPositionAndScale();
        else
            performDragOrPinch();
        continue; /* Loop/switch isn't completed */
_L4:
        if(!mCurrPt.isMultiTouch() || !mCurrPt.isDown())
        {
            if(!mCurrPt.isDown())
            {
                mMode = 0;
                MultiTouchObjectCanvas multitouchobjectcanvas = objectCanvas;
                selectedObject = null;
                multitouchobjectcanvas.selectObject(null, mCurrPt);
            } else
            {
                mMode = 1;
                anchorAtThisPositionAndScale();
                mSettleStartTime = mCurrPt.getEventTime();
                mSettleEndTime = 20L + mSettleStartTime;
            }
        } else
        if(Math.abs(mCurrPt.getX() - mPrevPt.getX()) > 30F || Math.abs(mCurrPt.getY() - mPrevPt.getY()) > 30F || 0.5F * Math.abs(mCurrPt.getMultiTouchWidth() - mPrevPt.getMultiTouchWidth()) > 40F || 0.5F * Math.abs(mCurrPt.getMultiTouchHeight() - mPrevPt.getMultiTouchHeight()) > 40F)
        {
            anchorAtThisPositionAndScale();
            mSettleStartTime = mCurrPt.getEventTime();
            mSettleEndTime = 20L + mSettleStartTime;
        } else
        if(mCurrPt.eventTime < mSettleEndTime)
            anchorAtThisPositionAndScale();
        else
            performDragOrPinch();
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void performDragOrPinch()
    {
        if(selectedObject != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        float f;
        if(mCurrXform.updateScale)
            break; /* Loop/switch isn't completed */
        f = 1.0F;
_L4:
        extractCurrPtInfo();
        float f1 = mCurrPtX - f * startPosX;
        float f2 = mCurrPtY - f * startPosY;
        float f3 = startScaleOverPinchDiam * mCurrPtDiam;
        float f4 = startScaleXOverPinchWidth * mCurrPtWidth;
        float f5 = startScaleYOverPinchHeight * mCurrPtHeight;
        mCurrXform.set(f1, f2, f3, f4, f5);
        if(objectCanvas.setPositionAndScale(selectedObject, mCurrXform, mCurrPt));
        if(true) goto _L1; else goto _L3
_L3:
        if(mCurrXform.scale == 0.0F)
            f = 1.0F;
        else
            f = mCurrXform.scale;
          goto _L4
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected boolean getHandleSingleTouchEvents()
    {
        return handleSingleTouchEvents;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!multiTouchSupported) goto _L2; else goto _L1
_L1:
        int i = ((Integer)m_getPointerCount.invoke(motionevent, new Object[0])).intValue();
_L14:
        if(mMode != 0 || handleSingleTouchEvents || i != 1) goto _L4; else goto _L3
_L3:
        boolean flag = false;
          goto _L5
_L4:
        int j;
        int k;
        int l;
        j = motionevent.getAction();
        k = motionevent.getHistorySize() / i;
        l = 0;
          goto _L6
_L15:
        boolean flag1;
        int i1;
        boolean flag2;
        long l2;
        if(!multiTouchSupported || i == 1)
        {
            float af[] = xVals;
            float f;
            float af1[];
            float f1;
            float af2[];
            float f2;
            float af3[];
            float af4[];
            float af5[];
            int ai[];
            if(flag1)
                f = motionevent.getHistoricalX(l);
            else
                f = motionevent.getX();
            af[0] = f;
            af1 = yVals;
            if(flag1)
                f1 = motionevent.getHistoricalY(l);
            else
                f1 = motionevent.getY();
            af1[0] = f1;
            af2 = pressureVals;
            if(flag1)
                f2 = motionevent.getHistoricalPressure(l);
            else
                f2 = motionevent.getPressure();
            af2[0] = f2;
        } else
        {
            int j1 = Math.min(i, 20);
            int k1 = 0;
            while(k1 < j1) 
            {
                Method method = m_getPointerId;
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(k1);
                int i2 = ((Integer)method.invoke(motionevent, aobj)).intValue();
                pointerIds[k1] = i2;
                float af6[] = xVals;
                Object obj;
                float af7[];
                Object obj1;
                float af8[];
                Object obj2;
                if(flag1)
                {
                    Method method6 = m_getHistoricalX;
                    Object aobj6[] = new Object[2];
                    aobj6[0] = Integer.valueOf(k1);
                    aobj6[1] = Integer.valueOf(l);
                    obj = method6.invoke(motionevent, aobj6);
                } else
                {
                    Method method1 = m_getX;
                    Object aobj1[] = new Object[1];
                    aobj1[0] = Integer.valueOf(k1);
                    obj = method1.invoke(motionevent, aobj1);
                }
                af6[k1] = ((Float)obj).floatValue();
                af7 = yVals;
                if(flag1)
                {
                    Method method5 = m_getHistoricalY;
                    Object aobj5[] = new Object[2];
                    aobj5[0] = Integer.valueOf(k1);
                    aobj5[1] = Integer.valueOf(l);
                    obj1 = method5.invoke(motionevent, aobj5);
                } else
                {
                    Method method2 = m_getY;
                    Object aobj2[] = new Object[1];
                    aobj2[0] = Integer.valueOf(k1);
                    obj1 = method2.invoke(motionevent, aobj2);
                }
                af7[k1] = ((Float)obj1).floatValue();
                af8 = pressureVals;
                if(flag1)
                {
                    Method method4 = m_getHistoricalPressure;
                    Object aobj4[] = new Object[2];
                    aobj4[0] = Integer.valueOf(k1);
                    aobj4[1] = Integer.valueOf(l);
                    obj2 = method4.invoke(motionevent, aobj4);
                } else
                {
                    Method method3 = m_getPressure;
                    Object aobj3[] = new Object[1];
                    aobj3[0] = Integer.valueOf(k1);
                    obj2 = method3.invoke(motionevent, aobj3);
                }
                af8[k1] = ((Float)obj2).floatValue();
                k1++;
            }
        }
        af3 = xVals;
        af4 = yVals;
        af5 = pressureVals;
        ai = pointerIds;
        if(!flag1) goto _L8; else goto _L7
_L7:
        i1 = 2;
          goto _L9
_L12:
        if(!flag1) goto _L11; else goto _L10
_L10:
        l2 = motionevent.getHistoricalEventTime(l);
_L13:
        decodeTouchEvent(i, af3, af4, af5, ai, i1, flag2, l2);
        l++;
          goto _L6
_L17:
        if(j == 1 || (j & (1 << ACTION_POINTER_INDEX_SHIFT) - 1) == ACTION_POINTER_UP || j == 3)
            break MISSING_BLOCK_LABEL_727;
        flag2 = true;
          goto _L12
_L11:
        long l1 = motionevent.getEventTime();
        l2 = l1;
          goto _L13
        Exception exception;
        exception;
        Log.e("MultiTouchController", "onTouchEvent() failed", exception);
        flag = false;
_L5:
        return flag;
_L2:
        i = 1;
          goto _L14
_L6:
label0:
        {
            if(l <= k)
                break label0;
            flag = true;
        }
          goto _L5
        if(l < k)
            flag1 = true;
        else
            flag1 = false;
          goto _L15
_L9:
        if(!flag1) goto _L17; else goto _L16
_L16:
        flag2 = true;
          goto _L12
_L8:
        i1 = j;
          goto _L9
        flag2 = false;
          goto _L12
    }

    protected void setHandleSingleTouchEvents(boolean flag)
    {
        handleSingleTouchEvents = flag;
    }

    private static int ACTION_POINTER_INDEX_SHIFT = 0;
    private static int ACTION_POINTER_UP = 0;
    public static final boolean DEBUG = false;
    private static final long EVENT_SETTLE_TIME_INTERVAL = 20L;
    private static final float MAX_MULTITOUCH_DIM_JUMP_SIZE = 40F;
    private static final float MAX_MULTITOUCH_POS_JUMP_SIZE = 30F;
    public static final int MAX_TOUCH_POINTS = 20;
    private static final float MIN_MULTITOUCH_SEPARATION = 30F;
    private static final int MODE_DRAG = 1;
    private static final int MODE_NOTHING = 0;
    private static final int MODE_PINCH = 2;
    private static Method m_getHistoricalPressure;
    private static Method m_getHistoricalX;
    private static Method m_getHistoricalY;
    private static Method m_getPointerCount;
    private static Method m_getPointerId;
    private static Method m_getPressure;
    private static Method m_getX;
    private static Method m_getY;
    public static final boolean multiTouchSupported;
    private static final int pointerIds[];
    private static final float pressureVals[];
    private static final float xVals[];
    private static final float yVals[];
    private boolean handleSingleTouchEvents;
    private PointInfo mCurrPt;
    private float mCurrPtAng;
    private float mCurrPtDiam;
    private float mCurrPtHeight;
    private float mCurrPtWidth;
    private float mCurrPtX;
    private float mCurrPtY;
    private PositionAndScale mCurrXform;
    private int mMode;
    private PointInfo mPrevPt;
    private long mSettleEndTime;
    private long mSettleStartTime;
    MultiTouchObjectCanvas objectCanvas;
    private Object selectedObject;
    private float startPosX;
    private float startPosY;
    private float startScaleOverPinchDiam;
    private float startScaleXOverPinchWidth;
    private float startScaleYOverPinchHeight;

    static 
    {
        boolean flag;
        ACTION_POINTER_UP = 6;
        ACTION_POINTER_INDEX_SHIFT = 8;
        flag = false;
        m_getPointerCount = android/view/MotionEvent.getMethod("getPointerCount", new Class[0]);
        Class aclass[] = new Class[1];
        aclass[0] = Integer.TYPE;
        m_getPointerId = android/view/MotionEvent.getMethod("getPointerId", aclass);
        Class aclass1[] = new Class[1];
        aclass1[0] = Integer.TYPE;
        m_getPressure = android/view/MotionEvent.getMethod("getPressure", aclass1);
        Class aclass2[] = new Class[2];
        aclass2[0] = Integer.TYPE;
        aclass2[1] = Integer.TYPE;
        m_getHistoricalX = android/view/MotionEvent.getMethod("getHistoricalX", aclass2);
        Class aclass3[] = new Class[2];
        aclass3[0] = Integer.TYPE;
        aclass3[1] = Integer.TYPE;
        m_getHistoricalY = android/view/MotionEvent.getMethod("getHistoricalY", aclass3);
        Class aclass4[] = new Class[2];
        aclass4[0] = Integer.TYPE;
        aclass4[1] = Integer.TYPE;
        m_getHistoricalPressure = android/view/MotionEvent.getMethod("getHistoricalPressure", aclass4);
        Class aclass5[] = new Class[1];
        aclass5[0] = Integer.TYPE;
        m_getX = android/view/MotionEvent.getMethod("getX", aclass5);
        Class aclass6[] = new Class[1];
        aclass6[0] = Integer.TYPE;
        m_getY = android/view/MotionEvent.getMethod("getY", aclass6);
        flag = true;
_L1:
        multiTouchSupported = flag;
        Exception exception;
        if(multiTouchSupported)
            try
            {
                ACTION_POINTER_UP = android/view/MotionEvent.getField("ACTION_POINTER_UP").getInt(null);
                ACTION_POINTER_INDEX_SHIFT = android/view/MotionEvent.getField("ACTION_POINTER_INDEX_SHIFT").getInt(null);
            }
            catch(Exception exception1) { }
        xVals = new float[20];
        yVals = new float[20];
        pressureVals = new float[20];
        pointerIds = new int[20];
        return;
        exception;
        Log.e("MultiTouchController", "static initializer failed", exception);
          goto _L1
    }
}
