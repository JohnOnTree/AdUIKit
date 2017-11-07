package com.aidai.uikit.utils.watcher;

import android.content.Context;
import android.support.v7.widget.ViewUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aidai.uikit.R;
import com.aidai.uikit.utils.UIViewUtils;
import com.aidai.uikit.views.DeletablePassword;
import com.aidai.uikit.views.LabelDeleteEditText;

import java.util.HashMap;
import java.util.List;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils.watcher
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/6/28 14:56
 */
public class InputNonNullWatcer implements TextWatcher{

	private Context mContext;
	private Button mButton;
	private LabelDeleteEditText mLbEt;
	private DeletablePassword mPass;

	public InputNonNullWatcer(Context context, LabelDeleteEditText lbEt, DeletablePassword pass, Button button){
		this.mContext = context;
		this.mLbEt = lbEt;
		this.mPass = pass;
		this.mButton = button;

	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	}

	@Override
	public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		String str1 = mLbEt.getText();
		String str2 = mPass.getText();
		if(TextUtils.isEmpty(str1)){
			UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_gray);
			mButton.setClickable(false);
			return;
		}

		if(!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2)){
			UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_red);
			mButton.setClickable(true);
		}else{
			UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_gray);
			mButton.setClickable(false);
		}
	}

	@Override
	public void afterTextChanged(Editable editable) {

	}
}
