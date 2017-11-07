package com.aidai.uikit.utils;

import android.os.Environment;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils
 * @Description: UI中的相关常量
 * @date 2017/6/27 15:40
 */
public class UiConst {

	public static String KEY_DIALOG_TITLE = "key_dialog_title";
	public static String KEY_DIALOG_CONTENT = "key_dialog_content";
	public static String KEY_DIALOG_LEFTTEXT = "key_dialog_lefttext";
	public static String KEY_DIALOG_RIGHTTEXT = "key_dialog_righttext";
	public static String KEY_DOWNLOAD_VERSION = "key_download_version";
	public static String KEY_DOWNLOAD_CONTENT = "key_download_content";
	public static String KEY_DOWNLOAD_URL = "key_download_url";
	public static String KEY_DOWNLOAD_STATE = "key_download_state";

	public static String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getPath() + "/update";
	public static String DOWNLOAD_NAME = Environment.getExternalStorageDirectory().getPath() + "/update/updata.apk";
}
