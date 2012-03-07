// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss;


public final class RSSUtils
{

    private RSSUtils()
    {
    }

    static int combins(int i, int j)
    {
_L2:
        int l1;
        int i2;
        for(; l1 <= k; l1++)
            i2 /= l1;

        return i2;
        int k;
        int l;
        int i1;
        int j1;
        if(i - j > j)
        {
            l = i - j;
            k = j;
        } else
        {
            k = i - j;
            l = j;
        }
        i1 = 1;
        j1 = 1;
        for(int k1 = i; k1 > l; k1--)
        {
            j1 *= k1;
            if(i1 <= k)
            {
                j1 /= i1;
                i1++;
            }
        }

        l1 = i1;
        i2 = j1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static int[] elements(int ai[], int i, int j)
    {
        int ai1[] = new int[2 + ai.length];
        int k = j << 1;
        ai1[0] = 1;
        int l = 10;
        int i1 = 1;
        int j1 = 1;
        for(; i1 < k - 2; i1 += 2)
        {
            ai1[i1] = ai[i1 - 1] - ai1[i1 - 1];
            ai1[i1 + 1] = ai[i1] - ai1[i1];
            j1 += ai1[i1] + ai1[i1 + 1];
            if(ai1[i1] < l)
                l = ai1[i1];
        }

        ai1[k - 1] = i - j1;
        int k1;
        if(ai1[k - 1] < l)
            k1 = ai1[k - 1];
        else
            k1 = l;
        if(k1 > 1)
        {
            for(int l1 = 0; l1 < k; l1 += 2)
            {
                ai1[l1] = ai1[l1] + (k1 - 1);
                int i2 = l1 + 1;
                ai1[i2] = ai1[i2] - (k1 - 1);
            }

        }
        return ai1;
    }

    public static int getRSSvalue(int ai[], int i, boolean flag)
    {
        int j = ai.length;
        int k = 0;
        for(int l = 0; l < j; l++)
            k += ai[l];

        int i1 = k;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        do
        {
            if(l1 >= j - 1)
                break;
            int i2 = j1 | 1 << l1;
            int j2 = k1;
            int k2 = i2;
            int l2 = 1;
            while(l2 < ai[l1]) 
            {
                int j3 = combins(i1 - l2 - 1, j - l1 - 2);
                if(flag && k2 == 0 && i1 - l2 - (j - l1 - 1) >= j - l1 - 1)
                    j3 -= combins(i1 - l2 - (j - l1), j - l1 - 2);
                if(j - l1 - 1 > 1)
                {
                    int k3 = i1 - l2 - (j - l1 - 2);
                    int l3 = 0;
                    for(int i4 = k3; i4 > i; i4--)
                        l3 += combins(i1 - l2 - i4 - 1, j - l1 - 3);

                    j3 -= l3 * (j - 1 - l1);
                } else
                if(i1 - l2 > i)
                    j3--;
                j2 += j3;
                l2++;
                k2 &= -1 ^ 1 << l1;
            }
            int i3 = i1 - l2;
            l1++;
            i1 = i3;
            j1 = k2;
            k1 = j2;
        } while(true);
        return k1;
    }

    static int[] getRSSwidths(int i, int j, int k, int l, boolean flag)
    {
        int ai[];
        int i1;
        int j1;
        int k1;
        int l1;
        ai = new int[k];
        i1 = j;
        j1 = 0;
        k1 = i;
        l1 = 0;
_L9:
        if(j1 >= k - 1) goto _L2; else goto _L1
_L1:
        int j2;
        int k2;
        int l2;
        int i2 = l1 | 1 << j1;
        j2 = k1;
        k2 = i2;
        l2 = 1;
_L7:
        int i3;
        i3 = combins(i1 - l2 - 1, k - j1 - 2);
        if(flag && k2 == 0 && i1 - l2 - (k - j1 - 1) >= k - j1 - 1)
            i3 -= combins(i1 - l2 - (k - j1), k - j1 - 2);
        if(k - j1 - 1 <= 1) goto _L4; else goto _L3
_L3:
        int k3 = i1 - l2 - (k - j1 - 2);
        int l3 = 0;
        for(int i4 = k3; i4 > l; i4--)
            l3 += combins(i1 - l2 - i4 - 1, k - j1 - 3);

        i3 -= l3 * (k - 1 - j1);
_L6:
        j2 -= i3;
        if(j2 >= 0)
            break; /* Loop/switch isn't completed */
        int j3 = j2 + i3;
        i1 -= l2;
        ai[j1] = l2;
        j1++;
        l1 = k2;
        k1 = j3;
        continue; /* Loop/switch isn't completed */
_L4:
        if(i1 - l2 > l)
            i3--;
        if(true) goto _L6; else goto _L5
_L5:
        l2++;
        k2 &= -1 ^ 1 << j1;
        if(true) goto _L7; else goto _L2
_L2:
        ai[j1] = i1;
        return ai;
        if(true) goto _L9; else goto _L8
_L8:
    }
}
