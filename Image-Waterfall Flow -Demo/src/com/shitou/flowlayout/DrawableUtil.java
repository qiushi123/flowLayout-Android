package com.shitou.flowlayout;

import java.util.Random;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtil {
	/**
	 * 生成一个图片
	 * @param argb
	 * @param radius
	 * @return
	 */
	public static GradientDrawable generateDrawable(int argb,int radius){
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
		gradientDrawable.setColor(argb);
		gradientDrawable.setCornerRadius(radius);
		return gradientDrawable;
	}
	
	/**
	 * 生成一个状态选择器
	 * @param normal
	 * @param pressed
	 * @return
	 */
	public static StateListDrawable generateSelector(Drawable normal,Drawable pressed){
		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
		stateListDrawable.addState(new int[]{}, normal);
		
		return stateListDrawable;
	}
}
