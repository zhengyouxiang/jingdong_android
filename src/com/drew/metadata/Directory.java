// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.drew.metadata;

import com.drew.lang.Rational;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.*;
import java.util.*;

// Referenced classes of package com.drew.metadata:
//            MetadataException, TagDescriptor, Tag

public abstract class Directory
    implements Serializable
{

    public Directory()
    {
    }

    public void addError(String s)
    {
        if(_errorList == null)
            _errorList = new ArrayList();
        _errorList.add(s);
    }

    public boolean containsTag(int i)
    {
        return _tagMap.containsKey(new Integer(i));
    }

    public boolean getBoolean(int i)
        throws MetadataException
    {
        Object obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        boolean flag;
        if(obj instanceof Boolean)
            flag = ((Boolean)obj).booleanValue();
        else
        if(obj instanceof String)
        {
            boolean flag1;
            try
            {
                flag1 = Boolean.getBoolean((String)obj);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new MetadataException((new StringBuilder("unable to parse string ")).append(obj).append(" as a boolean").toString(), numberformatexception);
            }
            flag = flag1;
        } else
        if(obj instanceof Number)
        {
            if(((Number)obj).doubleValue() != 0.0D)
                flag = true;
            else
                flag = false;
        } else
        {
            throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a boolean.  It is of type '").append(obj.getClass()).append("'.").toString());
        }
        return flag;
    }

    public byte[] getByteArray(int i)
        throws MetadataException
    {
        Object obj;
        obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(!(obj instanceof Rational[])) goto _L2; else goto _L1
_L1:
        Rational arational[];
        byte abyte3[];
        int l;
        arational = (Rational[])obj;
        abyte3 = new byte[arational.length];
        l = 0;
_L10:
        if(l < abyte3.length) goto _L4; else goto _L3
_L3:
        byte abyte1[] = abyte3;
_L6:
        return abyte1;
_L4:
        abyte3[l] = arational[l].byteValue();
        l++;
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        byte abyte0[];
        int j;
        if(obj instanceof byte[])
        {
            abyte1 = (byte[])obj;
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof int[])
        {
            int ai[] = (int[])obj;
            byte abyte2[] = new byte[ai.length];
            int k = 0;
            do
            {
                if(k >= ai.length)
                {
                    abyte1 = abyte2;
                    continue; /* Loop/switch isn't completed */
                }
                abyte2[k] = (byte)ai[k];
                k++;
            } while(true);
        }
        if(!(obj instanceof String))
            break MISSING_BLOCK_LABEL_228;
        s = (String)obj;
        abyte0 = new byte[s.length()];
        j = 0;
_L7:
label0:
        {
            if(j < s.length())
                break label0;
            abyte1 = abyte0;
        }
        if(true) goto _L6; else goto _L5
_L5:
        abyte0[j] = (byte)s.charAt(j);
        j++;
          goto _L7
        if(true) goto _L6; else goto _L8
_L8:
        throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a byte array.  It is of type '").append(obj.getClass()).append("'.").toString());
        if(true) goto _L10; else goto _L9
_L9:
    }

    public Date getDate(int i)
        throws MetadataException
    {
        Object obj;
        obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(!(obj instanceof Date)) goto _L2; else goto _L1
_L1:
        Date date1 = (Date)obj;
_L6:
        return date1;
_L2:
        if(!(obj instanceof String)) goto _L4; else goto _L3
_L3:
        String as[];
        String s;
        int j;
        as = new String[4];
        as[0] = "yyyy:MM:dd HH:mm:ss";
        as[1] = "yyyy:MM:dd HH:mm";
        as[2] = "yyyy-MM-dd HH:mm:ss";
        as[3] = "yyyy-MM-dd HH:mm";
        s = (String)obj;
        j = 0;
_L7:
        if(j < as.length) goto _L5; else goto _L4
_L4:
        throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a java.util.Date.  It is of type '").append(obj.getClass()).append("'.").toString());
_L5:
        Date date = (new SimpleDateFormat(as[j])).parse(s);
        date1 = date;
          goto _L6
        ParseException parseexception;
        parseexception;
        j++;
          goto _L7
    }

    public String getDescription(int i)
        throws MetadataException
    {
        if(_descriptor == null)
            throw new MetadataException("a descriptor must be set using setDescriptor(...) before descriptions can be provided");
        else
            return _descriptor.getDescription(i);
    }

    public double getDouble(int i)
        throws MetadataException
    {
        Object obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        double d;
        if(obj instanceof String)
        {
            double d1;
            try
            {
                d1 = Double.parseDouble((String)obj);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new MetadataException((new StringBuilder("unable to parse string ")).append(obj).append(" as a double").toString(), numberformatexception);
            }
            d = d1;
        } else
        if(obj instanceof Number)
            d = ((Number)obj).doubleValue();
        else
            throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a double.  It is of type '").append(obj.getClass()).append("'.").toString());
        return d;
    }

    public int getErrorCount()
    {
        return _errorList.size();
    }

    public Iterator getErrors()
    {
        return _errorList.iterator();
    }

    public float getFloat(int i)
        throws MetadataException
    {
        Object obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        float f;
        if(obj instanceof String)
        {
            float f1;
            try
            {
                f1 = Float.parseFloat((String)obj);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new MetadataException((new StringBuilder("unable to parse string ")).append(obj).append(" as a float").toString(), numberformatexception);
            }
            f = f1;
        } else
        if(obj instanceof Number)
            f = ((Number)obj).floatValue();
        else
            throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a float.  It is of type '").append(obj.getClass()).append("'.").toString());
        return f;
    }

    public int getInt(int i)
        throws MetadataException
    {
        Object obj;
        obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        int j;
        int i1;
        try
        {
            i1 = Integer.parseInt((String)obj);
        }
        catch(NumberFormatException numberformatexception)
        {
            byte abyte1[] = ((String)obj).getBytes();
            long l = 0L;
            int k = 0;
            do
            {
                if(k >= abyte1.length)
                {
                    j = (int)l;
                    continue; /* Loop/switch isn't completed */
                }
                l = (l << 8) + (long)abyte1[k];
                k++;
            } while(true);
        }
        j = i1;
_L4:
        return j;
_L2:
        if(obj instanceof Number)
        {
            j = ((Number)obj).intValue();
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof Rational[])
        {
            Rational arational[] = (Rational[])obj;
            if(arational.length != 1)
                break; /* Loop/switch isn't completed */
            j = arational[0].intValue();
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof byte[])
        {
            byte abyte0[] = (byte[])obj;
            if(abyte0.length != 1)
                break; /* Loop/switch isn't completed */
            j = abyte0[0];
            continue; /* Loop/switch isn't completed */
        }
        if(!(obj instanceof int[]))
            break; /* Loop/switch isn't completed */
        int ai[] = (int[])obj;
        if(ai.length != 1)
            break; /* Loop/switch isn't completed */
        j = ai[0];
        if(true) goto _L4; else goto _L3
_L3:
        throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to int.  It is of type '").append(obj.getClass()).append("'.").toString());
    }

    public int[] getIntArray(int i)
        throws MetadataException
    {
        Object obj;
        obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(!(obj instanceof Rational[])) goto _L2; else goto _L1
_L1:
        Rational arational[];
        int ai3[];
        int l;
        arational = (Rational[])obj;
        ai3 = new int[arational.length];
        l = 0;
_L10:
        if(l < ai3.length) goto _L4; else goto _L3
_L3:
        int ai1[] = ai3;
_L6:
        return ai1;
_L4:
        ai3[l] = arational[l].intValue();
        l++;
        continue; /* Loop/switch isn't completed */
_L2:
        String s;
        int ai[];
        int j;
        if(obj instanceof int[])
        {
            ai1 = (int[])obj;
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof byte[])
        {
            byte abyte0[] = (byte[])obj;
            int ai2[] = new int[abyte0.length];
            int k = 0;
            do
            {
                if(k >= abyte0.length)
                {
                    ai1 = ai2;
                    continue; /* Loop/switch isn't completed */
                }
                ai2[k] = abyte0[k];
                k++;
            } while(true);
        }
        if(!(obj instanceof String))
            break MISSING_BLOCK_LABEL_226;
        s = (String)obj;
        ai = new int[s.length()];
        j = 0;
_L7:
label0:
        {
            if(j < s.length())
                break label0;
            ai1 = ai;
        }
        if(true) goto _L6; else goto _L5
_L5:
        ai[j] = s.charAt(j);
        j++;
          goto _L7
        if(true) goto _L6; else goto _L8
_L8:
        throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to an int array.  It is of type '").append(obj.getClass()).append("'.").toString());
        if(true) goto _L10; else goto _L9
_L9:
    }

    public long getLong(int i)
        throws MetadataException
    {
        Object obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        long l;
        if(obj instanceof String)
        {
            long l1;
            try
            {
                l1 = Long.parseLong((String)obj);
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new MetadataException((new StringBuilder("unable to parse string ")).append(obj).append(" as a long").toString(), numberformatexception);
            }
            l = l1;
        } else
        if(obj instanceof Number)
            l = ((Number)obj).longValue();
        else
            throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a long.  It is of type '").append(obj.getClass()).append("'.").toString());
        return l;
    }

    public abstract String getName();

    public Object getObject(int i)
    {
        return _tagMap.get(new Integer(i));
    }

    public Rational getRational(int i)
        throws MetadataException
    {
        Object obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(obj instanceof Rational)
            return (Rational)obj;
        else
            throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a Rational.  It is of type '").append(obj.getClass()).append("'.").toString());
    }

    public Rational[] getRationalArray(int i)
        throws MetadataException
    {
        Object obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(obj instanceof Rational[])
            return (Rational[])obj;
        else
            throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to a Rational array.  It is of type '").append(obj.getClass()).append("'.").toString());
    }

    public String getString(int i)
    {
        Object obj = getObject(i);
        if(obj != null) goto _L2; else goto _L1
_L1:
        String s = null;
_L4:
        return s;
_L2:
        if(obj instanceof Rational)
        {
            s = ((Rational)obj).toSimpleString(true);
            continue; /* Loop/switch isn't completed */
        }
        if(obj.getClass().isArray())
        {
            int j = Array.getLength(obj);
            boolean flag = obj.getClass().toString().startsWith("class [L");
            StringBuffer stringbuffer = new StringBuffer();
            int k = 0;
            do
            {
                if(k >= j)
                {
                    s = stringbuffer.toString();
                    continue; /* Loop/switch isn't completed */
                }
                if(k != 0)
                    stringbuffer.append(' ');
                if(flag)
                    stringbuffer.append(Array.get(obj, k).toString());
                else
                    stringbuffer.append(Array.getInt(obj, k));
                k++;
            } while(true);
        }
        s = obj.toString();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String[] getStringArray(int i)
        throws MetadataException
    {
        Object obj;
        obj = getObject(i);
        if(obj == null)
            throw new MetadataException((new StringBuilder("Tag ")).append(getTagName(i)).append(" has not been set -- check using containsTag() first").toString());
        if(!(obj instanceof String[])) goto _L2; else goto _L1
_L1:
        String as1[] = (String[])obj;
_L4:
        return as1;
_L2:
        if(obj instanceof String)
        {
            String as4[] = new String[1];
            as4[0] = (String)obj;
            as1 = as4;
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof int[])
        {
            int ai[] = (int[])obj;
            String as3[] = new String[ai.length];
            int l = 0;
            do
            {
                if(l >= as3.length)
                {
                    as1 = as3;
                    continue; /* Loop/switch isn't completed */
                }
                as3[l] = Integer.toString(ai[l]);
                l++;
            } while(true);
        }
        if(obj instanceof byte[])
        {
            byte abyte0[] = (byte[])obj;
            String as2[] = new String[abyte0.length];
            int k = 0;
            do
            {
                if(k >= as2.length)
                {
                    as1 = as2;
                    continue; /* Loop/switch isn't completed */
                }
                as2[k] = Byte.toString(abyte0[k]);
                k++;
            } while(true);
        }
        if(obj instanceof Rational[])
        {
            Rational arational[] = (Rational[])obj;
            String as[] = new String[arational.length];
            int j = 0;
            do
            {
                if(j >= as.length)
                {
                    as1 = as;
                    continue; /* Loop/switch isn't completed */
                }
                as[j] = arational[j].toSimpleString(false);
                j++;
            } while(true);
        }
        throw new MetadataException((new StringBuilder("Tag '")).append(i).append("' cannot be cast to an String array.  It is of type '").append(obj.getClass()).append("'.").toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getTagCount()
    {
        return _definedTagList.size();
    }

    public Iterator getTagIterator()
    {
        return _definedTagList.iterator();
    }

    public String getTagName(int i)
    {
        Integer integer;
        HashMap hashmap;
        String s1;
        integer = new Integer(i);
        hashmap = getTagNameMap();
        if(hashmap.containsKey(integer))
            break MISSING_BLOCK_LABEL_89;
        s1 = Integer.toHexString(i);
_L3:
        if(s1.length() < 4) goto _L2; else goto _L1
_L1:
        String s = (new StringBuilder("Unknown tag (0x")).append(s1).append(")").toString();
_L4:
        return s;
_L2:
        s1 = (new StringBuilder("0")).append(s1).toString();
          goto _L3
        s = (String)hashmap.get(integer);
          goto _L4
    }

    protected abstract HashMap getTagNameMap();

    public boolean hasErrors()
    {
        boolean flag;
        if(_errorList != null && _errorList.size() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setBoolean(int i, boolean flag)
    {
        setObject(i, new Boolean(flag));
    }

    public void setByteArray(int i, byte abyte0[])
    {
        setObjectArray(i, abyte0);
    }

    public void setDate(int i, Date date)
    {
        setObject(i, date);
    }

    public void setDescriptor(TagDescriptor tagdescriptor)
    {
        if(tagdescriptor == null)
        {
            throw new NullPointerException("cannot set a null descriptor");
        } else
        {
            _descriptor = tagdescriptor;
            return;
        }
    }

    public void setDouble(int i, double d)
    {
        setObject(i, new Double(d));
    }

    public void setFloat(int i, float f)
    {
        setObject(i, new Float(f));
    }

    public void setInt(int i, int j)
    {
        setObject(i, new Integer(j));
    }

    public void setIntArray(int i, int ai[])
    {
        setObjectArray(i, ai);
    }

    public void setLong(int i, long l)
    {
        setObject(i, new Long(l));
    }

    public void setObject(int i, Object obj)
    {
        if(obj == null)
            throw new NullPointerException("cannot set a null object");
        Integer integer = new Integer(i);
        if(!_tagMap.containsKey(integer))
            _definedTagList.add(new Tag(i, this));
        _tagMap.put(integer, obj);
    }

    public void setObjectArray(int i, Object obj)
    {
        setObject(i, obj);
    }

    public void setRational(int i, Rational rational)
    {
        setObject(i, rational);
    }

    public void setRationalArray(int i, Rational arational[])
    {
        setObjectArray(i, arational);
    }

    public void setString(int i, String s)
    {
        setObject(i, s);
    }

    public void setStringArray(int i, String as[])
    {
        setObjectArray(i, as);
    }

    protected final List _definedTagList = new ArrayList();
    protected TagDescriptor _descriptor;
    private List _errorList;
    protected final HashMap _tagMap = new HashMap();
}
