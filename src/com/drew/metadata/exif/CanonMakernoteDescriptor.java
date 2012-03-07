// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class CanonMakernoteDescriptor extends TagDescriptor
{

    public CanonMakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getAfPointSelectedDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49427)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49427);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 12288: 
            s = "None (MF)";
            break;

        case 12289: 
            s = "Auto selected";
            break;

        case 12290: 
            s = "Right";
            break;

        case 12291: 
            s = "Centre";
            break;

        case 12292: 
            s = "Left";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getAfPointUsedDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(49678))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(49678);
            if((i & 7) == 0)
                s = "Right";
            else
            if((i & 7) == 1)
                s = "Centre";
            else
            if((i & 7) == 2)
                s = "Left";
            else
                s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
        }
        return s;
    }

    public String getAutoExposureBrackettingSequenceAndAutoCancellationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49927)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49927);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "0,-,+ / Enabled";
            break;

        case 1: // '\001'
            s = "0,-,+ / Disabled";
            break;

        case 2: // '\002'
            s = "-,0,+ / Enabled";
            break;

        case 3: // '\003'
            s = "-,0,+ / Disabled";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getAutoFocusAssistLightDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49925)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49925);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "On (Auto)";
            break;

        case 1: // '\001'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getContinuousDriveModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49413)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49413);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            if(_directory.getInt(49410) == 0)
                s = "Single shot";
            else
                s = "Single shot with self-timer";
            break;

        case 1: // '\001'
            s = "Continuous";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getContrastDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49421)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49421);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 65535: 
            s = "Low";
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "High";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 39: default 324
    //                   49409: 367
    //                   49410: 375
    //                   49411: 359
    //                   49412: 383
    //                   49413: 391
    //                   49415: 399
    //                   49418: 407
    //                   49419: 415
    //                   49420: 351
    //                   49421: 423
    //                   49422: 431
    //                   49423: 439
    //                   49424: 447
    //                   49425: 455
    //                   49426: 343
    //                   49427: 463
    //                   49428: 471
    //                   49431: 479
    //                   49432: 487
    //                   49433: 495
    //                   49436: 335
    //                   49437: 503
    //                   49440: 511
    //                   49671: 519
    //                   49678: 527
    //                   49679: 535
    //                   49921: 543
    //                   49922: 551
    //                   49923: 559
    //                   49924: 567
    //                   49925: 575
    //                   49926: 583
    //                   49927: 591
    //                   49928: 599
    //                   49929: 607
    //                   49930: 615
    //                   49931: 623
    //                   49932: 631
    //                   49933: 639;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40
_L1:
        String s = _directory.getString(i);
_L42:
        return s;
_L22:
        s = getFlashActivityDescription();
        continue; /* Loop/switch isn't completed */
_L16:
        s = getFocusTypeDescription();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getDigitalZoomDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getQualityDescription();
        continue; /* Loop/switch isn't completed */
_L2:
        s = getMacroModeDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getSelfTimerDelayDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getFlashModeDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getContinuousDriveModeDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getFocusMode1Description();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getImageSizeDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getEasyShootingModeDescription();
        continue; /* Loop/switch isn't completed */
_L11:
        s = getContrastDescription();
        continue; /* Loop/switch isn't completed */
_L12:
        s = getSaturationDescription();
        continue; /* Loop/switch isn't completed */
_L13:
        s = getSharpnessDescription();
        continue; /* Loop/switch isn't completed */
_L14:
        s = getIsoDescription();
        continue; /* Loop/switch isn't completed */
_L15:
        s = getMeteringModeDescription();
        continue; /* Loop/switch isn't completed */
_L17:
        s = getAfPointSelectedDescription();
        continue; /* Loop/switch isn't completed */
_L18:
        s = getExposureModeDescription();
        continue; /* Loop/switch isn't completed */
_L19:
        s = getLongFocalLengthDescription();
        continue; /* Loop/switch isn't completed */
_L20:
        s = getShortFocalLengthDescription();
        continue; /* Loop/switch isn't completed */
_L21:
        s = getFocalUnitsPerMillimetreDescription();
        continue; /* Loop/switch isn't completed */
_L23:
        s = getFlashDetailsDescription();
        continue; /* Loop/switch isn't completed */
_L24:
        s = getFocusMode2Description();
        continue; /* Loop/switch isn't completed */
_L25:
        s = getWhiteBalanceDescription();
        continue; /* Loop/switch isn't completed */
_L26:
        s = getAfPointUsedDescription();
        continue; /* Loop/switch isn't completed */
_L27:
        s = getFlashBiasDescription();
        continue; /* Loop/switch isn't completed */
_L28:
        s = getLongExposureNoiseReductionDescription();
        continue; /* Loop/switch isn't completed */
_L29:
        s = getShutterAutoExposureLockButtonDescription();
        continue; /* Loop/switch isn't completed */
_L30:
        s = getMirrorLockupDescription();
        continue; /* Loop/switch isn't completed */
_L31:
        s = getTvAndAvExposureLevelDescription();
        continue; /* Loop/switch isn't completed */
