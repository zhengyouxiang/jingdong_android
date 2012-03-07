// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.frame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import com.jindong.app.mall.utils.DPIUtil;

public class RadioStateDrawable extends Drawable
{

    public RadioStateDrawable(Context context1, int i, String s, boolean flag, int j)
    {
        highlight = flag;
        context = context1;
        label = s;
        bitmap = BitmapFactory.decodeStream(context1.getResources().openRawResource(i));
        setShade(j);
        highlightBitmap = BitmapFactory.decodeResource(context1.getResources(), 0x7f020069);
    }

    public RadioStateDrawable(Context context1, int i, String s, boolean flag, int j, int k)
    {
        highlight = flag;
        context = context1;
        label = s;
        bitmap = BitmapFactory.decodeStream(context1.getResources().openRawResource(i));
        int ai[] = new int[2];
        ai[0] = j;
        ai[1] = k;
        shader = new LinearGradient(0.0F, 0.0F, 0.0F, bitmap.getHeight(), ai, null, android.graphics.Shader.TileMode.MIRROR);
    }

    public void draw(Canvas canvas)
    {
        int l;
        Paint paint;
        int i = DPIUtil.dip2px(28F);
        int j = DPIUtil.dip2px(26F);
        int k = (width - i) / 2;
        l = DPIUtil.dip2px(6F);
        canvas.drawColor(0);
        paint = new Paint();
        paint.setColor(-1);
        paint.setStyle(android.graphics.Paint.Style.FILL);
        paint.setTextSize(DPIUtil.dip2px(13F));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setFakeBoldText(true);
        paint.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint.setAntiAlias(true);
        Rect rect = new Rect(k, l, k + i, l + j);
        canvas.drawBitmap(bitmap, null, rect, null);
        Paint paint1 = new Paint();
        paint1.setColor(-1);
        paint1.setStyle(android.graphics.Paint.Style.FILL);
        paint1.setTextSize(DPIUtil.dip2px(13F));
        paint1.setTypeface(Typeface.DEFAULT_BOLD);
        paint1.setTextAlign(android.graphics.Paint.Align.CENTER);
        paint1.setAntiAlias(true);
        canvas.drawText(label, width / 2, l + j + DPIUtil.dip2px(16F), paint1);
        if(stateController == null || stateController.getNum() == null) goto _L2; else goto _L1
_L1:
        String s;
        float f;
        float f1;
        float f2;
        float af[];
        int i1;
        s = stateController.getNum().toString();
        f = width - DPIUtil.dip2px(23F);
        f1 = l + DPIUtil.dip2px(11F);
        f2 = 0.0F;
        af = new float[s.length()];
        paint.getTextWidths(s, af);
        i1 = 0;
_L6:
        int j1 = af.length;
        if(i1 < j1) goto _L4; else goto _L3
_L3:
        android.graphics.Paint.FontMetrics fontmetrics = paint.getFontMetrics();
        float f3 = fontmetrics.descent - fontmetrics.top;
        float f4 = Math.max(f3, f2 + (float)DPIUtil.dip2px(10F));
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        RectF rectf = new RectF();
        rectf.left = f - f4 / 2.0F;
        rectf.top = f1 - (float)DPIUtil.dip2px(4F) - f3 / 2.0F;
        rectf.right = f4 + rectf.left;
        rectf.bottom = f3 + rectf.top;
        float f5 = f3 / 2.0F;
        LinearGradient lineargradient = new LinearGradient(0.0F, 0.0F, 0.0F, f3, 0xfff50000, 0xffb10202, android.graphics.Shader.TileMode.CLAMP);
        paint2.setStyle(android.graphics.Paint.Style.FILL);
        paint2.setShader(lineargradient);
        canvas.drawRoundRect(rectf, f5, f5, paint2);
        paint2.setStyle(android.graphics.Paint.Style.STROKE);
        paint2.setShader(null);
        paint2.setColor(-1);
        paint2.setStrokeWidth(DPIUtil.dip2px(2.0F));
        canvas.drawRoundRect(rectf, f5, f5, paint2);
        canvas.drawText(s, f, f1, paint);
_L2:
        return;
_L4:
        f2 += af[i1];
        i1++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int getOpacity()
    {
        return 0;
    }

    public void setAlpha(int i)
    {
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    public void setShade(int i)
    {
        int ai[] = new int[2];
        i;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 123
    //                   1 142
    //                   2 180
    //                   3 206
    //                   4 262
    //                   5 161
    //                   6 231;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        break MISSING_BLOCK_LABEL_262;
_L9:
        shader = new LinearGradient(0.0F, 0.0F, 0.0F, bitmap.getHeight(), ai, null, android.graphics.Shader.TileMode.MIRROR);
        if(highlight)
        {
            int ai2[] = new int[2];
            ai2[0] = -1;
            ai2[1] = 0xffcccccc;
            textShader = new LinearGradient(0.0F, 0.0F, 0.0F, 10F, ai2, null, android.graphics.Shader.TileMode.MIRROR);
        } else
        {
            int ai1[] = new int[2];
            ai1[0] = 0xffcccccc;
            ai1[1] = 0xff444444;
            textShader = new LinearGradient(0.0F, 0.0F, 0.0F, 10F, ai1, null, android.graphics.Shader.TileMode.MIRROR);
        }
        return;
_L2:
        ai = new int[2];
        ai[0] = 0xffcccccc;
        ai[1] = 0xff444444;
          goto _L9
_L3:
        ai = new int[2];
        ai[0] = 0xff00ffff;
        ai[1] = 0xff0000ff;
          goto _L9
_L7:
        ai = new int[2];
        ai[0] = -65281;
        ai[1] = 0xffff0000;
          goto _L9
_L4:
        ai = new int[2];
        ai[0] = -65281;
        ai[1] = Color.rgb(292, 52, 100);
          goto _L9
_L5:
        ai = new int[2];
        ai[0] = -256;
        ai[1] = Color.rgb(255, 126, 0);
          goto _L9
_L8:
        ai = new int[2];
        ai[0] = Color.rgb(255, 126, 0);
        ai[1] = Color.rgb(255, 90, 0);
          goto _L9
        ai = new int[2];
        ai[0] = 0xff00ff00;
        ai[1] = Color.rgb(0, 79, 4);
          goto _L9
    }

    public void setStateController(TabBarButton.StateController statecontroller)
    {
        stateController = statecontroller;
    }

    public static final int SHADE_BLUE = 1;
    public static final int SHADE_GRAY = 0;
    public static final int SHADE_GREEN = 4;
    public static final int SHADE_MAGENTA = 2;
    public static final int SHADE_ORANGE = 6;
    public static final int SHADE_RED = 5;
    public static final int SHADE_YELLOW = 3;
    public static int screen_width;
    public static int width;
    private Bitmap bitmap;
    Context context;
    private boolean highlight;
    private Bitmap highlightBitmap;
    private String label;
    private Shader shader;
    private TabBarButton.StateController stateController;
    private Shader textShader;
}
