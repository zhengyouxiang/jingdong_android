// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.metadata.*;

public class NikonType1MakernoteDescriptor extends TagDescriptor
{

    public NikonType1MakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getCcdSensitivityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(6)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(6);
        switch(i)
        {
        case 1: // '\001'
        case 3: // '\003'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "ISO80";
            break;

        case 2: // '\002'
            s = "ISO160";
            break;

        case 4: // '\004'
            s = "ISO320";
            break;

        case 5: // '\005'
            s = "ISO100";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getColorModeDescription()
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
            s = "Color";
            break;

        case 2: // '\002'
            s = "Monochrome";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getConverterDescription()
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
            s = "None";
            break;

        case 1: // '\001'
            s = "Fisheye converter";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 3 11: default 52
    //                   3 63
    //                   4 71
    //                   5 79
    //                   6 87
    //                   7 95
    //                   8 103
    //                   9 52
    //                   10 111
    //                   11 119;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L1 _L8 _L9
_L1:
        String s = _directory.getString(i);
_L11:
        return s;
_L2:
        s = getQualityDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getColorModeDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getImageAdjustmentDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getCcdSensitivityDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getWhiteBalanceDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getFocusDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getDigitalZoomDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getConverterDescription();
        if(true) goto _L11; else goto _L10
_L10:
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
            Rational rational = _directory.getRational(10);
            if(rational.getNumerator() == 0)
                s = "No digital zoom";
            else
                s = (new StringBuilder(String.valueOf(rational.toSimpleString(true)))).append("x digital zoom").toString();
        }
        return s;
    }

    public String getFocusDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(8))
        {
            s = null;
        } else
        {
            Rational rational = _directory.getRational(8);
            if(rational.getNumerator() == 1 && rational.getDenominator() == 0)
                s = "Infinite";
            else
                s = rational.toSimpleString(true);
        }
        return s;
    }

    public String getImageAdjustmentDescription()
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
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "Bright +";
            break;

        case 2: // '\002'
            s = "Bright -";
            break;

        case 3: // '\003'
            s = "Contrast +";
            break;

        case 4: // '\004'
            s = "Contrast -";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getQualityDescription()
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

        case 1: // '\001'
            s = "VGA Basic";
            break;

        case 2: // '\002'
            s = "VGA Normal";
            break;

        case 3: // '\003'
            s = "VGA Fine";
            break;

        case 4: // '\004'
            s = "SXGA Basic";
            break;

        case 5: // '\005'
            s = "SXGA Normal";
            break;

        case 6: // '\006'
            s = "SXGA Fine";
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
            s = "Preset";
            break;

        case 2: // '\002'
            s = "Daylight";
            break;

        case 3: // '\003'
            s = "Incandescense";
            break;

        case 4: // '\004'
            s = "Flourescence";
            break;

        case 5: // '\005'
            s = "Cloudy";
            break;

        case 6: // '\006'
            s = "SpeedLight";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
