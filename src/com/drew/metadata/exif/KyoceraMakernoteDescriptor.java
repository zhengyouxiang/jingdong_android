// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class KyoceraMakernoteDescriptor extends TagDescriptor
{

    public KyoceraMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 2: default 28
    //                   1: 47
    //                   3584: 39;
           goto _L1 _L2 _L3
_L1:
        String s = _directory.getString(i);
_L5:
        return s;
_L3:
        s = getPrintImageMatchingInfoDescription();
        continue; /* Loop/switch isn't completed */
_L2:
        s = getProprietaryThumbnailDataDescription();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public String getPrintImageMatchingInfoDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(3584))
        {
            s = null;
        } else
        {
            byte abyte0[] = _directory.getByteArray(3584);
            s = (new StringBuilder("(")).append(abyte0.length).append(" bytes)").toString();
        }
        return s;
    }

    public String getProprietaryThumbnailDataDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(1))
        {
            s = null;
        } else
        {
            byte abyte0[] = _directory.getByteArray(1);
            s = (new StringBuilder("(")).append(abyte0.length).append(" bytes)").toString();
        }
        return s;
    }
}
