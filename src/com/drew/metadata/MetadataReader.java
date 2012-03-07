// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;


// Referenced classes of package com.drew.metadata:
//            Metadata

public interface MetadataReader
{

    public abstract Metadata extract();

    public abstract Metadata extract(Metadata metadata);
}
