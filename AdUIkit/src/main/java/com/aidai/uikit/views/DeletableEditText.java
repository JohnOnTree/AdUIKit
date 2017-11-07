package com.aidai.uikit.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.aidai.uikit.R;


/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: licai_v4
 * @Package com.aidai.uikit.views
 * @Description: 可删除的EditText
 * @date 2017/6/27 16:01
 */
@SuppressLint("AppCompatCustomView")
public class DeletableEditText extends LinearLayout implements ViewInitImpl {

	public Context mContext;
	private View mContent;

	public String mHintText = "";
	private int mHintColor = 0;
	private String mRightRes = "";
	private boolean mIsFocus;
	public int mInputType = InputType.TYPE_CLASS_TEXT;
	private int mMaxLength;
	private int mMaxLins;

	private EditText mEdittext;
	private FontTextView mTextView;

	private TextWatcher mWatcher;

	public DeletableEditText(Context context) {
		super(context);
		this.mContext = context;
		initView();
	}

	public DeletableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		initAttr(attrs);
		initView();
	}

	public DeletableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		initAttr(attrs);
		initView();
	}

	@Override
	public void initAttr(AttributeSet attrs) {
		TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.DeletableEditText);
		mHintColor = a.getColor(R.styleable.DeletableEditText_hintColor, 0);
		mHintText = a.getString(R.styleable.DeletableEditText_hintText);
		mRightRes = a.getString(R.styleable.DeletableEditText_rightResourse);
		mInputType = a.getInt(R.styleable.DeletableEditText_android_inputType, 0);
		mMaxLength = a.getInt(R.styleable.DeletableEditText_android_maxLength, 0);
		mMaxLins = a.getInt(R.styleable.DeletableEditText_android_maxEms, 1);
		a.recycle();
	}

	@Override
	public void initView() {
		mContent = LayoutInflater.from(mContext).inflate(R.layout.ui_layout_delete_edittext, this);
		mEdittext = (EditText) mContent.findViewById(R.id.et_input);
		if(!TextUtils.isEmpty(mHintText)){
			mEdittext.setHint(mHintText);
		}
//		mEdittext.setHintTextColor(mContent.getResources().getColor(mHintColor));
		mEdittext.setOnFocusChangeListener(new ClearFocusListener());
		mEdittext.addTextChangedListener(new ClearTextWatcher());
		mEdittext.setInputType(mInputType);
		setMaxLength(mMaxLength);
		mEdittext.setMaxLines(mMaxLins);

		mTextView = (FontTextView) mContent.findViewById(R.id.tv_clear);
		mTextView.setVisibility(View.GONE);
		mTextView.setOnClickListener(new ClrarLisener());

	}

	protected void setClearIconVisible(int visible) {
		if(mTextView != null){
			mTextView.setVisibility(visible);
		}
	}

	public void setInputType(int type){
		mEdittext.setInputType(type);
	}

	public void setTransformationMethod(TransformationMethod method){
		mEdittext.setTransformationMethod(method);
	}

	/**
	 * 获取输入文字
	 *
	 * @return
	 */
	public String getText(){
		String show = mEdittext.getText().toString().trim();
		if(TextUtils.isEmpty(show)){
			show = "";
		}
		return show;
	}

	public void setText(String input){
		mEdittext.setText(input);
	}

	public void setHintText(String hintText){
		mEdittext.setHint(hintText);
	}

	public void addTextWatcherListener(TextWatcher watcher){
		if(null != watcher){
			mEdittext.addTextChangedListener(watcher);
		}
	}

	public void setMaxLength(int length){
		if(length != 0){
			mEdittext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
		}
	}

	private class ClrarLisener implements OnClickListener{

		@Override
		public void onClick(View view) {
			if(mEdittext != null){
				mEdittext.setText("");
			}
		}
	}

	private class ClearFocusListener implements OnFocusChangeListener{

		@Override
		public void onFocusChange(View view, boolean b) {
			mIsFocus = b;
			if(b){
				if(mEdittext.getText().length() > 0){
					setClearIconVisible(View.VISIBLE);
				}else{
					setClearIconVisible(View.GONE);
				}
			}else{
				setClearIconVisible(View.GONE);
			}
		}
	}

	private class ClearTextWatcher implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

		}

		@Override
		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			if (mIsFocus) {
				if(charSequence.length() > 0 ){
					setClearIconVisible(View.VISIBLE);
				}else{
					setClearIconVisible(View.GONE);
				}
//				setClearIconVisible(charSequence.length() > 0 == true ? View.VISIBLE : View.GONE);
			}
		}

		@Override
		public void afterTextChanged(Editable editable) {

		}
	}

	public void setFilters(InputFilter[] filters){
		mEdittext.setFilters(filters);
	}

	public void removeTextWatcherListener(TextWatcher textWatcher){
		mEdittext.removeTextChangedListener(textWatcher);
	}

	public void setSelection(int index){
		mEdittext.setSelection(index);
	}

	public int getSelectionEnd(){
		return mEdittext.getSelectionEnd();
	}

	public void setEnable(boolean isEnable){
		mEdittext.setEnabled(isEnable);
	}

	public void setFocusable(boolean isEnable){
		mEdittext.setFocusable(isEnable);
	}

	public void setFocusableInTouchMode(boolean isEnable){
		mEdittext.setFocusableInTouchMode(isEnable);
	}
}
