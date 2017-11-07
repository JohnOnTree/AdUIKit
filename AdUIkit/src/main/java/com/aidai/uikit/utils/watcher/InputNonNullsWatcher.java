package com.aidai.uikit.utils.watcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.aidai.uikit.R;
import com.aidai.uikit.utils.UIViewUtils;
import com.aidai.uikit.views.DeletableEditText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.utils.watcher
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/6/29 15:21
 */
public class InputNonNullsWatcher implements TextWatcher {

	private Button mButton;
	private Context mContext;
	private List<DeletableEditText> viewList;

	public InputNonNullsWatcher(Context context, List<DeletableEditText> viewList, Button button){
		this.mContext = context;
		this.viewList = viewList;
		this.mButton = button;
	}

	@Override
	public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	}

	@Override
	public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	}

	@Override
	public void afterTextChanged(Editable editable) {
		for(int i = 0; i < viewList.size(); i ++){
			if(viewList.get(i).getText().length() <= 0){
				UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_gray);
				mButton.setClickable(false);
				return;
			}else{
				UIViewUtils.setViewBg(mButton, mContext, R.drawable.ui_shape_button_red);
				mButton.setClickable(true);
			}
		}
	}
}
