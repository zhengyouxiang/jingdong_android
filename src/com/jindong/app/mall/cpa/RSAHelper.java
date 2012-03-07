// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.cpa;

import android.util.Log;
import com.jindong.app.mall.utils.Base64;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.StringTokenizer;
import javax.crypto.Cipher;

public class RSAHelper
{

    public RSAHelper()
    {
    }

    public static byte[] dencrypt(byte abyte0[])
    {
        byte abyte2[];
        cipher.init(2, privateKey);
        abyte2 = cipher.doFinal(abyte0);
        byte abyte1[] = abyte2;
_L2:
        return abyte1;
        Exception exception;
        exception;
        exception.printStackTrace();
        abyte1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String dencryptBySeg(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s, "|");
        StringBuffer stringbuffer = new StringBuffer();
        Log.i("test", (new StringBuilder("tokenizer counts:")).append(stringtokenizer.countTokens()).toString());
        do
        {
            if(!stringtokenizer.hasMoreTokens())
                return stringbuffer.toString();
            String s1 = (String)stringtokenizer.nextElement();
            Log.i("test", (new StringBuilder("dencryptBySeg:")).append(s1).toString());
            try
            {
                byte abyte0[] = Base64.decode(getFromatBase64String(s1));
                Log.i("test", (new StringBuilder("base64 decode:")).append(new String(abyte0)).toString());
                byte abyte1[] = dencrypt(abyte0);
                Log.i("test", (new StringBuilder("Rsa decode:")).append(new String(abyte1)).toString());
                stringbuffer.append(new String(abyte1));
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        } while(true);
    }

    public static String dencryptBySegBaseZip(String s, int i)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s, "|");
        StringBuffer stringbuffer = new StringBuffer();
        do
        {
            if(!stringtokenizer.hasMoreTokens())
                return stringbuffer.toString();
            String s1 = (String)stringtokenizer.nextElement();
            try
            {
                stringbuffer.append(new String(dencrypt(Base64.decode(getFromatBase64String(s1), 2))));
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        } while(true);
    }

