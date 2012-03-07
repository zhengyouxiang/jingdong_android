// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;

final class DecodedBitStreamParser
{

    private DecodedBitStreamParser()
    {
    }

    private static StringBuffer add(String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer(5);
        StringBuffer stringbuffer1 = new StringBuffer(5);
        StringBuffer stringbuffer2 = new StringBuffer(s.length());
        for(int i = 0; i < s.length(); i++)
            stringbuffer2.append('0');

        int j = s.length() - 3;
        int k = 0;
        for(; j > -1; j -= 3)
        {
            stringbuffer.setLength(0);
            stringbuffer.append(s.charAt(j));
            stringbuffer.append(s.charAt(j + 1));
            stringbuffer.append(s.charAt(j + 2));
            stringbuffer1.setLength(0);
            stringbuffer1.append(s1.charAt(j));
            stringbuffer1.append(s1.charAt(j + 1));
            stringbuffer1.append(s1.charAt(j + 2));
            int l = Integer.parseInt(stringbuffer.toString());
            int i1 = Integer.parseInt(stringbuffer1.toString());
            int j1 = (k + (l + i1)) % 1000;
            k = (k + (l + i1)) / 1000;
            stringbuffer2.setCharAt(j + 2, (char)(48 + j1 % 10));
            stringbuffer2.setCharAt(j + 1, (char)(48 + (j1 / 10) % 10));
            stringbuffer2.setCharAt(j, (char)(48 + j1 / 100));
        }

        return stringbuffer2;
    }

    private static int byteCompaction(int i, int ai[], int j, StringBuffer stringbuffer)
    {
        if(i == 901)
        {
            long l3 = 0L;
            char ac1[] = new char[6];
            int ai1[] = new int[6];
            int j2 = j;
            int k2 = 0;
            boolean flag1 = false;
            do
            {
                if(j2 >= ai[0] || flag1)
                    break;
                int j3 = j2 + 1;
                int k3 = ai[j2];
                int k;
                long l;
                int i1;
                int j1;
                boolean flag;
                int k1;
                int l1;
                char ac[];
                long l2;
                int i2;
                int i3;
                if(k3 < 900)
                {
                    ai1[k2] = k3;
                    k2++;
                    l3 = l3 * 900L + (long)k3;
                    j2 = j3;
                } else
                if(k3 == 900 || k3 == 901 || k3 == 902 || k3 == 924 || k3 == 928 || k3 == 923 || k3 == 922)
                {
                    j2 = j3 - 1;
                    flag1 = true;
                } else
                {
                    j2 = j3;
                }
                if(k2 % 5 == 0 && k2 > 0)
                {
                    for(int i4 = 0; i4 < 6; i4++)
                    {
                        ac1[5 - i4] = (char)(int)(l3 % 256L);
                        l3 >>= 8;
                    }

                    stringbuffer.append(ac1);
                    k2 = 0;
                }
            } while(true);
            for(i3 = 5 * (k2 / 5); i3 < k2; i3++)
                stringbuffer.append((char)ai1[i3]);

            k = j2;
        } else
        if(i == 924)
        {
            l = 0L;
            i1 = j;
            j1 = 0;
            flag = false;
            do
            {
                if(i1 >= ai[0] || flag)
                    break;
                k1 = i1 + 1;
                l1 = ai[i1];
                if(l1 < 900)
                {
                    j1++;
                    l = l * 900L + (long)l1;
                    i1 = k1;
                } else
                if(l1 == 900 || l1 == 901 || l1 == 902 || l1 == 924 || l1 == 928 || l1 == 923 || l1 == 922)
                {
                    i1 = k1 - 1;
                    flag = true;
                } else
                {
                    i1 = k1;
                }
                if(j1 % 5 == 0 && j1 > 0)
                {
                    ac = new char[6];
                    l2 = l;
                    for(i2 = 0; i2 < 6; i2++)
                    {
                        ac[5 - i2] = (char)(int)(255L & l2);
                        l2 >>= 8;
                    }

                    stringbuffer.append(ac);
                    l = l2;
                }
            } while(true);
            k = i1;
        } else
        {
            k = j;
        }
        return k;
    }

