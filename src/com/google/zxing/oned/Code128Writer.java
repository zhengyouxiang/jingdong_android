// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, Code128Reader

public final class Code128Writer extends UPCEANWriter
{

    public Code128Writer()
    {
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.CODE_128)
            throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeformat);
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    public byte[] encode(String s)
    {
        int i = s.length();
        if(i > 80)
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + i);
        int j = 35;
        for(int k = 0; k < i;)
        {
            int ai[] = Code128Reader.CODE_PATTERNS[s.charAt(k) - 32];
            int j2 = j;
            for(int k2 = 0; k2 < ai.length; k2++)
                j2 += ai[k2];

            k++;
            j = j2;
        }

        byte abyte0[] = new byte[j];
        int l = appendPattern(abyte0, 0, Code128Reader.CODE_PATTERNS[104], 1);
        int i1 = 104;
        int j1 = l;
        for(int k1 = 0; k1 < i; k1++)
        {
            i1 += (s.charAt(k1) - 32) * (k1 + 1);
            j1 += appendPattern(abyte0, j1, Code128Reader.CODE_PATTERNS[s.charAt(k1) - 32], 1);
        }

        int l1 = i1 % 103;
        int i2 = j1 + appendPattern(abyte0, j1, Code128Reader.CODE_PATTERNS[l1], 1);
        int _tmp = i2 + appendPattern(abyte0, i2, Code128Reader.CODE_PATTERNS[106], 1);
        return abyte0;
    }
}
