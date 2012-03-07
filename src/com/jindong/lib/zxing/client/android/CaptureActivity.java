// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import android.app.Activity;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.*;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.view.*;
import android.widget.*;
import com.google.zxing.*;
import com.google.zxing.client.result.ParsedResultType;
import com.jindong.app.mall.utils.Log;
import com.jindong.lib.zxing.client.android.camera.CameraManager;
import com.jindong.lib.zxing.client.android.history.HistoryManager;
import com.jindong.lib.zxing.client.android.result.ResultButtonListener;
import com.jindong.lib.zxing.client.android.result.ResultHandler;
import com.jindong.lib.zxing.client.android.result.ResultHandlerFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

// Referenced classes of package com.jindong.lib.zxing.client.android:
//            FinishListener, ViewfinderView, CaptureActivityHandler, InactivityTimer, 
//            DecodeFormatManager

public final class CaptureActivity extends Activity
    implements android.view.SurfaceHolder.Callback
{
    private static final class Source extends Enum
    {

        public static Source valueOf(String s)
        {
            return (Source)Enum.valueOf(com/jindong/lib/zxing/client/android/CaptureActivity$Source, s);
        }

        public static Source[] values()
        {
            Source asource[] = ENUM$VALUES;
            int i = asource.length;
            Source asource1[] = new Source[i];
            System.arraycopy(asource, 0, asource1, 0, i);
            return asource1;
        }

        private static final Source ENUM$VALUES[];
        public static final Source NATIVE_APP_INTENT;
        public static final Source NONE;
        public static final Source PRODUCT_SEARCH_LINK;
        public static final Source ZXING_LINK;

        static 
        {
            NATIVE_APP_INTENT = new Source("NATIVE_APP_INTENT", 0);
            PRODUCT_SEARCH_LINK = new Source("PRODUCT_SEARCH_LINK", 1);
            ZXING_LINK = new Source("ZXING_LINK", 2);
            NONE = new Source("NONE", 3);
            Source asource[] = new Source[4];
            asource[0] = NATIVE_APP_INTENT;
            asource[1] = PRODUCT_SEARCH_LINK;
            asource[2] = ZXING_LINK;
            asource[3] = NONE;
            ENUM$VALUES = asource;
        }

        private Source(String s, int i)
        {
            super(s, i);
        }
    }


    static int[] $SWITCH_TABLE$com$jindong$lib$zxing$client$android$CaptureActivity$Source()
    {
        int ai[] = $SWITCH_TABLE$com$jindong$lib$zxing$client$android$CaptureActivity$Source;
        if(ai == null)
        {
            ai = new int[Source.values().length];
            try
            {
                ai[Source.NATIVE_APP_INTENT.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            try
            {
                ai[Source.NONE.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Source.PRODUCT_SEARCH_LINK.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[Source.ZXING_LINK.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            $SWITCH_TABLE$com$jindong$lib$zxing$client$android$CaptureActivity$Source = ai;
        }
        return ai;
    }

    public CaptureActivity()
    {
    }

    private void displayFrameworkBugMessageAndExit()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(getString(0x7f09003c));
        builder.setMessage(getString(0x7f09020c));
        builder.setPositiveButton(0x7f0901ee, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        canvas.drawLine(resultpoint.getX(), resultpoint.getY(), resultpoint1.getX(), resultpoint1.getY(), paint);
    }

    private void drawResultPoints(Bitmap bitmap, Result result)
    {
        ResultPoint aresultpoint[] = result.getResultPoints();
        if(aresultpoint == null || aresultpoint.length <= 0) goto _L2; else goto _L1
_L1:
        Canvas canvas;
        Paint paint;
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(getResources().getColor(0x7f07000e));
        paint.setStrokeWidth(3F);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        canvas.drawRect(new Rect(2, 2, bitmap.getWidth() - 2, bitmap.getHeight() - 2), paint);
        paint.setColor(getResources().getColor(0x7f070010));
        if(aresultpoint.length != 2) goto _L4; else goto _L3
_L3:
        paint.setStrokeWidth(4F);
        drawLine(canvas, paint, aresultpoint[0], aresultpoint[1]);
_L2:
        return;
_L4:
        if((aresultpoint.length != 4 || !result.getBarcodeFormat().equals(BarcodeFormat.UPC_A)) && !result.getBarcodeFormat().equals(BarcodeFormat.EAN_13))
            break; /* Loop/switch isn't completed */
        drawLine(canvas, paint, aresultpoint[0], aresultpoint[1]);
        drawLine(canvas, paint, aresultpoint[2], aresultpoint[3]);
        if(true) goto _L2; else goto _L5
_L5:
        paint.setStrokeWidth(10F);
        int i = aresultpoint.length;
        int j = 0;
        while(j < i) 
        {
            ResultPoint resultpoint = aresultpoint[j];
            canvas.drawPoint(resultpoint.getX(), resultpoint.getY(), paint);
            j++;
        }
        if(true) goto _L2; else goto _L6
_L6:
    }

    private void handleDecodeExternally(Result result, Bitmap bitmap)
    {
        ResultHandler resulthandler;
        viewfinderView.drawResultBitmap(bitmap);
        resulthandler = ResultHandlerFactory.makeResultHandler(this, result);
        statusView.setText(getString(resulthandler.getDisplayTitle()));
        if(copyToClipboard)
            ((ClipboardManager)getSystemService("clipboard")).setText(resulthandler.getDisplayContents());
        if(source != Source.NATIVE_APP_INTENT) goto _L2; else goto _L1
_L1:
        Intent intent = new Intent(getIntent().getAction());
        intent.addFlags(0x80000);
        intent.putExtra("SCAN_RESULT", result.toString());
        intent.putExtra("SCAN_RESULT_FORMAT", result.getBarcodeFormat().toString());
        Message message = Message.obtain(handler, 0x7f0c0009);
        message.obj = intent;
        handler.sendMessageDelayed(message, 1500L);
_L4:
        return;
_L2:
        if(source == Source.PRODUCT_SEARCH_LINK)
        {
            Message message2 = Message.obtain(handler, 0x7f0c0006);
            int i = sourceUrl.lastIndexOf("/scan");
            message2.obj = (new StringBuilder(String.valueOf(sourceUrl.substring(0, i)))).append("?q=").append(resulthandler.getDisplayContents().toString()).append("&source=zxing").toString();
            handler.sendMessageDelayed(message2, 1500L);
        } else
        if(source == Source.ZXING_LINK)
        {
            Message message1 = Message.obtain(handler, 0x7f0c0006);
            message1.obj = returnUrlTemplate.replace("{CODE}", resulthandler.getDisplayContents().toString());
            handler.sendMessageDelayed(message1, 1500L);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void handleDecodeInternally(Result result, Bitmap bitmap)
    {
        ResultHandler resulthandler;
        StringBuilder stringbuilder;
        Iterator iterator;
        int i;
        ViewGroup viewgroup;
        int j;
        statusView.setVisibility(8);
        viewfinderView.setVisibility(8);
        resultView.setVisibility(0);
        ImageView imageview = (ImageView)findViewById(0x7f0c0045);
        String s;
        TextView textview;
        View view;
        java.util.Hashtable hashtable;
        TextView textview1;
        CharSequence charsequence;
        if(bitmap == null)
            imageview.setImageBitmap(BitmapFactory.decodeResource(getResources(), 0x7f020083));
        else
            imageview.setImageBitmap(bitmap);
        ((TextView)findViewById(0x7f0c0047)).setText(result.getBarcodeFormat().toString());
        resulthandler = ResultHandlerFactory.makeResultHandler(this, result);
        ((TextView)findViewById(0x7f0c0049)).setText(resulthandler.getType().toString());
        s = DateFormat.getDateTimeInstance(3, 3).format(new Date(result.getTimestamp()));
        ((TextView)findViewById(0x7f0c004b)).setText(s);
        textview = (TextView)findViewById(0x7f0c004d);
        view = findViewById(0x7f0c004c);
        textview.setVisibility(8);
        view.setVisibility(8);
        hashtable = result.getResultMetadata();
        if(hashtable == null) goto _L2; else goto _L1
_L1:
        stringbuilder = new StringBuilder(20);
        iterator = hashtable.entrySet().iterator();
_L5:
        if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
        if(stringbuilder.length() > 0)
        {
            stringbuilder.setLength(stringbuilder.length() - 1);
            textview.setText(stringbuilder);
            textview.setVisibility(0);
            view.setVisibility(0);
        }
_L2:
        textview1 = (TextView)findViewById(0x7f0c004e);
        charsequence = resulthandler.getDisplayContents();
        textview1.setText(charsequence);
        textview1.setTextSize(2, Math.max(22, 32 - charsequence.length() / 4));
        i = resulthandler.getButtonCount();
        viewgroup = (ViewGroup)findViewById(0x7f0c004f);
        viewgroup.requestFocus();
        j = 0;
_L6:
        if(j >= 4)
        {
            if(copyToClipboard)
                ((ClipboardManager)getSystemService("clipboard")).setText(charsequence);
            return;
        }
        break MISSING_BLOCK_LABEL_425;
_L4:
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        if(DISPLAYABLE_METADATA_TYPES.contains(entry.getKey()))
            stringbuilder.append(entry.getValue()).append('\n');
          goto _L5
        TextView textview2 = (TextView)viewgroup.getChildAt(j);
        if(j < i)
        {
            textview2.setVisibility(0);
            textview2.setText(resulthandler.getButtonText(j));
            ResultButtonListener resultbuttonlistener = new ResultButtonListener(resulthandler, j);
            textview2.setOnClickListener(resultbuttonlistener);
        } else
        {
            textview2.setVisibility(8);
        }
        j++;
          goto _L6
    }

    private void initBeepSound()
    {
        AssetFileDescriptor assetfiledescriptor;
        if(!playBeep || mediaPlayer != null)
            break MISSING_BLOCK_LABEL_101;
        setVolumeControlStream(3);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(beepListener);
        assetfiledescriptor = getResources().openRawResourceFd(0x7f060000);
        mediaPlayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
        assetfiledescriptor.close();
        mediaPlayer.setVolume(0.1F, 0.1F);
        mediaPlayer.prepare();
_L1:
        return;
        IOException ioexception;
        ioexception;
        mediaPlayer = null;
          goto _L1
    }

    private void initCamera(SurfaceHolder surfaceholder)
    {
        CameraManager.get().openDriver(surfaceholder);
        if(handler == null)
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(Log.W)
            Log.w(TAG, ioexception);
        displayFrameworkBugMessageAndExit();
        continue; /* Loop/switch isn't completed */
        RuntimeException runtimeexception;
        runtimeexception;
        if(Log.W)
            Log.w(TAG, "Unexpected error initializating camera", runtimeexception);
        displayFrameworkBugMessageAndExit();
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void playBeepSoundAndVibrate()
    {
        if(playBeep && mediaPlayer != null)
            mediaPlayer.start();
        if(vibrate)
            ((Vibrator)getSystemService("vibrator")).vibrate(200L);
    }

    private void resetStatusView()
    {
        resultView.setVisibility(8);
        statusView.setText(0x7f090211);
        statusView.setVisibility(0);
        viewfinderView.setVisibility(0);
        lastResult = null;
    }

    public void drawViewfinder()
    {
        viewfinderView.drawViewfinder();
    }

    public Handler getHandler()
    {
        return handler;
    }

    ViewfinderView getViewfinderView()
    {
        return viewfinderView;
    }

    public void handleDecode(Result result, Bitmap bitmap)
    {
        inactivityTimer.onActivity();
        lastResult = result;
        historyManager.addHistoryItem(result);
        if(bitmap != null) goto _L2; else goto _L1
_L1:
        handleDecodeInternally(result, null);
_L4:
        return;
_L2:
        playBeepSoundAndVibrate();
        drawResultPoints(bitmap, result);
        switch($SWITCH_TABLE$com$jindong$lib$zxing$client$android$CaptureActivity$Source()[source.ordinal()])
        {
        case 1: // '\001'
        case 2: // '\002'
            handleDecodeExternally(result, bitmap);
            break;

        case 3: // '\003'
            if(returnUrlTemplate == null)
                handleDecodeInternally(result, bitmap);
            else
                handleDecodeExternally(result, bitmap);
            break;

        case 4: // '\004'
            if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("preferences_bulk_mode", false))
            {
                Toast.makeText(this, 0x7f09020b, 0).show();
                if(handler != null)
                    handler.sendEmptyMessageDelayed(0x7f0c0008, 1000L);
                resetStatusView();
            } else
            {
                handleDecodeInternally(result, bitmap);
            }
            break;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(0x7f030011);
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView)findViewById(0x7f0c0043);
        resultView = findViewById(0x7f0c0044);
        statusView = (TextView)findViewById(0x7f0c0051);
        handler = null;
        lastResult = null;
        hasSurface = false;
        historyManager = new HistoryManager(this);
        historyManager.trimHistory();
        inactivityTimer = new InactivityTimer(this);
    }

    protected void onDestroy()
    {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i != 4) goto _L2; else goto _L1
_L1:
        if(source != Source.NATIVE_APP_INTENT) goto _L4; else goto _L3
_L3:
        boolean flag;
        setResult(0);
        finish();
        flag = true;
_L7:
        return flag;
_L4:
        if((source == Source.NONE || source == Source.ZXING_LINK) && lastResult != null)
        {
            resetStatusView();
            if(handler != null)
                handler.sendEmptyMessage(0x7f0c0008);
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
          goto _L5
_L2:
        if(i == 80 || i == 27)
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L5:
        flag = super.onKeyDown(i, keyevent);
        if(true) goto _L7; else goto _L6
_L6:
    }

    protected void onPause()
    {
        super.onPause();
        if(handler != null)
        {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    protected void onResume()
    {
        super.onResume();
        resetStatusView();
        SurfaceHolder surfaceholder = ((SurfaceView)findViewById(0x7f0c0042)).getHolder();
        Intent intent;
        String s;
        String s1;
        if(hasSurface)
        {
            initCamera(surfaceholder);
        } else
        {
            surfaceholder.addCallback(this);
            surfaceholder.setType(3);
        }
        intent = getIntent();
        if(intent == null)
            s = null;
        else
            s = intent.getAction();
        if(intent == null)
            s1 = null;
        else
            s1 = intent.getDataString();
        if(intent != null && s != null)
        {
            SharedPreferences sharedpreferences;
            if(s.equals("com.jindong.lib.zxing.client.android.SCAN"))
            {
                source = Source.NATIVE_APP_INTENT;
                decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
            } else
            if(s1 != null && s1.contains("http://www.google") && s1.contains("/m/products/scan"))
            {
                source = Source.PRODUCT_SEARCH_LINK;
                sourceUrl = s1;
                decodeFormats = DecodeFormatManager.PRODUCT_FORMATS;
            } else
            if(s1 != null && s1.startsWith("http://zxing.appspot.com/scan"))
            {
                source = Source.ZXING_LINK;
                sourceUrl = s1;
                Uri uri = Uri.parse(sourceUrl);
                returnUrlTemplate = uri.getQueryParameter("ret");
                decodeFormats = DecodeFormatManager.parseDecodeFormats(uri);
            } else
            {
                source = Source.NONE;
                decodeFormats = null;
            }
            characterSet = intent.getStringExtra("CHARACTER_SET");
        } else
        {
            source = Source.NONE;
            decodeFormats = null;
            characterSet = null;
        }
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        playBeep = sharedpreferences.getBoolean("preferences_play_beep", true);
        if(playBeep && ((AudioManager)getSystemService("audio")).getRingerMode() != 2)
            playBeep = false;
        vibrate = sharedpreferences.getBoolean("preferences_vibrate", false);
        copyToClipboard = sharedpreferences.getBoolean("preferences_copy_to_clipboard", true);
        initBeepSound();
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        if(!hasSurface)
        {
            hasSurface = true;
            initCamera(surfaceholder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        hasSurface = false;
    }

    private static int $SWITCH_TABLE$com$jindong$lib$zxing$client$android$CaptureActivity$Source[];
    private static final int ABOUT_ID = 5;
    private static final float BEEP_VOLUME = 0.1F;
    private static final long BULK_MODE_SCAN_DELAY_MS = 1000L;
    private static final Set DISPLAYABLE_METADATA_TYPES;
    private static final int HELP_ID = 4;
    private static final int HISTORY_ID = 2;
    private static final long INTENT_RESULT_DURATION = 1500L;
    private static final String PACKAGE_NAME = "com.jindong.lib.zxing.client.android";
    private static final String PRODUCT_SEARCH_URL_PREFIX = "http://www.google";
    private static final String PRODUCT_SEARCH_URL_SUFFIX = "/m/products/scan";
    private static final String RETURN_CODE_PLACEHOLDER = "{CODE}";
    private static final String RETURN_URL_PARAM = "ret";
    private static final int SETTINGS_ID = 3;
    private static final int SHARE_ID = 1;
    private static final String TAG = com/jindong/lib/zxing/client/android/CaptureActivity.getSimpleName();
    private static final long VIBRATE_DURATION = 200L;
    private static final String ZXING_URL = "http://zxing.appspot.com/scan";
    private final android.content.DialogInterface.OnClickListener aboutListener = new android.content.DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialoginterface, int i)
        {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(getString(0x7f09024e)));
            intent.addFlags(0x80000);
            startActivity(intent);
        }

        final CaptureActivity this$0;

            
            {
                this$0 = CaptureActivity.this;
                super();
            }
    }
;
    private final android.media.MediaPlayer.OnCompletionListener beepListener = new android.media.MediaPlayer.OnCompletionListener() {

        public void onCompletion(MediaPlayer mediaplayer)
        {
            mediaplayer.seekTo(0);
        }

        final CaptureActivity this$0;

            
            {
                this$0 = CaptureActivity.this;
                super();
            }
    }
;
    private String characterSet;
    private boolean copyToClipboard;
    private Vector decodeFormats;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private HistoryManager historyManager;
    private InactivityTimer inactivityTimer;
    private Result lastResult;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private View resultView;
    private String returnUrlTemplate;
    private Source source;
    private String sourceUrl;
    private TextView statusView;
    private String versionName;
    private boolean vibrate;
    private ViewfinderView viewfinderView;

    static 
    {
        DISPLAYABLE_METADATA_TYPES = new HashSet(5);
        DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.ISSUE_NUMBER);
        DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.SUGGESTED_PRICE);
        DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.ERROR_CORRECTION_LEVEL);
        DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.POSSIBLE_COUNTRY);
    }
}
