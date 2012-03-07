// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            CasioType1MakernoteDescriptor

public class CasioType1MakernoteDirectory extends Directory
{

    public CasioType1MakernoteDirectory()
    {
        setDescriptor(new CasioType1MakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Casio Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_CASIO_CCD_SENSITIVITY = 20;
    public static final int TAG_CASIO_CONTRAST = 12;
    public static final int TAG_CASIO_DIGITAL_ZOOM = 10;
    public static final int TAG_CASIO_FLASH_INTENSITY = 5;
    public static final int TAG_CASIO_FLASH_MODE = 4;
    public static final int TAG_CASIO_FOCUSING_MODE = 3;
    public static final int TAG_CASIO_OBJECT_DISTANCE = 6;
    public static final int TAG_CASIO_QUALITY = 2;
    public static final int TAG_CASIO_RECORDING_MODE = 1;
    public static final int TAG_CASIO_SATURATION = 13;
    public static final int TAG_CASIO_SHARPNESS = 11;
    public static final int TAG_CASIO_UNKNOWN_1 = 8;
    public static final int TAG_CASIO_UNKNOWN_2 = 9;
    public static final int TAG_CASIO_UNKNOWN_3 = 14;
    public static final int TAG_CASIO_UNKNOWN_4 = 15;
    public static final int TAG_CASIO_UNKNOWN_5 = 16;
    public static final int TAG_CASIO_UNKNOWN_6 = 17;
    public static final int TAG_CASIO_UNKNOWN_7 = 18;
    public static final int TAG_CASIO_UNKNOWN_8 = 19;
    public static final int TAG_CASIO_WHITE_BALANCE = 7;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(20), "CCD Sensitivity");
        tagNameMap.put(new Integer(12), "Contrast");
        tagNameMap.put(new Integer(10), "Digital Zoom");
        tagNameMap.put(new Integer(5), "Flash Intensity");
        tagNameMap.put(new Integer(4), "Flash Mode");
        tagNameMap.put(new Integer(3), "Focussing Mode");
        tagNameMap.put(new Integer(6), "Object Distance");
        tagNameMap.put(new Integer(2), "Quality");
        tagNameMap.put(new Integer(1), "Recording Mode");
        tagNameMap.put(new Integer(13), "Saturation");
        tagNameMap.put(new Integer(11), "Sharpness");
        tagNameMap.put(new Integer(8), "Makernote Unknown 1");
        tagNameMap.put(new Integer(9), "Makernote Unknown 2");
        tagNameMap.put(new Integer(14), "Makernote Unknown 3");
        tagNameMap.put(new Integer(15), "Makernote Unknown 4");
        tagNameMap.put(new Integer(16), "Makernote Unknown 5");
        tagNameMap.put(new Integer(17), "Makernote Unknown 6");
        tagNameMap.put(new Integer(18), "Makernote Unknown 7");
        tagNameMap.put(new Integer(19), "Makernote Unknown 8");
        tagNameMap.put(new Integer(7), "White Balance");
    }
}
