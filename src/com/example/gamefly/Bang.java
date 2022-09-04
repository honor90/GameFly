package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bang extends Sprite {
	
	public final int count_frame = 7;
	public int count_f=0;
	public final int count_line = 7;
	public int count_l=0;
	
	public Bang(Context context, Bitmap bmp, int window_x, int window_y)
	{
		super(context, bmp, 7, 7, window_x, window_y, (bmp.getWidth()/2)/7, (bmp.getHeight()/2)/7);
	}
	
	
	public void drawBang(Canvas canvas)
	{
		if(count_f<count_frame)count_f++;
		if(count_f==count_frame)
			{
				count_l++;
				count_f=0;
			}
			updateFrame(count_l);
			drawSprite(canvas);	
	}
}
