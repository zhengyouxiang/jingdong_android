// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;

import com.drew.lang.CompoundException;

public class MetadataException extends CompoundException
{

    public MetadataException(String s)
    {
        super(s);
    }

    public MetadataException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public MetadataException(Throwable throwable)
    {
        super(throwable);
    }
}
