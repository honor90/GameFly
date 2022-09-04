package com.example.gamefly;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private Intent intent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             
        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        intent = new Intent(this, SoundService.class);


        setContentView(new GameView(this, intent));

    }
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	startService(intent);
    }
    
    @Override
    public void onPause()
    {
    	super.onPause();
    	stopService(intent);
    }
    
    @Override
    public void onRestart()
    {
    	super.onRestart();
    	startService(intent);
    }
    
    @Override
    public void onStop()
    {
    	super.onStop();
    	stopService(intent);
    }
    
    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	stopService(intent);
    }
    
}
