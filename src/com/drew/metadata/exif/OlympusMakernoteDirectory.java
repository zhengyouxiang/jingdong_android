// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            OlympusMakernoteDescriptor

public class OlympusMakernoteDirectory extends Directory
{

    public OlympusMakernoteDirectory()
    {
        setDescriptor(new OlympusMakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Olympus Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_OLYMPUS_BLACK_LEVEL = 4114;
    public static final int TAG_OLYMPUS_BLUE_BIAS = 4120;
    public static final int TAG_OLYMPUS_BRACKET = 4102;
    public static final int TAG_OLYMPUS_CAMERA_ID = 521;
    public static final int TAG_OLYMPUS_CAMERA_SETTINGS_1 = 1;
    public static final int TAG_OLYMPUS_CAMERA_SETTINGS_2 = 3;
    public static final int TAG_OLYMPUS_COLOUR_CONTROL = 4139;
    public static final int TAG_OLYMPUS_COLOUR_MATRIX = 4113;
    public static final int TAG_OLYMPUS_COLOUR_MODE = 257;
    public static final int TAG_OLYMPUS_COMPRESSED_IMAGE_SIZE = 64;
    public static final int TAG_OLYMPUS_COMPRESSION_RATIO = 4148;
    public static final int TAG_OLYMPUS_CONTRAST = 4137;
    public static final int TAG_OLYMPUS_CORING_FILTER = 4141;
    public static final int TAG_OLYMPUS_DATA_DUMP = 3840;
    public static final int TAG_OLYMPUS_DIGI_ZOOM_RATIO = 516;
    public static final int TAG_OLYMPUS_FINAL_HEIGHT = 4143;
    public static final int TAG_OLYMPUS_FINAL_WIDTH = 4142;
    public static final int TAG_OLYMPUS_FIRMWARE_VERSION = 519;
    public static final int TAG_OLYMPUS_FLASH_BIAS = 4131;
    public static final int TAG_OLYMPUS_FLASH_MODE = 4100;
    public static final int TAG_OLYMPUS_FOCUS_DISTANCE = 4108;
    public static final int TAG_OLYMPUS_FOCUS_MODE = 4107;
    public static final int TAG_OLYMPUS_IMAGE_HEIGHT = 524;
    public static final int TAG_OLYMPUS_IMAGE_QUALITY_1 = 258;
    public static final int TAG_OLYMPUS_IMAGE_QUALITY_2 = 259;
    public static final int TAG_OLYMPUS_IMAGE_WIDTH = 523;
    public static final int TAG_OLYMPUS_JPEG_QUALITY = 513;
    public static final int TAG_OLYMPUS_MACRO_FOCUS = 4110;
    public static final int TAG_OLYMPUS_MACRO_MODE = 514;
    public static final int TAG_OLYMPUS_MAKERNOTE_VERSION = 0;
    public static final int TAG_OLYMPUS_MINOLTA_THUMBNAIL_LENGTH = 137;
    public static final int TAG_OLYMPUS_MINOLTA_THUMBNAIL_OFFSET_1 = 129;
    public static final int TAG_OLYMPUS_MINOLTA_THUMBNAIL_OFFSET_2 = 136;
    public static final int TAG_OLYMPUS_ORIGINAL_MANUFACTURER_MODEL = 525;
    public static final int TAG_OLYMPUS_PICT_INFO = 520;
    public static final int TAG_OLYMPUS_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_OLYMPUS_RED_BIAS = 4119;
    public static final int TAG_OLYMPUS_SERIAL_NUMBER = 4122;
    public static final int TAG_OLYMPUS_SHARPNESS = 4111;
    public static final int TAG_OLYMPUS_SHARPNESS_FACTOR = 4138;
    public static final int TAG_OLYMPUS_SPECIAL_MODE = 512;
    public static final int TAG_OLYMPUS_UNKNOWN_1 = 515;
    public static final int TAG_OLYMPUS_UNKNOWN_2 = 517;
    public static final int TAG_OLYMPUS_UNKNOWN_3 = 518;
    public static final int TAG_OLYMPUS_VALID_BITS = 4140;
    public static final int TAG_OLYMPUS_WHITE_BALANCE = 4117;
    public static final int TAG_OLYMPUS_ZOOM = 4109;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(512), "Special Mode");
        tagNameMap.put(new Integer(513), "Jpeg Quality");
        tagNameMap.put(new Integer(514), "Macro");
        tagNameMap.put(new Integer(515), "Makernote Unknown 1");
        tagNameMap.put(new Integer(516), "DigiZoom Ratio");
        tagNameMap.put(new Integer(517), "Makernote Unknown 2");
        tagNameMap.put(new Integer(518), "Makernote Unknown 3");
        tagNameMap.put(new Integer(519), "Firmware Version");
        tagNameMap.put(new Integer(520), "Pict Info");
        tagNameMap.put(new Integer(521), "Camera Id");
        tagNameMap.put(new Integer(3840), "Data Dump");
        tagNameMap.put(new Integer(0), "Makernote Version");
        tagNameMap.put(new Integer(1), "Camera Settings");
        tagNameMap.put(new Integer(3), "Camera Settings");
        tagNameMap.put(new Integer(64), "Compressed Image Size");
        tagNameMap.put(new Integer(129), "Thumbnail Offset");
        tagNameMap.put(new Integer(136), "Thumbnail Offset");
        tagNameMap.put(new Integer(137), "Thumbnail Length");
        tagNameMap.put(new Integer(257), "Colour Mode");
        tagNameMap.put(new Integer(258), "Image Quality");
        tagNameMap.put(new Integer(259), "Image Quality");
        tagNameMap.put(new Integer(524), "Image Height");
        tagNameMap.put(new Integer(525), "Original Manufacturer Model");
        tagNameMap.put(new Integer(3584), "Print Image Matching (PIM) Info");
        tagNameMap.put(new Integer(4100), "Flash Mode");
        tagNameMap.put(new Integer(4102), "Bracket");
        tagNameMap.put(new Integer(4107), "Focus Mode");
        tagNameMap.put(new Integer(4108), "Focus Distance");
        tagNameMap.put(new Integer(4109), "Zoom");
        tagNameMap.put(new Integer(4110), "Macro Focus");
        tagNameMap.put(new Integer(4111), "Sharpness");
        tagNameMap.put(new Integer(4113), "Colour Matrix");
        tagNameMap.put(new Integer(4114), "Black Level");
        tagNameMap.put(new Integer(4117), "White Balance");
        tagNameMap.put(new Integer(4119), "Red Bias");
        tagNameMap.put(new Integer(4120), "Blue Bias");
        tagNameMap.put(new Integer(4122), "Serial Number");
        tagNameMap.put(new Integer(4131), "Flash Bias");
        tagNameMap.put(new Integer(4137), "Contrast");
        tagNameMap.put(new Integer(4138), "Sharpness Factor");
        tagNameMap.put(new Integer(4139), "Colour Control");
        tagNameMap.put(new Integer(4140), "Valid Bits");
        tagNameMap.put(new Integer(4141), "Coring Filter");
        tagNameMap.put(new Integer(4142), "Final Width");
        tagNameMap.put(new Integer(4143), "Final Height");
        tagNameMap.put(new Integer(4148), "Compression Ratio");
    }
}
