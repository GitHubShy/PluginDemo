package com.dynamic.fragment;

import com.dynamic.server.DynamicUtils;
import com.shy.plugin.R;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PluginAPKFragment extends Fragment {
	public String mPluginApkPath;
	private RelativeLayout mRl;
	private ImageView mIv;
	private TextView mTv;

	public PluginAPKFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPluginApkPath = getActivity().getResources().getString(R.string.PLUGIN_APK_PATH);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRl = (RelativeLayout) inflater.inflate(R.layout.fragment_apk_main,
				container, false);
		mRl.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DynamicUtils.launchTargetActivity(mPluginApkPath, getActivity());
			}
		});
		mIv = (ImageView) mRl.findViewById(R.id.img);
		mTv = (TextView) mRl.findViewById(R.id.title);
		PackageInfo packageInfo = DynamicUtils.getPackageInfo(mPluginApkPath, getActivity());
//		mIv.setImageResource(packageInfo.applicationInfo.icon);
		if (packageInfo != null) {
			mIv.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(packageInfo.applicationInfo));
			mTv.setText(getActivity().getPackageManager().getApplicationLabel(packageInfo.applicationInfo));
		}
		return mRl;
	}

}
