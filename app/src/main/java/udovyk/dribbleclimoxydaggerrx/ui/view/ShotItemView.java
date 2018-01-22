package udovyk.dribbleclimoxydaggerrx.ui.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.mvp.view.ViewModel;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.ui.adapters.ItemClickListener;

/**
 * Created by udovik.s on 19.01.2018.
 */

public class ShotItemView extends LinearLayout implements ViewModel<Shot> {
    private static final String TAG = "ShotItemView";
    ItemClickListener itemClickListener;

    @BindView(R.id.imShotItem)
    ImageView imShotItem;
    @BindView(R.id.tvShotTitle)
    TextView tvShotTitle;
    @BindView(R.id.tvShotAutor)
    TextView tvShotAutor;
    @BindView(R.id.tvShotDate)
    TextView tvShotDate;

    public ShotItemView(Context context) {
        super(context);
        init(context);
    }

    public ShotItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShotItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShotItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        //View view = inflate(getContext(), R.layout.shots_item, (ViewGroup) getRootView());
        LayoutInflater.from(context).inflate(R.layout.shots_item, this);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(Shot mData) {
        if (mData == null) {
            return;
        }

        Log.d(TAG, mData.getDescription() + "-------");
        Picasso.with(getContext())
                .load(mData.getImages().getNormal())
                .into(imShotItem);
        tvShotTitle.setText(mData.getTitle());
        tvShotAutor.setText(mData.getUser().getName());

        String dateRaw = mData.getCreatedAt();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat sdfF = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date;
        try {
            date = sdf.parse(dateRaw);
            String finalFormatedDate = sdfF.format(date);
            tvShotDate.setText(finalFormatedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.cvShot)
    public void onClick() {
        itemClickListener.onClick();
    }
    @OnClick(R.id.imShotItem)
    public void onClick1() {
        itemClickListener.onClick();
    }



    public void setClickListener(ItemClickListener clickListener) {
        this.itemClickListener = clickListener;
    }
}
