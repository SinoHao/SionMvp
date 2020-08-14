package com.hao.sion.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hao.frame.base.SuperBaseFragment;
import com.hao.mvp.IBasePresenter;
import com.hao.mvp.IBaseView;
import com.hao.sion.mvp.R;
import com.hao.sion.mvp.control.BaseControl;

import butterknife.ButterKnife;

/**
 * Created by Hao on 2020/8/13
 */
@SuppressWarnings("all")
public abstract class BaseFragment<V extends IBaseView, P extends IBasePresenter> extends SuperBaseFragment {

    private long mLastToastTime;

    private V mView;
    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void setContentView(int layoutResID) {

        View baseView = LayoutInflater.from(mContext).inflate(R.layout.activity_base_fragment, null);
        View childView = LayoutInflater.from(mContext).inflate(layoutResID, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(params);

        RelativeLayout container = baseView.findViewById(R.id.rl_container);

        container.addView(childView);

        setContentView(baseView);

        ButterKnife.bind(this, baseView);

        initView();
    }

    @Override
    protected int setLoadingViewId() {
        return R.layout.layout_loading_view;
    }

    @Override
    protected int setErrorViewId() {
        return R.layout.layout_loading_error;
    }


    @NonNull
    protected abstract P createPresenter();

    @NonNull
    protected abstract V createView();

    public P getPresenter() {
        if (null == mPresenter) {
            if (null == mView) {
                mView = createView();
            }
            mPresenter = createPresenter();
            if (null != mPresenter) {
                mPresenter.attachView(mView);
            }
        }
        return mPresenter;
    }


    protected void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    protected void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    private void showToast(String message, int length) {
        long current = System.currentTimeMillis();
        if (current - mLastToastTime > BaseControl.TOAST_SHORT_INTERVAL) {
            Toast.makeText(mContext, message, length).show();
            mLastToastTime = current;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != mPresenter) {
            mPresenter.release();
        }
        mPresenter = null;
    }
}
