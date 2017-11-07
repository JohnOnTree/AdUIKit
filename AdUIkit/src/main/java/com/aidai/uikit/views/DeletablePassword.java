package com.aidai.uikit.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
 * @date 2017/6/28 11:48
 */
public class DeletablePassword extends DeletableEditText implements ViewInitImpl {

	private View mContent;

	private LabelDeleteEditText mInput;
	private TextView mTvVisable;

	private TextWatcher mWatcher;
	private String mHintText = "";
	private int mHintColor = 0;
	private String mRightRes = "";
	private boolean mIsFocus;
	private int mInputType = InputType.TYPE_TEXT_VARIATION_PASSWORD;
	private boolean mIsVisable;
	private String mLabel;
	private boolean mIsPasswordShow = false;
	private int mLabelLength = 2;

	public DeletablePassword(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	public DeletablePassword(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initAttr(attrs);
		initView();
	}

	public DeletablePassword(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAttr(attrs);
		initView();
	}

	@Override
	public void initAttr(AttributeSet attrs) {
		TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.DeletableEditText);
		mIsVisable = a.getBoolean(R.styleable.DeletableEditText_isPassVisable, true);
		mHintText = a.getString(R.styleable.DeletableEditText_hintText);
		mLabel = a.getString(R.styleable.DeletableEditText_labelText);
		mInputType = a.getInt(R.styleable.DeletableEditText_android_inputType, 0);
		mLabelLength = a.getInt(R.styleable.DeletableEditText_labelLength, 2);
		a.recycle();
	}

	@Override
	public void initView() {
		mContent = LayoutInflater.from(mContext).inflate(R.layout.ui_layout_delete_edittext_password, this);
		mInput = (LabelDeleteEditText) mContent.findViewById(R.id.ldl_input);
		mInput.setLabelLength(mLabelLength);
		mInput.setInputType(mInputType);
		mInput.setHintText(mHintText);
		mInput.setLabel(mLabel);
		mTvVisable = (TextView) mContent.findViewById(R.id.tv_visable);
		mTvVisable.setOnClickListener(new PassVisableListener());
		setPassWordViewVisable(mIsVisable);
	}

	public void setInputType(int type){
		mInputType = type;
	}


	protected void setPassWordViewVisable(boolean isShow){
		if(isShow){
			mTvVisable.setVisibility(View.VISIBLE);
		}else{
			mTvVisable.setVisibility(View.GONE);
		}
	}

	/**
	 * 获取输入文字
	 *
	 * @return
	 */
	public String getText(){
		return mInput.getText();
	}

	public void setText(String input){
		mInput.setText(input);
	}

	public void setHintText(String hintText){
		mInput.setHintText(hintText);
	}

	public void addTextWatcherListener(TextWatcher watcher){
		if(null != watcher){
			mInput.addTextWatcherListener(watcher);
		}
	}

	private class PassVisableListener implements OnClickListener{

		@Override
		public void onClick(View view) {
			passViewVisable(mIsPasswordShow);
		}
	}

	private void passViewVisable(boolean isShow){
		if(isShow){
			mInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
			mIsPasswordShow = false;
			mTvVisable.setText(R.string.icon_fount_invisable);
		}else{
			mInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
			mIsPasswordShow = true;
			mTvVisable.setText(R.string.icon_fount_visable);
		}
	}

	public void removeTextWatcherListener(TextWatcher watcher){
		mInput.removeTextWatcherListener(watcher);
	}

	public void setFilters(InputFilter[] filters){
		mInput.setFilters(filters);
	}

	public void setSelection(int index){
		mInput.setSelection(index);
	}


	public int getSelectionEnd(){
		return mInput.getSelectionEnd();
	}

}