    static DecoderResult decode(int ai[])
        throws FormatException
    {
        StringBuffer stringbuffer;
        int i;
        int j;
        stringbuffer = new StringBuffer(100);
        i = 1 + 1;
        j = ai[1];
_L7:
        if(i >= ai[0])
            break MISSING_BLOCK_LABEL_168;
        j;
        JVM INSTR lookupswitch 5: default 76
    //                   900: 106
    //                   901: 117
    //                   902: 129
    //                   913: 140
    //                   924: 152;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        int k = textCompaction(ai, i - 1, stringbuffer);
_L8:
        if(k < ai.length)
        {
            i = k + 1;
            j = ai[k];
        } else
        {
            throw FormatException.getFormatInstance();
        }
        if(true) goto _L7; else goto _L2
_L2:
        k = textCompaction(ai, i, stringbuffer);
          goto _L8
_L3:
        k = byteCompaction(j, ai, i, stringbuffer);
          goto _L8
_L4:
        k = numericCompaction(ai, i, stringbuffer);
          goto _L8
_L5:
        k = byteCompaction(j, ai, i, stringbuffer);
          goto _L8
_L6:
        k = byteCompaction(j, ai, i, stringbuffer);
          goto _L8
        return new DecoderResult(null, stringbuffer.toString(), null, null);
    }

    private static String decodeBase900toBase10(int ai[], int i)
    {
        StringBuffer stringbuffer;
        int k;
        int j = 0;
        stringbuffer = null;
        while(j < i) 
        {
            StringBuffer stringbuffer1 = multiply(EXP900[i - j - 1], ai[j]);
            if(stringbuffer == null)
                stringbuffer = stringbuffer1;
            else
                stringbuffer = add(stringbuffer.toString(), stringbuffer1.toString());
            j++;
        }
        k = 0;
_L3:
        if(k >= stringbuffer.length())
            break MISSING_BLOCK_LABEL_111;
        if(stringbuffer.charAt(k) != '1') goto _L2; else goto _L1
_L1:
        String s = stringbuffer.toString().substring(k + 1);
_L4:
        if(s == null)
            s = stringbuffer.toString();
        return s;
_L2:
        k++;
          goto _L3
        s = null;
          goto _L4
    }

