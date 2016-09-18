package com.dynamic.server;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class DynamicUtils {
	public static final String TAG = "DynamicUtils";
	
	public static void launchTargetActivity(String path, Context context) {
		Log.e(TAG, "path=" + path+"===>>context=" + context);
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageArchiveInfo(path, 1);
			Log.e(TAG, "packageInfo=" + packageInfo.applicationInfo.publicSourceDir);
			
			if ((packageInfo.activities != null)
					&& (packageInfo.activities.length > 0)) {
				
				for (ActivityInfo name : packageInfo.activities) {
					Log.e(TAG,"name="+name);
				}
				
				String activityName = packageInfo.activities[0].name;
				
				File dexOutputDir = context.getDir("dex", 0);
				final String dexOutputPath = dexOutputDir.getAbsolutePath();
				ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
				DexClassLoader dexClassLoader = new DexClassLoader(path, dexOutputPath,
						null, localClassLoader);
				Class<?> localClass = dexClassLoader.loadClass(activityName);
				Constructor<?> localConstructor = localClass
						.getConstructor(new Class[] {});
				Object instance = localConstructor.newInstance(new Object[] {});
				
				Log.e(TAG, "instance = " + instance);
				
				Method setProxy = localClass.getMethod("setProxy",
						new Class[] { Activity.class });
				setProxy.setAccessible(true);
				setProxy.invoke(instance, new Object[] { context });
				
				Method onCreate = localClass.getDeclaredMethod("onCreate",
						new Class[] { Bundle.class });
				onCreate.setAccessible(true);
				Bundle bundle = new Bundle();
				bundle.putInt("from", 1);
				onCreate.invoke(instance, new Object[] { bundle });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object launchView(String path, Context context) {
		Log.e(TAG, "path=" + path+"===>>context=" + context);
		try {
			File dexOutputDir = context.getDir("dex", 0);
			final String dexOutputPath = dexOutputDir.getAbsolutePath();
			ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
			DexClassLoader dexClassLoader = new DexClassLoader(path, dexOutputPath,
					null, localClassLoader);
			Class<?> localClass = dexClassLoader.loadClass("com.dynamic.cilent.JarView");
			Constructor<?> localConstructor = localClass
					.getConstructor(new Class[] {Context.class});
			Object instance = localConstructor.newInstance(context);
//			Class instance = null;            
//			instance = dexClassLoader.loadClass("com.dynamic.cilent.ZipView");
//			PluginInteface loader = (PluginInteface)instance.newInstance();
			Log.e(TAG, "instance = " + instance);
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static PackageInfo getPackageInfo (String path, Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageArchiveInfo(path, 1);
			 
			/* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */  
			packageInfo.applicationInfo.sourceDir = path;  
			packageInfo.applicationInfo.publicSourceDir = path;  
			Log.e(TAG, "packageInfo=" + packageInfo.applicationInfo.publicSourceDir);
			
			return packageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
