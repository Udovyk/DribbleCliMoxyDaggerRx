package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.ShotsRequestConstants;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotsView;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.ui.adapters.ShotsAdapter;
import udovyk.dribbleclimoxydaggerrx.ui.utils.GridSpacingItemDecoration;
import udovyk.dribbleclimoxydaggerrx.ui.utils.PaginationScrollListener;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class ShotsFragment extends BaseFragment implements ShotsView {

    @InjectPresenter
    ShotsPresenter presenter;
    @Inject
    ShotsAdapter adapter;

    @BindView(R.id.toolbar_shots)
    Toolbar mToolbar;
    @BindView(R.id.rvShotsList)
    RecyclerView rvShotsList;
    @BindView(R.id.pbShots)
    ProgressBar pbShots;

    private int currentPage = ShotsRequestConstants.PAGE_START;
    private boolean isLoading = false;
    private String sortValue = ShotsRequestConstants.SORT_BY_DEFAULT;
    private int spanSizeDefault = 1;
    private int spanSizeItem = spanSizeDefault;
    private int spanSizeLoading = 2;
    private int spanCount = 2;

    public static ShotsFragment newInstance() {
        ShotsFragment fragment = new ShotsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), spanCount);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case ShotsAdapter.ITEM:
                        return spanSizeItem;

                    case ShotsAdapter.LOADING:
                        return spanSizeLoading;

                    default:
                        return spanSizeDefault;

                }
            }
        });
        rvShotsList.setLayoutManager(mLayoutManager);
        rvShotsList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rvShotsList.setItemAnimator(new DefaultItemAnimator());
        rvShotsList.setAdapter(adapter);

        //if RV was scrolled
        rvShotsList.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadShotsPage(currentPage, sortValue);
                    }
                }, 1000);

            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        presenter.loadShotsPage(currentPage, sortValue);

        return v;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareToolbar();
        showDrawerToggleButton(mToolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort_shots, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            //make sort by populariry
            case R.id.sort_popular:
                sortValue = ShotsRequestConstants.SORT_BY_POPULARITY;
                adapter.getShotsList().clear();
                adapter.notifyDataSetChanged();
                currentPage = 1;
                presenter.loadShotsPage(currentPage, sortValue);
                return true;

            //make sort by views
            case R.id.sort_most_viewed:
                sortValue = ShotsRequestConstants.SORT_BY_VIEWS;
                adapter.getShotsList().clear();
                adapter.notifyDataSetChanged();
                currentPage = 1;
                presenter.loadShotsPage(currentPage, sortValue);
                return true;

            //make sort by comments
            case R.id.sort_most_commented:
                sortValue= ShotsRequestConstants.SORT_BY_COMMENTS;
                adapter.getShotsList().clear();
                adapter.notifyDataSetChanged();
                currentPage = 1;
                presenter.loadShotsPage(currentPage, sortValue);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showLoadingPb() {
        pbShots.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingPb() {
        pbShots.setVisibility(View.GONE);
    }

    @Override
    public void addAll(List<Shot> list) {
        adapter.addAll(list);
    }

    @Override
    public void addLoadingFooter() {
        adapter.addLoadingFooter();
    }

    @Override
    public void removeLoadingFooter() {
        adapter.removeLoadingFooter();
    }

    @Override
    public void isLoading(boolean flag) {
        isLoading = flag;
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
