// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.io.*;
import java.util.*;
import org.json.JSONException;

// Referenced classes of package com.jindong.app.mall.entity:
//            Image

public class OrderComment
    implements Serializable
{

    public OrderComment()
    {
        imageList = new LinkedList();
    }

    public OrderComment(JSONObjectProxy jsonobjectproxy, int i)
    {
        this(jsonobjectproxy, null, i);
    }

    public OrderComment(JSONObjectProxy jsonobjectproxy, JSONArrayPoxy jsonarraypoxy, int i)
    {
        imageList = new LinkedList();
        i;
        JVM INSTR tableswitch 0 2: default 44
    //                   0 45
    //                   1 108
    //                   2 180;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        setTitle(jsonobjectproxy.getStringOrNull("title"));
        setReplyCount(jsonobjectproxy.getIntOrNull("replyCount"));
        setViewCount(jsonobjectproxy.getIntOrNull("viewCount"));
        setUserId(jsonobjectproxy.getStringOrNull("userId"));
        setCreationTime(jsonobjectproxy.getStringOrNull("creationTime"));
        setId(jsonobjectproxy.getStringOrNull("id"));
          goto _L1
_L3:
        int j;
        setContent(jsonobjectproxy.getStringOrNull("content"));
        setTitle(jsonobjectproxy.getStringOrNull("title"));
        setReplyCount(jsonobjectproxy.getIntOrNull("replyCount"));
        setViewCount(jsonobjectproxy.getIntOrNull("viewCount"));
        setUserId(jsonobjectproxy.getStringOrNull("userId"));
        setCreationTime(jsonobjectproxy.getStringOrNull("creationTime"));
        j = 0;
_L5:
        if(j < jsonarraypoxy.length())
            break MISSING_BLOCK_LABEL_243;
_L4:
        setContent(jsonobjectproxy.getStringOrNull("content"));
        setCreationTime(jsonobjectproxy.getStringOrNull("creationTime"));
        setReplyCount(jsonobjectproxy.getIntOrNull("replyCount"));
        setTotalCount(jsonobjectproxy.getIntOrNull("totalCount"));
        setUserId(jsonobjectproxy.getStringOrNull("userId"));
        setViewCount(jsonobjectproxy.getIntOrNull("viewCount"));
          goto _L1
        try
        {
            Image image = new Image(jsonarraypoxy.getJSONObject(j), 0);
            imageList.add(image);
        }
        catch(JSONException jsonexception)
        {
            jsonexception.printStackTrace();
        }
        j++;
          goto _L5
    }

    public static byte[] readStream(InputStream inputstream)
        throws Exception
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[1024];
        do
        {
            int i = inputstream.read(abyte0);
            if(i == -1)
            {
                bytearrayoutputstream.close();
                inputstream.close();
                return bytearrayoutputstream.toByteArray();
            }
            bytearrayoutputstream.write(abyte0, 0, i);
        } while(true);
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        ArrayList arraylist = null;
        ArrayList arraylist1 = new ArrayList();
        int j = 0;
_L2:
        if(j >= jsonarraypoxy.length())
        {
            arraylist = arraylist1;
            break; /* Loop/switch isn't completed */
        }
        arraylist1.add(new OrderComment(jsonarraypoxy.getJSONObject(j), i));
        j++;
        if(true) goto _L2; else goto _L1
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L3:
        if(Log.V)
            Log.v("Comment", "JSONException -->> ", jsonexception1);
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        arraylist = arraylist1;
        if(true) goto _L3; else goto _L1
_L1:
        return arraylist;
    }

    public String getContent()
    {
        return content;
    }

    public String getCreationTime()
    {
        String s;
        if(creationTime != null)
            s = creationTime;
        else
            s = "\u6682\u65E0\u6652\u5355\u65F6\u95F4";
        return s;
    }

    public String getId()
    {
        return id;
    }

    public List getImageList()
    {
        return imageList;
    }

    public String getReplyCount()
    {
        String s;
        if(replyCount != null)
            s = replyCount;
        else
            s = "0\u56DE\u590D";
        return s;
    }

    public String getTitle()
    {
        String s;
        if(title != null)
            s = title;
        else
            s = "\u6682\u65E0\u6807\u9898";
        return s;
    }

    public Integer getTotalCount()
    {
        return totalCount;
    }

    public String getUserId()
    {
        String s;
        if(userId != null)
            s = userId;
        else
            s = "\u6682\u65E0\u4F5C\u8005\u540D";
        return s;
    }

    public Integer getViewCount()
    {
        int i;
        if(viewCount != null)
            i = viewCount.intValue();
        else
            i = 0;
        return Integer.valueOf(i);
    }

    public void setContent(String s)
    {
        content = s;
    }

    public void setCreationTime(String s)
    {
        creationTime = s;
    }

    public void setId(String s)
    {
        id = s;
    }

    public void setImageList(List list)
    {
        imageList = list;
    }

    public void setReplyCount(Integer integer)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(integer);
        stringbuffer.append("\u56DE\u590D");
        replyCount = stringbuffer.toString();
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setTotalCount(Integer integer)
    {
        totalCount = integer;
    }

    public void setUserId(String s)
    {
        userId = s;
    }

    public void setViewCount(Integer integer)
    {
        viewCount = integer;
    }

    public static final int DETAIL_REPLY = 2;
    public static final int ORDER_COMMENT = 0;
    public static final int ORDER_COMMENT_DETAIL = 1;
    private String content;
    private String creationTime;
    private String id;
    private List imageList;
    private String replyCount;
    private String title;
    private Integer totalCount;
    private String userId;
    private Integer viewCount;
}
