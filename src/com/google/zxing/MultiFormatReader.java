// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing:
//            Reader, NotFoundException, ReaderException, DecodeHintType, 
//            BarcodeFormat, BinaryBitmap, Result

public final class MultiFormatReader
    implements Reader
{

    public MultiFormatReader()
    {
    }

    private Result decodeInternal(BinaryBitmap binarybitmap)
        throws NotFoundException
    {
        int i;
        int j;
        i = readers.size();
        j = 0;
_L2:
        Reader reader;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        reader = (Reader)readers.elementAt(j);
        Result result = reader.decode(binarybitmap, hints);
        return result;
        ReaderException readerexception;
        readerexception;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException
    {
        setHints(null);
        return decodeInternal(binarybitmap);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException
    {
        setHints(hashtable);
        return decodeInternal(binarybitmap);
    }

    public Result decodeWithState(BinaryBitmap binarybitmap)
        throws NotFoundException
    {
        if(readers == null)
            setHints(null);
        return decodeInternal(binarybitmap);
    }

    public void reset()
    {
        int i = readers.size();
        for(int j = 0; j < i; j++)
            ((Reader)readers.elementAt(j)).reset();

    }

    public void setHints(Hashtable hashtable)
    {
        hints = hashtable;
        boolean flag;
        Vector vector;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER))
            flag = true;
        else
            flag = false;
        if(hashtable == null)
            vector = null;
        else
            vector = (Vector)hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        readers = new Vector();
        if(vector != null)
        {
            boolean flag1;
            if(vector.contains(BarcodeFormat.UPC_A) || vector.contains(BarcodeFormat.UPC_E) || vector.contains(BarcodeFormat.EAN_13) || vector.contains(BarcodeFormat.EAN_8) || vector.contains(BarcodeFormat.CODE_39) || vector.contains(BarcodeFormat.CODE_93) || vector.contains(BarcodeFormat.CODE_128) || vector.contains(BarcodeFormat.ITF) || vector.contains(BarcodeFormat.RSS14) || vector.contains(BarcodeFormat.RSS_EXPANDED))
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && !flag)
                readers.addElement(new MultiFormatOneDReader(hashtable));
            if(vector.contains(BarcodeFormat.QR_CODE))
                readers.addElement(new QRCodeReader());
            if(vector.contains(BarcodeFormat.DATA_MATRIX))
                readers.addElement(new DataMatrixReader());
            if(vector.contains(BarcodeFormat.PDF417))
                readers.addElement(new PDF417Reader());
            if(flag1 && flag)
                readers.addElement(new MultiFormatOneDReader(hashtable));
        }
        if(readers.isEmpty())
        {
            if(!flag)
                readers.addElement(new MultiFormatOneDReader(hashtable));
            readers.addElement(new QRCodeReader());
            readers.addElement(new DataMatrixReader());
            if(flag)
                readers.addElement(new MultiFormatOneDReader(hashtable));
        }
    }

    private Hashtable hints;
    private Vector readers;
}
