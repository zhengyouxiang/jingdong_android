// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.google.zxing.Result;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class ISBNResultHandler extends ResultHandler
{

    public ISBNResultHandler(Activity activity, ParsedResult parsedresult, Result result)
    {
        super(activity, parsedresult, result);
        showGoogleShopperButton(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                ISBNParsedResult isbnparsedresult = (ISBNParsedResult)getResult();
                openGoogleShopper(isbnparsedresult.getISBN());
            }

            final ISBNResultHandler this$0;

            
            {
                this$0 = ISBNResultHandler.this;
                super();
            }
        }
);
    }

    public int getButtonCount()
    {
        int i;
        if(hasCustomProductSearch())
            i = buttons.length;
        else
            i = buttons.length - 1;
        return i;
    }

    public int getButtonText(int i)
    {
        return buttons[i];
    }

    public int getDisplayTitle()
    {
        return 0x7f09023a;
    }

    public void handleButtonPress(final int index)
    {
        showNotOurResults(index, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                ISBNParsedResult isbnparsedresult = (ISBNParsedResult)getResult();
                index;
                JVM INSTR tableswitch 0 3: default 44
            //                           0 45
            //                           1 59
            //                           2 73
            //                           3 87;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                openProductSearch(isbnparsedresult.getISBN());
                continue; /* Loop/switch isn't completed */
_L3:
                openBookSearch(isbnparsedresult.getISBN());
                continue; /* Loop/switch isn't completed */
_L4:
                searchBookContents(isbnparsedresult.getISBN());
                continue; /* Loop/switch isn't completed */
_L5:
                openURL(fillInCustomSearchURL(isbnparsedresult.getISBN()));
                if(true) goto _L1; else goto _L6
_L6:
            }

            final ISBNResultHandler this$0;
            private final int val$index;

            
            {
                this$0 = ISBNResultHandler.this;
                index = i;
                super();
            }
        }
);
    }

    private static final int buttons[];

    static 
    {
        int ai[] = new int[4];
        ai[0] = 0x7f0901f0;
        ai[1] = 0x7f0901e4;
        ai[2] = 0x7f0901f2;
        ai[3] = 0x7f0901e7;
        buttons = ai;
    }
}
