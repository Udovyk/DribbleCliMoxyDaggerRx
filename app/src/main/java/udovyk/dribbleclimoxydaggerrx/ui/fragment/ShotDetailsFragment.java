package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.ShotDetailConstants;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotDetailsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotDetailView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class ShotDetailsFragment extends BaseFragment implements ShotDetailView {
    public static final String TAG = "ShotDetailFragment";

    @InjectPresenter
    ShotDetailsPresenter presenter;

    //region views
    @BindView(R.id.shot_detail_image)
    ImageView shotImage;
    @BindView(R.id.shot_detail_title)
    TextView title;
    @BindView(R.id.shot_detail_tv_likes_count)
    TextView likesCount;
    @BindView(R.id.shot_detail_tv_views_count)
    TextView viewsCount;
    @BindView(R.id.shot_detail_tv_comments_count)
    TextView commentsCount;
    @BindView(R.id.shot_detail_description)
    TextView description;
    //endregion

    Bundle bundle;

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
        presenter.setData(bundle);
        return v;
    }

    @OnClick (R.id.shot_detail_image)
    void showFullImageFragment() {
        /*if (getFragmentManager().findFragmentByTag(ShotAttachmentsFragment.TAG) == null) {
            ShotAttachmentsFragment shotFullImageFragment = new ShotAttachmentsFragment();
            Bundle bundleLocal = new Bundle();
            bundleLocal.putString(ShotDetailConstants.IMAGE_URL, bundle.getString(ShotDetailConstants.IMAGE_URL));
            bundleLocal.putInt(ShotDetailConstants.ID, bundle.getInt(ShotDetailConstants.ID));
            shotFullImageFragment.setArguments(bundleLocal);
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                    .add(R.id.drawer_layout, shotFullImageFragment, ShotAttachmentsFragment.TAG)
                    .addToBackStack(null)
                    .commit();

        }*/
    }

    @Override
    public void setData(Bundle bundle) {
        if (bundle != null) {
            Picasso.with(getContext())
                    .load(bundle.getString(ShotDetailConstants.IMAGE_URL))
                    .into(shotImage);
            title.setText(bundle.getString(ShotDetailConstants.TITLE));
            likesCount.setText(Integer.toString(bundle.getInt(ShotDetailConstants.LIKES_COUNT)));
            viewsCount.setText(Integer.toString(bundle.getInt(ShotDetailConstants.VIEWS_COUNT)));
            commentsCount.setText(Integer.toString(bundle.getInt(ShotDetailConstants.COMMENTS_COUNT)));
            try {
                description.setText(Html.fromHtml(bundle.getString(ShotDetailConstants.DESCRIPTION)).toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
                description.setText("");
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.shot_detail_layout;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }
}
