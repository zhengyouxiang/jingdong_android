// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.share;

import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.jindong.lib.zxing.client.android.share:
//            AppPickerActivity

final class LoadPackagesAsyncTask extends AsyncTask
{
    private static class ByFirstStringComparator
        implements Comparator, Serializable
    {

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((String[])obj, (String[])obj1);
        }

        public int compare(String as[], String as1[])
        {
            return as[0].compareTo(as1[0]);
        }

        private ByFirstStringComparator()
        {
        }

        ByFirstStringComparator(ByFirstStringComparator byfirststringcomparator)
        {
            this();
        }
    }


    LoadPackagesAsyncTask(AppPickerActivity apppickeractivity)
    {
        appPickerActivity = apppickeractivity;
    }

    private static boolean isHidden(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L5:
        return flag;
_L2:
        String as[];
        int i;
        int j;
        as = PKG_PREFIX_WHITELIST;
        i = as.length;
        j = 0;
_L6:
        if(j < i) goto _L4; else goto _L3
_L3:
        String as1[];
        int k;
        int l;
        as1 = PKG_PREFIX_BLACKLIST;
        k = as1.length;
        l = 0;
_L7:
        if(l >= k)
        {
            flag = false;
        } else
        {
label0:
            {
                if(!s.startsWith(as1[l]))
                    break label0;
                flag = true;
            }
        }
          goto _L5
_L4:
label1:
        {
            if(!s.startsWith(as[j]))
                break label1;
            flag = false;
        }
          goto _L5
        j++;
          goto _L6
        l++;
          goto _L7
    }

    protected volatile transient Object doInBackground(Object aobj[])
    {
        return doInBackground((List[])aobj);
    }

    protected transient List doInBackground(List alist[])
    {
        List list = alist[0];
        PackageManager packagemanager = appPickerActivity.getPackageManager();
        Iterator iterator = packagemanager.getInstalledApplications(0).iterator();
        do
        {
            CharSequence charsequence;
            String s;
            do
            {
                ApplicationInfo applicationinfo;
                do
                {
                    if(!iterator.hasNext())
                    {
                        Collections.sort(list, new ByFirstStringComparator(null));
                        return list;
                    }
                    applicationinfo = (ApplicationInfo)iterator.next();
                    charsequence = applicationinfo.loadLabel(packagemanager);
                } while(charsequence == null);
                s = applicationinfo.packageName;
            } while(isHidden(s));
            String as[] = new String[2];
            as[0] = charsequence.toString();
            as[1] = s;
            list.add(as);
        } while(true);
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((List)obj);
    }

    protected void onPostExecute(List list)
    {
        ArrayList arraylist = new ArrayList(list.size());
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                ArrayAdapter arrayadapter = new ArrayAdapter(appPickerActivity, 0x1090003, arraylist);
                appPickerActivity.setListAdapter(arrayadapter);
                appPickerActivity.getProgressDialog().dismiss();
                return;
            }
            arraylist.add(((String[])iterator.next())[0]);
        } while(true);
    }

    private static final String PKG_PREFIX_BLACKLIST[];
    private static final String PKG_PREFIX_WHITELIST[];
    private final AppPickerActivity appPickerActivity;

    static 
    {
        String as[] = new String[1];
        as[0] = "com.google.android.apps.";
        PKG_PREFIX_WHITELIST = as;
        String as1[] = new String[4];
        as1[0] = "com.android.";
        as1[1] = "android";
        as1[2] = "com.google.android.";
        as1[3] = "com.htc";
        PKG_PREFIX_BLACKLIST = as1;
    }
}
