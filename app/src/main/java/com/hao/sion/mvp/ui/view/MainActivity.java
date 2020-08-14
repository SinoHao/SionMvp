package com.hao.sion.mvp.ui.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hao.sion.mvp.R;
import com.hao.sion.mvp.base.BaseActivity;
import com.hao.sion.mvp.ui.presenter.IMainPresenter;
import com.hao.sion.mvp.ui.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IMainView, IMainPresenter> implements IMainView {

    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        super.initView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().showToast("你好啊");
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    protected IMainPresenter createPresenter() {
        return new MainPresenter(this, this);
    }

    @NonNull
    @Override
    protected IMainView createView() {
        return this;
    }

}