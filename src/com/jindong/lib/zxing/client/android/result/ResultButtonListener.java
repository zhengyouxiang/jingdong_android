// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.result;

import android.view.View;

// Referenced classes of package com.jindong.lib.zxing.client.android.result:
//            ResultHandler

public final class ResultButtonListener
    implements android.view.View.OnClickListener
{

    public ResultButtonListener(ResultHandler resulthandler, int i)
    {
        resultHandler = resulthandler;
        index = i;
    }

    public void onClick(View view)
    {
        resultHandler.handleButtonPress(index);
    }

    private final int index;
    private final ResultHandler resultHandler;
}
