// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


public final class PerspectiveTransform
{

    private PerspectiveTransform(float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            float f7, float f8)
    {
        a11 = f;
        a12 = f3;
        a13 = f6;
        a21 = f1;
        a22 = f4;
        a23 = f7;
        a31 = f2;
        a32 = f5;
        a33 = f8;
    }

    public static PerspectiveTransform quadrilateralToQuadrilateral(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, 
            float f8, float f9, float f10, float f11, float f12, float f13, float f14, 
            float f15)
    {
        PerspectiveTransform perspectivetransform = quadrilateralToSquare(f, f1, f2, f3, f4, f5, f6, f7);
        return squareToQuadrilateral(f8, f9, f10, f11, f12, f13, f14, f15).times(perspectivetransform);
    }

    public static PerspectiveTransform quadrilateralToSquare(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7)
    {
        return squareToQuadrilateral(f, f1, f2, f3, f4, f5, f6, f7).buildAdjoint();
    }

    public static PerspectiveTransform squareToQuadrilateral(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7)
    {
        float f8 = f7 - f5;
        float f9 = (f5 + (f1 - f3)) - f7;
        PerspectiveTransform perspectivetransform;
        if(f8 == 0.0F && f9 == 0.0F)
        {
            perspectivetransform = new PerspectiveTransform(f2 - f, f4 - f2, f, f3 - f1, f5 - f3, f1, 0.0F, 0.0F, 1.0F);
        } else
        {
            float f10 = f2 - f4;
            float f11 = f6 - f4;
            float f12 = (f4 + (f - f2)) - f6;
            float f13 = f3 - f5;
            float f14 = f10 * f8 - f11 * f13;
            float f15 = (f8 * f12 - f11 * f9) / f14;
            float f16 = (f10 * f9 - f12 * f13) / f14;
            perspectivetransform = new PerspectiveTransform((f2 - f) + f2 * f15, (f6 - f) + f16 * f6, f, (f3 - f1) + f3 * f15, (f7 - f1) + f16 * f7, f1, f15, f16, 1.0F);
        }
        return perspectivetransform;
    }

    PerspectiveTransform buildAdjoint()
    {
        return new PerspectiveTransform(a22 * a33 - a23 * a32, a23 * a31 - a21 * a33, a21 * a32 - a22 * a31, a13 * a32 - a12 * a33, a11 * a33 - a13 * a31, a12 * a31 - a11 * a32, a12 * a23 - a13 * a22, a13 * a21 - a11 * a23, a11 * a22 - a12 * a21);
    }

    PerspectiveTransform times(PerspectiveTransform perspectivetransform)
    {
        return new PerspectiveTransform(a11 * perspectivetransform.a11 + a21 * perspectivetransform.a12 + a31 * perspectivetransform.a13, a11 * perspectivetransform.a21 + a21 * perspectivetransform.a22 + a31 * perspectivetransform.a23, a11 * perspectivetransform.a31 + a21 * perspectivetransform.a32 + a31 * perspectivetransform.a33, a12 * perspectivetransform.a11 + a22 * perspectivetransform.a12 + a32 * perspectivetransform.a13, a12 * perspectivetransform.a21 + a22 * perspectivetransform.a22 + a32 * perspectivetransform.a23, a12 * perspectivetransform.a31 + a22 * perspectivetransform.a32 + a32 * perspectivetransform.a33, a13 * perspectivetransform.a11 + a23 * perspectivetransform.a12 + a33 * perspectivetransform.a13, a13 * perspectivetransform.a21 + a23 * perspectivetransform.a22 + a33 * perspectivetransform.a23, a13 * perspectivetransform.a31 + a23 * perspectivetransform.a32 + a33 * perspectivetransform.a33);
    }

    public void transformPoints(float af[])
    {
        int i = af.length;
        float f = a11;
        float f1 = a12;
        float f2 = a13;
        float f3 = a21;
        float f4 = a22;
        float f5 = a23;
        float f6 = a31;
        float f7 = a32;
        float f8 = a33;
        for(int j = 0; j < i; j += 2)
        {
            float f9 = af[j];
            float f10 = af[j + 1];
            float f11 = f8 + (f2 * f9 + f5 * f10);
            af[j] = (f6 + (f * f9 + f3 * f10)) / f11;
            af[j + 1] = (f7 + (f9 * f1 + f10 * f4)) / f11;
        }

    }

    public void transformPoints(float af[], float af1[])
    {
        int i = af.length;
        for(int j = 0; j < i; j++)
        {
            float f = af[j];
            float f1 = af1[j];
            float f2 = f * a13 + f1 * a23 + a33;
            af[j] = (f * a11 + f1 * a21 + a31) / f2;
            af1[j] = (f * a12 + f1 * a22 + a32) / f2;
        }

    }

    private final float a11;
    private final float a12;
    private final float a13;
    private final float a21;
    private final float a22;
    private final float a23;
    private final float a31;
    private final float a32;
    private final float a33;
}
