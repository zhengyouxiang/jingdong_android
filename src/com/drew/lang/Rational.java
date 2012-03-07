// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.lang;

import java.io.Serializable;

public class Rational extends Number
    implements Serializable
{

    public Rational(int i, int j)
    {
        maxSimplificationCalculations = 1000;
        numerator = i;
        denominator = j;
    }

    private boolean tooComplexForSimplification()
    {
        boolean flag;
        if(2D + (double)(Math.min(denominator, numerator) - 1) / 5D > (double)maxSimplificationCalculations)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final byte byteValue()
    {
        return (byte)(int)doubleValue();
    }

    public double doubleValue()
    {
        return (double)numerator / (double)denominator;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(!(obj instanceof Rational))
        {
            flag = false;
        } else
        {
            Rational rational = (Rational)obj;
            if(doubleValue() == rational.doubleValue())
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public float floatValue()
    {
        return (float)numerator / (float)denominator;
    }

    public final int getDenominator()
    {
        return denominator;
    }

    public final int getNumerator()
    {
        return numerator;
    }

    public Rational getReciprocal()
    {
        return new Rational(denominator, numerator);
    }

    public Rational getSimplifiedInstance()
    {
        if(!tooComplexForSimplification()) goto _L2; else goto _L1
_L1:
        Rational rational = this;
_L8:
        return rational;
_L2:
        int i = 2;
_L4:
        if(i > Math.min(denominator, numerator))
        {
            rational = this;
            continue; /* Loop/switch isn't completed */
        }
          goto _L3
_L6:
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        if(i % 2 == 0 && i > 2 || i % 5 == 0 && i > 5 || denominator % i != 0 || numerator % i != 0) goto _L6; else goto _L5
_L5:
        rational = new Rational(numerator / i, denominator / i);
        if(true) goto _L8; else goto _L7
_L7:
    }

    public final int intValue()
    {
        return (int)doubleValue();
    }

    public boolean isInteger()
    {
        boolean flag;
        if(denominator == 1 || denominator != 0 && numerator % denominator == 0 || denominator == 0 && numerator == 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final long longValue()
    {
        return (long)doubleValue();
    }

    public final short shortValue()
    {
        return (short)(int)doubleValue();
    }

    public String toSimpleString(boolean flag)
    {
        if(denominator != 0 || numerator == 0) goto _L2; else goto _L1
_L1:
        String s = toString();
_L4:
        return s;
_L2:
        if(isInteger())
        {
            s = Integer.toString(intValue());
            continue; /* Loop/switch isn't completed */
        }
        if(numerator != 1 && denominator % numerator == 0)
        {
            s = (new Rational(1, denominator / numerator)).toSimpleString(flag);
            continue; /* Loop/switch isn't completed */
        }
        Rational rational = getSimplifiedInstance();
        if(flag)
        {
            String s1 = Double.toString(rational.doubleValue());
            if(s1.length() < 5)
            {
                s = s1;
                continue; /* Loop/switch isn't completed */
            }
        }
        s = rational.toString();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(numerator))).append("/").append(denominator).toString();
    }

    private final int denominator;
    private int maxSimplificationCalculations;
    private final int numerator;
}
