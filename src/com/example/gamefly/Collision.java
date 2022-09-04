package com.example.gamefly;

import java.util.List;

public class Collision {
	
	public boolean CollisionBulletAndEnemy(BulletHero bull, EnemyAircraft enemy)
	{
		if (bull.window_x+bull.window_width >= enemy.window_x && bull.window_x <= enemy.window_x+enemy.window_width && bull.window_y+bull.window_height >=enemy.window_y && bull.window_y <= enemy.window_y+enemy.window_height)
     	 { 
    		 return true;
     	 }
     	 return false;
	}
	
	public boolean CollisionHeroAndEnemy(HeroAircraft hero, EnemyAircraft enemy)
	{
		if (hero.window_x+hero.window_width >= enemy.window_x && hero.window_x <= enemy.window_x+enemy.window_width && hero.window_y+hero.window_height >=enemy.window_y && hero.window_y <= enemy.window_y+enemy.window_height)
      	 { 
     		 return true;
      	 }
      	 return false;
	}
	
	public boolean CollisionHeroAndJerrycan(HeroAircraft hero, Jerrycan jerrycan)
	{
		if (hero.window_x+hero.window_width >= jerrycan.window_x && hero.window_x <= jerrycan.window_x+jerrycan.window_width && hero.window_y+hero.window_height >=jerrycan.window_y && hero.window_y <= jerrycan.window_y+jerrycan.window_height)
       	 { 
      		 return true;
       	 }
       	 return false;
	}
	
	public boolean CollisionBulletAndHero(HeroAircraft hero, BulletEnemy bull)
	{
		if (hero.window_x+hero.window_width >= bull.window_x && hero.window_x <= bull.window_x+bull.window_width && hero.window_y+hero.window_height >=bull.window_y && hero.window_y <= bull.window_y+bull.window_height)
       	 { 
      		 return true;
       	 }
       	 return false;
	}
	
	public boolean CollisionHeroAndArsenal(HeroAircraft hero, Arsenal arsenal)
	{
		if (hero.window_x+hero.window_width >= arsenal.window_x && hero.window_x <= arsenal.window_x+arsenal.window_width && hero.window_y+hero.window_height >=arsenal.window_y && hero.window_y <= arsenal.window_y+arsenal.window_height)
      	 { 
     		 return true;
      	 }
      	 return false;
	}

}
