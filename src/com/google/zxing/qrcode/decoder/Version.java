// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            FormatInformation, ErrorCorrectionLevel

public final class Version
{
    public static final class ECB
    {

        public int getCount()
        {
            return count;
        }

        public int getDataCodewords()
        {
            return dataCodewords;
        }

        private final int count;
        private final int dataCodewords;

        ECB(int i, int j)
        {
            count = i;
            dataCodewords = j;
        }
    }

    public static final class ECBlocks
    {

        public ECB[] getECBlocks()
        {
            return ecBlocks;
        }

        public int getECCodewordsPerBlock()
        {
            return ecCodewordsPerBlock;
        }

        public int getNumBlocks()
        {
            int i = 0;
            int j = 0;
            for(; i < ecBlocks.length; i++)
                j += ecBlocks[i].getCount();

            return j;
        }

        public int getTotalECCodewords()
        {
            return ecCodewordsPerBlock * getNumBlocks();
        }

        private final ECB ecBlocks[];
        private final int ecCodewordsPerBlock;

        ECBlocks(int i, ECB ecb)
        {
            ecCodewordsPerBlock = i;
            ECB aecb[] = new ECB[1];
            aecb[0] = ecb;
            ecBlocks = aecb;
        }

        ECBlocks(int i, ECB ecb, ECB ecb1)
        {
            ecCodewordsPerBlock = i;
            ECB aecb[] = new ECB[2];
            aecb[0] = ecb;
            aecb[1] = ecb1;
            ecBlocks = aecb;
        }
    }


    private Version(int i, int ai[], ECBlocks ecblocks, ECBlocks ecblocks1, ECBlocks ecblocks2, ECBlocks ecblocks3)
    {
        int j = 0;
        super();
        versionNumber = i;
        alignmentPatternCenters = ai;
        ECBlocks aecblocks[] = new ECBlocks[4];
        aecblocks[j] = ecblocks;
        aecblocks[1] = ecblocks1;
        aecblocks[2] = ecblocks2;
        aecblocks[3] = ecblocks3;
        ecBlocks = aecblocks;
        int k = ecblocks.getECCodewordsPerBlock();
        ECB aecb[] = ecblocks.getECBlocks();
        int l = 0;
        for(; j < aecb.length; j++)
        {
            ECB ecb = aecb[j];
            l += ecb.getCount() * (k + ecb.getDataCodewords());
        }

        totalCodewords = l;
    }

