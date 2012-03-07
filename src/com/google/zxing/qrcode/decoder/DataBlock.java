// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;


// Referenced classes of package com.google.zxing.qrcode.decoder:
//            Version, ErrorCorrectionLevel

final class DataBlock
{

    private DataBlock(int i, byte abyte0[])
    {
        numDataCodewords = i;
        codewords = abyte0;
    }

    static DataBlock[] getDataBlocks(byte abyte0[], Version version, ErrorCorrectionLevel errorcorrectionlevel)
    {
        Version.ECBlocks ecblocks;
        DataBlock adatablock[];
        int l;
        int i1;
        int j1;
        if(abyte0.length != version.getTotalCodewords())
            throw new IllegalArgumentException();
        ecblocks = version.getECBlocksForLevel(errorcorrectionlevel);
        Version.ECB aecb[] = ecblocks.getECBlocks();
        int i = 0;
        int j = 0;
        for(; i < aecb.length; i++)
            j += aecb[i].getCount();

        adatablock = new DataBlock[j];
        int k = 0;
        int i5;
        for(l = 0; k < aecb.length; l = i5)
        {
            Version.ECB ecb = aecb[k];
            i5 = l;
            for(int j5 = 0; j5 < ecb.getCount();)
            {
                int k5 = ecb.getDataCodewords();
                int l5 = k5 + ecblocks.getECCodewordsPerBlock();
                int i6 = i5 + 1;
                adatablock[i5] = new DataBlock(k5, new byte[l5]);
                j5++;
                i5 = i6;
            }

            k++;
        }

        i1 = adatablock[0].codewords.length;
        j1 = adatablock.length - 1;
_L6:
        if(j1 >= 0 && adatablock[j1].codewords.length != i1) goto _L2; else goto _L1
_L1:
        int k1;
        int l1;
        int i2;
        int j2;
        k1 = j1 + 1;
        l1 = i1 - ecblocks.getECCodewordsPerBlock();
        i2 = 0;
        j2 = 0;
_L4:
        if(i2 >= l1)
            break; /* Loop/switch isn't completed */
        int j4 = j2;
        for(int k4 = 0; k4 < l;)
        {
            byte abyte3[] = adatablock[k4].codewords;
            int l4 = j4 + 1;
            abyte3[i2] = abyte0[j4];
            k4++;
            j4 = l4;
        }

        i2++;
        j2 = j4;
        continue; /* Loop/switch isn't completed */
_L2:
        j1--;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        for(int k2 = k1; k2 < l;)
        {
            byte abyte2[] = adatablock[k2].codewords;
            int i4 = j2 + 1;
            abyte2[l1] = abyte0[j2];
            k2++;
            j2 = i4;
        }

        for(int l2 = adatablock[0].codewords.length; l1 < l2;)
        {
            int i3 = j2;
            int j3 = 0;
            while(j3 < l) 
            {
                int k3;
                byte abyte1[];
                int l3;
                if(j3 < k1)
                    k3 = l1;
                else
                    k3 = l1 + 1;
                abyte1 = adatablock[j3].codewords;
                l3 = i3 + 1;
                abyte1[k3] = abyte0[i3];
                j3++;
                i3 = l3;
            }
            l1++;
            j2 = i3;
        }

        return adatablock;
        if(true) goto _L6; else goto _L5
_L5:
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
