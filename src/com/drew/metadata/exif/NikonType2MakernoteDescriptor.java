// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.metadata.*;
import java.text.DecimalFormat;

// Referenced classes of package com.drew.metadata.exif:
//            NikonType2MakernoteDirectory, ExifDescriptor

public class NikonType2MakernoteDescriptor extends TagDescriptor
{

    public NikonType2MakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    private NikonType2MakernoteDirectory getMakernoteDirectory()
    {
        return (NikonType2MakernoteDirectory)_directory;
    }

    public String getAutoFirmwareVersionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(1))
            s = null;
        else
            s = ExifDescriptor.convertBytesToVersionString(_directory.getIntArray(1));
        return s;
    }

    public String getAutoFlashCompensationDescription()
        throws MetadataException
    {
        Rational rational = getMakernoteDirectory().getAutoFlashCompensation();
        String s;
        if(rational == null)
            s = "Unknown";
        else
            s = (new StringBuilder(String.valueOf((new DecimalFormat("0.##")).format(rational.floatValue())))).append(" EV").toString();
        return s;
    }

    public String getAutoFocusPositionDescription()
        throws MetadataException
    {
        if(_directory.containsTag(136)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int ai[];
        ai = _directory.getIntArray(136);
        if(ai.length == 4 && ai[0] == 0 && ai[2] == 0 && ai[3] == 0)
            break; /* Loop/switch isn't completed */
        s = (new StringBuilder("Unknown (")).append(_directory.getString(136)).append(")").toString();
        if(true) goto _L4; else goto _L3
_L3:
        switch(ai[1])
        {
        default:
            s = (new StringBuilder("Unknown (")).append(ai[1]).append(")").toString();
            break;

        case 0: // '\0'
            s = "Centre";
            break;

        case 1: // '\001'
            s = "Top";
            break;

        case 2: // '\002'
            s = "Bottom";
            break;

        case 3: // '\003'
            s = "Left";
            break;

        case 4: // '\004'
            s = "Right";
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public String getColorModeDescription()
    {
        String s1;
        if(!_directory.containsTag(141))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(141);
            if(s.startsWith("MODE1"))
                s1 = "Mode I (sRGB)";
            else
                s1 = s;
        }
        return s1;
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 8: default 76
    //                   1: 143
    //                   2: 119
    //                   18: 111
    //                   132: 87
    //                   134: 127
    //                   136: 135
    //                   141: 103
    //                   146: 95;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        String s = _directory.getString(i);
_L11:
        return s;
_L5:
        s = getLensDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getHueAdjustmentDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getColorModeDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getAutoFlashCompensationDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getIsoSettingDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getDigitalZoomDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getAutoFocusPositionDescription();
        continue; /* Loop/switch isn't completed */
_L2:
        s = getAutoFirmwareVersionDescription();
        if(true) goto _L11; else goto _L10
_L10:
    }

    public String getDigitalZoomDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(134))
        {
            s = null;
        } else
        {
            Rational rational = _directory.getRational(134);
            if(rational.intValue() == 1)
                s = "No digital zoom";
            else
                s = (new StringBuilder(String.valueOf(rational.toSimpleString(true)))).append("x digital zoom").toString();
        }
        return s;
    }

    public String getHueAdjustmentDescription()
    {
        String s;
        if(!_directory.containsTag(146))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(146)))).append(" degrees").toString();
        return s;
    }

    public String getIsoSettingDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(2))
        {
            s = null;
        } else
        {
            int ai[] = _directory.getIntArray(2);
            if(ai[0] != 0 || ai[1] == 0)
                s = (new StringBuilder("Unknown (")).append(_directory.getString(2)).append(")").toString();
            else
                s = (new StringBuilder("ISO ")).append(ai[1]).toString();
        }
        return s;
    }

    public String getLensDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(132))
        {
            s = null;
        } else
        {
            Rational arational[] = _directory.getRationalArray(132);
            if(arational.length != 4)
            {
                s = _directory.getString(132);
            } else
            {
                StringBuffer stringbuffer = new StringBuffer();
                stringbuffer.append(arational[0].intValue());
                stringbuffer.append('-');
                stringbuffer.append(arational[1].intValue());
                stringbuffer.append("mm f/");
                stringbuffer.append(arational[2].floatValue());
                stringbuffer.append('-');
                stringbuffer.append(arational[3].floatValue());
                s = stringbuffer.toString();
            }
        }
        return s;
    }
}
