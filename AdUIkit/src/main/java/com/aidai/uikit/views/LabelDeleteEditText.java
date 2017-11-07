package com.aidai.uikit.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidai.uikit.R;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.views
 * @Description: 带标签的输入控件
 * @date 2017/6/28 9:37
 */
public class LabelDeleteEditText extends DeletableEditText implements ViewInitImpl {

	private DeletableEditText mDelInput;
	private TextView mLabel;

	private View mContent;

	private String mHintText = "内容";
	private String mLabelText = "标签";
	private int mInputType = InputType.TYPE_CLASS_TEXT;
	private int mLabelLength = 2;

	public LabelDeleteEditText(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}

	public LabelDeleteEditText(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initAttr(attrs);
		initView();
	}

	public LabelDeleteEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		initAttr(attrs);
		initView();
	}

	@Override
	public void initAttr(AttributeSet attr) {
		TypedArray a = mContext.obtainStyledAttributes(attr, R.styleable.DeletableEditText);
		mLabelText = a.getString(R.styleable.DeletableEditText_labelText);
		mHintText = a.getString(R.styleable.DeletableEditText_hintText);
		mInputType = a.getInt(R.styleable.DeletableEditText_android_inputType, 0);
		mLabelLength = a.getInt(R.styleable.DeletableEditText_labelLength, 2);
		a.recycle();
	}

	public void setTransformationMethod(TransformationMethod method){
		mDelInput.setTransformationMethod(method);
	}

	public void setHintText(String text){
		mDelInput.setHintText(text);
	}

	@Override
	public void initView() {
		mContent = LayoutInflater.from(mContext).inflate(R.layout.ui_layout_delete_edittext_label, this);
		mLabel = (TextView) mContent.findViewById(R.id.tv_label);
//		mLabel.setFilters(new InputFilter[]{new InputFilter.m});
		mLabel.setMinEms(mLabelLength);
		if(!TextUtils.isEmpty(mLabelText)){
			mLabel.setText(mLabelText);
		}
		mDelInput = (DeletableEditText) mContent.findViewById(R.id.del_input);
		if(!TextUtils.isEmpty(mHintText)){
			mDelInput.setHintText(mHintText);
		}

		mDelInput.setInputType(mInputType);
	}

	public void setInputType(int type){
		mDelInput.setInputType(type);
	}

	public void setLabel(String label){
		mLabel.setText(label);
	}

	public void setText(String input){
		mDelInput.setText(input);
	}

	public String getText(){
		return mDelInput.getText();
	}

	public void addTextWatcherListener(TextWatcher watcher){
		if(watcher != null){
			mDelInput.addTextWatcherListener(watcher);
		}
	}

	public void removeTextWatcherListener(TextWatcher watcher){
		mDelInput.removeTextWatcherListener(watcher);
	}

	public void setFilters(InputFilter[] filters){
		mDelInput.setFilters(filters);
	}

	public void setSelection(int index){
		mDelInput.setSelection(index);
	}


	public int getSelectionEnd(){
		return mDelInput.getSelectionEnd();
	}

	public void setEnabled(boolean isEnable){
		mDelInput.setEnable(isEnable);
	}

	public void setFocusable(boolean isEnable){
		mDelInput.setFocusable(isEnable);
	}

	public void setLabelLength(int length){
		mLabel.setMinEms(length);
	}

}
