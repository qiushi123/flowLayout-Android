#文字瀑布流和图片瀑布流的实现
	-用自定义的布局（流式布局）来实现瀑布流的效果

#先看效果图
##文字瀑布流


![文字](http://a3.qpic.cn/psb?/V13yyfT93I2jgl/r8UpnJkLA*1bhhhk2gi*qqaIVHmdHKOsDmPIWUVuOgM!/b/dBwBAAAAAAAA&bo=bAGGAmwBhgICDCg!&rf=viewer_4)
###文字瀑布流特点：
	可以实现textview控件的随机排布。每个textview的背景色都不同。
	每次点击后，圆形textview还会变为正方形。每次点击有个水波效果，
	并且每个textview被点击时背景色会会改变

##图片瀑布流
![图片](http://a3.qpic.cn/psb?/V13yyfT93I2jgl/MGnlWrU1ttEwnDzYOCrrheDIzlrIvbEwY0noyOHI1l4!/b/dKQAAAAAAAAA&bo=bAGGAmwBhgICDCg!&rf=viewer_4)
###图片瀑布流特点：
	可以实现Imageview控件的随机排布。每个Imageview的背景色都不同。
	每次点击后，圆形Imageview还会变为正方形。每次点击有个水波效果，
	并且每个Imageview被点击时背景色会会改变
	可以实现图片的随机错乱排布。


#使用步骤
##1 导包
	包flowLayout.jar包导入到你的项目中。

##2，代码示例
### 文字布局使用代码
    1，添加流式布局
    	scrollView = new ScrollView(this);//需要在流布局外面包裹一个scrollView
		flowLayout = new FlowTextLayout(this);
		int padding = 10;
		//设置流布局的上下左右padding值
		flowLayout.setPadding(padding, padding, padding, padding);
		flowLayout.setHorizontalSpacing(12);//水平间距
		flowLayout.setVerticalSpacing(12);
		scrollView.addView(flowLayout, new LayoutParams(
		     LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

	2，设置显示的数据，动态的往流布局中添加textview控件
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
	    					random.nextInt(8) + 80);
	    	GradientDrawable pressedDrawable = DrawableUtil
	    			.generateDrawable(
	    					ColorUtil
	    							.generateBeautifulColor(),
	    					random.nextInt(8) + 8);
	    
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
	
	3，设置好以后一定不要忘记把scrollView添加到我们的activity界面布局中
		如我这里是mainactivity的相对布局文件。
			rl = (RelativeLayout) findViewById(R.id.rl);		
			rl.addView(scrollView);

### 图片布局使用代码
	和文字的添加方式一样，这里就不再啰嗦。我有分享示例源码，大家可以参考示例
    