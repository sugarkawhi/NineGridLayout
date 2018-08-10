package me.sugarkawhi.ninegridlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhzy
 * @Description 九宫格布局 1-9 布局
 * Created by zhzy on 2018/8/9.
 */
public class NineGridLayout extends ViewGroup {
    private static final String TAG = "NineGridLayout";
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
        setChildSize();
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mHeight = calHeight(mWidth);
        setMeasuredDimension(mWidth, mHeight);
    }


    /**
     * 设置子View的大小
     */
    private void setChildSize() {
        int childCount = getChildCount();
        switch (childCount) {
            case 1:
            case 4:
            case 7:
                measure147Children();
                break;
            case 2:
            case 5:
            case 8:
                measure258Children();
                break;
            case 3:
            case 6:
            case 9:
                measure369Children();
                break;
        }
    }

    private void measure147Children() {
        int oneWidth = mWidth;
        int oneHeight = mOneChildHeight;
        int threeSize = (mWidth - mGridSpace * 2) / 3;
        View firstChild = getChildAt(0);
        LayoutParams firstLp = firstChild.getLayoutParams();
        firstLp.width = oneWidth;
        firstLp.height = oneHeight;
        firstChild.setLayoutParams(firstLp);
        for (int i = 1; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            lp.width = threeSize;
            lp.height = threeSize;
            child.setLayoutParams(lp);
        }
    }

    private void measure258Children() {
        int twoSize = (mWidth - mGridSpace) / 2;
        int threeSize = (mWidth - mGridSpace * 2) / 3;
        for (int i = 0; i < 2; i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            lp.width = twoSize;
            lp.height = twoSize;
            child.setLayoutParams(lp);
        }
        for (int i = 2; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            lp.width = threeSize;
            lp.height = threeSize;
            child.setLayoutParams(lp);
        }
    }

    private void measure369Children() {
        int threeSize = (mWidth - mGridSpace * 2) / 3;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            lp.width = threeSize;
            lp.height = threeSize;
            child.setLayoutParams(lp);
        }
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
                View child = getChildAt(0);
                LayoutParams lp = child.getLayoutParams();
                lp.width = mWidth;
                lp.height = mOneChildHeight;
                child.setLayoutParams(lp);
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
            case 4:
            case 7:
                layoutOneAndFourAndSevenChildren();
                break;
            case 2:
            case 5:
            case 8:
                layoutTwoAndFiveAndEightChildren();
                break;
            case 3:
            case 6:
            case 9:
                layoutThreeMultipleChildren();
                break;
        }
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
     * 布局1/4/7个子View
     */
    private void layoutOneAndFourAndSevenChildren() {
        View firstChild = getChildAt(0);
        firstChild.layout(0, 0, mWidth, mOneChildHeight);
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
            child.layout(left, top, right, bottom);
        }
    }

    /**
     * 布局2/5/8个子View
     */
    private void layoutTwoAndFiveAndEightChildren() {
        int top2singleSize = (mWidth - mGridSpace) / 2;//前两个单个的尺寸
        for (int i = 0; i < 2; i++) {
            View child = getChildAt(i);
            int left = i * (top2singleSize + mGridSpace);
            int top = 0;
            int right = left + top2singleSize;
            int bottom = top + top2singleSize;
            child.layout(left, top, right, bottom);
        }
        int singleSize = (mWidth - mGridSpace * 2) / 3;//单个的尺寸
        int childCount = getChildCount();
        for (int i = 2; i < childCount; i++) {
            int rowCount = (i - 2) / 3;//行
            int columnCount = (i - 2) % 3;//列
            View child = getChildAt(i);
            int left = columnCount * (singleSize + mGridSpace);
            int top = top2singleSize + mGridSpace + rowCount * (singleSize + mGridSpace);
            int right = left + singleSize;
            int bottom = top + singleSize;
            child.layout(left, top, right, bottom);
            LayoutParams lp = child.getLayoutParams();
            lp.width = singleSize;
            lp.height = singleSize;
            child.setLayoutParams(lp);
        }

    }

    public void setNineGridAdapter(NineGridAdapter nineGridAdapter) {
        if (nineGridAdapter == null) return;
        //Log.e(TAG, "setNineGridAdapter " + System.currentTimeMillis());
        mNineGridAdapter = nineGridAdapter;
        removeAllViews();
        int childCount = getChildCount();
        int count = mNineGridAdapter.getItemCount();
        if (childCount > count) {
            removeViews(count, childCount - count);
        } else if (childCount < count) {
            for (int i = childCount; i < count; i++) {
                View view = getItemView(i);
                addView(view);
            }
        }
        for (int i = 0; i < count; i++) {
            mNineGridAdapter.bindView(getChildAt(i), i);
        }
//        int count = mNineGridAdapter.getItemCount();
//        int childCount = getChildCount();
//        if (childCount > count) {
//            removeViews(count, childCount - count);
//        } else if (childCount < count) {
//            for (int i = childCount; i < count; i++) {
//                View view = getItemView(i);
//                addView(view);
//            }
//        }
//        if (mNineGridAdapter != null) {
//            for (int i = 0; i < childCount; i++) {
//                Log.e(TAG, "childCount = " + childCount + " bindView > " + i);
//                mNineGridAdapter.bindView(getChildAt(i), i);
//            }
//        }
//        requestLayout();
    }

    public NineGridAdapter getNineGridAdapter() {
        return mNineGridAdapter;
    }


    /**
     * 获得 ImageView
     * 保证了 ImageView 的重用
     *
     * @param position 位置
     */
    private View getItemView(final int position) {
        if (mNineGridAdapter == null) {
            Log.e(TAG, "Your must set a NineGridAdapter ");
            return null;
        }
        View view = mNineGridAdapter.getItemView(this, position);
        return view;
    }
}
