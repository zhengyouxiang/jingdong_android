// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.constant;


public class NetworkConstant
{
    public static final class APN
    {

        public static final int CHECK_NETWORK = 9009;
        public static final int CONNECTED = 9008;
        public static final int CONNECTING = 9007;
        public static final int DISCONNECTED = 9006;
        public static final int FAIL = 9001;
        public static final int INIT = 9005;
        public static final int SUCCESS = 9000;
        public static final int TIMEOUT = 9002;

        public APN()
        {
        }
    }

    public static final class NetworkState
    {

        public static final int ALL = 0;
        public static final int CMLAN = 3;
        public static final int CMNET = 1;
        public static final int CMWAP = 2;

        public NetworkState()
        {
        }
    }

    public static final class TaskState
    {

        public static final int SUCCESS = 1000;

        public TaskState()
        {
        }
    }


    public NetworkConstant()
    {
    }
}
