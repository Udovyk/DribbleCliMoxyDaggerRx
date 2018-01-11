package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import udovyk.dribbleclimoxydaggerrx.di.components.ActivityComponent;
import udovyk.dribbleclimoxydaggerrx.di.components.FragmentComponent;
import udovyk.dribbleclimoxydaggerrx.di.modules.FragmentModule;
import udovyk.dribbleclimoxydaggerrx.mvp.view.BaseMvpView;
import udovyk.dribbleclimoxydaggerrx.ui.activity.BaseActivity;
import udovyk.dribbleclimoxydaggerrx.ui.widget.ToolbarActions;

/**
 * Created by udovik.s on 11.01.2018.
 */

public abstract class BaseFragment  extends MvpAppCompatFragment implements BaseMvpView {

    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fragmentComponent = getActivityComponent()
                .providesFragmentComponent(new FragmentModule(this));
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutRes(), container, false);
        injectViews(view);
        return view;
    }

    protected abstract int getLayoutRes();

    public abstract void injectDependencies();

    public void injectViews(View view) {
        ButterKnife.bind(this, view);
    }

    private ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity().getActivityComponent());
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    public void setSupportActionBar(Toolbar toolbar) {
        ((ToolbarActions) getActivity()).setSupportToolBar(toolbar);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void showMessage(String title, String message) {

    }

    @Override
    public void showMessage(String title) {

    }

    @Override
    public void showMessage(int title, int message) {

    }

    @Override
    public void showMessage(int title) {

    }

    @Override
    public void showToastMessage(int title) {

    }
}
