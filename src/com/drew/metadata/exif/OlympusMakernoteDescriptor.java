// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class OlympusMakernoteDescriptor extends TagDescriptor
{

    public OlympusMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 512 516: default 36
    //                   512 47
    //                   513 55
    //                   514 63
    //                   515 36
    //                   516 71;
           goto _L1 _L2 _L3 _L4 _L1 _L5
_L1:
        String s = _directory.getString(i);
_L7:
        return s;
_L2:
        s = getSpecialModeDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getJpegQualityDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getMacroModeDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getDigiZoomRatioDescription();
        if(true) goto _L7; else goto _L6
_L6:
    }

    public String getDigiZoomRatioDescription()
        throws MetadataException
    {
        if(_directory.containsTag(516)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(516);
        switch(i)
        {
        case 1: // '\001'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "Digital 2x Zoom";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getJpegQualityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(513)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(513);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "SQ";
            break;

        case 2: // '\002'
            s = "HQ";
            break;

        case 3: // '\003'
            s = "SHQ";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getMacroModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(514)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(514);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal (no macro)";
            break;

        case 1: // '\001'
            s = "Macro";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSpecialModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(512)) goto _L2; else goto _L1
_L1:
        String s = null;
_L18:
        return s;
_L2:
        int ai[];
        StringBuffer stringbuffer;
        ai = _directory.getIntArray(512);
        stringbuffer = new StringBuffer();
        ai[0];
        JVM INSTR tableswitch 0 3: default 72
    //                   0 177
    //                   1 187
    //                   2 197
    //                   3 207;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        stringbuffer.append("Unknown picture taking mode");
_L19:
        stringbuffer.append(" - ");
        ai[1];
        JVM INSTR tableswitch 0 3: default 120
    //                   0 217
    //                   1 227
    //                   2 237
    //                   3 247;
           goto _L8 _L9 _L10 _L11 _L12
_L8:
        stringbuffer.append(ai[1]);
        stringbuffer.append("th in a sequence");
_L20:
        ai[2];
        JVM INSTR tableswitch 1 4: default 168
    //                   1 257
    //                   2 267
    //                   3 277
    //                   4 287;
           goto _L13 _L14 _L15 _L16 _L17
_L13:
        s = stringbuffer.toString();
          goto _L18
_L4:
        stringbuffer.append("Normal picture taking mode");
          goto _L19
_L5:
        stringbuffer.append("Unknown picture taking mode");
          goto _L19
_L6:
        stringbuffer.append("Fast picture taking mode");
          goto _L19
_L7:
        stringbuffer.append("Panorama picture taking mode");
          goto _L19
_L9:
        stringbuffer.append("Unknown sequence number");
          goto _L20
_L10:
        stringbuffer.append("1st in a sequnce");
          goto _L20
_L11:
        stringbuffer.append("2nd in a sequence");
          goto _L20
_L12:
        stringbuffer.append("3rd in a sequence");
          goto _L20
_L14:
        stringbuffer.append("Left to right panorama direction");
          goto _L13
_L15:
        stringbuffer.append("Right to left panorama direction");
          goto _L13
_L16:
        stringbuffer.append("Bottom to top panorama direction");
          goto _L13
_L17:
        stringbuffer.append("Top to bottom panorama direction");
          goto _L13
    }
}
