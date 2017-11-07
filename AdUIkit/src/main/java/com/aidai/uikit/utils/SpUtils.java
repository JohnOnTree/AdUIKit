package com.aidai.uikit.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/4 11:21
 */
public class SpUtils {

	private SpUtils(){

	}

	public static void saveUpdate(Context context, int isDownload, String version) {
		SharedPreferences preferences = context.getSharedPreferences("key_download", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("key_isdownload", isDownload);
		editor.putString("key_version", version);
		editor.commit();
	}

	public static String getVersion(Context context) {
		SharedPreferences preferences = context.getSharedPreferences("key_download", Context.MODE_PRIVATE);
		return preferences.getString("key_version", "");
	}

	public static int getIsDownload(Context context) {
		SharedPreferences preferences = context.getSharedPreferences("key_download", Context.MODE_PRIVATE);
		return preferences.getInt("key_isdownload", 0);
	}
}
