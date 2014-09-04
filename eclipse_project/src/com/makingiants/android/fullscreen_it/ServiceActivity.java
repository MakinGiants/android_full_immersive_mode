package com.makingiants.android.fullscreen_it;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ServiceActivity extends ActionBarActivity {
    
    public static final String INTENT_ACTION_NAME = "FS";
    public static final String INTENT_EXTRA_SHOULD_HIDE = "h";
    
    boolean shouldHide;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        
        @Override
        public void onReceive(Context context, Intent intent) {
            shouldHide = intent.getBooleanExtra(INTENT_EXTRA_SHOULD_HIDE, false);
            
            Log.d("TTT", shouldHide ? "Received yes" : "Received no");
            
            manageHide();
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent startService = new Intent(this, ScreenService.class);
        startService(startService);
        
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(INTENT_ACTION_NAME));
        
    }
    
    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        
        super.onDestroy();
    }
    
    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("TT", "Key Down!!!");
        return false;
    }
    */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        
        Log.d("TTT", "FOcus?" + (hasFocus ? "Y" : "N"));
        if (hasFocus) {
            manageHide();
        }
    }
    
    private void manageHide() {
        if (shouldHide) {
            WindowHelper.hideSystemUI(getWindow());
        } else {
            WindowHelper.showSystemUI(getWindow());
        }
    }
}
