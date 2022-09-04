package com.example.gamefly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Panel extends Unit implements Runnable {
	

	public boolean num_enemy_kill = false;
	private int count_kill_1 = 0;
	private int count_kill_2 = 0;
	private int count_kill_3 = 0;
	

    
    private Sprite num1;
    private Sprite num2;
    private Sprite num3;

    private int count_bullet1;
    private int count_bullet2;
    private int count_bullet3;
    private Sprite numbers_bullet1;
    private Sprite numbers_bullet2;
    private Sprite numbers_bullet3;
    public boolean num_bullet;
    
    public int count_live1;
    public int count_live2;
    public int count_live3;
    private Sprite count_live_hero1;
    private Sprite count_live_hero2;
    private Sprite count_live_hero3;
    public boolean live_hero_sub;
    
    private List<Unit> fuel = new ArrayList<Unit>();
    private List<Unit> fuel_down = new ArrayList<Unit>();
    private int indexLedFuel;
    public int count_led_fuel_down;
    
    private Thread thread = new Thread(this);
    
    public boolean pause = true;

	
	public Panel(Context context, Resources res, int window_x, int window_y, int window_width, int window_height)
	{
		super(context, res.panel, 0, 0, window_x, window_y, window_width, window_height);
		
		numbers_bullet1 = new Sprite(context, res.numbers, 10, 1, window_x+50, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		numbers_bullet2 = new Sprite(context, res.numbers, 10, 1, window_x+50+res.numbers.getWidth()/10, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		numbers_bullet3 = new Sprite(context, res.numbers, 10, 1, window_x+50+2*res.numbers.getWidth()/10, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		count_live_hero1 = new Sprite(context, res.numbers, 10, 1, window_x+115, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		count_live_hero2 = new Sprite(context, res.numbers, 10, 1, window_x+115+res.numbers.getWidth()/10, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		count_live_hero3 = new Sprite(context, res.numbers, 10, 1, window_x+115+2*res.numbers.getWidth()/10, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		num1 = new Sprite(context, res.numbers, 10, 1, window_x+235, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		num2 = new Sprite(context, res.numbers, 10, 1, window_x+235+res.numbers.getWidth()/10, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		num3 = new Sprite(context, res.numbers, 10, 1, window_x+235+2*res.numbers.getWidth()/10, window_y+25, res.numbers.getWidth()/10, res.numbers.getHeight());
		
	    count_live1 = 1;
	    count_live2 = 9;
	    count_live3 = 10;
	    count_live_hero1.image_x =count_live_hero1.window_width;
	    
	    live_hero_sub = false;
	    
	    count_bullet1 = 3;
	    count_bullet2 = 9;
	    count_bullet3 = 10;
	    numbers_bullet1.image_x = numbers_bullet1.window_width*count_bullet1;
	    num_bullet = false;
		
		thread.start();
		
	    indexLedFuel = 0;
	    count_led_fuel_down = 0;
		
		for(int i=0; i<12; i++)
		{
			fuel_down.add(new Unit(context, res.fuel_down, 0, 0, window_x+80+15*i, window_y+55, 15, 25));
		}
		
		for(int i=0; i<12; i++)
		{
			fuel.add(new Unit(context, res.fuel, 0, 0, window_x+80+15*i, window_y+55, 15, 25));
		}
	}
	
	private void update_bullet_num()
	{
		if(num_bullet)
		{
			count_bullet3--;
			numbers_bullet3.image_x =numbers_bullet3.window_width * count_bullet3;
			numbers_bullet2.image_x =numbers_bullet2.window_width * count_bullet2;
			

			if(count_bullet3 == -1)
			{
				count_bullet3=9;
				count_bullet2--;
				numbers_bullet3.image_x =numbers_bullet3.window_width * count_bullet3;
				numbers_bullet2.image_x =numbers_bullet2.window_width * count_bullet2;	
			}
			
			if(count_bullet2 == -1)
			{
				count_bullet2=9;
				count_bullet1--;
				numbers_bullet2.image_x =numbers_bullet2.window_width * count_bullet2;
				numbers_bullet1.image_x =numbers_bullet1.window_width * count_bullet1;
			}	
			num_bullet = false;	
		}
	}
	
	private void update_live_hero()
	{
		if(live_hero_sub)
		{	
			count_live3--;
			count_live_hero3.image_x =count_live_hero3.window_width * count_live3;
			count_live_hero2.image_x =count_live_hero2.window_width * count_live2;
			
			if(count_live3 == -1)
			{
				count_live3=9;
				count_live2--;
				count_live_hero3.image_x =count_live_hero3.window_width * count_live3;
				count_live_hero2.image_x =count_live_hero2.window_width * count_live2;	
			}
			
			if(count_live2 == -1)
			{
				count_live2=9;
				count_live1--;
				count_live_hero2.image_x =count_live_hero2.window_width * count_live2;
				count_live_hero1.image_x =count_live_hero1.window_width * count_live1;
			}
			
			live_hero_sub = false;
		}
	}
	
	
	private void update_kill()
	{
		if(num_enemy_kill)
		{
			count_kill_3++;
			num3.image_x =num3.window_width * count_kill_3;
			num2.image_x =num2.window_width * count_kill_2;
			if(count_kill_3 == 10) 
				{
					count_kill_3 = 0;
					count_kill_2++;
					num3.image_x =num3.window_width * count_kill_3;
					num2.image_x =num2.window_width * count_kill_2;
				}
				
				if(count_kill_2 == 10) 
					{
						count_kill_3 = 0;
						count_kill_2 ++;
						num2.image_x =num2.window_width * count_kill_2;
						num1.image_x =num1.window_width * count_kill_1;
					}
				num_enemy_kill=false;
			}
	}

	
	public void add_bullet()
	{
		count_bullet2 +=5;
		if(count_bullet2>9)
		{
			count_bullet1++;
			count_bullet2 -=10;
		}
		numbers_bullet2.image_x =numbers_bullet2.window_width * count_bullet2;
		numbers_bullet1.image_x =numbers_bullet1.window_width * count_bullet1;
	}
	
	public void addLedFuel()
	{
		count_led_fuel_down -=2;
	}
	
	public void drawPanel(Canvas c)
	{
		drawImage(c);
		update_bullet_num();
		numbers_bullet1.drawSprite(c);
		numbers_bullet2.drawSprite(c);
		numbers_bullet3.drawSprite(c);
		update_live_hero();
		count_live_hero1.drawSprite(c);
		count_live_hero2.drawSprite(c);
		count_live_hero3.drawSprite(c);
		update_kill();
		num1.drawSprite(c);
		num2.drawSprite(c);
		num3.drawSprite(c);
		
        Iterator<Unit> d = fuel_down.iterator();
        while(d.hasNext()) {
      	  Unit f_d = d.next();
      	  f_d.drawImage(c);
        }
		
        Iterator<Unit> l = fuel.iterator();
        while(l.hasNext()) {
      	  Unit b = l.next();
      	  if(indexLedFuel < 12-count_led_fuel_down) {
      		  indexLedFuel++;	
      		  b.drawImage(c);
      	  }
        }
        indexLedFuel = 0;

	}
	
	public void reload()
	{
		 count_live1 = 1;
		 count_live2 = 0;
	     count_live3 = 0;
	     count_live_hero1.image_x =count_live_hero1.window_width*count_live1;
	     count_live_hero2.image_x =count_live_hero2.window_width*count_live2;
		 count_live_hero3.image_x =count_live_hero3.window_width*count_live3;
			
	     count_bullet1 = 0;
	     count_bullet2 = 5;
	     count_bullet3 = 0;
	     numbers_bullet1.image_x = numbers_bullet1.window_width*count_bullet1;
	     numbers_bullet2.image_x = numbers_bullet2.window_width*count_bullet2;
	     numbers_bullet3.image_x = numbers_bullet3.window_width*count_bullet3;
	     
	     count_kill_1 = 0;
	     count_kill_2 = 0;
	     count_kill_3 = 0;
	     num1.image_x =num1.window_width * count_kill_1;
	     num2.image_x =num2.window_width * count_kill_2;
		 num3.image_x =num3.window_width * count_kill_3;
		 
		 count_led_fuel_down = 0;
		 
	}

@Override
public void run()
{
	while(true) {
		try {
			Thread.sleep(6000);
			if(!pause)
			{
				
				count_led_fuel_down++;
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

