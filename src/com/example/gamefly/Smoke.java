package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Smoke extends Sprite {
	
	private HeroAircraft hero;
	
	public final int count_frame = 3;
	public int count_f=0;
	public final int count_line = 5;
	public int count_l=0;
	
	public Smoke (Context context, Bitmap bmp, HeroAircraft hero)
	{
		super(context, bmp, 3, 5, hero.window_x, hero.window_y, (bmp.getWidth()/2)/3, (bmp.getHeight()/2)/5);
		this.hero = hero;
	}
	
	@Override
	public void updateCoordinatesUnit()
	{
		window_x = hero.window_x-30;
		window_y = hero.window_y-20-window_height/2;
	}
	
	public void drawSmoke(Canvas canvas)
	{
		if(count_f<count_frame)count_f++;
		if(count_f==count_frame)
			{
				count_l++;
				count_f=0;
				if(count_l == count_line)
				{
					count_l=0;
				}
			}
			updateFrame(count_l);
			drawSprite(canvas);	
	}

}

