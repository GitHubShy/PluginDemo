package com.dynamic.cilent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class BaseActivity extends Activity {

	protected Activity mA;
	private int mfrom = 0;
	public void setProxy(Activity activity) {
		this.mA = activity;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mfrom = savedInstanceState.getInt("from");
		}
		if (mfrom == 0) {
			super.onCreate(savedInstanceState);
			mA = this;
		}
	}
	@Override
	public void setContentView(int layoutResID) {
		if (mA == this) {
			super.setContentView(layoutResID);
		} else {
			mA.setContentView(layoutResID);
		}
	}
	@Override
	public void setContentView(View view) {
		if (mA == this) {
			super.setContentView(view);
		} else {
			mA.setContentView(view);
		}
	}
	@Override
	public void setContentView(View view, LayoutParams params) {
		if (mA == this) {
			super.setContentView(view, params);
		} else {
			mA.setContentView(view, params);
		}
			
	}
	@Override
	public void addContentView(View view, LayoutParams params) {
		if (mA == this) {
			// TODO Auto-generated method stub
			super.addContentView(view, params);
		} else {
			mA.addContentView(view, params);
		}
	}
	
}
