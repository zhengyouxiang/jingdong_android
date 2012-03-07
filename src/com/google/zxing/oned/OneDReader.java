// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class OneDReader
    implements Reader
{

    public OneDReader()
    {
    }

    private Result doDecode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException
    {
        int i;
        Hashtable hashtable1;
        int j1;
        BitArray bitarray1;
        int l1;
        i = binarybitmap.getWidth();
        int j = binarybitmap.getHeight();
        BitArray bitarray = new BitArray(i);
        int k = j >> 1;
        boolean flag;
        byte byte0;
        int l;
        int i1;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        if(flag)
            byte0 = 8;
        else
            byte0 = 5;
        l = Math.max(1, j >> byte0);
        if(flag)
            i1 = j;
        else
            i1 = 15;
        hashtable1 = hashtable;
        j1 = 0;
        bitarray1 = bitarray;
_L4:
label0:
        {
            if(j1 < i1)
            {
                int k1 = j1 + 1 >> 1;
                boolean flag1;
                if((j1 & 1) == 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(!flag1)
                    k1 = -k1;
                l1 = k + k1 * l;
                if(l1 >= 0 && l1 < j)
                    break label0;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        BitArray bitarray2 = binarybitmap.getBlackRow(l1, bitarray1);
        Hashtable hashtable2;
        int i2;
        bitarray1 = bitarray2;
        hashtable2 = hashtable1;
        i2 = 0;
_L2:
        if(i2 >= 2)
            break; /* Loop/switch isn't completed */
        if(i2 == 1)
        {
            bitarray1.reverse();
            if(hashtable2 != null && hashtable2.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
            {
                Hashtable hashtable3 = new Hashtable();
                Enumeration enumeration = hashtable2.keys();
                do
                {
                    if(!enumeration.hasMoreElements())
                        break;
                    Object obj = enumeration.nextElement();
                    if(!obj.equals(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
                        hashtable3.put(obj, hashtable2.get(obj));
                } while(true);
                hashtable2 = hashtable3;
            }
        }
        Result result;
        result = decodeRow(l1, bitarray1, hashtable2);
        if(i2 == 1)
        {
            result.putMetadata(ResultMetadataType.ORIENTATION, new Integer(180));
            ResultPoint aresultpoint[] = result.getResultPoints();
            aresultpoint[0] = new ResultPoint((float)i - aresultpoint[0].getX() - 1.0F, aresultpoint[0].getY());
            aresultpoint[1] = new ResultPoint((float)i - aresultpoint[1].getX() - 1.0F, aresultpoint[1].getY());
        }
        return result;
        ReaderException readerexception;
        readerexception;
        i2++;
        if(true) goto _L2; else goto _L1
_L1:
        NotFoundException notfoundexception;
        break; /* Loop/switch isn't completed */
        notfoundexception;
_L5:
        j1++;
        if(true) goto _L4; else goto _L3
_L3:
        hashtable1 = hashtable2;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    protected static int patternMatchVariance(int ai[], int ai1[], int i)
    {
        int j;
        int k;
        int i1;
        int j1;
        j = 0;
        k = ai.length;
        int l = 0;
        i1 = 0;
        j1 = 0;
        for(; l < k; l++)
        {
            j1 += ai[l];
            i1 += ai1[l];
        }

        if(j1 >= i1) goto _L2; else goto _L1
_L1:
        int j2 = 0x7fffffff;
_L4:
        return j2;
_L2:
        int k1 = (j1 << 8) / i1;
        int l1 = i * k1 >> 8;
        int i2 = 0;
        do
        {
            if(i2 >= k)
                break;
            int k2 = ai[i2] << 8;
            int l2 = k1 * ai1[i2];
            int i3;
            if(k2 > l2)
                i3 = k2 - l2;
            else
                i3 = l2 - k2;
            if(i3 > l1)
            {
                j2 = 0x7fffffff;
                continue; /* Loop/switch isn't completed */
            }
            j += i3;
            i2++;
        } while(true);
        j2 = j / j1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static void recordPattern(BitArray bitarray, int i, int ai[])
        throws NotFoundException
    {
        int j;
        int l;
        int i1;
        boolean flag1;
        int j1;
        j = ai.length;
        for(int k = 0; k < j; k++)
            ai[k] = 0;

        l = bitarray.getSize();
        if(i >= l)
            throw NotFoundException.getNotFoundInstance();
        boolean flag;
        if(!bitarray.get(i))
            flag = true;
        else
            flag = false;
        i1 = 0;
        flag1 = flag;
        j1 = i;
        if(j1 >= l) goto _L2; else goto _L1
_L1:
        if(!(flag1 ^ bitarray.get(j1))) goto _L4; else goto _L3
_L3:
        ai[i1] = 1 + ai[i1];
_L6:
        j1++;
        break MISSING_BLOCK_LABEL_60;
_L4:
        if(++i1 != j)
            break MISSING_BLOCK_LABEL_135;
_L2:
        if(i1 != j && (i1 != j - 1 || j1 != l))
            throw NotFoundException.getNotFoundInstance();
        else
            return;
        ai[i1] = 1;
        if(!flag1)
            flag1 = true;
        else
            flag1 = false;
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected static void recordPatternInReverse(BitArray bitarray, int i, int ai[])
        throws NotFoundException
    {
        int j = ai.length;
        boolean flag = bitarray.get(i);
        int k = i;
        boolean flag1 = flag;
        int l = j;
        do
        {
            if(k <= 0 || l < 0)
                break;
            k--;
            if(bitarray.get(k) != flag1)
            {
                l--;
                if(!flag1)
                    flag1 = true;
                else
                    flag1 = false;
            }
        } while(true);
        if(l >= 0)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            recordPattern(bitarray, k + 1, ai);
            return;
        }
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, FormatException
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, FormatException
    {
        Result result2 = doDecode(binarybitmap, hashtable);
        Result result1 = result2;
_L2:
        return result1;
        NotFoundException notfoundexception;
        notfoundexception;
        boolean flag;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        if(flag && binarybitmap.isRotateSupported())
        {
            BinaryBitmap binarybitmap1 = binarybitmap.rotateCounterClockwise();
            Result result = doDecode(binarybitmap1, hashtable);
            Hashtable hashtable1 = result.getResultMetadata();
            int i;
            ResultPoint aresultpoint[];
            int j;
            if(hashtable1 != null && hashtable1.containsKey(ResultMetadataType.ORIENTATION))
                i = (270 + ((Integer)hashtable1.get(ResultMetadataType.ORIENTATION)).intValue()) % 360;
            else
                i = 270;
            result.putMetadata(ResultMetadataType.ORIENTATION, new Integer(i));
            aresultpoint = result.getResultPoints();
            j = binarybitmap1.getHeight();
            for(int k = 0; k < aresultpoint.length; k++)
                aresultpoint[k] = new ResultPoint((float)j - aresultpoint[k].getY() - 1.0F, aresultpoint[k].getX());

            result1 = result;
        } else
        {
            throw notfoundexception;
        }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public abstract Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException;

    public void reset()
    {
    }

    protected static final int INTEGER_MATH_SHIFT = 8;
    protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;
}
