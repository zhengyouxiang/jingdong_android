// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.lang;

import java.io.PrintStream;
import java.io.PrintWriter;

public class CompoundException extends Exception
{

    public CompoundException(String s)
    {
        this(s, null);
    }

    public CompoundException(String s, Throwable throwable)
    {
        super(s);
        _innnerException = throwable;
    }

    public CompoundException(Throwable throwable)
    {
        this(null, throwable);
    }

    public Throwable getInnerException()
    {
        return _innnerException;
    }

    public void printStackTrace()
    {
        super.printStackTrace();
        if(_innnerException != null)
        {
            System.err.println("--- inner exception ---");
            _innnerException.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream printstream)
    {
        super.printStackTrace(printstream);
        if(_innnerException != null)
        {
            printstream.println("--- inner exception ---");
            _innnerException.printStackTrace(printstream);
        }
    }

    public void printStackTrace(PrintWriter printwriter)
    {
        super.printStackTrace(printwriter);
        if(_innnerException != null)
        {
            printwriter.println("--- inner exception ---");
            _innnerException.printStackTrace(printwriter);
        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(super.toString());
        if(_innnerException != null)
        {
            stringbuffer.append("\n");
            stringbuffer.append("--- inner exception ---");
            stringbuffer.append("\n");
            stringbuffer.append(_innnerException.toString());
        }
        return stringbuffer.toString();
    }

    private final Throwable _innnerException;
}
