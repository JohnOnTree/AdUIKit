package com.aidai.uikit.utils.watcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import com.aidai.uikit.R;
import com.aidai.uikit.utils.UIViewUtils;
import com.aidai.uikit.views.LabelDeleteEditText;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils.watcher
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/6/29 9:49
 */
public class InputNonNullDoubleWatcher implements TextWatcher {

	private Context mContext;
	private Button mButton;
	private LabelDeleteEditText mLbEtOne;
	private LabelDeleteEditText mLbEtTwo;

	public InputNonNullDoubleWatcher(Context context, LabelDeleteEditText lbEtOne, LabelDeleteEditText lbEtTwo, Button button) {
		this.mContext = context;
		this.mLbEtOne = lbEtOne;
		this.mLbEtTwo = lbEtTwo;
		this.mButton = button;

	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	}

	@Override
	public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		String str1 = mLbEtOne.getText();
		String str2 = mLbEtTwo.getText();

		if (TextUtils.isEmpty(str1)) {
			UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_gray);
			mButton.setClickable(false);
			return;
		}
		if (!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2)) {

			UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_red);
			mButton.setClickable(true);
		} else {
			UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_gray);
			mButton.setClickable(false);
		}
	}

	@Override
	public void afterTextChanged(Editable editable) {

	}
}
