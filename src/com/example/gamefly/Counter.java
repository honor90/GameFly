package com.example.gamefly;

public class Counter {
	
	private Panel panel;
	
	public Counter(Panel panel)
	{
		this.panel = panel;
	}
	
	public boolean heroCounter()
	{
		if((panel.count_live1 == 0 && panel.count_live2 == 0 && panel.count_live3 == 0) || panel.count_led_fuel_down == 12)
		{
			return true;
		}
		return false;
	}

}
