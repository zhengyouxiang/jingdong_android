// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.metadata.MetadataException;
import java.io.Serializable;

public class JpegComponent
    implements Serializable
{

    public JpegComponent(int i, int j, int k)
    {
        _componentId = i;
        _samplingFactorByte = j;
        _quantizationTableNumber = k;
    }

    public int getComponentId()
    {
        return _componentId;
    }

    public String getComponentName()
        throws MetadataException
    {
        _componentId;
        JVM INSTR tableswitch 1 5: default 40
    //                   1 67
    //                   2 72
    //                   3 78
    //                   4 84
    //                   5 90;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        throw new MetadataException((new StringBuilder("Unsupported component id: ")).append(_componentId).toString());
_L2:
        String s = "Y";
_L8:
        return s;
_L3:
        s = "Cb";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "Cr";
        continue; /* Loop/switch isn't completed */
_L5:
        s = "I";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "Q";
        if(true) goto _L8; else goto _L7
_L7:
    }

    public int getHorizontalSamplingFactor()
    {
        return 0xf & _samplingFactorByte;
    }

    public int getQuantizationTableNumber()
    {
        return _quantizationTableNumber;
    }

    public int getVerticalSamplingFactor()
    {
        return 0xf & _samplingFactorByte >> 4;
    }

    private final int _componentId;
    private final int _quantizationTableNumber;
    private final int _samplingFactorByte;
}
