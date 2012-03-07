// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class DialogController
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnKeyListener
{

    public DialogController()
    {
        canBack = false;
    }

    public void init(Context context1)
    {
        context = context1;
        builder = new android.app.AlertDialog.Builder(context1);
        initContent();
        initButton();
    }

    protected void initButton()
    {
        if(!TextUtils.isEmpty(initPositiveButton))
            builder.setPositiveButton(initPositiveButton, this);
        if(!TextUtils.isEmpty(initNeutralButton))
            builder.setNeutralButton(initNeutralButton, this);
        if(!TextUtils.isEmpty(initNegativeButton))
            builder.setNegativeButton(initNegativeButton, this);
    }

    protected void initContent()
    {
        if(TextUtils.isEmpty(initTitle))
            builder.setTitle("\u4EAC\u4E1C\u5546\u57CE");
        else
            builder.setTitle(initTitle);
        if(!TextUtils.isEmpty(initMessage))
            builder.setMessage(initMessage);
        if(view != null)
            builder.setView(view);
        builder.setOnKeyListener(this);
    }

    public boolean isCanBack()
    {
        return canBack;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
    }

    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
    {
        boolean flag;
        if(!isCanBack() && 4 == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setCanBack(boolean flag)
    {
        canBack = flag;
    }

    public void setMessage(CharSequence charsequence)
    {
        if(alertDialog != null)
            alertDialog.setMessage(charsequence);
        else
        if(builder != null)
            builder.setMessage(charsequence);
        else
            initMessage = charsequence;
    }

    public void setNegativeButton(CharSequence charsequence)
    {
        if(alertDialog != null)
        {
            if(TextUtils.isEmpty(charsequence))
                alertDialog.getButton(-2).setVisibility(8);
            else
                alertDialog.setButton(-2, charsequence, this);
        } else
        if(builder != null)
            builder.setNegativeButton(initNegativeButton, this);
        else
            initNegativeButton = charsequence;
    }

    public void setNeutralButton(CharSequence charsequence)
    {
        if(alertDialog != null)
        {
            if(TextUtils.isEmpty(charsequence))
                alertDialog.getButton(-3).setVisibility(8);
            else
                alertDialog.setButton(-3, charsequence, this);
        } else
        if(builder != null)
            builder.setNeutralButton(initNeutralButton, this);
        else
            initNeutralButton = charsequence;
    }

    public void setPositiveButton(CharSequence charsequence)
    {
        if(alertDialog != null)
        {
            if(TextUtils.isEmpty(charsequence))
                alertDialog.getButton(-1).setVisibility(8);
            else
                alertDialog.setButton(-1, charsequence, this);
        } else
        if(builder != null)
            builder.setPositiveButton(charsequence, this);
        else
            initPositiveButton = charsequence;
    }

    public void setTitle(CharSequence charsequence)
    {
        if(alertDialog != null)
            alertDialog.setTitle(charsequence);
        else
        if(builder != null)
            builder.setTitle(charsequence);
        else
            initTitle = charsequence;
    }

    public void setView(View view1)
    {
        if(alertDialog != null)
            alertDialog.setView(view1);
        else
        if(builder != null)
            builder.setView(view1);
        else
            view = view1;
    }

    public void show()
    {
        if(alertDialog != null)
            alertDialog.show();
        else
        if(builder != null)
            alertDialog = builder.show();
        else
            throw new RuntimeException("builder is null, need init this controller");
    }

    protected AlertDialog alertDialog;
    protected android.app.AlertDialog.Builder builder;
    private boolean canBack;
    private Context context;
    private CharSequence initMessage;
    private CharSequence initNegativeButton;
    private CharSequence initNeutralButton;
    private CharSequence initPositiveButton;
    private CharSequence initTitle;
    private View view;
}
