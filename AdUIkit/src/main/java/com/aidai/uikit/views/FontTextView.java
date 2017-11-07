package com.aidai.uikit.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * description:加载iconfont 文件
 * autour: ChristLu
 * date: 17/6/22
 * package: net.cl.com.pdf
 */
public class FontTextView extends TextView {


    public FontTextView(Context context) {
        super(context);
        init(context);
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        //设置字体图标
        if (!isInEditMode()) {
            Typeface font = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
            this.setTypeface(font);
        }
    }


}
