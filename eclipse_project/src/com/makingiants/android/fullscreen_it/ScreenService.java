package com.makingiants.android.fullscreen_it;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class ScreenService extends Service {
    
    private WindowManager windowManager;
    private ImageView floatingImage;
    private boolean shouldHide;
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        final Context context = this.getApplicationContext();
        shouldHide = true;
        
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        
        floatingImage = new ImageView(this);
        floatingImage.setImageResource(R.drawable.ic_launcher);
        floatingImage.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("TTT", shouldHide ? "Sended yes" : "Sended no");
                    
                    Intent intent = new Intent(context, ServiceActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    
                    context.startActivity(intent);
                    
                    sendMessage();
                }
                return false;
            }
        });
        
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 0;
        
        windowManager.addView(floatingImage, params);
        
    }
    
    public void sendMessage() {
        Intent intent = new Intent(ServiceActivity.INTENT_ACTION_NAME);
        intent.putExtra(ServiceActivity.INTENT_EXTRA_SHOULD_HIDE, shouldHide);
        LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(intent);
        
        shouldHide = !shouldHide;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingImage != null)
            windowManager.removeView(floatingImage);
    }
    
}
