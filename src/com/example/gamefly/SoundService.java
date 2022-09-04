package com.example.gamefly;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class SoundService extends Service {
    private static final String TAG = "SoundService";
    MediaPlayer player;
    
    @Override
    public IBinder onBind(Intent intent) {
            return null;
    }
    
    @Override
    public void onCreate() {
           // Toast.makeText(this, "My sound Service Created", Toast.LENGTH_LONG).show();
            
            player = MediaPlayer.create(this, R.raw.fon_sound);
            player.setLooping(true); // зацикливаем
    }

    @Override
    public void onDestroy() {
            //Toast.makeText(this, "My sound Service Stopped", Toast.LENGTH_LONG).show();
            player.stop();
    }
    
   
    @Override
    public void onStart(Intent intent, int startid) {
            //Toast.makeText(this, "My sound Service Started", Toast.LENGTH_LONG).show();
            player.start();
    }
}
