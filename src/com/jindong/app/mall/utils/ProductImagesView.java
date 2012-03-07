// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, DPIUtil, MyActivity

public class ProductImagesView extends AdapterView
    implements android.view.GestureDetector.OnGestureListener
{
    private class AnimationDelegate
        implements Runnable
    {

        /**
         * @deprecated Method isFit is deprecated
         */

        private void isFit()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = fit;
            if(!flag)
                break MISSING_BLOCK_LABEL_41;
            if(Log.D)
                Log.d("Temp", "product image wait start -->> ");
            wait();
            if(Log.D)
                Log.d("Temp", "product image wait end -->> ");
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            InterruptedException interruptedexception;
            interruptedexception;
            isFit();
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        /**
         * @deprecated Method adjust is deprecated
         */

        public void adjust(int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(Log.D)
                Log.d("Temp", (new StringBuilder("adjust() -->> ")).append(i).toString());
              goto _L1
_L4:
            if(Log.D)
                Log.d("Temp", (new StringBuilder("adjust() -->> area : ")).append(area).toString());
            if(Log.D)
                Log.d("Temp", (new StringBuilder("adjust() -->> targetOffset before : ")).append(targetOffset).toString());
            if(childCount != pageSize) goto _L3; else goto _L2
_L2:
            targetOffset = 0;
_L5:
            if(Log.D)
                Log.d("Temp", (new StringBuilder("adjust() -->> targetOffset after : ")).append(targetOffset).toString());
            notify();
            this;
            JVM INSTR monitorexit ;
            return;
_L8:
            ProductImagesView productimagesview1 = ProductImagesView.this;
            productimagesview1.area = productimagesview1.area - pageSize;
            if(area <= -lastPageArea)
                area = -lastPageArea;
              goto _L4
            Exception exception;
            exception;
            throw exception;
_L7:
            ProductImagesView productimagesview = ProductImagesView.this;
            productimagesview.area = productimagesview.area + pageSize;
            if(area >= 0)
                area = 0;
              goto _L4
_L6:
            area = Math.round((float)offset / (float)unitWidth);
              goto _L4
_L3:
            targetOffset = area * unitWidth;
              goto _L5
_L1:
            i;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 270
        //                       1 228
        //                       2 163;
               goto _L4 _L6 _L7 _L8
        }

        public void run()
        {
            int i = 0;
            do
            {
                isFit();
                int j = (int)((float)i + 0.4F * (float)(targetOffset - offset));
                ProductImagesView productimagesview = ProductImagesView.this;
                int k = productimagesview.offset;
                i = (int)(0.3F * (float)j);
                productimagesview.offset = k + i;
                if(Math.abs(targetOffset - offset) < 9)
                {
                    offset = targetOffset;
                    i = 0;
                }
                boolean flag;
                if(offset == targetOffset)
                    flag = true;
                else
                    flag = false;
                fit = flag;
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("offset -->> ")).append(offset).toString());
                ((MyActivity)getContext()).post(new Runnable() {

                    public void run()
                    {
                        requestLayout();
                    }

                    final AnimationDelegate this$1;

                
                {
                    this$1 = AnimationDelegate.this;
                    super();
                }
                }
);
                try
                {
                    Thread.sleep(30L);
                }
                catch(InterruptedException interruptedexception) { }
            } while(true);
        }

        private static final int LEFT = 1;
        private static final int NONE = 0;
        private static final int RIGHT = 2;
        private boolean fit;
        final ProductImagesView this$0;


        private AnimationDelegate()
        {
            this$0 = ProductImagesView.this;
            super();
            fit = true;
        }

        AnimationDelegate(AnimationDelegate animationdelegate)
        {
            this();
        }
    }

    public static class Border
    {

        public int left;
        public int right;

        public Border()
        {
        }
    }

    public static interface BorderListener
    {

        public abstract void onChange(Border border1);
    }


    public ProductImagesView(Context context)
    {
        super(context);
        area = 0;
        border = new Border();
        animationDelegate = new AnimationDelegate(null);
        initProductImagesView();
    }

    public ProductImagesView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        area = 0;
        border = new Border();
        animationDelegate = new AnimationDelegate(null);
        initProductImagesView();
    }

    public ProductImagesView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        area = 0;
        border = new Border();
        animationDelegate = new AnimationDelegate(null);
        initProductImagesView();
    }

    private void borderChange()
    {
        if(borderListener != null)
            borderListener.onChange(border);
    }

    private void initProductImagesView()
    {
        mGestureDetector = new GestureDetector(getContext(), this);
        mGestureDetector.setIsLongpressEnabled(true);
    }

    private void measureChild(View view)
    {
        if(!alreadyMeasureChild)
        {
            if(Log.D)
                Log.d("Temp", "measureChild() -->> ");
            if(minPaddingLeft == 0)
                minPaddingLeft = getPaddingLeft();
            if(minPaddingRight == 0)
                minPaddingRight = getPaddingRight();
            android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
            childWidthWithPadding = layoutparams.width + view.getPaddingLeft() + view.getPaddingRight();
            childHeightWithPadding = layoutparams.height + view.getPaddingTop() + view.getPaddingBottom();
            int i = getMeasuredWidth() - minPaddingLeft - minPaddingRight;
            pageSize = i / (DPIUtil.dip2px(10F) + childWidthWithPadding);
            if(pageSize > childCount)
                pageSize = childCount;
            int j = i - pageSize * childWidthWithPadding;
            int k;
            int l;
            if(pageSize - 1 == 0)
            {
                k = 0;
                setPadding(minPaddingLeft + j / 2, getPaddingTop(), minPaddingRight + j / 2, getPaddingBottom());
            } else
            {
                k = j / (pageSize - 1);
            }
            l = minPaddingLeft + minPaddingRight;
            if(k > l)
            {
                k = (l + j) / (1 + pageSize);
                setPadding(k, getPaddingTop(), k, getPaddingBottom());
            }
            unitWidth = k + childWidthWithPadding;
            lastPageArea = childCount - pageSize;
            if(Log.D)
                Log.d("Temp", (new StringBuilder("area -->> ")).append(area).toString());
            if(Log.D)
                Log.d("Temp", (new StringBuilder("lastPageArea -->> ")).append(lastPageArea).toString());
            if(area <= -lastPageArea)
            {
                area = -lastPageArea;
                animationDelegate.adjust(-1);
            }
            alreadyMeasureChild = true;
        }
    }

    private boolean performItemLongClick(View view, int i, long l)
    {
        boolean flag = false;
        android.widget.AdapterView.OnItemLongClickListener onitemlongclicklistener = getOnItemLongClickListener();
        if(onitemlongclicklistener != null)
            flag = onitemlongclicklistener.onItemLongClick(this, view, i, l);
        if(flag)
            performHapticFeedback(0);
        return flag;
    }

    public volatile Adapter getAdapter()
    {
        return getAdapter();
    }

    public ListAdapter getAdapter()
    {
        return mAdapter;
    }

    public View getSelectedView()
    {
        return null;
    }

    void onCancel()
    {
        onUp();
    }

    public boolean onDown(MotionEvent motionevent)
    {
        flingFlag = 0;
        if(Log.D)
            Log.d("Temp", "onDown -->> ");
        return true;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if(f > 300F)
            flingFlag = 1;
        else
        if(f < -300F)
            flingFlag = 2;
        else
            flingFlag = 0;
        return true;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1;
        if(Log.D)
            Log.d("Temp", "onLayout() -->> ");
        childCount = getChildCount();
        i1 = 0;
_L2:
        View view;
        if(i1 >= childCount)
            return;
        view = getChildAt(i1);
        measureChild(view);
        if(childCount != pageSize)
            break; /* Loop/switch isn't completed */
        border.left = 0;
        border.right = 0;
        borderChange();
_L3:
        int j1 = getPaddingLeft() + offset + i1 * unitWidth;
        int k1 = getPaddingTop();
        view.layout(j1, k1, j1 + childWidthWithPadding, k1 + childHeightWithPadding);
        i1++;
        if(true) goto _L2; else goto _L1
_L1:
        if(area >= 0)
            border.left = 0;
        else
            border.left = 1;
        if(area <= -lastPageArea)
            border.right = 0;
        else
            border.right = 1;
        borderChange();
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    public void onLongPress(MotionEvent motionevent)
    {
        int i;
        if(Log.D)
            Log.d("Temp", "onLongPress -->> ");
        i = -Math.round(((float)offset - motionevent.getX()) / (float)unitWidth - 0.5F) - 1;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("index -->> ")).append(i).toString());
        if(i <= getChildCount() - 1) goto _L2; else goto _L1
