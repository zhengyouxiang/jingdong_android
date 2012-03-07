// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

abstract class DataMask
{
    private static class DataMask111 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            boolean flag;
            if((1 & (1 & i + j) + (i * j) % 3) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask111()
        {
        }

    }

    private static class DataMask110 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            int k = i * j;
            boolean flag;
            if((1 & (k & 1) + k % 3) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask110()
        {
        }

    }

    private static class DataMask101 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            int k = i * j;
            boolean flag;
            if((k & 1) + k % 3 == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask101()
        {
        }

    }

    private static class DataMask100 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            boolean flag;
            if((1 & (i >>> 1) + j / 3) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask100()
        {
        }

    }

    private static class DataMask011 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            boolean flag;
            if((i + j) % 3 == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask011()
        {
        }

    }

    private static class DataMask010 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            boolean flag;
            if(j % 3 == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask010()
        {
        }

    }

    private static class DataMask001 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            boolean flag;
            if((i & 1) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask001()
        {
        }

    }

    private static class DataMask000 extends DataMask
    {

        boolean isMasked(int i, int j)
        {
            boolean flag;
            if((1 & i + j) == 0)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private DataMask000()
        {
        }

    }


    private DataMask()
    {
    }


    static DataMask forReference(int i)
    {
        if(i < 0 || i > 7)
            throw new IllegalArgumentException();
        else
            return DATA_MASKS[i];
    }

    abstract boolean isMasked(int i, int j);

    final void unmaskBitMatrix(BitMatrix bitmatrix, int i)
    {
        for(int j = 0; j < i; j++)
        {
            for(int k = 0; k < i; k++)
                if(isMasked(j, k))
                    bitmatrix.flip(k, j);

        }

    }

    private static final DataMask DATA_MASKS[];

    static 
    {
        DataMask adatamask[] = new DataMask[8];
        adatamask[0] = new DataMask000();
        adatamask[1] = new DataMask001();
        adatamask[2] = new DataMask010();
        adatamask[3] = new DataMask011();
        adatamask[4] = new DataMask100();
        adatamask[5] = new DataMask101();
        adatamask[6] = new DataMask110();
        adatamask[7] = new DataMask111();
        DATA_MASKS = adatamask;
    }
}
