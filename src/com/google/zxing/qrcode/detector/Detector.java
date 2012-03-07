// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.detector;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPatternFinder, AlignmentPatternFinder, FinderPatternInfo, FinderPattern, 
//            AlignmentPattern

public class Detector
{

    public Detector(BitMatrix bitmatrix)
    {
        image = bitmatrix;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        float f = sizeOfBlackWhiteBlackRunBothWays((int)resultpoint.getX(), (int)resultpoint.getY(), (int)resultpoint1.getX(), (int)resultpoint1.getY());
        float f1 = sizeOfBlackWhiteBlackRunBothWays((int)resultpoint1.getX(), (int)resultpoint1.getY(), (int)resultpoint.getX(), (int)resultpoint.getY());
        float f2;
        if(Float.isNaN(f))
            f2 = f1 / 7F;
        else
        if(Float.isNaN(f1))
            f2 = f / 7F;
        else
            f2 = (f + f1) / 14F;
        return f2;
    }

    protected static int computeDimension(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, float f)
        throws NotFoundException
    {
        int i = 7 + (round(ResultPoint.distance(resultpoint, resultpoint1) / f) + round(ResultPoint.distance(resultpoint, resultpoint2) / f) >> 1);
        i & 3;
        JVM INSTR tableswitch 0 3: default 64
    //                   0 67
    //                   1 64
    //                   2 73
    //                   3 79;
           goto _L1 _L2 _L1 _L3 _L4
_L1:
        return i;
_L2:
        i++;
        continue; /* Loop/switch isn't completed */
_L3:
        i--;
        if(true) goto _L1; else goto _L4
_L4:
        throw NotFoundException.getNotFoundInstance();
    }

