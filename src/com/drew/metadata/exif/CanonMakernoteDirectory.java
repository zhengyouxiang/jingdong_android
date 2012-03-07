// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            CanonMakernoteDescriptor

public class CanonMakernoteDirectory extends Directory
{

    public CanonMakernoteDirectory()
    {
        setDescriptor(new CanonMakernoteDescriptor(this));
    }

    public String getName()
    {
        return "Canon Makernote";
    }

    protected HashMap getTagNameMap()
    {
        return _tagNameMap;
    }

    public void setIntArray(int i, int ai[])
    {
        if(i != 1) goto _L2; else goto _L1
_L1:
        int l = 1;
_L7:
        if(l < ai.length) goto _L4; else goto _L3
_L3:
        int k;
        if(i != 15)
            break MISSING_BLOCK_LABEL_108;
        k = 1;
_L8:
        if(k < ai.length) goto _L6; else goto _L5
_L5:
        return;
_L4:
        setInt(49408 + l, ai[l]);
        l++;
          goto _L7
_L2:
        if(i == 4)
        {
            int j = 1;
            while(j < ai.length) 
            {
                setInt(49664 + j, ai[j]);
                j++;
            }
        }
          goto _L3
_L6:
        setInt(1 + (49920 + k), 0xf & ai[k]);
        k++;
          goto _L8
        super.setIntArray(i, ai);
          goto _L5
    }

