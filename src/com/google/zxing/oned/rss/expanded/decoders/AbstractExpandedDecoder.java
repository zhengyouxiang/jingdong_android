// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            GeneralAppIdDecoder, AI01AndOtherAIs, AnyAIDecoder, AI013103decoder, 
//            AI01320xDecoder, AI01392xDecoder, AI01393xDecoder, AI013x0x1xDecoder

public abstract class AbstractExpandedDecoder
{

    AbstractExpandedDecoder(BitArray bitarray)
    {
        information = bitarray;
        generalDecoder = new GeneralAppIdDecoder(bitarray);
    }

    public static AbstractExpandedDecoder createDecoder(BitArray bitarray)
    {
        if(!bitarray.get(1)) goto _L2; else goto _L1
_L1:
        Object obj = new AI01AndOtherAIs(bitarray);
_L4:
        return ((AbstractExpandedDecoder) (obj));
_L2:
        if(bitarray.get(2))
            break; /* Loop/switch isn't completed */
        obj = new AnyAIDecoder(bitarray);
        if(true) goto _L4; else goto _L3
_L3:
        switch(GeneralAppIdDecoder.extractNumericValueFromBitArray(bitarray, 1, 4))
        {
        default:
            switch(GeneralAppIdDecoder.extractNumericValueFromBitArray(bitarray, 1, 5))
            {
            default:
                switch(GeneralAppIdDecoder.extractNumericValueFromBitArray(bitarray, 1, 7))
                {
                default:
                    throw new IllegalStateException("unknown decoder: " + bitarray);

                case 56: // '8'
                    obj = new AI013x0x1xDecoder(bitarray, "310", "11");
                    break;

                case 57: // '9'
                    obj = new AI013x0x1xDecoder(bitarray, "320", "11");
                    break;

                case 58: // ':'
                    obj = new AI013x0x1xDecoder(bitarray, "310", "13");
                    break;

                case 59: // ';'
                    obj = new AI013x0x1xDecoder(bitarray, "320", "13");
                    break;

                case 60: // '<'
                    obj = new AI013x0x1xDecoder(bitarray, "310", "15");
                    break;

                case 61: // '='
                    obj = new AI013x0x1xDecoder(bitarray, "320", "15");
                    break;

                case 62: // '>'
                    obj = new AI013x0x1xDecoder(bitarray, "310", "17");
                    break;

                case 63: // '?'
                    obj = new AI013x0x1xDecoder(bitarray, "320", "17");
                    break;
                }
                break;

            case 12: // '\f'
                obj = new AI01392xDecoder(bitarray);
                break;

            case 13: // '\r'
                obj = new AI01393xDecoder(bitarray);
                break;
            }
            break;

        case 4: // '\004'
            obj = new AI013103decoder(bitarray);
            break;

        case 5: // '\005'
            obj = new AI01320xDecoder(bitarray);
            break;
        }
        if(true) goto _L4; else goto _L5
_L5:
    }

    public abstract String parseInformation()
        throws NotFoundException;

    protected final GeneralAppIdDecoder generalDecoder;
    protected final BitArray information;
}
