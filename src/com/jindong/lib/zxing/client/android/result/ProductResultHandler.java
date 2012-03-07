// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class ProductResultHandler extends ResultHandler
{

    public ProductResultHandler(Activity activity, ParsedResult parsedresult, Result result)
    {
        super(activity, parsedresult, result);
        showGoogleShopperButton(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                ProductParsedResult productparsedresult = (ProductParsedResult)getResult();
                openGoogleShopper(productparsedresult.getNormalizedProductID());
            }

            final ProductResultHandler this$0;

            
            {
                this$0 = ProductResultHandler.this;
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
        return 0x7f09023b;
    }

    public void handleButtonPress(final int index)
    {
        showNotOurResults(index, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                ProductParsedResult productparsedresult = (ProductParsedResult)getResult();
                index;
                JVM INSTR tableswitch 0 2: default 40
            //                           0 41
            //                           1 55
            //                           2 69;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                openProductSearch(productparsedresult.getNormalizedProductID());
                continue; /* Loop/switch isn't completed */
_L3:
                webSearch(productparsedresult.getNormalizedProductID());
                continue; /* Loop/switch isn't completed */
_L4:
                openURL(fillInCustomSearchURL(productparsedresult.getNormalizedProductID()));
                if(true) goto _L1; else goto _L5
_L5:
            }

            final ProductResultHandler this$0;
            private final int val$index;

            
            {
                this$0 = ProductResultHandler.this;
                index = i;
                super();
            }
        }
);
    }

    private static final int buttons[];

    static 
    {
        int ai[] = new int[3];
        ai[0] = 0x7f0901f0;
        ai[1] = 0x7f0901fb;
        ai[2] = 0x7f0901e7;
        buttons = ai;
    }
}
