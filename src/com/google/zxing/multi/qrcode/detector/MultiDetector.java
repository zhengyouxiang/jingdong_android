// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.multi.qrcode.detector:
//            MultiFinderPatternFinder

public final class MultiDetector extends Detector
{

    public MultiDetector(BitMatrix bitmatrix)
    {
        super(bitmatrix);
    }

    public DetectorResult[] detectMulti(Hashtable hashtable)
        throws NotFoundException
    {
        com.google.zxing.qrcode.detector.FinderPatternInfo afinderpatterninfo[] = (new MultiFinderPatternFinder(getImage())).findMulti(hashtable);
        if(afinderpatterninfo == null || afinderpatterninfo.length == 0)
            throw NotFoundException.getNotFoundInstance();
        Vector vector = new Vector();
        int i = 0;
        while(i < afinderpatterninfo.length) 
        {
            DetectorResult adetectorresult[];
            int j;
            try
            {
                vector.addElement(processFinderPatternInfo(afinderpatterninfo[i]));
            }
            catch(ReaderException readerexception) { }
            i++;
        }
        if(vector.isEmpty())
        {
            adetectorresult = EMPTY_DETECTOR_RESULTS;
        } else
        {
            adetectorresult = new DetectorResult[vector.size()];
            j = 0;
            while(j < vector.size()) 
            {
                adetectorresult[j] = (DetectorResult)vector.elementAt(j);
                j++;
            }
        }
        return adetectorresult;
    }

    private static final DetectorResult EMPTY_DETECTOR_RESULTS[] = new DetectorResult[0];

}
