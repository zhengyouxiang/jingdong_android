// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.jpeg;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.jpeg:
//            JpegCommentDescriptor

public class JpegCommentDirectory extends Directory
{

    public JpegCommentDirectory()
    {
        setDescriptor(new JpegCommentDescriptor(this));
    }

    public String getName()
    {
        return "JpegComment";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_JPEG_COMMENT;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(0), "Jpeg Comment");
    }
}
