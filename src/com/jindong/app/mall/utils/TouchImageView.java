// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ImageView;

// Referenced classes of package com.jindong.app.mall.utils:
//            MultiTouchController

public class TouchImageView extends ImageView
    implements MultiTouchController.MultiTouchObjectCanvas
{
    public class Img
    {

        private void getMetrics(Resources resources)
        {
            DisplayMetrics displaymetrics = resources.getDisplayMetrics();
            int i;
            int j;
            if(resources.getConfiguration().orientation == 2)
                i = Math.max(displaymetrics.widthPixels, displaymetrics.heightPixels);
            else
                i = Math.min(displaymetrics.widthPixels, displaymetrics.heightPixels);
            displayWidth = i;
            if(resources.getConfiguration().orientation == 2)
                j = Math.min(displaymetrics.widthPixels, displaymetrics.heightPixels);
            else
                j = Math.max(displaymetrics.widthPixels, displaymetrics.heightPixels) - galleryHeight;
            displayHeight = j;
        }

        private void resetScreenMargin()
        {
            if((float)width * scaleX > (float)displayWidth)
            {
                TouchImageView.SCREEN_MARGIN_WIDTH_LEFT = (float)displayWidth;
                TouchImageView.SCREEN_MARGIN_WIDTH_RIGHT = (float)displayWidth;
            } else
            {
                TouchImageView.SCREEN_MARGIN_WIDTH_LEFT = (float)width * scaleX;
                TouchImageView.SCREEN_MARGIN_WIDTH_RIGHT = (float)width * scaleX;
            }
            if((float)height * scaleY > (float)displayHeight)
            {
                TouchImageView.SCREEN_MARGIN_HEIGHT_TOP = (float)(displayHeight - 32);
                TouchImageView.SCREEN_MARGIN_HEIGHT_BOTTOM = (float)(32 + displayHeight);
            } else
            {
                TouchImageView.SCREEN_MARGIN_HEIGHT_TOP = (float)height * scaleY - 32F;
                TouchImageView.SCREEN_MARGIN_HEIGHT_BOTTOM = 32F + (float)height * scaleY;
            }
        }

        private boolean setPos(float f, float f1, float f2, float f3)
        {
            resetScreenMargin();
            if(f2 == f3 && (double)f2 > 0.5D && f2 < 8F)
            {
                float f4 = f2 * (float)(width / 2);
                float f5 = f3 * (float)(height / 2);
                float f6 = f - f4;
                float f7 = f1 - f5;
                float f8 = f + f4;
                float f9 = f1 + f5;
                if(f6 > (float)displayWidth - TouchImageView.SCREEN_MARGIN_WIDTH_RIGHT)
                {
                    minX = (float)displayWidth - TouchImageView.SCREEN_MARGIN_WIDTH_RIGHT;
                    maxX = minX + f4 * 2.0F;
                } else
                if(f8 < TouchImageView.SCREEN_MARGIN_WIDTH_LEFT)
                {
                    maxX = TouchImageView.SCREEN_MARGIN_WIDTH_LEFT;
                    minX = maxX - f4 * 2.0F;
                } else
                {
                    minX = f6;
                    maxX = f8;
                }
                if(f7 > (float)displayHeight - TouchImageView.SCREEN_MARGIN_HEIGHT_BOTTOM)
                {
                    minY = (float)displayHeight - TouchImageView.SCREEN_MARGIN_HEIGHT_BOTTOM;
                    maxY = minY + f5 * 2.0F;
                } else
                if(f9 < TouchImageView.SCREEN_MARGIN_HEIGHT_TOP)
                {
                    maxY = TouchImageView.SCREEN_MARGIN_HEIGHT_TOP;
                    minY = maxY - f5 * 2.0F;
                } else
                {
                    minY = f7;
                    maxY = f9;
                }
                centerX = minX + (maxX - minX) / 2.0F;
                centerY = minY + (maxY - minY) / 2.0F;
                scaleX = f2;
                scaleY = f3;
            }
            return true;
        }

        public boolean containsPoint(float f, float f1)
        {
            boolean flag;
            if(f >= minX && f <= maxX && f1 >= minY && f1 <= maxY)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void draw(Canvas canvas)
        {
            canvas.save();
            drawable.setBounds((int)minX, (int)minY, (int)maxX, (int)maxY);
            drawable.draw(canvas);
            canvas.restore();
        }

        public float getCenterX()
        {
            return centerX;
        }

        public float getCenterY()
        {
            return centerY;
        }

        public Drawable getDrawable()
        {
            return drawable;
        }

        public int getHeight()
        {
            return height;
        }

        public float getMaxX()
        {
            return maxX;
        }

        public float getMaxY()
        {
            return maxY;
        }

        public float getMinX()
        {
            return minX;
        }

        public float getMinY()
        {
            return minY;
        }

        public float getScaleX()
        {
            return scaleX;
        }

        public float getScaleY()
        {
            return scaleY;
        }

        public int getWidth()
        {
            return width;
        }

        public void load(Resources resources)
        {
            getMetrics(resources);
            drawable = new BitmapDrawable(bitmap);
            width = drawable.getIntrinsicWidth();
            height = drawable.getIntrinsicHeight();
            if(firstLoad)
            {
                float f = displayWidth / 2;
                float f1 = displayHeight / 2;
                firstLoad = false;
                setPos(f, f1, 1.0F, 1.0F);
            }
        }

        public boolean setPos(MultiTouchController.PositionAndScale positionandscale)
        {
            float f = positionandscale.getXOff();
            float f1 = positionandscale.getYOff();
            float f2;
            float f3;
            if((2 & mUIMode) != 0)
                f2 = positionandscale.getScaleX();
            else
                f2 = positionandscale.getScale();
            if((2 & mUIMode) != 0)
                f3 = positionandscale.getScaleY();
            else
                f3 = positionandscale.getScale();
            return setPos(f, f1, f2, f3);
        }

        public void unload()
        {
            drawable = null;
        }

        public void zoomIn()
        {
            if(setPos(centerX, centerY, scaleX - 0.8F, scaleY - 0.8F))
                invalidate();
        }

        public void zoomOut()
        {
            if(setPos(centerX, centerY, 0.8F + scaleX, 0.8F + scaleY))
                invalidate();
        }

        private Bitmap bitmap;
        private float centerX;
        private float centerY;
        private int displayHeight;
        private int displayWidth;
        private Drawable drawable;
        private boolean firstLoad;
        private int height;
        private float maxX;
        private float maxY;
        private float minX;
        private float minY;
        private float scaleX;
        private float scaleY;
        final TouchImageView this$0;
        private int width;

        public Img(Bitmap bitmap1, Resources resources)
        {
            this$0 = TouchImageView.this;
            super();
            bitmap = bitmap1;
            firstLoad = true;
            getMetrics(resources);
        }
    }


    public TouchImageView(Context context)
    {
        this(context, null);
    }

    public TouchImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public TouchImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        img = null;
        multiTouchController = new MultiTouchController(this);
        currTouchPoint = new MultiTouchController.PointInfo();
        mShowDebugInfo = true;
        mUIMode = 1;
    }

    private void drawMultitouchDebugMarks(Canvas canvas)
    {
        currTouchPoint.isDown();
    }

    public Img getDraggableObjectAtPoint(MultiTouchController.PointInfo pointinfo)
    {
        return img;
    }

    public volatile Object getDraggableObjectAtPoint(MultiTouchController.PointInfo pointinfo)
    {
        return getDraggableObjectAtPoint(pointinfo);
    }

    public Img getImg()
    {
        return img;
    }

    public void getPositionAndScale(Img img1, MultiTouchController.PositionAndScale positionandscale)
    {
        boolean flag = false;
        float f = img1.getCenterX();
        float f1 = img1.getCenterY();
        boolean flag1;
        float f2;
        boolean flag2;
        float f3;
        float f4;
        if((2 & mUIMode) == 0)
            flag1 = true;
        else
            flag1 = false;
        f2 = (img1.getScaleX() + img1.getScaleY()) / 2.0F;
        if((2 & mUIMode) != 0)
            flag2 = true;
        else
            flag2 = false;
        f3 = img1.getScaleX();
        f4 = img1.getScaleY();
        if((1 & mUIMode) != 0)
            flag = true;
        positionandscale.set(f, f1, flag1, f2, flag2, f3, f4, flag);
    }

    public volatile void getPositionAndScale(Object obj, MultiTouchController.PositionAndScale positionandscale)
    {
        getPositionAndScale((Img)obj, positionandscale);
    }

    public void init(Context context, Bitmap bitmap, int i)
    {
        Resources resources = context.getResources();
        galleryHeight = i;
        SCREEN_MARGIN_HEIGHT_BOTTOM += 32F + (float)i;
        img = null;
        img = new Img(bitmap, resources);
        loadImages(context);
        setBackgroundColor(-1);
    }

    public void loadImages(Context context)
    {
        Resources resources = context.getResources();
        img.load(resources);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        img.draw(canvas);
        if(mShowDebugInfo)
            drawMultitouchDebugMarks(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return multiTouchController.onTouchEvent(motionevent);
    }

    public void selectObject(Img img1, MultiTouchController.PointInfo pointinfo)
    {
        currTouchPoint.set(pointinfo);
        invalidate();
    }

    public volatile void selectObject(Object obj, MultiTouchController.PointInfo pointinfo)
    {
        selectObject((Img)obj, pointinfo);
    }

    public boolean setPositionAndScale(Img img1, MultiTouchController.PositionAndScale positionandscale, MultiTouchController.PointInfo pointinfo)
    {
        currTouchPoint.set(pointinfo);
        boolean flag = img1.setPos(positionandscale);
        if(flag)
            invalidate();
        return flag;
    }

    public volatile boolean setPositionAndScale(Object obj, MultiTouchController.PositionAndScale positionandscale, MultiTouchController.PointInfo pointinfo)
    {
        return setPositionAndScale((Img)obj, positionandscale, pointinfo);
    }

    public void trackballClicked()
    {
        mUIMode = (1 + mUIMode) % 3;
        invalidate();
    }

    private static final int BOTTOM_FIX = 32;
    private static float SCREEN_MARGIN_HEIGHT_BOTTOM = 0F;
    private static float SCREEN_MARGIN_HEIGHT_TOP = 0F;
    private static float SCREEN_MARGIN_WIDTH_LEFT = 0F;
    private static float SCREEN_MARGIN_WIDTH_RIGHT = 0F;
    private static final int UI_MODE_ANISOTROPIC_SCALE = 2;
    private static final int UI_MODE_ROTATE = 1;
    private MultiTouchController.PointInfo currTouchPoint;
    private int galleryHeight;
    private Img img;
    private boolean mShowDebugInfo;
    private int mUIMode;
    private MultiTouchController multiTouchController;

    static 
    {
        SCREEN_MARGIN_WIDTH_RIGHT = 100F;
        SCREEN_MARGIN_WIDTH_LEFT = 100F;
        SCREEN_MARGIN_HEIGHT_BOTTOM = 100F;
        SCREEN_MARGIN_HEIGHT_TOP = 100F;
    }










}
