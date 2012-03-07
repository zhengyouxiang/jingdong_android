// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.iptc;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentReader;
import com.drew.metadata.*;
import java.io.File;
import java.io.InputStream;
import java.util.GregorianCalendar;

// Referenced classes of package com.drew.metadata.iptc:
//            IptcDirectory

public class IptcReader
    implements MetadataReader
{

    public IptcReader(File file)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(file)).readSegment((byte)-19));
    }

    public IptcReader(InputStream inputstream)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(inputstream)).readSegment((byte)-19));
    }

    public IptcReader(byte abyte0[])
    {
        _data = abyte0;
    }

    private int get32Bits(int i)
        throws MetadataException
    {
        if(i >= _data.length)
            throw new MetadataException("Attempt to read bytes from outside Iptc data buffer");
        else
            return (0xff & _data[i]) << 8 | 0xff & _data[i + 1];
    }

    private void processTag(Directory directory, int i, int j, int k, int l)
    {
        int i1 = j | i << 8;
        i1;
        JVM INSTR lookupswitch 4: default 52
    //                   512: 113
    //                   522: 143
    //                   542: 159
    //                   567: 159;
           goto _L1 _L2 _L3 _L4 _L4
_L1:
        String s;
        NumberFormatException numberformatexception;
        String s1;
        String as[];
        String as1[];
        String as2[];
        if(l < 1)
        {
            s1 = "";
        } else
        {
            byte abyte0[] = _data;
            s1 = new String(abyte0, k, l);
        }
        if(!directory.containsTag(i1)) goto _L6; else goto _L5
_L5:
        as2 = directory.getStringArray(i1);
        as = as2;
_L11:
        if(as != null) goto _L8; else goto _L7
_L7:
        as1 = new String[1];
_L13:
        as1[as1.length - 1] = s1;
        directory.setStringArray(i1, as1);
_L9:
        return;
_L2:
        directory.setInt(i1, (short)(_data[k] << 8 | _data[k + 1]));
          goto _L9
_L3:
        directory.setInt(i1, _data[k]);
          goto _L9
_L4:
        if(l < 8) goto _L1; else goto _L10
_L10:
        s = new String(_data, k, l);
        int l1 = Integer.parseInt(s.substring(0, 4));
        int i2 = Integer.parseInt(s.substring(4, 6)) - 1;
        int j2 = Integer.parseInt(s.substring(6, 8));
        GregorianCalendar gregoriancalendar = new GregorianCalendar(l1, i2, j2);
        directory.setDate(i1, gregoriancalendar.getTime());
          goto _L9
        numberformatexception;
          goto _L1
        MetadataException metadataexception;
        metadataexception;
        as = (String[])null;
          goto _L11
_L8:
        int j1;
        as1 = new String[1 + as.length];
        j1 = 0;
_L14:
        int k1 = as.length;
        if(j1 >= k1) goto _L13; else goto _L12
_L12:
        as1[j1] = as[j1];
        j1++;
          goto _L14
          goto _L13
_L6:
        directory.setString(i1, s1);
          goto _L9
    }

    public Metadata extract()
    {
        return extract(new Metadata());
    }

    public Metadata extract(Metadata metadata)
    {
        if(_data != null) goto _L2; else goto _L1
_L1:
        return metadata;
_L2:
        Directory directory;
        int i;
        directory = metadata.getDirectory(com/drew/metadata/iptc/IptcDirectory);
        i = 0;
_L9:
        if(i >= _data.length - 1) goto _L4; else goto _L3
_L3:
        int k1 = get32Bits(i);
        if(k1 != 7170) goto _L5; else goto _L4
_L4:
        if(i >= _data.length || _data[i] != 28 || i + 5 >= _data.length) goto _L1; else goto _L6
_L6:
        int j = i + 1;
        byte abyte0[] = _data;
        int k = j + 1;
        byte byte0;
        byte abyte1[];
        byte0 = abyte0[j];
        abyte1 = _data;
        int l = k + 1;
        byte byte1;
        int i1;
        byte1 = abyte1[k];
        i1 = get32Bits(l);
        int j1 = l + 2;
        if(j1 + i1 <= _data.length) goto _L8; else goto _L7
_L7:
        directory.addError("data for tag extends beyond end of iptc segment");
          goto _L1
_L5:
        i++;
          goto _L9
        MetadataException metadataexception;
        metadataexception;
        directory.addError("Couldn't find start of Iptc data (invalid segment)");
          goto _L1
        MetadataException metadataexception1;
        metadataexception1;
_L10:
        directory.addError("Iptc data segment ended mid-way through tag descriptor");
          goto _L1
_L8:
        processTag(directory, byte0, byte1, j1, i1);
        i = j1 + i1;
          goto _L4
        MetadataException metadataexception2;
        metadataexception2;
          goto _L10
    }

    private final byte _data[];
}
