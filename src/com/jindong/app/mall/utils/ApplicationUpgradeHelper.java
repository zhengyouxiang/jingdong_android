// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Button;
import com.jindong.app.mall.MyApplication;
import java.io.File;
import java.util.StringTokenizer;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, FileGuider, HttpGroup, MyActivity, 
//            FileService

public class ApplicationUpgradeHelper
{

    public ApplicationUpgradeHelper()
    {
    }

    public static int compareSoftwareUpdate(String s, String s1)
    {
        int i = 0;
        if(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1)) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        return i;
_L2:
        if(Integer.valueOf(s.split("\\.")[0]).intValue() > Integer.valueOf(s1.split("\\.")[0]).intValue())
            i = 1;
        else
        if(versionNumToInt(s) > versionNumToInt(s1))
            i = 2;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static void download(HttpGroup httpgroup)
    {
        if(Log.D)
            Log.d("Temp", "download() -->> ");
        FileGuider fileguider = new FileGuider();
        fileguider.setSpace(1);
        fileguider.setImmutable(true);
        fileguider.setFileName((new StringBuilder("jindong_")).append(mRemoteVersion).append(".apk").toString());
        fileguider.setMode(1);
        HttpGroup.HttpSetting httpsetting = new HttpGroup.HttpSetting();
        httpsetting.setUrl(mDownloadUrl);
        httpsetting.setListener(downloadListener);
        httpsetting.setType(500);
        httpsetting.setSavePath(fileguider);
        httpRequest = httpgroup.add(httpsetting);
    }

    private static void install(String s)
    {
        if(Log.D)
            Log.d("Temp", "install() -->> ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(0x10000000);
        intent.setDataAndType(Uri.parse((new StringBuilder("file://")).append(s).toString()), "application/vnd.android.package-archive");
        if(Log.D)
            Log.d("Temp", (new StringBuilder("install() upgradeState -->> ")).append(upgradeState).toString());
        if(upgradeState == 1)
            mMyActivity.startActivityForResult(intent, 1001);
        else
            mMyActivity.startActivity(intent);
    }

    public static void tryUpgrade(MyActivity myactivity, String s, String s1, String s2, final String description)
    {
        if(Log.D)
            Log.d("Temp", "tryUpgrade() -->> ");
        mMyActivity = myactivity;
        mRemoteVersion = s;
        mLocalVersion = s1;
        mDownloadUrl = s2;
        alertDialogBuilder = new android.app.AlertDialog.Builder(mMyActivity);
        alertDialogBuilder.setPositiveButton(0x7f09000e, clickListener);
        alertDialogBuilder.setNegativeButton(0x7f09000f, clickListener);
        alertDialogBuilder.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                return true;
            }

        }
);
        upgradeState = compareSoftwareUpdate(s, s1);
        upgradeState;
        JVM INSTR tableswitch 1 2: default 112
    //                   1 113
    //                   2 131;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        mMyActivity.post(new Runnable() {

            public void run()
            {
                String s3 = MyApplication.getInstance().getString(0x7f09001e);
                ApplicationUpgradeHelper.alertDialogBuilder.setMessage((new StringBuilder(String.valueOf(s3))).append("\n\n\u5347\u7EA7\u6539\u52A8\uFF1A\n").append(description).toString());
                ApplicationUpgradeHelper.alertDialog = ApplicationUpgradeHelper.alertDialogBuilder.show();
            }

            private final String val$description;

            
            {
                description = s;
                super();
            }
        }
);
        continue; /* Loop/switch isn't completed */
_L3:
        mMyActivity.post(new Runnable() {

            public void run()
            {
                String s3 = MyApplication.getInstance().getString(0x7f09001d);
                ApplicationUpgradeHelper.alertDialogBuilder.setMessage((new StringBuilder(String.valueOf(s3))).append("\n\n\u5347\u7EA7\u6539\u52A8\uFF1A\n").append(description).toString());
                ApplicationUpgradeHelper.alertDialog = ApplicationUpgradeHelper.alertDialogBuilder.show();
            }

            private final String val$description;

            
            {
                description = s;
                super();
            }
        }
);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static void updateUI()
    {
        mMyActivity.post(new Runnable() {

            public void run()
            {
                if(!ApplicationUpgradeHelper.alertDialog.isShowing())
                    ApplicationUpgradeHelper.alertDialog.show();
                ApplicationUpgradeHelper.alertDialog.setMessage("\u4E0B\u8F7D\u4E2D\uFF0C\u8BF7\u7A0D\u4FAF...");
            }

        }
);
    }

    private static int versionNumToInt(String s)
    {
        int i = 0;
        if(s == null) goto _L2; else goto _L1
_L1:
        StringTokenizer stringtokenizer = new StringTokenizer(s, ".");
_L5:
        if(stringtokenizer.hasMoreElements()) goto _L3; else goto _L2
_L2:
        return i;
_L3:
        i = i * 100 + Integer.valueOf(stringtokenizer.nextToken()).intValue();
        if(true) goto _L5; else goto _L4
_L4:
    }

    public static final int INSTALL_REQUEST_CODE = 1001;
    private static final int MUST_UPDATE = 1;
    private static final int NEED_UPDATE = 2;
    private static final int NO_UPDATE;
    private static AlertDialog alertDialog;
    private static android.app.AlertDialog.Builder alertDialogBuilder;
    private static boolean c = false;
    private static android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            i;
            JVM INSTR tableswitch -2 -1: default 24
        //                       -2 65
        //                       -1 25;
               goto _L1 _L2 _L3