_L1:
        i = getChildCount() - 1;
_L4:
        View view = getChildAt(i);
        performItemLongClick(view, i, view.getId());
        return;
_L2:
        if(i < 0)
            i = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), j) + getPaddingTop() + getPaddingBottom());
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        if(childCount != pageSize) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        int i;
        getParent().requestDisallowInterceptTouchEvent(true);
        i = offset + Math.round(-f);
        if(i <= 0)
            break; /* Loop/switch isn't completed */
        offset = 0;
_L5:
        requestLayout();
        flingFlag = 0;
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        if(i < -1 * (lastPageArea * unitWidth))
            offset = -1 * (lastPageArea * unitWidth);
        else
            offset = i;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public void onShowPress(MotionEvent motionevent)
    {
        if(Log.D)
            Log.d("Temp", "onShowPress -->> ");
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        int i;
        if(Log.D)
            Log.d("Temp", "onSingleTapUp -->> ");
        i = -Math.round(((float)offset - motionevent.getX()) / (float)unitWidth - 0.5F) - 1;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("index -->> ")).append(i).toString());
        if(i <= getChildCount() - 1) goto _L2; else goto _L1
_L1:
        i = getChildCount() - 1;
_L4:
        View view = getChildAt(i);
        performItemClick(view, i, view.getId());
        return false;
