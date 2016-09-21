package com.dynamic.cilent;

import android.content.Context;
import android.util.AttributeSet;

public class JarView extends PluginBaseView implements ViewInterface{

	public JarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public JarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public JarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sayHi() {
		// TODO Auto-generated method stub
		return "hi";
	}

}
