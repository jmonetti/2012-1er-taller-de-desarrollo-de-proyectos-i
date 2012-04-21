package com.seguridad;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private List<Integer> resources;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.resources = new LinkedList<Integer>();
        this.resources.add(R.drawable.red_button);
        this.resources.add(R.drawable.red_button_on);
    }
    
    public void sendEmergencyCall(View view)
    {
    	Toast.makeText(this, R.string.sending_emergency_call, Toast.LENGTH_SHORT).show();
    	ImageButton btnEmergency = (ImageButton)view;
    	btnEmergency.setClickable(false);
    	btnEmergency.startAnimation(new BlinkAnimation(btnEmergency,  this.resources, 500));
    }
    
    public class BlinkAnimation extends Animation implements AnimationListener {
    	private ImageButton imgButton;
    	private List<Integer> drawableResources;
    	private int duration;
    	private int currentIndex = 0;
		
		public BlinkAnimation(View view, List<Integer> drawableResources, int duration) {
			this(view);
			this.drawableResources = drawableResources;
			this.duration = duration;
			this.setRepeatCount(this.drawableResources.size() * 50);
			this.setDuration(this.duration);
		}
    	
    	public BlinkAnimation(View view) {
    		this.imgButton = (ImageButton) view;
    		this.setInterpolator(new AccelerateInterpolator());
    		this.setAnimationListener(this);
    	}

		public void onAnimationEnd(Animation animation) {
			
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
}
