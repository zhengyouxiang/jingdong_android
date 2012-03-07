// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser
{

    private FieldParser()
    {
    }

    static String parseFieldsInGeneralPurpose(String s)
        throws NotFoundException
    {
        if(s.length() != 0) goto _L2; else goto _L1
_L1:
        String s4 = "";
_L4:
        return s4;
_L2:
        if(s.length() < 2)
            throw NotFoundException.getNotFoundInstance();
        String s1 = s.substring(0, 2);
        int i = 0;
        do
        {
            if(i >= TWO_DIGIT_DATA_LENGTH.length)
                break;
            if(TWO_DIGIT_DATA_LENGTH[i][0].equals(s1))
            {
                if(TWO_DIGIT_DATA_LENGTH[i][1] == VARIABLE_LENGTH)
                    s4 = processVariableAI(2, ((Integer)TWO_DIGIT_DATA_LENGTH[i][2]).intValue(), s);
                else
                    s4 = processFixedAI(2, ((Integer)TWO_DIGIT_DATA_LENGTH[i][1]).intValue(), s);
                continue; /* Loop/switch isn't completed */
            }
            i++;
        } while(true);
        if(s.length() < 3)
            throw NotFoundException.getNotFoundInstance();
        String s2 = s.substring(0, 3);
        int j = 0;
        do
        {
            if(j >= THREE_DIGIT_DATA_LENGTH.length)
                break;
            if(THREE_DIGIT_DATA_LENGTH[j][0].equals(s2))
            {
                if(THREE_DIGIT_DATA_LENGTH[j][1] == VARIABLE_LENGTH)
                    s4 = processVariableAI(3, ((Integer)THREE_DIGIT_DATA_LENGTH[j][2]).intValue(), s);
                else
                    s4 = processFixedAI(3, ((Integer)THREE_DIGIT_DATA_LENGTH[j][1]).intValue(), s);
                continue; /* Loop/switch isn't completed */
            }
            j++;
        } while(true);
        int k = 0;
        do
        {
            if(k >= THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH.length)
                break;
            if(THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[k][0].equals(s2))
            {
                if(THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[k][1] == VARIABLE_LENGTH)
                    s4 = processVariableAI(4, ((Integer)THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[k][2]).intValue(), s);
                else
                    s4 = processFixedAI(4, ((Integer)THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[k][1]).intValue(), s);
                continue; /* Loop/switch isn't completed */
            }
            k++;
        } while(true);
        if(s.length() < 4)
            throw NotFoundException.getNotFoundInstance();
        String s3 = s.substring(0, 4);
        int l = 0;
        do
        {
            if(l >= FOUR_DIGIT_DATA_LENGTH.length)
                break;
            if(FOUR_DIGIT_DATA_LENGTH[l][0].equals(s3))
            {
                if(FOUR_DIGIT_DATA_LENGTH[l][1] == VARIABLE_LENGTH)
                    s4 = processVariableAI(4, ((Integer)FOUR_DIGIT_DATA_LENGTH[l][2]).intValue(), s);
                else
                    s4 = processFixedAI(4, ((Integer)FOUR_DIGIT_DATA_LENGTH[l][1]).intValue(), s);
                continue; /* Loop/switch isn't completed */
            }
            l++;
        } while(true);
        throw NotFoundException.getNotFoundInstance();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String processFixedAI(int i, int j, String s)
        throws NotFoundException
    {
        if(s.length() < i)
            throw NotFoundException.getNotFoundInstance();
        String s1 = s.substring(0, i);
        if(s.length() < i + j)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            String s2 = s.substring(i, i + j);
            String s3 = s.substring(i + j);
            return '(' + s1 + ')' + s2 + parseFieldsInGeneralPurpose(s3);
        }
    }

    private static String processVariableAI(int i, int j, String s)
        throws NotFoundException
    {
        String s1 = s.substring(0, i);
        int k;
        String s2;
        String s3;
        if(s.length() < i + j)
            k = s.length();
        else
            k = i + j;
        s2 = s.substring(i, k);
        s3 = s.substring(k);
        return '(' + s1 + ')' + s2 + parseFieldsInGeneralPurpose(s3);
    }

    private static final Object FOUR_DIGIT_DATA_LENGTH[][];
    private static final Object THREE_DIGIT_DATA_LENGTH[][];
    private static final Object THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH[][];
    private static final Object TWO_DIGIT_DATA_LENGTH[][];
    private static final Object VARIABLE_LENGTH;

    static 
    {
        VARIABLE_LENGTH = new Object();
        Object aobj[][] = new Object[24][];
        Object aobj1[] = new Object[2];
        aobj1[0] = "00";
        aobj1[1] = new Integer(18);
        aobj[0] = aobj1;
        Object aobj2[] = new Object[2];
        aobj2[0] = "01";
        aobj2[1] = new Integer(14);
        aobj[1] = aobj2;
        Object aobj3[] = new Object[2];
        aobj3[0] = "02";
        aobj3[1] = new Integer(14);
        aobj[2] = aobj3;
        Object aobj4[] = new Object[3];
        aobj4[0] = "10";
        aobj4[1] = VARIABLE_LENGTH;
        aobj4[2] = new Integer(20);
        aobj[3] = aobj4;
        Object aobj5[] = new Object[2];
        aobj5[0] = "11";
        aobj5[1] = new Integer(6);
        aobj[4] = aobj5;
        Object aobj6[] = new Object[2];
        aobj6[0] = "12";
        aobj6[1] = new Integer(6);
        aobj[5] = aobj6;
        Object aobj7[] = new Object[2];
        aobj7[0] = "13";
        aobj7[1] = new Integer(6);
        aobj[6] = aobj7;
        Object aobj8[] = new Object[2];
        aobj8[0] = "15";
        aobj8[1] = new Integer(6);
        aobj[7] = aobj8;
        Object aobj9[] = new Object[2];
        aobj9[0] = "17";
        aobj9[1] = new Integer(6);
        aobj[8] = aobj9;
        Object aobj10[] = new Object[2];
        aobj10[0] = "20";
        aobj10[1] = new Integer(2);
        aobj[9] = aobj10;
        Object aobj11[] = new Object[3];
        aobj11[0] = "21";
        aobj11[1] = VARIABLE_LENGTH;
        aobj11[2] = new Integer(20);
        aobj[10] = aobj11;
        Object aobj12[] = new Object[3];
        aobj12[0] = "22";
        aobj12[1] = VARIABLE_LENGTH;
        aobj12[2] = new Integer(29);
        aobj[11] = aobj12;
        Object aobj13[] = new Object[3];
        aobj13[0] = "30";
        aobj13[1] = VARIABLE_LENGTH;
        aobj13[2] = new Integer(8);
        aobj[12] = aobj13;
        Object aobj14[] = new Object[3];
        aobj14[0] = "37";
        aobj14[1] = VARIABLE_LENGTH;
        aobj14[2] = new Integer(8);
        aobj[13] = aobj14;
        Object aobj15[] = new Object[3];
        aobj15[0] = "90";
        aobj15[1] = VARIABLE_LENGTH;
        aobj15[2] = new Integer(30);
        aobj[14] = aobj15;
        Object aobj16[] = new Object[3];
        aobj16[0] = "91";
        aobj16[1] = VARIABLE_LENGTH;
        aobj16[2] = new Integer(30);
        aobj[15] = aobj16;
        Object aobj17[] = new Object[3];
        aobj17[0] = "92";
        aobj17[1] = VARIABLE_LENGTH;
        aobj17[2] = new Integer(30);
        aobj[16] = aobj17;
        Object aobj18[] = new Object[3];
        aobj18[0] = "93";
        aobj18[1] = VARIABLE_LENGTH;
        aobj18[2] = new Integer(30);
        aobj[17] = aobj18;
        Object aobj19[] = new Object[3];
        aobj19[0] = "94";
        aobj19[1] = VARIABLE_LENGTH;
        aobj19[2] = new Integer(30);
        aobj[18] = aobj19;
        Object aobj20[] = new Object[3];
        aobj20[0] = "95";
        aobj20[1] = VARIABLE_LENGTH;
        aobj20[2] = new Integer(30);
        aobj[19] = aobj20;
        Object aobj21[] = new Object[3];
        aobj21[0] = "96";
        aobj21[1] = VARIABLE_LENGTH;
        aobj21[2] = new Integer(30);
        aobj[20] = aobj21;
        Object aobj22[] = new Object[3];
        aobj22[0] = "97";
        aobj22[1] = VARIABLE_LENGTH;
        aobj22[2] = new Integer(30);
        aobj[21] = aobj22;
        Object aobj23[] = new Object[3];
        aobj23[0] = "98";
        aobj23[1] = VARIABLE_LENGTH;
        aobj23[2] = new Integer(30);
        aobj[22] = aobj23;
        Object aobj24[] = new Object[3];
        aobj24[0] = "99";
        aobj24[1] = VARIABLE_LENGTH;
        aobj24[2] = new Integer(30);
        aobj[23] = aobj24;
        TWO_DIGIT_DATA_LENGTH = aobj;
        Object aobj25[][] = new Object[23][];
        Object aobj26[] = new Object[3];
        aobj26[0] = "240";
        aobj26[1] = VARIABLE_LENGTH;
        aobj26[2] = new Integer(30);
        aobj25[0] = aobj26;
        Object aobj27[] = new Object[3];
        aobj27[0] = "241";
        aobj27[1] = VARIABLE_LENGTH;
        aobj27[2] = new Integer(30);
        aobj25[1] = aobj27;
        Object aobj28[] = new Object[3];
        aobj28[0] = "242";
        aobj28[1] = VARIABLE_LENGTH;
        aobj28[2] = new Integer(6);
        aobj25[2] = aobj28;
        Object aobj29[] = new Object[3];
        aobj29[0] = "250";
        aobj29[1] = VARIABLE_LENGTH;
        aobj29[2] = new Integer(30);
        aobj25[3] = aobj29;
        Object aobj30[] = new Object[3];
        aobj30[0] = "251";
        aobj30[1] = VARIABLE_LENGTH;
        aobj30[2] = new Integer(30);
        aobj25[4] = aobj30;
        Object aobj31[] = new Object[3];
        aobj31[0] = "253";
        aobj31[1] = VARIABLE_LENGTH;
        aobj31[2] = new Integer(17);
        aobj25[5] = aobj31;
        Object aobj32[] = new Object[3];
        aobj32[0] = "254";
        aobj32[1] = VARIABLE_LENGTH;
        aobj32[2] = new Integer(20);
        aobj25[6] = aobj32;
        Object aobj33[] = new Object[3];
        aobj33[0] = "400";
        aobj33[1] = VARIABLE_LENGTH;
        aobj33[2] = new Integer(30);
        aobj25[7] = aobj33;
        Object aobj34[] = new Object[3];
        aobj34[0] = "401";
        aobj34[1] = VARIABLE_LENGTH;
        aobj34[2] = new Integer(30);
        aobj25[8] = aobj34;
        Object aobj35[] = new Object[2];
        aobj35[0] = "402";
        aobj35[1] = new Integer(17);
        aobj25[9] = aobj35;
        Object aobj36[] = new Object[3];
        aobj36[0] = "403";
        aobj36[1] = VARIABLE_LENGTH;
        aobj36[2] = new Integer(30);
        aobj25[10] = aobj36;
        Object aobj37[] = new Object[2];
        aobj37[0] = "410";
        aobj37[1] = new Integer(13);
        aobj25[11] = aobj37;
        Object aobj38[] = new Object[2];
        aobj38[0] = "411";
        aobj38[1] = new Integer(13);
        aobj25[12] = aobj38;
        Object aobj39[] = new Object[2];
        aobj39[0] = "412";
        aobj39[1] = new Integer(13);
        aobj25[13] = aobj39;
        Object aobj40[] = new Object[2];
        aobj40[0] = "413";
        aobj40[1] = new Integer(13);
        aobj25[14] = aobj40;
        Object aobj41[] = new Object[2];
        aobj41[0] = "414";
        aobj41[1] = new Integer(13);
        aobj25[15] = aobj41;
        Object aobj42[] = new Object[3];
        aobj42[0] = "420";
        aobj42[1] = VARIABLE_LENGTH;
        aobj42[2] = new Integer(20);
        aobj25[16] = aobj42;
        Object aobj43[] = new Object[3];
        aobj43[0] = "421";
        aobj43[1] = VARIABLE_LENGTH;
        aobj43[2] = new Integer(15);
        aobj25[17] = aobj43;
        Object aobj44[] = new Object[2];
        aobj44[0] = "422";
        aobj44[1] = new Integer(3);
        aobj25[18] = aobj44;
        Object aobj45[] = new Object[3];
        aobj45[0] = "423";
        aobj45[1] = VARIABLE_LENGTH;
        aobj45[2] = new Integer(15);
        aobj25[19] = aobj45;
        Object aobj46[] = new Object[2];
        aobj46[0] = "424";
        aobj46[1] = new Integer(3);
        aobj25[20] = aobj46;
        Object aobj47[] = new Object[2];
        aobj47[0] = "425";
        aobj47[1] = new Integer(3);
        aobj25[21] = aobj47;
        Object aobj48[] = new Object[2];
        aobj48[0] = "426";
        aobj48[1] = new Integer(3);
        aobj25[22] = aobj48;
        THREE_DIGIT_DATA_LENGTH = aobj25;
        Object aobj49[][] = new Object[57][];
        Object aobj50[] = new Object[2];
        aobj50[0] = "310";
        aobj50[1] = new Integer(6);
        aobj49[0] = aobj50;
        Object aobj51[] = new Object[2];
        aobj51[0] = "311";
        aobj51[1] = new Integer(6);
        aobj49[1] = aobj51;
        Object aobj52[] = new Object[2];
        aobj52[0] = "312";
        aobj52[1] = new Integer(6);
        aobj49[2] = aobj52;
        Object aobj53[] = new Object[2];
        aobj53[0] = "313";
        aobj53[1] = new Integer(6);
        aobj49[3] = aobj53;
        Object aobj54[] = new Object[2];
        aobj54[0] = "314";
        aobj54[1] = new Integer(6);
        aobj49[4] = aobj54;
        Object aobj55[] = new Object[2];
        aobj55[0] = "315";
        aobj55[1] = new Integer(6);
        aobj49[5] = aobj55;
        Object aobj56[] = new Object[2];
        aobj56[0] = "316";
        aobj56[1] = new Integer(6);
        aobj49[6] = aobj56;
        Object aobj57[] = new Object[2];
        aobj57[0] = "320";
        aobj57[1] = new Integer(6);
        aobj49[7] = aobj57;
        Object aobj58[] = new Object[2];
        aobj58[0] = "321";
        aobj58[1] = new Integer(6);
        aobj49[8] = aobj58;
        Object aobj59[] = new Object[2];
        aobj59[0] = "322";
        aobj59[1] = new Integer(6);
        aobj49[9] = aobj59;
        Object aobj60[] = new Object[2];
        aobj60[0] = "323";
        aobj60[1] = new Integer(6);
        aobj49[10] = aobj60;
        Object aobj61[] = new Object[2];
        aobj61[0] = "324";
        aobj61[1] = new Integer(6);
        aobj49[11] = aobj61;
        Object aobj62[] = new Object[2];
        aobj62[0] = "325";
        aobj62[1] = new Integer(6);
        aobj49[12] = aobj62;
        Object aobj63[] = new Object[2];
        aobj63[0] = "326";
        aobj63[1] = new Integer(6);
        aobj49[13] = aobj63;
        Object aobj64[] = new Object[2];
        aobj64[0] = "327";
        aobj64[1] = new Integer(6);
        aobj49[14] = aobj64;
        Object aobj65[] = new Object[2];
        aobj65[0] = "328";
        aobj65[1] = new Integer(6);
        aobj49[15] = aobj65;
        Object aobj66[] = new Object[2];
        aobj66[0] = "329";
        aobj66[1] = new Integer(6);
        aobj49[16] = aobj66;
        Object aobj67[] = new Object[2];
        aobj67[0] = "330";
        aobj67[1] = new Integer(6);
        aobj49[17] = aobj67;
        Object aobj68[] = new Object[2];
        aobj68[0] = "331";
        aobj68[1] = new Integer(6);
        aobj49[18] = aobj68;
        Object aobj69[] = new Object[2];
        aobj69[0] = "332";
        aobj69[1] = new Integer(6);
        aobj49[19] = aobj69;
        Object aobj70[] = new Object[2];
        aobj70[0] = "333";
        aobj70[1] = new Integer(6);
        aobj49[20] = aobj70;
        Object aobj71[] = new Object[2];
        aobj71[0] = "334";
        aobj71[1] = new Integer(6);
        aobj49[21] = aobj71;
        Object aobj72[] = new Object[2];
        aobj72[0] = "335";
        aobj72[1] = new Integer(6);
        aobj49[22] = aobj72;
        Object aobj73[] = new Object[2];
        aobj73[0] = "336";
        aobj73[1] = new Integer(6);
        aobj49[23] = aobj73;
        Object aobj74[] = new Object[2];
        aobj74[0] = "340";
        aobj74[1] = new Integer(6);
        aobj49[24] = aobj74;
        Object aobj75[] = new Object[2];
        aobj75[0] = "341";
        aobj75[1] = new Integer(6);
        aobj49[25] = aobj75;
        Object aobj76[] = new Object[2];
        aobj76[0] = "342";
        aobj76[1] = new Integer(6);
        aobj49[26] = aobj76;
        Object aobj77[] = new Object[2];
        aobj77[0] = "343";
        aobj77[1] = new Integer(6);
        aobj49[27] = aobj77;
        Object aobj78[] = new Object[2];
        aobj78[0] = "344";
        aobj78[1] = new Integer(6);
        aobj49[28] = aobj78;
        Object aobj79[] = new Object[2];
        aobj79[0] = "345";
        aobj79[1] = new Integer(6);
        aobj49[29] = aobj79;
        Object aobj80[] = new Object[2];
        aobj80[0] = "346";
        aobj80[1] = new Integer(6);
        aobj49[30] = aobj80;
        Object aobj81[] = new Object[2];
        aobj81[0] = "347";
        aobj81[1] = new Integer(6);
        aobj49[31] = aobj81;
        Object aobj82[] = new Object[2];
        aobj82[0] = "348";
        aobj82[1] = new Integer(6);
        aobj49[32] = aobj82;
        Object aobj83[] = new Object[2];
        aobj83[0] = "349";
        aobj83[1] = new Integer(6);
        aobj49[33] = aobj83;
        Object aobj84[] = new Object[2];
        aobj84[0] = "350";
        aobj84[1] = new Integer(6);
        aobj49[34] = aobj84;
        Object aobj85[] = new Object[2];
        aobj85[0] = "351";
        aobj85[1] = new Integer(6);
        aobj49[35] = aobj85;
        Object aobj86[] = new Object[2];
        aobj86[0] = "352";
        aobj86[1] = new Integer(6);
        aobj49[36] = aobj86;
        Object aobj87[] = new Object[2];
        aobj87[0] = "353";
        aobj87[1] = new Integer(6);
        aobj49[37] = aobj87;
        Object aobj88[] = new Object[2];
        aobj88[0] = "354";
        aobj88[1] = new Integer(6);
        aobj49[38] = aobj88;
        Object aobj89[] = new Object[2];
        aobj89[0] = "355";
        aobj89[1] = new Integer(6);
        aobj49[39] = aobj89;
        Object aobj90[] = new Object[2];
        aobj90[0] = "356";
        aobj90[1] = new Integer(6);
        aobj49[40] = aobj90;
        Object aobj91[] = new Object[2];
        aobj91[0] = "357";
        aobj91[1] = new Integer(6);
        aobj49[41] = aobj91;
        Object aobj92[] = new Object[2];
        aobj92[0] = "360";
        aobj92[1] = new Integer(6);
        aobj49[42] = aobj92;
        Object aobj93[] = new Object[2];
        aobj93[0] = "361";
        aobj93[1] = new Integer(6);
        aobj49[43] = aobj93;
        Object aobj94[] = new Object[2];
        aobj94[0] = "362";
        aobj94[1] = new Integer(6);
        aobj49[44] = aobj94;
        Object aobj95[] = new Object[2];
        aobj95[0] = "363";
        aobj95[1] = new Integer(6);
        aobj49[45] = aobj95;
        Object aobj96[] = new Object[2];
        aobj96[0] = "364";
        aobj96[1] = new Integer(6);
        aobj49[46] = aobj96;
        Object aobj97[] = new Object[2];
        aobj97[0] = "365";
        aobj97[1] = new Integer(6);
        aobj49[47] = aobj97;
        Object aobj98[] = new Object[2];
        aobj98[0] = "366";
        aobj98[1] = new Integer(6);
        aobj49[48] = aobj98;
        Object aobj99[] = new Object[2];
        aobj99[0] = "367";
        aobj99[1] = new Integer(6);
        aobj49[49] = aobj99;
        Object aobj100[] = new Object[2];
        aobj100[0] = "368";
        aobj100[1] = new Integer(6);
        aobj49[50] = aobj100;
        Object aobj101[] = new Object[2];
        aobj101[0] = "369";
        aobj101[1] = new Integer(6);
        aobj49[51] = aobj101;
        Object aobj102[] = new Object[3];
        aobj102[0] = "390";
        aobj102[1] = VARIABLE_LENGTH;
        aobj102[2] = new Integer(15);
        aobj49[52] = aobj102;
        Object aobj103[] = new Object[3];
        aobj103[0] = "391";
        aobj103[1] = VARIABLE_LENGTH;
        aobj103[2] = new Integer(18);
        aobj49[53] = aobj103;
        Object aobj104[] = new Object[3];
        aobj104[0] = "392";
        aobj104[1] = VARIABLE_LENGTH;
        aobj104[2] = new Integer(15);
        aobj49[54] = aobj104;
        Object aobj105[] = new Object[3];
        aobj105[0] = "393";
        aobj105[1] = VARIABLE_LENGTH;
        aobj105[2] = new Integer(18);
        aobj49[55] = aobj105;
        Object aobj106[] = new Object[3];
        aobj106[0] = "703";
        aobj106[1] = VARIABLE_LENGTH;
        aobj106[2] = new Integer(30);
        aobj49[56] = aobj106;
        THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = aobj49;
        Object aobj107[][] = new Object[17][];
        Object aobj108[] = new Object[2];
        aobj108[0] = "7001";
        aobj108[1] = new Integer(13);
        aobj107[0] = aobj108;
        Object aobj109[] = new Object[3];
        aobj109[0] = "7002";
        aobj109[1] = VARIABLE_LENGTH;
        aobj109[2] = new Integer(30);
        aobj107[1] = aobj109;
        Object aobj110[] = new Object[2];
        aobj110[0] = "7003";
        aobj110[1] = new Integer(10);
        aobj107[2] = aobj110;
        Object aobj111[] = new Object[2];
        aobj111[0] = "8001";
        aobj111[1] = new Integer(14);
        aobj107[3] = aobj111;
        Object aobj112[] = new Object[3];
        aobj112[0] = "8002";
        aobj112[1] = VARIABLE_LENGTH;
        aobj112[2] = new Integer(20);
        aobj107[4] = aobj112;
        Object aobj113[] = new Object[3];
        aobj113[0] = "8003";
        aobj113[1] = VARIABLE_LENGTH;
        aobj113[2] = new Integer(30);
        aobj107[5] = aobj113;
        Object aobj114[] = new Object[3];
        aobj114[0] = "8004";
        aobj114[1] = VARIABLE_LENGTH;
        aobj114[2] = new Integer(30);
        aobj107[6] = aobj114;
        Object aobj115[] = new Object[2];
        aobj115[0] = "8005";
        aobj115[1] = new Integer(6);
        aobj107[7] = aobj115;
        Object aobj116[] = new Object[2];
        aobj116[0] = "8006";
        aobj116[1] = new Integer(18);
        aobj107[8] = aobj116;
        Object aobj117[] = new Object[3];
        aobj117[0] = "8007";
        aobj117[1] = VARIABLE_LENGTH;
        aobj117[2] = new Integer(30);
        aobj107[9] = aobj117;
        Object aobj118[] = new Object[3];
        aobj118[0] = "8008";
        aobj118[1] = VARIABLE_LENGTH;
        aobj118[2] = new Integer(12);
        aobj107[10] = aobj118;
        Object aobj119[] = new Object[2];
        aobj119[0] = "8018";
        aobj119[1] = new Integer(18);
        aobj107[11] = aobj119;
        Object aobj120[] = new Object[3];
        aobj120[0] = "8020";
        aobj120[1] = VARIABLE_LENGTH;
        aobj120[2] = new Integer(25);
        aobj107[12] = aobj120;
        Object aobj121[] = new Object[2];
        aobj121[0] = "8100";
        aobj121[1] = new Integer(6);
        aobj107[13] = aobj121;
        Object aobj122[] = new Object[2];
        aobj122[0] = "8101";
        aobj122[1] = new Integer(10);
        aobj107[14] = aobj122;
        Object aobj123[] = new Object[2];
        aobj123[0] = "8102";
        aobj123[1] = new Integer(2);
        aobj107[15] = aobj123;
        Object aobj124[] = new Object[3];
        aobj124[0] = "8110";
        aobj124[1] = VARIABLE_LENGTH;
        aobj124[2] = new Integer(30);
        aobj107[16] = aobj124;
        FOUR_DIGIT_DATA_LENGTH = aobj107;
    }
}
