package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite extends Unit {
	
	
	private int frame = 0;
	private int count_frame;
	

	
	public Sprite(Context context, Bitmap bmp, int columns, int lines, int window_x, int window_y, int window_width, int window_height)
	{
		super(context, bmp, 0, 0, window_x, window_y, window_width, window_height);

		image_width = image_width/columns;
		image_height =image_height/lines;	
		count_frame = columns-1;
	}
	
	public void updateFrame(int num_line)
	{
		if(frame < count_frame)frame++;
		else frame = 0;
		image_x = image_width*frame;
		if (num_line > 0)image_y = image_height*num_line;
	}
	
	public void reloadFrame()
	{
		frame=0;
		image_x=0;
	}
	
	public void drawSprite(Canvas canvas)
	{
		drawImage(canvas);
	}

}
