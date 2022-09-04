package com.example.gamefly;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
{

	private Context c;
	private Intent intent;
    
       /**Объект класса Sprite*/
       
       private List<BulletHero> bull_h = new ArrayList<BulletHero>();
       private List<BulletEnemy> bull_e = new ArrayList<BulletEnemy>();
       private List<EnemyAircraft> enemycraft = new ArrayList<EnemyAircraft>();
       private List<Bang> bang_e = new ArrayList<Bang>();
       private List<Jerrycan> jerrycan = new ArrayList<Jerrycan>();
       private List<Arsenal> arsenal = new ArrayList<Arsenal>();
       
       
       private JerrycanThread JerrycanThread;
       private ArsenalThread ArsenalThread;
       private EnemyAircraftThread EnemyAircraftThread;
       
       private gameDisplay display;
       
       private Map map_1;
       public  HeroAircraft hero;
       private Button ButtonUp;
       private Button ButtonDown;
       private Button ButtonFire;
       private Button ButtonSound_on;
       private Button ButtonSound_off;
       private Button ButtonPause;
       private BulletHero bull_hero;
       private BulletEnemy bull_enemy;
       private Panel panel;
       private Collision col = new Collision();
       private Counter counter;
       private Fire fire_hero;
       private Smoke smoke_hero;
       
       private int count_enemy_kill = 0;
       private int count_live_hero = 100;
       private int count_bullet_hero = 50;
       
       private Menu MainMenu;
       private Button ButtonMainPlay;
       private Button ButtonMainHighscores;
       private Button ButtonMainHelp;
       private Button ButtonMainExit;
       
       private Menu HighscoreMenu;
       private Button ButtonHighscoreBack;
       private Sprite count_enemy_1;
       private Sprite count_enemy_2;
       private Sprite count_enemy_3;
       private boolean draw_count_highscore = true;
       
       private Menu PauseMenu;
       private Button ButtonPauseMenuPlay;
       private Button ButtonPauseMenuReplay;
       private Button ButtonPauseMenuHome;
       
       private Menu ScreenDefeat;
       private Sprite count_enemycraft_1;
       private Sprite count_enemycraft_2;
       private Sprite count_enemycraft_3;
       private Sprite count_record_1;
       private Sprite count_record_2;
       private Sprite count_record_3;
       
       private Data data = new Data();
       
       /**Наше поле рисования*/
       private SurfaceHolder holder;
       
       private GameThread mThread;
       
       public boolean activate_game;
       
       
   	   public int shotX;
       public int shotY;   

       
       private SoundPool sounds;
       private int sound_bullet;
       private int explode;
       private Resources res;
       
       private boolean sound = true;
       
       private int count_bullet;
   	
       //конструктор
       public GameView(Context context, Intent intent) 
       {
  
   		super(context);
   		c = context;
   		this.intent = intent;
   		
   		res = new Resources(c);
		
   		mThread = new GameThread(this);
 	
   		display = new gameDisplay(context);

   		
   		
   		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
   		explode = sounds.load(context, R.raw.explode, 1);
   		
             /*Рисуем все наши объекты и все все все*/
             getHolder().addCallback(new SurfaceHolder.Callback() 
             {
          	  	 /*** Уничтожение области рисования */
            	 @Override
                 public void surfaceDestroyed(SurfaceHolder holder) 
                 {
              	   boolean retry = true;
              	    mThread.setRunning(false);
              	    while (retry)
              	    {
              	        try
              	        {
              	            // ожидание завершение потока
              	            mThread.join();
              	            retry = false;
              	        }
              	        catch (InterruptedException e) { }
              	    }
                 }
 
                 /** Создание области рисования */
                 @Override
                 public void surfaceCreated(SurfaceHolder holder) 
                 {
              	   mThread.setRunning(true);
              	   mThread.start();
                 }
 
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
                    {
                    }
             });
        	
             sound_bullet = sounds.load(context, R.raw.bullet, 1);
             
             map_1 = new Map(context, res.map);
             hero = new HeroAircraft(context, res.airplane);
             ButtonUp = new Button(context, res.button_up, 0, display.size_y-200, 100, 100);
             ButtonDown = new Button(context, res.button_down, 0, display.size_y-100, 100, 100);
             ButtonFire = new Button(context, res.button_fire, display.size_x-100, display.size_y-100, 100, 100);
             ButtonSound_on = new Button(context, res.sound_on, 170, display.size_y-70, 40, 70);
             ButtonSound_off = new Button(context, res.sound_off, 170, display.size_y-70, 40, 70);
             bull_hero = new BulletHero(context, hero, res.bullet);
             panel= new Panel(context, res, (display.size_x-res.panel.getWidth())/2, display.size_y-res.panel.getHeight(), res.panel.getWidth(), res.panel.getHeight());       
		   	 JerrycanThread = new JerrycanThread(c, res.jerrycan, jerrycan);
		   	 ArsenalThread = new ArsenalThread(c, res.arsenal, arsenal);
		   	 EnemyAircraftThread = new EnemyAircraftThread(c, res.enemyaircraft, enemycraft);            
             ButtonPause = new Button(context, res.button_pause, panel.window_x+panel.window_width+20, display.size_y-70, 50, 50);
             counter = new Counter(panel);
             fire_hero = new Fire(context, res.fire, hero);
             smoke_hero = new Smoke(context, res.smoke, hero);
             MainMenu = new Menu(context, res.main_menu);
     		 ButtonMainPlay = new Button(context, res.main_menu_button_play, 350, 200, res.main_menu_button_play.getWidth(), res.main_menu_button_play.getHeight());
     		 ButtonMainHighscores = new Button(context, res.main_menu_button_highscores, 250, 200+res.main_menu_button_play.getHeight(), res.main_menu_button_highscores.getWidth(), res.main_menu_button_highscores.getHeight());
     		 ButtonMainHelp = new Button(context, res.main_menu_button_help, 350, 200+2*res.main_menu_button_play.getHeight(), res.main_menu_button_help.getWidth(), res.main_menu_button_help.getHeight());
     		 ButtonMainExit = new Button(context, res.main_menu_button_exit, 350, 200+3*res.main_menu_button_play.getHeight(), res.main_menu_button_exit.getWidth(), res.main_menu_button_exit.getHeight());
     		 HighscoreMenu = new Menu(context, res.main_menu);
     		 ButtonHighscoreBack = new Button(context,res.highscore_menu_button_back, 0, display.size_y-res.highscore_menu_button_back.getHeight(),  res.highscore_menu_button_back.getWidth(), res.highscore_menu_button_back.getHeight());
     		 count_enemy_1 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-500, display.size_y-200, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 count_enemy_2 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-500+res.numbers_highscore.getWidth()/10, display.size_y-200, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 count_enemy_3 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-500+2*res.numbers_highscore.getWidth()/10, display.size_y-200, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 count_enemycraft_1 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-380, display.size_y-270, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
    		 count_enemycraft_2 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-380+res.numbers_highscore.getWidth()/10, display.size_y-270, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
    		 count_enemycraft_3 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-380+2*res.numbers_highscore.getWidth()/10, display.size_y-270, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 count_record_1 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-380, display.size_y-200, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 count_record_2 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-380+res.numbers_highscore.getWidth()/10, display.size_y-200, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 count_record_3 = new Sprite(context, res.numbers_highscore, 10, 1, display.size_x-380+2*res.numbers_highscore.getWidth()/10, display.size_y-200, res.numbers_highscore.getWidth()/10, res.numbers_highscore.getHeight());
     		 PauseMenu = new Menu(context, res.menu_pause);
     		 PauseMenu.window_x = display.size_x/2 - res.menu_pause.getWidth()/2;
     		 PauseMenu.window_y = display.size_y/2 - res.menu_pause.getHeight()/2;
     		 PauseMenu.window_width = res.menu_pause.getWidth();
     		 PauseMenu.window_height = res.menu_pause.getHeight();
     		 ButtonPauseMenuPlay = new Button(context, res.menu_pause_button_play, PauseMenu.window_x, PauseMenu.window_y+150, res.menu_pause_button_play.getWidth(), res.menu_pause_button_play.getHeight());
     		 ButtonPauseMenuReplay = new Button(context, res.menu_pause_button_replay, PauseMenu.window_x+2*res.menu_pause_button_play.getWidth(), PauseMenu.window_y+150, res.menu_pause_button_replay.getWidth(), res.menu_pause_button_replay.getHeight());
     		 ButtonPauseMenuHome = new Button(context, res.menu_pause_button_home, PauseMenu.window_x+4*res.menu_pause_button_play.getWidth(), PauseMenu.window_y+150, res.menu_pause_button_home.getWidth(), res.menu_pause_button_home.getHeight());
             ScreenDefeat = new Menu(context, res.screen_defeat);
		   	  
     		 MainMenu.activate_menu = true;
     		 HighscoreMenu.activate_menu = false;
     		 PauseMenu.activate_menu = false;
     		 activate_game = false;
     		 ScreenDefeat.activate_menu = false;
     		 
       }
       
       private int count;
       private boolean fire;
       private int count_e;
       //Рисуем нашу картинку на черном фоне
       protected void myDraw(Canvas canvas) 
       {
    	     map_1.updateCoordinatesUnit();
    	     map_1.drawImage(canvas);

            
       	   if(bull_hero.touch_fire && count_bullet_hero > 0) 
    	   {
    	       if(count==3)
    	    	   {
    	    	   bull_h.add(new BulletHero(c, hero, res.bullet));
    	    	   sounds.play(sound_bullet, 1.0f, 1.0f, 0, 0, 1.5f);
    	    	   count=0;
    	    	   count_bullet_hero--;
    	    	   panel.num_bullet = true;
    	    	   }
    	       count++;
    	   }
             
             Iterator<BulletHero> j = bull_h.iterator();
             while(j.hasNext()) {
           	  Bullet b = j.next();
           	  if(b.window_x <= display.size_x) {
           		  b.updateCoordinatesUnit();
           		  b.drawImage(canvas);
           	  } else {
           		  j.remove();
           	  }
             }

             Iterator<EnemyAircraft> i = enemycraft.iterator();
             while(i.hasNext()) {
            	 EnemyAircraft b = i.next();
           	  if(b.window_x >= -b.image_width) {	 
    	        	 
           		  		b.updateCoordinatesUnit(hero);
           		  		b.drawImage(canvas);
           		  		if(b.searhHero(hero))
           		  		{
           		  			fire = true;
           		  		}          		  
              	   if(fire) 
            	   {
            	       if(count_e==7)
            	    	   {       	   
            	    	   bull_e.add(new BulletEnemy(c, b, res.bullet));
            	    	   sounds.play(sound_bullet, 1.0f, 1.0f, 0, 0, 1.5f);
            	    	   count_e=0;
            	    	   fire=false;
            	    	   }
            	       count_e++;
            	       
            	   }
           	  } else {
           		  i.remove();
           	  }
             }
             
             Iterator<BulletEnemy> l = bull_e.iterator();
             while(l.hasNext()) {
           	  Bullet b = l.next();
           	  if(b.window_x >= -b.image_width) {
           		  b.updateCoordinatesUnit();
           		  b.drawImage(canvas);
           	  } else {
           		  l.remove();
           	  }
             }
             
             Iterator<Bang> b = bang_e.iterator();
             while(b.hasNext()) {
           	  Bang b_e = b.next();
           	  if (b_e.count_l<=b_e.count_line)
           	  {
           		  b_e.drawBang(canvas);
              }
           	  else b.remove();
           	  }
             
             Iterator<Jerrycan> m = jerrycan.iterator();
             while(m.hasNext()) {
           	  Jerrycan c = m.next();
           	  if(c.window_x >= -c.image_width) {
           		  c.updateCoordinatesUnit();
           		  c.drawImage(canvas);
           	  } else {
           		  m.remove();
           	  }
             }
             
             Iterator<Arsenal> z = arsenal.iterator();
             while(z.hasNext()) {
           	  Arsenal h = z.next();
           	  if(h.window_x >= -h.image_width) {
           		  h.updateCoordinatesUnit();
           		  h.drawImage(canvas);
           	  } else {
           		  z.remove();
           	  }
             }
             
             hero.updateCoordinatesUnit();
             hero.updateFrame(0);
             hero.drawSprite(canvas);
             
             if(count_live_hero < 60 && count_live_hero > 40)
             {
            	 smoke_hero.updateCoordinatesUnit();
            	 smoke_hero.drawSmoke(canvas);
             }
             
             if(count_live_hero < 40)
             {
            	 fire_hero.updateCoordinatesUnit();
            	 fire_hero.drawFire(canvas);
             }
             
             
             

             panel.drawPanel(canvas);
             ButtonUp.drawImage(canvas);
             ButtonDown.drawImage(canvas);
             ButtonFire.drawImage(canvas);
             ButtonPause.drawImage(canvas);

             if(sound)ButtonSound_on.drawImage(canvas);
             if(!sound)ButtonSound_off.drawImage(canvas);
             
             if(counter.heroCounter())
             {
            	 activate_game = false;
     			 ScreenDefeat.activate_menu = true;
     			 count_live_hero=100;
             }
             
             
       }
       
       public void DrawMainMenu(Canvas canvas)
       {
    	   if(MainMenu.activate_menu)
    	   {
   				MainMenu.drawImage(canvas);
   				ButtonMainPlay.drawImage(canvas);
   				ButtonMainHighscores.drawImage(canvas);
   				ButtonMainHelp.drawImage(canvas);
   				ButtonMainExit.drawImage(canvas);
    	   }
       }
     
       public void DrawHighscoreMenu(Canvas canvas)
       {
    	   int count_lo = 0;
    	   int count_hi = 0; 
    	   if(HighscoreMenu.activate_menu)
    	   {
    		   HighscoreMenu.drawImage(canvas);
    		   ButtonHighscoreBack.drawImage(canvas);
    		   data.loadHighscores(c);
    		   if(draw_count_highscore){
    		   for(int m=0; m<data.savedHighscore; m++)
    		   {
    			    count_enemy_3.updateFrame(0);
    				count_lo++;
    				if(count_lo == 10) 
    					{
    						count_lo = 0;
    						count_hi++;
    						count_enemy_2.updateFrame(0);
    					}
    					
    					if(count_hi == 10) 
    						{
    						    count_enemy_1.updateFrame(0);
    							count_hi = 0;
    						}   
    		   } 
    		   }
    		   draw_count_highscore = false;
    		   count_enemy_1.drawSprite(canvas);
    		   count_enemy_2.drawSprite(canvas);
    		   count_enemy_3.drawSprite(canvas);
    	   }
       }
       
       public void DrawPauseMenu(Canvas canvas)
       {
    	   if(PauseMenu.activate_menu)
    	   {
    		   PauseMenu.drawImage(canvas);
    		   ButtonPauseMenuPlay.drawImage(canvas);
    		   ButtonPauseMenuReplay.drawImage(canvas);
    		   ButtonPauseMenuHome.drawImage(canvas);
    		   mThread.pause = true;
    	   }
       }
       
       public void DrawScreenDefeat(Canvas canvas)
       {
    	   if(ScreenDefeat.activate_menu)
    	   {
        	   int count_record_lo = 0;
        	   int count_record_hi = 0; 
        	   int count_enemycraft_lo = 0;
        	   int count_enemycraft_hi = 0; 
        	   data.loadHighscores(c);
        	   int record = data.savedHighscore;
    		   ScreenDefeat.drawImage(canvas);
    		   
    		   for(int m=0; m<record; m++)
    		   {
    			    count_record_3.updateFrame(0);
    				count_record_lo++;
    				if(count_record_lo == 10) 
    					{
    						count_record_lo = 0;
    						count_record_hi++;
    						count_record_2.updateFrame(0);
    					}
    					
    					if(count_record_hi == 10) 
    						{
    						    count_record_1.updateFrame(0);
    							count_record_hi = 0;
    						}   
    		   }
    		   
    		   for(int m=0; m<count_enemy_kill; m++)
    		   {
    			    count_enemycraft_3.updateFrame(0);
    				count_enemycraft_lo++;
    				if(count_enemycraft_lo == 10) 
    					{
    						count_enemycraft_lo = 0;
    						count_enemycraft_hi++;
    						count_enemycraft_2.updateFrame(0);
    					}
    					
    					if(count_enemycraft_hi == 10) 
    						{
    						    count_enemycraft_1.updateFrame(0);
    							count_enemycraft_hi = 0;
    						}   
    		   }
    		   data.saveHighscores(c, count_enemy_kill);
    		   count_enemy_kill = 0;
     		   count_enemycraft_1.drawSprite(canvas);
    		   count_enemycraft_2.drawSprite(canvas);
    		   count_enemycraft_3.drawSprite(canvas);   		   
    		   count_record_1.drawSprite(canvas);
    		   count_record_2.drawSprite(canvas);
    		   count_record_3.drawSprite(canvas);
     		   count_enemycraft_1.reloadFrame();
    		   count_enemycraft_2.reloadFrame();
    		   count_enemycraft_3.reloadFrame();	   
    		   count_record_1.reloadFrame();
    		   count_record_2.reloadFrame();
    		   count_record_3.reloadFrame();

    		   mThread.pause = true;
    	   }
       }
       

       @Override 
       public boolean onTouchEvent(MotionEvent e) 
       {
    	int pointerIndex = e.getActionIndex();
       	shotX = (int) e.getX(pointerIndex);
       	shotY = (int) e.getY(pointerIndex);

       	switch(e.getActionMasked())
       	{
       	case MotionEvent.ACTION_DOWN:
       	case MotionEvent.ACTION_POINTER_DOWN:
       		if(activate_game)
       		{
      		 if(ButtonUp.onTouch(shotX, shotY)) hero.fly_up = true;
      		 if(ButtonDown.onTouch(shotX, shotY)) hero.fly_down = true;  		
      		 if(ButtonFire.onTouch(shotX, shotY)) bull_hero.touch_fire = true; 
      		 if(ButtonSound_on.onTouch(shotX, shotY)) 
      			 {
      			   if(sound)
      			   {
      				   sound = false;
      				   c.stopService(new Intent(intent));
      			   }
      			   else
      			   {
         			    sound = true;
          			    c.startService(new Intent(intent));    				   
      			   }
      			 }
      		 if(ButtonPause.onTouch(shotX, shotY)) 
      			 {
      			 	StopGame();
      			 	activate_game = false;
      			 	PauseMenu.activate_menu = true;
      			 }
       		}
       		
      		 if(MainMenu.activate_menu)
      			 {
      			 	if(ButtonMainPlay.onTouch(shotX, shotY))
      			 	{
      			 		ReloadLevel();
      					PlayGame();
      			 		MainMenu.activate_menu = false;
      			 		activate_game = true;
      			 	}
      			 	if(ButtonMainHighscores.onTouch(shotX, shotY))
      			 	{
      			 		HighscoreMenu.activate_menu = true;
      			 		MainMenu.activate_menu = false;
      			 		draw_count_highscore = true;
      			 	}
      			 	if(ButtonMainHelp.onTouch(shotX, shotY))
      			 	{
      			 		
      			 	}
      			 	if(ButtonMainExit.onTouch(shotX, shotY))
      			 	{
      			 		((Activity) c).finish();
      			 	}
      			 }
      		 if(HighscoreMenu.activate_menu)
      		 {
      			 if(ButtonHighscoreBack.onTouch(shotX, shotY))
      			 {
   			 		HighscoreMenu.activate_menu = false;
   			 		MainMenu.activate_menu = true;
      			 }
      		 }
      		 
      		 if(PauseMenu.activate_menu)
      		 {
      			 if(ButtonPauseMenuPlay.onTouch(shotX, shotY))
      			 {
       			 	mThread.pause = false;
       			 	PlayGame();
       			 	activate_game = true;
       			 	PauseMenu.activate_menu = false;
      			 }
      			 if(ButtonPauseMenuReplay.onTouch(shotX, shotY))
      			 {
      				 ReloadLevel();
      				 PlayGame();
      				 mThread.pause = false;
           			 activate_game = true;
           			 PauseMenu.activate_menu = false;
      				 
      			 }
      			 if(ButtonPauseMenuHome.onTouch(shotX, shotY))
      			 {
      				 mThread.pause = false;
           			 PauseMenu.activate_menu = false;
      				 activate_game = false;
      				 MainMenu.activate_menu = true;
      			 }
      		 }
      		 
      		 if(ScreenDefeat.activate_menu)
      		 {
      			 if(shotX>=0 && shotX<=display.size_x && shotY>=0 && shotY<=display.size_y)
      			 {
      				ReloadLevel();
      				PlayGame();
      				mThread.pause = false;
      				ScreenDefeat.activate_menu=false;
      				activate_game = true;
      			 }
      		 }
       	break;
       	case MotionEvent.ACTION_UP:
       	case MotionEvent.ACTION_POINTER_UP:
       		if(activate_game)
       		{
       			if(ButtonUp.onTouch(shotX, shotY)) hero.fly_up = false;
       			if(ButtonDown.onTouch(shotX, shotY)) hero.fly_down = false;  		
       			if(ButtonFire.onTouch(shotX, shotY))bull_hero.touch_fire = false;
       		}
       	break;	  
       	}

   		return true;
       }

       /*Проверка на столкновения*/
       public void testCollision() {

   			Iterator<EnemyAircraft> e = enemycraft.iterator();
   			while(e.hasNext()) {
   				EnemyAircraft enemy = e.next();     	  
   	        	 if (col.CollisionHeroAndEnemy(hero, enemy))
   	        	 {
   	        		 	   count_live_hero=100;
   	        		       data.saveHighscores(c, count_enemy_kill);
   	        		 	   bang_e.add(new Bang(c, res.bang, enemy.window_x, enemy.window_y));
   	        			   e.remove();
   	        			   sounds.play(explode, 1.0f, 1.0f, 0, 0, 1.5f);
   	        			   activate_game = false;
   	        			   ScreenDefeat.activate_menu = true;
   	        			   //data.saveHighscores(c, count_enemy_kill);

   	        	 } 	 
   	        	 
   	        	     	   Iterator<BulletHero> b = bull_h.iterator();
   		while(b.hasNext()) {
   			BulletHero bull = b.next();
   	        	 
   	        	 if (col.CollisionBulletAndEnemy(bull, enemy))
   	        	 {
   	        		 b.remove();
   	        		 if(count_bullet==3)
   	        		 {
   	        		 	   bang_e.add(new Bang(c, res.bang, enemy.window_x, enemy.window_y));
   	        			   e.remove();
   	        			   
   	        			   sounds.play(explode, 1.0f, 1.0f, 0, 0, 1.5f);
   	        			   panel.num_enemy_kill = true; 
   	        			   count_enemy_kill++;
   	        			   count_bullet=0;
   	        		 }
   	        		 count_bullet++;
   	        	 } 	 
   			}
   			}
   			
   			Iterator<Jerrycan> g = jerrycan.iterator();
   			while(g.hasNext()) {
   				Jerrycan jer = g.next();
   				if(col.CollisionHeroAndJerrycan(hero, jer))
   				{
   					panel.addLedFuel();
   					g.remove();
   				}
   			}
   			
   			Iterator<BulletEnemy> be = bull_e.iterator();
   			while(be.hasNext()) {
   				BulletEnemy ble = be.next();
   				if(col.CollisionBulletAndHero(hero, ble))
   				{
   					panel.live_hero_sub = true;
   					be.remove();
   					count_live_hero --;
   				}
   			}
   			
   			Iterator<Arsenal> ars = arsenal.iterator();
   			while(ars.hasNext()) {
   				Arsenal ar = ars.next();
   				if(col.CollisionHeroAndArsenal(hero, ar))
   				{
   					count_bullet_hero+=50;
   					panel.add_bullet();
   					ars.remove();
   				}
   			}
   			
   	}
       
       
      private void StopGame()
      {
		  ArsenalThread.pause = true;
		  JerrycanThread.pause = true;
		  EnemyAircraftThread.pause = true;
		  panel.pause = true;   	  
      }
      
      private void PlayGame()
      {
		  ArsenalThread.pause = false;
		  JerrycanThread.pause = false;
		  EnemyAircraftThread.pause = false;
		  panel.pause = false;
      }
       
       
      private void ReloadLevel()
      {
			 map_1.window_x = 0;
			 panel.reload();
			 count_bullet_hero=50;
			 hero.window_y=(display.size_y/2)-65-(hero.window_height/2);
			 
            Iterator<BulletHero> ji = bull_h.iterator();
            while(ji.hasNext()) {
           	  	ji.next();
          		ji.remove();
          	  }

            Iterator<EnemyAircraft> ii = enemycraft.iterator();
            while(ii.hasNext()) {
           	  	ii.next();
          		ii.remove();
          	  }
            
            Iterator<BulletEnemy> li = bull_e.iterator();
            while(li.hasNext()) {
            	li.next();
          		li.remove();
          	  }
            
            Iterator<Bang> bi = bang_e.iterator();
            while(bi.hasNext()) {
           	 	bi.next();
          	  	bi.remove();
          	  }
            
            Iterator<Jerrycan> mi = jerrycan.iterator();
            while(mi.hasNext()) {
           	  	mi.next();
          		mi.remove();
          	  }
            
            Iterator<Arsenal> zi = arsenal.iterator();
            while(zi.hasNext()) {
           	  	zi.next();
          		zi.remove();
            }
      }
       
		
}

