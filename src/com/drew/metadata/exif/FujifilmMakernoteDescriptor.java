// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.metadata.*;

public class FujifilmMakernoteDescriptor extends TagDescriptor
{

    public FujifilmMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getAutoExposureWarningDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4866)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4866);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "AE good";
            break;

        case 1: // '\001'
            s = "Over exposed (>1/1000s @ F11)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getBlurWarningDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4864)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4864);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "No blur warning";
            break;

        case 1: // '\001'
            s = "Blur warning";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getColorDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4099)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4099);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal (STD)";
            break;

        case 256: 
            s = "High";
            break;

        case 512: 
            s = "Low (ORG)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getContinuousTakingOrAutoBrackettingDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4352)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4352);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;

        case 1: // '\001'
            s = "On";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 14: default 124
    //                   4097: 135
    //                   4098: 143
    //                   4099: 151
    //                   4100: 159
    //                   4112: 167
    //                   4113: 175
    //                   4128: 183
    //                   4129: 191
    //                   4144: 199
    //                   4145: 207
    //                   4352: 215
    //                   4864: 223
    //                   4865: 231
    //                   4866: 239;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L1:
        String s = _directory.getString(i);
_L17:
        return s;
_L2:
        s = getSharpnessDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getWhiteBalanceDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getColorDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getToneDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getFlashModeDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getFlashStrengthDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getMacroDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getFocusModeDescription();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getSlowSyncDescription();
        continue; /* Loop/switch isn't completed */
_L11:
        s = getPictureModeDescription();
        continue; /* Loop/switch isn't completed */
_L12:
        s = getContinuousTakingOrAutoBrackettingDescription();
        continue; /* Loop/switch isn't completed */
_L13:
        s = getBlurWarningDescription();
        continue; /* Loop/switch isn't completed */
_L14:
        s = getFocusWarningDescription();
        continue; /* Loop/switch isn't completed */
_L15:
        s = getAutoExposureWarningDescription();
        if(true) goto _L17; else goto _L16
_L16:
    }

    public String getFlashModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4112)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4112);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto";
            break;

        case 1: // '\001'
            s = "On";
            break;

        case 2: // '\002'
            s = "Off";
            break;

        case 3: // '\003'
            s = "Red-eye reduction";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashStrengthDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(4113))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(4113).toSimpleString(false)))).append(" EV (Apex)").toString();
        return s;
    }

    public String getFocusModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4129)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4129);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto focus";
            break;

        case 1: // '\001'
            s = "Manual focus";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocusWarningDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4865)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4865);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto focus good";
            break;

        case 1: // '\001'
            s = "Out of focus";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getMacroDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4128)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4128);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;

        case 1: // '\001'
            s = "On";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getPictureModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4145)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4145);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto";
            break;

        case 1: // '\001'
            s = "Portrait scene";
            break;

        case 2: // '\002'
            s = "Landscape scene";
            break;

        case 4: // '\004'
            s = "Sports scene";
            break;

        case 5: // '\005'
            s = "Night scene";
            break;

        case 6: // '\006'
            s = "Program AE";
            break;

        case 256: 
            s = "Aperture priority AE";
            break;

        case 512: 
            s = "Shutter priority AE";
            break;

        case 768: 
            s = "Manual exposure";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSharpnessDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4097)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4097);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Softest";
            break;

        case 2: // '\002'
            s = "Soft";
            break;

        case 3: // '\003'
            s = "Normal";
            break;

        case 4: // '\004'
            s = "Hard";
            break;

        case 5: // '\005'
            s = "Hardest";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSlowSyncDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4144)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4144);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;

        case 1: // '\001'
            s = "On";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getToneDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4100)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4100);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal (STD)";
            break;

        case 256: 
            s = "High (HARD)";
            break;

        case 512: 
            s = "Low (ORG)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWhiteBalanceDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4098)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4098);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto";
            break;

        case 256: 
            s = "Daylight";
            break;

        case 512: 
            s = "Cloudy";
            break;

        case 768: 
            s = "DaylightColor-fluorescence";
            break;

        case 769: 
            s = "DaywhiteColor-fluorescence";
            break;

        case 770: 
            s = "White-fluorescence";
            break;

        case 1024: 
            s = "Incandenscense";
            break;

        case 3840: 
            s = "Custom white balance";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
