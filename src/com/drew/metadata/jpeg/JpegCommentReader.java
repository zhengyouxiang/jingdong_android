// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataReader;
import java.io.File;
import java.io.InputStream;

// Referenced classes of package com.drew.metadata.jpeg:
//            JpegCommentDirectory

public class JpegCommentReader
    implements MetadataReader
{

    public JpegCommentReader(File file)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(file)).readSegment((byte)-2));
    }

    public JpegCommentReader(InputStream inputstream)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(inputstream)).readSegment((byte)-19));
    }

    public JpegCommentReader(byte abyte0[])
    {
        _data = abyte0;
    }

    public Metadata extract()
    {
        return extract(new Metadata());
    }

    public Metadata extract(Metadata metadata)
    {
        if(_data != null)
            ((JpegCommentDirectory)metadata.getDirectory(com/drew/metadata/jpeg/JpegCommentDirectory)).setString(0, new String(_data));
        return metadata;
    }

    private final byte _data[];
}
