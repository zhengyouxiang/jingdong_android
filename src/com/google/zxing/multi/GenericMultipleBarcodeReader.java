// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.multi:
//            MultipleBarcodeReader

public final class GenericMultipleBarcodeReader
    implements MultipleBarcodeReader
{

    public GenericMultipleBarcodeReader(Reader reader)
    {
        _flddelegate = reader;
    }

    private void doDecodeMultiple(BinaryBitmap binarybitmap, Hashtable hashtable, Vector vector, int i, int j)
    {
        Result result = _flddelegate.decode(binarybitmap, hashtable);
        int k = 0;
_L3:
        if(k >= vector.size())
            break MISSING_BLOCK_LABEL_391;
        if(!((Result)vector.elementAt(k)).getText().equals(result.getText())) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L5:
        if(!flag)
        {
            vector.addElement(translateResultPoints(result, i, j));
            ResultPoint aresultpoint[] = result.getResultPoints();
            if(aresultpoint != null && aresultpoint.length != 0)
            {
                int l = binarybitmap.getWidth();
                int i1 = binarybitmap.getHeight();
                float f = l;
                float f1 = i1;
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = f1;
                float f5 = f;
                int j1 = 0;
                while(j1 < aresultpoint.length) 
                {
                    ResultPoint resultpoint = aresultpoint[j1];
                    float f6 = resultpoint.getX();
                    float f7 = resultpoint.getY();
                    if(f6 < f5)
                        f5 = f6;
                    ReaderException readerexception;
                    float f8;
                    if(f7 < f4)
                        f8 = f7;
                    else
                        f8 = f4;
                    if(f6 <= f3)
                        f6 = f3;
                    if(f7 <= f2)
                        f7 = f2;
                    j1++;
                    f2 = f7;
                    f3 = f6;
                    f4 = f8;
                }
                if(f5 > 100F)
                    doDecodeMultiple(binarybitmap.crop(0, 0, (int)f5, i1), hashtable, vector, i, j);
                if(f4 > 100F)
                    doDecodeMultiple(binarybitmap.crop(0, 0, l, (int)f4), hashtable, vector, i, j);
                if(f3 < (float)(l - 100))
                    doDecodeMultiple(binarybitmap.crop((int)f3, 0, l - (int)f3, i1), hashtable, vector, i + (int)f3, j);
                if(f2 < (float)(i1 - 100))
                    doDecodeMultiple(binarybitmap.crop(0, (int)f2, l, i1 - (int)f2), hashtable, vector, i, j + (int)f2);
            }
        }
_L4:
        return;
_L2:
        k++;
          goto _L3
        readerexception;
          goto _L4
        flag = false;
          goto _L5
    }

    private static Result translateResultPoints(Result result, int i, int j)
    {
        ResultPoint aresultpoint[] = result.getResultPoints();
        ResultPoint aresultpoint1[] = new ResultPoint[aresultpoint.length];
        for(int k = 0; k < aresultpoint.length; k++)
        {
            ResultPoint resultpoint = aresultpoint[k];
            aresultpoint1[k] = new ResultPoint(resultpoint.getX() + (float)i, resultpoint.getY() + (float)j);
        }

        return new Result(result.getText(), result.getRawBytes(), aresultpoint1, result.getBarcodeFormat());
    }

    public Result[] decodeMultiple(BinaryBitmap binarybitmap)
        throws NotFoundException
    {
        return decodeMultiple(binarybitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException
    {
        Vector vector = new Vector();
        doDecodeMultiple(binarybitmap, hashtable, vector, 0, 0);
        if(vector.isEmpty())
            throw NotFoundException.getNotFoundInstance();
        int i = vector.size();
        Result aresult[] = new Result[i];
        for(int j = 0; j < i; j++)
            aresult[j] = (Result)vector.elementAt(j);

        return aresult;
    }

    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader _flddelegate;
}
