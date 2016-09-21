package com.dynamic.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
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
				
				Log.e(TAG, "name=" + activityName + "instance = " + instance);
				
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
		//得到view的名字
		int startIndex = path.lastIndexOf("/");
		int endIndex = path.lastIndexOf(".");
		String name = path.substring(startIndex + 1, endIndex);
		
		Log.e(TAG, "path=" + path+"===>>context=" + context+"name="+name);
		try {
			File dexOutputDir = context.getDir("dex", 0);
			final String dexOutputPath = dexOutputDir.getAbsolutePath();
			ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
			DexClassLoader dexClassLoader = new DexClassLoader(path, dexOutputPath,
					null, localClassLoader);
			Class<?> localClass = dexClassLoader.loadClass(name);
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
	/**
	 * 判断SD卡
	 * @return
	 */
	public static String hasSdCard() {
		String file_path = "";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			file_path = Environment.getExternalStorageDirectory() + "";
		} else {
			file_path = "/data/data/com.chinaums.mpos/files";
		}
		return file_path;
	}
	

	/**
	 * 资源文件拷贝
	 * @param context
	 * @param fileName
	 * @param path
	 * @return
	 */
	public static boolean copyApkFromAssets(Context context, String fileName, String path) {
	     boolean copyIsFinish = false;
	     Log.e(TAG,"fileName="+fileName+"path="+path);
	     try {
	       InputStream is = context.getAssets().open(fileName);
	       File file = new File(path);
	       file.createNewFile();
	       FileOutputStream fos = new FileOutputStream(file);
	       byte[] temp = new byte[1024];
	       int i = 0;
	       while ((i = is.read(temp)) > 0) {
	    	 Log.e(TAG,"i="+i);
	         fos.write(temp, 0, i);
	       }
	       fos.close();
	       is.close();
	       copyIsFinish = true;
	     } catch (IOException e) {
	       e.printStackTrace();
	     }
	     return copyIsFinish;
	   }

}
