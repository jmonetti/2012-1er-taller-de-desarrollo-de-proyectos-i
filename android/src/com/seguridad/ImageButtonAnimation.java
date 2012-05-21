package com.seguridad;

import java.util.List;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;

public class ImageButtonAnimation extends Animation implements AnimationListener {
	private ImageButton imgButton;
	private List<Integer> drawableResources;
	private int duration;
	private int currentIndex = 0;
	
	public ImageButtonAnimation(View view, List<Integer> drawableResources, int duration) {
		this(view);
		this.drawableResources = drawableResources;
		this.duration = duration;
		this.setRepeatCount(this.drawableResources.size());
		this.setDuration(this.duration);
	}
	
	public ImageButtonAnimation(View view) {
		this.imgButton = (ImageButton) view;
		this.setInterpolator(new AccelerateInterpolator());
		this.setAnimationListener(this);
	}

	public void onAnimationEnd(Animation animation) {
		this.imgButton.startAnimation(this);
	}

	public void onAnimationRepeat(Animation animation) {
		this.imgButton.setImageResource(this.drawableResources.get(this.getNextIndex()));
	}

	public void onAnimationStart(Animation animation) {
		this.currentIndex = 0;
	}
	
	private int getNextIndex() {
		if(++this.currentIndex >= this.drawableResources.size())
			this.currentIndex = 0;

		return this.currentIndex;
	}
}
