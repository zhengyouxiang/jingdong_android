// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import com.google.zxing.ResultPoint;
import com.jindong.lib.zxing.client.android.camera.CameraManager;
import java.util.*;

public final class ViewfinderView extends View
{

    public ViewfinderView(Context context1, AttributeSet attributeset)
    {
        super(context1, attributeset);
        context = context1;
        Resources resources = getResources();
        maskColor = resources.getColor(0x7f070020);
        resultColor = resources.getColor(0x7f070012);
        frameColor = resources.getColor(0x7f07001e);
        laserColor = resources.getColor(0x7f07001f);
        resultPointColor = resources.getColor(0x7f07000d);
        scannerAlpha = 0;
        possibleResultPoints = new HashSet(5);
    }

    private void showError()
    {
        if(context != null)
            Toast.makeText(context, 0x7f0901d2, 1).show();
    }

    public void addPossibleResultPoint(ResultPoint resultpoint)
    {
        possibleResultPoints.add(resultpoint);
    }

    public void drawResultBitmap(Bitmap bitmap)
    {
        resultBitmap = bitmap;
        invalidate();
    }

    public void drawViewfinder()
    {
        resultBitmap = null;
        invalidate();
    }

    public void onDraw(Canvas canvas)
    {
        Rect rect;
        rect = CameraManager.get().getFramingRect();
        if(rect == null)
        {
            showError();
        } else
        {
label0:
            {
                int i = canvas.getWidth();
                int j = canvas.getHeight();
                int k = j / 2;
                int l = i / 2;
                Paint paint1;
                int i1;
                if(j > 400 && i > 600)
                {
                    rect.top = k - 180;
                    rect.bottom = k + 180;
                    rect.left = l - 240;
                    rect.right = l + 240;
                } else
                if(i > 400 && j > 600)
                {
                    rect.top = k - 240;
                    rect.bottom = k + 240;
                    rect.left = l - 180;
                    rect.right = l + 180;
                }
                paint1 = paint;
                if(resultBitmap != null)
                    i1 = resultColor;
                else
                    i1 = maskColor;
                paint1.setColor(i1);
                canvas.drawRect(0.0F, 0.0F, i, rect.top, paint);
                canvas.drawRect(0.0F, rect.top, rect.left, 1 + rect.bottom, paint);
                canvas.drawRect(1 + rect.right, rect.top, i, 1 + rect.bottom, paint);
                canvas.drawRect(0.0F, 1 + rect.bottom, i, j, paint);
                if(resultBitmap == null)
                    break label0;
                paint.setAlpha(255);
                if(resultBitmap.getWidth() != 480 || resultBitmap.getHeight() != 360)
                    resultBitmap = Bitmap.createScaledBitmap(resultBitmap, 480, 360, false);
                canvas.drawBitmap(resultBitmap, rect.left, rect.top, paint);
            }
        }
_L3:
        return;
        Iterator iterator1;
        paint.setColor(frameColor);
        canvas.drawRect(rect.left, rect.top, 1 + rect.right, 2 + rect.top, paint);
        canvas.drawRect(rect.left, 2 + rect.top, 2 + rect.left, rect.bottom - 1, paint);
        canvas.drawRect(rect.right - 1, rect.top, 1 + rect.right, rect.bottom - 1, paint);
        canvas.drawRect(rect.left, rect.bottom - 1, 1 + rect.right, 1 + rect.bottom, paint);
        paint.setColor(laserColor);
        paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
        scannerAlpha = (1 + scannerAlpha) % SCANNER_ALPHA.length;
        int j1 = rect.height() / 2 + rect.top;
        canvas.drawRect(2 + rect.left, j1 - 1, rect.right - 1, j1 + 2, paint);
        Collection collection = possibleResultPoints;
        Collection collection1 = lastPossibleResultPoints;
        if(collection.isEmpty())
        {
            lastPossibleResultPoints = null;
        } else
        {
            possibleResultPoints = new HashSet(5);
            lastPossibleResultPoints = collection;
            paint.setAlpha(255);
            paint.setColor(resultPointColor);
            Iterator iterator = collection.iterator();
            while(iterator.hasNext()) 
            {
                ResultPoint resultpoint = (ResultPoint)iterator.next();
                canvas.drawCircle((float)rect.left + resultpoint.getX(), (float)rect.top + resultpoint.getY(), 6F, paint);
            }
        }
        if(collection1 == null) goto _L2; else goto _L1
_L1:
        paint.setAlpha(127);
        paint.setColor(resultPointColor);
        iterator1 = collection1.iterator();
_L4:
        if(iterator1.hasNext())
            break MISSING_BLOCK_LABEL_798;
_L2:
        postInvalidateDelayed(100L, rect.left, rect.top, rect.right, rect.bottom);
          goto _L3
        ResultPoint resultpoint1 = (ResultPoint)iterator1.next();
        canvas.drawCircle((float)rect.left + resultpoint1.getX(), (float)rect.top + resultpoint1.getY(), 3F, paint);
          goto _L4
    }

    private static final long ANIMATION_DELAY = 100L;
    private static final int OPAQUE = 255;
    private static final int SCANNER_ALPHA[];
    private Context context;
    private final int frameColor;
    private final int laserColor;
    private Collection lastPossibleResultPoints;
    private final int maskColor;
    private final Paint paint = new Paint();
    private Collection possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private final int resultPointColor;
    private int scannerAlpha;

    static 
    {
        int ai[] = new int[8];
        ai[1] = 64;
        ai[2] = 128;
        ai[3] = 192;
        ai[4] = 255;
        ai[5] = 192;
        ai[6] = 128;
        ai[7] = 64;
        SCANNER_ALPHA = ai;
    }
}
