package com.aidai.uikit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.aidai.uikit.R;
import com.aidai.uikit.utils.UiConst;

import org.w3c.dom.Text;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.dialog
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/6/27 15:28
 */
public class CommonNoticeDialog extends DialogFragment{

	private TextView mTvTitle;
	private TextView mTvContent;
	private TextView mTvCancel;
	private TextView mTvOk;

	private String mTitle;
	private String mContent;
	private String mLeftText;
	private String mRightText;

	private View mContentView;
	private LayoutInflater mInflater;
	private View.OnClickListener mLeftListener;
	private View.OnClickListener mRightListener;

	public static CommonNoticeDialog createDialog(String title, String content){
		CommonNoticeDialog dialog = new CommonNoticeDialog();
		Bundle bundle = new Bundle();
		bundle.putString(UiConst.KEY_DIALOG_CONTENT, content);
		bundle.putString(UiConst.KEY_DIALOG_TITLE, title);
		dialog.setArguments(bundle);
		return dialog;
	}

	public static CommonNoticeDialog createDialog(String title, String content, String leftText, String rightText){
		CommonNoticeDialog dialog = new CommonNoticeDialog();
		Bundle bundle = new Bundle();
		bundle.putString(UiConst.KEY_DIALOG_CONTENT, content);
		bundle.putString(UiConst.KEY_DIALOG_TITLE, title);
		bundle.putString(UiConst.KEY_DIALOG_LEFTTEXT, leftText);
		bundle.putString(UiConst.KEY_DIALOG_RIGHTTEXT, rightText);
		dialog.setArguments(bundle);
		return dialog;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawableResource(R.drawable.ui_shape_dialog_bg);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if(bundle == null ){
			return;
		}
		mContent = bundle.getString(UiConst.KEY_DIALOG_CONTENT) == null ? "提示"
			:bundle.getString(UiConst.KEY_DIALOG_CONTENT);
		mTitle = bundle.getString(UiConst.KEY_DIALOG_TITLE) == null ? "提示"
			: bundle.getString(UiConst.KEY_DIALOG_TITLE);
		mLeftText = bundle.getString(UiConst.KEY_DIALOG_LEFTTEXT) == null ? "取消"
			: bundle.getString(UiConst.KEY_DIALOG_LEFTTEXT);
		mRightText = bundle.getString(UiConst.KEY_DIALOG_RIGHTTEXT) == null ? "确定"
			: bundle.getString(UiConst.KEY_DIALOG_RIGHTTEXT);
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mInflater = getActivity().getLayoutInflater();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		mContentView = mInflater.inflate(R.layout.ui_dialog_notice, null);

		initView(mContentView);

		mTvTitle.setText(mTitle);
		mTvContent.setText(mContent);

		mTvCancel.setOnClickListener(mLeftListener);
		mTvCancel.setText(mLeftText);
		mTvOk.setOnClickListener(mRightListener);
		mTvOk.setText(mRightText);

		builder.setView(mContentView);

		return builder.create();
	}

	private void initView(View contentView) {
		mTvTitle = (TextView) contentView.findViewById(R.id.tv_dialog_title);
		mTvContent = (TextView) contentView.findViewById(R.id.tv_dialog_content);
		mTvCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
		mTvOk = (TextView) contentView.findViewById(R.id.tv_ok);
	}

	@Override
	public void onStart() {
		super.onStart();

		Window window = getDialog().getWindow();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		window.setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	public void setLeftClickListener(View.OnClickListener listener){
		mLeftListener = listener;
	}

	public void setRightClickListener(View.OnClickListener listener){
		mRightListener = listener;
	}

}