_L1:
            return;
_L3:
            if(Log.D)
                Log.d("Temp", "onClick() BUTTON_POSITIVE -->> ");
            ApplicationUpgradeHelper.download(ApplicationUpgradeHelper.mMyActivity.getHttpGroupaAsynPool());
            ApplicationUpgradeHelper.alertDialog.getButton(i).setVisibility(8);
            ApplicationUpgradeHelper.updateUI();
            continue; /* Loop/switch isn't completed */
_L2:
            if(Log.D)
                Log.d("Temp", "onClick() BUTTON_NEGATIVE -->> ");
            if(ApplicationUpgradeHelper.upgradeState == 1)
            {
                if(ApplicationUpgradeHelper.httpRequest != null)
                    ApplicationUpgradeHelper.httpRequest.stop();
                MyApplication.exitAll();
            } else
            {
                ApplicationUpgradeHelper.c = true;
                if(ApplicationUpgradeHelper.httpRequest != null)
                    ApplicationUpgradeHelper.httpRequest.stop();
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

    }
;
    private static HttpGroup.OnAllListener downloadListener = new HttpGroup.OnAllListener() {

        public void onEnd(HttpGroup.HttpResponse httpresponse)
        {
            if(!ApplicationUpgradeHelper.c)
            {
                if(Log.D)
                    Log.d("Temp", "onEnd()");
                String s = httpresponse.getSaveFile().getAbsolutePath();
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("onEnd() apkFilePath -->> ")).append(s).toString());
                ApplicationUpgradeHelper.install(s);
            }
        }

        public void onError(HttpGroup.HttpError httperror)
        {
            if(Log.D)
                Log.d("Temp", "onError()");
            ApplicationUpgradeHelper.mMyActivity.post(new Runnable() {

                public void run()
                {
                    ApplicationUpgradeHelper.alertDialog.setMessage("\u4E0B\u8F7D\u51FA\u9519\u3002\u8BF7\u53D6\u6D88\u540E\u91CD\u65B0\u5C1D\u8BD5\u3002");
                }

                final _cls2 this$1;

                    
                    {
                        this$1 = _cls2.this;
                        super();
                    }
            }
);
        }

        public void onProgress(final int max, final int progress)
        {
            if(Log.D)
                Log.d("Temp", (new StringBuilder("application upgrade onProgress() max -->> ")).append(max).toString());
            if(Log.D)
                Log.d("Temp", (new StringBuilder("application upgrade onProgress() progress -->> ")).append(progress).toString());
            final int percent = (progress * 100) / max;
            final String size = FileService.formatSize2(progress);
            ApplicationUpgradeHelper.mMyActivity.post(new Runnable() {

                public void run()
                {
                    if(!ApplicationUpgradeHelper.c)
                        if(percent == 100 && ApplicationUpgradeHelper.alertDialog.isShowing())
                        {
                            ApplicationUpgradeHelper.alertDialog.dismiss();
                        } else
                        {
                            if(!ApplicationUpgradeHelper.alertDialog.isShowing())
                                ApplicationUpgradeHelper.alertDialog.show();
                            if(Log.D)
                                Log.d("Temp", (new StringBuilder("application upgrade onProgress() UI percent -->> ")).append(percent).toString());
                            if(progress > max)
                                ApplicationUpgradeHelper.alertDialog.setMessage((new StringBuilder("\u4E0B\u8F7D\u4E2D\uFF0C\u8BF7\u7A0D\u4FAF...\n\u5DF2\u4E0B\u8F7D\uFF1A")).append(size).toString());
                            else
                                ApplicationUpgradeHelper.alertDialog.setMessage((new StringBuilder("\u4E0B\u8F7D\u4E2D\uFF0C\u8BF7\u7A0D\u4FAF...\n\u5DF2\u4E0B\u8F7D\uFF1A")).append(percent).append("%").toString());
                        }
                }

                final _cls2 this$1;
                private final int val$max;
                private final int val$percent;
                private final int val$progress;
                private final String val$size;

                    
                    {
                        this$1 = _cls2.this;
                        percent = i;
                        progress = j;
                        max = k;
                        size = s;
                        super();
                    }
            }
);
        }

        public void onStart()
        {
        }

    }
;
    private static HttpGroup.HttpRequest httpRequest;
    private static String mDownloadUrl;
    private static String mLocalVersion;
    private static MyActivity mMyActivity;
    private static String mRemoteVersion;
    private static int upgradeState;












}
