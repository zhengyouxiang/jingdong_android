// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class CasioType1MakernoteDescriptor extends TagDescriptor
{

    public CasioType1MakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getCcdSensitivityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(20)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(20);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 64: // '@'
            s = "Normal";
            break;

        case 125: // '}'
            s = "+1.0";
            break;

        case 250: 
            s = "+2.0";
            break;

        case 244: 
            s = "+3.0";
            break;

        case 80: // 'P'
            s = "Normal (ISO 80 equivalent)";
            break;

        case 100: // 'd'
            s = "High";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getContrastDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "Low";
            break;

        case 2: // '\002'
            s = "High";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 1 20: default 96
    //                   1 107
    //                   2 115
    //                   3 123
    //                   4 131
    //                   5 139
    //                   6 147
    //                   7 155
    //                   8 96
    //                   9 96
    //                   10 163
    //                   11 171
    //                   12 179
    //                   13 187
    //                   14 96
    //                   15 96
    //                   16 96
    //                   17 96
    //                   18 96
    //                   19 96
    //                   20 195;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L1 _L1 _L9 _L10 _L11 _L12 _L1 _L1 _L1 _L1 _L1 _L1 _L13
_L1:
        String s = _directory.getString(i);
_L15:
        return s;
_L2:
        s = getRecordingModeDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getQualityDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getFocusingModeDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getFlashModeDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getFlashIntensityDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getObjectDistanceDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getWhiteBalanceDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getDigitalZoomDescription();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getSharpnessDescription();
        continue; /* Loop/switch isn't completed */
_L11:
        s = getContrastDescription();
        continue; /* Loop/switch isn't completed */
_L12:
        s = getSaturationDescription();
        continue; /* Loop/switch isn't completed */
_L13:
        s = getCcdSensitivityDescription();
        if(true) goto _L15; else goto _L14
_L14:
    }

    public String getDigitalZoomDescription()
        throws MetadataException
    {
        if(_directory.containsTag(10)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(10);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 65536: 
            s = "No digital zoom";
            break;

        case 65537: 
            s = "2x digital zoom";
            break;

        case 131072: 
            s = "2x digital zoom";
            break;

        case 262144: 
            s = "4x digital zoom";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashIntensityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(5)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(5);
        switch(i)
        {
        case 12: // '\f'
        case 14: // '\016'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 11: // '\013'
            s = "Weak";
            break;

        case 13: // '\r'
            s = "Normal";
            break;

        case 15: // '\017'
            s = "Strong";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(4)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(4);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Auto";
            break;

        case 2: // '\002'
            s = "On";
            break;

        case 3: // '\003'
            s = "Off";
            break;

        case 4: // '\004'
            s = "Red eye reduction";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocusingModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(3)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(3);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 2: // '\002'
            s = "Macro";
            break;

        case 3: // '\003'
            s = "Auto focus";
            break;

        case 4: // '\004'
            s = "Manual focus";
            break;

        case 5: // '\005'
            s = "Infinity";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getObjectDistanceDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(6))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getInt(6)))).append(" mm").toString();
        return s;
    }

    public String getQualityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(2)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(2);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Economy";
            break;

        case 2: // '\002'
            s = "Normal";
            break;

        case 3: // '\003'
            s = "Fine";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getRecordingModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(1)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(1);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Single shutter";
            break;

        case 2: // '\002'
            s = "Panorama";
            break;

        case 3: // '\003'
            s = "Night scene";
            break;

        case 4: // '\004'
            s = "Portrait";
            break;

        case 5: // '\005'
            s = "Landscape";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSaturationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(13)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(13);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "Low";
            break;

        case 2: // '\002'
            s = "High";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSharpnessDescription()
        throws MetadataException
    {
        if(_directory.containsTag(11)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(11);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "Soft";
            break;

        case 2: // '\002'
            s = "Hard";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWhiteBalanceDescription()
        throws MetadataException
    {
        if(_directory.containsTag(7)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(7);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Auto";
            break;

        case 2: // '\002'
            s = "Tungsten";
            break;

        case 3: // '\003'
            s = "Daylight";
            break;

        case 4: // '\004'
            s = "Flourescent";
            break;

        case 5: // '\005'
            s = "Shade";
            break;

        case 129: 
            s = "Manual";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
