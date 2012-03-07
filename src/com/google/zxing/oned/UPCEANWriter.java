// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

public abstract class UPCEANWriter
    implements Writer
{

    public UPCEANWriter()
    {
    }

    protected static int appendPattern(byte abyte0[], int i, int ai[], int j)
    {
        if(j != 0 && j != 1)
            throw new IllegalArgumentException("startColor must be either 0 or 1, but got: " + j);
        byte byte0 = (byte)j;
        int k = 0;
        int l = byte0;
        int i1 = i;
        for(int j1 = 0; j1 < ai.length;)
        {
            int k1 = i1;
            int l1 = k;
            for(int i2 = 0; i2 < ai[j1]; i2++)
            {
                abyte0[k1] = l;
                k1++;
                l1++;
            }

            int j2 = l ^ 1;
            j1++;
            l = j2;
            k = l1;
            i1 = k1;
        }

        return k;
    }

    private static BitMatrix renderResult(byte abyte0[], int i, int j)
    {
        int k = abyte0.length;
        int l = k + (UPCEANReader.START_END_PATTERN.length << 1);
        int i1 = Math.max(i, l);
        int j1 = Math.max(1, j);
        int k1 = i1 / l;
        int l1 = (i1 - k * k1) / 2;
        BitMatrix bitmatrix = new BitMatrix(i1, j1);
        int i2 = l1;
        for(int j2 = 0; j2 < k;)
        {
            if(abyte0[j2] == 1)
                bitmatrix.setRegion(i2, 0, k1, j1);
            j2++;
            i2 += k1;
        }

        return bitmatrix;
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("Found empty contents");
        if(i < 0 || j < 0)
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + j);
        else
            return renderResult(encode(s), i, j);
    }

    public abstract byte[] encode(String s);
}
