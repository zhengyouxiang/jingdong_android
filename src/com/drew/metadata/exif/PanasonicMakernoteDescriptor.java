// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class PanasonicMakernoteDescriptor extends TagDescriptor
{

    public PanasonicMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   28: 47
    //                   31: 55
    //                   3584: 63;
           goto _L1 _L2 _L3 _L4
_L1:
        String s = _directory.getString(i);
_L6:
        return s;
_L2:
        s = getMacroModeDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getRecordModeDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getPrintImageMatchingInfoDescription();
        if(true) goto _L6; else goto _L5
_L5:
    }

    public String getMacroModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(28)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(28);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "On";
            break;

        case 2: // '\002'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getPrintImageMatchingInfoDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(3584))
        {
            s = null;
        } else
        {
            byte abyte0[] = _directory.getByteArray(3584);
            s = (new StringBuilder("(")).append(abyte0.length).append(" bytes)").toString();
        }
        return s;
    }

    public String getRecordModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(31)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(31);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "Portrait";
            break;

        case 9: // '\t'
            s = "Macro";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
