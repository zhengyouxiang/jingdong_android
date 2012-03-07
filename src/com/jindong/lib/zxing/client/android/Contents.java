// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;


public final class Contents
{
    public static final class Type
    {

        public static final String CONTACT = "CONTACT_TYPE";
        public static final String EMAIL = "EMAIL_TYPE";
        public static final String LOCATION = "LOCATION_TYPE";
        public static final String PHONE = "PHONE_TYPE";
        public static final String SMS = "SMS_TYPE";
        public static final String TEXT = "TEXT_TYPE";

        private Type()
        {
        }
    }


    private Contents()
    {
    }

    public static final String EMAIL_KEYS[];
    public static final String PHONE_KEYS[];

    static 
    {
        String as[] = new String[3];
        as[0] = "phone";
        as[1] = "secondary_phone";
        as[2] = "tertiary_phone";
        PHONE_KEYS = as;
        String as1[] = new String[3];
        as1[0] = "email";
        as1[1] = "secondary_email";
        as1[2] = "tertiary_email";
        EMAIL_KEYS = as1;
    }
}
