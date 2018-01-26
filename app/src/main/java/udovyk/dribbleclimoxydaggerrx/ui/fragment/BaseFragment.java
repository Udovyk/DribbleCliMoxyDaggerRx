package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import udovyk.dribbleclimoxydaggerrx.di.components.ActivityComponent;
import udovyk.dribbleclimoxydaggerrx.di.components.FragmentComponent;
import udovyk.dribbleclimoxydaggerrx.di.modules.FragmentModule;
import udovyk.dribbleclimoxydaggerrx.ui.activity.BaseActivity;
import udovyk.dribbleclimoxydaggerrx.ui.widget.ToolbarActions;

/**
 * Created by udovik.s on 11.01.2018.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    private FragmentComponent fragmentComponent;
    private Unbinder unbinder;

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    public void injectViews(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    public void hideToolBar() {
        ((BaseActivity) getActivity()).getSupportActionBar().hide();
    }

    public void showToolBar() {
        ((BaseActivity) getActivity()).getSupportActionBar().show();
    }

    private ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity()).getActivityComponent();
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    public Toolbar getToolBar() {
        return ((ToolbarActions) getActivity()).getToolBar();
    }

    public void showDrawerToggleButton() {
        ((ToolbarActions) getActivity()).showDrawerToggleButton();
    }

    public void showDrawerToggleButton(Toolbar toolbar) {
        ((ToolbarActions) getActivity()).showDrawerToggleButton(toolbar);
    }

    public void setSupportActionBar(Toolbar toolbar) {
        ((ToolbarActions) getActivity()).setSupportToolBar(toolbar);
    }

    protected abstract int getLayoutRes();

    public abstract void injectDependencies();

}
