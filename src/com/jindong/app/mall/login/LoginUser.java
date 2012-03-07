// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.login;

import android.content.Intent;
import com.jindong.app.mall.utils.MyActivity;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.login:
//            LoginActivity

public class LoginUser
{

    public LoginUser()
    {
    }

    public static boolean checkUserLogin(MyActivity myactivity)
    {
        if(!hasLogin())
            myactivity.startActivityInFrame(new Intent(myactivity, com/jindong/app/mall/login/LoginActivity));
        return hasLogin();
    }

    public static JSONObject getLoginUserInfo()
    {
        return jbUserInfo;
    }

    public static String getLoginUserName()
    {
        String s1 = jbUserInfo.getString("pin");
        String s = s1;
_L2:
        return s;
        JSONException jsonexception;
        jsonexception;
        s = "";
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static boolean hasLogin()
    {
        boolean flag;
        if(UserState == 0)
            flag = false;
        else
        if(UserState == 1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void setUserInfo(JSONObject jsonobject)
    {
        jbUserInfo = jsonobject;
    }

    public static void setUserState(int i)
    {
        UserState = i;
    }

    private static int UserState = 0;
    private static JSONObject jbUserInfo;

}
