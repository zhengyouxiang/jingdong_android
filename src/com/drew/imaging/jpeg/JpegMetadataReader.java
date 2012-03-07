// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.imaging.jpeg;

import com.drew.metadata.*;
import com.drew.metadata.exif.ExifDirectory;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.jpeg.JpegCommentReader;
import com.drew.metadata.jpeg.JpegReader;
import java.io.*;
import java.util.Iterator;

// Referenced classes of package com.drew.imaging.jpeg:
//            JpegProcessingException, JpegSegmentReader

public class JpegMetadataReader
{

    private JpegMetadataReader()
    {
    }

    public static Metadata extractMetadataFromJpegSegmentReader(JpegSegmentReader jpegsegmentreader)
    {
        Metadata metadata = new Metadata();
        try
        {
            (new ExifReader(jpegsegmentreader.readSegment((byte)-31))).extract(metadata);
        }
        catch(JpegProcessingException jpegprocessingexception) { }
        try
        {
            (new IptcReader(jpegsegmentreader.readSegment((byte)-19))).extract(metadata);
        }
        catch(JpegProcessingException jpegprocessingexception1) { }
        try
        {
            (new JpegReader(jpegsegmentreader.readSegment((byte)-64))).extract(metadata);
        }
        catch(JpegProcessingException jpegprocessingexception2) { }
        try
        {
            (new JpegCommentReader(jpegsegmentreader.readSegment((byte)-2))).extract(metadata);
        }
        catch(JpegProcessingException jpegprocessingexception3) { }
        return metadata;
    }

    public static void main(String args[])
        throws MetadataException, IOException
    {
        Metadata metadata = null;
        Metadata metadata1 = readMetadata(new File(args[0]));
        metadata = metadata1;
_L1:
        Iterator iterator = metadata.getDirectoryIterator();
_L2:
        if(!iterator.hasNext())
        {
            if(args.length > 1 && args[1].trim().equals("/thumb"))
            {
                ExifDirectory exifdirectory = (ExifDirectory)metadata.getDirectory(com/drew/metadata/exif/ExifDirectory);
                Exception exception;
                Directory directory;
                Iterator iterator1;
                Tag tag;
                MetadataException metadataexception;
                Iterator iterator2;
                if(exifdirectory.containsThumbnail())
                {
                    System.out.println("Writing thumbnail...");
                    exifdirectory.writeThumbnail((new StringBuilder(String.valueOf(args[0].trim()))).append(".thumb.jpg").toString());
                } else
                {
                    System.out.println("No thumbnail data exists in this image");
                }
            }
            return;
        }
        break MISSING_BLOCK_LABEL_126;
        exception;
        exception.printStackTrace(System.err);
        System.exit(1);
          goto _L1
        directory = (Directory)iterator.next();
        iterator1 = directory.getTagIterator();
_L3:
label0:
        {
            if(iterator1.hasNext())
                break label0;
            if(directory.hasErrors())
            {
                iterator2 = directory.getErrors();
                while(iterator2.hasNext()) 
                    System.out.println((new StringBuilder("ERROR: ")).append(iterator2.next()).toString());
            }
        }
          goto _L2
        tag = (Tag)iterator1.next();
        try
        {
            System.out.println((new StringBuilder("[")).append(directory.getName()).append("] ").append(tag.getTagName()).append(" = ").append(tag.getDescription()).toString());
        }
        // Misplaced declaration of an exception variable
        catch(MetadataException metadataexception)
        {
            System.err.println(metadataexception.getMessage());
            System.err.println((new StringBuilder(String.valueOf(tag.getDirectoryName()))).append(" ").append(tag.getTagName()).append(" (error)").toString());
        }
          goto _L3
    }

    public static Metadata readMetadata(File file)
        throws JpegProcessingException
    {
        return extractMetadataFromJpegSegmentReader(new JpegSegmentReader(file));
    }

    public static Metadata readMetadata(InputStream inputstream)
        throws JpegProcessingException
    {
        return extractMetadataFromJpegSegmentReader(new JpegSegmentReader(inputstream));
    }
}