    public static byte[] encrypt(byte abyte0[])
    {
        byte abyte2[];
        cipher.init(1, publicKey);
        abyte2 = cipher.doFinal(abyte0);
        byte abyte1[] = abyte2;
_L2:
        return abyte1;
        Exception exception;
        exception;
        exception.printStackTrace();
        abyte1 = null;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String encryptBySeg(byte abyte0[])
        throws IOException
    {
        return encryptBySeg(abyte0, 0);
    }

    public static String encryptBySeg(byte abyte0[], int i)
        throws IOException
    {
        int j = 1 + abyte0.length / 117;
        StringBuffer stringbuffer = new StringBuffer();
        int k = 0;
        do
        {
            if(k >= j)
            {
                stringbuffer.deleteCharAt(stringbuffer.length() - 1);
                return stringbuffer.toString();
            }
            byte abyte1[];
            if(k == j - 1)
            {
                int l = abyte0.length - k * 117;
                abyte1 = new byte[l];
                System.arraycopy(abyte0, k * 117, abyte1, 0, l);
            } else
            {
                abyte1 = new byte[117];
                System.arraycopy(abyte0, k * 117, abyte1, 0, 117);
            }
            stringbuffer.append(Base64.encodeBytes(encrypt(abyte1), i)).append("|");
            k++;
        } while(true);
    }

    public static String getFromatBase64String(String s)
    {
        String s1 = s.substring(s.length() - 172, s.length());
        Log.i("reg", (new StringBuilder("regleng:")).append(s.length()).append("reg:").append(s).toString());
        return s1;
    }

    public static PrivateKey getPrivateKey()
        throws Exception
    {
        RSAPrivateKeySpec rsaprivatekeyspec = new RSAPrivateKeySpec(new BigInteger("151271798880033854287265892076354612533153713832406385904911178209493654664939238905534582371895950811379233854875946546459589151862470463852440833899497247142453992630018166245597346904446766965609150343967974904481793067794500077913992375940130870900414482641497283447201731956542465730058686924517791676251", 10), new BigInteger("10181422781937368681220224452580987776122511431935312391878834964708126870730228463498680788599310908784256229822204254336226066937231750248762020207374189368257192202796979116569703068970833761011754528432893872901525616312296004047864321453311474491486232253554750079150960526505082970220415017289481286449", 10));
        return (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(rsaprivatekeyspec);
    }

    public static PrivateKey getPrivateKey(String s)
        throws Exception
    {
        PKCS8EncodedKeySpec pkcs8encodedkeyspec = new PKCS8EncodedKeySpec(Base64.decode(s));
        return KeyFactory.getInstance("RSA").generatePrivate(pkcs8encodedkeyspec);
    }

    public static PublicKey getPublicKey()
        throws Exception
    {
        KeyFactory keyfactory = KeyFactory.getInstance("RSA");
        BigInteger biginteger = new BigInteger("65537", 10);
        return (RSAPublicKey)keyfactory.generatePublic(new RSAPublicKeySpec(new BigInteger("151271798880033854287265892076354612533153713832406385904911178209493654664939238905534582371895950811379233854875946546459589151862470463852440833899497247142453992630018166245597346904446766965609150343967974904481793067794500077913992375940130870900414482641497283447201731956542465730058686924517791676251", 10), biginteger));
    }

    public static RSAPublicKey getPublicKey(String s)
        throws Exception
    {
        byte abyte0[] = Base64.decode(s);
        int i = 0;
        int j = abyte0.length;
        do
        {
            if(i >= j)
            {
                X509EncodedKeySpec x509encodedkeyspec = new X509EncodedKeySpec(abyte0);
                return (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(x509encodedkeyspec);
            }
            abyte0[i] = (byte)(-1 ^ abyte0[i]);
            i++;
        } while(true);
    }

    public static void init()
    {
        publicKey = getPublicKey("z35gz/L59tV5t3kI8v7+/vr//H5y/89+dv1+fv9RH8n/YuxVfsaY7SBdsGEyLx3TpHJIbcMqCppBVAIzlgJsnrdvlBQBbCV/1THP2uA2HgEPY95bUufkqDZn35FMEQzM1j69oqYu0/ptnimGf3lLXG/G6TIcOiG7PtFWJiJfeF3jwFnneJ5UHb7t5c0B2tEr1sXK75UK54wAQxii2P38/v/+");
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public static void init(String s)
    {
        publicKey = getPublicKey(s);
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    public static void init(String s, String s1)
    {
        privateKey = getPrivateKey(s);
        publicKey = getPublicKey(s1);
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
_L1:
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
          goto _L1
    }

    private static final int PT_LEN = 117;
    private static final String PUBLIC_KEY = "z35gz/L59tV5t3kI8v7+/vr//H5y/89+dv1+fv9RH8n/YuxVfsaY7SBdsGEyLx3TpHJIbcMqCppBVAIzlgJsnrdvlBQBbCV/1THP2uA2HgEPY95bUufkqDZn35FMEQzM1j69oqYu0/ptnimGf3lLXG/G6TIcOiG7PtFWJiJfeF3jwFnneJ5UHb7t5c0B2tEr1sXK75UK54wAQxii2P38/v/+";
    public static final String RAS_D1 = "10181422781937368681220224452580987776122511431935312391878834964708126870730228463498680788599310908784256229822204254336226066937231750248762020207374189368257192202796979116569703068970833761011754528432893872901525616312296004047864321453311474491486232253554750079150960526505082970220415017289481286449";
    public static final String RAS_E1 = "65537";
    public static final String RAS_N1 = "151271798880033854287265892076354612533153713832406385904911178209493654664939238905534582371895950811379233854875946546459589151862470463852440833899497247142453992630018166245597346904446766965609150343967974904481793067794500077913992375940130870900414482641497283447201731956542465730058686924517791676251";
    private static final String RSA_N = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMmJVS+HQCMeVKDPsGZNwjdQtj/m/QliM1evvRZj+7yu9nmfk13b8nX/UMhg0tcs0Vbgt66VK759qpmgCH6uLKVe2Xr0QDA4guH32SU9RUWXKlC3r6RDzPAY3Bb9CG2q3tJC5RnkoVtWRM6owkd4CycMXiOXhT3Zut/Ne+8q30eQIDAQAB";
    private static final String SPRIT_CHAR = "|";
    private static Cipher cipher;
    private static PrivateKey privateKey;
    private static PublicKey publicKey;
}
