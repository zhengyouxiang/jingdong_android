// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.encoder;


// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix, QRCode

public final class MaskUtil
{

    private MaskUtil()
    {
    }

    public static int applyMaskPenaltyRule1(ByteMatrix bytematrix)
    {
        return applyMaskPenaltyRule1Internal(bytematrix, true) + applyMaskPenaltyRule1Internal(bytematrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix bytematrix, boolean flag)
    {
        int i;
        int j;
        byte abyte0[][];
        byte byte0;
        int k;
        int l;
        if(flag)
            i = bytematrix.getHeight();
        else
            i = bytematrix.getWidth();
        if(flag)
            j = bytematrix.getWidth();
        else
            j = bytematrix.getHeight();
        abyte0 = bytematrix.getArray();
        byte0 = -1;
        k = 0;
        l = 0;
        do
        {
            if(l >= i)
                break;
            int i1 = k;
            int j1 = 0;
            byte byte1 = byte0;
            int k1 = 0;
            while(k1 < j) 
            {
                byte byte2;
                if(flag)
                    byte2 = abyte0[l][k1];
                else
                    byte2 = abyte0[k1][l];
                if(byte2 == byte1)
                {
                    if(++j1 == 5)
                        i1 += 3;
                    else
                    if(j1 > 5)
                        i1++;
                } else
                {
                    j1 = 1;
                    byte1 = byte2;
                }
                k1++;
            }
            l++;
            byte0 = byte1;
            k = i1;
        } while(true);
        return k;
    }

    public static int applyMaskPenaltyRule2(ByteMatrix bytematrix)
    {
        byte abyte0[][] = bytematrix.getArray();
        int i = bytematrix.getWidth();
        int j = bytematrix.getHeight();
        int k = 0;
        int l;
        int i1;
        for(l = 0; k < j - 1; l = i1)
        {
            i1 = l;
            for(int j1 = 0; j1 < i - 1; j1++)
            {
                byte byte0 = abyte0[k][j1];
                if(byte0 == abyte0[k][j1 + 1] && byte0 == abyte0[k + 1][j1] && byte0 == abyte0[k + 1][j1 + 1])
                    i1 += 3;
            }

            k++;
        }

        return l;
    }

    public static int applyMaskPenaltyRule3(ByteMatrix bytematrix)
    {
        byte abyte0[][] = bytematrix.getArray();
        int i = bytematrix.getWidth();
        int j = bytematrix.getHeight();
        int k = 0;
        int l;
        int i1;
        for(l = 0; k < j; l = i1)
        {
            i1 = l;
            for(int j1 = 0; j1 < i; j1++)
            {
                if(j1 + 6 < i && abyte0[k][j1] == 1 && abyte0[k][j1 + 1] == 0 && abyte0[k][j1 + 2] == 1 && abyte0[k][j1 + 3] == 1 && abyte0[k][j1 + 4] == 1 && abyte0[k][j1 + 5] == 0 && abyte0[k][j1 + 6] == 1 && (j1 + 10 < i && abyte0[k][j1 + 7] == 0 && abyte0[k][j1 + 8] == 0 && abyte0[k][j1 + 9] == 0 && abyte0[k][j1 + 10] == 0 || j1 - 4 >= 0 && abyte0[k][j1 - 1] == 0 && abyte0[k][j1 - 2] == 0 && abyte0[k][j1 - 3] == 0 && abyte0[k][j1 - 4] == 0))
                    i1 += 40;
                if(k + 6 < j && abyte0[k][j1] == 1 && abyte0[k + 1][j1] == 0 && abyte0[k + 2][j1] == 1 && abyte0[k + 3][j1] == 1 && abyte0[k + 4][j1] == 1 && abyte0[k + 5][j1] == 0 && abyte0[k + 6][j1] == 1 && (k + 10 < j && abyte0[k + 7][j1] == 0 && abyte0[k + 8][j1] == 0 && abyte0[k + 9][j1] == 0 && abyte0[k + 10][j1] == 0 || k - 4 >= 0 && abyte0[k - 1][j1] == 0 && abyte0[k - 2][j1] == 0 && abyte0[k - 3][j1] == 0 && abyte0[k - 4][j1] == 0))
                    i1 += 40;
            }

            k++;
        }

        return l;
    }

    public static int applyMaskPenaltyRule4(ByteMatrix bytematrix)
    {
        byte abyte0[][] = bytematrix.getArray();
        int i = bytematrix.getWidth();
        int j = bytematrix.getHeight();
        int k = 0;
        int l;
        int j1;
        for(l = 0; k < j; l = j1)
        {
            j1 = l;
            for(int k1 = 0; k1 < i; k1++)
                if(abyte0[k][k1] == 1)
                    j1++;

            k++;
        }

        int i1 = bytematrix.getHeight() * bytematrix.getWidth();
        return 10 * (Math.abs((int)(100D * ((double)l / (double)i1) - 50D)) / 5);
    }

    public static boolean getDataMaskBit(int i, int j, int k)
    {
        if(!QRCode.isValidMaskPattern(i))
            throw new IllegalArgumentException("Invalid mask pattern");
        i;
        JVM INSTR tableswitch 0 7: default 64
    //                   0 91
    //                   1 107
    //                   2 114
    //                   3 121
    //                   4 130
    //                   5 143
    //                   6 161
    //                   7 181;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_181;
_L1:
        throw new IllegalArgumentException("Invalid mask pattern: " + i);
_L2:
        int l = 1 & k + j;
_L10:
        boolean flag;
        int i1;
        int j1;
        if(l == 0)
            flag = true;
        else
            flag = false;
        return flag;
_L3:
        l = k & 1;
          goto _L10
_L4:
        l = j % 3;
          goto _L10
_L5:
        l = (k + j) % 3;
          goto _L10
_L6:
        l = 1 & (k >>> 1) + j / 3;
          goto _L10
_L7:
        j1 = k * j;
        l = (j1 & 1) + j1 % 3;
          goto _L10
_L8:
        i1 = k * j;
        l = 1 & (i1 & 1) + i1 % 3;
          goto _L10
        l = 1 & (k * j) % 3 + (1 & k + j);
          goto _L10
    }
}
