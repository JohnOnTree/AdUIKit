package com.aidai.uikit.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidai.uikit.R;

/**
 * description:
 * autour: ChristLu
 * date: 17/7/3
 * package: com.aidai.uikit.views
 */
public class NoticeTextView extends RelativeLayout implements ViewInitImpl{
    private TextView mTvNotice;
    private String mNotice ;
    private LinearLayout mLlContent;

    public NoticeTextView(Context context) {
        super(context);
        initView();
    }

    public NoticeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
        initView();
    }

    public NoticeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(attrs);
        initView();
    }



    @Override
    public void initAttr(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ui_layout_notice, this,true);
        mTvNotice = (TextView) view.findViewById(R.id.tv_notice);
        mLlContent = (LinearLayout) view.findViewById(R.id.ll_Cungaun_tishi);
          TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NoticeTextView);
        mNotice = a.getString(R.styleable.NoticeTextView_notice);
        a.recycle();
    }

    @Override
    public void initView() {
        //添加布局文件,获取子View
        mTvNotice.setText(mNotice);
    }

    public void setOnClickListener(OnClickListener listener){
        mLlContent.setOnClickListener(listener);
    }


}
