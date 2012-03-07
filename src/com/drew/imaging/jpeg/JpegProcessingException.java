// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.imaging.jpeg;

import com.drew.lang.CompoundException;

public class JpegProcessingException extends CompoundException
{

    public JpegProcessingException(String s)
    {
        super(s);
    }

    public JpegProcessingException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public JpegProcessingException(Throwable throwable)
    {
        super(throwable);
    }
}
