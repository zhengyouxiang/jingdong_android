// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            ExifInteropDescriptor

public class ExifInteropDirectory extends Directory
{

    public ExifInteropDirectory()
    {
        setDescriptor(new ExifInteropDescriptor(this));
    }

    public String getName()
    {
        return "Interoperability";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_INTEROP_INDEX = 1;
    public static final int TAG_INTEROP_VERSION = 2;
    public static final int TAG_RELATED_IMAGE_FILE_FORMAT = 4096;
    public static final int TAG_RELATED_IMAGE_LENGTH = 4098;
    public static final int TAG_RELATED_IMAGE_WIDTH = 4097;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(1), "Interoperability Index");
        tagNameMap.put(new Integer(2), "Interoperability Version");
        tagNameMap.put(new Integer(4096), "Related Image File Format");
        tagNameMap.put(new Integer(4097), "Related Image Width");
        tagNameMap.put(new Integer(4098), "Related Image Length");
    }
}
