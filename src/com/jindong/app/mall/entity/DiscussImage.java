// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;
import com.jindong.app.mall.utils.DPIUtil;
import com.jindong.app.mall.utils.Log;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscussImage
{

    public DiscussImage()
    {
    }

    public static boolean checkFileSizeIsBig(String s)
    {
        File file = new File(s);
        boolean flag;
        if(file.isFile() && file.exists() && file.length() > 0x19000L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static DiscussImage createDiscussImage(Context context, Uri uri)
    {
        InputStream inputstream;
        InputStream inputstream1;
        InputStream inputstream4;
        InputStream inputstream5;
        int j;
        ContentResolver contentresolver = context.getContentResolver();
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        Bitmap bitmap;
        String s;
        DiscussImage discussimage1;
        Matrix matrix;
        Bitmap bitmap1;
        Matcher matcher;
        String s1;
        int k;
        if(checkFileSizeIsBig(uri.getPath()))
            options.inSampleSize = 4;
        else
            options.inSampleSize = 2;
        inputstream = null;
        inputstream1 = null;
        inputstream4 = contentresolver.openInputStream(uri);
        Exception exception1;
        InputStream inputstream2;
        int i;
        InputStream inputstream3;
        Exception exception3;
        Exception exception4;
        Exception exception5;
        Exception exception6;
        Exception exception7;
        try
        {
            bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputstream4, null, options), DPIUtil.dip2px(100F), DPIUtil.dip2px(100F), false);
            inputstream5 = contentresolver.openInputStream(uri);
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception3)
        {
            i = 0;
            inputstream3 = inputstream4;
            continue; /* Loop/switch isn't completed */
        }
        s = JpegMetadataReader.readMetadata(inputstream5).getDirectory(com/drew/metadata/exif/ExifDirectory).getDescription(274);
        if(Log.D)
            Log.d("Temp", (new StringBuilder("orientationAll -->> ")).append(s).toString());
        if(s == null) goto _L2; else goto _L1
_L1:
        matcher = Pattern.compile("(\\d{1,3})").matcher(s);
        if(!matcher.find()) goto _L2; else goto _L3
_L3:
        s1 = matcher.group(1);
        if(s1 == null) goto _L2; else goto _L4
_L4:
        k = Integer.parseInt(s1);
        j = k;
_L9:
        if(j == 0)
            break MISSING_BLOCK_LABEL_214;
        matrix = new Matrix();
        matrix.setRotate(j);
        bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap = bitmap1;
        Exception exception;
        IOException ioexception;
        IOException ioexception1;
        Exception exception2;
        DiscussImage discussimage;
        IOException ioexception2;
        IOException ioexception3;
        if(inputstream4 != null)
            try
            {
                inputstream4.close();
            }
            catch(IOException ioexception5)
            {
                ioexception5.printStackTrace();
            }
        if(inputstream5 != null)
            try
            {
                inputstream5.close();
            }
            catch(IOException ioexception4)
            {
                ioexception4.printStackTrace();
            }
        discussimage1 = new DiscussImage();
        discussimage1.setPicture(new BitmapDrawable(bitmap));
        discussimage1.setPath(uri);
        discussimage1.setRotate(j);
        j;
        discussimage = discussimage1;
        return discussimage;
        exception2;
        i = 0;
        inputstream3 = null;
        exception3 = exception2;
_L7:
        exception3.printStackTrace();
        if(inputstream3 != null)
            try
            {
                inputstream3.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
        if(inputstream1 != null)
            try
            {
                inputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
        i;
        discussimage = null;
        inputstream3;
        inputstream1;
        break MISSING_BLOCK_LABEL_277;
        exception;
        exception1 = exception;
        inputstream2 = null;
_L6:
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        if(inputstream2 != null)
            try
            {
                inputstream2.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        throw exception1;
        exception5;
        exception1 = exception5;
        inputstream2 = null;
        inputstream = inputstream4;
        continue; /* Loop/switch isn't completed */
        exception6;
        exception1 = exception6;
        inputstream2 = inputstream5;
        inputstream = inputstream4;
        continue; /* Loop/switch isn't completed */
        exception7;
        exception1 = exception7;
        j;
        inputstream2 = inputstream5;
        inputstream = inputstream4;
        continue; /* Loop/switch isn't completed */
        exception4;
        inputstream = inputstream3;
        inputstream2 = inputstream1;
        i;
        exception1 = exception4;
        if(true) goto _L6; else goto _L5
_L5:
        break MISSING_BLOCK_LABEL_71;
        exception3;
        i = 0;
        inputstream1 = inputstream5;
        inputstream3 = inputstream4;
        continue; /* Loop/switch isn't completed */
        exception3;
        i = j;
        inputstream1 = inputstream5;
        inputstream3 = inputstream4;
        if(true) goto _L7; else goto _L2
_L2:
        j = 0;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static byte[] getBytesFromInputStream(InputStream inputstream, int i)
        throws IOException
    {
        int j = 0;
        byte abyte0[] = new byte[4096];
        ByteBuffer bytebuffer = ByteBuffer.allocate(i);
        do
        {
            int k = inputstream.read(abyte0);
            if(k == -1)
            {
                byte abyte1[] = new byte[j];
                bytebuffer.flip();
                bytebuffer.get(abyte1, 0, j);
                return abyte1;
            }
            bytebuffer.put(abyte0, 0, k);
            j += k;
        } while(true);
    }

    public Uri getPath()
    {
        return path;
    }

    public BitmapDrawable getPicture()
    {
        return picture;
    }

    public int getRotate()
    {
        return rotate;
    }

    public void setPath(Uri uri)
    {
        path = uri;
    }

    public void setPicture(BitmapDrawable bitmapdrawable)
    {
        picture = bitmapdrawable;
    }

    public void setRotate(int i)
    {
        rotate = i;
    }

    static final long LIMIT_IMAGE_SIZE = 0x19000L;
    private Uri path;
    private BitmapDrawable picture;
    private int rotate;
}
