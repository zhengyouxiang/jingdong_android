// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

public class FlingGallery extends FrameLayout
{
    private class FlingGalleryAnimation extends Animation
    {

        protected void applyTransformation(float f, Transformation transformation)
        {
            if(f > 1.0F)
                f = 1.0F;
            int i = mInitialOffset + (int)(f * (float)mTargetDistance);
            int j = 0;
            do
            {
                if(j >= 3)
                    return;
                if(mTargetDistance > 0 && j != getNextViewNumber(mRelativeViewNumber) || mTargetDistance < 0 && j != getPrevViewNumber(mRelativeViewNumber))
                    mViews[j].setOffset(i, 0, mRelativeViewNumber);
                j++;
            } while(true);
        }

        public boolean getTransformation(long l, Transformation transformation)
        {
            boolean flag;
            if(!super.getTransformation(l, transformation))
            {
                mViews[0].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                mViews[1].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                mViews[2].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                mIsAnimationInProgres = false;
                flag = false;
            } else
            if(mIsTouched || mIsDragging)
                flag = false;
            else
                flag = true;
            return flag;
        }

        public void prepareAnimation(int i)
        {
            if(mRelativeViewNumber != i)
            {
                if(mIsAnimationInProgres)
                {
                    byte byte0;
                    byte byte1;
                    if(i == getPrevViewNumber(mRelativeViewNumber))
                        byte0 = 1;
                    else
                        byte0 = -1;
                    if(mTargetDistance < 0)
                        byte1 = 1;
                    else
                        byte1 = -1;
                    if(byte1 == byte0)
                    {
                        mViews[0].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                        mViews[1].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                        mViews[2].setOffset(mTargetOffset, 0, mRelativeViewNumber);
                    }
                }
                mRelativeViewNumber = i;
            }
            mInitialOffset = mViews[mRelativeViewNumber].getCurrentOffset();
            mTargetOffset = getViewOffset(mRelativeViewNumber, mRelativeViewNumber);
            mTargetDistance = mTargetOffset - mInitialOffset;
            setDuration(mAnimationDuration);
            setInterpolator(mDecelerateInterpolater);
            mIsAnimationInProgres = true;
        }

        private int mInitialOffset;
        private boolean mIsAnimationInProgres;
        private int mRelativeViewNumber;
        private int mTargetDistance;
        private int mTargetOffset;
        final FlingGallery this$0;

        public FlingGalleryAnimation()
        {
            this$0 = FlingGallery.this;
            super();
            mIsAnimationInProgres = false;
            mRelativeViewNumber = 0;
            mInitialOffset = 0;
            mTargetOffset = 0;
            mTargetDistance = 0;
        }
    }

    private class FlingGalleryView
    {

        public int getCurrentOffset()
        {
            return mInternalLayout.getScrollX();
        }

        public void recycleView(int i)
        {
            if(mExternalView != null)
                mInternalLayout.removeView(mExternalView);
            if(mAdapter != null)
                if(i >= getFirstPosition() && i <= getLastPosition())
                    mExternalView = mAdapter.getView(i, mExternalView, mInternalLayout);
                else
                    mExternalView = mInvalidLayout;
            if(mExternalView != null)
                mInternalLayout.addView(mExternalView, new android.widget.LinearLayout.LayoutParams(-1, -1));
        }

        public void requestFocus()
        {
            mInternalLayout.requestFocus();
        }

        public void setOffset(int i, int j, int k)
        {
            mInternalLayout.scrollTo(i + getViewOffset(mViewNumber, k), j);
        }

        private View mExternalView;
        private LinearLayout mInternalLayout;
        private FrameLayout mInvalidLayout;
        private FrameLayout mParentLayout;
        private int mViewNumber;
        final FlingGallery this$0;

