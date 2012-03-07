// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import java.util.Vector;

final class EANManufacturerOrgSupport
{

    EANManufacturerOrgSupport()
    {
    }

    private void add(int ai[], String s)
    {
        ranges.addElement(ai);
        countryIdentifiers.addElement(s);
    }

    /**
     * @deprecated Method initIfNeeded is deprecated
     */

    private void initIfNeeded()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = ranges.isEmpty();
        if(flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int ai[] = new int[2];
        ai[0] = 0;
        ai[1] = 19;
        add(ai, "US/CA");
        int ai1[] = new int[2];
        ai1[0] = 30;
        ai1[1] = 39;
        add(ai1, "US");
        int ai2[] = new int[2];
        ai2[0] = 60;
        ai2[1] = 139;
        add(ai2, "US/CA");
        int ai3[] = new int[2];
        ai3[0] = 300;
        ai3[1] = 379;
        add(ai3, "FR");
        int ai4[] = new int[1];
        ai4[0] = 380;
        add(ai4, "BG");
        int ai5[] = new int[1];
        ai5[0] = 383;
        add(ai5, "SI");
        int ai6[] = new int[1];
        ai6[0] = 385;
        add(ai6, "HR");
        int ai7[] = new int[1];
        ai7[0] = 387;
        add(ai7, "BA");
        int ai8[] = new int[2];
        ai8[0] = 400;
        ai8[1] = 440;
        add(ai8, "DE");
        int ai9[] = new int[2];
        ai9[0] = 450;
        ai9[1] = 459;
        add(ai9, "JP");
        int ai10[] = new int[2];
        ai10[0] = 460;
        ai10[1] = 469;
        add(ai10, "RU");
        int ai11[] = new int[1];
        ai11[0] = 471;
        add(ai11, "TW");
        int ai12[] = new int[1];
        ai12[0] = 474;
        add(ai12, "EE");
        int ai13[] = new int[1];
        ai13[0] = 475;
        add(ai13, "LV");
        int ai14[] = new int[1];
        ai14[0] = 476;
        add(ai14, "AZ");
        int ai15[] = new int[1];
        ai15[0] = 477;
        add(ai15, "LT");
        int ai16[] = new int[1];
        ai16[0] = 478;
        add(ai16, "UZ");
        int ai17[] = new int[1];
        ai17[0] = 479;
        add(ai17, "LK");
        int ai18[] = new int[1];
        ai18[0] = 480;
        add(ai18, "PH");
        int ai19[] = new int[1];
        ai19[0] = 481;
        add(ai19, "BY");
        int ai20[] = new int[1];
        ai20[0] = 482;
        add(ai20, "UA");
        int ai21[] = new int[1];
        ai21[0] = 484;
        add(ai21, "MD");
        int ai22[] = new int[1];
        ai22[0] = 485;
        add(ai22, "AM");
        int ai23[] = new int[1];
        ai23[0] = 486;
        add(ai23, "GE");
        int ai24[] = new int[1];
        ai24[0] = 487;
        add(ai24, "KZ");
        int ai25[] = new int[1];
        ai25[0] = 489;
        add(ai25, "HK");
        int ai26[] = new int[2];
        ai26[0] = 490;
        ai26[1] = 499;
        add(ai26, "JP");
        int ai27[] = new int[2];
        ai27[0] = 500;
        ai27[1] = 509;
        add(ai27, "GB");
        int ai28[] = new int[1];
        ai28[0] = 520;
        add(ai28, "GR");
        int ai29[] = new int[1];
        ai29[0] = 528;
        add(ai29, "LB");
        int ai30[] = new int[1];
        ai30[0] = 529;
        add(ai30, "CY");
        int ai31[] = new int[1];
        ai31[0] = 531;
        add(ai31, "MK");
        int ai32[] = new int[1];
        ai32[0] = 535;
        add(ai32, "MT");
        int ai33[] = new int[1];
        ai33[0] = 539;
        add(ai33, "IE");
        int ai34[] = new int[2];
        ai34[0] = 540;
        ai34[1] = 549;
        add(ai34, "BE/LU");
        int ai35[] = new int[1];
        ai35[0] = 560;
        add(ai35, "PT");
        int ai36[] = new int[1];
        ai36[0] = 569;
        add(ai36, "IS");
        int ai37[] = new int[2];
        ai37[0] = 570;
        ai37[1] = 579;
        add(ai37, "DK");
        int ai38[] = new int[1];
        ai38[0] = 590;
        add(ai38, "PL");
        int ai39[] = new int[1];
        ai39[0] = 594;
        add(ai39, "RO");
        int ai40[] = new int[1];
        ai40[0] = 599;
        add(ai40, "HU");
        int ai41[] = new int[2];
        ai41[0] = 600;
        ai41[1] = 601;
        add(ai41, "ZA");
        int ai42[] = new int[1];
        ai42[0] = 603;
        add(ai42, "GH");
        int ai43[] = new int[1];
        ai43[0] = 608;
        add(ai43, "BH");
        int ai44[] = new int[1];
        ai44[0] = 609;
        add(ai44, "MU");
        int ai45[] = new int[1];
        ai45[0] = 611;
        add(ai45, "MA");
        int ai46[] = new int[1];
        ai46[0] = 613;
        add(ai46, "DZ");
        int ai47[] = new int[1];
        ai47[0] = 616;
        add(ai47, "KE");
        int ai48[] = new int[1];
        ai48[0] = 618;
        add(ai48, "CI");
        int ai49[] = new int[1];
        ai49[0] = 619;
        add(ai49, "TN");
        int ai50[] = new int[1];
        ai50[0] = 621;
        add(ai50, "SY");
        int ai51[] = new int[1];
        ai51[0] = 622;
        add(ai51, "EG");
        int ai52[] = new int[1];
        ai52[0] = 624;
        add(ai52, "LY");
        int ai53[] = new int[1];
        ai53[0] = 625;
        add(ai53, "JO");
        int ai54[] = new int[1];
        ai54[0] = 626;
        add(ai54, "IR");
        int ai55[] = new int[1];
        ai55[0] = 627;
        add(ai55, "KW");
        int ai56[] = new int[1];
        ai56[0] = 628;
        add(ai56, "SA");
        int ai57[] = new int[1];
        ai57[0] = 629;
        add(ai57, "AE");
        int ai58[] = new int[2];
        ai58[0] = 640;
        ai58[1] = 649;
        add(ai58, "FI");
        int ai59[] = new int[2];
        ai59[0] = 690;
        ai59[1] = 695;
        add(ai59, "CN");
        int ai60[] = new int[2];
        ai60[0] = 700;
        ai60[1] = 709;
        add(ai60, "NO");
        int ai61[] = new int[1];
        ai61[0] = 729;
        add(ai61, "IL");
        int ai62[] = new int[2];
        ai62[0] = 730;
        ai62[1] = 739;
        add(ai62, "SE");
        int ai63[] = new int[1];
        ai63[0] = 740;
        add(ai63, "GT");
        int ai64[] = new int[1];
        ai64[0] = 741;
        add(ai64, "SV");
        int ai65[] = new int[1];
        ai65[0] = 742;
        add(ai65, "HN");
        int ai66[] = new int[1];
        ai66[0] = 743;
        add(ai66, "NI");
        int ai67[] = new int[1];
        ai67[0] = 744;
        add(ai67, "CR");
        int ai68[] = new int[1];
        ai68[0] = 745;
        add(ai68, "PA");
        int ai69[] = new int[1];
        ai69[0] = 746;
        add(ai69, "DO");
        int ai70[] = new int[1];
        ai70[0] = 750;
        add(ai70, "MX");
        int ai71[] = new int[2];
        ai71[0] = 754;
        ai71[1] = 755;
        add(ai71, "CA");
        int ai72[] = new int[1];
        ai72[0] = 759;
        add(ai72, "VE");
        int ai73[] = new int[2];
        ai73[0] = 760;
        ai73[1] = 769;
        add(ai73, "CH");
        int ai74[] = new int[1];
        ai74[0] = 770;
        add(ai74, "CO");
        int ai75[] = new int[1];
        ai75[0] = 773;
        add(ai75, "UY");
        int ai76[] = new int[1];
        ai76[0] = 775;
        add(ai76, "PE");
        int ai77[] = new int[1];
        ai77[0] = 777;
        add(ai77, "BO");
        int ai78[] = new int[1];
        ai78[0] = 779;
        add(ai78, "AR");
        int ai79[] = new int[1];
        ai79[0] = 780;
        add(ai79, "CL");
        int ai80[] = new int[1];
        ai80[0] = 784;
        add(ai80, "PY");
        int ai81[] = new int[1];
        ai81[0] = 785;
        add(ai81, "PE");
        int ai82[] = new int[1];
        ai82[0] = 786;
        add(ai82, "EC");
        int ai83[] = new int[2];
        ai83[0] = 789;
        ai83[1] = 790;
        add(ai83, "BR");
        int ai84[] = new int[2];
        ai84[0] = 800;
        ai84[1] = 839;
        add(ai84, "IT");
        int ai85[] = new int[2];
        ai85[0] = 840;
        ai85[1] = 849;
        add(ai85, "ES");
        int ai86[] = new int[1];
        ai86[0] = 850;
        add(ai86, "CU");
        int ai87[] = new int[1];
        ai87[0] = 858;
        add(ai87, "SK");
        int ai88[] = new int[1];
        ai88[0] = 859;
        add(ai88, "CZ");
        int ai89[] = new int[1];
        ai89[0] = 860;
        add(ai89, "YU");
        int ai90[] = new int[1];
        ai90[0] = 865;
        add(ai90, "MN");
        int ai91[] = new int[1];
        ai91[0] = 867;
        add(ai91, "KP");
        int ai92[] = new int[2];
        ai92[0] = 868;
        ai92[1] = 869;
        add(ai92, "TR");
        int ai93[] = new int[2];
        ai93[0] = 870;
        ai93[1] = 879;
        add(ai93, "NL");
        int ai94[] = new int[1];
        ai94[0] = 880;
        add(ai94, "KR");
        int ai95[] = new int[1];
        ai95[0] = 885;
        add(ai95, "TH");
        int ai96[] = new int[1];
        ai96[0] = 888;
        add(ai96, "SG");
        int ai97[] = new int[1];
        ai97[0] = 890;
        add(ai97, "IN");
        int ai98[] = new int[1];
        ai98[0] = 893;
        add(ai98, "VN");
        int ai99[] = new int[1];
        ai99[0] = 896;
        add(ai99, "PK");
        int ai100[] = new int[1];
        ai100[0] = 899;
        add(ai100, "ID");
        int ai101[] = new int[2];
        ai101[0] = 900;
        ai101[1] = 919;
        add(ai101, "AT");
        int ai102[] = new int[2];
        ai102[0] = 930;
        ai102[1] = 939;
        add(ai102, "AU");
        int ai103[] = new int[2];
        ai103[0] = 940;
        ai103[1] = 949;
        add(ai103, "AZ");
        int ai104[] = new int[1];
        ai104[0] = 955;
        add(ai104, "MY");
        int ai105[] = new int[1];
        ai105[0] = 958;
        add(ai105, "MO");
        if(true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    String lookupCountryIdentifier(String s)
    {
        int i;
        int j;
        int k;
        initIfNeeded();
        i = Integer.parseInt(s.substring(0, 3));
        j = ranges.size();
        k = 0;
_L1:
        String s1;
        if(k >= j)
            break MISSING_BLOCK_LABEL_115;
        int ai[] = (int[])(int[])ranges.elementAt(k);
        int l = ai[0];
        if(i < l)
        {
            s1 = null;
        } else
        {
label0:
            {
                int i1;
                if(ai.length == 1)
                    i1 = l;
                else
                    i1 = ai[1];
                if(i > i1)
                    break label0;
                s1 = (String)countryIdentifiers.elementAt(k);
            }
        }
_L2:
        return s1;
        k++;
          goto _L1
        s1 = null;
          goto _L2
    }

    private final Vector countryIdentifiers = new Vector();
    private final Vector ranges = new Vector();
}
