// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.MainActivity;
import com.jindong.app.mall.personel.PersonelActivity;
import com.jindong.app.mall.register.RegisterActivity;
import com.jindong.app.mall.utils.*;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.login:
//            LoginUser

public class LoginActivity extends MyActivity
{
    public static interface LoginListener
    {

        public abstract void loginCompletedNotify();
    }


    public LoginActivity()
    {
    }

    private String EncryptPassword(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s1;
        try
        {
            if(Log.D)
                Log.d("Login...", (new StringBuilder("start to encrypt password---")).append(s).toString());
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes());
            messagedigest.digest();
            if(Log.D)
                Log.d("Login md5 code:", stringbuffer.toString());
        }
        catch(Exception exception)
        {
            if(Log.D)
                Log.d("Login...", (new StringBuilder("encrypt password error:")).append(exception.getMessage()).toString());
        }
        if(stringbuffer.toString().length() < 1)
            s1 = "";
        else
            s1 = stringbuffer.toString();
        return s1;
    }

    public static String EncryptPassword2(String s)
    {
        MessageDigest messagedigest = null;
        byte abyte0[];
        StringBuffer stringbuffer;
        int i;
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.reset();
            messagedigest.update(s.getBytes("UTF-8"));
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
        }
        abyte0 = messagedigest.digest();
        stringbuffer = new StringBuffer();
        i = 0;
        do
        {
            if(i >= abyte0.length)
                return stringbuffer.toString().toUpperCase();
            if(Integer.toHexString(0xff & abyte0[i]).length() == 1)
                stringbuffer.append("0").append(Integer.toHexString(0xff & abyte0[i]));
            else
                stringbuffer.append(Integer.toHexString(0xff & abyte0[i]));
            i++;
        } while(true);
    }

    private void LoginError(final String tip)
    {
        if(Log.D)
            Log.d("Log in", "LoginError---");
        post(new Runnable() {

            public void run()
            {
                try
                {
                    mRememberMe.setChecked(true);
                    final android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(LoginActivity.this);
                    dialogBuilder.setTitle(0x7f090062);
                    if("".equals(tip))
                        dialogBuilder.setMessage(0x7f090064);
                    else
                        dialogBuilder.setMessage(tip);
                    dialogBuilder.setPositiveButton(0x7f09000e, new android.content.DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i)
                        {
                            alertDialog.dismiss();
                        }

                        final _cls5 this$1;

                    
                    {
                        this$1 = _cls5.this;
                        super();
                    }
                    }
);
                    post(new Runnable() {

                        public void run()
                        {
                            alertDialog = dialogBuilder.show();
                        }

                        final _cls5 this$1;
                        private final android.app.AlertDialog.Builder val$dialogBuilder;

                    
                    {
                        this$1 = _cls5.this;
                        dialogBuilder = builder;
                        super();
                    }
                    }
);
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    if(Log.D)
                        Log.d("Login Error", (new StringBuilder("Error Message:")).append(exception.getMessage()).toString());
                }
            }

            final LoginActivity this$0;
            private final String val$tip;


            
            {
                this$0 = LoginActivity.this;
                tip = s;
                super();
            }
        }
);
    }

    private void LoginSuccess(final String pinName)
    {
        if(Log.D)
            Log.d("Temp", "LoginActivity  LoginSuccess() -->>");
        post(new Runnable() {

            public void run()
            {
                try
                {
                    if(bRememberMe)
                        onRemember();
                    Contants.hasLogIn = true;
                    LoginUser.setUserState(Contants.LOG_IN);
                    JSONObject jsonobject = new JSONObject();
                    jsonobject.put("pin", pinName);
                    LoginUser.setUserInfo(jsonobject);
                    Intent intent = getIntent();
                    putBooleanToPreference("login", Boolean.valueOf(true));
                    if(1 == intent.getIntExtra("com.360buy:loginResendFlag", 0))
                    {
                        noShowAgain();
                        getSharedPreferences("userInfo", 0);
                        Intent intent1 = new Intent(LoginActivity.this, com/jindong/app/mall/personel/PersonelActivity);
                        intent1.putExtra("com.360buy:singleInstanceFlag", true);
                        Bundle bundle = new Bundle();
                        bundle.putString("pinName", pinName);
                        intent1.putExtras(bundle);
                        startActivityInFrame(intent1);
                    } else
                    {
                        finish();
                    }
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                    if(Log.D)
                        Log.d("Login Error", (new StringBuilder("Error Message:")).append(exception.getMessage()).toString());
                }
            }

            final LoginActivity this$0;
            private final String val$pinName;

            
            {
                this$0 = LoginActivity.this;
                pinName = s;
                super();
            }
        }
);
    }

    public static void autoLoginError(final MyActivity myActivity, final LoginListener listener)
    {
        if(Log.D)
            Log.d("Temp", "LoginActivity  autoLoginError() -->>");
        myActivity.post(new Runnable() {

            public void run()
            {
                AlertDialog alertdialog = (new android.app.AlertDialog.Builder(myActivity)).create();
                alertdialog.setTitle(0x7f090004);
                alertdialog.setMessage(myActivity.getText(0x7f090005));
                alertdialog.setButton(myActivity.getText(0x7f09000e), new android.content.DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i)
                    {
                        Intent intent = new Intent(myActivity, com/jindong/app/mall/login/LoginActivity);
                        myActivity.startActivityInFrame(intent);
                        if(Log.D)
                            Log.d("Temp", "autoLoginError LoginCompletedNotify() -->> ");
                        listener.loginCompletedNotify();
                    }

                    final _cls7 this$1;
                    private final LoginListener val$listener;
                    private final MyActivity val$myActivity;

                    
                    {
                        this$1 = _cls7.this;
                        myActivity = myactivity;
                        listener = loginlistener;
                        super();
                    }
                }
);
                alertdialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                    {
                        return true;
                    }

                    final _cls7 this$1;

                    
                    {
                        this$1 = _cls7.this;
                        super();
                    }
                }
);
                alertdialog.show();
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                  goto _L1
            }

            private final LoginListener val$listener;
            private final MyActivity val$myActivity;

            
            {
                myActivity = myactivity;
                listener = loginlistener;
                super();
            }
        }
);
    }

    private static void clearRemember(MyActivity myactivity)
    {
        if(Log.D)
            Log.d("Log in", "clearRemember......");
        myactivity.putStringToPreference("userName", "");
        myactivity.putStringToPreference("password", "");
        myactivity.putBooleanToPreference("remember", Boolean.valueOf(false));
    }

    private void getRememberedUser()
    {
        if(getBooleanFromPreference("remember"))
        {
            setUserName(getStringFromPreference("userName", ""));
            setUserPassword(getStringFromPreference("password", ""));
        }
    }

    private void getUserName()
    {
        sUserName = mUserNameTxt.getText().toString();
    }

    private void getUserPassword()
    {
        sUserPassword = EncryptPassword2(mUserPassword.getText().toString());
    }

    private void getUserPasswordNoCode()
    {
        sUserPassword = mUserPassword.getText().toString();
    }

    private void handleClickEvent()
    {
        mLoginCancel.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                LoginActivity.clearRemember(LoginActivity.this);
                finish();
_L1:
                return;
                Exception exception;
                exception;
                if(Log.V)
                    Log.v("CancleFailed", (new StringBuilder("CancleFailed\uFF1A")).append(exception.getMessage()).toString());
                  goto _L1
            }

            final LoginActivity this$0;

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        }
);
        mLoginConfirm.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onLogin();
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                  goto _L1
            }

            final LoginActivity this$0;

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        }
);
        mRegLink.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, com/jindong/app/mall/register/RegisterActivity);
                startActivityInFrame(intent);
            }

            final LoginActivity this$0;

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        }
);
        mRememberMe.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                compoundbutton.getId();
                JVM INSTR tableswitch 2131493195 2131493195: default 24
            //                           2131493195 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                if(flag)
                    bRememberMe = true;
                else
                    bRememberMe = false;
                if(true) goto _L1; else goto _L3
