// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            CasioType2MakernoteDescriptor

public class CasioType2MakernoteDirectory extends Directory
{

    public CasioType2MakernoteDirectory()
    {
        setDescriptor(new CasioType2MakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Casio Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_CASIO_TYPE2_BESTSHOT_MODE = 12295;
    public static final int TAG_CASIO_TYPE2_CASIO_PREVIEW_THUMBNAIL = 8192;
    public static final int TAG_CASIO_TYPE2_CCD_ISO_SENSITIVITY = 12308;
    public static final int TAG_CASIO_TYPE2_COLOUR_MODE = 12309;
    public static final int TAG_CASIO_TYPE2_CONTRAST = 32;
    public static final int TAG_CASIO_TYPE2_ENHANCEMENT = 12310;
    public static final int TAG_CASIO_TYPE2_FILTER = 12311;
    public static final int TAG_CASIO_TYPE2_FLASH_DISTANCE = 8244;
    public static final int TAG_CASIO_TYPE2_FOCAL_LENGTH = 29;
    public static final int TAG_CASIO_TYPE2_FOCUS_MODE_1 = 13;
    public static final int TAG_CASIO_TYPE2_FOCUS_MODE_2 = 12291;
    public static final int TAG_CASIO_TYPE2_IMAGE_SIZE = 9;
    public static final int TAG_CASIO_TYPE2_ISO_SENSITIVITY = 20;
    public static final int TAG_CASIO_TYPE2_OBJECT_DISTANCE = 8226;
    public static final int TAG_CASIO_TYPE2_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_CASIO_TYPE2_QUALITY = 12290;
    public static final int TAG_CASIO_TYPE2_QUALITY_MODE = 8;
    public static final int TAG_CASIO_TYPE2_RECORD_MODE = 12288;
    public static final int TAG_CASIO_TYPE2_SATURATION = 31;
    public static final int TAG_CASIO_TYPE2_SELF_TIMER = 12289;
    public static final int TAG_CASIO_TYPE2_SHARPNESS = 33;
    public static final int TAG_CASIO_TYPE2_THUMBNAIL_DIMENSIONS = 2;
    public static final int TAG_CASIO_TYPE2_THUMBNAIL_OFFSET = 4;
    public static final int TAG_CASIO_TYPE2_THUMBNAIL_SIZE = 3;
    public static final int TAG_CASIO_TYPE2_TIME_ZONE = 12294;
    public static final int TAG_CASIO_TYPE2_WHITE_BALANCE_1 = 25;
    public static final int TAG_CASIO_TYPE2_WHITE_BALANCE_2 = 8210;
    public static final int TAG_CASIO_TYPE2_WHITE_BALANCE_BIAS = 8209;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(2), "Thumbnail Dimensions");
        tagNameMap.put(new Integer(3), "Thumbnail Size");
        tagNameMap.put(new Integer(4), "Thumbnail Offset");
        tagNameMap.put(new Integer(8), "Quality Mode");
        tagNameMap.put(new Integer(9), "Image Size");
        tagNameMap.put(new Integer(13), "Focus Mode");
        tagNameMap.put(new Integer(20), "ISO Sensitivity");
        tagNameMap.put(new Integer(25), "White Balance");
        tagNameMap.put(new Integer(29), "Focal Length");
        tagNameMap.put(new Integer(31), "Saturation");
        tagNameMap.put(new Integer(32), "Contrast");
        tagNameMap.put(new Integer(33), "Sharpness");
        tagNameMap.put(new Integer(3584), "Print Image Matching (PIM) Info");
        tagNameMap.put(new Integer(8192), "Casio Preview Thumbnail");
        tagNameMap.put(new Integer(8209), "White Balance Bias");
        tagNameMap.put(new Integer(8210), "White Balance");
        tagNameMap.put(new Integer(8226), "Object Distance");
        tagNameMap.put(new Integer(8244), "Flash Distance");
        tagNameMap.put(new Integer(12288), "Record Mode");
        tagNameMap.put(new Integer(12289), "Self Timer");
        tagNameMap.put(new Integer(12290), "Quality");
        tagNameMap.put(new Integer(12291), "Focus Mode");
        tagNameMap.put(new Integer(12294), "Time Zone");
        tagNameMap.put(new Integer(12295), "BestShot Mode");
        tagNameMap.put(new Integer(12308), "CCD ISO Sensitivity");
        tagNameMap.put(new Integer(12309), "Colour Mode");
        tagNameMap.put(new Integer(12310), "Enhancement");
        tagNameMap.put(new Integer(12311), "Filter");
    }
}
