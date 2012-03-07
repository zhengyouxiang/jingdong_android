// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;

import java.io.Serializable;

// Referenced classes of package com.drew.metadata:
//            MetadataException, Directory

public abstract class TagDescriptor
    implements Serializable
{

    public TagDescriptor(Directory directory)
    {
        _directory = directory;
    }

    public abstract String getDescription(int i)
        throws MetadataException;

    protected final Directory _directory;
}
