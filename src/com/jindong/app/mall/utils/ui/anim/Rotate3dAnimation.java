// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.ui.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3dAnimation extends Animation
{

    public Rotate3dAnimation(float f, float f1, float f2, float f3, float f4, boolean flag)
    {
        mFromDegrees = f;
        mToDegrees = f1;
        mCenterX = f2;
        mCenterY = f3;
        mDepthZ = f4;
        mReverse = flag;
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        float f1 = mFromDegrees;
        float f2 = f1 + f * (mToDegrees - f1);
        float f3 = mCenterX;
        float f4 = mCenterY;
        Camera camera = mCamera;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if(mReverse)
            camera.translate(0.0F, 0.0F, f * mDepthZ);
        else
            camera.translate(0.0F, 0.0F, mDepthZ * (1.0F - f));
        camera.rotateY(f2);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f3, -f4);
        matrix.postTranslate(f3, f4);
    }

    public void initialize(int i, int j, int k, int l)
    {
        super.initialize(i, j, k, l);
        mCamera = new Camera();
    }

    private Camera mCamera;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final float mFromDegrees;
    private final boolean mReverse;
    private final float mToDegrees;
}
