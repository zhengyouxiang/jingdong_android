// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Hashtable;

public final class QRCodeWriter
    implements Writer
{

    public QRCodeWriter()
    {
    }

    private static BitMatrix renderResult(QRCode qrcode, int i, int j)
    {
        ByteMatrix bytematrix = qrcode.getMatrix();
        int k = bytematrix.getWidth();
        int l = bytematrix.getHeight();
        int i1 = k + 8;
        int j1 = l + 8;
        int k1 = Math.max(i, i1);
        int l1 = Math.max(j, j1);
        int i2 = Math.min(k1 / i1, l1 / j1);
        int j2 = (k1 - k * i2) / 2;
        int k2 = (l1 - l * i2) / 2;
        BitMatrix bitmatrix = new BitMatrix(k1, l1);
        int l2 = k2;
        for(int i3 = 0; i3 < l;)
        {
            int j3 = j2;
            for(int k3 = 0; k3 < k;)
            {
                if(bytematrix.get(k3, i3) == 1)
                    bitmatrix.setRegion(j3, l2, i2, i2);
                k3++;
                j3 += i2;
            }

            i3++;
            l2 += i2;
        }

        return bitmatrix;
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException("Found empty contents");
        if(barcodeformat != BarcodeFormat.QR_CODE)
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeformat);
        if(i < 0 || j < 0)
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + j);
        ErrorCorrectionLevel errorcorrectionlevel = ErrorCorrectionLevel.L;
        if(hashtable != null)
        {
            ErrorCorrectionLevel errorcorrectionlevel1 = (ErrorCorrectionLevel)hashtable.get(EncodeHintType.ERROR_CORRECTION);
            if(errorcorrectionlevel1 != null)
                errorcorrectionlevel = errorcorrectionlevel1;
        }
        QRCode qrcode = new QRCode();
        Encoder.encode(s, errorcorrectionlevel, hashtable, qrcode);
        return renderResult(qrcode, i, j);
    }

    private static final int QUIET_ZONE_SIZE = 4;
}
