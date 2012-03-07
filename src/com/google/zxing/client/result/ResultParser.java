// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package com.google.zxing.client.result:
//            BookmarkDoCoMoResultParser, AddressBookDoCoMoResultParser, EmailDoCoMoResultParser, AddressBookAUResultParser, 
//            VCardResultParser, BizcardResultParser, VEventResultParser, EmailAddressResultParser, 
//            TelResultParser, SMSMMSResultParser, SMSTOMMSTOResultParser, GeoResultParser, 
//            WifiResultParser, URLTOResultParser, URIResultParser, ISBNResultParser, 
//            ProductResultParser, ExpandedProductResultParser, TextParsedResult, ParsedResult

public abstract class ResultParser
{

    public ResultParser()
    {
    }

    private static void appendKeyValue(String s, int i, int j, Hashtable hashtable)
    {
        int k = s.indexOf('=', i);
        if(k >= 0)
            hashtable.put(s.substring(i, k), urlDecode(s.substring(k + 1, j)));
    }

    private static int findFirstEscape(char ac[])
    {
        int i;
        int j;
        i = ac.length;
        j = 0;
_L3:
        char c;
        if(j >= i)
            break MISSING_BLOCK_LABEL_39;
        c = ac[j];
        if(c != '+' && c != '%') goto _L2; else goto _L1
_L1:
        int k = j;
_L4:
        return k;
_L2:
        j++;
          goto _L3
        k = -1;
          goto _L4
    }

    protected static boolean isStringOfDigits(String s, int i)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        if(i != s.length())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        int j = 0;
        do
        {
            if(j >= i)
                break;
            char c = s.charAt(j);
            if(c < '0' || c > '9')
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            j++;
        } while(true);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected static boolean isSubstringOfDigits(String s, int i, int j)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int k = s.length();
        int l = i + j;
        if(k < l)
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        int i1 = i;
        do
        {
            if(i1 >= l)
                break;
            char c = s.charAt(i1);
            if(c < '0' || c > '9')
            {
                flag = false;
                continue; /* Loop/switch isn't completed */
            }
            i1++;
        } while(true);
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    static String[] matchPrefixedField(String s, String s1, char c, boolean flag)
    {
        int i = s1.length();
        int j = 0;
        Vector vector = null;
        do
        {
            Vector vector1;
label0:
            {
                int k;
                if(j < i)
                {
                    k = s1.indexOf(s, j);
                    if(k >= 0)
                        break label0;
                }
                String as[];
                int l;
                int i1;
                boolean flag1;
                int j1;
                String s2;
                if(vector == null || vector.isEmpty())
                    as = null;
                else
                    as = toStringArray(vector);
                return as;
            }
            l = k + s.length();
            i1 = l;
            vector1 = vector;
            flag1 = false;
            while(!flag1) 
            {
                j1 = s1.indexOf(c, i1);
                if(j1 < 0)
                {
                    i1 = s1.length();
                    flag1 = true;
                } else
                if(s1.charAt(j1 - 1) == '\\')
                {
                    i1 = j1 + 1;
                } else
                {
                    Vector vector2;
                    if(vector1 == null)
                        vector2 = new Vector(3);
                    else
                        vector2 = vector1;
                    s2 = unescapeBackslash(s1.substring(l, j1));
                    if(flag)
                        s2 = s2.trim();
                    vector2.addElement(s2);
                    i1 = j1 + 1;
                    vector1 = vector2;
                    flag1 = true;
                }
            }
            j = i1;
            vector = vector1;
        } while(true);
    }

    static String matchSinglePrefixedField(String s, String s1, char c, boolean flag)
    {
        String as[] = matchPrefixedField(s, s1, c, flag);
        String s2;
        if(as == null)
            s2 = null;
        else
            s2 = as[0];
        return s2;
    }

    protected static void maybeAppend(String s, StringBuffer stringbuffer)
    {
        if(s != null)
        {
            stringbuffer.append('\n');
            stringbuffer.append(s);
        }
    }

    protected static void maybeAppend(String as[], StringBuffer stringbuffer)
    {
        if(as != null)
        {
            for(int i = 0; i < as.length; i++)
            {
                stringbuffer.append('\n');
                stringbuffer.append(as[i]);
            }

        }
    }

    protected static String[] maybeWrap(String s)
    {
        String as[];
        if(s == null)
        {
            as = null;
        } else
        {
            as = new String[1];
            as[0] = s;
        }
        return as;
    }

