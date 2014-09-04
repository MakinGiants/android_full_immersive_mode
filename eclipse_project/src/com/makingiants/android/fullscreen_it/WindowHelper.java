package com.makingiants.android.fullscreen_it;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class WindowHelper {
    
    // This snippet hides the system bars.
    public static void hideSystemUI(Window window) {
        
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        
        //View.Invisible
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    
    public static void showSystemUI(Window window) {
        
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.getDecorView().setSystemUiVisibility(0);
        
        /*
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);*/
    }
}
