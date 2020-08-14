package com.hao.sion.mvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hao.frame.base.SuperBaseActivity;
import com.hao.mvp.IBasePresenter;
import com.hao.mvp.IBaseView;
import com.hao.sion.mvp.R;
import com.hao.sion.mvp.control.BaseControl;

import butterknife.ButterKnife;


/**
 * Created by Hao on 2020/8/13
 */
@SuppressWarnings("all")
public abstract class BaseActivity<V extends IBaseView, P extends IBasePresenter> extends SuperBaseActivity {

    private P mPresenter;

    private long mLastToastTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        getLifecycle().addObserver(mPresenter);

    }

    @Override
    public void setContentView(int layoutResID) {
        View baseView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(params);

        RelativeLayout container = baseView.findViewById(R.id.rl_container);

        container.addView(childView);

        setContentView(baseView);

        ButterKnife.bind(this, baseView);

        initView();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLoadingViewId() {
        return R.layout.layout_loading_view;
    }

    @Override
    protected int getErrorViewId() {
        return R.layout.layout_loading_error;
    }

    @NonNull
    protected abstract P createPresenter();

    @NonNull
    protected abstract V createView();

    @NonNull
    protected P getPresenter() {
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
            Toast.makeText(this, message, length).show();
            mLastToastTime = current;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.release();
            mPresenter = null;
        }
    }
}
