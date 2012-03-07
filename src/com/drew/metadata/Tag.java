// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;

import java.io.Serializable;

// Referenced classes of package com.drew.metadata:
//            MetadataException, Directory

public class Tag
    implements Serializable
{

    public Tag(int i, Directory directory)
    {
        _tagType = i;
        _directory = directory;
    }

    public String getDescription()
        throws MetadataException
    {
        return _directory.getDescription(_tagType);
    }

    public String getDirectoryName()
    {
        return _directory.getName();
    }

    public String getTagName()
    {
        return _directory.getTagName(_tagType);
    }

    public int getTagType()
    {
        return _tagType;
    }

    public String getTagTypeHex()
    {
        String s = Integer.toHexString(_tagType);
        do
        {
            if(s.length() >= 4)
                return (new StringBuilder("0x")).append(s).toString();
            s = (new StringBuilder("0")).append(s).toString();
        } while(true);
    }

    public String toString()
    {
        String s1 = getDescription();
        String s = s1;
_L2:
        return (new StringBuilder("[")).append(_directory.getName()).append("] ").append(getTagName()).append(" - ").append(s).toString();
        MetadataException metadataexception;
        metadataexception;
        s = (new StringBuilder(String.valueOf(_directory.getString(getTagType())))).append(" (unable to formulate description)").toString();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private final Directory _directory;
    private final int _tagType;
}