    public static final int TAG_CANON_CAMERA_STATE_1 = 1;
    public static final int TAG_CANON_CAMERA_STATE_2 = 4;
    public static final int TAG_CANON_CUSTOM_FUNCTIONS = 15;
    public static final int TAG_CANON_CUSTOM_FUNCTION_AF_ASSIST_LIGHT = 49925;
    public static final int TAG_CANON_CUSTOM_FUNCTION_AF_STOP = 49929;
    public static final int TAG_CANON_CUSTOM_FUNCTION_BRACKETTING = 49927;
    public static final int TAG_CANON_CUSTOM_FUNCTION_FILL_FLASH_REDUCTION = 49930;
    public static final int TAG_CANON_CUSTOM_FUNCTION_LONG_EXPOSURE_NOISE_REDUCTION = 49921;
    public static final int TAG_CANON_CUSTOM_FUNCTION_MENU_BUTTON_RETURN = 49931;
    public static final int TAG_CANON_CUSTOM_FUNCTION_MIRROR_LOCKUP = 49923;
    public static final int TAG_CANON_CUSTOM_FUNCTION_SENSOR_CLEANING = 49933;
    public static final int TAG_CANON_CUSTOM_FUNCTION_SET_BUTTON_FUNCTION = 49932;
    public static final int TAG_CANON_CUSTOM_FUNCTION_SHUTTER_AUTO_EXPOSURE_LOCK_BUTTONS = 49922;
    public static final int TAG_CANON_CUSTOM_FUNCTION_SHUTTER_CURTAIN_SYNC = 49928;
    public static final int TAG_CANON_CUSTOM_FUNCTION_SHUTTER_SPEED_IN_AV_MODE = 49926;
    public static final int TAG_CANON_CUSTOM_FUNCTION_TV_AV_AND_EXPOSURE_LEVEL = 49924;
    public static final int TAG_CANON_FIRMWARE_VERSION = 7;
    public static final int TAG_CANON_IMAGE_NUMBER = 8;
    public static final int TAG_CANON_IMAGE_TYPE = 6;
    public static final int TAG_CANON_OWNER_NAME = 9;
    public static final int TAG_CANON_SERIAL_NUMBER = 12;
    public static final int TAG_CANON_STATE1_AF_POINT_SELECTED = 49427;
    public static final int TAG_CANON_STATE1_CONTINUOUS_DRIVE_MODE = 49413;
    public static final int TAG_CANON_STATE1_CONTRAST = 49421;
    public static final int TAG_CANON_STATE1_DIGITAL_ZOOM = 49420;
    public static final int TAG_CANON_STATE1_EASY_SHOOTING_MODE = 49419;
    public static final int TAG_CANON_STATE1_EXPOSURE_MODE = 49428;
    public static final int TAG_CANON_STATE1_FLASH_ACTIVITY = 49436;
    public static final int TAG_CANON_STATE1_FLASH_DETAILS = 49437;
    public static final int TAG_CANON_STATE1_FLASH_MODE = 49412;
    public static final int TAG_CANON_STATE1_FOCAL_UNITS_PER_MM = 49433;
    public static final int TAG_CANON_STATE1_FOCUS_MODE_1 = 49415;
    public static final int TAG_CANON_STATE1_FOCUS_MODE_2 = 49440;
    public static final int TAG_CANON_STATE1_FOCUS_TYPE = 49426;
    public static final int TAG_CANON_STATE1_IMAGE_SIZE = 49418;
    public static final int TAG_CANON_STATE1_ISO = 49424;
    public static final int TAG_CANON_STATE1_LONG_FOCAL_LENGTH = 49431;
    public static final int TAG_CANON_STATE1_MACRO_MODE = 49409;
    public static final int TAG_CANON_STATE1_METERING_MODE = 49425;
    public static final int TAG_CANON_STATE1_QUALITY = 49411;
    public static final int TAG_CANON_STATE1_SATURATION = 49422;
    public static final int TAG_CANON_STATE1_SELF_TIMER_DELAY = 49410;
    public static final int TAG_CANON_STATE1_SHARPNESS = 49423;
    public static final int TAG_CANON_STATE1_SHORT_FOCAL_LENGTH = 49432;
    public static final int TAG_CANON_STATE1_UNKNOWN_10 = 49435;
    public static final int TAG_CANON_STATE1_UNKNOWN_12 = 49438;
    public static final int TAG_CANON_STATE1_UNKNOWN_13 = 49439;
    public static final int TAG_CANON_STATE1_UNKNOWN_2 = 49414;
    public static final int TAG_CANON_STATE1_UNKNOWN_3 = 49416;
    public static final int TAG_CANON_STATE1_UNKNOWN_4 = 49417;
    public static final int TAG_CANON_STATE1_UNKNOWN_7 = 49429;
    public static final int TAG_CANON_STATE1_UNKNOWN_8 = 49430;
    public static final int TAG_CANON_STATE1_UNKNOWN_9 = 49434;
    public static final int TAG_CANON_STATE2_AEB_BRACKET_VALUE = 49681;
    public static final int TAG_CANON_STATE2_AF_POINT_USED = 49678;
    public static final int TAG_CANON_STATE2_AUTO_EXPOSURE_BRACKETING = 49680;
    public static final int TAG_CANON_STATE2_FLASH_BIAS = 49679;
    public static final int TAG_CANON_STATE2_SEQUENCE_NUMBER = 49673;
    public static final int TAG_CANON_STATE2_SUBJECT_DISTANCE = 49683;
    public static final int TAG_CANON_STATE2_WHITE_BALANCE = 49671;
    public static final int TAG_CANON_UNKNOWN_1 = 13;
    protected static final HashMap _tagNameMap;

