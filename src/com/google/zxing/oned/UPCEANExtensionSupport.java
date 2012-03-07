// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader

final class UPCEANExtensionSupport
{

    UPCEANExtensionSupport()
    {
    }

    private static int determineCheckDigit(int i)
        throws NotFoundException
    {
        for(int j = 0; j < 10; j++)
            if(i == CHECK_DIGIT_ENCODINGS[j])
                return j;

        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(String s)
    {
        int i = s.length();
        int j = i - 2;
        int k = 0;
        for(int l = j; l >= 0; l -= 2)
            k += s.charAt(l) - 48;

        int i1 = k * 3;
        for(int j1 = i - 1; j1 >= 0; j1 -= 2)
            i1 += s.charAt(j1) - 48;

        return (i1 * 3) % 10;
    }

    private static Integer parseExtension2String(String s)
    {
        return Integer.valueOf(s);
    }

    private static String parseExtension5String(String s)
    {
        String s1 = null;
        s.charAt(0);
        JVM INSTR lookupswitch 3: default 40
    //                   48: 88
    //                   53: 94
    //                   57: 100;
           goto _L1 _L2 _L3 _L4
_L1:
        s1 = "";
_L5:
        String s2;
        int i = Integer.parseInt(s.substring(1));
        s2 = s1 + i / 100 + '.' + i % 100;
_L6:
        return s2;
_L2:
        s1 = "\u62E2";
          goto _L5
_L3:
        s1 = "$";
          goto _L5
_L4:
        if(!"99991".equals(s))
            continue; /* Loop/switch isn't completed */
        s2 = "0.00";
          goto _L6
        if(!"99990".equals(s)) goto _L5; else goto _L7
_L7:
        s2 = "Used";
          goto _L6
    }

    private static Hashtable parseExtensionString(String s)
    {
        s.length();
        JVM INSTR tableswitch 2 5: default 36
    //                   2 42
    //                   3 36
    //                   4 36
    //                   5 71;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        Hashtable hashtable1 = null;
_L4:
        return hashtable1;
_L2:
        ResultMetadataType resultmetadatatype1;
        Object obj;
        ResultMetadataType resultmetadatatype2 = ResultMetadataType.ISSUE_NUMBER;
        Integer integer = parseExtension2String(s);
        resultmetadatatype1 = resultmetadatatype2;
        obj = integer;
_L5:
        if(obj == null)
        {
            hashtable1 = null;
        } else
        {
            Hashtable hashtable = new Hashtable(1);
            hashtable.put(resultmetadatatype1, obj);
            hashtable1 = hashtable;
        }
        if(true) goto _L4; else goto _L3
_L3:
        ResultMetadataType resultmetadatatype = ResultMetadataType.SUGGESTED_PRICE;
        String s1 = parseExtension5String(s);
        resultmetadatatype1 = resultmetadatatype;
        obj = s1;
          goto _L5
    }

    int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException
    {
        int ai1[] = decodeMiddleCounters;
        ai1[0] = 0;
        ai1[1] = 0;
        ai1[2] = 0;
        ai1[3] = 0;
        int i = bitarray.getSize();
        int j = ai[1];
        int k = 0;
        int l = j;
        for(int i1 = 0; i1 < 5 && l < i; i1++)
        {
            int k1 = UPCEANReader.decodeDigit(bitarray, ai1, l, UPCEANReader.L_AND_G_PATTERNS);
            stringbuffer.append((char)(48 + k1 % 10));
            int l1 = l;
            for(int i2 = 0; i2 < ai1.length; i2++)
                l1 += ai1[i2];

            if(k1 >= 10)
                k |= 1 << 4 - i1;
            if(i1 != 4)
            {
                for(l = l1; l < i && !bitarray.get(l); l++);
                for(; l < i && bitarray.get(l); l++);
            } else
            {
                l = l1;
            }
        }

        if(stringbuffer.length() != 5)
            throw NotFoundException.getNotFoundInstance();
        int j1 = determineCheckDigit(k);
        if(extensionChecksum(stringbuffer.toString()) != j1)
            throw NotFoundException.getNotFoundInstance();
        else
            return l;
    }

    Result decodeRow(int i, BitArray bitarray, int j)
        throws NotFoundException
    {
        int ai[] = UPCEANReader.findGuardPattern(bitarray, j, false, EXTENSION_START_PATTERN);
        StringBuffer stringbuffer = decodeRowStringBuffer;
        stringbuffer.setLength(0);
        int k = decodeMiddle(bitarray, ai, stringbuffer);
        String s = stringbuffer.toString();
        Hashtable hashtable = parseExtensionString(s);
        ResultPoint aresultpoint[] = new ResultPoint[2];
        aresultpoint[0] = new ResultPoint((float)(ai[0] + ai[1]) / 2.0F, i);
        aresultpoint[1] = new ResultPoint(k, i);
        Result result = new Result(s, null, aresultpoint, BarcodeFormat.UPC_EAN_EXTENSION);
        if(hashtable != null)
            result.putAllMetadata(hashtable);
        return result;
    }

    private static final int CHECK_DIGIT_ENCODINGS[];
    private static final int EXTENSION_START_PATTERN[];
    private final int decodeMiddleCounters[] = new int[4];
    private final StringBuffer decodeRowStringBuffer = new StringBuffer();

    static 
    {
        int ai[] = new int[3];
        ai[0] = 1;
        ai[1] = 1;
        ai[2] = 2;
        EXTENSION_START_PATTERN = ai;
        int ai1[] = new int[10];
        ai1[0] = 24;
        ai1[1] = 20;
        ai1[2] = 18;
        ai1[3] = 17;
        ai1[4] = 12;
        ai1[5] = 6;
        ai1[6] = 3;
        ai1[7] = 10;
        ai1[8] = 9;
        ai1[9] = 5;
        CHECK_DIGIT_ENCODINGS = ai1;
    }
}
