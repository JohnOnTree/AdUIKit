package com.aidai.uikit.update;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import com.aidai.uikit.R;
import com.aidai.uikit.utils.SpUtils;
import com.aidai.uikit.utils.UiConst;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/4 9:15
 */
public class UpdataService extends Service {

	private NotificationManager nm;
	private File file;
	private UpdateListener mListener;
	private String mVersion = "";

	@Override
	public void onCreate() {
		super.onCreate();
		nm = (NotificationManager) getApplication().getSystemService(Activity.NOTIFICATION_SERVICE);
	}

	public void DownLoad(String url, String version) {
		new DownloadTask().execute(url);
		mVersion = version;
	}

	public class DownloadTask extends AsyncTask<String, Integer, File> {

		@Override
		protected File doInBackground(String... params) {
			final String fileName = "updata.apk";
			String downloadPath = UiConst.DOWNLOAD_PATH;
			String downloadName = UiConst.DOWNLOAD_NAME;
			File tmpFile = new File(downloadPath);
			if (!tmpFile.exists()) {
				tmpFile.mkdir();
			}
			file = new File(downloadName);

			HttpURLConnection con = null;
			InputStream is = null;
			OutputStream os = null;
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				URL url = new URL(params[0]);
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5 * 1000);  //设置超时时间
				if (con.getResponseCode() == 200) { //判断是否连接成功
					int fileLength = con.getContentLength();
					is = con.getInputStream();    //获取输入
					os = new FileOutputStream(file);
					byte[] buffer = new byte[1024 * 1024 * 10];
					long total = 0;
					int count;
					while ((count = is.read(buffer)) != -1) {
						total += count;
						if (fileLength > 0) {
							publishProgress((int) (total * 100 / fileLength));  //传递进度（注意顺序）
						}
						os.write(buffer, 0, count);
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
				file = null;
			} catch (IOException e) {
				e.printStackTrace();
				file = null;
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (con != null) {
					con.disconnect();
				}
			}
			return file;
		}

		@Override
		protected void onPostExecute(File result) {
			super.onPostExecute(result);
			if(null != mListener){
				mListener.onDownloadExecute(result);
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			if(null != mListener){
				mListener.onProgressUpdata(values[0]);
			}
			super.onProgressUpdate(values);

			NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
				.setProgress(100, values[0], false)
				.setSmallIcon(R.mipmap.icon_aidai)
				.setContentInfo("下载中...")
				.setContentTitle("正在下载");
			Notification nf = builder.build();
			nm.notify(0, nf);
			if (values[0] == 100) {
				//下载完成后点击安装
				SpUtils.saveUpdate(getApplicationContext(), 1, mVersion);
				Intent it = new Intent(Intent.ACTION_VIEW);
				it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				it.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
				PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
				builder.setContentTitle("下载完成").setContentText("点击安装").setContentIntent(pendingIntent);
				nf = builder.build();
				nm.notify(0, nf);
			}
		}
	}

	public void setOnUpdateListener(UpdateListener listener){
		mListener = listener;
	}

	/**
	 * 返回一个Binder对象
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return new UpdataBinder();
	}

	public class UpdataBinder extends Binder {
		/**
		 * 获取当前Service的实例
		 *
		 * @return
		 */
		public UpdataService getService() {
			return UpdataService.this;
		}
	}
}