_L2:
        if(i < 0)
            i = 0;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mAdapter != null) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L4:
        return flag1;
_L2:
        boolean flag;
        int i;
        if(thread == null)
        {
            thread = new Thread(animationDelegate);
            thread.start();
        }
        flag = mGestureDetector.onTouchEvent(motionevent);
        i = motionevent.getAction();
        if(i != 1)
            break; /* Loop/switch isn't completed */
        onUp();
_L6:
        flag1 = flag;
        if(true) goto _L4; else goto _L3
_L3:
        if(i != 3) goto _L6; else goto _L5
_L5:
        onCancel();
          goto _L6
    }

    void onUp()
    {
        animationDelegate.adjust(flingFlag);
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((ListAdapter)adapter);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(Log.D)
            Log.d("Temp", "setAdapter() -->> ");
        alreadyMeasureChild = false;
        mAdapter = listadapter;
        detachAllViewsFromParent();
        mAdapter.registerDataSetObserver(dataSetObserver);
        int i = 0;
        do
        {
            if(i >= mAdapter.getCount())
                return;
            View view = mAdapter.getView(i, null, this);
            addViewInLayout(view, i, view.getLayoutParams());
            i++;
        } while(true);
    }

    public void setOnBorderListener(BorderListener borderlistener)
    {
        borderListener = borderlistener;
    }

    public void setSelection(int i)
    {
    }

    private boolean alreadyMeasureChild;
    private AnimationDelegate animationDelegate;
    private int area;
    private Border border;
    private BorderListener borderListener;
    private int childCount;
    private int childHeightWithPadding;
    private int childWidthWithPadding;
    private DataSetObserver dataSetObserver = new DataSetObserver() {

        public void onChanged()
        {
            int i = 0;
            do
            {
                if(i >= mAdapter.getCount())
                    return;
                mAdapter.getView(i, getChildAt(i), ProductImagesView.this);
                i++;
            } while(true);
        }

        final ProductImagesView this$0;

            
            {
                this$0 = ProductImagesView.this;
                super();
            }
    }
;
    private int flingFlag;
    private int lastPageArea;
    private ListAdapter mAdapter;
    private GestureDetector mGestureDetector;
    private int minPaddingLeft;
    private int minPaddingRight;
    private int offset;
    private int pageSize;
    private int targetOffset;
    private Thread thread;
    private int unitWidth;











}
