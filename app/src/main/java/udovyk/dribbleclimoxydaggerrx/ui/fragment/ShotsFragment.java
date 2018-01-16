package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotsView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class ShotsFragment extends BaseFragment implements ShotsView {

    @InjectPresenter
    ShotsPresenter presenter;

    @BindView(R.id.toolbar_shots)
    Toolbar mToolbar;


    public static ShotsFragment newInstance() {
        ShotsFragment fragment = new ShotsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareToolbar();
        showDrawerToggleButton(mToolbar);
    }

    private void prepareToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setVisibility(View.VISIBLE);
        mToolbar.setTitle("Shots");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.shots_fragment;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }
}
