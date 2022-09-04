package com.example.gamefly;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;

public class JerrycanThread implements Runnable {
	
	private Context context;
	private Bitmap bmp;
	private  List<Jerrycan> jerrycan;
	
	private Thread thread = new Thread(this);
	
	public boolean pause = true;
	
	public JerrycanThread(Context context, Bitmap bmp, List<Jerrycan> jerrycan)
	{
		this.context = context;
		this.bmp = bmp;
		this.jerrycan = jerrycan;
		thread.start();
	}
	

	@Override
	public void run() {
		
		while(true) {
			try {
					Thread.sleep(18000);
					if(!pause)
					{
						
						jerrycan.add(new Jerrycan(context, bmp));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

	}

}