_L32:
        s = getAutoFocusAssistLightDescription();
        continue; /* Loop/switch isn't completed */
_L33:
        s = getShutterSpeedInAvModeDescription();
        continue; /* Loop/switch isn't completed */
_L34:
        s = getAutoExposureBrackettingSequenceAndAutoCancellationDescription();
        continue; /* Loop/switch isn't completed */
_L35:
        s = getShutterCurtainSyncDescription();
        continue; /* Loop/switch isn't completed */
_L36:
        s = getLensAutoFocusStopButtonDescription();
        continue; /* Loop/switch isn't completed */
_L37:
        s = getFillFlashReductionDescription();
        continue; /* Loop/switch isn't completed */
_L38:
        s = getMenuButtonReturnPositionDescription();
        continue; /* Loop/switch isn't completed */
_L39:
        s = getSetButtonFunctionWhenShootingDescription();
        continue; /* Loop/switch isn't completed */
_L40:
        s = getSensorCleaningDescription();
        if(true) goto _L42; else goto _L41
_L41:
    }

    public String getDigitalZoomDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49420)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49420);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "No digital zoom";
            break;

        case 1: // '\001'
            s = "2x";
            break;

        case 2: // '\002'
            s = "4x";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getEasyShootingModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49419)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49419);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Full auto";
            break;

        case 1: // '\001'
            s = "Manual";
            break;

        case 2: // '\002'
            s = "Landscape";
            break;

        case 3: // '\003'
            s = "Fast shutter";
            break;

        case 4: // '\004'
            s = "Slow shutter";
            break;

        case 5: // '\005'
            s = "Night";
            break;

        case 6: // '\006'
            s = "B&W";
            break;

        case 7: // '\007'
            s = "Sepia";
            break;

        case 8: // '\b'
            s = "Portrait";
            break;

        case 9: // '\t'
            s = "Sports";
            break;

        case 10: // '\n'
            s = "Macro / Closeup";
            break;

        case 11: // '\013'
            s = "Pan focus";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getExposureModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49428)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49428);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Easy shooting";
            break;

        case 1: // '\001'
            s = "Program";
            break;

        case 2: // '\002'
            s = "Tv-priority";
            break;

        case 3: // '\003'
            s = "Av-priority";
            break;

        case 4: // '\004'
            s = "Manual";
            break;

        case 5: // '\005'
            s = "A-DEP";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFillFlashReductionDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49930)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49930);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Enabled";
            break;

        case 1: // '\001'
            s = "Disabled";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashActivityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49436)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49436);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Flash did not fire";
            break;

        case 1: // '\001'
            s = "Flash fired";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashBiasDescription()
        throws MetadataException
    {
        String s1;
        if(!_directory.containsTag(49679))
        {
            s1 = null;
        } else
        {
            int i = _directory.getInt(49679);
            boolean flag = false;
            if(i > 61440)
            {
                flag = true;
                i = 1 + (65535 - i);
            }
            String s;
            if(flag)
                s = "-";
            else
                s = "";
            s1 = (new StringBuilder(String.valueOf(s))).append(Float.toString((float)i / 32F)).append(" EV").toString();
        }
        return s1;
    }

    public String getFlashDetailsDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(49437))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(49437);
            if((1 & i << 14) > 0)
                s = "External E-TTL";
            else
            if((1 & i << 13) > 0)
                s = "Internal flash";
            else
            if((1 & i << 11) > 0)
                s = "FP sync used";
            else
            if((1 & i << 4) > 0)
                s = "FP sync enabled";
            else
                s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
        }
        return s;
    }

    public String getFlashModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49412)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49412);
        switch(i)
        {
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "No flash fired";
            break;

        case 1: // '\001'
            s = "Auto";
            break;

        case 2: // '\002'
            s = "On";
            break;

        case 3: // '\003'
            s = "Red-eye reduction";
            break;

        case 4: // '\004'
            s = "Slow-synchro";
            break;

        case 5: // '\005'
            s = "Auto and red-eye reduction";
            break;

        case 6: // '\006'
            s = "On and red-eye reduction";
            break;

        case 16: // '\020'
            s = "Extenal flash";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocalUnitsPerMillimetreDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(49433))
        {
            s = "";
        } else
        {
            int i = _directory.getInt(49433);
            if(i != 0)
                s = Integer.toString(i);
            else
                s = "";
        }
        return s;
    }

    public String getFocusMode1Description()
        throws MetadataException
    {
        if(_directory.containsTag(49415)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49415);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "One-shot";
            break;

        case 1: // '\001'
            s = "AI Servo";
            break;

        case 2: // '\002'
            s = "AI Focus";
            break;

        case 3: // '\003'
            s = "Manual Focus";
            break;

        case 4: // '\004'
            s = "Single";
            break;

        case 5: // '\005'
            s = "Continuous";
            break;

        case 6: // '\006'
            s = "Manual Focus";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocusMode2Description()
        throws MetadataException
    {
        if(_directory.containsTag(49440)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49440);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Single";
            break;

        case 1: // '\001'
            s = "Continuous";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocusTypeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49426)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49426);
        switch(i)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Manual";
            break;

        case 1: // '\001'
            s = "Auto";
            break;

        case 3: // '\003'
            s = "Close-up (Macro)";
            break;

        case 8: // '\b'
            s = "Locked (Pan Mode)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getImageSizeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49418)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49418);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Large";
            break;

        case 1: // '\001'
            s = "Medium";
            break;

        case 2: // '\002'
            s = "Small";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getIsoDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49424)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49424);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Not specified (see ISOSpeedRatings tag)";
            break;

        case 15: // '\017'
            s = "Auto";
            break;

        case 16: // '\020'
            s = "50";
            break;

        case 17: // '\021'
            s = "100";
            break;

        case 18: // '\022'
            s = "200";
            break;

        case 19: // '\023'
            s = "400";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getLensAutoFocusStopButtonDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49929)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49929);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "AF stop";
            break;

        case 1: // '\001'
            s = "Operate AF";
            break;

        case 2: // '\002'
            s = "Lock AE and start timer";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getLongExposureNoiseReductionDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49921)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49921);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;

        case 1: // '\001'
            s = "On";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getLongFocalLengthDescription()
        throws MetadataException
    {
        String s1;
        if(!_directory.containsTag(49431))
        {
            s1 = null;
        } else
        {
            int i = _directory.getInt(49431);
            String s = getFocalUnitsPerMillimetreDescription();
            s1 = (new StringBuilder(String.valueOf(Integer.toString(i)))).append(" ").append(s).toString();
        }
        return s1;
    }

    public String getMacroModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49409)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49409);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Macro";
            break;

        case 2: // '\002'
            s = "Normal";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getMenuButtonReturnPositionDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49931)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49931);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Top";
            break;

        case 1: // '\001'
            s = "Previous (volatile)";
            break;

        case 2: // '\002'
            s = "Previous";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getMeteringModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49425)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49425);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 3: // '\003'
            s = "Evaluative";
            break;

        case 4: // '\004'
            s = "Partial";
            break;

        case 5: // '\005'
            s = "Centre weighted";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getMirrorLockupDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49923)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49923);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Disabled";
            break;

        case 1: // '\001'
            s = "Enabled";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getQualityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49411)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49411);
        switch(i)
        {
        case 4: // '\004'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 2: // '\002'
            s = "Normal";
            break;

        case 3: // '\003'
            s = "Fine";
            break;

        case 5: // '\005'
            s = "Superfine";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSaturationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49422)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49422);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 65535: 
            s = "Low";
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "High";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSelfTimerDelayDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(49410))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(49410);
            if(i == 0)
                s = "Self timer not used";
            else
                s = (new StringBuilder(String.valueOf(Double.toString(0.10000000000000001D * (double)i)))).append(" sec").toString();
        }
        return s;
    }

    public String getSensorCleaningDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49933)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49933);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Disabled";
            break;

        case 1: // '\001'
            s = "Enabled";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSetButtonFunctionWhenShootingDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49932)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49932);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Not Assigned";
            break;

        case 1: // '\001'
            s = "Change Quality";
            break;

        case 2: // '\002'
            s = "Change ISO Speed";
            break;

        case 3: // '\003'
            s = "Select Parameters";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSharpnessDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49423)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49423);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 65535: 
            s = "Low";
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "High";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getShortFocalLengthDescription()
        throws MetadataException
    {
        String s1;
        if(!_directory.containsTag(49432))
        {
            s1 = null;
        } else
        {
            int i = _directory.getInt(49432);
            String s = getFocalUnitsPerMillimetreDescription();
            s1 = (new StringBuilder(String.valueOf(Integer.toString(i)))).append(" ").append(s).toString();
        }
        return s1;
    }

    public String getShutterAutoExposureLockButtonDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49922)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49922);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "AF/AE lock";
            break;

        case 1: // '\001'
            s = "AE lock/AF";
            break;

        case 2: // '\002'
            s = "AF/AF lock";
            break;

        case 3: // '\003'
            s = "AE+release/AE+AF";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getShutterCurtainSyncDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49928)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49928);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "1st Curtain Sync";
            break;

        case 1: // '\001'
            s = "2nd Curtain Sync";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getShutterSpeedInAvModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49926)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49926);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Automatic";
            break;

        case 1: // '\001'
            s = "1/200 (fixed)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getTvAndAvExposureLevelDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49924)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49924);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "1/2 stop";
            break;

        case 1: // '\001'
            s = "1/3 stop";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWhiteBalanceDescription()
        throws MetadataException
    {
        if(_directory.containsTag(49671)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(49671);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto";
            break;

        case 1: // '\001'
            s = "Sunny";
            break;

        case 2: // '\002'
            s = "Cloudy";
            break;

        case 3: // '\003'
            s = "Tungsten";
            break;

        case 4: // '\004'
            s = "Flourescent";
            break;

        case 5: // '\005'
            s = "Flash";
            break;

        case 6: // '\006'
            s = "Custom";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }
}
