package udovyk.dribbleclimoxydaggerrx.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import butterknife.BindView;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.ShotDetailConstants;
import udovyk.dribbleclimoxydaggerrx.mvp.presenter.ShotAttachmentsPresenter;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ShotAttachmentsView;

/**
 * Created by udovik.s on 10.01.2018.
 */

public class ShotAttachmentsFragment extends BaseFragment implements ShotAttachmentsView {
    public static final String TAG = "ShotAttachmentsFragment";

    @InjectPresenter
    ShotAttachmentsPresenter presenter;

    @BindView(R.id.tv_attachment_count)
    TextView tvAttachmentsCount;
    @BindView(R.id.pb_attachments)
    ProgressBar pbAttachments;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<String> listOfAttachments = new ArrayList<>();

    ImagePagerAdapter adapter;

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

        presenter.addAttachmentsToList(listOfAttachments, idOfShot);

        return v;
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

    @Override
    public void showViewPager() {

        adapter = new ImagePagerAdapter(listOfAttachments);
        viewPager.setOffscreenPageLimit(listOfAttachments.size() - 1);
        Log.d(TAG, "--list consists of next elements: ");
        for (String s : listOfAttachments) {
            Log.d(TAG, s);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvAttachmentsCount.setText(position + 1 + "/" + listOfAttachments.size());
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        presenter.hideStatusBar();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.swipe_attachments_fragment;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }


    private class ImagePagerAdapter extends PagerAdapter {
        private ArrayList<String> mImages;

        public ImagePagerAdapter(ArrayList<String> mas) {
            this.mImages = mas;
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = getContext();
            ImageView imageView = new ImageView(context);

            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (position != 0) {
                pbAttachments.setVisibility(View.VISIBLE);
            }
            Glide.with(getContext())
                    .load(mImages.get(position))
                    .thumbnail(0.3f)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            pbAttachments.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);

            ((ViewPager) container).addView(imageView, 0);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}
