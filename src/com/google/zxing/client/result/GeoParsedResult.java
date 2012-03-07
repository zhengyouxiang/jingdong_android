// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class GeoParsedResult extends ParsedResult
{

    GeoParsedResult(double d, double d1, double d2, String s)
    {
        super(ParsedResultType.GEO);
        latitude = d;
        longitude = d1;
        altitude = d2;
        query = s;
    }

    public double getAltitude()
    {
        return altitude;
    }

    public String getDisplayResult()
    {
        StringBuffer stringbuffer = new StringBuffer(20);
        stringbuffer.append(latitude);
        stringbuffer.append(", ");
        stringbuffer.append(longitude);
        if(altitude > 0.0D)
        {
            stringbuffer.append(", ");
            stringbuffer.append(altitude);
            stringbuffer.append('m');
        }
        if(query != null)
        {
            stringbuffer.append(" (");
            stringbuffer.append(query);
            stringbuffer.append(')');
        }
        return stringbuffer.toString();
    }

    public String getGeoURI()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("geo:");
        stringbuffer.append(latitude);
        stringbuffer.append(',');
        stringbuffer.append(longitude);
        if(altitude > 0.0D)
        {
            stringbuffer.append(',');
            stringbuffer.append(altitude);
        }
        if(query != null)
        {
            stringbuffer.append('?');
            stringbuffer.append(query);
        }
        return stringbuffer.toString();
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public String getQuery()
    {
        return query;
    }

    private final double altitude;
    private final double latitude;
    private final double longitude;
    private final String query;
}
