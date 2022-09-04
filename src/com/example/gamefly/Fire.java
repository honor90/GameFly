package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Fire extends Sprite {
	
	private HeroAircraft hero;
	
	public final int count_frame = 4;
	public int count_f=0;
	public final int count_line = 4;
	public int count_l=0;
	
	public Fire(Context context, Bitmap bmp, HeroAircraft hero)
	{
		super(context, bmp, 4, 4, hero.window_x, hero.window_y, (bmp.getWidth()/2)/4, (bmp.getHeight()/2)/4);
		this.hero = hero;
	}
	
	@Override
	public void updateCoordinatesUnit()
	{
		window_x = hero.window_x;
		window_y = hero.window_y-window_height/2;
	}
	
	public void drawFire(Canvas canvas)
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
