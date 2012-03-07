// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.iptc;

import com.drew.metadata.Directory;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.iptc:
//            IptcDescriptor

public class IptcDirectory extends Directory
{

    public IptcDirectory()
    {
        setDescriptor(new IptcDescriptor(this));
    }

    public String getName()
    {
        return "Iptc";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public static final int TAG_BY_LINE = 592;
    public static final int TAG_BY_LINE_TITLE = 597;
    public static final int TAG_CAPTION = 632;
    public static final int TAG_CATEGORY = 527;
    public static final int TAG_CITY = 602;
    public static final int TAG_COPYRIGHT_NOTICE = 628;
    public static final int TAG_COUNTRY_OR_PRIMARY_LOCATION = 613;
    public static final int TAG_CREDIT = 622;
    public static final int TAG_DATE_CREATED = 567;
    public static final int TAG_HEADLINE = 617;
    public static final int TAG_KEYWORDS = 537;
    public static final int TAG_OBJECT_NAME = 517;
    public static final int TAG_ORIGINAL_TRANSMISSION_REFERENCE = 615;
    public static final int TAG_ORIGINATING_PROGRAM = 577;
    public static final int TAG_PROVINCE_OR_STATE = 607;
    public static final int TAG_RECORD_VERSION = 512;
    public static final int TAG_RELEASE_DATE = 542;
    public static final int TAG_RELEASE_TIME = 547;
    public static final int TAG_SOURCE = 627;
    public static final int TAG_SPECIAL_INSTRUCTIONS = 552;
    public static final int TAG_SUPPLEMENTAL_CATEGORIES = 532;
    public static final int TAG_TIME_CREATED = 572;
    public static final int TAG_URGENCY = 522;
    public static final int TAG_WRITER = 634;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(512), "Directory Version");
        tagNameMap.put(new Integer(632), "Caption/Abstract");
        tagNameMap.put(new Integer(634), "Writer/Editor");
        tagNameMap.put(new Integer(617), "Headline");
        tagNameMap.put(new Integer(552), "Special Instructions");
        tagNameMap.put(new Integer(592), "By-line");
        tagNameMap.put(new Integer(597), "By-line Title");
        tagNameMap.put(new Integer(622), "Credit");
        tagNameMap.put(new Integer(627), "Source");
        tagNameMap.put(new Integer(517), "Object Name");
        tagNameMap.put(new Integer(567), "Date Created");
        tagNameMap.put(new Integer(602), "City");
        tagNameMap.put(new Integer(607), "Province/State");
        tagNameMap.put(new Integer(613), "Country/Primary Location");
        tagNameMap.put(new Integer(615), "Original Transmission Reference");
        tagNameMap.put(new Integer(527), "Category");
        tagNameMap.put(new Integer(532), "Supplemental Category(s)");
        tagNameMap.put(new Integer(522), "Urgency");
        tagNameMap.put(new Integer(537), "Keywords");
        tagNameMap.put(new Integer(628), "Copyright Notice");
        tagNameMap.put(new Integer(542), "Release Date");
        tagNameMap.put(new Integer(547), "Release Time");
        tagNameMap.put(new Integer(572), "Time Created");
        tagNameMap.put(new Integer(577), "Originating Program");
    }
}
