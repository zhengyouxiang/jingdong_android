// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;

final class ExpandedPair
{

    ExpandedPair(DataCharacter datacharacter, DataCharacter datacharacter1, FinderPattern finderpattern, boolean flag)
    {
        leftChar = datacharacter;
        rightChar = datacharacter1;
        finderPattern = finderpattern;
        mayBeLast = flag;
    }

    FinderPattern getFinderPattern()
    {
        return finderPattern;
    }

    DataCharacter getLeftChar()
    {
        return leftChar;
    }

    DataCharacter getRightChar()
    {
        return rightChar;
    }

    boolean mayBeLast()
    {
        return mayBeLast;
    }

    public boolean mustBeLast()
    {
        boolean flag;
        if(rightChar == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private final FinderPattern finderPattern;
    private final DataCharacter leftChar;
    private final boolean mayBeLast;
    private final DataCharacter rightChar;
}
