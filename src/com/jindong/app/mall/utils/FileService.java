// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.database.table.CacheFileTable;
import com.jindong.app.mall.entity.CacheFile;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Referenced classes of package com.jindong.app.mall.utils:
//            Log, FileGuider

public class FileService
{
    public static class Directory
    {

        public File getDir()
        {
            return dir;
        }

        public String getPath()
        {
            if(path == null && dir != null)
                path = dir.getAbsolutePath();
            return path;
        }

        public int getSpace()
        {
            return space;
        }

        public void setDir(File file)
        {
            dir = file;
        }

        public void setPath(String s)
        {
            if(getPath() == null || !getPath().equals(s))
            {
                dir = new File(s);
                path = s;
            }
        }

        public void setSpace(int i)
        {
            space = i;
        }

        public static final int EXTERNAL = 2;
        public static final int INTERNAL = 1;
        private File dir;
        private String path;
        private int space;

        public Directory(File file, int i)
        {
            dir = file;
            space = i;
        }

        public Directory(String s, int i)
        {
            this(new File(s), i);
        }
    }


    public FileService()
    {
    }

    public static void clearCacheFiles()
    {
        ArrayList arraylist = CacheFileTable.getListByClean();
        boolean flag = externalMemoryAvailable();
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                return;
            CacheFile cachefile = (CacheFile)arraylist.get(i);
            Directory directory = cachefile.getDirectory();
            if(directory.getSpace() == 1 || directory.getSpace() == 2 && flag)
            {
                boolean flag1 = cachefile.getFile().delete();
                if(Log.D)
                    Log.d("Temp", (new StringBuilder("cacheFile.getName() -->> ")).append(cachefile.getName()).toString());
                if(flag1)
                    CacheFileTable.delete(cachefile);
            }
            i++;
        } while(true);
    }

    public static boolean externalMemoryAvailable()
    {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String formatSize(long l)
    {
        String s = null;
        if(l >= 1024L)
        {
            s = "KiB";
            l /= 1024L;
            if(l >= 1024L)
            {
                s = "MiB";
                l /= 1024L;
            }
        }
        StringBuilder stringbuilder = new StringBuilder(Long.toString(l));
        int i = stringbuilder.length() - 3;
        do
        {
            if(i <= 0)
            {
                if(s != null)
                    stringbuilder.append(s);
                return stringbuilder.toString();
            }
            stringbuilder.insert(i, ',');
            i -= 3;
        } while(true);
    }

    public static String formatSize2(long l)
    {
        String s = null;
        float f = Long.valueOf(l).floatValue();
        if(f >= 1024F)
        {
            s = "KB";
            f /= 1024F;
            if(f >= 1024F)
            {
                s = "MB";
                f /= 1024F;
            }
        }
        StringBuilder stringbuilder = new StringBuilder((new DecimalFormat(".00")).format(f));
        int i = stringbuilder.indexOf(".") - 3;
        do
        {
            if(i <= 0)
            {
                if(s != null)
                    stringbuilder.append(s);
                return stringbuilder.toString();
            }
            stringbuilder.insert(i, ',');
            i -= 3;
        } while(true);
    }

    public static long getAvailableExternalMemorySize()
    {
        long l;
        if(externalMemoryAvailable())
        {
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            l = (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
        } else
        {
            l = -1L;
        }
        return l;
    }

    public static long getAvailableInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
    }

    public static Directory getDirectory(int i)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 35
    //                   2 28;
           goto _L1 _L2 _L3
_L1:
        Directory directory = null;
_L5:
        return directory;
_L3:
        directory = getJsonDirectory();
        continue; /* Loop/switch isn't completed */
_L2:
        directory = getImageDirectory();
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static Directory getDirectoryByBigSize(String s)
    {
        if(Log.D)
            Log.d("Temp", "getDirectoryByBigSize() -->> ");
        Directory directory;
        if(getTotalInternalMemorySize() > 0x20000000L)
        {
            if(Log.D)
                Log.d("Temp", "getDirectoryByBigSize() -->> INTERNAL");
            directory = new Directory(getInternalDirectory(s), 1);
        } else
        if(getTotalExternalMemorySize() > 0x20000000L)
        {
            if(Log.D)
                Log.d("Temp", "getDirectoryByBigSize() -->> EXTERNAL");
            directory = new Directory(getExternalDirectory(s), 2);
        } else
        {
            directory = null;
        }
        return directory;
    }

    public static File getExternalDirectory(String s)
    {
        if(Log.D)
            Log.d("Temp", "getExternalDirectory() -->> ");
        File file = Environment.getExternalStorageDirectory();
        StringBuilder stringbuilder = new StringBuilder("/jindong");
        String s1;
        File file1;
        if(s != null)
            s1 = s;
        else
            s1 = "";
        file1 = new File(file, stringbuilder.append(s1).toString());
        if(!file1.exists())
            file1.mkdirs();
        return file1;
    }

    private static Directory getImageDirectory()
    {
        Directory directory;
        if(!externalMemoryAvailable())
            directory = null;
        else
            directory = new Directory(getExternalDirectory("/image"), 2);
        return directory;
    }

    public static File getInternalDirectory(String s)
    {
        return getInternalDirectory(s, 2);
    }

    public static File getInternalDirectory(String s, int i)
    {
        File file;
        if(Log.D)
            Log.d("Temp", "getInternalDirectory() -->> ");
        file = null;
        i;
        JVM INSTR tableswitch 1 2: default 40
    //                   1 156
    //                   2 166;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_166;
_L4:
        StringBuilder stringbuilder = new StringBuilder("/jindong");
        String s1;
        File file1;
        if(s != null)
            s1 = s;
        else
            s1 = "";
        file1 = new File(file, stringbuilder.append(s1).toString());
        if(!file1.exists())
            file1.mkdirs();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getInternalDirectory() dir.getAbsolutePath() -->> ")).append(file1.getAbsolutePath()).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getInternalDirectory() dir.exists() -->> ")).append(file1.exists()).toString());
        return file1;
