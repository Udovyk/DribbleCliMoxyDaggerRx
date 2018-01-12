package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.OnClick;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.Utils;
import udovyk.dribbleclimoxydaggerrx.manager.PrefManager;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.StartScreenPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.StartScreenView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class StartScreenFragment extends BaseFragment implements StartScreenView {

    @InjectPresenter
    StartScreenPresenter presenter;

    @Inject
    PrefManager prefManager;

    public static StartScreenFragment newInstance() {
        StartScreenFragment fragment = new StartScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showNoInternetConnectionDialog() {
        Toast.makeText(getContext(), "No internet connection :(", Toast.LENGTH_SHORT).show();
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
    void onLoginButtonClicked() {
        if (Utils.isNetworkAwailable(getContext())) {
            presenter.goToLoginScreen();
        } else {
            presenter.showDialog();
        }
    }
}
