package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;

public class BulletHero extends Bullet {
	
    private final int mSpeed=10;
    public boolean touch_fire;
    
	public BulletHero(Context context, HeroAircraft hero, Bitmap bmp)
	{
    	super(context, bmp, 0, 0, hero.window_x+hero.window_width, hero.window_y+hero.window_height/2, bmp.getWidth()/2, bmp.getHeight()/2);
    }

    @Override
    public void updateCoordinatesUnit() 
    {     
 	    window_x += mSpeed ;
    }

}