_L2:
        file = MyApplication.getInstance().getFilesDir();
          goto _L4
        file = MyApplication.getInstance().getCacheDir();
          goto _L4
    }

    private static Directory getJsonDirectory()
    {
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getJsonDirectory() jsonDirState -->> ")).append(jsonDirState).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getJsonDirectory() jsonDir -->> ")).append(jsonDir).toString());
        Directory directory;
        if(jsonDirState == -1)
            directory = null;
        else
        if(jsonDir != null)
        {
            directory = jsonDir;
        } else
        {
            SharedPreferences sharedpreferences = MyApplication.getInstance().getSharedPreferences("jdAndroidClient", 0);
            String s = sharedpreferences.getString("jsonFileCachePath", null);
            jsonDirState = sharedpreferences.getInt("jsonFileCachePathState", 0);
            if(s == null)
            {
                if(Log.D)
                    Log.d("Temp", "getJsonDirectory() no preferences -->> ");
                Directory directory1 = getDirectoryByBigSize("/json");
                if(directory1 == null)
                {
                    if(Log.D)
                        Log.d("Temp", "getJsonDirectory() no big size -->> ");
                    jsonDirState = -1;
                    directory = null;
                } else
                {
                    if(Log.D)
                        Log.d("Temp", "getJsonDirectory() has big size -->> ");
                    jsonDir = directory1;
                    jsonDirState = directory1.getSpace();
                    String s1 = jsonDir.getDir().getAbsolutePath();
                    android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("jsonFileCachePath", s1);
                    editor.putInt("jsonFileCachePathState", jsonDirState);
                    editor.commit();
                    directory = jsonDir;
                }
            } else
            {
                if(Log.D)
                    Log.d("Temp", "getJsonDirectory() is preferences -->> ");
                if(jsonDirState == 2 && !externalMemoryAvailable())
                {
                    if(Log.D)
                        Log.d("Temp", "getJsonDirectory() no external -->> ");
                    jsonDirState = -1;
                    directory = null;
                } else
                {
                    if(Log.D)
                        Log.d("Temp", (new StringBuilder("getJsonDirectory() jsonFileCachePath -->> ")).append(s).toString());
                    File file = new File(s);
                    int i;
                    File file1;
                    if(jsonDirState == 1)
                        i = 1;
                    else
                        i = 2;
                    jsonDir = new Directory(file, i);
                    file1 = jsonDir.getDir();
                    if(!file1.exists())
                        file1.mkdirs();
                    directory = jsonDir;
                }
            }
        }
        return directory;
    }

    public static long getTotalExternalMemorySize()
    {
        long l1;
        if(externalMemoryAvailable())
        {
            StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long l = (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
            if(Log.D)
                Log.d("Temp", (new StringBuilder("getTotalExternalMemorySize() -->> ")).append(l).toString());
            l1 = l;
        } else
        {
            l1 = -1L;
        }
        return l1;
    }

    public static long getTotalInternalMemorySize()
    {
        StatFs statfs = new StatFs(Environment.getDataDirectory().getPath());
        long l = (long)statfs.getBlockSize() * (long)statfs.getBlockCount();
        if(Log.D)
            Log.d("Temp", (new StringBuilder("getTotalInternalMemorySize() -->> ")).append(l).toString());
        return l;
    }

    public static boolean isReady()
    {
        return externalMemoryAvailable();
    }

    public static FileOutputStream openFileOutput(FileGuider fileguider)
        throws FileNotFoundException
    {
        long l = fileguider.getAvailableSize();
        if(0L == l) goto _L2; else goto _L1
_L1:
        if(1 != fileguider.getSpace() || getAvailableInternalMemorySize() >= l) goto _L4; else goto _L3
_L3:
        FileOutputStream fileoutputstream = null;
_L6:
        return fileoutputstream;
_L4:
        if(2 == fileguider.getSpace() && getAvailableExternalMemorySize() < l)
        {
            fileoutputstream = null;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        fileoutputstream = MyApplication.getInstance().openFileOutput(fileguider.getFileName(), fileguider.getMode());
        if(true) goto _L6; else goto _L5
_L5:
    }

    private byte[] readInputStream(FileInputStream fileinputstream)
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        do
        {
            int i = fileinputstream.read(abyte0);
            if(i == -1)
            {
                fileinputstream.close();
                bytearrayoutputstream.close();
                return bytearrayoutputstream.toByteArray();
            }
            bytearrayoutputstream.write(abyte0, 0, i);
        } while(true);
    }

    public static boolean saveToSDCard(Directory directory, String s, String s1)
    {
        return saveToSDCard(directory, s, s1, 0);
    }

    public static boolean saveToSDCard(Directory directory, String s, String s1, int i)
    {
        boolean flag;
        if(s1 == null)
            flag = false;
        else
            flag = saveToSDCard(directory, s, s1.getBytes(), i);
        return flag;
    }

    public static boolean saveToSDCard(Directory directory, String s, byte abyte0[])
    {
        boolean flag = false;
        if(abyte0 != null)
            flag = saveToSDCard(directory, s, abyte0, 0);
        return flag;
    }

    public static boolean saveToSDCard(Directory directory, String s, byte abyte0[], int i)
    {
        File file;
        FileOutputStream fileoutputstream;
        file = new File(directory.getDir(), s);
        fileoutputstream = null;
        FileOutputStream fileoutputstream1 = new FileOutputStream(file);
        if(fileoutputstream1 == null)
            break MISSING_BLOCK_LABEL_39;
        fileoutputstream1.write(abyte0);
        Exception exception1;
        Exception exception2;
        boolean flag;
        IOException ioexception1;
        IOException ioexception2;
        Exception exception3;
        if(fileoutputstream1 != null)
            try
            {
                fileoutputstream1.close();
            }
            catch(IOException ioexception) { }
        flag = true;
        return flag;
        exception3;
        exception1 = exception3;
_L4:
        exception1.printStackTrace();
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception2) { }
        flag = false;
        break MISSING_BLOCK_LABEL_52;
        exception2;
_L2:
        if(fileoutputstream != null)
            try
            {
                fileoutputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException ioexception1) { }
        throw exception2;
        exception2;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        exception1 = exception;
        fileoutputstream = fileoutputstream1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String read(String s)
        throws Exception
    {
        return new String(readInputStream(MyApplication.getInstance().openFileInput(s)));
    }

    public byte[] readAsByteArray(String s)
        throws Exception
    {
        return readInputStream(MyApplication.getInstance().openFileInput(s));
    }

    public void save(String s, String s1)
        throws Exception
    {
        FileOutputStream fileoutputstream = MyApplication.getInstance().openFileOutput(s, 0);
        fileoutputstream.write(s1.getBytes());
        fileoutputstream.close();
    }

    public void saveAppend(String s, String s1)
        throws Exception
    {
        FileOutputStream fileoutputstream = MyApplication.getInstance().openFileOutput(s, 32768);
        fileoutputstream.write(s1.getBytes());
        fileoutputstream.close();
    }

    public void saveReadable(String s, String s1)
        throws Exception
    {
        FileOutputStream fileoutputstream = MyApplication.getInstance().openFileOutput(s, 1);
        fileoutputstream.write(s1.getBytes());
        fileoutputstream.close();
    }

    public void saveReadableWriteable(String s, String s1)
        throws Exception
    {
        FileOutputStream fileoutputstream = MyApplication.getInstance().openFileOutput(s, 32771);
        fileoutputstream.write(s1.getBytes());
        fileoutputstream.close();
    }

    public void saveToSDCard(String s, String s1)
        throws Exception
    {
        saveToSDCard(null, s, s1);
    }

    public void saveWriteable(String s, String s1)
        throws Exception
    {
        FileOutputStream fileoutputstream = MyApplication.getInstance().openFileOutput(s, 2);
        fileoutputstream.write(s1.getBytes());
        fileoutputstream.close();
    }

    private static final long BIG_SIZE_THRESHOLD = 0x20000000L;
    private static final int ERROR = -1;
    private static final String IMAGE_CHILD_DIR = "/image";
    public static final int IMAGE_DIR = 1;
    public static final int INTERNAL_TYPE_CACHE = 2;
    public static final int INTERNAL_TYPE_FILE = 1;
    private static final String JSON_CHILD_DIR = "/json";
    public static final int JSON_DIR = 2;
    private static final String SHARED_PREFERENCES_JSON_DIR = "jsonFileCachePath";
    private static final String SHARED_PREFERENCES_JSON_DIR_STATE = "jsonFileCachePathState";
    private static final String aplcationDir = "/jindong";
    private static Directory imageDir;
    private static Directory jsonDir;
    private static int jsonDirState;
}
