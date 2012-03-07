// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import com.google.zxing.Result;
import com.google.zxing.client.result.*;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            AddressBookResultHandler, EmailAddressResultHandler, ProductResultHandler, URIResultHandler, 
//            WifiResultHandler, TextResultHandler, GeoResultHandler, TelResultHandler, 
//            SMSResultHandler, CalendarResultHandler, ISBNResultHandler, ResultHandler

public final class ResultHandlerFactory
{

    private ResultHandlerFactory()
    {
    }

    public static ResultHandler makeResultHandler(Activity activity, Result result)
    {
        ParsedResult parsedresult = parseResult(result);
        ParsedResultType parsedresulttype = parsedresult.getType();
        Object obj;
        if(parsedresulttype.equals(ParsedResultType.ADDRESSBOOK))
            obj = new AddressBookResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.EMAIL_ADDRESS))
            obj = new EmailAddressResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.PRODUCT))
            obj = new ProductResultHandler(activity, parsedresult, result);
        else
        if(parsedresulttype.equals(ParsedResultType.URI))
            obj = new URIResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.WIFI))
            obj = new WifiResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.TEXT))
            obj = new TextResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.GEO))
            obj = new GeoResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.TEL))
            obj = new TelResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.SMS))
            obj = new SMSResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.CALENDAR))
            obj = new CalendarResultHandler(activity, parsedresult);
        else
        if(parsedresulttype.equals(ParsedResultType.ISBN))
            obj = new ISBNResultHandler(activity, parsedresult, result);
        else
            obj = new TextResultHandler(activity, parsedresult);
        return ((ResultHandler) (obj));
    }

    private static ParsedResult parseResult(Result result)
    {
        return ResultParser.parseResult(result);
    }
}
