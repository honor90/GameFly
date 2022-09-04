package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;

public class Bullet extends Unit{
 
    public Bullet(Context context, Bitmap bmp, int image_X, int image_Y, int window_x, int window_y, int window_width, int window_height) 
    {
    	super(context, bmp, image_X, image_Y, window_x, window_y, window_width, window_height);
    }

    
}
