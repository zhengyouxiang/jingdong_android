// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class EmailAddressParsedResult extends ParsedResult
{

    EmailAddressParsedResult(String s, String s1, String s2, String s3)
    {
        super(ParsedResultType.EMAIL_ADDRESS);
        emailAddress = s;
        subject = s1;
        body = s2;
        mailtoURI = s3;
    }

    public String getBody()
    {
        return body;
    }

    public String getDisplayResult()
    {
        StringBuffer stringbuffer = new StringBuffer(30);
        maybeAppend(emailAddress, stringbuffer);
        maybeAppend(subject, stringbuffer);
        maybeAppend(body, stringbuffer);
        return stringbuffer.toString();
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public String getMailtoURI()
    {
        return mailtoURI;
    }

    public String getSubject()
    {
        return subject;
    }

    private final String body;
    private final String emailAddress;
    private final String mailtoURI;
    private final String subject;
}