    private static Version[] buildVersions()
    {
        Version aversion[] = new Version[40];
        aversion[0] = new Version(1, new int[0], new ECBlocks(7, new ECB(1, 19)), new ECBlocks(10, new ECB(1, 16)), new ECBlocks(13, new ECB(1, 13)), new ECBlocks(17, new ECB(1, 9)));
        int ai[] = new int[2];
        ai[0] = 6;
        ai[1] = 18;
        aversion[1] = new Version(2, ai, new ECBlocks(10, new ECB(1, 34)), new ECBlocks(16, new ECB(1, 28)), new ECBlocks(22, new ECB(1, 22)), new ECBlocks(28, new ECB(1, 16)));
        int ai1[] = new int[2];
        ai1[0] = 6;
        ai1[1] = 22;
        aversion[2] = new Version(3, ai1, new ECBlocks(15, new ECB(1, 55)), new ECBlocks(26, new ECB(1, 44)), new ECBlocks(18, new ECB(2, 17)), new ECBlocks(22, new ECB(2, 13)));
        int ai2[] = new int[2];
        ai2[0] = 6;
        ai2[1] = 26;
        aversion[3] = new Version(4, ai2, new ECBlocks(20, new ECB(1, 80)), new ECBlocks(18, new ECB(2, 32)), new ECBlocks(26, new ECB(2, 24)), new ECBlocks(16, new ECB(4, 9)));
        int ai3[] = new int[2];
        ai3[0] = 6;
        ai3[1] = 30;
        aversion[4] = new Version(5, ai3, new ECBlocks(26, new ECB(1, 108)), new ECBlocks(24, new ECB(2, 43)), new ECBlocks(18, new ECB(2, 15), new ECB(2, 16)), new ECBlocks(22, new ECB(2, 11), new ECB(2, 12)));
        int ai4[] = new int[2];
        ai4[0] = 6;
        ai4[1] = 34;
        aversion[5] = new Version(6, ai4, new ECBlocks(18, new ECB(2, 68)), new ECBlocks(16, new ECB(4, 27)), new ECBlocks(24, new ECB(4, 19)), new ECBlocks(28, new ECB(4, 15)));
        int ai5[] = new int[3];
        ai5[0] = 6;
        ai5[1] = 22;
        ai5[2] = 38;
        aversion[6] = new Version(7, ai5, new ECBlocks(20, new ECB(2, 78)), new ECBlocks(18, new ECB(4, 31)), new ECBlocks(18, new ECB(2, 14), new ECB(4, 15)), new ECBlocks(26, new ECB(4, 13), new ECB(1, 14)));
        int ai6[] = new int[3];
        ai6[0] = 6;
        ai6[1] = 24;
        ai6[2] = 42;
        aversion[7] = new Version(8, ai6, new ECBlocks(24, new ECB(2, 97)), new ECBlocks(22, new ECB(2, 38), new ECB(2, 39)), new ECBlocks(22, new ECB(4, 18), new ECB(2, 19)), new ECBlocks(26, new ECB(4, 14), new ECB(2, 15)));
        int ai7[] = new int[3];
        ai7[0] = 6;
        ai7[1] = 26;
        ai7[2] = 46;
        aversion[8] = new Version(9, ai7, new ECBlocks(30, new ECB(2, 116)), new ECBlocks(22, new ECB(3, 36), new ECB(2, 37)), new ECBlocks(20, new ECB(4, 16), new ECB(4, 17)), new ECBlocks(24, new ECB(4, 12), new ECB(4, 13)));
        int ai8[] = new int[3];
        ai8[0] = 6;
        ai8[1] = 28;
        ai8[2] = 50;
        aversion[9] = new Version(10, ai8, new ECBlocks(18, new ECB(2, 68), new ECB(2, 69)), new ECBlocks(26, new ECB(4, 43), new ECB(1, 44)), new ECBlocks(24, new ECB(6, 19), new ECB(2, 20)), new ECBlocks(28, new ECB(6, 15), new ECB(2, 16)));
        int ai9[] = new int[3];
        ai9[0] = 6;
        ai9[1] = 30;
        ai9[2] = 54;
        aversion[10] = new Version(11, ai9, new ECBlocks(20, new ECB(4, 81)), new ECBlocks(30, new ECB(1, 50), new ECB(4, 51)), new ECBlocks(28, new ECB(4, 22), new ECB(4, 23)), new ECBlocks(24, new ECB(3, 12), new ECB(8, 13)));
        int ai10[] = new int[3];
        ai10[0] = 6;
        ai10[1] = 32;
        ai10[2] = 58;
        aversion[11] = new Version(12, ai10, new ECBlocks(24, new ECB(2, 92), new ECB(2, 93)), new ECBlocks(22, new ECB(6, 36), new ECB(2, 37)), new ECBlocks(26, new ECB(4, 20), new ECB(6, 21)), new ECBlocks(28, new ECB(7, 14), new ECB(4, 15)));
        int ai11[] = new int[3];
        ai11[0] = 6;
        ai11[1] = 34;
        ai11[2] = 62;
        aversion[12] = new Version(13, ai11, new ECBlocks(26, new ECB(4, 107)), new ECBlocks(22, new ECB(8, 37), new ECB(1, 38)), new ECBlocks(24, new ECB(8, 20), new ECB(4, 21)), new ECBlocks(22, new ECB(12, 11), new ECB(4, 12)));
        int ai12[] = new int[4];
        ai12[0] = 6;
        ai12[1] = 26;
        ai12[2] = 46;
        ai12[3] = 66;
        aversion[13] = new Version(14, ai12, new ECBlocks(30, new ECB(3, 115), new ECB(1, 116)), new ECBlocks(24, new ECB(4, 40), new ECB(5, 41)), new ECBlocks(20, new ECB(11, 16), new ECB(5, 17)), new ECBlocks(24, new ECB(11, 12), new ECB(5, 13)));
        int ai13[] = new int[4];
        ai13[0] = 6;
        ai13[1] = 26;
        ai13[2] = 48;
        ai13[3] = 70;
        aversion[14] = new Version(15, ai13, new ECBlocks(22, new ECB(5, 87), new ECB(1, 88)), new ECBlocks(24, new ECB(5, 41), new ECB(5, 42)), new ECBlocks(30, new ECB(5, 24), new ECB(7, 25)), new ECBlocks(24, new ECB(11, 12), new ECB(7, 13)));
        int ai14[] = new int[4];
        ai14[0] = 6;
        ai14[1] = 26;
        ai14[2] = 50;
        ai14[3] = 74;
        aversion[15] = new Version(16, ai14, new ECBlocks(24, new ECB(5, 98), new ECB(1, 99)), new ECBlocks(28, new ECB(7, 45), new ECB(3, 46)), new ECBlocks(24, new ECB(15, 19), new ECB(2, 20)), new ECBlocks(30, new ECB(3, 15), new ECB(13, 16)));
        int ai15[] = new int[4];
        ai15[0] = 6;
        ai15[1] = 30;
        ai15[2] = 54;
        ai15[3] = 78;
        aversion[16] = new Version(17, ai15, new ECBlocks(28, new ECB(1, 107), new ECB(5, 108)), new ECBlocks(28, new ECB(10, 46), new ECB(1, 47)), new ECBlocks(28, new ECB(1, 22), new ECB(15, 23)), new ECBlocks(28, new ECB(2, 14), new ECB(17, 15)));
        int ai16[] = new int[4];
        ai16[0] = 6;
        ai16[1] = 30;
        ai16[2] = 56;
        ai16[3] = 82;
        aversion[17] = new Version(18, ai16, new ECBlocks(30, new ECB(5, 120), new ECB(1, 121)), new ECBlocks(26, new ECB(9, 43), new ECB(4, 44)), new ECBlocks(28, new ECB(17, 22), new ECB(1, 23)), new ECBlocks(28, new ECB(2, 14), new ECB(19, 15)));
        int ai17[] = new int[4];
        ai17[0] = 6;
        ai17[1] = 30;
        ai17[2] = 58;
        ai17[3] = 86;
        aversion[18] = new Version(19, ai17, new ECBlocks(28, new ECB(3, 113), new ECB(4, 114)), new ECBlocks(26, new ECB(3, 44), new ECB(11, 45)), new ECBlocks(26, new ECB(17, 21), new ECB(4, 22)), new ECBlocks(26, new ECB(9, 13), new ECB(16, 14)));
        int ai18[] = new int[4];
        ai18[0] = 6;
        ai18[1] = 34;
        ai18[2] = 62;
        ai18[3] = 90;
        aversion[19] = new Version(20, ai18, new ECBlocks(28, new ECB(3, 107), new ECB(5, 108)), new ECBlocks(26, new ECB(3, 41), new ECB(13, 42)), new ECBlocks(30, new ECB(15, 24), new ECB(5, 25)), new ECBlocks(28, new ECB(15, 15), new ECB(10, 16)));
        int ai19[] = new int[5];
        ai19[0] = 6;
        ai19[1] = 28;
        ai19[2] = 50;
        ai19[3] = 72;
        ai19[4] = 94;
        aversion[20] = new Version(21, ai19, new ECBlocks(28, new ECB(4, 116), new ECB(4, 117)), new ECBlocks(26, new ECB(17, 42)), new ECBlocks(28, new ECB(17, 22), new ECB(6, 23)), new ECBlocks(30, new ECB(19, 16), new ECB(6, 17)));
        int ai20[] = new int[5];
        ai20[0] = 6;
        ai20[1] = 26;
        ai20[2] = 50;
        ai20[3] = 74;
        ai20[4] = 98;
        aversion[21] = new Version(22, ai20, new ECBlocks(28, new ECB(2, 111), new ECB(7, 112)), new ECBlocks(28, new ECB(17, 46)), new ECBlocks(30, new ECB(7, 24), new ECB(16, 25)), new ECBlocks(24, new ECB(34, 13)));
        int ai21[] = new int[5];
        ai21[0] = 6;
        ai21[1] = 30;
        ai21[2] = 54;
        ai21[3] = 78;
        ai21[4] = 102;
        aversion[22] = new Version(23, ai21, new ECBlocks(30, new ECB(4, 121), new ECB(5, 122)), new ECBlocks(28, new ECB(4, 47), new ECB(14, 48)), new ECBlocks(30, new ECB(11, 24), new ECB(14, 25)), new ECBlocks(30, new ECB(16, 15), new ECB(14, 16)));
        int ai22[] = new int[5];
        ai22[0] = 6;
        ai22[1] = 28;
        ai22[2] = 54;
        ai22[3] = 80;
        ai22[4] = 106;
        aversion[23] = new Version(24, ai22, new ECBlocks(30, new ECB(6, 117), new ECB(4, 118)), new ECBlocks(28, new ECB(6, 45), new ECB(14, 46)), new ECBlocks(30, new ECB(11, 24), new ECB(16, 25)), new ECBlocks(30, new ECB(30, 16), new ECB(2, 17)));
        int ai23[] = new int[5];
        ai23[0] = 6;
        ai23[1] = 32;
        ai23[2] = 58;
        ai23[3] = 84;
        ai23[4] = 110;
        aversion[24] = new Version(25, ai23, new ECBlocks(26, new ECB(8, 106), new ECB(4, 107)), new ECBlocks(28, new ECB(8, 47), new ECB(13, 48)), new ECBlocks(30, new ECB(7, 24), new ECB(22, 25)), new ECBlocks(30, new ECB(22, 15), new ECB(13, 16)));
        int ai24[] = new int[5];
        ai24[0] = 6;
        ai24[1] = 30;
        ai24[2] = 58;
        ai24[3] = 86;
        ai24[4] = 114;
        aversion[25] = new Version(26, ai24, new ECBlocks(28, new ECB(10, 114), new ECB(2, 115)), new ECBlocks(28, new ECB(19, 46), new ECB(4, 47)), new ECBlocks(28, new ECB(28, 22), new ECB(6, 23)), new ECBlocks(30, new ECB(33, 16), new ECB(4, 17)));
        int ai25[] = new int[5];
        ai25[0] = 6;
        ai25[1] = 34;
        ai25[2] = 62;
        ai25[3] = 90;
        ai25[4] = 118;
        aversion[26] = new Version(27, ai25, new ECBlocks(30, new ECB(8, 122), new ECB(4, 123)), new ECBlocks(28, new ECB(22, 45), new ECB(3, 46)), new ECBlocks(30, new ECB(8, 23), new ECB(26, 24)), new ECBlocks(30, new ECB(12, 15), new ECB(28, 16)));
        int ai26[] = new int[6];
        ai26[0] = 6;
        ai26[1] = 26;
        ai26[2] = 50;
        ai26[3] = 74;
        ai26[4] = 98;
        ai26[5] = 122;
        aversion[27] = new Version(28, ai26, new ECBlocks(30, new ECB(3, 117), new ECB(10, 118)), new ECBlocks(28, new ECB(3, 45), new ECB(23, 46)), new ECBlocks(30, new ECB(4, 24), new ECB(31, 25)), new ECBlocks(30, new ECB(11, 15), new ECB(31, 16)));
        int ai27[] = new int[6];
        ai27[0] = 6;
        ai27[1] = 30;
        ai27[2] = 54;
        ai27[3] = 78;
        ai27[4] = 102;
        ai27[5] = 126;
        aversion[28] = new Version(29, ai27, new ECBlocks(30, new ECB(7, 116), new ECB(7, 117)), new ECBlocks(28, new ECB(21, 45), new ECB(7, 46)), new ECBlocks(30, new ECB(1, 23), new ECB(37, 24)), new ECBlocks(30, new ECB(19, 15), new ECB(26, 16)));
        int ai28[] = new int[6];
        ai28[0] = 6;
        ai28[1] = 26;
        ai28[2] = 52;
        ai28[3] = 78;
        ai28[4] = 104;
        ai28[5] = 130;
        aversion[29] = new Version(30, ai28, new ECBlocks(30, new ECB(5, 115), new ECB(10, 116)), new ECBlocks(28, new ECB(19, 47), new ECB(10, 48)), new ECBlocks(30, new ECB(15, 24), new ECB(25, 25)), new ECBlocks(30, new ECB(23, 15), new ECB(25, 16)));
        int ai29[] = new int[6];
        ai29[0] = 6;
        ai29[1] = 30;
        ai29[2] = 56;
        ai29[3] = 82;
        ai29[4] = 108;
        ai29[5] = 134;
        aversion[30] = new Version(31, ai29, new ECBlocks(30, new ECB(13, 115), new ECB(3, 116)), new ECBlocks(28, new ECB(2, 46), new ECB(29, 47)), new ECBlocks(30, new ECB(42, 24), new ECB(1, 25)), new ECBlocks(30, new ECB(23, 15), new ECB(28, 16)));
        int ai30[] = new int[6];
        ai30[0] = 6;
        ai30[1] = 34;
        ai30[2] = 60;
        ai30[3] = 86;
        ai30[4] = 112;
        ai30[5] = 138;
        aversion[31] = new Version(32, ai30, new ECBlocks(30, new ECB(17, 115)), new ECBlocks(28, new ECB(10, 46), new ECB(23, 47)), new ECBlocks(30, new ECB(10, 24), new ECB(35, 25)), new ECBlocks(30, new ECB(19, 15), new ECB(35, 16)));
        int ai31[] = new int[6];
        ai31[0] = 6;
        ai31[1] = 30;
        ai31[2] = 58;
        ai31[3] = 86;
        ai31[4] = 114;
        ai31[5] = 142;
        aversion[32] = new Version(33, ai31, new ECBlocks(30, new ECB(17, 115), new ECB(1, 116)), new ECBlocks(28, new ECB(14, 46), new ECB(21, 47)), new ECBlocks(30, new ECB(29, 24), new ECB(19, 25)), new ECBlocks(30, new ECB(11, 15), new ECB(46, 16)));
        int ai32[] = new int[6];
        ai32[0] = 6;
        ai32[1] = 34;
        ai32[2] = 62;
        ai32[3] = 90;
        ai32[4] = 118;
        ai32[5] = 146;
        aversion[33] = new Version(34, ai32, new ECBlocks(30, new ECB(13, 115), new ECB(6, 116)), new ECBlocks(28, new ECB(14, 46), new ECB(23, 47)), new ECBlocks(30, new ECB(44, 24), new ECB(7, 25)), new ECBlocks(30, new ECB(59, 16), new ECB(1, 17)));
        int ai33[] = new int[7];
        ai33[0] = 6;
        ai33[1] = 30;
        ai33[2] = 54;
        ai33[3] = 78;
        ai33[4] = 102;
        ai33[5] = 126;
        ai33[6] = 150;
        aversion[34] = new Version(35, ai33, new ECBlocks(30, new ECB(12, 121), new ECB(7, 122)), new ECBlocks(28, new ECB(12, 47), new ECB(26, 48)), new ECBlocks(30, new ECB(39, 24), new ECB(14, 25)), new ECBlocks(30, new ECB(22, 15), new ECB(41, 16)));
        int ai34[] = new int[7];
        ai34[0] = 6;
        ai34[1] = 24;
        ai34[2] = 50;
        ai34[3] = 76;
        ai34[4] = 102;
        ai34[5] = 128;
        ai34[6] = 154;
        aversion[35] = new Version(36, ai34, new ECBlocks(30, new ECB(6, 121), new ECB(14, 122)), new ECBlocks(28, new ECB(6, 47), new ECB(34, 48)), new ECBlocks(30, new ECB(46, 24), new ECB(10, 25)), new ECBlocks(30, new ECB(2, 15), new ECB(64, 16)));
        int ai35[] = new int[7];
        ai35[0] = 6;
        ai35[1] = 28;
        ai35[2] = 54;
        ai35[3] = 80;
        ai35[4] = 106;
        ai35[5] = 132;
        ai35[6] = 158;
        aversion[36] = new Version(37, ai35, new ECBlocks(30, new ECB(17, 122), new ECB(4, 123)), new ECBlocks(28, new ECB(29, 46), new ECB(14, 47)), new ECBlocks(30, new ECB(49, 24), new ECB(10, 25)), new ECBlocks(30, new ECB(24, 15), new ECB(46, 16)));
        int ai36[] = new int[7];
        ai36[0] = 6;
        ai36[1] = 32;
        ai36[2] = 58;
        ai36[3] = 84;
        ai36[4] = 110;
        ai36[5] = 136;
        ai36[6] = 162;
        aversion[37] = new Version(38, ai36, new ECBlocks(30, new ECB(4, 122), new ECB(18, 123)), new ECBlocks(28, new ECB(13, 46), new ECB(32, 47)), new ECBlocks(30, new ECB(48, 24), new ECB(14, 25)), new ECBlocks(30, new ECB(42, 15), new ECB(32, 16)));
        int ai37[] = new int[7];
        ai37[0] = 6;
        ai37[1] = 26;
        ai37[2] = 54;
        ai37[3] = 82;
        ai37[4] = 110;
        ai37[5] = 138;
        ai37[6] = 166;
        aversion[38] = new Version(39, ai37, new ECBlocks(30, new ECB(20, 117), new ECB(4, 118)), new ECBlocks(28, new ECB(40, 47), new ECB(7, 48)), new ECBlocks(30, new ECB(43, 24), new ECB(22, 25)), new ECBlocks(30, new ECB(10, 15), new ECB(67, 16)));
        int ai38[] = new int[7];
        ai38[0] = 6;
        ai38[1] = 30;
        ai38[2] = 58;
        ai38[3] = 86;
        ai38[4] = 114;
        ai38[5] = 142;
        ai38[6] = 170;
        aversion[39] = new Version(40, ai38, new ECBlocks(30, new ECB(19, 118), new ECB(6, 119)), new ECBlocks(28, new ECB(18, 47), new ECB(31, 48)), new ECBlocks(30, new ECB(34, 24), new ECB(34, 25)), new ECBlocks(30, new ECB(20, 15), new ECB(61, 16)));
        return aversion;
    }

