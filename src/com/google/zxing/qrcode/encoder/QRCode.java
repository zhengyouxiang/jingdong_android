// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix

public final class QRCode
{

    public QRCode()
    {
        mode = null;
        ecLevel = null;
        version = -1;
        matrixWidth = -1;
        maskPattern = -1;
        numTotalBytes = -1;
        numDataBytes = -1;
        numECBytes = -1;
        numRSBlocks = -1;
        matrix = null;
    }

    public static boolean isValidMaskPattern(int i)
    {
        boolean flag;
        if(i >= 0 && i < 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int at(int i, int j)
    {
        byte byte0 = matrix.get(i, j);
        if(byte0 != 0 && byte0 != 1)
            throw new RuntimeException("Bad value");
        else
            return byte0;
    }

    public ErrorCorrectionLevel getECLevel()
    {
        return ecLevel;
    }

    public int getMaskPattern()
    {
        return maskPattern;
    }

    public ByteMatrix getMatrix()
    {
        return matrix;
    }

    public int getMatrixWidth()
    {
        return matrixWidth;
    }

    public Mode getMode()
    {
        return mode;
    }

    public int getNumDataBytes()
    {
        return numDataBytes;
    }

    public int getNumECBytes()
    {
        return numECBytes;
    }

    public int getNumRSBlocks()
    {
        return numRSBlocks;
    }

    public int getNumTotalBytes()
    {
        return numTotalBytes;
    }

    public int getVersion()
    {
        return version;
    }

    public boolean isValid()
    {
        boolean flag;
        if(mode != null && ecLevel != null && version != -1 && matrixWidth != -1 && maskPattern != -1 && numTotalBytes != -1 && numDataBytes != -1 && numECBytes != -1 && numRSBlocks != -1 && isValidMaskPattern(maskPattern) && numTotalBytes == numDataBytes + numECBytes && matrix != null && matrixWidth == matrix.getWidth() && matrix.getWidth() == matrix.getHeight())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setECLevel(ErrorCorrectionLevel errorcorrectionlevel)
    {
        ecLevel = errorcorrectionlevel;
    }

    public void setMaskPattern(int i)
    {
        maskPattern = i;
    }

    public void setMatrix(ByteMatrix bytematrix)
    {
        matrix = bytematrix;
    }

    public void setMatrixWidth(int i)
    {
        matrixWidth = i;
    }

    public void setMode(Mode mode1)
    {
        mode = mode1;
    }

    public void setNumDataBytes(int i)
    {
        numDataBytes = i;
    }

    public void setNumECBytes(int i)
    {
        numECBytes = i;
    }

    public void setNumRSBlocks(int i)
    {
        numRSBlocks = i;
    }

    public void setNumTotalBytes(int i)
    {
        numTotalBytes = i;
    }

    public void setVersion(int i)
    {
        version = i;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(200);
        stringbuffer.append("<<\n");
        stringbuffer.append(" mode: ");
        stringbuffer.append(mode);
        stringbuffer.append("\n ecLevel: ");
        stringbuffer.append(ecLevel);
        stringbuffer.append("\n version: ");
        stringbuffer.append(version);
        stringbuffer.append("\n matrixWidth: ");
        stringbuffer.append(matrixWidth);
        stringbuffer.append("\n maskPattern: ");
        stringbuffer.append(maskPattern);
        stringbuffer.append("\n numTotalBytes: ");
        stringbuffer.append(numTotalBytes);
        stringbuffer.append("\n numDataBytes: ");
        stringbuffer.append(numDataBytes);
        stringbuffer.append("\n numECBytes: ");
        stringbuffer.append(numECBytes);
        stringbuffer.append("\n numRSBlocks: ");
        stringbuffer.append(numRSBlocks);
        if(matrix == null)
        {
            stringbuffer.append("\n matrix: null\n");
        } else
        {
            stringbuffer.append("\n matrix:\n");
            stringbuffer.append(matrix.toString());
        }
        stringbuffer.append(">>\n");
        return stringbuffer.toString();
    }

    public static final int NUM_MASK_PATTERNS = 8;
    private ErrorCorrectionLevel ecLevel;
    private int maskPattern;
    private ByteMatrix matrix;
    private int matrixWidth;
    private Mode mode;
    private int numDataBytes;
    private int numECBytes;
    private int numRSBlocks;
    private int numTotalBytes;
    private int version;
}
