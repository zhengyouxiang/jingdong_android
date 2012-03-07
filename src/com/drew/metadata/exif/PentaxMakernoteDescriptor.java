// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class PentaxMakernoteDescriptor extends TagDescriptor
{

    public PentaxMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getCaptureModeDescription()
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
            s = "Auto";
            break;

        case 2: // '\002'
            s = "Night-scene";
            break;

        case 3: // '\003'
            s = "Manual";
            break;

        case 4: // '\004'
            s = "Multiple";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getColourDescription()
        throws MetadataException
    {
        if(_directory.containsTag(23)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(23);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "Black & White";
            break;

        case 3: // '\003'
            s = "Sepia";
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
        JVM INSTR tableswitch 1 23: default 108
    //                   1 119
    //                   2 127
    //                   3 135
    //                   4 143
    //                   5 108
    //                   6 108
    //                   7 151
    //                   8 108
    //                   9 108
    //                   10 159
    //                   11 167
    //                   12 175
    //                   13 183
    //                   14 108
    //                   15 108
    //                   16 108
    //                   17 108
    //                   18 108
    //                   19 108
    //                   20 191
    //                   21 108
    //                   22 108
    //                   23 199;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L6 _L1 _L1 _L7 _L8 _L9 _L10 _L1 _L1 _L1 _L1 _L1 _L1 _L11 _L1 _L1 _L12
_L1:
        String s = _directory.getString(i);
_L14:
        return s;
_L2:
        s = getCaptureModeDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getQualityLevelDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getFocusModeDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getFlashModeDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getWhiteBalanceDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getDigitalZoomDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getSharpnessDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getContrastDescription();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getSaturationDescription();
        continue; /* Loop/switch isn't completed */
_L11:
        s = getIsoSpeedDescription();
        continue; /* Loop/switch isn't completed */
_L12:
        s = getColourDescription();
        if(true) goto _L14; else goto _L13
_L13:
    }

    public String getDigitalZoomDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(10))
        {
            s = null;
        } else
        {
            float f = _directory.getFloat(10);
            if(f == 0.0F)
                s = "Off";
            else
                s = Float.toString(f);
        }
        return s;
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
        case 3: // '\003'
        case 5: // '\005'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Auto";
            break;

        case 2: // '\002'
            s = "Flash On";
            break;

        case 4: // '\004'
            s = "Flash Off";
            break;

        case 6: // '\006'
            s = "Red-eye Reduction";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocusModeDescription()
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
            s = "Custom";
            break;

        case 3: // '\003'
            s = "Auto";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getIsoSpeedDescription()
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

        case 10: // '\n'
            s = "ISO 100";
            break;

        case 16: // '\020'
            s = "ISO 200";
            break;

        case 100: // 'd'
            s = "ISO 100";
            break;

        case 200: 
            s = "ISO 200";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getQualityLevelDescription()
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

        case 0: // '\0'
            s = "Good";
            break;

        case 1: // '\001'
            s = "Better";
            break;

        case 2: // '\002'
            s = "Best";
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

        case 0: // '\0'
            s = "Auto";
            break;

        case 1: // '\001'
            s = "Daylight";
            break;

        case 2: // '\002'
            s = "Shade";
            break;

        case 3: // '\003'
            s = "Tungsten";
            break;

        case 4: // '\004'
            s = "Fluorescent";
            break;

        case 5: // '\005'
            s = "Manual";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
