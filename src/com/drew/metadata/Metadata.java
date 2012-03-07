// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.drew.metadata:
//            Directory

public final class Metadata
    implements Serializable
{

    public Metadata()
    {
    }

    public boolean containsDirectory(Class class1)
    {
        return directoryMap.containsKey(class1);
    }

    public Directory getDirectory(Class class1)
    {
        if(!com/drew/metadata/Directory.isAssignableFrom(class1))
            throw new RuntimeException("Class type passed to getDirectory must be an implementation of com.drew.metadata.Directory");
        Directory directory;
        if(directoryMap.containsKey(class1))
        {
            directory = (Directory)directoryMap.get(class1);
        } else
        {
            Object obj;
            try
            {
                obj = class1.newInstance();
            }
            catch(Exception exception)
            {
                throw new RuntimeException((new StringBuilder("Cannot instantiate provided Directory type: ")).append(class1.toString()).toString());
            }
            directoryMap.put(class1, obj);
            directoryList.add(obj);
            directory = (Directory)obj;
        }
        return directory;
    }

    public int getDirectoryCount()
    {
        return directoryList.size();
    }

    public Iterator getDirectoryIterator()
    {
        return directoryList.iterator();
    }

    private final ArrayList directoryList = new ArrayList();
    private final HashMap directoryMap = new HashMap();
}
