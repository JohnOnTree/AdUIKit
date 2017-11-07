package com.aidai.uikit.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.aidai.uikit.R;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: aidai_online
 * @Package com.aidai.uikit.guide
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/31 10:01
 */
public class GuideFragment extends Fragment implements GuideActivity.OnTransSelectListener {

	private ImageView mIvPic;
	private int mRes;
	private Button mBtnSkip;
	private View.OnClickListener mListener;

	public static GuideFragment creat(int resId){
		Bundle bundle = new Bundle();
		bundle.putInt("trans_id", resId);
		GuideFragment fragment = new GuideFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getArguments();
		if(bundle != null){
			mRes = bundle.getInt("trans_id");
		}
		GuideActivity activity = (GuideActivity) getActivity();
		activity.setOnTransSelectListener(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.ui_activity_guide, null);
		return v;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view) {
		mIvPic = (ImageView) view.findViewById(R.id.iv_pic);
		mIvPic.setBackgroundResource(mRes);
		mBtnSkip = (Button) view.findViewById(R.id.btn_skip);
		if(null != mBtnSkip){
			mBtnSkip.setOnClickListener(mListener);
		}

	}

	@Override
	public void onPageSelect(float offict) {
		if(null != mBtnSkip){
			mBtnSkip.setAlpha(offict);
			mBtnSkip.setTranslationY(-(300 * offict));
		}
	}

	public void setOnClickListener(View.OnClickListener listener){
		mListener = listener;
	}
}
