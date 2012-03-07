// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.app.AlertDialog;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.jindong.app.mall.MyApplication;
import com.jindong.app.mall.config.Configuration;
import com.jindong.app.mall.database.table.CacheFileTable;
import com.jindong.app.mall.entity.CacheFile;
import com.jindong.app.mall.utils.thread.PooledThread;
import com.jindong.app.mall.utils.thread.ThreadPool;
import com.jindong.app.mall.utils.ui.DialogController;
import java.io.*;
import java.lang.ref.SoftReference;
import java.net.*;
import java.util.*;
import java.util.zip.GZIPInputStream;
import org.json.*;

// Referenced classes of package com.jindong.app.mall.utils:
//            DefaultEffectHttpListener, Log, JSONObjectProxy, MyActivity, 
//            Md5Encrypt, FileGuider, FileService, IOUtil, 
//            StatisticsReportUtil, NetUtils, GlobalInitialization, CommonUtil, 
//            Base64

public abstract class HttpGroup
    implements MyActivity.DestroyListener
{
    public static interface CompleteListener
    {

        public abstract void onComplete(Bundle bundle);
    }

    public static interface CustomOnAllListener
        extends OnAllListener
    {
    }

    static interface Handler
    {

        public abstract void run();
    }

    public static class HttpError
    {

        public int getErrorCode()
        {
            return errorCode;
        }

        public String getErrorCodeStr()
        {
            errorCode;
            JVM INSTR tableswitch 0 3: default 36
        //                       0 41
        //                       1 47
        //                       2 53
        //                       3 59;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            String s = "UNKNOWN";
_L7:
            return s;
_L2:
            s = "EXCEPTION";
            continue; /* Loop/switch isn't completed */
_L3:
            s = "TIME_OUT";
            continue; /* Loop/switch isn't completed */
_L4:
            s = "RESPONSE_CODE";
            continue; /* Loop/switch isn't completed */
_L5:
            s = "JSON_CODE";
            if(true) goto _L7; else goto _L6
_L6:
        }

        public Throwable getException()
        {
            return exception;
        }

        public HttpResponse getHttpResponse()
        {
            return httpResponse;
        }

        public int getJsonCode()
        {
            return jsonCode;
        }

        public String getMessage()
        {
            return message;
        }

        public int getResponseCode()
        {
            return responseCode;
        }

        public int getTimes()
        {
            return times;
        }

        public boolean isNoRetry()
        {
            return noRetry;
        }

        public void setErrorCode(int i)
        {
            errorCode = i;
        }

        public void setException(Throwable throwable)
        {
            exception = throwable;
        }

        public void setHttpResponse(HttpResponse httpresponse)
        {
            httpResponse = httpresponse;
        }

        public void setJsonCode(int i)
        {
            jsonCode = i;
        }

        public void setMessage(String s)
        {
            message = s;
        }

        public void setNoRetry(boolean flag)
        {
            noRetry = flag;
        }

        public void setResponseCode(int i)
        {
            responseCode = i;
        }

        public void setTimes(int i)
        {
            times = i;
        }

        public String toString()
        {
            if(getException() != null && Log.D)
                Log.d("HttpGroup", "HttpError Exception -->> ", getException());
            return (new StringBuilder("HttpError [errorCode=")).append(getErrorCodeStr()).append(", exception=").append(exception).append(", jsonCode=").append(jsonCode).append(", message=").append(message).append(", responseCode=").append(responseCode).append(", time=").append(times).append("]").toString();
        }

        public static final int EXCEPTION = 0;
        public static final String EXCEPTION_MESSAGE_ATTESTATION_WIFI = "attestation WIFI";
        public static final String EXCEPTION_MESSAGE_NO_CACHE = "no cache";
        public static final int JSON_CODE = 3;
        public static final int RESPONSE_CODE = 2;
        public static final int TIME_OUT = 1;
        private int errorCode;
        private Throwable exception;
        private HttpResponse httpResponse;
        private int jsonCode;
        private String message;
        private boolean noRetry;
        private int responseCode;
        private int times;

        public HttpError()
        {
        }

        public HttpError(Throwable throwable)
        {
            errorCode = 0;
            exception = throwable;
        }
    }

    public static class HttpGroupSetting
    {

        public MyActivity getMyActivity()
        {
            return myActivity;
        }

        public int getPriority()
        {
            return priority;
        }

        public int getType()
        {
            return type;
        }

        public void setMyActivity(MyActivity myactivity)
        {
            myActivity = myactivity;
        }

        public void setPriority(int i)
        {
            priority = i;
        }

        public void setType(int i)
        {
            type = i;
            if(priority != 0) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR lookupswitch 2: default 40
        //                       1000: 41
        //                       5000: 51;
               goto _L2 _L3 _L4
_L2:
            return;
_L3:
            setPriority(1000);
            continue; /* Loop/switch isn't completed */
_L4:
            setPriority(5000);
            if(true) goto _L2; else goto _L5
_L5:
        }

        public static final int PRIORITY_FILE = 500;
        public static final int PRIORITY_IMAGE = 5000;
        public static final int PRIORITY_JSON = 1000;
        public static final int TYPE_FILE = 500;
        public static final int TYPE_IMAGE = 5000;
        public static final int TYPE_JSON = 1000;
        private MyActivity myActivity;
        private int priority;
        private int type;

        public HttpGroupSetting()
        {
        }
    }

    public static class HttpGroupSync extends HttpGroup
    {

        public void execute(HttpRequest httprequest)
        {
        }

        public HttpGroupSync(HttpGroupSetting httpgroupsetting)
        {
            super(httpgroupsetting);
        }
    }

    public static class HttpGroupaAsynPool extends HttpGroup
    {

        public void execute(final HttpRequest httpRequest)
        {
            PooledThread.getThreadPool().offerTask(new Runnable() {

                public void run()
                {
                    if(httpList.size() < 1)
                        onStart();
                    httpList.add(httpRequest);
                    httpRequest.nextHandler();
                }

                final HttpGroupaAsynPool this$1;
                private final HttpRequest val$httpRequest;

                
                {
                    this$1 = HttpGroupaAsynPool.this;
                    httpRequest = httprequest;
                    super();
                }
            }
, httpRequest.getHttpSetting().getPriority());
        }

        public HttpGroupaAsynPool(HttpGroupSetting httpgroupsetting)
        {
            super(httpgroupsetting);
        }
    }

    public class HttpRequest
        implements StopController
    {
        class HttpDialogController extends DialogController
        {

            protected void actionCancel()
            {
                actionCommon(false);
            }

            protected void actionCommon(boolean flag)
            {
                alertDialog.dismiss();
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- notifyUser() retry -->> httpRequestList.size() = ").append(httpRequestList.size()).toString());
                HashMap hashmap = HttpGroup.alertDialogStateMap;
                hashmap;
                JVM INSTR monitorenter ;
                int i = 0;
_L2:
                if(i < httpRequestList.size())
                    break MISSING_BLOCK_LABEL_91;
                HttpGroup.alertDialogStateMap.remove(myActivity);
                hashmap;
                JVM INSTR monitorexit ;
                return;
                HttpRequest httprequest;
                httprequest = (HttpRequest)httpRequestList.get(i);
                if(flag)
                    httprequest.manualRetry = true;
                httprequest;
                JVM INSTR monitorenter ;
                httprequest.notify();
                httprequest;
                JVM INSTR monitorexit ;
                i++;
                if(true) goto _L2; else goto _L1
_L1:
                Exception exception;
                exception;
                throw exception;
            }

            protected void actionRetry()
            {
                actionCommon(true);
            }

            public void init(ArrayList arraylist, MyActivity myactivity)
            {
                myActivity = myactivity;
                httpRequestList = arraylist;
                init(((android.content.Context) (myactivity)));
            }

            protected ArrayList httpRequestList;
            protected MyActivity myActivity;
            final HttpRequest this$1;

            HttpDialogController()
            {
                this$1 = HttpRequest.this;
                super();
            }
        }


        private void alertAttestationWIFIDialog()
        {
            HttpDialogController httpdialogcontroller = new HttpDialogController() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    switch(i)
                    {
                    case -1: 
                        switch(state)
                        {
                        case 0: // '\0'
                            if(Log.D)
                                Log.d("HttpGroup", "http dialog BUTTON_POSITIVE -->> 1");
                            state = 1;
                            myActivity.post(new Runnable() {

                                public void run()
                                {
                                    if(Log.D)
                                        Log.d("HttpGroup", "http dialog change -->> ");
                                    setMessage("\u73B0\u5728\u662F\u5426\u91CD\u8BD5\uFF1F");
                                    setPositiveButton("\u91CD\u8BD5");
                                    if(!D.isShowing())
                                        D.show();
                                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://app.360buy.com/"));
                                    intent.setFlags(0x10000000);
                                    MyApplication.getInstance().startActivity(intent);
                                }

                                final _cls12 this$2;

                        
                        {
                            this$2 = _cls12.this;
                            super();
                        }
                            }
);
                            break;

                        case 1: // '\001'
                            if(Log.D)
                                Log.d("HttpGroup", "http dialog BUTTON_POSITIVE -->> 2");
                            actionRetry();
                            break;
                        }
                        // fall through

                    default:
                        if(false)
                            ;
                        break;

                    case -2: 
                        if(Log.D)
                            Log.d("HttpGroup", "http dialog BUTTON_NEGATIVE -->> 1");
                        actionCancel();
                        break;
                    }
                    while(true) 
                        return;
                }

                private int state;
                final HttpRequest this$1;


                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            httpdialogcontroller.setTitle("WIFI\u8BA4\u8BC1");
            httpdialogcontroller.setMessage("\u60A8\u6240\u8FDE\u63A5\u7684\u7F51\u7EDC\u53EF\u80FD\u9700\u8981\u9A8C\u8BC1\uFF0C\u73B0\u5728\u6253\u5F00\u6D4F\u89C8\u5668\u8FDB\u884C\u9A8C\u8BC1\uFF1F");
            httpdialogcontroller.setPositiveButton("\u786E\u5B9A");
            httpdialogcontroller.setNegativeButton("\u53D6\u6D88");
            notifyUser(httpdialogcontroller);
        }

        private void alertErrorDialog()
        {
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- alertErrorDialog() -->> ").toString());
            if(httpSetting.isNotifyUser())
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- alertErrorDialog() -->> true").toString());
                HttpDialogController httpdialogcontroller = new HttpDialogController() {

                    public void onClick(DialogInterface dialoginterface, int j)
                    {
                        j;
                        JVM INSTR tableswitch -2 -1: default 24
                    //                                   -2 32
                    //                                   -1 25;
                           goto _L1 _L2 _L3
_L1:
                        return;
_L3:
                        actionRetry();
                        continue; /* Loop/switch isn't completed */
_L2:
                        actionCancel();
                        if(true) goto _L1; else goto _L4
_L4:
                    }

                    final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
                }
;
                httpdialogcontroller.setTitle(MyApplication.getInstance().getText(0x7f090019));
                httpdialogcontroller.setMessage(MyApplication.getInstance().getText(0x7f09001a));
                httpdialogcontroller.setPositiveButton(MyApplication.getInstance().getText(0x7f09001b));
                MyApplication myapplication = MyApplication.getInstance();
                int i;
                if(httpSetting.isNotifyUserWithExit())
                    i = 0x7f090010;
                else
                    i = 0x7f09000f;
                httpdialogcontroller.setNegativeButton(myapplication.getText(i));
                notifyUser(httpdialogcontroller);
            }
        }

        private void beforeConnection()
        {
            if(!checkModule(3))
                break MISSING_BLOCK_LABEL_214;
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- encrypt -->> ").toString());
            if(HttpGroup.mMd5Key != null) goto _L2; else goto _L1
_L1:
            HttpGroup.queryMd5Key(continueListener);
            this;
            JVM INSTR monitorenter ;
            try
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- encrypt wait start -->> ").toString());
                wait();
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- encrypt wait end -->> ").toString());
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            this;
            JVM INSTR monitorexit ;
_L2:
            httpSetting.setFinalUrl((new StringBuilder(String.valueOf(httpSetting.getUrl()))).append("&hash=").append(Md5Encrypt.md5((new StringBuilder(String.valueOf(httpSetting.getJsonParams().toString()))).append(HttpGroup.mMd5Key).toString())).toString());
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private void clearErrorList()
        {
            getErrorList().clear();
        }

        private void doNetAndCache()
        {
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- doNetAndCache() -->> ").toString());
            if(1 != httpSetting.getCacheMode()) goto _L2; else goto _L1
_L1:
            HttpError httperror = new HttpError(new Exception("no cache"));
            httperror.setNoRetry(true);
            throwError(httperror);
_L4:
            return;
_L2:
            nextHandler();
            if(!isLastError())
                save();
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void fileContent()
        {
            FileGuider fileguider = httpSetting.getSavePath();
            fileguider.setAvailableSize(httpResponse.getLength());
            java.io.FileOutputStream fileoutputstream = FileService.openFileOutput(fileguider);
            IOUtil.readAsFile(httpResponse.getInputStream(), fileoutputstream, ioProgressListener, this);
            File file = new File(MyApplication.getInstance().getFilesDir(), fileguider.getFileName());
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- download() apkFilePath -->> ").append(file).toString());
            if(isStop())
                file.delete();
            httpResponse.setSaveFile(file);
_L1:
            return;
            Exception exception;
            exception;
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- file content connection read error -->> ").toString(), exception);
            throwError(new HttpError(exception));
            connectionRetry = true;
              goto _L1
        }

        private File findCachesFileByMd5()
        {
            FileService.Directory directory;
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- findCachesFileByMd5() -->> ").toString());
            directory = null;
            httpSetting.getType();
            JVM INSTR lookupswitch 2: default 76
        //                       1000: 129
        //                       5000: 137;
               goto _L1 _L2 _L3
_L1:
            break; /* Loop/switch isn't completed */
_L3:
            break MISSING_BLOCK_LABEL_137;
_L4:
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- findCachesFileByMd5() directory -->> ").append(directory).toString());
            File file1;
            if(directory == null)
            {
                file1 = null;
            } else
            {
                File file = directory.getDir();
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- findCachesFileByMd5() dir.exists() -->> ").append(file.exists()).toString());
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- findCachesFileByMd5() dir.isDirectory() -->> ").append(file.isDirectory()).toString());
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- findCachesFileByMd5() dir -->> ").append(file).toString());
                File afile[] = file.listFiles(new FilenameFilter() {

                    public boolean accept(File file2, String s)
                    {
                        String s1 = httpSetting.getMd5();
                        boolean flag;
                        if(s1 == null)
                            flag = false;
                        else
                            flag = s.startsWith(s1);
                        return flag;
                    }

                    final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
                }
);
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- findCachesFileByMd5() fileList -->> ").append(afile).toString());
                if(afile != null && afile.length > 0)
                {
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- can find caches file by md5 -->> ").toString());
                    file1 = afile[0];
                } else
                {
                    file1 = null;
                }
            }
            return file1;
_L2:
            directory = FileService.getDirectory(2);
              goto _L4
            directory = FileService.getDirectory(1);
              goto _L4
        }

        private void get()
            throws Exception
        {
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- get() -->> ").toString());
            httpResponse = new HttpResponse(conn);
            conn.setRequestMethod("GET");
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- get() -->> ok").toString());
        }

        private android.graphics.BitmapFactory.Options getBitmapOpt()
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            if(httpResponse.getLength() > 0x10000L)
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- opt.inSampleSize -->> ").append(2).toString());
                options.inSampleSize = 2;
            }
            return options;
        }

        private ArrayList getErrorList()
        {
            if(errorList == null)
                errorList = new ArrayList();
            return errorList;
        }

        private HttpError getLastError()
        {
            ArrayList arraylist = getErrorList();
            int i = arraylist.size();
            HttpError httperror;
            if(i > 0)
                httperror = (HttpError)arraylist.get(i - 1);
            else
                httperror = null;
            return httperror;
        }

        private void handleGetOrPost()
            throws Exception
        {
            if(httpSetting.isPost())
                post();
            else
                get();
            connectionHandler2();
        }

        private void imageContent()
            throws Exception
        {
            if(httpResponse.getType() == null || !httpResponse.getType().contains("image/"))
            {
                HttpError httperror = new HttpError();
                httperror.setErrorCode(2);
                httperror.setResponseCode(404);
                throwError(httperror);
                connectionRetry = true;
            } else
            {
                try
                {
                    httpResponse.setInputData(IOUtil.readAsBytes(httpResponse.getInputStream(), ioProgressListener));
                    Bitmap bitmap = BitmapFactory.decodeByteArray(httpResponse.getInputData(), 0, httpResponse.getInputData().length, getBitmapOpt());
                    httpResponse.setBitmap(bitmap);
                    httpResponse.setDrawable(new BitmapDrawable(bitmap));
                }
                catch(Throwable throwable)
                {
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- image content connection read error -->> ").toString());
                    HttpError httperror1 = new HttpError(throwable);
                    httperror1.setNoRetry(true);
                    throwError(httperror1);
                }
            }
        }

        private void jsonContent()
            throws Exception
        {
            if(httpResponse.getType() != null && httpResponse.getType().contains("application/json")) goto _L2; else goto _L1
_L1:
            HttpError httperror = new HttpError();
            httperror.setErrorCode(2);
            httperror.setResponseCode(404);
            throwError(httperror);
            connectionRetry = true;
_L4:
            return;
_L2:
            Integer integer;
            try
            {
                httpResponse.setString(IOUtil.readAsString(httpResponse.getInputStream(), HttpGroup.charset, ioProgressListener));
                if(Log.I)
                    Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- response string -->> ").append(httpResponse.getString()).toString());
            }
            catch(Exception exception)
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- json content connection read error -->> ").toString(), exception);
                throwError(new HttpError(exception));
                connectionRetry = true;
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                httpResponse.setJsonObject(new JSONObjectProxy(new JSONObject(httpResponse.getString())));
            }
            catch(JSONException jsonexception)
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- Can not format json -->> ").toString(), jsonexception);
                throwError(new HttpError(jsonexception));
                connectionRetry = true;
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                integer = Integer.valueOf(httpResponse.getJSONObject().getString("code"));
            }
            catch(NumberFormatException numberformatexception)
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- Can not format jsonCode -->> ").toString(), numberformatexception);
                throwError(new HttpError(numberformatexception));
                connectionRetry = true;
                continue; /* Loop/switch isn't completed */
            }
            catch(JSONException jsonexception1)
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- not find jsonCode -->> ").toString(), jsonexception1);
                throwError(new HttpError(jsonexception1));
                connectionRetry = true;
                continue; /* Loop/switch isn't completed */
            }
            if(integer == null || integer.intValue() == 0)
                continue; /* Loop/switch isn't completed */
            if(!integer.equals(Integer.valueOf(9)))
                break MISSING_BLOCK_LABEL_590;
            HttpGroup.queryMd5Key(continueListener);
            this;
            JVM INSTR monitorenter ;
            try
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- encrypt wait start -->> ").append(httpSetting.getUrl()).toString());
                wait();
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- encrypt wait end -->> ").append(httpSetting.getUrl()).toString());
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            this;
            JVM INSTR monitorexit ;
            connectionRetry = true;
            continue; /* Loop/switch isn't completed */
            Exception exception1;
            exception1;
            throw exception1;
            if(integer.equals(Integer.valueOf(10)))
            {
                setModule(3);
                connectionRetry = true;
            } else
            if(integer.intValue() == -1 || integer.intValue() == -2)
            {
                HttpError httperror1 = new HttpError();
                httperror1.setErrorCode(3);
                httperror1.setJsonCode(integer.intValue());
                httperror1.setHttpResponse(httpResponse);
                throwError(httperror1);
                connectionRetry = true;
            } else
            if(integer.intValue() == 30 || integer.intValue() == 1 || integer.intValue() == 2)
            {
                final MyActivity myActivity = httpGroupSetting.getMyActivity();
                if(myActivity != null)
                    myActivity.post(new Runnable() {

                        public void run()
                        {
                            Toast.makeText(myActivity, 0x7f090018, 1).show();
                        }

                        final HttpRequest this$1;
                        private final MyActivity val$myActivity;

                
                {
                    this$1 = HttpRequest.this;
                    myActivity = myactivity;
                    super();
                }
                    }
);
                HttpError httperror2 = new HttpError();
                httperror2.setErrorCode(3);
                httperror2.setJsonCode(integer.intValue());
                httperror2.setHttpResponse(httpResponse);
                httperror2.setNoRetry(true);
                throwError(httperror2);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void nextHandler()
        {
            int i = currentHandlerIndex;
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- nextHandler() i -->> ").append(currentHandlerIndex).toString());
            currentHandlerIndex = 1 + currentHandlerIndex;
            if(i < handlers.size())
            {
                ((Handler)handlers.get(i)).run();
                currentHandlerIndex = i;
            }
        }

        private void notifyUser(final HttpDialogController httpDialogController)
        {
            MyActivity myactivity = httpGroupSetting.getMyActivity();
            if(myactivity != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            boolean flag = false;
            HashMap hashmap = HttpGroup.alertDialogStateMap;
            hashmap;
            JVM INSTR monitorenter ;
            ArrayList arraylist;
            ArrayList arraylist1;
            arraylist = (ArrayList)HttpGroup.alertDialogStateMap.get(myactivity);
            if(arraylist != null)
                break MISSING_BLOCK_LABEL_68;
            arraylist1 = new ArrayList();
            HttpGroup.alertDialogStateMap.put(myactivity, arraylist1);
            flag = true;
            arraylist = arraylist1;
            arraylist.add(this);
            hashmap;
            JVM INSTR monitorexit ;
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- notifyUser() -->> result = ").append(flag).toString());
            if(flag)
            {
                httpDialogController.init(arraylist, myactivity);
                myactivity.post(new Runnable() {

                    public void run()
                    {
                        httpDialogController.show();
                    }

                    final HttpRequest this$1;
                    private final HttpDialogController val$httpDialogController;

                
                {
                    this$1 = HttpRequest.this;
                    httpDialogController = httpdialogcontroller;
                    super();
                }
                }
);
            }
            this;
            JVM INSTR monitorenter ;
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- dialog wait start -->> ").toString());
            wait();
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- dialog wait end -->> ").toString());
_L3:
            this;
            JVM INSTR monitorexit ;
              goto _L1
            Exception exception1;
            exception1;
            throw exception1;
            Exception exception;
            exception;
_L4:
            hashmap;
            JVM INSTR monitorexit ;
            throw exception;
            InterruptedException interruptedexception;
            interruptedexception;
            interruptedexception.printStackTrace();
              goto _L3
            exception;
              goto _L4
        }

        private void post()
            throws Exception
        {
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- post() -->> ").toString());
            httpResponse = new HttpResponse(conn);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            (byte[])null;
            if(httpSetting.getMapParams() != null) goto _L2; else goto _L1
_L1:
            byte abyte0[] = "body=".getBytes();
_L4:
            conn.setRequestProperty("Content-Length", String.valueOf(abyte0.length));
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- post() -->> 1").toString());
            DataOutputStream dataoutputstream = new DataOutputStream(conn.getOutputStream());
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- post() -->> 2").toString());
            dataoutputstream.write(abyte0);
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- post() -->> ready").toString());
            dataoutputstream.flush();
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- post() -->> ok").toString());
            return;
_L2:
            StringBuilder stringbuilder = new StringBuilder();
            Map map = httpSetting.getMapParams();
            Iterator iterator = map.keySet().iterator();
            do
            {
label0:
                {
                    if(iterator.hasNext())
                        break label0;
                    abyte0 = stringbuilder.toString().getBytes();
                }
                if(true)
                    continue;
                String s = (String)iterator.next();
                if(!"functionId".equals(s))
                {
                    String s1 = (String)map.get(s);
                    if(Log.I)
                        Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- param key and value -->> ").append(s).append("\uFF1A").append(s1).toString());
                    stringbuilder.append(s).append("=").append(s1);
                    if(iterator.hasNext())
                        stringbuilder.append("&");
                }
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
        }

        private void save()
        {
            if(!httpSetting.isLocalFileCache()) goto _L2; else goto _L1
_L1:
            httpSetting.getType();
            JVM INSTR lookupswitch 2: default 44
        //                       1000: 45
        //                       5000: 234;
               goto _L2 _L3 _L4
_L2:
            return;
_L3:
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- save json file start -->> ").toString());
            FileService.Directory directory1 = FileService.getDirectory(2);
            if(directory1 != null)
            {
                String s1 = (new StringBuilder(String.valueOf(httpSetting.getMd5()))).append(".json").toString();
                if(httpResponse != null)
                {
                    String s2 = httpResponse.getString();
                    boolean flag1 = FileService.saveToSDCard(FileService.getDirectory(2), s1, s2);
                    if(flag1)
                    {
                        CacheFile cachefile1 = new CacheFile(s1, httpSetting.getLocalFileCacheTime());
                        cachefile1.setDirectory(directory1);
                        CacheFileTable.insertOrUpdate(cachefile1);
                    }
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- save json file -->> ").append(flag1).toString());
                }
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- save image file start -->> ").toString());
            FileService.Directory directory = FileService.getDirectory(1);
            if(directory != null)
            {
                String s = (new StringBuilder(String.valueOf(httpSetting.getMd5()))).append(".image").toString();
                if(httpResponse != null)
                {
                    boolean flag = FileService.saveToSDCard(directory, s, httpResponse.getInputData());
                    if(flag)
                    {
                        CacheFile cachefile = new CacheFile(s, httpSetting.getLocalFileCacheTime());
                        cachefile.setDirectory(directory);
                        CacheFileTable.insertOrUpdate(cachefile);
                    }
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- save image file -->> ").append(flag).toString());
                }
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        private void urlParam()
        {
            if(!httpSetting.isPost()) goto _L2; else goto _L1
_L1:
            if(httpSetting.getMapParams() != null)
                if(reportUserInfoFlag)
                    httpSetting.setUrl((new StringBuilder(String.valueOf(httpSetting.getUrl()))).append("?").append("functionId=").append((String)httpSetting.getMapParams().get("functionId")).append(StatisticsReportUtil.getReportString(httpSetting.isNeedGlobalInitialization())).toString());
                else
                    httpSetting.setUrl((new StringBuilder(String.valueOf(httpSetting.getUrl()))).append("?").append("functionId=").append((String)httpSetting.getMapParams().get("functionId")).toString());
_L6:
            return;
_L2:
            if(httpSetting.getMapParams() == null) goto _L4; else goto _L3
_L3:
            StringBuilder stringbuilder;
            Map map;
            Iterator iterator;
            stringbuilder = new StringBuilder(httpSetting.getUrl());
            stringbuilder.append("?");
            map = httpSetting.getMapParams();
            iterator = map.keySet().iterator();
_L7:
label0:
            {
                if(iterator.hasNext())
                    break label0;
                String s;
                String s1;
                if(reportUserInfoFlag)
                    httpSetting.setUrl((new StringBuilder(String.valueOf(stringbuilder.toString()))).append(StatisticsReportUtil.getReportString(httpSetting.isNeedGlobalInitialization())).toString());
                else
                    httpSetting.setUrl(stringbuilder.toString());
            }
_L4:
            if(true) goto _L6; else goto _L5
_L5:
            s = (String)iterator.next();
            s1 = (String)map.get(s);
            stringbuilder.append(s).append("=").append(s1);
            if(iterator.hasNext())
                stringbuilder.append("&");
              goto _L7
        }

        public void checkErrorInteraction()
        {
            HttpError httperror = getLastError();
            if(httperror == null || httperror.getErrorCode() != 0 || !"attestation WIFI".equals(httperror.getException().getMessage())) goto _L2; else goto _L1
_L1:
            alertAttestationWIFIDialog();
_L4:
            return;
_L2:
            if(isLastError())
                alertErrorDialog();
            if(true) goto _L4; else goto _L3
_L3:
        }

        protected boolean checkModule(int i)
        {
            if(httpSetting.getFunctionId() == null || HttpGroup.mModules == null) goto _L2; else goto _L1
_L1:
            Integer integer = HttpGroup.mModules.getIntOrNull(httpSetting.getFunctionId());
            if(integer == null || i != integer.intValue()) goto _L2; else goto _L3
_L3:
            boolean flag = true;
_L5:
            return flag;
_L2:
            flag = false;
            if(true) goto _L5; else goto _L4
_L4:
        }

        protected void connectionHandler2()
        {
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- connectionHandler2() -->> ").toString());
            conn.connect();
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- ResponseCode() -->> ").toString());
            httpResponse.setHeaderFields(conn.getHeaderFields());
            if(!Log.D) goto _L2; else goto _L1
_L1:
            JSONObject jsonobject;
            Iterator iterator;
            Set set = conn.getHeaderFields().entrySet();
            jsonobject = new JSONObject();
            iterator = set.iterator();
_L6:
            if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
            Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- headerFields -->> ").append(jsonobject.toString()).toString());
_L2:
            httpResponse.setCode(conn.getResponseCode());
            httpResponse.setLength(conn.getContentLength());
            addMaxProgress(Long.valueOf(httpResponse.getLength()).intValue());
            httpResponse.setType(conn.getContentType());
            if(httpResponse.getCode() != 200)
            {
                HttpError httperror1 = new HttpError();
                httperror1.setErrorCode(2);
                httperror1.setResponseCode(httpResponse.getCode());
                throwError(httperror1);
                connectionRetry = true;
                break MISSING_BLOCK_LABEL_755;
            }
            break MISSING_BLOCK_LABEL_408;
_L4:
            java.util.Map.Entry entry;
            String s1;
            entry = (java.util.Map.Entry)iterator.next();
            if(entry.getKey() != null)
                break; /* Loop/switch isn't completed */
            s1 = "<null>";
_L7:
            jsonobject.put(s1, (new JSONArray((Collection)entry.getValue())).toString());
            Exception exception;
            HttpError httperror;
            if(true) goto _L6; else goto _L5
_L5:
            s1 = (String)entry.getKey();
              goto _L7
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- ResponseCode() -->> ok").toString());
            String s = conn.getHeaderField("Set-Cookie");
            if(s != null)
            {
                if(Log.D)
                    Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- cookies get -->> ").append(s).toString());
                HttpGroup.cookies = s.substring(0, s.indexOf(";"));
            }
            if(!"gzip".equals(conn.getHeaderField("Content-Encoding"))) goto _L9; else goto _L8
_L8:
            Object obj = new GZIPInputStream(conn.getInputStream());
_L10:
            httpResponse.setInputStream(((InputStream) (obj)));
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- ResponseCode() -->> ok nextHandler()").toString());
            nextHandler();
            try
            {
                if(httpResponse.getInputStream() != null)
                {
                    httpResponse.getInputStream().close();
                    httpResponse.setInputStream(null);
                }
                if(conn != null)
                {
                    conn.disconnect();
                    conn = null;
                }
            }
            catch(Exception exception3) { }
            break MISSING_BLOCK_LABEL_755;
_L9:
            InputStream inputstream = conn.getInputStream();
            obj = inputstream;
              goto _L10
            Exception exception1;
            exception1;
            try
            {
                if(httpResponse.getInputStream() != null)
                {
                    httpResponse.getInputStream().close();
                    httpResponse.setInputStream(null);
                }
                if(conn != null)
                {
                    conn.disconnect();
                    conn = null;
                }
            }
            catch(Exception exception2) { }
            try
            {
                throw exception1;
            }
            // Misplaced declaration of an exception variable
            catch(Exception exception)
            {
                if(exception instanceof SocketTimeoutException)
                {
                    httperror = new HttpError();
                    httperror.setErrorCode(1);
                    throwError(httperror);
                } else
                {
                    throwError(new HttpError(exception));
                }
                connectionRetry = true;
            }
        }

        public HttpSetting getHttpSetting()
        {
            return httpSetting;
        }

        public boolean isLastError()
        {
            boolean flag;
            if(errorList != null && errorList.size() >= HttpGroup.attempts)
                flag = true;
            else
                flag = false;
            if(!flag)
            {
                HttpError httperror = getLastError();
                if(httperror != null && httperror.isNoRetry())
                    flag = true;
            }
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- isLastError() -->> ").append(flag).toString());
            return flag;
        }

        public boolean isStop()
        {
            return stopFlag;
        }

        protected void setModule(int i)
        {
            if(httpSetting.getFunctionId() == null || HttpGroup.mModules == null)
                break MISSING_BLOCK_LABEL_31;
            HttpGroup.mModules.put(httpSetting.getFunctionId(), i);
_L1:
            return;
            JSONException jsonexception;
            jsonexception;
            jsonexception.printStackTrace();
              goto _L1
        }

        public void stop()
        {
            stopFlag = true;
        }

        public void throwError(HttpError httperror)
        {
            ArrayList arraylist = getErrorList();
            arraylist.add(httperror);
            httperror.setTimes(arraylist.size());
            if(Log.I)
                Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- HttpError -->> ").append(httperror).toString());
            checkErrorInteraction();
        }

        public void typeHandler()
        {
            nextHandler();
        }

        protected static final int MODULE_STATE_DISABLE = 0;
        protected static final int MODULE_STATE_ENCRYPT = 3;
        private Handler cacheHandler;
        protected HttpURLConnection conn;
        private Handler connectionHandler;
        protected boolean connectionRetry;
        private Handler contentHandler;
        private CompleteListener continueListener;
        private int currentHandlerIndex;
        protected ArrayList errorList;
        private Handler firstHandler;
        private ArrayList handlers;
        protected HttpResponse httpResponse;
        protected HttpSetting httpSetting;
        protected InputStream inputStream;
        private IOUtil.ProgressListener ioProgressListener;
        protected boolean manualRetry;
        private Handler paramHandler;
        private Handler proxyHandler;
        private boolean stopFlag;
        private Handler testHandler;
        private String thirdHost;
        final HttpGroup this$0;
















        public HttpRequest(HttpSetting httpsetting)
        {
            this$0 = HttpGroup.this;
            super();
            currentHandlerIndex = 0;
            handlers = new ArrayList();
            paramHandler = new Handler() {

                public void run()
                {
                    if(httpSetting.getFunctionId() != null)
                    {
                        httpSetting.putMapParams("functionId", httpSetting.getFunctionId());
                        String s = httpSetting.getJsonParams().toString();
                        if(Log.I)
                            Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- body -->> ").append(s).toString());
                        httpSetting.putMapParams("body", s);
                    }
                    nextHandler();
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            proxyHandler = new Handler() {

                public String getHostAndPortByUrl(String s)
                {
                    String s1;
                    if(hostAndPort != null)
                        s1 = hostAndPort;
                    else
                    if(s != null)
                    {
                        int i = 3 + s.indexOf("://");
                        int j = s.indexOf("/", i);
                        if(i == -1)
                            s1 = null;
                        else
                        if(j == -1)
                        {
                            s1 = null;
                        } else
                        {
                            hostAndPort = s.substring(i, j);
                            s1 = hostAndPort;
                        }
                    } else
                    {
                        s1 = null;
                    }
                    return s1;
                }

                public void run()
                {
                    String s = NetUtils.getProxyHost();
                    if(s != null)
                    {
                        String s1 = httpSetting.getUrl();
                        if(s1 != null)
                        {
                            thirdHost = getHostAndPortByUrl(s1);
                            if(thirdHost != null)
                                httpSetting.setUrl(s1.replace(thirdHost, s));
                        }
                    }
                    if(httpSetting.getFunctionId() != null)
                        if(s != null)
                            httpSetting.setUrl((new StringBuilder("http://")).append(s).append("/client.action").toString());
                        else
                            httpSetting.setUrl((new StringBuilder("http://")).append(HttpGroup.host).append("/client.action").toString());
                    nextHandler();
                }

                private String hostAndPort;
                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            firstHandler = new Handler() {

                public void run()
                {
                    if(httpSetting.getConnectTimeout() == 0)
                        httpSetting.setConnectTimeout(HttpGroup.connectTimeout);
                    if(httpSetting.getReadTimeout() == 0)
                        httpSetting.setReadTimeout(HttpGroup.readTimeout);
                    if(httpSetting.getType() == 5000 || httpSetting.getType() == 500)
                        httpSetting.setPost(false);
                    if(httpSetting.getType() == 5000)
                        httpSetting.setReadTimeout(0x36ee80);
                    if(httpSetting.getType() == 5000)
                    {
                        httpSetting.setLocalFileCache(true);
                        httpSetting.setLocalFileCacheTime(0xf731400L);
                    }
                    if(httpSetting.getType() == 5000)
                        httpSetting.setNeedGlobalInitialization(false);
                    if(httpSetting.isNeedGlobalInitialization())
                        GlobalInitialization.initNetwork(true);
                    addMaxStep(1);
                    urlParam();
                    if(checkModule(0))
                    {
                        if(Log.D)
                            Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- functionId close -->> ").toString());
                    } else
                    if(TextUtils.isEmpty(httpSetting.getUrl()) && TextUtils.isEmpty(httpSetting.getFunctionId()) || httpSetting.getUrl().endsWith(".gif") || httpSetting.getUrl().endsWith(".bmp"))
                    {
                        HttpError httperror = new HttpError();
                        httperror.setErrorCode(2);
                        httperror.setResponseCode(404);
                        throwError(httperror);
                        httpSetting.onError(getLastError());
                    } else
                    {
                        nextHandler();
                        if(isLastError())
                        {
                            if(Log.I)
                                Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- onError -->> ").toString());
                            httpSetting.onError(getLastError());
                        } else
                        {
                            if(Log.I)
                                Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- onEnd -->> ").toString());
                            addCompletesCount();
                            addStep(1);
                            httpSetting.onEnd(httpResponse);
                        }
                    }
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            testHandler = new Handler() {

                public void run()
                {
                    if(!Configuration.getBooleanProperty("testMode", Boolean.valueOf(false)).booleanValue()) goto _L2; else goto _L1
_L1:
                    if(!"viewActivity".equals(httpSetting.getFunctionId())) goto _L4; else goto _L3
_L3:
                    httpResponse = new HttpResponse();
                    httpResponse.setString("{\"adword\":\"\u5F15\u7206\u7EA2\u516D\u6708\uFF01\u76F4\u964D314\u5143\uFF01\u52A0\u8D60\u97E9\u56FD\u7CBE\u54C1\u9910\u5177\u5957\u88C5\uFF01\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/2417/17c1667a-ab1d-47d9-9d2d-7c44ded749b2.jpg\",\"jdPrice\":\"899.00\",\"wareId\":\"318157\",\"wmaprice\":\"http://price.360buy.com/P40FB32EE41477A324FFC16FEAA74891F,1.png\",\"wname\":\"\u76F4\u964D314\uFF01\u52A0\u8D60\u7CBE\u54C1\u9910\u5177\u5957\u88C5\uFF01\u79D1\u6C83\u65AF\u667A\u80FD\u673A\u5668\u4EBA\u5438\u5C18\u5668\u5730\u5B9D520\uFF08FR\u99A8\u559C\u7EA2\uFF09\"},{\"adword\":\"\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/4515/5af248ad-f84a-4db7-a124-3fe1fb30c5da.jpg\",\"jdPrice\":\"359.00\",\"wareId\":\"108001\",\"wmaprice\":\"http://price.360buy.com/P7E50A4D7F3680EC11BD8D2251465E4BC,1.png\",\"wname\":\"\u745E\u58EB\u519B\u5200\u5DE5\u4F5C\u51A0\u519B0.9064\u529F\u80FD\u9F50\u5168\uFF0C\u5B8C\u7F8E\u54C1\u8D28\uFF01\"},{\"adword\":\"\u76F4\u964D96\uFF0C\u7B80\u76F4\u8D85\u5B9E\u60E0\uFF01\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/1629/c1bedf11-3450-4029-ba5a-0706c61866ca.jpg\",\"jdPrice\":\"89.00\",\"wareId\":\"261923\",\"wmaprice\":\"http://price.360buy.com/P4A467FE6B0EB74674C81374307B9986B,1.png\",\"wname\":\"\u597D\u5B9C\u4F73\u9AD8\u6548\u7EB3\u7C73\u6539\u6027\u70AD\u5BB6\u5EAD\u88C5HZN2000\"},{\"adword\":\"\u51FA\u884C\u66F4\u65B9\u4FBF\uFF01\u4E0B\u5355\u7ACB\u770120\u5143\uFF01\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/4493/9fad1aa4-3ac6-4d7c-8b14-074e707ff5ed.jpg\",\"jdPrice\":\"99.00\",\"wareId\":\"326802\",\"wmaprice\":\"http://price.360buy.com/P1E2CA69C3EFEECEA27C2A26A93D4AE49,1.png\",\"wname\":\"\u6B65\u884C\u8005\u9632\u6C34\u888B\uFF08\u989C\u8272\u968F\u673A\uFF09\uFF01\uFF01\u4E0B\u5355\u7ACB\u770120\u5143\uFF01\uFF01\"},{\"adword\":\"\u76F4\u964D101\u5143\uFF01\u62A2\u8D2D\u65F6\u95F4\uFF1A13\u70B9-17\u70B9\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/7478/302f0b63-d793-4249-b6d4-e8d0f725d595.jpg\",\"jdPrice\":\"469.00\",\"wareId\":\"1000201612\",\"wmaprice\":\"http://price.360buy.com/PFFBB0AE38B210E68D6F2C55D311B53F4,1.png\",\"wname\":\"E\u8DEF\u822Agps\u5BFC\u822A\u4EEALH980N\u5347\u7EA7\u7248\u9AD8\u6E055\u5BF83D\u5B9E\u666F\u5185\u7F6E4G\u8F66\u8F7D\u5BFC\u822A\u5B98\u65B9\u6807\u914D\"},{\"adword\":\"\u5F15\u7206\u7EA2\u516D\u6708\uFF01\u76F4\u964D100\u5143\uFF013.2\u82F1\u5BF8WQVGA\u9AD8\u6E05\u9753\u5C4F\uFF0C\u9AD8\u6E05RMVB\u89C6\u9891\u76F4\u64AD\uFF0C320\u4E07\u50CF\u7D20\uFF0C\u652F\u6301\u540E\u53F0QQ\uFF01\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/4149/bc3553ee-410e-4b58-8777-e1b1e24bd69f.jpg\",\"jdPrice\":\"699.00\",\"wareId\":\"226753\",\"wmaprice\":\"http://price.360buy.com/P6F7733F4CAC0DB064764D91F9B1E5A91,1.png\",\"wname\":\"\u76F4\u964D100\uFF01\u8054\u60F3i61\u5F71\u97F3\u624B\u673A\uFF013.2\u82F1\u5BF8WQVGA\u9AD8\u6E05\u9753\u5C4F\uFF0C\u9AD8\u6E05RMVB\u89C6\u9891\u76F4\u64AD\uFF01\"},{\"adword\":\"\u75AF\u62A2\u4F18\u60E0300\uFF01\u7279\u4EF7\u4FC3\u9500\u5C31\u5728\u4ECA\u5929\uFF01~\u62A2\u5B8C\u5373\u6B62\uFF01\u5168\u4E0D\u9508\u94A2\u9519\u4F4D\u5F62\u53CC\u5C42\u6CB9\u7F51\uFF0C\u5168\u4E0D\u9508\u94A2\u58F3\u4F53\u52A0\u94A2\u5316\u73BB\u7483\u88C5\u9970\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/2198/39d00199-2a62-4fab-aab6-fc46834ee18a.jpg\",\"jdPrice\":\"998.00\",\"wareId\":\"362238\",\"wmaprice\":\"http://price.360buy.com/P16479663FCCB36E351597EEC5E3A4AA0,1.png\",\"wname\":\"\u4E07\u548CCXW-200-J02C\u94A2\u5316\u73BB\u7483\u8FD1\u5438\u5F0F\u70DF\u673A\u75AF\u62A2\u4F18\u60E0300\u5143\uFF01\u7279\u4EF7\u51FA\u51FB\uFF0C\u9650\u91CF50\u53F0\"},{\"adword\":\"\u597D\u793C\u9001\u4E0D\u505C,\u73B0\u5728\u8D2D\u4E70\u9001\u539F\u88C5\u5305\u9F20,\u767E\u5B9D\u7BB1\u8FD8\u6709\u9AD8\u6863\u63D2\u677F!!!\u7ED9\u4F60\u7535\u8111\u6700\u597D\u7684\u4FDD\u62A4!!!\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/42/8e230abc-07fd-445f-bfb9-4107323f6ece.jpg\",\"jdPrice\":\"3499.00\",\"wareId\":\"408037\",\"wmaprice\":\"http://price.360buy.com/P0637C82DD11C3E8DD81EC4B19EDC4372,1.png\",\"wname\":\"\u534E\u7855X42EI38JZ(i3-380+HD6470),\u4E3B\u6D41\u673A\u578B\u51B0\u70B9\u4EF7,\u9001\u9AD8\u6863\u63D2\u677F,\u539F\u88C5\u5305\u9F20,\u5927\u793C\u5305!\"},{\"adword\":\"\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/4878/71a2fb53-7dc0-414e-a4c0-4dbf41fcd5f5.jpg\",\"jdPrice\":\"1119.00\",\"wareId\":\"348595\",\"wmaprice\":\"http://price.360buy.com/PE57C181E89ADEDA1AEBE2604187D2852,1.png\",\"wname\":\"\u4E09\u661FST951610\u4E07\u5927\u5E7F\u89D2\u5927\u89E6\u6478\u5C4F\uFF01\u65F6\u5C1A\u76F8\u673A\u76F4\u964D200\u5143\uFF0C\u518D\u90014G\u5361\uFF0C\u9650\u65F6\u9650\u91CF\u62A2\u8D2D\uFF01\"},{\"adword\":\"\u7535\u8111\u53EA\u4E70\u6700\u65B0\u7684\uFF01\u65B0\u5E73\u53F0i5\uFF0C\u672C\u5468\u7279\u4EF7\u5566\uFF01\",\"book\":\"false\",\"imageurl\":\"http://img10.360buyimg.com/n5/778/ff284d0a-256d-4fd2-965e-4d978becf00a.jpg\",\"jdPrice\":\"4699.00\",\"wareId\":\"355888\",\"wmaprice\":\"http://price.360buy.com/PBE806E20BEE3D450022A37D49D8FDB7F,1.png\",\"wname\":\"\u4E70\u7535\u8111\u5C31\u8981\u559C\u65B0\u538C\u65E7\uFF01\u65B0\u5E73\u53F0i5+1G\u72EC\u663E\uFF0C\u8054\u60F3G470\u7279\u4EF7\u5566\uFF01\"}");
                    try
                    {
                        httpResponse.setJsonObject(new JSONObjectProxy(new JSONObject(httpResponse.getString())));
                    }
                    catch(JSONException jsonexception1)
                    {
                        jsonexception1.printStackTrace();
                    }
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- test json file -->> ").toString());
_L6:
                    return;
_L4:
                    if("listActivity".equals(httpSetting.getFunctionId()))
                    {
                        httpResponse = new HttpResponse();
                        httpResponse.setString("{\"activityList\":[{\"activityId\":14215100,\"horizontalImag\":\"http://img11.360buyimg.com/da/20110531/766_120_WAYiog.jpg\",\"verticalImage\":\"http://img11.360buyimg.com/da/20110531/766_120_WAYiog.jpg\"},{\"activityId\":14215100,\"horizontalImag\":\"http://img11.360buyimg.com/da/20110531/766_120_WAYiog.jpg\",\"verticalImage\":\"http://img11.360buyimg.com/da/20110531/766_120_WAYiog.jpg\"}],\"code\":\"0\"}");
                        try
                        {
                            httpResponse.setJsonObject(new JSONObjectProxy(new JSONObject(httpResponse.getString())));
                        }
                        catch(JSONException jsonexception)
                        {
                            jsonexception.printStackTrace();
                        }
                        if(Log.D)
                            Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- test json file -->> ").toString());
                    } else
                    {
                        nextHandler();
                    }
                    continue; /* Loop/switch isn't completed */
_L2:
                    nextHandler();
                    if(true) goto _L6; else goto _L5
_L5:
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            cacheHandler = new Handler() {

                public void run()
                {
                    if(httpSetting.getCacheMode() == 2 || !httpSetting.isLocalFileCache()) goto _L2; else goto _L1
_L1:
                    File file = findCachesFileByMd5();
                    if(file == null) goto _L2; else goto _L3
_L3:
                    if(httpSetting.getLocalFileCacheTime() == 0L || !CacheFileTable.isExpired(file)) goto _L5; else goto _L4
_L4:
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- local file cache time out -->> ").toString());
                    doNetAndCache();
_L6:
                    return;
_L5:
                    httpResponse = new HttpResponse();
                    httpSetting.getType();
                    JVM INSTR lookupswitch 2: default 168
                //                               1000: 171
                //                               5000: 359;
                       goto _L6 _L7 _L8
_L7:
                    FileInputStream fileinputstream;
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- read json file -->> ").toString());
                    fileinputstream = null;
                    FileInputStream fileinputstream1 = new FileInputStream(file);
                    httpResponse.setString(IOUtil.readAsString(fileinputstream1, HttpGroup.charset));
                    httpResponse.setJsonObject(new JSONObjectProxy(new JSONObject(httpResponse.getString())));
                    if(fileinputstream1 != null)
                        try
                        {
                            fileinputstream1.close();
                        }
                        catch(Exception exception5) { }
                      goto _L6
                    Exception exception6;
                    exception6;
                    Exception exception1 = exception6;
_L10:
                    exception1.printStackTrace();
                    file.delete();
                    httpResponse = null;
                    doNetAndCache();
                    if(fileinputstream != null)
                        try
                        {
                            fileinputstream.close();
                        }
                        catch(Exception exception4) { }
                      goto _L6
                    Exception exception2;
                    exception2;
_L9:
                    Throwable throwable;
                    Bitmap bitmap;
                    if(fileinputstream != null)
                        try
                        {
                            fileinputstream.close();
                        }
                        catch(Exception exception3) { }
                    throw exception2;
_L8:
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- read image file -->> ").toString());
                    try
                    {
                        httpResponse.setLength(file.length());
                        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), getBitmapOpt());
                        httpResponse.setBitmap(bitmap);
                        httpResponse.setDrawable(new BitmapDrawable(bitmap));
                    }
                    // Misplaced declaration of an exception variable
                    catch(Throwable throwable)
                    {
                        file.delete();
                        httpResponse = null;
                        doNetAndCache();
                    }
                      goto _L6
_L2:
                    doNetAndCache();
                      goto _L6
                    exception2;
                    fileinputstream = fileinputstream1;
                      goto _L9
                    Exception exception;
                    exception;
                    exception1 = exception;
                    fileinputstream = fileinputstream1;
                      goto _L10
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            connectionHandler = new Handler() {

                public void run()
                {
                    int i = 0;
_L10:
                    if(i < HttpGroup.attempts) goto _L2; else goto _L1
_L1:
                    return;
_L2:
                    boolean flag = false;
                    beforeConnection();
                    String s = httpSetting.getFinalUrl();
                    if(s == null)
                        s = httpSetting.getUrl();
                    URL url = new URL(s);
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- url.openConnection() -->> ").toString());
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- finalUrl -->> ").append(url).toString());
                    conn = (HttpURLConnection)url.openConnection();
                    if(NetUtils.getProxyHost() == null) goto _L4; else goto _L3
_L3:
                    HttpURLConnection httpurlconnection = conn;
                    if(thirdHost == null) goto _L6; else goto _L5
_L5:
                    String s2 = thirdHost;
_L11:
                    httpurlconnection.setRequestProperty("X-Online-Host", s2);
_L4:
                    conn.setConnectTimeout(httpSetting.getConnectTimeout());
                    conn.setReadTimeout(httpSetting.getReadTimeout());
                    conn.setUseCaches(useCaches);
                    conn.setRequestProperty("Charset", HttpGroup.charset);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
                    if(HttpGroup.cookies != null)
                    {
                        if(Log.D)
                            Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- cookies set -->> ").append(HttpGroup.cookies).toString());
                        conn.setRequestProperty("Cookie", HttpGroup.cookies);
                        CommonUtil.getJdSharedPreferences().edit().putString("cookies", HttpGroup.cookies).commit();
                    }
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- handleGetOrPost() -->> ").toString());
                    handleGetOrPost();
                    if(!connectionRetry) goto _L8; else goto _L7
_L7:
                    connectionRetry = false;
                    flag = true;
_L8:
                    if(!flag) goto _L1; else goto _L9
_L9:
                    Exception exception;
                    HttpError httperror;
                    String s1;
                    if(i < HttpGroup.attempts - 1)
                        try
                        {
                            if(Log.D)
                                Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- sleep -->> ").append(HttpGroup.attemptsTime).toString());
                            Thread.sleep(HttpGroup.attemptsTime);
                        }
                        catch(InterruptedException interruptedexception)
                        {
                            interruptedexception.printStackTrace();
                        }
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- onRetry -->> ").append(" manualRetry = ").append(manualRetry).toString());
                    if(manualRetry)
                    {
                        manualRetry = false;
                        clearErrorList();
                        i = 0;
                    } else
                    {
                        i++;
                    }
                      goto _L10
_L6:
                    s1 = HttpGroup.host;
                    s2 = s1;
                      goto _L11
                    exception;
                    httperror = new HttpError(exception);
                    throwError(httperror);
                    flag = true;
                      goto _L8
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            contentHandler = new Handler() {

                public void run()
                {
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- contentHandler -->>").toString());
                    if(httpSetting.getType() != 1000) goto _L2; else goto _L1
_L1:
                    jsonContent();
_L5:
                    httpResponse.clean();
                    if(Log.D)
                        Log.d("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- contentHandler -->> ok").toString());
_L6:
                    return;
_L2:
                    if(httpSetting.getType() != 5000) goto _L4; else goto _L3
_L3:
                    imageContent();
                      goto _L5
                    Exception exception;
                    exception;
                    HttpError httperror = new HttpError(exception);
                    throwError(httperror);
                    connectionRetry = true;
                      goto _L6
_L4:
                    if(httpSetting.getType() != 500) goto _L5; else goto _L7
_L7:
                    fileContent();
                      goto _L5
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            ioProgressListener = new IOUtil.ProgressListener() {

                public void notify(int i, int j)
                {
                    addProgress(i);
                    httpSetting.onProgress(Long.valueOf(httpResponse.getLength()).intValue(), j);
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            continueListener = new CompleteListener() {

                public void onComplete(Bundle bundle)
                {
                    HttpRequest httprequest = HttpRequest.this;
                    httprequest;
                    JVM INSTR monitorenter ;
                    notify();
                    return;
                }

                final HttpRequest this$1;

                
                {
                    this$1 = HttpRequest.this;
                    super();
                }
            }
;
            httpSetting = httpsetting;
            handlers.add(proxyHandler);
            handlers.add(paramHandler);
            handlers.add(firstHandler);
            handlers.add(testHandler);
            handlers.add(cacheHandler);
            handlers.add(connectionHandler);
            handlers.add(contentHandler);
        }
    }

    public class HttpResponse
    {

        private void imageClean()
        {
            softReferenceInputData = new SoftReference(inputData);
            softReferenceBitmap = new SoftReference(bitmap);
            softReferenceDrawable = new SoftReference(drawable);
            inputData = null;
            bitmap = null;
            drawable = null;
        }

        public void clean()
        {
            httpURLConnection = null;
        }

        public Bitmap getBitmap()
        {
            Bitmap bitmap1;
            if(bitmap != null)
            {
                Bitmap bitmap2 = bitmap;
                imageClean();
                bitmap1 = bitmap2;
            } else
            if(softReferenceBitmap == null)
                bitmap1 = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), 0x7f020077);
            else
                bitmap1 = (Bitmap)softReferenceBitmap.get();
            return bitmap1;
        }

        public int getCode()
        {
            return code;
        }

        public Drawable getDrawable()
        {
            Object obj;
            if(drawable != null)
            {
                Drawable drawable1 = drawable;
                imageClean();
                obj = drawable1;
            } else
            if(softReferenceDrawable == null)
                obj = new MySimpleAdapter.ExceptionDrawable(MyApplication.getInstance(), MyApplication.getInstance().getString(0x7f09007d));
            else
                obj = (Drawable)softReferenceDrawable.get();
            return ((Drawable) (obj));
        }

        public String getHeaderField(String s)
        {
            String s1;
            if(headerFields == null)
            {
                s1 = null;
            } else
            {
                List list = (List)headerFields.get(s);
                if(list == null || list.size() < 1)
                    s1 = null;
                else
                    s1 = (String)list.get(0);
            }
            return s1;
        }

        public Map getHeaderFields()
        {
            return headerFields;
        }

        public byte[] getInputData()
        {
            return inputData;
        }

        public InputStream getInputStream()
        {
            return inputStream;
        }

        public JSONObjectProxy getJSONObject()
        {
            return jsonObject;
        }

        public long getLength()
        {
            return length;
        }

        public File getSaveFile()
        {
            return saveFile;
        }

        public String getString()
        {
            return string;
        }

        public Drawable getThumbDrawable(float f, float f1)
        {
            Bitmap bitmap1 = getBitmap();
            int i = bitmap1.getWidth();
            int j = bitmap1.getHeight();
            float f2;
            Drawable drawable1;
            if(i > j)
                f2 = f / (float)i;
            else
                f2 = f1 / (float)j;
            if(f2 < 1.0F)
            {
                Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1, Math.round(f2 * (float)i), Math.round(f2 * (float)j), false);
                setBitmap(bitmap2);
                bitmap1.recycle();
                setDrawable(new BitmapDrawable(bitmap2));
                drawable1 = getDrawable();
            } else
            {
                drawable1 = getDrawable();
            }
            return drawable1;
        }

        public String getType()
        {
            return type;
        }

        public void setBitmap(Bitmap bitmap1)
        {
            if(bitmap1 == null)
            {
                throw new RuntimeException("bitmap is null");
            } else
            {
                bitmap = bitmap1;
                return;
            }
        }

        public void setCode(int i)
        {
            code = i;
        }

        public void setDrawable(Drawable drawable1)
        {
            drawable = drawable1;
        }

        public void setHeaderFields(Map map)
        {
            headerFields = map;
        }

        public void setInputData(byte abyte0[])
        {
            inputData = abyte0;
        }

        public void setInputStream(InputStream inputstream)
        {
            inputStream = inputstream;
        }

        public void setJsonObject(JSONObjectProxy jsonobjectproxy)
        {
            jsonObject = jsonobjectproxy;
        }

        public void setLength(long l)
        {
            length = l;
        }

        public void setSaveFile(File file)
        {
            saveFile = file;
        }

        public void setString(String s)
        {
            string = s;
        }

        public void setType(String s)
        {
            type = s;
        }

        private Bitmap bitmap;
        private int code;
        private Drawable drawable;
        private Map headerFields;
        private HttpURLConnection httpURLConnection;
        private byte inputData[];
        private InputStream inputStream;
        private JSONObjectProxy jsonObject;
        private long length;
        private File saveFile;
        private SoftReference softReferenceBitmap;
        private SoftReference softReferenceDrawable;
        private SoftReference softReferenceInputData;
        private String string;
        final HttpGroup this$0;
        private String type;

        public HttpResponse()
        {
            this$0 = HttpGroup.this;
            super();
        }

        public HttpResponse(Drawable drawable1)
        {
            this$0 = HttpGroup.this;
            super();
            drawable = drawable1;
        }

        public HttpResponse(HttpURLConnection httpurlconnection)
        {
            this$0 = HttpGroup.this;
            super();
            httpURLConnection = httpurlconnection;
        }
    }

    public static class HttpSetting
        implements HttpSettingParams
    {

        public int getCacheMode()
        {
            return cacheMode;
        }

        public int getConnectTimeout()
        {
            return connectTimeout;
        }

        public int getEffect()
        {
            return effect;
        }

        public int getEffectState()
        {
            return effectState;
        }

        public String getFinalUrl()
        {
            return finalUrl;
        }

        public String getFunctionId()
        {
            return functionId;
        }

        public int getId()
        {
            return id;
        }

        public JSONObject getJsonParams()
        {
            if(jsonParams == null)
                jsonParams = new JSONObject();
            return jsonParams;
        }

        public long getLocalFileCacheTime()
        {
            return localFileCacheTime;
        }

        public Map getMapParams()
        {
            return mapParams;
        }

        public String getMd5()
        {
            if(md5 != null) goto _L2; else goto _L1
_L1:
            String s1 = getUrl();
            if(s1 != null) goto _L4; else goto _L3
_L3:
            String s = null;
_L6:
            return s;
_L4:
            int i = 0;
            int j = 0;
            do
            {
                if(j >= 3)
                {
                    if(i != -1)
                        break;
                    s = null;
                    continue; /* Loop/switch isn't completed */
                }
                i = s1.indexOf("/", i + 1);
                j++;
            } while(true);
            String s2 = getUrl().substring(i);
            if(isPost())
                md5 = Md5Encrypt.md5((new StringBuilder(String.valueOf(s2))).append(getJsonParams()).toString());
            else
                md5 = Md5Encrypt.md5(s2);
            if(Log.D)
                Log.d("HttpGroup", (new StringBuilder("urlPath -->> ")).append(s2).append(" md5 -->> ").append(md5).toString());
_L2:
            s = md5;
            if(true) goto _L6; else goto _L5
_L5:
        }

        public OnEndListener getOnEndListener()
        {
            return onEndListener;
        }

        public OnErrorListener getOnErrorListener()
        {
            return onErrorListener;
        }

        public OnProgressListener getOnProgressListener()
        {
            return onProgressListener;
        }

        public OnReadyListener getOnReadyListener()
        {
            return onReadyListener;
        }

        public OnStartListener getOnStartListener()
        {
            return onStartListener;
        }

        public int getPriority()
        {
            return priority;
        }

        public int getReadTimeout()
        {
            return readTimeout;
        }

        public FileGuider getSavePath()
        {
            return savePath;
        }

        public int getType()
        {
            return type;
        }

        public String getUrl()
        {
            return url;
        }

        public boolean isLocalFileCache()
        {
            return localFileCache;
        }

        public boolean isLocalMemoryCache()
        {
            return localMemoryCache;
        }

        public boolean isNeedGlobalInitialization()
        {
            return needGlobalInitialization;
        }

        public boolean isNotifyUser()
        {
            return notifyUser;
        }

        public boolean isNotifyUserWithExit()
        {
            return notifyUserWithExit;
        }

        public boolean isPost()
        {
            return post;
        }

        public void onEnd(HttpResponse httpresponse)
        {
            if(onEndListener != null)
                onEndListener.onEnd(httpresponse);
        }

        public void onError(HttpError httperror)
        {
            if(onErrorListener != null)
                onErrorListener.onError(httperror);
        }

        public void onProgress(int i, int j)
        {
            if(onProgressListener != null)
                onProgressListener.onProgress(i, j);
        }

        public void onStart()
        {
            if(onStartListener != null)
                onStartListener.onStart();
        }

        public void putJsonParam(String s, Object obj)
        {
            if(jsonParams == null)
                jsonParams = new JSONObject();
            jsonParams.put(s, obj);
_L1:
            return;
            JSONException jsonexception;
            jsonexception;
            if(Log.D)
                Log.d("HttpGroup", "JSONException -->> ", jsonexception);
              goto _L1
        }

        public void putMapParams(String s, String s1)
        {
            if(mapParams == null)
                mapParams = new HashMap();
            String s2;
            try
            {
                s2 = URLEncoder.encode(s1, HttpGroup.charset);
            }
            catch(UnsupportedEncodingException unsupportedencodingexception)
            {
                throw new RuntimeException(unsupportedencodingexception);
            }
            mapParams.put(s, s2);
        }

        public void setCacheMode(int i)
        {
            cacheMode = i;
        }

        public void setConnectTimeout(int i)
        {
            connectTimeout = i;
        }

        public void setEffect(int i)
        {
            effect = i;
        }

        public void setEffectState(int i)
        {
            effectState = i;
        }

        public void setFinalUrl(String s)
        {
            finalUrl = s;
        }

        public void setFunctionId(String s)
        {
            functionId = s;
        }

        public void setId(int i)
        {
            id = i;
        }

        public void setJsonParams(JSONObject jsonobject)
        {
            if(jsonobject != null)
                try
                {
                    jsonParams = new JSONObject(jsonobject.toString());
                }
                catch(JSONException jsonexception)
                {
                    jsonexception.printStackTrace();
                }
        }

        public void setListener(HttpTaskListener httptasklistener)
        {
            if(httptasklistener instanceof CustomOnAllListener)
                setEffect(0);
            if(httptasklistener instanceof DefaultEffectHttpListener)
                setEffectState(1);
            if(httptasklistener instanceof OnErrorListener)
                onErrorListener = (OnErrorListener)httptasklistener;
            if(httptasklistener instanceof OnStartListener)
                onStartListener = (OnStartListener)httptasklistener;
            if(httptasklistener instanceof OnProgressListener)
                onProgressListener = (OnProgressListener)httptasklistener;
            if(httptasklistener instanceof OnEndListener)
                onEndListener = (OnEndListener)httptasklistener;
            if(httptasklistener instanceof OnReadyListener)
                onReadyListener = (OnReadyListener)httptasklistener;
        }

        public void setLocalFileCache(boolean flag)
        {
            localFileCache = flag;
        }

        public void setLocalFileCacheTime(long l)
        {
            localFileCacheTime = l;
        }

        public void setLocalMemoryCache(boolean flag)
        {
            localMemoryCache = flag;
        }

        public void setMapParams(Map map)
        {
            if(map != null)
            {
                Iterator iterator = map.keySet().iterator();
                while(iterator.hasNext()) 
                {
                    String s = (String)iterator.next();
                    putMapParams(s, (String)map.get(s));
                }
            }
        }

        public void setMd5(String s)
        {
            md5 = s;
        }

        public void setNeedGlobalInitialization(boolean flag)
        {
            needGlobalInitialization = flag;
        }

        public void setNotifyUser(boolean flag)
        {
            notifyUser = flag;
        }

        public void setNotifyUserWithExit(boolean flag)
        {
            notifyUserWithExit = flag;
        }

        public void setPost(boolean flag)
        {
            post = flag;
        }

        public void setPriority(int i)
        {
            priority = i;
        }

        public void setReadTimeout(int i)
        {
            readTimeout = i;
        }

        public void setSavePath(FileGuider fileguider)
        {
            savePath = fileguider;
        }

        public void setType(int i)
        {
            type = i;
        }

        public void setUrl(String s)
        {
            url = s;
        }

        public static final int CACHE_MODE_AUTO = 0;
        public static final int CACHE_MODE_ONLY_CACHE = 1;
        public static final int CACHE_MODE_ONLY_NET = 2;
        public static final int EFFECT_DEFAULT = 1;
        public static final int EFFECT_NO = 0;
        public static final int EFFECT_STATE_NO = 0;
        public static final int EFFECT_STATE_YES = 1;
        private int cacheMode;
        private int connectTimeout;
        private int effect;
        private int effectState;
        private String finalUrl;
        private String functionId;
        private int id;
        private JSONObject jsonParams;
        private boolean localFileCache;
        private long localFileCacheTime;
        private boolean localMemoryCache;
        private Map mapParams;
        private String md5;
        private boolean needGlobalInitialization;
        private boolean notifyUser;
        private boolean notifyUserWithExit;
        private OnEndListener onEndListener;
        private OnErrorListener onErrorListener;
        private OnProgressListener onProgressListener;
        private OnReadyListener onReadyListener;
        private OnStartListener onStartListener;
        private boolean post;
        private int priority;
        private int readTimeout;
        private FileGuider savePath;
        private int type;
        private String url;

        public HttpSetting()
        {
            post = "post".equals(Configuration.getProperty("requestMethod", "post"));
            notifyUser = false;
            notifyUserWithExit = false;
            localMemoryCache = false;
            localFileCache = false;
            localFileCacheTime = -1L;
            needGlobalInitialization = true;
            effect = 1;
            effectState = 0;
            cacheMode = 0;
        }
    }

    public static interface HttpSettingParams
    {

        public abstract void putJsonParam(String s, Object obj);

        public abstract void putMapParams(String s, String s1);
    }

    public static interface HttpTaskListener
    {
    }

    public static interface OnAllListener
        extends OnStartListener, OnEndListener, OnErrorListener, OnProgressListener
    {
    }

    public static interface OnCommonListener
        extends OnEndListener, OnErrorListener, OnReadyListener
    {
    }

    public static interface OnEndListener
        extends HttpTaskListener
    {

        public abstract void onEnd(HttpResponse httpresponse);
    }

    public static interface OnErrorListener
        extends HttpTaskListener
    {

        public abstract void onError(HttpError httperror);
    }

    public static interface OnGroupEndListener
    {

        public abstract void onEnd();
    }

    public static interface OnGroupErrorListener
    {

        public abstract void onError();
    }

    public static interface OnGroupProgressListener
    {

        public abstract void onProgress(int i, int j);
    }

    public static interface OnGroupStartListener
    {

        public abstract void onStart();
    }

    public static interface OnGroupStepListener
    {

        public abstract void onStep(int i, int j);
    }

    public static interface OnProgressListener
        extends HttpTaskListener
    {

        public abstract void onProgress(int i, int j);
    }

    public static interface OnReadyListener
        extends HttpTaskListener
    {

        public abstract void onReady(HttpSettingParams httpsettingparams);
    }

    public static interface OnStartListener
        extends HttpTaskListener
    {

        public abstract void onStart();
    }

    public static interface StopController
    {

        public abstract boolean isStop();

        public abstract void stop();
    }


    public HttpGroup(HttpGroupSetting httpgroupsetting)
    {
        useCaches = false;
        httpList = new ArrayList();
        reportUserInfoFlag = true;
        completesCount = 0;
        maxProgress = 0;
        progress = 0;
        maxStep = 0;
        step = 0;
        httpGroupSetting = httpgroupsetting;
        priority = httpgroupsetting.getPriority();
        type = httpgroupsetting.getType();
    }

    public static void cleanCookies()
    {
        cookies = null;
    }

    private void onProgress(int i, int j)
    {
        if(onGroupProgressListener != null)
            onGroupProgressListener.onProgress(i, j);
    }

    private void onStep(int i, int j)
    {
        if(onGroupStepListener != null)
            onGroupStepListener.onStep(i, j);
    }

    public static void queryMd5Key(CompleteListener completelistener)
    {
        HttpGroupSetting httpgroupsetting = new HttpGroupSetting();
        httpgroupsetting.setPriority(1000);
        httpgroupsetting.setType(1000);
        queryMd5Key(((HttpGroup) (new HttpGroupaAsynPool(httpgroupsetting))), completelistener);
    }

    public static void queryMd5Key(HttpGroup httpgroup, final CompleteListener listener)
    {
        OnAllListener onalllistener = new OnAllListener() {

            public void onEnd(HttpResponse httpresponse)
            {
                byte abyte0[];
                int i;
                int j;
                String s = httpresponse.getJSONObject().getStringOrNull("key");
                if(s == null)
                    break MISSING_BLOCK_LABEL_143;
                abyte0 = Base64.decode(s);
                i = 0;
                j = abyte0.length;
                do
                {
                    Exception exception;
                    if(i >= j)
                    {
                        String s1 = new String(abyte0);
                        if(Log.D)
                            Log.d("HttpGroup", (new StringBuilder("md5Key -->> ")).append(s1).toString());
                        HttpGroup.setMd5Key(s1);
                        if(listener != null)
                            listener.onComplete(null);
                        break MISSING_BLOCK_LABEL_143;
                    }
                    try
                    {
                        abyte0[i] = (byte)(-1 ^ abyte0[i]);
                    }
                    // Misplaced declaration of an exception variable
                    catch(Exception exception)
                    {
                        if(listener != null)
                            listener.onComplete(null);
                        break MISSING_BLOCK_LABEL_143;
                    }
                    i++;
                } while(true);
            }

            public void onError(HttpError httperror)
            {
                if(listener != null)
                    listener.onComplete(null);
            }

            public void onProgress(int i, int j)
            {
            }

            public void onStart()
            {
            }

            private final CompleteListener val$listener;

            
            {
                listener = completelistener;
                super();
            }
        }
;
        HttpSetting httpsetting = new HttpSetting();
        httpsetting.setFunctionId("key");
        httpsetting.setJsonParams(new JSONObject());
        httpsetting.setListener(onalllistener);
        httpsetting.setPost(true);
        httpgroup.add(httpsetting);
    }

    public static void setCookies(String s)
    {
        cookies = s;
    }

    public static void setMd5Key(String s)
    {
        mMd5Key = s;
    }

    public static void setModules(JSONObjectProxy jsonobjectproxy)
    {
        mModules = jsonobjectproxy;
    }

    private void tryEffect(HttpSetting httpsetting)
    {
        MyActivity myactivity = httpGroupSetting.getMyActivity();
        if(1 == httpsetting.getEffect() && httpsetting.getEffectState() == 0 && myactivity != null)
            httpsetting.setListener(new DefaultEffectHttpListener(httpsetting, myactivity));
    }

    public HttpRequest add(final HttpSetting httpSetting)
    {
        httpIdCounter = 1 + httpIdCounter;
        httpSetting.setId(httpIdCounter);
        tryEffect(httpSetting);
        if(Log.I)
            Log.i("HttpGroup", (new StringBuilder("id:")).append(httpSetting.getId()).append("- onStart -->> ").toString());
        httpSetting.onStart();
        final HttpRequest httpRequest = new HttpRequest(httpSetting);
        final OnReadyListener onReadyListener = httpSetting.getOnReadyListener();
        if(onReadyListener != null)
            (new Thread() {

                public void run()
                {
                    onReadyListener.onReady(httpSetting);
                    add2(httpRequest);
                }

                final HttpGroup this$0;
                private final HttpRequest val$httpRequest;
                private final HttpSetting val$httpSetting;
                private final OnReadyListener val$onReadyListener;

            
            {
                this$0 = HttpGroup.this;
                onReadyListener = onreadylistener;
                httpSetting = httpsetting;
                httpRequest = httprequest;
                super();
            }
            }
).start();
        else
            add2(httpRequest);
        return httpRequest;
    }

    public HttpRequest add(String s, Map map, OnAllListener onalllistener)
    {
        HttpSetting httpsetting = new HttpSetting();
        httpsetting.setUrl(s);
        httpsetting.setMapParams(map);
        httpsetting.setListener(onalllistener);
        return add(httpsetting);
    }

    public HttpRequest add(String s, JSONObject jsonobject, OnAllListener onalllistener)
    {
        HttpSetting httpsetting = new HttpSetting();
        httpsetting.setFunctionId(s);
        httpsetting.setJsonParams(jsonobject);
        httpsetting.setListener(onalllistener);
        return add(httpsetting);
    }

    public void add2(HttpRequest httprequest)
    {
        HttpSetting httpsetting;
        httpsetting = httprequest.getHttpSetting();
        if(Log.I && httpsetting.getFunctionId() != null)
            Log.i("HttpGroup", (new StringBuilder("id:")).append(httpsetting.getId()).append("- functionId -->> ").append(httpsetting.getFunctionId()).toString());
        if(Log.I && httpsetting.getUrl() != null)
            Log.i("HttpGroup", (new StringBuilder("id:")).append(httpsetting.getId()).append("- url -->> ").append(httpsetting.getUrl()).toString());
        if(httpsetting.getType() == 0)
            httpsetting.setType(type);
        if(httpsetting.getPriority() == 0)
            httpsetting.setPriority(priority);
        if(httpsetting.getPriority() != 0) goto _L2; else goto _L1
_L1:
        httpsetting.getType();
        JVM INSTR lookupswitch 3: default 184
    //                   500: 210
    //                   1000: 190
    //                   5000: 200;
           goto _L2 _L3 _L4 _L5
_L2:
        execute(httprequest);
        return;
_L4:
        httpsetting.setPriority(1000);
        continue; /* Loop/switch isn't completed */
_L5:
        httpsetting.setPriority(5000);
        continue; /* Loop/switch isn't completed */
_L3:
        httpsetting.setPriority(500);
        if(true) goto _L2; else goto _L6
_L6:
    }

    protected void addCompletesCount()
    {
        completesCount = 1 + completesCount;
        if(completesCount == httpList.size())
            onEnd();
    }

    protected void addMaxProgress(int i)
    {
        maxProgress = i + maxProgress;
        onProgress(maxProgress, progress);
    }

    protected void addMaxStep(int i)
    {
        maxStep = i + maxStep;
        onStep(maxStep, step);
    }

    protected void addProgress(int i)
    {
        progress = i + progress;
        onProgress(maxProgress, progress);
    }

    protected void addStep(int i)
    {
        step = i + step;
        onStep(maxStep, step);
    }

    protected abstract void execute(HttpRequest httprequest);

    public void onDestroy()
    {
    }

    protected void onEnd()
    {
        if(onGroupEndListener != null)
            onGroupEndListener.onEnd();
    }

    protected void onError()
    {
        if(onGroupErrorListener != null)
            onGroupErrorListener.onError();
    }

    protected void onStart()
    {
        if(onGroupStartListener != null)
            onGroupStartListener.onStart();
    }

    public void setOnGroupEndListener(OnGroupEndListener ongroupendlistener)
    {
        onGroupEndListener = ongroupendlistener;
    }

    public void setOnGroupErrorListener(OnGroupErrorListener ongrouperrorlistener)
    {
        onGroupErrorListener = ongrouperrorlistener;
    }

    public void setOnGroupProgressListener(OnGroupProgressListener ongroupprogresslistener)
    {
        onGroupProgressListener = ongroupprogresslistener;
    }

    public void setOnGroupStartListener(OnGroupStartListener ongroupstartlistener)
    {
        onGroupStartListener = ongroupstartlistener;
    }

    public void setOnGroupStepListener(OnGroupStepListener ongroupsteplistener)
    {
        onGroupStepListener = ongroupsteplistener;
    }

    private static final HashMap alertDialogStateMap = new HashMap();
    private static final int attempts = Integer.parseInt(Configuration.getProperty("attempts"));
    private static final int attemptsTime = Integer.parseInt(Configuration.getProperty("attemptsTime"));
    private static String charset = "UTF-8";
    private static final int connectTimeout = Integer.parseInt(Configuration.getProperty("connectTimeout"));
    private static String cookies;
    private static final String host = Configuration.getProperty("host");
    private static int httpIdCounter = 0;
    private static String mMd5Key;
    private static JSONObjectProxy mModules;
    private static final int readTimeout = Integer.parseInt(Configuration.getProperty("readTimeout"));
    private int completesCount;
    protected HttpGroupSetting httpGroupSetting;
    protected ArrayList httpList;
    private int maxProgress;
    private int maxStep;
    private OnGroupEndListener onGroupEndListener;
    private OnGroupErrorListener onGroupErrorListener;
    private OnGroupProgressListener onGroupProgressListener;
    private OnGroupStartListener onGroupStartListener;
    private OnGroupStepListener onGroupStepListener;
    protected int priority;
    private int progress;
    private boolean reportUserInfoFlag;
    private int step;
    protected int type;
    private boolean useCaches;














}
