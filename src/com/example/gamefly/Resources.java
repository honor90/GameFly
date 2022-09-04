package com.example.gamefly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Resources {
	
	public Bitmap airplane;
	public Bitmap enemyaircraft;
	public Bitmap button_up;
	public Bitmap button_down;
	public Bitmap button_fire;      
	public Bitmap bullet;
	public Bitmap map;
	public Bitmap sound_on;
	public Bitmap sound_off;
	public Bitmap panel;
	public Bitmap numbers;
	public Bitmap button_menu;
	public Bitmap texture;
	public Bitmap bang;
	public Bitmap button_pause;
	public Bitmap fuel;
	public Bitmap numbers_bullet;
	public Bitmap jerrycan;
	public Bitmap arsenal;
	public Bitmap fuel_down;
	public Bitmap fire;
	public Bitmap smoke;
	public Bitmap main_menu;
	public Bitmap main_menu_button_play;
	public Bitmap main_menu_button_highscores;
	public Bitmap main_menu_button_help;
	public Bitmap main_menu_button_exit;
	public Bitmap highscore_menu_button_back;
	public Bitmap numbers_highscore;
	public Bitmap menu_pause;
	public Bitmap menu_pause_button_play;
	public Bitmap menu_pause_button_replay;
	public Bitmap menu_pause_button_home;
	public Bitmap screen_defeat;
	
	public Resources(Context context)
	{
        airplane = BitmapFactory.decodeResource(context.getResources(), R.drawable.airplane);
        button_up = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_up);
        button_down = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_down);
        button_fire = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_fire);
        bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);  
        map = BitmapFactory.decodeResource(context.getResources(), R.drawable.maps_1);
        sound_on = BitmapFactory.decodeResource(context.getResources(), R.drawable.sound_on);
        sound_off = BitmapFactory.decodeResource(context.getResources(), R.drawable.sound_off);
        panel = BitmapFactory.decodeResource(context.getResources(), R.drawable.panel);
        numbers = BitmapFactory.decodeResource(context.getResources(), R.drawable.numbers);
        button_menu = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_menu);
        enemyaircraft = BitmapFactory.decodeResource(context.getResources(), R.drawable.aircraft_1);
        bang = BitmapFactory.decodeResource(context.getResources(), R.drawable.bang);
        texture = BitmapFactory.decodeResource(context.getResources(), R.drawable.p);
        button_pause = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_pause);
        fuel = BitmapFactory.decodeResource(context.getResources(), R.drawable.fuel);
        numbers_bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.numbers_bullet);
        jerrycan = BitmapFactory.decodeResource(context.getResources(), R.drawable.jerrycan);
        arsenal = BitmapFactory.decodeResource(context.getResources(), R.drawable.arsenal);
        fuel_down = BitmapFactory.decodeResource(context.getResources(), R.drawable.fuel_down);
        fire = BitmapFactory.decodeResource(context.getResources(), R.drawable.fire);
        smoke = BitmapFactory.decodeResource(context.getResources(), R.drawable.smoke);
        main_menu = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu);
        main_menu_button_play = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_button_play);
        main_menu_button_highscores = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_button_highscores);
        main_menu_button_help = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_button_help);
        highscore_menu_button_back = BitmapFactory.decodeResource(context.getResources(), R.drawable.highscore_menu_button_back);
        numbers_highscore = BitmapFactory.decodeResource(context.getResources(), R.drawable.number_highscore);
        menu_pause = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_2);
        menu_pause_button_play = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_play);
        menu_pause_button_replay = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_replay);
        menu_pause_button_home = BitmapFactory.decodeResource(context.getResources(), R.drawable.button_menu);
        main_menu_button_exit = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_button_exit);
        screen_defeat = BitmapFactory.decodeResource(context.getResources(), R.drawable.screen_defeat);
	}

}
