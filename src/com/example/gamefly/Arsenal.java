package com.example.gamefly;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;

public class Arsenal extends Unit {

	private static Random rnd = new Random();
	
	public Arsenal(Context context, Bitmap bmp)
	{
		super(context, bmp, 0, 0, 800, rnd.nextInt(480-100), 75/2, 60/2);
	}
	
	@Override
	public void updateCoordinatesUnit()
	{
		window_x -=5;
	}
}
