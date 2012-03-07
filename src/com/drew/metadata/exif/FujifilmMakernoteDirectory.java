// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            FujifilmMakernoteDescriptor

public class FujifilmMakernoteDirectory extends Directory
{

    public FujifilmMakernoteDirectory()
    {
        setDescriptor(new FujifilmMakernoteDescriptor(this));
    }

    public String getName()
    {
        return "FujiFilm Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_FUJIFILM_AE_WARNING = 4866;
    public static final int TAG_FUJIFILM_BLUR_WARNING = 4864;
    public static final int TAG_FUJIFILM_COLOR = 4099;
    public static final int TAG_FUJIFILM_CONTINUOUS_TAKING_OR_AUTO_BRACKETTING = 4352;
    public static final int TAG_FUJIFILM_FLASH_MODE = 4112;
    public static final int TAG_FUJIFILM_FLASH_STRENGTH = 4113;
    public static final int TAG_FUJIFILM_FOCUS_MODE = 4129;
    public static final int TAG_FUJIFILM_FOCUS_WARNING = 4865;
    public static final int TAG_FUJIFILM_MACRO = 4128;
    public static final int TAG_FUJIFILM_MAKERNOTE_VERSION = 0;
    public static final int TAG_FUJIFILM_PICTURE_MODE = 4145;
    public static final int TAG_FUJIFILM_QUALITY = 4096;
    public static final int TAG_FUJIFILM_SHARPNESS = 4097;
    public static final int TAG_FUJIFILM_SLOW_SYNCHRO = 4144;
    public static final int TAG_FUJIFILM_TONE = 4100;
    public static final int TAG_FUJIFILM_UNKNOWN_1 = 4146;
    public static final int TAG_FUJIFILM_UNKNOWN_2 = 4608;
    public static final int TAG_FUJIFILM_WHITE_BALANCE = 4098;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(4866), "AE Warning");
        tagNameMap.put(new Integer(4864), "Blur Warning");
        tagNameMap.put(new Integer(4099), "Color");
        tagNameMap.put(new Integer(4352), "Continuous Taking Or Auto Bracketting");
        tagNameMap.put(new Integer(4112), "Flash Mode");
        tagNameMap.put(new Integer(4113), "Flash Strength");
        tagNameMap.put(new Integer(4129), "Focus Mode");
        tagNameMap.put(new Integer(4865), "Focus Warning");
        tagNameMap.put(new Integer(4128), "Macro");
        tagNameMap.put(new Integer(0), "Makernote Version");
        tagNameMap.put(new Integer(4145), "Picture Mode");
        tagNameMap.put(new Integer(4096), "Quality");
        tagNameMap.put(new Integer(4097), "Sharpness");
        tagNameMap.put(new Integer(4144), "Slow Synchro");
        tagNameMap.put(new Integer(4100), "Tone");
        tagNameMap.put(new Integer(4146), "Makernote Unknown 1");
        tagNameMap.put(new Integer(4608), "Makernote Unknown 2");
        tagNameMap.put(new Integer(4098), "White Balance");
    }
}
