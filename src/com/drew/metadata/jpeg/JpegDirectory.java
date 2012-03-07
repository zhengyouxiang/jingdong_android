// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.jpeg:
//            JpegDescriptor, JpegComponent

public class JpegDirectory extends Directory
{

    public JpegDirectory()
    {
        setDescriptor(new JpegDescriptor(this));
    }

    public JpegComponent getComponent(int i)
    {
        return (JpegComponent)getObject(i + 6);
    }

    public int getImageHeight()
        throws MetadataException
    {
        return getInt(1);
    }

    public int getImageWidth()
        throws MetadataException
    {
        return getInt(3);
    }

    public String getName()
    {
        return "Jpeg";
    }

    public int getNumberOfComponents()
        throws MetadataException
    {
        return getInt(5);
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_JPEG_COMPONENT_DATA_1 = 6;
    public static final int TAG_JPEG_COMPONENT_DATA_2 = 7;
    public static final int TAG_JPEG_COMPONENT_DATA_3 = 8;
    public static final int TAG_JPEG_COMPONENT_DATA_4 = 9;
    public static final int TAG_JPEG_DATA_PRECISION = 0;
    public static final int TAG_JPEG_IMAGE_HEIGHT = 1;
    public static final int TAG_JPEG_IMAGE_WIDTH = 3;
    public static final int TAG_JPEG_NUMBER_OF_COMPONENTS = 5;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(0), "Data Precision");
        tagNameMap.put(new Integer(3), "Image Width");
        tagNameMap.put(new Integer(1), "Image Height");
        tagNameMap.put(new Integer(5), "Number of Components");
        tagNameMap.put(new Integer(6), "Component 1");
        tagNameMap.put(new Integer(7), "Component 2");
        tagNameMap.put(new Integer(8), "Component 3");
        tagNameMap.put(new Integer(9), "Component 4");
    }
}
