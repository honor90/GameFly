package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Unit {
	
	public int image_x ;
	public int image_y ;	
    public int image_width;
    public int image_height;
    
    public int window_x;
    public int window_y;
    public int window_width;
    public int window_height;
    
    private final int display_width = 800;
    private final int display_height = 480;
    
    private Bitmap bmp;
    
    private gameDisplay display;
    
    public Unit(Context context, Bitmap bmp, int image_X, int image_Y, int window_x, int window_y, int window_width, int window_height)
    {
		this.bmp = bmp;
		this.image_x = image_X;
		this.image_y = image_Y;
		image_width = bmp.getWidth();
		image_height = bmp.getHeight();
		
    	this.window_x=window_x;
    	this.window_y=window_y;
    	this.window_width=window_width;
    	this.window_height=window_height;
    	
		image_width = bmp.getWidth();
		image_height = bmp.getHeight();
    	
		display = new gameDisplay(context);
    }
    
    public void UnitScale()
    {
    	window_x = display_width/window_x;
    	window_x = display.size_x/window_x;
    	window_y = display_height/window_y;
    	window_y = display.size_y/window_y;   	
    	window_width = window_width * (display.size_x/display_width);
    	window_height = window_height * (display.size_y/display_height);
    }
    
    public void Mirror()
    {
    	Matrix matrix = new Matrix();
    	matrix.preScale(-1,1);
    	bmp = Bitmap.createBitmap(bmp, 0,0,bmp.getWidth(),bmp.getHeight(), matrix,true);
    }
    
	public void drawImage(Canvas canvas)
	{
        Rect src = new Rect(image_x, image_y, image_x + image_width, image_y + image_height);//изображение , которое надо нарисовать
        Rect dst = new Rect(window_x, window_y, window_x + window_width, window_y + window_height); //окошко, в котором рисуют (маштабирует картинку)
        canvas.drawBitmap(bmp, src, dst, null);
	}	
	
	public void destroyUnion()
	{
		//this = null;
	}
	public void updateCoordinatesUnit(){}

}
