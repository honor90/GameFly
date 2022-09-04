package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;

public class Button extends Unit {
	
	public Button(Context context, Bitmap bmp, int window_x, int window_y, int window_width, int window_height)
	{
		super(context, bmp, 0, 0, window_x, window_y, window_width, window_height);
	}
	
	public boolean onTouch(int shot_x, int shot_y) 
	{
		if (shot_x >= window_x & shot_x <= window_x+window_width & shot_y >= window_y & shot_y <= window_y+window_height) 
		{
			return true;
		}
		return false;
	}

}
