// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.DataCharacter;
import java.util.Vector;

// Referenced classes of package com.google.zxing.oned.rss.expanded:
//            ExpandedPair

final class BitArrayBuilder
{

    private BitArrayBuilder()
    {
    }

    static BitArray buildBitArray(Vector vector)
    {
        int i = (vector.size() << 1) - 1;
        int j;
        BitArray bitarray;
        int k;
        int l;
        int j1;
        if(((ExpandedPair)vector.lastElement()).getRightChar() == null)
            j = i - 1;
        else
            j = i;
        bitarray = new BitArray(j * 12);
        k = ((ExpandedPair)vector.elementAt(0)).getRightChar().getValue();
        l = 0;
        for(int i1 = 11; i1 >= 0; i1--)
        {
            if((k & 1 << i1) != 0)
                bitarray.set(l);
            l++;
        }

        j1 = 1;
        while(j1 < vector.size()) 
        {
            ExpandedPair expandedpair = (ExpandedPair)vector.elementAt(j1);
            int k1 = expandedpair.getLeftChar().getValue();
            int l1 = l;
            for(int i2 = 11; i2 >= 0; i2--)
            {
                if((k1 & 1 << i2) != 0)
                    bitarray.set(l1);
                l1++;
            }

            int j2;
            if(expandedpair.getRightChar() != null)
            {
                int k2 = expandedpair.getRightChar().getValue();
                int l2 = 11;
                int i3 = l1;
                for(; l2 >= 0; l2--)
                {
                    if((k2 & 1 << l2) != 0)
                        bitarray.set(i3);
                    i3++;
                }

                j2 = i3;
            } else
            {
                j2 = l1;
            }
            j1++;
            l = j2;
        }
        return bitarray;
    }
}
