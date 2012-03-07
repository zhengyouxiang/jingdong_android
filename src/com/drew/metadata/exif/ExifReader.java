// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.imaging.jpeg.*;
import com.drew.lang.Rational;
import com.drew.metadata.*;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            ExifDirectory, ExifInteropDirectory, GpsDirectory, OlympusMakernoteDirectory, 
//            NikonType1MakernoteDirectory, NikonType2MakernoteDirectory, SonyMakernoteDirectory, KodakMakernoteDirectory, 
//            CanonMakernoteDirectory, CasioType2MakernoteDirectory, CasioType1MakernoteDirectory, FujifilmMakernoteDirectory, 
//            KyoceraMakernoteDirectory, PanasonicMakernoteDirectory, PentaxMakernoteDirectory

public class ExifReader
    implements MetadataReader
{

    public ExifReader(JpegSegmentData jpegsegmentdata)
    {
        this(jpegsegmentdata.getSegment((byte)-31));
    }

    public ExifReader(File file)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(file)).readSegment((byte)-31));
    }

    public ExifReader(InputStream inputstream)
        throws JpegProcessingException
    {
        this((new JpegSegmentReader(inputstream)).readSegment((byte)-31));
    }

    public ExifReader(byte abyte0[])
    {
        _data = abyte0;
    }

    private int calculateTagOffset(int i, int j)
    {
        return i + 2 + j * 12;
    }

    private int calculateTagValueOffset(int i, int j, int k)
    {
        int l;
        if(i > 4)
        {
            int i1 = get32Bits(j + 8);
            if(i1 + i > _data.length)
                l = -1;
            else
                l = k + i1;
        } else
        {
            l = j + 8;
        }
        return l;
    }

    private int get16Bits(int i)
    {
        if(i < 0 || i + 2 > _data.length)
            throw new ArrayIndexOutOfBoundsException((new StringBuilder("attempt to read data outside of exif segment (index ")).append(i).append(" where max index is ").append(_data.length - 1).append(")").toString());
        int j;
        if(_isMotorollaByteOrder)
            j = 0xff00 & _data[i] << 8 | 0xff & _data[i + 1];
        else
            j = 0xff00 & _data[i + 1] << 8 | 0xff & _data[i];
        return j;
    }

    private int get32Bits(int i)
    {
        if(i < 0 || i + 4 > _data.length)
            throw new ArrayIndexOutOfBoundsException((new StringBuilder("attempt to read data outside of exif segment (index ")).append(i).append(" where max index is ").append(_data.length - 1).append(")").toString());
        int j;
        if(_isMotorollaByteOrder)
            j = 0xff000000 & _data[i] << 24 | 0xff0000 & _data[i + 1] << 16 | 0xff00 & _data[i + 2] << 8 | 0xff & _data[i + 3];
        else
            j = 0xff000000 & _data[i + 3] << 24 | 0xff0000 & _data[i + 2] << 16 | 0xff00 & _data[i + 1] << 8 | 0xff & _data[i];
        return j;
    }

    private boolean isDirectoryLengthValid(int i, int j)
    {
        boolean flag;
        if(j + (i + (4 + (2 + 12 * get16Bits(i)))) >= _data.length)
            flag = false;
        else
            flag = true;
        return flag;
    }

    private void processDirectory(Directory directory, HashMap hashmap, int i, int j)
    {
        Integer integer = new Integer(i);
        if(!hashmap.containsKey(integer)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int k;
        int l;
        Integer integer1 = new Integer(i);
        hashmap.put(integer1, "processed");
        if(i >= _data.length || i < 0)
        {
            directory.addError("Ignored directory marked to start outside data segement");
            continue; /* Loop/switch isn't completed */
        }
        if(!isDirectoryLengthValid(i, j))
        {
            directory.addError("Illegally sized directory");
            continue; /* Loop/switch isn't completed */
        }
        k = get16Bits(i);
        l = 0;
_L4:
        int i1;
        int j1;
        int k1;
        if(l >= k)
        {
            int l2 = get32Bits(calculateTagOffset(i, k));
            if(l2 != 0)
            {
                int i3 = l2 + j;
                if(i3 < _data.length && i3 >= i)
                    processDirectory(directory, hashmap, i3, j);
            }
            continue; /* Loop/switch isn't completed */
        }
        i1 = calculateTagOffset(i, l);
        j1 = get16Bits(i1);
        k1 = get16Bits(i1 + 2);
        if(k1 >= 1 && k1 <= 12)
            break; /* Loop/switch isn't completed */
        directory.addError((new StringBuilder("Invalid format code: ")).append(k1).toString());
_L5:
        l++;
        if(true) goto _L4; else goto _L3
_L3:
        int l1;
        int j2;
        l1 = get32Bits(i1 + 4);
        if(l1 < 0)
        {
            directory.addError("Negative component count in EXIF");
        } else
        {
            int i2 = l1 * BYTES_PER_FORMAT[k1];
            j2 = calculateTagValueOffset(i2, i1, j);
            if(j2 < 0 || j2 > _data.length)
            {
                directory.addError("Illegal pointer offset value in EXIF");
            } else
            {
label0:
                {
                    if(i2 >= 0 && j2 + i2 <= _data.length)
                        break label0;
                    directory.addError((new StringBuilder("Illegal number of bytes: ")).append(i2).toString());
                }
            }
        }
          goto _L5
          goto _L4
        int k2 = j + get32Bits(j2);
        switch(j1)
        {
        default:
            processTag(directory, j1, j2, l1, k1);
            break;

        case 34665: 
            processDirectory(_metadata.getDirectory(com/drew/metadata/exif/ExifDirectory), hashmap, k2, j);
            break;

        case 40965: 
            processDirectory(_metadata.getDirectory(com/drew/metadata/exif/ExifInteropDirectory), hashmap, k2, j);
            break;

        case 34853: 
            processDirectory(_metadata.getDirectory(com/drew/metadata/exif/GpsDirectory), hashmap, k2, j);
            break;

        case 37500: 
            processMakerNote(j2, hashmap, j);
            break;
        }
        if(true) goto _L5; else goto _L6
_L6:
        if(true) goto _L1; else goto _L7
_L7:
    }

    private void processMakerNote(int i, HashMap hashmap, int j)
    {
        Directory directory = _metadata.getDirectory(com/drew/metadata/exif/ExifDirectory);
        if(directory != null)
        {
            String s = directory.getString(271);
            String s1 = new String(_data, i, 2);
            String s2 = new String(_data, i, 3);
            String s3 = new String(_data, i, 4);
            String s4 = new String(_data, i, 5);
            String s5 = new String(_data, i, 6);
            String s6 = new String(_data, i, 7);
            String s7 = new String(_data, i, 8);
            if("OLYMP".equals(s4) || "EPSON".equals(s4) || "AGFA".equals(s3))
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/OlympusMakernoteDirectory), hashmap, i + 8, j);
            else
            if(s != null && s.trim().toUpperCase().startsWith("NIKON"))
            {
                if("Nikon".equals(s4))
                {
                    if(_data[i + 6] == 1)
                        processDirectory(_metadata.getDirectory(com/drew/metadata/exif/NikonType1MakernoteDirectory), hashmap, i + 8, j);
                    else
                    if(_data[i + 6] == 2)
                        processDirectory(_metadata.getDirectory(com/drew/metadata/exif/NikonType2MakernoteDirectory), hashmap, i + 18, i + 10);
                    else
                        directory.addError("Unsupported makernote data ignored.");
                } else
                {
                    processDirectory(_metadata.getDirectory(com/drew/metadata/exif/NikonType2MakernoteDirectory), hashmap, i, j);
                }
            } else
            if("SONY CAM".equals(s7) || "SONY DSC".equals(s7))
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/SonyMakernoteDirectory), hashmap, i + 12, j);
            else
            if("KDK".equals(s2))
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/KodakMakernoteDirectory), hashmap, i + 20, j);
            else
            if("Canon".equalsIgnoreCase(s))
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/CanonMakernoteDirectory), hashmap, i, j);
            else
            if(s != null && s.toUpperCase().startsWith("CASIO"))
            {
                if("QVC\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD".equals(s5))
                    processDirectory(_metadata.getDirectory(com/drew/metadata/exif/CasioType2MakernoteDirectory), hashmap, i + 6, j);
                else
                    processDirectory(_metadata.getDirectory(com/drew/metadata/exif/CasioType1MakernoteDirectory), hashmap, i, j);
            } else
            if("FUJIFILM".equals(s7) || "Fujifilm".equalsIgnoreCase(s))
            {
                boolean flag = _isMotorollaByteOrder;
                _isMotorollaByteOrder = false;
                int k = i + get32Bits(i + 8);
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/FujifilmMakernoteDirectory), hashmap, k, j);
                _isMotorollaByteOrder = flag;
            } else
            if(s != null && s.toUpperCase().startsWith("MINOLTA"))
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/OlympusMakernoteDirectory), hashmap, i, j);
            else
            if("KC".equals(s1) || "MINOL".equals(s4) || "MLY".equals(s2) || "+M+M+M+M".equals(s7))
                directory.addError("Unsupported Konica/Minolta data ignored.");
            else
            if("KYOCERA".equals(s6))
            {
                processDirectory(_metadata.getDirectory(com/drew/metadata/exif/KyoceraMakernoteDirectory), hashmap, i + 22, j);
            } else
            {
                String s8 = new String(_data, i, 12);
                if("Panasonic\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD".equals(s8))
                    processDirectory(_metadata.getDirectory(com/drew/metadata/exif/PanasonicMakernoteDirectory), hashmap, i + 12, j);
                else
                if("AOC\uFFFD\uFFFD".equals(s3))
                    processDirectory(_metadata.getDirectory(com/drew/metadata/exif/CasioType2MakernoteDirectory), hashmap, i + 6, i);
                else
                if(s != null && (s.toUpperCase().startsWith("PENTAX") || s.toUpperCase().startsWith("ASAHI")))
                    processDirectory(_metadata.getDirectory(com/drew/metadata/exif/PentaxMakernoteDirectory), hashmap, i, i);
                else
                    directory.addError("Unsupported makernote data ignored.");
            }
        }
    }

    private void processTag(Directory directory, int i, int j, int k, int l)
    {
        l;
        JVM INSTR tableswitch 1 12: default 64
    //                   1 271
    //                   2 154
    //                   3 403
    //                   4 469
    //                   5 169
    //                   6 271
    //                   7 97
    //                   8 403
    //                   9 469
    //                   10 169
    //                   11 337
    //                   12 337;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L2 _L7 _L4 _L5 _L6 _L8 _L8
_L1:
        directory.addError((new StringBuilder("Unknown format code ")).append(l).append(" for tag ").append(i).toString());
_L10:
        return;
_L7:
        byte abyte0[] = new byte[k];
        int j2 = k * BYTES_PER_FORMAT[l];
        int k2 = 0;
        do
        {
            if(k2 >= j2)
            {
                directory.setByteArray(i, abyte0);
                continue; /* Loop/switch isn't completed */
            }
            abyte0[k2] = _data[j + k2];
            k2++;
        } while(true);
_L3:
        directory.setString(i, readString(j, k));
        continue; /* Loop/switch isn't completed */
_L6:
        if(k == 1)
        {
            directory.setRational(i, new Rational(get32Bits(j), get32Bits(j + 4)));
            continue; /* Loop/switch isn't completed */
        }
        Rational arational[] = new Rational[k];
        int i2 = 0;
        do
        {
            if(i2 >= k)
            {
                directory.setRationalArray(i, arational);
                continue; /* Loop/switch isn't completed */
            }
            arational[i2] = new Rational(get32Bits(j + i2 * 8), get32Bits(j + 4 + i2 * 8));
            i2++;
        } while(true);
_L2:
        if(k == 1)
        {
            directory.setInt(i, _data[j]);
            continue; /* Loop/switch isn't completed */
        }
        int ai3[] = new int[k];
        int l1 = 0;
        do
        {
            if(l1 >= k)
            {
                directory.setIntArray(i, ai3);
                continue; /* Loop/switch isn't completed */
            }
            ai3[l1] = _data[j + l1];
            l1++;
        } while(true);
_L8:
        if(k == 1)
        {
            directory.setInt(i, _data[j]);
            continue; /* Loop/switch isn't completed */
        }
        int ai2[] = new int[k];
        int k1 = 0;
        do
        {
            if(k1 >= k)
            {
                directory.setIntArray(i, ai2);
                continue; /* Loop/switch isn't completed */
            }
            ai2[k1] = _data[j + k1];
            k1++;
        } while(true);
_L4:
        if(k == 1)
        {
            directory.setInt(i, get16Bits(j));
            continue; /* Loop/switch isn't completed */
        }
        int ai1[] = new int[k];
        int j1 = 0;
        do
        {
            if(j1 >= k)
            {
                directory.setIntArray(i, ai1);
                continue; /* Loop/switch isn't completed */
            }
            ai1[j1] = get16Bits(j + j1 * 2);
            j1++;
        } while(true);
_L5:
        int ai[];
        int i1;
        if(k == 1)
        {
            directory.setInt(i, get32Bits(j));
            continue; /* Loop/switch isn't completed */
        }
        ai = new int[k];
        i1 = 0;
_L11:
label0:
        {
            if(i1 < k)
                break label0;
            directory.setIntArray(i, ai);
        }
        if(true) goto _L10; else goto _L9
_L9:
        ai[i1] = get32Bits(j + i1 * 4);
        i1++;
          goto _L11
        if(true) goto _L10; else goto _L12
_L12:
    }

    private String readString(int i, int j)
    {
        int k = 0;
        do
        {
            if(i + k >= _data.length || _data[i + k] == 0 || k >= j)
                return new String(_data, i, k);
            k++;
        } while(true);
    }

    private boolean setByteOrder(String s)
    {
        boolean flag;
        if("MM".equals(s))
        {
            _isMotorollaByteOrder = true;
        } else
        {
label0:
            {
                if(!"II".equals(s))
                    break label0;
                _isMotorollaByteOrder = false;
            }
        }
        flag = true;
_L2:
        return flag;
        flag = false;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void storeThumbnailBytes(ExifDirectory exifdirectory, int i)
    {
_L2:
        return;
        if(!exifdirectory.containsTag(259) || !exifdirectory.containsTag(514) || !exifdirectory.containsTag(513)) goto _L2; else goto _L1
_L1:
        int j;
        byte abyte0[];
        int k;
        j = exifdirectory.getInt(513);
        abyte0 = new byte[exifdirectory.getInt(514)];
        k = 0;
_L5:
        if(k < abyte0.length) goto _L4; else goto _L3
_L3:
        exifdirectory.setByteArray(61441, abyte0);
          goto _L2
        Throwable throwable;
        throwable;
        exifdirectory.addError((new StringBuilder("Unable to extract thumbnail: ")).append(throwable.getMessage()).toString());
          goto _L2
_L4:
        abyte0[k] = _data[k + (i + j)];
        k++;
          goto _L5
    }

    public Metadata extract()
    {
        return extract(new Metadata());
    }

    public Metadata extract(Metadata metadata)
    {
        _metadata = metadata;
        Metadata metadata1;
        if(_data == null)
        {
            metadata1 = _metadata;
        } else
        {
            ExifDirectory exifdirectory = (ExifDirectory)_metadata.getDirectory(com/drew/metadata/exif/ExifDirectory);
            if(_data.length <= 14)
            {
                exifdirectory.addError("Exif data segment must contain at least 14 bytes");
                metadata1 = _metadata;
            } else
            if(!"Exif\uFFFD\uFFFD\uFFFD\uFFFD".equals(new String(_data, 0, 6)))
            {
                exifdirectory.addError("Exif data segment doesn't begin with 'Exif'");
                metadata1 = _metadata;
            } else
            {
                String s = new String(_data, 6, 2);
                if(!setByteOrder(s))
                {
                    exifdirectory.addError((new StringBuilder("Unclear distinction between Motorola/Intel byte ordering: ")).append(s).toString());
                    metadata1 = _metadata;
                } else
                if(get16Bits(8) != 42)
                {
                    exifdirectory.addError("Invalid Exif start - should have 0x2A at offset 8 in Exif header");
                    metadata1 = _metadata;
                } else
                {
                    int i = 6 + get32Bits(10);
                    if(i >= _data.length - 1)
                    {
                        exifdirectory.addError("First exif directory offset is beyond end of Exif data segment");
                        i = 14;
                    }
                    processDirectory(exifdirectory, new HashMap(), i, 6);
                    storeThumbnailBytes(exifdirectory, 6);
                    metadata1 = _metadata;
                }
            }
        }
        return metadata1;
    }

    private static final int BYTES_PER_FORMAT[];
    private static final int FMT_BYTE = 1;
    private static final int FMT_DOUBLE = 12;
    private static final int FMT_SBYTE = 6;
    private static final int FMT_SINGLE = 11;
    private static final int FMT_SLONG = 9;
    private static final int FMT_SRATIONAL = 10;
    private static final int FMT_SSHORT = 8;
    private static final int FMT_STRING = 2;
    private static final int FMT_ULONG = 4;
    private static final int FMT_UNDEFINED = 7;
    private static final int FMT_URATIONAL = 5;
    private static final int FMT_USHORT = 3;
    private static final int MAX_FORMAT_CODE = 12;
    public static final int TAG_EXIF_OFFSET = 34665;
    public static final int TAG_GPS_INFO_OFFSET = 34853;
    public static final int TAG_INTEROP_OFFSET = 40965;
    public static final int TAG_MAKER_NOTE = 37500;
    public static final int TIFF_HEADER_START_OFFSET = 6;
    private final byte _data[];
    private boolean _isMotorollaByteOrder;
    private Metadata _metadata;

    static 
    {
        int ai[] = new int[13];
        ai[1] = 1;
        ai[2] = 1;
        ai[3] = 2;
        ai[4] = 4;
        ai[5] = 8;
        ai[6] = 1;
        ai[7] = 1;
        ai[8] = 2;
        ai[9] = 4;
        ai[10] = 8;
        ai[11] = 4;
        ai[12] = 8;
        BYTES_PER_FORMAT = ai;
    }
}
