// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            PanasonicMakernoteDescriptor

public class PanasonicMakernoteDirectory extends Directory
{

    public PanasonicMakernoteDirectory()
    {
        setDescriptor(new PanasonicMakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Panasonic Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_PANASONIC_MACRO_MODE = 28;
    public static final int TAG_PANASONIC_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_PANASONIC_QUALITY_MODE = 1;
    public static final int TAG_PANASONIC_RECORD_MODE = 31;
    public static final int TAG_PANASONIC_VERSION = 2;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(1), "Quality Mode");
        tagNameMap.put(new Integer(2), "Version");
        tagNameMap.put(new Integer(28), "Macro Mode");
        tagNameMap.put(new Integer(31), "Record Mode");
        tagNameMap.put(new Integer(3584), "Print Image Matching (PIM) Info");
    }
}
