// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.encode;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import com.google.zxing.*;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.Contents;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

// Referenced classes of package com.jindong.lib.zxing.client.android.encode:
//            EncodeThread

final class QRCodeEncoder
{

    QRCodeEncoder(Activity activity1, Intent intent)
    {
        activity = activity1;
        if(intent == null)
            throw new IllegalArgumentException("No valid data to encode.");
        String s = intent.getAction();
        if(s.equals("com.jindong.lib.zxing.client.android.ENCODE"))
        {
            if(!encodeContentsFromZXingIntent(intent))
                throw new IllegalArgumentException("No valid data to encode.");
        } else
        if(s.equals("android.intent.action.SEND") && !encodeContentsFromShareIntent(intent))
            throw new IllegalArgumentException("No valid data to encode.");
    }

    static Bitmap encodeAsBitmap(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException
    {
        BitMatrix bitmatrix;
        int k;
        int l;
        int ai[];
        int i1;
        Hashtable hashtable = null;
        String s1 = guessAppropriateEncoding(s);
        if(s1 != null)
        {
            hashtable = new Hashtable(2);
            hashtable.put(EncodeHintType.CHARACTER_SET, s1);
        }
        bitmatrix = (new MultiFormatWriter()).encode(s, barcodeformat, i, j, hashtable);
        k = bitmatrix.getWidth();
        l = bitmatrix.getHeight();
        ai = new int[k * l];
        i1 = 0;
_L2:
        if(i1 >= l)
        {
            Bitmap bitmap = Bitmap.createBitmap(k, l, android.graphics.Bitmap.Config.ARGB_8888);
            bitmap.setPixels(ai, 0, k, 0, 0, k, l);
            return bitmap;
        }
        int j1 = i1 * k;
        int k1 = 0;
        do
        {
label0:
            {
                if(k1 < k)
                    break label0;
                i1++;
            }
            if(true)
                continue;
            int l1 = j1 + k1;
            int i2;
            if(bitmatrix.get(k1, i1))
                i2 = 0xff000000;
            else
                i2 = -1;
            ai[l1] = i2;
            k1++;
        } while(true);
        if(true) goto _L2; else goto _L1
_L1:
    }

    private boolean encodeContentsFromShareIntent(Intent intent)
    {
        format = BarcodeFormat.QR_CODE;
        InputStream inputstream;
        int i;
        Uri uri = (Uri)intent.getExtras().getParcelable("android.intent.extra.STREAM");
        inputstream = activity.getContentResolver().openInputStream(uri);
        i = inputstream.available();
        if(i > 0) goto _L2; else goto _L1
_L1:
        if(Log.W)
            Log.w(TAG, "Content stream is empty");
          goto _L3
_L2:
        byte abyte0[];
        int j;
        abyte0 = new byte[i];
        j = inputstream.read(abyte0, 0, i);
        if(j >= i) goto _L5; else goto _L4
_L4:
        if(Log.W)
            Log.w(TAG, "Unable to fully read available bytes from content stream");
          goto _L6
_L5:
        com.google.zxing.client.result.ParsedResult parsedresult;
        String s = new String(abyte0, 0, j, "UTF-8");
        if(Log.D)
            Log.d(TAG, "Encoding share intent content:");
        if(Log.D)
            Log.d(TAG, s);
        parsedresult = ResultParser.parseResult(new Result(s, abyte0, null, BarcodeFormat.QR_CODE));
        if(parsedresult instanceof AddressBookParsedResult) goto _L8; else goto _L7
_L7:
        if(Log.D)
            Log.d(TAG, "Result was not an address");
          goto _L9
_L8:
        if(encodeQRCodeContents((AddressBookParsedResult)parsedresult)) goto _L11; else goto _L10
_L10:
        if(Log.D)
            Log.d(TAG, "Unable to encode contents");
        boolean flag = false;
          goto _L12
        IOException ioexception;
        ioexception;
        if(Log.W)
            Log.w(TAG, ioexception);
        flag = false;
          goto _L12
        NullPointerException nullpointerexception;
        nullpointerexception;
        if(Log.W)
            Log.w(TAG, nullpointerexception);
        flag = false;
          goto _L12
_L11:
        if(contents != null && contents.length() > 0)
            flag = true;
        else
            flag = false;
          goto _L12
_L3:
        flag = false;
_L12:
        return flag;
_L6:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L9:
        flag = false;
        if(true) goto _L12; else goto _L13
_L13:
    }

    private boolean encodeContentsFromZXingIntent(Intent intent)
    {
        String s1;
        boolean flag;
        String s = intent.getStringExtra("ENCODE_FORMAT");
        try
        {
            format = BarcodeFormat.valueOf(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            format = null;
        }
        if(format != null && !BarcodeFormat.QR_CODE.equals(format)) goto _L2; else goto _L1
_L1:
        s1 = intent.getStringExtra("ENCODE_TYPE");
        if(s1 != null && s1.length() != 0) goto _L4; else goto _L3
_L3:
        flag = false;
_L5:
        return flag;
_L4:
        format = BarcodeFormat.QR_CODE;
        encodeQRCodeContents(intent, s1);
_L6:
        if(contents != null && contents.length() > 0)
            flag = true;
        else
            flag = false;
        if(true) goto _L5; else goto _L2
_L2:
        String s2 = intent.getStringExtra("ENCODE_DATA");
        if(s2 != null && s2.length() > 0)
        {
            contents = s2;
            displayContents = s2;
            title = activity.getString(0x7f090202);
        }
          goto _L6
    }

    private void encodeQRCodeContents(Intent intent, String s)
    {
        if(!s.equals("TEXT_TYPE")) goto _L2; else goto _L1
_L1:
        String s8 = intent.getStringExtra("ENCODE_DATA");
        if(s8 != null && s8.length() > 0)
        {
            contents = s8;
            displayContents = s8;
            title = activity.getString(0x7f090202);
        }
_L8:
        return;
_L2:
        Bundle bundle1;
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        int i;
        if(s.equals("EMAIL_TYPE"))
        {
            String s7 = trim(intent.getStringExtra("ENCODE_DATA"));
            if(s7 != null)
            {
                contents = (new StringBuilder("mailto:")).append(s7).toString();
                displayContents = s7;
                title = activity.getString(0x7f0901fe);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("PHONE_TYPE"))
        {
            String s6 = trim(intent.getStringExtra("ENCODE_DATA"));
            if(s6 != null)
            {
                contents = (new StringBuilder("tel:")).append(s6).toString();
                displayContents = PhoneNumberUtils.formatNumber(s6);
                title = activity.getString(0x7f090200);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("SMS_TYPE"))
        {
            String s5 = trim(intent.getStringExtra("ENCODE_DATA"));
            if(s5 != null)
            {
                contents = (new StringBuilder("sms:")).append(s5).toString();
                displayContents = PhoneNumberUtils.formatNumber(s5);
                title = activity.getString(0x7f090201);
            }
            continue; /* Loop/switch isn't completed */
        }
        if(!s.equals("CONTACT_TYPE"))
            break MISSING_BLOCK_LABEL_644;
        bundle1 = intent.getBundleExtra("ENCODE_DATA");
        if(bundle1 == null)
            continue; /* Loop/switch isn't completed */
        stringbuilder = new StringBuilder(100);
        stringbuilder1 = new StringBuilder(100);
        stringbuilder.append("MECARD:");
        String s1 = trim(bundle1.getString("name"));
        if(s1 != null)
        {
            stringbuilder.append("N:").append(escapeMECARD(s1)).append(';');
            stringbuilder1.append(s1);
        }
        String s2 = trim(bundle1.getString("postal"));
        if(s2 != null)
        {
            stringbuilder.append("ADR:").append(escapeMECARD(s2)).append(';');
            stringbuilder1.append('\n').append(s2);
        }
        i = 0;
_L5:
        if(i < Contents.PHONE_KEYS.length) goto _L4; else goto _L3
_L3:
        int j = 0;
_L6:
        if(j >= Contents.EMAIL_KEYS.length)
        {
            String s3;
            String s4;
            if(stringbuilder1.length() > 0)
            {
                stringbuilder.append(';');
                contents = stringbuilder.toString();
                displayContents = stringbuilder1.toString();
                title = activity.getString(0x7f0901fd);
            } else
            {
                contents = null;
                displayContents = null;
            }
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_569;
_L4:
        s3 = trim(bundle1.getString(Contents.PHONE_KEYS[i]));
        if(s3 != null)
        {
            stringbuilder.append("TEL:").append(escapeMECARD(s3)).append(';');
            stringbuilder1.append('\n').append(PhoneNumberUtils.formatNumber(s3));
        }
        i++;
          goto _L5
        s4 = trim(bundle1.getString(Contents.EMAIL_KEYS[j]));
        if(s4 != null)
        {
            stringbuilder.append("EMAIL:").append(escapeMECARD(s4)).append(';');
            stringbuilder1.append('\n').append(s4);
        }
        j++;
          goto _L6
        if(s.equals("LOCATION_TYPE"))
        {
            Bundle bundle = intent.getBundleExtra("ENCODE_DATA");
            if(bundle != null)
            {
                float f = bundle.getFloat("LAT", 3.402823E+038F);
                float f1 = bundle.getFloat("LONG", 3.402823E+038F);
                if(f != 3.402823E+038F && f1 != 3.402823E+038F)
                {
                    contents = (new StringBuilder("geo:")).append(f).append(',').append(f1).toString();
                    displayContents = (new StringBuilder(String.valueOf(f))).append(",").append(f1).toString();
                    title = activity.getString(0x7f0901ff);
                }
            }
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    private boolean encodeQRCodeContents(AddressBookParsedResult addressbookparsedresult)
    {
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        String as1[];
        stringbuilder = new StringBuilder(100);
        stringbuilder1 = new StringBuilder(100);
        stringbuilder.append("MECARD:");
        String as[] = addressbookparsedresult.getNames();
        if(as != null && as.length > 0)
        {
            String s4 = trim(as[0]);
            if(s4 != null)
            {
                stringbuilder.append("N:").append(escapeMECARD(s4)).append(';');
                stringbuilder1.append(s4);
            }
        }
        as1 = addressbookparsedresult.getAddresses();
        if(as1 == null) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        i1 = as1.length;
        j1 = 0;
_L9:
        if(j1 < i1) goto _L3; else goto _L2
_L2:
        String as2[] = addressbookparsedresult.getPhoneNumbers();
        if(as2 == null) goto _L5; else goto _L4
_L4:
        int k;
        int l;
        k = as2.length;
        l = 0;
_L10:
        if(l < k) goto _L6; else goto _L5
_L5:
        String as3[] = addressbookparsedresult.getEmails();
        if(as3 == null) goto _L8; else goto _L7
_L7:
        int i;
        int j;
        i = as3.length;
        j = 0;
_L11:
        if(j < i)
            break MISSING_BLOCK_LABEL_373;
_L8:
        String s = trim(addressbookparsedresult.getURL());
        if(s != null)
        {
            stringbuilder.append("URL:").append(escapeMECARD(s)).append(';');
            stringbuilder1.append('\n').append(s);
        }
        boolean flag;
        String s1;
        String s2;
        String s3;
        if(stringbuilder1.length() > 0)
        {
            stringbuilder.append(';');
            contents = stringbuilder.toString();
            displayContents = stringbuilder1.toString();
            title = activity.getString(0x7f0901fd);
            flag = true;
        } else
        {
            contents = null;
            displayContents = null;
            flag = false;
        }
        return flag;
_L3:
        s3 = trim(as1[j1]);
        if(s3 != null)
        {
            stringbuilder.append("ADR:").append(escapeMECARD(s3)).append(';');
            stringbuilder1.append('\n').append(s3);
        }
        j1++;
          goto _L9
_L6:
        s2 = trim(as2[l]);
        if(s2 != null)
        {
            stringbuilder.append("TEL:").append(escapeMECARD(s2)).append(';');
            stringbuilder1.append('\n').append(PhoneNumberUtils.formatNumber(s2));
        }
        l++;
          goto _L10
        s1 = trim(as3[j]);
        if(s1 != null)
        {
            stringbuilder.append("EMAIL:").append(escapeMECARD(s1)).append(';');
            stringbuilder1.append('\n').append(s1);
        }
        j++;
          goto _L11
    }

    private static String escapeMECARD(String s)
    {
        if(s != null && (s.indexOf(':') >= 0 || s.indexOf(';') >= 0)) goto _L2; else goto _L1
_L1:
        String s1 = s;
_L4:
        return s1;
_L2:
        int i = s.length();
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                    break label0;
                s1 = stringbuilder.toString();
            }
            if(true)
                continue;
            char c = s.charAt(j);
            if(c == ':' || c == ';')
                stringbuilder.append('\\');
            stringbuilder.append(c);
            j++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String guessAppropriateEncoding(CharSequence charsequence)
    {
        int i = 0;
_L6:
        if(i < charsequence.length()) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        if(charsequence.charAt(i) <= '\377')
            break; /* Loop/switch isn't completed */
        s = "UTF-8";
        if(true) goto _L4; else goto _L3
_L3:
        i++;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static String trim(String s)
    {
        String s2;
        if(s == null)
        {
            s2 = null;
        } else
        {
            String s1 = s.trim();
            if(s1.length() == 0)
                s2 = null;
            else
                s2 = s1;
        }
        return s2;
    }

    public String getContents()
    {
        return contents;
    }

    public String getDisplayContents()
    {
        return displayContents;
    }

    public String getFormat()
    {
        return format.toString();
    }

    public String getTitle()
    {
        return title;
    }

    public void requestBarcode(Handler handler, int i)
    {
        (new EncodeThread(contents, handler, i, format)).start();
    }

    private static final int BLACK = 0xff000000;
    private static final String TAG = com/jindong/lib/zxing/client/android/encode/QRCodeEncoder.getSimpleName();
    private static final int WHITE = -1;
    private final Activity activity;
    private String contents;
    private String displayContents;
    private BarcodeFormat format;
    private String title;

}
