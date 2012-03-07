// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.jindong.app.mall.login.LoginUser;
import com.jindong.app.mall.more.HelpActivity;
import com.jindong.app.mall.personel.PersonelActivity;
import com.jindong.app.mall.utils.*;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.*;

public class RegisterActivity extends MyActivity
{

    public RegisterActivity()
    {
        bVerifiedName = false;
        bVerifiedMail = false;
        bThreadStop = true;
        bShowPassword = false;
    }

    private String EncryptPassword2(String s)
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

    private void LoginError()
    {
        post(new Runnable() {

            public void run()
            {
                Toast.makeText(RegisterActivity.this, getString(0x7f090064), 1).show();
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                if(Log.D)
                    Log.d("Login Error", (new StringBuilder("Error Message:")).append(exception.getMessage()).toString());
                  goto _L1
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
    }

    private void LoginSuccess(final String pinName)
    {
        post(new Runnable() {

            public void run()
            {
                Contants.hasLogIn = true;
                LoginUser.setUserState(Contants.LOG_IN);
                JSONObject jsonobject = new JSONObject();
                jsonobject.put("pin", pinName);
                LoginUser.setUserInfo(jsonobject);
                getIntent();
                noShowAgain();
                Intent intent = new Intent(RegisterActivity.this, com/jindong/app/mall/personel/PersonelActivity);
                Bundle bundle = new Bundle();
                bundle.putString("pinName", pinName);
                intent.putExtras(bundle);
                startActivityInFrame(intent);
_L1:
                return;
                Exception exception;
                exception;
                exception.printStackTrace();
                if(Log.D)
                    Log.d("Login Error", (new StringBuilder("Error Message:")).append(exception.getMessage()).toString());
                  goto _L1
            }

            final RegisterActivity this$0;
            private final String val$pinName;

            
            {
                this$0 = RegisterActivity.this;
                pinName = s;
                super();
            }
        }
);
    }

    private void clearTextview()
    {
        mRegisterName.setText("");
        mRegisterMail.setText("");
        mRegisterFirstPwd.setText("");
        mRegisterSecondPwd.setText("");
    }

    private void getUserPassword()
    {
        sRegPwd2 = EncryptPassword2(sRegPwd1);
    }

    private void initBtn()
    {
        mConfirmBtn = (Button)findViewById(0x7f0c0294);
        mConfirmBtn.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onRegister();
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        mTitle = (TextView)findViewById(0x7f0c02c7);
        mTitle.setText(0x7f09006c);
        mShowPassword = (CheckBox)findViewById(0x7f0c018b);
        mAccept = (CheckBox)findViewById(0x7f0c0292);
        showAgreement = (TextView)findViewById(0x7f0c0293);
    }

    private void initCheckBox()
    {
        mShowPassword.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                compoundbutton.getId();
                JVM INSTR tableswitch 2131493259 2131493259: default 24
            //                           2131493259 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                if(flag)
                {
                    mRegisterFirstPwd.setInputType(144);
                    mRegisterSecondPwd.setInputType(144);
                } else
                {
                    mRegisterFirstPwd.setInputType(129);
                    mRegisterSecondPwd.setInputType(129);
                }
                if(true) goto _L1; else goto _L3
_L3:
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        mAccept.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                if(flag)
                {
                    mConfirmBtn.setEnabled(true);
                    mConfirmBtn.setTextColor(0xff000000);
                } else
                {
                    mConfirmBtn.setEnabled(false);
                    mConfirmBtn.setTextColor(Color.rgb(153, 153, 153));
                }
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
    }

    private void initTextView()
    {
        mRegisterName = (EditText)findViewById(0x7f0c028b);
        mRegisterName.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(!flag)
                    nameCheck();
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        mRegisterMail = (EditText)findViewById(0x7f0c028d);
        mRegisterMail.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(!flag)
                    mailCheck();
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        mRegisterMail.setInputType(32);
        mRegisterFirstPwd = (EditText)findViewById(0x7f0c028f);
        mRegisterSecondPwd = (EditText)findViewById(0x7f0c0291);
        showAgreement.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent(RegisterActivity.this, com/jindong/app/mall/more/HelpActivity);
                Bundle bundle = new Bundle();
                bundle.putString("firstPage", getStringFromPreference("regiterAgreementUrl"));
                intent.putExtras(bundle);
                startActivityInFrame(intent);
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
    }

    private boolean inputCheck()
    {
        boolean flag;
        String s;
        flag = false;
        if(nameCheck() || mailCheck())
            flag = true;
        s = mRegisterFirstPwd.getText().toString().trim();
        if(!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        flag = true;
        mRegisterFirstPwd.setError(getString(0x7f090066));
_L4:
        if(Log.D)
            Log.d("temp", "inputCheck-end");
        return flag;
_L2:
        if(!CommonUtil.checkPassword(s, 6, 20))
        {
            flag = true;
            mRegisterFirstPwd.setError(getString(0x7f090068));
        } else
        if(!s.equals(mRegisterSecondPwd.getText().toString()))
        {
            flag = true;
            mRegisterSecondPwd.setError(getText(0x7f090075));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean mailCheck()
    {
        boolean flag;
        String s;
        flag = false;
        s = mRegisterMail.getText().toString().trim();
        if(!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        flag = true;
        mRegisterMail.setError(getString(0x7f09006f));
_L4:
        if(Log.D)
            Log.d("temp", "mailCheck-end");
        return flag;
_L2:
        if(!CommonUtil.checkEmailWithSuffix(s))
        {
            flag = true;
            mRegisterMail.setError(getString(0x7f090076));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean nameCheck()
    {
        boolean flag;
        String s;
        int i;
        flag = false;
        s = mRegisterName.getText().toString();
        i = CommonUtil.getLength(s.trim());
        if(Log.D)
            Log.d("temp", (new StringBuilder("length:")).append(i).toString());
        if(!TextUtils.isEmpty(s.trim())) goto _L2; else goto _L1
_L1:
        flag = true;
        mRegisterName.setError(getString(0x7f090065));
_L4:
        if(Log.D)
            Log.d("temp", "nameCheck-end");
        return flag;
_L2:
        if(!CommonUtil.checkUsername(s))
        {
            flag = true;
            mRegisterName.setError(getText(0x7f090067));
        } else
        if(i < 4 || i > 20)
        {
            flag = true;
            mRegisterName.setError(getText(0x7f090067));
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void onLogin()
    {
        getUserPassword();
        if(sRegPwd1.length() >= 1 && sRegName.length() >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("loginpwd", sRegPwd2);
        jsonobject.put("loginname", sRegName);
        getHttpGroupaAsynPool().add("login", jsonobject, new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

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
                        LoginError();
                        break MISSING_BLOCK_LABEL_192;
                    }
                    if(jsonarraypoxy.getJSONObject(0).names().toString().contains("failure"))
                    {
                        LoginError();
                        if(Log.D)
                            Log.d("Log in", jsonarraypoxy.getJSONObject(0).getString("failure"));
                        break MISSING_BLOCK_LABEL_192;
                    }
                }
                catch(Exception exception)
                {
                    if(Log.D)
                        Log.d("Log in", (new StringBuilder("error message:")).append(exception.getMessage()).toString());
                    break MISSING_BLOCK_LABEL_192;
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
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Log in", "Start to login......");
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
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

    private void onRegister()
    {
        JSONObject jsonobject;
        if(inputCheck())
            break MISSING_BLOCK_LABEL_218;
        if(!bVerifiedMail);
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(mRegisterSecondPwd.getWindowToken(), 0);
        getRegisterUserInfo();
        jsonobject = new JSONObject();
        jsonobject.put("username", sRegName);
        jsonobject.put("pwd", sRegPwd1);
        jsonobject.put("pwd2", sRegPwd2);
        jsonobject.put("mail", sRegMailAddr);
        jsonobject.put("uuid", StatisticsReportUtil.readDeviceUUID());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setReadTimeout(60000);
        httpsetting.setFunctionId("register");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setPost(true);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("Register", "end to register.........");
                JSONArrayPoxy jsonarraypoxy = httpresponse.getJSONObject().getJSONArray("regInfo");
                if(jsonarraypoxy.length() >= 1) goto _L2; else goto _L1
_L1:
                showDialog(getText(0x7f090073).toString());
_L4:
                bThreadStop = true;
                return;
_L2:
                Exception exception1;
                if(jsonarraypoxy.getJSONObject(0).names().toString().contains("info"))
                {
                    messageBody = jsonarraypoxy.getJSONObject(0).getString("info");
                    showDialog(messageBody);
                    if(Log.D)
                        Log.d("Register", jsonarraypoxy.getJSONObject(0).getString("info"));
                    continue; /* Loop/switch isn't completed */
                }
                try
                {
                    onRegisterSuccess();
                    onLogin();
                    if(Log.D)
                        Log.d("Register", "successs to register");
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception1)
                {
                    if(Log.D)
                        Log.d("Register", (new StringBuilder("error message:")).append(exception1.getMessage()).toString());
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                if(Log.D)
                    Log.d("Register Error", (new StringBuilder()).append(httperror).toString());
                showDialog(getText(0x7f090073).toString());
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Register", "start to register.........");
                bThreadStop = false;
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        httpsetting.setNotifyUser(true);
        getHttpGroupaAsynPool().add(httpsetting);
        break MISSING_BLOCK_LABEL_218;
        Exception exception;
        exception;
        if(Log.D)
            Log.d("Register Error", exception.getMessage());
        break MISSING_BLOCK_LABEL_218;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("Register user information error", jsonexception.getMessage());
    }

    private void onRegisterSuccess()
    {
        post(new Runnable() {

            public void run()
            {
                Toast.makeText(RegisterActivity.this, 0x7f09007e, 0).show();
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
    }

    private void popupToast(final String sMsg)
    {
        post(new Runnable() {

            public void run()
            {
                Toast.makeText(RegisterActivity.this, sMsg, 1).show();
            }

            final RegisterActivity this$0;
            private final String val$sMsg;

            
            {
                this$0 = RegisterActivity.this;
                sMsg = s;
                super();
            }
        }
);
    }

    private void verifyUserMailAddr()
    {
        JSONObject jsonobject;
        bVerifiedMail = false;
        jsonobject = new JSONObject();
        jsonobject.put("email", mRegisterMail.getText());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("validate");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("Register verify mail", "end to verify mail.........");
                final String sMailBack;
                sMailBack = httpresponse.getJSONObject().getString("userName");
                if(Log.D)
                    Log.d("Register verify mail back string length", String.valueOf(sMailBack.length()));
                if(sMailBack.length() >= 1) goto _L2; else goto _L1
_L1:
                if(Log.D)
                    Log.d("Register verify mail", "get empty string.....");
                mRegisterMail.setError(getString(0x7f090073));
_L4:
                bThreadStop = true;
                return;
_L2:
                if(Log.D)
                    Log.d("Register", "successs to verify mail");
                Exception exception;
                if(sMailBack.contains(getString(0x7f090077)))
                {
                    bVerifiedMail = true;
                    continue; /* Loop/switch isn't completed */
                }
                try
                {
                    bVerifiedMail = false;
                    post(new Runnable() {

                        public void run()
                        {
                            mRegisterMail.setError(sMailBack);
                        }

                        final _cls8 this$1;
                        private final String val$sMailBack;

                    
                    {
                        this$1 = _cls8.this;
                        sMailBack = s;
                        super();
                    }
                    }
);
                }
                // Misplaced declaration of an exception variable
                catch(Exception exception)
                {
                    if(Log.D)
                        Log.d("Register verify mail", (new StringBuilder("verify mail error message:")).append(exception.getMessage()).toString());
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                bVerifiedMail = true;
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Register verify mail", "start to verify mail address");
                bThreadStop = false;
            }

            final RegisterActivity this$0;


            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        getHttpGroupaAsynPool().add(httpsetting);
_L2:
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("Register  mail error", jsonexception.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void verifyUserName()
    {
        JSONObject jsonobject;
        bVerifiedName = false;
        jsonobject = new JSONObject();
        jsonobject.put("userName", mRegisterName.getText());
        com.jindong.app.mall.utils.HttpGroup.HttpSetting httpsetting = new com.jindong.app.mall.utils.HttpGroup.HttpSetting();
        httpsetting.setFunctionId("validate");
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setPost(true);
        httpsetting.setListener(new com.jindong.app.mall.utils.HttpGroup.OnAllListener() {

            public void onEnd(com.jindong.app.mall.utils.HttpGroup.HttpResponse httpresponse)
            {
                if(Log.D)
                    Log.d("Register verify name", "end to verify name.........");
                final String sNameBack;
                sNameBack = httpresponse.getJSONObject().getString("userName");
                if(Log.D)
                    Log.d("Register verify name back string length", String.valueOf(sNameBack.length()));
                if(sNameBack.length() >= 1) goto _L2; else goto _L1
_L1:
                if(Log.D)
                    Log.d("Register verify name", "get empty string.....");
_L3:
                bThreadStop = true;
                return;
_L2:
                if(!sNameBack.contains(getString(0x7f090077)))
                    break MISSING_BLOCK_LABEL_145;
                bVerifiedName = true;
_L4:
                if(Log.D)
                    Log.d("Register", "successs to register verify name");
                  goto _L3
                Exception exception;
                exception;
                if(Log.D)
                    Log.d("Register verify mail", (new StringBuilder("verify mail error message:")).append(exception.getMessage()).toString());
                  goto _L3
                bVerifiedName = false;
                post(new Runnable() {

                    public void run()
                    {
                        mRegisterName.setError(sNameBack);
                    }

                    final _cls7 this$1;
                    private final String val$sNameBack;

                    
                    {
                        this$1 = _cls7.this;
                        sNameBack = s;
                        super();
                    }
                }
);
                  goto _L4
            }

            public void onError(com.jindong.app.mall.utils.HttpGroup.HttpError httperror)
            {
                bVerifiedName = false;
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
                if(Log.D)
                    Log.d("Register verify name", "start to verify user name");
                bThreadStop = false;
            }

            final RegisterActivity this$0;


            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        getHttpGroupaAsynPool().add(httpsetting);
_L2:
        return;
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v("Register user information error", jsonexception.getMessage());
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void getRegisterUserInfo()
    {
        sRegName = mRegisterName.getText().toString();
        sRegMailAddr = mRegisterMail.getText().toString();
        sRegPwd1 = mRegisterFirstPwd.getText().toString();
        sRegPwd2 = mRegisterSecondPwd.getText().toString();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03007b);
        initBtn();
        initTextView();
        initCheckBox();
    }

    void showDialog(String s)
    {
        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(0x7f09006b);
        alertDialogBuilder.setMessage(s);
        alertDialogBuilder.setPositiveButton(0x7f09019d, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            final RegisterActivity this$0;

            
            {
                this$0 = RegisterActivity.this;
                super();
            }
        }
);
        post(new Runnable() {

            public void run()
            {
                alertDialogBuilder.show();
            }

            final RegisterActivity this$0;
            private final android.app.AlertDialog.Builder val$alertDialogBuilder;

            
            {
                this$0 = RegisterActivity.this;
                alertDialogBuilder = builder;
                super();
            }
        }
);
    }

    boolean bShowPassword;
    boolean bThreadStop;
    boolean bVerifiedMail;
    boolean bVerifiedName;
    CheckBox mAccept;
    Button mConfirmBtn;
    EditText mRegisterFirstPwd;
    EditText mRegisterMail;
    EditText mRegisterName;
    EditText mRegisterSecondPwd;
    CheckBox mShowPassword;
    TextView mTitle;
    String messageBody;
    private String sRegMailAddr;
    private String sRegName;
    private String sRegPwd1;
    private String sRegPwd2;
    private String sRegUuid;
    TextView showAgreement;







}
