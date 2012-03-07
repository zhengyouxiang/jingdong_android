// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix, MaskUtil, QRCode

public final class MatrixUtil
{

    private MatrixUtil()
    {
    }

    public static void buildMatrix(BitArray bitarray, ErrorCorrectionLevel errorcorrectionlevel, int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        clearMatrix(bytematrix);
        embedBasicPatterns(i, bytematrix);
        embedTypeInfo(errorcorrectionlevel, j, bytematrix);
        maybeEmbedVersionInfo(i, bytematrix);
        embedDataBits(bitarray, j, bytematrix);
    }

    public static int calculateBCHCode(int i, int j)
    {
        int k = findMSBSet(j);
        int l;
        for(l = i << k - 1; findMSBSet(l) >= k; l ^= j << findMSBSet(l) - k);
        return l;
    }

    public static void clearMatrix(ByteMatrix bytematrix)
    {
        bytematrix.clear((byte)-1);
    }

    public static void embedBasicPatterns(int i, ByteMatrix bytematrix)
        throws WriterException
    {
        embedPositionDetectionPatternsAndSeparators(bytematrix);
        embedDarkDotAtLeftBottomCorner(bytematrix);
        maybeEmbedPositionAdjustmentPatterns(i, bytematrix);
        embedTimingPatterns(bytematrix);
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix bytematrix)
        throws WriterException
    {
        if(bytematrix.get(8, bytematrix.getHeight() - 8) == 0)
        {
            throw new WriterException();
        } else
        {
            bytematrix.set(8, bytematrix.getHeight() - 8, 1);
            return;
        }
    }

    public static void embedDataBits(BitArray bitarray, int i, ByteMatrix bytematrix)
        throws WriterException
    {
        int j = bytematrix.getWidth() - 1;
        int k = bytematrix.getHeight() - 1;
        int l = -1;
        int i1 = 0;
        int j1 = j;
        int k1 = k;
        for(; j1 > 0; j1 += -2)
        {
            if(j1 == 6)
                j1--;
            while(k1 >= 0 && k1 < bytematrix.getHeight()) 
            {
                int l1 = i1;
                int i2 = 0;
                while(i2 < 2) 
                {
                    int j2 = j1 - i2;
                    if(isEmpty(bytematrix.get(j2, k1)))
                    {
                        int k2;
                        boolean flag;
                        if(l1 < bitarray.getSize())
                        {
                            boolean flag1 = bitarray.get(l1);
                            k2 = l1 + 1;
                            flag = flag1;
                        } else
                        {
                            k2 = l1;
                            flag = false;
                        }
                        if(i != -1 && MaskUtil.getDataMaskBit(i, j2, k1))
                            if(!flag)
                                flag = true;
                            else
                                flag = false;
                        bytematrix.set(j2, k1, flag);
                        l1 = k2;
                    }
                    i2++;
                }
                k1 += l;
                i1 = l1;
            }
            l = -l;
            k1 += l;
        }

        if(i1 != bitarray.getSize())
            throw new WriterException("Not all bits consumed: " + i1 + '/' + bitarray.getSize());
        else
            return;
    }

