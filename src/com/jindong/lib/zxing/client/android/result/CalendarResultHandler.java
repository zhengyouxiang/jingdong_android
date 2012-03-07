// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.*;
import java.util.*;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class CalendarResultHandler extends ResultHandler
{

    public CalendarResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
    }

    private static void appendTime(String s, StringBuffer stringbuffer)
    {
        if(s.length() == 8)
        {
            Date date1;
            synchronized(DATE_FORMAT)
            {
                date1 = DATE_FORMAT.parse(s, new ParsePosition(0));
            }
            ParsedResult.maybeAppend(DateFormat.getDateInstance().format(Long.valueOf(date1.getTime())), stringbuffer);
        } else
        {
            Date date;
            synchronized(DATE_TIME_FORMAT)
            {
                date = DATE_TIME_FORMAT.parse(s.substring(0, 15), new ParsePosition(0));
            }
            long l = date.getTime();
            if(s.length() == 16 && s.charAt(15) == 'Z')
            {
                GregorianCalendar gregoriancalendar = new GregorianCalendar();
                l += gregoriancalendar.get(15) + gregoriancalendar.get(16);
            }
            ParsedResult.maybeAppend(DateFormat.getDateTimeInstance().format(Long.valueOf(l)), stringbuffer);
        }
        return;
        exception1;
        dateformat1;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        dateformat;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getButtonCount()
    {
        return buttons.length;
    }

    public int getButtonText(int i)
    {
        return buttons[i];
    }

    public CharSequence getDisplayContents()
    {
        CalendarParsedResult calendarparsedresult = (CalendarParsedResult)getResult();
        StringBuffer stringbuffer = new StringBuffer(100);
        ParsedResult.maybeAppend(calendarparsedresult.getSummary(), stringbuffer);
        appendTime(calendarparsedresult.getStart(), stringbuffer);
        String s = calendarparsedresult.getEnd();
        if(s == null)
            s = calendarparsedresult.getStart();
        appendTime(s, stringbuffer);
        ParsedResult.maybeAppend(calendarparsedresult.getLocation(), stringbuffer);
        ParsedResult.maybeAppend(calendarparsedresult.getAttendee(), stringbuffer);
        ParsedResult.maybeAppend(calendarparsedresult.getDescription(), stringbuffer);
        return stringbuffer.toString();
    }

    public int getDisplayTitle()
    {
        return 0x7f090237;
    }

    public void handleButtonPress(int i)
    {
        CalendarParsedResult calendarparsedresult = (CalendarParsedResult)getResult();
        if(i == 0)
            addCalendarEvent(calendarparsedresult.getSummary(), calendarparsedresult.getStart(), calendarparsedresult.getEnd(), calendarparsedresult.getLocation(), calendarparsedresult.getDescription());
    }

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    private static final int buttons[];

    static 
    {
        int ai[] = new int[1];
        ai[0] = 0x7f0901e1;
        buttons = ai;
    }
}