    static 
    {
        _tagNameMap = new HashMap();
        _tagNameMap.put(new Integer(7), "Firmware Version");
        _tagNameMap.put(new Integer(8), "Image Number");
        _tagNameMap.put(new Integer(6), "Image Type");
        _tagNameMap.put(new Integer(9), "Owner Name");
        _tagNameMap.put(new Integer(13), "Makernote Unknown 1");
        _tagNameMap.put(new Integer(15), "Custom Functions");
        _tagNameMap.put(new Integer(12), "Camera Serial Number");
        _tagNameMap.put(new Integer(49427), "AF Point Selected");
        _tagNameMap.put(new Integer(49413), "Continuous Drive Mode");
        _tagNameMap.put(new Integer(49421), "Contrast");
        _tagNameMap.put(new Integer(49419), "Easy Shooting Mode");
        _tagNameMap.put(new Integer(49428), "Exposure Mode");
        _tagNameMap.put(new Integer(49437), "Flash Details");
        _tagNameMap.put(new Integer(49412), "Flash Mode");
        _tagNameMap.put(new Integer(49433), "Focal Units per mm");
        _tagNameMap.put(new Integer(49415), "Focus Mode");
        _tagNameMap.put(new Integer(49440), "Focus Mode");
        _tagNameMap.put(new Integer(49418), "Image Size");
        _tagNameMap.put(new Integer(49424), "Iso");
        _tagNameMap.put(new Integer(49431), "Long Focal Length");
        _tagNameMap.put(new Integer(49409), "Macro Mode");
        _tagNameMap.put(new Integer(49425), "Metering Mode");
        _tagNameMap.put(new Integer(49422), "Saturation");
        _tagNameMap.put(new Integer(49410), "Self Timer Delay");
        _tagNameMap.put(new Integer(49423), "Sharpness");
        _tagNameMap.put(new Integer(49432), "Short Focal Length");
        _tagNameMap.put(new Integer(49411), "Quality");
        _tagNameMap.put(new Integer(49414), "Unknown Camera State 2");
        _tagNameMap.put(new Integer(49416), "Unknown Camera State 3");
        _tagNameMap.put(new Integer(49417), "Unknown Camera State 4");
        _tagNameMap.put(new Integer(49420), "Digital Zoom");
        _tagNameMap.put(new Integer(49426), "Focus Type");
        _tagNameMap.put(new Integer(49429), "Unknown Camera State 7");
        _tagNameMap.put(new Integer(49430), "Unknown Camera State 8");
        _tagNameMap.put(new Integer(49434), "Unknown Camera State 9");
        _tagNameMap.put(new Integer(49435), "Unknown Camera State 10");
        _tagNameMap.put(new Integer(49436), "Flash Activity");
        _tagNameMap.put(new Integer(49438), "Unknown Camera State 12");
        _tagNameMap.put(new Integer(49439), "Unknown Camera State 13");
        _tagNameMap.put(new Integer(49671), "White Balance");
        _tagNameMap.put(new Integer(49673), "Sequence Number");
        _tagNameMap.put(new Integer(49678), "AF Point Used");
        _tagNameMap.put(new Integer(49679), "Flash Bias");
        _tagNameMap.put(new Integer(49680), "Auto Exposure Bracketing");
        _tagNameMap.put(new Integer(49681), "AEB Bracket Value");
        _tagNameMap.put(new Integer(49683), "Subject Distance");
        _tagNameMap.put(new Integer(49921), "Long Exposure Noise Reduction");
        _tagNameMap.put(new Integer(49922), "Shutter/Auto Exposure-lock Buttons");
        _tagNameMap.put(new Integer(49923), "Mirror Lockup");
        _tagNameMap.put(new Integer(49924), "Tv/Av And Exposure Level");
        _tagNameMap.put(new Integer(49925), "AF-Assist Light");
        _tagNameMap.put(new Integer(49926), "Shutter Speed in Av Mode");
        _tagNameMap.put(new Integer(49927), "Auto-Exposure Bracketting Sequence/Auto Cancellation");
        _tagNameMap.put(new Integer(49928), "Shutter Curtain Sync");
        _tagNameMap.put(new Integer(49929), "Lens Auto-Focus Stop Button Function Switch");
        _tagNameMap.put(new Integer(49930), "Auto Reduction of Fill Flash");
        _tagNameMap.put(new Integer(49931), "Menu Button Return Position");
        _tagNameMap.put(new Integer(49932), "SET Button Function When Shooting");
        _tagNameMap.put(new Integer(49933), "Sensor Cleaning");
    }
}
