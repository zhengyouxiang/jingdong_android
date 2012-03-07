// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.NotFoundException;

// Referenced classes of package com.google.zxing.common:
//            GridSampler, PerspectiveTransform, BitMatrix

public final class DefaultGridSampler extends GridSampler
{

    public DefaultGridSampler()
    {
    }

    public BitMatrix sampleGrid(BitMatrix bitmatrix, int i, float f, float f1, float f2, float f3, float f4, 
            float f5, float f6, float f7, float f8, float f9, float f10, float f11, 
            float f12, float f13, float f14, float f15)
        throws NotFoundException
    {
        return sampleGrid(bitmatrix, i, PerspectiveTransform.quadrilateralToQuadrilateral(f, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15));
    }

    public BitMatrix sampleGrid(BitMatrix bitmatrix, int i, PerspectiveTransform perspectivetransform)
        throws NotFoundException
    {
        BitMatrix bitmatrix1 = new BitMatrix(i);
        float af[] = new float[i << 1];
        for(int j = 0; j < i; j++)
        {
            int k = af.length;
            float f = 0.5F + (float)j;
            for(int l = 0; l < k; l += 2)
            {
                af[l] = 0.5F + (float)(l >> 1);
                af[l + 1] = f;
            }

            perspectivetransform.transformPoints(af);
            checkAndNudgePoints(bitmatrix, af);
            int i1 = 0;
            while(i1 < k) 
            {
                try
                {
                    if(bitmatrix.get((int)af[i1], (int)af[i1 + 1]))
                        bitmatrix1.set(i1 >> 1, j);
                }
                catch(ArrayIndexOutOfBoundsException arrayindexoutofboundsexception)
                {
                    throw NotFoundException.getNotFoundInstance();
                }
                i1 += 2;
            }
        }

        return bitmatrix1;
    }
}
