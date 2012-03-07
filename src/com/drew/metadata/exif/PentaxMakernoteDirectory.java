// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            PentaxMakernoteDescriptor

public class PentaxMakernoteDirectory extends Directory
{

    public PentaxMakernoteDirectory()
    {
        setDescriptor(new PentaxMakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Pentax Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_PENTAX_CAPTURE_MODE = 1;
    public static final int TAG_PENTAX_COLOUR = 23;
    public static final int TAG_PENTAX_CONTRAST = 12;
    public static final int TAG_PENTAX_DAYLIGHT_SAVINGS = 4097;
    public static final int TAG_PENTAX_DIGITAL_ZOOM = 10;
    public static final int TAG_PENTAX_FLASH_MODE = 4;
    public static final int TAG_PENTAX_FOCUS_MODE = 3;
    public static final int TAG_PENTAX_ISO_SPEED = 20;
    public static final int TAG_PENTAX_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_PENTAX_QUALITY_LEVEL = 2;
    public static final int TAG_PENTAX_SATURATION = 13;
    public static final int TAG_PENTAX_SHARPNESS = 11;
    public static final int TAG_PENTAX_TIME_ZONE = 4096;
    public static final int TAG_PENTAX_WHITE_BALANCE = 7;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(1), "Capture Mode");
        tagNameMap.put(new Integer(2), "Quality Level");
        tagNameMap.put(new Integer(3), "Focus Mode");
        tagNameMap.put(new Integer(4), "Flash Mode");
        tagNameMap.put(new Integer(7), "White Balance");
        tagNameMap.put(new Integer(10), "Digital Zoom");
        tagNameMap.put(new Integer(11), "Sharpness");
        tagNameMap.put(new Integer(12), "Contrast");
        tagNameMap.put(new Integer(13), "Saturation");
        tagNameMap.put(new Integer(20), "ISO Speed");
        tagNameMap.put(new Integer(23), "Colour");
        tagNameMap.put(new Integer(3584), "Print Image Matching (PIM) Info");
        tagNameMap.put(new Integer(4096), "Time Zone");
        tagNameMap.put(new Integer(4097), "Daylight Savings");
    }
}
