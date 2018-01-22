package udovyk.dribbleclimoxydaggerrx.ui.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import udovyk.dribbleclimoxydaggerrx.mvp.view.ViewModel;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;

/**
 * Created by udovik.s on 22.01.2018.
 */

public class LoadingItemView extends RelativeLayout implements ViewModel<Shot> {
    private static final String TAG = "ShotItemView";

    public LoadingItemView(Context context) {
        super(context);
    }

    public LoadingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setData(Shot data) {

    }
}
