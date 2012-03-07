// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


public final class BitArray
{

    public BitArray()
    {
        size = 0;
        bits = new int[1];
    }

    public BitArray(int i)
    {
        size = i;
        bits = makeArray(i);
    }

    private void ensureCapacity(int i)
    {
        if(i > bits.length << 5)
        {
            int ai[] = makeArray(i);
            System.arraycopy(bits, 0, ai, 0, bits.length);
            bits = ai;
        }
    }

    private static int[] makeArray(int i)
    {
        return new int[i + 31 >> 5];
    }

    public void appendBit(boolean flag)
    {
        ensureCapacity(1 + size);
        if(flag)
        {
            int ai[] = bits;
            int i = size >> 5;
            ai[i] = ai[i] | 1 << (0x1f & size);
        }
        size = 1 + size;
    }

    public void appendBitArray(BitArray bitarray)
    {
        int i = bitarray.getSize();
        ensureCapacity(i + size);
        for(int j = 0; j < i; j++)
            appendBit(bitarray.get(j));

    }

    public void appendBits(int i, int j)
    {
        if(j < 0 || j > 32)
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        ensureCapacity(j + size);
        int k = j;
        while(k > 0) 
        {
            boolean flag;
            if((1 & i >> k - 1) == 1)
                flag = true;
            else
                flag = false;
            appendBit(flag);
            k--;
        }
    }

    public void clear()
    {
        int i = bits.length;
        for(int j = 0; j < i; j++)
            bits[j] = 0;

    }

    public void flip(int i)
    {
        int ai[] = bits;
        int j = i >> 5;
        ai[j] = ai[j] ^ 1 << (i & 0x1f);
    }

    public boolean get(int i)
    {
        boolean flag;
        if((bits[i >> 5] & 1 << (i & 0x1f)) != 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int[] getBitArray()
    {
        return bits;
    }

    public int getSize()
    {
        return size;
    }

    public int getSizeInBytes()
    {
        return 7 + size >> 3;
    }

    public boolean isRange(int i, int j, boolean flag)
    {
        if(j < i)
            throw new IllegalArgumentException();
        if(j != i) goto _L2; else goto _L1
_L1:
        boolean flag1 = true;
_L9:
        return flag1;
_L2:
        int k;
        int l;
        int i1;
        int j1;
        k = j - 1;
        l = i >> 5;
        i1 = k >> 5;
        j1 = l;
_L10:
        if(j1 > i1) goto _L4; else goto _L3
_L3:
        int k1;
        int l1;
        int j2;
        int k2;
        if(j1 > l)
            k1 = 0;
        else
            k1 = i & 0x1f;
        if(j1 < i1)
            l1 = 31;
        else
            l1 = k & 0x1f;
        if(k1 != 0 || l1 != 31) goto _L6; else goto _L5
_L5:
        j2 = -1;
_L11:
        int i2;
        k2 = j2 & bits[j1];
        if(!flag)
            j2 = 0;
        if(k2 == j2) goto _L8; else goto _L7
_L7:
        flag1 = false;
          goto _L9
_L6:
        i2 = 0;
        for(; k1 <= l1; k1++)
            i2 |= 1 << k1;

        break MISSING_BLOCK_LABEL_179;
_L8:
        j1++;
          goto _L10
_L4:
        flag1 = true;
          goto _L9
        j2 = i2;
          goto _L11
    }

    public void reverse()
    {
        int ai[] = new int[bits.length];
        int i = size;
        for(int j = 0; j < i; j++)
            if(get(i - j - 1))
            {
                int k = j >> 5;
                ai[k] = ai[k] | 1 << (j & 0x1f);
            }

        bits = ai;
    }

    public void set(int i)
    {
        int ai[] = bits;
        int j = i >> 5;
        ai[j] = ai[j] | 1 << (i & 0x1f);
    }

    public void setBulk(int i, int j)
    {
        bits[i >> 5] = j;
    }

    public void toBytes(int i, byte abyte0[], int j, int k)
    {
        int l = 0;
        int k1;
        for(int i1 = i; l < k; i1 = k1)
        {
            int j1 = 0;
            k1 = i1;
            for(int l1 = 0; l1 < 8; l1++)
            {
                if(get(k1))
                    j1 |= 1 << 7 - l1;
                k1++;
            }

            abyte0[j + l] = (byte)j1;
            l++;
        }

    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(size);
        int i = 0;
        while(i < size) 
        {
            if((i & 7) == 0)
                stringbuffer.append(' ');
            char c;
            if(get(i))
                c = 'X';
            else
                c = '.';
            stringbuffer.append(c);
            i++;
        }
        return stringbuffer.toString();
    }

    public void xor(BitArray bitarray)
    {
        if(bits.length != bitarray.bits.length)
            throw new IllegalArgumentException("Sizes don't match");
        for(int i = 0; i < bits.length; i++)
        {
            int ai[] = bits;
            ai[i] = ai[i] ^ bitarray.bits[i];
        }

    }

    public int bits[];
    public int size;
}
