// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.RSS14Reader;
import com.google.zxing.oned.rss.expanded.RSSExpandedReader;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.oned:
//            OneDReader, MultiFormatUPCEANReader, Code39Reader, Code93Reader, 
//            Code128Reader, ITFReader, CodaBarReader

public final class MultiFormatOneDReader extends OneDReader
{

    public MultiFormatOneDReader(Hashtable hashtable)
    {
        Vector vector;
        boolean flag;
        if(hashtable == null)
            vector = null;
        else
            vector = (Vector)hashtable.get(DecodeHintType.POSSIBLE_FORMATS);
        if(hashtable != null && hashtable.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT) != null)
            flag = true;
        else
            flag = false;
        if(vector != null)
        {
            if(vector.contains(BarcodeFormat.EAN_13) || vector.contains(BarcodeFormat.UPC_A) || vector.contains(BarcodeFormat.EAN_8) || vector.contains(BarcodeFormat.UPC_E))
                readers.addElement(new MultiFormatUPCEANReader(hashtable));
            if(vector.contains(BarcodeFormat.CODE_39))
                readers.addElement(new Code39Reader(flag));
            if(vector.contains(BarcodeFormat.CODE_93))
                readers.addElement(new Code93Reader());
            if(vector.contains(BarcodeFormat.CODE_128))
                readers.addElement(new Code128Reader());
            if(vector.contains(BarcodeFormat.ITF))
                readers.addElement(new ITFReader());
            if(vector.contains(BarcodeFormat.CODABAR))
                readers.addElement(new CodaBarReader());
            if(vector.contains(BarcodeFormat.RSS14))
                readers.addElement(new RSS14Reader());
            if(vector.contains(BarcodeFormat.RSS_EXPANDED))
                readers.addElement(new RSSExpandedReader());
        }
        if(readers.isEmpty())
        {
            readers.addElement(new MultiFormatUPCEANReader(hashtable));
            readers.addElement(new Code39Reader());
            readers.addElement(new Code93Reader());
            readers.addElement(new Code128Reader());
            readers.addElement(new ITFReader());
            readers.addElement(new RSS14Reader());
            readers.addElement(new RSSExpandedReader());
        }
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException
    {
        int j;
        int k;
        j = readers.size();
        k = 0;
_L2:
        OneDReader onedreader;
        if(k >= j)
            break; /* Loop/switch isn't completed */
        onedreader = (OneDReader)readers.elementAt(k);
        Result result = onedreader.decodeRow(i, bitarray, hashtable);
        return result;
        ReaderException readerexception;
        readerexception;
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset()
    {
        int i = readers.size();
        for(int j = 0; j < i; j++)
            ((Reader)readers.elementAt(j)).reset();

    }

    private final Vector readers = new Vector();
}
