// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.share;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.jindong.lib.zxing.client.android.share:
//            LoadPackagesAsyncTask

public final class AppPickerActivity extends ListActivity
{

    public AppPickerActivity()
    {
    }

    DialogInterface getProgressDialog()
    {
        return dialog;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        dialog = ProgressDialog.show(this, "", getString(0x7f090219), true, true);
        if(labelsPackages.isEmpty())
        {
            LoadPackagesAsyncTask loadpackagesasynctask = new LoadPackagesAsyncTask(this);
            List alist[] = new List[1];
            alist[0] = labelsPackages;
            loadpackagesasynctask.execute(alist);
        }
    }

    protected void onListItemClick(ListView listview, View view, int i, long l)
    {
        if(i >= 0 && i < labelsPackages.size())
        {
            String s = (new StringBuilder("market://search?q=pname:")).append(((String[])labelsPackages.get(i))[1]).toString();
            Intent intent = new Intent();
            intent.addFlags(0x80000);
            intent.putExtra("url", s);
            setResult(-1, intent);
        } else
        {
            setResult(0);
        }
        finish();
    }

    private DialogInterface dialog;
    private final List labelsPackages = new ArrayList();
}
