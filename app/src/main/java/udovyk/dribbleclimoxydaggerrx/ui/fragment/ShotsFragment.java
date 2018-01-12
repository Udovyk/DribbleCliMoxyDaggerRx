package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
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

    /*@InjectPresenter
    ShotsPresenter presenter;*/

    @BindView(R.id.test)
    TextView textView;

    public static ShotsFragment newInstance() {
        ShotsFragment fragment = new ShotsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.test_layout;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }
}
