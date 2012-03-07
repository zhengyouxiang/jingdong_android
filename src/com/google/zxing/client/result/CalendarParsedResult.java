// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class CalendarParsedResult extends ParsedResult
{

    public CalendarParsedResult(String s, String s1, String s2, String s3, String s4, String s5)
    {
        super(ParsedResultType.CALENDAR);
        if(s1 == null)
            throw new IllegalArgumentException();
        validateDate(s1);
        String s6;
        if(s2 == null)
        {
            s6 = s1;
        } else
        {
            validateDate(s2);
            s6 = s2;
        }
        summary = s;
        start = s1;
        end = s6;
        location = s3;
        attendee = s4;
        description = s5;
    }

    private static void validateDate(String s)
    {
        if(s != null)
        {
            int i = s.length();
            if(i != 8 && i != 15 && i != 16)
                throw new IllegalArgumentException();
            for(int j = 0; j < 8; j++)
                if(!Character.isDigit(s.charAt(j)))
                    throw new IllegalArgumentException();

            if(i > 8)
            {
                if(s.charAt(8) != 'T')
                    throw new IllegalArgumentException();
                for(int k = 9; k < 15; k++)
                    if(!Character.isDigit(s.charAt(k)))
                        throw new IllegalArgumentException();

                if(i == 16 && s.charAt(15) != 'Z')
                    throw new IllegalArgumentException();
            }
        }
    }

    public String getAttendee()
    {
        return attendee;
    }

    public String getDescription()
    {
        return description;
    }

    public String getDisplayResult()
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        maybeAppend(summary, stringbuffer);
        maybeAppend(start, stringbuffer);
        maybeAppend(end, stringbuffer);
        maybeAppend(location, stringbuffer);
        maybeAppend(attendee, stringbuffer);
        maybeAppend(description, stringbuffer);
        return stringbuffer.toString();
    }

    public String getEnd()
    {
        return end;
    }

    public String getLocation()
    {
        return location;
    }

    public String getStart()
    {
        return start;
    }

    public String getSummary()
    {
        return summary;
    }

    private final String attendee;
    private final String description;
    private final String end;
    private final String location;
    private final String start;
    private final String summary;
}
