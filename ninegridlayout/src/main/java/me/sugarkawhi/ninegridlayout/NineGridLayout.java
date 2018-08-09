package me.sugarkawhi.ninegridlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhzy
 * @Description 九宫格布局 1-9 布局
 * Created by zhzy on 2018/8/9.
 */
public class NineGridLayout extends ViewGroup {

    //宽
    private int mWidth;
    //高
    private int mHeight;
    //间距大小
    private int mGridSpace;
    //一个子View时最小高度
    private int mOneChildHeight;
    //适配器
    private NineGridAdapter mNineGridAdapter;

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        mGridSpace = array.getDimensionPixelSize(R.styleable.NineGridLayout_ngl_gridSpace, 20);
        mOneChildHeight = array.getDimensionPixelSize(R.styleable.NineGridLayout_ngl_oneChildHeight, 400);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                mWidth = 500;
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }
        mHeight = calHeight(mWidth);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }


    /**
     * 根据宽度计算高度
     *
     * @param width 高度
     * @return height
     */
    private int calHeight(int width) {
        int height = 0;
        int childCount = getChildCount();
        //根据子View的个数&间距 来确定高度
        switch (childCount) {
            case 0:
                height = 0;
                break;
            case 1:
                height = mOneChildHeight;
                break;
            case 2:
                height = (width - mGridSpace) / 2;
                break;
            case 3:
                height = (width - 2 * mGridSpace) / 3;
                break;
            case 4:
                height = mOneChildHeight + mGridSpace + (width - 2 * mGridSpace) / 3;
                break;
            case 5:
                height = (width - mGridSpace) / 2 + mGridSpace + (width - 2 * mGridSpace) / 3;
                break;
            case 6:
                height = (width - 2 * mGridSpace) / 3 * 2 + mGridSpace;
                break;
            case 7:
                height = mOneChildHeight + (width - 2 * mGridSpace) / 3 * 2 + mGridSpace * 2;
                break;
            case 8:
                height = (width - mGridSpace) / 2 + (width - 2 * mGridSpace) / 3 * 2 + mGridSpace * 2;
                break;
            case 9:
                height = width;
                break;
        }
        return height;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChildren();
    }

    /**
     * 布局
     */
    private void layoutChildren() {
        int childCount = getChildCount();
        switch (childCount) {
            case 1:
                layoutOneChild();
                break;
            case 2:
                layoutTwoChildren();
                break;
            case 3:
            case 6:
            case 9:
                layoutThreeMultipleChildren();
                break;
            case 4:
            case 7:
                layoutFourAndSevenChildren();
                break;
            case 5:
            case 8:
                layoutFiveAndEightChildren();
                break;
        }
    }

    /**
     * 只有一个子View的时候的布局
     */
    private void layoutOneChild() {
        View child = getChildAt(0);
        LayoutParams lp = child.getLayoutParams();
        lp.width = mWidth;
        lp.height = mOneChildHeight;
        child.setLayoutParams(lp);
        child.layout(0, 0, mWidth, mOneChildHeight);
    }

    /**
     * 布局两个子View
     */
    private void layoutTwoChildren() {
        int singleSize = (mWidth - mGridSpace) / 2;//单个的尺寸
        View child1 = getChildAt(0);
        LayoutParams lp = child1.getLayoutParams();
        lp.width = singleSize;
        lp.height = singleSize;
        child1.setLayoutParams(lp);
        child1.layout(0, 0, singleSize, singleSize);
        View child2 = getChildAt(1);
        child2.setLayoutParams(lp);
        child2.layout(singleSize + mGridSpace, 0, mWidth, singleSize);
    }

    /**
     * 布局3、6、9（3的倍数） 个子View
     */
    private void layoutThreeMultipleChildren() {
        int singleSize = (mWidth - mGridSpace * 2) / 3;//单个的尺寸
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int rowCount = i / 3;//行
            int columnCount = i % 3;//列
            View child = getChildAt(i);
            int left = columnCount * (singleSize + mGridSpace);
            int top = rowCount * (singleSize + mGridSpace);
            int right = left + singleSize;
            int bottom = top + singleSize;
            LayoutParams lp = child.getLayoutParams();
            lp.width = singleSize;
            lp.height = singleSize;
            child.setLayoutParams(lp);
            child.layout(left, top, right, bottom);
        }
    }

    /**
     * 布局4/7个子View
     */
    private void layoutFourAndSevenChildren() {
        layoutOneChild();
        int singleSize = (mWidth - mGridSpace * 2) / 3;//单个的尺寸
        int childCount = getChildCount();
        for (int i = 1; i < childCount; i++) {
            int rowCount = (i - 1) / 3;//行
            int columnCount = (i - 1) % 3;//列
            View child = getChildAt(i);
            int left = columnCount * (singleSize + mGridSpace);
            int top = mOneChildHeight + mGridSpace + rowCount * (singleSize + mGridSpace);
            int right = left + singleSize;
            int bottom = top + singleSize;
            LayoutParams lp = child.getLayoutParams();
            lp.width = singleSize;
            lp.height = singleSize;
            child.setLayoutParams(lp);
            child.layout(left, top, right, bottom);
        }
    }

    /**
     * 布局5/8个子View
     */
    private void layoutFiveAndEightChildren() {
        layoutTwoChildren();
        int top2Size = (mWidth - mGridSpace) / 2;//前两个子View的尺寸
        int singleSize = (mWidth - mGridSpace * 2) / 3;//单个的尺寸
        int childCount = getChildCount();
        for (int i = 2; i < childCount; i++) {
            int rowCount = (i - 2) / 3;//行
            int columnCount = (i - 2) % 3;//列
            View child = getChildAt(i);
            int left = columnCount * (singleSize + mGridSpace);
            int top = top2Size + mGridSpace + rowCount * (singleSize + mGridSpace);
            int right = left + singleSize;
            int bottom = top + singleSize;
            LayoutParams lp = child.getLayoutParams();
            lp.width = singleSize;
            lp.height = singleSize;
            child.setLayoutParams(lp);
            child.layout(left, top, right, bottom);
        }
    }


    public void setNineGridAdapter(NineGridAdapter nineGridAdapter) {
        mNineGridAdapter = nineGridAdapter;
        int count = nineGridAdapter.getItemCount();
        removeAllViews();
        for (int i = 0; i < count; i++) {
            View view = nineGridAdapter.getItemView(this, i);
            mNineGridAdapter.bindView(view, i);
            addView(view, generateDefaultLayoutParams());
        }
        requestLayout();
    }
}
