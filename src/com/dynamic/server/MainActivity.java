package com.dynamic.server;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dynamicapk.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		public static final String url = "/mnt/sdcard/uxin/a.apk";
		public static final String inUrl = "/mnt/sdcard/uxin/ZipView.zip";
		public static final String outuUrl = "/mnt/sdcard/uxin/";
		public String ViewUrl = "/mnt/sdcard/uxin/b.jar";
		private RelativeLayout mRl;
		private ImageView mIv;
		private TextView mTv;
		private View mZipView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mRl = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container,
					false);
			mRl.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					DynamicUtils.launchTargetActivity(url, getActivity());
				}
			});
			mRl.findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//					try {
//						ZipUtils.UnZipFolder(inUrl, outuUrl);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					mZipView = new JarView(getActivity());
//					mZipView.setBackgroundColor(color.black);
//					RelativeLayout.LayoutParams relLayoutParams=new RelativeLayout.LayoutParams(100,100);
//					mZipView.setLayoutParams(relLayoutParams);
//					TextView tv = new TextView(getActivity());
//					tv.setText("Hello World");
//					mRl.addView(mZipView);
					Object instance = DynamicUtils.launchView(ViewUrl, getActivity());
					mZipView = (View) instance;
					RelativeLayout.LayoutParams relLayoutParams=new RelativeLayout.LayoutParams(100,100);
					mZipView.setLayoutParams(relLayoutParams);
					mRl.addView(mZipView);
				}
			});
			mIv = (ImageView) mRl.findViewById(R.id.img);
			mTv = (TextView) mRl.findViewById(R.id.title);
			PackageInfo packageInfo = DynamicUtils.getPackageInfo(url, getActivity());
//			mIv.setImageResource(packageInfo.applicationInfo.icon);
			mIv.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(packageInfo.applicationInfo));
			mTv.setText(getActivity().getPackageManager().getApplicationLabel(packageInfo.applicationInfo));
			return mRl;
		}

	}

}
