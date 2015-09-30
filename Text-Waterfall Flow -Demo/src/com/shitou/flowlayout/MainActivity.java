package com.shitou.flowlayout;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> hotList = new ArrayList<String>();

	private ScrollView scrollView;
	private FlowTextLayout flowLayout;
	private RelativeLayout rl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rl = (RelativeLayout) findViewById(R.id.rl);
		initFlowLayout();
		rl.addView(scrollView);

		// 模拟流式布局要显示的数据

		for (int j = 0; j < 10; j++) {
			hotList.add("控件" + j);
		}
		list.addAll(hotList);

		hotList.clear();
		for (int j = 0; j < 10; j++) {
			hotList.add("长的子控件" + j);
		}
		list.addAll(hotList);
		
		hotList.clear();
		for (int j = 0; j < 11; j++) {
			hotList.add("控件" + j);
		}
		list.addAll(hotList);

		hotList.clear();
		for (int j = 0; j < 3; j++) {
			hotList.add("很长很长的子控件" + j);
		}
		list.addAll(hotList);

		loadData();

	}

	private void initFlowLayout() {
		scrollView = new ScrollView(this);
		flowLayout = new FlowTextLayout(this);
		int padding = dip2px(10);
		flowLayout.setPadding(padding, padding, padding, padding);
		flowLayout.setHorizontalSpacing(dip2px(12));
		flowLayout.setVerticalSpacing(dip2px(12));
		scrollView.addView(flowLayout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void loadData() {
		if (hotList != null && hotList.size() > 0) {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(300);

						runOnUiThread(new Runnable() {
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										for (int i = 0; i < list.size(); i++) {
											final TextView textView = new TextView(
													MainActivity.this);
											textView.setTextColor(Color.WHITE);
											textView.setGravity(Gravity.CENTER);
											Random random = new Random();
											textView.setTextSize(
													TypedValue.COMPLEX_UNIT_SP,
													dip2px(9));
											GradientDrawable normalDrawable = DrawableUtil
													.generateDrawable(
															ColorUtil
																	.generateBeautifulColor(),
																	random.nextInt(8)+80);
											GradientDrawable pressedDrawable = DrawableUtil
													.generateDrawable(
															Color.parseColor("#aaaaaa"),
															random.nextInt(8)+8);

											textView.setBackgroundDrawable(DrawableUtil
													.generateSelector(
															normalDrawable,
															pressedDrawable));
											textView.setPadding(dip2px(12),
													dip2px(5), dip2px(12),
													dip2px(5));
											textView.setText(list.get(i));
											flowLayout.addView(textView);

											textView.setOnClickListener(new OnClickListener() {
												@Override
												public void onClick(View v) {
													showToast(textView
															.getText()
															.toString());
												}
											});
										}
									}
								});
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();

		} 
	}

	/**
	 * 强大的可以连续图的toast
	 */
	private static Toast toast;

	public void showToast(String text) {
		if (toast == null) {
			toast = Toast.makeText(MainActivity.this, text, 0);
		}
		toast.setText(text);
		toast.show();
	}

	/**
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
	 */
	public int dip2px(float dpValue) {
		final float scale = MainActivity.this.getResources()
				.getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
