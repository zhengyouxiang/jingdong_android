// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.graphics.Bitmap;
import com.google.zxing.LuminanceSource;
import com.jindong.app.mall.utils.Log;

public final class PlanarYUVLuminanceSource extends LuminanceSource
{

    public PlanarYUVLuminanceSource(byte abyte0[], int i, int j, int k, int l, int i1, int j1)
    {
        super(i1, j1);
        if(k + i1 > i || l + j1 > j)
        {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        } else
        {
            yuvData = abyte0;
            dataWidth = i;
            dataHeight = j;
            left = k;
            top = l;
            return;
        }
    }

    public int getDataHeight()
    {
        return dataHeight;
    }

    public int getDataWidth()
    {
        return dataWidth;
    }

    public byte[] getMatrix()
    {
        int i;
        int j;
        i = getWidth();
        j = getHeight();
        if(i != dataWidth || j != dataHeight) goto _L2; else goto _L1
_L1:
        byte abyte2[] = yuvData;
_L4:
        return abyte2;
_L2:
        byte abyte0[];
        int l;
        byte abyte1[];
        int i1;
        int k = i * j;
        abyte0 = new byte[k];
        l = top * dataWidth + left;
        if(i == dataWidth)
        {
            System.arraycopy(yuvData, l, abyte0, 0, k);
            abyte2 = abyte0;
            continue; /* Loop/switch isn't completed */
        }
        abyte1 = yuvData;
        i1 = 0;
_L5:
label0:
        {
            if(i1 < j)
                break label0;
            abyte2 = abyte0;
        }
        if(true) goto _L4; else goto _L3
_L3:
        int j1 = i1 * i;
        try
        {
            System.arraycopy(abyte1, l, abyte0, j1, i);
        }
        catch(Exception exception)
        {
            if(Log.E)
                exception.printStackTrace();
        }
        l += dataWidth;
        i1++;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public byte[] getRow(int i, byte abyte0[])
    {
        int j;
        int k;
        if(i < 0 || i >= getHeight())
            throw new IllegalArgumentException((new StringBuilder("Requested row is outside the image: ")).append(i).toString());
        j = getWidth();
        if(abyte0 == null || abyte0.length < j)
            abyte0 = new byte[j];
        k = (i + top) * dataWidth + left;
        System.arraycopy(yuvData, k, abyte0, 0, j);
_L2:
        return abyte0;
        Exception exception;
        exception;
        if(Log.E)
            exception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isCropSupported()
    {
        return true;
    }

    public Bitmap renderCroppedGreyscaleBitmap()
    {
        int i;
        int j;
        int ai[];
        byte abyte0[];
        int k;
        int l;
        i = getWidth();
        j = getHeight();
        ai = new int[i * j];
        abyte0 = yuvData;
        k = top * dataWidth + left;
        l = 0;
_L2:
        if(l >= j)
        {
            Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            bitmap.setPixels(ai, 0, i, 0, 0, i, j);
            return bitmap;
        }
        int i1 = l * i;
        int j1 = 0;
        do
        {
label0:
            {
                if(j1 < i)
                    break label0;
                k += dataWidth;
                l++;
            }
            if(true)
                continue;
            int k1 = 0xff & abyte0[k + j1];
            ai[i1 + j1] = 0xff000000 | 0x10101 * k1;
            j1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte yuvData[];
}
