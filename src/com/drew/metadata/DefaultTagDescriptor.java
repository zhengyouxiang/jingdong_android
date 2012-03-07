// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;


// Referenced classes of package com.drew.metadata:
//            TagDescriptor, Directory

public class DefaultTagDescriptor extends TagDescriptor
{

    public DefaultTagDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
    {
        return _directory.getString(i);
    }

    public String getTagName(int i)
    {
        String s = Integer.toHexString(i).toUpperCase();
        do
        {
            if(s.length() >= 4)
                return (new StringBuilder("Unknown tag 0x")).append(s).toString();
            s = (new StringBuilder("0")).append(s).toString();
        } while(true);
    }
}
