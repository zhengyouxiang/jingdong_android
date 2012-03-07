// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            BitMatrixParser, DecodedBitStreamParser

public final class Decoder
{

    public Decoder()
    {
    }

    private static int correctErrors(int ai[], int ai1[], int i)
        throws FormatException
    {
        if(ai1 != null && ai1.length > 3 + i / 2 || i < 0 || i > 512)
            throw FormatException.getFormatInstance();
        if(ai1 != null)
        {
            int j = ai1.length;
            if(0 > 0)
                j += 0;
            if(j > 3)
                throw FormatException.getFormatInstance();
        }
        return 0;
    }

    private static void verifyCodewordCount(int ai[], int i)
        throws FormatException
    {
label0:
        {
            if(ai.length < 4)
                throw FormatException.getFormatInstance();
            int j = ai[0];
            if(j > ai.length)
                throw FormatException.getFormatInstance();
            if(j == 0)
            {
                if(i >= ai.length)
                    break label0;
                ai[0] = ai.length - i;
            }
            return;
        }
        throw FormatException.getFormatInstance();
    }

    public DecoderResult decode(BitMatrix bitmatrix)
        throws FormatException
    {
        BitMatrixParser bitmatrixparser = new BitMatrixParser(bitmatrix);
        int ai[] = bitmatrixparser.readCodewords();
        if(ai == null || ai.length == 0)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            int i = 1 << 1 + bitmatrixparser.getECLevel();
            correctErrors(ai, bitmatrixparser.getErasures(), i);
            verifyCodewordCount(ai, i);
            return DecodedBitStreamParser.decode(ai);
        }
    }

    public DecoderResult decode(boolean aflag[][])
        throws FormatException
    {
        int i = aflag.length;
        BitMatrix bitmatrix = new BitMatrix(i);
        for(int j = 0; j < i; j++)
        {
            for(int k = 0; k < i; k++)
                if(aflag[k][j])
                    bitmatrix.set(k, j);

        }

        return decode(bitmatrix);
    }

    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
}
