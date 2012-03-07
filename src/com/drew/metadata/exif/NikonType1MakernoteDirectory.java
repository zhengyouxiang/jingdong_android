// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            NikonType1MakernoteDescriptor

public class NikonType1MakernoteDirectory extends Directory
{

    public NikonType1MakernoteDirectory()
    {
        setDescriptor(new NikonType1MakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Nikon Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return _tagNameMap;
    }

    public static final int TAG_NIKON_TYPE1_CCD_SENSITIVITY = 6;
    public static final int TAG_NIKON_TYPE1_COLOR_MODE = 4;
    public static final int TAG_NIKON_TYPE1_CONVERTER = 11;
    public static final int TAG_NIKON_TYPE1_DIGITAL_ZOOM = 10;
    public static final int TAG_NIKON_TYPE1_FOCUS = 8;
    public static final int TAG_NIKON_TYPE1_IMAGE_ADJUSTMENT = 5;
    public static final int TAG_NIKON_TYPE1_QUALITY = 3;
    public static final int TAG_NIKON_TYPE1_UNKNOWN_1 = 2;
    public static final int TAG_NIKON_TYPE1_UNKNOWN_2 = 9;
    public static final int TAG_NIKON_TYPE1_UNKNOWN_3 = 3840;
    public static final int TAG_NIKON_TYPE1_WHITE_BALANCE = 7;
    protected static final HashMap _tagNameMap;

    static 
    {
        _tagNameMap = new HashMap();
        _tagNameMap.put(new Integer(6), "CCD Sensitivity");
        _tagNameMap.put(new Integer(4), "Color Mode");
        _tagNameMap.put(new Integer(10), "Digital Zoom");
        _tagNameMap.put(new Integer(11), "Fisheye Converter");
        _tagNameMap.put(new Integer(8), "Focus");
        _tagNameMap.put(new Integer(5), "Image Adjustment");
        _tagNameMap.put(new Integer(3), "Quality");
        _tagNameMap.put(new Integer(2), "Makernote Unknown 1");
        _tagNameMap.put(new Integer(9), "Makernote Unknown 2");
        _tagNameMap.put(new Integer(3840), "Makernote Unknown 3");
        _tagNameMap.put(new Integer(7), "White Balance");
    }
}
