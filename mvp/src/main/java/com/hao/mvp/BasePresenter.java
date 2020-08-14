package com.hao.mvp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Hao on 2020/5/7
 */
public abstract class BasePresenter<V extends IBaseView, M extends IBaseModel> implements IBasePresenter<V, M> {
    private Reference<Context> mContext;
    private Reference<V> mViewRef;
    private M mModel;

    public BasePresenter(Context context, V view) {
        mContext = new WeakReference<>(context);
        attachView(view);
        mModel = createModel();
    }

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @Override
    public Context getContext() {
        return null == mContext ? null : mContext.get();
    }

    @Override
    public V getView() {
        return null == mViewRef ? null : mViewRef.get();
    }

    @Override
    public M getModel() {
        return mModel;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @Override
    public void release() {
        detachView();
        mModel = null;
        if (mContext != null) {
            mContext.clear();
            mContext = null;
        }
    }


    public abstract M createModel();


    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }
}
