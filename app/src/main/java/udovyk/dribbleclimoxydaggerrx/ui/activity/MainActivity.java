package udovyk.dribbleclimoxydaggerrx.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.MainActivityPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.MainActivityView;
import udovyk.dribbleclimoxydaggerrx.ui.widget.ToolbarActions;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class MainActivity extends BaseActivity implements MainActivityView, ToolbarActions{

    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.initScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.setNavigator(baseNavigator);
    }

    @Override
    public Toolbar getToolBar() {
        return null;
    }

    @Override
    public void setSupportToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void injectDependency() {
        getActivityComponent().inject(this);
    }
}
