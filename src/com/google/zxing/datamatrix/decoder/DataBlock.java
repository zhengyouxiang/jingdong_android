// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.decoder;


// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

final class DataBlock
{

    private DataBlock(int i, byte abyte0[])
    {
        numDataCodewords = i;
        codewords = abyte0;
    }

    static DataBlock[] getDataBlocks(byte abyte0[], Version version)
    {
        Version.ECBlocks ecblocks = version.getECBlocks();
        Version.ECB aecb[] = ecblocks.getECBlocks();
        int i = 0;
        int j = 0;
        for(; i < aecb.length; i++)
            j += aecb[i].getCount();

        DataBlock adatablock[] = new DataBlock[j];
        int k = 0;
        int l;
        int k5;
        for(l = 0; k < aecb.length; l = k5)
        {
            Version.ECB ecb = aecb[k];
            k5 = l;
            for(int l5 = 0; l5 < ecb.getCount();)
            {
                int i6 = ecb.getDataCodewords();
                int j6 = i6 + ecblocks.getECCodewords();
                int k6 = k5 + 1;
                adatablock[k5] = new DataBlock(i6, new byte[j6]);
                l5++;
                k5 = k6;
            }

            k++;
        }

        int i1 = adatablock[0].codewords.length - ecblocks.getECCodewords();
        int j1 = i1 - 1;
        int k1 = 0;
        int l1;
        int l4;
        for(l1 = 0; k1 < j1; l1 = l4)
        {
            l4 = l1;
            for(int i5 = 0; i5 < l;)
            {
                byte abyte3[] = adatablock[i5].codewords;
                int j5 = l4 + 1;
                abyte3[k1] = abyte0[l4];
                i5++;
                l4 = j5;
            }

            k1++;
        }

        boolean flag;
        int i2;
        int j2;
        if(version.getVersionNumber() == 24)
            flag = true;
        else
            flag = false;
        if(flag)
            i2 = 8;
        else
            i2 = l;
        j2 = l1;
        for(int k2 = 0; k2 < i2;)
        {
            byte abyte2[] = adatablock[k2].codewords;
            int j4 = i1 - 1;
            int k4 = j2 + 1;
            abyte2[j4] = abyte0[j2];
            k2++;
            j2 = k4;
        }

        int l2 = adatablock[0].codewords.length;
        int i3;
        int j3;
        for(i3 = j2; i1 < l2; i3 = j3)
        {
            j3 = i3;
            int k3 = 0;
            while(k3 < l) 
            {
                int l3;
                byte abyte1[];
                int i4;
                if(flag && k3 > 7)
                    l3 = i1 - 1;
                else
                    l3 = i1;
                abyte1 = adatablock[k3].codewords;
                i4 = j3 + 1;
                abyte1[l3] = abyte0[j3];
                k3++;
                j3 = i4;
            }
            i1++;
        }

        if(i3 != abyte0.length)
            throw new IllegalArgumentException();
        else
            return adatablock;
    }

    byte[] getCodewords()
    {
        return codewords;
    }

    int getNumDataCodewords()
    {
        return numDataCodewords;
    }

    private final byte codewords[];
    private final int numDataCodewords;
}
