// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            NikonType2MakernoteDescriptor

public class NikonType2MakernoteDirectory extends Directory
{

    public NikonType2MakernoteDirectory()
    {
        setDescriptor(new NikonType2MakernoteDescriptor(this));
    }

    public static Rational CalculateFlashCompensationFromBytes(byte abyte0[])
    {
        Rational rational;
        if(abyte0.length == 3)
        {
            byte byte0 = abyte0[2];
            rational = new Rational(abyte0[0] * abyte0[1], byte0);
        } else
        {
            rational = null;
        }
        return rational;
    }

    public Rational getAutoFlashCompensation()
        throws MetadataException
    {
        Rational rational;
        if(!containsTag(18))
            rational = null;
        else
            rational = CalculateFlashCompensationFromBytes(getByteArray(18));
        return rational;
    }

    public String getName()
    {
        return "Nikon Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return _tagNameMap;
    }

    public static final int TAG_NIKON_TYPE2_ADAPTER = 130;
    public static final int TAG_NIKON_TYPE2_AF_FOCUS_POSITION = 136;
    public static final int TAG_NIKON_TYPE2_AF_TYPE = 7;
    public static final int TAG_NIKON_TYPE2_AUTO_FLASH_COMPENSATION = 18;
    public static final int TAG_NIKON_TYPE2_AUTO_FLASH_MODE = 9;
    public static final int TAG_NIKON_TYPE2_CAMERA_COLOR_MODE = 141;
    public static final int TAG_NIKON_TYPE2_CAMERA_HUE_ADJUSTMENT = 146;
    public static final int TAG_NIKON_TYPE2_CAMERA_SHARPENING = 6;
    public static final int TAG_NIKON_TYPE2_CAMERA_TONE_COMPENSATION = 129;
    public static final int TAG_NIKON_TYPE2_CAMERA_WHITE_BALANCE = 5;
    public static final int TAG_NIKON_TYPE2_CAMERA_WHITE_BALANCE_FINE = 11;
    public static final int TAG_NIKON_TYPE2_CAMERA_WHITE_BALANCE_RB_COEFF = 12;
    public static final int TAG_NIKON_TYPE2_CAPTURE_EDITOR_DATA = 3585;
    public static final int TAG_NIKON_TYPE2_COLOR_MODE = 3;
    public static final int TAG_NIKON_TYPE2_DATA_DUMP = 16;
    public static final int TAG_NIKON_TYPE2_DIGITAL_ZOOM = 134;
    public static final int TAG_NIKON_TYPE2_EXPOSURE_SEQUENCE_NUMBER = 167;
    public static final int TAG_NIKON_TYPE2_FIRMWARE_VERSION = 1;
    public static final int TAG_NIKON_TYPE2_FLASH_SYNC_MODE = 8;
    public static final int TAG_NIKON_TYPE2_IMAGE_ADJUSTMENT = 128;
    public static final int TAG_NIKON_TYPE2_ISO_1 = 2;
    public static final int TAG_NIKON_TYPE2_ISO_2 = 19;
    public static final int TAG_NIKON_TYPE2_ISO_SELECTION = 15;
    public static final int TAG_NIKON_TYPE2_LENS = 132;
    public static final int TAG_NIKON_TYPE2_LIGHT_SOURCE = 144;
    public static final int TAG_NIKON_TYPE2_MANUAL_FOCUS_DISTANCE = 133;
    public static final int TAG_NIKON_TYPE2_NOISE_REDUCTION = 149;
    public static final int TAG_NIKON_TYPE2_QUALITY_AND_FILE_FORMAT = 4;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_1 = 13;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_11 = 145;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_12 = 151;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_13 = 152;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_14 = 153;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_15 = 154;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_16 = 3600;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_2 = 14;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_20 = 138;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_21 = 22;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_22 = 23;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_23 = 24;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_24 = 25;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_25 = 160;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_26 = 162;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_27 = 163;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_29 = 170;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_3 = 17;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_30 = 171;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_32 = 168;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_33 = 169;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_34 = 10;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_4 = 131;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_5 = 135;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_7 = 137;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_8 = 139;
    public static final int TAG_NIKON_TYPE2_UNKNOWN_9 = 140;
    protected static final HashMap _tagNameMap;

