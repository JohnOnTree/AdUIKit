package com.aidai.uikit.guide;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Title: AnimDemo
 * @Package com.aidai.animdemo
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/7/27 14:58
 */
public class RotateTransor implements ViewPager.PageTransformer {

	private static final float ROT_MAX = 30.0f;
	private float mRot;

	@Override
	public void transformPage(View page, float position) {
		if (position < -1) { // [-Infinity,-1)
			// This page is way off-screen to the left.
			page.setRotation(0);

		} else if (position <= 1) { // [-1,1]
			// Modify the default slide transition to shrink the page as well
			if (position < 0) {

				mRot = (ROT_MAX * position);
				page.setPivotX(page.getMeasuredWidth() * 0.5f);
				page.setPivotY(page.getMeasuredHeight() * 0.7f);
				page.setRotation(mRot);

			} else {

				mRot = (ROT_MAX * position);
				page.setPivotX(page.getMeasuredWidth() * 0.5f);
				page.setPivotY(page.getMeasuredHeight() * 0.7f);
				page.setRotation(mRot);
			}

			// Scale the page down (between MIN_SCALE and 1)

			// Fade the page relative to its size.

		} else { // (1,+Infinity]
			// This page is way off-screen to the right.
			page.setRotation(0);
		}
	}

}