    private static int round(float f)
    {
        return (int)(0.5F + f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitmatrix, PerspectiveTransform perspectivetransform, int i)
        throws NotFoundException
    {
        return GridSampler.getInstance().sampleGrid(bitmatrix, i, perspectivetransform);
    }

    private float sizeOfBlackWhiteBlackRun(int i, int j, int k, int l)
    {
        boolean flag;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        byte byte0;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        float f;
        int k4;
        int l4;
        int i5;
        int j5;
        if(Math.abs(l - j) > Math.abs(k - i))
            flag = true;
        else
            flag = false;
        int i4;
        int j4;
        if(flag)
        {
            i1 = k;
            j1 = l;
            k1 = i;
            l1 = j;
        } else
        {
            i1 = l;
            j1 = k;
            k1 = j;
            l1 = i;
        }
        i2 = Math.abs(j1 - l1);
        j2 = Math.abs(i1 - k1);
        k2 = -i2 >> 1;
        if(k1 < i1)
            byte0 = 1;
        else
            byte0 = -1;
        if(l1 < j1)
            l2 = 1;
        else
            l2 = -1;
        i3 = 0;
        j3 = k2;
        k3 = k1;
        l3 = l1;
        if(l3 == j1) goto _L2; else goto _L1
_L1:
        if(flag)
            k4 = k3;
        else
            k4 = l3;
        if(flag)
            l4 = l3;
        else
            l4 = k3;
        if(i3 == 1)
        {
            if(image.get(k4, l4))
                i3++;
        } else
        if(!image.get(k4, l4))
            i3++;
        if(i3 != 3) goto _L4; else goto _L3
_L3:
        i5 = l3 - l1;
        j5 = k3 - k1;
        if(l2 < 0)
            i5++;
        f = (float)Math.sqrt(i5 * i5 + j5 * j5);
_L6:
        return f;
_L4:
        j3 += j2;
        if(j3 <= 0)
            break MISSING_BLOCK_LABEL_318;
        if(k3 != i1)
            break; /* Loop/switch isn't completed */
_L2:
        i4 = j1 - l1;
        j4 = i1 - k1;
        f = (float)Math.sqrt(i4 * i4 + j4 * j4);
        if(true) goto _L6; else goto _L5
_L5:
        k3 += byte0;
        j3 -= i2;
        l3 += l2;
        if(false)
            ;
        else
            break MISSING_BLOCK_LABEL_99;
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i, int j, int k, int l)
    {
        float f = sizeOfBlackWhiteBlackRun(i, j, k, l);
        int i1 = i - (k - i);
        float f1;
        int j1;
        float f2;
        if(i1 < 0)
        {
            f1 = (float)i / (float)(i - i1);
            i1 = 0;
        } else
        if(i1 > image.getWidth())
        {
            float f4 = (float)(image.getWidth() - i) / (float)(i1 - i);
            int l1 = image.getWidth();
            f1 = f4;
            i1 = l1;
        } else
        {
            f1 = 1.0F;
        }
        j1 = (int)((float)j - f1 * (float)(l - j));
        if(j1 < 0)
        {
            f2 = (float)j / (float)(j - j1);
            j1 = 0;
        } else
        if(j1 > image.getHeight())
        {
            float f3 = (float)(image.getHeight() - j) / (float)(j1 - j);
            int k1 = image.getHeight();
            f2 = f3;
            j1 = k1;
        } else
        {
            f2 = 1.0F;
        }
        return f + sizeOfBlackWhiteBlackRun(i, j, (int)((float)i + f2 * (float)(i1 - i)), j1);
    }

    protected float calculateModuleSize(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2)
    {
        return (calculateModuleSizeOneWay(resultpoint, resultpoint1) + calculateModuleSizeOneWay(resultpoint, resultpoint2)) / 2.0F;
    }

    public PerspectiveTransform createTransform(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2, ResultPoint resultpoint3, int i)
    {
        float f = (float)i - 3.5F;
        float f3;
        float f4;
        float f5;
        float f6;
        if(resultpoint3 != null)
        {
            float f7 = resultpoint3.getX();
            float f8 = resultpoint3.getY();
            float f9 = f - 3F;
            f3 = f9;
            f4 = f9;
            f5 = f8;
            f6 = f7;
        } else
        {
            float f1 = (resultpoint1.getX() - resultpoint.getX()) + resultpoint2.getX();
            float f2 = (resultpoint1.getY() - resultpoint.getY()) + resultpoint2.getY();
            f3 = f;
            f4 = f;
            f5 = f2;
            f6 = f1;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5F, 3.5F, f, 3.5F, f4, f3, 3.5F, f, resultpoint.getX(), resultpoint.getY(), resultpoint1.getX(), resultpoint1.getY(), f6, f5, resultpoint2.getX(), resultpoint2.getY());
    }

    public DetectorResult detect()
        throws NotFoundException, FormatException
    {
        return detect(null);
    }

    public DetectorResult detect(Hashtable hashtable)
        throws NotFoundException, FormatException
    {
        ResultPointCallback resultpointcallback;
        if(hashtable == null)
            resultpointcallback = null;
        else
            resultpointcallback = (ResultPointCallback)hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        resultPointCallback = resultpointcallback;
        return processFinderPatternInfo((new FinderPatternFinder(image, resultPointCallback)).find(hashtable));
    }

    protected AlignmentPattern findAlignmentInRegion(float f, int i, int j, float f1)
        throws NotFoundException
    {
        int k = (int)(f1 * f);
        int l = Math.max(0, i - k);
        int i1 = Math.min(image.getWidth() - 1, i + k);
        if((float)(i1 - l) < f * 3F)
            throw NotFoundException.getNotFoundInstance();
        int j1 = Math.max(0, j - k);
        int k1 = Math.min(image.getHeight() - 1, k + j);
        if((float)(k1 - j1) < f * 3F)
            throw NotFoundException.getNotFoundInstance();
        else
            return (new AlignmentPatternFinder(image, l, j1, i1 - l, k1 - j1, f, resultPointCallback)).find();
    }

    protected BitMatrix getImage()
    {
        return image;
    }

    protected ResultPointCallback getResultPointCallback()
    {
        return resultPointCallback;
    }

    protected DetectorResult processFinderPatternInfo(FinderPatternInfo finderpatterninfo)
        throws NotFoundException, FormatException
    {
        FinderPattern finderpattern;
        FinderPattern finderpattern1;
        FinderPattern finderpattern2;
        float f;
        int i;
        int k;
        int l;
        int i1;
        finderpattern = finderpatterninfo.getTopLeft();
        finderpattern1 = finderpatterninfo.getTopRight();
        finderpattern2 = finderpatterninfo.getBottomLeft();
        f = calculateModuleSize(finderpattern, finderpattern1, finderpattern2);
        if(f < 1.0F)
            throw NotFoundException.getNotFoundInstance();
        i = computeDimension(finderpattern, finderpattern1, finderpattern2, f);
        Version version = Version.getProvisionalVersionForDimension(i);
        int j = version.getDimensionForVersion() - 7;
        if(version.getAlignmentPatternCenters().length <= 0)
            break MISSING_BLOCK_LABEL_303;
        float f1 = (finderpattern1.getX() - finderpattern.getX()) + finderpattern2.getX();
        float f2 = (finderpattern1.getY() - finderpattern.getY()) + finderpattern2.getY();
        float f3 = 1.0F - 3F / (float)j;
        k = (int)(finderpattern.getX() + f3 * (f1 - finderpattern.getX()));
        l = (int)(finderpattern.getY() + f3 * (f2 - finderpattern.getY()));
        i1 = 4;
_L1:
        float f4;
        if(i1 > 16)
            break MISSING_BLOCK_LABEL_303;
        f4 = i1;
        AlignmentPattern alignmentpattern1 = findAlignmentInRegion(f, k, l, f4);
        AlignmentPattern alignmentpattern = alignmentpattern1;
_L2:
        PerspectiveTransform perspectivetransform = createTransform(finderpattern, finderpattern1, finderpattern2, alignmentpattern, i);
        BitMatrix bitmatrix = sampleGrid(image, perspectivetransform, i);
        ResultPoint aresultpoint1[];
        NotFoundException notfoundexception;
        if(alignmentpattern == null)
        {
            ResultPoint aresultpoint2[] = new ResultPoint[3];
            aresultpoint2[0] = finderpattern2;
            aresultpoint2[1] = finderpattern;
            aresultpoint2[2] = finderpattern1;
            aresultpoint1 = aresultpoint2;
        } else
        {
            ResultPoint aresultpoint[] = new ResultPoint[4];
            aresultpoint[0] = finderpattern2;
            aresultpoint[1] = finderpattern;
            aresultpoint[2] = finderpattern1;
            aresultpoint[3] = alignmentpattern;
            aresultpoint1 = aresultpoint;
        }
        return new DetectorResult(bitmatrix, aresultpoint1);
        notfoundexception;
        i1 <<= 1;
          goto _L1
        alignmentpattern = null;
          goto _L2
    }

    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;
}
