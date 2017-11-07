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

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: aidai_v2
 * @Package com.aidai.uikit.dialog
 * @Description: 单个按钮的对话框
 * @date 2017/8/18 9:21
 */
public class SingleNoticeDialog extends DialogFragment implements View.OnClickListener {

    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvOk;

    private String mTitle;
    private String mContent;
    private String mLeftText;

    private View mContentView;
    private LayoutInflater mInflater;
    private View.OnClickListener mOkListener;
    private Dialog mDialog;

    public static SingleNoticeDialog createDialog(String title, String content) {
        SingleNoticeDialog dialog = new SingleNoticeDialog();
        Bundle bundle = new Bundle();
        bundle.putString(UiConst.KEY_DIALOG_CONTENT, content);
        bundle.putString(UiConst.KEY_DIALOG_TITLE, title);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static SingleNoticeDialog createDialog(String title, String content, String leftText) {
        SingleNoticeDialog dialog = new SingleNoticeDialog();
        Bundle bundle = new Bundle();
        bundle.putString(UiConst.KEY_DIALOG_CONTENT, content);
        bundle.putString(UiConst.KEY_DIALOG_TITLE, title);
        bundle.putString(UiConst.KEY_DIALOG_LEFTTEXT, leftText);
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
        if (bundle == null) {
            return;
        }
        mContent = bundle.getString(UiConst.KEY_DIALOG_CONTENT) == null ? "提示"
                : bundle.getString(UiConst.KEY_DIALOG_CONTENT);
        mTitle = bundle.getString(UiConst.KEY_DIALOG_TITLE) == null ? "提示"
                : bundle.getString(UiConst.KEY_DIALOG_TITLE);
        mLeftText = bundle.getString(UiConst.KEY_DIALOG_LEFTTEXT) == null ? "确定"
                : bundle.getString(UiConst.KEY_DIALOG_LEFTTEXT);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mInflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mContentView = mInflater.inflate(R.layout.ui_dialog_sigle_notice, null);

        initView(mContentView);

        mTvTitle.setText(mTitle);
        mTvContent.setText(mContent);

        if (mOkListener != null) {
            mTvOk.setOnClickListener(mOkListener);
        } else {
            mTvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        mTvOk.setText(mLeftText);

        builder.setView(mContentView);

        mDialog = builder.create();
        return mDialog;
    }

    private void initView(View contentView) {
        mTvTitle = (TextView) contentView.findViewById(R.id.tv_dialog_title);
        mTvContent = (TextView) contentView.findViewById(R.id.tv_dialog_content);
        mTvOk = (TextView) contentView.findViewById(R.id.tv_ok);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        window.setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().setCanceledOnTouchOutside(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setOkClickListener(View.OnClickListener listener) {
        mOkListener = listener;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public void setOutDismiss(boolean outDismiss) {
        if (null != mDialog) {
            mDialog.setCanceledOnTouchOutside(outDismiss);
        }
    }
}
