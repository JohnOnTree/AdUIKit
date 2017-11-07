package com.aidai.uikit.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidai.uikit.R;

/**
 * description: 加载中的按钮
 * autour: ChristLu
 * date: 17/8/10
 * package: com.aidai.uikit.views
 */
public class LoadingButton extends RelativeLayout implements ViewInitImpl {
    private String btnStr;//按钮文字
    private int btnTextColor;//按钮文字颜色
    private int btnBgColor;//按钮背景颜色

    private TextView mTvConfirm;
    private ImageView mIvLoading;
    private RelativeLayout mRlBtn ;
    private View mView;


    private Animation anim ;

    private Context mContext ;


    public LoadingButton(Context context) {
        super(context);
        this.mContext = context ;
        initView();
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context ;
        initAttr(attrs);
        initView();
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context ;
        initAttr(attrs);
        initView();
    }


    @Override
    public void initAttr(AttributeSet attrs) {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.ui_layout_loading_btn, this, true);
        mTvConfirm = (TextView) mView.findViewById(R.id.tv_confirm);
        mIvLoading = (ImageView) mView.findViewById(R.id.iv_loading);
        mRlBtn = (RelativeLayout) mView.findViewById(R.id.rl_confirm);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingButton);
        btnStr = a.getString(R.styleable.LoadingButton_btnStr);
        btnTextColor = a.getIndex(R.styleable.LoadingButton_btnTextColor);
        btnBgColor = a.getIndex(R.styleable.LoadingButton_btnBgColor);
        a.recycle();
    }

    @Override
    public void initView() {

    }


    public void setViewOnClickListener(OnClickListener listener){
        mView.setOnClickListener(listener);
    }

    public void setBtnText(String text){
        mTvConfirm.setText(text);
    }

    public void setBtnTextColor(int textColor){
        mTvConfirm.setTextColor(textColor);
    }


    public void startLoading(){
        mIvLoading.setVisibility(VISIBLE);
        anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_loading);
        LinearInterpolator lir = new LinearInterpolator();
        anim.setInterpolator(lir);
        mIvLoading.startAnimation(anim);
    }

    public void endLoading(){
        if (anim != null) {
            mIvLoading.clearAnimation();
        }
        mIvLoading.setVisibility(GONE);
    }

    public void setBackground(Drawable background) {
        mRlBtn.setBackground(background);
    }

}
