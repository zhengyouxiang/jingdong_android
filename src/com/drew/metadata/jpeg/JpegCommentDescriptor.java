// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;

public class JpegCommentDescriptor extends TagDescriptor
{

    public JpegCommentDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
    {
        return _directory.getString(i);
    }
}
