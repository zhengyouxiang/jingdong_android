// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import java.util.Enumeration;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing:
//            ResultPoint, ResultMetadataType, BarcodeFormat

public final class Result
{

    public Result(String s, byte abyte0[], ResultPoint aresultpoint[], BarcodeFormat barcodeformat)
    {
        this(s, abyte0, aresultpoint, barcodeformat, System.currentTimeMillis());
    }

    public Result(String s, byte abyte0[], ResultPoint aresultpoint[], BarcodeFormat barcodeformat, long l)
    {
        if(s == null && abyte0 == null)
        {
            throw new IllegalArgumentException("Text and bytes are null");
        } else
        {
            text = s;
            rawBytes = abyte0;
            resultPoints = aresultpoint;
            format = barcodeformat;
            resultMetadata = null;
            timestamp = l;
            return;
        }
    }

    public void addResultPoints(ResultPoint aresultpoint[])
    {
        if(resultPoints != null) goto _L2; else goto _L1
_L1:
        resultPoints = aresultpoint;
_L4:
        return;
_L2:
        if(aresultpoint != null && aresultpoint.length > 0)
        {
            ResultPoint aresultpoint1[] = new ResultPoint[resultPoints.length + aresultpoint.length];
            System.arraycopy(resultPoints, 0, aresultpoint1, 0, resultPoints.length);
            System.arraycopy(aresultpoint, 0, aresultpoint1, resultPoints.length, aresultpoint.length);
            resultPoints = aresultpoint1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public BarcodeFormat getBarcodeFormat()
    {
        return format;
    }

    public byte[] getRawBytes()
    {
        return rawBytes;
    }

    public Hashtable getResultMetadata()
    {
        return resultMetadata;
    }

    public ResultPoint[] getResultPoints()
    {
        return resultPoints;
    }

    public String getText()
    {
        return text;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void putAllMetadata(Hashtable hashtable)
    {
        if(hashtable != null)
            if(resultMetadata == null)
            {
                resultMetadata = hashtable;
            } else
            {
                Enumeration enumeration = hashtable.keys();
                while(enumeration.hasMoreElements()) 
                {
                    ResultMetadataType resultmetadatatype = (ResultMetadataType)enumeration.nextElement();
                    Object obj = hashtable.get(resultmetadatatype);
                    resultMetadata.put(resultmetadatatype, obj);
                }
            }
    }

    public void putMetadata(ResultMetadataType resultmetadatatype, Object obj)
    {
        if(resultMetadata == null)
            resultMetadata = new Hashtable(3);
        resultMetadata.put(resultmetadatatype, obj);
    }

    public String toString()
    {
        String s;
        if(text == null)
            s = "[" + rawBytes.length + " bytes]";
        else
            s = text;
        return s;
    }

    private final BarcodeFormat format;
    private final byte rawBytes[];
    private Hashtable resultMetadata;
    private ResultPoint resultPoints[];
    private final String text;
    private final long timestamp;
}