        public FlingGalleryView(int i, FrameLayout framelayout)
        {
            this$0 = FlingGallery.this;
            super();
            mInvalidLayout = null;
            mInternalLayout = null;
            mExternalView = null;
            mViewNumber = i;
            mParentLayout = framelayout;
            mInvalidLayout = new FrameLayout(mContext);
            mInvalidLayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
            mInternalLayout = new LinearLayout(mContext);
            mInternalLayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
            mParentLayout.addView(mInternalLayout);
        }
    }

    private class FlingGestureDetector
        implements android.view.GestureDetector.OnGestureListener
    {

        public boolean onDown(MotionEvent motionevent)
        {
            mIsTouched = true;
            mFlingDirection = 0;
            return true;
        }

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if(Math.abs(motionevent.getY() - motionevent1.getY()) <= 250F)
            {
                if(motionevent1.getX() - motionevent.getX() > 120F && Math.abs(f) > 400F)
                    movePrevious();
                if(motionevent.getX() - motionevent1.getX() > 120F && Math.abs(f) > 400F)
                    moveNext();
            }
            return false;
        }

        public void onLongPress(MotionEvent motionevent)
        {
            mFlingDirection = 0;
            processGesture();
        }

        public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
            if(motionevent1.getAction() == 2)
            {
                if(!mIsDragging)
                {
                    mIsTouched = true;
                    mIsDragging = true;
                    mFlingDirection = 0;
                    mScrollTimestamp = System.currentTimeMillis();
                    mCurrentOffset = (float)mViews[mCurrentViewNumber].getCurrentOffset();
                }
                float f2 = ((float)mGalleryWidth / ((float)mAnimationDuration / 1000F)) * ((float)(System.currentTimeMillis() - mScrollTimestamp) / 1000F);
                float f3 = motionevent.getX() - motionevent1.getX();
                if(f3 < -1F * f2)
                    f3 = f2 * -1F;
                if(f3 > f2)
                    f3 = f2;
                int i = Math.round(f3 + mCurrentOffset);
                if(i >= mGalleryWidth)
                    i = mGalleryWidth;
                if(i <= -1 * mGalleryWidth)
                    i = -1 * mGalleryWidth;
                mViews[0].setOffset(i, 0, mCurrentViewNumber);
                mViews[1].setOffset(i, 0, mCurrentViewNumber);
                mViews[2].setOffset(i, 0, mCurrentViewNumber);
            }
            return false;
        }

        public void onShowPress(MotionEvent motionevent)
        {
        }

        public boolean onSingleTapUp(MotionEvent motionevent)
        {
            mFlingDirection = 0;
            return false;
        }

        final FlingGallery this$0;

        private FlingGestureDetector()
        {
            this$0 = FlingGallery.this;
            super();
        }

        FlingGestureDetector(FlingGestureDetector flinggesturedetector)
        {
            this();
        }
    }


    public FlingGallery(Context context)
    {
        super(context);
        swipe_min_distance = 120;
        swipe_max_off_path = 250;
        swipe_threshold_veloicty = 400;
        mViewPaddingWidth = 0;
        mAnimationDuration = 250;
        mSnapBorderRatio = 0.5F;
        mIsGalleryCircular = true;
        mGalleryWidth = 0;
        mIsTouched = false;
        mIsDragging = false;
        mCurrentOffset = 0.0F;
        mScrollTimestamp = 0L;
        mFlingDirection = 0;
        mCurrentPosition = 0;
        mCurrentViewNumber = 0;
        init(context);
    }

    public FlingGallery(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        swipe_min_distance = 120;
        swipe_max_off_path = 250;
        swipe_threshold_veloicty = 400;
        mViewPaddingWidth = 0;
        mAnimationDuration = 250;
        mSnapBorderRatio = 0.5F;
        mIsGalleryCircular = true;
        mGalleryWidth = 0;
        mIsTouched = false;
        mIsDragging = false;
        mCurrentOffset = 0.0F;
        mScrollTimestamp = 0L;
        mFlingDirection = 0;
        mCurrentPosition = 0;
        mCurrentViewNumber = 0;
        init(context);
    }

    public FlingGallery(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        swipe_min_distance = 120;
        swipe_max_off_path = 250;
        swipe_threshold_veloicty = 400;
        mViewPaddingWidth = 0;
        mAnimationDuration = 250;
        mSnapBorderRatio = 0.5F;
        mIsGalleryCircular = true;
        mGalleryWidth = 0;
        mIsTouched = false;
        mIsDragging = false;
        mCurrentOffset = 0.0F;
        mScrollTimestamp = 0L;
        mFlingDirection = 0;
        mCurrentPosition = 0;
        mCurrentViewNumber = 0;
        init(context);
    }

    private int getNextPosition(int i)
    {
        int j = i + 1;
        if(j > getLastPosition())
        {
            j = 1 + getLastPosition();
            if(mIsGalleryCircular)
                j = getFirstPosition();
        }
        return j;
    }

    private int getNextViewNumber(int i)
    {
        int j;
        if(i == 2)
            j = 0;
        else
            j = i + 1;
        return j;
    }

    private int getPrevPosition(int i)
    {
        int j = i - 1;
        if(j < getFirstPosition())
        {
            j = getFirstPosition() - 1;
            if(mIsGalleryCircular)
                j = getLastPosition();
        }
        return j;
    }

    private int getPrevViewNumber(int i)
    {
        int j;
        if(i == 0)
            j = 2;
        else
            j = i - 1;
        return j;
    }

    private int getViewOffset(int i, int j)
    {
        int k = mGalleryWidth + mViewPaddingWidth;
        int l;
        if(i == getPrevViewNumber(j))
            l = k;
        else
        if(i == getNextViewNumber(j))
            l = k * -1;
        else
            l = 0;
        return l;
    }

    private void init(Context context)
    {
        mContext = context;
        mAdapter = null;
        mViews = new FlingGalleryView[3];
        mViews[0] = new FlingGalleryView(0, this);
        mViews[1] = new FlingGalleryView(1, this);
        mViews[2] = new FlingGalleryView(2, this);
        mAnimation = new FlingGalleryAnimation();
        mGestureDetector = new GestureDetector(new FlingGestureDetector(null));
        mGestureDetector.setIsLongpressEnabled(true);
        mDecelerateInterpolater = AnimationUtils.loadInterpolator(mContext, 0x10a0006);
        setStaticTransformationsEnabled(true);
    }

    public int getFirstPosition()
    {
        return 0;
    }

    public int getGalleryCount()
    {
        int i;
        if(mAdapter == null)
            i = 0;
        else
            i = mAdapter.getCount();
        return i;
    }

    public int getLastPosition()
    {
        int i;
        if(getGalleryCount() == 0)
            i = 0;
        else
            i = getGalleryCount() - 1;
        return i;
    }

    void moveNext()
    {
        mFlingDirection = -1;
        processGesture();
    }

    void movePrevious()
    {
        mFlingDirection = 1;
        processGesture();
    }

    public boolean onGalleryTouchEvent(MotionEvent motionevent)
    {
        boolean flag = mGestureDetector.onTouchEvent(motionevent);
        if(motionevent.getAction() == 1 && (mIsTouched || mIsDragging))
        {
            processScrollSnap();
            processGesture();
        }
        return flag;
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        i;
        JVM INSTR tableswitch 21 22: default 24
    //                   21 33
    //                   22 40;
           goto _L1 _L2 _L3
_L1:
        flag = super.onKeyDown(i, keyevent);
_L5:
        return flag;
_L2:
        movePrevious();
        continue; /* Loop/switch isn't completed */
_L3:
        moveNext();
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mGalleryWidth = k - i;
        if(flag)
        {
            mViews[0].setOffset(0, 0, mCurrentViewNumber);
            mViews[1].setOffset(0, 0, mCurrentViewNumber);
            mViews[2].setOffset(0, 0, mCurrentViewNumber);
        }
    }

    void processGesture()
    {
        int i = mCurrentViewNumber;
        int j = 0;
        int k = 0;
        mIsTouched = false;
        mIsDragging = false;
        if(mFlingDirection > 0 && (mCurrentPosition > getFirstPosition() || mIsGalleryCircular))
        {
            i = getPrevViewNumber(mCurrentViewNumber);
            mCurrentPosition = getPrevPosition(mCurrentPosition);
            j = getNextViewNumber(mCurrentViewNumber);
            k = getPrevPosition(mCurrentPosition);
        }
        if(mFlingDirection < 0 && (mCurrentPosition < getLastPosition() || mIsGalleryCircular))
        {
            i = getNextViewNumber(mCurrentViewNumber);
            mCurrentPosition = getNextPosition(mCurrentPosition);
            j = getPrevViewNumber(mCurrentViewNumber);
            k = getNextPosition(mCurrentPosition);
        }
        if(i != mCurrentViewNumber)
        {
            mCurrentViewNumber = i;
            mViews[j].recycleView(k);
        }
        mViews[mCurrentViewNumber].requestFocus();
        mAnimation.prepareAnimation(mCurrentViewNumber);
        startAnimation(mAnimation);
        mFlingDirection = 0;
    }

    void processScrollSnap()
    {
        float f = (float)mGalleryWidth * mSnapBorderRatio;
        int i = mGalleryWidth - (int)f;
        int j = mViews[mCurrentViewNumber].getCurrentOffset();
        if(j <= i * -1)
            mFlingDirection = 1;
        if(j >= i)
            mFlingDirection = -1;
    }

    public void setAdapter(Adapter adapter)
    {
        mAdapter = adapter;
        mCurrentPosition = 0;
        mCurrentViewNumber = 0;
        mViews[0].recycleView(mCurrentPosition);
        mViews[1].recycleView(getNextPosition(mCurrentPosition));
        mViews[2].recycleView(getPrevPosition(mCurrentPosition));
        mViews[0].setOffset(0, 0, mCurrentViewNumber);
        mViews[1].setOffset(0, 0, mCurrentViewNumber);
        mViews[2].setOffset(0, 0, mCurrentViewNumber);
    }

    public void setAnimationDuration(int i)
    {
        mAnimationDuration = i;
    }

    public void setIsGalleryCircular(boolean flag)
    {
        if(mIsGalleryCircular != flag)
        {
            mIsGalleryCircular = flag;
            if(mCurrentPosition == getFirstPosition())
                mViews[getPrevViewNumber(mCurrentViewNumber)].recycleView(getPrevPosition(mCurrentPosition));
            if(mCurrentPosition == getLastPosition())
                mViews[getNextViewNumber(mCurrentViewNumber)].recycleView(getNextPosition(mCurrentPosition));
        }
    }

    public void setPaddingWidth(int i)
    {
        mViewPaddingWidth = i;
    }

    public void setSnapBorderRatio(float f)
    {
        mSnapBorderRatio = f;
    }

    private Adapter mAdapter;
    private FlingGalleryAnimation mAnimation;
    private int mAnimationDuration;
    private Context mContext;
    private float mCurrentOffset;
    private int mCurrentPosition;
    private int mCurrentViewNumber;
    private Interpolator mDecelerateInterpolater;
    private int mFlingDirection;
    private int mGalleryWidth;
    private GestureDetector mGestureDetector;
    private boolean mIsDragging;
    private boolean mIsGalleryCircular;
    private boolean mIsTouched;
    private long mScrollTimestamp;
    private float mSnapBorderRatio;
    private int mViewPaddingWidth;
    private FlingGalleryView mViews[];
    private final int swipe_max_off_path;
    private final int swipe_min_distance;
    private final int swipe_threshold_veloicty;



















}
