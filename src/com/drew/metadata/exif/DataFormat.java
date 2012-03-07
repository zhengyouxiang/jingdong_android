// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.MetadataException;

public class DataFormat
{

    private DataFormat(String s, int i)
    {
        myName = s;
        value = i;
    }

    public static DataFormat fromValue(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 1 12: default 64
    //                   1 93
    //                   2 99
    //                   3 106
    //                   4 113
    //                   5 120
    //                   6 127
    //                   7 134
    //                   8 141
    //                   9 148
    //                   10 155
    //                   11 162
    //                   12 169;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        throw new MetadataException((new StringBuilder("value '")).append(i).append("' does not represent a known data format.").toString());
_L2:
        DataFormat dataformat = BYTE;
_L15:
        return dataformat;
_L3:
        dataformat = STRING;
        continue; /* Loop/switch isn't completed */
_L4:
        dataformat = USHORT;
        continue; /* Loop/switch isn't completed */
_L5:
        dataformat = ULONG;
        continue; /* Loop/switch isn't completed */
_L6:
        dataformat = URATIONAL;
        continue; /* Loop/switch isn't completed */
_L7:
        dataformat = SBYTE;
        continue; /* Loop/switch isn't completed */
_L8:
        dataformat = UNDEFINED;
        continue; /* Loop/switch isn't completed */
_L9:
        dataformat = SSHORT;
        continue; /* Loop/switch isn't completed */
_L10:
        dataformat = SLONG;
        continue; /* Loop/switch isn't completed */
_L11:
        dataformat = SRATIONAL;
        continue; /* Loop/switch isn't completed */
_L12:
        dataformat = SINGLE;
        continue; /* Loop/switch isn't completed */
_L13:
        dataformat = DOUBLE;
        if(true) goto _L15; else goto _L14
_L14:
    }

    public int getValue()
    {
        return value;
    }

    public String toString()
    {
        return myName;
    }

    public static final DataFormat BYTE = new DataFormat("BYTE", 1);
    public static final DataFormat DOUBLE = new DataFormat("DOUBLE", 12);
    public static final DataFormat SBYTE = new DataFormat("SBYTE", 6);
    public static final DataFormat SINGLE = new DataFormat("SINGLE", 11);
    public static final DataFormat SLONG = new DataFormat("SLONG", 9);
    public static final DataFormat SRATIONAL = new DataFormat("SRATIONAL", 10);
    public static final DataFormat SSHORT = new DataFormat("SSHORT", 8);
    public static final DataFormat STRING = new DataFormat("STRING", 2);
    public static final DataFormat ULONG = new DataFormat("ULONG", 4);
    public static final DataFormat UNDEFINED = new DataFormat("UNDEFINED", 7);
    public static final DataFormat URATIONAL = new DataFormat("URATIONAL", 5);
    public static final DataFormat USHORT = new DataFormat("USHORT", 3);
    private final String myName;
    private final int value;

}
