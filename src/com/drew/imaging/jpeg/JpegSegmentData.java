// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.imaging.jpeg;

import java.io.*;
import java.util.*;

public class JpegSegmentData
    implements Serializable
{

    public JpegSegmentData()
    {
    }

    public static JpegSegmentData FromFile(File file)
        throws IOException, ClassNotFoundException
    {
        ObjectInputStream objectinputstream = null;
        ObjectInputStream objectinputstream1 = new ObjectInputStream(new FileInputStream(file));
        JpegSegmentData jpegsegmentdata = (JpegSegmentData)objectinputstream1.readObject();
        if(objectinputstream1 != null)
            objectinputstream1.close();
        return jpegsegmentdata;
        Exception exception;
        exception;
_L2:
        if(objectinputstream != null)
            objectinputstream.close();
        throw exception;
        exception;
        objectinputstream = objectinputstream1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static void ToFile(File file, JpegSegmentData jpegsegmentdata)
        throws IOException
    {
        ObjectOutputStream objectoutputstream = null;
        ObjectOutputStream objectoutputstream1 = new ObjectOutputStream(new FileOutputStream(file));
        objectoutputstream1.writeObject(jpegsegmentdata);
        if(objectoutputstream1 != null)
            objectoutputstream1.close();
        return;
        Exception exception;
        exception;
_L2:
        if(objectoutputstream != null)
            objectoutputstream.close();
        throw exception;
        exception;
        objectoutputstream = objectoutputstream1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private List getOrCreateSegmentList(byte byte0)
    {
        Byte byte1 = new Byte(byte0);
        Object obj;
        if(_segmentDataMap.containsKey(byte1))
        {
            obj = (List)_segmentDataMap.get(byte1);
        } else
        {
            obj = new ArrayList();
            _segmentDataMap.put(byte1, obj);
        }
        return ((List) (obj));
    }

    private List getSegmentList(byte byte0)
    {
        return (List)_segmentDataMap.get(new Byte(byte0));
    }

    public void addSegment(byte byte0, byte abyte0[])
    {
        getOrCreateSegmentList(byte0).add(abyte0);
    }

    public boolean containsSegment(byte byte0)
    {
        return _segmentDataMap.containsKey(new Byte(byte0));
    }

    public byte[] getSegment(byte byte0)
    {
        return getSegment(byte0, 0);
    }

    public byte[] getSegment(byte byte0, int i)
    {
        List list = getSegmentList(byte0);
        byte abyte0[];
        if(list == null || list.size() <= i)
            abyte0 = null;
        else
            abyte0 = (byte[])list.get(i);
        return abyte0;
    }

    public int getSegmentCount(byte byte0)
    {
        List list = getSegmentList(byte0);
        int i;
        if(list == null)
            i = 0;
        else
            i = list.size();
        return i;
    }

    public void removeSegment(byte byte0)
    {
        _segmentDataMap.remove(new Byte(byte0));
    }

    public void removeSegmentOccurrence(byte byte0, int i)
    {
        ((List)_segmentDataMap.get(new Byte(byte0))).remove(i);
    }

    static final long serialVersionUID = 0x62ac6ab02265562bL;
    private final HashMap _segmentDataMap = new HashMap(10);
}
