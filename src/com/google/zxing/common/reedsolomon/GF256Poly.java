// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common.reedsolomon;


// Referenced classes of package com.google.zxing.common.reedsolomon:
//            GF256

final class GF256Poly
{

    GF256Poly(GF256 gf256, int ai[])
    {
        if(ai == null || ai.length == 0)
            throw new IllegalArgumentException();
        field = gf256;
        int i = ai.length;
        if(i > 1 && ai[0] == 0)
        {
            int j;
            for(j = 1; j < i && ai[j] == 0; j++);
            if(j == i)
            {
                coefficients = gf256.getZero().coefficients;
            } else
            {
                coefficients = new int[i - j];
                System.arraycopy(ai, j, coefficients, 0, coefficients.length);
            }
        } else
        {
            coefficients = ai;
        }
    }

    GF256Poly addOrSubtract(GF256Poly gf256poly)
    {
        if(!field.equals(gf256poly.field))
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        GF256Poly gf256poly1;
        if(isZero())
            gf256poly1 = gf256poly;
        else
        if(gf256poly.isZero())
        {
            gf256poly1 = this;
        } else
        {
            int ai[] = coefficients;
            int ai1[] = gf256poly.coefficients;
            int ai3[];
            int i;
            if(ai.length <= ai1.length)
            {
                int ai2[] = ai1;
                ai1 = ai;
                ai = ai2;
            }
            ai3 = new int[ai.length];
            i = ai.length - ai1.length;
            System.arraycopy(ai, 0, ai3, 0, i);
            for(int j = i; j < ai.length; j++)
                ai3[j] = GF256.addOrSubtract(ai1[j - i], ai[j]);

            gf256poly1 = new GF256Poly(field, ai3);
        }
        return gf256poly1;
    }

    GF256Poly[] divide(GF256Poly gf256poly)
    {
        if(!field.equals(gf256poly.field))
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        if(gf256poly.isZero())
            throw new IllegalArgumentException("Divide by 0");
        GF256Poly gf256poly1 = field.getZero();
        int i = gf256poly.getCoefficient(gf256poly.getDegree());
        int j = field.inverse(i);
        GF256Poly gf256poly2 = gf256poly1;
        GF256Poly gf256poly3;
        GF256Poly gf256poly4;
        for(gf256poly3 = this; gf256poly3.getDegree() >= gf256poly.getDegree() && !gf256poly3.isZero(); gf256poly3 = gf256poly3.addOrSubtract(gf256poly4))
        {
            int k = gf256poly3.getDegree() - gf256poly.getDegree();
            int l = field.multiply(gf256poly3.getCoefficient(gf256poly3.getDegree()), j);
            gf256poly4 = gf256poly.multiplyByMonomial(k, l);
            gf256poly2 = gf256poly2.addOrSubtract(field.buildMonomial(k, l));
        }

        GF256Poly agf256poly[] = new GF256Poly[2];
        agf256poly[0] = gf256poly2;
        agf256poly[1] = gf256poly3;
        return agf256poly;
    }

    int evaluateAt(int i)
    {
        int j = 0;
        int j1;
        if(i == 0)
        {
            j1 = getCoefficient(0);
        } else
        {
            int k = coefficients.length;
            if(i == 1)
            {
                for(int k1 = 0; k1 < k; k1++)
                    j = GF256.addOrSubtract(j, coefficients[k1]);

                j1 = j;
            } else
            {
                int l = coefficients[0];
                for(int i1 = 1; i1 < k; i1++)
                    l = GF256.addOrSubtract(field.multiply(i, l), coefficients[i1]);

                j1 = l;
            }
        }
        return j1;
    }

    int getCoefficient(int i)
    {
        return coefficients[coefficients.length - 1 - i];
    }

    int[] getCoefficients()
    {
        return coefficients;
    }

    int getDegree()
    {
        return coefficients.length - 1;
    }

    boolean isZero()
    {
        boolean flag;
        if(coefficients[0] == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    GF256Poly multiply(int i)
    {
        GF256Poly gf256poly;
        if(i == 0)
            gf256poly = field.getZero();
        else
        if(i == 1)
        {
            gf256poly = this;
        } else
        {
            int j = coefficients.length;
            int ai[] = new int[j];
            for(int k = 0; k < j; k++)
                ai[k] = field.multiply(coefficients[k], i);

            gf256poly = new GF256Poly(field, ai);
        }
        return gf256poly;
    }

    GF256Poly multiply(GF256Poly gf256poly)
    {
        if(!field.equals(gf256poly.field))
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        GF256Poly gf256poly1;
        if(isZero() || gf256poly.isZero())
        {
            gf256poly1 = field.getZero();
        } else
        {
            int ai[] = coefficients;
            int i = ai.length;
            int ai1[] = gf256poly.coefficients;
            int j = ai1.length;
            int ai2[] = new int[(i + j) - 1];
            for(int k = 0; k < i; k++)
            {
                int l = ai[k];
                for(int i1 = 0; i1 < j; i1++)
                    ai2[k + i1] = GF256.addOrSubtract(ai2[k + i1], field.multiply(l, ai1[i1]));

            }

            gf256poly1 = new GF256Poly(field, ai2);
        }
        return gf256poly1;
    }

    GF256Poly multiplyByMonomial(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException();
        GF256Poly gf256poly;
        if(j == 0)
        {
            gf256poly = field.getZero();
        } else
        {
            int k = coefficients.length;
            int ai[] = new int[k + i];
            for(int l = 0; l < k; l++)
                ai[l] = field.multiply(coefficients[l], j);

            gf256poly = new GF256Poly(field, ai);
        }
        return gf256poly;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(8 * getDegree());
        int i = getDegree();
        do
        {
            if(i >= 0)
            {
                int j = getCoefficient(i);
                if(j != 0)
                {
                    if(j < 0)
                    {
                        stringbuffer.append(" - ");
                        j = -j;
                    } else
                    if(stringbuffer.length() > 0)
                        stringbuffer.append(" + ");
                    if(i == 0 || j != 1)
                    {
                        int k = field.log(j);
                        if(k == 0)
                            stringbuffer.append('1');
                        else
                        if(k == 1)
                        {
                            stringbuffer.append('a');
                        } else
                        {
                            stringbuffer.append("a^");
                            stringbuffer.append(k);
                        }
                    }
                    if(i != 0)
                        if(i == 1)
                        {
                            stringbuffer.append('x');
                        } else
                        {
                            stringbuffer.append("x^");
                            stringbuffer.append(i);
                        }
                }
                i--;
                continue;
            }
            return stringbuffer.toString();
        } while(true);
    }

    private final int coefficients[];
    private final GF256 field;
}
