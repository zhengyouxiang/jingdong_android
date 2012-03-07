// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log

public class NoTouchImageView extends ImageView
{

    public NoTouchImageView(Context context)
    {
        super(context);
        standardMatrix = new Matrix();
        referenceMatrix = new Matrix();
        currentMatrix = new Matrix();
        referenceDistance = 1.0F;
        referencePoint = new PointF();
        midPoint = new PointF();
        mode = 0;
        globalRect = new Rect();
        referenceImageCenterPoint = new PointF();
        imageCenterPoint = new PointF();
        init();
    }

    public NoTouchImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        standardMatrix = new Matrix();
        referenceMatrix = new Matrix();
        currentMatrix = new Matrix();
        referenceDistance = 1.0F;
        referencePoint = new PointF();
        midPoint = new PointF();
        mode = 0;
        globalRect = new Rect();
        referenceImageCenterPoint = new PointF();
        imageCenterPoint = new PointF();
        init();
    }

    public NoTouchImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        standardMatrix = new Matrix();
        referenceMatrix = new Matrix();
        currentMatrix = new Matrix();
        referenceDistance = 1.0F;
        referencePoint = new PointF();
        midPoint = new PointF();
        mode = 0;
        globalRect = new Rect();
        referenceImageCenterPoint = new PointF();
        imageCenterPoint = new PointF();
        init();
    }

    private void correctZoom()
    {
        if(currentScale <= standardScale)
        {
            currentScale = standardScale;
            imageCenterPoint.set(midPoint);
            currentMatrix.set(standardMatrix);
        }
        if(currentScale >= 4F)
        {
            currentScale = 4F;
            Matrix matrix = new Matrix();
            matrix.set(standardMatrix);
            matrix.postScale(4F, 4F, midPoint.x, midPoint.y);
            currentMatrix.set(matrix);
        }
        setImageMatrix(currentMatrix);
    }

    private float getCurHeight()
    {
        return (float)srcHeight * currentScale;
    }

    private float getCurWidth()
    {
        return (float)srcWidth * currentScale;
    }

    private void init()
    {
    }

    private void zoom(float f)
    {
        currentMatrix.postScale(f, f, midPoint.x, midPoint.y);
        currentScale = f * currentScale;
        if(Log.D)
            Log.d("Temp", (new StringBuilder("(referenceImageCenterPoint.x - midPoint.x) * scale -->> ")).append(f * (referenceImageCenterPoint.x - midPoint.x)).toString());
        float f1 = referenceImageCenterPoint.x - midPoint.x;
        float f2 = referenceImageCenterPoint.y - midPoint.y;
        if(f1 < 0.0F)
            imageCenterPoint.x = referenceImageCenterPoint.x - FloatMath.sqrt(f * Math.abs(f1));
        else
            imageCenterPoint.x = referenceImageCenterPoint.x + FloatMath.sqrt(f * Math.abs(f1));
        if(f1 < 0.0F)
            imageCenterPoint.y = referenceImageCenterPoint.y - FloatMath.sqrt(f * Math.abs(f2));
        else
            imageCenterPoint.y = referenceImageCenterPoint.y + FloatMath.sqrt(f * Math.abs(f2));
        setImageMatrix(currentMatrix);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(!isInitStandardMatrix)
        {
            standardMatrix.set(getImageMatrix());
            referenceMatrix.set(getImageMatrix());
            currentMatrix.set(getImageMatrix());
            isInitStandardMatrix = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 32
    //                   0 34
    //                   1 134
    //                   2 68;
           goto _L1 _L2 _L3 _L4
_L1:
        return true;
_L2:
        referencePoint.set(motionevent.getX(), motionevent.getY());
        referenceMatrix.set(getImageMatrix());
        mode = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        if(mode == 1)
        {
            currentMatrix.set(referenceMatrix);
            float f = motionevent.getX() - referencePoint.x;
            float f1 = motionevent.getY() - referencePoint.y;
            currentMatrix.postTranslate(f, f1);
            setImageMatrix(currentMatrix);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        mode = 0;
        if(true) goto _L1; else goto _L5
_L5:
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        if(getScaleType() != android.widget.ImageView.ScaleType.MATRIX)
            setScaleType(android.widget.ImageView.ScaleType.MATRIX);
        srcHeight = getDrawable().getIntrinsicHeight();
        srcWidth = getDrawable().getIntrinsicWidth();
        getLocalVisibleRect(globalRect);
        if(midPoint.x == 0.0F && midPoint.y == 0.0F)
            midPoint.set(globalRect.centerX(), globalRect.centerY());
        standardScale = Math.min((float)globalRect.height() / (float)srcHeight, (float)globalRect.width() / (float)srcWidth);
        imageCenterPoint.set(globalRect.centerX(), globalRect.centerY());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("imageCenterPoint.x``` -->> ")).append(imageCenterPoint.x).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("imageCenterPoint.y``` -->> ")).append(imageCenterPoint.y).toString());
        return flag;
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        super.setImageBitmap(bitmap);
        currentMatrix.set(getImageMatrix());
    }

    public void zoomIn()
    {
        zoom(0.8F);
    }

    public void zoomOut()
    {
        zoom(1.25F);
    }

    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final String TAG = "TouchImageView";
    private Matrix currentMatrix;
    private float currentScale;
    private Rect globalRect;
    private float imageBotttom;
    private PointF imageCenterPoint;
    private float imageLeft;
    private float imageRight;
    private float imageTop;
    private boolean isInitStandardMatrix;
    private PointF midPoint;
    private int mode;
    private float referenceDistance;
    private PointF referenceImageCenterPoint;
    private Matrix referenceMatrix;
    private PointF referencePoint;
    private float referenceScale;
    private int srcHeight;
    private int srcWidth;
    private Matrix standardMatrix;
    private float standardScale;
}
