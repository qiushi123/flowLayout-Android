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
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ArrayList<String> list = new ArrayList<String>();

	private ScrollView scrollView;
	private FlowImageLayout flowLayout;
	private RelativeLayout rl;

	private int[] imags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rl = (RelativeLayout) findViewById(R.id.rl);
		initFlowLayout();
		rl.addView(scrollView);

		imags = new int[] { R.drawable.aa002, R.drawable.appicon,
				R.drawable.aa006, R.drawable.img_3, R.drawable.aa009,
				R.drawable.qq, R.drawable.aa010,
				R.drawable.bg01, R.drawable.img_1, R.drawable.bg01,
				R.drawable.img_4, R.drawable.bg01, R.drawable.img_2 };
		loadData();

	}

	private void initFlowLayout() {
		scrollView = new ScrollView(this);
		flowLayout = new FlowImageLayout(this);
		int padding = dip2px(12);
		int padding2 = dip2px(10);
		flowLayout.setPadding(padding2, padding, padding2, padding);
		flowLayout.setHorizontalSpacing(dip2px(12));
		flowLayout.setVerticalSpacing(dip2px(12));
		scrollView.addView(flowLayout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void loadData() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(300);

					runOnUiThread(new Runnable() {
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									for (int i = 0; i < imags.length; i++) {
										final ImageView imageView = new ImageView(
												MainActivity.this);
										imageView.setClickable(true);
										Random random = new Random();
										GradientDrawable normalDrawable = DrawableUtil
												.generateDrawable(
														ColorUtil
																.generateBeautifulColor(),
														random.nextInt(8) + 80);
										GradientDrawable pressedDrawable = DrawableUtil
												.generateDrawable(
														ColorUtil
																.generateBeautifulColor(),
														random.nextInt(8) + 8);

										imageView
												.setBackgroundDrawable(DrawableUtil
														.generateSelector(
																normalDrawable,
																pressedDrawable));
										imageView
												.setPadding(dip2px(5),
														dip2px(8), dip2px(5),
														dip2px(8));
										imageView.setImageResource(imags[i]);
										flowLayout.addView(imageView);
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

	/**
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
	 */
	public int dip2px(float dpValue) {
		final float scale = MainActivity.this.getResources()
				.getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
