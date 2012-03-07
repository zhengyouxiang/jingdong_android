// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.entity;

import com.jindong.app.mall.utils.*;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

// Referenced classes of package com.jindong.app.mall.entity:
//            CityMode1, Image, ProvinceMode1

public class Product
    implements Serializable
{

    public Product()
    {
        nIndex = 0;
        imageList = new LinkedList();
        provinceStockMode = Integer.valueOf(0);
        sImgUrlList = new LinkedList();
    }

    public Product(JSONObjectProxy jsonobjectproxy, int i)
    {
        this(jsonobjectproxy, ((JSONArray) (null)), i);
    }

    public Product(JSONObjectProxy jsonobjectproxy, int i, Object aobj[])
    {
        this(jsonobjectproxy, null, i, aobj);
    }

    public Product(JSONObjectProxy jsonobjectproxy, JSONArray jsonarray, int i)
    {
        this(jsonobjectproxy, jsonarray, i, null);
    }

    private Product(JSONObjectProxy jsonobjectproxy, JSONArray jsonarray, int i, Object aobj[])
    {
        nIndex = 0;
        imageList = new LinkedList();
        provinceStockMode = Integer.valueOf(0);
        sImgUrlList = new LinkedList();
        update(jsonobjectproxy, jsonarray, i, aobj);
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i)
    {
        return toList(jsonarraypoxy, i, null);
    }

    public static ArrayList toList(JSONArrayPoxy jsonarraypoxy, int i, Object aobj[])
    {
        if(jsonarraypoxy != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist2 = null;
_L7:
        return arraylist2;
_L2:
        ArrayList arraylist = null;
        ArrayList arraylist1 = new ArrayList();
        int j = 0;
_L4:
        if(j >= jsonarraypoxy.length())
        {
            arraylist = arraylist1;
            break; /* Loop/switch isn't completed */
        }
        arraylist1.add(new Product(jsonarraypoxy.getJSONObject(j), i, aobj));
        j++;
        if(true) goto _L4; else goto _L3
        JSONException jsonexception2;
        jsonexception2;
        JSONException jsonexception1 = jsonexception2;
_L5:
        if(Log.V)
            Log.v("Ware", jsonexception1.getMessage());
        break; /* Loop/switch isn't completed */
        JSONException jsonexception;
        jsonexception;
        jsonexception1 = jsonexception;
        arraylist = arraylist1;
        if(true) goto _L5; else goto _L3
_L3:
        arraylist2 = arraylist;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void appendImgUrl(String s, int i)
    {
        if(i < 0)
            sImgUrlList.add(s.toString());
        else
            sImgUrlList.add(i, s.toString());
    }

    public int geItemCount()
    {
        int i;
        if(nItemCount <= 0)
            i = Integer.valueOf("1").intValue();
        else
            i = nItemCount;
        return i;
    }

    public String getAdWord()
    {
        String s;
        if(adWord == null)
            s = "";
        else
            s = adWord;
        return s;
    }

    public Integer getCityIdMode1()
    {
        return cityIdMode1;
    }

    public CityMode1 getCityMode1BySkuId(Long long1)
    {
        return (CityMode1)cityMode1Map.get(long1);
    }

    public ArrayList getCouponList()
    {
        return couponList;
    }

    public String getFid()
    {
        return fid;
    }

    public ArrayList getGiftList()
    {
        return giftList;
    }

    public Long getId()
    {
        return id;
    }

    public Image getImage()
    {
        Image image;
        if(imageList.size() > 0)
            image = (Image)imageList.get(0);
        else
            image = null;
        return image;
    }

    public List getImageList()
    {
        return imageList;
    }

    public String getImageUrl()
    {
        String s;
        if(imageList.size() > 0)
            s = ((Image)imageList.get(0)).getSmall();
        else
            s = null;
        return s;
    }

    public String getImgPrice()
    {
        return imgPrice;
    }

    public String getJdDixcount()
    {
        Float float1 = null;
        Float float2;
        if(discount == null)
            break MISSING_BLOCK_LABEL_21;
        float2 = Float.valueOf(discount);
        float1 = float2;
_L2:
        String s;
        if(float1 == null || float1.floatValue() < 0.0F)
            s = "\u6682\u65E0\u6298\u6263";
        else
            s = (new DecimalFormat("0.00")).format(float1);
        return s;
        NumberFormatException numberformatexception;
        numberformatexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getJdPrice()
    {
        Float float1 = null;
        Float float2;
        if(jdPrice == null)
            break MISSING_BLOCK_LABEL_21;
        float2 = Float.valueOf(jdPrice);
        float1 = float2;
_L2:
        String s;
        if(float1 == null || float1.floatValue() <= 0.0F)
            s = "\u6682\u65E0\u62A5\u4EF7";
        else
            s = (new DecimalFormat("0.00")).format(float1);
        return s;
        NumberFormatException numberformatexception;
        numberformatexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getMarketPrice()
    {
        Float float1 = null;
        Float float2;
        if(marketPrice == null)
            break MISSING_BLOCK_LABEL_21;
        float2 = Float.valueOf(marketPrice);
        float1 = float2;
_L2:
        String s;
        if(float1 == null || float1.floatValue() <= 0.0F)
            s = "\u6682\u65E0\u62A5\u4EF7";
        else
            s = (new DecimalFormat("0.00")).format(float1);
        return s;
        NumberFormatException numberformatexception;
        numberformatexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getMsgBody()
    {
        String s;
        if(sMsgBody.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sMsgBody;
        return s;
    }

    public String getMsgFlag()
    {
        String s;
        if(sMsgFlag.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sMsgFlag;
        return s;
    }

    public String getMsgTime()
    {
        String s;
        if(sMsgTime.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sMsgTime;
        return s;
    }

    public String getMsgType()
    {
        String s;
        if(sMsgType.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sMsgType;
        return s;
    }

    public String getMsgTypeName()
    {
        return msgTypeName;
    }

    public String getName()
    {
        String s;
        if(name != null)
            s = name;
        else
            s = "\u6682\u65E0\u540D\u79F0";
        return s;
    }

    public Integer getNum()
    {
        return num;
    }

    public String getOrderId()
    {
        String s;
        if(sOrderId.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sOrderId;
        return s;
    }

    public String getOrderPrice()
    {
        String s;
        if(sTotalPrice.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sTotalPrice;
        return s;
    }

    public String getOrderStatus()
    {
        System.out.println((new StringBuilder("get++++++")).append(sOrderStatus).toString());
        String s;
        if(sOrderStatus.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sOrderStatus;
        return s;
    }

    public String getOrderSubtime()
    {
        String s;
        if(sSubmitTime.length() <= 0)
            s = String.valueOf(" ");
        else
            s = sSubmitTime;
        return s;
    }

    public String getProvinceID()
    {
        return provinceID;
    }

    public Integer getProvinceIdMode1()
    {
        return provinceIdMode1;
    }

    public Integer getProvinceMode1IndexById(int i)
    {
        return (Integer)provinceMode1Map.get(Integer.valueOf(i));
    }

    public ArrayList getProvinceMode1List()
    {
        return provinceMode1List;
    }

    public String getProvinceName()
    {
        return provinceName;
    }

    public String getProvinceStockContent()
    {
        return provinceStockContent;
    }

    public Boolean getProvinceStockFlag()
    {
        return provinceStockFlag;
    }

    public Integer getProvinceStockMode()
    {
        return provinceStockMode;
    }

    public Long getShowId()
    {
        Long long1;
        if(showId == null)
            long1 = getId();
        else
            long1 = showId;
        return long1;
    }

    public boolean getSubOrderFlag()
    {
        return subOrderFlag;
    }

    public String getUserBalance()
    {
        String s;
        if(sUserBalance == null)
            s = "";
        else
            s = sUserBalance;
        return s;
    }

    public String getUserClass()
    {
        String s;
        if(sUserClass == null)
            s = "";
        else
            s = sUserClass;
        return s;
    }

    public String getUserPriceContent()
    {
        return userPriceContent;
    }

    public String getUserPriceLabel()
    {
        return userPriceLabel;
    }

    public String getUserScore()
    {
        String s;
        if(sUerScore.length() > 0)
            s = sUerScore;
        else
            s = "0";
        return s;
    }

    public String getUsername()
    {
        String s;
        if(sUserName.length() <= 0)
            s = String.valueOf("Customer");
        else
            s = sUserName;
        return s;
    }

    public String getmPaymentType()
    {
        return mPaymentType;
    }

    public String getsMsgId()
    {
        return sMsgId;
    }

    public String getsMsgUpdateTime()
    {
        return sMsgUpdateTime;
    }

    public Boolean isBook()
    {
        boolean flag;
        if(isBook != null)
            flag = isBook.booleanValue();
        else
            flag = false;
        return Boolean.valueOf(flag);
    }

    public boolean isFiledExist(JSONObjectProxy jsonobjectproxy, String s)
    {
        boolean flag;
        if(jsonobjectproxy.toString().contains(s))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Boolean isPromotion()
    {
        boolean flag;
        if(isPromotion != null)
            flag = isPromotion.booleanValue();
        else
            flag = false;
        return Boolean.valueOf(flag);
    }

    public String popImgUrl(int i)
    {
        String s;
        if(i > -1 && sImgUrlList.size() > 0 && i < sImgUrlList.size())
            s = ((String)sImgUrlList.get(i)).toString();
        else
            s = null;
        return s;
    }

    protected void putInCityMode1Map(Long long1, CityMode1 citymode1)
    {
        if(cityMode1Map == null)
            cityMode1Map = new HashMap();
        if(!cityMode1Map.containsKey(long1))
            cityMode1Map.put(long1, citymode1);
    }

    public void setAdWord(String s)
    {
        adWord = s;
    }

    public void setBook(Boolean boolean1)
    {
        isBook = boolean1;
    }

    public void setCityIdMode1(Integer integer)
    {
        cityIdMode1 = integer;
    }

    public void setCouponList(ArrayList arraylist)
    {
        couponList = arraylist;
    }

    public void setFid(String s)
    {
        fid = s;
    }

    public void setGiftList(ArrayList arraylist)
    {
        giftList = arraylist;
    }

    public void setId(Long long1)
    {
        id = long1;
    }

    public void setImage(String s, String s1)
    {
        imageList.add(new Image(s, s1));
    }

    public void setImageList(List list)
    {
        imageList = list;
    }

    public void setImgPrice(String s)
    {
        imgPrice = s;
    }

    public void setItemCount(int i)
    {
        nItemCount = i;
    }

    public void setJdDixcount(String s)
    {
        discount = s;
    }

    public void setJdPrice(String s)
    {
        jdPrice = s;
    }

    public void setMarketPrice(String s)
    {
        marketPrice = s;
    }

    public void setMessageBody(String s)
    {
        sMsgBody = s;
    }

    public void setMessageFlag(String s)
    {
        sMsgFlag = s;
    }

    public void setMessageTime(String s)
    {
        sMsgTime = s;
    }

    public void setMessageType(String s)
    {
        sMsgType = s;
    }

    public void setMsgTypeName(String s)
    {
        msgTypeName = s;
    }

    public void setName(String s)
    {
        if(s != null) goto _L2; else goto _L1
_L1:
        name = s;
_L5:
        return;
_L2:
        Matcher matcher;
        StringBuffer stringbuffer;
        matcher = Pattern.compile("([^a-zA-Z0-9\uFF08\uFF09\\(\\) ])([a-zA-Z\uFF08\\(])|([^ ])([\uFF08\\(])|([\uFF08\\(])([^ ])|([A-Z0-9])(\\-)|(\\-)([A-Z0-9])|([0-9]*[A-Z]+[0-9]*)([^a-zA-Z0-9\uFF08\uFF09\\(\\) ])").matcher(s);
        stringbuffer = new StringBuffer();
_L6:
        if(matcher.find()) goto _L4; else goto _L3
_L3:
        matcher.appendTail(stringbuffer);
        stringbuffer.append(" ");
        name = stringbuffer.toString();
          goto _L5
        Exception exception;
        exception;
        name = s;
          goto _L5
_L4:
        StringBuffer stringbuffer1;
        int i;
        stringbuffer1 = new StringBuffer();
        i = 1;
_L7:
        if(i <= matcher.groupCount())
        {
            if(matcher.group(i) == null)
                break MISSING_BLOCK_LABEL_246;
            stringbuffer1.append(matcher.group(i)).append(" ").append(matcher.group(i + 1));
        }
        if(Log.D)
            Log.d("Temp", (new StringBuilder("name -->> ")).append(s).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("stringBuffer.toString() -->> ")).append(stringbuffer.toString()).toString());
        if(Log.D)
            Log.d("Temp", (new StringBuilder("sb.toString() -->> ")).append(stringbuffer1.toString()).toString());
        matcher.appendReplacement(stringbuffer, stringbuffer1.toString());
          goto _L6
        i++;
          goto _L7
    }

    public void setNum(Integer integer)
    {
        num = integer;
    }

    public void setOrderId(String s)
    {
        sOrderId = s;
    }

    public void setOrderPrice(String s)
    {
        sTotalPrice = s;
    }

    public void setOrderStatus(String s)
    {
        System.out.println((new StringBuilder("set++++++")).append(s).toString());
        sOrderStatus = s;
    }

    public void setOrderSubtime(String s)
    {
        sSubmitTime = s;
    }

    public void setPromotion(Boolean boolean1)
    {
        isPromotion = boolean1;
    }

    public void setProvinceID(String s)
    {
        provinceID = s;
    }

    public void setProvinceIdMode1(Integer integer)
    {
        provinceIdMode1 = integer;
    }

    public void setProvinceMode1List(ArrayList arraylist)
    {
        provinceMode1List = arraylist;
        provinceMode1Map = new HashMap();
        int i = 0;
        do
        {
            if(i >= arraylist.size())
                return;
            ProvinceMode1 provincemode1 = (ProvinceMode1)arraylist.get(i);
            provinceMode1Map.put(Integer.valueOf(provincemode1.getId()), Integer.valueOf(i));
            i++;
        } while(true);
    }

    public void setProvinceName(String s)
    {
        provinceName = s;
    }

    public void setProvinceStockContent(String s)
    {
        provinceStockContent = s;
    }

    public void setProvinceStockFlag(Boolean boolean1)
    {
        provinceStockFlag = boolean1;
    }

    public void setProvinceStockMode(Integer integer)
    {
        provinceStockMode = integer;
    }

    public void setShowId(Long long1)
    {
        showId = long1;
    }

    public void setSubOrderFlag(boolean flag)
    {
        subOrderFlag = flag;
    }

    public void setUserBalance(String s)
    {
        sUserBalance = s;
    }

    public void setUserClass(String s)
    {
        sUserClass = s;
    }

    public void setUserPriceContent(String s)
    {
        userPriceContent = s;
    }

    public void setUserPriceLabel(String s)
    {
        userPriceLabel = s;
    }

    public void setUserScore(String s)
    {
        sUerScore = s;
    }

    public void setUsername(String s)
    {
        sUserName = s;
    }

    public void setmPaymentType(String s)
    {
        mPaymentType = s;
    }

    public void setsMsgId(String s)
    {
        sMsgId = s;
    }

    public void setsMsgUpdateTime(String s)
    {
        sMsgUpdateTime = s;
    }

    public void update(JSONObjectProxy jsonobjectproxy, JSONArray jsonarray, int i)
    {
        update(jsonobjectproxy, jsonarray, i, null);
    }

    public void update(JSONObjectProxy jsonobjectproxy, JSONArray jsonarray, int i, Object aobj[])
    {
        i;
        JVM INSTR tableswitch 0 13: default 72
    //                   0 73
    //                   1 165
    //                   2 72
    //                   3 420
    //                   4 647
    //                   5 728
    //                   6 765
    //                   7 317
    //                   8 882
    //                   9 996
    //                   10 1068
    //                   11 1126
    //                   12 1196
    //                   13 235;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
        return;
_L2:
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setName(jsonobjectproxy.getStringOrNull("wname"));
        setImage(jsonobjectproxy.getStringOrNull("imageurl"), null);
        setJdPrice(jsonobjectproxy.getStringOrNull("jdPrice"));
        setImgPrice(jsonobjectproxy.getStringOrNull("wmaprice"));
        setMarketPrice(jsonobjectproxy.getStringOrNull("marketPrice"));
        if(isFiledExist(jsonobjectproxy, "adword"))
            setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        continue; /* Loop/switch isn't completed */
_L3:
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setImage(jsonobjectproxy.getStringOrNull("imageurl"), null);
        setName(jsonobjectproxy.getStringOrNull("wname"));
        setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        setMarketPrice(jsonobjectproxy.getStringOrNull("martPrice"));
        setJdPrice(jsonobjectproxy.getStringOrNull("jdPrice"));
        continue; /* Loop/switch isn't completed */
_L14:
        setId(jsonobjectproxy.getLongOrNull("SkuId"));
        if(aobj[0] != null)
            setImage((new StringBuilder(String.valueOf((String)aobj[0]))).append(jsonobjectproxy.getStringOrNull("SkuPicUrl")).toString(), null);
        else
            setImage(jsonobjectproxy.getStringOrNull("SkuPicUrl"), null);
        setName(jsonobjectproxy.getStringOrNull("SkuName"));
        continue; /* Loop/switch isn't completed */
_L8:
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setImage(jsonobjectproxy.getStringOrNull("imageurl"), null);
        setName(jsonobjectproxy.getStringOrNull("wname"));
        setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        setMarketPrice(jsonobjectproxy.getStringOrNull("martPrice"));
        setJdPrice(jsonobjectproxy.getStringOrNull("jdPrice"));
        setOrderId(jsonobjectproxy.getStringOrNull("orderId"));
        setBook(jsonobjectproxy.getBooleanOrNull("book"));
        fid = jsonobjectproxy.getStringOrNull("fid");
        continue; /* Loop/switch isn't completed */
_L4:
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setShowId(getId());
        setName(jsonobjectproxy.getStringOrNull("wname"));
        setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        setMarketPrice(jsonobjectproxy.getStringOrNull("marketPrice"));
        setJdPrice(jsonobjectproxy.getStringOrNull("jdPrice"));
        setBook(jsonobjectproxy.getBooleanOrNull("isbook"));
        setPromotion(jsonobjectproxy.getBooleanOrNull("promotion"));
        setUserPriceLabel(jsonobjectproxy.getStringOrNull("userLevel"));
        setUserPriceContent(jsonobjectproxy.getStringOrNull("userLevelPrice"));
        Boolean boolean1 = jsonobjectproxy.getBooleanOrNull("directShow");
        int j;
        org.json.JSONObject jsonobject;
        if(boolean1 != null && boolean1.booleanValue())
            setProvinceStockMode(Integer.valueOf(1));
        else
            setProvinceStockMode(Integer.valueOf(0));
        imageList.clear();
        j = 0;
_L15:
        if(j >= jsonarray.length())
            continue; /* Loop/switch isn't completed */
        jsonobject = jsonarray.getJSONObject(j);
        imageList.add(new Image(jsonobject, 0));
        j++;
          goto _L15
        JSONException jsonexception;
        jsonexception;
        if(Log.V)
            Log.v(com/jindong/app/mall/entity/Product.getName(), jsonexception.getMessage());
        continue; /* Loop/switch isn't completed */
_L5:
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setName(jsonobjectproxy.getStringOrNull("wname"));
        if(isFiledExist(jsonobjectproxy, "adword"))
            setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        setMarketPrice(jsonobjectproxy.getStringOrNull("martPrice"));
        setJdPrice(jsonobjectproxy.getStringOrNull("jdPrice"));
        setImage(jsonobjectproxy.getStringOrNull("imageurl"), null);
        continue; /* Loop/switch isn't completed */
_L6:
        setUsername(jsonobjectproxy.getStringOrNull("unickName"));
        setImage(jsonobjectproxy.getStringOrNull("imgUrl"), null);
        setUserClass(jsonobjectproxy.getStringOrNull("uclass"));
        continue; /* Loop/switch isn't completed */
_L7:
        setOrderId(jsonobjectproxy.getStringOrNull("orderId"));
        setOrderStatus(jsonobjectproxy.getStringOrNull("orderStatus"));
        setOrderPrice(jsonobjectproxy.getStringOrNull("price"));
        setOrderSubtime(jsonobjectproxy.getStringOrNull("dataSubmit"));
        setNum(jsonobjectproxy.getIntOrNull("num"));
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setName(jsonobjectproxy.getStringOrNull("wname"));
        setImage(jsonobjectproxy.getStringOrNull("imageurl"), null);
        setmPaymentType(jsonobjectproxy.getStringOrNull("paymentType"));
        setSubOrderFlag(jsonobjectproxy.getBooleanOrNull("subOrder").booleanValue());
        continue; /* Loop/switch isn't completed */
_L9:
        setMessageFlag(jsonobjectproxy.getStringOrNull("msgFlag"));
        setMessageType(jsonobjectproxy.getStringOrNull("msgType"));
        setMessageTime(jsonobjectproxy.getStringOrNull("createTime"));
        setMessageBody(jsonobjectproxy.getStringOrNull("msgBody"));
        setImage(jsonobjectproxy.getStringOrNull("imgUrl"), null);
        setOrderId(jsonobjectproxy.getStringOrNull("ordId"));
        setId(jsonobjectproxy.getLongOrNull("proId"));
        setsMsgUpdateTime(jsonobjectproxy.getStringOrNull("updateTime"));
        setsMsgId(jsonobjectproxy.getStringOrNull("msgId"));
        setMsgTypeName(jsonobjectproxy.getStringOrNull("msgName"));
        continue; /* Loop/switch isn't completed */
_L10:
        setId(jsonobjectproxy.getLongOrNull("Id"));
        setName(jsonobjectproxy.getStringOrNull("Name"));
        setJdPrice(jsonobjectproxy.getStringOrNull("Price"));
        setJdDixcount(jsonobjectproxy.getStringOrNull("Discount"));
        setImgPrice(jsonobjectproxy.getStringOrNull("PriceImg"));
        setItemCount(jsonobjectproxy.getIntOrNull("Num").intValue());
        continue; /* Loop/switch isn't completed */
_L11:
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        setBook(jsonobjectproxy.getBooleanOrNull("book"));
        setName(jsonobjectproxy.getStringOrNull("wname"));
        setNum(jsonobjectproxy.getIntOrNull("num"));
        continue; /* Loop/switch isn't completed */
_L12:
        setAdWord(jsonobjectproxy.getStringOrNull("adword"));
        setBook(jsonobjectproxy.getBooleanOrNull("book"));
        setImage(jsonobjectproxy.getStringOrNull("imageurl"), null);
        setMarketPrice(jsonobjectproxy.getStringOrNull("martPrice"));
        setId(jsonobjectproxy.getLongOrNull("wareId"));
        setName(jsonobjectproxy.getStringOrNull("wname"));
        continue; /* Loop/switch isn't completed */
_L13:
        setName(jsonobjectproxy.getStringOrNull("Name"));
        if(true) goto _L1; else goto _L16
_L16:
    }

    public static final int CART_LIST = 9;
    public static final int COLLECT_LIST = 7;
    public static final int CRAZY = 0;
    public static final int EASY_LIST = 12;
    public static final int MESSAGE_LIST = 8;
    public static final int ORDER_LIST = 6;
    public static final int PACKS_LIST = 13;
    public static final int PRODUCT_DETAIL = 3;
    public static final int PROMOTION = 10;
    public static final int RECOMAND_PRODECT = 4;
    public static final int SEARCH_CATEGORY = 1;
    private static final String TAG = "Product";
    public static final int USER_INFO = 5;
    public static final int WARE_ID_BY_BAR_CODE_LIST = 11;
    private static final long serialVersionUID = 0xa66dafcde04368b6L;
    private String adWord;
    private Integer cityIdMode1;
    private HashMap cityMode1Map;
    private ArrayList couponList;
    private String discount;
    private String fid;
    private ArrayList giftList;
    private Long id;
    private List imageList;
    private String imgPrice;
    private Boolean isBook;
    private Boolean isPromotion;
    private String jdPrice;
    private String mPaymentType;
    private String marketPrice;
    private String msgTypeName;
    private int nIndex;
    private int nItemCount;
    private String name;
    private Integer num;
    private String provinceID;
    private Integer provinceIdMode1;
    private ArrayList provinceMode1List;
    private HashMap provinceMode1Map;
    private String provinceName;
    private Integer provinceStockCode;
    private String provinceStockContent;
    private Boolean provinceStockFlag;
    private Integer provinceStockMode;
    private List sImgUrlList;
    private String sItemPrice;
    private String sMsgBody;
    private String sMsgFlag;
    private String sMsgId;
    private String sMsgTime;
    private String sMsgType;
    private String sMsgUpdateTime;
    private String sOrderId;
    private String sOrderStatus;
    private String sPriceShow;
    private String sSkuID;
    private String sSkuName;
    private String sSubmitTime;
    private String sTotalPrice;
    private String sUerScore;
    private String sUserBalance;
    private String sUserClass;
    private String sUserName;
    private Long showId;
    private boolean subOrderFlag;
    private String userPriceContent;
    private String userPriceLabel;
}
