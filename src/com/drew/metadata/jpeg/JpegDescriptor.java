// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.metadata.*;

// Referenced classes of package com.drew.metadata.jpeg:
//            JpegDirectory, JpegComponent

public class JpegDescriptor extends TagDescriptor
{

    public JpegDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getComponentDataDescription(int i)
        throws MetadataException
    {
        JpegComponent jpegcomponent = ((JpegDirectory)_directory).getComponent(i);
        if(jpegcomponent == null)
        {
            throw new MetadataException((new StringBuilder("No Jpeg component exists with number ")).append(i).toString());
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(jpegcomponent.getComponentName());
            stringbuffer.append(" component: Quantization table ");
            stringbuffer.append(jpegcomponent.getQuantizationTableNumber());
            stringbuffer.append(", Sampling factors ");
            stringbuffer.append(jpegcomponent.getHorizontalSamplingFactor());
            stringbuffer.append(" horiz/");
            stringbuffer.append(jpegcomponent.getVerticalSamplingFactor());
            stringbuffer.append(" vert");
            return stringbuffer.toString();
        }
    }

    public String getDataPrecisionDescription()
    {
        return (new StringBuilder(String.valueOf(_directory.getString(0)))).append(" bits").toString();
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 0 9: default 56
    //                   0 103
    //                   1 111
    //                   2 56
    //                   3 119
    //                   4 56
    //                   5 56
    //                   6 67
    //                   7 76
    //                   8 85
    //                   9 94;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L5 _L6 _L7 _L8
_L1:
        String s = _directory.getString(i);
_L10:
        return s;
_L5:
        s = getComponentDataDescription(0);
        continue; /* Loop/switch isn't completed */
_L6:
        s = getComponentDataDescription(1);
        continue; /* Loop/switch isn't completed */
_L7:
        s = getComponentDataDescription(2);
        continue; /* Loop/switch isn't completed */
_L8:
        s = getComponentDataDescription(3);
        continue; /* Loop/switch isn't completed */
_L2:
        s = getDataPrecisionDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getImageHeightDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getImageWidthDescription();
        if(true) goto _L10; else goto _L9
_L9:
    }

    public String getImageHeightDescription()
    {
        return (new StringBuilder(String.valueOf(_directory.getString(1)))).append(" pixels").toString();
    }

    public String getImageWidthDescription()
    {
        return (new StringBuilder(String.valueOf(_directory.getString(3)))).append(" pixels").toString();
    }
}
