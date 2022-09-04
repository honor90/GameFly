package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;

public class Menu extends Unit {
	
	private gameDisplay display;
	
	public boolean activate_menu;
	
	
	public Menu(Context context, Bitmap bmp)
	{
		super(context, bmp, 0, 0, 0, 0, 800, 480);	
		display = new gameDisplay(context);
		window_width = display.size_x;
		window_height = display.size_y;
	}
}
