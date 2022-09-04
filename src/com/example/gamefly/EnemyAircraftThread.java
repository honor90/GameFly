package com.example.gamefly;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;

public class EnemyAircraftThread implements Runnable {
	
	private Context context;
	private Bitmap bmp;
	private  List<EnemyAircraft> enemyaircraft;
	
	private Thread thread = new Thread(this);
	
	public boolean pause = true;
	
	public EnemyAircraftThread(Context context, Bitmap bmp, List<EnemyAircraft> enemyaircraft)
	{
		this.context = context;
		this.bmp = bmp;
		this.enemyaircraft = enemyaircraft;
		thread.start();
	}

    @Override   
   	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
				if(!pause)
				{
					enemyaircraft.add(new EnemyAircraft(context, bmp));
					
				}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
    }
}
