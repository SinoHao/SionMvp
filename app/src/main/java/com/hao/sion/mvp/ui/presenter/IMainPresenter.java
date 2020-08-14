package com.hao.sion.mvp.ui.presenter;

import com.hao.mvp.IBasePresenter;
import com.hao.sion.mvp.ui.model.IMainModel;
import com.hao.sion.mvp.ui.view.IMainView;

/**
 * Created by Hao on 2020/8/14
 */
public interface IMainPresenter extends IBasePresenter<IMainView, IMainModel> {

    void showToast(String message);
}
