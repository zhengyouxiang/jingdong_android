// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            FormatInformation, DataMask, Version

final class BitMatrixParser
{

    BitMatrixParser(BitMatrix bitmatrix)
        throws FormatException
    {
        int i = bitmatrix.getHeight();
        if(i < 21 || (i & 3) != 1)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            bitMatrix = bitmatrix;
            return;
        }
    }

    private int copyBit(int i, int j, int k)
    {
        int l;
        if(bitMatrix.get(i, j))
            l = 1 | k << 1;
        else
            l = k << 1;
        return l;
    }

    byte[] readCodewords()
        throws FormatException
    {
        FormatInformation formatinformation = readFormatInformation();
        Version version = readVersion();
        DataMask datamask = DataMask.forReference(formatinformation.getDataMask());
        int i = bitMatrix.getHeight();
        datamask.unmaskBitMatrix(bitMatrix, i);
        BitMatrix bitmatrix = version.buildFunctionPattern();
        byte abyte0[] = new byte[version.getTotalCodewords()];
        int j = 0;
        int k = i - 1;
        boolean flag = true;
        int l = k;
        int i1 = 0;
        int j1;
        int k1;
        for(j1 = 0; l > 0; j1 = k1)
        {
            if(l == 6)
                l--;
            k1 = j1;
            int l1 = j;
            int i2 = i1;
            for(int j2 = 0; j2 < i;)
            {
                int k2;
                int l2;
                int i3;
                int j3;
                if(flag)
                    k2 = i - 1 - j2;
                else
                    k2 = j2;
                l2 = k1;
                i3 = l1;
                j3 = i2;
                for(int k3 = 0; k3 < 2; k3++)
                {
                    if(bitmatrix.get(l - k3, k2))
                        continue;
                    j3++;
                    i3 <<= 1;
                    if(bitMatrix.get(l - k3, k2))
                        i3 |= 1;
                    if(j3 == 8)
                    {
                        int l3 = l2 + 1;
                        abyte0[l2] = (byte)i3;
                        i3 = 0;
                        l2 = l3;
                        j3 = 0;
                    }
                }

                j2++;
                i2 = j3;
                l1 = i3;
                k1 = l2;
            }

            boolean flag1 = flag ^ true;
            l -= 2;
            flag = flag1;
            i1 = i2;
            j = l1;
        }

        if(j1 != version.getTotalCodewords())
            throw FormatException.getFormatInstance();
        else
            return abyte0;
    }

    FormatInformation readFormatInformation()
        throws FormatException
    {
        int i = 0;
        FormatInformation formatinformation;
        if(parsedFormatInfo != null)
        {
            formatinformation = parsedFormatInfo;
        } else
        {
            int j = 0;
            int k = 0;
            for(; j < 6; j++)
                k = copyBit(j, 8, k);

            int l = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, k)));
            for(int i1 = 5; i1 >= 0; i1--)
                l = copyBit(8, i1, l);

            int j1 = bitMatrix.getHeight();
            int k1 = j1 - 8;
            for(int l1 = j1 - 1; l1 >= k1; l1--)
                i = copyBit(l1, 8, i);

            int i2 = j1 - 7;
            int j2 = i;
            for(; i2 < j1; i2++)
                j2 = copyBit(8, i2, j2);

            parsedFormatInfo = FormatInformation.decodeFormatInformation(l, j2);
            if(parsedFormatInfo != null)
                formatinformation = parsedFormatInfo;
            else
                throw FormatException.getFormatInstance();
        }
        return formatinformation;
    }

    Version readVersion()
        throws FormatException
    {
        Version version;
        if(parsedVersion != null)
        {
            version = parsedVersion;
        } else
        {
            int i = bitMatrix.getHeight();
            int j = i - 17 >> 2;
            if(j <= 6)
            {
                version = Version.getVersionForNumber(j);
            } else
            {
                int k = i - 11;
                int l = 5;
                int i1;
                int l2;
                for(i1 = 0; l >= 0; i1 = l2)
                {
                    int k2 = i - 9;
                    l2 = i1;
                    for(int i3 = k2; i3 >= k; i3--)
                        l2 = copyBit(i3, l, l2);

                    l--;
                }

                parsedVersion = Version.decodeVersionInformation(i1);
                if(parsedVersion != null && parsedVersion.getDimensionForVersion() == i)
                {
                    version = parsedVersion;
                } else
                {
                    int j1 = 5;
                    int k1;
                    int i2;
                    for(k1 = 0; j1 >= 0; k1 = i2)
                    {
                        int l1 = i - 9;
                        i2 = k1;
                        for(int j2 = l1; j2 >= k; j2--)
                            i2 = copyBit(j1, j2, i2);

                        j1--;
                    }

                    parsedVersion = Version.decodeVersionInformation(k1);
                    if(parsedVersion != null && parsedVersion.getDimensionForVersion() == i)
                        version = parsedVersion;
                    else
                        throw FormatException.getFormatInstance();
                }
            }
        }
        return version;
    }

    private final BitMatrix bitMatrix;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;
}
