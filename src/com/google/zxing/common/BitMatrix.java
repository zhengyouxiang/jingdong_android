// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


// Referenced classes of package com.google.zxing.common:
//            BitArray

public final class BitMatrix
{

    public BitMatrix(int i)
    {
        this(i, i);
    }

    public BitMatrix(int i, int j)
    {
        if(i < 1 || j < 1)
        {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        } else
        {
            width = i;
            height = j;
            rowSize = i + 31 >> 5;
            bits = new int[j * rowSize];
            return;
        }
    }

    public void clear()
    {
        int i = bits.length;
        for(int j = 0; j < i; j++)
            bits[j] = 0;

    }

    public boolean equals(Object obj)
    {
        if(obj instanceof BitMatrix) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        BitMatrix bitmatrix = (BitMatrix)obj;
        if(width != bitmatrix.width || height != bitmatrix.height || rowSize != bitmatrix.rowSize || bits.length != bitmatrix.bits.length)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        int i = 0;
        do
        {
            if(i >= bits.length)
                break;
            if(bits[i] != bitmatrix.bits[i])
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while(true);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void flip(int i, int j)
    {
        int k = j * rowSize + (i >> 5);
        int ai[] = bits;
        ai[k] = ai[k] ^ 1 << (i & 0x1f);
    }

    public boolean get(int i, int j)
    {
        int k = j * rowSize + (i >> 5);
        boolean flag;
        if((1 & bits[k] >>> (i & 0x1f)) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getHeight()
    {
        return height;
    }

    public BitArray getRow(int i, BitArray bitarray)
    {
        BitArray bitarray1;
        int j;
        if(bitarray == null || bitarray.getSize() < width)
            bitarray1 = new BitArray(width);
        else
            bitarray1 = bitarray;
        j = i * rowSize;
        for(int k = 0; k < rowSize; k++)
            bitarray1.setBulk(k << 5, bits[j + k]);

        return bitarray1;
    }

    public int[] getTopLeftOnBit()
    {
        int i;
        for(i = 0; i < bits.length && bits[i] == 0; i++);
        int ai1[];
        if(i == bits.length)
        {
            ai1 = null;
        } else
        {
            int j = i / rowSize;
            int k = i % rowSize << 5;
            int l = bits[i];
            int i1;
            for(i1 = 0; l << 31 - i1 == 0; i1++);
            int j1 = k + i1;
            int ai[] = new int[2];
            ai[0] = j1;
            ai[1] = j;
            ai1 = ai;
        }
        return ai1;
    }

    public int getWidth()
    {
        return width;
    }

    public int hashCode()
    {
        int i = 31 * (31 * (31 * width + width) + height) + rowSize;
        for(int j = 0; j < bits.length; j++)
            i = i * 31 + bits[j];

        return i;
    }

    public void set(int i, int j)
    {
        int k = j * rowSize + (i >> 5);
        int ai[] = bits;
        ai[k] = ai[k] | 1 << (i & 0x1f);
    }

    public void setRegion(int i, int j, int k, int l)
    {
        if(j < 0 || i < 0)
            throw new IllegalArgumentException("Left and top must be nonnegative");
        if(l < 1 || k < 1)
            throw new IllegalArgumentException("Height and width must be at least 1");
        int i1 = i + k;
        int j1 = j + l;
        if(j1 > height || i1 > width)
            throw new IllegalArgumentException("The region must fit inside the matrix");
        for(int k1 = j; k1 < j1; k1++)
        {
            int l1 = k1 * rowSize;
            for(int i2 = i; i2 < i1; i2++)
            {
                int ai[] = bits;
                int j2 = l1 + (i2 >> 5);
                ai[j2] = ai[j2] | 1 << (i2 & 0x1f);
            }

        }

    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(height * (1 + width));
        for(int i = 0; i < height; i++)
        {
            int j = 0;
            while(j < width) 
            {
                String s;
                if(get(j, i))
                    s = "X ";
                else
                    s = "  ";
                stringbuffer.append(s);
                j++;
            }
            stringbuffer.append('\n');
        }

        return stringbuffer.toString();
    }

    public final int bits[];
    public final int height;
    public final int rowSize;
    public final int width;
}
