package com.example.gamefly;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class gameDisplay {
	
    public int size_x;
    public int size_y;
	
	public gameDisplay(Context context)
	{
        WindowManager wm = (WindowManager)  context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        size_x = size.x; 
        size_y = size.y; 
	}
}
