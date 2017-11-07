package com.aidai.uikit.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import java.io.File;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/6/28 14:55
 */
public class UIViewUtils {

	public static void setViewBg(View view, Context ctx, int resId) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackground(ctx.getResources().getDrawable(
				resId));
		} else {
			view.setBackgroundDrawable(ctx.getResources()
				.getDrawable(resId));
		}
	}

	////打开APK程序代码
	public static void openFile(File file, Context context) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
			"application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
