package com.dynamic.server;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dynamic.fragment.PluginAPKFragment;
import com.dynamic.fragment.PluginViewFragment;
import com.shy.plugin.R;

public class MainActivity extends Activity {
	private RadioGroup mRadioGroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mRadioGroup = (RadioGroup) findViewById(R.id.uirg_tabb);
		initListener();
		RadioButton childAt = (RadioButton) mRadioGroup.getChildAt(0);
        childAt.setChecked(true);
	}
	protected void initListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.uirb_ride:
                    	getFragmentManager().beginTransaction().replace(R.id.container,  new PluginViewFragment()).commit();
                        break;
                    case R.id.uirb_car:
                    	getFragmentManager().beginTransaction().replace(R.id.container,  new PluginAPKFragment()).commit();
                        break;
                    case R.id.uirb_normal:
                        break;
                }
            }
        });
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
