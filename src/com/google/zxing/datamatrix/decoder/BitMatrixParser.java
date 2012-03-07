// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.datamatrix.decoder:
//            Version

final class BitMatrixParser
{

    BitMatrixParser(BitMatrix bitmatrix)
        throws FormatException
    {
        int i = bitmatrix.getHeight();
        if(i < 10 || i > 144 || (i & 1) != 0)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            version = readVersion(bitmatrix);
            mappingBitMatrix = extractDataRegion(bitmatrix);
            readMappingMatrix = new BitMatrix(mappingBitMatrix.getHeight());
            return;
        }
    }

    BitMatrix extractDataRegion(BitMatrix bitmatrix)
    {
        int i = version.getSymbolSizeRows();
        int j = version.getSymbolSizeColumns();
        if(bitmatrix.getHeight() != i)
            throw new IllegalArgumentException("Dimension of bitMarix must match the version size");
        int k = version.getDataRegionSizeRows();
        int l = version.getDataRegionSizeColumns();
        int i1 = i / k;
        int j1 = j / l;
        BitMatrix bitmatrix1 = new BitMatrix(i1 * k);
        for(int k1 = 0; k1 < i1; k1++)
        {
            int l1 = k1 * k;
            for(int i2 = 0; i2 < j1; i2++)
            {
                int j2 = i2 * l;
                for(int k2 = 0; k2 < k; k2++)
                {
                    int l2 = k2 + (1 + k1 * (k + 2));
                    int i3 = l1 + k2;
                    for(int j3 = 0; j3 < l; j3++)
                        if(bitmatrix.get(j3 + (1 + i2 * (l + 2)), l2))
                            bitmatrix1.set(j2 + j3, i3);

                }

            }

        }

        return bitmatrix1;
    }

    byte[] readCodewords()
        throws FormatException
    {
        boolean flag = false;
        byte abyte0[] = new byte[version.getTotalCodewords()];
        int i = mappingBitMatrix.getHeight();
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        int j = 0;
        int k = 4;
        int l = 0;
        do
            if(k == i && j == 0 && !flag3)
            {
                int l2 = l + 1;
                abyte0[l] = (byte)readCorner1(i, i);
                k -= 2;
                j += 2;
                l = l2;
                flag3 = true;
            } else
            if(k == i - 2 && j == 0 && (i & 3) != 0 && !flag2)
            {
                int k2 = l + 1;
                abyte0[l] = (byte)readCorner2(i, i);
                k -= 2;
                j += 2;
                l = k2;
                flag2 = true;
            } else
            if(k == i + 4 && j == 2 && (i & 7) == 0 && !flag1)
            {
                int j2 = l + 1;
                abyte0[l] = (byte)readCorner3(i, i);
                k -= 2;
                j += 2;
                l = j2;
                flag1 = true;
            } else
            if(k == i - 2 && j == 0 && (i & 7) == 4 && !flag)
            {
                int i2 = l + 1;
                abyte0[l] = (byte)readCorner4(i, i);
                k -= 2;
                j += 2;
                l = i2;
                flag = true;
            } else
            {
                do
                {
                    if(k < i && j >= 0 && !readMappingMatrix.get(j, k))
                    {
                        int l1 = l + 1;
                        abyte0[l] = (byte)readUtah(k, j, i, i);
                        l = l1;
                    }
                    k -= 2;
                    j += 2;
                } while(k >= 0 && j < i);
                int i1 = k + 1;
                int j1 = j + 3;
                do
                {
                    if(i1 >= 0 && j1 < i && !readMappingMatrix.get(j1, i1))
                    {
                        int k1 = l + 1;
                        abyte0[l] = (byte)readUtah(i1, j1, i, i);
                        l = k1;
                    }
                    i1 += 2;
                    j1 -= 2;
                } while(i1 < i && j1 >= 0);
                k = i1 + 3;
                j = j1 + 1;
            }
        while(k < i || j < i);
        if(l != version.getTotalCodewords())
            throw FormatException.getFormatInstance();
        else
            return abyte0;
    }

    int readCorner1(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(readModule(i - 1, 0, i, j))
            k = false | true;
        else
            k = 0;
        l = k << 1;
        if(readModule(i - 1, 1, i, j))
            l |= 1;
        i1 = l << 1;
        if(readModule(i - 1, 2, i, j))
            i1 |= 1;
        j1 = i1 << 1;
        if(readModule(0, j - 2, i, j))
            j1 |= 1;
        k1 = j1 << 1;
        if(readModule(0, j - 1, i, j))
            k1 |= 1;
        l1 = k1 << 1;
        if(readModule(1, j - 1, i, j))
            l1 |= 1;
        i2 = l1 << 1;
        if(readModule(2, j - 1, i, j))
            i2 |= 1;
        j2 = i2 << 1;
        if(readModule(3, j - 1, i, j))
            j2 |= 1;
        return j2;
    }

    int readCorner2(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(readModule(i - 3, 0, i, j))
            k = false | true;
        else
            k = 0;
        l = k << 1;
        if(readModule(i - 2, 0, i, j))
            l |= 1;
        i1 = l << 1;
        if(readModule(i - 1, 0, i, j))
            i1 |= 1;
        j1 = i1 << 1;
        if(readModule(0, j - 4, i, j))
            j1 |= 1;
        k1 = j1 << 1;
        if(readModule(0, j - 3, i, j))
            k1 |= 1;
        l1 = k1 << 1;
        if(readModule(0, j - 2, i, j))
            l1 |= 1;
        i2 = l1 << 1;
        if(readModule(0, j - 1, i, j))
            i2 |= 1;
        j2 = i2 << 1;
        if(readModule(1, j - 1, i, j))
            j2 |= 1;
        return j2;
    }

    int readCorner3(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(readModule(i - 1, 0, i, j))
            k = false | true;
        else
            k = 0;
        l = k << 1;
        if(readModule(i - 1, j - 1, i, j))
            l |= 1;
        i1 = l << 1;
        if(readModule(0, j - 3, i, j))
            i1 |= 1;
        j1 = i1 << 1;
        if(readModule(0, j - 2, i, j))
            j1 |= 1;
        k1 = j1 << 1;
        if(readModule(0, j - 1, i, j))
            k1 |= 1;
        l1 = k1 << 1;
        if(readModule(1, j - 3, i, j))
            l1 |= 1;
        i2 = l1 << 1;
        if(readModule(1, j - 2, i, j))
            i2 |= 1;
        j2 = i2 << 1;
        if(readModule(1, j - 1, i, j))
            j2 |= 1;
        return j2;
    }

    int readCorner4(int i, int j)
    {
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(readModule(i - 3, 0, i, j))
            k = false | true;
        else
            k = 0;
        l = k << 1;
        if(readModule(i - 2, 0, i, j))
            l |= 1;
        i1 = l << 1;
        if(readModule(i - 1, 0, i, j))
            i1 |= 1;
        j1 = i1 << 1;
        if(readModule(0, j - 2, i, j))
            j1 |= 1;
        k1 = j1 << 1;
        if(readModule(0, j - 1, i, j))
            k1 |= 1;
        l1 = k1 << 1;
        if(readModule(1, j - 1, i, j))
            l1 |= 1;
        i2 = l1 << 1;
        if(readModule(2, j - 1, i, j))
            i2 |= 1;
        j2 = i2 << 1;
        if(readModule(3, j - 1, i, j))
            j2 |= 1;
        return j2;
    }

    boolean readModule(int i, int j, int k, int l)
    {
        int i1;
        int j1;
        if(i < 0)
        {
            int k1 = i + k;
            int l1 = j + (4 - (7 & k + 4));
            j1 = k1;
            i1 = l1;
        } else
        {
            i1 = j;
            j1 = i;
        }
        if(i1 < 0)
        {
            i1 += l;
            j1 += 4 - (7 & l + 4);
        }
        readMappingMatrix.set(i1, j1);
        return mappingBitMatrix.get(i1, j1);
    }

    int readUtah(int i, int j, int k, int l)
    {
        int i1 = 0;
        if(readModule(i - 2, j - 2, k, l))
            i1 = false | true;
        int j1 = i1 << 1;
        if(readModule(i - 2, j - 1, k, l))
            j1 |= 1;
        int k1 = j1 << 1;
        if(readModule(i - 1, j - 2, k, l))
            k1 |= 1;
        int l1 = k1 << 1;
        if(readModule(i - 1, j - 1, k, l))
            l1 |= 1;
        int i2 = l1 << 1;
        if(readModule(i - 1, j, k, l))
            i2 |= 1;
        int j2 = i2 << 1;
        if(readModule(i, j - 2, k, l))
            j2 |= 1;
        int k2 = j2 << 1;
        if(readModule(i, j - 1, k, l))
            k2 |= 1;
        int l2 = k2 << 1;
        if(readModule(i, j, k, l))
            l2 |= 1;
        return l2;
    }

    Version readVersion(BitMatrix bitmatrix)
        throws FormatException
    {
        Version version1;
        if(version != null)
        {
            version1 = version;
        } else
        {
            int i = bitmatrix.getHeight();
            version1 = Version.getVersionForDimensions(i, i);
        }
        return version1;
    }

    private final BitMatrix mappingBitMatrix;
    private final BitMatrix readMappingMatrix;
    private final Version version;
}
