package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;

import udovyk.dribbleclimoxydaggerrx.mvp.presenter.LoginPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.LoginView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class LoginFragment extends BaseFragment implements LoginView{

    @InjectPresenter
    LoginPresenter presenter;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



}
