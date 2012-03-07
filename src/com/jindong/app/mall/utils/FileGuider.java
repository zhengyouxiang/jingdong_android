// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;


public class FileGuider
{

    public FileGuider()
    {
    }

    public long getAvailableSize()
    {
        return AvailableSize;
    }

    public String getChildDirName()
    {
        return childDirName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public int getInternalType()
    {
        return internalType;
    }

    public int getMode()
    {
        return mode;
    }

    public int getSpace()
    {
        return space;
    }

    public long getTotalSize()
    {
        return TotalSize;
    }

    public boolean isImmutable()
    {
        return immutable;
    }

    public void setAvailableSize(long l)
    {
        AvailableSize = l;
    }

    public void setChildDirName(String s)
    {
        childDirName = s;
    }

    public void setFileName(String s)
    {
        fileName = s;
    }

    public void setImmutable(boolean flag)
    {
        immutable = flag;
    }

    public void setInternalType(int i)
    {
        internalType = i;
    }

    public void setMode(int i)
    {
        mode = i;
    }

    public void setSpace(int i)
    {
        space = i;
    }

    public void setTotalSize(long l)
    {
        TotalSize = l;
    }

    private long AvailableSize;
    private long TotalSize;
    private String childDirName;
    private String fileName;
    private boolean immutable;
    private int internalType;
    private int mode;
    private int space;
}
