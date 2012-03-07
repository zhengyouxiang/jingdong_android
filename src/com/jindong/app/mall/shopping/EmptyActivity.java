// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.shopping;

import android.os.Bundle;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.MyActivity;

public class EmptyActivity extends MyActivity
{

    public EmptyActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        if(Log.D)
            Log.d("packs---", "get in");
        super.onCreate(bundle);
        if(Log.D)
            Log.d("packs---", "get in2");
    }
}
