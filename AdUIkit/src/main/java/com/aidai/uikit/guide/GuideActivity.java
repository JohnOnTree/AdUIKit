package com.aidai.uikit.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aidai.uikit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: aidai_online
 * @Package com.aidai.uikit.guide
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/31 9:58
 */
public class GuideActivity extends AppCompatActivity{

	private List<Integer> mGuideList;

	private ViewPager mViewPager;
	private BaseFragmentAdapter mAdapter;
	private OnTransSelectListener mListener;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_activity_guide);
		initView();
		initData();
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.vp_content);
	}

	private void initData() {
		mGuideList = new ArrayList<>();

		List<android.support.v4.app.Fragment> fragmentList = new ArrayList<>();

//		for(int i = 0; i < mGuideList.size(); i++){
//			GuideFragment fragment = GuideFragment.createFragment(mGuideList.get(i));
//			fragmentList.add(fragment);
//		}

		mAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList);
		mViewPager.setAdapter(mAdapter);
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				if(null != mListener){
					if(position == 2){
						mListener.onPageSelect(positionOffset);
					}
				}
			}

			@Override
			public void onPageSelected(int position) {
				if(null != mListener){
					if(position == 3){
						mListener.onPageSelect(1);
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		mViewPager.setPageTransformer(false, new RotateTransor());
	}

	public void setOnTransSelectListener(OnTransSelectListener listener){
		mListener = listener;
	}

	public interface OnTransSelectListener{
		public void onPageSelect(float offset);
	}
}
