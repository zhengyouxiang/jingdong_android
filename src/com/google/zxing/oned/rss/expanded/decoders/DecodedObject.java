// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;


abstract class DecodedObject
{

    DecodedObject(int i)
    {
        newPosition = i;
    }

    int getNewPosition()
    {
        return newPosition;
    }

    protected final int newPosition;
}
