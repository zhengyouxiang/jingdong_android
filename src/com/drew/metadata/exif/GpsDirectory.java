// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            GpsDescriptor

public class GpsDirectory extends Directory
{

    public GpsDirectory()
    {
        setDescriptor(new GpsDescriptor(this));
    }

    public String getName()
    {
        return "GPS";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_GPS_ALTITUDE = 6;
    public static final int TAG_GPS_ALTITUDE_REF = 5;
    public static final int TAG_GPS_DEST_BEARING = 24;
    public static final int TAG_GPS_DEST_BEARING_REF = 23;
    public static final int TAG_GPS_DEST_DISTANCE = 26;
    public static final int TAG_GPS_DEST_DISTANCE_REF = 25;
    public static final int TAG_GPS_DEST_LATITUDE = 20;
    public static final int TAG_GPS_DEST_LATITUDE_REF = 19;
    public static final int TAG_GPS_DEST_LONGITUDE = 22;
    public static final int TAG_GPS_DEST_LONGITUDE_REF = 21;
    public static final int TAG_GPS_DOP = 11;
    public static final int TAG_GPS_IMG_DIRECTION = 17;
    public static final int TAG_GPS_IMG_DIRECTION_REF = 16;
    public static final int TAG_GPS_LATITUDE = 2;
    public static final int TAG_GPS_LATITUDE_REF = 1;
    public static final int TAG_GPS_LONGITUDE = 4;
    public static final int TAG_GPS_LONGITUDE_REF = 3;
    public static final int TAG_GPS_MAP_DATUM = 18;
    public static final int TAG_GPS_MEASURE_MODE = 10;
    public static final int TAG_GPS_SATELLITES = 8;
    public static final int TAG_GPS_SPEED = 13;
    public static final int TAG_GPS_SPEED_REF = 12;
    public static final int TAG_GPS_STATUS = 9;
    public static final int TAG_GPS_TIME_STAMP = 7;
    public static final int TAG_GPS_TRACK = 15;
    public static final int TAG_GPS_TRACK_REF = 14;
    public static final int TAG_GPS_VERSION_ID;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(0), "GPS Version ID");
        tagNameMap.put(new Integer(1), "GPS Latitude Ref");
        tagNameMap.put(new Integer(2), "GPS Latitude");
        tagNameMap.put(new Integer(3), "GPS Longitude Ref");
        tagNameMap.put(new Integer(4), "GPS Longitude");
        tagNameMap.put(new Integer(5), "GPS Altitude Ref");
        tagNameMap.put(new Integer(6), "GPS Altitude");
        tagNameMap.put(new Integer(7), "GPS Time-Stamp");
        tagNameMap.put(new Integer(8), "GPS Satellites");
        tagNameMap.put(new Integer(9), "GPS Status");
        tagNameMap.put(new Integer(10), "GPS Measure Mode");
        tagNameMap.put(new Integer(11), "GPS DOP");
        tagNameMap.put(new Integer(12), "GPS Speed Ref");
        tagNameMap.put(new Integer(13), "GPS Speed");
        tagNameMap.put(new Integer(14), "GPS Track Ref");
        tagNameMap.put(new Integer(15), "GPS Track");
        tagNameMap.put(new Integer(16), "GPS Img Direction Ref");
        tagNameMap.put(new Integer(16), "GPS Img Direction");
        tagNameMap.put(new Integer(18), "GPS Map Datum");
        tagNameMap.put(new Integer(19), "GPS Dest Latitude Ref");
        tagNameMap.put(new Integer(20), "GPS Dest Latitude");
        tagNameMap.put(new Integer(21), "GPS Dest Longitude Ref");
        tagNameMap.put(new Integer(22), "GPS Dest Longitude");
        tagNameMap.put(new Integer(23), "GPS Dest Bearing Ref");
        tagNameMap.put(new Integer(24), "GPS Dest Bearing");
        tagNameMap.put(new Integer(25), "GPS Dest Distance Ref");
        tagNameMap.put(new Integer(26), "GPS Dest Distance");
    }
}
