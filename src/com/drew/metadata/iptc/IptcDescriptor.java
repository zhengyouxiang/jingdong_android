// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.iptc;

import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;

public class IptcDescriptor extends TagDescriptor
{

    public IptcDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
    {
        return _directory.getString(i);
    }
}
