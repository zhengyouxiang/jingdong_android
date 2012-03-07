// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code39Reader extends OneDReader
{

    public Code39Reader()
    {
        usingCheckDigit = false;
        extendedMode = false;
    }

    public Code39Reader(boolean flag)
    {
        usingCheckDigit = flag;
        extendedMode = false;
    }

    public Code39Reader(boolean flag, boolean flag1)
    {
        usingCheckDigit = flag;
        extendedMode = flag1;
    }

    private static String decodeExtended(StringBuffer stringbuffer)
        throws FormatException
    {
        int i;
        StringBuffer stringbuffer1;
        int j;
        i = stringbuffer.length();
        stringbuffer1 = new StringBuffer(i);
        j = 0;
_L9:
        char c;
        char c1;
        if(j >= i)
            break MISSING_BLOCK_LABEL_287;
        c = stringbuffer.charAt(j);
        if(c != '+' && c != '$' && c != '%' && c != '/')
            break MISSING_BLOCK_LABEL_277;
        c1 = stringbuffer.charAt(j + 1);
        c;
        JVM INSTR lookupswitch 4: default 108
    //                   36: 155
    //                   37: 183
    //                   43: 127
    //                   47: 235;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        int k = 0;
_L6:
        stringbuffer1.append(k);
        j++;
_L7:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        if(c1 >= 'A' && c1 <= 'Z')
            k = c1 + 32;
        else
            throw FormatException.getFormatInstance();
          goto _L6
_L2:
        if(c1 >= 'A' && c1 <= 'Z')
            k = c1 - 64;
        else
            throw FormatException.getFormatInstance();
          goto _L6
_L3:
        if(c1 >= 'A' && c1 <= 'E')
            k = c1 - 38;
        else
        if(c1 >= 'F' && c1 <= 'W')
            k = c1 - 11;
        else
            throw FormatException.getFormatInstance();
          goto _L6
_L5:
        if(c1 >= 'A' && c1 <= 'O')
            k = c1 - 32;
        else
        if(c1 == 'Z')
            k = 58;
        else
            throw FormatException.getFormatInstance();
          goto _L6
        stringbuffer1.append(c);
          goto _L7
        return stringbuffer1.toString();
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static int[] findAsteriskPattern(BitArray bitarray)
        throws NotFoundException
    {
        int i = bitarray.getSize();
        int j = 0;
        do
        {
            if(j >= i || bitarray.get(j))
            {
                int ai[] = new int[9];
                int k = ai.length;
                boolean flag = false;
                int l = j;
                int i1 = 0;
                while(j < i) 
                {
                    if(flag ^ bitarray.get(j))
                    {
                        ai[i1] = 1 + ai[i1];
                    } else
                    {
                        if(i1 == k - 1)
                        {
                            if(toNarrowWidePattern(ai) == ASTERISK_ENCODING && bitarray.isRange(Math.max(0, l - (j - l) / 2), l, false))
                            {
                                int ai1[] = new int[2];
                                ai1[0] = l;
                                ai1[1] = j;
                                return ai1;
                            }
                            l += ai[0] + ai[1];
                            for(int j1 = 2; j1 < k; j1++)
                                ai[j1 - 2] = ai[j1];

                            ai[k - 2] = 0;
                            ai[k - 1] = 0;
                            i1--;
                        } else
                        {
                            i1++;
                        }
                        ai[i1] = 1;
                        if(!flag)
                            flag = true;
                        else
                            flag = false;
                    }
                    j++;
                }
            } else
            {
                j++;
                continue;
            }
            throw NotFoundException.getNotFoundInstance();
        } while(true);
    }

    private static char patternToChar(int i)
        throws NotFoundException
    {
        for(int j = 0; j < CHARACTER_ENCODINGS.length; j++)
            if(CHARACTER_ENCODINGS[j] == i)
                return ALPHABET[j];

        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int ai[])
    {
        int i;
        int j;
        i = ai.length;
        j = 0;
_L6:
        int k;
        int j1;
        int k1;
        int l1;
        int j2;
        k = 0x7fffffff;
        for(int l = 0; l < i; l++)
        {
            int i3 = ai[l];
            if(i3 < k && i3 > j)
                k = i3;
        }

        int i1 = 0;
        j1 = 0;
        k1 = 0;
        l1 = 0;
        for(; i1 < i; i1++)
        {
            int l2 = ai[i1];
            if(ai[i1] > k)
            {
                j1 |= 1 << i - 1 - i1;
                l1++;
                k1 += l2;
            }
        }

        if(l1 != 3)
            break MISSING_BLOCK_LABEL_171;
        j2 = 0;
_L3:
        if(j2 >= i || l1 <= 0) goto _L2; else goto _L1
_L1:
        int i2;
        int k2 = ai[j2];
        if(ai[j2] <= k)
            continue; /* Loop/switch isn't completed */
        l1--;
        if(k2 << 1 < k1)
            continue; /* Loop/switch isn't completed */
        i2 = -1;
_L4:
        return i2;
        j2++;
          goto _L3
_L2:
        i2 = j1;
          goto _L4
label0:
        {
            if(l1 > 3)
                break label0;
            i2 = -1;
        }
          goto _L4
        j = k;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        int ai[];
        int j;
        int k;
        StringBuffer stringbuffer;
        int ai1[];
        ai = findAsteriskPattern(bitarray);
        j = ai[1];
        for(k = bitarray.getSize(); j < k && !bitarray.get(j); j++);
        stringbuffer = new StringBuffer(20);
        ai1 = new int[9];
_L2:
        char c;
        int j1;
        recordPattern(bitarray, j, ai1);
        int l = toNarrowWidePattern(ai1);
        if(l < 0)
            throw NotFoundException.getNotFoundInstance();
        c = patternToChar(l);
        stringbuffer.append(c);
        int i1 = 0;
        j1 = j;
        for(; i1 < ai1.length; i1++)
            j1 += ai1[i1];

        break;
        while(false) 
        {
            k1 = j1;
            for(; k1 < k && !bitarray.get(k1); k1++);
            if(c == '*')
            {
                stringbuffer.deleteCharAt(stringbuffer.length() - 1);
                int l1 = 0;
                int i2 = 0;
                for(; l1 < ai1.length; l1++)
                    i2 += ai1[l1];

                int j2 = k1 - j - i2;
                if(k1 != k && j2 / 2 < i2)
                    throw NotFoundException.getNotFoundInstance();
                if(usingCheckDigit)
                {
                    int k2 = stringbuffer.length() - 1;
                    int l2 = 0;
                    int i3 = 0;
                    for(; l2 < k2; l2++)
                        i3 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(stringbuffer.charAt(l2));

                    if(stringbuffer.charAt(k2) != ALPHABET[i3 % 43])
                        throw ChecksumException.getChecksumInstance();
                    stringbuffer.deleteCharAt(k2);
                }
                if(stringbuffer.length() == 0)
                    throw NotFoundException.getNotFoundInstance();
                String s;
                float f;
                float f1;
                ResultPoint aresultpoint[];
                if(extendedMode)
                    s = decodeExtended(stringbuffer);
                else
                    s = stringbuffer.toString();
                f = (float)(ai[1] + ai[0]) / 2.0F;
                f1 = (float)(j + k1) / 2.0F;
                aresultpoint = new ResultPoint[2];
                aresultpoint[0] = new ResultPoint(f, i);
                aresultpoint[1] = new ResultPoint(f1, i);
                return new Result(s, null, aresultpoint, BarcodeFormat.CODE_39);
            }
            j = k1;
        }
_L4:
        if(true) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final char ALPHABET[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    private static final int ASTERISK_ENCODING;
    static final int CHARACTER_ENCODINGS[];
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static 
    {
        int ai[] = new int[44];
        ai[0] = 52;
        ai[1] = 289;
        ai[2] = 97;
        ai[3] = 352;
        ai[4] = 49;
        ai[5] = 304;
        ai[6] = 112;
        ai[7] = 37;
        ai[8] = 292;
        ai[9] = 100;
        ai[10] = 265;
        ai[11] = 73;
        ai[12] = 328;
        ai[13] = 25;
        ai[14] = 280;
        ai[15] = 88;
        ai[16] = 13;
        ai[17] = 268;
        ai[18] = 76;
        ai[19] = 28;
        ai[20] = 259;
        ai[21] = 67;
        ai[22] = 322;
        ai[23] = 19;
        ai[24] = 274;
        ai[25] = 82;
        ai[26] = 7;
        ai[27] = 262;
        ai[28] = 70;
        ai[29] = 22;
        ai[30] = 385;
        ai[31] = 193;
        ai[32] = 448;
        ai[33] = 145;
        ai[34] = 400;
        ai[35] = 208;
        ai[36] = 133;
        ai[37] = 388;
        ai[38] = 196;
        ai[39] = 148;
        ai[40] = 168;
        ai[41] = 162;
        ai[42] = 138;
        ai[43] = 42;
        CHARACTER_ENCODINGS = ai;
        ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
    }
}
