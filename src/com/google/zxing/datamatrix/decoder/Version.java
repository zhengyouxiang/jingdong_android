// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;

public final class Version
{
    static final class ECB
    {

        int getCount()
        {
            return count;
        }

        int getDataCodewords()
        {
            return dataCodewords;
        }

        private final int count;
        private final int dataCodewords;

        private ECB(int i, int j)
        {
            count = i;
            dataCodewords = j;
        }

    }

    static final class ECBlocks
    {

        ECB[] getECBlocks()
        {
            return ecBlocks;
        }

        int getECCodewords()
        {
            return ecCodewords;
        }

        private final ECB ecBlocks[];
        private final int ecCodewords;

        private ECBlocks(int i, ECB ecb)
        {
            ecCodewords = i;
            ECB aecb[] = new ECB[1];
            aecb[0] = ecb;
            ecBlocks = aecb;
        }


        private ECBlocks(int i, ECB ecb, ECB ecb1)
        {
            ecCodewords = i;
            ECB aecb[] = new ECB[2];
            aecb[0] = ecb;
            aecb[1] = ecb1;
            ecBlocks = aecb;
        }

    }


    private Version(int i, int j, int k, int l, int i1, ECBlocks ecblocks)
    {
        int j1 = 0;
        super();
        versionNumber = i;
        symbolSizeRows = j;
        symbolSizeColumns = k;
        dataRegionSizeRows = l;
        dataRegionSizeColumns = i1;
        ecBlocks = ecblocks;
        int k1 = ecblocks.getECCodewords();
        ECB aecb[] = ecblocks.getECBlocks();
        int l1 = 0;
        for(; j1 < aecb.length; j1++)
        {
            ECB ecb = aecb[j1];
            l1 += ecb.getCount() * (k1 + ecb.getDataCodewords());
        }

        totalCodewords = l1;
    }

    private static Version[] buildVersions()
    {
        Version aversion[] = new Version[30];
        aversion[0] = new Version(1, 10, 10, 8, 8, new ECBlocks(5, new ECB(1, 3)));
        aversion[1] = new Version(2, 12, 12, 10, 10, new ECBlocks(7, new ECB(1, 5)));
        aversion[2] = new Version(3, 14, 14, 12, 12, new ECBlocks(10, new ECB(1, 8)));
        aversion[3] = new Version(4, 16, 16, 14, 14, new ECBlocks(12, new ECB(1, 12)));
        aversion[4] = new Version(5, 18, 18, 16, 16, new ECBlocks(14, new ECB(1, 18)));
        aversion[5] = new Version(6, 20, 20, 18, 18, new ECBlocks(18, new ECB(1, 22)));
        aversion[6] = new Version(7, 22, 22, 20, 20, new ECBlocks(20, new ECB(1, 30)));
        aversion[7] = new Version(8, 24, 24, 22, 22, new ECBlocks(24, new ECB(1, 36)));
        aversion[8] = new Version(9, 26, 26, 24, 24, new ECBlocks(28, new ECB(1, 44)));
        aversion[9] = new Version(10, 32, 32, 14, 14, new ECBlocks(36, new ECB(1, 62)));
        aversion[10] = new Version(11, 36, 36, 16, 16, new ECBlocks(42, new ECB(1, 86)));
        aversion[11] = new Version(12, 40, 40, 18, 18, new ECBlocks(48, new ECB(1, 114)));
        aversion[12] = new Version(13, 44, 44, 20, 20, new ECBlocks(56, new ECB(1, 144)));
        aversion[13] = new Version(14, 48, 48, 22, 22, new ECBlocks(68, new ECB(1, 174)));
        aversion[14] = new Version(15, 52, 52, 24, 24, new ECBlocks(42, new ECB(2, 102)));
        aversion[15] = new Version(16, 64, 64, 14, 14, new ECBlocks(56, new ECB(2, 140)));
        aversion[16] = new Version(17, 72, 72, 16, 16, new ECBlocks(36, new ECB(4, 92)));
        aversion[17] = new Version(18, 80, 80, 18, 18, new ECBlocks(48, new ECB(4, 114)));
        aversion[18] = new Version(19, 88, 88, 20, 20, new ECBlocks(56, new ECB(4, 144)));
        aversion[19] = new Version(20, 96, 96, 22, 22, new ECBlocks(68, new ECB(4, 174)));
        aversion[20] = new Version(21, 104, 104, 24, 24, new ECBlocks(56, new ECB(6, 136)));
        aversion[21] = new Version(22, 120, 120, 18, 18, new ECBlocks(68, new ECB(6, 175)));
        aversion[22] = new Version(23, 132, 132, 20, 20, new ECBlocks(62, new ECB(8, 163)));
        aversion[23] = new Version(24, 144, 144, 22, 22, new ECBlocks(62, new ECB(8, 156), new ECB(2, 155)));
        aversion[24] = new Version(25, 8, 18, 6, 16, new ECBlocks(7, new ECB(1, 5)));
        aversion[25] = new Version(26, 8, 32, 6, 14, new ECBlocks(11, new ECB(1, 10)));
        aversion[26] = new Version(27, 12, 26, 10, 24, new ECBlocks(14, new ECB(1, 16)));
        aversion[27] = new Version(28, 12, 36, 10, 16, new ECBlocks(18, new ECB(1, 22)));
        aversion[28] = new Version(29, 16, 36, 10, 16, new ECBlocks(24, new ECB(1, 32)));
        aversion[29] = new Version(30, 16, 48, 14, 22, new ECBlocks(28, new ECB(1, 49)));
        return aversion;
    }

    public static Version getVersionForDimensions(int i, int j)
        throws FormatException
    {
        if((i & 1) != 0 || (j & 1) != 0)
            throw FormatException.getFormatInstance();
        int k = VERSIONS.length;
        for(int l = 0; l < k; l++)
        {
            Version version = VERSIONS[l];
            if(version.symbolSizeRows == i && version.symbolSizeColumns == j)
                return version;
        }

        throw FormatException.getFormatInstance();
    }

    public int getDataRegionSizeColumns()
    {
        return dataRegionSizeColumns;
    }

    public int getDataRegionSizeRows()
    {
        return dataRegionSizeRows;
    }

    ECBlocks getECBlocks()
    {
        return ecBlocks;
    }

    public int getSymbolSizeColumns()
    {
        return symbolSizeColumns;
    }

    public int getSymbolSizeRows()
    {
        return symbolSizeRows;
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
    private final int dataRegionSizeColumns;
    private final int dataRegionSizeRows;
    private final ECBlocks ecBlocks;
    private final int symbolSizeColumns;
    private final int symbolSizeRows;
    private final int totalCodewords;
    private final int versionNumber;

}
