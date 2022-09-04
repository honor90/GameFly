package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;

public class Map extends Unit {

    private gameDisplay display;
   
    /**Скорость по Х=5*/
    private int xSpeed = 5;
	
	
	public Map(Context context, Bitmap bmp)
	{
		super(context, bmp, 0, 0, 0, 0, bmp.getWidth(), bmp.getHeight());
		display = new gameDisplay(context);
		//UnitScale();
	}
	
	@Override
	public void updateCoordinatesUnit()
	{
       if (window_x > -(window_width - display.size_x)) window_x -= xSpeed;
       else window_x = 0;
	}

}
