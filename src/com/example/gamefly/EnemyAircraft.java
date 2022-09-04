package com.example.gamefly;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;

public class EnemyAircraft extends Unit{

	private final int radius_search_x = 500;
	private final int radius_search_y = 50;
	private static Random rnd = new Random();
	
	public EnemyAircraft(Context context, Bitmap bmp)
	{
		super(context, bmp, 0, 0, 800, rnd.nextInt(480-200), bmp.getWidth()/3, bmp.getHeight()/3);
	}
	
	
	public void updateCoordinatesUnit(HeroAircraft hero)
	{
		window_x -= 6;
		//if(searhHero(hero))
		//{
			if(hero.window_y>window_y && window_y > 0)window_y-=1;
			if(hero.window_y<window_y)
				{
					if(window_y+image_height<380)
						{
							window_y+=1;
						}
				}
		//}
	}
	
	public boolean searhHero( HeroAircraft hero)
	{
		if(window_x - (hero.window_x+hero.window_width) < radius_search_x 
				&& Math.abs(window_y-(hero.window_y+hero.window_height)) < radius_search_y) return true;
		return false;
	}

}
