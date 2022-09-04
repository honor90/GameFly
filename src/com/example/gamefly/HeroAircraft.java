package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;

public class HeroAircraft extends Sprite {
	
	public boolean fly_up;
	public boolean fly_down;
	
	
	public HeroAircraft(Context context, Bitmap bmp)
	{
		super(context, bmp, 3, 1, 100, 100, (bmp.getWidth()/3)/3, bmp.getHeight()/3);
		//UnitScale();
	}
	
	@Override
	public void updateCoordinatesUnit()
	{
 	   if (fly_down) 
 	   {
 		   if(window_y + image_height < 480) window_y+=5;
 	   }
 	   if (fly_up) 
 	   {
 		   if(window_y > 0) window_y-=5;
 	   }
	}	

}
