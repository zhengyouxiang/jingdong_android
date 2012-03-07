// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.imaging.PhotographicConversions;
import com.drew.lang.Rational;
import com.drew.metadata.*;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

public class ExifDescriptor extends TagDescriptor
{

    public ExifDescriptor(Directory directory)
    {
        super(directory);
        _allowDecimalRepresentationOfRationals = true;
    }

    public static String convertBytesToVersionString(int ai[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if(i >= 4 || i >= ai.length)
                return stringbuffer.toString();
            if(i == 2)
                stringbuffer.append('.');
            String s = String.valueOf((char)ai[i]);
            if(i != 0 || !"0".equals(s))
                stringbuffer.append(s);
            i++;
        } while(true);
    }

    private String getUnicodeDescription(int i)
        throws MetadataException
    {
        if(_directory.containsTag(i)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        byte abyte0[] = _directory.getByteArray(i);
        String s1 = (new String(abyte0, "UTF-16LE")).trim();
        s = s1;
        continue; /* Loop/switch isn't completed */
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        s = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String get35mmFilmEquivFocalLengthDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(41989))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(41989);
            if(i == 0)
                s = "Unknown";
            else
                s = (new StringBuilder(String.valueOf(SimpleDecimalFormatter.format(i)))).append("mm").toString();
        }
        return s;
    }

    public String getApertureValueDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37378))
        {
            s = null;
        } else
        {
            double d = PhotographicConversions.apertureToFStop(_directory.getDouble(37378));
            s = (new StringBuilder("F")).append(SimpleDecimalFormatter.format(d)).toString();
        }
        return s;
    }

    public String getBitsPerSampleDescription()
    {
        String s;
        if(!_directory.containsTag(258))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(258)))).append(" bits/component/pixel").toString();
        return s;
    }

    public String getColorSpaceDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(40961))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(40961);
            if(i == 1)
                s = "sRGB";
            else
            if(i == 65535)
                s = "Undefined";
            else
                s = "Unknown";
        }
        return s;
    }

    public String getComponentConfigurationDescription()
        throws MetadataException
    {
        int ai[] = _directory.getIntArray(37121);
        String as[] = new String[7];
        as[0] = "";
        as[1] = "Y";
        as[2] = "Cb";
        as[3] = "Cr";
        as[4] = "R";
        as[5] = "G";
        as[6] = "B";
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        do
        {
            if(i >= Math.min(4, ai.length))
                return stringbuffer.toString();
            int j = ai[i];
            if(j > 0 && j < as.length)
                stringbuffer.append(as[j]);
            i++;
        } while(true);
    }

    public String getCompressionDescription()
        throws MetadataException
    {
        if(_directory.containsTag(259)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(259))
        {
        default:
            s = "Unknown compression";
            break;

        case 1: // '\001'
            s = "Uncompressed";
            break;

        case 2: // '\002'
            s = "CCITT 1D";
            break;

        case 3: // '\003'
            s = "T4/Group 3 Fax";
            break;

        case 4: // '\004'
            s = "T6/Group 4 Fax";
            break;

        case 5: // '\005'
            s = "LZW";
            break;

        case 6: // '\006'
            s = "JPEG (old-style)";
            break;

        case 7: // '\007'
            s = "JPEG";
            break;

        case 8: // '\b'
            s = "Adobe Deflate";
            break;

        case 9: // '\t'
            s = "JBIG B&W";
            break;

        case 10: // '\n'
            s = "JBIG Color";
            break;

        case 32766: 
            s = "Next";
            break;

        case 32771: 
            s = "CCIRLEW";
            break;

        case 32773: 
            s = "PackBits";
            break;

        case 32809: 
            s = "Thunderscan";
            break;

        case 32895: 
            s = "IT8CTPAD";
            break;

        case 32896: 
            s = "IT8LW";
            break;

        case 32897: 
            s = "IT8MP";
            break;

        case 32898: 
            s = "IT8BL";
            break;

        case 32908: 
            s = "PixarFilm";
            break;

        case 32909: 
            s = "PixarLog";
            break;

        case 32946: 
            s = "Deflate";
            break;

        case 32947: 
            s = "DCS";
            break;

        case 32661: 
            s = "JBIG";
            break;

        case 32676: 
            s = "SGILog";
            break;

        case 32677: 
            s = "SGILog24";
            break;

        case 32712: 
            s = "JPEG 2000";
            break;

        case 32713: 
            s = "Nikon NEF Compressed";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getCompressionLevelDescription()
        throws MetadataException
    {
        String s1;
        if(!_directory.containsTag(37122))
        {
            s1 = null;
        } else
        {
            Rational rational = _directory.getRational(37122);
            String s = rational.toSimpleString(_allowDecimalRepresentationOfRationals);
            if(rational.isInteger() && rational.intValue() == 1)
                s1 = (new StringBuilder(String.valueOf(s))).append(" bit/pixel").toString();
            else
                s1 = (new StringBuilder(String.valueOf(s))).append(" bits/pixel").toString();
        }
        return s1;
    }

    public String getContrastDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41992)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41992))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41992)).append(")").toString();
            break;

        case 0: // '\0'
            s = "None";
            break;

        case 1: // '\001'
            s = "Soft";
            break;

        case 2: // '\002'
            s = "Hard";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getCustomRenderedDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41985)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41985))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41985)).append(")").toString();
            break;

        case 0: // '\0'
            s = "Normal process";
            break;

        case 1: // '\001'
            s = "Custom process";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDescription(int i)
        throws MetadataException
    {
        i;
        JVM INSTR lookupswitch 66: default 540
    //                   254: 559
    //                   255: 567
    //                   256: 759
    //                   257: 767
    //                   258: 775
    //                   259: 783
    //                   262: 791
    //                   263: 575
    //                   266: 583
    //                   274: 551
    //                   277: 815
    //                   278: 799
    //                   279: 807
    //                   282: 631
    //                   283: 639
    //                   284: 823
    //                   296: 591
    //                   513: 647
    //                   514: 655
    //                   530: 831
    //                   531: 599
    //                   532: 919
    //                   33434: 607
    //                   33437: 623
    //                   34850: 839
    //                   34855: 927
    //                   36864: 903
    //                   37121: 895
    //                   37122: 663
    //                   37377: 615
    //                   37378: 847
    //                   37380: 871
    //                   37381: 855
    //                   37382: 671
    //                   37383: 679
    //                   37384: 687
    //                   37385: 695
    //                   37386: 703
    //                   37510: 943
    //                   40091: 1071
    //                   40092: 1047
    //                   40093: 1039
    //                   40094: 1055
    //                   40095: 1063
    //                   40960: 911
    //                   40961: 711
    //                   40962: 719
    //                   40963: 727
    //                   41486: 743
    //                   41487: 751
    //                   41488: 735
    //                   41495: 863
    //                   41728: 879
    //                   41729: 887
    //                   41985: 951
    //                   41986: 959
    //                   41987: 967
    //                   41988: 975
    //                   41989: 983
    //                   41990: 991
    //                   41991: 999
    //                   41992: 1007
    //                   41993: 1015
    //                   41994: 1023
    //                   41996: 1031
    //                   61441: 935;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50 _L51 _L52 _L53 _L54 _L55 _L56 _L57 _L58 _L59 _L60 _L61 _L62 _L63 _L64 _L65 _L66 _L67
_L1:
        String s = _directory.getString(i);
_L69:
        return s;
_L11:
        s = getOrientationDescription();
        continue; /* Loop/switch isn't completed */
_L2:
        s = getNewSubfileTypeDescription();
        continue; /* Loop/switch isn't completed */
_L3:
        s = getSubfileTypeDescription();
        continue; /* Loop/switch isn't completed */
_L9:
        s = getThresholdingDescription();
        continue; /* Loop/switch isn't completed */
_L10:
        s = getFillOrderDescription();
        continue; /* Loop/switch isn't completed */
_L18:
        s = getResolutionDescription();
        continue; /* Loop/switch isn't completed */
_L22:
        s = getYCbCrPositioningDescription();
        continue; /* Loop/switch isn't completed */
_L24:
        s = getExposureTimeDescription();
        continue; /* Loop/switch isn't completed */
_L31:
        s = getShutterSpeedDescription();
        continue; /* Loop/switch isn't completed */
_L25:
        s = getFNumberDescription();
        continue; /* Loop/switch isn't completed */
_L15:
        s = getXResolutionDescription();
        continue; /* Loop/switch isn't completed */
_L16:
        s = getYResolutionDescription();
        continue; /* Loop/switch isn't completed */
_L19:
        s = getThumbnailOffsetDescription();
        continue; /* Loop/switch isn't completed */
_L20:
        s = getThumbnailLengthDescription();
        continue; /* Loop/switch isn't completed */
_L30:
        s = getCompressionLevelDescription();
        continue; /* Loop/switch isn't completed */
_L35:
        s = getSubjectDistanceDescription();
        continue; /* Loop/switch isn't completed */
_L36:
        s = getMeteringModeDescription();
        continue; /* Loop/switch isn't completed */
_L37:
        s = getWhiteBalanceDescription();
        continue; /* Loop/switch isn't completed */
_L38:
        s = getFlashDescription();
        continue; /* Loop/switch isn't completed */
_L39:
        s = getFocalLengthDescription();
        continue; /* Loop/switch isn't completed */
_L47:
        s = getColorSpaceDescription();
        continue; /* Loop/switch isn't completed */
_L48:
        s = getExifImageWidthDescription();
        continue; /* Loop/switch isn't completed */
_L49:
        s = getExifImageHeightDescription();
        continue; /* Loop/switch isn't completed */
_L52:
        s = getFocalPlaneResolutionUnitDescription();
        continue; /* Loop/switch isn't completed */
_L50:
        s = getFocalPlaneXResolutionDescription();
        continue; /* Loop/switch isn't completed */
_L51:
        s = getFocalPlaneYResolutionDescription();
        continue; /* Loop/switch isn't completed */
_L4:
        s = getThumbnailImageWidthDescription();
        continue; /* Loop/switch isn't completed */
_L5:
        s = getThumbnailImageHeightDescription();
        continue; /* Loop/switch isn't completed */
_L6:
        s = getBitsPerSampleDescription();
        continue; /* Loop/switch isn't completed */
_L7:
        s = getCompressionDescription();
        continue; /* Loop/switch isn't completed */
_L8:
        s = getPhotometricInterpretationDescription();
        continue; /* Loop/switch isn't completed */
_L13:
        s = getRowsPerStripDescription();
        continue; /* Loop/switch isn't completed */
_L14:
        s = getStripByteCountsDescription();
        continue; /* Loop/switch isn't completed */
_L12:
        s = getSamplesPerPixelDescription();
        continue; /* Loop/switch isn't completed */
_L17:
        s = getPlanarConfigurationDescription();
        continue; /* Loop/switch isn't completed */
_L21:
        s = getYCbCrSubsamplingDescription();
        continue; /* Loop/switch isn't completed */
_L26:
        s = getExposureProgramDescription();
        continue; /* Loop/switch isn't completed */
_L32:
        s = getApertureValueDescription();
        continue; /* Loop/switch isn't completed */
_L34:
        s = getMaxApertureValueDescription();
        continue; /* Loop/switch isn't completed */
_L53:
        s = getSensingMethodDescription();
        continue; /* Loop/switch isn't completed */
_L33:
        s = getExposureBiasDescription();
        continue; /* Loop/switch isn't completed */
_L54:
        s = getFileSourceDescription();
        continue; /* Loop/switch isn't completed */
_L55:
        s = getSceneTypeDescription();
        continue; /* Loop/switch isn't completed */
_L29:
        s = getComponentConfigurationDescription();
        continue; /* Loop/switch isn't completed */
_L28:
        s = getExifVersionDescription();
        continue; /* Loop/switch isn't completed */
_L46:
        s = getFlashPixVersionDescription();
        continue; /* Loop/switch isn't completed */
_L23:
        s = getReferenceBlackWhiteDescription();
        continue; /* Loop/switch isn't completed */
_L27:
        s = getIsoEquivalentDescription();
        continue; /* Loop/switch isn't completed */
_L67:
        s = getThumbnailDescription();
        continue; /* Loop/switch isn't completed */
_L40:
        s = getUserCommentDescription();
        continue; /* Loop/switch isn't completed */
_L56:
        s = getCustomRenderedDescription();
        continue; /* Loop/switch isn't completed */
_L57:
        s = getExposureModeDescription();
        continue; /* Loop/switch isn't completed */
_L58:
        s = getWhiteBalanceModeDescription();
        continue; /* Loop/switch isn't completed */
_L59:
        s = getDigitalZoomRatioDescription();
        continue; /* Loop/switch isn't completed */
_L60:
        s = get35mmFilmEquivFocalLengthDescription();
        continue; /* Loop/switch isn't completed */
_L61:
        s = getSceneCaptureTypeDescription();
        continue; /* Loop/switch isn't completed */
_L62:
        s = getGainControlDescription();
        continue; /* Loop/switch isn't completed */
_L63:
        s = getContrastDescription();
        continue; /* Loop/switch isn't completed */
_L64:
        s = getSaturationDescription();
        continue; /* Loop/switch isn't completed */
_L65:
        s = getSharpnessDescription();
        continue; /* Loop/switch isn't completed */
_L66:
        s = getSubjectDistanceRangeDescription();
        continue; /* Loop/switch isn't completed */
_L43:
        s = getWindowsAuthorDescription();
        continue; /* Loop/switch isn't completed */
_L42:
        s = getWindowsCommentDescription();
        continue; /* Loop/switch isn't completed */
_L44:
        s = getWindowsKeywordsDescription();
        continue; /* Loop/switch isn't completed */
_L45:
        s = getWindowsSubjectDescription();
        continue; /* Loop/switch isn't completed */
_L41:
        s = getWindowsTitleDescription();
        if(true) goto _L69; else goto _L68
_L68:
    }

    public String getDigitalZoomRatioDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(41988))
        {
            s = null;
        } else
        {
            Rational rational = _directory.getRational(41988);
            if(rational.getNumerator() == 0)
                s = "Digital zoom not used.";
            else
                s = SimpleDecimalFormatter.format(rational.doubleValue());
        }
        return s;
    }

    public String getExifImageHeightDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(40963))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getInt(40963)))).append(" pixels").toString();
        return s;
    }

    public String getExifImageWidthDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(40962))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getInt(40962)))).append(" pixels").toString();
        return s;
    }

    public String getExifVersionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(36864))
            s = null;
        else
            s = convertBytesToVersionString(_directory.getIntArray(36864));
        return s;
    }

    public String getExposureBiasDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37380))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(37380).toSimpleString(true)))).append(" EV").toString();
        return s;
    }

    public String getExposureModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41986)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41986))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41986)).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto exposure";
            break;

        case 1: // '\001'
            s = "Manual exposure";
            break;

        case 2: // '\002'
            s = "Auto bracket";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getExposureProgramDescription()
        throws MetadataException
    {
        if(_directory.containsTag(34850)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(34850))
        {
        default:
            s = (new StringBuilder("Unknown program (")).append(_directory.getInt(34850)).append(")").toString();
            break;

        case 1: // '\001'
            s = "Manual control";
            break;

        case 2: // '\002'
            s = "Program normal";
            break;

        case 3: // '\003'
            s = "Aperture priority";
            break;

        case 4: // '\004'
            s = "Shutter priority";
            break;

        case 5: // '\005'
            s = "Program creative (slow program)";
            break;

        case 6: // '\006'
            s = "Program action (high-speed program)";
            break;

        case 7: // '\007'
            s = "Portrait mode";
            break;

        case 8: // '\b'
            s = "Landscape mode";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getExposureTimeDescription()
    {
        String s;
        if(!_directory.containsTag(33434))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(33434)))).append(" sec").toString();
        return s;
    }

    public String getFNumberDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(33437))
        {
            s = null;
        } else
        {
            Rational rational = _directory.getRational(33437);
            s = (new StringBuilder("F")).append(SimpleDecimalFormatter.format(rational.doubleValue())).toString();
        }
        return s;
    }

    public String getFileSourceDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(41728))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(41728);
            if(i == 3)
                s = "Digital Still Camera (DSC)";
            else
                s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
        }
        return s;
    }

    public String getFillOrderDescription()
        throws MetadataException
    {
        if(_directory.containsTag(266)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(266))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(266)).append(")").toString();
            break;

        case 1: // '\001'
            s = "Normal";
            break;

        case 2: // '\002'
            s = "Reversed";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFlashDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37385))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(37385);
            StringBuffer stringbuffer = new StringBuffer();
            if((i & 1) != 0)
                stringbuffer.append("Flash fired");
            else
                stringbuffer.append("Flash did not fire");
            if((i & 4) != 0)
                if((i & 2) != 0)
                    stringbuffer.append(", return detected");
                else
                    stringbuffer.append(", return not detected");
            if((i & 0x10) != 0)
                stringbuffer.append(", auto");
            if((i & 0x40) != 0)
                stringbuffer.append(", red-eye reduction");
            s = stringbuffer.toString();
        }
        return s;
    }

    public String getFlashPixVersionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(40960))
            s = null;
        else
            s = convertBytesToVersionString(_directory.getIntArray(40960));
        return s;
    }

    public String getFocalLengthDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37386))
            s = null;
        else
            s = (new StringBuilder(String.valueOf((new DecimalFormat("0.0##")).format(_directory.getRational(37386).doubleValue())))).append(" mm").toString();
        return s;
    }

    public String getFocalPlaneResolutionUnitDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41488)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41488))
        {
        default:
            s = "";
            break;

        case 1: // '\001'
            s = "(No unit)";
            break;

        case 2: // '\002'
            s = "Inches";
            break;

        case 3: // '\003'
            s = "cm";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getFocalPlaneXResolutionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(41486))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(41486).getReciprocal().toSimpleString(_allowDecimalRepresentationOfRationals)))).append(" ").append(getFocalPlaneResolutionUnitDescription().toLowerCase()).toString();
        return s;
    }

    public String getFocalPlaneYResolutionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(41487))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(41487).getReciprocal().toSimpleString(_allowDecimalRepresentationOfRationals)))).append(" ").append(getFocalPlaneResolutionUnitDescription().toLowerCase()).toString();
        return s;
    }

    public String getGainControlDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41991)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41991))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41991)).append(")").toString();
            break;

        case 0: // '\0'
            s = "None";
            break;

        case 1: // '\001'
            s = "Low gain up";
            break;

        case 2: // '\002'
            s = "Low gain down";
            break;

        case 3: // '\003'
            s = "High gain up";
            break;

        case 4: // '\004'
            s = "High gain down";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getIsoEquivalentDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(34855))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(34855);
            if(i < 50)
                i *= 200;
            s = Integer.toString(i);
        }
        return s;
    }

    public String getMaxApertureValueDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37381))
        {
            s = null;
        } else
        {
            double d = PhotographicConversions.apertureToFStop(_directory.getDouble(37381));
            s = (new StringBuilder("F")).append(SimpleDecimalFormatter.format(d)).toString();
        }
        return s;
    }

    public String getMeteringModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(37383)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(37383))
        {
        default:
            s = "";
            break;

        case 0: // '\0'
            s = "Unknown";
            break;

        case 1: // '\001'
            s = "Average";
            break;

        case 2: // '\002'
            s = "Center weighted average";
            break;

        case 3: // '\003'
            s = "Spot";
            break;

        case 4: // '\004'
            s = "Multi-spot";
            break;

        case 5: // '\005'
            s = "Multi-segment";
            break;

        case 6: // '\006'
            s = "Partial";
            break;

        case 255: 
            s = "(Other)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getNewSubfileTypeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(254)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(254))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(254)).append(")").toString();
            break;

        case 1: // '\001'
            s = "Full-resolution image";
            break;

        case 2: // '\002'
            s = "Reduced-resolution image";
            break;

        case 3: // '\003'
            s = "Single page of multi-page reduced-resolution image";
            break;

        case 4: // '\004'
            s = "Transparency mask";
            break;

        case 5: // '\005'
            s = "Transparency mask of reduced-resolution image";
            break;

        case 6: // '\006'
            s = "Transparency mask of multi-page image";
            break;

        case 7: // '\007'
            s = "Transparency mask of reduced-resolution multi-page image";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getOrientationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(274)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(274);
        switch(i)
        {
        default:
            s = String.valueOf(i);
            break;

        case 1: // '\001'
            s = "Top, left side (Horizontal / normal)";
            break;

        case 2: // '\002'
            s = "Top, right side (Mirror horizontal)";
            break;

        case 3: // '\003'
            s = "Bottom, right side (Rotate 180)";
            break;

        case 4: // '\004'
            s = "Bottom, left side (Mirror vertical)";
            break;

        case 5: // '\005'
            s = "Left side, top (Mirror horizontal and rotate 270 CW)";
            break;

        case 6: // '\006'
            s = "Right side, top (Rotate 90 CW)";
            break;

        case 7: // '\007'
            s = "Right side, bottom (Mirror horizontal and rotate 90 CW)";
            break;

        case 8: // '\b'
            s = "Left side, bottom (Rotate 270 CW)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getPhotometricInterpretationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(262)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(262))
        {
        default:
            s = "Unknown colour space";
            break;

        case 0: // '\0'
            s = "WhiteIsZero";
            break;

        case 1: // '\001'
            s = "BlackIsZero";
            break;

        case 2: // '\002'
            s = "RGB";
            break;

        case 3: // '\003'
            s = "RGB Palette";
            break;

        case 4: // '\004'
            s = "Transparency Mask";
            break;

        case 5: // '\005'
            s = "CMYK";
            break;

        case 6: // '\006'
            s = "YCbCr";
            break;

        case 8: // '\b'
            s = "CIELab";
            break;

        case 9: // '\t'
            s = "ICCLab";
            break;

        case 10: // '\n'
            s = "ITULab";
            break;

        case 32803: 
            s = "Color Filter Array";
            break;

        case 32844: 
            s = "Pixar LogL";
            break;

        case 32845: 
            s = "Pixar LogLuv";
            break;

        case 32892: 
            s = "Linear Raw";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getPlanarConfigurationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(284)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(284))
        {
        default:
            s = "Unknown configuration";
            break;

        case 1: // '\001'
            s = "Chunky (contiguous for each subsampling pixel)";
            break;

        case 2: // '\002'
            s = "Separate (Y-plane/Cb-plane/Cr-plane format)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getReferenceBlackWhiteDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(532))
        {
            s = null;
        } else
        {
            int ai[] = _directory.getIntArray(532);
            int i = ai[0];
            int j = ai[1];
            int k = ai[2];
            int l = ai[3];
            int i1 = ai[4];
            int j1 = ai[5];
            s = (new StringBuilder("[")).append(i).append(",").append(k).append(",").append(i1).append("] ").append("[").append(j).append(",").append(l).append(",").append(j1).append("]").toString();
        }
        return s;
    }

    public String getResolutionDescription()
        throws MetadataException
    {
        if(_directory.containsTag(296)) goto _L2; else goto _L1
_L1:
        String s = "";
_L4:
        return s;
_L2:
        switch(_directory.getInt(296))
        {
        default:
            s = "";
            break;

        case 1: // '\001'
            s = "(No unit)";
            break;

        case 2: // '\002'
            s = "Inch";
            break;

        case 3: // '\003'
            s = "cm";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getRowsPerStripDescription()
    {
        String s;
        if(!_directory.containsTag(278))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(278)))).append(" rows/strip").toString();
        return s;
    }

    public String getSamplesPerPixelDescription()
    {
        String s;
        if(!_directory.containsTag(277))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(277)))).append(" samples/pixel").toString();
        return s;
    }

    public String getSaturationDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41993)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41993))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41993)).append(")").toString();
            break;

        case 0: // '\0'
            s = "None";
            break;

        case 1: // '\001'
            s = "Low saturation";
            break;

        case 2: // '\002'
            s = "High saturation";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSceneCaptureTypeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41990)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41990))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41990)).append(")").toString();
            break;

        case 0: // '\0'
            s = "Standard";
            break;

        case 1: // '\001'
            s = "Landscape";
            break;

        case 2: // '\002'
            s = "Portrait";
            break;

        case 3: // '\003'
            s = "Night scene";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSceneTypeDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(41729))
        {
            s = null;
        } else
        {
            int i = _directory.getInt(41729);
            if(i == 1)
                s = "Directly photographed image";
            else
                s = (new StringBuilder("Unknown (")).append(i).append(")").toString();
        }
        return s;
    }

    public String getSensingMethodDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41495)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41495))
        {
        case 6: // '\006'
        default:
            s = "";
            break;

        case 1: // '\001'
            s = "(Not defined)";
            break;

        case 2: // '\002'
            s = "One-chip color area sensor";
            break;

        case 3: // '\003'
            s = "Two-chip color area sensor";
            break;

        case 4: // '\004'
            s = "Three-chip color area sensor";
            break;

        case 5: // '\005'
            s = "Color sequential area sensor";
            break;

        case 7: // '\007'
            s = "Trilinear sensor";
            break;

        case 8: // '\b'
            s = "Color sequential linear sensor";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSharpnessDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41994)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41994))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41994)).append(")").toString();
            break;

        case 0: // '\0'
            s = "None";
            break;

        case 1: // '\001'
            s = "Low";
            break;

        case 2: // '\002'
            s = "Hard";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getShutterSpeedDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37377))
        {
            s = null;
        } else
        {
            float f = _directory.getFloat(37377);
            if(f <= 1.0F)
            {
                s = (new StringBuilder(String.valueOf((float)Math.round(10D * (double)(float)(1.0D / Math.exp((double)f * Math.log(2D)))) / 10F))).append(" sec").toString();
            } else
            {
                int i = (int)Math.exp((double)f * Math.log(2D));
                s = (new StringBuilder("1/")).append(i).append(" sec").toString();
            }
        }
        return s;
    }

    public String getStripByteCountsDescription()
    {
        String s;
        if(!_directory.containsTag(279))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(279)))).append(" bytes").toString();
        return s;
    }

    public String getSubfileTypeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(255)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(255))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(255)).append(")").toString();
            break;

        case 1: // '\001'
            s = "Full-resolution image";
            break;

        case 2: // '\002'
            s = "Reduced-resolution image";
            break;

        case 3: // '\003'
            s = "Single page of multi-page image";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getSubjectDistanceDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(37382))
        {
            s = null;
        } else
        {
            Rational rational = _directory.getRational(37382);
            s = (new StringBuilder(String.valueOf((new DecimalFormat("0.0##")).format(rational.doubleValue())))).append(" metres").toString();
        }
        return s;
    }

    public String getSubjectDistanceRangeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41996)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41996))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41996)).append(")").toString();
            break;

        case 0: // '\0'
            s = "Unknown";
            break;

        case 1: // '\001'
            s = "Macro";
            break;

        case 2: // '\002'
            s = "Close view";
            break;

        case 3: // '\003'
            s = "Distant view";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getThresholdingDescription()
        throws MetadataException
    {
        if(_directory.containsTag(263)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(263))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(263)).append(")").toString();
            break;

        case 1: // '\001'
            s = "No dithering or halftoning";
            break;

        case 2: // '\002'
            s = "Ordered dither or halftone";
            break;

        case 3: // '\003'
            s = "Randomized dither";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getThumbnailDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(61441))
        {
            s = null;
        } else
        {
            int ai[] = _directory.getIntArray(61441);
            s = (new StringBuilder("[")).append(ai.length).append(" bytes of thumbnail data]").toString();
        }
        return s;
    }

    public String getThumbnailImageHeightDescription()
    {
        String s;
        if(!_directory.containsTag(257))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(257)))).append(" pixels").toString();
        return s;
    }

    public String getThumbnailImageWidthDescription()
    {
        String s;
        if(!_directory.containsTag(256))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(256)))).append(" pixels").toString();
        return s;
    }

    public String getThumbnailLengthDescription()
    {
        String s;
        if(!_directory.containsTag(514))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(514)))).append(" bytes").toString();
        return s;
    }

    public String getThumbnailOffsetDescription()
    {
        String s;
        if(!_directory.containsTag(513))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getString(513)))).append(" bytes").toString();
        return s;
    }

    public String getUserCommentDescription()
        throws MetadataException
    {
        byte abyte0[];
        String s2;
        if(!_directory.containsTag(37510))
        {
            s2 = null;
        } else
        {
label0:
            {
                abyte0 = _directory.getByteArray(37510);
                if(abyte0.length != 0)
                    break label0;
                s2 = "";
            }
        }
_L4:
        return s2;
        String as[];
        as = new String[3];
        as[0] = "ASCII";
        as[1] = "UNICODE";
        as[2] = "JIS";
        if(abyte0.length < 10) goto _L2; else goto _L1
_L1:
        String s;
        int i;
        s = new String(abyte0, 0, 10);
        i = 0;
_L5:
        if(i < as.length) goto _L3; else goto _L2
_L2:
        s2 = (new String(abyte0)).trim();
          goto _L4
_L3:
label1:
        {
            String s1 = as[i];
            if(!s.startsWith(s1))
                break MISSING_BLOCK_LABEL_257;
            byte byte0;
            UnsupportedEncodingException unsupportedencodingexception;
            String s3;
            for(int j = s1.length(); j < 10; j++)
                break label1;

            s2 = (new String(abyte0, 10, abyte0.length - 10)).trim();
        }
          goto _L4
        byte0 = abyte0[j];
        if(byte0 == 0 || byte0 == 32)
            break MISSING_BLOCK_LABEL_251;
        if(!s1.equals("UNICODE"))
            break MISSING_BLOCK_LABEL_228;
        s3 = (new String(abyte0, j, abyte0.length - j, "UTF-16LE")).trim();
        s2 = s3;
          goto _L4
        unsupportedencodingexception;
        s2 = null;
          goto _L4
        s2 = (new String(abyte0, j, abyte0.length - j)).trim();
          goto _L4
        i++;
          goto _L5
    }

    public String getWhiteBalanceDescription()
        throws MetadataException
    {
        if(_directory.containsTag(37384)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(37384))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(37384)).append(")").toString();
            break;

        case 0: // '\0'
            s = "Unknown";
            break;

        case 1: // '\001'
            s = "Daylight";
            break;

        case 2: // '\002'
            s = "Flourescent";
            break;

        case 3: // '\003'
            s = "Tungsten";
            break;

        case 10: // '\n'
            s = "Flash";
            break;

        case 17: // '\021'
            s = "Standard light";
            break;

        case 18: // '\022'
            s = "Standard light (B)";
            break;

        case 19: // '\023'
            s = "Standard light (C)";
            break;

        case 20: // '\024'
            s = "D55";
            break;

        case 21: // '\025'
            s = "D65";
            break;

        case 22: // '\026'
            s = "D75";
            break;

        case 255: 
            s = "(Other)";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWhiteBalanceModeDescription()
        throws MetadataException
    {
        if(_directory.containsTag(41987)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        switch(_directory.getInt(41987))
        {
        default:
            s = (new StringBuilder("Unknown (")).append(_directory.getInt(41987)).append(")").toString();
            break;

        case 0: // '\0'
            s = "Auto white balance";
            break;

        case 1: // '\001'
            s = "Manual white balance";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getWindowsAuthorDescription()
        throws MetadataException
    {
        return getUnicodeDescription(40093);
    }

    public String getWindowsCommentDescription()
        throws MetadataException
    {
        return getUnicodeDescription(40092);
    }

    public String getWindowsKeywordsDescription()
        throws MetadataException
    {
        return getUnicodeDescription(40094);
    }

    public String getWindowsSubjectDescription()
        throws MetadataException
    {
        return getUnicodeDescription(40095);
    }

    public String getWindowsTitleDescription()
        throws MetadataException
    {
        return getUnicodeDescription(40091);
    }

    public String getXResolutionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(282))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(282).toSimpleString(_allowDecimalRepresentationOfRationals)))).append(" dots per ").append(getResolutionDescription().toLowerCase()).toString();
        return s;
    }

    public String getYCbCrPositioningDescription()
        throws MetadataException
    {
        if(_directory.containsTag(531)) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        int i = _directory.getInt(531);
        switch(i)
        {
        default:
            s = String.valueOf(i);
            break;

        case 1: // '\001'
            s = "Center of pixel array";
            break;

        case 2: // '\002'
            s = "Datum point";
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getYCbCrSubsamplingDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(530))
        {
            s = null;
        } else
        {
            int ai[] = _directory.getIntArray(530);
            if(ai[0] == 2 && ai[1] == 1)
                s = "YCbCr4:2:2";
            else
            if(ai[0] == 2 && ai[1] == 2)
                s = "YCbCr4:2:0";
            else
                s = "(Unknown)";
        }
        return s;
    }

    public String getYResolutionDescription()
        throws MetadataException
    {
        String s;
        if(!_directory.containsTag(283))
            s = null;
        else
            s = (new StringBuilder(String.valueOf(_directory.getRational(283).toSimpleString(_allowDecimalRepresentationOfRationals)))).append(" dots per ").append(getResolutionDescription().toLowerCase()).toString();
        return s;
    }

    private static final DecimalFormat SimpleDecimalFormatter = new DecimalFormat("0.#");
    private boolean _allowDecimalRepresentationOfRationals;

}
