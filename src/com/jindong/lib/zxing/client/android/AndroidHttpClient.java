// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import java.io.IOException;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.*;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.*;
import org.apache.http.protocol.*;

public final class AndroidHttpClient
    implements HttpClient
{

    private AndroidHttpClient(ClientConnectionManager clientconnectionmanager, HttpParams httpparams)
    {
        _flddelegate = new DefaultHttpClient(clientconnectionmanager, httpparams) {

            protected HttpContext createHttpContext()
            {
                BasicHttpContext basichttpcontext = new BasicHttpContext();
                basichttpcontext.setAttribute("http.authscheme-registry", getAuthSchemes());
                basichttpcontext.setAttribute("http.cookiespec-registry", getCookieSpecs());
                basichttpcontext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
                return basichttpcontext;
            }

            protected BasicHttpProcessor createHttpProcessor()
            {
                BasicHttpProcessor basichttpprocessor = super.createHttpProcessor();
                basichttpprocessor.addRequestInterceptor(AndroidHttpClient.sThreadCheckInterceptor);
                return basichttpprocessor;
            }

            final AndroidHttpClient this$0;

            
            {
                this$0 = AndroidHttpClient.this;
                super(clientconnectionmanager, httpparams);
            }
        }
;
    }

    public static AndroidHttpClient newInstance(String s)
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(basichttpparams, false);
        HttpConnectionParams.setConnectionTimeout(basichttpparams, 20000);
        HttpConnectionParams.setSoTimeout(basichttpparams, 20000);
        HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
        HttpClientParams.setRedirecting(basichttpparams, false);
        HttpProtocolParams.setUserAgent(basichttpparams, s);
        SchemeRegistry schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        return new AndroidHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
    }

    public void close()
    {
        getConnectionManager().shutdown();
    }

    public Object execute(HttpHost httphost, HttpRequest httprequest, ResponseHandler responsehandler)
        throws IOException
    {
        return _flddelegate.execute(httphost, httprequest, responsehandler);
    }

    public Object execute(HttpHost httphost, HttpRequest httprequest, ResponseHandler responsehandler, HttpContext httpcontext)
        throws IOException
    {
        return _flddelegate.execute(httphost, httprequest, responsehandler, httpcontext);
    }

    public Object execute(HttpUriRequest httpurirequest, ResponseHandler responsehandler)
        throws IOException
    {
        return _flddelegate.execute(httpurirequest, responsehandler);
    }

    public Object execute(HttpUriRequest httpurirequest, ResponseHandler responsehandler, HttpContext httpcontext)
        throws IOException
    {
        return _flddelegate.execute(httpurirequest, responsehandler, httpcontext);
    }

    public HttpResponse execute(HttpHost httphost, HttpRequest httprequest)
        throws IOException
    {
        return _flddelegate.execute(httphost, httprequest);
    }

    public HttpResponse execute(HttpHost httphost, HttpRequest httprequest, HttpContext httpcontext)
        throws IOException
    {
        return _flddelegate.execute(httphost, httprequest, httpcontext);
    }

    public HttpResponse execute(HttpUriRequest httpurirequest)
        throws IOException
    {
        return _flddelegate.execute(httpurirequest);
    }

    public HttpResponse execute(HttpUriRequest httpurirequest, HttpContext httpcontext)
        throws IOException
    {
        return _flddelegate.execute(httpurirequest, httpcontext);
    }

    public ClientConnectionManager getConnectionManager()
    {
        return _flddelegate.getConnectionManager();
    }

    public HttpParams getParams()
    {
        return _flddelegate.getParams();
    }

    private static final ThreadLocal sThreadBlocked = new ThreadLocal();
    private static final HttpRequestInterceptor sThreadCheckInterceptor = new HttpRequestInterceptor() {

        public void process(HttpRequest httprequest, HttpContext httpcontext)
        {
            if(Boolean.TRUE.equals(AndroidHttpClient.sThreadBlocked.get()))
                throw new RuntimeException("This thread forbids HTTP requests");
            else
                return;
        }

    }
;
    private final HttpClient _flddelegate;



}