    static Version decodeVersionInformation(int i)
    {
        int j;
        int k;
        int l;
        j = 0;
        k = 0x7fffffff;
        l = 0;
_L5:
        if(l >= VERSION_DECODE_INFO.length) goto _L2; else goto _L1
_L1:
        int i1 = VERSION_DECODE_INFO[l];
        if(i1 != i) goto _L4; else goto _L3
_L3:
        Version version = getVersionForNumber(l + 7);
_L6:
        return version;
_L4:
        int j1 = FormatInformation.numBitsDiffering(i, i1);
        if(j1 < k)
        {
            j = l + 7;
            k = j1;
        }
        l++;
          goto _L5
_L2:
        if(k <= 3)
            version = getVersionForNumber(j);
        else
            version = null;
          goto _L6
    }

    public static Version getProvisionalVersionForDimension(int i)
        throws FormatException
    {
        if(i % 4 != 1)
            throw FormatException.getFormatInstance();
        int j = i - 17 >> 2;
        Version version;
        try
        {
            version = getVersionForNumber(j);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw FormatException.getFormatInstance();
        }
        return version;
    }

    public static Version getVersionForNumber(int i)
    {
        if(i < 1 || i > 40)
            throw new IllegalArgumentException();
        else
            return VERSIONS[i - 1];
    }

