// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;

import com.drew.imaging.jpeg.*;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;
import java.io.File;
import java.io.PrintStream;
import java.util.Iterator;

// Referenced classes of package com.drew.metadata:
//            Metadata, Directory, Tag

public class SampleUsage
{

    public SampleUsage(String s)
    {
        File file = new File(s);
        JpegSegmentReader jpegsegmentreader;
        byte abyte0[];
        byte abyte1[];
        Metadata metadata1;
        try
        {
            printImageTags(1, JpegMetadataReader.readMetadata(file));
        }
        catch(JpegProcessingException jpegprocessingexception)
        {
            System.err.println("error 1a");
        }
        try
        {
            Metadata metadata = new Metadata();
            (new ExifReader(file)).extract(metadata);
            (new IptcReader(file)).extract(metadata);
            printImageTags(2, metadata);
        }
        catch(JpegProcessingException jpegprocessingexception1)
        {
            System.err.println("error 2a");
        }
        jpegsegmentreader = new JpegSegmentReader(file);
        abyte0 = jpegsegmentreader.readSegment((byte)-31);
        abyte1 = jpegsegmentreader.readSegment((byte)-19);
        metadata1 = new Metadata();
        (new ExifReader(abyte0)).extract(metadata1);
        (new IptcReader(abyte1)).extract(metadata1);
        printImageTags(3, metadata1);
_L1:
        return;
        JpegProcessingException jpegprocessingexception2;
        jpegprocessingexception2;
        System.err.println("error 3a");
          goto _L1
    }

    public static void main(String args[])
    {
        new SampleUsage("src/com/drew/metadata/test/withIptcExifGps.jpg");
    }

    private void printImageTags(int i, Metadata metadata)
    {
        Iterator iterator;
        System.out.println();
        System.out.println((new StringBuilder("*** APPROACH ")).append(i).append(" ***").toString());
        System.out.println();
        iterator = metadata.getDirectoryIterator();
_L2:
        if(!iterator.hasNext())
            return;
        Directory directory = (Directory)iterator.next();
        Iterator iterator1 = directory.getTagIterator();
        do
        {
label0:
            {
                if(iterator1.hasNext())
                    break label0;
                if(directory.hasErrors())
                {
                    Iterator iterator2 = directory.getErrors();
                    while(iterator2.hasNext()) 
                        System.out.println((new StringBuilder("ERROR: ")).append(iterator2.next()).toString());
                }
            }
            if(true)
                continue;
            Tag tag = (Tag)iterator1.next();
            System.out.println(tag);
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }
}
