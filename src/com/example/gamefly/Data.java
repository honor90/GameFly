package com.example.gamefly;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Data {
	  private SharedPreferences sPref;
	  
	  private final String SAVED_HIGHSCORE = "saved_highscore";
	  
	  public int savedHighscore;
	
	  public void saveHighscores(Context c, int highscore) 
	  {
		  loadHighscores(c); 
		  int data = savedHighscore;
		  if(highscore>data)
		  {
			  sPref = c.getSharedPreferences("game_fly", Activity.MODE_PRIVATE);
		      Editor ed = sPref.edit();
		      ed.putInt(SAVED_HIGHSCORE, highscore);
		      ed.commit();
		  }
	  }
		  
	  public void loadHighscores(Context c) 
	  {
		    sPref = c.getSharedPreferences("game_fly", Activity.MODE_PRIVATE);
		    savedHighscore = sPref.getInt(SAVED_HIGHSCORE, 0);
	  }
}