    private static void decodeTextCompaction(int ai[], int ai1[], int i, StringBuffer stringbuffer)
    {
        int j;
        int k;
        int l;
        j = 0;
        k = 0;
        l = 0;
_L15:
        if(j >= i) goto _L2; else goto _L1
_L1:
        int i1 = ai[j];
        l;
        JVM INSTR tableswitch 0 4: default 56
    //                   0 93
    //                   1 233
    //                   2 373
    //                   3 534
    //                   4 616;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        int j1;
        char c;
        j1 = l;
        l = k;
        c = '\0';
_L9:
        if(c != 0)
            stringbuffer.append(c);
        j++;
        k = l;
        l = j1;
        continue; /* Loop/switch isn't completed */
_L4:
        if(i1 < 26)
        {
            char c5 = (char)(i1 + 65);
            j1 = l;
            l = k;
            c = c5;
        } else
        if(i1 == 26)
        {
            j1 = l;
            l = k;
            c = ' ';
        } else
        if(i1 == 27)
        {
            j1 = 1;
            l = k;
            c = '\0';
        } else
        if(i1 == 28)
        {
            j1 = 2;
            l = k;
            c = '\0';
        } else
        {
            if(i1 != 29)
                continue; /* Loop/switch isn't completed */
            j1 = 4;
            c = '\0';
        }
          goto _L9
        if(i1 != 913) goto _L3; else goto _L10
_L10:
        stringbuffer.append((char)ai1[j]);
        j1 = l;
        l = k;
        c = '\0';
          goto _L9
_L5:
        if(i1 < 26)
        {
            char c4 = (char)(i1 + 97);
            j1 = l;
            l = k;
            c = c4;
        } else
        if(i1 == 26)
        {
            j1 = l;
            l = k;
            c = ' ';
        } else
        if(i1 == 28)
        {
            l = k;
            j1 = 0;
            c = '\0';
        } else
        if(i1 == 28)
        {
            j1 = 2;
            l = k;
            c = '\0';
        } else
        {
            if(i1 != 29)
                continue; /* Loop/switch isn't completed */
            j1 = 4;
            c = '\0';
        }
          goto _L9
        if(i1 != 913) goto _L3; else goto _L11
_L11:
        stringbuffer.append((char)ai1[j]);
        j1 = l;
        l = k;
        c = '\0';
          goto _L9
_L6:
        if(i1 < 25)
        {
            char c3 = MIXED_CHARS[i1];
            j1 = l;
            l = k;
            c = c3;
        } else
        if(i1 == 25)
        {
            j1 = 3;
            l = k;
            c = '\0';
        } else
        if(i1 == 26)
        {
            j1 = l;
            l = k;
            c = ' ';
        } else
        if(i1 == 27)
        {
            j1 = l;
            l = k;
            c = '\0';
        } else
        if(i1 == 28)
        {
            l = k;
            j1 = 0;
            c = '\0';
        } else
        {
            if(i1 != 29)
                continue; /* Loop/switch isn't completed */
            j1 = 4;
            c = '\0';
        }
          goto _L9
        if(i1 != 913) goto _L3; else goto _L12
_L12:
        stringbuffer.append((char)ai1[j]);
        j1 = l;
        l = k;
        c = '\0';
          goto _L9
_L7:
        if(i1 < 29)
        {
            char c2 = PUNCT_CHARS[i1];
            j1 = l;
            l = k;
            c = c2;
        } else
        {
            if(i1 != 29)
                continue; /* Loop/switch isn't completed */
            l = k;
            j1 = 0;
            c = '\0';
        }
          goto _L9
        if(i1 != 913) goto _L3; else goto _L13
_L13:
        stringbuffer.append((char)ai1[j]);
        j1 = l;
        l = k;
        c = '\0';
          goto _L9
_L8:
        if(i1 < 29)
        {
            char c1 = PUNCT_CHARS[i1];
            j1 = k;
            int k1 = k;
            c = c1;
            l = k1;
        } else
        if(i1 == 29)
        {
            l = k;
            j1 = 0;
            c = '\0';
        } else
        {
            l = k;
            j1 = k;
            c = '\0';
        }
          goto _L9
_L2:
        return;
        if(true) goto _L15; else goto _L14
_L14:
    }

    private static StringBuffer multiply(String s, int i)
    {
        StringBuffer stringbuffer = new StringBuffer(s.length());
        for(int j = 0; j < s.length(); j++)
            stringbuffer.append('0');

        int k = i / 100;
        int l = (i / 10) % 10;
        int i1 = i % 10;
        StringBuffer stringbuffer1 = stringbuffer;
        for(int j1 = 0; j1 < i1; j1++)
            stringbuffer1 = add(stringbuffer1.toString(), s);

        int k1 = 0;
        StringBuffer stringbuffer2 = stringbuffer1;
        for(; k1 < l; k1++)
            stringbuffer2 = add(stringbuffer2.toString(), (s + '0').substring(1));

        int l1 = 0;
        StringBuffer stringbuffer3 = stringbuffer2;
        for(; l1 < k; l1++)
            stringbuffer3 = add(stringbuffer3.toString(), (s + "00").substring(2));

        return stringbuffer3;
    }

    private static int numericCompaction(int ai[], int i, StringBuffer stringbuffer)
    {
        int ai1[] = new int[15];
        boolean flag = false;
        int j = 0;
        int k = i;
        while(k < ai[0] && !flag) 
        {
            int l = k + 1;
            int i1 = ai[k];
            if(l == ai[0])
                flag = true;
            if(i1 < 900)
            {
                ai1[j] = i1;
                j++;
            } else
            if(i1 == 900 || i1 == 901 || i1 == 924 || i1 == 928 || i1 == 923 || i1 == 922)
            {
                l--;
                flag = true;
            }
            if(j % 15 == 0 || i1 == 902 || flag)
            {
                stringbuffer.append(decodeBase900toBase10(ai1, j));
                j = 0;
            }
            k = l;
        }
        return k;
    }

