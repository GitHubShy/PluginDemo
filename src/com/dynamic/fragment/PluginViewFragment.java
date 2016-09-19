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
import android.widget.Toast;

public class PluginViewFragment extends Fragment {
	public String mPluginViewJarPath;
	private RelativeLayout mRl;
	private View mZipView;

	public PluginViewFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPluginViewJarPath = getActivity().getResources().getString(
				R.string.PLUGIN_VIEW_JAR_PATH);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRl = (RelativeLayout) inflater.inflate(R.layout.fragment_main,
				container, false);
		mRl.findViewById(R.id.button2).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Object instance = DynamicUtils.launchView(
								mPluginViewJarPath, getActivity());
						if (instance != null) {
							mZipView = (View) instance;
							RelativeLayout.LayoutParams relLayoutParams = new RelativeLayout.LayoutParams(
									200, 200);
							relLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
							mZipView.setLayoutParams(relLayoutParams);
							mRl.addView(mZipView);
							Toast.makeText(getActivity(), "from sdcard view("+mPluginViewJarPath+")", 5000).show();
						}
					}
				});
		return mRl;
	}

}
