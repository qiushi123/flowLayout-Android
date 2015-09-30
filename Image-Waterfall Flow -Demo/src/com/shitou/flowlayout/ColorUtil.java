package com.shitou.flowlayout;

import java.util.Random;

import android.graphics.Color;

public class ColorUtil {
	public static int generateBeautifulColor(){
		Random random = new Random();
		
		int red = 30+random.nextInt(200);
		int green = 30+random.nextInt(200);
		int blue = 30+random.nextInt(200);
		return Color.rgb(red, green, blue);
	}
}
