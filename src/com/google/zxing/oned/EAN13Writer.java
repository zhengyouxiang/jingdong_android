// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANWriter, EAN13Reader, UPCEANReader

public final class EAN13Writer extends UPCEANWriter
{

    public EAN13Writer()
    {
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(barcodeformat != BarcodeFormat.EAN_13)
            throw new IllegalArgumentException("Can only encode EAN_13, but got " + barcodeformat);
        else
            return super.encode(s, barcodeformat, i, j, hashtable);
    }

    public byte[] encode(String s)
    {
        if(s.length() != 13)
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + s.length());
        int i = Integer.parseInt(s.substring(0, 1));
        int j = EAN13Reader.FIRST_DIGIT_ENCODINGS[i];
        byte abyte0[] = new byte[95];
        int k = 0 + appendPattern(abyte0, 0, UPCEANReader.START_END_PATTERN, 1);
        for(int l = 1; l <= 6; l++)
        {
            int l1 = Integer.parseInt(s.substring(l, l + 1));
            if((1 & j >> 6 - l) == 1)
                l1 += 10;
            k += appendPattern(abyte0, k, UPCEANReader.L_AND_G_PATTERNS[l1], 0);
        }

        int i1 = k + appendPattern(abyte0, k, UPCEANReader.MIDDLE_PATTERN, 0);
        for(int j1 = 7; j1 <= 12; j1++)
        {
            int k1 = Integer.parseInt(s.substring(j1, j1 + 1));
            i1 += appendPattern(abyte0, i1, UPCEANReader.L_PATTERNS[k1], 1);
        }

        int _tmp = i1 + appendPattern(abyte0, i1, UPCEANReader.START_END_PATTERN, 1);
        return abyte0;
    }

    private static final int codeWidth = 95;
}
