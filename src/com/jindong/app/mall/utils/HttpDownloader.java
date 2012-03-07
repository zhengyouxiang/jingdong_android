// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.text.TextUtils;
import java.io.*;
import java.net.*;
import org.json.JSONObject;

// Referenced classes of package com.jindong.app.mall.utils:
//            FileUtils

public class HttpDownloader
{
    public static interface DownloadListener
    {

        public abstract void onDownloadEnd(Object obj);
    }


    public HttpDownloader()
    {
        url = null;
    }

    public static String getCookies()
    {
        return cookies;
    }

    public static boolean isCookiesFlag()
    {
        return cookiesFlag;
    }

    public static void setCookies(String s)
    {
        cookies = s;
    }

    public static void setCookiesFlag(boolean flag)
    {
        cookiesFlag = flag;
    }

    public int downFile(String s, String s1, String s2, DownloadListener downloadlistener)
    {
        InputStream inputstream = null;
        FileUtils fileutils;
        InputStream inputstream1;
        fileutils = new FileUtils();
        inputstream1 = getInputStreamFromURL(s);
        inputstream = inputstream1;
        if(inputstream != null) goto _L2; else goto _L1
_L1:
        byte byte0;
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception4)
            {
                ioexception4.printStackTrace();
            }
        byte0 = -1;
_L3:
        return byte0;
_L2:
        java.io.File file = fileutils.writeFromInput(s1, s2, inputstream);
        Exception exception;
        Exception exception1;
        if(file == null)
        {
            if(inputstream != null)
                try
                {
                    inputstream.close();
                }
                catch(IOException ioexception3)
                {
                    ioexception3.printStackTrace();
                }
            byte0 = -1;
        } else
        {
            if(inputstream != null)
                try
                {
                    inputstream.close();
                }
                catch(IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
            downloadlistener.onDownloadEnd(null);
            byte0 = 0;
        }
          goto _L3
        exception1;
        exception1.printStackTrace();
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        byte0 = -1;
          goto _L3
        exception;
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        throw exception;
    }

    public int download(String s, DownloadListener downloadlistener)
    {
        StringBuffer stringbuffer;
        BufferedReader bufferedreader;
        byte byte0;
        stringbuffer = new StringBuffer();
        bufferedreader = null;
        byte0 = -1;
        BufferedReader bufferedreader1;
        url = new URL(s);
        HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
        if(cookiesFlag)
            httpurlconnection.setRequestProperty("Cookie", cookies);
        bufferedreader1 = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
_L7:
        String s1 = bufferedreader1.readLine();
        if(s1 != null) goto _L2; else goto _L1
_L1:
        bufferedreader1.close();
        if(TextUtils.isEmpty(stringbuffer.toString())) goto _L4; else goto _L3
_L3:
        JSONObject jsonobject2 = new JSONObject(stringbuffer.toString());
        if(!"0".equals(jsonobject2.getString("code"))) goto _L6; else goto _L5
_L5:
        downloadlistener.onDownloadEnd(jsonobject2);
        byte0 = 0;
_L6:
        return byte0;
_L2:
        stringbuffer.append(s1);
          goto _L7
        Exception exception4;
        exception4;
        bufferedreader = bufferedreader1;
_L10:
        byte0 = -1;
        JSONObject jsonobject;
        bufferedreader.close();
        if(TextUtils.isEmpty(stringbuffer.toString()))
            break MISSING_BLOCK_LABEL_222;
        jsonobject = new JSONObject(stringbuffer.toString());
        if(!"0".equals(jsonobject.getString("code"))) goto _L6; else goto _L8
_L8:
        downloadlistener.onDownloadEnd(jsonobject);
        byte0 = 0;
          goto _L6
        byte0 = -1;
          goto _L6
        Exception exception1;
        exception1;
        byte0 = -1;
          goto _L6
        Exception exception2;
        exception2;
_L9:
        try
        {
            bufferedreader.close();
            if(!TextUtils.isEmpty(stringbuffer.toString()))
            {
                JSONObject jsonobject1 = new JSONObject(stringbuffer.toString());
                if("0".equals(jsonobject1.getString("code")))
                    downloadlistener.onDownloadEnd(jsonobject1);
            }
        }
        catch(Exception exception3) { }
        throw exception2;
_L4:
        byte0 = -1;
          goto _L6
        Exception exception5;
        exception5;
        byte0 = -1;
          goto _L6
        exception2;
        bufferedreader = bufferedreader1;
          goto _L9
        Exception exception;
        exception;
          goto _L10
    }

    public InputStream getInputStreamFromURL(String s)
    {
        InputStream inputstream = null;
        InputStream inputstream1;
        url = new URL(s);
        HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
        httpurlconnection.setConnectTimeout(15000);
        httpurlconnection.setReadTimeout(15000);
        inputstream1 = httpurlconnection.getInputStream();
        inputstream = inputstream1;
_L2:
        return inputstream;
        MalformedURLException malformedurlexception;
        malformedurlexception;
        malformedurlexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static String cookies;
    private static boolean cookiesFlag;
    private final int CONNECTION_TIME_OUT = 15000;
    private URL url;
}
