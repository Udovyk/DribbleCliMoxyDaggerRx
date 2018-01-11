package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.OnClick;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.StartScreenPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.StartScreenView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class StartScreenFragment extends BaseFragment implements StartScreenView{

    @InjectPresenter
    StartScreenPresenter presenter;

    public static StartScreenFragment newInstance() {
        StartScreenFragment fragment = new StartScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_start_screen;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }

    @OnClick(R.id.btnLogin)
    void onLoginButtonClicked() { presenter.goToLoginScreen();}
}
