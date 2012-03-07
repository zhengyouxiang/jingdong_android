// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            KyoceraMakernoteDescriptor

public class KyoceraMakernoteDirectory extends Directory
{

    public KyoceraMakernoteDirectory()
    {
        setDescriptor(new KyoceraMakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Kyocera/Contax Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_KYOCERA_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_KYOCERA_PROPRIETARY_THUMBNAIL = 1;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(1), "Proprietary Thumbnail Format Data");
        tagNameMap.put(new Integer(3584), "Print Image Matching (PIM) Info");
    }
}
