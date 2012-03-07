// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentReader;
import com.drew.metadata.*;
import java.io.File;
import java.io.InputStream;

// Referenced classes of package com.drew.metadata.jpeg:
//            JpegDirectory, JpegComponent

public class JpegReader
    implements MetadataReader
{

    public JpegReader(File file)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(file)).readSegment((byte)-64));
    }

    public JpegReader(InputStream inputstream)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(inputstream)).readSegment((byte)-19));
    }

    public JpegReader(byte abyte0[])
    {
        _data = abyte0;
    }

    private int get16Bits(int i)
        throws MetadataException
    {
        if(i >= _data.length)
            throw new MetadataException("Attempt to read bytes from outside Jpeg segment data buffer");
        else
            return 0xff & _data[i];
    }

    private int get32Bits(int i)
        throws MetadataException
    {
        if(i + 1 >= _data.length)
            throw new MetadataException("Attempt to read bytes from outside Jpeg segment data buffer");
        else
            return (0xff & _data[i]) << 8 | 0xff & _data[i + 1];
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
        JpegDirectory jpegdirectory = (JpegDirectory)metadata.getDirectory(com/drew/metadata/jpeg/JpegDirectory);
        int i;
        int j;
        int k;
        jpegdirectory.setInt(0, get16Bits(0));
        jpegdirectory.setInt(1, get32Bits(1));
        jpegdirectory.setInt(3, get32Bits(3));
        i = get16Bits(5);
        jpegdirectory.setInt(5, i);
        j = 0;
        k = 6;
_L3:
        int l1;
        if(j >= i)
            continue; /* Loop/switch isn't completed */
        int l = k + 1;
        int i1 = get16Bits(k);
        int j1 = l + 1;
        int k1 = get16Bits(l);
        l1 = j1 + 1;
        JpegComponent jpegcomponent = new JpegComponent(i1, k1, get16Bits(j1));
        jpegdirectory.setObject(j + 6, jpegcomponent);
        j++;
        k = l1;
          goto _L3
        MetadataException metadataexception;
        metadataexception;
        jpegdirectory.addError((new StringBuilder("MetadataException: ")).append(metadataexception).toString());
        if(true) goto _L1; else goto _L4
_L4:
    }

    private final byte _data[];
}