    private static int textCompaction(int ai[], int i, StringBuffer stringbuffer)
    {
        int ai1[] = new int[ai[0] << 1];
        int ai2[] = new int[ai[0] << 1];
        boolean flag = false;
        int j = 0;
label0:
        do
        {
            int k;
            for(k = i; k < ai[0] && !flag;)
            {
                int l = k + 1;
                int i1 = ai[k];
                if(i1 < 900)
                {
                    ai1[j] = i1 / 30;
                    ai1[j + 1] = i1 % 30;
                    j += 2;
                    k = l;
                } else
                {
                    switch(i1)
                    {
                    default:
                        k = l;
                        break;

                    case 900: 
                        k = l - 1;
                        flag = true;
                        break;

                    case 901: 
                        k = l - 1;
                        flag = true;
                        break;

                    case 902: 
                        k = l - 1;
                        flag = true;
                        break;

                    case 913: 
                        ai1[j] = 913;
                        ai2[j] = i1;
                        j++;
                        k = l;
                        break;

                    case 924: 
                        k = l - 1;
                        flag = true;
                        break;
                    }
                    continue label0;
                }
            }

            decodeTextCompaction(ai1, ai2, j, stringbuffer);
            return k;
        } while(true);
    }

    private static final int AL = 28;
    private static final int ALPHA = 0;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final String EXP900[];
    private static final int LL = 27;
    private static final int LOWER = 1;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int MIXED = 2;
    private static final char MIXED_CHARS[];
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int PUNCT = 3;
    private static final char PUNCT_CHARS[];
    private static final int PUNCT_SHIFT = 4;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    static 
    {
        char ac[] = new char[29];
        ac[0] = ';';
        ac[1] = '<';
        ac[2] = '>';
        ac[3] = '@';
        ac[4] = '[';
        ac[5] = '\\';
        ac[6] = '}';
        ac[7] = '_';
        ac[8] = '`';
        ac[9] = '~';
        ac[10] = '!';
        ac[11] = '\r';
        ac[12] = '\t';
        ac[13] = ',';
        ac[14] = ':';
        ac[15] = '\n';
        ac[16] = '-';
        ac[17] = '.';
        ac[18] = '$';
        ac[19] = '/';
        ac[20] = '"';
        ac[21] = '|';
        ac[22] = '*';
        ac[23] = '(';
        ac[24] = ')';
        ac[25] = '?';
        ac[26] = '{';
        ac[27] = '}';
        ac[28] = '\'';
        PUNCT_CHARS = ac;
        char ac1[] = new char[25];
        ac1[0] = '0';
        ac1[1] = '1';
        ac1[2] = '2';
        ac1[3] = '3';
        ac1[4] = '4';
        ac1[5] = '5';
        ac1[6] = '6';
        ac1[7] = '7';
        ac1[8] = '8';
        ac1[9] = '9';
        ac1[10] = '&';
        ac1[11] = '\r';
        ac1[12] = '\t';
        ac1[13] = ',';
        ac1[14] = ':';
        ac1[15] = '#';
        ac1[16] = '-';
        ac1[17] = '.';
        ac1[18] = '$';
        ac1[19] = '/';
        ac1[20] = '+';
        ac1[21] = '%';
        ac1[22] = '*';
        ac1[23] = '=';
        ac1[24] = '^';
        MIXED_CHARS = ac1;
        String as[] = new String[16];
        as[0] = "000000000000000000000000000000000000000000001";
        as[1] = "000000000000000000000000000000000000000000900";
        as[2] = "000000000000000000000000000000000000000810000";
        as[3] = "000000000000000000000000000000000000729000000";
        as[4] = "000000000000000000000000000000000656100000000";
        as[5] = "000000000000000000000000000000590490000000000";
        as[6] = "000000000000000000000000000531441000000000000";
        as[7] = "000000000000000000000000478296900000000000000";
        as[8] = "000000000000000000000430467210000000000000000";
        as[9] = "000000000000000000387420489000000000000000000";
        as[10] = "000000000000000348678440100000000000000000000";
        as[11] = "000000000000313810596090000000000000000000000";
        as[12] = "000000000282429536481000000000000000000000000";
        as[13] = "000000254186582832900000000000000000000000000";
        as[14] = "000228767924549610000000000000000000000000000";
        as[15] = "205891132094649000000000000000000000000000000";
        EXP900 = as;
    }
}