    private static void embedHorizontalSeparationPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        if(HORIZONTAL_SEPARATION_PATTERN[0].length != 8 || HORIZONTAL_SEPARATION_PATTERN.length != 1)
            throw new WriterException("Bad horizontal separation pattern");
        for(int k = 0; k < 8; k++)
        {
            if(!isEmpty(bytematrix.get(i + k, j)))
                throw new WriterException();
            bytematrix.set(i + k, j, HORIZONTAL_SEPARATION_PATTERN[0][k]);
        }

    }

    private static void embedPositionAdjustmentPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        if(POSITION_ADJUSTMENT_PATTERN[0].length != 5 || POSITION_ADJUSTMENT_PATTERN.length != 5)
            throw new WriterException("Bad position adjustment");
        for(int k = 0; k < 5; k++)
        {
            for(int l = 0; l < 5; l++)
            {
                if(!isEmpty(bytematrix.get(i + l, j + k)))
                    throw new WriterException();
                bytematrix.set(i + l, j + k, POSITION_ADJUSTMENT_PATTERN[k][l]);
            }

        }

    }

    private static void embedPositionDetectionPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        if(POSITION_DETECTION_PATTERN[0].length != 7 || POSITION_DETECTION_PATTERN.length != 7)
            throw new WriterException("Bad position detection pattern");
        for(int k = 0; k < 7; k++)
        {
            for(int l = 0; l < 7; l++)
            {
                if(!isEmpty(bytematrix.get(i + l, j + k)))
                    throw new WriterException();
                bytematrix.set(i + l, j + k, POSITION_DETECTION_PATTERN[k][l]);
            }

        }

    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix bytematrix)
        throws WriterException
    {
        int i = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, bytematrix);
        embedPositionDetectionPattern(bytematrix.getWidth() - i, 0, bytematrix);
        embedPositionDetectionPattern(0, bytematrix.getWidth() - i, bytematrix);
        int j = HORIZONTAL_SEPARATION_PATTERN[0].length;
        embedHorizontalSeparationPattern(0, j - 1, bytematrix);
        embedHorizontalSeparationPattern(bytematrix.getWidth() - j, j - 1, bytematrix);
        embedHorizontalSeparationPattern(0, bytematrix.getWidth() - j, bytematrix);
        int k = VERTICAL_SEPARATION_PATTERN.length;
        embedVerticalSeparationPattern(k, 0, bytematrix);
        embedVerticalSeparationPattern(bytematrix.getHeight() - k - 1, 0, bytematrix);
        embedVerticalSeparationPattern(k, bytematrix.getHeight() - k, bytematrix);
    }

    private static void embedTimingPatterns(ByteMatrix bytematrix)
        throws WriterException
    {
        for(int i = 8; i < bytematrix.getWidth() - 8; i++)
        {
            int j = (i + 1) % 2;
            if(!isValidValue(bytematrix.get(i, 6)))
                throw new WriterException();
            if(isEmpty(bytematrix.get(i, 6)))
                bytematrix.set(i, 6, j);
            if(!isValidValue(bytematrix.get(6, i)))
                throw new WriterException();
            if(isEmpty(bytematrix.get(6, i)))
                bytematrix.set(6, i, j);
        }

    }

    public static void embedTypeInfo(ErrorCorrectionLevel errorcorrectionlevel, int i, ByteMatrix bytematrix)
        throws WriterException
    {
        BitArray bitarray = new BitArray();
        makeTypeInfoBits(errorcorrectionlevel, i, bitarray);
        int j = 0;
        while(j < bitarray.getSize()) 
        {
            boolean flag = bitarray.get(bitarray.getSize() - 1 - j);
            bytematrix.set(TYPE_INFO_COORDINATES[j][0], TYPE_INFO_COORDINATES[j][1], flag);
            if(j < 8)
                bytematrix.set(bytematrix.getWidth() - j - 1, 8, flag);
            else
                bytematrix.set(8, (bytematrix.getHeight() - 7) + (j - 8), flag);
            j++;
        }
    }

    private static void embedVerticalSeparationPattern(int i, int j, ByteMatrix bytematrix)
        throws WriterException
    {
        if(VERTICAL_SEPARATION_PATTERN[0].length != 1 || VERTICAL_SEPARATION_PATTERN.length != 7)
            throw new WriterException("Bad vertical separation pattern");
        for(int k = 0; k < 7; k++)
        {
            if(!isEmpty(bytematrix.get(i, j + k)))
                throw new WriterException();
            bytematrix.set(i, j + k, VERTICAL_SEPARATION_PATTERN[k][0]);
        }

    }

    public static int findMSBSet(int i)
    {
        int j = 0;
        for(int k = i; k != 0;)
        {
            k >>>= 1;
            j++;
        }

        return j;
    }

    private static boolean isEmpty(int i)
    {
        boolean flag;
        if(i == -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static boolean isValidValue(int i)
    {
        boolean flag;
        if(i == -1 || i == 0 || i == 1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void makeTypeInfoBits(ErrorCorrectionLevel errorcorrectionlevel, int i, BitArray bitarray)
        throws WriterException
    {
        if(!QRCode.isValidMaskPattern(i))
            throw new WriterException("Invalid mask pattern");
        int j = i | errorcorrectionlevel.getBits() << 3;
        bitarray.appendBits(j, 5);
        bitarray.appendBits(calculateBCHCode(j, 1335), 10);
        BitArray bitarray1 = new BitArray();
        bitarray1.appendBits(21522, 15);
        bitarray.xor(bitarray1);
        if(bitarray.getSize() != 15)
            throw new WriterException("should not happen but we got: " + bitarray.getSize());
        else
            return;
    }

    public static void makeVersionInfoBits(int i, BitArray bitarray)
        throws WriterException
    {
        bitarray.appendBits(i, 6);
        bitarray.appendBits(calculateBCHCode(i, 7973), 12);
        if(bitarray.getSize() != 18)
            throw new WriterException("should not happen but we got: " + bitarray.getSize());
        else
            return;
    }

    private static void maybeEmbedPositionAdjustmentPatterns(int i, ByteMatrix bytematrix)
        throws WriterException
    {
        if(i >= 2) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = i - 1;
        int ai[] = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[j];
        int k = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[j].length;
        int l = 0;
        do
        {
            if(l >= k)
                continue;
            int i1 = 0;
            do
            {
                if(i1 >= k)
                    break;
                int j1 = ai[l];
                int k1 = ai[i1];
                if(k1 != -1 && j1 != -1 && isEmpty(bytematrix.get(k1, j1)))
                    embedPositionAdjustmentPattern(k1 - 2, j1 - 2, bytematrix);
                i1++;
            } while(true);
            l++;
        } while(true);
        if(true) goto _L1; else goto _L3
_L3:
    }

    public static void maybeEmbedVersionInfo(int i, ByteMatrix bytematrix)
        throws WriterException
    {
        if(i >= 7)
        {
            BitArray bitarray = new BitArray();
            makeVersionInfoBits(i, bitarray);
            int j = 17;
            int k = 0;
            while(k < 6) 
            {
                int l = j;
                for(int i1 = 0; i1 < 3; i1++)
                {
                    boolean flag = bitarray.get(l);
                    l--;
                    bytematrix.set(k, i1 + (bytematrix.getHeight() - 11), flag);
                    bytematrix.set(i1 + (bytematrix.getHeight() - 11), k, flag);
                }

                k++;
                j = l;
            }
        }
    }

    private static final int HORIZONTAL_SEPARATION_PATTERN[][];
    private static final int POSITION_ADJUSTMENT_PATTERN[][];
    private static final int POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[][];
    private static final int POSITION_DETECTION_PATTERN[][];
    private static final int TYPE_INFO_COORDINATES[][];
    private static final int TYPE_INFO_MASK_PATTERN = 21522;
    private static final int TYPE_INFO_POLY = 1335;
    private static final int VERSION_INFO_POLY = 7973;
    private static final int VERTICAL_SEPARATION_PATTERN[][];

    static 
    {
        int ai[][] = new int[7][];
        int ai1[] = new int[7];
        ai1[0] = 1;
        ai1[1] = 1;
        ai1[2] = 1;
        ai1[3] = 1;
        ai1[4] = 1;
        ai1[5] = 1;
        ai1[6] = 1;
        ai[0] = ai1;
        int ai2[] = new int[7];
        ai2[0] = 1;
        ai2[1] = 0;
        ai2[2] = 0;
        ai2[3] = 0;
        ai2[4] = 0;
        ai2[5] = 0;
        ai2[6] = 1;
        ai[1] = ai2;
        int ai3[] = new int[7];
        ai3[0] = 1;
        ai3[1] = 0;
        ai3[2] = 1;
        ai3[3] = 1;
        ai3[4] = 1;
        ai3[5] = 0;
        ai3[6] = 1;
        ai[2] = ai3;
        int ai4[] = new int[7];
        ai4[0] = 1;
        ai4[1] = 0;
        ai4[2] = 1;
        ai4[3] = 1;
        ai4[4] = 1;
        ai4[5] = 0;
        ai4[6] = 1;
        ai[3] = ai4;
        int ai5[] = new int[7];
        ai5[0] = 1;
        ai5[1] = 0;
        ai5[2] = 1;
        ai5[3] = 1;
        ai5[4] = 1;
        ai5[5] = 0;
        ai5[6] = 1;
        ai[4] = ai5;
        int ai6[] = new int[7];
        ai6[0] = 1;
        ai6[1] = 0;
        ai6[2] = 0;
        ai6[3] = 0;
        ai6[4] = 0;
        ai6[5] = 0;
        ai6[6] = 1;
        ai[5] = ai6;
        int ai7[] = new int[7];
        ai7[0] = 1;
        ai7[1] = 1;
        ai7[2] = 1;
        ai7[3] = 1;
        ai7[4] = 1;
        ai7[5] = 1;
        ai7[6] = 1;
        ai[6] = ai7;
        POSITION_DETECTION_PATTERN = ai;
        int ai8[][] = new int[1][];
        int ai9[] = new int[8];
        ai9[0] = 0;
        ai9[1] = 0;
        ai9[2] = 0;
        ai9[3] = 0;
        ai9[4] = 0;
        ai9[5] = 0;
        ai9[6] = 0;
        ai9[7] = 0;
        ai8[0] = ai9;
        HORIZONTAL_SEPARATION_PATTERN = ai8;
        int ai10[][] = new int[7][];
        int ai11[] = new int[1];
        ai11[0] = 0;
        ai10[0] = ai11;
        int ai12[] = new int[1];
        ai12[0] = 0;
        ai10[1] = ai12;
        int ai13[] = new int[1];
        ai13[0] = 0;
        ai10[2] = ai13;
        int ai14[] = new int[1];
        ai14[0] = 0;
        ai10[3] = ai14;
        int ai15[] = new int[1];
        ai15[0] = 0;
        ai10[4] = ai15;
        int ai16[] = new int[1];
        ai16[0] = 0;
        ai10[5] = ai16;
        int ai17[] = new int[1];
        ai17[0] = 0;
        ai10[6] = ai17;
        VERTICAL_SEPARATION_PATTERN = ai10;
        int ai18[][] = new int[5][];
        int ai19[] = new int[5];
        ai19[0] = 1;
        ai19[1] = 1;
        ai19[2] = 1;
        ai19[3] = 1;
        ai19[4] = 1;
        ai18[0] = ai19;
        int ai20[] = new int[5];
        ai20[0] = 1;
        ai20[1] = 0;
        ai20[2] = 0;
        ai20[3] = 0;
        ai20[4] = 1;
        ai18[1] = ai20;
        int ai21[] = new int[5];
        ai21[0] = 1;
        ai21[1] = 0;
        ai21[2] = 1;
        ai21[3] = 0;
        ai21[4] = 1;
        ai18[2] = ai21;
        int ai22[] = new int[5];
        ai22[0] = 1;
        ai22[1] = 0;
        ai22[2] = 0;
        ai22[3] = 0;
        ai22[4] = 1;
        ai18[3] = ai22;
        int ai23[] = new int[5];
        ai23[0] = 1;
        ai23[1] = 1;
        ai23[2] = 1;
        ai23[3] = 1;
        ai23[4] = 1;
        ai18[4] = ai23;
        POSITION_ADJUSTMENT_PATTERN = ai18;
        int ai24[][] = new int[40][];
        int ai25[] = new int[7];
        ai25[0] = -1;
        ai25[1] = -1;
        ai25[2] = -1;
        ai25[3] = -1;
        ai25[4] = -1;
        ai25[5] = -1;
        ai25[6] = -1;
        ai24[0] = ai25;
        int ai26[] = new int[7];
        ai26[0] = 6;
        ai26[1] = 18;
        ai26[2] = -1;
        ai26[3] = -1;
        ai26[4] = -1;
        ai26[5] = -1;
        ai26[6] = -1;
        ai24[1] = ai26;
        int ai27[] = new int[7];
        ai27[0] = 6;
        ai27[1] = 22;
        ai27[2] = -1;
        ai27[3] = -1;
        ai27[4] = -1;
        ai27[5] = -1;
        ai27[6] = -1;
        ai24[2] = ai27;
        int ai28[] = new int[7];
        ai28[0] = 6;
        ai28[1] = 26;
        ai28[2] = -1;
        ai28[3] = -1;
        ai28[4] = -1;
        ai28[5] = -1;
        ai28[6] = -1;
        ai24[3] = ai28;
        int ai29[] = new int[7];
        ai29[0] = 6;
        ai29[1] = 30;
        ai29[2] = -1;
        ai29[3] = -1;
        ai29[4] = -1;
        ai29[5] = -1;
        ai29[6] = -1;
        ai24[4] = ai29;
        int ai30[] = new int[7];
        ai30[0] = 6;
        ai30[1] = 34;
        ai30[2] = -1;
        ai30[3] = -1;
        ai30[4] = -1;
        ai30[5] = -1;
        ai30[6] = -1;
        ai24[5] = ai30;
        int ai31[] = new int[7];
        ai31[0] = 6;
        ai31[1] = 22;
        ai31[2] = 38;
        ai31[3] = -1;
        ai31[4] = -1;
        ai31[5] = -1;
        ai31[6] = -1;
        ai24[6] = ai31;
        int ai32[] = new int[7];
        ai32[0] = 6;
        ai32[1] = 24;
        ai32[2] = 42;
        ai32[3] = -1;
        ai32[4] = -1;
        ai32[5] = -1;
        ai32[6] = -1;
        ai24[7] = ai32;
        int ai33[] = new int[7];
        ai33[0] = 6;
        ai33[1] = 26;
        ai33[2] = 46;
        ai33[3] = -1;
        ai33[4] = -1;
        ai33[5] = -1;
        ai33[6] = -1;
        ai24[8] = ai33;
        int ai34[] = new int[7];
        ai34[0] = 6;
        ai34[1] = 28;
        ai34[2] = 50;
        ai34[3] = -1;
        ai34[4] = -1;
        ai34[5] = -1;
        ai34[6] = -1;
        ai24[9] = ai34;
        int ai35[] = new int[7];
        ai35[0] = 6;
        ai35[1] = 30;
        ai35[2] = 54;
        ai35[3] = -1;
        ai35[4] = -1;
        ai35[5] = -1;
        ai35[6] = -1;
        ai24[10] = ai35;
        int ai36[] = new int[7];
        ai36[0] = 6;
        ai36[1] = 32;
        ai36[2] = 58;
        ai36[3] = -1;
        ai36[4] = -1;
        ai36[5] = -1;
        ai36[6] = -1;
        ai24[11] = ai36;
        int ai37[] = new int[7];
        ai37[0] = 6;
        ai37[1] = 34;
        ai37[2] = 62;
        ai37[3] = -1;
        ai37[4] = -1;
        ai37[5] = -1;
        ai37[6] = -1;
        ai24[12] = ai37;
        int ai38[] = new int[7];
        ai38[0] = 6;
        ai38[1] = 26;
        ai38[2] = 46;
        ai38[3] = 66;
        ai38[4] = -1;
        ai38[5] = -1;
        ai38[6] = -1;
        ai24[13] = ai38;
        int ai39[] = new int[7];
        ai39[0] = 6;
        ai39[1] = 26;
        ai39[2] = 48;
        ai39[3] = 70;
        ai39[4] = -1;
        ai39[5] = -1;
        ai39[6] = -1;
        ai24[14] = ai39;
        int ai40[] = new int[7];
        ai40[0] = 6;
        ai40[1] = 26;
        ai40[2] = 50;
        ai40[3] = 74;
        ai40[4] = -1;
        ai40[5] = -1;
        ai40[6] = -1;
        ai24[15] = ai40;
        int ai41[] = new int[7];
        ai41[0] = 6;
        ai41[1] = 30;
        ai41[2] = 54;
        ai41[3] = 78;
        ai41[4] = -1;
        ai41[5] = -1;
        ai41[6] = -1;
        ai24[16] = ai41;
        int ai42[] = new int[7];
        ai42[0] = 6;
        ai42[1] = 30;
        ai42[2] = 56;
        ai42[3] = 82;
        ai42[4] = -1;
        ai42[5] = -1;
        ai42[6] = -1;
        ai24[17] = ai42;
        int ai43[] = new int[7];
        ai43[0] = 6;
        ai43[1] = 30;
        ai43[2] = 58;
        ai43[3] = 86;
        ai43[4] = -1;
        ai43[5] = -1;
        ai43[6] = -1;
        ai24[18] = ai43;
        int ai44[] = new int[7];
        ai44[0] = 6;
        ai44[1] = 34;
        ai44[2] = 62;
        ai44[3] = 90;
        ai44[4] = -1;
        ai44[5] = -1;
        ai44[6] = -1;
        ai24[19] = ai44;
        int ai45[] = new int[7];
        ai45[0] = 6;
        ai45[1] = 28;
        ai45[2] = 50;
        ai45[3] = 72;
        ai45[4] = 94;
        ai45[5] = -1;
        ai45[6] = -1;
        ai24[20] = ai45;
        int ai46[] = new int[7];
        ai46[0] = 6;
        ai46[1] = 26;
        ai46[2] = 50;
        ai46[3] = 74;
        ai46[4] = 98;
        ai46[5] = -1;
        ai46[6] = -1;
        ai24[21] = ai46;
        int ai47[] = new int[7];
        ai47[0] = 6;
        ai47[1] = 30;
        ai47[2] = 54;
        ai47[3] = 78;
        ai47[4] = 102;
        ai47[5] = -1;
        ai47[6] = -1;
        ai24[22] = ai47;
        int ai48[] = new int[7];
        ai48[0] = 6;
        ai48[1] = 28;
        ai48[2] = 54;
        ai48[3] = 80;
        ai48[4] = 106;
        ai48[5] = -1;
        ai48[6] = -1;
        ai24[23] = ai48;
        int ai49[] = new int[7];
        ai49[0] = 6;
        ai49[1] = 32;
        ai49[2] = 58;
        ai49[3] = 84;
        ai49[4] = 110;
        ai49[5] = -1;
        ai49[6] = -1;
        ai24[24] = ai49;
        int ai50[] = new int[7];
        ai50[0] = 6;
        ai50[1] = 30;
        ai50[2] = 58;
        ai50[3] = 86;
        ai50[4] = 114;
        ai50[5] = -1;
        ai50[6] = -1;
        ai24[25] = ai50;
        int ai51[] = new int[7];
        ai51[0] = 6;
        ai51[1] = 34;
        ai51[2] = 62;
        ai51[3] = 90;
        ai51[4] = 118;
        ai51[5] = -1;
        ai51[6] = -1;
        ai24[26] = ai51;
        int ai52[] = new int[7];
        ai52[0] = 6;
        ai52[1] = 26;
        ai52[2] = 50;
        ai52[3] = 74;
        ai52[4] = 98;
        ai52[5] = 122;
        ai52[6] = -1;
        ai24[27] = ai52;
        int ai53[] = new int[7];
        ai53[0] = 6;
        ai53[1] = 30;
        ai53[2] = 54;
        ai53[3] = 78;
        ai53[4] = 102;
        ai53[5] = 126;
        ai53[6] = -1;
        ai24[28] = ai53;
        int ai54[] = new int[7];
        ai54[0] = 6;
        ai54[1] = 26;
        ai54[2] = 52;
        ai54[3] = 78;
        ai54[4] = 104;
        ai54[5] = 130;
        ai54[6] = -1;
        ai24[29] = ai54;
        int ai55[] = new int[7];
        ai55[0] = 6;
        ai55[1] = 30;
        ai55[2] = 56;
        ai55[3] = 82;
        ai55[4] = 108;
        ai55[5] = 134;
        ai55[6] = -1;
        ai24[30] = ai55;
        int ai56[] = new int[7];
        ai56[0] = 6;
        ai56[1] = 34;
        ai56[2] = 60;
        ai56[3] = 86;
        ai56[4] = 112;
        ai56[5] = 138;
        ai56[6] = -1;
        ai24[31] = ai56;
        int ai57[] = new int[7];
        ai57[0] = 6;
        ai57[1] = 30;
        ai57[2] = 58;
        ai57[3] = 86;
        ai57[4] = 114;
        ai57[5] = 142;
        ai57[6] = -1;
        ai24[32] = ai57;
        int ai58[] = new int[7];
        ai58[0] = 6;
        ai58[1] = 34;
        ai58[2] = 62;
        ai58[3] = 90;
        ai58[4] = 118;
        ai58[5] = 146;
        ai58[6] = -1;
        ai24[33] = ai58;
        int ai59[] = new int[7];
        ai59[0] = 6;
        ai59[1] = 30;
        ai59[2] = 54;
        ai59[3] = 78;
        ai59[4] = 102;
        ai59[5] = 126;
        ai59[6] = 150;
        ai24[34] = ai59;
        int ai60[] = new int[7];
        ai60[0] = 6;
        ai60[1] = 24;
        ai60[2] = 50;
        ai60[3] = 76;
        ai60[4] = 102;
        ai60[5] = 128;
        ai60[6] = 154;
        ai24[35] = ai60;
        int ai61[] = new int[7];
        ai61[0] = 6;
        ai61[1] = 28;
        ai61[2] = 54;
        ai61[3] = 80;
        ai61[4] = 106;
        ai61[5] = 132;
        ai61[6] = 158;
        ai24[36] = ai61;
        int ai62[] = new int[7];
        ai62[0] = 6;
        ai62[1] = 32;
        ai62[2] = 58;
        ai62[3] = 84;
        ai62[4] = 110;
        ai62[5] = 136;
        ai62[6] = 162;
        ai24[37] = ai62;
        int ai63[] = new int[7];
        ai63[0] = 6;
        ai63[1] = 26;
        ai63[2] = 54;
        ai63[3] = 82;
        ai63[4] = 110;
        ai63[5] = 138;
        ai63[6] = 166;
        ai24[38] = ai63;
        int ai64[] = new int[7];
        ai64[0] = 6;
        ai64[1] = 30;
        ai64[2] = 58;
        ai64[3] = 86;
        ai64[4] = 114;
        ai64[5] = 142;
        ai64[6] = 170;
        ai24[39] = ai64;
        POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = ai24;
        int ai65[][] = new int[15][];
        int ai66[] = new int[2];
        ai66[0] = 8;
        ai66[1] = 0;
        ai65[0] = ai66;
        int ai67[] = new int[2];
        ai67[0] = 8;
        ai67[1] = 1;
        ai65[1] = ai67;
        int ai68[] = new int[2];
        ai68[0] = 8;
        ai68[1] = 2;
        ai65[2] = ai68;
        int ai69[] = new int[2];
        ai69[0] = 8;
        ai69[1] = 3;
        ai65[3] = ai69;
        int ai70[] = new int[2];
        ai70[0] = 8;
        ai70[1] = 4;
        ai65[4] = ai70;
        int ai71[] = new int[2];
        ai71[0] = 8;
        ai71[1] = 5;
        ai65[5] = ai71;
        int ai72[] = new int[2];
        ai72[0] = 8;
        ai72[1] = 7;
        ai65[6] = ai72;
        int ai73[] = new int[2];
        ai73[0] = 8;
        ai73[1] = 8;
        ai65[7] = ai73;
        int ai74[] = new int[2];
        ai74[0] = 7;
        ai74[1] = 8;
        ai65[8] = ai74;
        int ai75[] = new int[2];
        ai75[0] = 5;
        ai75[1] = 8;
        ai65[9] = ai75;
        int ai76[] = new int[2];
        ai76[0] = 4;
        ai76[1] = 8;
        ai65[10] = ai76;
        int ai77[] = new int[2];
        ai77[0] = 3;
        ai77[1] = 8;
        ai65[11] = ai77;
        int ai78[] = new int[2];
        ai78[0] = 2;
        ai78[1] = 8;
        ai65[12] = ai78;
        int ai79[] = new int[2];
        ai79[0] = 1;
        ai79[1] = 8;
        ai65[13] = ai79;
        int ai80[] = new int[2];
        ai80[0] = 0;
        ai80[1] = 8;
        ai65[14] = ai80;
        TYPE_INFO_COORDINATES = ai65;
    }
}
