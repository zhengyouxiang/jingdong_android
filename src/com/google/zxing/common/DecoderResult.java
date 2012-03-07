// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Vector;

public final class DecoderResult
{

    public DecoderResult(byte abyte0[], String s, Vector vector, ErrorCorrectionLevel errorcorrectionlevel)
    {
        if(abyte0 == null && s == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            rawBytes = abyte0;
            text = s;
            byteSegments = vector;
            ecLevel = errorcorrectionlevel;
            return;
        }
    }

    public Vector getByteSegments()
    {
        return byteSegments;
    }

    public ErrorCorrectionLevel getECLevel()
    {
        return ecLevel;
    }

    public byte[] getRawBytes()
    {
        return rawBytes;
    }

    public String getText()
    {
        return text;
    }

    private final Vector byteSegments;
    private final ErrorCorrectionLevel ecLevel;
    private final byte rawBytes[];
    private final String text;
}
