// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata.exif;

import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package com.drew.metadata.exif:
//            ExifDescriptor

public class ExifDirectory extends Directory
{

    public ExifDirectory()
    {
        setDescriptor(new ExifDescriptor(this));
    }

    public boolean containsThumbnail()
    {
        return containsTag(61441);
    }

    public String getName()
    {
        return "Exif";
    }

    protected HashMap getTagNameMap()
    {
        return tagNameMap;
    }

    public byte[] getThumbnailData()
        throws MetadataException
    {
        byte abyte0[];
        if(!containsThumbnail())
            abyte0 = null;
        else
            abyte0 = getByteArray(61441);
        return abyte0;
    }

    public void writeThumbnail(String s)
        throws MetadataException, IOException
    {
        byte abyte0[];
        FileOutputStream fileoutputstream;
        abyte0 = getThumbnailData();
        if(abyte0 == null)
            throw new MetadataException("No thumbnail data exists.");
        fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream(s);
        fileoutputstream1.write(abyte0);
        if(fileoutputstream1 != null)
            fileoutputstream1.close();
        return;
        Exception exception;
        exception;
_L2:
        if(fileoutputstream != null)
            fileoutputstream.close();
        throw exception;
        exception;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int COMPRESSION_JPEG = 6;
    public static final int COMPRESSION_NONE = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_MONOCHROME = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    public static final int TAG_35MM_FILM_EQUIV_FOCAL_LENGTH = 41989;
    public static final int TAG_APERTURE = 37378;
    public static final int TAG_ARTIST = 315;
    public static final int TAG_BATTERY_LEVEL = 33423;
    public static final int TAG_BITS_PER_SAMPLE = 258;
    public static final int TAG_BRIGHTNESS_VALUE = 37379;
    public static final int TAG_CFA_PATTERN = 41730;
    public static final int TAG_CFA_PATTERN_2 = 33422;
    public static final int TAG_CFA_REPEAT_PATTERN_DIM = 33421;
    public static final int TAG_COLOR_SPACE = 40961;
    public static final int TAG_COMPONENTS_CONFIGURATION = 37121;
    public static final int TAG_COMPRESSION = 259;
    public static final int TAG_COMPRESSION_LEVEL = 37122;
    public static final int TAG_CONTRAST = 41992;
    public static final int TAG_COPYRIGHT = 33432;
    public static final int TAG_CUSTOM_RENDERED = 41985;
    public static final int TAG_DATETIME = 306;
    public static final int TAG_DATETIME_DIGITIZED = 36868;
    public static final int TAG_DATETIME_ORIGINAL = 36867;
    public static final int TAG_DEVICE_SETTING_DESCRIPTION = 41995;
    public static final int TAG_DIGITAL_ZOOM_RATIO = 41988;
    public static final int TAG_DOCUMENT_NAME = 269;
    public static final int TAG_EXIF_IMAGE_HEIGHT = 40963;
    public static final int TAG_EXIF_IMAGE_WIDTH = 40962;
    public static final int TAG_EXIF_VERSION = 36864;
    public static final int TAG_EXPOSURE_BIAS = 37380;
    public static final int TAG_EXPOSURE_INDEX = 41493;
    public static final int TAG_EXPOSURE_INDEX_2 = 37397;
    public static final int TAG_EXPOSURE_MODE = 41986;
    public static final int TAG_EXPOSURE_PROGRAM = 34850;
    public static final int TAG_EXPOSURE_TIME = 33434;
    public static final int TAG_FILE_SOURCE = 41728;
    public static final int TAG_FILL_ORDER = 266;
    public static final int TAG_FLASH = 37385;
    public static final int TAG_FLASHPIX_VERSION = 40960;
    public static final int TAG_FLASH_ENERGY = 37387;
    public static final int TAG_FLASH_ENERGY_2 = 41483;
    public static final int TAG_FNUMBER = 33437;
    public static final int TAG_FOCAL_LENGTH = 37386;
    public static final int TAG_FOCAL_PLANE_UNIT = 41488;
    public static final int TAG_FOCAL_PLANE_X_RES = 41486;
    public static final int TAG_FOCAL_PLANE_Y_RES = 41487;
    public static final int TAG_GAIN_CONTROL = 41991;
    public static final int TAG_GPS_INFO = 34853;
    public static final int TAG_IMAGE_DESCRIPTION = 270;
    public static final int TAG_IMAGE_HISTORY = 37395;
    public static final int TAG_IMAGE_NUMBER = 37393;
    public static final int TAG_IMAGE_UNIQUE_ID = 42016;
    public static final int TAG_INTERLACE = 34857;
    public static final int TAG_INTER_COLOR_PROFILE = 34675;
    public static final int TAG_IPTC_NAA = 33723;
    public static final int TAG_ISO_EQUIVALENT = 34855;
    public static final int TAG_JPEG_TABLES = 347;
    public static final int TAG_LIGHT_SOURCE = 37384;
    public static final int TAG_MAKE = 271;
    public static final int TAG_MAX_APERTURE = 37381;
    public static final int TAG_MAX_SAMPLE_VALUE = 281;
    public static final int TAG_METERING_MODE = 37383;
    public static final int TAG_MIN_SAMPLE_VALUE = 280;
    public static final int TAG_MODEL = 272;
    public static final int TAG_NEW_SUBFILE_TYPE = 254;
    public static final int TAG_NOISE = 37389;
    public static final int TAG_OECF = 34856;
    public static final int TAG_ORIENTATION = 274;
    public static final int TAG_PAGE_NAME = 285;
    public static final int TAG_PHOTOMETRIC_INTERPRETATION = 262;
    public static final int TAG_PLANAR_CONFIGURATION = 284;
    public static final int TAG_PREDICTOR = 317;
    public static final int TAG_PRIMARY_CHROMATICITIES = 319;
    public static final int TAG_REFERENCE_BLACK_WHITE = 532;
    public static final int TAG_RELATED_SOUND_FILE = 40964;
    public static final int TAG_RESOLUTION_UNIT = 296;
    public static final int TAG_ROWS_PER_STRIP = 278;
    public static final int TAG_SAMPLES_PER_PIXEL = 277;
    public static final int TAG_SATURATION = 41993;
    public static final int TAG_SCENE_CAPTURE_TYPE = 41990;
    public static final int TAG_SCENE_TYPE = 41729;
    public static final int TAG_SECURITY_CLASSIFICATION = 37394;
    public static final int TAG_SELF_TIMER_MODE = 34859;
    public static final int TAG_SENSING_METHOD = 41495;
    public static final int TAG_SHARPNESS = 41994;
    public static final int TAG_SHUTTER_SPEED = 37377;
    public static final int TAG_SOFTWARE = 305;
    public static final int TAG_SPATIAL_FREQ_RESPONSE = 37388;
    public static final int TAG_SPATIAL_FREQ_RESPONSE_2 = 41484;
    public static final int TAG_SPECTRAL_SENSITIVITY = 34852;
    public static final int TAG_STRIP_BYTE_COUNTS = 279;
    public static final int TAG_STRIP_OFFSETS = 273;
    public static final int TAG_SUBFILE_TYPE = 255;
    public static final int TAG_SUBJECT_DISTANCE = 37382;
    public static final int TAG_SUBJECT_DISTANCE_RANGE = 41996;
    public static final int TAG_SUBJECT_LOCATION = 37396;
    public static final int TAG_SUBJECT_LOCATION_2 = 41492;
    public static final int TAG_SUBSECOND_TIME = 37520;
    public static final int TAG_SUBSECOND_TIME_DIGITIZED = 37522;
    public static final int TAG_SUBSECOND_TIME_ORIGINAL = 37521;
    public static final int TAG_SUB_IFDS = 330;
    public static final int TAG_THRESHOLDING = 263;
    public static final int TAG_THUMBNAIL_DATA = 61441;
    public static final int TAG_THUMBNAIL_IMAGE_HEIGHT = 257;
    public static final int TAG_THUMBNAIL_IMAGE_WIDTH = 256;
    public static final int TAG_THUMBNAIL_LENGTH = 514;
    public static final int TAG_THUMBNAIL_OFFSET = 513;
    public static final int TAG_TIFF_EP_STANDARD_ID = 37398;
    public static final int TAG_TILE_BYTE_COUNTS = 325;
    public static final int TAG_TILE_LENGTH = 323;
    public static final int TAG_TILE_OFFSETS = 324;
    public static final int TAG_TILE_WIDTH = 322;
    public static final int TAG_TIME_ZONE_OFFSET = 34858;
    public static final int TAG_TRANSFER_FUNCTION = 301;
    public static final int TAG_USER_COMMENT = 37510;
    public static final int TAG_WHITE_BALANCE = 37384;
    public static final int TAG_WHITE_BALANCE_MODE = 41987;
    public static final int TAG_WHITE_POINT = 318;
    public static final int TAG_WIN_AUTHOR = 40093;
    public static final int TAG_WIN_COMMENT = 40092;
    public static final int TAG_WIN_KEYWORDS = 40094;
    public static final int TAG_WIN_SUBJECT = 40095;
    public static final int TAG_WIN_TITLE = 40091;
    public static final int TAG_X_RESOLUTION = 282;
    public static final int TAG_YCBCR_COEFFICIENTS = 529;
    public static final int TAG_YCBCR_POSITIONING = 531;
    public static final int TAG_YCBCR_SUBSAMPLING = 530;
    public static final int TAG_Y_RESOLUTION = 283;
    protected static final HashMap tagNameMap;

    static 
    {
        tagNameMap = new HashMap();
        tagNameMap.put(new Integer(266), "Fill Order");
        tagNameMap.put(new Integer(269), "Document Name");
        tagNameMap.put(new Integer(4096), "Related Image File Format");
        tagNameMap.put(new Integer(4097), "Related Image Width");
        tagNameMap.put(new Integer(4098), "Related Image Length");
        tagNameMap.put(new Integer(342), "Transfer Range");
        tagNameMap.put(new Integer(512), "JPEG Proc");
        tagNameMap.put(new Integer(34665), "Exif Offset");
        tagNameMap.put(new Integer(37122), "Compressed Bits Per Pixel");
        tagNameMap.put(new Integer(37500), "Maker Note");
        tagNameMap.put(new Integer(40965), "Interoperability Offset");
        tagNameMap.put(new Integer(254), "New Subfile Type");
        tagNameMap.put(new Integer(255), "Subfile Type");
        tagNameMap.put(new Integer(256), "Thumbnail Image Width");
        tagNameMap.put(new Integer(257), "Thumbnail Image Height");
        tagNameMap.put(new Integer(258), "Bits Per Sample");
        tagNameMap.put(new Integer(259), "Compression");
        tagNameMap.put(new Integer(262), "Photometric Interpretation");
        tagNameMap.put(new Integer(263), "Thresholding");
        tagNameMap.put(new Integer(270), "Image Description");
        tagNameMap.put(new Integer(271), "Make");
        tagNameMap.put(new Integer(272), "Model");
        tagNameMap.put(new Integer(273), "Strip Offsets");
        tagNameMap.put(new Integer(274), "Orientation");
        tagNameMap.put(new Integer(277), "Samples Per Pixel");
        tagNameMap.put(new Integer(278), "Rows Per Strip");
        tagNameMap.put(new Integer(279), "Strip Byte Counts");
        tagNameMap.put(new Integer(282), "X Resolution");
        tagNameMap.put(new Integer(283), "Y Resolution");
        tagNameMap.put(new Integer(285), "Page Name");
        tagNameMap.put(new Integer(284), "Planar Configuration");
        tagNameMap.put(new Integer(296), "Resolution Unit");
        tagNameMap.put(new Integer(301), "Transfer Function");
        tagNameMap.put(new Integer(305), "Software");
        tagNameMap.put(new Integer(306), "Date/Time");
        tagNameMap.put(new Integer(315), "Artist");
        tagNameMap.put(new Integer(317), "Predictor");
        tagNameMap.put(new Integer(318), "White Point");
        tagNameMap.put(new Integer(319), "Primary Chromaticities");
        tagNameMap.put(new Integer(322), "Tile Width");
        tagNameMap.put(new Integer(323), "Tile Length");
        tagNameMap.put(new Integer(324), "Tile Offsets");
        tagNameMap.put(new Integer(325), "Tile Byte Counts");
        tagNameMap.put(new Integer(330), "Sub IFDs");
        tagNameMap.put(new Integer(347), "JPEG Tables");
        tagNameMap.put(new Integer(513), "Thumbnail Offset");
        tagNameMap.put(new Integer(514), "Thumbnail Length");
        tagNameMap.put(new Integer(61441), "Thumbnail Data");
        tagNameMap.put(new Integer(529), "YCbCr Coefficients");
        tagNameMap.put(new Integer(530), "YCbCr Sub-Sampling");
        tagNameMap.put(new Integer(531), "YCbCr Positioning");
        tagNameMap.put(new Integer(532), "Reference Black/White");
        tagNameMap.put(new Integer(33421), "CFA Repeat Pattern Dim");
        tagNameMap.put(new Integer(33422), "CFA Pattern");
        tagNameMap.put(new Integer(33423), "Battery Level");
        tagNameMap.put(new Integer(33432), "Copyright");
        tagNameMap.put(new Integer(33434), "Exposure Time");
        tagNameMap.put(new Integer(33437), "F-Number");
        tagNameMap.put(new Integer(33723), "IPTC/NAA");
        tagNameMap.put(new Integer(34675), "Inter Color Profile");
        tagNameMap.put(new Integer(34850), "Exposure Program");
        tagNameMap.put(new Integer(34852), "Spectral Sensitivity");
        tagNameMap.put(new Integer(34853), "GPS Info");
        tagNameMap.put(new Integer(34855), "ISO Speed Ratings");
        tagNameMap.put(new Integer(34856), "OECF");
        tagNameMap.put(new Integer(34857), "Interlace");
        tagNameMap.put(new Integer(34858), "Time Zone Offset");
        tagNameMap.put(new Integer(34859), "Self Timer Mode");
        tagNameMap.put(new Integer(36864), "Exif Version");
        tagNameMap.put(new Integer(36867), "Date/Time Original");
        tagNameMap.put(new Integer(36868), "Date/Time Digitized");
        tagNameMap.put(new Integer(37121), "Components Configuration");
        tagNameMap.put(new Integer(37377), "Shutter Speed Value");
        tagNameMap.put(new Integer(37378), "Aperture Value");
        tagNameMap.put(new Integer(37379), "Brightness Value");
        tagNameMap.put(new Integer(37380), "Exposure Bias Value");
        tagNameMap.put(new Integer(37381), "Max Aperture Value");
        tagNameMap.put(new Integer(37382), "Subject Distance");
        tagNameMap.put(new Integer(37383), "Metering Mode");
        tagNameMap.put(new Integer(37384), "Light Source");
        tagNameMap.put(new Integer(37385), "Flash");
        tagNameMap.put(new Integer(37386), "Focal Length");
        tagNameMap.put(new Integer(37387), "Flash Energy");
        tagNameMap.put(new Integer(37388), "Spatial Frequency Response");
        tagNameMap.put(new Integer(37389), "Noise");
        tagNameMap.put(new Integer(37393), "Image Number");
        tagNameMap.put(new Integer(37394), "Security Classification");
        tagNameMap.put(new Integer(37395), "Image History");
        tagNameMap.put(new Integer(37396), "Subject Location");
        tagNameMap.put(new Integer(41493), "Exposure Index");
        tagNameMap.put(new Integer(37398), "TIFF/EP Standard ID");
        tagNameMap.put(new Integer(37510), "User Comment");
        tagNameMap.put(new Integer(37520), "Sub-Sec Time");
        tagNameMap.put(new Integer(37521), "Sub-Sec Time Original");
        tagNameMap.put(new Integer(37522), "Sub-Sec Time Digitized");
        tagNameMap.put(new Integer(40960), "FlashPix Version");
        tagNameMap.put(new Integer(40961), "Color Space");
        tagNameMap.put(new Integer(40962), "Exif Image Width");
        tagNameMap.put(new Integer(40963), "Exif Image Height");
        tagNameMap.put(new Integer(40964), "Related Sound File");
        tagNameMap.put(new Integer(41483), "Flash Energy");
        tagNameMap.put(new Integer(41484), "Spatial Frequency Response");
        tagNameMap.put(new Integer(41486), "Focal Plane X Resolution");
        tagNameMap.put(new Integer(41487), "Focal Plane Y Resolution");
        tagNameMap.put(new Integer(41488), "Focal Plane Resolution Unit");
        tagNameMap.put(new Integer(41492), "Subject Location");
        tagNameMap.put(new Integer(37397), "Exposure Index");
        tagNameMap.put(new Integer(41495), "Sensing Method");
        tagNameMap.put(new Integer(41728), "File Source");
        tagNameMap.put(new Integer(41729), "Scene Type");
        tagNameMap.put(new Integer(41730), "CFA Pattern");
        tagNameMap.put(new Integer(41985), "Custom Rendered");
        tagNameMap.put(new Integer(41986), "Exposure Mode");
        tagNameMap.put(new Integer(41987), "White Balance");
        tagNameMap.put(new Integer(41988), "Digital Zoom Ratio");
        tagNameMap.put(new Integer(41989), "Focal Length 35");
        tagNameMap.put(new Integer(41990), "Scene Capture Type");
        tagNameMap.put(new Integer(41991), "Gain Control");
        tagNameMap.put(new Integer(41992), "Contrast");
        tagNameMap.put(new Integer(41993), "Saturation");
        tagNameMap.put(new Integer(41994), "Sharpness");
        tagNameMap.put(new Integer(41995), "Device Setting Description");
        tagNameMap.put(new Integer(41996), "Subject Distance Range");
        tagNameMap.put(new Integer(40093), "Windows XP Author");
        tagNameMap.put(new Integer(40092), "Windows XP Comment");
        tagNameMap.put(new Integer(40094), "Windows XP Keywords");
        tagNameMap.put(new Integer(40095), "Windows XP Subject");
        tagNameMap.put(new Integer(40091), "Windows XP Title");
        tagNameMap.put(new Integer(280), "Minimum sample value");
        tagNameMap.put(new Integer(281), "Maximum sample value");
    }
}
