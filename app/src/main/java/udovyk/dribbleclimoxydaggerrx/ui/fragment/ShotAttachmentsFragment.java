package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.ShotDetailConstants;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotAttachmentsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotAttachmentsView;
import udovyk.dribbleclimoxydaggerrx.network.model.Attachment;
import udovyk.dribbleclimoxydaggerrx.ui.adapters.ViewPagerAdapter;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class ShotAttachmentsFragment extends BaseFragment implements ShotAttachmentsView, ViewPager.OnPageChangeListener {
    public static final String TAG = "ShotAttachmentsFragment";

    //region di, bind
    @InjectPresenter
    ShotAttachmentsPresenter presenter;

    @BindView(R.id.tv_attachment_count)
    TextView tvAttachmentsCount;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

   /* @BindView(R.id.pb_attachments)
    ProgressBar progressBar;*/
    //endregion

    private List<Attachment> mData = new ArrayList<>();
    protected int mPreviousPos = 0;

    protected ViewPagerAdapter mAdapter;

    private Bundle bundle;

    public static ShotAttachmentsFragment newInstance(Bundle bundle) {
        ShotAttachmentsFragment fragment = new ShotAttachmentsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        savedInstanceState = getArguments();
        bundle = savedInstanceState;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        int idOfShot = bundle.getInt(ShotDetailConstants.ID);

        presenter.addAttachmentsToList(mData, idOfShot);

        return v;
    }

    public void showViewPager() {

        if (mData == null) {
            Log.d(TAG, "---list of attachments == null ----");
            return;
        }

        mAdapter = new ViewPagerAdapter(getFragmentManager());

        for (Attachment model : mData) {
            mAdapter.addFragment(ItemViewerFragment.newInstance(model));
        }

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPreviousPos, false);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            ((ItemViewerFragment) mAdapter.getItem(mPreviousPos)).imHiddenNow();
            ((ItemViewerFragment) mAdapter.getItem(position)).imVisibleNow();

        } catch (Exception e) {
            e.printStackTrace();
        }

        mPreviousPos = position;
        tvAttachmentsCount.setText(position + 1 + "/" + mData.size());
    }

    @Override
    public void onPageSelected(int position) {

        tvAttachmentsCount.setText(position + 1 + "/" + mData.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View decorView = ((AppCompatActivity) getActivity()).getWindow().getDecorView();
        int uiOptions = View.FOCUSABLES_ALL;
        decorView.setSystemUiVisibility(uiOptions);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.activity_main_update_menu_item);
        menuItem.setVisible(false);
    }

    @Override
    public void hideStatusBar() {
        View decorView = ((AppCompatActivity) getActivity()).getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    public TextView getTvAttachmentsCount() {
        return tvAttachmentsCount;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.swipe_attachments_fragment;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void showProgressbar() {
        ((ProgressActivityListener) getActivity()).showPb();
    }

    @Override
    public void hideProgressbar() {
        ((ProgressActivityListener) getActivity()).hidePb();
    }
}
