// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import java.util.Hashtable;

// Referenced classes of package com.google.zxing.common:
//            ECI

public final class CharacterSetECI extends ECI
{

    private CharacterSetECI(int i, String s)
    {
        super(i);
        encodingName = s;
    }

    private static void addCharacterSet(int i, String s)
    {
        CharacterSetECI characterseteci = new CharacterSetECI(i, s);
        VALUE_TO_ECI.put(new Integer(i), characterseteci);
        NAME_TO_ECI.put(s, characterseteci);
    }

    private static void addCharacterSet(int i, String as[])
    {
        CharacterSetECI characterseteci = new CharacterSetECI(i, as[0]);
        VALUE_TO_ECI.put(new Integer(i), characterseteci);
        for(int j = 0; j < as.length; j++)
            NAME_TO_ECI.put(as[j], characterseteci);

    }

    public static CharacterSetECI getCharacterSetECIByName(String s)
    {
        if(NAME_TO_ECI == null)
            initialize();
        return (CharacterSetECI)NAME_TO_ECI.get(s);
    }

    public static CharacterSetECI getCharacterSetECIByValue(int i)
    {
        if(VALUE_TO_ECI == null)
            initialize();
        if(i < 0 || i >= 900)
            throw new IllegalArgumentException("Bad ECI value: " + i);
        else
            return (CharacterSetECI)VALUE_TO_ECI.get(new Integer(i));
    }

    private static void initialize()
    {
        VALUE_TO_ECI = new Hashtable(29);
        NAME_TO_ECI = new Hashtable(29);
        addCharacterSet(0, "Cp437");
        String as[] = new String[2];
        as[0] = "ISO8859_1";
        as[1] = "ISO-8859-1";
        addCharacterSet(1, as);
        addCharacterSet(2, "Cp437");
        String as1[] = new String[2];
        as1[0] = "ISO8859_1";
        as1[1] = "ISO-8859-1";
        addCharacterSet(3, as1);
        addCharacterSet(4, "ISO8859_2");
        addCharacterSet(5, "ISO8859_3");
        addCharacterSet(6, "ISO8859_4");
        addCharacterSet(7, "ISO8859_5");
        addCharacterSet(8, "ISO8859_6");
        addCharacterSet(9, "ISO8859_7");
        addCharacterSet(10, "ISO8859_8");
        addCharacterSet(11, "ISO8859_9");
        addCharacterSet(12, "ISO8859_10");
        addCharacterSet(13, "ISO8859_11");
        addCharacterSet(15, "ISO8859_13");
        addCharacterSet(16, "ISO8859_14");
        addCharacterSet(17, "ISO8859_15");
        addCharacterSet(18, "ISO8859_16");
        String as2[] = new String[2];
        as2[0] = "SJIS";
        as2[1] = "Shift_JIS";
        addCharacterSet(20, as2);
    }

    public String getEncodingName()
    {
        return encodingName;
    }

    private static Hashtable NAME_TO_ECI;
    private static Hashtable VALUE_TO_ECI;
    private final String encodingName;
}
