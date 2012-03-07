// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

// Referenced classes of package com.drew.metadata.exif:
//            ExifDescriptor

public class ExifInteropDescriptor extends TagDescriptor
{

    public ExifInteropDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 35
    //                   2 43;
           goto _L1 _L2 _L3
_L1:
        String s = _directory.getString(i);
_L5:
        return s;
_L2:
        s = getInteropIndexDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getInteropVersionDescription();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getInteropIndexDescription()
    {
        String s1;
        if(!_directory.containsTag(1))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(1).trim();
            if("R98".equalsIgnoreCase(s))
                s1 = "Recommended Exif Interoperability Rules (ExifR98)";
            else
                s1 = (new StringBuilder("Unknown (")).append(s).append(")").toString();
        }
        return s1;
    }

    public String getInteropVersionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(2))
            s = null;
        else
            s = ExifDescriptor.convertBytesToVersionString(_directory.getIntArray(2));
        return s;
    }
}
