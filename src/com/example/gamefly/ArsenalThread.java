package com.example.gamefly;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;

public class ArsenalThread implements Runnable {
	
	private Context context;
	private Bitmap bmp;
	private  List<Arsenal> arsenal;
	
	private Thread thread = new Thread(this);
	
	public boolean pause = true;
	
	public ArsenalThread(Context context, Bitmap bmp, List<Arsenal> arsenal)
	{
		this.context = context;
		this.bmp = bmp;
		this.arsenal = arsenal;
		thread.start();
	}
	

	@Override
	public void run() {
		
		while(true) {
			try {
					Thread.sleep(12000);
					if(!pause)
					{
						arsenal.add(new Arsenal(context, bmp));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

	}
}
