// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils.ui.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3d extends Animation
{

    public Rotate3d(float f, float f1, float f2, float f3)
    {
        mFromDegree = f;
        mToDegree = f1;
        mCenterX = f2;
        mCenterY = f3;
        mSaveFromDegree = f;
        mSaveToDegree = f1;
        type = 1;
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
        float f1 = mFromDegree + f * (mToDegree - mFromDegree);
        float f2 = mCenterX;
        float f3 = mCenterY;
        Matrix matrix = transformation.getMatrix();
        if(f1 <= -76F)
        {
            mCamera.save();
            rotate(-90F);
            mCamera.getMatrix(matrix);
            mCamera.restore();
        } else
        if(f1 >= 76F)
        {
            mCamera.save();
            rotate(90F);
            mCamera.getMatrix(matrix);
            mCamera.restore();
        } else
        {
            mCamera.save();
            mCamera.translate(0.0F, 0.0F, f2);
            rotate(f1);
            mCamera.translate(0.0F, 0.0F, -f2);
            mCamera.getMatrix(matrix);
            mCamera.restore();
        }
        matrix.preTranslate(-f2, -f3);
        matrix.postTranslate(f2, f3);
    }

    public int getType()
    {
        return type;
    }

    public void initialize(int i, int j, int k, int l)
    {
        super.initialize(i, j, k, l);
        mCamera = new Camera();
    }

    public void reverseTransformation(boolean flag)
    {
        if(flag)
        {
            mFromDegree = -mSaveFromDegree;
            mToDegree = -mSaveToDegree;
        } else
        {
            mFromDegree = mSaveFromDegree;
            mToDegree = mSaveToDegree;
        }
    }

    public void rotate(float f)
    {
        type;
        JVM INSTR tableswitch 0 6: default 48
    //                   0 49
    //                   1 60
    //                   2 71
    //                   3 82
    //                   4 101
    //                   5 120
    //                   6 139;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return;
_L2:
        mCamera.rotateX(f);
        continue; /* Loop/switch isn't completed */
_L3:
        mCamera.rotateY(f);
        continue; /* Loop/switch isn't completed */
_L4:
        mCamera.rotateZ(f);
        continue; /* Loop/switch isn't completed */
_L5:
        mCamera.rotateX(f);
        mCamera.rotateY(f);
        continue; /* Loop/switch isn't completed */
_L6:
        mCamera.rotateX(f);
        mCamera.rotateZ(f);
        continue; /* Loop/switch isn't completed */
_L7:
        mCamera.rotateY(f);
        mCamera.rotateZ(f);
        continue; /* Loop/switch isn't completed */
_L8:
        mCamera.rotateX(f);
        mCamera.rotateY(f);
        mCamera.rotateZ(f);
        if(true) goto _L1; else goto _L9
_L9:
    }

    public void setType(int i)
    {
        type = i;
    }

    public static final int ROTATE_X = 0;
    public static final int ROTATE_XY = 3;
    public static final int ROTATE_XYZ = 6;
    public static final int ROTATE_XZ = 4;
    public static final int ROTATE_Y = 1;
    public static final int ROTATE_YZ = 5;
    public static final int ROTATE_Z = 2;
    private Camera mCamera;
    private float mCenterX;
    private float mCenterY;
    private float mFromDegree;
    private float mSaveFromDegree;
    private float mSaveToDegree;
    private float mToDegree;
    private int type;
}