    private static int parseHexDigit(char c)
    {
        if(c < 'a') goto _L2; else goto _L1
_L1:
        if(c > 'f') goto _L4; else goto _L3
_L3:
        int i = 10 + (c - 97);
_L6:
        return i;
_L2:
        if(c >= 'A')
        {
            if(c <= 'F')
            {
                i = 10 + (c - 65);
                continue; /* Loop/switch isn't completed */
            }
        } else
        if(c >= '0' && c <= '9')
        {
            i = c - 48;
            continue; /* Loop/switch isn't completed */
        }
_L4:
        i = -1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    static Hashtable parseNameValuePairs(String s)
    {
        int i = s.indexOf('?');
        Hashtable hashtable1;
        if(i < 0)
        {
            hashtable1 = null;
        } else
        {
            Hashtable hashtable = new Hashtable(3);
            int j = i + 1;
            do
            {
                int k = s.indexOf('&', j);
                if(k < 0)
                    break;
                appendKeyValue(s, j, k, hashtable);
                j = k + 1;
            } while(true);
            appendKeyValue(s, j, s.length(), hashtable);
            hashtable1 = hashtable;
        }
        return hashtable1;
    }

    public static ParsedResult parseResult(Result result)
    {
        Object obj = BookmarkDoCoMoResultParser.parse(result);
        if(obj == null) goto _L2; else goto _L1
_L1:
        return ((ParsedResult) (obj));
_L2:
        obj = AddressBookDoCoMoResultParser.parse(result);
        if(obj == null)
        {
            obj = EmailDoCoMoResultParser.parse(result);
            if(obj == null)
            {
                obj = AddressBookAUResultParser.parse(result);
                if(obj == null)
                {
                    obj = VCardResultParser.parse(result);
                    if(obj == null)
                    {
                        obj = BizcardResultParser.parse(result);
                        if(obj == null)
                        {
                            obj = VEventResultParser.parse(result);
                            if(obj == null)
                            {
                                obj = EmailAddressResultParser.parse(result);
                                if(obj == null)
                                {
                                    obj = TelResultParser.parse(result);
                                    if(obj == null)
                                    {
                                        obj = SMSMMSResultParser.parse(result);
                                        if(obj == null)
                                        {
                                            obj = SMSTOMMSTOResultParser.parse(result);
                                            if(obj == null)
                                            {
                                                obj = GeoResultParser.parse(result);
                                                if(obj == null)
                                                {
                                                    obj = WifiResultParser.parse(result);
                                                    if(obj == null)
                                                    {
                                                        obj = URLTOResultParser.parse(result);
                                                        if(obj == null)
                                                        {
                                                            obj = URIResultParser.parse(result);
                                                            if(obj == null)
                                                            {
                                                                obj = ISBNResultParser.parse(result);
                                                                if(obj == null)
                                                                {
                                                                    obj = ProductResultParser.parse(result);
                                                                    if(obj == null)
                                                                    {
                                                                        obj = ExpandedProductResultParser.parse(result);
                                                                        if(obj == null)
                                                                            obj = new TextParsedResult(result.getText(), null);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    static String[] toStringArray(Vector vector)
    {
        int i = vector.size();
        String as[] = new String[i];
        for(int j = 0; j < i; j++)
            as[j] = (String)vector.elementAt(j);

        return as;
    }

    protected static String unescapeBackslash(String s)
    {
        if(s == null) goto _L2; else goto _L1
_L1:
        int i = s.indexOf('\\');
        if(i < 0) goto _L2; else goto _L3
_L3:
        String s1;
        int j = s.length();
        StringBuffer stringbuffer = new StringBuffer(j - 1);
        stringbuffer.append(s.toCharArray(), 0, i);
        boolean flag = false;
        while(i < j) 
        {
            char c = s.charAt(i);
            if(flag || c != '\\')
            {
                stringbuffer.append(c);
                flag = false;
            } else
            {
                flag = true;
            }
            i++;
        }
        s1 = stringbuffer.toString();
_L5:
        return s1;
_L2:
        s1 = s;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private static String urlDecode(String s)
    {
        String s1;
        if(s == null)
        {
            s1 = null;
        } else
        {
            char ac[] = s.toCharArray();
            int i = findFirstEscape(ac);
            if(i < 0)
            {
                s1 = s;
            } else
            {
                int j = ac.length;
                StringBuffer stringbuffer = new StringBuffer(j - 2);
                stringbuffer.append(ac, 0, i);
                while(i < j) 
                {
                    char c = ac[i];
                    if(c == '+')
                        stringbuffer.append(' ');
                    else
                    if(c == '%')
                    {
                        if(i >= j - 2)
                        {
                            stringbuffer.append('%');
                        } else
                        {
                            int k = i + 1;
                            int l = parseHexDigit(ac[k]);
                            i = k + 1;
                            int i1 = parseHexDigit(ac[i]);
                            if(l < 0 || i1 < 0)
                            {
                                stringbuffer.append('%');
                                stringbuffer.append(ac[i - 1]);
                                stringbuffer.append(ac[i]);
                            }
                            stringbuffer.append((char)(i1 + (l << 4)));
                        }
                    } else
                    {
                        stringbuffer.append(c);
                    }
                    i++;
                }
                s1 = stringbuffer.toString();
            }
        }
        return s1;
    }
}
