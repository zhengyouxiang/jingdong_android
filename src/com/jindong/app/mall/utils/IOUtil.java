// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.io.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log

public class IOUtil
{
    public static interface ProgressListener
    {

        public abstract void notify(int i, int j);
    }


    public IOUtil()
    {
    }

    public static byte[] readAsBytes(InputStream inputstream, ProgressListener progresslistener)
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream;
        (byte[])null;
        bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[];
        int i;
        abyte0 = new byte[bufferSize];
        i = 0;
_L1:
        int j;
        byte abyte1[];
        j = inputstream.read(abyte0);
        if(j != -1)
            break MISSING_BLOCK_LABEL_55;
        abyte1 = bytearrayoutputstream.toByteArray();
        Exception exception;
        Exception exception1;
        IOException ioexception;
        if(bytearrayoutputstream != null)
            try
            {
                bytearrayoutputstream.close();
            }
            catch(Exception exception2) { }
        return abyte1;
        bytearrayoutputstream.write(abyte0, 0, j);
        i += j;
        if(progresslistener != null)
            progresslistener.notify(j, i);
          goto _L1
        ioexception;
        throw ioexception;
        exception;
        if(bytearrayoutputstream != null)
            try
            {
                bytearrayoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(Exception exception1) { }
        throw exception;
    }

    public static void readAsFile(InputStream inputstream, FileOutputStream fileoutputstream, ProgressListener progresslistener, HttpGroup.StopController stopcontroller)
        throws Exception
    {
        byte abyte0[];
        int i;
        abyte0 = new byte[bufferSize];
        i = 0;
_L4:
        int j = inputstream.read(abyte0);
        if(j == -1) goto _L2; else goto _L1
_L1:
        boolean flag = stopcontroller.isStop();
        if(!flag) goto _L3; else goto _L2
_L2:
        if(fileoutputstream == null)
            break MISSING_BLOCK_LABEL_46;
        fileoutputstream.close();
_L5:
        return;
_L3:
        fileoutputstream.write(abyte0, 0, j);
        i += j;
        if(progresslistener != null)
            progresslistener.notify(j, i);
          goto _L4
        Exception exception2;
        exception2;
        throw exception2;
        Exception exception;
        exception;
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            catch(Exception exception1) { }
        throw exception;
        Exception exception3;
        exception3;
          goto _L5
    }

    public static String readAsString(InputStream inputstream, String s)
        throws Exception
    {
        return readAsString(inputstream, s, null);
    }

    public static String readAsString(InputStream inputstream, String s, ProgressListener progresslistener)
        throws Exception
    {
        String s1;
        try
        {
            s1 = new String(readAsBytes(inputstream, progresslistener), s);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            if(Log.V)
                Log.v("HttpRequest", unsupportedencodingexception.getMessage());
            s1 = null;
        }
        return s1;
    }

    private static int bufferSize = 16384;

}