    static 
    {
        _tagNameMap = new HashMap();
        _tagNameMap.put(new Integer(1), "Firmware Version");
        _tagNameMap.put(new Integer(2), "ISO");
        _tagNameMap.put(new Integer(4), "Quality & File Format");
        _tagNameMap.put(new Integer(5), "White Balance");
        _tagNameMap.put(new Integer(6), "Sharpening");
        _tagNameMap.put(new Integer(7), "AF Type");
        _tagNameMap.put(new Integer(11), "White Balance Fine");
        _tagNameMap.put(new Integer(12), "White Balance RB Coefficients");
        _tagNameMap.put(new Integer(19), "ISO");
        _tagNameMap.put(new Integer(15), "ISO Selection");
        _tagNameMap.put(new Integer(16), "Data Dump");
        _tagNameMap.put(new Integer(128), "Image Adjustment");
        _tagNameMap.put(new Integer(129), "Tone Compensation");
        _tagNameMap.put(new Integer(130), "Adapter");
        _tagNameMap.put(new Integer(132), "Lens");
        _tagNameMap.put(new Integer(133), "Manual Focus Distance");
        _tagNameMap.put(new Integer(134), "Digital Zoom");
        _tagNameMap.put(new Integer(141), "Colour Mode");
        _tagNameMap.put(new Integer(146), "Camera Hue Adjustment");
        _tagNameMap.put(new Integer(149), "Noise Reduction");
        _tagNameMap.put(new Integer(3585), "Capture Editor Data");
        _tagNameMap.put(new Integer(13), "Unknown 01");
        _tagNameMap.put(new Integer(14), "Unknown 02");
        _tagNameMap.put(new Integer(17), "Unknown 03");
        _tagNameMap.put(new Integer(131), "Unknown 04");
        _tagNameMap.put(new Integer(135), "Unknown 05");
        _tagNameMap.put(new Integer(136), "AF Focus Position");
        _tagNameMap.put(new Integer(137), "Unknown 07");
        _tagNameMap.put(new Integer(139), "Unknown 08");
        _tagNameMap.put(new Integer(140), "Unknown 09");
        _tagNameMap.put(new Integer(144), "Light source");
        _tagNameMap.put(new Integer(145), "Unknown 11");
        _tagNameMap.put(new Integer(151), "Unknown 12");
        _tagNameMap.put(new Integer(152), "Unknown 13");
        _tagNameMap.put(new Integer(153), "Unknown 14");
        _tagNameMap.put(new Integer(154), "Unknown 15");
        _tagNameMap.put(new Integer(3600), "Unknown 16");
        _tagNameMap.put(new Integer(8), "Flash Sync Mode");
        _tagNameMap.put(new Integer(9), "Auto Flash Mode");
        _tagNameMap.put(new Integer(18), "Auto Flash Compensation");
        _tagNameMap.put(new Integer(167), "Exposure Sequence Number");
        _tagNameMap.put(new Integer(3), "Color Mode");
        _tagNameMap.put(new Integer(138), "Unknown 20");
        _tagNameMap.put(new Integer(22), "Unknown 21");
        _tagNameMap.put(new Integer(23), "Unknown 22");
        _tagNameMap.put(new Integer(24), "Unknown 23");
        _tagNameMap.put(new Integer(25), "Unknown 24");
        _tagNameMap.put(new Integer(160), "Unknown 25");
        _tagNameMap.put(new Integer(162), "Unknown 26");
        _tagNameMap.put(new Integer(163), "Unknown 27");
        _tagNameMap.put(new Integer(170), "Unknown 29");
        _tagNameMap.put(new Integer(171), "Unknown 30");
        _tagNameMap.put(new Integer(168), "Unknown 32");
        _tagNameMap.put(new Integer(169), "Unknown 33");
    }
}
