// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.history;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Message;
import com.google.zxing.Result;
import com.jindong.lib.zxing.client.android.CaptureActivity;
import java.util.List;

// Referenced classes of package com.jindong.lib.zxing.client.android.history:
//            HistoryManager

final class HistoryClickListener
    implements android.content.DialogInterface.OnClickListener
{

    HistoryClickListener(HistoryManager historymanager, CaptureActivity captureactivity, String as[], List list)
    {
        historyManager = historymanager;
        activity = captureactivity;
        dialogItems = as;
        items = list;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if(i == dialogItems.length - 1)
            historyManager.clearHistory();
        else
        if(i == dialogItems.length - 2)
        {
            Uri uri = HistoryManager.saveHistory(historyManager.buildHistory().toString());
            if(uri == null)
            {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
                builder.setMessage(0x7f090224);
                builder.setPositiveButton(0x7f0901ee, null);
                builder.show();
            } else
            {
                Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
                intent.addFlags(0x80000);
                String s = activity.getResources().getString(0x7f090204);
                intent.putExtra("android.intent.extra.SUBJECT", s);
                intent.putExtra("android.intent.extra.TEXT", s);
                intent.putExtra("android.intent.extra.STREAM", uri);
                intent.setType("text/csv");
                activity.startActivity(intent);
            }
        } else
        {
            Result result = (Result)items.get(i);
            Message.obtain(activity.getHandler(), 0x7f0c0003, result).sendToTarget();
        }
    }

    private final CaptureActivity activity;
    private final String dialogItems[];
    private final HistoryManager historyManager;
    private final List items;
}
