// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import android.telephony.PhoneNumberUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import java.text.*;
import java.util.Date;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class AddressBookResultHandler extends ResultHandler
{

    public AddressBookResultHandler(Activity activity, ParsedResult parsedresult)
    {
        super(activity, parsedresult);
        AddressBookParsedResult addressbookparsedresult = (AddressBookParsedResult)parsedresult;
        String as[] = addressbookparsedresult.getAddresses();
        boolean flag;
        String as1[];
        boolean flag1;
        String as2[];
        boolean flag2;
        int i;
        if(as != null && as.length > 0 && as[0].length() > 0)
            flag = true;
        else
            flag = false;
        as1 = addressbookparsedresult.getPhoneNumbers();
        if(as1 != null && as1.length > 0)
            flag1 = true;
        else
            flag1 = false;
        as2 = addressbookparsedresult.getEmails();
        if(as2 != null && as2.length > 0)
            flag2 = true;
        else
            flag2 = false;
        fields[0] = true;
        fields[1] = flag;
        fields[2] = flag1;
        fields[3] = flag2;
        buttonCount = 0;
        i = 0;
        do
        {
            if(i >= 4)
                return;
            if(fields[i])
                buttonCount = 1 + buttonCount;
            i++;
        } while(true);
    }

    private int mapIndexToAction(int i)
    {
        if(i >= buttonCount) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        k = -1;
        l = 0;
_L7:
        if(l < 4) goto _L3; else goto _L2
_L2:
        int j = -1;
_L5:
        return j;
_L3:
        if(fields[l])
            k++;
        if(k != i)
            break; /* Loop/switch isn't completed */
        j = l;
        if(true) goto _L5; else goto _L4
_L4:
        l++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static Date parseDate(String s)
    {
        int i;
        DateFormat adateformat[];
        int j;
        i = 0;
        adateformat = DATE_FORMATS;
        j = adateformat.length;
_L6:
        if(i < j) goto _L2; else goto _L1
_L1:
        Date date1 = null;
_L4:
        return date1;
_L2:
        DateFormat dateformat = adateformat[i];
        dateformat;
        JVM INSTR monitorenter ;
        dateformat.setLenient(false);
        Date date = dateformat.parse(s, new ParsePosition(0));
        if(date == null)
            break; /* Loop/switch isn't completed */
        date1 = date;
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public int getButtonCount()
    {
        return buttonCount;
    }

    public int getButtonText(int i)
    {
        mapIndexToAction(i);
        JVM INSTR tableswitch 0 3: default 36
    //                   0 44
    //                   1 49
    //                   2 55
    //                   3 61;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new ArrayIndexOutOfBoundsException();
_L2:
        int j = 0x7f0901e2;
_L7:
        return j;
_L3:
        j = 0x7f0901f9;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 0x7f0901e8;
        continue; /* Loop/switch isn't completed */
_L5:
        j = 0x7f0901ea;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public CharSequence getDisplayContents()
    {
        AddressBookParsedResult addressbookparsedresult;
        StringBuffer stringbuffer;
        int i;
        String as[];
        addressbookparsedresult = (AddressBookParsedResult)getResult();
        stringbuffer = new StringBuffer(100);
        ParsedResult.maybeAppend(addressbookparsedresult.getNames(), stringbuffer);
        i = stringbuffer.length();
        String s = addressbookparsedresult.getPronunciation();
        if(s != null && s.length() > 0)
        {
            stringbuffer.append("\n(");
            stringbuffer.append(s);
            stringbuffer.append(')');
        }
        ParsedResult.maybeAppend(addressbookparsedresult.getTitle(), stringbuffer);
        ParsedResult.maybeAppend(addressbookparsedresult.getOrg(), stringbuffer);
        ParsedResult.maybeAppend(addressbookparsedresult.getAddresses(), stringbuffer);
        as = addressbookparsedresult.getPhoneNumbers();
        if(as == null) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        j = as.length;
        k = 0;
_L5:
        if(k < j) goto _L3; else goto _L2
_L2:
        ParsedResult.maybeAppend(addressbookparsedresult.getEmails(), stringbuffer);
        ParsedResult.maybeAppend(addressbookparsedresult.getURL(), stringbuffer);
        String s1 = addressbookparsedresult.getBirthday();
        if(s1 != null && s1.length() > 0)
        {
            Date date = parseDate(s1);
            if(date != null)
                ParsedResult.maybeAppend(DateFormat.getDateInstance().format(Long.valueOf(date.getTime())), stringbuffer);
        }
        ParsedResult.maybeAppend(addressbookparsedresult.getNote(), stringbuffer);
        Object obj;
        if(i > 0)
        {
            SpannableString spannablestring = new SpannableString(stringbuffer.toString());
            spannablestring.setSpan(new StyleSpan(1), 0, i, 0);
            obj = spannablestring;
        } else
        {
            obj = stringbuffer.toString();
        }
        return ((CharSequence) (obj));
_L3:
        ParsedResult.maybeAppend(PhoneNumberUtils.formatNumber(as[k]), stringbuffer);
        k++;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public int getDisplayTitle()
    {
        return 0x7f090236;
    }

    public void handleButtonPress(int i)
    {
        AddressBookParsedResult addressbookparsedresult;
        String s;
        addressbookparsedresult = (AddressBookParsedResult)getResult();
        String as[] = addressbookparsedresult.getAddresses();
        if(as == null || as.length < 1)
            s = null;
        else
            s = as[0];
        mapIndexToAction(i);
        JVM INSTR tableswitch 0 3: default 60
    //                   0 69
    //                   1 102
    //                   2 136
    //                   3 149;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        addContact(addressbookparsedresult.getNames(), addressbookparsedresult.getPhoneNumbers(), addressbookparsedresult.getEmails(), addressbookparsedresult.getNote(), s, addressbookparsedresult.getOrg(), addressbookparsedresult.getTitle());
        continue; /* Loop/switch isn't completed */
_L3:
        String as1[] = addressbookparsedresult.getNames();
        String s1;
        if(as1 != null)
            s1 = as1[0];
        else
            s1 = null;
        searchMap(s, s1);
        continue; /* Loop/switch isn't completed */
_L4:
        dialPhone(addressbookparsedresult.getPhoneNumbers()[0]);
        continue; /* Loop/switch isn't completed */
_L5:
        sendEmail(addressbookparsedresult.getEmails()[0], null, null);
        if(true) goto _L1; else goto _L6
_L6:
    }

    private static final DateFormat DATE_FORMATS[];
    private int buttonCount;
    private final boolean fields[] = new boolean[4];

    static 
    {
        DateFormat adateformat[] = new DateFormat[4];
        adateformat[0] = new SimpleDateFormat("yyyyMMdd");
        adateformat[1] = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        adateformat[2] = new SimpleDateFormat("yyyy-MM-dd");
        adateformat[3] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DATE_FORMATS = adateformat;
    }
}
