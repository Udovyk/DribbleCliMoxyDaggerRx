package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

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

    //region di
    @InjectPresenter
    ShotDetailsPresenter presenter;
    //endregion

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

    public static ShotDetailsFragment newInstance(Bundle bundle) {
        ShotDetailsFragment fragment = new ShotDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        presenter.setData(bundle);

        RxView.clicks(shotImage).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(empty -> {
            Bundle data = new Bundle();
            data.putString(ShotDetailConstants.IMAGE_URL, bundle.getString(ShotDetailConstants.IMAGE_URL));
            data.putInt(ShotDetailConstants.ID, bundle.getInt(ShotDetailConstants.ID));
            presenter.onItemClick(data);
        });

        return v;
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
        } else {
            Log.d(TAG, "-----Bundle == null ----");
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
