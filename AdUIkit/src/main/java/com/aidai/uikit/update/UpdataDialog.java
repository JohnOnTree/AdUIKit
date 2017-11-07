package com.aidai.uikit.update;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidai.uikit.R;
import com.aidai.uikit.utils.SpUtils;
import com.aidai.uikit.utils.UIViewUtils;
import com.aidai.uikit.utils.UiConst;

import java.io.File;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.dialog
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/3 15:32
 */
public class UpdataDialog extends DialogFragment implements DialogInterface.OnKeyListener {

	private View mContentView;
	private LayoutInflater mInflater;

	private TextView mTvVersion;
	private TextView mTvContent;
	private TextView mTvBackground;
	private ProgressBar mProgressBar;
	private Button mCancel;
	private Button mDownload;
	private TextView mTvPercent;
	private LinearLayout mLlButton;
	private RelativeLayout mRlProgress;

	private String mVersion;
	private String mContent;
	private String mDownloadUrl;
	private String mState;

	private UpdataService mService;
	private ServiceConnection mConnection;
	private File mFile = new File(UiConst.DOWNLOAD_NAME);
	private View.OnClickListener mListener;
	private View.OnClickListener mBackListener;

	public static UpdataDialog createDialog(String version, String content, String downloadUrl, String state) {
		UpdataDialog dialog = new UpdataDialog();
		Bundle bundle = new Bundle();
		bundle.putString(UiConst.KEY_DOWNLOAD_VERSION, version);
		bundle.putString(UiConst.KEY_DOWNLOAD_CONTENT, content);
		bundle.putString(UiConst.KEY_DOWNLOAD_URL, downloadUrl);
		bundle.putString(UiConst.KEY_DOWNLOAD_STATE, state);
		dialog.setArguments(bundle);
		return dialog;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawableResource(R.drawable.ui_shape_dialog_bg);
		getDialog().setOnKeyListener(this);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if (bundle == null) {
			return;
		}
		mVersion = bundle.getString(UiConst.KEY_DOWNLOAD_VERSION) == null ? "" : bundle.getString(UiConst.KEY_DOWNLOAD_VERSION);
		mContent = bundle.getString(UiConst.KEY_DOWNLOAD_CONTENT) == null ? "" : bundle.getString(UiConst.KEY_DOWNLOAD_CONTENT);
		mDownloadUrl = bundle.getString(UiConst.KEY_DOWNLOAD_URL) == null ? "" : bundle.getString(UiConst.KEY_DOWNLOAD_URL);
		mState = bundle.getString(UiConst.KEY_DOWNLOAD_STATE) == null ? "" : bundle.getString(UiConst.KEY_DOWNLOAD_STATE);
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mInflater = getActivity().getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		mContentView = mInflater.inflate(R.layout.ui_download_dialog, null);
		initView(mContentView);
		initService();
		mTvVersion.setText(mVersion);
		mTvContent.setText(Html.fromHtml(mContent));
		checkState();
		checkVersion();

		mDownload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String key = mDownload.getText().toString().trim();
				if ("立即安装".equals(key)) {
					if (null != mFile) {
						UIViewUtils.openFile(mFile, getContext());
					}
				} else if ("立即升级".equals(key)) {
					mService.DownLoad(mDownloadUrl, mVersion);
					mLlButton.setVisibility(View.GONE);
					mRlProgress.setVisibility(View.VISIBLE);
				}
			}
		});

		mCancel.setOnClickListener(mListener);


		mTvBackground.setOnClickListener(mBackListener);

		builder.setView(mContentView);
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}

	private void checkVersion() {
		String nowVersion = SpUtils.getVersion(getContext());
		int nowIsDown = SpUtils.getIsDownload(getContext());
		if (TextUtils.isEmpty(nowVersion)) {
			mDownload.setText("立即升级");
		} else {
			if (nowIsDown == 1) {
				if (null != mFile) {
					mDownload.setText("立即安装");
				} else {
					mDownload.setText("立即升级");
				}
			} else {
				mDownload.setText("立即升级");
			}
		}
	}

	private void checkState() {
		if (mState.equals("1")) {
			mCancel.setVisibility(View.GONE);
		} else {
			mCancel.setVisibility(View.VISIBLE);
		}
	}

	private void initView(View contentView) {
		mTvVersion = (TextView) contentView.findViewById(R.id.tv_version);
		mTvContent = (TextView) contentView.findViewById(R.id.tv_content);
		mTvBackground = (TextView) contentView.findViewById(R.id.tv_back);
		mProgressBar = (ProgressBar) contentView.findViewById(R.id.ui_download_progress);
		mCancel = (Button) contentView.findViewById(R.id.btn_cancel);
		mDownload = (Button) contentView.findViewById(R.id.btn_updata);
		mTvPercent = (TextView) contentView.findViewById(R.id.tv_percent);
		mLlButton = (LinearLayout) contentView.findViewById(R.id.ll_button);
		mRlProgress = (RelativeLayout) contentView.findViewById(R.id.rl_progress);
	}

	@Override
	public void onStart() {
		super.onStart();

		Window window = getDialog().getWindow();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		window.setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);

	}

	private void initService() {
		//启动service,定时发实时位置
		mConnection = new ServiceConnection() {
			public void onServiceConnected(ComponentName className, IBinder localBinder) {
				mService = ((UpdataService.UpdataBinder) localBinder).getService();
				mService.setOnUpdateListener(new UpdateListener() {

					@Override
					public void onDownloadExecute(File downloadFile) {
						//下载完成
					}

					@Override
					public void onProgressUpdata(int value) {
						if (value > 0 && value < 100) {
							//下载中
							mLlButton.setVisibility(View.GONE);
							mRlProgress.setVisibility(View.VISIBLE);
							mProgressBar.setProgress(value);
							mTvPercent.setText(value + "%");
						} else if (value == 100) {
							mLlButton.setVisibility(View.VISIBLE);
							mRlProgress.setVisibility(View.GONE);
							mCancel.setVisibility(View.GONE);
							mDownload.setText("立即安装");
						}
					}
				});
			}

			public void onServiceDisconnected(ComponentName arg0) {
				mService = null;
			}
		};
		Intent intent = new Intent(getActivity(), UpdataService.class);
		getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			dismiss();
			return true;
		} else {
			//这里注意当不是返回键时需将事件扩散，否则无法处理其他点击事件
			return false;
		}
	}

	public void setOnCancelListener(View.OnClickListener listener) {
		this.mListener = listener;
	}

	public void setOnBackListener(View.OnClickListener listener) {
		this.mBackListener = listener;
	}
}
