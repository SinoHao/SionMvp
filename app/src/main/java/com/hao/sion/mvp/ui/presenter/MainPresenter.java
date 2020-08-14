package com.hao.sion.mvp.ui.presenter;

import android.content.Context;

import com.hao.mvp.BasePresenter;
import com.hao.sion.mvp.ui.model.IMainModel;
import com.hao.sion.mvp.ui.model.MainModel;
import com.hao.sion.mvp.ui.view.IMainView;

/**
 * Created by Hao on 2020/8/14
 */
public class MainPresenter extends BasePresenter<IMainView, IMainModel> implements IMainPresenter {

    public MainPresenter(Context context, IMainView view) {
        super(context, view);
    }

    @Override
    public IMainModel createModel() {
        return new MainModel();
    }

    @Override
    public void showToast(String message) {
        getView().showToast(message);
    }
}
