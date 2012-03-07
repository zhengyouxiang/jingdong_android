// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class KodakMakernoteDescriptor extends TagDescriptor
{

    public KodakMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
        throws MetadataException
    {
        return _directory.getString(i);
    }
}
