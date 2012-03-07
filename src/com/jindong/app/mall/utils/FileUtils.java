// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.io.*;

public class FileUtils
{

    public FileUtils()
    {
    }

    public File createFile(String s)
        throws IOException
    {
        File file = new File(s);
        file.createNewFile();
        return file;
    }

    public File createSDDir(String s)
    {
        File file = new File((new StringBuilder(String.valueOf(SDPATH))).append(s).toString());
        file.mkdir();
        return file;
    }

    public void deleFile(String s)
    {
        File file = new File((new StringBuilder(String.valueOf(SDPATH))).append(s).toString());
        if(file.exists())
            file.deleteOnExit();
    }

    public String getSDPATH()
    {
        return SDPATH;
    }

    public boolean isFileExist(String s)
    {
        return (new File((new StringBuilder(String.valueOf(SDPATH))).append(s).toString())).exists();
    }

    public File writeFromInput(String s, String s1, InputStream inputstream)
    {
        File file;
        FileOutputStream fileoutputstream;
        file = null;
        fileoutputstream = null;
        File file1 = new File((new StringBuilder(String.valueOf(s))).append(s1).toString());
        FileOutputStream fileoutputstream1;
        if(file1.exists())
            file1.deleteOnExit();
        file1.createNewFile();
        fileoutputstream1 = new FileOutputStream(file1);
        byte abyte0[];
        BufferedInputStream bufferedinputstream;
        abyte0 = new byte[1024];
        bufferedinputstream = new BufferedInputStream(inputstream);
_L5:
        int i = bufferedinputstream.read(abyte0);
        if(i != -1) goto _L2; else goto _L1
_L1:
        fileoutputstream1.flush();
        if(fileoutputstream1 == null) goto _L4; else goto _L3
_L3:
        fileoutputstream1.close();
        file = file1;
_L6:
        return file;
_L2:
        fileoutputstream1.write(abyte0, 0, i);
          goto _L5
        Exception exception3;
        exception3;
        Exception exception1;
        exception1 = exception3;
        fileoutputstream = fileoutputstream1;
        file = file1;
_L8:
        exception1.printStackTrace();
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
          goto _L6
        Exception exception2;
        exception2;
_L7:
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        throw exception2;
        IOException ioexception2;
        ioexception2;
        ioexception2.printStackTrace();
_L4:
        file = file1;
          goto _L6
        exception2;
          goto _L7
        exception2;
        fileoutputstream = fileoutputstream1;
          goto _L7
        Exception exception4;
        exception4;
        exception1 = exception4;
          goto _L8
        Exception exception;
        exception;
        exception1 = exception;
        file = file1;
          goto _L8
    }

    private final int FILESIZE = 1024;
    private String SDPATH;
}
