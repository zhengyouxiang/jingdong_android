// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import android.content.*;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.result.*;
import com.jindong.lib.zxing.client.android.Contents;
import com.jindong.lib.zxing.client.android.LocaleManager;
import com.jindong.lib.zxing.client.android.book.SearchBookContentsActivity;
import com.jindong.lib.zxing.client.android.wifi.WifiActivity;
import java.text.*;
import java.util.*;

public abstract class ResultHandler
{

    ResultHandler(Activity activity1, ParsedResult parsedresult)
    {
        this(activity1, parsedresult, null);
    }

    ResultHandler(Activity activity1, ParsedResult parsedresult, Result result1)
    {
        shopperMarketListener = new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:com.google.android.apps.shopper&referrer=utm_source%3Dbarcodescanner%26utm_medium%3Dapps%26utm_campaign%3Dscan")));
            }

            final ResultHandler this$0;

            
            {
                this$0 = ResultHandler.this;
                super();
            }
        }
;
        result = parsedresult;
        activity = activity1;
        rawResult = result1;
        customProductSearch = parseCustomSearchURL();
        activity1.findViewById(0x7f0c0050).setVisibility(8);
    }

    private static long calculateMilliseconds(String s)
    {
        long l1;
        if(s.length() == 8)
        {
            Date date1;
            synchronized(DATE_FORMAT)
            {
                date1 = DATE_FORMAT.parse(s, new ParsePosition(0));
            }
            l1 = date1.getTime();
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
            l1 = l;
        }
        return l1;
        exception1;
        dateformat1;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        dateformat;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private String parseCustomSearchURL()
    {
        String s = PreferenceManager.getDefaultSharedPreferences(activity).getString("preferences_custom_product_search", null);
        String s1;
        if(s != null && s.trim().length() == 0)
            s1 = null;
        else
            s1 = s;
        return s1;
    }

    private static void putExtra(Intent intent, String s, String s1)
    {
        if(s1 != null && s1.length() > 0)
            intent.putExtra(s, s1);
    }

    final void addCalendarEvent(String s, String s1, String s2, String s3, String s4)
    {
        Intent intent = new Intent("android.intent.action.EDIT");
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", calculateMilliseconds(s1));
        if(s1.length() == 8)
            intent.putExtra("allDay", true);
        if(s2 == null)
            s2 = s1;
        intent.putExtra("endTime", calculateMilliseconds(s2));
        intent.putExtra("title", s);
        intent.putExtra("eventLocation", s3);
        intent.putExtra("description", s4);
        launchIntent(intent);
    }

    final void addContact(String as[], String as1[], String as2[], String s, String s1, String s2, String s3)
    {
        Intent intent;
        int k;
        int j1;
        intent = new Intent("android.intent.action.INSERT", android.provider.Contacts.People.CONTENT_URI);
        String s4;
        int i;
        int j;
        int i1;
        if(as != null)
            s4 = as[0];
        else
            s4 = null;
        putExtra(intent, "name", s4);
        if(as1 != null)
            i = as1.length;
        else
            i = 0;
        j = Math.min(i, Contents.PHONE_KEYS.length);
        k = 0;
_L3:
        if(k < j) goto _L2; else goto _L1
_L1:
        int l;
        if(as2 != null)
            l = as2.length;
        else
            l = 0;
        i1 = Math.min(l, Contents.EMAIL_KEYS.length);
        j1 = 0;
_L4:
        if(j1 >= i1)
        {
            putExtra(intent, "notes", s);
            putExtra(intent, "postal", s1);
            putExtra(intent, "company", s2);
            putExtra(intent, "job_title", s3);
            launchIntent(intent);
            return;
        }
        break MISSING_BLOCK_LABEL_172;
_L2:
        putExtra(intent, Contents.PHONE_KEYS[k], as1[k]);
        k++;
          goto _L3
        putExtra(intent, Contents.EMAIL_KEYS[j1], as2[j1]);
        j1++;
          goto _L4
    }

    final void dialPhone(String s)
    {
        launchIntent(new Intent("android.intent.action.DIAL", Uri.parse((new StringBuilder("tel:")).append(s).toString())));
    }

    final void dialPhoneFromUri(String s)
    {
        launchIntent(new Intent("android.intent.action.DIAL", Uri.parse(s)));
    }

    String fillInCustomSearchURL(String s)
    {
        String s1 = customProductSearch.replace("%s", s);
        if(rawResult != null)
            s1 = s1.replace("%f", rawResult.getBarcodeFormat().toString());
        return s1;
    }

    public abstract int getButtonCount();

    public abstract int getButtonText(int i);

    final void getDirections(double d, double d1)
    {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://maps.google.")).append(LocaleManager.getCountryTLD()).append("/maps?f=d&daddr=").append(d).append(',').append(d1).toString())));
    }

    public CharSequence getDisplayContents()
    {
        return result.getDisplayResult().replace("\r", "");
    }

    public abstract int getDisplayTitle();

    ParsedResult getResult()
    {
        return result;
    }

    public final ParsedResultType getType()
    {
        return result.getType();
    }

    public abstract void handleButtonPress(int i);

    boolean hasCustomProductSearch()
    {
        boolean flag;
        if(customProductSearch != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    void launchIntent(Intent intent)
    {
        if(intent == null)
            break MISSING_BLOCK_LABEL_20;
        intent.addFlags(0x80000);
        activity.startActivity(intent);
_L1:
        return;
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle(0x7f09003c);
        builder.setMessage(0x7f090218);
        builder.setPositiveButton(0x7f0901ee, null);
        builder.show();
          goto _L1
    }

    final void openBookSearch(String s)
    {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://books.google.")).append(LocaleManager.getBookSearchCountryTLD()).append("/books?vid=isbn").append(s).toString())));
    }

    final void openGoogleShopper(String s)
    {
        activity.getPackageManager().getPackageInfo("com.google.android.apps.shopper", 0);
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setClassName("com.google.android.apps.shopper", "com.google.android.apps.shopper.results.SearchResultsActivity");
        intent.putExtra("query", s);
        activity.startActivity(intent);
_L1:
        return;
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle(0x7f090216);
        builder.setMessage(0x7f090217);
        builder.setIcon(0x7f0200aa);
        builder.setPositiveButton(0x7f0901ee, shopperMarketListener);
        builder.setNegativeButton(0x7f0901e5, null);
        builder.show();
          goto _L1
    }

    final void openMap(String s)
    {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse(s)));
    }

    final void openProductSearch(String s)
    {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("http://www.google.")).append(LocaleManager.getProductSearchCountryTLD()).append("/m/products?q=").append(s).append("&source=zxing").toString())));
    }

    final void openURL(String s)
    {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse(s)));
    }

    final void searchBookContents(String s)
    {
        Intent intent = new Intent("com.jindong.lib.zxing.client.android.SEARCH_BOOK_CONTENTS");
        intent.setClassName(activity, com/jindong/lib/zxing/client/android/book/SearchBookContentsActivity.getName());
        putExtra(intent, "ISBN", s);
        launchIntent(intent);
    }

    final void searchMap(String s, String s1)
    {
        String s2 = s;
        if(s1 != null && s1.length() > 0)
            s2 = (new StringBuilder(String.valueOf(s2))).append(" (").append(s1).append(')').toString();
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("geo:0,0?q=")).append(Uri.encode(s2)).toString())));
    }

    final void sendEmail(String s, String s1, String s2)
    {
        sendEmailFromUri((new StringBuilder("mailto:")).append(s).toString(), s, s1, s2);
    }

    final void sendEmailFromUri(String s, String s1, String s2, String s3)
    {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse(s));
        if(s1 != null)
        {
            String as[] = new String[1];
            as[0] = s1;
            intent.putExtra("android.intent.extra.EMAIL", as);
        }
        putExtra(intent, "android.intent.extra.SUBJECT", s2);
        putExtra(intent, "android.intent.extra.TEXT", s3);
        intent.setType("text/plain");
        launchIntent(intent);
    }

    final void sendMMS(String s, String s1, String s2)
    {
        sendMMSFromUri((new StringBuilder("mmsto:")).append(s).toString(), s1, s2);
    }

    final void sendMMSFromUri(String s, String s1, String s2)
    {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(s));
        if(s1 == null || s1.length() == 0)
            putExtra(intent, "subject", activity.getString(0x7f090210));
        else
            putExtra(intent, "subject", s1);
        putExtra(intent, "sms_body", s2);
        intent.putExtra("compose_mode", true);
        launchIntent(intent);
    }

    final void sendSMS(String s, String s1)
    {
        sendSMSFromUri((new StringBuilder("smsto:")).append(s).toString(), s1);
    }

    final void sendSMSFromUri(String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(s));
        putExtra(intent, "sms_body", s1);
        intent.putExtra("compose_mode", true);
        launchIntent(intent);
    }

    final void shareByEmail(String s)
    {
        sendEmailFromUri("mailto:", null, activity.getString(0x7f090223), s);
    }

    final void shareBySMS(String s)
    {
        sendSMSFromUri("smsto:", (new StringBuilder(String.valueOf(activity.getString(0x7f090223)))).append(":\n").append(s).toString());
    }

    protected void showGoogleShopperButton(android.view.View.OnClickListener onclicklistener)
    {
        View view = activity.findViewById(0x7f0c0050);
        view.setVisibility(0);
        view.setOnClickListener(onclicklistener);
    }

    protected void showNotOurResults(int i, android.content.DialogInterface.OnClickListener onclicklistener)
    {
        SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        if(sharedpreferences.getBoolean("preferences_not_out_results_shown", false))
        {
            onclicklistener.onClick(null, i);
        } else
        {
            sharedpreferences.edit().putBoolean("preferences_not_out_results_shown", true).commit();
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
            builder.setMessage(0x7f09021a);
            builder.setPositiveButton(0x7f0901ee, onclicklistener);
            builder.show();
        }
    }

    final void webSearch(String s)
    {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra("query", s);
        launchIntent(intent);
    }

    final void wifiConnect(WifiParsedResult wifiparsedresult)
    {
        Intent intent = new Intent("com.jindong.lib.zxing.client.android.WIFI_CONNECT");
        intent.setClassName(activity, com/jindong/lib/zxing/client/android/wifi/WifiActivity.getName());
        putExtra(intent, "SSID", wifiparsedresult.getSsid());
        putExtra(intent, "TYPE", wifiparsedresult.getNetworkEncryption());
        putExtra(intent, "PASSWORD", wifiparsedresult.getPassword());
        launchIntent(intent);
    }

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    private static final String GOOGLE_SHOPPER_ACTIVITY = "com.google.android.apps.shopper.results.SearchResultsActivity";
    private static final String GOOGLE_SHOPPER_PACKAGE = "com.google.android.apps.shopper";
    private static final String MARKET_REFERRER_SUFFIX = "&referrer=utm_source%3Dbarcodescanner%26utm_medium%3Dapps%26utm_campaign%3Dscan";
    private static final String MARKET_URI_PREFIX = "market://search?q=pname:";
    public static final int MAX_BUTTON_COUNT = 4;
    private final Activity activity;
    private final String customProductSearch;
    private final Result rawResult;
    private final ParsedResult result;
    private final android.content.DialogInterface.OnClickListener shopperMarketListener;

}
