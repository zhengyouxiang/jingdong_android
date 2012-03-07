// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.*;

public class CasioType2MakernoteDescriptor extends TagDescriptor
{

    public CasioType2MakernoteDescriptor(Directory directory)
    {
        super(directory);
    }

    public String getBestShotModeDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(12295))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(12295);
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
        }
        return s;
    }

    public String getCasioPreviewThumbnailDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(8192))
        {
            s = null;
        } else
        {
            byte abyte0[] = _directory.getByteArray(8192);
            s = (new StringBuilder("<")).append(abyte0.length).append(" bytes of image data>").toString();
        }
        return s;
    }

    public String getCcdIsoSensitivityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12308)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12308);
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

    public String getColourModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12309)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12309);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getContrastDescription()
        throws MetadataException
    {
        if(_directory.containsTag(32)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(32);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "-1";
            break;

        case 1: // '\001'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "+1";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 28: default 236
    //                   2: 247
    //                   3: 255
    //                   4: 263
    //                   8: 271
    //                   9: 279
    //                   13: 287
    //                   20: 295
    //                   25: 303
    //                   29: 311
    //                   31: 319
    //                   32: 327
    //                   33: 335
    //                   3584: 343
    //                   8192: 351
    //                   8209: 359
    //                   8210: 367
    //                   8226: 375
    //                   8244: 383
    //                   12288: 391
    //                   12289: 399
    //                   12290: 407
    //                   12291: 415
    //                   12294: 423
    //                   12295: 431
    //                   12308: 439
    //                   12309: 447
    //                   12310: 455
    //                   12311: 463;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29
_L1:
        String s = _directory.getString(i);
_L31:
        return s;
_L2:
        s = getThumbnailDimensionsDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getThumbnailSizeDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getThumbnailOffsetDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getQualityModeDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getImageSizeDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getFocusMode1Description();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getIsoSensitivityDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getWhiteBalance1Description();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getFocalLengthDescription();
        continue; /* Loop/switch isn't completed */
_L11:
        s = getSaturationDescription();
        continue; /* Loop/switch isn't completed */
_L12:
        s = getContrastDescription();
        continue; /* Loop/switch isn't completed */
_L13:
        s = getSharpnessDescription();
        continue; /* Loop/switch isn't completed */
_L14:
        s = getPrintImageMatchingInfoDescription();
        continue; /* Loop/switch isn't completed */
_L15:
        s = getCasioPreviewThumbnailDescription();
        continue; /* Loop/switch isn't completed */
_L16:
        s = getWhiteBalanceBiasDescription();
        continue; /* Loop/switch isn't completed */
_L17:
        s = getWhiteBalance2Description();
        continue; /* Loop/switch isn't completed */
_L18:
        s = getObjectDistanceDescription();
        continue; /* Loop/switch isn't completed */
_L19:
        s = getFlashDistanceDescription();
        continue; /* Loop/switch isn't completed */
_L20:
        s = getRecordModeDescription();
        continue; /* Loop/switch isn't completed */
_L21:
        s = getSelfTimerDescription();
        continue; /* Loop/switch isn't completed */
_L22:
        s = getQualityDescription();
        continue; /* Loop/switch isn't completed */
_L23:
        s = getFocusMode2Description();
        continue; /* Loop/switch isn't completed */
_L24:
        s = getTimeZoneDescription();
        continue; /* Loop/switch isn't completed */
_L25:
        s = getBestShotModeDescription();
        continue; /* Loop/switch isn't completed */
_L26:
        s = getCcdIsoSensitivityDescription();
        continue; /* Loop/switch isn't completed */
_L27:
        s = getColourModeDescription();
        continue; /* Loop/switch isn't completed */
_L28:
        s = getEnhancementDescription();
        continue; /* Loop/switch isn't completed */
_L29:
        s = getFilterDescription();
        if(true) goto _L31; else goto _L30
_L30:
    }

    public String getEnhancementDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12310)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12310);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFilterDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12311)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12311);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashDistanceDescription()
        throws MetadataException
    {
        if(_directory.containsTag(8244)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(8244);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocalLengthDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(29))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(Double.toString(_directory.getDouble(29) / 10D)))).append(" mm").toString();
        return s;
    }

    public String getFocusMode1Description()
        throws MetadataException
    {
        if(_directory.containsTag(13)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(13);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal";
            break;

        case 1: // '\001'
            s = "Macro";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocusMode2Description()
        throws MetadataException
    {
        if(_directory.containsTag(12291)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12291);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Fixation";
            break;

        case 6: // '\006'
            s = "Multi-Area Focus";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getImageSizeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(9)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(9);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "640 x 480 pixels";
            break;

        case 4: // '\004'
            s = "1600 x 1200 pixels";
            break;

        case 5: // '\005'
            s = "2048 x 1536 pixels";
            break;

        case 20: // '\024'
            s = "2288 x 1712 pixels";
            break;

        case 21: // '\025'
            s = "2592 x 1944 pixels";
            break;

        case 22: // '\026'
            s = "2304 x 1728 pixels";
            break;

        case 36: // '$'
            s = "3008 x 2008 pixels";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getIsoSensitivityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(20)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(20);
        switch(i)
        {
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 3: // '\003'
            s = "50";
            break;

        case 4: // '\004'
            s = "64";
            break;

        case 6: // '\006'
            s = "100";
            break;

        case 9: // '\t'
            s = "200";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getObjectDistanceDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(8226))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(Integer.toString(_directory.getInt(8226))))).append(" mm").toString();
        return s;
    }

    public String getPrintImageMatchingInfoDescription()
    {
        String s;
        if(!_directory.containsTag(3584))
            s = null;
        else
            s = _directory.getString(3584);
        return s;
    }

    public String getQualityDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12290)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12290);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 3: // '\003'
            s = "Fine";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getQualityModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(8)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(8);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Fine";
            break;

        case 2: // '\002'
            s = "Super Fine";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getRecordModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12288)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12288);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 2: // '\002'
            s = "Normal";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSaturationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(31)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(31);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "-1";
            break;

        case 1: // '\001'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "+1";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSelfTimerDescription()
        throws MetadataException
    {
        if(_directory.containsTag(12289)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(12289);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 1: // '\001'
            s = "Off";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSharpnessDescription()
        throws MetadataException
    {
        if(_directory.containsTag(33)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(33);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "-1";
            break;

        case 1: // '\001'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "+1";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getThumbnailDimensionsDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(2))
        {
            s = null;
        } else
        {
            int ai[] = _directory.getIntArray(2);
            if(ai.length != 2)
                s = _directory.getString(2);
            else
                s = (new StringBuilder(String.valueOf(ai[0]))).append(" x ").append(ai[1]).append(" pixels").toString();
        }
        return s;
    }

    public String getThumbnailOffsetDescription()
    {
        String s;
        if(!_directory.containsTag(4))
            s = null;
        else
            s = _directory.getString(4);
        return s;
    }

    public String getThumbnailSizeDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(3))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(Integer.toString(_directory.getInt(3))))).append(" bytes").toString();
        return s;
    }

    public String getTimeZoneDescription()
    {
        String s;
        if(!_directory.containsTag(12294))
            s = null;
        else
            s = _directory.getString(12294);
        return s;
    }

    public String getWhiteBalance1Description()
        throws MetadataException
    {
        if(_directory.containsTag(25)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(25);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto";
            break;

        case 1: // '\001'
            s = "Daylight";
            break;

        case 2: // '\002'
            s = "Shade";
            break;

        case 3: // '\003'
            s = "Tungsten";
            break;

        case 4: // '\004'
            s = "Flourescent";
            break;

        case 5: // '\005'
            s = "Manual";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWhiteBalance2Description()
        throws MetadataException
    {
        if(_directory.containsTag(8210)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(8210);
        switch(i)
        {
        default:
            s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
            break;

        case 0: // '\0'
            s = "Manual";
            break;

        case 1: // '\001'
            s = "Auto";
            break;

        case 4: // '\004'
            s = "Flash";
            break;

        case 12: // '\f'
            s = "Flash";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWhiteBalanceBiasDescription()
    {
        String s;
        if(!_directory.containsTag(8209))
            s = null;
        else
            s = _directory.getString(8209);
        return s;
    }
}