    BitMatrix buildFunctionPattern()
    {
        int i = getDimensionForVersion();
        BitMatrix bitmatrix = new BitMatrix(i);
        bitmatrix.setRegion(0, 0, 9, 9);
        bitmatrix.setRegion(i - 8, 0, 8, 9);
        bitmatrix.setRegion(0, i - 8, 9, 8);
        int j = alignmentPatternCenters.length;
        for(int k = 0; k < j; k++)
        {
            int l = alignmentPatternCenters[k] - 2;
            int i1 = 0;
            while(i1 < j) 
            {
                if((k != 0 || i1 != 0 && i1 != j - 1) && (k != j - 1 || i1 != 0))
                    bitmatrix.setRegion(alignmentPatternCenters[i1] - 2, l, 5, 5);
                i1++;
            }
        }

        bitmatrix.setRegion(6, 9, 1, i - 17);
        bitmatrix.setRegion(9, 6, i - 17, 1);
        if(versionNumber > 6)
        {
            bitmatrix.setRegion(i - 11, 0, 3, 6);
            bitmatrix.setRegion(0, i - 11, 6, 3);
        }
        return bitmatrix;
    }

    public int[] getAlignmentPatternCenters()
    {
        return alignmentPatternCenters;
    }

    public int getDimensionForVersion()
    {
        return 17 + 4 * versionNumber;
    }

