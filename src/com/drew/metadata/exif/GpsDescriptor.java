// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.lang.Rational;
import com.drew.metadata.*;

public class GpsDescriptor extends TagDescriptor
{

    public GpsDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR tableswitch 2 25: default 112
    //                   2 205
    //                   3 112
    //                   4 197
    //                   5 131
    //                   6 123
    //                   7 189
    //                   8 112
    //                   9 139
    //                   10 147
    //                   11 112
    //                   12 155
    //                   13 112
    //                   14 163
    //                   15 172
    //                   16 163
    //                   17 172
    //                   18 112
    //                   19 112
    //                   20 112
    //                   21 112
    //                   22 112
    //                   23 163
    //                   24 172
    //                   25 181;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L1 _L7 _L8 _L1 _L9 _L1 _L10 _L11 _L10 _L11 _L1 _L1 _L1 _L1 _L1 _L10 _L11 _L12
_L1:
        String s = _directory.getString(i);
_L14:
        return s;
_L5:
        s = getGpsAltitudeDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getGpsAltitudeRefDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getGpsStatusDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getGpsMeasureModeDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getGpsSpeedRefDescription();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getGpsDirectionReferenceDescription(i);
        continue; /* Loop/switch isn't completed */
_L11:
        s = getGpsDirectionDescription(i);
        continue; /* Loop/switch isn't completed */
_L12:
        s = getGpsDestinationReferenceDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getGpsTimeStampDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getGpsLongitudeDescription();
        continue; /* Loop/switch isn't completed */
_L2:
        s = getGpsLatitudeDescription();
        if(true) goto _L14; else goto _L13
_L13:
    }

    public String getGpsAltitudeDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(6))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(6).toSimpleString(true)))).append(" metres").toString();
        return s;
    }

    public String getGpsAltitudeRefDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(5))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(5);
            if(i == 0)
                s = "Sea level";
            else
                s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
        }
        return s;
    }

    public String getGpsDestinationReferenceDescription()
    {
        String s1;
        if(!_directory.containsTag(25))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(25).trim();
            if("K".equalsIgnoreCase(s))
                s1 = "kilometers";
            else
            if("M".equalsIgnoreCase(s))
                s1 = "miles";
            else
            if("N".equalsIgnoreCase(s))
                s1 = "knots";
            else
                s1 = (new StringBuilder("Unknown (")).append(s).append(")").toString();
        }
        return s1;
    }

    public String getGpsDirectionDescription(int i)
    {
        String s;
        if(!_directory.containsTag(i))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(i).trim()))).append(" degrees").toString();
        return s;
    }

    public String getGpsDirectionReferenceDescription(int i)
    {
        String s1;
        if(!_directory.containsTag(i))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(i).trim();
            if("T".equalsIgnoreCase(s))
                s1 = "True direction";
            else
            if("M".equalsIgnoreCase(s))
                s1 = "Magnetic direction";
            else
                s1 = (new StringBuilder("Unknown (")).append(s).append(")").toString();
        }
        return s1;
    }

    public String getGpsLatitudeDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(2))
            s = null;
        else
            s = getHoursMinutesSecondsDescription(2);
        return s;
    }

    public String getGpsLongitudeDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(4))
            s = null;
        else
            s = getHoursMinutesSecondsDescription(4);
        return s;
    }

    public String getGpsMeasureModeDescription()
    {
        String s1;
        if(!_directory.containsTag(10))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(10).trim();
            if("2".equalsIgnoreCase(s))
                s1 = "2-dimensional measurement";
            else
            if("3".equalsIgnoreCase(s))
                s1 = "3-dimensional measurement";
            else
                s1 = (new StringBuilder("Unknown (")).append(s).append(")").toString();
        }
        return s1;
    }

    public String getGpsSpeedRefDescription()
    {
        String s1;
        if(!_directory.containsTag(12))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(12).trim();
            if("K".equalsIgnoreCase(s))
                s1 = "kph";
            else
            if("M".equalsIgnoreCase(s))
                s1 = "mph";
            else
            if("N".equalsIgnoreCase(s))
                s1 = "knots";
            else
                s1 = (new StringBuilder("Unknown (")).append(s).append(")").toString();
        }
        return s1;
    }

    public String getGpsStatusDescription()
    {
        String s1;
        if(!_directory.containsTag(9))
        {
            s1 = null;
        } else
        {
            String s = _directory.getString(9).trim();
            if("A".equalsIgnoreCase(s))
                s1 = "Measurement in progess";
            else
            if("V".equalsIgnoreCase(s))
                s1 = "Measurement Interoperability";
            else
                s1 = (new StringBuilder("Unknown (")).append(s).append(")").toString();
        }
        return s1;
    }

    public String getGpsTimeStampDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(7))
        {
            s = null;
        } else
        {
            int ai[] = _directory.getIntArray(7);
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(ai[0]);
            stringbuffer.append(":");
            stringbuffer.append(ai[1]);
            stringbuffer.append(":");
            stringbuffer.append(ai[2]);
            stringbuffer.append(" UTC");
            s = stringbuffer.toString();
        }
        return s;
    }

    public String getHoursMinutesSecondsDescription(int i)
        throws MetadataException
    {
        Rational arational[] = _directory.getRationalArray(i);
        int j = arational[0].intValue();
        float f = arational[1].floatValue();
        float f1 = arational[2].floatValue() + 60F * (f % 1.0F);
        return (new StringBuilder(String.valueOf(String.valueOf(j)))).append("\"").append(String.valueOf((int)f)).append("'").append(String.valueOf(f1)).toString();
    }
}
