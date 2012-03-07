// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.imaging.jpeg;

import java.io.*;

// Referenced classes of package com.drew.imaging.jpeg:
//            JpegProcessingException, JpegSegmentData

public class JpegSegmentReader
{

    public JpegSegmentReader(JpegSegmentData jpegsegmentdata)
    {
        _file = null;
        _data = null;
        _stream = null;
        _segmentData = jpegsegmentdata;
    }

    public JpegSegmentReader(File file)
        throws JpegProcessingException
    {
        _file = file;
        _data = null;
        _stream = null;
        readSegments();
    }

    public JpegSegmentReader(InputStream inputstream)
        throws JpegProcessingException
    {
        _stream = inputstream;
        _file = null;
        _data = null;
        readSegments();
    }

    public JpegSegmentReader(byte abyte0[])
        throws JpegProcessingException
    {
        _file = null;
        _data = abyte0;
        _stream = null;
        readSegments();
    }

    private BufferedInputStream getJpegInputStream()
        throws JpegProcessingException
    {
        BufferedInputStream bufferedinputstream;
        if(_stream != null)
        {
            if(_stream instanceof BufferedInputStream)
                bufferedinputstream = (BufferedInputStream)_stream;
            else
                bufferedinputstream = new BufferedInputStream(_stream);
        } else
        {
            Object obj;
            if(_data == null)
                try
                {
                    obj = new FileInputStream(_file);
                }
                catch(FileNotFoundException filenotfoundexception)
                {
                    throw new JpegProcessingException("Jpeg file does not exist", filenotfoundexception);
                }
            else
                obj = new ByteArrayInputStream(_data);
            bufferedinputstream = new BufferedInputStream(((InputStream) (obj)));
        }
        return bufferedinputstream;
    }

    private boolean isValidJpegHeaderBytes(InputStream inputstream)
        throws IOException
    {
        byte abyte0[] = new byte[2];
        inputstream.read(abyte0, 0, 2);
        boolean flag;
        if((0xff & abyte0[0]) == 255 && (0xff & abyte0[1]) == 216)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void readSegments()
        throws JpegProcessingException
    {
        BufferedInputStream bufferedinputstream;
        _segmentData = new JpegSegmentData();
        bufferedinputstream = getJpegInputStream();
        if(!isValidJpegHeaderBytes(bufferedinputstream))
            throw new JpegProcessingException("not a jpeg file");
        break MISSING_BLOCK_LABEL_77;
        IOException ioexception1;
        ioexception1;
        throw new JpegProcessingException((new StringBuilder("IOException processing Jpeg file: ")).append(ioexception1.getMessage()).toString(), ioexception1);
        Exception exception;
        exception;
        int i;
        byte byte0;
        int j;
        byte byte1;
        int k;
        byte abyte0[];
        int l;
        int i1;
        byte abyte1[];
        IOException ioexception2;
        if(bufferedinputstream != null)
            try
            {
                bufferedinputstream.close();
            }
            catch(IOException ioexception)
            {
                throw new JpegProcessingException((new StringBuilder("IOException processing Jpeg file: ")).append(ioexception.getMessage()).toString(), ioexception);
            }
        throw exception;
        i = 0 + 2;
_L4:
        byte0 = (byte)(0xff & bufferedinputstream.read());
        if((byte0 & 0xff) != 255)
            throw new JpegProcessingException((new StringBuilder("expected jpeg segment start identifier 0xFF at offset ")).append(i).append(", not 0x").append(Integer.toHexString(byte0 & 0xff)).toString());
        j = i + 1;
        byte1 = (byte)(0xff & bufferedinputstream.read());
        k = j + 1;
        abyte0 = new byte[2];
        bufferedinputstream.read(abyte0, 0, 2);
        l = k + 2;
        i1 = -2 + (0xff00 & abyte0[0] << 8 | 0xff & abyte0[1]);
        if(i1 > bufferedinputstream.available())
            throw new JpegProcessingException("segment size would extend beyond file stream length");
        if(i1 < 0)
            throw new JpegProcessingException("segment size would be less than zero");
        abyte1 = new byte[i1];
        bufferedinputstream.read(abyte1, 0, i1);
        i = l + i1;
          goto _L1
_L3:
        if(bufferedinputstream == null)
            break MISSING_BLOCK_LABEL_291;
        bufferedinputstream.close();
        return;
        ioexception2;
        throw new JpegProcessingException((new StringBuilder("IOException processing Jpeg file: ")).append(ioexception2.getMessage()).toString(), ioexception2);
_L1:
        if((byte1 & 0xff) == 218 || (byte1 & 0xff) == 217) goto _L3; else goto _L2
_L2:
        _segmentData.addSegment(byte1, abyte1);
          goto _L4
    }

    public final int getSegmentCount(byte byte0)
    {
        return _segmentData.getSegmentCount(byte0);
    }

    public final JpegSegmentData getSegmentData()
    {
        return _segmentData;
    }

    public byte[] readSegment(byte byte0)
        throws JpegProcessingException
    {
        return readSegment(byte0, 0);
    }

    public byte[] readSegment(byte byte0, int i)
    {
        return _segmentData.getSegment(byte0, i);
    }

    private static final byte MARKER_EOI = -39;
    public static final byte SEGMENT_APP0 = -32;
    public static final byte SEGMENT_APP1 = -31;
    public static final byte SEGMENT_APP2 = -30;
    public static final byte SEGMENT_APP3 = -29;
    public static final byte SEGMENT_APP4 = -28;
    public static final byte SEGMENT_APP5 = -27;
    public static final byte SEGMENT_APP6 = -26;
    public static final byte SEGMENT_APP7 = -25;
    public static final byte SEGMENT_APP8 = -24;
    public static final byte SEGMENT_APP9 = -23;
    public static final byte SEGMENT_APPA = -22;
    public static final byte SEGMENT_APPB = -21;
    public static final byte SEGMENT_APPC = -20;
    public static final byte SEGMENT_APPD = -19;
    public static final byte SEGMENT_APPE = -18;
    public static final byte SEGMENT_APPF = -17;
    public static final byte SEGMENT_COM = -2;
    public static final byte SEGMENT_DHT = -60;
    public static final byte SEGMENT_DQT = -37;
    public static final byte SEGMENT_SOF0 = -64;
    public static final byte SEGMENT_SOI = -40;
    private static final byte SEGMENT_SOS = -38;
    private final byte _data[];
    private final File _file;
    private JpegSegmentData _segmentData;
    private final InputStream _stream;
}
