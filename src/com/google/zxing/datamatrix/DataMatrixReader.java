// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.datamatrix.decoder.Decoder;
import com.google.zxing.datamatrix.detector.Detector;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

public final class DataMatrixReader
    implements Reader
{

    public DataMatrixReader()
    {
    }

    private static BitMatrix extractPureBits(BitMatrix bitmatrix)
        throws NotFoundException
    {
        int i = bitmatrix.getHeight();
        int j = bitmatrix.getWidth();
        int k = Math.min(i, j);
        int ai[] = bitmatrix.getTopLeftOnBit();
        if(ai == null)
            throw NotFoundException.getNotFoundInstance();
        int l = ai[0];
        int i1;
        for(i1 = ai[1]; l < k && i1 < k && bitmatrix.get(l, i1); l++);
        if(l == k)
            throw NotFoundException.getNotFoundInstance();
        int j1 = l - ai[0];
        int k1;
        for(k1 = j - 1; k1 >= 0 && !bitmatrix.get(k1, i1); k1--);
        if(k1 < 0)
            throw NotFoundException.getNotFoundInstance();
        int l1 = k1 + 1;
        if((l1 - l) % j1 != 0)
            throw NotFoundException.getNotFoundInstance();
        int i2 = 2 + (l1 - l) / j1;
        int j2 = i1 + j1;
        int k2 = l - (j1 >> 1);
        int l2 = j2 - (j1 >> 1);
        if(k2 + j1 * (i2 - 1) >= j || l2 + j1 * (i2 - 1) >= i)
            throw NotFoundException.getNotFoundInstance();
        BitMatrix bitmatrix1 = new BitMatrix(i2);
        for(int i3 = 0; i3 < i2; i3++)
        {
            int j3 = l2 + i3 * j1;
            for(int k3 = 0; k3 < i2; k3++)
                if(bitmatrix.get(k2 + k3 * j1, j3))
                    bitmatrix1.set(k3, i3);

        }

        return bitmatrix1;
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, ChecksumException, FormatException
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        DecoderResult decoderresult;
        ResultPoint aresultpoint[];
        Result result;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.PURE_BARCODE))
        {
            BitMatrix bitmatrix = extractPureBits(binarybitmap.getBlackMatrix());
            DecoderResult decoderresult1 = decoder.decode(bitmatrix);
            ResultPoint aresultpoint1[] = NO_POINTS;
            decoderresult = decoderresult1;
            aresultpoint = aresultpoint1;
        } else
        {
            DetectorResult detectorresult = (new Detector(binarybitmap.getBlackMatrix())).detect();
            decoderresult = decoder.decode(detectorresult.getBits());
            aresultpoint = detectorresult.getPoints();
        }
        result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.DATA_MATRIX);
        if(decoderresult.getByteSegments() != null)
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderresult.getByteSegments());
        if(decoderresult.getECLevel() != null)
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderresult.getECLevel().toString());
        return result;
    }

    public void reset()
    {
    }

    private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

}