_L3:
            }

            final LoginActivity this$0;

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        }
);
        mRememberMe.setChecked(true);
    }

    private void initBtn()
    {
        mLoginCancel = (Button)findViewById(0x7f0c02c8);
        mLoginConfirm = (Button)findViewById(0x7f0c014c);
        mRegLink = (Button)findViewById(0x7f0c014d);
        mRememberMe = (CheckBox)findViewById(0x7f0c014b);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f090144);
    }

    private void initEditTxt()
    {
        mUserNameTxt = (EditText)findViewById(0x7f0c0148);
        mUserPassword = (EditText)findViewById(0x7f0c014a);
        if(getBooleanFromPreference("remember"))
        {
            getRememberedUser();
            mRememberMe = (CheckBox)findViewById(0x7f0c014b);
        } else
        {
            mUserNameTxt.setText("");
            mUserPassword.setText("");
        }
    }

    private boolean nameCheck()
    {
        boolean flag = false;
        if(TextUtils.isEmpty(mUserNameTxt.getText().toString().trim()))
        {
            flag = true;
            mUserNameTxt.setError(getString(0x7f090065));
        }
        return flag;
    }

    private void onLogin()
    {
        if(!nameCheck() && !passWordCheck()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(mUserPassword.getWindowToken(), 0);
        getUserName();
        if(Log.D)
        {
            Log.d("LoginActivity", (new StringBuilder("input-password:")).append(mUserPassword.getText().toString()).toString());
            Log.d("LoginActivity", (new StringBuilder("file-password:")).append(getStringFromPreference("password", "")).toString());
        }
        JSONObject jsonobject;
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting;
        if(mUserPassword.getText().toString().equals(getStringFromPreference("password", "")))
            getUserPasswordNoCode();
        else
            getUserPassword();
        if(sUserPassword.length() < 1 || sUserName.length() < 1)
            continue; /* Loop/switch isn't completed */
        jsonobject = new JSONObject();
        jsonobject.put("loginpwd", sUserPassword);
        jsonobject.put("loginname", sUserName);
        httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("login");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("Log in", "return back after click login...");
                try
                {
                    JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("logInfo");
                    if(Log.D)
                        Log.d("Login...end", String.valueOf(jsonarraypoxy.length()));
                    if(jsonarraypoxy.length() < 1)
                    {
                        if(Log.D)
                            Log.d("Log in", "get empty string.....");
                        LoginError("");
                        break MISSING_BLOCK_LABEL_208;
                    }
                    if(jsonarraypoxy.getJSONObject(0).names().toString().contains("failure"))
                    {
                        String s1 = jsonarraypoxy.getJSONObject(0).getString("failure");
                        LoginError(s1);
                        if(Log.D)
                            Log.d("Log in", jsonarraypoxy.getJSONObject(0).getString("failure"));
                        break MISSING_BLOCK_LABEL_208;
                    }
                }
                catch(Exception exception)
                {
                    if(Log.D)
                        Log.d("Log in", (new StringBuilder("error message:")).append(exception.getMessage()).toString());
                    break MISSING_BLOCK_LABEL_208;
                }
                String s = httpresponse.getJSONObject().get("oldpin").toString();
                if(Log.D)
                    Log.d("Login pin..", s);
                LoginSuccess(s);
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("Log in ", (new StringBuilder("Login error-->> ")).append(httperror).toString());
                LoginActivity.clearRemember(LoginActivity.this);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Log in", "Start to login......");
            }

            final LoginActivity this$0;

            
            {
                this$0 = LoginActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("login_activity", jsonexception.getMessage());
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static void onLogin(final MyActivity myActivity, final LoginListener listener)
    {
        String s;
        String s1;
        if(Log.D)
            Log.d("Temp", "LoginActivity  onLogin() -->> ");
        s = "";
        s1 = "";
        if(myActivity.getBooleanFromPreference("remember"))
        {
            myActivity.post(new Runnable() {

                public void run()
                {
                    ((MainActivity)myActivity.getParent()).setStateText(myActivity.getString(0x7f090008));
                }

                private final MyActivity val$myActivity;

            
            {
                myActivity = myactivity;
                super();
            }
            }
);
            s1 = myActivity.getStringFromPreference("userName", "");
            s = myActivity.getStringFromPreference("password", "");
            if(Log.D)
            {
                Log.d("Temp", (new StringBuilder("onLogin() sName -->> ")).append(s1).toString());
                Log.d("Temp", (new StringBuilder("onLogin() sPassword -->> ")).append(s).toString());
            }
        } else
        {
            if(Log.D)
                Log.d("Temp", "no login LoginCompletedNotify() -->> ");
            listener.loginCompletedNotify();
        }
        if(s.length() >= 1 && s1.length() >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("loginpwd", s);
        jsonobject.put("loginname", s1);
        myActivity.getHttpGroupaAsynPool().add("login", jsonobject, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("Log in", "return back after click login...");
                try
                {
                    JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("logInfo");
                    if(Log.D)
                        Log.d("Login...end", String.valueOf(jsonarraypoxy.length()));
                    if(jsonarraypoxy.length() < 1)
                    {
                        LoginActivity.autoLoginError(myActivity, listener);
                        break MISSING_BLOCK_LABEL_233;
                    }
                    if(jsonarraypoxy.getJSONObject(0).names().toString().contains("failure"))
                    {
                        LoginActivity.clearRemember(myActivity);
                        LoginActivity.autoLoginError(myActivity, listener);
                        break MISSING_BLOCK_LABEL_233;
                    }
                }
                catch(Exception exception)
                {
                    if(Log.D)
                        Log.d("Log in", (new StringBuilder("error message:")).append(exception.getMessage()).toString());
                    break MISSING_BLOCK_LABEL_233;
                }
                String s2 = httpresponse.getJSONObject().get("oldpin").toString();
                if(Log.D)
                    Log.d("Login pin..", s2);
                Contants.hasLogIn = true;
                LoginUser.setUserState(Contants.LOG_IN);
                JSONObject jsonobject1 = new JSONObject();
                jsonobject1.put("pin", s2);
                LoginUser.setUserInfo(jsonobject1);
                if(Log.D)
                    Log.d("Temp", "ok LoginCompletedNotify() -->> ");
                listener.loginCompletedNotify();
                myActivity.putBooleanToPreference("login", Boolean.valueOf(true));
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("Temp", "net error LoginCompletedNotify() -->> ");
                listener.loginCompletedNotify();
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Log in", "Start to login......");
            }

            private final LoginListener val$listener;
            private final MyActivity val$myActivity;

            
            {
                myActivity = myactivity;
                listener = loginlistener;
                super();
            }
        }
);
        continue; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("login_activity", jsonexception.getMessage());
        if(true) goto _L1; else goto _L3
_L3:
    }

    private boolean passWordCheck()
    {
        boolean flag = false;
        if(TextUtils.isEmpty(mUserPassword.getText().toString().trim()))
        {
            flag = true;
            mUserPassword.setError(getString(0x7f090066));
        }
        return flag;
    }

    private void setUserName(String s)
    {
        if(s.length() > 0)
            mUserNameTxt.setText(s);
    }

    private void setUserPassword(String s)
    {
        if(s.length() > 0)
            mUserPassword.setText(s);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030042);
        initBtn();
        initEditTxt();
        handleClickEvent();
    }

    public void onRemember()
    {
        if(!nameCheck() && !passWordCheck())
        {
            getUserName();
            getUserPassword();
            if(sUserName != null && sUserName.length() > 0)
                putStringToPreference("userName", sUserName);
            if(sUserPassword != null && sUserPassword.length() > 0)
                putStringToPreference("password", sUserPassword);
            putBooleanToPreference("remember", Boolean.valueOf(true));
        }
    }

    public void onStart()
    {
        super.onStart();
    }

    public static final String PREF_LOGIN = "loginFlag";
    public static final String PREF_NAME = "userInfo";
    public static final String RESEND_FLAG = "com.360buy:loginResendFlag";
    public static final int TO_MY_JD = 1;
    private AlertDialog alertDialog;
    boolean bRememberMe;
    Button mLoginCancel;
    Button mLoginConfirm;
    LoginUser mLoginUser;
    Button mRegLink;
    CheckBox mRememberMe;
    TextView mTitle;
    private EditText mUserNameTxt;
    private EditText mUserPassword;
    String sUserName;
    String sUserPassword;






}
