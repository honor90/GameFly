package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;

public class BulletEnemy extends Bullet {

	private final int bulletSpeed=10;
	public boolean fire;
	
	public BulletEnemy(Context context, EnemyAircraft enemy, Bitmap bmp)
	{
		super(context, bmp, 0, 0, enemy.window_x, enemy.window_y+enemy.window_height/2, bmp.getWidth()/2, bmp.getHeight()/2);
		Mirror();
	}
	
    @Override
    public void updateCoordinatesUnit() 
    {     
 	    window_x -= bulletSpeed ;
    }

}
