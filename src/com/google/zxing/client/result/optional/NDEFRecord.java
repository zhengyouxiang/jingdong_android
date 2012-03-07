// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result.optional;


// Referenced classes of package com.google.zxing.client.result.optional:
//            AbstractNDEFResultParser

final class NDEFRecord
{

    private NDEFRecord(int i, String s, byte abyte0[], int j)
    {
        header = i;
        type = s;
        payload = abyte0;
        totalRecordLength = j;
    }

    static NDEFRecord readRecord(byte abyte0[], int i)
    {
        int j = 0xff & abyte0[i];
        NDEFRecord ndefrecord;
        if((0x3f & (j ^ 0x11)) != 0)
        {
            ndefrecord = null;
        } else
        {
            int k = 0xff & abyte0[i + 1];
            int l = 0xff & abyte0[i + 2];
            String s = AbstractNDEFResultParser.bytesToString(abyte0, i + 3, k, "US-ASCII");
            byte abyte1[] = new byte[l];
            System.arraycopy(abyte0, k + (i + 3), abyte1, 0, l);
            ndefrecord = new NDEFRecord(j, s, abyte1, l + (k + 3));
        }
        return ndefrecord;
    }

    byte[] getPayload()
    {
        return payload;
    }

    int getTotalRecordLength()
    {
        return totalRecordLength;
    }

    String getType()
    {
        return type;
    }

    boolean isMessageBegin()
    {
        boolean flag;
        if((0x80 & header) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isMessageEnd()
    {
        boolean flag;
        if((0x40 & header) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static final String ACTION_WELL_KNOWN_TYPE = "act";
    public static final String SMART_POSTER_WELL_KNOWN_TYPE = "Sp";
    private static final int SUPPORTED_HEADER = 17;
    private static final int SUPPORTED_HEADER_MASK = 63;
    public static final String TEXT_WELL_KNOWN_TYPE = "T";
    public static final String URI_WELL_KNOWN_TYPE = "U";
    private final int header;
    private final byte payload[];
    private final int totalRecordLength;
    private final String type;
}
