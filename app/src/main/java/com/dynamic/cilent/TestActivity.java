package com.dynamic.cilent;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TestActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(generateContentView(mA));
	}


	private View generateContentView(final Context context) {
		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		layout.setBackgroundColor(Color.parseColor("#F79AB5"));
		Button button = new Button(context);
		button.setText("button");
		layout.addView(button, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
//		button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, "you clicked button",
//						Toast.LENGTH_SHORT).show();
//				startActivityByProxy("com.ryg.dynamicloadclient.TestActivity");
//			}
//		});
		return layout;
	}

}
