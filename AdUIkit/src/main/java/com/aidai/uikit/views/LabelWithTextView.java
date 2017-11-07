package com.aidai.uikit.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/6/28 16:31
 */
public class LabelWithTextView extends DeletableEditText implements ViewInitImpl {

	private FontTextView mLeftIcon;
	private FontTextView mRightIcon;
	private TextView mTvLabel;
	private TextView mTvContent;

	private View mContent;

	private String mIconLeft;
	private String mIconRight;
	private String mLabel;
	private String mArea;
	private boolean mLeftVisable;
	private boolean mRightVisable;

	public LabelWithTextView(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}

	public LabelWithTextView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initAttr(attrs);
		initView();
	}

	public LabelWithTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		initAttr(attrs);
		initView();
	}

	@Override
	public void initAttr(AttributeSet attrs) {
		TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.DeletableEditText);
		mIconLeft = a.getString(R.styleable.DeletableEditText_leftIcon);
		mIconRight = a.getString(R.styleable.DeletableEditText_rightIcon);
		mLabel = a.getString(R.styleable.DeletableEditText_labelText);
		mArea = a.getString(R.styleable.DeletableEditText_area);
		mLeftVisable = a.getBoolean(R.styleable.DeletableEditText_leftVisable, false);
		mRightVisable = a.getBoolean(R.styleable.DeletableEditText_rightVisable, true);
		a.recycle();
	}

	@Override
	public void initView() {
		if(TextUtils.isEmpty(mIconRight)){
			mIconRight = mContext.getResources().getString(R.string.icon_fount_right);
		}

		mContent = LayoutInflater.from(mContext).inflate(R.layout.ui_layout_label_text, this);
		mTvLabel = (TextView) mContent.findViewById(R.id.tv_label);
		mTvContent = (TextView) mContent.findViewById(R.id.tv_content);
		mRightIcon = (FontTextView) mContent.findViewById(R.id.tv_right_icon);
		mLeftIcon = (FontTextView) mContent.findViewById(R.id.tv_icon);

		mTvLabel.setText(mLabel);
		mTvContent.setText(mArea);
		if(mLeftVisable){
			mLeftIcon.setVisibility(View.VISIBLE);
			mLeftIcon.setText(mIconLeft);
		}else{
			mLeftIcon.setVisibility(View.GONE);
		}
		if(mRightVisable){
			mRightIcon.setVisibility(View.VISIBLE);
			mRightIcon.setText(mIconRight);
		}else{
			mRightIcon.setVisibility(View.GONE);
		}
	}

	public void setLabel(String text){
		if(TextUtils.isEmpty(text)){
			mTvLabel.setText("");
		}else{
			mTvLabel.setText(text);
		}
	}

	public void setContent(String text){
		if(TextUtils.isEmpty(text)){
			mTvContent.setText("");
		}else{
			mTvContent.setText(text);
		}
	}

	public void setRightIcon(String text){
		if(TextUtils.isEmpty(text)){
			mRightIcon.setText(R.string.icon_fount_right);
		}else{
			mTvContent.setText(text);
		}
	}

	public void setleftIcon(String text){
		if(TextUtils.isEmpty(text)){
			mLeftIcon.setText(R.string.icon_fount_right);
		}else{
			mLeftIcon.setText(text);
		}
	}

	public String getText(){
		return mTvContent.getText().toString().trim();
	}

	public void addTextWatcherListener(TextWatcher watcher){
		if(watcher != null){
			mTvContent.addTextChangedListener(watcher);
		}
	}

	public void setLeftVisable(int visable){
		mLeftIcon.setVisibility(VISIBLE);
	}

	public void setRightVisable(int visable){
		mRightIcon.setVisibility(VISIBLE);
	}
}
