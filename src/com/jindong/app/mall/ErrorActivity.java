// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.*;
import android.widget.*;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.utils.Log;
import com.jindong.app.mall.utils.StatisticsReportUtil;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall:
//            MainActivity

public class ErrorActivity extends Activity
{

    public ErrorActivity()
    {
        isKill = true;
    }

    private void doPost(String s, JSONObject jsonobject)
    {
        com.jindong.app.mall.utils.HttpGroup.HttpGroupSetting httpgroupsetting = new com.jindong.app.mall.utils.HttpGroup.HttpGroupSetting();
        httpgroupsetting.setPriority(1000);
        httpgroupsetting.setType(1000);
        com.jindong.app.mall.utils.HttpGroup.HttpGroupaAsynPool httpgroupaasynpool = new com.jindong.app.mall.utils.HttpGroup.HttpGroupaAsynPool(httpgroupsetting);
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId(s);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            private void restart()
            {
                if(isRestart())
                    startActivity(new Intent(ErrorActivity.this, com/jindong/app/mall/MainActivity));
                killProcess();
            }

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("ErrorActivity", (new StringBuilder(" -->> onEnd() code:")).append(httpresponse.getCode()).toString());
                restart();
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("ErrorActivity", (new StringBuilder(" -->> onError() error:")).append(httperror.toString()).toString());
                restart();
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
);
        httpgroupaasynpool.add(httpsetting);
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    private boolean isRestart()
    {
        boolean flag;
        if(checkBox != null)
            flag = isRestart;
        else
            flag = false;
        return flag;
    }

    private void killProcess()
    {
        finish();
        Process.killProcess(Process.myTid());
        System.exit(0);
    }

    private void myOnClick(int i)
    {
        i;
        JVM INSTR tableswitch -1 0: default 24
    //                   -1 79
    //                   0 25;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        errorStr = (new StringBuilder()).append(editText.getText()).append("|| version code: ").append(StatisticsReportUtil.getSoftwareVersionCode()).append(" ||").append(errorStr).toString();
        onSubmitError();
        continue; /* Loop/switch isn't completed */
_L2:
        killProcess();
        if(true) goto _L1; else goto _L4
_L4:
    }

    private void onActivity()
    {
        android.view.View.OnClickListener onclicklistener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                myOnClick(0);
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
;
        android.view.View.OnClickListener onclicklistener1 = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                myOnClick(-1);
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
;
        btnSubmit.setOnClickListener(onclicklistener);
        btnCancel.setOnClickListener(onclicklistener1);
        textView.setText((new StringBuilder()).append(textView.getText()).append("||").append(msg).toString());
    }

    private void onDialog(View view)
    {
        (new android.app.AlertDialog.Builder(this)).setView(view).setMessage(msg).setTitle(0x7f0901d5).setPositiveButton(0x7f0901d6, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                myOnClick(0);
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
).setNegativeButton(0x7f0901d7, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                myOnClick(-1);
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
).setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                boolean flag;
                if(i == 4)
                    flag = true;
                else
                if(i == 84)
                    flag = true;
                else
                    flag = false;
                return flag;
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
).show();
    }

    private void onSubmitError()
    {
        final JSONObject json = new JSONObject();
        if(errorStr.length() > 20000)
            errorStr = errorStr.substring(0, 20000);
        json.put("msg", errorStr);
        json.put("partner", Configuration.getProperty("partner"));
        loading = ProgressDialog.show(this, null, getString(0x7f0901d8));
        loading.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

            public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
            {
                if(i == 4)
                    dialoginterface.dismiss();
                return false;
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
);
        (new Thread() {

            public void run()
            {
                isKill = false;
                doPost("crash", json);
            }

            final ErrorActivity this$0;
            private final JSONObject val$json;

            
            {
                this$0 = ErrorActivity.this;
                json = jsonobject;
                super();
            }
        }
).start();
        Exception exception;
        Exception exception1;
        if(isRestart)
            Toast.makeText(this, getString(0x7f0901d9), 1).show();
        else
            Toast.makeText(this, getString(0x7f0901da), 1).show();
        finish();
        return;
        exception1;
        exception1.printStackTrace();
        if(isRestart)
            Toast.makeText(this, getString(0x7f0901d9), 1).show();
        else
            Toast.makeText(this, getString(0x7f0901da), 1).show();
        finish();
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_130;
        exception;
        if(isRestart)
            Toast.makeText(this, getString(0x7f0901d9), 1).show();
        else
            Toast.makeText(this, getString(0x7f0901da), 1).show();
        finish();
        throw exception;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        user = getIntent().getStringExtra("user");
        errorStr = getIntent().getStringExtra("error");
        msg = getString(0x7f0901d4);
        if(false)
        {
            setTheme(0x103000b);
            setContentView(0x7f030002);
            findViewById(0x7f0c0016).setVisibility(0);
            textView = (TextView)findViewById(0x7f0c0013);
            btnSubmit = (Button)findViewById(0x7f0c0017);
            btnCancel = (Button)findViewById(0x7f0c0018);
            checkBox = (CheckBox)findViewById(0x7f0c0014);
            editText = (EditText)findViewById(0x7f0c0015);
            onActivity();
        } else
        {
            setContentView(0x7f030000);
            findViewById(0x7f0c000c).setVisibility(8);
            View view = View.inflate(this, 0x7f030002, null);
            editText = (EditText)view.findViewById(0x7f0c0015);
            checkBox = (CheckBox)view.findViewById(0x7f0c0014);
            isRestart = checkBox.isChecked();
            onDialog(view);
        }
        checkBox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                isRestart = flag;
            }

            final ErrorActivity this$0;

            
            {
                this$0 = ErrorActivity.this;
                super();
            }
        }
);
        Toast.makeText(this, msg, 1).show();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        killProcess();
        return false;
    }

    protected void onStop()
    {
        if(isKill)
            killProcess();
        super.onStop();
    }

    private Button btnCancel;
    private Button btnSubmit;
    private CheckBox checkBox;
    private EditText editText;
    private String errorStr;
    private boolean isKill;
    private boolean isRestart;
    private ProgressDialog loading;
    private String msg;
    private TextView textView;
    private String user;






}
