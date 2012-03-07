// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import java.io.File;
import java.util.Date;

public class CacheFile
{

    public CacheFile()
    {
    }

    public CacheFile(File file1)
    {
        setFile(file1);
    }

    public CacheFile(String s, long l)
    {
        setName(s);
        cleanTime = new Date(l + (new Date()).getTime());
    }

    public Date getCleanTime()
    {
        return cleanTime;
    }

    public com.jindong.app.mall.utils.FileService.Directory getDirectory()
    {
        return directory;
    }

    public File getFile()
    {
        if(file == null && getDirectory() != null)
            file = new File(getDirectory().getDir(), getName());
        return file;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getName()
    {
        if(name == null)
            name = (new StringBuilder(String.valueOf(firstName))).append(".").append(lastName).toString();
        return name;
    }

    public void setCleanTime(Date date)
    {
        cleanTime = date;
    }

    public void setDirectory(com.jindong.app.mall.utils.FileService.Directory directory1)
    {
        directory = directory1;
    }

    public void setFile(File file1)
    {
        setName(file1.getName());
        file = file1;
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    public void setLastName(String s)
    {
        lastName = s;
    }

    public void setName(String s)
    {
        name = s;
        int i = s.lastIndexOf(".");
        firstName = s.substring(0, i);
        lastName = s.substring(i + 1);
    }

    private Date cleanTime;
    private com.jindong.app.mall.utils.FileService.Directory directory;
    private File file;
    private String firstName;
    private String lastName;
    private String name;
}