    public ECBlocks getECBlocksForLevel(ErrorCorrectionLevel errorcorrectionlevel)
    {
        return ecBlocks[errorcorrectionlevel.ordinal()];
    }

    public int getTotalCodewords()
    {
        return totalCodewords;
    }

    public int getVersionNumber()
    {
        return versionNumber;
    }

    public String toString()
    {
        return String.valueOf(versionNumber);
    }

    private static final Version VERSIONS[] = buildVersions();
    private static final int VERSION_DECODE_INFO[];
    private final int alignmentPatternCenters[];
    private final ECBlocks ecBlocks[];
    private final int totalCodewords;
    private final int versionNumber;

    static 
    {
        int ai[] = new int[34];
        ai[0] = 31892;
        ai[1] = 34236;
        ai[2] = 39577;
        ai[3] = 42195;
        ai[4] = 48118;
        ai[5] = 51042;
        ai[6] = 55367;
        ai[7] = 58893;
        ai[8] = 63784;
        ai[9] = 0x10b78;
        ai[10] = 0x1145d;
        ai[11] = 0x12a17;
        ai[12] = 0x13532;
        ai[13] = 0x149a6;
        ai[14] = 0x15683;
        ai[15] = 0x168c9;
        ai[16] = 0x177ec;
        ai[17] = 0x18ec4;
        ai[18] = 0x191e1;
        ai[19] = 0x1afab;
        ai[20] = 0x1b08e;
        ai[21] = 0x1cc1a;
        ai[22] = 0x1d33f;
        ai[23] = 0x1ed75;
        ai[24] = 0x1f250;
        ai[25] = 0x209d5;
        ai[26] = 0x216f0;
        ai[27] = 0x228ba;
        ai[28] = 0x2379f;
        ai[29] = 0x24b0b;
        ai[30] = 0x2542e;
        ai[31] = 0x26a64;
        ai[32] = 0x27541;
        ai[33] = 0x28c69;
        VERSION_DECODE_INFO = ai;
    }
}
