// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, ExpandedProductParsedResult

final class ExpandedProductResultParser extends ResultParser
{

    private ExpandedProductResultParser()
    {
    }

    private static String findAIvalue(int i, String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(s.charAt(i) == '(') goto _L2; else goto _L1
_L1:
        String s2 = "ERROR";
_L4:
        return s2;
_L2:
        String s1 = s.substring(i + 1);
        int j = 0;
label0:
        do
        {
            if(j < s1.length())
            {
                char c = s1.charAt(j);
                switch(c)
                {
                case 42: // '*'
                case 43: // '+'
                case 44: // ','
                case 45: // '-'
                case 46: // '.'
                case 47: // '/'
                default:
                    s2 = "ERROR";
                    break label0;

                case 48: // '0'
                case 49: // '1'
                case 50: // '2'
                case 51: // '3'
                case 52: // '4'
                case 53: // '5'
                case 54: // '6'
                case 55: // '7'
                case 56: // '8'
                case 57: // '9'
                    stringbuffer.append(c);
                    j++;
                    continue;

                case 41: // ')'
                    s2 = stringbuffer.toString();
                    break;
                }
            } else
            {
                s2 = stringbuffer.toString();
            }
            break;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String findValue(int i, String s)
    {
        StringBuffer stringbuffer;
        String s1;
        int j;
        stringbuffer = new StringBuffer();
        s1 = s.substring(i);
        j = 0;
_L8:
        if(j >= s1.length()) goto _L2; else goto _L1
_L1:
        char c = s1.charAt(j);
        if(c != '(') goto _L4; else goto _L3
_L3:
        if(!"ERROR".equals(findAIvalue(j, s1))) goto _L2; else goto _L5
_L5:
        stringbuffer.append('(');
_L6:
        j++;
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuffer.append(c);
        if(true) goto _L6; else goto _L2
_L2:
        return stringbuffer.toString();
        if(true) goto _L8; else goto _L7
_L7:
    }

    public static ExpandedProductParsedResult parse(Result result)
    {
        BarcodeFormat barcodeformat = result.getBarcodeFormat();
        if(BarcodeFormat.RSS_EXPANDED.equals(barcodeformat)) goto _L2; else goto _L1
_L1:
        ExpandedProductParsedResult expandedproductparsedresult = null;
_L4:
        return expandedproductparsedresult;
_L2:
        String s = result.getText();
        if(s == null)
        {
            expandedproductparsedresult = null;
            continue; /* Loop/switch isn't completed */
        }
        Hashtable hashtable = new Hashtable();
        String s1 = "-";
        String s2 = "-";
        String s3 = "-";
        String s4 = "-";
        String s5 = "-";
        String s6 = "-";
        String s7 = "-";
        String s8 = "-";
        String s9 = "-";
        String s10 = "-";
        String s11 = "-";
        String s12 = "-";
        String s13 = "-";
        int i = 0;
        do
        {
            int j = s.length();
            if(i >= j)
                break;
            String s14 = findAIvalue(i, s);
            if("ERROR".equals(s14))
            {
                expandedproductparsedresult = null;
                continue; /* Loop/switch isn't completed */
            }
            int k = i + (2 + s14.length());
            String s15 = findValue(k, s);
            i = k + s15.length();
            String s17;
            String s18;
            String s19;
            String s20;
            String s21;
            String s22;
            String s24;
            String s25;
            String s27;
            String s28;
            String s30;
            String s31;
            String s32;
            String s33;
            String s34;
            String s35;
            String s36;
            String s37;
            if("00".equals(s14))
            {
                s25 = s2;
                s24 = s15;
                String s91 = s3;
                s30 = s11;
                s31 = s91;
                String s92 = s5;
                s19 = s9;
                s20 = s92;
                String s93 = s8;
                s22 = s6;
                s21 = s93;
                String s94 = s10;
                s18 = s4;
                s17 = s94;
                String s95 = s1;
                s27 = s13;
                s28 = s95;
            } else
            if("01".equals(s14))
            {
                s28 = s1;
                s27 = s15;
                String s86 = s3;
                s30 = s11;
                s31 = s86;
                String s87 = s5;
                s19 = s9;
                s20 = s87;
                String s88 = s8;
                s22 = s6;
                s21 = s88;
                String s89 = s10;
                s18 = s4;
                s17 = s89;
                String s90 = s12;
                s25 = s2;
                s24 = s90;
            } else
            if("10".equals(s14))
            {
                s31 = s3;
                s30 = s15;
                String s81 = s1;
                s27 = s13;
                s28 = s81;
                String s82 = s5;
                s19 = s9;
                s20 = s82;
                String s83 = s8;
                s22 = s6;
                s21 = s83;
                String s84 = s10;
                s18 = s4;
                s17 = s84;
                String s85 = s12;
                s25 = s2;
                s24 = s85;
            } else
            if("11".equals(s14))
            {
                s18 = s4;
                s17 = s15;
                String s76 = s3;
                s30 = s11;
                s31 = s76;
                String s77 = s5;
                s19 = s9;
                s20 = s77;
                String s78 = s8;
                s22 = s6;
                s21 = s78;
                String s79 = s2;
                s24 = s12;
                s25 = s79;
                String s80 = s1;
                s27 = s13;
                s28 = s80;
            } else
            if("13".equals(s14))
            {
                s20 = s5;
                s19 = s15;
                String s71 = s3;
                s30 = s11;
                s31 = s71;
                String s72 = s1;
                s27 = s13;
                s28 = s72;
                String s73 = s8;
                s22 = s6;
                s21 = s73;
                String s74 = s10;
                s18 = s4;
                s17 = s74;
                String s75 = s12;
                s25 = s2;
                s24 = s75;
            } else
            if("15".equals(s14))
            {
                s22 = s6;
                s21 = s15;
                String s66 = s3;
                s30 = s11;
                s31 = s66;
                String s67 = s5;
                s19 = s9;
                s20 = s67;
                String s68 = s2;
                s24 = s12;
                s25 = s68;
                String s69 = s10;
                s18 = s4;
                s17 = s69;
                String s70 = s1;
                s27 = s13;
                s28 = s70;
            } else
            if("17".equals(s14))
            {
                s7 = s15;
                String s60 = s2;
                s24 = s12;
                s25 = s60;
                String s61 = s4;
                s17 = s10;
                s18 = s61;
                String s62 = s6;
                s21 = s8;
                s22 = s62;
                String s63 = s1;
                s27 = s13;
                s28 = s63;
                String s64 = s5;
                s19 = s9;
                s20 = s64;
                String s65 = s3;
                s30 = s11;
                s31 = s65;
            } else
            if("3100".equals(s14) || "3101".equals(s14) || "3102".equals(s14) || "3103".equals(s14) || "3104".equals(s14) || "3105".equals(s14) || "3106".equals(s14) || "3107".equals(s14) || "3108".equals(s14) || "3109".equals(s14))
            {
                String s16 = s14.substring(3);
                s17 = s10;
                s18 = s16;
                s19 = s9;
                s20 = "KG";
                s21 = s8;
                s22 = s15;
                String s23 = s2;
                s24 = s12;
                s25 = s23;
                String s26 = s1;
                s27 = s13;
                s28 = s26;
                String s29 = s3;
                s30 = s11;
                s31 = s29;
            } else
            if("3200".equals(s14) || "3201".equals(s14) || "3202".equals(s14) || "3203".equals(s14) || "3204".equals(s14) || "3205".equals(s14) || "3206".equals(s14) || "3207".equals(s14) || "3208".equals(s14) || "3209".equals(s14))
            {
                String s38 = s14.substring(3);
                s17 = s10;
                s18 = s38;
                s19 = s9;
                s20 = "LB";
                s21 = s8;
                s22 = s15;
                String s39 = s2;
                s24 = s12;
                s25 = s39;
                String s40 = s1;
                s27 = s13;
                s28 = s40;
                String s41 = s3;
                s30 = s11;
                s31 = s41;
            } else
            if("3920".equals(s14) || "3921".equals(s14) || "3922".equals(s14) || "3923".equals(s14))
            {
                String s42 = s14.substring(3);
                s24 = s12;
                s25 = s42;
                s30 = s11;
                s31 = s15;
                String s43 = s5;
                s19 = s9;
                s20 = s43;
                String s44 = s8;
                s22 = s6;
                s21 = s44;
                String s45 = s10;
                s18 = s4;
                s17 = s45;
                String s46 = s1;
                s27 = s13;
                s28 = s46;
            } else
            if("3930".equals(s14) || "3931".equals(s14) || "3932".equals(s14) || "3933".equals(s14))
            {
                if(s15.length() < 4)
                {
                    expandedproductparsedresult = null;
                    continue; /* Loop/switch isn't completed */
                }
                String s47 = s15.substring(3);
                String s48 = s15.substring(0, 3);
                String s49 = s14.substring(3);
                s24 = s12;
                s25 = s49;
                s27 = s13;
                s28 = s48;
                String s50 = s5;
                s19 = s9;
                s20 = s50;
                String s51 = s8;
                s22 = s6;
                s21 = s51;
                String s52 = s10;
                s18 = s4;
                s17 = s52;
                String s53 = s11;
                s31 = s47;
                s30 = s53;
            } else
            {
                hashtable.put(s14, s15);
                String s54 = s1;
                s27 = s13;
                s28 = s54;
                String s55 = s3;
                s30 = s11;
                s31 = s55;
                String s56 = s5;
                s19 = s9;
                s20 = s56;
                String s57 = s8;
                s22 = s6;
                s21 = s57;
                String s58 = s10;
                s18 = s4;
                s17 = s58;
                String s59 = s12;
                s25 = s2;
                s24 = s59;
            }
            s32 = s28;
            s13 = s27;
            s1 = s32;
            s33 = s31;
            s11 = s30;
            s3 = s33;
            s34 = s20;
            s9 = s19;
            s5 = s34;
            s35 = s21;
            s6 = s22;
            s8 = s35;
            s36 = s17;
            s4 = s18;
            s10 = s36;
            s37 = s24;
            s2 = s25;
            s12 = s37;
        } while(true);
        expandedproductparsedresult = new ExpandedProductParsedResult(s13, s12, s11, s10, s9, s8, s7, s6, s5, s4, s3, s2, s1, hashtable);
        if(true) goto _L4; else goto _L3
_L3:
    }
}
