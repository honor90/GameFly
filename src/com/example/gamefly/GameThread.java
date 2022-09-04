package com.example.gamefly;

import android.graphics.Canvas;

public class GameThread extends Thread{
	
	private Canvas canvas;
	
	static final long FPS = 10;
	
    /**Переменная запускающая поток рисования*/
    private boolean running = false;
    public boolean pause = false;
       
	/**Объект класса*/
    private GameView view;	 
    
    /**Конструктор класса*/
    public GameThread(GameView view) 
    {
          this.view = view;
    }

    /**Задание состояния потока*/
    public void setRunning(boolean run) 
    {
          running = run;
    }

    /** Действия, выполняемые в потоке */
    public void run()
    {
    	
        long ticksPS = 1000 / FPS;
        
        long startTime = 0;
        
        long sleepTime;
        
    	
        while (running)
        {
            canvas = null;
            
            try
            {
            	
            	if(!pause){
                // подготовка Canvas-а
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                	view.DrawMainMenu(canvas);
                	view.DrawHighscoreMenu(canvas);
                	view.DrawPauseMenu(canvas);
                	view.DrawScreenDefeat(canvas);
                	if(view.activate_game)
                	{
                		view.myDraw(canvas);
                		view.testCollision();
                	}
                }
            	}
            	
            }
            catch (Exception e) { }
            finally
            {
                if (canvas != null)
                {
                	view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
           /* sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try 
            {
                   if (sleepTime > 0)
                          sleep(sleepTime);
                   else
                          sleep(10);
            } catch (Exception e) {}*/
        }
    }
}
