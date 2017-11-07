package com.aidai.uikit.update;

import java.io.File;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.update
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/4 9:21
 */
public interface UpdateListener {
	public void onDownloadExecute(File downloadFile);
	public void onProgressUpdata(int value);
}
