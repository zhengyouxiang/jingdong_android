// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader

public final class Code93Reader extends OneDReader
{

    public Code93Reader()
    {
    }

    private static void checkChecksums(StringBuffer stringbuffer)
        throws ChecksumException
    {
        int i = stringbuffer.length();
        checkOneChecksum(stringbuffer, i - 2, 20);
        checkOneChecksum(stringbuffer, i - 1, 15);
    }

    private static void checkOneChecksum(StringBuffer stringbuffer, int i, int j)
        throws ChecksumException
    {
        int k = i - 1;
        int l = 1;
        int i1 = k;
        int j1 = 0;
        for(; i1 >= 0; i1--)
        {
            j1 += l * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(stringbuffer.charAt(i1));
            if(++l > j)
                l = 1;
        }

        if(stringbuffer.charAt(i) != ALPHABET[j1 % 47])
            throw ChecksumException.getChecksumInstance();
        else
            return;
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
            break MISSING_BLOCK_LABEL_263;
        c = stringbuffer.charAt(j);
        if(c < 'a' || c > 'd')
            break MISSING_BLOCK_LABEL_253;
        c1 = stringbuffer.charAt(j + 1);
        c;
        JVM INSTR tableswitch 97 100: default 84
    //                   97 131
    //                   98 159
    //                   99 211
    //                   100 103;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        int k = 0;
_L6:
        stringbuffer1.append(k);
        j++;
_L7:
        j++;
        continue; /* Loop/switch isn't completed */
_L5:
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
_L4:
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
                int ai[] = new int[6];
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
                            if(toPattern(ai) == ASTERISK_ENCODING)
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

    private static int toPattern(int ai[])
    {
        int i;
        int k;
        int l;
        int i1;
        i = ai.length;
        int j = 0;
        k = 0;
        for(; j < i; j++)
            k += ai[j];

        l = 0;
        i1 = 0;
_L3:
        if(l >= i)
            break MISSING_BLOCK_LABEL_148;
        int k1 = (9 * (ai[l] << 8)) / k;
        int l1 = k1 >> 8;
        int j1;
        int i2;
        int j2;
        int k2;
        if((k1 & 0xff) > 127)
            i2 = l1 + 1;
        else
            i2 = l1;
        if(i2 >= 1 && i2 <= 4) goto _L2; else goto _L1
_L1:
        j1 = -1;
_L4:
        return j1;
_L2:
        if((l & 1) == 0)
        {
            j2 = i1;
            for(k2 = 0; k2 < i2; k2++)
                j2 = 1 | j2 << 1;

            i1 = j2;
        } else
        {
            i1 <<= i2;
        }
        l++;
          goto _L3
        j1 = i1;
          goto _L4
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
        ai1 = new int[6];
_L2:
        char c;
        int j1;
        recordPattern(bitarray, j, ai1);
        int l = toPattern(ai1);
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
                if(k1 == k || !bitarray.get(k1))
                    throw NotFoundException.getNotFoundInstance();
                if(stringbuffer.length() < 2)
                {
                    throw NotFoundException.getNotFoundInstance();
                } else
                {
                    checkChecksums(stringbuffer);
                    stringbuffer.setLength(stringbuffer.length() - 2);
                    String s = decodeExtended(stringbuffer);
                    float f = (float)(ai[1] + ai[0]) / 2.0F;
                    float f1 = (float)(j + k1) / 2.0F;
                    ResultPoint aresultpoint[] = new ResultPoint[2];
                    aresultpoint[0] = new ResultPoint(f, i);
                    aresultpoint[1] = new ResultPoint(f1, i);
                    return new Result(s, null, aresultpoint, BarcodeFormat.CODE_93);
                }
            }
            j = k1;
        }
_L4:
        if(true) goto _L2; else goto _L1
_L1:
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final char ALPHABET[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    private static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final int ASTERISK_ENCODING;
    private static final int CHARACTER_ENCODINGS[];

    static 
    {
        int ai[] = new int[48];
        ai[0] = 276;
        ai[1] = 328;
        ai[2] = 324;
        ai[3] = 322;
        ai[4] = 296;
        ai[5] = 292;
        ai[6] = 290;
        ai[7] = 336;
        ai[8] = 274;
        ai[9] = 266;
        ai[10] = 424;
        ai[11] = 420;
        ai[12] = 418;
        ai[13] = 404;
        ai[14] = 402;
        ai[15] = 394;
        ai[16] = 360;
        ai[17] = 356;
        ai[18] = 354;
        ai[19] = 308;
        ai[20] = 282;
        ai[21] = 344;
        ai[22] = 332;
        ai[23] = 326;
        ai[24] = 300;
        ai[25] = 278;
        ai[26] = 436;
        ai[27] = 434;
        ai[28] = 428;
        ai[29] = 422;
        ai[30] = 406;
        ai[31] = 410;
        ai[32] = 364;
        ai[33] = 358;
        ai[34] = 310;
        ai[35] = 314;
        ai[36] = 302;
        ai[37] = 468;
        ai[38] = 466;
        ai[39] = 458;
        ai[40] = 366;
        ai[41] = 374;
        ai[42] = 430;
        ai[43] = 294;
        ai[44] = 474;
        ai[45] = 470;
        ai[46] = 306;
        ai[47] = 350;
        CHARACTER_ENCODINGS = ai;
        ASTERISK_ENCODING = CHARACTER_ENCODINGS[47];
    }
}
